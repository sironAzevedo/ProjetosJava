package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Escala;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.NotificacaoPrevisaoChegadaCapitaniaVo;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class NotificacaoDePrevisaoModel extends BasePresentationModel<SistamService> {
    
    private Agenciamento agenciamento;
    private NotificacaoPrevisaoChegadaCapitaniaVo voFormulario;
    
    
    public NotificacaoDePrevisaoModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        this.voFormulario = new NotificacaoPrevisaoChegadaCapitaniaVo();
        
    }
            
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public NotificacaoPrevisaoChegadaCapitaniaVo getVoFormulario() {
        return voFormulario;
    }

    
    public void registrarEmissao() {
        getService().registrarEmissaoDeDocumento(TipoDocumento.NOTIFICACAO_PREVISAO_CHEGADA, agenciamento, false);
    }

    public void carregarVO() {
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());
        
        List<Escala> listaCincoUltimasEscalas = getService().buscarUltimasEscalasDaEmbarcacao(agenciamento.getEmbarcacao(), 5);

        voFormulario.setProcessoDespacho(agenciamento.getNumeroProcessoDespacho());
        voFormulario.setDuv(agenciamento.getNumeroDUV());
        voFormulario.setPortoChegada(agenciamento.getPorto().getNomeCompleto());
        voFormulario.setDataHoraPrevisaoChegada(SistamDateUtils.formatDateComplete(agenciamento.getEta(), zone));
        voFormulario.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        voFormulario.setIrin(agenciamento.getEmbarcacao().getIrin());
        voFormulario.setBandeira(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto());
        voFormulario.setImo(agenciamento.getEmbarcacao().getImo());
        voFormulario.setNumeroInscricao(agenciamento.getEmbarcacao().getInscricao());
        voFormulario.setTipoEmbarcacao(agenciamento.getEmbarcacao().getClassificacao().getPorExtenso());
        
        if (agenciamento.getAreaNavegacao() != null) {
            voFormulario.setAreaNavegacaoEmbarcacao(agenciamento.getAreaNavegacao().getPorExtenso());
        } 
        
        voFormulario.setArqueacaoBruta(agenciamento.getEmbarcacao().getArqueacaoBruta());
        
        voFormulario.setInformacoesAgente(agenciamento.getAgencia().getNomeEnderecoEmailTelefoneAgente());
        voFormulario.setSociedadClassificadora(agenciamento.getEmbarcacao().getSociedade_classificadora());
        voFormulario.setArmador(agenciamento.getEmbarcacao().getArmador());
        voFormulario.setPortosVisitados(listaCincoUltimasEscalas);
        voFormulario.setMunicipio(agenciamento.getAgencia().getCidade().trim() + ",");
        voFormulario.setDataAssinatura(SistamDateUtils.formatDateByExtensive(new Date(), zone));
        
    }

}
