package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.PedidoDespachoProximoPortoCapitaniaVo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class PedidoDespachoModel extends BasePresentationModel<SistamService> {
    
    private Agenciamento agenciamento;
    private PedidoDespachoProximoPortoCapitaniaVo voFormulario;
    private List<String> despachoRevalidacao = new ArrayList<String>(Arrays.asList("DESPACHO", "REVALIDAÇÃO"));
    private String despachoRevalidacaoSelecionado = despachoRevalidacao.get(0);
    private TipoSimNao ait;
    private TipoSimNao cargaConves;
    private TipoSimNao cargaPerigosa;
    
    public PedidoDespachoModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        this.voFormulario = new PedidoDespachoProximoPortoCapitaniaVo();
        
        this.ait = TipoSimNao.NAO;
        this.cargaConves = TipoSimNao.NAO;
        this.cargaPerigosa = TipoSimNao.NAO;
        
    }
            
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public PedidoDespachoProximoPortoCapitaniaVo getVoFormulario() {
        return voFormulario;
    }
    
    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, this.agenciamento);
    }

    public List<String> getDespachoRevalidacao() {
        return despachoRevalidacao;
    }

    public String getDespachoRevalidacaoSelecionado() {
        return despachoRevalidacaoSelecionado;
    }

    public void setDespachoRevalidacaoSelecionado(String despachoRevalidacaoSelecionado) {
        this.despachoRevalidacaoSelecionado = despachoRevalidacaoSelecionado;
        firePropertyChange("despachoRevalidacaoSelecionado", null, this.despachoRevalidacaoSelecionado);
    }
    
    public List<TipoSimNao> getListaSimNao() {
        return TipoSimNao.listValues();
    }

    public TipoSimNao getAit() {
        return ait;
    }

    public void setAit(TipoSimNao ait) {
        this.ait = ait;
        firePropertyChange("ait", null, this.ait);
    }

    public TipoSimNao getCargaConves() {
        return cargaConves;
    }

    public void setCargaConves(TipoSimNao cargaConves) {
        this.cargaConves = cargaConves;
        firePropertyChange("cargaConves", null, this.cargaConves);
    }

    public TipoSimNao getCargaPerigosa() {
        return cargaPerigosa;
    }

    public void setCargaPerigosa(TipoSimNao cargaPerigosa) {
        this.cargaPerigosa = cargaPerigosa;
        firePropertyChange("cargaPerigosa", null, this.cargaPerigosa);
    }
    
    public void validar(){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotEmpty(voFormulario.getAoTo()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_AO_TO);
        pm.assertThat(!despachoRevalidacaoSelecionado.equals("REVALIDAÇÃO") || (voFormulario.getObservacao() != null && !voFormulario.getObservacao().isEmpty())).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_OBSERVACAO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getClassificacao()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.EMBARCACAO_CLASSIFICACAO_OBRIGATORIA);
        pm.verifyAll();
    } 
    
    public void registrarEmissao() {
        getService().registrarEmissaoDeDocumento(TipoDocumento.PEDIDO_DESPACHO_PROXIMO_PORTO, agenciamento, false);
    }

    public void carregarVO() {
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());
        
        voFormulario.setNumeroProcessoDespacho(agenciamento.getNumeroProcessoDespacho());
        voFormulario.setDuv(agenciamento.getNumeroDUV());
        voFormulario.setPortoDestino(agenciamento.getDestinoFormatado());
        voFormulario.setDoFrom(Agencia.PETROBRAS);
        voFormulario.setDespacho(despachoRevalidacaoSelecionado.equals("DESPACHO"));
        voFormulario.setRevalidacao(despachoRevalidacaoSelecionado.equals("REVALIDAÇÃO"));
        voFormulario.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        voFormulario.setIrin(agenciamento.getEmbarcacao().getIrin());
        voFormulario.setBandeira(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto());
        voFormulario.setTipoEmbarcacao(agenciamento.getEmbarcacao().getClassificacao().getPorExtenso());
        voFormulario.setAreaNavegacaoEmbarcacao(agenciamento.getAreaNavegacaoSaida().getPorExtenso());
        voFormulario.setImo(agenciamento.getEmbarcacao().getImo());
        voFormulario.setNumeroInscricao(agenciamento.getEmbarcacao().getInscricao());
        voFormulario.setArqueacaoBruta(agenciamento.getEmbarcacao().getArqueacaoBruta());
        voFormulario.setTonelagemPortoBruto(agenciamento.getEmbarcacao().getDwt());

        if (agenciamento.getEmbarcacao().getDataConstrucao() != null){
            voFormulario.setAnoConstrucao(SistamDateUtils.getYearDate(agenciamento.getEmbarcacao().getDataConstrucao(), null).toString());
        }
        
        voFormulario.setDataEstimadaSaida(SistamDateUtils.formatDateComplete(agenciamento.getDataEstimadaSaida(), zone));
        voFormulario.setAit(ait.getId());
        voFormulario.setArmador(agenciamento.getEmbarcacao().getArmador());
        voFormulario.setInformacoesAgente(agenciamento.getAgencia().getNomeEnderecoEmailTelefoneAgente());
        voFormulario.setCargaConves(cargaConves.getId());
        voFormulario.setCargaPerigosa(cargaPerigosa.getId());
        voFormulario.setMunicipio(agenciamento.getAgencia().getCidade().trim() + ",");
        
        String data_documento = SistamDateUtils.formatDateByExtensive(voFormulario.getDataDocumento(), null);        
        String data_assinatura = SistamDateUtils.formatDateByExtensive(new Date(), zone);
        
        voFormulario.setDataAssinatura(!"".equals(data_documento) ? data_documento : data_assinatura);
    }

}
