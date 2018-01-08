/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.common.filtro;

/**
 *
 * @author spassu
 */
public class MonitorFiltro {
    
    private Long id;
    private Long idTarget;
    private String idTipoTarget;
    private Boolean orderByDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTarget() {
        return idTarget;
    }

    public void setIdTarget(Long idTarget) {
        this.idTarget = idTarget;
    }

    public Boolean getOrderByDesc() {
        return orderByDesc;
    }

    public void setOrderByDesc(Boolean orderByDesc) {
        this.orderByDesc = orderByDesc;
    }

    public String getIdTipoTarget() {
        return idTipoTarget;
    }

    public void setIdTipoTarget(String idTipoTarget) {
        this.idTipoTarget = idTipoTarget;
    }
    
}
