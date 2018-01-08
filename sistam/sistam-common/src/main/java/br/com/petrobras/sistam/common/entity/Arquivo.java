package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ANEXO", schema = "STAM")
public class Arquivo extends AbstractIdBean<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String PROP_ID = "id";
    public static final String PROP_ANEXO="anexo";
    public static final String PROP_CONTEUDO = "conteudo";
    
    @Id
    @Column(name = "AGAN_SQ_AGENCIAMENTO_ANEXO", nullable=false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AGAN_SQ_AGENCIAMENTO_ANEXO", nullable=false, updatable=false, insertable=false)
    private Anexo anexo;
    
    @Lob
    @Column(name = "ANEX_MM_ANEXO",  nullable=false)
    private byte[] conteudo;

    public Arquivo() {}

    public Arquivo(byte[] conteudo) {
        this.conteudo = conteudo.clone();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo.clone();
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }
    
    
}
