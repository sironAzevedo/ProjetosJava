package br.com.petrobras.sistam.service.mock;

import br.com.petrobras.sistam.service.aspect.*;
import br.com.petrobras.sistam.common.business.AcessoService;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;

public class AspectValidatorMock extends AspectValidator {
    

    @Override
    public void setAcessoService(AcessoService acessoService) {
    }

    @Override
    public void setValidadorPermissao(ValidadorPermissao validadorPermissao) {
    }

    @Override
    public void credentialsBeanInject() {
    }

}
