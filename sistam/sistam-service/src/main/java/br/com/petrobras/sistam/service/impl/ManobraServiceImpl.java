package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.ManobraService;
import br.com.petrobras.sistam.common.business.PendenciaService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.exception.SistamPendingException;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioManobra;
import br.com.petrobras.sistam.common.valueobjects.RelatorioManobraVO;
import br.com.petrobras.sistam.service.query.manobra.ConsultaFinalidadePorManobraId;
import br.com.petrobras.sistam.service.query.manobra.ConsultaManobraPorAgenciamento;
import br.com.petrobras.sistam.service.query.manobra.ConsultaManobraPorId;
import br.com.petrobras.sistam.service.query.manobra.ConsultaManobrasEncerradasOuRegistradasOuPreRegistradasPorAgenciamento;
import br.com.petrobras.sistam.service.query.manobra.ConsultaManobrasPorFiltro;
import br.com.petrobras.sistam.service.query.manobra.ConsultaServicoManobraPorId;
import br.com.petrobras.sistam.service.query.manobra.ConsultaServicoResponsavelPorId;
import br.com.petrobras.sistam.service.query.manobra.ConsultaServicoResponsavelPorManobra;
import br.com.petrobras.sistam.service.validator.ValidadorManobra;
import br.com.petrobras.snarf.common.exception.Pendency;
import br.com.petrobras.snarf.persistence.GenericDao;
import br.com.petrobras.snarf.persistence.TransactionCallback;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ManobraServiceImpl implements ManobraService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private GenericDao dao;
    private NotesWebServiceImpl notesWebService;
    @Autowired
    private ValidadorManobra validador;
    @Autowired
    private ValidadorPermissao validadorPermissao;
    private PendenciaService pendenciaService;

    public void setDao(GenericDao dao) {
        this.dao = dao;
    }

    public void setNotesWebService(NotesWebServiceImpl notesWebService) {
        this.notesWebService = notesWebService;
    }

    public void setPendenciaService(PendenciaService pendenciaService) {
        this.pendenciaService = pendenciaService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Manobra> buscarManobrasPorAgenciamento(Agenciamento agenciamento) {
        return dao.list(new ConsultaManobraPorAgenciamento(agenciamento));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Manobra> buscarManobrasEncerradasOuRegistradasOuPreRegistradasPorAgenciamento(Agenciamento agenciamento) {
        return dao.list(new ConsultaManobrasEncerradasOuRegistradasOuPreRegistradasPorAgenciamento(agenciamento));
    }

    @Transactional(readOnly = true)
    @Override
    public Manobra buscarManobrasPorId(Long id) {
        return dao.uniqueResult(new ConsultaManobraPorId(id));
    }

    @Transactional(readOnly = true)
    @Override
    public ServicoManobra buscarServicoManobrasPorId(Long id) {
        return dao.uniqueResult(new ConsultaServicoManobraPorId(id));
    }
    
    @Transactional(readOnly = false)
    @Override
    public List<ServicoResponsavel> buscarServicoResponsavelPorManobra(Manobra manobra) {
        return dao.list(new ConsultaServicoResponsavelPorManobra(manobra));
    }

    @Transactional(readOnly = true)
    @Override
    public ServicoResponsavel buscarServicoResponsavelPorId(Long id) {
        return dao.uniqueResult(new ConsultaServicoResponsavelPorId(id));
    }

    @Transactional(readOnly = false)
    @Override
    public Manobra salvarManobra(Manobra manobra) {
        if (manobra.getStatus() == null) {
            manobra.setStatus(StatusManobra.PLANEJADA);
        }
        AssertUtils.assertExpression(validadorPermissao.podeSalvarManobra(manobra.getAgenciamento().getAgencia()), ConstantesI18N.MANOBRA_SEM_PERMISSAO_INFORMAR_MANOBRA);
        validador.validarCamposObrigatoriosManobra(manobra);
        validador.validarSalvarManobra(manobra);

        //Salva a manobra
        boolean novaManobra = manobra.getId() == null ? true : false;
        dao.saveOrUpdate(manobra);

        //Se for uma nova manobra, salva todos os serviços
        if (novaManobra) {
            salvarServicosEParadasDaNovaManobra(manobra);
        }
        return manobra;
    }

    @Override
    public void solicitarServicoManobra(final Manobra manobra) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarManobra(manobra.getAgenciamento().getAgencia()), ConstantesI18N.MANOBRA_SEM_PERMISSAO_INFORMAR_MANOBRA);
        AssertUtils.assertExpression(validadorPermissao.podeEnviarSolicitacaoApoioManobra(manobra.getAgenciamento().getAgencia()), ConstantesI18N.MANOBRA_SEM_PERMISSAO_INFORMAR_MANOBRA);

        /*
         * A manobra deve ser salva antes de proceder a solicitação dos serviços
         * Se algum erro ocorrer neste momento, os serviços não devem ser solicitados
         */
        dao.runInsideCustomTransaction(new TransactionCallback() {
            @Override
            public Object doInsideTransaction() {
                return salvarManobra(manobra);
            }
        });

        /*
         * Uma transação é aberta para cada serviço que está sendo solicitado.
         * Um erro no envio ou na alteração da data de um serviço não deve afetar os demais
         * Ao final do processo, se algum problema ocorreu em algum serviço, deve ser lançada uma exceção informando.
         */
        List<SistamPendingException> listaExcecao = new ArrayList<SistamPendingException>();
        StringBuffer erros = new StringBuffer();
        for (final ServicoManobra servico : manobra.getServicos()) {
            try {
                if (servico.getDataEnvio() == null) {
                    dao.runInsideCustomTransaction(new TransactionCallback() {
                        @Override
                        public Object doInsideTransaction() {
                            notesWebService.enviarSolicitacaoApoioManobra(servico);
                            servico.setDataEnvio(new Date());
                            dao.saveOrUpdate(servico);
                            return servico;
                        }
                    });

                }
            } catch (SistamPendingException ex) {
                listaExcecao.add(ex);
            } catch (Exception ex) {
                String erro = "Erro ao enviar solicitação para o servico " + servico.getTipoDoServico() + "\n" + SistamUtils.stackTraceToString(ex);
                erros.append(erro);
                logger.error(erro, ex);
            }
        }

        /*
         * Após o processo de envio de solicitação dos serviços a manobra deve mudar para o status de SOLICITADA
         */
        dao.runInsideCustomTransaction(new TransactionCallback() {
            @Override
            public Object doInsideTransaction() {
                manobra.setStatus(StatusManobra.SOLICITADA);
                return salvarManobra(manobra);
            }
        });

        /*
         * Algum erro não esperado pode ter ocorrido no envio das solicitações.
         * Neste momento os erros são lançados de volta para quem chamou.
         */
        if (!erros.toString().equals("")) {
            throw new RuntimeException(erros.toString());
        }

        /*
         * Varre todas as exceções esperadas e armazena todas em uma única exceção.
         */
        if (!listaExcecao.isEmpty()) {
            SistamPendencyManager pm = new SistamPendencyManager();
            for (SistamPendingException spe : listaExcecao) {
                for (TipoExcecao te : spe.getPendingMap().keySet()) {
                    for (Pendency pendency : spe.getPendingMap().get(te)) {
                        pm.assertThat(false).orRegister(te, pendency.getMessage());
                    }
                }
            }
            pm.verifyAll();
        }


    }

    @Transactional(readOnly = false)
    @Override
    public Manobra registrarManobra(Manobra manobra) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarManobra(manobra.getAgenciamento().getAgencia()), ConstantesI18N.MANOBRA_SEM_PERMISSAO_INFORMAR_MANOBRA);
        FinalidadeManobra finalidadeManobra = obterFinalidadePorManobraId(manobra.getId());
        if (manobra.isPreRegistrada()) {
            AssertUtils.assertExpression(finalidadeManobra.equals(manobra.getFinalidadeManobra()), ConstantesI18N.MANOBRA_JA_MOVIMENTADA_NAO_PODE_REGISTRAR);
        }

        manobra.setStatus(StatusManobra.REGISTRADA);

        validador.validarCamposObrigatoriosManobra(manobra);
        validador.validarRegistrarManobra(manobra);

        //Salva a manobra
        boolean novaManobra = manobra.getId() == null ? true : false;
        dao.saveOrUpdate(manobra);

        //Se for uma nova manobra, salva todos os serviços e todas as paradas
        if (novaManobra) {
            salvarServicosEParadasDaNovaManobra(manobra);
        }

        return manobra;
    }

    @Transactional(readOnly = false)
    @Override
    public Manobra cancelarManobraForaDoProazo(Manobra manobra, String motivoDoCancelamento) {
        //Valida a permissão.
        AssertUtils.assertExpression(validadorPermissao.podeSalvarManobra(manobra.getAgenciamento().getAgencia()), ConstantesI18N.MANOBRA_SEM_PERMISSAO_INFORMAR_MANOBRA);

        manobra.setStatus(StatusManobra.CANCELADA_FORA_PRAZO);

        //Valida as informações.
        validador.validarCamposObrigatoriosManobra(manobra);
        validador.validarCancelarManobraForaDoPrazo(manobra, motivoDoCancelamento);

        //Atualiza a manobra
        manobra.setMotivoDoCancelamento(motivoDoCancelamento);

        //Salva a manobra
        boolean novaManobra = manobra.getId() == null ? true : false;
        dao.saveOrUpdate(manobra);

        //Se for uma nova manobra, salva todos os serviços e todas as paradas
        if (novaManobra) {
            salvarServicosEParadasDaNovaManobra(manobra);
        }

        return manobra;
    }

    private void salvarServicosEParadasDaNovaManobra(Manobra manobra) {
        for (ServicoManobra servico : manobra.getServicos()) {
            servico.setManobra(manobra);
            salvarServicoManobra(servico);
        }


    }

    @Transactional(readOnly = false)
    @Override
    public Manobra movimentarManobra(Manobra manobra) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarManobra(manobra.getAgenciamento().getAgencia()), ConstantesI18N.MANOBRA_SEM_PERMISSAO_INFORMAR_MANOBRA);
        manobra.setStatus(StatusManobra.PRE_REGISTRADA);

        validador.validarMovimentarManobra(manobra);
        manobra.setMovimentada(true);

        boolean novaManobra = manobra.getId() == null ? true : false;
        dao.saveOrUpdate(manobra);

        //Se for uma nova manobra, salva todos os serviços
        if (novaManobra) {
            salvarServicosEParadasDaNovaManobra(manobra);
        }
        
        if (!StatusEmbarcacao.SAIDO.equals(manobra.getAgenciamento().getStatusEmbarcacao())
                && !StatusEmbarcacao.DESVIADO.equals(manobra.getAgenciamento().getStatusEmbarcacao())
                && !StatusEmbarcacao.CANCELADO.equals(manobra.getAgenciamento().getStatusEmbarcacao())) {

            Agenciamento agenciamento = manobra.getAgenciamento();

            //Só atualiza o status da embarcação se a finalidade NÃO for SHIP TO SHIP nem NAVEGACAO
            if (!(FinalidadeManobra.SHIP_TO_SHIP.equals(manobra.getFinalidadeManobra())
                    || FinalidadeManobra.NAVEGACAO.equals(manobra.getFinalidadeManobra()))) {
                agenciamento.setStatusEmbarcacao(manobra.getFinalidadeManobra().statusEmbarcacao());
            }
            dao.saveOrUpdate(agenciamento);
        }

        return manobra;
    }

    @Transactional(readOnly = false)
    @Override
    public Manobra encerrarManobra(Manobra manobra, Porto portoDestino, Date eta) {
        //Salva a manobra logo para atachar à sessão. Não é preciso salvar novamente.
        dao.saveOrUpdate(manobra);

        AssertUtils.assertExpression(validadorPermissao.podeSalvarManobra(manobra.getAgenciamento().getAgencia()), ConstantesI18N.MANOBRA_SEM_PERMISSAO_INFORMAR_MANOBRA);
        validador.validarEncerrarManobra(manobra, portoDestino, eta);

        manobra.setStatus(StatusManobra.ENCERRADA);

        if (!manobra.isResponsavelCustoSemCusto()) {
            if (!StatusEmbarcacao.SAIDO.equals(manobra.getAgenciamento().getStatusEmbarcacao())
                    && !StatusEmbarcacao.DESVIADO.equals(manobra.getAgenciamento().getStatusEmbarcacao())
                    && !StatusEmbarcacao.CANCELADO.equals(manobra.getAgenciamento().getStatusEmbarcacao())) {

                atualizarAgenciamento(manobra, portoDestino, eta);

                //Só gera as pendências quando o tipo de contrato do agenciamento fot TCP
                if (TipoContrato.TCP.equals(manobra.getAgenciamento().getTipoContrato())) {
                    gerarPendenciasDoAgenciamentoNoEncerramentoDaManobra(manobra);
                }
            }
        }

        return manobra;
    }

    private void atualizarAgenciamento(Manobra manobra, Porto portoDestino, Date eta) {
        Agenciamento agenciamento = manobra.getAgenciamento();


        //Só atualiza o status da embarcação se a finalidade NÃO for SHIP TO SHIP nem NAVEGACAO
        if (!(FinalidadeManobra.SHIP_TO_SHIP.equals(manobra.getFinalidadeManobra())
                || FinalidadeManobra.NAVEGACAO.equals(manobra.getFinalidadeManobra()))) {
            if (!manobra.getMovimentada().booleanValue()) {
                agenciamento.setStatusEmbarcacao(manobra.getFinalidadeManobra().statusEmbarcacao());
            }
        }


        if (FinalidadeManobra.TROCA_PORTO.equals(manobra.getFinalidadeManobra())) {
            agenciamento.setEtaProximoPorto(manobra.getDataChegadaDestino());
            agenciamento.setPortoDestino(manobra.getPontoAtracacaoDestino().getPontoOperacional().getPorto());
        } else if (FinalidadeManobra.DESATRACACAO_SAIDA.equals(manobra.getFinalidadeManobra())
                || FinalidadeManobra.SAIDA_FUNDEIO.equals(manobra.getFinalidadeManobra())) {

            agenciamento.setPortoDestino(portoDestino);
            agenciamento.setEtaProximoPorto(eta);
        }

        if (FinalidadeManobra.DESATRACACAO_SAIDA.equals(manobra.getFinalidadeManobra())
                || FinalidadeManobra.SAIDA_FUNDEIO.equals(manobra.getFinalidadeManobra())
                || FinalidadeManobra.TROCA_PORTO.equals(manobra.getFinalidadeManobra())) {

            agenciamento.setDataSaida(manobra.getDataPartidaOrigem());
            agenciamento.setCaladoSaidaVante(manobra.getCaladoVante());
            agenciamento.setCaladoSaidaRe(manobra.getCaladoRe());
        }

        dao.saveOrUpdate(agenciamento);
    }

    private void gerarPendenciasDoAgenciamentoNoEncerramentoDaManobra(Manobra manobra) {
        if (FinalidadeManobra.DESATRACACAO_SAIDA.equals(manobra.getFinalidadeManobra())
                || FinalidadeManobra.SAIDA_FUNDEIO.equals(manobra.getFinalidadeManobra())) {

            pendenciaService.criarPendencia(manobra.getAgenciamento(), TipoPendencia.PARTE_SAIDA);

            if (manobra.getAgenciamento().possuiOperacaoCarga()) {
                pendenciaService.criarPendencia(manobra.getAgenciamento(), TipoPendencia.DOCUMENTACAO_OPERACAO_CARGA_CABOTAGEM);
            }
        } else {
            pendenciaService.criarPendencia(manobra.getAgenciamento(), TipoPendencia.REGISTRO_MOVIMENTACAO);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Serviço Manobra">
    @Transactional(readOnly = false)
    @Override
    public ServicoManobra salvarServicoManobra(ServicoManobra servicoManobra) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarManobra(servicoManobra.getManobra().getAgenciamento().getAgencia()), ConstantesI18N.MANOBRA_SEM_PERMISSAO_ENVIAR_SOLICITACAO_MANOBRA);
        validarSalvarServicoManobra(servicoManobra);

        boolean novoServico = servicoManobra.getId() == null ? true : false;
        dao.saveOrUpdate(servicoManobra);

        if (novoServico) {
            for (ServicoResponsavel responsavel : servicoManobra.getResponsaveis()) {
                responsavel.setServicoManobra(servicoManobra);
                salvarResponsavel(responsavel);
            }
        }

        return servicoManobra;
    }

    @Transactional(readOnly = false)
    @Override
    public void excluirServicoManobra(ServicoManobra servicoManobra) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarManobra(servicoManobra.getManobra().getAgenciamento().getAgencia()), ConstantesI18N.MANOBRA_SEM_PERMISSAO_INFORMAR_MANOBRA);
        validador.validarExclusaoServicoManobra(servicoManobra);

        servicoManobra = dao.get(ServicoManobra.class, servicoManobra.getId());

        for (ServicoResponsavel responsavel : servicoManobra.getResponsaveis()) {
            dao.remove(responsavel);
        }

        dao.remove(servicoManobra);
    }

    @Override
    public void validarSalvarServicoManobra(ServicoManobra servicoManobra) {
        validador.validarSalvarServicoManobra(servicoManobra);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Serviço Responsável">
    @Transactional(readOnly = false)
    @Override
    public ServicoResponsavel salvarResponsavel(ServicoResponsavel responsavel) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarManobra(responsavel.getServicoManobra().getManobra().getAgenciamento().getAgencia()), ConstantesI18N.MANOBRA_SEM_PERMISSAO_ENVIAR_SOLICITACAO_MANOBRA);
        validarSalvarResposnavel(responsavel);

        dao.saveOrUpdate(responsavel);

        return responsavel;
    }

    @Transactional(readOnly = false)
    @Override
    public ServicoResponsavel excluirResponsavel(ServicoResponsavel responsavel) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarManobra(responsavel.getServicoManobra().getManobra().getAgenciamento().getAgencia()), ConstantesI18N.MANOBRA_SEM_PERMISSAO_ENVIAR_SOLICITACAO_MANOBRA);
        validador.validarExclusaoDeResponsavel(responsavel);

        dao.remove(responsavel);

        return responsavel;
    }

    @Override
    public void validarSalvarResposnavel(ServicoResponsavel responsavel) {
        validador.validarSalvarResponsavel(responsavel);
    }
    //</editor-fold>

    @Transactional(readOnly = false)
    @Override
    public void cancelarManobraDentroPrazo(Manobra manobra, String motivo) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarManobra(manobra.getAgenciamento().getAgencia()), ConstantesI18N.MANOBRA_SEM_PERMISSAO_CANCELAR_DENTRO_PRAZO);
        validador.validarCancelarManobraDentroPrazo(manobra, motivo);

        manobra.setMotivoDoCancelamento(motivo);
        manobra.setStatus(StatusManobra.CANCELADA);
        dao.saveOrUpdate(manobra);

    }

    @Transactional(readOnly = false)
    @Override
    public List<RelatorioManobraVO> buscarManobrasParaRelatorio(FiltroRelatorioManobra filtro) {
        List<Manobra> manobras = dao.list(new ConsultaManobrasPorFiltro(filtro));
        List<RelatorioManobraVO> vos = new ArrayList<RelatorioManobraVO>();

        for (Manobra manobra : manobras) {
            final Agenciamento agenciamento = manobra.getAgenciamento();

            RelatorioManobraVO vo = new RelatorioManobraVO();
            vo.setFinalidade(manobra.getFinalidadeManobra());
            vo.setNavio(agenciamento.getEmbarcacao());
            vo.setObservacao(manobra.getObservacaoInterna());
            vo.setPontoInicio(manobra.getPontoAtracacaoOrigem());
            vo.setPontoFim(manobra.getPontoAtracacaoDestino());
            vo.setPontoOperacional(vo.getPontoInicio().getPontoOperacional());
            vo.setTipoContrato(agenciamento.getTipoContrato());
            vo.setDataInicioReal(manobra.getDataInicio());
            vo.setDataFimReal(manobra.getDataTermino());
            vo.setDataInicioOperacao(manobra.getDataInicioOperacao());
            vo.setDataFimOperacao(manobra.getDataFimOperacao());
            vo.setNumeroViagem(agenciamento.getVgm());

            List<ServicoResponsavel> servicos = dao.list(new ConsultaServicoResponsavelPorManobra(manobra));
            for (ServicoResponsavel servicoResponsavel : servicos) {
                if (TipoServico.PRATICOS.equals(servicoResponsavel.getServicoManobra().getTipoDoServico())) {
                    vo.getPraticos().add(servicoResponsavel);
                }
                if (TipoServico.REBOCADORES.equals(servicoResponsavel.getServicoManobra().getTipoDoServico())) {
                    vo.getRebocadores().add(servicoResponsavel);
                }
            }

            vos.add(vo);
        }

        return vos;
    }

    public FinalidadeManobra obterFinalidadePorManobraId(Long id) {
        return dao.uniqueResult(new ConsultaFinalidadePorManobraId(id));
    }
}
