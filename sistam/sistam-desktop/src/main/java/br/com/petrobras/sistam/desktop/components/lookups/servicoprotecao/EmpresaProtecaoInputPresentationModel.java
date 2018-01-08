package br.com.petrobras.sistam.desktop.components.lookups.servicoprotecao;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamUtils;
import java.util.Collections;
import java.util.List;

/**
 * @author adzk
 */
public class EmpresaProtecaoInputPresentationModel extends AbstractBean {

    private List<EmpresaProtecao> empresas = Collections.EMPTY_LIST;
    private EmpresaProtecao empresaSelecionada;
    private boolean confirmado = false;
    private EmpresaProtecaoInput comboPai;
    private TipoServicoProtecao tipoServicoProtecao;
    private String nome;
    private String cnpj;

    public void consultar() {
        AssertUtils.assertExpression((nome != null && nome.length() >= 3) || (cnpj != null && cnpj.length() == 14), ConstantesI18N.EMPRESA_PROTECAO_LOOKUP_NOME_OU_CNPJ_OBRIGATORIO);
        List<EmpresaProtecao> lista = comboPai.buscarEmpresas(tipoServicoProtecao, nome, cnpj);
        setEmpresas(lista);
        AssertUtils.assertNotNullNotEmpty(lista, ConstantesI18N.EMPRESA_PROTECAO_LOOKUP_NENHUM_REGISTRO_ENCONTRADO);
    }

    public List<EmpresaProtecao> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<EmpresaProtecao> empresas) {
        this.empresas = empresas;
        firePropertyChange("empresas", null, this.nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        firePropertyChange("nome", null, this.nome);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
        firePropertyChange("cnpj", null, this.nome);
    }

    public String getCnpjComMascara() {
        return SistamUtils.formatMask("##.###.###/####-##", cnpj);
    }

    public void setCnpjComMascara(String cnpj) {
        setCnpj(cnpj == null ? null : cnpj.replaceAll("\\D", ""));
        firePropertyChange("cnpjComMascara", null, this.nome);
    }

    public EmpresaProtecao getEmpresaSelecionada() {
        return empresaSelecionada;
    }

    public void setEmpresaSelecionada(EmpresaProtecao empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
        firePropertyChange("empresaSelecionada", null, this.nome);
    }

    public EmpresaProtecaoInput getComboPai() {
        return comboPai;
    }

    public void setComboPai(EmpresaProtecaoInput comboPai) {
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

    public void setTipoServicoProtecao(TipoServicoProtecao tipoServicoProtecao) {
        this.tipoServicoProtecao = tipoServicoProtecao;
    }

    public TipoServicoProtecao getTipoServicoProtecao() {
        return tipoServicoProtecao;
    }
    
}
