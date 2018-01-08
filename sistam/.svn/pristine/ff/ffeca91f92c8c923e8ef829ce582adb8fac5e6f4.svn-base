package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author adzk
 */
public class FiltroFormulatorioReceitaPrestacaoServico extends AbstractBean {

    private ServicoAcessoPessoa servicoAcessoPessoa;
    private String numeroDocumento;
    private String descricaoOperacao;
    private String numeroTermoResponsabilidade;
    private String lancha;
    private Date periodoInicio;
    private Date periodoFim;
    private List<PessoaAcessoVO> pessoas = new ArrayList<PessoaAcessoVO>();
    private List<VeiculoVO> veiculos = new ArrayList<VeiculoVO>();
    private VeiculoVO veiculo;
    private VeiculoVO veiculoSelecionado;

    public FiltroFormulatorioReceitaPrestacaoServico() {
        veiculo = new VeiculoVO();
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDescricaoOperacao() {
        return descricaoOperacao;
    }

    public void setDescricaoOperacao(String descricaoOperacao) {
        this.descricaoOperacao = descricaoOperacao;
    }

    public void setVeiculoSelecionado(VeiculoVO veiculoSelecionado) {
        this.veiculoSelecionado = veiculoSelecionado;
        firePropertyChange("veiculoSelecionado", null, null);
    }

    public VeiculoVO getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoVO veiculo) {
        this.veiculo = veiculo;
        firePropertyChange("veiculo", null, null);
    }
    
    public VeiculoVO getVeiculoSelecionado() {
        return veiculoSelecionado;
    }

    public String getNumeroTermoResponsabilidade() {
        return numeroTermoResponsabilidade;
    }

    public void setNumeroTermoResponsabilidade(String numeroTermoResponsabilidade) {
        this.numeroTermoResponsabilidade = numeroTermoResponsabilidade;
    }

    public Date getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(Date periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public Date getPeriodoFim() {
        return periodoFim;
    }

    public void setPeriodoFim(Date periodoFim) {
        this.periodoFim = periodoFim;
    }

    public List<VeiculoVO> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<VeiculoVO> veiculos) {
        this.veiculos = veiculos;
        firePropertyChange("veiculos", null, null);
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
