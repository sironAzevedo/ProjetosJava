/*
 * Serviço de controle e informações da empresa.  
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmpresa;
import java.util.List;

public interface EmpresaService {
    
    @AuthorizedResource("")
    public List<EmpresaMaritima> buscarEmpresasPorFiltro(FiltroEmpresa filtro);
    
    @AuthorizedResource("")
    public EmpresaMaritima salvarEmpresa(EmpresaMaritima empresa);

    @AuthorizedResource("")
    public void excluirEmpresa(EmpresaMaritima empresa);
    
    @AuthorizedResource("")
    public Servico salvarServicoDaEmpresa(Servico servico);
    
    @AuthorizedResource("")
    public Servico excluirServicoDaEmpresa(Servico servico);
    
    @AuthorizedResource("")
    public void validarSalvarServicoDaEmpresa(Servico servico);
   
    

   
}
