package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.enums.TipoRequerimentoPasseSaidaPoliciaFederal;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.io.Serializable;
import java.util.Date;

public class FormularioRequerimentoPasseSaidaPoliciaFederalVO extends AbstractBean implements Serializable {

    //INFORMAÇÕES COMPLEMENTARES 
    
    private String nomeEmbarcacao;
    private String nacionalidade;
    private String petrobrasAgenciaMaritima;
    private String petrobrasPetroleoBrasileiro;
    private String classificacaoNavio;
    private String numeroPasseEntrada;
    private Date dataEntradaNoPorto;
    private Date dataDocumento;
    private RepresentanteLegal listaRepresentante;
    private TipoRequerimentoPasseSaidaPoliciaFederal tipoRequerimentoPasseSaidaPoliciaFederal;
    private String carga;
    private String destino;
    private String justificativa;
    private String comandanteSaida;
    private Date ETS;

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
     
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
     
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    } 
     
    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }     
    
    public RepresentanteLegal getListaRepresentante() {
        return listaRepresentante;
    }

    public void setListaRepresentante(RepresentanteLegal listaRepresentante) {
        this.listaRepresentante = listaRepresentante;
    } 

    public TipoRequerimentoPasseSaidaPoliciaFederal getTipoRequerimentoPasseSaidaPoliciaFederal() {
        return tipoRequerimentoPasseSaidaPoliciaFederal;
    }

    public void setTipoRequerimentoPasseSaidaPoliciaFederal(TipoRequerimentoPasseSaidaPoliciaFederal tipoRequerimentoPasseSaidaPoliciaFederal) {
        this.tipoRequerimentoPasseSaidaPoliciaFederal = tipoRequerimentoPasseSaidaPoliciaFederal;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }  

    public String getPetrobrasAgenciaMaritima() {
        return petrobrasAgenciaMaritima;
    }

    public void setPetrobrasAgenciaMaritima(String petrobrasAgenciaMaritima) {
        this.petrobrasAgenciaMaritima = petrobrasAgenciaMaritima;
    }

    public String getPetrobrasPetroleoBrasileiro() {
        return petrobrasPetroleoBrasileiro;
    }

    public void setPetrobrasPetroleoBrasileiro(String petrobrasPetroleoBrasileiro) {
        this.petrobrasPetroleoBrasileiro = petrobrasPetroleoBrasileiro;
    }  

    public Date getDataEntradaNoPorto() {
        return dataEntradaNoPorto;
    }

    public void setDataEntradaNoPorto(Date dataEntradaNoPorto) {
        this.dataEntradaNoPorto = dataEntradaNoPorto;
    }  

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    } 
    
    public String getETS() {
        return ETS == null ? " - " : SistamDateUtils.formatDate(ETS, SistamDateUtils.DATE_PATTERN, null);
    }

    public void setETS(Date ETS) {
        this.ETS = ETS;
    } 

    public String getComandanteSaida() {
        return comandanteSaida;
    }

    public void setComandanteSaida(String comandanteSaida) {
        this.comandanteSaida = comandanteSaida;
    } 

    public String getClassificacaoNavio() {
        return classificacaoNavio;
    }

    public void setClassificacaoNavio(String classificacaoNavio) {
        this.classificacaoNavio = classificacaoNavio;
    }  

    public String getNumeroPasseEntrada() {
        return numeroPasseEntrada;
    }

    public void setNumeroPasseEntrada(String numeroPasseEntrada) {
        this.numeroPasseEntrada = numeroPasseEntrada;
    }
    
    //</editor-fold>  
    
}
