package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author adzk
 */
public class RelatorioReceitaPrestacaoServicoVO extends AbstractBean {

    public static final int MAXIMO_FUNCIONARIOS_POR_PAGINA = 5;
    private String numeroDocumento;
    private String nomePrestador;
    private String cnpjPrestador;
    private String telefonePrestador;
    private String descricao;
    private String navio;
    private String escala;
    private String numeroTermo;
    private String lancha;
    private Date periodoInicio;
    private Date periodoFim;
    private String observacoes;
    private List<VeiculoVO> veiculos = new ArrayList<VeiculoVO>();
    private List<FuncionarioVO> funcionarios = new ArrayList<FuncionarioVO>();

    public RelatorioReceitaPrestacaoServicoVO() {
    }

    public String getLocalEDataGeracao() {
        return "Salvador/BA, " + SistamDateUtils.formatDate(new Date(), SistamDateUtils.DATE_PATTERN, null);
    }

    public String getNavio() {
        return navio;
    }

    public void setNavio(String navio) {
        this.navio = navio;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getPeriodo() {
        return periodoInicio == null || periodoFim == null ? "" : (SistamDateUtils.formatDate(periodoInicio, SistamDateUtils.DATE_PATTERN, null)
                + " a " + SistamDateUtils.formatDate(periodoFim, SistamDateUtils.DATE_PATTERN, null));
    }

    public void setPeriodoInicio(Date periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public void setPeriodoFim(Date periodoFim) {
        this.periodoFim = periodoFim;
    }

    public String getLancha() {
        return lancha;
    }

    public void setLancha(String lancha) {
        this.lancha = lancha;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNomePrestador() {
        return nomePrestador;
    }

    public void setNomePrestador(String nomePrestador) {
        this.nomePrestador = nomePrestador;
    }

    public String getCnpjPrestador() {
        return cnpjPrestador;
    }

    public void setCnpjPrestador(String cnpjPrestador) {
        this.cnpjPrestador = cnpjPrestador;
    }

    public String getTelefonePrestador() {
        return telefonePrestador;
    }

    public void setTelefonePrestador(String telefonePrestador) {
        this.telefonePrestador = telefonePrestador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroTermo() {
        return numeroTermo;
    }

    public void setNumeroTermo(String numeroTermo) {
        this.numeroTermo = numeroTermo;
    }

    public List<VeiculoVO> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<VeiculoVO> veiculos) {
        this.veiculos = veiculos;
    }

    public List<FuncionarioVO> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<FuncionarioVO> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public RelatorioReceitaPrestacaoServicoVO clone() {
        RelatorioReceitaPrestacaoServicoVO clone = (RelatorioReceitaPrestacaoServicoVO) super.clone();
        clone.setVeiculos(veiculos);
        clone.setFuncionarios(new ArrayList<FuncionarioVO>());
        return clone;
    }
}