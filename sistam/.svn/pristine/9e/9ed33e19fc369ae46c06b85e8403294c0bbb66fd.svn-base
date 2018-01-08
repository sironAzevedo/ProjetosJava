package br.com.petrobras.sistam.service.query.agenciamento.taxa;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.valueobjects.FiltroTaxa;
import br.com.petrobras.sistam.common.valueobjects.TaxaAcumuladoVO;
import br.com.petrobras.snarf.persistence.BusinessQuery;

/**
 * Buscar o valor acumulado em um período de teterminados tipos de taxa.
 */
public class ConsultaTaxasValorAcumulado extends BusinessQuery<TaxaAcumuladoVO> {

    public ConsultaTaxasValorAcumulado(FiltroTaxa filtro) {
        StringBuilder texto = new StringBuilder();
        
        texto.append(" select new {TaxaAcumuladoVO}(t.{tipoTaxa}, sum(t.{valor})) ")
             .append(" from {Taxa} t ")
             .append(" join t.{agenciamento} a ")
             .append(" join a.{agencia} ag ")
             .append(" where ag = :agencia ")
             .append(" and t.{dataPagamento} >= :dataInicial ")
             .append(" and t.{dataPagamento} <= :dataFinal ")
             .append(" and t.{tipoTaxa} in (:tiposTaxa) ")
             .append(" group by t.{tipoTaxa} ")
             .append(" order by t.{tipoTaxa}")
             ;
        
        String hql = texto.toString();     
                
        hql = hql.replace("{TaxaAcumuladoVO}", TaxaAcumuladoVO.class.getName())
                 .replace("{Taxa}", Taxa.class.getSimpleName())
                 .replace("{agenciamento}", Taxa.PROP_AGENCIAMENTO)
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{dataPagamento}", Taxa.PROP_DATA_PAGAMENTO)
                 .replace("{tipoTaxa}", Taxa.PROP_TIPO_TAXA)
                 .replace("{valor}", Taxa.PROP_VALOR);
        
        this.setText(hql);
        addParameter("agencia", filtro.getAgencia());
        addParameter("dataInicial", filtro.getDataPagamentoInicial());
        addParameter("dataFinal", filtro.getDataPagamentoFinal());
        addParameter("tiposTaxa", filtro.getListaTipoTaxa());
    }
    
}
