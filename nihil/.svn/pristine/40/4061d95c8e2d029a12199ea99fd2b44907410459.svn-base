/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.service.impl;

import br.com.spassu.nihil.common.entity.Projeto;
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
public class ProjetoServiceImpl {

    @Autowired
    private GenericDao dao;

    @Transactional(readOnly = true)
    public List<Projeto> buscarProjetos() {
        return dao.findAll(Projeto.class);
    }

    @Transactional(readOnly = false)
    public Projeto salvarProjeto(Projeto projeto) {
        return dao.saveOrUpdate(projeto);
    }
    
    @Transactional(readOnly = false)
    public void remover(Long projetoId) {
        dao.remove(dao.get(Projeto.class, projetoId));
    }
    
    @Transactional(readOnly = true)
    public Projeto buscarPorId(Long projetoId) {
        return dao.get(Projeto.class, projetoId);
    }
    
    
}
