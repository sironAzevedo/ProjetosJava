package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.util.SistamUtils;
import java.io.Serializable;
import java.util.Date;

/**
 * @author adzk
 */
public class FiltroPessoaProtecao extends AbstractBean implements Serializable {

    private String nome;
    private String documento;
    private String cpf;
    private Date dataNascimento;
    private EmpresaProtecao empresa;

    public void limpar() {
        setNome(null);
        setDocumento(null);
        setCpfComMascara(null);
        setDataNascimento(null);
        setEmpresa(null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        firePropertyChange("nome", null, null);
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
        firePropertyChange("documento", null, null);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
        firePropertyChange("cpf", null, null);
    }

    public String getCpfComMascara() {
        return SistamUtils.formatMask("###.###.###-##", cpf);
    }

    public void setCpfComMascara(String cpf) {
        setCpf(cpf == null ? null : cpf.replaceAll("\\D", ""));
        firePropertyChange("cpfComMascara", null, null);
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
        firePropertyChange("dataNascimento", null, null);
    }

    public EmpresaProtecao getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaProtecao empresa) {
        this.empresa = empresa;
        firePropertyChange("empresa", null, null);
    }
}
