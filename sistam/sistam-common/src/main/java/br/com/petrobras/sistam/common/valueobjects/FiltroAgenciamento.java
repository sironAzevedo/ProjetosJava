package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.Acompanhamento;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.AreaNavegacao;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoArmador;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoFrota;
import java.io.Serializable;
import java.util.Date;

/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class FiltroAgenciamento extends AbstractBean implements Serializable {
    public static final String PROP_AGENCIA = "agencia";
    
    private static final long serialVersionUID = 1L;
    private Agencia agencia;
    private Porto porto;
    private Embarcacao embarcacao; 
    private String vgm;
    private String numeroProcesso;
    private StatusEmbarcacao statusEmbarcacao;
    private TipoFrota tipoFrota;
    private TipoContrato tipoContrato;
    private AreaNavegacao areaNavegacao;
    private Porto portoOrigem;
    private Porto portoDestino;
    private Date dataSaidaIni;
    private Date dataSaidaFim;
    private Date dataChegadaIni;
    private Date dataChegadaFim;
    private Date dataInclusaoIni;
    private Date dataInclusaoFim;
    private Date etaInicial;
    private Date etaFinal;
    private TipoArmador tipoArmador;
    private Acompanhamento acompanhamentos;

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
        firePropertyChange(PROP_AGENCIA, null, this.agencia);
    }

    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
    }

    public Embarcacao getEmbarcacao() {
        return embarcacao;
    }

    public void setEmbarcacao(Embarcacao embarcacao) {
        this.embarcacao = embarcacao;
    } 

    public String getVgm() {
        return vgm;
    }

    public void setVgm(String vgm) {
        this.vgm = vgm;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public StatusEmbarcacao getStatusEmbarcacao() {
        return statusEmbarcacao;
    }

    public void setStatusEmbarcacao(StatusEmbarcacao statusEmbarcacao) {
        this.statusEmbarcacao = statusEmbarcacao;
    }

    public TipoFrota getTipoFrota() {
        return tipoFrota;
    }

    public void setTipoFrota(TipoFrota tipoFrota) {
        this.tipoFrota = tipoFrota;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public AreaNavegacao getAreaNavegacao() {
        return areaNavegacao;
    }

    public void setAreaNavegacao(AreaNavegacao areaNavegacao) {
        this.areaNavegacao = areaNavegacao;
    }

    public Porto getPortoOrigem() {
        return portoOrigem;
    }

    public void setPortoOrigem(Porto portoOrigem) {
        this.portoOrigem = portoOrigem;
    }

    public Porto getPortoDestino() {
        return portoDestino;
    }

    public void setPortoDestino(Porto portoDestino) {
        this.portoDestino = portoDestino;
    }

    public Date getDataSaidaIni() {
        return dataSaidaIni;
    }

    public void setDataSaidaIni(Date dataSaidaIni) {
        this.dataSaidaIni = dataSaidaIni;
    }

    public Date getDataSaidaFim() {
        return dataSaidaFim;
    }

    public void setDataSaidaFim(Date dataSaidaFim) {
        this.dataSaidaFim = dataSaidaFim;
    }

    public Date getDataChegadaIni() {
        return dataChegadaIni;
    }

    public void setDataChegadaIni(Date dataChegadaIni) {
        this.dataChegadaIni = dataChegadaIni;
    }

    public Date getDataChegadaFim() {
        return dataChegadaFim;
    }

    public void setDataChegadaFim(Date dataChegadaFim) {
        this.dataChegadaFim = dataChegadaFim;
    }

    public Date getDataInclusaoIni() {
        return dataInclusaoIni;
    }

    public void setDataInclusaoIni(Date dataInclusaoIni) {
        this.dataInclusaoIni = dataInclusaoIni;
    }

    public Date getDataInclusaoFim() {
        return dataInclusaoFim;
    }

    public void setDataInclusaoFim(Date dataInclusaoFim) {
        this.dataInclusaoFim = dataInclusaoFim;
    }

    public TipoArmador getTipoArmador() {
        return tipoArmador;
    }

    public void setTipoArmador(TipoArmador tipoArmador) {
        this.tipoArmador = tipoArmador;
    }

    public Date getEtaInicial() {
        return etaInicial;
    }

    public void setEtaInicial(Date etaInicial) {
        this.etaInicial = etaInicial;
    }

    public Date getEtaFinal() {
        return etaFinal;
    }

    public void setEtaFinal(Date etaFinal) {
        this.etaFinal = etaFinal;
    }

    public Acompanhamento getAcompanhamentos() {
        return acompanhamentos;
    }

    public void setAcompanhamentos(Acompanhamento acompanhamentos) {
        this.acompanhamentos = acompanhamentos;
    } 
}
