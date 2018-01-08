package br.com.petrobras.sistam.desktop.agenciamento.operacoes;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import java.util.List;

public class DetalheDocumentacaoDescargaCabotagemModel extends BasePresentationModel<SistamService> {
    private DocumentacaoCabotagem documentacao;
    private List<Agencia> listaAgencia;
    private boolean salvo;
    
    public DetalheDocumentacaoDescargaCabotagemModel(DocumentacaoCabotagem documentacao){
        this.documentacao = documentacao;
        carregarListaAgencia();
    }

    public DocumentacaoCabotagem getDocumentacao() {
        return documentacao;
    }

    public List<Agencia> getListaAgencia() {
        return listaAgencia;
    }

    public boolean isSalvo() {
        return salvo;
    }

    public void salvar(){
        documentacao = getService().salvarDocumentacaoCabotagem(documentacao);
        salvo = true;
    }
    
    private void carregarListaAgencia(){
        SistamCredentialsBean contexto = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        listaAgencia = contexto.getAgenciasAutorizadas();
        listaAgencia.add(0, null);
    }

    
}
