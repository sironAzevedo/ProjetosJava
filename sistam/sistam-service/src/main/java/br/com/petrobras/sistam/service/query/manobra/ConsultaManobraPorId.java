package br.com.petrobras.sistam.service.query.manobra;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.HashMap;
import java.util.Map;

public class ConsultaManobraPorId extends BusinessQuery<Manobra> {

    public ConsultaManobraPorId(Long id) {
        
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

            texto
                .append(" select m from {Manobra} m ")
                .append(" join fetch m.{agenciamento} a ")
                .append(" join fetch a.{porto} pt ")
                .append(" join fetch a.{embarcacao} em ")
                .append(" left join fetch em.{bandeira} ban ")
                .append(" join fetch m.{pontoInicial} pi ")
                .append(" join fetch pi.{ptOperacional} poi ")
                .append(" join fetch poi.{portoPPF} pppf ")
                .append(" join fetch m.{pontoFinal} pf ")
                .append(" join fetch pf.{ptOperacional} pof ")
                .append(" join fetch pof.{portoPPF} pppf ")
                .append(" join fetch m.{responsavelCusto} rc ")
                .append(" left join fetch m.{pontoEscala} pe ")
                .append(" join fetch a.{agencia} ag ")
                .append(" left join fetch m.{servicos} serv ")
                .append(" left join fetch serv.{empresaMaritima} emp")
                .append(" left join fetch serv.{responsaveis} ")
                
                .append(" where m.{id} = :id");
        
        map.put("id", id);
        
        String hql = texto.toString();     
                
        hql = hql.replace("{Manobra}", Manobra.class.getSimpleName())
                 .replace("{agenciamento}", Manobra.PROP_AGENCIAMENTO)
                 .replace("{porto}", Agenciamento.PROP_PORTO)
                 .replace("{embarcacao}", Agenciamento.PROP_EMBARCACAO)
                 .replace("{bandeira}", Embarcacao.PROP_BANDEIRA)
                 .replace("{pontoInicial}", Manobra.PROP_PONTO_ATRACACAO_ORIGEM)
                 .replace("{pontoFinal}", Manobra.PROP_PONTO_ATRACACAO_DESTINO)
                 .replace("{pontoEscala}", Manobra.PROP_PONTO_ATRACACAO_ESCALA)
                 .replace("{ptOperacional}", PontoAtracacao.PROP_PONTO_OPERACIONAL)
                 .replace("{portoPPF}", PontoOperacional.PROP_PORTO)
                 .replace("{agencia}", Agenciamento.PROP_AGENCIA)
                 .replace("{servicos}", Manobra.PROP_SERVICOS)
                 .replace("{empresaMaritima}", ServicoManobra.PROP_EMPRESA_MARITIMA)
                 .replace("{responsavelCusto}", Manobra.PROP_RESPONSAVEL_CUSTO)
                 .replace("{id}", Manobra.PROP_ID)
                 .replace("{responsaveis}", ServicoManobra.PROP_RESPONSAVEIS);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
