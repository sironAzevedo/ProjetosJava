package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;

/**
 * @author adzk
 */
public class VeiculoVO extends AbstractBean {

    private String modelo;
    private String placa;

    public VeiculoVO() {
    }

    public VeiculoVO(String modelo, String placa) {
        this.modelo = modelo;
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.modelo != null ? this.modelo.hashCode() : 0);
        hash = 97 * hash + (this.placa != null ? this.placa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VeiculoVO other = (VeiculoVO) obj;
        if ((this.modelo == null) ? (other.modelo != null) : !this.modelo.equals(other.modelo)) {
            return false;
        }
        if ((this.placa == null) ? (other.placa != null) : !this.placa.equals(other.placa)) {
            return false;
        }
        return true;
    }
    
}
