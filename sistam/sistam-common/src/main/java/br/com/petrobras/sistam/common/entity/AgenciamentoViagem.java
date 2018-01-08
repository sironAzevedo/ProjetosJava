package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.snarf.common.hibernate.SimNaoType;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "AGENCIAMENTO_VIAGEM", schema = "STAM")
@org.hibernate.annotations.Entity( selectBeforeUpdate = true)
public class AgenciamentoViagem extends AbstractIdBean<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String PROP_ID = "id";
    public static final String PROP_COMANDANTE_ENTRADA = "comandanteEntrada";
    public static final String PROP_NACIONALIDADE_ENTRADA = "nacionalidadeEntrada";
    public static final String PROP_TRIPULANTE_ENTRADA = "numeroTripulantesEntrada";
    public static final String PROP_PASSAGEIRO_ENTRADA = "numeroPassageirosEntrada";
    
    public static final String PROP_COMANDANTE_SAIDA = "comandanteSaida";
    public static final String PROP_NACIONALIDADE_SAIDA = "nacionalidadeSaida";
    public static final String PROP_TRIPULANTE_SAIDA = "numeroTripulantesSaida";
    public static final String PROP_PASSAGEIRO_SAIDA = "numeroPassageirosSaida";
    public static final String PROP_OBITO = "obito";
    public static final String PROP_SEPULTAMENTO = "sepultamento";
    public static final String PROP_DOENCA = "doenca";
    public static final String PROP_FEBRE_HEMORRAGIA = "febreHemorragia";
    public static final String PROP_ICTERICIA = "ictericia";
    public static final String PROP_DIARREIA = "diarreia";
    public static final String PROP_DISFUNCOES_NEUROLOGICAS = "disfuncoesNeurologicas";
    public static final String PROP_DIFICULDADE_RESPIRATORIA = "dificuldadeRespiratoria";
    public static final String PROP_ACIDENTE = "acidente";
    public static final String PROP_NOME_ACIDENTE = "nomeAcidente";
    public static final String PROP_ROEDORES = "roedores";
    public static final String PROP_COMPARTIMENTO_ROEDORES = "compartimentoRoedores";
    public static final String PROP_CONSUMO_MEDICAMENTO = "consumoMedicamento";
    public static final String PROP_NOME_MEDICAMENTO = "nomeMedicamento";
    

    @Id
    @Column(name = "AGEC_SQ_AGENCIAMENTO", nullable=false)
    private Long id;
    
    @Version
    @Column(name = "AGVI_NR_VERSAO_REGISTRO", nullable=false)
    private Long versao;
    
    @Column(name = "AGVI_NM_COMANDANTE_ENTRADA", nullable=true)
    private String comandanteEntrada;

    @Column(name = "AGVI_TX_NACIONALIDADE_ENTRADA", nullable=true)
    private String nacionalidadeEntrada;

     @Column(name = "AGVI_NR_TRIPULANTES_ENTRADA", nullable=true)
    private Integer numeroTripulantesEntrada;

    @Column(name = "AGVI_NR_PASSAGEIROS_ENTRADA", nullable=true)
    private Integer numeroPassageirosEntrada;

    @Column(name = "AGVI_NM_COMANDANTE_SAIDA", nullable=true)
    private String comandanteSaida;

    @Column(name = "AGVI_TX_NACIONALIDADE_SAIDA", nullable=true)
    private String nacionalidadeSaida;
    
     @Column(name = "AGVI_NR_TRIPULANTES_SAIDA", nullable=true)
    private Integer numeroTripulantesSaida;

    @Column(name = "AGVI_NR_PASSAGEIROS_SAIDA", nullable=true)
    private Integer numeroPassageirosSaida;
   
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGVI_IN_OBITO", nullable=false)
    private Boolean obito = false;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGVI_IN_SEPULTAMENTO", nullable=false)
    private Boolean sepultamento = false;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGVI_IN_DOENCA", nullable=false)
    private Boolean doenca = false;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGVI_IN_FEBRE_HEMORRAGIA", nullable=false)
    private Boolean febreHemorragia = false;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGVI_IN_ICTERICIA", nullable=false)
    private Boolean ictericia = false;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGVI_IN_DIARREIA", nullable=false)
    private Boolean diarreia = false;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGVI_IN_DISF_NEURO", nullable=false)
    private Boolean disfuncoesNeurologicas = false;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGVI_IN_DIFICUL_RESP", nullable=false)
    private Boolean dificuldadeRespiratoria = false;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGVI_IN_ACIDENTE", nullable=false)
    private Boolean acidente = false;
    
    @Column(name = "AGVI_TX_ACIDENTE", nullable=true)
    private String nomeAcidente;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGVI_IN_ROEDORES", nullable=false)
    private Boolean roedores = false;
    
    @Column(name = "AGVI_TX_COMPART_ROEDOR", nullable=true)
    private String compartimentoRoedores;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "AGVI_IN_CONSUMO_MEDICAM", nullable=false)
    private Boolean consumoMedicamento = false;

    @Column(name = "AGVI_TX_MEDICAMENTO", nullable=true)
    private String nomeMedicamento;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getComandanteEntrada() {
        return comandanteEntrada;
    }

    public void setComandanteEntrada(String comandanteEntrada) {
        this.comandanteEntrada = comandanteEntrada;
    }

    public String getNacionalidadeEntrada() {
        return nacionalidadeEntrada;
    }

    
    public void setNacionalidadeEntrada(String nacionalidadeEntrada) {
        this.nacionalidadeEntrada = nacionalidadeEntrada;
    }

    public Integer getNumeroTripulantesEntrada() {
        return numeroTripulantesEntrada;
    }

    public void setNumeroTripulantesEntrada(Integer numeroTripulantesEntrada) {
        this.numeroTripulantesEntrada = numeroTripulantesEntrada;
    }

    public Integer getNumeroPassageirosEntrada() {
        return numeroPassageirosEntrada;
    }

    public void setNumeroPassageirosEntrada(Integer numeroPassageirosEntrada) {
        this.numeroPassageirosEntrada = numeroPassageirosEntrada;
    }

    
    public String getComandanteSaida() {
        return comandanteSaida;
    }

    public void setComandanteSaida(String comandanteSaida) {
        this.comandanteSaida = comandanteSaida;
    }

    public String getNacionalidadeSaida() {
        return nacionalidadeSaida;
    }

    public void setNacionalidadeSaida(String nacionalidadeSaida) {
        this.nacionalidadeSaida = nacionalidadeSaida;
    }

    public Integer getNumeroTripulantesSaida() {
        return numeroTripulantesSaida;
    }

    public void setNumeroTripulantesSaida(Integer numeroTripulantesSaida) {
        this.numeroTripulantesSaida = numeroTripulantesSaida;
    }

    public Integer getNumeroPassageirosSaida() {
        return numeroPassageirosSaida;
    }

    public void setNumeroPassageirosSaida(Integer numeroPassageirosSaida) {
        this.numeroPassageirosSaida = numeroPassageirosSaida;
    }

    
    public boolean isObito() {
        return obito;
    }

    public void setObito(Boolean obito) {
        this.obito = obito;
    }

    public boolean isSepultamento() {
        return sepultamento;
    }

    public void setSepultamento(Boolean sepultamento) {
        this.sepultamento = sepultamento;
    }

    public boolean isDoenca() {
        return doenca;
    }

    public void setDoenca(Boolean doenca) {
        this.doenca = doenca;
    }

    public boolean isFebreHemorragia() {
        return febreHemorragia;
    }

    public void setFebreHemorragia(Boolean febreHemorragia) {
        this.febreHemorragia = febreHemorragia;
    }

    public boolean isIctericia() {
        return ictericia;
    }

    public void setIctericia(Boolean ictericia) {
        this.ictericia = ictericia;
    }

    public boolean isDiarreia() {
        return diarreia;
    }

    public void setDiarreia(Boolean diarreia) {
        this.diarreia = diarreia;
    }

    public boolean isDisfuncoesNeurologicas() {
        return disfuncoesNeurologicas;
    }

    public void setDisfuncoesNeurologicas(Boolean disfuncoesNeurologicas) {
        this.disfuncoesNeurologicas = disfuncoesNeurologicas;
    }

    public boolean isDificuldadeRespiratoria() {
        return dificuldadeRespiratoria;
    }

    public void setDificuldadeRespiratoria(Boolean dificuldadeRespiratoria) {
        this.dificuldadeRespiratoria = dificuldadeRespiratoria;
    }

    public boolean isAcidente() {
        return acidente;
    }

    public void setAcidente(Boolean acidente) {
        this.acidente = acidente;
    }

    public String getNomeAcidente() {
        return nomeAcidente;
    }

    public void setNomeAcidente(String nomeAcidente) {
        this.nomeAcidente = nomeAcidente;
    }

    public boolean isRoedores() {
        return roedores;
    }

    public void setRoedores(Boolean roedores) {
        this.roedores = roedores;
    }

    public String getCompartimentoRoedores() {
        return compartimentoRoedores;
    }

    public void setCompartimentoRoedores(String compartimentoRoedores) {
        this.compartimentoRoedores = compartimentoRoedores;
    }

    public boolean isConsumoMedicamento() {
        return consumoMedicamento;
    }

    public void setConsumoMedicamento(Boolean consumoMedicamento) {
        this.consumoMedicamento = consumoMedicamento;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }
    
    @Override
    public AgenciamentoViagem clone(){
        return (AgenciamentoViagem)super.clone();
    }
    
}
