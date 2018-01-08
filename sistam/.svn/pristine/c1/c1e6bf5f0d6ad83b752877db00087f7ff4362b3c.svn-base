package br.com.petrobras.sistam.desktop.agenciamento.operacoes;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;

public class DetalheDocumentacaoCargaCabotagemModel extends BasePresentationModel<SistamService> {
    private DocumentacaoCabotagem documentacao;
    private boolean salvo;
    
    public DetalheDocumentacaoCargaCabotagemModel(DocumentacaoCabotagem documentacao){
        this.documentacao = documentacao;
    }

    public DocumentacaoCabotagem getDocumentacao() {
        return documentacao;
    }

    public boolean isSalvo() {
        return salvo;
    }

    public void salvar(){
        documentacao = getService().salvarDocumentacaoCabotagem(documentacao);
        salvo = true;
    }
    
}
