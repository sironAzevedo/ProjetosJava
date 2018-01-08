package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FiltroRelatorioVisita extends AbstractBean {

    public static final String PROP_AGENCIA = "agencia";
    private Agencia agencia;
    private Porto porto;
    private Usuario agente;
    private List<ResponsavelCustoEntity> responsaveis = Collections.EMPTY_LIST;
    private TipoTransporte tipoTransporte;
    private Date inicio;
    private Date termino;

    // <editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
        firePropertyChange("agencia", null, null);
    }

    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
    }

    public Usuario getAgente() {
        return agente;
    }

    public void setAgente(Usuario agente) {
        this.agente = agente;
    }

    public List<ResponsavelCustoEntity> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List<ResponsavelCustoEntity> responsaveis) {
        this.responsaveis = responsaveis;
    }

    public TipoTransporte getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(TipoTransporte tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }
    // </editor-fold>
    
     public String getInicioFormatado(){
        return inicio == null ? "" : SistamDateUtils.formatDate(inicio, SistamDateUtils.DATE_PATTERN, null);
    }
    
    public String getTerminoFormatado(){
        return termino == null ? "" : SistamDateUtils.formatDate(termino, SistamDateUtils.DATE_PATTERN, null);
    }
    public String getResponsaveisDescricao(){
        StringBuilder nomes = new StringBuilder();
        for (ResponsavelCustoEntity responsavel : responsaveis) {
            if (nomes.length() != 0)
                nomes.append(", ");
            nomes.append(responsavel.getDescricao());
        }
        return nomes.toString();
    }
}