package br.com.petrobras.sistam.desktop.servicoprotecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.fcorp.swing.components.SCheckList;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.EmpresaProtecaoServico;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ListModel;

/**
 * @author adzk
 */
public class DetalhesEmpresaProtecaoModel extends BasePresentationModel<SistamService> {

    private EmpresaProtecao empresa;
    private boolean salvo = false;
    private List<TipoSimNao> tiposSimNao;
    private TipoSimNao ativoSimNaoSelecionado;
    private List<TipoServicoProtecao> tiposServicoProtecao;
    private List<TipoServicoProtecao> tiposServicoProtecaoSelecionado = new ArrayList<TipoServicoProtecao>();

    private DetalhesEmpresaProtecaoModel() {
        this.tiposSimNao = new ArrayList<TipoSimNao>(Arrays.asList(TipoSimNao.values()));
        this.tiposServicoProtecao = TipoServicoProtecao.valuesToListOrdered();
    }

    public DetalhesEmpresaProtecaoModel(EmpresaProtecao empresa) {
        this();
        this.empresa = empresa;
    }

    public EmpresaProtecao getEmpresa() {
        return empresa;
    }

    public boolean isSalvo() {
        return salvo;
    }

    public List<TipoSimNao> getTiposSimNao() {
        return tiposSimNao;
    }

    public List<TipoServicoProtecao> getTiposServicoProtecao() {
        return tiposServicoProtecao;
    }

    public List<TipoServicoProtecao> getTiposServicoProtecaoSelecionado() {
        return tiposServicoProtecaoSelecionado;
    }

    public void setTiposServicoProtecaoSelecionado(List<TipoServicoProtecao> tiposServicoProtecaoSelecionado) {
        this.tiposServicoProtecaoSelecionado = tiposServicoProtecaoSelecionado;
        firePropertyChange("tiposServicoProtecaoSelecionado", null, null);
    }

    public TipoSimNao getAtivoSimNaoSelecionado() {
        return TipoSimNao.from(empresa.getAtiva());
    }

    public void setAtivoSimNaoSelecionado(TipoSimNao ativoSimNaoSelecionado) {
        this.ativoSimNaoSelecionado = ativoSimNaoSelecionado;
        firePropertyChange("ativoSimNaoSelecionado", null, null);
        empresa.setAtiva(this.ativoSimNaoSelecionado.getId());
    }

    public void salvar() {
        for (TipoServicoProtecao tipo : tiposServicoProtecaoSelecionado) {
            this.empresa.adicionarTipoServico(tipo);
        }
        if (getService().salvarEmpresaProtecao(empresa) != null) {
            salvo = true;
        }
    }

    public void checkTiposServicoProtecaoSelecionado(SCheckList checkListTipos) {
        List<EmpresaProtecaoServico> servicos = empresa.getServicosAsList();
        for (EmpresaProtecaoServico servico : servicos) {
            tiposServicoProtecaoSelecionado.add(servico.getTipo());
        }

        int[] indices = new int[tiposServicoProtecaoSelecionado.size()];
        int indice = 0;

        ListModel checkListTiposModel = checkListTipos.getModel();
        for (int i = 0; i < checkListTiposModel.getSize(); i++) {
            TipoServicoProtecao tipo = (TipoServicoProtecao) checkListTiposModel.getElementAt(i);
            for (TipoServicoProtecao tipoSelecionado : tiposServicoProtecaoSelecionado) {
                if (tipoSelecionado.equals(tipo)) {
                    indices[indice++] = i;
                    break;
                }
            }
        }

        if (indices.length > 0) {
            checkListTipos.setSelectedIndices(indices);
        }
    }
}