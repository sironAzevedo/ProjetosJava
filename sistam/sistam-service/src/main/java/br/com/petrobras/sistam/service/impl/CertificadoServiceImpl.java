package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.snarf.persistence.GenericDao;
import br.com.petrobras.sistam.common.business.CertificadoService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Certificado;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.enums.TipoCertificado;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.service.query.ConsultaCertificados;
import br.com.petrobras.sistam.service.validator.ValidadorCertificado;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class CertificadoServiceImpl implements CertificadoService {

    @Autowired
    private ValidadorPermissao validadorPermissao;
    
    
    @Autowired
    private ValidadorCertificado validadorCertificado;
    
    private GenericDao dao;
    
    public void setDao(GenericDao dao) {
        this.dao = dao;
    }
    
    @Transactional(readOnly = true)    
    @Override
    public Certificado buscarCertificadoValidoPorTipo(TipoCertificado tipo, Agenciamento agenciamento, Embarcacao embarcacao, Date dataEstimadaSaida) {
        List<Certificado> certificadosAux = dao.list(new ConsultaCertificados(tipo,  embarcacao, agenciamento, dataEstimadaSaida));
        if (certificadosAux.isEmpty()) {
            return null;
        }
        return certificadosAux.get(0);
    }

    @Transactional(readOnly = false)    
    @Override
    public Certificado salvarCertificado(Certificado certificado) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarCertificado(), ConstantesI18N.CERTIFICADO_SEM_PERMISSAO_SALVAR);
        validadorCertificado.validarSalvarCertificado(certificado);
        dao.saveOrUpdate(certificado);
        return certificado;
    }

    @Transactional(readOnly = false)    
    @Override
    public Certificado salvarCertificadoLivrePratica(Certificado certificado) {
        AssertUtils.assertExpression(validadorPermissao.podeSalvarCertificado(), ConstantesI18N.CERTIFICADO_SEM_PERMISSAO_SALVAR);
        validadorCertificado.validarSalvarCertificadoLivrePratica(certificado);
        dao.saveOrUpdate(certificado);
        return certificado;
    }

    @Transactional(readOnly = false)    
    @Override
    public List<Certificado> salvarCertificados(List<Certificado> certificados) {
        
        for (Certificado certificado : certificados) {
            certificado = salvarCertificado(certificado);
        }
        
        return certificados;
    }
                            
}
