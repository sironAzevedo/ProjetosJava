package br.com.petrobras.sistam.service.aspect;

import br.com.petrobras.sistam.common.business.AcessoService;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AspectValidator {
    
    AcessoService acessoService;
    ValidadorPermissao validadorPermissao;

    public void setAcessoService(AcessoService acessoService) {
        this.acessoService = acessoService;
    }

    public void setValidadorPermissao(ValidadorPermissao validadorPermissao) {
        this.validadorPermissao = validadorPermissao;
    }

    @Before("execution(* br.com.petrobras.sistam.common.validator.ValidadorPermissao.pode*(..))")
    public void credentialsBeanInject() {
        validadorPermissao.setCredentialsBean((SistamCredentialsBean)acessoService.buscarDadosDeAutenticacao());
    }

}
