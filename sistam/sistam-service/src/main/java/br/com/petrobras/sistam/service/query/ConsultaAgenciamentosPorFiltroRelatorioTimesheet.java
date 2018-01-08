package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.CancelamentoAgenciamento;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioTimesheet;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.List;
import java.util.ArrayList;

public class ConsultaAgenciamentosPorFiltroRelatorioTimesheet extends BusinessQuery<Agenciamento> {

    public ConsultaAgenciamentosPorFiltroRelatorioTimesheet(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas) {

        StringBuilder texto = new StringBuilder();
        texto.append(" select distinct agm from {Agenciamento} agm")
                .append(" join fetch agm.{agencia} ag")
                .append(" join fetch agm.{embarcacao} e")
                .append(" join fetch agm.{porto} por")
                .append(" left join fetch agm.{portoOrigem} porOrg")
                .append(" left join fetch agm.{portoDestino} porDtn")
                .append(" left join fetch agm.{manobras} man ")
                .append(" left join fetch man.{pontoAtracacaoDestino} pad ")
                .append(" where 1 = 1");

        if (filtro.getDataDeCorte() != null) {
            texto.append(" and (agm.{dataSaida} is null or agm.{dataSaida} >= :dataDeCorte)");
            addParameter("dataDeCorte", filtro.getDataDeCorte());
        }

        if (filtro.getAgencia() != null) {
            texto.append(" and ag = :agencia");
            addParameter("agencia", filtro.getAgencia());

            if (filtro.getPorto() != null) {
                texto.append(" and por = :porto");
                addParameter("porto", filtro.getPorto());
            }
        } else if (!agenciasAutorizadas.isEmpty()) {
            texto.append(" and ag in (:agencias)");
            addParameter("agencias", agenciasAutorizadas);
        }

        if (filtro.getTipoFrota() != null) {
            texto.append(" and agm.{tipoFrota} = :tipoFrota");
            addParameter("tipoFrota", filtro.getTipoFrota());
        }

        if (!filtro.getEmbarcacoes().isEmpty()) {
            texto.append(" and e in (:embarcacoes)");
            addParameter("embarcacoes", filtro.getEmbarcacoes());
        }

        texto.append(" and agm.{statusEmbarcacao} not in (:statusEmbarcacao)");
        List<StatusEmbarcacao> status = new ArrayList<StatusEmbarcacao>();
        status.add(StatusEmbarcacao.ESPERADO);
        status.add(StatusEmbarcacao.CANCELADO);
        status.add(StatusEmbarcacao.DESVIADO);
        addParameter("statusEmbarcacao", status);

        texto.append(" order by e.{nomeCompleto}");

        String hql = texto.toString();
        hql = hql.replace("{Agenciamento}", Agenciamento.class.getSimpleName())
                .replace("{desvio}", Agenciamento.PROP_DESVIO)
                .replace("{cancelamento}", Agenciamento.PROP_CANCELAMENTO)
                .replace("{dataSaida}", Agenciamento.PROP_DATA_SAIDA)
                .replace("{dataDesvio}", Desvio.PROP_DATA)
                .replace("{data}", CancelamentoAgenciamento.PROP_DATA)
                .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                .replace("{embarcacao}", Agenciamento.PROP_EMBARCACAO)
                .replace("{porto}", Agenciamento.PROP_PORTO)
                .replace("{portoOrigem}", Agenciamento.PROP_PORTO_ORIGEM)
                .replace("{portoDestino}", Agenciamento.PROP_PORTO_DESTINO)
                .replace("{statusEmbarcacao}", Agenciamento.PROP_STATUS_EMBARCACAO)
                .replace("{tipoFrota}", Agenciamento.PROP_FROTA)
                .replace("{nomeCompleto}", Embarcacao.PROP_NOME_COMPLETO)
                .replace("{manobras}", Agenciamento.PROP_MANOBRAS)
                .replace("{pontoAtracacaoDestino}", Manobra.PROP_PONTO_ATRACACAO_DESTINO);;

        this.setText(hql);
    }
}
