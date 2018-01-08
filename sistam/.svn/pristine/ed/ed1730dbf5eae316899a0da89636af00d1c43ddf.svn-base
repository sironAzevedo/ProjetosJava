package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.beans.CredentialsBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.AcessoService;
import br.com.petrobras.sistam.common.business.PessoaProtecaoService;
import br.com.petrobras.sistam.common.business.ServicoProtecaoService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Hospede;
import br.com.petrobras.sistam.common.entity.Passageiro;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.entity.ServicoExecutado;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.entity.ServicoMedicoOdontologico;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransito;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoEmpresa;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoVeiculo;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoHospedagem;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoTransporte;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioServicoSuprimentoAosNavios;
import br.com.petrobras.sistam.common.valueobjects.PessoaAcessoVO;
import br.com.petrobras.sistam.service.query.agenciamento.protecao.ConsultaServicoAcessoPessoaDoAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.protecao.ConsultaServicoAcessoPessoaPorId;
import br.com.petrobras.sistam.service.query.agenciamento.protecao.ConsultaServicoHospedagemDoAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.protecao.ConsultaServicoHospedagemPorId;
import br.com.petrobras.sistam.service.query.agenciamento.protecao.ConsultaServicoMedicoOdontologicoDoAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.protecao.ConsultaServicoProtecaoDoAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.protecao.ConsultaServicoRetiradaResiduoLixoDoAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.protecao.ConsultaServicoRetiradaResiduoLixoPorId;
import br.com.petrobras.sistam.service.query.agenciamento.protecao.ConsultaServicoSuprimentoDoAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.protecao.ConsultaServicoSuprimentoPorId;
import br.com.petrobras.sistam.service.query.agenciamento.protecao.ConsultaServicoSuprimentoTransitoEmpresa;
import br.com.petrobras.sistam.service.query.agenciamento.protecao.ConsultaServicoTransporteDoAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.protecao.ConsultaServicoTransportePorId;
import br.com.petrobras.sistam.service.validator.ValidadorServicoProtecao;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ServicoProtecaoServiceImpl implements ServicoProtecaoService {

    private GenericDao dao;
    private AcessoService acessoService;
    private PessoaProtecaoService pessoaProtecaoService;
    private NotesWebServiceImpl notesWebService;
    @Autowired
    private ValidadorPermissao validadorPermissao;
    @Autowired
    private ValidadorServicoProtecao validadorServicoProtecao;

    public ServicoProtecaoServiceImpl(GenericDao dao) {
        this.dao = dao;
    }

    public void setAcessoService(AcessoService acessoService) {
        this.acessoService = acessoService;
    }

    public void setPessoaProtecaoService(PessoaProtecaoService pessoaProtecaoService) {
        this.pessoaProtecaoService = pessoaProtecaoService;
    }

    public void setNotesWebService(NotesWebServiceImpl notesWebService) {
        this.notesWebService = notesWebService;
    }

    //<editor-fold defaultstate="collapsed" desc="Serviço Proteção">
    @Transactional(readOnly = false)
    @Override
    public ServicoProtecao confirmarEnvioEmailServicoProtecao(ServicoProtecao servicoProtecao) {
        if (BooleanUtils.isTrue(servicoProtecao.getEmailEnviado())) {
            return servicoProtecao;
        }
        servicoProtecao.setEmailEnviado(true);
        servicoProtecao.setDataEmailEnviado(new Date());
        return dao.saveOrUpdate(servicoProtecao);
    }

    @Transactional(readOnly = false)
    @Override
    public ServicoProtecao salvarServicoProtecao(ServicoProtecao servicoProtecao) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarServicoProtecao(servicoProtecao.getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_PROTECAO_SEM_PERMISSAO_SALVAR);
        validadorServicoProtecao.validarSalvarServicoProtecao(servicoProtecao);

        CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();

        if (servicoProtecao.getId() == null) {
            servicoProtecao.setNovo(true);
            servicoProtecao.setChaveUsuarioInclusao(credentialsBean.getLogon());
            servicoProtecao.setNomeUsuarioInclusao(credentialsBean.getUsername());
        } else {
            servicoProtecao.setNovo(false);
            servicoProtecao.setDataAlteracao(new Date());
            servicoProtecao.setChaveUsuarioAlteracao(credentialsBean.getLogon());
            servicoProtecao.setNomeUsuarioAlteracao(credentialsBean.getUsername());
        }

        dao.saveOrUpdate(servicoProtecao);

        return servicoProtecao;
    }

    @Transactional(readOnly = false)
    @Override
    public void excluirServicoProtecao(ServicoProtecao servicoProtecao) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarServicoProtecao(servicoProtecao.getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_PROTECAO_SEM_PERMISSAO_EXCLUIR);
        dao.remove(servicoProtecao);
    }

    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Serviço Executado">
    @Transactional(readOnly = false)
    @Override
    public ServicoExecutado salvarServicoExecutado(ServicoExecutado servicoExecutado) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarServicoProtecao(servicoExecutado.getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_PROTECAO_SEM_PERMISSAO_SALVAR);
        validadorServicoProtecao.validarSalvarServicoProtecao(servicoExecutado.getServicoProtecao());

        dao.saveOrUpdate(servicoExecutado.getServicoProtecao());

        salvarServicoProtecaoMedicoOdontologico(servicoExecutado);
        salvarServicoProtecaoTransporte(servicoExecutado);
        salvarServicoProtecaoHospedagem(servicoExecutado);
        salvarServicoProtecaoAcessoPessoa(servicoExecutado);
        salvarServicoProtecaoSuprimentoAosNavios(servicoExecutado);
        salvarServicoProtecaoRetiradaResiduoLixo(servicoExecutado);

        return servicoExecutado;


    }

    @Transactional(readOnly = false)
    @Override
    public void excluirServicoExecutado(ServicoExecutado servicoExecutado) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarServicoProtecao(servicoExecutado.getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_PROTECAO_SEM_PERMISSAO_SALVAR);
        dao.remove(servicoExecutado.getServicoProtecao());
    }

    @Override
    @Transactional(readOnly = false)
    public ServicoExecutado cancelarServicoExecutado(ServicoExecutado servicoExecutado) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarServicoProtecao(servicoExecutado.getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_PROTECAO_SEM_PERMISSAO_SALVAR);
        validadorServicoProtecao.validarCancelamentoServicoExecutado(servicoExecutado);
        ServicoProtecao servicoProtecao = servicoExecutado.getServicoProtecao();
        CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();
        servicoProtecao.setChaveUsuarioCancelamento(credentialsBean.getLogon());
        servicoProtecao.setNomeUsuarioCancelamento(credentialsBean.getUsername());
        dao.saveOrUpdate(servicoProtecao);

        cancelarServicoHospedagem(servicoExecutado);
        cancelarServicoTransporte(servicoExecutado);
        cancelarServicoAcessoPessoa(servicoExecutado);
        cancelarServicoSuprimento(servicoExecutado);
        cancelarServicoProtecaoRetiradaResiduoLixo(servicoExecutado);

        return servicoExecutado;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ServicoExecutado> buscarServicosExecutados(Agenciamento agenciamento) {
        Set<ServicoExecutado> servicosExecutados = new HashSet<ServicoExecutado>();

        servicosExecutados.addAll(dao.list(new ConsultaServicoMedicoOdontologicoDoAgenciamento(agenciamento)));
        servicosExecutados.addAll(dao.list(new ConsultaServicoTransporteDoAgenciamento(agenciamento)));
        servicosExecutados.addAll(dao.list(new ConsultaServicoHospedagemDoAgenciamento(agenciamento)));
        servicosExecutados.addAll(dao.list(new ConsultaServicoAcessoPessoaDoAgenciamento(agenciamento)));
        servicosExecutados.addAll(dao.list(new ConsultaServicoSuprimentoDoAgenciamento(agenciamento)));
        servicosExecutados.addAll(dao.list(new ConsultaServicoRetiradaResiduoLixoDoAgenciamento(agenciamento)));
        servicosExecutados.addAll(dao.list(new ConsultaServicoProtecaoDoAgenciamento(agenciamento)));

        return new ArrayList<ServicoExecutado>(servicosExecutados);
    }

    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Serviço Médico Odontológico">
    @Transactional(readOnly = false)
    @Override
    public void salvarServicoProtecaoMedicoOdontologico(ServicoExecutado servicoExecutado) {

        if (!servicoExecutado.getServicoProtecao().getTipoAtendimentoServico().equals(TipoAtendimentoServico.MEDICO_ODONTOLOGICO)) {
            return;
        }

        ServicoMedicoOdontologico servico = (ServicoMedicoOdontologico) servicoExecutado;

        validadorServicoProtecao.validarSalvarServicoMedicoOdontologico(servico);

        servico.setServicoProtecao(servicoExecutado.getServicoProtecao());
        servico.setId(servicoExecutado.getServicoProtecao().getId());

        dao.saveOrUpdate(servico);
    }

    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Serviço Transporte">
    @Transactional(readOnly = false)
    @Override
    public void salvarServicoProtecaoTransporte(ServicoExecutado servicoExecutado) {
        if (!servicoExecutado.getServicoProtecao().getTipoAtendimentoServico().equals(TipoAtendimentoServico.TRANSPORTE)) {
            return;
        }

        ServicoTransporte servico = (ServicoTransporte) servicoExecutado;

        validadorServicoProtecao.validarSalvarServicoTransporte(servico);

        servico.setServicoProtecao(servicoExecutado.getServicoProtecao());
        servico.setId(servicoExecutado.getServicoProtecao().getId());

        dao.saveOrUpdate(servico);

        for (Passageiro passageiro : servico.getPassageiros()) {
            passageiro = salvarPassageiro(passageiro);
        }

    }

    @Transactional(readOnly = true)
    @Override
    public ServicoTransporte buscarServicoTransportePorId(Long id) {
        return dao.uniqueResult(new ConsultaServicoTransportePorId(id));
    }

    @Override
    public void enviarFormularioSolicitacaoTransporte(FiltroEnvioSolicitacaoTransporte filtro) {
        notesWebService.enviarFormularioSolicitacaoTransporte(filtro);
    }

    @Override
    public String montarCorpoEmailSolicitacaoTransporte(FiltroEnvioSolicitacaoTransporte filtro) {
        return notesWebService.montarCorpoEmailSolicitacaoTransporte(filtro, true);
    }

    @Override
    public Set<String> destinatariosEmailSolicitacaoTransporte(FiltroEnvioSolicitacaoTransporte filtro) {
        return notesWebService.destinatariosEmailSolicitacaoTransporte(filtro);
    }

    private void cancelarServicoTransporte(ServicoExecutado servicoExecutado) {
        if (!servicoExecutado.getServicoProtecao().getTipoAtendimentoServico().equals(TipoAtendimentoServico.TRANSPORTE)) {
            return;
        }

        ServicoTransporte servico = buscarServicoTransportePorId(((ServicoTransporte) servicoExecutado).getId());

        for (Passageiro passageiro : servico.getPassageiros()) {
            passageiro.setAtivo(Boolean.FALSE);
            dao.saveOrUpdate(passageiro);
        }
    }

    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Serviço Hospedagem">
    @Transactional(readOnly = false)
    @Override
    public void salvarServicoProtecaoHospedagem(ServicoExecutado servicoExecutado) {
        if (!servicoExecutado.getServicoProtecao().getTipoAtendimentoServico().equals(TipoAtendimentoServico.HOSPEDAGEM)) {
            return;
        }

        ServicoHospedagem servico = (ServicoHospedagem) servicoExecutado;

        validadorServicoProtecao.validarSalvarServicoHospedagem(servico);

        servico.setServicoProtecao(servicoExecutado.getServicoProtecao());
        servico.setId(servicoExecutado.getServicoProtecao().getId());

        dao.saveOrUpdate(servico);

        for (Hospede hospede : servico.getHospedes()) {
            hospede = salvarHospede(hospede);
        }

    }

    @Transactional(readOnly = true)
    @Override
    public ServicoHospedagem buscarServicoHospedagemPorId(Long id) {
        return dao.uniqueResult(new ConsultaServicoHospedagemPorId(id));
    }

    @Override
    public void enviarEmailSolicitacaoHospedagem(FiltroEnvioSolicitacaoHospedagem filtro) {
        notesWebService.enviarEmailSolicitacaoHospedagem(filtro);
    }

    @Override
    public String montarCorpoEmailSolicitacaoHospedagem(FiltroEnvioSolicitacaoHospedagem filtro) {
        return notesWebService.montarCorpoEmailSolicitacaoHospedagem(filtro, true);
    }

    @Override
    public Set<String> destinatariosEmailSolicitacaoHospedagem(FiltroEnvioSolicitacaoHospedagem filtro) {
        return notesWebService.destinatariosEmailSolicitacaoHospedagem(filtro);
    }

    private void cancelarServicoHospedagem(ServicoExecutado servicoExecutado) {
        if (!servicoExecutado.getServicoProtecao().getTipoAtendimentoServico().equals(TipoAtendimentoServico.HOSPEDAGEM)) {
            return;
        }

        ServicoHospedagem servico = buscarServicoHospedagemPorId(((ServicoHospedagem) servicoExecutado).getId());

        for (Hospede hospede : servico.getHospedes()) {
            hospede.setAtivo(Boolean.FALSE);
            dao.saveOrUpdate(hospede);
        }
    }

    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Serviço Acesso Pessoa">
    @Transactional(readOnly = false)
    @Override
    public void salvarServicoProtecaoAcessoPessoa(ServicoExecutado servicoExecutado) {
        if (!servicoExecutado.getServicoProtecao().getTipoAtendimentoServico().equals(TipoAtendimentoServico.ACESSO_PESSOA)) {
            return;
        }

        ServicoAcessoPessoa servico = (ServicoAcessoPessoa) servicoExecutado;

        validadorServicoProtecao.validarSalvarServicoAcessoPessoa(servico);

        servico.setServicoProtecao(servicoExecutado.getServicoProtecao());
        servico.setId(servicoExecutado.getServicoProtecao().getId());

        dao.saveOrUpdate(servico);

        for (PessoaAcesso pessoa : servico.getPessoasAsList()) {
            pessoa.setFormularioGeradoPolicia(false);
            pessoa.setFormularioGeradoReceita(false);
            pessoa = salvarPessoa(pessoa);
        }

    }

    @Transactional(readOnly = true)
    @Override
    public ServicoAcessoPessoa buscarServicoAcessoPessoaPorId(Long id) {
        return dao.uniqueResult(new ConsultaServicoAcessoPessoaPorId(id));
    }

    private void cancelarServicoAcessoPessoa(ServicoExecutado servicoExecutado) {
        if (!servicoExecutado.getServicoProtecao().getTipoAtendimentoServico().equals(TipoAtendimentoServico.ACESSO_PESSOA)) {
            return;
        }

        ServicoAcessoPessoa servico = buscarServicoAcessoPessoaPorId(((ServicoAcessoPessoa) servicoExecutado).getId());

        for (PessoaAcesso pessoa : servico.getPessoasAsList()) {
            pessoa.setAtivo(Boolean.FALSE);
            dao.saveOrUpdate(pessoa);
        }
    }

    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Serviço Suprimento Ao Navio">
    @Transactional(readOnly = false)
    @Override
    public void salvarServicoProtecaoSuprimentoAosNavios(ServicoExecutado servicoExecutado) {
        if (!servicoExecutado.getServicoProtecao().getTipoAtendimentoServico().equals(TipoAtendimentoServico.SERVICO_SUPRIMENTO)) {
            return;
        }

        ServicoSuprimento suprimento = (ServicoSuprimento) servicoExecutado;

        validadorServicoProtecao.validarSalvarServicoSuprimentoAosNavios(suprimento);

        suprimento.setServicoProtecao(servicoExecutado.getServicoProtecao());
        suprimento.setId(servicoExecutado.getServicoProtecao().getId());

        dao.saveOrUpdate(suprimento);

        for (ServicoSuprimentoTransito suprimentoTransito : suprimento.getTransitos()) {
            suprimentoTransito.setFormularioGeradoReceitaFederal(suprimentoTransito.isFormularioGeradoReceitaFederal());
            suprimentoTransito = salvarServicoProtecaoTransito(suprimentoTransito);
        }

    } 

    @Override
    @Transactional(readOnly = true)
    public ServicoSuprimento buscarServicoSuprimentoPorId(Long id) {
        return dao.uniqueResult(new ConsultaServicoSuprimentoPorId(id));
    }

    private void cancelarServicoSuprimento(ServicoExecutado servicoExecutado) {
        if (!servicoExecutado.getServicoProtecao().getTipoAtendimentoServico().equals(TipoAtendimentoServico.SERVICO_SUPRIMENTO)) {
            return;
        }

        ServicoSuprimento servico = buscarServicoSuprimentoPorId(((ServicoSuprimento) servicoExecutado).getId());

        for (ServicoSuprimentoTransito suprimentoTransito : servico.getTransitos()) {
            dao.saveOrUpdate(suprimentoTransito);
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Hospede">
    @Transactional(readOnly = false)
    @Override
    public Hospede salvarHospede(Hospede hospede) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarHospede(hospede.getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.HOSPSDE_SEM_PERMISSAO_SALVAR);
        validadorServicoProtecao.validarCamposObrigatoriosHospede(hospede);

        dao.saveOrUpdate(hospede);

        return hospede;
    }

    @Override
    public void validarCamposObrigatoriosHospede(Hospede hospede) {
        validadorServicoProtecao.validarCamposObrigatoriosHospede(hospede);
    }

    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Passageiro">
    @Transactional(readOnly = false)
    @Override
    public Passageiro salvarPassageiro(Passageiro passageiro) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarPassageiro(passageiro.getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.PASSAGEIRO_SEM_PERMISSAO_SALVAR);
        validadorServicoProtecao.validarCamposObrigatoriosPassageiro(passageiro);

        dao.saveOrUpdate(passageiro);

        return passageiro;
    }

    @Override
    public void validarCamposObrigatoriosPassageiro(Passageiro passageiro) {
        validadorServicoProtecao.validarCamposObrigatoriosPassageiro(passageiro);
    }
    //</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="Pessoa">
    @Transactional(readOnly = false)
    private PessoaAcesso salvarPessoa(PessoaAcesso pessoa) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarPessoa(pessoa.getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.PESSOA_SEM_PERMISSAO_SALVAR);
        dao.saveOrUpdate(pessoa);
        return pessoa;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PessoaAcesso> instanciarPessoasDaEmpresa(EmpresaProtecao empresa) {
        List<Pessoa> pessoas = pessoaProtecaoService.buscarPessoasProtecaoAtivaPorEmpresaNome(empresa, null);
        List<PessoaAcesso> pessoasAcesso = new ArrayList<PessoaAcesso>();
        for (Pessoa pessoa : pessoas) {
            PessoaAcesso pessoaAcesso = new PessoaAcesso();
            pessoaAcesso.setNome(pessoa.getNome());
            pessoaAcesso.setDocumento(pessoa.getDocumento());
            pessoaAcesso.setTipoDocumentoIdentificacao(pessoa.getTipoDocumentoIdentificacao());
            pessoaAcesso.setCpf(pessoa.getCpf());
            pessoaAcesso.setDataNascimento(pessoa.getDataNascimento());
            pessoaAcesso.setNacionalidade(pessoa.getNacionalidade());
            if (pessoa.getEmpresa() != null) {
                pessoaAcesso.setNomeEmpresa(pessoa.getEmpresa().getRazaoSocial());
                pessoaAcesso.setCnpjEmpresa(pessoa.getEmpresa().getCnpj());
            }
            pessoasAcesso.add(pessoaAcesso);
        }
        return pessoasAcesso;
    }

    @Override
    public void validarCamposObrigatoriosPessoa(PessoaAcesso pessoa) {
        validadorServicoProtecao.validarCamposObrigatoriosPessoa(pessoa);
    }

    @Transactional(readOnly = false)
    @Override
    public void alterarPessoaStatusFormularioGeradoPolicia(List<PessoaAcessoVO> pessoaVOs) {
        for (PessoaAcessoVO pessoaVO : pessoaVOs) {
            if (pessoaVO.isSelecionado()) {
                pessoaVO.getPessoa().setFormularioGeradoPolicia(true);
                dao.saveOrUpdate(pessoaVO.getPessoa());
            }
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void alterarPessoaStatusFormularioGeradoReceita(List<PessoaAcessoVO> pessoaVOs) {
        for (PessoaAcessoVO pessoaVO : pessoaVOs) {
            if (pessoaVO.isSelecionado()) {
                pessoaVO.getPessoa().setFormularioGeradoReceita(true);
                dao.saveOrUpdate(pessoaVO.getPessoa());
            }
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void alterarPessoaStatusFormularioGeradoDesEmbarqueTripulantes(List<PessoaAcessoVO> pessoaVOs) {
        for (PessoaAcessoVO pessoaVO : pessoaVOs) {
            if (pessoaVO.isSelecionado()) {
                pessoaVO.getPessoa().setFormularioGeradoTripulante(true);
                dao.saveOrUpdate(pessoaVO.getPessoa());
            }
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void alterarPessoaStatusFormularioGeradoPrestacaoServico(List<PessoaAcessoVO> pessoaVOs) {
        for (PessoaAcessoVO pessoaVO : pessoaVOs) {
            if (pessoaVO.isSelecionado()) {
                pessoaVO.getPessoa().setFormularioGeradoPrestacaoServico(true);
                dao.saveOrUpdate(pessoaVO.getPessoa());
            }
        }
    }
    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Serviço Suprimento Transito">

    @Override
    @Transactional(readOnly = false)
    public ServicoSuprimentoTransito salvarServicoProtecaoTransito(ServicoSuprimentoTransito suprimentoTransito) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarServicoSuprimento(suprimentoTransito.getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_SUPRIMENTO_TRANSITO_SALVAR_SEM_PERMISSAO);
        validadorServicoProtecao.validarCamposObrigatoriosSuprimentoTransito(suprimentoTransito);

        boolean novoSuprimentoTransito = suprimentoTransito.getId() == null ? true : false;
        dao.saveOrUpdate(suprimentoTransito);

        if (novoSuprimentoTransito) {
            salvarServicoSuprimentoFornecedor(suprimentoTransito);
            salvarServicoSuprimentoVeiculo(suprimentoTransito);

        }
        return suprimentoTransito;
    }

    private void salvarServicoSuprimentoFornecedor(ServicoSuprimentoTransito suprimentoTransito) {
        for (ServicoSuprimentoTransitoEmpresa transitoEmpresa : suprimentoTransito.getTransitosEmpresas()) {
            transitoEmpresa.setSolicitacaoTransito(suprimentoTransito);
            salvarServicoProtecaoFornecedor(transitoEmpresa);
        }
    }

    private void salvarServicoSuprimentoVeiculo(ServicoSuprimentoTransito suprimentoTransito) {
        for (ServicoSuprimentoTransitoVeiculo transitoVeiculo : suprimentoTransito.getTransitosVeiculos()) {
            transitoVeiculo.setSolicitacaoTransito(suprimentoTransito);
            salvarServicoProtecaoVeiculo(transitoVeiculo);
        }
    }

    @Override
    public void validarCamposObrigatoriosTransito(ServicoSuprimentoTransito suprimentoTransito) {
        validadorServicoProtecao.validarCamposObrigatoriosSuprimentoTransito(suprimentoTransito);
    }

    @Override
    @Transactional(readOnly = false)
    public void alterarSuprimentoTransitoStatusFormularioGerado(ServicoSuprimentoTransito suprimentoTransito) {
        suprimentoTransito.setFormularioGeradoReceitaFederal(true);
        dao.saveOrUpdate(suprimentoTransito);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Serviço Suprimento Transito Fornecedor">
    @Override
    @Transactional(readOnly = false)
    public ServicoSuprimentoTransitoEmpresa salvarServicoProtecaoFornecedor(ServicoSuprimentoTransitoEmpresa suprimentoTransitoEmpresa) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarServicoSuprimentoFornecedor(suprimentoTransitoEmpresa.getSolicitacaoTransito().getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_SUPRIMENTO_FORNECEDOR_SALVAR_SEM_PERMISSAO);
        validadorServicoProtecao.validarCamposObrigatoriosSuprimentoFornecedor(suprimentoTransitoEmpresa);
        dao.saveOrUpdate(suprimentoTransitoEmpresa);
        return suprimentoTransitoEmpresa;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicoSuprimentoTransitoEmpresa> buscarServicoSuprimentoTransitoEmpresaParaRelatorio(FiltroRelatorioServicoSuprimentoAosNavios filtro) {
        validadorServicoProtecao.validarCamposObrigatoriosBuscaServicoSuprimentoTransitoEmpresaParaRelatorio(filtro);
        return dao.list(new ConsultaServicoSuprimentoTransitoEmpresa(filtro));
    }

    @Override
    @Transactional(readOnly = false)
    public void excluirServicoProtecaoFornecedor(ServicoSuprimentoTransitoEmpresa suprimentoTransitoEmpresa) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarServicoSuprimentoFornecedor(suprimentoTransitoEmpresa.getSolicitacaoTransito().getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_SUPRIMENTO_FORNECEDOR_EXCLUIR_SEM_PERMISSAO);
        dao.remove(suprimentoTransitoEmpresa);
    }

    @Transactional(readOnly = true)
    @Override
    public void validarCamposObrigatoriosFornecedor(ServicoSuprimentoTransitoEmpresa suprimentoTransitoEmpresa) {
        validadorServicoProtecao.validarCamposObrigatoriosSuprimentoFornecedor(suprimentoTransitoEmpresa);
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Serviço Suprimento Transito Veiculo">
    @Override
    @Transactional(readOnly = false)
    public ServicoSuprimentoTransitoVeiculo salvarServicoProtecaoVeiculo(ServicoSuprimentoTransitoVeiculo suprimentoTransitoVeiculo) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarServicoSuprimentoVeiculo(suprimentoTransitoVeiculo.getSolicitacaoTransito().getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_SUPRIMENTO_CONDUTOR_SALVAR_SEM_PERMISSAO);
        validadorServicoProtecao.validarCamposObrigatoriosSuprimentoCondutor(suprimentoTransitoVeiculo);
        dao.saveOrUpdate(suprimentoTransitoVeiculo);
        return suprimentoTransitoVeiculo;
    }

    @Override
    @Transactional(readOnly = false)
    public void excluirServicoProtecaoVeiculo(ServicoSuprimentoTransitoVeiculo suprimentoTransitoVeiculo) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarServicoSuprimentoVeiculo(suprimentoTransitoVeiculo.getSolicitacaoTransito().getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_SUPRIMENTO_CONDUTOR_EXCLUIR_SEM_PERMISSAO);
        dao.remove(suprimentoTransitoVeiculo);
    }

    @Override
    @Transactional(readOnly = false)
    public void excluirServicoSuprimentoTransito(ServicoSuprimentoTransito suprimentoTransito) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarServicoSuprimento(suprimentoTransito.getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_SUPRIMENTO_TRANSITO_EXCLUIR_SEM_PERMISSAO);
        dao.remove(suprimentoTransito);
    }
   
    
    @Override
    @Transactional(readOnly = true)
    public void validarCamposObrigatoriosCondutor(ServicoSuprimentoTransitoVeiculo suprimentoTransitoVeiculo) {
        validadorServicoProtecao.validarCamposObrigatoriosSuprimentoCondutor(suprimentoTransitoVeiculo);
    }
    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Serviço Retirada de Resíduos/Lixo">
    @Transactional(readOnly = false)
    @Override
    public void salvarServicoProtecaoRetiradaResiduoLixo(ServicoExecutado servicoExecutado) {
        if (!servicoExecutado.getServicoProtecao().getTipoAtendimentoServico().equals(TipoAtendimentoServico.SERVICO_RETIRADA_RESIDUO_LIXO)) {
            return;
        }

        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = (ServicoRetiradaResiduoLixo) servicoExecutado;

        validadorServicoProtecao.validarSalvarServicoProtecaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo);

        servicoRetiradaResiduoLixo.setServicoProtecao(servicoExecutado.getServicoProtecao());
        servicoRetiradaResiduoLixo.setId(servicoExecutado.getServicoProtecao().getId());

        dao.saveOrUpdate(servicoRetiradaResiduoLixo); 

    }
    
    private void cancelarServicoProtecaoRetiradaResiduoLixo(ServicoExecutado servicoExecutado) {
        if (!servicoExecutado.getServicoProtecao().getTipoAtendimentoServico().equals(TipoAtendimentoServico.SERVICO_RETIRADA_RESIDUO_LIXO)) {
            return;
        }
        buscarServicoRetiradaResiduoLixoPorId(((ServicoRetiradaResiduoLixo) servicoExecutado).getId());
        servicoExecutado.getServicoProtecao().setEmailEnviado(false);
        servicoExecutado.getServicoProtecao().setDataEmailEnviado(null);
    }
    
    @Transactional(readOnly = true)
    @Override
    public ServicoRetiradaResiduoLixo buscarServicoRetiradaResiduoLixoPorId(Long id) {
        return dao.uniqueResult(new ConsultaServicoRetiradaResiduoLixoPorId(id));
    }
    
    @Override
    public void enviarFormularioSolicitacaoRetiradaResiduoLixo(FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro) {
        notesWebService.enviarFormularioSolicitacaoRetiradaResiduoLixo(filtro);
    }

    @Override
    public String montarCorpoEmailSolicitacaoRetiradaResiduoLixo(FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro) {
        return notesWebService.montarCorpoEmailSolicitacaoRetiradaResiduoLixo(filtro, true);
    }

    @Override
    public Set<String> destinatariosEmailSolicitacaoRetiradaResiduoLixo(FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro) {
        return notesWebService.destinatariosEmailSolicitacaoRetiradaResiduoLixo(filtro);
    }
    
    //</editor-fold>
}
