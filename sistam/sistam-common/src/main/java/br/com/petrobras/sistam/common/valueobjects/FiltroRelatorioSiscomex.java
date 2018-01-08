package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author adzk
 */
public class FiltroRelatorioSiscomex extends AbstractBean {
        public static final String PROP_AGENCIA = "agencia";

    private Agencia agencia;
    private Porto porto;
    private List<TipoOperacao> tiposOperacao;
    private List<TipoContrato> tiposContrato;
    private List<StatusEmbarcacao> situacoesEmbarcacao;
    private TipoSimNao comManisfesto;
    private TipoSimNao comEscalaMercante;
    private Periodo periodoOficialChegada;
    private Periodo periodoOficialSaida;
    private Periodo periodoInicioOperacao;
    private Periodo periodoTerminoOperacao;
    private Periodo periodoInclusao;
    private Periodo periodoEta;
    private Periodo periodoEts;
    private TipoOrdenacaoSiscomex ordenacao;

    public FiltroRelatorioSiscomex() {
        tiposOperacao = new ArrayList<TipoOperacao>();
        tiposContrato = new ArrayList<TipoContrato>();
        situacoesEmbarcacao = new ArrayList<StatusEmbarcacao>();

        periodoOficialChegada = new Periodo();
        periodoOficialSaida = new Periodo();
        periodoInicioOperacao = new Periodo();
        periodoTerminoOperacao = new Periodo();
        periodoInclusao = new Periodo();
        periodoEta = new Periodo();
        periodoEts = new Periodo();

        ordenacao = TipoOrdenacaoSiscomex.POR_NAVIO_VIAGEM;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
        firePropertyChange("agencia", null, this.agencia);
    }
    
    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
        firePropertyChange("porto", null, this.porto);
    }

    public List<TipoOperacao> getTiposOperacao() {
        return tiposOperacao;
    }

    public void setTiposOperacao(List<TipoOperacao> tiposOperacao) {
        this.tiposOperacao = tiposOperacao;
    }

    public List<TipoContrato> getTiposContrato() {
        return tiposContrato;
    }

    public void setTiposContrato(List<TipoContrato> tiposContrato) {
        this.tiposContrato = tiposContrato;
    }

    public List<StatusEmbarcacao> getSituacoesEmbarcacao() {
        return situacoesEmbarcacao;
    }

    public void setSituacoesEmbarcacao(List<StatusEmbarcacao> situacoesEmbarcacao) {
        this.situacoesEmbarcacao = situacoesEmbarcacao;
    }

    public boolean isComManifestoSim() {
        return TipoSimNao.SIM.equals(comManisfesto);
    }

    public boolean isComEscalaMercanteSim() {
        return TipoSimNao.SIM.equals(comEscalaMercante);
    }

    public TipoSimNao getComManisfesto() {
        return comManisfesto;
    }

    public void setComManisfesto(TipoSimNao comManisfesto) {
        this.comManisfesto = comManisfesto;
    }

    public Periodo getPeriodoOficialChegada() {
        return periodoOficialChegada;
    }

    public Periodo getPeriodoOficialSaida() {
        return periodoOficialSaida;
    }

    public Periodo getPeriodoInicioOperacao() {
        return periodoInicioOperacao;
    }

    public Periodo getPeriodoTerminoOperacao() {
        return periodoTerminoOperacao;
    }

    public Periodo getPeriodoInclusao() {
        return periodoInclusao;
    }

    public Periodo getPeriodoEta() {
        return periodoEta;
    }

    public TipoSimNao getComEscalaMercante() {
        return comEscalaMercante;
    }

    public void setComEscalaMercante(TipoSimNao comEscalaMercante) {
        this.comEscalaMercante = comEscalaMercante;
    }

    public Periodo getPeriodoEts() {
        return periodoEts;
    }

    public void setPeriodoEts(Periodo periodoEts) {
        this.periodoEts = periodoEts;
    }

    public TipoOrdenacaoSiscomex getOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(TipoOrdenacaoSiscomex ordenacao) {
        this.ordenacao = ordenacao;
    }

    public boolean isOrdenacaoPorNavioEViagem() {
        return TipoOrdenacaoSiscomex.POR_NAVIO_VIAGEM.equals(ordenacao);
    }

    public boolean isOrdenacaoPorNumeroProcesso() {
        return TipoOrdenacaoSiscomex.POR_NUMERO_PROCESSO.equals(ordenacao);
    }

    public boolean isOrdenacaoPorDataChegada() {
        return TipoOrdenacaoSiscomex.POR_DATA_CHEGADA.equals(ordenacao);
    }

    public boolean isTipoOperacaoCabotagem() {
        //return !tiposOperacao.isEmpty() && (tiposOperacao.contains(TipoOperacao.CARGA_CABOTAGEM) || tiposOperacao.contains(TipoOperacao.DESCARGA_CABOTAGEM));
       return isTipoOperacaoCargaCabotagem() || isTipoOperacaoDescargaCabotagem();
    }

    public boolean isTipoOperacaoCargaCabotagem() {
        return !tiposOperacao.isEmpty() && (tiposOperacao.contains(TipoOperacao.CARGA_CABOTAGEM));
    }

    public boolean isTipoOperacaoDescargaCabotagem() {
        return !tiposOperacao.isEmpty() && (tiposOperacao.contains(TipoOperacao.DESCARGA_CABOTAGEM));
    }

    public boolean isTipoOperacaoLongoCurso() {
        //return !tiposOperacao.isEmpty() && (tiposOperacao.contains(TipoOperacao.CARGA_EXPORTACAO) || tiposOperacao.contains(TipoOperacao.DESCARGA_IMPORTACAO));
       return isTipoOperacaoCargaExportacao() || isTipoOperacaoDescargaImportacao();
    }

    public boolean isTipoOperacaoCargaExportacao() {
        return !tiposOperacao.isEmpty() && (tiposOperacao.contains(TipoOperacao.CARGA_EXPORTACAO));
    }
    
    public boolean isTipoOperacaoDescargaImportacao() {
        return !tiposOperacao.isEmpty() && (tiposOperacao.contains(TipoOperacao.DESCARGA_IMPORTACAO));
    }

    public boolean isTipoSemOperacaoComercial() {
        return !tiposOperacao.isEmpty() && (tiposOperacao.contains(TipoOperacao.SEM_OPERACAO_COMERCIAL));
    }

     public boolean isPortoPreenchido() {
        return porto != null;
    }
    
    public String getTiposOperacaoDescricao() {
        StringBuilder sb = new StringBuilder();
        for (TipoOperacao tipo : tiposOperacao) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append(tipo.getPorExtenso());
        }
        return sb.toString();
    }

    public String getTiposContratoDescricao() {
        StringBuilder sb = new StringBuilder();
        for (TipoContrato tipo : tiposContrato) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append(tipo.getPorExtenso());
        }
        return sb.toString();
    }

    public String getSituacoesEmbarcacaoDescricao() {
        StringBuilder sb = new StringBuilder();
        for (StatusEmbarcacao situacao : situacoesEmbarcacao) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append(situacao.getPorExtenso());
        }
        return sb.toString();
    }

    public enum TipoOrdenacaoSiscomex {

        POR_NAVIO_VIAGEM("NV", "Por Embarcação/Viagem"),
        POR_NUMERO_PROCESSO("NP", "Por Nº do Processo no Sistam"),
        POR_DATA_CHEGADA("DC", "Por Data de Chegada no Porto");
        private String id;
        private String porExtenso;

        private TipoOrdenacaoSiscomex(String id, String porExtenso) {
            this.id = id;
            this.porExtenso = porExtenso;
        }

        public String getId() {
            return id;
        }

        public String getPorExtenso() {
            return porExtenso;
        }

        public static List<TipoOrdenacaoSiscomex> listValues() {
            return new ArrayList<TipoOrdenacaoSiscomex>(Arrays.asList(TipoOrdenacaoSiscomex.values()));
        }

        public static TipoOrdenacaoSiscomex from(String id) {
            if (id == null) {
                return null;
            }
            for (TipoOrdenacaoSiscomex tipo : values()) {
                if (tipo.id.equals(id)) {
                    return tipo;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return porExtenso;
        }
    }
}