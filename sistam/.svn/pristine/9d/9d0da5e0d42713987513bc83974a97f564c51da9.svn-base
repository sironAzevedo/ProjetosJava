package br.com.petrobras.sistam.desktop.agenciamento.formularios.policiaFederal;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.enums.TipoRequerimentoPasseSaidaPoliciaFederal;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FormularioRequerimentoPasseSaidaPoliciaFederalVO;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FormularioRequerimentoPasseSaidaPoliciaFederalModel extends BasePresentationModel<SistamService> {

    private Agenciamento agenciamento;
    private FormularioRequerimentoPasseSaidaPoliciaFederalVO requerimentoPasseSaidaPoliciaFederalVO = new FormularioRequerimentoPasseSaidaPoliciaFederalVO();
    private RepresentanteLegal representanteSelecionado;
    private String numeroPasseEntrada;
    private String carga;
    private List<TipoRequerimentoPasseSaidaPoliciaFederal> listaTipoRequerimento;
    private String justificativa;

    public FormularioRequerimentoPasseSaidaPoliciaFederalModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        this.requerimentoPasseSaidaPoliciaFederalVO = new FormularioRequerimentoPasseSaidaPoliciaFederalVO();

        listaTipoRequerimento = new ArrayList<TipoRequerimentoPasseSaidaPoliciaFederal>(Arrays.asList(TipoRequerimentoPasseSaidaPoliciaFederal.values()));
        listaTipoRequerimento.add(0, null);

    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public FormularioRequerimentoPasseSaidaPoliciaFederalVO getRequerimentoPasseSaidaPoliciaFederalVO() {
        return requerimentoPasseSaidaPoliciaFederalVO;
    }

    public void setRequerimentoPasseSaidaPoliciaFederalVO(FormularioRequerimentoPasseSaidaPoliciaFederalVO requerimentoPasseSaidaPoliciaFederalVO) {
        this.requerimentoPasseSaidaPoliciaFederalVO = requerimentoPasseSaidaPoliciaFederalVO;
    }

    public RepresentanteLegal getRepresentanteSelecionado() {
        return representanteSelecionado;
    }

    public void setRepresentanteSelecionado(RepresentanteLegal representanteSelecionado) {
        this.representanteSelecionado = representanteSelecionado;
    }

    public String getNumeroPasseEntrada() {
        return numeroPasseEntrada;
    }

    public void setNumeroPasseEntrada(String numeroPasseEntrada) {
        this.numeroPasseEntrada = numeroPasseEntrada;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public List<TipoRequerimentoPasseSaidaPoliciaFederal> getListaTipoRequerimento() {
        return listaTipoRequerimento;
    }

    public void setListaTipoRequerimento(List<TipoRequerimentoPasseSaidaPoliciaFederal> listaTipoRequerimento) {
        this.listaTipoRequerimento = listaTipoRequerimento;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    //</editor-fold>
    public void validar() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotEmpty(numeroPasseEntrada).orRegister(TipoExcecao.POLICIA_FEDERAL, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_INFORME_NUMERO_PASSE_ENTRADA);
        pm.assertNotEmpty(carga).orRegister(TipoExcecao.POLICIA_FEDERAL, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_INFORME_OPERACAO);
        pm.assertNotNull(requerimentoPasseSaidaPoliciaFederalVO.getDataEntradaNoPorto()).orRegister(TipoExcecao.POLICIA_FEDERAL, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_INFORME_DATA_ENTRADA_PORTO);
        pm.assertNotNull(requerimentoPasseSaidaPoliciaFederalVO.getTipoRequerimentoPasseSaidaPoliciaFederal()).orRegister(TipoExcecao.POLICIA_FEDERAL, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_INFORME_TIPO_REQUERIMENTO);
        pm.assertNotNull(requerimentoPasseSaidaPoliciaFederalVO.getListaRepresentante()).orRegister(TipoExcecao.POLICIA_FEDERAL, ConstantesI18N.DOCUMENTO_INFORME_REPRESENTANTE_LEGAL);

        pm.verifyAll();
    }

    public Map<String, Object> obterParametros() {
        Map<String, Object> parametros = new HashMap<String, Object>();
        String data_Entrada_NoPorto = SistamDateUtils.formatDate(requerimentoPasseSaidaPoliciaFederalVO.getDataEntradaNoPorto(), "dd/MM/yyyy", null);
        String representanteLegal = requerimentoPasseSaidaPoliciaFederalVO.getListaRepresentante().getNome();
        String tipoRequerimento = requerimentoPasseSaidaPoliciaFederalVO.getTipoRequerimentoPasseSaidaPoliciaFederal().getPorExtenso();
        String data_documento = SistamDateUtils.formatDateByExtensive(requerimentoPasseSaidaPoliciaFederalVO.getDataDocumento(), null);

        parametros.put("PORTO_NOME", agenciamento.getPorto().getNomeCompleto() != null ? agenciamento.getPorto().getNomeCompleto() : "-");
        parametros.put("AGENCIA_SIGLA", agenciamento.getAgencia().getEstado() != null ? agenciamento.getAgencia().getEstado() : "-");
        parametros.put("REPRESENTANTE_LEGAL", representanteLegal != null ? representanteLegal : "-");
        parametros.put("TIPO_REQUERIMENTO", tipoRequerimento != null ? tipoRequerimento : "-");
        parametros.put("DATA_ENTRADA_PORTO", !"".equals(data_Entrada_NoPorto) ? data_Entrada_NoPorto : " __/__/____ ");
        parametros.put("DATA_DOCUMENTO", !"".equals(data_documento) ? data_documento : " _____ de _____________ de _____ ");


        return parametros;
    }

    public void carregarVO() {

        requerimentoPasseSaidaPoliciaFederalVO.setNumeroPasseEntrada(numeroPasseEntrada != null ? numeroPasseEntrada : "________/______");
        requerimentoPasseSaidaPoliciaFederalVO.setPetrobrasAgenciaMaritima("PETROBRAS/AGÊNCIA MARÍTIMA");
        requerimentoPasseSaidaPoliciaFederalVO.setPetrobrasPetroleoBrasileiro("PETROBRAS/PETROLEO BRASILEIRO");
        requerimentoPasseSaidaPoliciaFederalVO.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto() != null ? agenciamento.getEmbarcacao().getNomeCompleto() : "-");
        requerimentoPasseSaidaPoliciaFederalVO.setNacionalidade(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto() != null ? agenciamento.getEmbarcacao().getBandeira().getNomeCompleto() : "-");
        requerimentoPasseSaidaPoliciaFederalVO.setCarga(carga != null ? carga : "-");
        requerimentoPasseSaidaPoliciaFederalVO.setComandanteSaida(agenciamento.getAgenciementoViagem().getComandanteSaida() != null ? agenciamento.getAgenciementoViagem().getComandanteSaida() : "-");
        requerimentoPasseSaidaPoliciaFederalVO.setETS(agenciamento.getDataEstimadaSaida());
        requerimentoPasseSaidaPoliciaFederalVO.setJustificativa(justificativa != null ? justificativa : "");
        requerimentoPasseSaidaPoliciaFederalVO.setClassificacaoNavio(agenciamento.getEmbarcacao().getClassificacao().getPorExtenso() != null ? agenciamento.getEmbarcacao().getClassificacao().getPorExtenso() : "-");
        if (agenciamento.getPortoDestino() != null) {
            requerimentoPasseSaidaPoliciaFederalVO.setDestino(agenciamento.getPortoDestino().getNomeCompleto());

        } else {
            requerimentoPasseSaidaPoliciaFederalVO.setDestino("-");
        }
    }

    public void gerarPendenciasDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario() {
        if (agenciamento.isTipoContatoTCP()) {
            List<Pendencia> listaPendencia = getService().buscarPendenciasDoAgenciamentoPorTipo(agenciamento, TipoPendencia.CABOTAGEM_PASSE_SAIDA);
            if (listaPendencia.isEmpty()) {
                getService().criarPendencia(agenciamento, TipoPendencia.CABOTAGEM_PASSE_SAIDA);
            }
        }
    }

    public void gerarDocumentoDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario() {

        TipoDocumento tipo = TipoDocumento.REQUERIMENTO_PASSE_SAIDA_PF;

        boolean criaDocNovo = false;
        Documento documento = null;
        List<Documento> docs = getService().buscarDocumentoDoAgenciamentoPorTipoSemProtocolo(tipo, agenciamento);
        if (docs != null && !docs.isEmpty()) {
            documento = docs.get(0);
        }
        if ((documento == null)) {
            criaDocNovo = true;
        } else {
            criaDocNovo = false;
        }

        getService().registrarEmissaoDeDocumento(tipo, agenciamento, null, criaDocNovo);
    }

    public void gerarFormulario() {
        RelatorioUtil.abrirFormularioRequerimentoPasseSaidaPoliciaFederalAgenciaSalvador(agenciamento.getAgencia(), getRequerimentoPasseSaidaPoliciaFederalVO(), obterParametros());
        //gerarPendenciasDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario(); /*ainda não foi definida pendÊncia para esse tipo de documento*/
        gerarDocumentoDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario();
    }
}
