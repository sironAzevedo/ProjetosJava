/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.service.query;

import br.com.spassu.nihil.common.entity.Monitor;
import br.com.spassu.nihil.common.enums.TipoTarget;
import br.com.spassu.nihil.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarMonitor<T extends Serializable> extends BusinessFluentQuery<T, BuscarMonitor> {

    public static class Entities extends BuscarMonitor<Monitor> {
        public Entities() {
            appendText("select m from Monitor m ");
        }
    }
    
    public static class Count extends BuscarMonitor<Long> {
        public Count() {
            appendText("select count(m) from Monitor m ");
        }
    }
    
    public BuscarMonitor joinTarget() {
        appendText(" join m.target t ");
        return this;
    }
    
    public BuscarMonitor fetchTarget() {
        appendText(" join fetch m.target t ");
        return this;
    }
    
    public BuscarMonitor whereId(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" m.id = :id ");
            addParameter("id", id);
        }    
        return this;
    }

    public BuscarMonitor whereIdTarget(Long idTarget) {
        if (idTarget != null) {
            appendText(getPreffixFilter());
            appendText(" t.id = :idTarget ");
            addParameter("idTarget", idTarget);
        }    
        return this; 
    }
    
    
    public BuscarMonitor whereIdTipoTarget(String idTipo) {
        if (idTipo!=null && !idTipo.isEmpty()) {
            TipoTarget tipo = TipoTarget.from(idTipo);
            appendText(getPreffixFilter());
            appendText(" t.tipo = :tipo ");
            addParameter("tipo", tipo);
        }
        return this;
    }
    
    public BuscarMonitor orderByData(Boolean desc) {
        appendText(" order by m.data ");
        
        if (desc != null && desc) {
            appendText(" desc ");
        }
        
        return this;
    }    
    
    
}
