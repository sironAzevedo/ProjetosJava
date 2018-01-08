package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoFrota;
import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class GrupoAgenciaEFrotaVO implements Serializable {

    private Agencia agencia; 
    private TipoFrota tipoFrota;
    
    private String nomeArquivo;
    private String extensao;
    private Date geradoEm;

    public GrupoAgenciaEFrotaVO(Agencia agencia, TipoFrota tipoFrota, Date geradoEm) {
        this.agencia = agencia; 
        this.tipoFrota = tipoFrota;
        this.geradoEm = geradoEm;
    }

    public Agencia getAgencia() {
        return agencia;
    } 

    public TipoFrota getTipoFrota() {
        return tipoFrota;
    } 

    public Date getGeradoEm() {
        return geradoEm;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.agencia != null ? this.agencia.hashCode() : 0);
        hash = 29 * hash + (this.tipoFrota != null ? this.tipoFrota.hashCode() : 0);
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
        final GrupoAgenciaEFrotaVO other = (GrupoAgenciaEFrotaVO) obj;
        if (this.agencia != other.agencia && (this.agencia == null || !this.agencia.equals(other.agencia))) {
            return false;
        }
        if (this.tipoFrota != other.tipoFrota) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agencia=" + agencia.getNome() + ", TipoFrota=" + tipoFrota.getPorExtenso();
    }
    
}