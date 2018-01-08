package br.com.petrobras.sistam.service.query.porto;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaPorto;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.Arrays;

public class ConsultaPortosPorAgencia extends BusinessQuery<Porto> {

    public ConsultaPortosPorAgencia(Agencia... agencias) {
                 
        StringBuilder texto = new StringBuilder();        

        texto        
            .append(" select distinct porto from {Porto} porto ")
            .append(" left join fetch porto.{agenciasPortos} ap ")
            .append(" left join fetch ap.{agencias} a ")
            .append(" left join fetch porto.{pontosOperacionais} po ")
            .append(" where a in (:agencias) ")
            .append(" order by porto.{nomeCompleto} ")
            ;
                            
        String hql = texto.toString();

        hql = hql.replace("{Porto}", Porto.class.getSimpleName())
                 .replace("{agenciasPortos}", Porto.PROP_AGENCIAS_PORTOS)
                 .replace("{agencias}", AgenciaPorto.PROP_AGENCIA)
                 .replace("{pontosOperacionais}", Porto.PROP_PONTOS_OPERACIONAIS)
                 .replace("{nomeCompleto}", Porto.PROP_NOME_COMPLETO);
        
        this.setText(hql);
        addParameter("agencias", Arrays.asList(agencias));
    }
    
}
