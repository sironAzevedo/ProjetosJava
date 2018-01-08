package br.com.petrobras.sistam.service.query.servicoprotecao;

import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.valueobjects.FiltroPessoaProtecao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import org.apache.commons.lang.StringUtils;

/**
 * @author adzk
 */
public class ConsultaPessoasProtecaoPorFiltro extends BusinessQuery<Pessoa> {

    public ConsultaPessoasProtecaoPorFiltro(FiltroPessoaProtecao filtro) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select distinct(p)")
                .append(" from {pessoa} p")
                .append(" left join fetch p.{empresa} e")
                .append(" where 1 = 1");

        if(StringUtils.isNotBlank(filtro.getNome())){
            hql.append(" and UPPER(p.{nome}) like '%' || :nome || '%'");
            addParameter("nome", filtro.getNome().toUpperCase());
        }
        
        if(StringUtils.isNotBlank(filtro.getCpf())){
            hql.append(" and p.{cpf} = :cpf");
            addParameter("cpf", filtro.getCpf());
        }
        
        if(StringUtils.isNotBlank(filtro.getDocumento())){
            hql.append(" and p.{documento} = :documento");
            addParameter("documento", filtro.getDocumento());
        }
        
        if(filtro.getDataNascimento() != null){
            hql.append(" and TRUNC(p.{dtNascimento}) = TRUNC(:dtNascimento)");
            addParameter("dtNascimento", filtro.getDataNascimento());
        }
        
        if(filtro.getEmpresa() != null){
            hql.append(" and e.{empresaId} = :empresaId");
            addParameter("empresaId", filtro.getEmpresa().getId());
        }
        
        hql.append(" order by p.{nome}");

        String text = hql.toString()
                .replace("{pessoa}", Pessoa.class.getSimpleName())
                .replace("{empresa}", Pessoa.PROP_EMPRESA)
                .replace("{nome}", Pessoa.PROP_NOME)
                .replace("{cpf}", Pessoa.PROP_CPF)
                .replace("{documento}", Pessoa.PROP_DOCUMENTO)
                .replace("{dtNascimento}", Pessoa.PROP_DATA_NASCIMENTO)
                .replace("{empresaId}", EmpresaProtecao.PROP_ID)
                ;
        this.setText(text);
    }
}
