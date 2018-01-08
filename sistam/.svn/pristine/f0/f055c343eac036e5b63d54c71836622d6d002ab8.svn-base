/*
 * Serviço de controle e busca de certificados.
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Certificado;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.enums.TipoCertificado;
import java.util.Date;
import java.util.List;

public interface CertificadoService {

    @AuthorizedResource("")
    public Certificado buscarCertificadoValidoPorTipo(TipoCertificado tipo, Agenciamento agenciamento, Embarcacao embarcacao, Date dataEstimadaSaida);

    @AuthorizedResource("")
    public Certificado salvarCertificado(Certificado certificado);

    @AuthorizedResource("")
    public Certificado salvarCertificadoLivrePratica(Certificado certificado);

    @AuthorizedResource("")
    public List<Certificado> salvarCertificados(List<Certificado> certificados);
}
