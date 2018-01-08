/*
 * Serviço de controle e busca de informações das empresas.  
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmpresaProtecao;
import java.util.List;

public interface EmpresaProtecaoService {

    @AuthorizedResource("")
    public EmpresaProtecao salvarEmpresaProtecao(EmpresaProtecao empresa);

    @AuthorizedResource("")
    public EmpresaProtecao buscarEmpresaProtecaoPorCnpj(String cnpj);

    @AuthorizedResource("")
    public EmpresaProtecao buscarEmpresaProtecaoPorId(long id);

    @AuthorizedResource("")
    public List<EmpresaProtecao> buscarEmpresasProtecao();

    @AuthorizedResource("")
    public List<EmpresaProtecao> buscarEmpresasProtecaoAtiva();

    @AuthorizedResource("")
    public List<EmpresaProtecao> buscarEmpresasProtecaoPorTipoServico(TipoServicoProtecao tipoServicoProtecao);

    @AuthorizedResource("")
    public List<EmpresaProtecao> buscarEmpresasProtecaoAtivaPorTipoNomeCnpj(TipoServicoProtecao tipo, String nome, String cnpj);

    @AuthorizedResource("")
    public List<EmpresaProtecao> buscarEmpresasProtecaoPorFiltro(FiltroEmpresaProtecao filtro);
}