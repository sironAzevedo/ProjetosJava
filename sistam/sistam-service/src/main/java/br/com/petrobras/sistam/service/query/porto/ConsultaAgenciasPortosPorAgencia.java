package br.com.petrobras.sistam.service.query.porto;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaPorto;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaAgenciasPortosPorAgencia extends BusinessQuery<AgenciaPorto> {

    public ConsultaAgenciasPortosPorAgencia(Agencia agencia) {
                 
        StringBuilder texto = new StringBuilder();        

        texto        
            .append(" select ap from {AgenciaPorto} ap ")
            .append(" join fetch ap.{porto} p ")
            .append(" join fetch ap.{agencia} a ")
            .append(" where a = :agencia ")
            .append(" order by p.{nomeCompleto} ")
            ;
                            
        String hql = texto.toString();

        hql = hql.replace("{AgenciaPorto}", AgenciaPorto.class.getSimpleName())
                 .replace("{porto}", AgenciaPorto.PROP_PORTO)
                 .replace("{agencia}", AgenciaPorto.PROP_AGENCIA)
                 .replace("{nomeCompleto}", Porto.PROP_NOME_COMPLETO);
        
        this.setText(hql);
        addParameter("agencia", agencia);
    }
    
}
