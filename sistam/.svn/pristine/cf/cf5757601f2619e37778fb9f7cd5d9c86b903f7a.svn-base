package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.TipoFrota;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrupoFrota implements Serializable{ 
    private TipoFrota frota;    
    private List<Agenciamento> agenciamento;

    public GrupoFrota(TipoFrota frota) {
        this.frota = frota;
        this.agenciamento = new ArrayList<Agenciamento>();
    }

    public List<Agenciamento> getAgenciamento() {
        return Collections.unmodifiableList(agenciamento);
    } 

    public TipoFrota getFrota() {
        return frota;
    } 
    
    public void adicionarAgenciamentos(List<Agenciamento> listaAgenciamento){
        this.agenciamento.addAll(listaAgenciamento);
        Collections.sort(agenciamento);
    }
    
    
    @Override
    public String toString() {
        return getFrota().getPorExtenso();
    }
    
}
