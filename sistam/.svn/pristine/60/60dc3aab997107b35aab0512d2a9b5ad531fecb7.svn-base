package br.com.petrobras.sistam.desktop.servicoprotecao;

import br.com.petrobras.fcorp.common.support.CollectionUtils;
import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author adzk
 */
public class DetalhesPessoaProtecaoModel extends BasePresentationModel<SistamService> {

    private Pessoa pessoa;
    private List<EmpresaProtecao> empresas;
    private boolean salvo = false;
    private List<TipoSimNao> tiposSimNao;
    private TipoSimNao ativoSimNaoSelecionado;

    private DetalhesPessoaProtecaoModel() {
        this.tiposSimNao = new ArrayList<TipoSimNao>(Arrays.asList(TipoSimNao.values()));
        this.empresas = getService().buscarEmpresasProtecaoAtiva();
        this.empresas.add(0, null);
    }

    public DetalhesPessoaProtecaoModel(Pessoa pessoa) {
        this();
        this.pessoa = pessoa;
        if(!pessoa.isNova() && pessoa.getEmpresa() != null && !pessoa.getEmpresa().getAtiva().booleanValue()){
            empresas.add(pessoa.getEmpresa());
            CollectionUtils.sort(empresas, "razaoSocial");
        }
    }

    public List<TipoSimNao> getTiposSimNao() {
        return tiposSimNao;
    }

    public TipoSimNao getAtivoSimNaoSelecionado() {
        return TipoSimNao.from(pessoa.getAtiva());
    }  

    public void setAtivoSimNaoSelecionado(TipoSimNao ativoSimNaoSelecionado) {
        this.ativoSimNaoSelecionado = ativoSimNaoSelecionado;
        firePropertyChange("ativoSimNaoSelecionado", null, null);
        pessoa.setAtiva(this.ativoSimNaoSelecionado.getId());
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public List<EmpresaProtecao> getEmpresas() {
        return empresas;
    }

    public boolean isSalvo() {
        return salvo;
    }

    public void salvar() {
        if (getService().salvarPessoaProtecao(pessoa) != null) {
            salvo = true;
        }
    }
}