/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help.estudo.service.rest.classEnum.usuario;

/**
 *
 * @author aluno
 */
public enum Tipo {
    ADMIN   (1, "ADIMINISTRADOR"),
    SUPORTE (2, "SUPORTE"),
    COMUM   (3, "COMUN");

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
