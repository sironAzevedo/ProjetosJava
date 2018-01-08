package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.ParteDeEntradaCapitaniaVo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ParteEntradaModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private ParteDeEntradaCapitaniaVo voFormulario;
    private RepresentanteLegal representanteLegalSelecionado;   
    private List<TipoSimNao> listaSimNao;
    private TipoSimNao deficienciasTratadasPorto;     
    private TipoSimNao transportaCargaConves;     
    private TipoSimNao transportaCargaPerigosa;     
    private TipoSimNao navioMaior18Anos;  
    private String localizacaoPorto;
    private Date dataChegada;

    public ParteEntradaModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        voFormulario = new ParteDeEntradaCapitaniaVo();
        
        listaSimNao = Collections.unmodifiableList(new ArrayList<TipoSimNao> (Arrays.asList(TipoSimNao.values())));
        
        deficienciasTratadasPorto = TipoSimNao.NAO;
        transportaCargaConves = TipoSimNao.NAO;
        transportaCargaPerigosa = TipoSimNao.NAO;
        navioMaior18Anos = TipoSimNao.NAO;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public ParteDeEntradaCapitaniaVo getVoFormulario() {
        return voFormulario;
    }
    
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public List<TipoSimNao> getListaSimNao() {
        return listaSimNao;
    }
    
    public TipoSimNao getDeficienciasTratadasPorto() {
        return deficienciasTratadasPorto;
    }
    
    public void setDeficienciasTratadasPorto(TipoSimNao deficienciasTratadasPorto) {
        this.deficienciasTratadasPorto = deficienciasTratadasPorto;
        firePropertyChange("deficienciasTratadasPorto", null, this.deficienciasTratadasPorto);
    }
    
    public TipoSimNao getTransportaCargaConves() {
        return transportaCargaConves;
    }
    
    public void setTransportaCargaConves(TipoSimNao transportaCargaConves) {
        this.transportaCargaConves = transportaCargaConves;
        firePropertyChange("transportaCargaConves", null, this.transportaCargaConves);
    }
    
    public TipoSimNao getTransportaCargaPerigosa() {
        return transportaCargaPerigosa;
    }
    
    public void setTransportaCargaPerigosa(TipoSimNao transportaCargaPerigosa) {
        this.transportaCargaPerigosa = transportaCargaPerigosa;
        firePropertyChange("transportaCargaPerigosa", null, this.transportaCargaPerigosa);
    }
    
    public TipoSimNao getNavioMaior18Anos() {
        return navioMaior18Anos;
    }
    
    public void setNavioMaior18Anos(TipoSimNao navioMaior18Anos) {
        this.navioMaior18Anos = navioMaior18Anos;
        firePropertyChange("navioMaior18Anos", null, this.navioMaior18Anos);
    }
    
  
    public RepresentanteLegal getRepresentanteLegalSelecionado() {
        return representanteLegalSelecionado;
    }
    
    public void setRepresentanteLegalSelecionado(RepresentanteLegal representanteLegalSelecionado) {
        this.representanteLegalSelecionado = representanteLegalSelecionado;
        firePropertyChange("representanteLegalSelecionado", null, this.representanteLegalSelecionado);
    }

    public String getLocalizacaoPorto() {
        return localizacaoPorto;
    }

    public void setLocalizacaoPorto(String localizacaoPorto) {
        this.localizacaoPorto = localizacaoPorto;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        Object old = this.dataChegada;
        this.dataChegada = dataChegada;
        firePropertyChange("dataChegada", old, this.dataChegada);
    }
    
    //</editor-fold>
    
    
    
    public void validarParteEntrada() {
        
        SistamPendencyManager pm = new SistamPendencyManager();
            pm.assertNotNull(agenciamento.getEmbarcacao().getBandeira()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_BANDEIRA_OBRIGATORIO);
            pm.assertNotNull(representanteLegalSelecionado).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.INFORME_O_REPRESENTANTE);
            pm.assertNotEmpty(localizacaoPorto).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.COMUNICACAO_CAPITANIA_PARTE_ENTRADA_INFORME_LOCALIZACAO_PORTO);
            pm.verifyAll();       
    }
    
    public void carregarVO() {
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());
        
        
        
        voFormulario.setDuv(agenciamento.getNumeroDUV());
        voFormulario.setPortoChegada(agenciamento.getPorto().getNomeCompleto());
        voFormulario.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        voFormulario.setIrin(agenciamento.getEmbarcacao().getIrin());
        voFormulario.setBandeira(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto());
        
        if (agenciamento.getEmbarcacao().getDataConstrucao() != null){
            voFormulario.setAnoConstrucao(SistamDateUtils.getYearDate(agenciamento.getEmbarcacao().getDataConstrucao(), zone).toString());
        }
        
        voFormulario.setArqueacaoBruta(agenciamento.getEmbarcacao().getArqueacaoBruta());
        voFormulario.setImo(agenciamento.getEmbarcacao().getImo());
        voFormulario.setNumeroInscricao(agenciamento.getEmbarcacao().getInscricao());
        voFormulario.setPortoOrigem(agenciamento.getPortoOrigem().getNomeCompleto());
        voFormulario.setDataHoraEstimadaSaida(SistamDateUtils.formatDateComplete(agenciamento.getDataEstimadaSaida(), zone));
        voFormulario.setTipoEmbarcacao(agenciamento.getEmbarcacao().getClassificacao().getPorExtenso());
        
        voFormulario.setTonelagemPortoBruto(agenciamento.getEmbarcacao().getDwt());
        
        if (agenciamento.getAreaNavegacao() != null) {
            voFormulario.setAreaNavegacaoEmbarcacao(agenciamento.getAreaNavegacao().getPorExtenso());
        } 
        
        voFormulario.setPossuiHeliporto(agenciamento.getEmbarcacao().getHeliporto());
        
        String destinoFomratado = ( !agenciamento.getDestinoFormatado().isEmpty()) ? agenciamento.getDestinoFormatado() : "A confirmar";
        voFormulario.setProximoDestino(destinoFomratado);
        
        voFormulario.setLocalizacaoNoPorto(localizacaoPorto.toString().trim());
        voFormulario.setInformacoesAgente(agenciamento.getAgencia().getNomeEnderecoEmailTelefoneAgente());
        voFormulario.setDataUltimaInspecao(SistamDateUtils.formatShortDate(agenciamento.getEmbarcacao().getDataUltimaInspecao(), zone));
        
        if (agenciamento.getEmbarcacao().getPaisInspecao() != null) {
            voFormulario.setPaisInspecao(agenciamento.getEmbarcacao().getPaisInspecao().getNomeCompleto());
        }
        
        voFormulario.setDataValidadeDeclaracaoConformidade(SistamDateUtils.formatShortDate(agenciamento.getEmbarcacao().getDataValidadeDeclaracaoConformidade(), zone));
        voFormulario.setDataValidadeDeclaracaoProvisoria(SistamDateUtils.formatShortDate(agenciamento.getEmbarcacao().getDataValidadeDeclaracaoProvisoria(), zone));
        voFormulario.setDeficienciaNestePorto(deficienciasTratadasPorto.getId());
        voFormulario.setCargaConves(transportaCargaConves.getId());        
        voFormulario.setCargaPerigosa(transportaCargaPerigosa.getId());
        voFormulario.setNavioGraneleiroOuCombinado(navioMaior18Anos.getId());
        voFormulario.setMunicipio(agenciamento.getAgencia().getCidade().trim() + ",");
        
        String data_documento = SistamDateUtils.formatDateByExtensive(voFormulario.getDataDocumento(), null);        
        String data_assinatura = SistamDateUtils.formatDateByExtensive(new Date(), zone);
        
        voFormulario.setDataAssinatura(!"".equals(data_documento) ? data_documento : data_assinatura);
        
        voFormulario.setNumeroProcessoDespacho(agenciamento.getNumeroProcessoDespacho());
        voFormulario.setDataHoraChegada(SistamDateUtils.formatDate(dataChegada, "dd/MM/yyyy HH:mm", zone));
        
    }
    
    public void registrarEmissao(){
        getService().registrarEmissaoDeDocumento(TipoDocumento.PARTE_ENTRADA, agenciamento, representanteLegalSelecionado, false);
    }
    
    

}
