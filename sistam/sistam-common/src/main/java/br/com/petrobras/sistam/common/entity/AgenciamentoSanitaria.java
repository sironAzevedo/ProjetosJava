package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "AGENCIAMENTO_SANITARIA", schema = "STAM")
@org.hibernate.annotations.Entity( selectBeforeUpdate = true)
public class AgenciamentoSanitaria extends AbstractIdBean<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String PROP_ID = "id";
    public static final String PROP_AGUA_LASTRO = "aguaLastro";
    public static final String PROP_SUB_AGUA_LASTRO = "subAguaLastro";
    public static final String PROP_LONGITUDE_SUBST_AGUA = "longitudeSubstAgua";
    public static final String PROP_LATITUDE_SUBST_AGUA = "latitudeSubstAgua";
    public static final String PROP_DESLASTRO = "deslastro";
    public static final String PROP_TANQUE_TRATAMENTO = "tanqueTratamento";
    public static final String PROP_CAPACIDADE_AFLUENTE = "capacidadeEfluente";
    public static final String PROP_PORTO = "porto";
    public static final String PROP_PRODUZ_AGUA_POTAVEL = "produzAguaPotavel";
    public static final String PROP_TRATAMENTO_AGUA_POTAVEL = "tratamentoAguaPotavel";
    public static final String PROP_AUTONOMIA_RETENCAO = "autonomiaRetencao";
    public static final String PROP_CARGA_PERIGOSA = "cargaPerigosa";
    public static final String PROP_DESINSETIZACAO = "desinsetizacao";
    public static final String PROP_RESIDUOS_SOLIDOS = "residuosSolidos";
    public static final String PROP_PRODUTO_DESINSETIZACAO = "produtoDesinsetizacao";
    public static final String PROP_DATA_DESINSETIZACAO = "dataDesinsetizacao";
    public static final String PROP_ABASTECIMENTO_AGUA = "abastecimentoAgua";
    public static final String PROP_ABASTECIMENTO_ALIMENTO = "abastecimentoAlimento";
    
    @Id
    @Column(name = "AGEC_SQ_AGENCIAMENTO", nullable=false)
    private Long id;
    
    @Version
    @Column(name = "AGSA_NR_VERSAO_REGISTRO", nullable=false)
    private Long versao;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGSA_IN_AGUA_LASTRO", nullable=false)
    private Boolean aguaLastro = false;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGSA_IN_SUB_AGUA_LASTRO", nullable=false)
    private Boolean subAguaLastro = false;

    @Column(name = "AGSA_TX_LONGITUDE_SUBST_AGUA", nullable=true)
    private String longitudeSubstAgua;

    @Column(name = "AGSA_TX_LATITUDE_SUBST_AGUA", nullable=true)
    private String latitudeSubstAgua;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGSA_IN_DESLASTRO", nullable=false)
    private Boolean deslastro = false;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGSA_IN_TANQUE_TRATAMENTO", nullable=false)
    private Boolean tanqueTratamento = false;

    @Column(name = "AGSA_MD_CAP_ARMAZ_EFLUENTE", nullable=true)
    private Double capacidadeEfluente;
    
    @Column(name = "AGSA_NM_PORTO", nullable=true)
    private String porto;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGSA_IN_AGUA_POTAVEL", nullable=false)
    private Boolean produzAguaPotavel = false;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGSA_IN_TRAT_AGUA_POTAVEL", nullable=false)
    private Boolean tratamentoAguaPotavel = false;
    
    @Column(name = "AGSA_QN_AUTONOMIA_RETENCAO", nullable=true)
    private Double autonomiaRetencao;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGSA_IN_CARGA_PERIGOSA", nullable=false)
    private Boolean cargaPerigosa = false;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGSA_IN_DESINSETIZACAO", nullable=false)
    private Boolean desinsetizacao = false;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGSA_IN_RESIDUOS_SOLIDOS", nullable=false)
    private Boolean residuosSolidos = false;
    
    @Column(name = "AGSA_NM_PRODUTO_DESINSETIZACAO", nullable=true)
    private String produtoDesinsetizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGSA_DT_DESINSETIZACAO", nullable=true)
    private Date dataDesinsetizacao;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGSA_IN_ABASTECIMENTO_AGUA", nullable=false)
    private Boolean abastecimentoAgua = false;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGSA_IN_ABAST_ALIMENTO", nullable=false)
    private Boolean abastecimentoAlimento = false;
    
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public boolean isAguaLastro() {
        return aguaLastro;
    }

    public void setAguaLastro(Boolean aguaLastro) {
        this.aguaLastro = aguaLastro;
    }

    public boolean isSubAguaLastro() {
        return subAguaLastro;
    }

    public void setSubAguaLastro(Boolean subAguaLastro) {
        this.subAguaLastro = subAguaLastro;
    }

    public String getLongitudeSubstAgua() {
        return longitudeSubstAgua;
    }

    public void setLongitudeSubstAgua(String longitudeSubstAgua) {
        this.longitudeSubstAgua = longitudeSubstAgua;
    }

    public String getLatitudeSubstAgua() {
        return latitudeSubstAgua;
    }

    public void setLatitudeSubstAgua(String latitudeSubstAgua) {
        this.latitudeSubstAgua = latitudeSubstAgua;
    }

    public boolean isDeslastro() {
        return deslastro;
    }

    public void setDeslastro(Boolean deslastro) {
        this.deslastro = deslastro;
    }

    public boolean isTanqueTratamento() {
        return tanqueTratamento;
    }

    public void setTanqueTratamento(Boolean tanqueTratamento) {
        this.tanqueTratamento = tanqueTratamento;
    }

    public Double getCapacidadeEfluente() {
        return capacidadeEfluente;
    }

    public void setCapacidadeEfluente(Double capacidadeEfluente) {
        this.capacidadeEfluente = capacidadeEfluente;
    }

    public String getPorto() {
        return porto;
    }

    public void setPorto(String porto) {
        this.porto = porto;
    }

    public boolean isProduzAguaPotavel() {
        return produzAguaPotavel;
    }

    public void setProduzAguaPotavel(Boolean produzAguaPotavel) {
        this.produzAguaPotavel = produzAguaPotavel;
    }

    public boolean isTratamentoAguaPotavel() {
        return tratamentoAguaPotavel;
    }

    public void setTratamentoAguaPotavel(Boolean tratamentoAguaPotavel) {
        this.tratamentoAguaPotavel = tratamentoAguaPotavel;
    }

    public Double getAutonomiaRetencao() {
        return autonomiaRetencao;
    }

    public void setAutonomiaRetencao(Double autonomiaRetencao) {
        this.autonomiaRetencao = autonomiaRetencao;
    }

    public boolean isCargaPerigosa() {
        return cargaPerigosa;
    }

    public void setCargaPerigosa(Boolean cargaPerigosa) {
        this.cargaPerigosa = cargaPerigosa;
    }

    public boolean isDesinsetizacao() {
        return desinsetizacao;
    }

    public void setDesinsetizacao(Boolean desinsetizacao) {
        this.desinsetizacao = desinsetizacao;
    }

    public boolean isResiduosSolidos() {
        return residuosSolidos;
    }

    public void setResiduosSolidos(Boolean residuosSolidos) {
        this.residuosSolidos = residuosSolidos;
    }

    public String getProdutoDesinsetizacao() {
        return produtoDesinsetizacao;
    }

    public void setProdutoDesinsetizacao(String produtoDesinsetizacao) {
        this.produtoDesinsetizacao = produtoDesinsetizacao;
    }

    public Date getDataDesinsetizacao() {
        return dataDesinsetizacao;
    }

    public void setDataDesinsetizacao(Date dataDesinsetizacao) {
        this.dataDesinsetizacao = dataDesinsetizacao;
    }

    public boolean isAbastecimentoAgua() {
        return abastecimentoAgua;
    }

    public void setAbastecimentoAgua(Boolean abastecimentoAgua) {
        this.abastecimentoAgua = abastecimentoAgua;
    }

    public boolean isAbastecimentoAlimento() {
        return abastecimentoAlimento;
    }

    public void setAbastecimentoAlimento(boolean abastecimentoAlimento) {
        this.abastecimentoAlimento = abastecimentoAlimento;
    }
    
     @Override
    public AgenciamentoSanitaria clone(){
        return (AgenciamentoSanitaria)super.clone();
    }
    
}
