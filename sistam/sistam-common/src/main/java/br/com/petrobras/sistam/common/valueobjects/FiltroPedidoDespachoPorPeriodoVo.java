package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.enums.TipoSimNao;
import java.io.Serializable;

/**
 * Classe que contém as informações para geração do formulário Pedido de Despacho por Periodo
 */
public class FiltroPedidoDespachoPorPeriodoVo implements Serializable{
    private String duv;
    private String portoAgenciamento;
    private String dataEntradaAgenciamento;
    
    private String orgaoDespachoAgencia;
    
    private String nomeEmbarcacao;
    private TipoSimNao embarcacaoPrep; 
    private String irin;
    private String numeroInscricao;
    private String tipo;
    private String areaNavegacao;
    private String imo;
    private String comprimentoTotalLoa;
    private String bandeiraEmbarcacao;
    private TipoSimNao atestadoInscricaoTemporariaEmbarcacao;
    private Long porteBrutoDwt;
    private Long arqueacaoBrutaGrt;
    private String nomeComadanteSaidaEmbarcao;
    private String armadorEmbarcacao ; 
    private String numeroRegistroCRA; 
    private String validadeCRA;
    private String cnpjArmador;
    private String representanteLegalEmbarcacao;
    private String representanteLegalEmbarcacaoEmail;
    
    private Boolean vhf = false;
    private Boolean mfComDsc = false;
    private Boolean vhsSemDsc = false;
    private Boolean semDsc = false;
    private Boolean hfComDsc = false;
    private Boolean semDsc1 = false;
    private Boolean inmarssatTipoA = false;
    private Boolean tranponder9Ghz = false;
    private Boolean epirb = false;
    private Boolean navtex = false; 
    
    private String observacao;  
    private String dataDocumento;
    
    private String nomeAgencia;
        

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters"> 
    
     public String getNomeAgencia() {
        return nomeAgencia;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }
    
    public String getDuv() {
        return duv;
    }

    public void setDuv(String duv) {
        this.duv = duv;
    }

    public String getPortoAgenciamento() {
        return portoAgenciamento;
    }

    public void setPortoAgenciamento(String portoAgenciamento) {
        this.portoAgenciamento = portoAgenciamento;
    }

    public String getDataEntradaAgenciamento() {
        return dataEntradaAgenciamento;
    }

    public void setDataEntradaAgenciamento(String dataEntradaAgenciamento) {
        this.dataEntradaAgenciamento = dataEntradaAgenciamento;
    } 

    public String getOrgaoDespachoAgencia() {
        return orgaoDespachoAgencia;
    }

    public void setOrgaoDespachoAgencia(String orgaoDespachoAgencia) {
        this.orgaoDespachoAgencia = orgaoDespachoAgencia;
    }

    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        this.nomeEmbarcacao = nomeEmbarcacao;
    } 

    public TipoSimNao getEmbarcacaoPrep() {
        return embarcacaoPrep;
    }

    public void setEmbarcacaoPrep(TipoSimNao embarcacaoPrep) {
        this.embarcacaoPrep = embarcacaoPrep;
    }

    public String getIrin() {
        return irin;
    }

    public void setIrin(String irin) {
        this.irin = irin;
    }

    public String getNumeroInscricao() {
        return numeroInscricao;
    }

    public void setNumeroInscricao(String numeroInscricao) {
        this.numeroInscricao = numeroInscricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAreaNavegacao() {
        return areaNavegacao;
    }

    public void setAreaNavegacao(String areaNavegacao) {
        this.areaNavegacao = areaNavegacao;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getComprimentoTotalLoa() {
        return comprimentoTotalLoa;
    }

    public void setComprimentoTotalLoa(String comprimentoTotalLoa) {
        this.comprimentoTotalLoa = comprimentoTotalLoa;
    } 

    public String getBandeiraEmbarcacao() {
        return bandeiraEmbarcacao;
    }

    public void setBandeiraEmbarcacao(String bandeiraEmbarcacao) {
        this.bandeiraEmbarcacao = bandeiraEmbarcacao;
    }

    public TipoSimNao getAtestadoInscricaoTemporariaEmbarcacao() {
        return atestadoInscricaoTemporariaEmbarcacao;
    }

    public void setAtestadoInscricaoTemporariaEmbarcacao(TipoSimNao atestadoInscricaoTemporariaEmbarcacao) {
        this.atestadoInscricaoTemporariaEmbarcacao = atestadoInscricaoTemporariaEmbarcacao;
    } 

    public Long getPorteBrutoDwt() {
        return porteBrutoDwt;
    }

    public void setPorteBrutoDwt(Long porteBrutoDwt) {
        this.porteBrutoDwt = porteBrutoDwt;
    } 

    public Long getArqueacaoBrutaGrt() {
        return arqueacaoBrutaGrt;
    }

    public void setArqueacaoBrutaGrt(Long arqueacaoBrutaGrt) {
        this.arqueacaoBrutaGrt = arqueacaoBrutaGrt;
    }

    public String getNomeComadanteSaidaEmbarcao() {
        return nomeComadanteSaidaEmbarcao;
    }

    public void setNomeComadanteSaidaEmbarcao(String nomeComadanteSaidaEmbarcao) {
        this.nomeComadanteSaidaEmbarcao = nomeComadanteSaidaEmbarcao;
    } 
    
    public String getArmadorEmbarcacao() {
        return armadorEmbarcacao;
    }

    public void setArmadorEmbarcacao(String armadorEmbarcacao) {
        this.armadorEmbarcacao = armadorEmbarcacao;
    }

    public String getNumeroRegistroCRA() {
        return numeroRegistroCRA;
    }

    public void setNumeroRegistroCRA(String numeroRegistroCRA) {
        this.numeroRegistroCRA = numeroRegistroCRA;
    } 

    public String getValidadeCRA() {
        return validadeCRA;
    }

    public void setValidadeCRA(String validadeCRA) {
        this.validadeCRA = validadeCRA;
    } 

    public String getCnpjArmador() {
        return cnpjArmador;
    }

    public void setCnpjArmador(String cnpjArmador) {
        this.cnpjArmador = cnpjArmador;
    }

    public String getRepresentanteLegalEmbarcacao() {
        return representanteLegalEmbarcacao;
    }

    public void setRepresentanteLegalEmbarcacao(String representanteLegalEmbarcacao) {
        this.representanteLegalEmbarcacao = representanteLegalEmbarcacao;
    }

    public String getRepresentanteLegalEmbarcacaoEmail() {
        return representanteLegalEmbarcacaoEmail;
    }

    public void setRepresentanteLegalEmbarcacaoEmail(String representanteLegalEmbarcacaoEmail) {
        this.representanteLegalEmbarcacaoEmail = representanteLegalEmbarcacaoEmail;
    } 

    public Boolean getVhf() {
        return vhf != null ? vhf : false;
    }

    public void setVhf(Boolean vhf) {
        this.vhf = vhf;
    }

    public Boolean getMfComDsc() {
        return mfComDsc != null ? mfComDsc : false;
    }

    public void setMfComDsc(Boolean mfComDsc) {
        this.mfComDsc = mfComDsc;
    }

    public Boolean getVhsSemDsc() {
        return vhsSemDsc != null ? vhsSemDsc : false;
    }

    public void setVhsSemDsc(Boolean vhsSemDsc) {
        this.vhsSemDsc = vhsSemDsc;
    }

    public Boolean getSemDsc() {
        return semDsc != null ? semDsc : false;
    }

    public void setSemDsc(Boolean semDsc) {
        this.semDsc = semDsc;
    }

    public Boolean getHfComDsc() {
        return hfComDsc != null ? hfComDsc : false;
    }

    public void setHfComDsc(Boolean hfComDsc) {
        this.hfComDsc = hfComDsc;
    }

    public Boolean getSemDsc1() {
        return semDsc1 != null ? semDsc1 : false;
    }

    public void setSemDsc1(Boolean semDsc1) {
        this.semDsc1 = semDsc1;
    }

    public Boolean getInmarssatTipoA() {
        return inmarssatTipoA != null ? inmarssatTipoA : false;
    }

    public void setInmarssatTipoA(Boolean inmarssatTipoA) {
        this.inmarssatTipoA = inmarssatTipoA;
    }

    public Boolean getTranponder9Ghz() {
        return tranponder9Ghz != null ? tranponder9Ghz : false;
    }

    public void setTranponder9Ghz(Boolean tranponder9Ghz) {
        this.tranponder9Ghz = tranponder9Ghz;
    }

    public Boolean getEpirb() {
        return epirb != null ? epirb : false;
    }

    public void setEpirb(Boolean epirb) {
        this.epirb = epirb;
    }

    public Boolean getNavtex() {
        return navtex != null ? navtex : false;
    }

    public void setNavtex(Boolean navtex) {
        this.navtex = navtex;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public String getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(String dataDocumento) {
        this.dataDocumento = dataDocumento;
    } 

    //</editor-fold> 

    
}
