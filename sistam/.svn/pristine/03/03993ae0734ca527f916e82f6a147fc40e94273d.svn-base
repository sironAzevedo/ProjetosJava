package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.util.SistamUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AGENCIA", schema = "STAM")
public class Agencia extends AbstractIdBean<Long> {
    private static final long serialVersionUID = 1L;
    
    public static final String AGENCIA_MANAUS = "MAN";
    public static final String PETROBRAS = "PETRÓLEO BRASILEIRO S/A - PETROBRAS";
    public static final String AGENCIA_PETROBRAS = "PETROBRAS - AGÊNCIA MARÍTIMA";
    public static final String SEDE_PETROBRAS = "RIO DE JANEIRO";
    
    public static final String PROP_ID = "id";
    public static final String PROP_NOME="nome";
    public static final String PROP_SIGLA = "sigla";
    public static final String PROP_ENDERECO = "endereco";
    public static final String PROP_TELEFONE = "telefone";
    public static final String PROP_FAX = "fax";
    public static final String PROP_EMAIL = "email";
    public static final String PROP_BAIRRO = "bairro";
    public static final String PROP_CIDADE = "cidade";
    public static final String PROP_ESTADO = "estado";
    public static final String PROP_CEP = "cep";
    public static final String PROP_CNPJ = "cnpj";
    public static final String PROP_NR_AFE = "afe";
    public static final String PROP_REPRESENTANTES = "representantes";
    public static final String PROP_AGENCIA_PORTOS = "agenciaPortos";
    public static final String PROP_AGENCIA_ORGAO = "agenciaOrgao";
    
    @Id
    @Column(name = "AGEN_SQ_AGENCIA", nullable=false)
    @GeneratedValue(generator = "SQ_AGEN_SQ_AGENCIA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_AGEN_SQ_AGENCIA", sequenceName = "STAM.SQ_AGEN_SQ_AGENCIA", allocationSize = 1)
    private Long id;

    @Column(name = "AGEN_SG_AGENCIA", nullable=false)
    private String sigla;
    
    @Column(name = "AGEN_NM_AGENCIA", nullable=false)
    private String nome;
    
    @Column(name = "AGEN_TX_ENDERECO", nullable=false)
    private String endereco;
    
    @Column(name = "AGEN_TX_TELEFONE", nullable=false)
    private String telefone;

    @Column(name = "AGEN_TX_FAX", nullable=true)
    private String fax;

    @Column(name = "AGEN_TX_EMAIL", nullable=false)
    private String email;

    @Column(name = "AGEN_TX_BAIRRO", nullable=true)
    private String bairro;
    
    @Column(name = "AGEN_TX_CIDADE", nullable=false)
    private String cidade;

    @Column(name = "AGEN_CD_ESTADO", nullable=false)
    private String estado;

    @Column(name = "AGEN_CD_CEP", nullable=true)
    private String cep;

    @Column(name = "AGEN_TX_NR_CNPJ", nullable=true)
    private String cnpj;
    
    @Column(name = "AGEN_TX_NR_AFE", nullable=true)
    private String afe;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy=RepresentanteLegal.PROP_AGENCIA, cascade=CascadeType.ALL)
    private Set<RepresentanteLegal> representantes = new HashSet<RepresentanteLegal>();
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy=AgenciaOrgaoDespacho.PROP_AGENCIA, cascade=CascadeType.ALL)
    private Set<AgenciaOrgaoDespacho> agenciaOrgao = new HashSet<AgenciaOrgaoDespacho>();

    @OneToMany(fetch=FetchType.LAZY, mappedBy=AgenciaPorto.PROP_AGENCIA, cascade=CascadeType.ALL)
    private Set<AgenciaPorto> agenciaPortos = new HashSet<AgenciaPorto>();
    
    @Column(name = "AGEN_CD_TIMEZONE", nullable=true)
    private String timezone;
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getFax() {
        return fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getBairro() {
        return bairro;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getCep() {
        return cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public String getCidade() {
        return cidade;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public String getAfe() {
        return afe;
    }

    public void setAfe(String afe) {
        this.afe = afe;
    }
    
    public String getTimezone(){
        return timezone;
    }
    
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters Sintéticos">
    public List<RepresentanteLegal> getRepresentantes() {
        List<RepresentanteLegal> lista =  new ArrayList<RepresentanteLegal>();
        for (RepresentanteLegal representante : representantes) {
            if (Boolean.TRUE.equals(representante.getAtivo())) {
                lista.add(representante);
            }
        }
        Collections.sort(lista);
        return Collections.unmodifiableList(lista);
    }

    public List<RepresentanteLegal> getRepresentantesAtivosInativos() {
        List<RepresentanteLegal> lista =  new ArrayList<RepresentanteLegal>(representantes);
        Collections.sort(lista);
        return Collections.unmodifiableList(lista);
    }
    
    
    public List<Porto> getPortos() {
        List<Porto> listaPortos = new ArrayList<Porto>();
        for(AgenciaPorto porto : agenciaPortos) {
            listaPortos.add(porto.getPorto());
        }
        return Collections.unmodifiableList(new ArrayList<Porto>(listaPortos));
    }
    
    public String getMunicipioDoPorto(Porto porto) {
        String municipio = null;
        if (porto != null) {
            for (AgenciaPorto agenciaPorto : agenciaPortos) {
                if (porto.equals(agenciaPorto.getPorto())) {
                    municipio = agenciaPorto.getMunicipio();
                }
            }
        }
        return municipio;
    }
    
    public String getCnpjComMascara() {
        return SistamUtils.formatMask("##.###.###/####-##", cnpj);
    }
    
    public void setCnpjComMascara(String cnpj) {
        if (cnpj == null) {
            setCnpj(null);
        } else {
            setCnpj(cnpj.replaceAll("\\D", ""));
        }
    }
    
    public List<AgenciaOrgaoDespacho> getAgenciaOrgao() {
        return Collections.unmodifiableList(new ArrayList<AgenciaOrgaoDespacho>(agenciaOrgao));
    }
    
    public String getNomeEnderecoAgente(){
        StringBuilder sb = new StringBuilder();
        sb
                .append(PETROBRAS)
                .append(" - ")
                .append(getEndereco())
                .append(" CEP. :").append(getCep())
                .append(" - ").append(getCidade())
                .append("/").append(getEstado())
                ;
        return sb.toString();
    }
    public String getNomeEnderecoEmailTelefoneAgente(){
        StringBuilder sb = new StringBuilder();
        sb
                .append(PETROBRAS)
                .append(", end: ")
                .append(getEndereco())
                .append(", e-mail: ")
                .append(getEmail())
                .append(", tel: ")
                .append(getTelefone()) ;
        return sb.toString();
    }
    
    public String getNomeCompleto(){
        StringBuilder sb = new StringBuilder();
        sb
                .append(AGENCIA_PETROBRAS)
                .append(" ")
                .append(getNome()) ;
        return sb.toString();
        
    }
    
    public String getEnderecoCompleto() {
        StringBuilder sb = new StringBuilder();
        sb.append(endereco)
                .append(", ")
                .append(bairro)
                .append(", ")
                .append(cidade)
                .append("-")
                .append(estado);
        return sb.toString();
    }
    public String getPetroCNPJAgencia() {
        StringBuilder sb = new StringBuilder();
        sb.append(PETROBRAS)
                .append("/ CNPJ: ")
                .append(getCnpjComMascara());
        return sb.toString();
    }
    
    //</editor-fold>
    
    public TimeZone obterTimezone() {
        if (timezone == null){
            return null;
        }
        return TimeZone.getTimeZone(timezone);
    }

    public void adicionarRepresentante(RepresentanteLegal representante) {
        if (representante != null) {
            representantes.add(representante);
        }
    }
    
    public void removerRepresentante(RepresentanteLegal representante) {
        representantes.remove(representante);
    }

    public void adicionarAgenciaPorto(AgenciaPorto agenciaPorto) {
        if (agenciaPorto != null) {
            agenciaPortos.add(agenciaPorto);
        }
    }
    
    public void removerAgenciaPorto(AgenciaPorto agenciaPorto) {
        agenciaPortos.remove(agenciaPorto);
    }
    
    @Override
    public String toString() {
        return nome;
    }
}