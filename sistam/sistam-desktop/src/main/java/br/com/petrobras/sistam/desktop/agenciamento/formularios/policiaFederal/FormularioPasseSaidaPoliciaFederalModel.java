package br.com.petrobras.sistam.desktop.agenciamento.formularios.policiaFederal;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoPasseAcessoPoliciaFederal;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FormularioPasseSaidaPoliciaFederalVO;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FormularioPasseSaidaPoliciaFederalModel extends BasePresentationModel<SistamService> {

    private Agenciamento agenciamento;
    private FormularioPasseSaidaPoliciaFederalVO saidaPoliciaFederalVO = new FormularioPasseSaidaPoliciaFederalVO();
    private List<TipoPasseAcessoPoliciaFederal> listaPasseAcessoPoliciaFederal;
    private String nrEscala;
    private String carga;
    //EM TRANSITO    
    private Integer clandestinoImpedidosTransito;
    private Integer outrosTransito;
    //DESEMBARQUE NESTE PORTO
    private Integer tripulanteChegadaEmbarcao;
    private Integer passageiroChegadaEmbarcao;
    private Integer clandestinoImpedidosChegadaEmbarcacao;
    private Integer outrosChegadaEmbarcacao;
    //EMBARQUE NESTE PORTO
    private Integer tripulanteSaidaEmbarcao;
    private Integer passageiroSaidaEmbarcao;
    private Integer clandestinoImpedidosSaidaEmbarcacao;
    private Integer tecnicosSaidaEmbarcacao;

    public FormularioPasseSaidaPoliciaFederalModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        this.saidaPoliciaFederalVO = new FormularioPasseSaidaPoliciaFederalVO();
        setListaPasseAcessoPoliciaFederal(new ArrayList<TipoPasseAcessoPoliciaFederal>(Arrays.asList(TipoPasseAcessoPoliciaFederal.values())));

    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public FormularioPasseSaidaPoliciaFederalVO getSaidaPoliciaFederalVO() {
        return saidaPoliciaFederalVO;
    }

    public void setSaidaPoliciaFederalVO(FormularioPasseSaidaPoliciaFederalVO saidaPoliciaFederalVO) {
        this.saidaPoliciaFederalVO = saidaPoliciaFederalVO;
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

    public List<TipoPasseAcessoPoliciaFederal> getListaPasseAcessoPoliciaFederal() {
        return listaPasseAcessoPoliciaFederal;
    }

    public void setListaPasseAcessoPoliciaFederal(List<TipoPasseAcessoPoliciaFederal> listaPasseAcessoPoliciaFederal) {
        this.listaPasseAcessoPoliciaFederal = listaPasseAcessoPoliciaFederal;
    }

    public Integer getClandestinoImpedidosChegadaEmbarcacao() {
        return clandestinoImpedidosChegadaEmbarcacao;
    }

    public void setClandestinoImpedidosChegadaEmbarcacao(Integer clandestinoImpedidosChegadaEmbarcacao) {
        this.clandestinoImpedidosChegadaEmbarcacao = clandestinoImpedidosChegadaEmbarcacao;
    }

    public Integer getClandestinoImpedidosSaidaEmbarcacao() {
        return clandestinoImpedidosSaidaEmbarcacao;
    }

    public void setClandestinoImpedidosSaidaEmbarcacao(Integer clandestinoImpedidosSaidaEmbarcacao) {
        this.clandestinoImpedidosSaidaEmbarcacao = clandestinoImpedidosSaidaEmbarcacao;
    }

    public Integer getTecnicosSaidaEmbarcacao() {
        return tecnicosSaidaEmbarcacao;
    }

    public void setTecnicosSaidaEmbarcacao(Integer tecnicosSaidaEmbarcacao) {
        this.tecnicosSaidaEmbarcacao = tecnicosSaidaEmbarcacao;
    }

    public Integer getTripulanteChegadaEmbarcao() {
        return tripulanteChegadaEmbarcao;
    }

    public void setTripulanteChegadaEmbarcao(Integer tripulanteChegadaEmbarcao) {
        this.tripulanteChegadaEmbarcao = tripulanteChegadaEmbarcao;
    }

    public Integer getPassageiroChegadaEmbarcao() {
        return passageiroChegadaEmbarcao;
    }

    public void setPassageiroChegadaEmbarcao(Integer passageiroChegadaEmbarcao) {
        this.passageiroChegadaEmbarcao = passageiroChegadaEmbarcao;
    }

    public Integer getTripulanteSaidaEmbarcao() {
        return tripulanteSaidaEmbarcao;
    }

    public void setTripulanteSaidaEmbarcao(Integer tripulanteSaidaEmbarcao) {
        this.tripulanteSaidaEmbarcao = tripulanteSaidaEmbarcao;
    }

    public Integer getPassageiroSaidaEmbarcao() {
        return passageiroSaidaEmbarcao;
    }

    public void setPassageiroSaidaEmbarcao(Integer passageiroSaidaEmbarcao) {
        this.passageiroSaidaEmbarcao = passageiroSaidaEmbarcao;
    }

    public Integer getClandestinoImpedidosTransito() {
        return clandestinoImpedidosTransito;
    }

    public void setClandestinoImpedidosTransito(Integer clandestinoImpedidosTransito) {
        this.clandestinoImpedidosTransito = clandestinoImpedidosTransito;
    }

    public Integer getOutrosTransito() {
        return outrosTransito;
    }

    public void setOutrosTransito(Integer outrosTransito) {
        this.outrosTransito = outrosTransito;
    }

    public Integer getOutrosChegadaEmbarcacao() {
        return outrosChegadaEmbarcacao;
    }

    public void setOutrosChegadaEmbarcacao(Integer outrosChegadaEmbarcacao) {
        this.outrosChegadaEmbarcacao = outrosChegadaEmbarcacao;
    }

    //</editor-fold>
    public void validar() {

        SistamPendencyManager pm = new SistamPendencyManager();


        pm.verifyAll();
    }

    public Map<String, Object> obterParametros() {
        Map<String, Object> parametros = new HashMap<String, Object>();
        SimpleDateFormat ano = new SimpleDateFormat("yyyy");

        //EM TRANSITO

        int clandestino_Impedidos_Transito = clandestinoImpedidosTransito != null ? clandestinoImpedidosTransito : 0;
        int outros_Transito = outrosTransito != null ? outrosTransito : 0;

        //DESEMBARQUE NESTE PORTO        
        int clandestino_Impedidos_Chegada_Embarcacao = clandestinoImpedidosChegadaEmbarcacao != null ? clandestinoImpedidosChegadaEmbarcacao : 0;
        int outros_Chegada_Embarcacao = outrosChegadaEmbarcacao != null ? outrosChegadaEmbarcacao : 0;

        //EMBARQUE NESTE PORTO 
        int clandestino_Impedidos_Saida_Embarcacao = clandestinoImpedidosSaidaEmbarcacao != null ? clandestinoImpedidosSaidaEmbarcacao : 0;
        int tecnicos_Saida_Embarcacao = tecnicosSaidaEmbarcacao != null ? tecnicosSaidaEmbarcacao : 0;

        //TOTAL DA OPERAÇÃO
        int totalTripulantes = agenciamento.getAgenciementoViagem().getNumeroTripulantesSaida() != null ? agenciamento.getAgenciementoViagem().getNumeroTripulantesSaida() : 0;
        int totalPassageiros = agenciamento.getAgenciementoViagem().getNumeroPassageirosSaida() != null ? agenciamento.getAgenciementoViagem().getNumeroPassageirosSaida() : 0;
        int totalImpedidosClandestino = (clandestino_Impedidos_Transito - clandestino_Impedidos_Chegada_Embarcacao) + clandestino_Impedidos_Saida_Embarcacao;
        int totalOutrosTransito = (outros_Transito - outros_Chegada_Embarcacao) + tecnicos_Saida_Embarcacao;

        //CONVERTENDO O TOTAL P/ STRING
        String total_tripulante = String.valueOf(Math.abs(totalTripulantes) != 0 ? Math.abs(totalTripulantes) : "00");
        String total_passageiro = String.valueOf(Math.abs(totalPassageiros) != 0 ? Math.abs(totalPassageiros) : "00");
        String total_Impedidos_Clandestino = String.valueOf(Math.abs(totalImpedidosClandestino) != 0 ? Math.abs(totalImpedidosClandestino) : "00");
        String total_Outros_Transito = String.valueOf(Math.abs(totalOutrosTransito) != 0 ? Math.abs(totalOutrosTransito) : "00");


        String data_documento = SistamDateUtils.formatDateByExtensive(saidaPoliciaFederalVO.getDataDocumento(), null);

        parametros.put("AGENCIA_NOME", agenciamento.getAgencia().getNome());
        parametros.put("AGENCIA_SIGLA", agenciamento.getAgencia().getEstado());
        parametros.put("ANO_DOCUMENTO", String.valueOf(ano.format(new Date())) + ".");

        parametros.put("TOTAL_TRIPULANTE", total_tripulante);
        parametros.put("TOTAL_PASSAGEIRO", total_passageiro);
        parametros.put("TOTAL_IMPEDIDOS_CLANDESTINO", total_Impedidos_Clandestino);
        parametros.put("TOTAL_OUTROS", total_Outros_Transito);
        parametros.put("DATA_DOCUMENTO", !"".equals(data_documento) ? data_documento : " _____ de _____________ de _____ ");


        return parametros;
    }

    public void carregarVO() {

        //EM TRANSITO   
        Integer numeroTripulantesEmTransito = agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada();
        Integer numeroPassageirosEmTransito = agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada();

        String tripulante_Transito = String.valueOf(numeroTripulantesEmTransito != null && numeroTripulantesEmTransito != 0 ? numeroTripulantesEmTransito : "00");
        String passgeiroEmTransito = String.valueOf(numeroPassageirosEmTransito != null && numeroPassageirosEmTransito != 0 ? numeroPassageirosEmTransito : "00");
        String clandestino_Impedidos_Transito = String.valueOf(clandestinoImpedidosTransito != null && clandestinoImpedidosTransito != 0 ? clandestinoImpedidosTransito : "00");
        String outros_Transito = String.valueOf(outrosTransito != null && outrosTransito != 0 ? outrosTransito : "00");

        //DESEMBARQUE NESTE PORTO
        String tripulantes_Chegada_Embarcacao = String.valueOf(tripulanteChegadaEmbarcao != null && tripulanteChegadaEmbarcao != 0 ? tripulanteChegadaEmbarcao : "00");
        String passageiro_Chegada_Embarcacao = String.valueOf((passageiroChegadaEmbarcao != null) && (passageiroChegadaEmbarcao != 0) ? passageiroChegadaEmbarcao : "00");
        String clandestino_Impedidos_Chegada_Embarcacao = String.valueOf(clandestinoImpedidosChegadaEmbarcacao != null && clandestinoImpedidosChegadaEmbarcacao != 0 ? clandestinoImpedidosChegadaEmbarcacao : "00");
        String outros_Chegada_Embarcacao = String.valueOf(outrosChegadaEmbarcacao != null && outrosChegadaEmbarcacao != 0 ? outrosChegadaEmbarcacao : "00");

        //EMBARQUE NESTE PORTO
        String tripulantes_Saida_Embarcacao = String.valueOf(tripulanteSaidaEmbarcao != null && tripulanteSaidaEmbarcao != 0 ? tripulanteSaidaEmbarcao : "00");
        String passageiro_Saida_Embarcacao = String.valueOf(passageiroSaidaEmbarcao != null && passageiroSaidaEmbarcao != 0 ? passageiroSaidaEmbarcao : "00");
        String clandestino_Impedidos_Saida_Embarcacao = String.valueOf(clandestinoImpedidosSaidaEmbarcacao != null && clandestinoImpedidosSaidaEmbarcacao != 0 ? clandestinoImpedidosSaidaEmbarcacao : "00");
        String tecnicos_Saida_Embarcacao = String.valueOf(tecnicosSaidaEmbarcacao != null && tecnicosSaidaEmbarcacao != 0 ? tecnicosSaidaEmbarcacao : "00");

        //EM TRANSITO         
        saidaPoliciaFederalVO.setTripulanteTransito(tripulante_Transito);
        saidaPoliciaFederalVO.setPassageiroTransito(passgeiroEmTransito);
        saidaPoliciaFederalVO.setClandestinoImpedidosTransito(clandestino_Impedidos_Transito);
        saidaPoliciaFederalVO.setOutrosTransito(outros_Transito);

        //DESEMBARQUE NESTE PORTO
        saidaPoliciaFederalVO.setTripulantesChegadaEmbarcacao(tripulantes_Chegada_Embarcacao);
        saidaPoliciaFederalVO.setPassageiroParaEstePorto(passageiro_Chegada_Embarcacao);
        saidaPoliciaFederalVO.setClandestinoImpedidosChegadaEmbarcacao(clandestino_Impedidos_Chegada_Embarcacao);
        saidaPoliciaFederalVO.setOutrosChegadaEmbarcacao(outros_Chegada_Embarcacao);

        //EMBARQUE NESTE PORTO
        saidaPoliciaFederalVO.setTripulantesSaidaEmbarcacao(tripulantes_Saida_Embarcacao);
        saidaPoliciaFederalVO.setPassageiroSaidaEmbarcacao(passageiro_Saida_Embarcacao);
        saidaPoliciaFederalVO.setClandestinoImpedidosSaidaEmbarcacao(clandestino_Impedidos_Saida_Embarcacao);
        saidaPoliciaFederalVO.setTecnicosSaidaEmbarcacao(tecnicos_Saida_Embarcacao);

        //DADOS EM COMUN DE TODAS AS AGENCIAS
        saidaPoliciaFederalVO.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        saidaPoliciaFederalVO.setNacionalidade(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto());
        saidaPoliciaFederalVO.setComandante(agenciamento.getAgenciementoViagem().getComandanteSaida() != null ? agenciamento.getAgenciementoViagem().getComandanteSaida() : "-");
        saidaPoliciaFederalVO.setArmador(agenciamento.getEmbarcacao().getArmador() != null ? agenciamento.getEmbarcacao().getArmador() : "-");
        saidaPoliciaFederalVO.setDestino(agenciamento.getPortoDestino() != null ? agenciamento.getPortoDestino().getNomeCompleto() : "-");
        saidaPoliciaFederalVO.setNrEscala(nrEscala != null ? nrEscala : "-");
        saidaPoliciaFederalVO.setETS(agenciamento.getDataEstimadaSaida());
        saidaPoliciaFederalVO.setTonelagemLiquida(agenciamento.getEmbarcacao().getArqueacaoLiquida());
        saidaPoliciaFederalVO.setCarga(carga != null ? carga : "-");
    }

    public void gerarPendenciasDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario() {
        if (agenciamento.isTipoContatoTCP()) {
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

        TipoDocumento tipo = TipoDocumento.PASSE_SAIDA_PF;

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
        RelatorioUtil.abrirFormularioPasseSaidaPoliciaFederalAgenciaSalvador(agenciamento.getAgencia(), getSaidaPoliciaFederalVO(), obterParametros());
        gerarPendenciasDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario();
        gerarDocumentoDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario();
    }
}
