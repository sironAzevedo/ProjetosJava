package br.com.petrobras.sistam.desktop.agencia;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetalhesRepresentanteModel extends BasePresentationModel<SistamService> {
    private RepresentanteLegal representanteLegal;
    private List<TipoSimNao> tiposSimNao;
    private TipoSimNao ativoSimNaoSelecionado;
    
    public DetalhesRepresentanteModel(RepresentanteLegal representanteLegal){
        this.representanteLegal = representanteLegal;
        tiposSimNao = new ArrayList<TipoSimNao>(Arrays.asList(TipoSimNao.values()));
    }
    
    public void salvar() {
        representanteLegal = getService().salvarRepresentanteLegal(representanteLegal);
    }
    
    public RepresentanteLegal getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(RepresentanteLegal representanteLegal) {
        this.representanteLegal = representanteLegal;
        firePropertyChange("representanteLegal", null, this.representanteLegal);
    }
    
    public List<TipoSimNao> getTiposSimNao() {
        return tiposSimNao;
    }

    public TipoSimNao getAtivoSimNaoSelecionado() {
        return TipoSimNao.from(representanteLegal.getAtivo());
    }

    public void setAtivoSimNaoSelecionado(TipoSimNao ativoSimNaoSelecionado) {
        this.ativoSimNaoSelecionado = ativoSimNaoSelecionado;
        firePropertyChange("ativoSimNaoSelecionado", null, null);
        
        representanteLegal.setAtivo(ativoSimNaoSelecionado.getId());
    }
    
    
}
