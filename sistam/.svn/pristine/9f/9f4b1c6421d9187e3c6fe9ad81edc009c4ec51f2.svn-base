package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Consulta os agenciamentos para carregar a caixa de entrada.
 * Serão consultados apenas os agenciamentos com data de saída nula ou data de saída
 * maior ou igual que a data atual menos 30 dias.
 * @author x4z0
 */
public class ConsultaAgenciamentoParaCaixaDeEntrada extends BusinessQuery<Agenciamento> {

    public ConsultaAgenciamentoParaCaixaDeEntrada(Agencia agencia, Porto porto, Date dataDeCorte) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder texto = new StringBuilder();
        TimeZone zone = null;

        texto
                .append(" select distinct agm from {Agenciamento} agm ")
                .append(" join fetch agm.{agencia} ag ")
                .append(" join fetch agm.{embarcacao} e ")
                .append(" join fetch agm.{portoOrigem} poo ")
                .append(" left join fetch agm.{portoDestino} pod ")
                .append(" left join fetch agm.{porto} por ")
                .append(" left join fetch agm.{pendencias} pen ")
                .append(" left join fetch agm.{desvio} d ")
                .append(" left join agm.cancelamento c ") 
                .append(" where (((agm.{dataSaida} is null ")
                .append("      or agm.{dataSaida} >= :dataDeCorte ")
                .append("      or  d.{dataDesvio} >= :dataDeCorte )") 
                .append("      and ( c.data is null or c.data >= :dataDeCorte )) ") 
		.append("      or (c.data >= :dataDeCorte))");
        
        
        if (agencia != null) {
            texto.append(" and agm.{agencia} = :agencia ");
            map.put("agencia", agencia);
            zone = agencia.obterTimezone();
        }
        
        map.put("dataDeCorte", DateBuilder.on(dataDeCorte).at(0, 0, 0).getTime(zone));
        
        if (porto != null) {
            texto.append(" and agm.{porto} = :porto ");
            map.put("porto", porto);
        }
        
        String hql = texto.toString();
        hql = hql.replace("{Agenciamento}", Agenciamento.class.getSimpleName())
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{porto}", Agenciamento.PROP_PORTO)
                 .replace("{embarcacao}", Agenciamento.PROP_EMBARCACAO)
                 .replace("{portoOrigem}", Agenciamento.PROP_PORTO_ORIGEM)
                 .replace("{portoDestino}", Agenciamento.PROP_PORTO_DESTINO)
                 .replace("{dataSaida}", Agenciamento.PROP_DATA_SAIDA)
                 .replace("{pendencias}", Agenciamento.PROP_PENDENCIAS)
                 .replace("{desvio}", Agenciamento.PROP_DESVIO )
                 .replace("{dataDesvio}", Desvio.PROP_DATA )
                 .replace("{nomeCompleto}", Embarcacao.PROP_NOME_COMPLETO)
                ;
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
