package br.com.petrobras.sistam.desktop.agenciamento;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.SituacaoProcesso;
import br.com.petrobras.sistam.common.enums.TipoArmador;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoFrota;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class AgenciamentoIniciarModel extends BasePresentationModel<SistamService> {
    private List<Agencia> agencias;
    private List<Porto> portos;
    private List<TipoFrota> tipoFrota;
    private Agenciamento agenciamento = new Agenciamento();
    private TimeZone timeZoneSelecionado;
    private List<TipoContrato> tipoContrato;
    private List<TipoArmador> tipoArmador; 
    private boolean habilitar = true; 
    
    public AgenciamentoIniciarModel() {
        
        SistamCredentialsBean contexto = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        setAgencias(contexto.getAgenciasAutorizadas());
        
        agenciamento.setSituacaoProcesso(SituacaoProcesso.EM_ANDAMENTO);
        agenciamento.addPropertyChangeListener(this);
        
        tipoContrato = new ArrayList<TipoContrato>(Arrays.asList(TipoContrato.values()));
        tipoContrato.add(0, null);

        tipoArmador = new ArrayList<TipoArmador>(Arrays.asList(TipoArmador.values()));
        tipoArmador.add(0, null);
        
        tipoFrota = new ArrayList<TipoFrota>(Arrays.asList(TipoFrota.values()));
        tipoFrota.add(0, null);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public List<Agencia> getAgencias() {
        return agencias;
    }

    public void setAgencias(List<Agencia> agencias) {
        this.agencias = agencias;
        firePropertyChange("agencias", null, this.agencias);
    }

    public List<Porto> getPortos() {
        return portos;
    }

    public void setPortos(List<Porto> portos) {
        this.portos = portos;
        firePropertyChange("portos", null, this.portos);
    }

    public TimeZone getTimeZoneSelecionado() {
        return timeZoneSelecionado;
    }

    public void setTimeZoneSelecionado(TimeZone timeZoneSelecionado) {
        this.timeZoneSelecionado = timeZoneSelecionado;
        firePropertyChange("timeZoneSelecionado", null, this.timeZoneSelecionado);
    }

    public List<TipoContrato> getTipoContrato() {
        return tipoContrato;
    }

    public List<TipoArmador> getTipoArmador() {
        return tipoArmador;
    }

    public boolean isHabilitar() {
        return habilitar;
    }

    public void setHabilitar(boolean habilitar) {
        this.habilitar = habilitar;
        firePropertyChange("habilitar", null, null);
    }  

    public List<TipoFrota> getTipoFrota() {
        return tipoFrota;
    }

    public void setTipoFrota(List<TipoFrota> tipoFrota) {
        this.tipoFrota = tipoFrota;
        firePropertyChange("tipoFrota", null, null);
    }  
    
    //</editor-fold>
    
    /**
     * Salva um novo agenciamento.
     */
    public void salvar(){
        getService().criarNovoAgenciamento(agenciamento, SistamDateUtils.getCurrentYear(TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone())));
        SistamApp.getSistamApp().atualizarCaixaEntrada();
    }
    
    /**
     * Verifica se o agenciamento com o navio, viagem, porto e eta informados e situação diferente de cancelado ainda não foi criado para a agência.
     * @return 
     */
    public boolean verificarSeAgenciamentoJaFoiCriado(){
        return getService().verificarSeAgenciamentoJaFoiCriado(agenciamento);
    } 
    
    public void habilitarComponentes(){ 
        if(agenciamento.getAgenciamentoPlataforma()){ 
            agenciamento.setAgenciamentoCarga(false);
            agenciamento.setAgenciamentoProtecao(false);
            setHabilitar(false); 
        } else{
            setHabilitar(true);
        }
    } 
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Agenciamento.PROP_AGENCIA)) { 
            List<Porto> listaDePortos = getService().buscarPortosPorAgencia(agenciamento.getAgencia());
            listaDePortos.add(0, null);
            setPortos(listaDePortos);
            setTimeZoneSelecionado(TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone()));
        }else if(evt.getPropertyName().equals(Agenciamento.PROP_AGENCIAMENTO_PLATAFORMA)){
            habilitarComponentes();
        }
    }
}
