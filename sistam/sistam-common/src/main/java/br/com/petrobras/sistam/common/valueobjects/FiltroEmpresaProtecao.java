package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.util.SistamUtils;
import java.io.Serializable;

/**
 * @author adzk
 */
public class FiltroEmpresaProtecao extends AbstractBean implements Serializable {

    private String razaoSocial;
    private String cnpj;

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
        firePropertyChange("razaoSocial", null, null);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
        firePropertyChange("cnpj", null, null);
    }

    public String getCnpjComMascara() {
        return SistamUtils.formatMask("##.###.###/####-##", cnpj);
    }

    public void setCnpjComMascara(String cnpj) {
        setCnpj(cnpj == null ? null : cnpj.replaceAll("\\D", ""));
        firePropertyChange("cnpjComMascara", null, null);
    }

    public void limpar() {
        setCnpjComMascara(null);
        setRazaoSocial(null);
    }
}
