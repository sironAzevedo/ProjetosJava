package br.com.petrobras.sistam.service.query.manobra;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.snarf.persistence.BusinessQuery;

public class ConsultaManobraPorAgenciamento extends BusinessQuery<Manobra> {
    
    public ConsultaManobraPorAgenciamento(Agenciamento agenciamento) {
        StringBuilder texto = new StringBuilder();        

        texto
                .append(" select distinct m from {Manobra} m ")
                .append(" join fetch m.{pontoInicial} pi ")
                .append(" join fetch pi.{pontoOperacionalInicial} poi ")  
                .append(" join fetch poi.{portoPontoOpInicial} ppoi ")                                           
                .append(" join fetch m.{pontoFinal} pf ")
                .append(" join fetch pf.{pontoOperacionalFinal} pof ")  
                .append(" join fetch pof.{portoPontoOpFinal} ppof ") 
                .append(" join fetch m.{responsavelCusto} rc ")
                .append(" left join fetch m.{servicos} serv ")
                .append(" left join fetch serv.{empresas} em ")
                .append(" join fetch m.agenciamento a ")
                .append(" join fetch a.agencia ag ")
                .append(" where m.{agenciamento} = :agenciamento");
        
        addParameter("agenciamento", agenciamento);
        
        String hql = texto.toString();

        hql = hql.replace("{Manobra}", Manobra.class.getSimpleName())
                 .replace("{pontoInicial}", Manobra.PROP_PONTO_ATRACACAO_ORIGEM)
                 .replace("{pontoOperacionalInicial}", PontoAtracacao.PROP_PONTO_OPERACIONAL)
                 .replace("{portoPontoOpInicial}", PontoOperacional.PROP_PORTO)
                 .replace("{pontoFinal}", Manobra.PROP_PONTO_ATRACACAO_DESTINO)
                 .replace("{pontoOperacionalFinal}", PontoAtracacao.PROP_PONTO_OPERACIONAL)
                 .replace("{portoPontoOpFinal}", PontoOperacional.PROP_PORTO)
                 .replace("{agenciamento}", Manobra.PROP_AGENCIAMENTO)
                 .replace("{responsavelCusto}", Manobra.PROP_RESPONSAVEL_CUSTO)
                 .replace("{empresas}", ServicoManobra.PROP_EMPRESA_MARITIMA)
                 .replace("{servicos}", Manobra.PROP_SERVICOS)
               ;
        
        this.setText(hql);
    }
    
}
