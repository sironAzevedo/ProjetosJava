package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.PastaAnexo;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "AGENCIAMENTO_ANEXO", schema = "STAM")
public class Anexo extends AbstractIdBean<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String PROP_ID = "id";
    public static final String PROP_AGENCIAMENTO = "agenciamento";
    public static final String PROP_NOME = "nome";
    public static final String PROP_TAMANHO_EM_BYTES = "tamanhoEmBytes";
    public static final String PROP_PASTA = "pasta";
    public static final String PROP_EXTENSAO = "extensao";
    public static final String PROP_DATA_CRIACAO = "dataDeCriacao";
    public static final String PROP_CHAVE_USUARIO =  "chaveUsuario";
    public static final String PROP_NOME_USUARIO = "nomeUsuario";
    
    
    @Id
    @Column(name = "AGAN_SQ_AGENCIAMENTO_ANEXO", nullable=false)
    @GeneratedValue(generator = "SQ_AGAN_SQ_AGENCIAMENTO_ANEXO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_AGAN_SQ_AGENCIAMENTO_ANEXO", sequenceName = "STAM.SQ_AGAN_SQ_AGENCIAMENTO_ANEXO", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGEC_SQ_AGENCIAMENTO", nullable=false)
    private Agenciamento agenciamento;
    
    @Column(name = "AGAN_NM_ANEXO", nullable=false)
    private String nome;
    
    @Column(name = "AGAN_QN_TAMANHO", nullable=false)
    private Long tamanhoEmBytes;
 
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name  = "enumClass",value = "br.com.petrobras.sistam.common.enums.PastaAnexo")})
    @Column(name="AGAN_IN_PASTA", nullable=false)
    private PastaAnexo pasta;
    
    @Column(name = "AGAN_TX_EXTENSAO", nullable=false)
    private String extensao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AGAN_DT_ANEXO", nullable=false)
    private Date dataDeCriacao;
    
    @Column(name = "AGAN_CD_CHAVE_CADASTRADOR", nullable=false)
    private String chaveUsuario;
    
    @Column(name = "AGAN_NM_CADASTRADOR", nullable=false)
    private String nomeUsuario;
    
    @OneToMany(cascade= CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = Arquivo.PROP_ANEXO)
    private Set<Arquivo> arquivo  = new  HashSet<Arquivo>(); 
    

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {}
    
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTamanhoEmBytes() {
        return tamanhoEmBytes;
    }

    public void setTamanhoEmBytes(Long tamanhoEmBytes) {
        this.tamanhoEmBytes = tamanhoEmBytes;
    }

    
    public PastaAnexo getPasta() {
        return pasta;
    }
    
    public void setPasta(PastaAnexo pasta) {
        this.pasta = pasta;
    }
    
    public String getExtensao() {
        return extensao;
    }
    
    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public Arquivo getArquivo() {
        if (arquivo.size() > 0){
            return arquivo.iterator().next();
        }
        return null;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo.clear();
        
        if (arquivo != null){
            arquivo.setAnexo(this);
            this.arquivo.add(arquivo);
        }
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }


    public String getChaveUsuario() {
        return chaveUsuario;
    }

    public void setChaveUsuario(String chaveUsuario) {
        this.chaveUsuario = chaveUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getter Sintéticos">
    
    /**
     * Obtém o tamanho do arquivo formatado. A depender do tamanho, pode ser mostrado em bytes, kB ou MB.
     * @return 
     */
    public String getTamanhoFormatado() {
        if (tamanhoEmBytes <= 0) {
            return "0 MB";
        }
        final String[] units = new String[]{"bytes", "kB", "MB"};
        int digitGroups = (int) (Math.log10(tamanhoEmBytes) / Math.log10(1024));
        return new DecimalFormat("#,##0.##").format(tamanhoEmBytes / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
    
    /**
     * Obtém o usuário formatado na forma 'chave - nome'.
     * @return 
     */
    public String getUsuarioFormatado(){
        return chaveUsuario + " - " + nomeUsuario;
    }
    
    //</editor-fold>

    @Override
    public String toString() {
        return nome;
    }

   
    
}
