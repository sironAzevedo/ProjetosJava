package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.enums.TipoPasseAcessoPoliciaFederal;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.io.Serializable;
import java.util.Date;

public class FormularioPasseEntradaPoliciaFederalVO extends AbstractBean implements Serializable {

  //INFORMAÇÕES COMPLEMENTARES 
    private String nrEscala;
    private String produtoCarga;
    private String passageiroTransito;
    private String clandestinoImpedidos;
    private String outros; 
    private TipoPasseAcessoPoliciaFederal tipoPasseAcessoPoliciaFederal; 
    
    //DADOS EM COMUN AGENCIA
    private String nomeEmbarcacao;
    private String nacionalidade; 
    private String comandante;
    private String armador; 
    private String portoProcedencia;
    private String destino;
    private Date ETS;
    private Date anoDocumento;
    private Date dataDocumento;
    private Long tonelagemLiquida;
    private String carga; 
    private String tripulantes;
    private String passageiroParaEstePorto;
   

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

    public String getTripulantes() {
        return tripulantes;
    }

    public void setTripulantes(String tripulantes) {
        this.tripulantes = tripulantes;
    } 

    public String getPassageiroParaEstePorto() {
        return passageiroParaEstePorto;
    }

    public void setPassageiroParaEstePorto(String passageiroParaEstePorto) {
        this.passageiroParaEstePorto = passageiroParaEstePorto;
    } 

    public String getClandestinoImpedidos() {
        return clandestinoImpedidos;
    }

    public void setClandestinoImpedidos(String clandestinoImpedidos) {
        this.clandestinoImpedidos = clandestinoImpedidos;
    }

    public String getOutros() {
        return outros;
    }

    public void setOutros(String outros) {
        this.outros = outros;
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

    public String getETS() {
        return ETS == null ? " - " : SistamDateUtils.formatDate(ETS, SistamDateUtils.DATE_HOUR_PATTERN, null);
    }

    public void setETS(Date ETS) {
        this.ETS = ETS;
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
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
 //</editor-fold> 
    
    
    
}
