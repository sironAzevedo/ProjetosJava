package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoFrota;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FiltroRelatorioTimesheet extends AbstractBean {

    public static final String PROP_AGENCIA = "agencia";
    public static final String PROP_PORTO = "porto";
    public static final String PROP_SITUACAO_EMBARCACAO = "situacaoEmbarcacao";
    public static final String PROP_TIPO_FROTA = "tipoFrota";
    
    private Agencia agencia;
    private Porto porto;
    private StatusEmbarcacao situacaoEmbarcacao;
    private TipoFrota tipoFrota;
    private List<Embarcacao> embarcacoes = new ArrayList<Embarcacao>();
    private List<Agencia> agencias = new ArrayList<Agencia>();
    private Date dataDeCorte;
    
    public FiltroRelatorioTimesheet(){
         //Data de Corte para as Embarcações, com status Saído, é a Data Atual -1 dias
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        this.dataDeCorte = SistamDateUtils.alterarHorarioParaInicioDia(calendar.getTime(), null);
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
        firePropertyChange("agencia", null, null);
    }

    public List<Agencia> getAgencias() {
        return agencias;
    }

    public void setAgencias(List<Agencia> agencias) {
        this.agencias = agencias;
    } 

    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
        firePropertyChange("porto", null, null);
    }

    public StatusEmbarcacao getSituacaoEmbarcacao() {
        return situacaoEmbarcacao;
    }

    public void setSituacaoEmbarcacao(StatusEmbarcacao situacaoEmbarcacao) {
        this.situacaoEmbarcacao = situacaoEmbarcacao;
        firePropertyChange("situacaoEmbarcacao", null, null);
    }

    public TipoFrota getTipoFrota() {
        return tipoFrota;
    }

    public void setTipoFrota(TipoFrota tipoFrota) {
        this.tipoFrota = tipoFrota;
        firePropertyChange("tipoFrota", null, null);
    } 

    public List<Embarcacao> getEmbarcacoes() {
        return embarcacoes;
    }

    public void setEmbarcacoes(List<Embarcacao> embarcacoes) {
        this.embarcacoes = embarcacoes;
    }

    public Date getDataDeCorte() {
        return dataDeCorte;
    }

    public void setDataDeCorte(Date dataDeCorte) {
        this.dataDeCorte = dataDeCorte;
    }

}