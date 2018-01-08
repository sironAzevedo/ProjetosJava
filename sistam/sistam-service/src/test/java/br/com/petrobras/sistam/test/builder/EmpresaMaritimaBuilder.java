package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;

public class EmpresaMaritimaBuilder {

    private EmpresaMaritima empresa;
    
    private EmpresaMaritimaBuilder(EmpresaMaritima empresa) {
        this.empresa = empresa;
    }
    
    public static EmpresaMaritimaBuilder novaEmpresaMaritima() {
        return new EmpresaMaritimaBuilder(new EmpresaMaritima());
    }
    
    public static EmpresaMaritimaBuilder novaEmpresaMaritima(EmpresaMaritima empresa) {
        return new EmpresaMaritimaBuilder(empresa);
    }
    
    public EmpresaMaritima build() {
        return this.empresa;
    }
    
    public EmpresaMaritimaBuilder comCnpj(String cnpj) {
        this.empresa.setCnpj(cnpj);
        return this;
    }
    
    public EmpresaMaritimaBuilder comEmail(String email) {
        this.empresa.setEmail(email);
        return this;
    }
    
    public EmpresaMaritimaBuilder comId(Long id) {
        this.empresa.setId(id);
        return this;
    }

    public EmpresaMaritimaBuilder comRazaoSocial(String razaoSocial) {
        this.empresa.setRazaoSocial(razaoSocial);
        return this;
    }
    
     public EmpresaMaritimaBuilder comAgencia(Agencia agencia) {
        this.empresa.setAgencia(agencia);
        return this;
    }

}
