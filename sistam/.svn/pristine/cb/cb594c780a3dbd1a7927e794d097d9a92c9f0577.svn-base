package br.com.petrobras.sistam.service.query.operacao;

import br.com.petrobras.sistam.service.query.SistamBusinessQuery;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioSiscomex;

public class ConsultaSemOperacaoComercialPorFiltroSiscomex extends SistamBusinessQuery<Operacao> {

    public ConsultaSemOperacaoComercialPorFiltroSiscomex(FiltroRelatorioSiscomex filtro) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select distinct(op) from {Operacao} op")
                .append(" left join fetch op.{produto} prod")
                .append(" join fetch op.{agenciamento} agm")
                .append(" join fetch agm.{agencia} ag")
                .append(" join fetch agm.{porto} por")
                .append(" join fetch agm.{embarcacao} nav")
                .append(" left join fetch agm.{agenciamentoViagem} agVgm")
                .append(" left join fetch agm.{portoOrigem} portoOrigem")
                .append(" left join fetch agm.{portoDestino} portoDestino")
                .append(" where 1 = 1");

        if (filtro.getAgencia() != null) {
            hql.append(" and ag.{idAgencia} = :idAgencia");
            addParameter("idAgencia", filtro.getAgencia().getId());
        }
        
        if (filtro.isPortoPreenchido()) {
            hql.append(" and agm.{porto} = :porto ");
            addParameter("porto", filtro.getPorto());
        }

        hql.append(" and op.{tipoOperacao} IN (:tipoOperacaoSemOperacaoComercial, :tipoOperacaoAbastecimento, :tipoOperacaoShipToShip)");
        addParameter("tipoOperacaoSemOperacaoComercial", TipoOperacao.SEM_OPERACAO_COMERCIAL);
        addParameter("tipoOperacaoAbastecimento", TipoOperacao.ABASTECIMENTO);
        addParameter("tipoOperacaoShipToShip", TipoOperacao.SHIPTOSHIP);
        
        if (!filtro.getTiposContrato().isEmpty()) {
            hql.append(" and agm.{tipoContrato} in (:tiposContrato)");
            addParameter("tiposContrato", filtro.getTiposContrato());
        }

        if (!filtro.getSituacoesEmbarcacao().isEmpty()) {
            hql.append(" and agm.{statusEmbarcacao} in (:situacoesEmbarcacao)");
            addParameter("situacoesEmbarcacao", filtro.getSituacoesEmbarcacao());
        }
        else {
            hql.append(" and agm.{statusEmbarcacao} != (:cancelado)");
            addParameter("cancelado", StatusEmbarcacao.CANCELADO);
        }

        if (filtro.getComEscalaMercante() != null) {
            if (filtro.isComEscalaMercanteSim()) {
                hql.append(" and agm.{numeroEscalaMercante} is not null");
            } else {
                hql.append(" and agm.{numeroEscalaMercante} is null");
            }
        }

        adicionarCondicaoPeriodo(hql, filtro.getPeriodoOficialChegada(), "agm.{dataChegada}");
        adicionarCondicaoPeriodo(hql, filtro.getPeriodoOficialSaida(), "agm.{dataSaida}");
        adicionarCondicaoPeriodoZerandoAsHoras(hql, filtro.getPeriodoInicioOperacao(), "op.{dataInicio}");
        adicionarCondicaoPeriodoZerandoAsHoras(hql, filtro.getPeriodoTerminoOperacao(), "op.{dataFim}");
        adicionarCondicaoPeriodoZerandoAsHoras(hql, filtro.getPeriodoInclusao(), "agm.{dataInclusao}");
        adicionarCondicaoPeriodo(hql, filtro.getPeriodoEta(), "agm.{eta}");
        adicionarCondicaoPeriodo(hql, filtro.getPeriodoEts(), "agm.{dataEstimadaSaida}");

        if (filtro.isOrdenacaoPorNavioEViagem()) {
            hql.append(" order by nav.{nomeCompletoNavio}, agm.{numeroViagem}");
        } else if (filtro.isOrdenacaoPorNumeroProcesso()) {
            hql.append(" order by ag.{siglaAgencia}, agm.{anoProcesso}, agm.{codigoProcesso}");
        } else if (filtro.isOrdenacaoPorDataChegada()) {
            hql.append(" order by agm.{dataChegada}, agm.{eta} ");
        }

        this.setText(hql.toString().replace("{Operacao}", Operacao.class.getSimpleName())
                .replace("{dataInicio}", Operacao.PROP_DATA_INICIO)
                .replace("{dataFim}", Operacao.PROP_DATA_FIM)
                .replace("{produto}", Operacao.PROP_PRODUTO)
                .replace("{agenciamento}", Operacao.PROP_AGENCIAMENTO)
                .replace("{agenciamentoViagem}", Agenciamento.PROP_AGENCIAMENTO_VIAGEM)
                .replace("{porto}", Agenciamento.PROP_PORTO)
                .replace("{portoOrigem}", Agenciamento.PROP_PORTO_ORIGEM)
                .replace("{portoDestino}", Agenciamento.PROP_PORTO_DESTINO)
                .replace("{numeroViagem}", Agenciamento.PROP_VGM)
                .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                .replace("{anoProcesso}", Agenciamento.PROP_ANO_PROCESSO)
                .replace("{codigoProcesso}", Agenciamento.PROP_CODIGO)
                .replace("{numeroEscalaMercante}", Agenciamento.PROP_NUMERO_ESCALA_MERCANTE)
                .replace("{dataChegada}", Agenciamento.PROP_DATA_OFICIAL_CHEGADA)
                .replace("{dataSaida}", Agenciamento.PROP_DATA_SAIDA)
                .replace("{dataInclusao}", Agenciamento.PROP_DATA_INCLUSAO)
                .replace("{eta}", Agenciamento.PROP_ETA)
                .replace("{dataEstimadaSaida}", Agenciamento.PROP_DATA_ESTIMADA_SAIDA)
                .replace("{embarcacao}", Agenciamento.PROP_EMBARCACAO)
                .replace("{nomeCompletoNavio}", Embarcacao.PROP_NOME_COMPLETO)
                .replace("{idAgencia}", Agencia.PROP_ID)
                .replace("{siglaAgencia}", Agencia.PROP_SIGLA)
                .replace("{tipoOperacao}", Operacao.PROP_TIPO_OPERACAO)
                .replace("{tipoContrato}", Agenciamento.PROP_TIPO_CONTRATO)
                .replace("{statusEmbarcacao}", Agenciamento.PROP_STATUS_EMBARCACAO));
    }
}
