package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

public class RelatorioVisitaVO implements Serializable, Cloneable {

    private TimeZone timeZone;
    private Embarcacao navio;
    private String numeroViagem;
    private Date data;
    private String lancha;
    private String servico;
    private String condicaoNavio;
    private Date dataRequisicao;
    private Date dataInicio;
    private Date dataTermino;
    private Usuario agente;
    private String observacao;

    public RelatorioVisitaVO(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public TimeZone getTimeZone() {
        return timeZone;
    }

    public Embarcacao getNavio() {
        return navio;
    }

    public void setNavio(Embarcacao navio) {
        this.navio = navio;
    }

    public String getNumeroViagem() {
        return numeroViagem;
    }

    public void setNumeroViagem(String numeroViagem) {
        this.numeroViagem = numeroViagem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLancha() {
        return lancha;
    }

    public void setLancha(String lancha) {
        this.lancha = lancha;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getCondicaoNavio() {
        return condicaoNavio;
    }

    public void setCondicaoNavio(String condicaoNavio) {
        this.condicaoNavio = condicaoNavio;
    }

    public Date getDataRequisicao() {
        return dataRequisicao;
    }

    public void setDataRequisicao(Date dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Usuario getAgente() {
        return agente;
    }

    public void setAgente(Usuario agente) {
        this.agente = agente;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    //</editor-fold>

    public String getDiferencaDataInicoEFim() {
        long diferenca = SistamDateUtils.diferencaEmMinutos(dataInicio, dataTermino, timeZone);
        return String.format("%02d", diferenca / 60) + ":" + String.format("%02d", diferenca % 60);
    }
}