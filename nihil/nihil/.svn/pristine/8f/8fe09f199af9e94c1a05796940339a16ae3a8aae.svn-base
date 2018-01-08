/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.service.impl;

import br.com.spassu.nihil.common.entity.Empresa;
import br.com.spassu.nihil.service.persistence.GenericDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADM
 */
@Component
public class EmpresaServiceImpl {

    @Autowired
    private GenericDao dao;

    @Transactional(readOnly = true)
    public List<Empresa> buscarEmpresas() {
        return dao.findAll(Empresa.class);
    }

    @Transactional(readOnly = false)
    public Empresa salvarEmpresa(Empresa empresa) {
        return dao.saveOrUpdate(empresa);
    }
    
    @Transactional(readOnly = true)
    public Empresa buscarEmpresaPorId(Long empresaId) {
        return dao.get(Empresa.class, empresaId);
    }
    
    
}
