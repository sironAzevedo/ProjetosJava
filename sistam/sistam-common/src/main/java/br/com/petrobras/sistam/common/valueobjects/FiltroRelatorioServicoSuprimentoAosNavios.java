package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoMaterial;
import br.com.petrobras.sistam.common.enums.TipoMercadorias;
import org.apache.commons.lang.StringUtils;

public class FiltroRelatorioServicoSuprimentoAosNavios extends AbstractBean {

    private Agencia agencia;
    private Porto porto;
    private Embarcacao navio;
    private String numeroViagem;
    private TipoMercadorias tipoAcesso;
    private TipoMaterial tipoMaterial;
    private EmpresaProtecao fornecedor;
    private String notaFiscal;
    private Periodo periodoOperacao;
    private EmpresaMaritima empresaMaritima;
    private Servico servico;
    private boolean companhiaDocas;
    private boolean terminal;

    public FiltroRelatorioServicoSuprimentoAosNavios() {
        this.periodoOperacao = new Periodo();
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
        firePropertyChange("agencia", null, null);
    }

    public Embarcacao getNavio() {
        return navio;
    }

    public void setNavio(Embarcacao navio) {
        this.navio = navio;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
        firePropertyChange("servico", null, null);
    }

    public String getNumeroViagem() {
        return numeroViagem;
    }

    public void setNumeroViagem(String numeroViagem) {
        this.numeroViagem = numeroViagem;
    }

    public TipoMercadorias getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoMercadorias tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public EmpresaProtecao getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(EmpresaProtecao fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public Periodo getPeriodoOperacao() {
        return periodoOperacao;
    }

    public EmpresaMaritima getEmpresaMaritima() {
        return empresaMaritima;
    }

    public void setEmpresaMaritima(EmpresaMaritima empresaMaritima) {
        this.empresaMaritima = empresaMaritima;
        firePropertyChange("empresaMaritima", null, null);
    }

    public void setCompanhiaDocas(boolean companhiaDocas) {
        this.companhiaDocas = companhiaDocas;
    }
    
    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public boolean isAgenciaPreenchido() {
        return agencia != null;
    }

    public boolean isEmpresaMaritimaPreenchido() {
        return empresaMaritima != null;
    }

    public boolean isNavioPreenchido() {
        return navio != null;
    }
    
    public boolean isPortoPreenchido() {
        return porto != null;
    }

    public boolean isServicoPreenchido() {
        return servico != null;
    }

    public boolean isTipoAcessoPreenchido() {
        return tipoAcesso != null;
    }

    public boolean isNumeroViagemPreenchido() {
        return StringUtils.isNotBlank(numeroViagem);
    }

    public boolean isNotaFiscalPreenchido() {
        return StringUtils.isNotBlank(notaFiscal);
    }

    public boolean isTipoMaterialPreenchido() {
        return tipoMaterial != null;
    }

    public boolean isFornecedorPreenchido() {
        return fornecedor != null;
    }

    public boolean isCompanhiaDocas() {
        return companhiaDocas;
    }

    public String getLocalEmbarqueDescricao() {
        if (companhiaDocas && terminal) {
            return "Companhia Docas ou Terminal";
        } else if (companhiaDocas) {
            return "Companhia Docas";
        } else if (terminal) {
            return "Terminal";
        }
        return "-";
    }
}
