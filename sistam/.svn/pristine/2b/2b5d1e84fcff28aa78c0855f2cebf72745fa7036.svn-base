package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PORTO_SIGO", schema = "STAM")
public class Porto extends AbstractIdBean<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String PROP_ID = "id";
    public static final String PROP_NOME_COMPLETO = "nomeCompleto";
    public static final String PROP_APELIDO = "apelido";
    public static final String PROP_PAIS = "pais";
    public static final String PROP_PONTOS_OPERACIONAIS = "pontosOperacionais";
    public static final String PROP_AGENCIAS_PORTOS = "agenciasPortos";
    public static final String PROP_COMPLEMENTOS = "complementos";
    
    @Id
    @Column(name = "PORT_CD_ID", nullable=false, insertable=false, updatable=false)
    private String id;
    
    @Column(name = "PORT_NM_COMPLETO", nullable=true, insertable=false, updatable=false)
    private String nomeCompleto;

    @Column(name = "PORT_NM_APELIDO", nullable=true, insertable=false, updatable=false)
    private String apelido;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PAIS_CD_IBGE", nullable=true,insertable=false, updatable=false)
    private Pais pais;
    
    @OneToMany(fetch= FetchType.LAZY, mappedBy=PontoOperacional.PROP_PORTO)
    private Set<PontoOperacional> pontosOperacionais = new HashSet<PontoOperacional>();
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy=AgenciaPorto.PROP_PORTO)
    private Set<AgenciaPorto> agenciasPortos = new HashSet<AgenciaPorto>();
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy=PortoComplemento.PROP_PORTO, cascade = CascadeType.ALL)
    private Set<PortoComplemento> complementos = new HashSet<PortoComplemento>(0);
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }
   
    public String getApelido() {
        return apelido;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<PontoOperacional> getPontosOperacionais() {
        return Collections.unmodifiableList(new ArrayList<PontoOperacional>(pontosOperacionais));
    }

    public  List<AgenciaPorto> getAgenciasPortos() {
        return Collections.unmodifiableList(new ArrayList<AgenciaPorto>(agenciasPortos));
    }

    public Set<PortoComplemento> getComplementos() {
        return complementos;
    }

    public void setComplementos(Set<PortoComplemento> complementos) {
        this.complementos = complementos;
        firePropertyChange("complementos", null, null);
    }
    
    public List<PortoComplemento> getComplementosAsList(){
        return new LinkedList<PortoComplemento>(this.complementos);
    }

    public PortoComplemento getComplemento() {
        Iterator<PortoComplemento> iterator = complementos.iterator();
        
        if (iterator.hasNext()){
            return iterator.next();
        }
        
        return null;
    }

    public boolean isNacional(){
        return Pais.CODIGO_BRASIL.equals(pais.getId());
    }
    
    @Override
    public String toString() {
        return nomeCompleto;
    }
    
}
