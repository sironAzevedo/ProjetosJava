package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.beans.CredentialsBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.AcessoService;
import br.com.petrobras.sistam.common.business.DocumentoService;
import br.com.petrobras.sistam.common.business.PendenciaService;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.CTACReceitaVo;
import br.com.petrobras.sistam.service.query.agenciamento.documento.ConsultaDocumentoDoAgenciamentoPorTipo;
import br.com.petrobras.sistam.service.query.agenciamento.documento.ConsultaDocumentoDoAgenciamentoPorTipoSemProtocolo;
import br.com.petrobras.sistam.service.query.agenciamento.documento.ConsultaDocumentoPorTipoAgenciamentoCtacPortoDestino;
import br.com.petrobras.sistam.service.query.agenciamento.documento.ConsultaDocumentosDoAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.documento.ConsultaDocumentosPorId;
import br.com.petrobras.sistam.service.validator.ValidadorDocumento;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class DocumentoServiceImpl implements DocumentoService {

    private GenericDao dao;
    private AcessoService acessoService;
    private PendenciaService pendenciaServie;
    @Autowired
    private ValidadorPermissao validadorPermissao;
    @Autowired
    private ValidadorDocumento validadorDocumento;

    public DocumentoServiceImpl(GenericDao dao) {
        this.dao = dao;
    }

    public void setAcessoService(AcessoService acessoService) {
        this.acessoService = acessoService;
    }

    public void setPendenciaServie(PendenciaService pendenciaServie) {
        this.pendenciaServie = pendenciaServie;
    }

    //<editor-fold defaultstate="collapsed" desc="Busca de Documentos">
    @Transactional(readOnly = true)
    @Override
    public List<Documento> buscarDocumentoDoAgenciamentoPorTipo(TipoDocumento tipo, Agenciamento agenciamento) {
        return dao.list(new ConsultaDocumentoDoAgenciamentoPorTipo(tipo, agenciamento));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Documento> buscarDocumentoDoAgenciamentoPorTipoSemProtocolo(TipoDocumento tipo, Agenciamento agenciamento) {
        return dao.list(new ConsultaDocumentoDoAgenciamentoPorTipoSemProtocolo(tipo, agenciamento));
    }
    
    @Transactional(readOnly = true)
    @Override
    public Documento buscarDocumentoPorTipoAgenciamentoCtacPortoDestino(TipoDocumento tipo, Agenciamento agenciamento, String numeroCTAC, Porto portoDestino) {
        Query query = dao.createHibernateQuery(new ConsultaDocumentoPorTipoAgenciamentoCtacPortoDestino(tipo, agenciamento, numeroCTAC, portoDestino));
        query.setMaxResults(1);
        return (Documento) query.uniqueResult();
    }

    @Transactional(readOnly = false)
    @Override
    public Map<Agenciamento, Documento> buscarDocumentoAnexoUnicoPorAgenciamento(Set<Agenciamento> agenciamentos) {
        Map<Agenciamento, Documento> anexosUnico = new LinkedHashMap<Agenciamento, Documento>();
        
        for (Agenciamento agenciamento : agenciamentos) {
            if (!anexosUnico.containsKey(agenciamento)) {
                List<Documento> anexos = buscarDocumentoDoAgenciamentoPorTipo(TipoDocumento.ANEXO_UNICO, agenciamento);
                if (!anexos.isEmpty()) {
                    anexosUnico.put(agenciamento, anexos.get(0));
                }
            }
        }
        return anexosUnico;
    }

    @Transactional(readOnly = true)
    @Override
    public Documento buscarDocumentoDaManobra(Manobra manobra) {
        return dao.uniqueResult(new ConsultaDocumentoDoAgenciamentoPorTipo(TipoDocumento.REGISTRO_MOVIMENTACAO, manobra));
    }

    @Transactional(readOnly = true)
    @Override
    public Documento buscarDocumentoDaOperacao(Operacao operacao) {
        return dao.uniqueResult(new ConsultaDocumentoDoAgenciamentoPorTipo(TipoDocumento.BILL_OF_LADING, operacao));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Documento> buscarDocumentosDoAgenciamento(Agenciamento agenciamento) {
        return dao.list(new ConsultaDocumentosDoAgenciamento(agenciamento));
    }

    @Transactional(readOnly = true)
    @Override
    public Documento buscarDocumentoPorId(Long id) {
        return dao.uniqueResult(new ConsultaDocumentosPorId(id));
    }
    //</editor-fold>

    @Override
    @Transactional(readOnly = false)
    public Documento salvarDocumento(Documento documento) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarDocumento(documento.getAgenciamento().getAgencia()), ConstantesI18N.DOCUMENTO_SEM_PERMISSAO_SALVAR);
        validadorDocumento.validarSalvarDocumento(documento);

        if (documento.getId() == null) {
            Documento doc;

            //Se o documento for da manobra, verifica se já foi criado um documento para a manobra
            if (TipoDocumento.REGISTRO_MOVIMENTACAO.equals(documento.getTipoDocumento())) {
                doc = buscarDocumentoDaManobra(documento.getManobra());
            } //Se o documento for da operação, verifica se já foi criado um documento para a opearção
            else if (TipoDocumento.BILL_OF_LADING.equals(documento.getTipoDocumento())) {
                doc = buscarDocumentoDaOperacao(documento.getOperacao());
            } //Se for só do agenciamento, verifica se já foi criado um documento para o agenciamento
            else {
                doc = null;
                List<Documento> docs = buscarDocumentoDoAgenciamentoPorTipo(documento.getTipoDocumento(), documento.getAgenciamento());
                if (docs != null && !docs.isEmpty()) {
                    doc = docs.get(0);
                }
            }

            //Se já foi criado o documento, para manora, opearção ou agenciamento, não permite criar outro
            AssertUtils.assertExpression(doc == null, ConstantesI18N.DOCUMENTO_JA_EXISTE_DOCUMENTO_DO_MESMO_TIPO);
        }

        //Seta a data de emissão para merio dia.
        TimeZone timeZone = documento.getAgenciamento().getAgencia().obterTimezone();
        documento.setDataEmissao(SistamDateUtils.alterarParaMeioDia(documento.getDataEmissao(), timeZone));

        dao.saveOrUpdate(documento);
        return documento;
    }

    @Override
    @Transactional(readOnly = false)
    public void excluirDocumento(Documento documento) {
        AssertUtils.assertExpression(validadorPermissao.podeExcluirDocumento(documento.getAgenciamento().getAgencia()), ConstantesI18N.DOCUMENTO_SEM_PERMISSAO_EXCLUIR);
        validadorDocumento.validarExcluirDocumento(documento);

        dao.remove(documento);
    }

    /**
     * Registra a emissão de um documento de um agenciamento. Se não existir
     * documento para o tipo e agenciamento informado, cria um novo. Caso exista
     * um, mas não esteja protocolado, atualiza com a data de emissão, as
     * informações do usuário que emitiu o documento e o repesentante legal. Se
     * o documento já estiver protocolado, não faz nada com o documento.
     *
     * @param tipo
     * @param agenciamento
     * @param representante
     * @return O documento novo, atualizado ou sem alteração.
     */
    @Override
    @Transactional(readOnly = false)
    public Documento registrarEmissaoDeDocumento(TipoDocumento tipo, Agenciamento agenciamento, RepresentanteLegal representante, Boolean criarNovo) {
        return registrarEmissaoDeDocumento(tipo, agenciamento, representante, null, null, null, criarNovo);
    }

    @Transactional(readOnly = false)
    private Documento registrarEmissaoDeDocumento(TipoDocumento tipo, Agenciamento agenciamento, RepresentanteLegal representante, Date dataFormulario, String numeroCtac, Porto portoDestino, Boolean criarNovo) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarDocumento(agenciamento.getAgencia()), ConstantesI18N.DOCUMENTO_SEM_PERMISSAO_SALVAR);
        validadorDocumento.validarRegistrarEmissaoDeDocumento(tipo, agenciamento, representante);

        Documento documento = null;

        if (!criarNovo) {
            List<Documento> docs = buscarDocumentoDoAgenciamentoPorTipo(tipo, agenciamento);
            if (docs != null && !docs.isEmpty()) {
                documento = docs.get(0);
            }
        }

        //Se não tiver documento, cria um.
        if (documento == null) {
            documento = new Documento();
            documento.setAgenciamento(agenciamento);
            documento.setTipoDocumento(tipo);
        }

        if (dataFormulario != null) {
            documento.setDataFormulario(dataFormulario);
        }

        if (StringUtils.isNotBlank(numeroCtac)) {
            documento.setNumeroCTAC(numeroCtac);
        }

        if (portoDestino != null) {
            documento.setPorto(portoDestino);
        }
        
         if(TipoDocumento.SOLICITACAO_CERTIFICADO.equals(tipo)){
             documento.setDataEta(agenciamento.getEta());
         }
         
        if(TipoDocumento.PARTE_ENTRADA.equals(tipo)){
            documento.setDataHoraParteChegada(agenciamento.getDataChegada());
         }

        //Só atualiza o documento se ele não for protocolado
        if (documento.getDataProtocolo() == null) {
            documento.setDataEmissao(new Date());

            //Se for do tipo dos documentos AVISA, informa o representante
            if (TipoDocumento.SOLICITACAO_CERTIFICADO.equals(tipo)
                    || TipoDocumento.COMUNICACAO_CHEGADA.equals(tipo)
                    || TipoDocumento.PARTE_ENTRADA.equals(tipo)
                    || TipoDocumento.TERMO_RESPONSABILIDADE_EMPRESA.equals(tipo)) {
                documento.setRepresentante(representante);
            } 

            //Atualiza com o usuário logado
            CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();
            documento.setChaveEmitente(credentialsBean.getLogon());
            documento.setNomeEmitente(credentialsBean.getUsername());

            dao.saveOrUpdate(documento);
        }
        return documento;
    }

    @Override
    @Transactional(readOnly = false)
    public Documento registrarEmissaoDeDocumentoSemProtocolo(TipoDocumento tipo, Agenciamento agenciamento, RepresentanteLegal representante, Boolean criarNovo) {
        return registrarEmissaoDeDocumentoSemProtocolo(tipo, agenciamento, representante, null, null, null, criarNovo);
    }
    
    @Transactional(readOnly = false)
    private Documento registrarEmissaoDeDocumentoSemProtocolo(TipoDocumento tipo, Agenciamento agenciamento, RepresentanteLegal representante, Date dataFormulario, String numeroCtac, Porto portoDestino, Boolean criarNovo) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarDocumento(agenciamento.getAgencia()), ConstantesI18N.DOCUMENTO_SEM_PERMISSAO_SALVAR);
        validadorDocumento.validarRegistrarEmissaoDeDocumento(tipo, agenciamento, representante);

        Documento documento = null;

        if (!criarNovo) {
            List<Documento> docs = buscarDocumentoDoAgenciamentoPorTipoSemProtocolo(tipo, agenciamento);
            if (docs != null && !docs.isEmpty()) {
                documento = docs.get(0);
            }
        }

        //Se não tiver documento, cria um.
        if (documento == null) {
            documento = new Documento();
            documento.setAgenciamento(agenciamento);
            documento.setTipoDocumento(tipo);
        }

        if (dataFormulario != null) {
            documento.setDataFormulario(dataFormulario);
        }

        if (StringUtils.isNotBlank(numeroCtac)) {
            documento.setNumeroCTAC(numeroCtac);
        }

        if (portoDestino != null) {
            documento.setPorto(portoDestino);
        }
        
         if(TipoDocumento.SOLICITACAO_CERTIFICADO.equals(tipo)){
             documento.setDataEta(agenciamento.getEta());
         }
         
        if(TipoDocumento.PARTE_ENTRADA.equals(tipo)){
            documento.setDataHoraParteChegada(agenciamento.getDataChegada());
         }

        //Só atualiza o documento se ele não for protocolado
        if (documento.getDataProtocolo() == null) {
            documento.setDataEmissao(new Date());

            //Se for do tipo dos documentos AVISA, informa o representante
            if (TipoDocumento.SOLICITACAO_CERTIFICADO.equals(tipo)
                    || TipoDocumento.COMUNICACAO_CHEGADA.equals(tipo)
                    || TipoDocumento.PARTE_ENTRADA.equals(tipo)
                    || TipoDocumento.TERMO_RESPONSABILIDADE_EMPRESA.equals(tipo)) {
                documento.setRepresentante(representante);
            } 

            //Atualiza com o usuário logado
            CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();
            documento.setChaveEmitente(credentialsBean.getLogon());
            documento.setNomeEmitente(credentialsBean.getUsername());

            dao.saveOrUpdate(documento);
        }
        return documento;
    }
    
    /**
     * Registra a emissão de um documento de um agenciamento. Se não existir
     * documento para o tipo e agenciamento informado, cria um novo. Caso exista
     * um, mas não esteja protocolado, atualiza com a data de emissão, as
     * informações do usuário que emitiu o documento. Se já estiver protocolado,
     * não faz nada com o documento.
     *
     * @param tipo
     * @param agenciamento
     * @return O documento novo, atualizado ou sem alteração.
     */
    @Override
    @Transactional(readOnly = false)
    public Documento registrarEmissaoDeDocumento(TipoDocumento tipo, Agenciamento agenciamento, Boolean criarNovo) {
        return registrarEmissaoDeDocumento(tipo, agenciamento, null, null, null, null, criarNovo);
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarEmissaoDeDocumentoConhecimentoEmbarque(Agenciamento agenciamento, Date dataFormulario, CTACReceitaVo vo) {
        registrarEmissaoDeDocumento(TipoDocumento.CONHECIMENTO_EMBARQUE, agenciamento, null, dataFormulario, vo.getConhecimentoEmbarque(), vo.getPortoDesembarqueDestino(), true);
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarEmissaoDeDocumentosConhecimentoEmbarque(Agenciamento agenciamento, Date dataFormulario, List<CTACReceitaVo> listaVo) {
        for (CTACReceitaVo vo : listaVo) {
            registrarEmissaoDeDocumento(TipoDocumento.CONHECIMENTO_EMBARQUE, agenciamento, null, dataFormulario, vo.getConhecimentoEmbarque(), vo.getPortoDesembarqueDestino(), true);
        }
    }

    /**
     * Registra a emissão de um documento de uma manobra. Se não existir
     * documento para o tipo e manobra informados, cria um novo. Caso exista um,
     * mas não esteja protocolado, atualiza com a data de emissão, as
     * informações do usuário que emitiu o documento e o repesentante legal. Se
     * já estiver protocolado, não faz nada com o documento.
     *
     * @param tipo
     * @param agenciamento
     * @param representante
     * @return O documento novo, atualizado ou sem alteração.
     */
    @Override
    @Transactional(readOnly = false)
    public Documento registrarEmissaoDeDocumentoDaManobra(TipoDocumento tipo, Manobra manobra, RepresentanteLegal representante) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarDocumento(manobra.getAgenciamento().getAgencia()), ConstantesI18N.DOCUMENTO_SEM_PERMISSAO_SALVAR);
        validadorDocumento.validarRegistrarEmissaoDeDocumentoDaManobra(tipo, manobra, representante);

        //Se não tiver documento NÃO protocolado, cria um.
        Documento documento = buscarDocumentoDaManobra(manobra);
        if (documento == null) {
            documento = new Documento();
            documento.setManobra(manobra);
            documento.setTipoDocumento(tipo);
            documento.setAgenciamento(manobra.getAgenciamento());

        }

        //Só atualiza se o documento não estiver protocolado
        if (documento.getDataProtocolo() == null) {
            documento.setDataEmissao(new Date());
            documento.setRepresentante(representante);

            //Atualiza com o usuário logado
            CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();
            documento.setChaveEmitente(credentialsBean.getLogon());
            documento.setNomeEmitente(credentialsBean.getUsername());

            dao.saveOrUpdate(documento);
        }
        return documento;
    }

    @Override
    @Transactional(readOnly = false)
    public Documento informarProtocoloDoDocumento(Documento documento) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarDocumento(documento.getAgenciamento().getAgencia()), ConstantesI18N.DOCUMENTO_SEM_PERMISSAO_SALVAR);
        validadorDocumento.validarProtocolarDocumento(documento);

        //Só tem pdenência para resolver se o agenciamento for TCP
        if (TipoContrato.TCP.equals(documento.getAgenciamento().getTipoContrato())) {

            //Busca o documento na base para verificar se já está protocolado.
            //Se estiver, não solicita a resolução das pendências, uma fez que já foi solicitado a primeira vez que o documento foi protocolado.
            Documento documentoDaBase = dao.get(Documento.class, documento.getId());

            if (documentoDaBase.getDataProtocolo() == null) {
                pendenciaServie.resolverPendenciaDoDocumento(documento);
            }
        }


        dao.merge(documento);
        return documento;
    }
}
