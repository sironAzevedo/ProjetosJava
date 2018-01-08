package br.com.petrobras.sistam.service.query.agenciamento.visita;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.Transporte;
import br.com.petrobras.sistam.common.entity.Visita;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioVisita;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ConsultaTransportesPorFiltroRelatorio extends BusinessQuery<Transporte> {

    public ConsultaTransportesPorFiltroRelatorio(FiltroRelatorioVisita filtro) {
        
        final List<Long> responsaveisId = new ArrayList<Long>();
        for (ResponsavelCustoEntity responsavelCustoEntity : filtro.getResponsaveis()) {
            if (responsavelCustoEntity.getId() != null) {
                responsaveisId.add(responsavelCustoEntity.getId());
            }
        }
        
        StringBuilder hql = new StringBuilder();

        hql.append(" select tr from {Transporte} tr")
                .append(" join fetch tr.{visita} v ")
                .append(" join fetch v.{agenciamento} agm ")
                .append(" join fetch agm.{navio} nav ")
                .append(" join fetch agm.{agencia} ag")
                .append(" join fetch agm.{porto} pt")
                .append(" where ag = :agencia");

        addParameter("agencia", filtro.getAgencia());

        if (filtro.getPorto() != null) {
            hql.append(" and pt = :porto");
            addParameter("porto", filtro.getPorto());
        }

        if (filtro.getAgente() != null) {
            hql.append(" and v.{chaveAgente} = :chaveAgente");
            addParameter("chaveAgente", filtro.getAgente().getChave());
        }

        if (!filtro.getResponsaveis().isEmpty()&& !responsaveisId.isEmpty()) {
            hql.append(" and tr.{respCusto}.{idRespCusto} in (:responsaveis)");
            addParameter("responsaveis", responsaveisId);
        }

        if (filtro.getTipoTransporte() != null) {
            hql.append(" and tr.{tipoTransporte} = :tipoTransporte");
            addParameter("tipoTransporte", filtro.getTipoTransporte());
        }

        Date periodoInicio = filtro.getInicio();
        if (periodoInicio != null) {
            periodoInicio = SistamDateUtils.alterarHorarioParaInicioDia(periodoInicio, null);
        }
        Date periodoTermino = filtro.getTermino();
        if (periodoTermino != null) {
            periodoTermino = SistamDateUtils.alterarHorarioParaFimDia(periodoTermino, null);
        }

        if (periodoInicio != null && periodoTermino != null) {
            hql.append(" and (")
                    .append(" (v.{dataInicio} >= :periodoInicio and v.{dataInicio} < :periodoTermino) ")
                    .append(" or (v.{dataInicio} > :periodoInicio and v.{dataInicio} <= :periodoTermino) ")
                    .append(" or (v.{dataInicio} = :periodoInicio and v.{dataInicio} = :periodoTermino) ")
                    .append(" ) ");
            addParameter("periodoInicio", periodoInicio);
            addParameter("periodoTermino", periodoTermino);
        } else if (periodoInicio != null) {
            hql.append(" and v.{dataInicio} >= :periodoInicio ");
            addParameter("periodoInicio", periodoInicio);
        } else if (periodoTermino != null) {
            hql.append(" and v.{dataInicio} <= :periodoTermino ");
            addParameter("periodoTermino", periodoTermino);
        }


        String text = hql.toString();
        text = text.replace("{Transporte}", Transporte.class.getSimpleName())
                .replace("{agenciamento}", Visita.PROP_AGENCIAMENTO)
                .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                .replace("{navio}", Agenciamento.PROP_EMBARCACAO)
                .replace("{porto}", Agenciamento.PROP_PORTO)
                .replace("{visita}", Transporte.PROP_VISITA)
                .replace("{dataInicio}", Visita.PROP_DATA_INICIO)
                .replace("{respCusto}", Transporte.PROP_RESPONSAVEL_CUSTO)
                .replace("{idRespCusto}", ResponsavelCustoEntity.PROP_ID)
                .replace("{tipoTransporte}", Transporte.PROP_TIPO_TRANSPORTE)
                .replace("{chaveAgente}", Visita.PROP_CHAVE_AGENTE);

        this.setText(text);
    }
}
