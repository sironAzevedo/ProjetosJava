package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Classe RelatorioAgenciamentoProdutividadeVO
 */
public class RelatorioAgenciamentoProdutividadeVO {
    private List<Agenciamento> agenciamentosCarga;
    private List<Agenciamento> agenciamentosProtecao;
    private List<Agenciamento> agenciamentosPlataforma;
    private Long totalEmbarcacoes;
    private Long totalPlataforma;

    public List<Agenciamento> getAgenciamentosCarga() {
        return agenciamentosCarga;
    }

    public void setAgenciamentosCarga(List<Agenciamento> agenciamentosCarga) {
        this.agenciamentosCarga = agenciamentosCarga;
    }

    public List<Agenciamento> getAgenciamentosProtecao() {
        return agenciamentosProtecao;
    }

    public void setAgenciamentosProtecao(List<Agenciamento> agenciamentosProtecao) {
        this.agenciamentosProtecao = agenciamentosProtecao;
    }

    public List<Agenciamento> getAgenciamentosPlataforma() {
        return agenciamentosPlataforma;
    }

    public void setAgenciamentosPlataforma(List<Agenciamento> agenciamentosPlataforma) {
        this.agenciamentosPlataforma = agenciamentosPlataforma;
    } 

    public Long getTotalEmbarcacoes() {
        return totalEmbarcacoes;
    }

    public void setTotalEmbarcacoes(Long totalEmbarcacoes) {
        this.totalEmbarcacoes = totalEmbarcacoes;
    }

    public Long getTotalPlataforma() {
        return totalPlataforma;
    }

    public void setTotalPlataforma(Long totalPlataforma) {
        this.totalPlataforma = totalPlataforma;
    } 
    
    public void ordenarAgenciamentos(){
        ordenarAgenciamentoCargaPelaDataChegada();
        ordenarAgenciamentoProtecaoPelaDataChegada();
        ordenarAgenciamentoPlataformaPelaDataChegada();
    } 
    
    //<editor-fold defaultstate="collapsed" desc="Ordenar o relatorio pela data de chegada">
    private void ordenarAgenciamentoCargaPelaDataChegada() {
        Collections.sort(agenciamentosCarga, new Comparator<Agenciamento>() {
            @Override
            public int compare(Agenciamento o1, Agenciamento o2) {
                if (o1.getDataChegada() == null && o2.getDataChegada() == null) {
                    return o1.getEmbarcacao().getNomeCompleto().compareToIgnoreCase(o2.getEmbarcacao().getNomeCompleto());
                } else if (o1.getDataChegada() != null && o2.getDataChegada() == null) {
                    return 1;
                } else if (o1.getDataChegada() == null && o2.getDataChegada() != null) {
                    return -1;
                }
                int retorno = o1.getDataChegada().compareTo(o2.getDataChegada());
                return retorno == 0 ? o1.getEmbarcacao().getNomeCompleto().compareToIgnoreCase(o2.getEmbarcacao().getNomeCompleto()) : retorno;
            }
        });
    }
    
    private void ordenarAgenciamentoProtecaoPelaDataChegada() {
        Collections.sort(agenciamentosProtecao, new Comparator<Agenciamento>() {
            @Override
            public int compare(Agenciamento o1, Agenciamento o2) {
                if (o1.getDataChegada() == null && o2.getDataChegada() == null) {
                    return o1.getEmbarcacao().getNomeCompleto().compareToIgnoreCase(o2.getEmbarcacao().getNomeCompleto());
                } else if (o1.getDataChegada() != null && o2.getDataChegada() == null) {
                    return 1;
                } else if (o1.getDataChegada() == null && o2.getDataChegada() != null) {
                    return -1;
                }
                int retorno = o1.getDataChegada().compareTo(o2.getDataChegada());
                return retorno == 0 ? o1.getEmbarcacao().getNomeCompleto().compareToIgnoreCase(o2.getEmbarcacao().getNomeCompleto()) : retorno;
            }
        });
    }
    
    private void ordenarAgenciamentoPlataformaPelaDataChegada() {
        Collections.sort(agenciamentosPlataforma, new Comparator<Agenciamento>() {
            @Override
            public int compare(Agenciamento o1, Agenciamento o2) {
                if (o1.getDataChegada() == null && o2.getDataChegada() == null) {
                    return o1.getEmbarcacao().getNomeCompleto().compareToIgnoreCase(o2.getEmbarcacao().getNomeCompleto());
                } else if (o1.getDataChegada() != null && o2.getDataChegada() == null) {
                    return 1;
                } else if (o1.getDataChegada() == null && o2.getDataChegada() != null) {
                    return -1;
                }
                int retorno = o1.getDataChegada().compareTo(o2.getDataChegada());
                return retorno == 0 ? o1.getEmbarcacao().getNomeCompleto().compareToIgnoreCase(o2.getEmbarcacao().getNomeCompleto()) : retorno;
            }
        });
    } 
    //</editor-fold>
}
