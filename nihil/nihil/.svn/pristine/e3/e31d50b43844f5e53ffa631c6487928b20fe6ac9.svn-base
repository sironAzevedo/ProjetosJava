/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.common.entity;

import br.com.spassu.nihil.common.enums.StatusTarget;
import br.com.spassu.nihil.common.support.IEntity;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "MONITOR", schema = "nihil")
public class Monitor implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA", nullable = false)
    private Date data;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TARGET_ID", nullable = false)
    private Target target;

    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.spassu.nihil.common.support.GenericEnumUserType",
        parameters = {
            @org.hibernate.annotations.Parameter(name = "enumClass",
                    value = "br.com.spassu.nihil.common.enums.StatusTarget")
    })
    @Column(name = "STATUS", nullable = false)
    private StatusTarget status;
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public StatusTarget getStatus() {
        return status;
    }

    public void setStatus(StatusTarget status) {
        this.status = status;
    }

}
