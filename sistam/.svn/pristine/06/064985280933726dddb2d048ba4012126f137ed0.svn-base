package br.com.petrobras.sistam.desktop.agenciamento.operacoes;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.enums.AreaNavegacao;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.Petrobras;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.CTACProdutoVo;
import br.com.petrobras.sistam.common.valueobjects.CTACReceitaVo;
import br.com.petrobras.snarf.common.util.SerializableObservableList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DocumentacaoCargaCabotagemModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    
    private List<DocumentacaoCabotagem> listaDocumentacao = new SerializableObservableList<DocumentacaoCabotagem>();
    private DocumentacaoCabotagem documentacaoSelecionada;
    
    private List<DocumentacaoOperacao> listaDocumentacaoProduto = new SerializableObservableList<DocumentacaoOperacao>();
    private List<DocumentacaoOperacao> listaDocumentacaoProdutoSelecionada = new SerializableObservableList<DocumentacaoOperacao>();
    
    private CTACReceitaVo ctacReceitaVO;
    
    public DocumentacaoCargaCabotagemModel(Agenciamento agenciamento){
        this.agenciamento = agenciamento;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public CTACReceitaVo getVoFormulario() {
        return ctacReceitaVO;
    }
   
    
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public List<DocumentacaoCabotagem> getListaDocumentacao() {
        return listaDocumentacao;
    }

    public List<DocumentacaoOperacao> getListaDocumentacaoProduto() {
        return listaDocumentacaoProduto;
    }
    
    public DocumentacaoCabotagem getDocumentacaoSelecionada() {
        return documentacaoSelecionada;
    }
    
    public void setDocumentacaoSelecionada(DocumentacaoCabotagem documentacaoSelecionada) {
        this.documentacaoSelecionada = documentacaoSelecionada;
        firePropertyChange("documentacaoSelecionada", null, null);
        carregarListaDeDocuemtancaoProduto();
    }

    public List<DocumentacaoOperacao> getListaDocumentacaoProdutoSelecionada() {
        return listaDocumentacaoProdutoSelecionada;
    }

    public void setListaDocumentacaoProdutoSelecionada(List<DocumentacaoOperacao> listaDocumentacaoProdutoSelecionada) {
        this.listaDocumentacaoProdutoSelecionada = listaDocumentacaoProdutoSelecionada;
        firePropertyChange("listaDocumentacaoProdutoSelecionada", null, null);
    }
    
    //</editor-fold>
    
    public void excluirProduto() {
        DocumentacaoOperacao documentacaoOperacao = listaDocumentacaoProdutoSelecionada.get(0);
        getService().excluirDocumentacaoOperacao(getService().buscarDocumentacaoOperacaoPorId(documentacaoOperacao.getId()));
        documentacaoSelecionada.removerDocumentacaoProduto(documentacaoOperacao);
        listaDocumentacaoProduto.remove(documentacaoOperacao);
    }
    
    public void excluirDocumentacao(){
        getService().excluirDocumentacaoCabotagem(documentacaoSelecionada);
        listaDocumentacao.remove(documentacaoSelecionada);
    }
    
    public void carregarListaDeDocumentacao(){
        listaDocumentacao.addAll(getService().buscarDocumentacaoCabotagemPorAgenciamentoETipo(agenciamento, TipoOperacao.CARGA_CABOTAGEM));
    }
    
    public void carregarListaDeDocuemtancaoProduto(){
        listaDocumentacaoProduto.clear();
        
        if (documentacaoSelecionada != null){
            listaDocumentacaoProduto.addAll(documentacaoSelecionada.getDocumentacaoProdutos());
        }
    }
    
    public DocumentacaoCabotagem obterNovaDocumentacao(){
        DocumentacaoCabotagem documentacao = new DocumentacaoCabotagem();
        documentacao.setAgenciamento(agenciamento);
        documentacao.setTipoOperacao(TipoOperacao.CARGA_CABOTAGEM);
        return documentacao;
    }
    
    public DocumentacaoCabotagem obterDocumetacaoParaEdicao(){
        DocumentacaoCabotagem clone =  (DocumentacaoCabotagem)documentacaoSelecionada.clone();
        
        //atualiza o agenciamento para evitar erro de lazy.
        clone.setAgenciamento(agenciamento);
        return clone;
    }
    
    public void adicionarDocumentacao(DocumentacaoCabotagem documentacao){
        listaDocumentacao.add(documentacao);
    }
    
    public void atualizarDocumentacao(DocumentacaoCabotagem documentacao){
        listaDocumentacao.remove(documentacaoSelecionada);
        listaDocumentacao.add(documentacao);
    }
    
    public void adicionarProduto(DocumentacaoOperacao documentacaoProduto){
        documentacaoSelecionada.adicionarDocumentacaoProduto(documentacaoProduto);
        listaDocumentacaoProduto.add(documentacaoProduto);
    }
    
    public void atualizarProduto(DocumentacaoOperacao documentacaoProduto){
        documentacaoSelecionada.removerDocumentacaoProduto(documentacaoProduto);
        documentacaoSelecionada.adicionarDocumentacaoProduto(documentacaoProduto);
        int index = listaDocumentacaoProduto.indexOf(documentacaoProduto);
        listaDocumentacaoProduto.remove(documentacaoProduto);
        listaDocumentacaoProduto.add(index, documentacaoProduto);
    }
    
    public void validarEmissaoCTAC(){

        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getAreaNavegacaoSaida()).orRegister(TipoExcecao.INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_INFORME_AREA_NAVEGACAO_SAIDA);
        pm.assertNotNull(agenciamento.getEmbarcacao().getImo()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_IMO_OBRIGATORIO);
        pm.assertThat(!getService().buscarPortosComplementosPorPorto(documentacaoSelecionada.getPorto()).isEmpty()).orRegister(TipoExcecao.PORTO_COMPLEMENTO, ConstantesI18N.PORTO_DESEMBARQUE_COMPLEMENTO_OBRGIGATORIO, documentacaoSelecionada.getPorto().getNomeCompleto());

        for (DocumentacaoOperacao documentacaoOperacao : listaDocumentacaoProdutoSelecionada) {
            pm.assertNotEmpty(documentacaoOperacao.getCtac()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.DOCUMENTACAO_PRODUTO_INFORME_CTAC);
            pm.assertNotNull(documentacaoOperacao.getOperacao().getDataFim()).orRegister(TipoExcecao.OPERACAO, ConstantesI18N.OPERACAO_INFORME_DATA_FIM);
        }
            
        pm.verifyAll();       
    }
    
 
    public void carregarVO(){
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());
        
        ctacReceitaVO = new CTACReceitaVo();

        try {
            ctacReceitaVO.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto().toString());
            ctacReceitaVO.setImo(agenciamento.getEmbarcacao().getImo());
            ctacReceitaVO.setAreaNavegacaoEmbarcacao(agenciamento.getAreaNavegacaoSaida().getPorExtenso());
            ctacReceitaVO.setViagem(agenciamento.getVgm());

            //consignatario - petrobras
            ctacReceitaVO.setPetrobras(Petrobras.PETROBRAS_NOME);
            ctacReceitaVO.setConsignatarioEndereco(Petrobras.PETROBRAS_ENDERECO);
            ctacReceitaVO.setConsignatarioMunicipio(Petrobras.PETROBRAS_CIDADE);
            ctacReceitaVO.setConsignatarioUF(Petrobras.PETROBRAS_UF);
            ctacReceitaVO.setConsignatarioCNPJ(Petrobras.PETROBRAS_CNPJ);
            ctacReceitaVO.setConsignatarioInscEstadual(Petrobras.PETROBRAS_INSC_ESTADUAL);
            ctacReceitaVO.setPetrobrasCep(Petrobras.PETROBRAS_CEP);
            ctacReceitaVO.setPetrobrasBairroUf(Petrobras.PETROBRAS_BAIRRO + "/" + Petrobras.PETROBRAS_UF);

            Double totalFrete = null;
            // produto
            for (DocumentacaoOperacao documentacaoProdutoSelecionada : listaDocumentacaoProdutoSelecionada) {
                CTACProdutoVo ctacProdutoVO = new CTACProdutoVo();
                ctacProdutoVO.setDescricaoProduto(documentacaoProdutoSelecionada.getOperacao().getProduto().getNomeCompleto());
                ctacProdutoVO.setPeso(documentacaoProdutoSelecionada.getQuantidadeKg());
                ctacProdutoVO.setVolume(documentacaoProdutoSelecionada.getQuantidadeLt());
                ctacProdutoVO.setFrete(documentacaoProdutoSelecionada.getFrete());
                ctacProdutoVO.setConhecimentoEmbarque(documentacaoProdutoSelecionada.getCtac());
                ctacReceitaVO.getProdutos().add(ctacProdutoVO);
                if (ctacProdutoVO.getFrete() != null){
                    totalFrete = totalFrete == null ? 0D : totalFrete;
                    totalFrete += ctacProdutoVO.getFrete();
                }
            }
            ctacReceitaVO.setTotalFrete(totalFrete);

            //Local e data 
            ctacReceitaVO.setMunicipio(agenciamento.getAgencia().getCidade());

            Date dataAssinatura = new Date();
            ctacReceitaVO.setDataAssinatura(agenciamento.getAgencia().getCidade() + ", " + SistamDateUtils.formatDateByExtensive(dataAssinatura, zone) + ".");
            ctacReceitaVO.setEmitente(agenciamento.getAgencia().getNomeCompleto());
        } catch (Exception ex) {
            
        }
        
    }
     
        
    public DocumentacaoOperacao obterNovaDocumetacaoProduto(){
        DocumentacaoOperacao novo = new DocumentacaoOperacao();
        novo.setDocumentacaoOperacao(documentacaoSelecionada);
        return novo;
    }
    
    public DocumentacaoOperacao obterDocumentacaoProdutoParaEdicao(){
         DocumentacaoOperacao documentacaoOperacao = (DocumentacaoOperacao)listaDocumentacaoProdutoSelecionada.get(0);
         documentacaoOperacao = getService().buscarDocumentacaoOperacaoPorId(documentacaoOperacao.getId());
         return documentacaoOperacao;
    }

    
}
