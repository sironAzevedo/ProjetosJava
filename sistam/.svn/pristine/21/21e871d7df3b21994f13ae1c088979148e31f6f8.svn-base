package br.com.petrobras.sistam.desktop.agenciamento.documento;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Documento;

public class InformacoesDoProtocoloModel extends BasePresentationModel<SistamService> {
    private Documento documento;
    private boolean operacaoConfirmada = false;

    public InformacoesDoProtocoloModel(Documento documento) {
        this.documento = documento;
    }

    public Documento getDocumento() {
        return documento;
    }
    
    public void salvar(){
        documento = getService().informarProtocoloDoDocumento(documento);
        operacaoConfirmada = true;
    }
    
    public boolean operacaoConfirmada(){
        return operacaoConfirmada;
    }
    
}
