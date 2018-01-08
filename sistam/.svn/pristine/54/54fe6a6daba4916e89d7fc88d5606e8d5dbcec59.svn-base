package br.com.petrobras.sistam.service.query.operacao;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioSiscomex;
import br.com.petrobras.sistam.service.query.SistamBusinessQuery;

public class ConsultaDocumentacaoCabotagemPorFiltroSiscomex extends SistamBusinessQuery<DocumentacaoCabotagem> {

    public ConsultaDocumentacaoCabotagemPorFiltroSiscomex(FiltroRelatorioSiscomex filtro) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select distinct(dc) from {DocumentacaoCabotagem} dc")
                .append(" left join fetch dc.{agenciaOrigem}")
                .append(" left join fetch dc.{porto} port")
                .append(" left join fetch port.{pais}")
                .append(" left join fetch dc.{documentacaoProdutos} dop")
                .append(" left join fetch dop.{operacao} op")
                .append(" left join fetch op.{produto} prod")
                .append(" left join fetch dc.{agenciamento} agm")      
                .append(" left join fetch agm.{agencia} ag")
                .append(" left join fetch agm.{embarcacao} nav")
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

        if (!filtro.getTiposOperacao().isEmpty()) {
            hql.append(" and op.{tipoOperacao} in (:tiposOperacao)");
            addParameter("tiposOperacao", filtro.getTiposOperacao());
        }

        if (!filtro.getTiposContrato().isEmpty()) {
            hql.append(" and agm.{tipoContrato} in (:tiposContrato)");
            addParameter("tiposContrato", filtro.getTiposContrato());
        }

        if (!filtro.getSituacoesEmbarcacao().isEmpty()) {
            hql.append(" and agm.{statusEmbarcacao} in (:situacoesEmbarcacao)");
            addParameter("situacoesEmbarcacao", filtro.getSituacoesEmbarcacao());
        }
        else {
            hql.append(" and agm.{statusEmbarcacao} != 8");
        }

        if (filtro.getComManisfesto() != null) {
            if (filtro.isComManifestoSim()) {
                hql.append(" and dc.{manifestoEletronico} is not null");
            } else {
                hql.append(" and dc.{manifestoEletronico} is null");
            }
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
            hql.append(" order by nav.{nomeCompletoNavio}, agm.{numeroViagem}, dc.{porto}");
        } else if (filtro.isOrdenacaoPorNumeroProcesso()) {
            hql.append(" order by ag.{siglaAgencia}, agm.{anoProcesso}, agm.{codigoProcesso}, dc.{porto}");
        } else if (filtro.isOrdenacaoPorDataChegada()) {
            hql.append(" order by agm.{dataChegada}, agm.{eta}, dc.{porto}");
        }

        this.setText(hql.toString().replace("{DocumentacaoCabotagem}", DocumentacaoCabotagem.class.getSimpleName())
                .replace("{agenciaOrigem}", DocumentacaoCabotagem.PROP_AGENCIA)
                .replace("{manifestoEletronico}", DocumentacaoCabotagem.PROP_MANIFESTO_ELETRONICO)
                .replace("{documentacaoProdutos}", DocumentacaoCabotagem.PROP_DOCUMENTACAO_PRODUTOS)
                .replace("{operacao}", DocumentacaoOperacao.PROP_OPERACAO)
                .replace("{produto}", Operacao.PROP_PRODUTO)
                .replace("{porto}", DocumentacaoCabotagem.PROP_PORTO)
                .replace("{pais}", Porto.PROP_PAIS)
                .replace("{agenciamento}", DocumentacaoCabotagem.PROP_AGENCIAMENTO)
                .replace("{agenciamentoViagem}", Agenciamento.PROP_AGENCIAMENTO_VIAGEM)
                .replace("{portoOrigem}", Agenciamento.PROP_PORTO_ORIGEM)
                .replace("{portoDestino}", Agenciamento.PROP_PORTO_DESTINO)
                .replace("{numeroViagem}", Agenciamento.PROP_VGM)
                .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                .replace("{anoProcesso}", Agenciamento.PROP_ANO_PROCESSO)
                .replace("{codigoProcesso}", Agenciamento.PROP_CODIGO)
                .replace("{siglaAgencia}", Agencia.PROP_SIGLA)
                .replace("{idAgencia}", Agencia.PROP_ID)
                .replace("{numeroEscalaMercante}", Agenciamento.PROP_NUMERO_ESCALA_MERCANTE)
                .replace("{embarcacao}", Agenciamento.PROP_EMBARCACAO)
                .replace("{nomeCompletoNavio}", Embarcacao.PROP_NOME_COMPLETO)
                .replace("{tipoOperacao}", Operacao.PROP_TIPO_OPERACAO)
                .replace("{tipoContrato}", Agenciamento.PROP_TIPO_CONTRATO)
                .replace("{statusEmbarcacao}", Agenciamento.PROP_STATUS_EMBARCACAO)
                .replace("{dataChegada}", Agenciamento.PROP_DATA_OFICIAL_CHEGADA)
                .replace("{dataSaida}", Agenciamento.PROP_DATA_SAIDA)
                .replace("{dataInclusao}", Agenciamento.PROP_DATA_INCLUSAO)
                .replace("{eta}", Agenciamento.PROP_ETA)
                .replace("{etaProximoPorto}", Agenciamento.PROP_ETA_PROX_PORTO)
                .replace("{dataEstimadaSaida}", Agenciamento.PROP_DATA_ESTIMADA_SAIDA)
                .replace("{dataInicio}", Operacao.PROP_DATA_INICIO)
                .replace("{dataFim}", Operacao.PROP_DATA_FIM));
    }
}
