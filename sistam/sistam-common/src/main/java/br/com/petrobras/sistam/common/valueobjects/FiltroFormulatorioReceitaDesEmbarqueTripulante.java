package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author adzk
 */
public class FiltroFormulatorioReceitaDesEmbarqueTripulante extends AbstractBean {

    private ServicoAcessoPessoa servicoAcessoPessoa;
    private String numeroDocumento;
    private Date dataPrevista;
    private String lancha;
    private List<PessoaAcessoVO> pessoas = new ArrayList<PessoaAcessoVO>();

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public String getLancha() {
        return lancha;
    }

    public void setLancha(String lancha) {
        this.lancha = lancha;
    }

    public ServicoAcessoPessoa getServicoAcessoPessoa() {
        return servicoAcessoPessoa;
    }

    public void setServicoAcessoPessoa(ServicoAcessoPessoa servicoAcessoPessoa) {
        this.servicoAcessoPessoa = servicoAcessoPessoa;
    }

    public List<PessoaAcessoVO> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<PessoaAcessoVO> pessoas) {
        this.pessoas = pessoas;
        firePropertyChange("pessoas", null, null);
    }
}
