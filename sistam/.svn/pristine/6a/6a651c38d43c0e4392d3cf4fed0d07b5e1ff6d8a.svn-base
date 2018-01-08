package br.com.petrobras.sistam.desktop.agenciamento.operacoes;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.enums.SituacaoAFRM;
import br.com.petrobras.sistam.common.enums.SituacaoCarga;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import java.util.Arrays;
import java.util.List;

public class AssociacaoProdutoDescargaCabotagemModel extends BasePresentationModel<SistamService> {
    private DocumentacaoOperacao documentacaoProduto;
    private List<Operacao> listaProduto;
    private List<SituacaoAFRM> listaSituacaoAfrm;
    private List<SituacaoCarga> listaSituacaoCarga;
    private boolean salvo = false;
    
    public AssociacaoProdutoDescargaCabotagemModel(DocumentacaoOperacao documentacaoProduto){
        this.documentacaoProduto = documentacaoProduto;
        carregarListaProduto();
        
        listaSituacaoAfrm = Arrays.asList(SituacaoAFRM.values());
        listaSituacaoCarga = Arrays.asList(SituacaoCarga.values());
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public DocumentacaoOperacao getDocumentacaoProduto() {
        return documentacaoProduto;
    }
    
    public List<Operacao> getListaProduto() {
        return listaProduto;
    }
    
    public List<SituacaoAFRM> getListaSituacaoAfrm() {
        return listaSituacaoAfrm;
    }
    
    public List<SituacaoCarga> getListaSituacaoCarga() {
        return listaSituacaoCarga;
    }
    //</editor-fold>
    
    public boolean isSalvo(){
        return salvo;
    }
    
    public void salvar(){
        documentacaoProduto = getService().salvarDocumentacaoProduto(documentacaoProduto);
        salvo = true;
    }
    
    private void carregarListaProduto(){
        listaProduto = getService().buscarOperacoesPorAgenciamentoETipo(documentacaoProduto.getDocumentacaoOperacao().getAgenciamento(), TipoOperacao.DESCARGA_CABOTAGEM);
        
        //Remove os produtos já adicionados
        for (DocumentacaoOperacao dp : documentacaoProduto.getDocumentacaoOperacao().getDocumentacaoProdutos()) {
            
            //Se o produto que está sendo editado/adicionado estiver na lista, não o remove da lista.
            if (!documentacaoProduto.equals(dp)){
                listaProduto.remove(dp.getOperacao());
            }
        }
        
        listaProduto.add(0, null);
    }
}
