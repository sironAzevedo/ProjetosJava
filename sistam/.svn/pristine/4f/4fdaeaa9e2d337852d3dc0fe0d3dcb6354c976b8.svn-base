
package br.com.petrobras.sistam.desktop.agenciamento.operacoes;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import java.util.List;

public class AssociacaoProdutoCargaCabotagemModel extends BasePresentationModel<SistamService> {
    private DocumentacaoOperacao documentacaoProduto;
    private List<Operacao> listaProduto;
    private boolean salvo = false;
    private DocumentacaoCabotagem documentacaoCabotagem;
    
    public AssociacaoProdutoCargaCabotagemModel(DocumentacaoOperacao documentacaoProduto){
        this.documentacaoProduto = documentacaoProduto;
        this.documentacaoCabotagem = getService().buscarDocumentacaoCabotagemPorId(documentacaoProduto.getDocumentacaoOperacao().getId());
        carregarListaProduto();
         if(!isPermitidoEditarCE())
            documentacaoProduto.setConhecimentoEletronico(documentacaoCabotagem.getConhecimentoEletronico());
        if(!isPermitidoEditarCTAC())
            documentacaoProduto.setCtac(documentacaoCabotagem.getNumeroCTAC());
    }

    public DocumentacaoOperacao getDocumentacaoProduto() {
        return documentacaoProduto;
    }

    public List<Operacao> getListaProduto() {
        return listaProduto;
    }
    
    public boolean isSalvo(){
        return salvo;
    }
    
    public void salvar(){
        documentacaoProduto = getService().salvarDocumentacaoProduto(documentacaoProduto);
        salvo = true;
    }

    public DocumentacaoCabotagem getDocumentacaoCabotagem() {
        return documentacaoCabotagem;
    }

    public boolean isPermitidoEditarCE(){
        return documentacaoCabotagem.isPermitidoEditarCE();
    }
    
    public boolean isPermitidoEditarCTAC(){
        return documentacaoCabotagem.isPermitidoEditarCTAC();
    }
    
    private void carregarListaProduto(){
        listaProduto = getService().buscarOperacoesPorAgenciamentoETipo(documentacaoCabotagem.getAgenciamento(), TipoOperacao.CARGA_CABOTAGEM);
        
        //Remove os produtos já adicionados
        for (DocumentacaoOperacao dp : documentacaoCabotagem.getDocumentacaoProdutos()) {
            
            //Se o produto que está sendo editado/adicionado estiver na lista, não o remove da lista.
            if (!documentacaoProduto.equals(dp)){
                listaProduto.remove(dp.getOperacao());
            }
        }
        
        listaProduto.add(0, null);
    }
}
