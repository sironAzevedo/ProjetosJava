package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.RegistroDeMovimentacaoCapitaniaVo;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class EmitirRegistroMovimentacaoModel extends BasePresentationModel<SistamService> {
    
    private Agenciamento agenciamento;
    private RepresentanteLegal representanteLegalSelecionado;   
    private Manobra manobraSelecionada;    
    private AgenciaOrgaoDespacho agenciaOrgaoSelecionado;
    private RegistroDeMovimentacaoCapitaniaVo voFormulario;
    private List<Manobra> listaManobrasRegistradas = new SerializableObservableList<Manobra>();
    
    public EmitirRegistroMovimentacaoModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        voFormulario = new RegistroDeMovimentacaoCapitaniaVo();
        carregarManobrasRegistradas();
    }
            
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public RegistroDeMovimentacaoCapitaniaVo getVoFormulario() {
        return voFormulario;
    }
    
    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, this.agenciamento);
    }
    
    public Manobra getManobraSelecionada() {
        return manobraSelecionada;
    }
    
    public void setManobraSelecionada(Manobra manobraSelecionada) {
        this.manobraSelecionada = manobraSelecionada;
        firePropertyChange("manobraSelecionada", null, this.manobraSelecionada);
    }
    
    public AgenciaOrgaoDespacho getAgenciaOrgaoSelecionado() {
        return agenciaOrgaoSelecionado;
    }
    
    public void setAgenciaOrgaoSelecionado(AgenciaOrgaoDespacho agenciaOrgaoSelecionado) {
        this.agenciaOrgaoSelecionado = agenciaOrgaoSelecionado;
        firePropertyChange("agenciaOrgaoSelecionado", null, this.agenciaOrgaoSelecionado);
    }
    
    public RepresentanteLegal getRepresentanteLegalSelecionado() {
        return representanteLegalSelecionado;
    }
    
    public void setRepresentanteLegalSelecionado(RepresentanteLegal representanteLegalSelecionado) {
        this.representanteLegalSelecionado = representanteLegalSelecionado;
        firePropertyChange("representanteLegalSelecionado", null, this.representanteLegalSelecionado);
    }

    public List<Manobra> getListaManobrasRegistradas() {
        return listaManobrasRegistradas;
    }
    
    //</editor-fold>
    
    /**
     * Carrega apenas as manobras registradas, desconsiderando as abortadas.
     */
    private void carregarManobrasRegistradas(){
        listaManobrasRegistradas.clear();
        
        for (Manobra manobra : agenciamento.getManobras()){
            if (StatusManobra.ENCERRADA.equals(manobra.getStatus())){
                listaManobrasRegistradas.add(manobra);
            }
        }
    }
    
    public void validarRegistroMovimentacao(){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(getRepresentanteLegalSelecionado()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.INFORME_O_REPRESENTANTE);
        pm.assertNotNull(getAgenciaOrgaoSelecionado()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.INFORME_O_ORGAO_DESPACHO);
        pm.assertNotNull(getManobraSelecionada()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.COMUNICACAO_CAPITANIA_REGISTRO_MOVIMENTACAO_SELECIONE_A_MANOBRA);
        pm.verifyAll();
    } 
    
    public void carregarVO() {
        
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());
        
        voFormulario.setDuv(agenciamento.getNumeroDUV());
        voFormulario.setOrgaoDespacho(getAgenciaOrgaoSelecionado().getNome());
        voFormulario.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        voFormulario.setIrin(agenciamento.getEmbarcacao().getIrin());
        voFormulario.setBandeira(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto());
        voFormulario.setImo(agenciamento.getEmbarcacao().getImo());
        voFormulario.setNumeroInscricao(agenciamento.getEmbarcacao().getInscricao());
        voFormulario.setTipoEmbarcacao(agenciamento.getEmbarcacao().getClassificacao().getPorExtenso());
        voFormulario.setArqueacaoBruta(agenciamento.getEmbarcacao().getArqueacaoBruta());
        
        
        if (agenciamento.getAreaNavegacao() != null) {
            voFormulario.setAreaNavegacaoEmbarcacao(agenciamento.getAreaNavegacao().getPorExtenso());
        } 
        voFormulario.setMotivoMovimentacao(manobraSelecionada.getObservacaoInterna());
        voFormulario.setInformacoesAgente(agenciamento.getAgencia().getNomeEnderecoEmailTelefoneAgente());
       
        for (ServicoManobra servico : manobraSelecionada.getServicos()){
            if (TipoServico.PRATICOS.equals(servico.getTipoDoServico())){
                voFormulario.setManobraComPratico(true);
                break;
            }
        }
        voFormulario.setLocalizacaoAtual(manobraSelecionada.getPontoAtracacaoDestino().getNome());
        voFormulario.setDataChegada(SistamDateUtils.formatDate(manobraSelecionada.getDataChegadaDestino(),"dd/MM/yy", zone));
        voFormulario.setHoraChegada(SistamDateUtils.formatDate(manobraSelecionada.getDataChegadaDestino() , "HH:mm", zone));
        voFormulario.setLocalizacaoAnterior(manobraSelecionada.getPontoAtracacaoOrigem().getNome());
        voFormulario.setDataPartida(SistamDateUtils.formatDate(manobraSelecionada.getDataPartidaOrigem(),"dd/MM/yy", zone));
        voFormulario.setHoraPartida(SistamDateUtils.formatDate(manobraSelecionada.getDataPartidaOrigem(), "HH:mm", zone));        
        voFormulario.setMunicipio(agenciamento.getAgencia().getCidade().trim() + ",");
        voFormulario.setDataAssinatura(SistamDateUtils.formatDateByExtensive(new Date(), zone));
        voFormulario.setNumeroProcessoDespacho(agenciamento.getNumeroProcessoDespacho());
    }
    
    public void registrarEmissao(){
        //Atualiza o agenciamento da manobra selecionada com o mesmo agenciamento, 
        //só que com as propriedades carregadas, para evitar erro de lazy.
        manobraSelecionada.setAgenciamento(agenciamento);
        getService().registrarEmissaoDeDocumentoDaManobra(TipoDocumento.REGISTRO_MOVIMENTACAO, manobraSelecionada, representanteLegalSelecionado);
    }

}
