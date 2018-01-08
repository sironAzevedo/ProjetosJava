package br.com.petrobras.sistam.desktop.agenciamento.documento;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Certificado;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.enums.TipoCertificado;

public class InformacaoNumeroCertificadoModel extends BasePresentationModel<SistamService> {
    private Certificado certificado;
    private boolean operacaoConfirmada = false;
    
     public InformacaoNumeroCertificadoModel(Documento documento) {
        Agenciamento agenciamento = documento.getAgenciamento();
        this.certificado = getService().buscarCertificadoValidoPorTipo(TipoCertificado.CLP, agenciamento, null, agenciamento.getDataEstimadaSaida());
        if(this.certificado == null){
            this.certificado = new Certificado();
            this.certificado.setAgenciamento(agenciamento);
            this.certificado.setEmbarcacao(agenciamento.getEmbarcacao());
            this.certificado.setTipo(TipoCertificado.CLP);
        }
    }

    public Certificado getCertificado() {
        return certificado;
    }
    
    public void salvar(){
        getService().salvarCertificadoLivrePratica(certificado);
        operacaoConfirmada = true;
    }
    
    public boolean operacaoConfirmada(){
        return operacaoConfirmada;
    }

}