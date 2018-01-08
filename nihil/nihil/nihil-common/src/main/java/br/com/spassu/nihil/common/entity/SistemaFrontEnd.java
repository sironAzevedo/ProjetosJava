/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.common.entity;

import br.com.spassu.nihil.common.enums.FrontEnd;
import br.com.spassu.nihil.common.support.IEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "SISTEMA_FRONTEND", schema = "nihil")
public class SistemaFrontEnd implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SISTEMA_ID", nullable = false)
    private Sistema sistema;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.spassu.nihil.common.support.GenericEnumUserType",
        parameters = {
            @org.hibernate.annotations.Parameter(name = "enumClass",
                    value = "br.com.spassu.nihil.common.enums.FrontEnd")
    })
    @Column(name = "FRONTEND", nullable = false)
    private FrontEnd frontEnd;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public FrontEnd getFrontEnd() {
        return frontEnd;
    }

    public void setFrontEnd(FrontEnd frontEnd) {
        this.frontEnd = frontEnd;
    }

}
