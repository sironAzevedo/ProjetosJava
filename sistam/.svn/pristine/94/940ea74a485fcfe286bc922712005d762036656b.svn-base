package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.sistam.common.enums.TipoAcesso;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author adzk
 */
public class RelatorioReceitaDesEmbarqueTripulanteVO extends AbstractBean {

    public static final int QTD_TRIPULANTES_POR_PAGINA = 13;
    private boolean embarque = false;
    private String navio;
    private String escala;
    private Date dataHoraPrevista;
    private String lancha;
    private String observacoes;
    private String numeroDocumento;
    private List<PessoaTripulateVO> tripulantes = new ArrayList<PessoaTripulateVO>();

    private RelatorioReceitaDesEmbarqueTripulanteVO() {
    }

    private RelatorioReceitaDesEmbarqueTripulanteVO(boolean embarque) {
        this();
        this.embarque = embarque;
    }

    public String getLocalEDataGeracao() {
        return "Salvador/BA, " + SistamDateUtils.formatDate(new Date(), SistamDateUtils.DATE_PATTERN, null);
    }

    public static RelatorioReceitaDesEmbarqueTripulanteVO novo(TipoAcesso tipo) {
        if (!TipoAcesso.EMBARQUE.equals(tipo) && !TipoAcesso.DESEMBARQUE.equals(tipo)) {
            throw new BusinessException("O Relatório de Embarque e Desembarque de Tripulantes só aceita tipo de acesso Embarque ou Desembarque");
        }
        return new RelatorioReceitaDesEmbarqueTripulanteVO(TipoAcesso.EMBARQUE.equals(tipo));
    }

    public String getNavio() {
        return navio;
    }

    public void setNavio(String navio) {
        this.navio = navio;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getDataPrevista() {
        return dataHoraPrevista == null ? "" : SistamDateUtils.formatDate(dataHoraPrevista, SistamDateUtils.DATE_PATTERN, null);
    }

    public String getHoraPrevista() {
        return dataHoraPrevista == null ? "" : (SistamDateUtils.formatDate(dataHoraPrevista, SistamDateUtils.HOUR_PATTERN, null) + "h");
    }

    public void setDataHoraPrevista(Date dataHoraPrevista) {
        this.dataHoraPrevista = dataHoraPrevista;
    }

    public String getLancha() {
        return lancha;
    }

    public void setLancha(String lancha) {
        this.lancha = lancha;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public List<PessoaTripulateVO> getTripulantes() {
        return tripulantes;
    }

    private void setTripulantes(List<PessoaTripulateVO> tripulantes) {
        this.tripulantes = tripulantes;
    }

    public boolean isEmbarque() {
        return embarque;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    @Override
    public RelatorioReceitaDesEmbarqueTripulanteVO clone() {
        RelatorioReceitaDesEmbarqueTripulanteVO clone = (RelatorioReceitaDesEmbarqueTripulanteVO) super.clone();
        clone.setTripulantes(new ArrayList<PessoaTripulateVO>());
        return clone;
    }
}