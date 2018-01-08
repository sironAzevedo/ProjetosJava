package br.com.petrobras.sistam.desktop.components.lookups.servicoprotecao;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import java.util.Collections;
import java.util.List;

/**
 * @author adzk
 */
public class PessoaProtecaoInputPresentationModel extends BasePresentationModel<SistamService> {

    private List<Pessoa> pessoas = Collections.EMPTY_LIST;
    private Pessoa pessoaSelecionada;
    private boolean confirmado = false;
    private PessoaProtecaoInput comboPai;
    private String nome;
    private EmpresaProtecao empresa;
    private List<EmpresaProtecao> empresas;
    
    public PessoaProtecaoInputPresentationModel(TipoAtendimentoServico tipoAtendimentoServico){
        TipoServicoProtecao tipoServicoProtecao = TipoServicoProtecao.from(tipoAtendimentoServico);
        empresas = getService().buscarEmpresasProtecaoPorTipoServico(tipoServicoProtecao);
        empresas.add(0, null);
    }

    public void consultar() {
        AssertUtils.assertExpression((nome != null && nome.length() >= 3) || empresa != null, ConstantesI18N.PESSOA_PROTECAO_LOOKUP_NOME_OU_EMPRESA_OBRIGATORIO);
        setPessoas(comboPai.buscarPessoas(empresa, nome));
        AssertUtils.assertNotNullNotEmpty(pessoas, ConstantesI18N.PESSOA_PROTECAO_LOOKUP_NENHUM_REGISTRO_ENCONTRADO);
    }

    public List<EmpresaProtecao> getEmpresas() {
        return empresas;
    }

    public EmpresaProtecao getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaProtecao empresa) {
        this.empresa = empresa;
        firePropertyChange("empresa", null, null);
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
        firePropertyChange("pessoas", null, this.nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        firePropertyChange("nome", null, this.nome);
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
        firePropertyChange("pessoaSelecionada", null, this.nome);
    }

    public PessoaProtecaoInput getComboPai() {
        return comboPai;
    }

    public void setComboPai(PessoaProtecaoInput comboPai) {
        this.comboPai = comboPai;
        firePropertyChange("comboPai", null, this.nome);
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
        firePropertyChange("confirmado", null, this.nome);
    }
}
