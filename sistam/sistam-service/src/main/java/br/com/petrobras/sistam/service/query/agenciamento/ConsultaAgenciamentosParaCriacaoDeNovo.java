package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamento;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaAgenciamentosParaCriacaoDeNovo extends BusinessQuery<Long> {

    public ConsultaAgenciamentosParaCriacaoDeNovo(FiltroAgenciamento filtro) {
        StringBuilder texto = new StringBuilder();

        texto.append(" select 1 from {Agenciamento} agm ")
             .append(" where agm.{agencia} = :agencia ")
             .append(" and agm.{embarcacao} = :embarcacao ")
             .append(" and agm.{porto} = :porto ")
             .append(" and agm.{eta} >= :etaInicial ")
             .append(" and agm.{eta} <= :etaFinal ")
             .append(" and agm.{statusEmbarcacao} <> :cancelado")
             ;
            
        String hql = texto.toString();
        hql = hql.replace("{Agenciamento}", Agenciamento.class.getSimpleName())
                .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                .replace("{porto}", Agenciamento.PROP_PORTO)
                .replace("{embarcacao}", Agenciamento.PROP_EMBARCACAO)
                .replace("{statusEmbarcacao}", Agenciamento.PROP_STATUS_EMBARCACAO)
                .replace("{eta}", Agenciamento.PROP_ETA)
                ;
        
        this.setText(hql);
        addParameter("agencia", filtro.getAgencia());
        addParameter("embarcacao", filtro.getEmbarcacao());
        addParameter("porto", filtro.getPorto());
        addParameter("etaInicial", filtro.getEtaInicial());
        addParameter("etaFinal", filtro.getEtaFinal());
        addParameter("cancelado", StatusEmbarcacao.CANCELADO);
        
    }
    
}
