package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.io.Serializable;
import java.util.Date;


/**
 * Filtro de busca para objetos da classe Agenciamento.
 */
public class RelatorioRetiradaResiduoLixoVO extends AbstractBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String nomeEmbarcacao;
    private String origemEmbarcacao;
    private String empresaResponsavel;
    private String nomeEmbarcacaoTransbordo;
    private String transportadoraRodoviaria;
    private String tipoResiduo;
    private String caracterizacao;
    private String estadoFisico;
    private String classificacao;
    private String codigoOnu;
    private Integer quantidadeResiduo;
    private String unidadeMedida;
    private Date dataOperacao;
    private Date dataPeriodo;
    private Date horarioInicioOperacao;
    private Date horarioTerminoOperacao;
    private String armazenamentoTemporarioResiduo;
    private String loTemporario;
    private String cadriTemporario;
    private String destinoFinalResiduo;
    private String loFinal;
    private String cadriFinal;

    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        this.nomeEmbarcacao = nomeEmbarcacao;
    }

    public String getOrigemEmbarcacao() {
        return origemEmbarcacao;
    }

    public void setOrigemEmbarcacao(String origemEmbarcacao) {
        this.origemEmbarcacao = origemEmbarcacao;
    }

    public String getEmpresaResponsavel() {
        return empresaResponsavel;
    }

    public void setEmpresaResponsavel(String empresaResponsavel) {
        this.empresaResponsavel = empresaResponsavel;
    }

    public String getNomeEmbarcacaoTransbordo() {
        return nomeEmbarcacaoTransbordo;
    }

    public void setNomeEmbarcacaoTransbordo(String nomeEmbarcacaoTransbordo) {
        this.nomeEmbarcacaoTransbordo = nomeEmbarcacaoTransbordo;
    }

    public String getTransportadoraRodoviaria() {
        return transportadoraRodoviaria;
    }

    public void setTransportadoraRodoviaria(String transportadoraRodoviaria) {
        this.transportadoraRodoviaria = transportadoraRodoviaria;
    }

    public String getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(String tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public String getCaracterizacao() {
        return caracterizacao;
    }

    public void setCaracterizacao(String caracterizacao) {
        this.caracterizacao = caracterizacao;
    }

    public String getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoFisico(String estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getCodigoOnu() {
        return codigoOnu;
    }

    public void setCodigoOnu(String codigoOnu) {
        this.codigoOnu = codigoOnu;
    }

    public Integer getQuantidadeResiduo() {
        return quantidadeResiduo;
    }

    public void setQuantidadeResiduo(Integer quantidadeResiduo) {
        this.quantidadeResiduo = quantidadeResiduo;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    } 
    
    public String getDataOperacao(){
        return dataOperacao == null ? "" : SistamDateUtils.formatDate(dataOperacao, SistamDateUtils.DATE_PATTERN, null); 
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    } 

    public String getDataPeriodo() {
        return dataPeriodo == null ? "" : SistamDateUtils.formatDate(dataPeriodo, SistamDateUtils.DATE_PATTERN, null);
    }

    public void setDataPeriodo(Date dataPeriodo) {
        this.dataPeriodo = dataPeriodo;
    } 
    
    public String getHorarioInicioOperacao() {
        return horarioInicioOperacao == null ? "" : (SistamDateUtils.formatDate(horarioInicioOperacao, SistamDateUtils.HOUR_PATTERN, null) + "h");
    }

    public void setHorarioInicioOperacao(Date horarioInicioOperacao) {
        this.horarioInicioOperacao = horarioInicioOperacao;
    }

    public String getHorarioTerminoOperacao() {
        return horarioTerminoOperacao == null ? "" : (SistamDateUtils.formatDate(horarioTerminoOperacao, SistamDateUtils.HOUR_PATTERN, null) + "h");
    }

    public void setHorarioTerminoOperacao(Date horarioTerminoOperacao) {
        this.horarioTerminoOperacao = horarioTerminoOperacao;
    }

    public String getArmazenamentoTemporarioResiduo() {
        return armazenamentoTemporarioResiduo;
    }

    public void setArmazenamentoTemporarioResiduo(String armazenamentoTemporarioResiduo) {
        this.armazenamentoTemporarioResiduo = armazenamentoTemporarioResiduo;
    }

    public String getLoTemporario() {
        return loTemporario;
    }

    public void setLoTemporario(String loTemporario) {
        this.loTemporario = loTemporario;
    }

    public String getCadriTemporario() {
        return cadriTemporario;
    }

    public void setCadriTemporario(String cadriTemporario) {
        this.cadriTemporario = cadriTemporario;
    }

    public String getDestinoFinalResiduo() {
        return destinoFinalResiduo;
    }

    public void setDestinoFinalResiduo(String destinoFinalResiduo) {
        this.destinoFinalResiduo = destinoFinalResiduo;
    }

    public String getLoFinal() {
        return loFinal;
    }

    public void setLoFinal(String loFinal) {
        this.loFinal = loFinal;
    }

    public String getCadriFinal() {
        return cadriFinal;
    }

    public void setCadriFinal(String cadriFinal) {
        this.cadriFinal = cadriFinal;
    }
    
}
       