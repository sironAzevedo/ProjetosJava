package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.enums.MotivoDesvio;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.RegistroDeAlteracaoDestinoCapitaniaVo;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class EmitirRegistroAlteracaoDestinoModel extends BasePresentationModel<SistamService> {
    
    private Agenciamento agenciamento;
    private AgenciaOrgaoDespacho agenciaOrgaoSelecionado;
    private RegistroDeAlteracaoDestinoCapitaniaVo voFormulario;
    
    public EmitirRegistroAlteracaoDestinoModel(Agenciamento agenciamento) {
        this.agenciamento = getService().buscarAgenciamentoParaDesvioDeRota(agenciamento.getId());
        this.voFormulario = new RegistroDeAlteracaoDestinoCapitaniaVo();
    }
            
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public RegistroDeAlteracaoDestinoCapitaniaVo getVoFormulario() {
        return voFormulario;
    }
    
    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, this.agenciamento);
    }
    
    public AgenciaOrgaoDespacho getAgenciaOrgaoSelecionado() {
        return agenciaOrgaoSelecionado;
    }
    
    public void setAgenciaOrgaoSelecionado(AgenciaOrgaoDespacho agenciaOrgaoSelecionado) {
        this.agenciaOrgaoSelecionado = agenciaOrgaoSelecionado;
        firePropertyChange("agenciaOrgaoSelecionado", null, this.agenciaOrgaoSelecionado);
    }

    /**
     * Carrega apenas as manobras registradas, desconsiderando as abortadas.
     */
    
    public void validarRegistroAlteracaoDestino(){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotEmpty(voFormulario.getAoTo()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_AO_TO);
        pm.assertNotNull(getAgenciaOrgaoSelecionado()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.INFORME_O_ORGAO_DESPACHO);
        pm.verifyAll();
    } 

    public void carregarVO() {
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());
        
        //o campo aoTo do vo está com bind com o formulário;
        
        voFormulario.setDuv(agenciamento.getNumeroDUV());
        voFormulario.setDoFrom(agenciamento.getAgencia().getNomeCompleto());
        voFormulario.setOrgaoDespacho(agenciaOrgaoSelecionado.getNome());
        voFormulario.setImo(agenciamento.getEmbarcacao().getImo());
        voFormulario.setNumeroInscricao(agenciamento.getEmbarcacao().getInscricao());
        voFormulario.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        voFormulario.setInformacoesAgente(agenciamento.getAgencia().getNomeEnderecoEmailTelefoneAgente());
        voFormulario.setPortoDestinoEfetivo( agenciamento.getDestinoFormatado());
        voFormulario.setPortoDestinoAlterado(agenciamento.getDesvio().getDestinoAlteradoFormatado()) ;
        voFormulario.setReabastecimento(MotivoDesvio.REABASTECIMENTO.equals(agenciamento.getDesvio().getMotivo()));
        voFormulario.setCarregamentoOuDescarregamento(MotivoDesvio.CARREGAMENTO_DESCARREGAMENTO_POR_INTERESSES_COMERCIAIS.equals(agenciamento.getDesvio().getMotivo()));
        voFormulario.setServicoMedicoHospitalar(MotivoDesvio.PRESTAR_SERVICOS_MEDICOS_HOSPITALARES_A_ENFERMO.equals(agenciamento.getDesvio().getMotivo()));
        voFormulario.setDesembarcarCorpo(MotivoDesvio.DESEMBARCAR_CORPO_DE_TRIPULANTE_OU_PASSAGEIRO.equals(agenciamento.getDesvio().getMotivo()));
        voFormulario.setSolicitacaoDeAbrigo(MotivoDesvio.SOLICITACAO_DE_ABRIGO_EM_FUNCAO_DE_MAU_TEMPO.equals(agenciamento.getDesvio().getMotivo()));
        voFormulario.setEmbarcacaoAvariada(MotivoDesvio.EMBARCACAO_AVARIADA.equals(agenciamento.getDesvio().getMotivo()));
        voFormulario.setMunicipio(agenciamento.getAgencia().getCidade().trim() + ",");
        voFormulario.setDataAssinatura(SistamDateUtils.formatDateByExtensive(new Date(), zone));
        voFormulario.setNumeroProcessoDespacho(agenciamento.getNumeroProcessoDespacho());
    }
    
    public void registrarEmissao(Boolean criarNovo){
        getService().registrarEmissaoDeDocumento(TipoDocumento.REGISTRO_ALTERACAO_DESTINO, agenciamento, criarNovo);
    }
    
    public boolean existeDocumento() {
        List<Documento> docs = getService().buscarDocumentoDoAgenciamentoPorTipo(TipoDocumento.REGISTRO_ALTERACAO_DESTINO, agenciamento);
        return docs != null && !docs.isEmpty() ? true : false;
    }

}
