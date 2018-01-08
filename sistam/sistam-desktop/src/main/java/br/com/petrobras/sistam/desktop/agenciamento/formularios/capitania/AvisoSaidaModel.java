package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.AvisoDeSaidaCapitaniaVo;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;
import org.hibernate.Hibernate;

public class AvisoSaidaModel extends BasePresentationModel<SistamService> {

    private Agenciamento agenciamento;
    private AvisoDeSaidaCapitaniaVo voFormulario = new AvisoDeSaidaCapitaniaVo();
    private Date dataDocumento;

    public AvisoSaidaModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
    }

    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, null);
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
        firePropertyChange("dataDocumento", null, null);
    }

    public AvisoDeSaidaCapitaniaVo getVoFormulario() {
        return voFormulario;
    }

    public void registrarEmissao() {
        getService().registrarEmissaoDeDocumento(TipoDocumento.AVISO_SAIDA, agenciamento, false);
    }

    public void carregarVO() {
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());

        voFormulario.setDuv(agenciamento.getNumeroDUV());
        String portoCidadeSaida = agenciamento.getPorto().getNomeCompleto();
        String portoSaida = agenciamento.getPorto().getNomeCompleto();

        agenciamento.getPorto().setComplementos(new HashSet<PortoComplemento>(getService().buscarPortosComplementosPorPorto(agenciamento.getPorto())));
        if (agenciamento.getPorto().getComplemento() != null) {
            portoSaida = agenciamento.getPorto().getComplemento().getEstado();
        }

        voFormulario.setPortoSaida(portoCidadeSaida + "-" + portoSaida);
        voFormulario.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        voFormulario.setImo(agenciamento.getEmbarcacao().getImo());
        voFormulario.setNumeroInscricao(agenciamento.getEmbarcacao().getInscricao());

//        String portoDestino = "A confirmar";
//        if (!agenciamento.getDestinoFormatado().isEmpty()) {
//            agenciamento.getPortoDestino().setComplementos(new HashSet<PortoComplemento>(getService().buscarPortosComplementosPorPorto(agenciamento.getPorto())));
//            if (agenciamento.getPortoDestino().getComplemento() != null) {
//                portoDestino = String.format("%s-%s", agenciamento.getDestinoFormatado(), agenciamento.getPortoDestino().getComplemento().getEstado());
//            }
//        }
        
        if (agenciamento.getPortoDestino() != null) {
            String portoDestino = agenciamento.getDestinoFormatado();
            String estadoPortoDestino = agenciamento.getDestinoFormatado();
            
            agenciamento.getPortoDestino().setComplementos(new HashSet<PortoComplemento>(getService().buscarPortosComplementosPorPorto(agenciamento.getPortoDestino())));
            if (agenciamento.getPortoDestino().getComplementos() != null && !agenciamento.getPortoDestino().getComplementos().isEmpty()) {
                estadoPortoDestino = agenciamento.getPortoDestino().getComplemento().getEstado();
                estadoPortoDestino = "-" + estadoPortoDestino; 
            }else{
                estadoPortoDestino = "";
            }
            
            String cidadeEstadoPortoDestino= portoDestino.concat(estadoPortoDestino);
            voFormulario.setDestino(cidadeEstadoPortoDestino);
        }else{ 
            voFormulario.setDestino("A confirmar");             
        }
        
        
        
        

//        voFormulario.setDestino(portoDestino);

        String caldadoAVante = agenciamento.getCaladoSaidaVante() != null ? String.format("%.2f", agenciamento.getCaladoSaidaVante()) : null;
        voFormulario.setCaladoAVante(caldadoAVante);

        String caladoARe = agenciamento.getCaladoSaidaRe() != null ? String.format("%.2f", agenciamento.getCaladoSaidaRe()) : null;
        voFormulario.setCaladoARe(caladoARe);

        voFormulario.setMunicipio(agenciamento.getAgencia().getCidade().trim() + ",");

        String data_documento = dataDocumento != null ? SistamDateUtils.formatDateByExtensive(dataDocumento, null) : SistamDateUtils.formatDateByExtensive(new Date(), zone);
        voFormulario.setDataAssinatura(data_documento);

        voFormulario.setNumeroProcessoDespacho(agenciamento.getNumeroProcessoDespacho());
    }
}
