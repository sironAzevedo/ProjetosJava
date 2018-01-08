package br.com.petrobras.sistam.service.query.manobra;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.StatusManobra;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioManobra;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ConsultaManobrasPorFiltro extends BusinessQuery<Manobra> {

    public ConsultaManobrasPorFiltro(FiltroRelatorioManobra filtro) {
        final List<Long> responsaveisId = new ArrayList<Long>();

        StringBuilder hql = new StringBuilder();

        hql.append(" select distinct m from {Manobra} m")
                .append(" join fetch m.{responsavelCusto} rcusto ")
                .append(" join fetch m.{ptAtracacaoOrigem} pto ")
                .append(" join fetch pto.{pontoOperacional} ptop ")
                .append(" join fetch ptop.{portoOp} portor ")
                .append(" left join fetch m.{ptAtracacaoDestino} ptd ")
                .append(" left join fetch ptd.{pontoOperacional} ptdp ")
                .append(" left join fetch ptdp.{portoOp} portde ")
                .append(" left join fetch m.{ptAtracacaoEscala} pte ")
                .append(" join fetch m.{agenciamento} agm ")
                .append(" join fetch agm.{porto} port ")
                .append(" join fetch agm.{embarcacao} emb ")
                .append(" join fetch agm.{agencia} ag ")
                .append(" left join fetch m.{servicos} s ")
                .append(" left join fetch s.{empMaritima} empm ")
                .append(" left join fetch s.{responsaveis} resp ")
                .append(" where ag = :agencia")
                .append(" and m.{status} in (:status)");
        //.append(" and m.{respCusto}.{descricao} != :naoResponsaveis ");

        addParameter("agencia", filtro.getAgencia());
        addParameter("status", Arrays.asList(new StatusManobra[]{StatusManobra.ENCERRADA, StatusManobra.CANCELADA_FORA_PRAZO}));
        //addParameter("naoResponsaveis","SC"); 

        if (filtro.getPorto() != null) {
            hql.append(" and port = :porto");
            addParameter("porto", filtro.getPorto());
        }

        if (filtro.getTipoContrato() != null) {
            hql.append(" and agm.{tipoContrato} = :tipoContrato");
            addParameter("tipoContrato", filtro.getTipoContrato());
        }

        Date periodoInicio = filtro.getPeriodoInicio();
        if (periodoInicio != null) {
            periodoInicio = SistamDateUtils.alterarHorarioParaInicioDia(periodoInicio, null);
        }
        Date periodoTermino = filtro.getPeriodoTermino();
        if (periodoTermino != null) {
            periodoTermino = SistamDateUtils.alterarHorarioParaFimDia(periodoTermino, null);
        }

        if (periodoInicio != null && periodoTermino != null) {
            hql.append(" and (")
                    .append(" (m.{dataInicio} >= :periodoInicio and m.{dataInicio} < :periodoTermino) ")
                    .append(" or (m.{dataInicio} > :periodoInicio and m.{dataInicio} <= :periodoTermino) ")
                    .append(" or (m.{dataInicio} = :periodoInicio and m.{dataInicio} = :periodoTermino) ")
                    .append(" ) ");
            addParameter("periodoInicio", periodoInicio);
            addParameter("periodoTermino", periodoTermino);
        } else if (periodoInicio != null) {
            hql.append(" and m.{dataInicio} >= :periodoInicio ");
            addParameter("periodoInicio", periodoInicio);
        } else if (periodoTermino != null) {
            hql.append(" and m.{dataInicio} <= :periodoTermino ");
            addParameter("periodoTermino", periodoTermino);
        }

        for (ResponsavelCustoEntity responsavelCustoEntity : filtro.getResponsaveis()) {
            if (responsavelCustoEntity.getId() != null) {
                responsaveisId.add(responsavelCustoEntity.getId());
            }
        }

        if (!filtro.getResponsaveis().isEmpty() && !responsaveisId.isEmpty()) {

            hql.append(" and rcusto.{idRespCusto} in (:responsaveis)");
            addParameter("responsaveis", responsaveisId);
        }
        else
        {
            hql.append(" and rcusto.descricao not in (:semCusto)");
            addParameter("semCusto", ResponsavelCusto.SEM_CUSTO.getPorExtenso());
        }

        hql.append(" order by m.{dataInicio}");

        String text = hql.toString().replace("{Manobra}", Manobra.class.getSimpleName())
                .replace("{status}", Manobra.PROP_STATUS)
                .replace("{ptAtracacaoOrigem}", Manobra.PROP_PONTO_ATRACACAO_ORIGEM)
                .replace("{ptAtracacaoDestino}", Manobra.PROP_PONTO_ATRACACAO_DESTINO)
                .replace("{ptAtracacaoEscala}", Manobra.PROP_PONTO_ATRACACAO_ESCALA)
                .replace("{pontoOperacional}", PontoAtracacao.PROP_PONTO_OPERACIONAL)
                .replace("{porto}", Agenciamento.PROP_PORTO)
                .replace("{portoOp}", PontoOperacional.PROP_PORTO)
                .replace("{agenciamento}", Manobra.PROP_AGENCIAMENTO)
                .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                .replace("{servicos}", Manobra.PROP_SERVICOS)
                .replace("{tipoContrato}", Agenciamento.PROP_TIPO_CONTRATO)
                .replace("{dataInicio}", Manobra.PROP_DATA_INICIO)
                .replace("{responsavelCusto}", Manobra.PROP_RESPONSAVEL_CUSTO)
                .replace("{idRespCusto}", ResponsavelCustoEntity.PROP_ID)
                //.replace("{descricao}", ResponsavelCustoEntity.PROP_DESCRICAO)
                .replace("{embarcacao}", Agenciamento.PROP_EMBARCACAO)
                .replace("{empMaritima}", ServicoManobra.PROP_EMPRESA_MARITIMA)
                .replace("{responsaveis}", ServicoManobra.PROP_RESPONSAVEIS);

        this.setText(text);
    }
}
