package br.com.petrobras.sistam.service.query.agenciamento;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.valueobjects.FiltroAgenciamento;
import br.com.petrobras.snarf.common.util.DateBuilder;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class ConsultaAgenciamentosPorFiltro extends BusinessQuery<Agenciamento> {

    public ConsultaAgenciamentosPorFiltro(FiltroAgenciamento filtro) {
        
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder texto = new StringBuilder();

        TimeZone zone = null;

        texto
                .append(" select distinct agm from {Agenciamento} agm ")
                .append(" join fetch agm.{agencia} ag ")
                .append(" join fetch agm.{embarcacao} e ")
                .append(" left join fetch agm.{porto} por ")
                .append(" left join fetch agm.{portoOrigem} porOrigem ")
                .append(" left join fetch agm.{portoDestino} porDestino ")
                .append(" where 1 = 1");

        if (filtro.getAgencia() != null) {
            texto.append(" and agm.{agencia} = :agencia ");
            map.put("agencia", filtro.getAgencia());
            zone = filtro.getAgencia().obterTimezone();
        }

        if (filtro.getEmbarcacao() != null) {
            texto.append(" and agm.{embarcacao} = :embarcacao ");
            map.put("embarcacao", filtro.getEmbarcacao());
        }
        
        if (filtro.getPorto() != null) {
            texto.append(" and agm.{porto} = :porto ");
            map.put("porto", filtro.getPorto());
        }
        
        if (filtro.getAreaNavegacao() != null) {
            texto.append(" and agm.{areaNavegacao} = :areaNavegacao ");
            map.put("areaNavegacao", filtro.getAreaNavegacao());
        }
        
        if (filtro.getNumeroProcesso() != null && !filtro.getNumeroProcesso().isEmpty()) {
            texto.append(" and agm.{numeroProcessoDespacho} = :numeroProcessoDespacho ");
            map.put("numeroProcessoDespacho", filtro.getNumeroProcesso());
        }
        
        if (filtro.getStatusEmbarcacao() != null) {
            texto.append(" and agm.{statusEmbarcacao} = :statusEmbarcacao ");
            map.put("statusEmbarcacao", filtro.getStatusEmbarcacao());
        }
        
        if (filtro.getTipoContrato() != null) {
            texto.append(" and agm.{tipoContrato} = :tipoContrato ");
            map.put("tipoContrato", filtro.getTipoContrato());
        }
        
        if (filtro.getVgm() != null && !filtro.getVgm().isEmpty()) {
            texto.append(" and agm.{vgm} = :vgm ");
            map.put("vgm", filtro.getVgm());
        }
        
        if (filtro.getPortoOrigem() != null) {
            texto.append(" and agm.{portoOrigem} = :portoOrigem ");
            map.put("portoOrigem", filtro.getPortoOrigem());
        }
        
        if (filtro.getPortoDestino() != null) {
            texto.append(" and agm.{portoDestino} = :portoDestino ");
            map.put("portoDestino", filtro.getPortoDestino());
        }
        
        if (filtro.getDataSaidaIni() != null) {
             texto.append(" and agm.{dataSaida} >= :dataSaidaIni ");
            map.put("dataSaidaIni", DateBuilder.on(filtro.getDataSaidaIni()).at(0, 0, 0).getTime(zone));
        }
        
        if (filtro.getDataSaidaFim()!= null) {
             texto.append(" and agm.{dataSaida} <= :dataSaidaFim ");
            map.put("dataSaidaFim", DateBuilder.on(filtro.getDataSaidaFim()).at(0, 0, 0).getTime(zone));
        }
        
        if (filtro.getDataChegadaIni()!= null) {
             texto.append(" and agm.{dataChegada} >= :dataChegadaIni ");
            map.put("dataChegadaIni", DateBuilder.on(filtro.getDataChegadaIni()).at(0, 0, 0).getTime(zone));
        }
        
        if (filtro.getDataChegadaFim()!= null) {
             texto.append(" and agm.{dataChegada} <= :dataChegadaFim ");
            map.put("dataChegadaFim", DateBuilder.on(filtro.getDataChegadaFim()).at(0, 0, 0).getTime(zone));
        }
        
        
        if (filtro.getDataInclusaoIni()!= null) {
             texto.append(" and agm.{dataInclusao} >= :dataInclusaoIni ");
            map.put("dataInclusaoIni", DateBuilder.on(filtro.getDataInclusaoIni()).at(0, 0, 0).getTime(zone));
        }
        
        if (filtro.getDataInclusaoFim()!= null) {
             texto.append(" and agm.{dataInclusao} <= :dataInclusaoFim ");
            map.put("dataInclusaoFim", DateBuilder.on(filtro.getDataInclusaoFim()).at(0, 0, 0).getTime(zone));
        }
        
        String hql = texto.toString();
        hql = hql.replace("{Agenciamento}", Agenciamento.class.getSimpleName())
                .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                .replace("{porto}", Agenciamento.PROP_PORTO)
                .replace("{embarcacao}", Agenciamento.PROP_EMBARCACAO)
                .replace("{areaNavegacao}", Agenciamento.PROP_AREA_NAVEGACAO)
                .replace("{numeroProcessoDespacho}", Agenciamento.PROP_NUMERO_PROCESSO_DESPACHO)
                .replace("{statusEmbarcacao}", Agenciamento.PROP_STATUS_EMBARCACAO)
                .replace("{tipoContrato}", Agenciamento.PROP_TIPO_CONTRATO)
                .replace("{vgm}", Agenciamento.PROP_VGM)
                .replace("{portoOrigem}", Agenciamento.PROP_PORTO_ORIGEM)
                .replace("{portoDestino}", Agenciamento.PROP_PORTO_DESTINO)
                .replace("{dataSaida}", Agenciamento.PROP_DATA_SAIDA)
                .replace("{dataChegada}", Agenciamento.PROP_DATA_OFICIAL_CHEGADA)
                .replace("{dataInclusao}", Agenciamento.PROP_DATA_INCLUSAO);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
