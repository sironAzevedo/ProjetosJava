package br.com.petrobras.sistam.service.query.operacao;

import br.com.petrobras.sistam.service.query.SistamBusinessQuery;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioSiscomex;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;

public class ConsultaSemOperacaoPorFiltroSiscomex extends SistamBusinessQuery<Agenciamento> {

    public ConsultaSemOperacaoPorFiltroSiscomex(FiltroRelatorioSiscomex filtro) {
        StringBuilder hql = new StringBuilder();

        hql.append(" select agm from Agenciamento agm")
                .append(" join fetch agm.agencia ag")
                .append(" join fetch agm.embarcacao nav")
                .append(" left join fetch agm.agenciementoViagem agVgm") 
                .append(" left join fetch agm.portoOrigem portoOrigem")
                .append(" left join fetch agm.portoDestino portoDestino")
                .append(" where not exists (select op from Operacao op where agm = op.agenciamento)");

        if (filtro.getAgencia() != null) {
            hql.append(" and ag.id = :idAgencia");
            addParameter("idAgencia", filtro.getAgencia().getId());
        } 
        
        if(filtro.isPortoPreenchido()){
            hql.append(" and agm.porto = :porto");
            addParameter("porto", filtro.getPorto());
        }

        if (!filtro.getTiposContrato().isEmpty()) {
            hql.append(" and agm.tipoContrato in (:tiposContrato)");
            addParameter("tiposContrato", filtro.getTiposContrato());
        }

        if (!filtro.getSituacoesEmbarcacao().isEmpty()) {
            hql.append(" and agm.statusEmbarcacao in (:situacoesEmbarcacao)");
            addParameter("situacoesEmbarcacao", filtro.getSituacoesEmbarcacao());
        }
        else {
            hql.append(" and agm.statusEmbarcacao != (:cancelado)");
            addParameter("cancelado", StatusEmbarcacao.CANCELADO);
        }

        if (filtro.getComEscalaMercante() != null) {
            if (filtro.isComEscalaMercanteSim()) {
                hql.append(" and agm.numeroEscalaMercante is not null");
            } else {
                hql.append(" and agm.numeroEscalaMercante is null");
            }
        }

        adicionarCondicaoPeriodo(hql, filtro.getPeriodoOficialChegada(), "agm.dataChegada");
        adicionarCondicaoPeriodo(hql, filtro.getPeriodoOficialSaida(), "agm.dataSaida");
        adicionarCondicaoPeriodoZerandoAsHoras(hql, filtro.getPeriodoInclusao(), "agm.dataInclusao");
        adicionarCondicaoPeriodo(hql, filtro.getPeriodoEta(), "agm.eta");
        adicionarCondicaoPeriodo(hql, filtro.getPeriodoEts(), "agm.dataEstimadaSaida");

        if (filtro.isOrdenacaoPorNavioEViagem()) {
            hql.append(" order by nav.nomeCompleto, agm.vgm");
        } else if (filtro.isOrdenacaoPorNumeroProcesso()) {
            hql.append(" order by ag.sigla, agm.anoProcesso, agm.codigo");
        } else if (filtro.isOrdenacaoPorDataChegada()) {
            hql.append(" order by agm.dataChegada, agm.eta ");
        }

        this.setText(hql.toString());
    }
}
