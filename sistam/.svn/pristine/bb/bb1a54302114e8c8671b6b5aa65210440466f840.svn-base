package br.com.petrobras.sistam.desktop.agenciamento.formularios.policiaFederal;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoPasseAcessoPoliciaFederal;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FormularioPasseEntradaPoliciaFederalVO;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FormularioPasseEntradaPoliciaFederalModel extends BasePresentationModel<SistamService> {

    private FormularioPasseEntradaPoliciaFederalDialog dialog;
    private Agenciamento agenciamento;
    private FormularioPasseEntradaPoliciaFederalVO entradaPoliciaFederalVO = new FormularioPasseEntradaPoliciaFederalVO();
    private List<TipoPasseAcessoPoliciaFederal> listaPasseAcessoPoliciaFederal;
    private String nrEscala;
    private String carga;
    private Integer passageiroTransito;
    private Integer passageiroParaEstePorto;
    private Integer clandestinoImpedidos;
    private Integer outros;

    public FormularioPasseEntradaPoliciaFederalModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        this.entradaPoliciaFederalVO = new FormularioPasseEntradaPoliciaFederalVO();
        setListaPasseAcessoPoliciaFederal(new ArrayList<TipoPasseAcessoPoliciaFederal>(Arrays.asList(TipoPasseAcessoPoliciaFederal.values())));

    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public FormularioPasseEntradaPoliciaFederalVO getEntradaPoliciaFederalVO() {
        return entradaPoliciaFederalVO;
    }

    public List<TipoPasseAcessoPoliciaFederal> getListaPasseAcessoPoliciaFederal() {
        return listaPasseAcessoPoliciaFederal;
    }

    public void setListaPasseAcessoPoliciaFederal(List<TipoPasseAcessoPoliciaFederal> listaPasseAcessoPoliciaFederal) {
        this.listaPasseAcessoPoliciaFederal = listaPasseAcessoPoliciaFederal;
    }

    public String getNrEscala() {
        return nrEscala;
    }

    public void setNrEscala(String nrEscala) {
        this.nrEscala = nrEscala;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public Integer getPassageiroTransito() {
        return passageiroTransito;
    }

    public void setPassageiroTransito(Integer passageiroTransito) {
        this.passageiroTransito = passageiroTransito;
    }

    public Integer getPassageiroParaEstePorto() {
        return passageiroParaEstePorto;
    }

    public void setPassageiroParaEstePorto(Integer passageiroParaEstePorto) {
        this.passageiroParaEstePorto = passageiroParaEstePorto;
    }

    public Integer getClandestinoImpedidos() {
        return clandestinoImpedidos;
    }

    public void setClandestinoImpedidos(Integer clandestinoImpedidos) {
        this.clandestinoImpedidos = clandestinoImpedidos;
    }

    public Integer getOutros() {
        return outros;
    }

    public void setOutros(Integer outros) {
        this.outros = outros;
    }

    public FormularioPasseEntradaPoliciaFederalDialog getDialog() {
        return dialog;
    }

    public void setDialog(FormularioPasseEntradaPoliciaFederalDialog dialog) {
        this.dialog = dialog;
    }

    //</editor-fold>
    public void validar() {
        SistamPendencyManager pm = new SistamPendencyManager();

        pm.verifyAll();
    }

    public Map<String, Object> obterParametros() {
        Map<String, Object> parametros = new HashMap<String, Object>();

        //String anoDocumento = SistamDateUtils.formatDate(entradaPoliciaFederalVO.getAnoDocumento(),"dd/MM/yyyy", null);
        SimpleDateFormat ano = new SimpleDateFormat("yyyy");
        String data_documento = SistamDateUtils.formatDateByExtensive(entradaPoliciaFederalVO.getDataDocumento(), null);

        parametros.put("AGENCIA_NOME", agenciamento.getAgencia().getNome());
        parametros.put("AGENCIA_SIGLA", agenciamento.getAgencia().getEstado());
        parametros.put("ANO_CORRENTE", String.valueOf(ano.format(new Date())) + ".");
        parametros.put("DATA_DOCUMENTO", !"".equals(data_documento) ? data_documento : " _____ de _____________ de _____ ");

        return parametros;
    }

    public void carregarVO() {

        //INFORMAÇÕES COMPLEMENTARES 
        String tripulantes = String.valueOf(agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada() != null ? agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada() : "00");
        String passgeiroEmTransito = String.valueOf(agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada() != null ? agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada() : "00");
        String passageiroParaEstePorto = String.valueOf(passageiroTransito != null ? passageiroTransito : "00");
        String clandestinoEImpedidos = String.valueOf(clandestinoImpedidos != null ? clandestinoImpedidos : "00");
        String outrosPassageiros = String.valueOf(outros != null ? outros : "00");

        entradaPoliciaFederalVO.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        entradaPoliciaFederalVO.setNacionalidade(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto());
        entradaPoliciaFederalVO.setComandante(agenciamento.getAgenciementoViagem().getComandanteEntrada() != null ? agenciamento.getAgenciementoViagem().getComandanteEntrada() : "-");
        entradaPoliciaFederalVO.setArmador(agenciamento.getEmbarcacao().getArmador() != null ? agenciamento.getEmbarcacao().getArmador() : "-");
        entradaPoliciaFederalVO.setPortoProcedencia(agenciamento.getPortoOrigem().getNomeCompleto());
        entradaPoliciaFederalVO.setDestino(agenciamento.getPortoDestino() != null ? agenciamento.getPortoDestino().getNomeCompleto() : "-");
        entradaPoliciaFederalVO.setNrEscala(nrEscala != null ? nrEscala : "-");
        entradaPoliciaFederalVO.setETS(agenciamento.getDataEstimadaSaida());
        entradaPoliciaFederalVO.setTonelagemLiquida(agenciamento.getEmbarcacao().getArqueacaoLiquida());
        entradaPoliciaFederalVO.setCarga(carga != null ? carga : "-");

        entradaPoliciaFederalVO.setTripulantes(tripulantes);
        entradaPoliciaFederalVO.setPassageiroTransito(passgeiroEmTransito);
        entradaPoliciaFederalVO.setPassageiroParaEstePorto(passageiroParaEstePorto);
        entradaPoliciaFederalVO.setClandestinoImpedidos(clandestinoEImpedidos);
        entradaPoliciaFederalVO.setOutros(outrosPassageiros);

    }

//    public void gerarPendenciasDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario() { 
//        List<Pendencia> listaPendencia = getService().buscarPendenciasDoAgenciamentoPorTipo(agenciamento, TipoPendencia.CONTROLE_FISCALIZACAO_UNICO_ENTRADA);
//        if (listaPendencia.isEmpty()) {
////            getService().criarPendencia(agenciamento, TipoPendencia.PARTE_ENTRADA);           
//        }
//    }
    public void gerarPendenciasDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario() {

        if (agenciamento.isTipoContatoTCP()) {
            if (agenciamento.getDataChegada() != null) {
                List<Pendencia> listaPendenciaEntrada = getService().buscarPendenciasDoAgenciamentoPorTipo(agenciamento, TipoPendencia.CABOTAGEM_PASSE_ENTRADA);
                if (listaPendenciaEntrada.isEmpty()) {
                    getService().criarPendencia(agenciamento, TipoPendencia.CABOTAGEM_PASSE_ENTRADA);
                } else {
                    getService().removerResolucaoDaPendencia(listaPendenciaEntrada.get(0));
                }

                List<Pendencia> listaPendenciaSaida = getService().buscarPendenciasDoAgenciamentoPorTipo(agenciamento, TipoPendencia.CABOTAGEM_PASSE_SAIDA);
                if (listaPendenciaSaida.isEmpty()) {
                    getService().criarPendencia(agenciamento, TipoPendencia.CABOTAGEM_PASSE_SAIDA);
                } else {
                    getService().removerResolucaoDaPendencia(listaPendenciaSaida.get(0));
                }
            }

            if (agenciamento.getDataEstimadaSaida() != null) {
                List<Pendencia> listaPendencia = getService().buscarPendenciasDoAgenciamentoPorTipo(agenciamento, TipoPendencia.CABOTAGEM_PASSE_SAIDA);
                if (listaPendencia.isEmpty()) {
                    getService().criarPendencia(agenciamento, TipoPendencia.CABOTAGEM_PASSE_SAIDA);
                } else {
                    getService().removerResolucaoDaPendencia(listaPendencia.get(0));
                }
            }
        }
    }

    public void gerarDocumentoDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario() {

        TipoDocumento tipo = TipoDocumento.PASSE_ENTRADA_PF;

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
        RelatorioUtil.abrirFormularioPasseEntradaPoliciaFederalAgenciaSalvador(agenciamento.getAgencia(), getEntradaPoliciaFederalVO(), obterParametros());
        gerarPendenciasDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario();
        gerarDocumentoDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario();
    }
}
