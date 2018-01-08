package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.enums.TipoVeiculo;
import br.com.petrobras.sistam.common.util.SistamUtils;
import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 *
 */
@Entity
@Table(name = "SUPRIMENTO_TRANSITO_VEICULO", schema = "STAM")
public class ServicoSuprimentoTransitoVeiculo extends AbstractIdBean<Long> implements Serializable, Comparable<ServicoSuprimentoTransitoVeiculo>{

    //<editor-fold defaultstate="collapsed" desc="Constantes">
    public static final String SERV_SUPRIMENTO_TRANSITO_VEICULO_ID = "id";
    public static final String SERV_SUPRIMENTO_TRANSITO_VEICULO_SOLICITACAO_TRANSITO = "solicitacaoTransito";
    public static final String SERV_SUPRIMENTO_TRANSITO_VEICULO_NOME_CONDUTOR = "nomeCondutor";
    public static final String SERV_SUPRIMENTO_TRANSITO_VEICULO_CPF_CONDUTOR = "cpfCondutor";
    public static final String SERV_SUPRIMENTO_TRANSITO_VEICULO_TIPO_VEICULO = "tipoVeiculo";
    public static final String SERV_SUPRIMENTO_TRANSITO_VEICULO_PLACA_VEICULO = "placaVeiculo";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Propriedade">
    @Id
    @Column(name = "SUTV_SQ_VEICULO", nullable=false)
    @GeneratedValue(generator = "SQ_SUTV_SQ_VEICULO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_SUTV_SQ_VEICULO", sequenceName = "STAM.SQ_SUTV_SQ_VEICULO", allocationSize = 1)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUTR_SQ_SOLICITACAO", nullable = false)
    private ServicoSuprimentoTransito solicitacaoTransito;
    
    @Column(name = "SUTV_NM_CONDUTOR")
    private String nomeCondutor;
    
    @Column(name = "SUTV_CD_CPF")
    private String cpfCondutor;
    
    @Column(name = "SUTV_TX_PLACA")
    private String placaVeiculo;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.petrobras.fcorp.persistence.dao.hibernate.support.GenericEnumUserType",
            parameters = {@Parameter(name = "enumClass", value = "br.com.petrobras.sistam.common.enums.TipoVeiculo")})
    @Column(name = "SUTV_TX_VEICULO", nullable = true)
    private TipoVeiculo tipoVeiculo;
    
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    @Override
    public void setId(Long id) {
        this.id = id; 
    }

    @Override
    public Long getId() {
        return  id;
    }

    public ServicoSuprimentoTransito getSolicitacaoTransito() {
        return solicitacaoTransito;
    }

    public void setSolicitacaoTransito(ServicoSuprimentoTransito solicitacaoTransito) {
        this.solicitacaoTransito = solicitacaoTransito;
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_VEICULO_SOLICITACAO_TRANSITO, null, null);
    } 
    
    public String getNomeCondutor() {
        return nomeCondutor;
    }

    public void setNomeCondutor(String nomeCondutor) {
        this.nomeCondutor = nomeCondutor;
        firePropertyChange("nomeCondutor", null, null);
    }

    public String getCpfCondutor() {
        return cpfCondutor;
    }

    public void setCpfCondutor(String cpfCondutor) {
        this.cpfCondutor = cpfCondutor;
        firePropertyChange("cpfCondutor", null, null);
    }
    
    public String getCpfComMascara() {
        return SistamUtils.formatMask("###.###.###-##", cpfCondutor);
    }

    public void setCpfComMascara(String cpf) {
        setCpfCondutor(cpf == null ? null : cpf.replaceAll("\\D", ""));
         firePropertyChange("cpfComMascara", null, null);
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
        firePropertyChange(SERV_SUPRIMENTO_TRANSITO_VEICULO_TIPO_VEICULO, null, null);
    } 
    
    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
        firePropertyChange("placaVeiculo", null, null);
    }
    
    //</editor-fold>

   @Override
    public String toString() {
        return nomeCondutor;
    }

    @Override
    public int compareTo(ServicoSuprimentoTransitoVeiculo o) {
        if(StringUtils.isBlank(this.getNomeCondutor())){
            return -1;
        }
        return this.getNomeCondutor().compareToIgnoreCase(o.getNomeCondutor());
    }
    
}
