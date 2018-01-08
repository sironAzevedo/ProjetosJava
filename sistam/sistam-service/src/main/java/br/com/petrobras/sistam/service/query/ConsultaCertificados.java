package br.com.petrobras.sistam.service.query;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Certificado;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.enums.TipoCertificado;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConsultaCertificados extends BusinessQuery<Certificado> {

    public ConsultaCertificados(TipoCertificado tipo, Embarcacao embarcacao, Agenciamento agenciamento, Date dataEstimadaSaida) {
        Map<String, Object> map = new HashMap<String, Object>();
         
        StringBuilder texto = new StringBuilder();

        texto
                .append(" select c from {Certificado} c ")                
                .append(" where c.{dataValidade} >=  :dataEstimadaSaida");
        
        map.put("dataEstimadaSaida", dataEstimadaSaida);
        
        if (tipo!=null) {
            texto.append(" and c.{tipo} = :tipo ");
            map.put("tipo", tipo);
        }

        if (embarcacao!=null) {
            texto.append(" and c.{embarcacao} = :embarcacao ");
            map.put("embarcacao", embarcacao);
        }

        if (agenciamento!=null) {
            texto.append(" and c.{agenciamento} = :agenciamento ");
            map.put("agenciamento", agenciamento);
        }
        
        texto.append(" order by c.{dataValidade} desc, c.{dataEmissao} desc, c.{id} desc");
        
        String hql = texto.toString();     
        hql = hql.replace("{Certificado}", Certificado.class.getSimpleName())
                 .replace("{id}", Certificado.PROP_ID)
                 .replace("{tipo}", Certificado.PROP_TIPO)
                 .replace("{dataValidade}", Certificado.PROP_DATA_VALIDADE)
                 .replace("{dataEmissao}", Certificado.PROP_DATA)
                 .replace("{embarcacao}", Certificado.PROP_EMBARCACAO)
                 .replace("{agenciamento}", Certificado.PROP_AGENCIAMENTO);
        
        this.setText(hql);
        setParameters(map);
        
    }
    
}
