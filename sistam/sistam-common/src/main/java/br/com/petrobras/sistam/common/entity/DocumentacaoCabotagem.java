package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "DOCUMENTACAO_CABOTAGEM", schema = "STAM")
public class DocumentacaoCabotagem extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_AGENCIAMENTO = "agenciamento";
    public static final String PROP_AGENCIA = "agencia";
    public static final String PROP_PORTO = "porto";
    public static final String PROP_MANIFESTO_ELETRONICO = "manifestoEletronico";
    public static final String PROP_TIPO_OPERACAO = "tipoOperacao";
    public static final String PROP_DOCUMENTACAO_PRODUTOS = "documentacaoProdutos";
    
    @Id
    @Column(name = "DOCA_SQ_DOC_CABOTAGEM", nullable=false)
    @GeneratedValue(generator = "SQ_DOCA_SQ_DOC_CABOTAGEM", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DOCA_SQ_DOC_CABOTAGEM", sequenceName = "STAM.SQ_DOCA_SQ_DOC_CABOTAGEM", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEC_SQ_AGENCIAMENTO", nullable=false)
    private Agenciamento agenciamento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEN_CD_ID", nullable=true)
    private AgenciaSigo agencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PORT_CD_ID", nullable=true)
    private Porto porto;
    
    @Column(name = "DOCA_TX_MANIFESTO_ELETRONICO", nullable=false)
    private String manifestoEletronico;
    
    @Column(name = "DOCA_TX_CONHECIMENTO_ELETRONIC", nullable=true)
    private String conhecimentoEletronico;
    
    @Column(name = "DOCA_TX_NR_CTAC", nullable=true)
    private String numeroCTAC;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.TipoOperacao")})
    @Column(name="DOCA_IN_TIPO_OPERACAO", nullable=false)
    private TipoOperacao tipoOperacao;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = DocumentacaoOperacao.PROP_DOCUMENTACAO_OPERACAO)
    private Set<DocumentacaoOperacao> documentacaoProdutos = new  HashSet<DocumentacaoOperacao>(); 
    
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
    }

    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
    }

    public String getConhecimentoEletronico() {
        return conhecimentoEletronico;
    }

    public void setConhecimentoEletronico(String conhecimentoEletronico) {
        this.conhecimentoEletronico = conhecimentoEletronico;
    }

    public String getNumeroCTAC() {
        return numeroCTAC;
    }

    public void setNumeroCTAC(String numeroCTAC) {
        this.numeroCTAC = numeroCTAC;
    }

    public AgenciaSigo getAgencia() {
        return agencia;
    }

    public void setAgencia(AgenciaSigo agencia) {
        this.agencia = agencia;
    }

    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
    }

    public String getManifestoEletronico() {
        return manifestoEletronico;
    }

    public void setManifestoEletronico(String manifestoEletronico) {
        this.manifestoEletronico = manifestoEletronico;
    }

    public TipoOperacao getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(TipoOperacao tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public List<DocumentacaoOperacao> getDocumentacaoProdutos() {
        return Collections.unmodifiableList(new ArrayList<DocumentacaoOperacao>(documentacaoProdutos));
    }
    
    
    public void adicionarDocumentacaoProduto(DocumentacaoOperacao documentacaoProduto){
        if (documentacaoProduto != null){
            documentacaoProduto.setDocumentacaoOperacao(this);
            documentacaoProdutos.add(documentacaoProduto);
        }
    }
    
    public void removerDocumentacaoProduto(DocumentacaoOperacao documentacaoProduto){
        if (documentacaoProduto != null){
            documentacaoProduto.setDocumentacaoOperacao(null);
            documentacaoProdutos.remove(documentacaoProduto);
        }
    }
    @Override
    public DocumentacaoCabotagem clone(){
        DocumentacaoCabotagem clone = (DocumentacaoCabotagem)super.clone();
        return clone;
    }
    
    
    
    public boolean isPermitidoEditarCE(){
        return StringUtils.isBlank(conhecimentoEletronico);
    }
    
    public boolean isPermitidoEditarCTAC(){
        return StringUtils.isBlank(numeroCTAC);
    }

    public boolean isTipoCargaCabotagem() {
        return TipoOperacao.CARGA_CABOTAGEM.equals(tipoOperacao);
    }

    public boolean isTipoDescargaCabotagem() {
        return TipoOperacao.DESCARGA_CABOTAGEM.equals(tipoOperacao);
    }

    public boolean isTipoCargaExportacao() {
        return TipoOperacao.CARGA_EXPORTACAO.equals(tipoOperacao);
    }

    public boolean isTipoDescargaImportacao() {
        return TipoOperacao.DESCARGA_IMPORTACAO.equals(tipoOperacao);
    }
        
}
