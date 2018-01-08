package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.valueobjects.FiltroEmbarcacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaEmbarcacoesPorFiltro extends BusinessQuery<Embarcacao> {

    public ConsultaEmbarcacoesPorFiltro(FiltroEmbarcacao filtro) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();
        texto
                .append(" select e from {Embarcacao} e ") 
                .append(" left join fetch e.{bandeira}  ") 
                .append(" left join fetch e.{portoRegistro}  ") 
                .append(" left join fetch e.{paisInspecao}  ") 
                .append(" where 1=1 ");
        
                
            if (filtro.getNomeEmbarcacao()!=null && !filtro.getNomeEmbarcacao().isEmpty()) {        
                texto.append(" and upper(e.{nomeCompleto}) like '%'|| upper(:nome) ||'%' ");
                map.put("nome", filtro.getNomeEmbarcacao());
            }
            
            if (null != filtro.getBandeira()){
                texto.append(" and e.{bandeira} = :bandeira");
                map.put("bandeira", filtro.getBandeira() );
            }
            if (filtro.getImo()!=null && !filtro.getImo().isEmpty()) {        
                texto.append(" and e.{imo} = :imo");
                map.put("imo", filtro.getImo() );
            }
            
            texto.append(" order by e.{nomeCompleto}");
            
            String hql = texto.toString();     
                
        hql = hql.replace("{Embarcacao}", Embarcacao.class.getSimpleName())
                 .replace("{nomeCompleto}", Embarcacao.PROP_NOME_COMPLETO)
                 .replace("{bandeira}", Embarcacao.PROP_BANDEIRA)
                 .replace("{portoRegistro}", Embarcacao.PROP_PORTO_REGISTRO)
                 .replace("{paisInspecao}", Embarcacao.PROP_PAIS_INSPECAO)
                .replace("{imo}", Embarcacao.PROP_IMO)
                ;
        
        this.setText(hql);
        setParameters(map);
        
        }
    
    
}
