package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

public class AgenciamentoSanitariaModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private TimeZone timeZone;
    
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, this.agenciamento);
        
        if (agenciamento != null){
            refreshCampos();
        }
        
        if (agenciamento != null && agenciamento.getAgencia() != null) {
            setTimeZone(TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone()));
        } else {
            setTimeZone(TimeZone.getDefault());
        }    
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        firePropertyChange("timeZone", null, this.timeZone);
    }
    
    

    public List<TipoSimNao> getListaSimNao() {
        return Collections.unmodifiableList(new ArrayList<TipoSimNao> (Arrays.asList(TipoSimNao.values())));
    }

    public TipoSimNao getProduzAguaPotavel() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoSanitaria().isProduzAguaPotavel());
    }

    public void setProduzAguaPotavel(TipoSimNao produzAguaPotavel) {
        this.agenciamento.getAgenciementoSanitaria().setProduzAguaPotavel(produzAguaPotavel.getId());
        firePropertyChange("produzAguaPotavel", null, null);
    }

    public TipoSimNao getTratamentoAguaPotavel() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoSanitaria().isTratamentoAguaPotavel());
    }

    public void setTratamentoAguaPotavel(TipoSimNao tratamentoAguaPotavel) {
        this.agenciamento.getAgenciementoSanitaria().setTratamentoAguaPotavel(tratamentoAguaPotavel.getId());
        firePropertyChange("tratamentoAguaPotavel", null, null);
    }

    public TipoSimNao getAguaLastro() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoSanitaria().isAguaLastro());
    }

    public void setAguaLastro(TipoSimNao aguaLastro) {
        this.agenciamento.getAgenciementoSanitaria().setAguaLastro(aguaLastro.getId());
        firePropertyChange("aguaLastro", null, null);
    }

    public TipoSimNao getSubAguaLastro() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoSanitaria().isSubAguaLastro());
    }
    
    //Serve para habilitar de desabilitar os campos latitude e longitude
    public boolean isSubAguaLastroBoolean() {
        if (agenciamento == null) {
            return false;
        }
       return agenciamento.getAgenciementoSanitaria().isSubAguaLastro(); 
    }

    public void setSubAguaLastro(TipoSimNao subAguaLastro) {
        this.agenciamento.getAgenciementoSanitaria().setSubAguaLastro(subAguaLastro.getId());
        firePropertyChange("subAguaLastro", null, null);
        if (subAguaLastro.equals(TipoSimNao.NAO)){
            agenciamento.getAgenciementoSanitaria().setLatitudeSubstAgua(null);
            agenciamento.getAgenciementoSanitaria().setLongitudeSubstAgua(null);
            firePropertyChange("agenciamento", null, this.agenciamento);
        }
        firePropertyChange("subAguaLastroBoolean", null, subAguaLastro.getId());
    }

    public TipoSimNao getDeslastre() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoSanitaria().isDeslastro());
    }
    
    public void setDeslastre(TipoSimNao deslastre) {
        this.agenciamento.getAgenciementoSanitaria().setDeslastro(deslastre.getId());
        firePropertyChange("deslastre", null, null);
    }

    public TipoSimNao getResiduosSolidos() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoSanitaria().isResiduosSolidos());
    }

    public void setResiduosSolidos(TipoSimNao residuosSolidos) {
        this.agenciamento.getAgenciementoSanitaria().setResiduosSolidos(residuosSolidos.getId());
        firePropertyChange("residuosSolidos", null, null);
    }

    public TipoSimNao getAbastecimentoAgua() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoSanitaria().isAbastecimentoAgua());
    }

    public void setAbastecimentoAgua(TipoSimNao abastecimentoAgua) {
        this.agenciamento.getAgenciementoSanitaria().setAbastecimentoAgua(abastecimentoAgua.getId());
        firePropertyChange("abastecimentoAgua", null, null);
    }

    public TipoSimNao getAbastecimentoAlimento() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoSanitaria().isAbastecimentoAlimento());
    }

    public void setAbastecimentoAlimento(TipoSimNao abastecimentoAlimento) {
        this.agenciamento.getAgenciementoSanitaria().setAbastecimentoAlimento(abastecimentoAlimento.getId());
        firePropertyChange("abastecimentoAlimento", null, null);
    }

    public TipoSimNao getTanqueTratamento() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoSanitaria().isTanqueTratamento());
    }
    
    //Serve para habilitar e desabilitar os campos capacidade de armazenamento e autonomia de retenção.
    public boolean isTanqueTratamentoBoolean() {
        if (agenciamento == null) {
            return false;
        }
         return agenciamento.getAgenciementoSanitaria().isTanqueTratamento();
    }

    public void setTanqueTratamento(TipoSimNao tanqueTratamento) {
        this.agenciamento.getAgenciementoSanitaria().setTanqueTratamento(tanqueTratamento.getId());
        firePropertyChange("tanqueTratamento", null, null);
        if (tanqueTratamento.equals(TipoSimNao.NAO)){
            agenciamento.getAgenciementoSanitaria().setAutonomiaRetencao(null);
            agenciamento.getAgenciementoSanitaria().setCapacidadeEfluente(null);
            firePropertyChange("agenciamento", null, this.agenciamento);
        }
        firePropertyChange("tanqueTratamentoBoolean", null, tanqueTratamento.getId());
    }

    public TipoSimNao getCargaPerigosa() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoSanitaria().isCargaPerigosa());
    }

    public void setCargaPerigosa(TipoSimNao cargaPerigosa) {
        this.agenciamento.getAgenciementoSanitaria().setCargaPerigosa(cargaPerigosa.getId());
        firePropertyChange("cargaPerigosa", null, null);
    }

    public TipoSimNao getDesinsetizacao() {
        if (agenciamento == null) {
            return null;
        }
        return TipoSimNao.from(agenciamento.getAgenciementoSanitaria().isDesinsetizacao());
    }

    //Serve para habilitar e desabilitar os campos Produto e Data
    public boolean isDesinsetizacaoBoolean() {
        if (agenciamento == null) {
            return false;
        }
        return agenciamento.getAgenciementoSanitaria().isDesinsetizacao();
    }
    
    public void setDesinsetizacao(TipoSimNao desinsetizacao) {
        this.agenciamento.getAgenciementoSanitaria().setDesinsetizacao(desinsetizacao.getId());
        firePropertyChange("desinsetizacao", null, null);
         if (desinsetizacao.equals(TipoSimNao.NAO)){
            agenciamento.getAgenciementoSanitaria().setDataDesinsetizacao(null);
            agenciamento.getAgenciementoSanitaria().setProdutoDesinsetizacao(null);            
        }
        firePropertyChange("desinsetizacaoBoolean", null, desinsetizacao.getId());
        firePropertyChange("agenciamento", null, this.agenciamento);
    }

    /*
     * Os campos do tipo da enumeração TipoSimNao devem ser atualizados aqui pois não são afetados
     * pelo firePropertyChange da propriedade agenciamento
     */
    public void refreshCampos() {
       firePropertyChange("produzAguaPotavel", null,null);
        firePropertyChange("tratamentoAguaPotavel", null, null);
        firePropertyChange("aguaLastro", null, null);
        firePropertyChange("subAguaLastro", null, null);
        firePropertyChange("deslastre", null, null);
        firePropertyChange("residuosSolidos", null, null);
        firePropertyChange("abastecimentoAgua", null, null);
        firePropertyChange("abastecimentoAlimento", null, null);
        firePropertyChange("tanqueTratamento", null, null);
        firePropertyChange("cargaPerigosa", null, null);
        firePropertyChange("desinsetizacao", null, null);
        firePropertyChange("desinsetizacaoBoolean", null, agenciamento.getAgenciementoSanitaria().isDesinsetizacao());
        firePropertyChange("tanqueTratamentoBoolean", null, agenciamento.getAgenciementoSanitaria().isTanqueTratamento());
        firePropertyChange("subAguaLastroBoolean", null, agenciamento.getAgenciementoSanitaria().isSubAguaLastro());
    }
    
    
     
}
