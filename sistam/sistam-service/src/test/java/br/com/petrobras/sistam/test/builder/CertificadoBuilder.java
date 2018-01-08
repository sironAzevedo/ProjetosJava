package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Certificado;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.enums.TipoCertificado;
import java.util.Date;

public class CertificadoBuilder {

    private Certificado certificado;
    
    private CertificadoBuilder(Certificado certificado) {
        this.certificado = certificado;
    }
    
    public static CertificadoBuilder novoCertificado() {
        return new CertificadoBuilder(new Certificado());
    }
    
    public Certificado build() {
        return this.certificado;
    }

    public CertificadoBuilder comId(Long id) {
        this.certificado.setId(id);
        return this;
    }

    public CertificadoBuilder doAgenciamento(Agenciamento agenciamento) {
        this.certificado.setAgenciamento(agenciamento);
        return this;
    }
    
    public CertificadoBuilder daEmbarcacao(Embarcacao embarcacao) {
        this.certificado.setEmbarcacao(embarcacao);
        return this;
    }
    
    public CertificadoBuilder comData(Date data) {
        this.certificado.setData(data);
        return this;
    }

    public CertificadoBuilder doTipo(TipoCertificado tipo) {
        this.certificado.setTipo(tipo);
        return this;
    }
    
    
    
}
