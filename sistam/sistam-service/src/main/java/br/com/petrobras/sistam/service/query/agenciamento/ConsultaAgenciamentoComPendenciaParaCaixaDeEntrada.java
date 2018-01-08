package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaAgenciamentoComPendenciaParaCaixaDeEntrada extends BusinessQuery<Agenciamento> {

    public ConsultaAgenciamentoComPendenciaParaCaixaDeEntrada(Agencia agencia, Porto porto) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select distinct agm from {Agenciamento} agm ")
                .append(" join fetch agm.{agencia} ag ")
                .append(" join fetch agm.{embarcacao} e ")
                .append(" join fetch agm.{portoOrigem} poo ")
                .append(" left join fetch agm.{portoDestino} pod ")
                .append(" left join fetch agm.{porto} por ")
                .append(" join fetch agm.{pendencias} pen ")
                .append(" where pen.{resolvida} = false");
        
        if (agencia != null) {
            texto.append(" and agm.{agencia} = :agencia ");
            map.put("agencia", agencia);
        }
        
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
                 .replace("{pendencias}", Agenciamento.PROP_PENDENCIAS)
                 .replace("{resolvida}", Pendencia.PROP_RESOLVIDA)
                 .replace("{nomeCompleto}", Embarcacao.PROP_NOME_COMPLETO)
                ;
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
