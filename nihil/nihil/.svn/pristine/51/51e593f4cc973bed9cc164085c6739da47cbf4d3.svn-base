/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.common.entity;

import br.com.spassu.nihil.common.support.IEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "SISTEMA", schema = "nihil")
public class Sistema implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "CODIGO_ORIGEM", nullable = true)
    private String codigoOrigem;

    @Column(name = "SIGLA_ORIGEM", nullable = true)
    private String siglaOrigem;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EMPRESA_ID", nullable = false)
    private Empresa empresa;

    @Column(name = "URL_CONTROLE_VERSAO", nullable = true)
    private String urlControleVersao;

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

    public String getCodigoOrigem() {
        return codigoOrigem;
    }

    public void setCodigoOrigem(String codigoOrigem) {
        this.codigoOrigem = codigoOrigem;
    }

    public String getSiglaOrigem() {
        return siglaOrigem;
    }

    public void setSiglaOrigem(String siglaOrigem) {
        this.siglaOrigem = siglaOrigem;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getUrlControleVersao() {
        return urlControleVersao;
    }

    public void setUrlControleVersao(String urlControleVersao) {
        this.urlControleVersao = urlControleVersao;
    }


}
