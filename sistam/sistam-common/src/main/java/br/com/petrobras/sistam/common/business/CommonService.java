/*
 * Serviço de comunicação com os serviços de busca.
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.Produto;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.enums.TipoServico;
import java.io.Serializable;
import java.util.List;

public interface CommonService {

    @AuthorizedResource("")
    public Object buscarEntidadePorId(Class clazz, Serializable id);

    @AuthorizedResource("")
    public List<Pais> buscarPaises(String nome);
    
    @AuthorizedResource("")
    public List<ResponsavelCustoEntity> buscarTodosResponsavelCusto();
    
    @AuthorizedResource("")
    public List<ResponsavelCustoEntity> buscarResponsavelCustoApenasPetrobrasETranspetro();
    
    @AuthorizedResource("")
    public List<ResponsavelCustoEntity> buscarResponsavelCustoExcetoSemCusto();

    @AuthorizedResource("")
    public List<Produto> buscarProdutos(String nome);

    @AuthorizedResource("")
    public Produto buscarProdutoPorId(String id);

    @AuthorizedResource("")
    public List<EmpresaMaritima> buscarEmpresasMaritimasPorAgenciaTipoServico(Agencia agencia, TipoServico tipoServico);

    @AuthorizedResource("")
    public List<Servico> buscarServicosPorEmpresaETipo(EmpresaMaritima empresa, TipoServico tipo);

    @AuthorizedResource("")
    public List<Servico> buscarServicosPorTipo(TipoServico tipo);
}
