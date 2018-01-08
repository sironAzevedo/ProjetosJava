/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.common.entity;

import br.com.spassu.nihil.common.enums.TipoTarget;
import br.com.spassu.nihil.common.support.IEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "TARGET", schema = "nihil")
public class Target implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.spassu.nihil.common.support.GenericEnumUserType",
        parameters = {
            @org.hibernate.annotations.Parameter(name = "enumClass",
                    value = "br.com.spassu.nihil.common.enums.TipoTarget")
    })
    @Column(name = "TIPO", nullable = false)
    private TipoTarget tipo;
    
    
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoTarget getTipo() {
        return tipo;
    }

    public void setTipo(TipoTarget tipo) {
        this.tipo = tipo;
    }

}
