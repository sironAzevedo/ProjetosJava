package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.Agencia;

/**
 * Filtro de busca para objetos da classe Empresa.
 */
public class FiltroEmpresa extends AbstractBean{
    private Agencia agencia;
    private String cnpj;
    private String empresa;
    private Boolean ativo;

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
        firePropertyChange("agencia", null, null);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
        firePropertyChange("cnpj", null, null);
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
        firePropertyChange("empresa", null, null);
    }
    
    public String getCnpjSemMascara(){
        if (cnpj == null){
            return null;
        }
        return cnpj.replaceAll("\\D", "");
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    
    public void limpar(){
        setAgencia(null);
        setCnpj(null);
        setEmpresa(null);
        setAtivo(null);
    }

}
