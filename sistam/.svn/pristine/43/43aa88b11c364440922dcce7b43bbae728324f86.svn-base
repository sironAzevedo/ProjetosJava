package br.com.petrobras.sistam.common.entity;

import br.com.petrobras.fcorp.common.beans.AbstractIdBean;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "VW_ESCALA_EMBARCACAO", schema = "STAM")
public class Escala extends AbstractIdBean<EscalaPk>  {
    private static final long serialVersionUID = 1L;
    private static final int TAM_MAX_ESCALAS = 1200;
    public static final String PROP_ID = "id";

    public Escala() {
    }

    public Escala(EscalaPk id) {
        this.id = id;
    }

    @EmbeddedId
    private EscalaPk id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_CHEGADA", nullable=false)
    private Date dataChegada;
    
    
    @Override
    public EscalaPk getId() {
        return id;
    }

    @Override
    public void setId(EscalaPk id) {
        this.id = id;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }
    
    
      public static String formatarEscalas(List<Escala> escalas, TimeZone timeZone) {
        StringBuilder sbEscalasFormatadas = new StringBuilder();           

        for (int i = escalas.size() - 1 ; i >=0 ; i--){
            StringBuilder sbEscala = new StringBuilder();
            Escala e = escalas.get(i);
            
                        
            sbEscala.append(e.getId().getPorto().getNomeCompleto());
            sbEscala.append(" - "); 
            sbEscala.append(e.getId().getPorto().getPais().getNomeCompleto());
            sbEscala.append(" - ");
            sbEscala.append(SistamDateUtils.formatDate(e.getId().getData(), "dd/MM/yyyy", timeZone));
           
             if (i > 0){ 
                sbEscala.append(" / ");
            }
             
            int tamanhoDisponivel = TAM_MAX_ESCALAS - sbEscalasFormatadas.length();
            if ( tamanhoDisponivel > sbEscala.length()){
                sbEscalasFormatadas.append(sbEscala);
            }
            else{
                break;
            }
        }
       
        return sbEscalasFormatadas.toString();
    
    }
}
