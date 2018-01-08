package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.ParteDeSaidaCapitaniaVo;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ParteSaidaDialogModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private ParteDeSaidaCapitaniaVo voFormulario;
    private Date dataDocumento;
    
    public ParteSaidaDialogModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        voFormulario = new ParteDeSaidaCapitaniaVo();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public ParteDeSaidaCapitaniaVo getVoFormulario() {
        return voFormulario;
    }
    
    public List<TipoSimNao> getListaSimNao() {
        return Collections.unmodifiableList(new ArrayList<TipoSimNao> (Arrays.asList(TipoSimNao.values())));
    }
    
    public TipoSimNao getTripulantes() {
        return TipoSimNao.from(voFormulario.isListaTripulantes());
    }
    
    public void setTripulantes(TipoSimNao tripulantes) {
        voFormulario.setListaTripulantes(tripulantes.getId());
    }
    
    public TipoSimNao getPassageiros() {
        return TipoSimNao.from(voFormulario.isListaPassageiros());
    }
    
    public void setPassageiros(TipoSimNao passageiros) {
        voFormulario.setListaPassageiros(passageiros.getId());
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }
    
    //</editor-fold>
    
    public void gerarFormulario(){
        carregarVO();
        getService().registrarEmissaoDeDocumento(TipoDocumento.PARTE_SAIDA, agenciamento, false);
        RelatorioUtil.abrirRelatorioParteDeSaidaCapitania(voFormulario);
    }
    
    public void carregarVO() {
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());
        
        voFormulario.setDuv(agenciamento.getNumeroDUV());
        voFormulario.setPortoSaida(agenciamento.getPorto().getNomeCompleto());
        voFormulario.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        voFormulario.setImo(agenciamento.getEmbarcacao().getImo());
        voFormulario.setNumeroInscricao(agenciamento.getEmbarcacao().getInscricao());
        
        String portoDestino = (!agenciamento.getDestinoFormatado().isEmpty()) ? agenciamento.getDestinoFormatado() : "A confirmar";
        voFormulario.setDestino(portoDestino);
        
        String dataEstimadaChegada = agenciamento.getEtaProximoPorto() != null ? SistamDateUtils.formatDateComplete(agenciamento.getEtaProximoPorto(), zone) : null;
        voFormulario.setDataHoraEstimadaChegadaDestino(dataEstimadaChegada);
        
        String caldadoAVante = agenciamento.getCaladoSaidaVante() != null ? String.format("%.2f", agenciamento.getCaladoSaidaVante()) : null;
        voFormulario.setCaladoAVante(caldadoAVante);
        
        String caladoARe = agenciamento.getCaladoSaidaRe() != null ? String.format("%.2f", agenciamento.getCaladoSaidaRe()) : null;
        voFormulario.setCaladoARe(caladoARe);
        
        String saida = agenciamento.getDataSaida() != null ? SistamDateUtils.formatDateComplete(agenciamento.getDataSaida(), zone) : null;
        voFormulario.setDataHoraRealSaida(saida);
        
        voFormulario.setMunicipio(agenciamento.getAgencia().getCidade().trim() + ",");
        
        String data_documento = dataDocumento!=null?  SistamDateUtils.formatDateByExtensive(dataDocumento, null) : SistamDateUtils.formatDateByExtensive(new Date(), zone);        
        voFormulario.setDataAssinatura(data_documento);
        
        voFormulario.setNumeroProcessoDespacho(agenciamento.getNumeroProcessoDespacho());
    }
}
