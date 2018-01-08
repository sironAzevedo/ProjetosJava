package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrupoStatusAgenciamento implements Serializable{
    private StatusEmbarcacao status;
    private List<Agenciamento> agenciamento;

    public GrupoStatusAgenciamento(StatusEmbarcacao status) {
        this.status = status;
        this.agenciamento = new ArrayList<Agenciamento>();
    }

    public List<Agenciamento> getAgenciamento() {
        return Collections.unmodifiableList(agenciamento);
    }

    
    public StatusEmbarcacao getStatus() {
        return status;
    }
    
    public void adicionarAgenciamentos(List<Agenciamento> listaAgenciamento){
        this.agenciamento.addAll(listaAgenciamento);
        Collections.sort(agenciamento, new AgenciamentoComparator());
//        Collections.sort(agenciamento);
    }
    
    
    @Override
    public String toString() {
        return getStatus().getPorExtenso();
    }
    
}
