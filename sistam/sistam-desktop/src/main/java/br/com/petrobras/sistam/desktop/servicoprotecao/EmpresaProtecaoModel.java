package br.com.petrobras.sistam.desktop.servicoprotecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmpresaProtecao;
import java.util.Collections;
import java.util.List;

/**
 * @author adzk
 */
public class EmpresaProtecaoModel extends BasePresentationModel<SistamService> {

    private FiltroEmpresaProtecao filtro;
    private List<EmpresaProtecao> empresas = Collections.EMPTY_LIST;
    private EmpresaProtecao empresaSelecionada;

    public EmpresaProtecaoModel() {
        filtro = new FiltroEmpresaProtecao();
    }

    public final void pesquisar() {
        this.empresas.clear();
        this.empresas = getService().buscarEmpresasProtecaoPorFiltro(filtro);
        firePropertyChange("empresas", null, null);
    }

    public final void limparPesquisa() {
        filtro.limpar();
    }

    public FiltroEmpresaProtecao getFiltro() {
        return filtro;
    }

    public List<EmpresaProtecao> getEmpresas() {
        return empresas;
    }

    public EmpresaProtecao getEmpresaSelecionada() {
        return empresaSelecionada;
    }
    
    public void setEmpresaSelecionada(EmpresaProtecao empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
        firePropertyChange("empresaSelecionada", null, null);
    }

    public EmpresaProtecao obterNovaEmpresa() {
        return new EmpresaProtecao();
    }

    public EmpresaProtecao obterEmpresaParaEdicao() {
        return getService().buscarEmpresaProtecaoPorId(empresaSelecionada.getId());
    }
}