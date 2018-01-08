/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.common.entity;

import br.com.spassu.nihil.common.support.IEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "INCIDENTE", schema = "nihil")
public class Incidente implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CODIGO_ORIGEM", nullable = true)
    private String codigoOrigem;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CATEGORIA_ID", nullable = false)
    private Categoria categoria;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SISTEMA_ID", nullable = false)
    private Sistema sistema;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_CRIACAO", nullable = false)
    private Date dataCriacao;
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoOrigem() {
        return codigoOrigem;
    }

    public void setCodigoOrigem(String codigoOrigem) {
        this.codigoOrigem = codigoOrigem;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    } 

}
