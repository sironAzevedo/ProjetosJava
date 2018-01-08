/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help.estudo.service.rest.classEnum.chamado;

/**
 *
 * @author aluno
 */
public enum Tipo {
    SOLICITACAO (1, "SOLICITAÇÃO"),
    CORRECAO    (2, "CORREÇÃO");

    private Integer id;
    private String tipo;

    private Tipo(Integer id, String status) {
        this.id = id;
        this.tipo = status;
    }

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Status{" + "tipo=" + tipo + '}';
    }
}
