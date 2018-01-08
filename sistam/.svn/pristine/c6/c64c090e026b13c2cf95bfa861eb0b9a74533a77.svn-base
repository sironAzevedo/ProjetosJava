package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import org.hibernate.annotations.Type;

@Entity
@Table(name = "DOCUMENTACAO_LONGO_CURSO", schema = "STAM")
public class DocumentacaoLongoCurso extends AbstractIdBean<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int TAM_MAX_LISTA_CE = 512;
    
    public static final String PROP_ID = "id";
    public static final String PROP_OPERACAO = "operacao";
    public static final String PROP_PORTO = "porto";
    public static final String PROP_MANIFESTO_ELETRONICO = "manifestoEletronico";
    public static final String PROP_LISTA_CONHECIMENTO_ELETRONICO = "listaConhecimentoEletronico";
    public static final String PROP_CIDADE = "cidade";
    public static final String PROP_BL_RECEBIDO = "blRecebido";
    
    @Id
    @Column(name = "DOLC_SQ_DOC_LONGO_CURSO", nullable=false)
    @GeneratedValue(generator = "SQ_DOLC_SQ_DOC_LONGO_CURSO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_DOLC_SQ_DOC_LONGO_CURSO", sequenceName = "STAM.SQ_DOLC_SQ_DOC_LONGO_CURSO", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OPER_SQ_OPERACAO", nullable=false)
    private Operacao operacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PORT_CD_ID", nullable=false)
    private Porto porto;

    @Column(name = "DOLC_TX_MANIFESTO_ELETRONICO", nullable=false)
    private String manifestoEletronico;

    @Column(name = "DOLC_TX_CONHECIMENTO_ELETRONIC", nullable=false)
    private String listaConhecimentoEletronico;

    @Column(name = "DOLC_NM_CIDADE", nullable=false)
    private String cidade;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "DOLC_IN_BL_RECEBIDO", nullable=true) 
    private Boolean blRecebido = Boolean.FALSE;

    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }
    
    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        Object old = this.porto;
        this.porto = porto;
        firePropertyChange("porto", old, this.porto);
    }

    public String getManifestoEletronico() {
        return manifestoEletronico;
    }

    public void setManifestoEletronico(String manifestoEletronico) {
        this.manifestoEletronico = manifestoEletronico;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public boolean isBlRecebido() {
        return blRecebido != null ? blRecebido : false;
    }

    public void setBlRecebido(boolean blRecebido) {
        this.blRecebido = blRecebido;
    }

    public String getListaConhecimentoEletronico() {
        return listaConhecimentoEletronico;
    }

    public List<String> getListaConhecimentoEletronicoFormatada(){
        if (listaConhecimentoEletronico == null){
            return new ArrayList<String>();
        }
        return Arrays.asList(listaConhecimentoEletronico.split(";"));
    }
    /**
     * Adiciona um CE (Conhecimento Eletrônico)
     * @param ce 
     */
    public void adicionarCE(String ce){
        if (ce != null && !ce.isEmpty()){
            
            if (listaConhecimentoEletronico == null || listaConhecimentoEletronico.isEmpty()){
                listaConhecimentoEletronico = ce;
            }
            else {
                int tamanhoDisopnivel = TAM_MAX_LISTA_CE - listaConhecimentoEletronico.length();
                AssertUtils.assertExpression(tamanhoDisopnivel >= ce.length() + 1, ConstantesI18N.DOCUMENTACAO_LONGO_CURSO_TAMANHO_MAX_LISTA_CE_ATINGIDO);
                
                listaConhecimentoEletronico += ";" + ce;
            }
        }
    }
    
    public void removerCE(String ce){
        if (listaConhecimentoEletronico != null){
            List<String> listaCE = new ArrayList<String>(Arrays.asList(listaConhecimentoEletronico.split(";")));
            listaCE.remove(ce);
            listaConhecimentoEletronico = transformarListParaStringDeCe(listaCE);
        }
    }
    
    private String transformarListParaStringDeCe(List<String> listaCE){
        String stringTransformada = "";
        
        for (String ce : listaCE){
            if (stringTransformada.isEmpty()){
                stringTransformada = ce;
            }else{
                stringTransformada += ";" + ce;
            }
        }
        return stringTransformada;
    }
    
    @Override
    public DocumentacaoLongoCurso clone(){
        DocumentacaoLongoCurso clone = (DocumentacaoLongoCurso)super.clone();
        return clone;
    }
        
}
