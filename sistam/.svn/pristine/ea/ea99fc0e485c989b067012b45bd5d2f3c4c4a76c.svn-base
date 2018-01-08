package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaAgenciamentoPorId extends BusinessQuery<Agenciamento> {

    public ConsultaAgenciamentoPorId(Long id) {

        Map<String, Object> map = new HashMap<String, Object>();
                
                
        StringBuilder texto = new StringBuilder();    
        

        texto
                .append(" select agm from {Agenciamento} agm ")        
                .append(" join fetch agm.{portoAgm} por ")        
                .append(" join fetch agm.{agencia} a ")                     
                .append(" left join fetch a.{agenciaOrgao} ao ")                                
                .append(" left join fetch a.{agenciaPortos} ps ")
                .append(" left join fetch ps.porto pps ")
                .append(" left join fetch ps.agencia aps ")
                .append(" left join fetch a.{representantes} co ")                                
                .append(" join fetch agm.{embarcacao} e ")
                .append(" left join fetch e.{paisInspecao} pi ")
                .append(" left join fetch agm.{pendencias} pend ")                
                .append(" left join fetch agm.{porto} po ") 
                .append(" join fetch agm.{portoOrigem} poo ") 
                .append(" left join fetch e.{pais} pa ") 
                .append(" left join fetch agm.{portoDestino} pod ")
                .append(" left join fetch pod.{complementos} com ")
                .append(" left join fetch pod.{paisPortoDestino} ppod ")
                .append(" left join fetch e.{portoRegistro} por ")
                .append(" where agm.{id} = :id ");
        
        map.put("id", id);
                
        
        String hql = texto.toString();

        hql = hql.replace("{Agenciamento}", Agenciamento.class.getSimpleName())
                 .replace("{id}", Agenciamento.PROP_ID)                 
                 .replace("{portoAgm}", Agenciamento.PROP_PORTO)                 
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)                                               
                 .replace("{agenciaOrgao}", Agencia.PROP_AGENCIA_ORGAO)                 
                 .replace("{agenciaPortos}", Agencia.PROP_AGENCIA_PORTOS)                 
                 .replace("{representantes}", Agencia.PROP_REPRESENTANTES)                                 
                 .replace("{embarcacao}", Agenciamento.PROP_EMBARCACAO)
                 .replace("{pendencias}", Agenciamento.PROP_PENDENCIAS)
                 .replace("{porto}", Agenciamento.PROP_PORTO)
                 .replace("{portoOrigem}", Agenciamento.PROP_PORTO_ORIGEM)
                 .replace("{pais}", Embarcacao.PROP_BANDEIRA)
                 .replace("{paisInspecao}", Embarcacao.PROP_PAIS_INSPECAO)              
                 .replace("{portoDestino}", Agenciamento.PROP_PORTO_DESTINO)
                 .replace("{complementos}", Porto.PROP_COMPLEMENTOS)
                 .replace("{paisPortoDestino}", Porto.PROP_PAIS)
                 .replace("{portoRegistro}", Embarcacao.PROP_PORTO_REGISTRO );

        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
