package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.enums.TipoServico;
import java.io.Serializable;

public class ManobraEmpresaTipoServicoVO implements Serializable {

    private Manobra manobra;
    private EmpresaMaritima empresa;
    private TipoServico tipoServico;

    public ManobraEmpresaTipoServicoVO(Manobra manobra, EmpresaMaritima empresa, TipoServico tipoServico) { 
        this.manobra = manobra;
        this.empresa = empresa;
        this.tipoServico = tipoServico;    
    }        
    
    public Manobra getManobra() {
        return manobra;
    }

    public void setManobra(Manobra manobra) {
        this.manobra = manobra;
    }

    public EmpresaMaritima getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaMaritima empresa) {
        this.empresa = empresa;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ManobraEmpresaTipoServicoVO other = (ManobraEmpresaTipoServicoVO) obj;
        
        if (this.manobra != other.manobra && (this.manobra == null || !this.manobra.equals(other.manobra))) {
            return false;
        }
        if ((this.empresa == null) ? (other.empresa != null) : !this.empresa.equals(other.empresa)) {
            return false;
        }
        if ((this.tipoServico == null) ? (other.tipoServico != null) : !this.tipoServico.equals(other.tipoServico)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.manobra != null ? this.manobra.hashCode() : 0);
        hash = 67 * hash + (this.empresa != null ? this.empresa.hashCode() : 0);
        hash = 67 * hash + (this.tipoServico != null ? this.tipoServico.hashCode() : 0);
        return hash;
    }    
    
    
}
