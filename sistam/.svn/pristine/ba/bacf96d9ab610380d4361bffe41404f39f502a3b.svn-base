package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.CancelamentoAgenciamento;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.enums.MotivoCancelamento;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.Date;
import java.util.List;

public class ConsultaAgenciamentosParaPainel extends BusinessQuery<Agenciamento> {

    public ConsultaAgenciamentosParaPainel(Agencia agencia, List<StatusEmbarcacao> listaStatus) {
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select distinct agm from {Agenciamento} agm ")
                .append(" join fetch agm.{embarcacao} e ")
                .append(" join fetch agm.{portoOrigem} ")
                .append(" left join fetch agm.{portoDestino} ")
                .append(" left join fetch agm.{operacoes} ops")
                .append(" left join fetch ops.{produto} prd")
                .append(" left join fetch agm.{documentos} ")
                .append(" left join fetch agm.{manobras} man ")
                .append(" left join fetch agm.{taxas} ")
                .append(" left join fetch agm.{desvio} d ")
                .append(" left join fetch d.portoDestinoAlterado ")
                .append(" left join agm.{cancelamento} c ")
                .append(" left join fetch man.{pontoAtracacaoDestino} pad ") 
                .append(" left join fetch pad.{pontoOperacional} po ")
                .append(" left join fetch po.{porto} ")
                .append(" where agm.{agencia} = :agencia")
                .append(" and agm.{statusEmbarcacao} in (:listaStatus) ")
                .append(" and ( agm.{dataSaida} is null ")
                .append("       or agm.{dataSaida} >= trunc(:dataAtual) - 2 ")
                .append("       or d.{dataDesvio} >= trunc(:dataAtual) - 7 )")
                .append("       and ( c.{dataCancelamento} is null or (c.{dataCancelamento} >= trunc(:dataAtual) - 2 and c.{motivo} <> :motivoOutros )  ) ")
                ;
        addParameter("agencia", agencia);
        addParameter("listaStatus", listaStatus);
        addParameter("dataAtual", new Date());
        addParameter("motivoOutros", MotivoCancelamento.OUTROS);
        
        String hql = texto.toString();
        hql = hql.replace("{Agenciamento}", Agenciamento.class.getSimpleName())
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{embarcacao}", Agenciamento.PROP_EMBARCACAO)
                 .replace("{portoOrigem}", Agenciamento.PROP_PORTO_ORIGEM)
                 .replace("{portoDestino}", Agenciamento.PROP_PORTO_DESTINO)
                 .replace("{documentos}", Agenciamento.PROP_DOCUMENTOS)
                 .replace("{operacoes}", Agenciamento.PROP_OPERACOES)
                 .replace("{statusEmbarcacao}", Agenciamento.PROP_STATUS_EMBARCACAO)
                 .replace("{manobras}", Agenciamento.PROP_MANOBRAS)
                 .replace("{pontoAtracacaoDestino}", Manobra.PROP_PONTO_ATRACACAO_DESTINO)
                 .replace("{responsavelCusto}", Manobra.PROP_RESPONSAVEL_CUSTO)
                 .replace("{pontoOperacional}", PontoAtracacao.PROP_PONTO_OPERACIONAL)
                 .replace("{porto}", PontoOperacional.PROP_PORTO)
                 .replace("{desvio}", Agenciamento.PROP_DESVIO)
                 .replace("{cancelamento}", Agenciamento.PROP_CANCELAMENTO)
                 .replace("{dataSaida}", Agenciamento.PROP_DATA_SAIDA)
                 .replace("{dataDesvio}", Desvio.PROP_DATA)
                 .replace("{dataCancelamento}", CancelamentoAgenciamento.PROP_DATA)
                 .replace("{motivo}", CancelamentoAgenciamento.PROP_MOTIVO)
                 .replace("{produto}", Operacao.PROP_PRODUTO)
                 .replace("{taxas}", Agenciamento.PROP_TAXAS)
                 ;
        
        this.setText(hql);
        
    }
    
}
