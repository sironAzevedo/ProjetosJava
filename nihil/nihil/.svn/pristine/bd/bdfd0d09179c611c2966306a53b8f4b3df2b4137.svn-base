/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.service.query;

import br.com.spassu.nihil.common.entity.Target;
import br.com.spassu.nihil.common.enums.TipoTarget;
import br.com.spassu.nihil.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarTarget<T extends Serializable> extends BusinessFluentQuery<T, BuscarTarget> {

    public static class Entities extends BuscarTarget<Target> {
        public Entities() {
            appendText("select t from Target t ");
        }
    }
    
    public static class Count extends BuscarTarget<Long> {
        public Count() {
            appendText("select count(t) from Target t ");
        }
    }
    
    public BuscarTarget whereId(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" t.id = :id ");
            addParameter("id", id);
        }    
        return this;
    }
    
    public BuscarTarget whereIdTipo(String idTipo) {
        if (idTipo!=null && !idTipo.isEmpty()) {
            TipoTarget tipo = TipoTarget.from(idTipo);
            appendText(getPreffixFilter());
            appendText(" t.tipo = :tipo ");
            addParameter("tipo", tipo);
        }
        return this;
    }
    
}
