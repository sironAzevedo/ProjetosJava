package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.LoggerFactory;

public class RelatorioManobraVO implements Serializable, Cloneable {

    private PontoOperacional pontoOperacional;
    private Embarcacao navio;
    private TipoContrato tipoContrato;
    private PontoAtracacao pontoInicio;
    private PontoAtracacao pontoFim;
    private FinalidadeManobra finalidade;
    private Date dataProgramada;
    private Date dataInicioReal;
    private Date dataFimReal;
    private Date dataInicioOperacao;
    private Date dataFimOperacao;
    private String numeroViagem;
    private String observacao;
    private List<ServicoResponsavel> praticos = new ArrayList<ServicoResponsavel>();
    private List<ServicoResponsavel> rebocadores = new ArrayList<ServicoResponsavel>();

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public String getNumeroViagem() {
        return numeroViagem;
    }

    public void setNumeroViagem(String numeroViagem) {
        this.numeroViagem = numeroViagem;
    }

    public Date getDataProgramada() {
        return dataProgramada;
    }

    public void setDataProgramada(Date dataProgramada) {
        this.dataProgramada = dataProgramada;
    }

    public Date getDataInicioReal() {
        return dataInicioReal;
    }

    public void setDataInicioReal(Date dataInicioReal) {
        this.dataInicioReal = dataInicioReal;
    }

    public Date getDataFimReal() {
        return dataFimReal;
    }

    public void setDataFimReal(Date dataFimReal) {
        this.dataFimReal = dataFimReal;
    }

    public Date getDataInicioOperacao() {
        return dataInicioOperacao;
    }

    public void setDataInicioOperacao(Date dataInicioOperacao) {
        this.dataInicioOperacao = dataInicioOperacao;
    }

    public Date getDataFimOperacao() {
        return dataFimOperacao;
    }

    public void setDataFimOperacao(Date dataFimOperacao) {
        this.dataFimOperacao = dataFimOperacao;
    }

    public FinalidadeManobra getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(FinalidadeManobra finalidade) {
        this.finalidade = finalidade;
    }

    public PontoAtracacao getPontoInicio() {
        return pontoInicio;
    }

    public void setPontoInicio(PontoAtracacao pontoInicio) {
        this.pontoInicio = pontoInicio;
    }

    public PontoAtracacao getPontoFim() {
        return pontoFim;
    }

    public void setPontoFim(PontoAtracacao pontoFim) {
        this.pontoFim = pontoFim;
    }

    public List<ServicoResponsavel> getPraticos() {
        return praticos;
    }

    public void setPraticos(List<ServicoResponsavel> praticos) {
        this.praticos = praticos;
    }

    public List<ServicoResponsavel> getRebocadores() {
        return rebocadores;
    }

    public void setRebocadores(List<ServicoResponsavel> rebocadores) {
        this.rebocadores = rebocadores;
    }

    public PontoOperacional getPontoOperacional() {
        return pontoOperacional;
    }

    public void setPontoOperacional(PontoOperacional pontoOperacional) {
        this.pontoOperacional = pontoOperacional;
    }

    public Embarcacao getNavio() {
        return navio;
    }

    public void setNavio(Embarcacao navio) {
        this.navio = navio;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    //</editor-fold>

    @Override
    public RelatorioManobraVO clone() {
        try {
            RelatorioManobraVO vo = (RelatorioManobraVO) BeanUtils.cloneBean(this);
            vo.setPraticos(new ArrayList<ServicoResponsavel>(praticos));
            vo.setRebocadores(new ArrayList<ServicoResponsavel>(rebocadores));
            return vo;
        } catch (Exception ex) {
            LoggerFactory.getLogger(RelatorioManobraVO.class.getName()).error(ex.getMessage(), ex);
            throw new BusinessException(ex.getMessage(), ex);
        }
    }
}