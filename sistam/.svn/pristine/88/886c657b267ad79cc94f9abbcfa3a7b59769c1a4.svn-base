package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class GrupoAgenciaEStatusEmbarcacaoVO implements Serializable {

    private Agencia agencia;
    private StatusEmbarcacao statusEmbarcacao;
    
    private String nomeArquivo;
    private String extensao;
    private Date geradoEm;

    public GrupoAgenciaEStatusEmbarcacaoVO(Agencia agencia, StatusEmbarcacao statusEmbarcacao, Date geradoEm) {
        this.agencia = agencia;
        this.statusEmbarcacao = statusEmbarcacao;
        this.geradoEm = geradoEm;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public StatusEmbarcacao getStatusEmbarcacao() {
        return statusEmbarcacao;
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
        hash = 29 * hash + (this.statusEmbarcacao != null ? this.statusEmbarcacao.hashCode() : 0);
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
        final GrupoAgenciaEStatusEmbarcacaoVO other = (GrupoAgenciaEStatusEmbarcacaoVO) obj;
        if (this.agencia != other.agencia && (this.agencia == null || !this.agencia.equals(other.agencia))) {
            return false;
        }
        if (this.statusEmbarcacao != other.statusEmbarcacao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agencia=" + agencia.getNome() + ", StatusEmbarcacao=" + statusEmbarcacao.getPorExtenso();
    }
    
}