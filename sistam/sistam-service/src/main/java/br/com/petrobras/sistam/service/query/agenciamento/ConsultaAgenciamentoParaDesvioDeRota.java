package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 * Classe que consulta o desvio de um agenciamento.
 */
public class ConsultaAgenciamentoParaDesvioDeRota extends BusinessQuery<Agenciamento> {

    public ConsultaAgenciamentoParaDesvioDeRota(Long idAgenciamento) {
        
        
        StringBuilder texto = new StringBuilder();

        texto.append(" from {Agenciamento} ag ")
             .append(" join fetch ag.{agencia} agen")
             .append(" left join fetch agen.{agenciaOrgao} ao ")                                
             .append(" join fetch ag.{embarcacao} ")
             .append(" left join fetch ag.{portoDestino} ")
             .append(" left join fetch ag.{desvio} d ")
             .append(" left join fetch d.{portoDestinoAlterado} ")
             .append(" where ag.{id} = :idAgenciamento ");
        
        addParameter("idAgenciamento", idAgenciamento);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Agenciamento}", Agenciamento.class.getSimpleName())
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{agenciaOrgao}", Agencia.PROP_AGENCIA_ORGAO)                 
                 .replace("{embarcacao}", Agenciamento.PROP_EMBARCACAO)
                 .replace("{portoDestino}", Agenciamento.PROP_PORTO_DESTINO)
                 .replace("{desvio}", Agenciamento.PROP_DESVIO)
                 .replace("{portoDestinoAlterado}", Desvio.PROP_PORTO_DESTINO_ALTERADO)
                 .replace("{id}", Agenciamento.PROP_ID);
        
        setText(hql);
    }
    
}
