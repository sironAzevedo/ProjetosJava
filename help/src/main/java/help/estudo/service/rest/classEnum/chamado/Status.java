/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help.estudo.service.rest.classEnum.chamado;

/**
 *
 * @author siron
 */
public enum Status {
    NOVO   (1, "NOVO"),
    PENDENTE(2, "PENDENTE"),
    FECHADO(3, "FECHADO");
    
    private Integer id;
    private String status;

    private Status(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Status{" + "status=" + status + '}';
    }
    
}
