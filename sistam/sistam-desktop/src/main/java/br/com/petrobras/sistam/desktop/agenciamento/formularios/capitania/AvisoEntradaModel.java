package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroAvisoEntradaVo;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;

public class AvisoEntradaModel extends BasePresentationModel<SistamService> {

    private Agenciamento agenciamento;
    private FiltroAvisoEntradaVo voFormulario = new FiltroAvisoEntradaVo();
    private List<TipoSimNao> listaSimNao; 
    private Date dataValidade;
    private String localizacaoPorto;
    private Date dataDocumento;

    public AvisoEntradaModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        listaSimNao = Collections.unmodifiableList(new ArrayList<TipoSimNao> (Arrays.asList(TipoSimNao.values())));
        voFormulario.setDeficienciasNestePorto(TipoSimNao.NAO);
        voFormulario.setCargaNoConves(TipoSimNao.NAO);
        voFormulario.setCargaPerigosa(TipoSimNao.NAO);
        voFormulario.setNavioGraneiroOuCombinado(TipoSimNao.NAO);
    }
    
     //<editor-fold defaultstate="collapsed" desc="Getters e Setters">

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

    public FiltroAvisoEntradaVo getVoFormulario() {
        return voFormulario;
    }
    
    public List<TipoSimNao> getListaSimNao() {
        return listaSimNao;
    }
    
    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) { 
        Object old = this.dataValidade;
        this.dataValidade = dataValidade;
        firePropertyChange("dataValidade", old, this.dataValidade);
    }
    
    public String getLocalizacaoPorto() {
        return localizacaoPorto;
    }

    public void setLocalizacaoPorto(String localizacaoPorto) {
        this.localizacaoPorto = localizacaoPorto;
    }
        
     //</editor-fold>

    public void registrarEmissao() {
        getService().registrarEmissaoDeDocumento(TipoDocumento.AVISO_ENTRADA, agenciamento, false);
    }
    
    public void validarAvisoEntrada() {
        
        SistamPendencyManager pm = new SistamPendencyManager(); 
            pm.assertNotEmpty(localizacaoPorto).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.COMUNICACAO_CAPITANIA_PARTE_ENTRADA_INFORME_LOCALIZACAO_PORTO); 
            pm.verifyAll();       
    }

    public void carregarVO() { 
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());
        
        voFormulario.setNumeroProcessoDespacho(agenciamento.getNumeroProcessoDespacho());
        voFormulario.setDuv(agenciamento.getNumeroDUV() != null ? agenciamento.getNumeroDUV() : " - "); 
        
        String portoAgenciamento = agenciamento.getPorto().getNomeCompleto();
        String portoEstadoChegada = agenciamento.getAgencia().getEstado();
        
        agenciamento.getPorto().setComplementos(new HashSet<PortoComplemento>(getService().buscarPortosComplementosPorPorto(agenciamento.getPorto())));
        if (agenciamento.getPorto().getComplemento() != null) {
            portoEstadoChegada = agenciamento.getPorto().getComplemento().getEstado();
        }
       
        String cidadeEstadoPortoChegada= portoAgenciamento + "-" + portoEstadoChegada;  
        voFormulario.setPortoChegada(cidadeEstadoPortoChegada);
       
        

        //DADOS EMBARCAÇÃO         
        voFormulario.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto()!= null ? agenciamento.getEmbarcacao().getNomeCompleto(): " - ");
        voFormulario.setIrin(agenciamento.getEmbarcacao().getIrin() != null ? agenciamento.getEmbarcacao().getIrin() : " - ");
        voFormulario.setBandeiraEmbarcacao(agenciamento.getEmbarcacao().getBandeira() != null ? agenciamento.getEmbarcacao().getBandeira().getNomeCompleto() : " - ");
        voFormulario.setImo(agenciamento.getEmbarcacao().getImo() != null ? agenciamento.getEmbarcacao().getImo() : " - ");
        voFormulario.setNumeroInscricao(agenciamento.getEmbarcacao().getInscricao() != null ? agenciamento.getEmbarcacao().getInscricao() : " - ");
        voFormulario.setAreaNavegacao(agenciamento.getAreaNavegacao()!= null ? agenciamento.getAreaNavegacao().getPorExtenso() : " - ");
        
        NumberFormat format = NumberFormat.getInstance();
        
        
        String arqueacaoBruta = format.format(agenciamento.getEmbarcacao().getArqueacaoBruta()); 
        voFormulario.setArqueacaoBrutaGrt(arqueacaoBruta != null ? arqueacaoBruta + " TM" :" - " );
        voFormulario.setHeliporto(agenciamento.getEmbarcacao().getHeliporto());
        
        if (agenciamento.getPortoOrigem() != null) {
            String portoOrigem = agenciamento.getPortoOrigem().getNomeCompleto();
            String estadoPortoOrigem = agenciamento.getPortoOrigem().getNomeCompleto();

            agenciamento.getPortoOrigem().setComplementos(new HashSet<PortoComplemento>(getService().buscarPortosComplementosPorPorto(agenciamento.getPortoOrigem())));
            if (!agenciamento.getPortoOrigem().getComplementos().isEmpty()) {
                estadoPortoOrigem = agenciamento.getPortoOrigem().getComplemento().getEstado();
                estadoPortoOrigem = "-" + estadoPortoOrigem; 
            } else{
                estadoPortoOrigem = "";
            }

            String cidadeEstadoPortoOrigem = portoOrigem.concat(estadoPortoOrigem);
            voFormulario.setPortoOrigem(cidadeEstadoPortoOrigem);
        }else{
            voFormulario.setPortoOrigem("A confirmar");
        } 
        
        String dataHoraChegada = SistamDateUtils.formatDate(agenciamento.getDataChegada(), SistamDateUtils.DATE_HOUR_PATTERN, zone);
        String dataHoraEstimadaSaida = SistamDateUtils.formatDate(agenciamento.getDataEstimadaSaida(), SistamDateUtils.DATE_HOUR_PATTERN, zone);
        
        voFormulario.setDataHoraChegda(dataHoraChegada != null ? dataHoraChegada : "");
        voFormulario.setDataHoraEstimadaSaida(dataHoraEstimadaSaida != null ? dataHoraEstimadaSaida : "");
        
        
        
        if (agenciamento.getPortoDestino() != null) {
            String portoDestino = agenciamento.getDestinoFormatado();
            String estadoPortoDestino = agenciamento.getDestinoFormatado();
            
            agenciamento.getPortoDestino().setComplementos(new HashSet<PortoComplemento>(getService().buscarPortosComplementosPorPorto(agenciamento.getPortoDestino())));
            if (!agenciamento.getPortoDestino().getComplementos().isEmpty()) {
                estadoPortoDestino = agenciamento.getPortoDestino().getComplemento().getEstado();
            }
            String cidadeEstadoPortoDestino= portoDestino + "-" + estadoPortoDestino;
            voFormulario.setProximoDestino(cidadeEstadoPortoDestino);
        }else{ 
            voFormulario.setProximoDestino("A confirmar");             
        }
        
        voFormulario.setLocalizaçãoNoPorto(localizacaoPorto != null ? localizacaoPorto.toString().trim() : ""); 
        String representanteLegalNome = agenciamento.getAgencia().getNomeCompleto();
        String representanteLegalEndereco = agenciamento.getAgencia().getEndereco();
        String representanteLegalEmail = agenciamento.getAgencia().getEmail();
        String representanteLegalTelefone = agenciamento.getAgencia().getTelefone();
        voFormulario.setRepresentanteLegalEmbarcacao(representanteLegalNome + "/ " + representanteLegalEndereco + "/ Tel: " + representanteLegalTelefone);  
        voFormulario.setRepresentanteLegalEmbarcacaoEmail(representanteLegalEmail);
        voFormulario.setDataUltimaInspecao(SistamDateUtils.formatShortDate(agenciamento.getEmbarcacao().getDataUltimaInspecao(),null));
        
        if (agenciamento.getEmbarcacao().getPaisInspecao() != null) {
            String paisInspecao = agenciamento.getEmbarcacao().getPaisInspecao().getNomeCompleto();
            voFormulario.setPaisDeInspecao(paisInspecao != null ? paisInspecao : "");
        }
        
        voFormulario.setDeclaracaoConformidade(SistamDateUtils.formatShortDate(agenciamento.getEmbarcacao().getDataValidadeDeclaracaoConformidade(), null));
        
        String declaracao = SistamDateUtils.formatShortDate(agenciamento.getEmbarcacao().getDataValidadeDeclaracaoProvisoria(), zone);
        voFormulario.setDeclaracaoProvisoria(declaracao != null ? declaracao : "____ / _____ / _____"); 
        
        String dataFormulario = dataDocumento != null ? SistamDateUtils.formatDateByExtensive(dataDocumento, zone) : SistamDateUtils.formatDateByExtensive(new Date(), zone);
        voFormulario.setDataDocumento(dataFormulario);
    }
}
