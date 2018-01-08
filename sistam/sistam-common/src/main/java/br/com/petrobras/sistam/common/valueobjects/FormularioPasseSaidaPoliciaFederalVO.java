package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.enums.TipoPasseAcessoPoliciaFederal;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.io.Serializable;
import java.util.Date;

public class FormularioPasseSaidaPoliciaFederalVO extends AbstractBean implements Serializable {

    //INFORMAÇÕES COMPLEMENTARES 
    private String nrEscala;
    private String produtoCarga;
    private TipoPasseAcessoPoliciaFederal tipoPasseAcessoPoliciaFederal;
    private Date dataDocumento;
    //TRIPULANTES E PASSAGEIROS - EM TRANSITO
    private String tripulanteTransito;
    private String passageiroTransito;
    private String clandestinoImpedidosTransito;
    private String outrosTransito;
    //TRIPULANTES E PASSAGEIROS - CHEGADA DA EMBARCAÇÃO
    private String tripulantesChegadaEmbarcacao;
    private String passageiroParaEstePorto;
    private String clandestinoImpedidosChegadaEmbarcacao;
    private String outrosChegadaEmbarcacao;
    //TRIPULANTES E PASSAGEIROS - SAÍDA DA EMBARCAÇÃO
    private String tripulantesSaidaEmbarcacao;
    private String passageiroSaidaEmbarcacao;
    private String clandestinoImpedidosSaidaEmbarcacao;
    private String tecnicosSaidaEmbarcacao;
    //DADOS EM COMUN AGENCIA
    private String nomeEmbarcacao;
    private String nacionalidade;
    private String comandante;
    private String armador;
    private String portoProcedencia;
    private String destino;
    private Date ETS;
    private Date anoDocumento;
    private Long tonelagemLiquida;
    private String carga;

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public String getNrEscala() {
        return nrEscala;
    }

    public void setNrEscala(String nrEscala) {
        this.nrEscala = nrEscala;
    }

    public String getProdutoCarga() {
        return produtoCarga;
    }

    public void setProdutoCarga(String produtoCarga) {
        this.produtoCarga = produtoCarga;
    }

    public String getPassageiroTransito() {
        return passageiroTransito;
    }

    public void setPassageiroTransito(String passageiroTransito) {
        this.passageiroTransito = passageiroTransito;
    }

    public String getPassageiroParaEstePorto() {
        return passageiroParaEstePorto;
    }

    public void setPassageiroParaEstePorto(String passageiroParaEstePorto) {
        this.passageiroParaEstePorto = passageiroParaEstePorto;
    }

    public TipoPasseAcessoPoliciaFederal getTipoPasseAcessoPoliciaFederal() {
        return tipoPasseAcessoPoliciaFederal;
    }

    public void setTipoPasseAcessoPoliciaFederal(TipoPasseAcessoPoliciaFederal tipoPasseAcessoPoliciaFederal) {
        this.tipoPasseAcessoPoliciaFederal = tipoPasseAcessoPoliciaFederal;
    }

    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        this.nomeEmbarcacao = nomeEmbarcacao;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getComandante() {
        return comandante;
    }

    public void setComandante(String comandante) {
        this.comandante = comandante;
    }

    public String getArmador() {
        return armador;
    }

    public void setArmador(String armador) {
        this.armador = armador;
    }

    public String getPortoProcedencia() {
        return portoProcedencia;
    }

    public void setPortoProcedencia(String portoProcedencia) {
        this.portoProcedencia = portoProcedencia;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    } 

    public String getETS() {
        return ETS == null ? " - " : SistamDateUtils.formatDate(ETS, SistamDateUtils.DATE_HOUR_PATTERN, null);
    }

    public void setETS(Date ETS) {
        this.ETS = ETS;
    }

    public Date getAnoDocumento() {
        return anoDocumento;
    }

    public void setAnoDocumento(Date anoDocumento) {
        this.anoDocumento = anoDocumento;
    }

    public Long getTonelagemLiquida() {
        return tonelagemLiquida;
    }

    public void setTonelagemLiquida(Long tonelagemLiquida) {
        this.tonelagemLiquida = tonelagemLiquida;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public String getTripulanteTransito() {
        return tripulanteTransito;
    }

    public void setTripulanteTransito(String tripulanteTransito) {
        this.tripulanteTransito = tripulanteTransito;
    }

    public String getClandestinoImpedidosTransito() {
        return clandestinoImpedidosTransito;
    }

    public void setClandestinoImpedidosTransito(String clandestinoImpedidosTransito) {
        this.clandestinoImpedidosTransito = clandestinoImpedidosTransito;
    }

    public String getOutrosTransito() {
        return outrosTransito;
    }

    public void setOutrosTransito(String outrosTransito) {
        this.outrosTransito = outrosTransito;
    }

    public String getTripulantesChegadaEmbarcacao() {
        return tripulantesChegadaEmbarcacao;
    }

    public void setTripulantesChegadaEmbarcacao(String tripulantesChegadaEmbarcacao) {
        this.tripulantesChegadaEmbarcacao = tripulantesChegadaEmbarcacao;
    }

    public String getClandestinoImpedidosChegadaEmbarcacao() {
        return clandestinoImpedidosChegadaEmbarcacao;
    }

    public void setClandestinoImpedidosChegadaEmbarcacao(String clandestinoImpedidosChegadaEmbarcacao) {
        this.clandestinoImpedidosChegadaEmbarcacao = clandestinoImpedidosChegadaEmbarcacao;
    }

    public String getOutrosChegadaEmbarcacao() {
        return outrosChegadaEmbarcacao;
    }

    public void setOutrosChegadaEmbarcacao(String outrosChegadaEmbarcacao) {
        this.outrosChegadaEmbarcacao = outrosChegadaEmbarcacao;
    }

    public String getTripulantesSaidaEmbarcacao() {
        return tripulantesSaidaEmbarcacao;
    }

    public void setTripulantesSaidaEmbarcacao(String tripulantesSaidaEmbarcacao) {
        this.tripulantesSaidaEmbarcacao = tripulantesSaidaEmbarcacao;
    }

    public String getPassageiroSaidaEmbarcacao() {
        return passageiroSaidaEmbarcacao;
    }

    public void setPassageiroSaidaEmbarcacao(String passageiroSaidaEmbarcacao) {
        this.passageiroSaidaEmbarcacao = passageiroSaidaEmbarcacao;
    }

    public String getClandestinoImpedidosSaidaEmbarcacao() {
        return clandestinoImpedidosSaidaEmbarcacao;
    }

    public void setClandestinoImpedidosSaidaEmbarcacao(String clandestinoImpedidosSaidaEmbarcacao) {
        this.clandestinoImpedidosSaidaEmbarcacao = clandestinoImpedidosSaidaEmbarcacao;
    }

    public String getTecnicosSaidaEmbarcacao() {
        return tecnicosSaidaEmbarcacao;
    }

    public void setTecnicosSaidaEmbarcacao(String tecnicosSaidaEmbarcacao) {
        this.tecnicosSaidaEmbarcacao = tecnicosSaidaEmbarcacao;
    }
    //</editor-fold> 
}
