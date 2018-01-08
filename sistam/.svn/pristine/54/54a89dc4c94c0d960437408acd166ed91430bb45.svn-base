package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.util.SistamUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.StringUtils;

/**
 *
 */
@Entity
@Table(name = "SUPRIMENTO_TRANSITO_EMPRESA", schema = "STAM")
public class ServicoSuprimentoTransitoEmpresa extends AbstractIdBean<Long> implements Serializable, Comparable<ServicoSuprimentoTransitoEmpresa>{

    //<editor-fold defaultstate="collapsed" desc="Constantes">
    public static final String SERV_SUPRIMENTO_TRANSITO_EMPRESA_ID = "id";
    public static final String SERV_SUPRIMENTO_TRANSITO_EMPRESA_SOLICITACAO_TRANSITO = "solicitacaoTransito";
    public static final String SERV_SUPRIMENTO_TRANSITO_EMPRESA_NOME_PRESTADOR_SERVICO = "nomePrestadorServico";
    public static final String SERV_SUPRIMENTO_TRANSITO_EMPRESA_CNPJ_FORNECEDOR = "cnpjPrestadorServico";
    public static final String SERV_SUPRIMENTO_TRANSITO_EMPRESA_VALOR_PESO_BRUTO = "valorPesoBruto";
    public static final String SERV_SUPRIMENTO_TRANSITO_EMPRESA_QUANTIDADE_VOLUME = "quantidadeVolume";
    public static final String SERV_SUPRIMENTO_TRANSITO_EMPRESA_DESCRICAO_NOTA_FISCAL = "descNotaFiscal";
    public static final String SERV_SUPRIMENTO_TRANSITO_EMPRESA_VALOR_MERCADORIAS = "valorMercadorias";
    
    private static final long serialVersionUID = 1L;    
   
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Propriedades"> 
    @Id
    @Column(name = "SUTE_SQ_EMPRESA", nullable=false)
    @GeneratedValue(generator = "SQ_SUTE_SQ_EMPRESA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_SUTE_SQ_EMPRESA", sequenceName = "STAM.SQ_SUTE_SQ_EMPRESA", allocationSize = 1)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUTR_SQ_SOLICITACAO", nullable = false)
    private ServicoSuprimentoTransito solicitacaoTransito;
    
    @Column(name = "SUTE_TX_EMPRESA")
    private String nomePrestadorServico;
    
    @Column(name = "SUTE_NR_CNPJ")
    private String cnpjPrestadorServico;
    
    @Column(name = "SUTE_TX_PESO_BRUTO")
    private String valorPesoBruto;
    
    @Column(name = "SUTE_QN_VOLUMES")
    private Integer quantidadeVolume;
    
    @Column(name = "SUTE_TX_NOTA_FISCAL_GTRM")
    private String descNotaFiscal;
    
    @Column(name = "SUTE_VL_MERCADORIAS")
    private BigDecimal valorMercadorias = BigDecimal.ZERO; 
    //</editor-fold>   
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
     @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id; 
    }

    public ServicoSuprimentoTransito getSolicitacaoTransito() {
        return solicitacaoTransito;
    }

    public void setSolicitacaoTransito(ServicoSuprimentoTransito solicitacaoTransito) {
        this.solicitacaoTransito = solicitacaoTransito;
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_EMPRESA_SOLICITACAO_TRANSITO, null, null);
    }

    public String getNomePrestadorServico() {
        return nomePrestadorServico;
    }

    public void setNomePrestadorServico(String nomePrestadorServico) {
        this.nomePrestadorServico = nomePrestadorServico;
        firePropertyChange("nomePrestadorServico", null, null);
    }
  
    public String getCnpjPrestadorServico() {
        return cnpjPrestadorServico;
    }

    public void setCnpjPrestadorServico(String cnpjPrestadorServico) {
        this.cnpjPrestadorServico = cnpjPrestadorServico;
        firePropertyChange("cnpjPrestadorServico", null, null);
    } 
    
   public String getCnpjPrestadorServicoComMascara() {
        return SistamUtils.formatMask("##.###.###/####-##", cnpjPrestadorServico);
    }

    public void setCnpjPrestadorServicoComMascara(String cnpj) {
        setCnpjPrestadorServico(cnpj == null ? null : cnpj.replaceAll("\\D", ""));
    }

    public String getValorPesoBruto() {
        return valorPesoBruto;
    }

    public void setValorPesoBruto(String valorPesoBruto) {
        this.valorPesoBruto = valorPesoBruto;
        firePropertyChange("valorPesoBruto", null, null);
    }

    public Integer getQuantidadeVolume() {
        return quantidadeVolume;
    }

    public void setQuantidadeVolume(Integer quantidadeVolume) {
        this.quantidadeVolume = quantidadeVolume;
        firePropertyChange("quantidadeVolume", null, null);
    }

    public String getDescNotaFiscal() {
        return descNotaFiscal;
    }

    public void setDescNotaFiscal(String descNotaFiscal) {
        this.descNotaFiscal = descNotaFiscal;
        firePropertyChange("descNotaFiscal", null, null);
    }

    public BigDecimal getValorMercadorias() {
        return valorMercadorias;
    }

    public void setValorMercadorias(BigDecimal valorMercadorias) {
        this.valorMercadorias = valorMercadorias != null ? valorMercadorias : BigDecimal.ZERO; 
        firePropertyChange("valorMercadorias", null, null);
    
    }
    
    //</editor-fold>
     
    
    @Override
    public String toString() {
        return nomePrestadorServico;
    }

    @Override
    public int compareTo(ServicoSuprimentoTransitoEmpresa o) {
        if(StringUtils.isBlank(this.getNomePrestadorServico())){
            return -1;
        }
        return this.getNomePrestadorServico().compareToIgnoreCase(o.getNomePrestadorServico());
    }
    
}
