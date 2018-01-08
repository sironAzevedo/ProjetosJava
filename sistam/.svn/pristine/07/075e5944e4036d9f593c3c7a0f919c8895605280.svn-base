package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.beans.CredentialsBean;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.AcessoService;
import br.com.petrobras.sistam.common.business.AnexoService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Anexo;
import br.com.petrobras.sistam.common.entity.Arquivo;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.service.query.agenciamento.anexo.ConsultaAnexosDoAgenciamento;
import br.com.petrobras.sistam.service.query.agenciamento.anexo.ConsultaArquivoDoAnexo;
import br.com.petrobras.sistam.service.validator.ValidadorAnexo;
import br.com.petrobras.snarf.persistence.GenericDao;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class AnexoServiceImpl implements AnexoService {
    
    private GenericDao dao;
    private AcessoService acessoService;
    
    @Autowired
    private ValidadorPermissao validadorPermissao;

    @Autowired
    private ValidadorAnexo validadorAnexo;
    
    public AnexoServiceImpl(GenericDao dao) {
        this.dao = dao;
    }

    public void setAcessoService(AcessoService acessoService) {
        this.acessoService = acessoService;
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Anexo> buscarAnexosDoAgenciamento(Agenciamento agenciamento){
        return dao.list(new ConsultaAnexosDoAgenciamento(agenciamento));
    }
    
    @Override
    @Transactional(readOnly=true)
    public Arquivo buscarArquivoDoAnexo(Anexo anexo){
        return dao.uniqueResult(new ConsultaArquivoDoAnexo(anexo));
    }
    
    @Override
    @Transactional(readOnly=false)
    public List<Anexo> salvarAnexos(List<Anexo> listaDeAnexos){
        AssertUtils.assertNotNullNotEmpty(listaDeAnexos, ConstantesI18N.ANEXO_LISTA_VAZIO);
        AssertUtils.assertExpression(validadorPermissao.podeSalvarAnexo(listaDeAnexos.get(0).getAgenciamento().getAgencia()), ConstantesI18N.ANEXO_SEM_PERMISSAO_SALVAR);
        
        //Obtém os dados do usuário logado
        CredentialsBean credentialsBean = acessoService.buscarDadosDeAutenticacao();
        
        for (Anexo anexo : listaDeAnexos){
            validadorAnexo.validarSalvarAnexo(anexo);
            
            //Atualiza com o usuário logado e a data atual.
            anexo.setChaveUsuario(credentialsBean.getLogon());
            anexo.setNomeUsuario(credentialsBean.getUsername());
            anexo.setDataDeCriacao(new Date());
            
            //Salva o anexo
            dao.saveOrUpdate(anexo);
            
            //Salva o arquivo do anexo
            anexo.getArquivo().setId(anexo.getId());
            dao.saveOrUpdate(anexo.getArquivo());
        }
        
        return listaDeAnexos;
    }
    
    @Override
    @Transactional(readOnly=false)
    public void excluirAnexo(Anexo anexo){
        AssertUtils.assertExpression(validadorPermissao.podeSalvarAnexo(anexo.getAgenciamento().getAgencia()), ConstantesI18N.ANEXO_SEM_PERMISSAO_SALVAR);
        
        dao.remove(anexo);
    }

    
    
   
                            
}
