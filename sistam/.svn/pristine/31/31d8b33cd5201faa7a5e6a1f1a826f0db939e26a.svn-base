package br.com.petrobras.sistam.common.valueobjects;

import static br.com.petrobras.sistam.common.enums.StatusEmbarcacao.FUNDEADO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * VO que contém as informações para o Relatório de Movimentação da Embarcação
 */
public class RelatorioMovimentacaoEmbarcacaoVO implements Serializable {

    private List<SubrelatorioMovimentacaoEmbarcacaoVO> naviosAtracados; // pela Posiçao
    private List<SubrelatorioMovimentacaoEmbarcacaoVO> naviosFundeados; // pela chegada
    private List<SubrelatorioMovimentacaoEmbarcacaoVO> naviosEsperados; //pelo eta
    private List<SubrelatorioMovimentacaoEmbarcacaoVO> naviosSaidos; // pela saida
    private List<SubrelatorioMovimentacaoEmbarcacaoVO> naviosCancelados; // pela dataCancelamento
    private List<SubrelatorioMovimentacaoEmbarcacaoVO> naviosDesviados; // pelo etaProxPorto

    public RelatorioMovimentacaoEmbarcacaoVO() {
        naviosAtracados = new ArrayList<SubrelatorioMovimentacaoEmbarcacaoVO>();
        naviosFundeados = new ArrayList<SubrelatorioMovimentacaoEmbarcacaoVO>();
        naviosEsperados = new ArrayList<SubrelatorioMovimentacaoEmbarcacaoVO>();
        naviosSaidos = new ArrayList<SubrelatorioMovimentacaoEmbarcacaoVO>();
        naviosCancelados = new ArrayList<SubrelatorioMovimentacaoEmbarcacaoVO>();
        naviosDesviados = new ArrayList<SubrelatorioMovimentacaoEmbarcacaoVO>();
    }

    public void adicionarSubrelatorioVO(SubrelatorioMovimentacaoEmbarcacaoVO subVo) {
        switch (subVo.getStatusEmbarcacao()) {
            case ATRACADO:
                naviosAtracados.add(subVo);
                break;

            case FUNDEADO:
            case FUNDEADO_NA_ENTRADA:
            case FUNDEADO_NA_SAIDA:
            case FUNDEADO_PARA_REATRACACAO:
            case NO_PORTO:
                naviosFundeados.add(subVo);
                break;

            case ESPERADO:
                naviosEsperados.add(subVo);
                break;

            case SAIDO:
                naviosSaidos.add(subVo);
                break;

            case DESVIADO:
                naviosDesviados.add(subVo);
                break;

            case CANCELADO:
                naviosCancelados.add(subVo);
                break;
        }
    }

    public List<SubrelatorioMovimentacaoEmbarcacaoVO> getNaviosAtracados() {
        return naviosAtracados;
    }

    public List<SubrelatorioMovimentacaoEmbarcacaoVO> getNaviosFundeados() {
        return naviosFundeados;
    }

    public List<SubrelatorioMovimentacaoEmbarcacaoVO> getNaviosEsperados() {
        return naviosEsperados;
    }

    public List<SubrelatorioMovimentacaoEmbarcacaoVO> getNaviosSaidos() {
        return naviosSaidos;
    }

    public List<SubrelatorioMovimentacaoEmbarcacaoVO> getNaviosCancelados() {
        return naviosCancelados;
    }

    public List<SubrelatorioMovimentacaoEmbarcacaoVO> getNaviosDesviados() {
        return naviosDesviados;
    }

    public void ordenarListas() {
        ordenarNaviosCancelados();
        ordenarNaviosDesviados();
        ordenarNaviosEsperados();
        ordenarNaviosFundeados();
        ordenarNaviosSaidos();
    }


    private void ordenarNaviosDesviados() {
        Collections.sort(naviosDesviados, new Comparator<SubrelatorioMovimentacaoEmbarcacaoVO>() {
            @Override
            public int compare(SubrelatorioMovimentacaoEmbarcacaoVO o1, SubrelatorioMovimentacaoEmbarcacaoVO o2) {
                if (o1.getDataEtaProxPorto() == null && o2.getDataEtaProxPorto() == null) {
                    return o1.getNavio().compareToIgnoreCase(o2.getNavio());
                } else if (o1.getDataEtaProxPorto() != null && o2.getDataEtaProxPorto() == null) {
                    return 1;
                } else if (o1.getDataEtaProxPorto() == null && o2.getDataEtaProxPorto() != null) {
                    return -1;
                }
                int retorno = o1.getDataEtaProxPorto().compareTo(o2.getDataEtaProxPorto());
                return retorno == 0 ? o1.getNavio().compareToIgnoreCase(o2.getNavio()) : retorno;
            }
        });
    }

    private void ordenarNaviosEsperados() {
        Collections.sort(naviosEsperados, new Comparator<SubrelatorioMovimentacaoEmbarcacaoVO>() {
            @Override
            public int compare(SubrelatorioMovimentacaoEmbarcacaoVO o1, SubrelatorioMovimentacaoEmbarcacaoVO o2) {
                if (o1.getDataEta() == null && o2.getDataEta() == null) {
                    return o1.getNavio().compareToIgnoreCase(o2.getNavio());
                } else if (o1.getDataEta() != null && o2.getDataEta() == null) {
                    return 1;
                } else if (o1.getDataEta() == null && o2.getDataEta() != null) {
                    return -1;
                }
                int retorno = o1.getDataEta().compareTo(o2.getDataEta());
                return retorno == 0 ? o1.getNavio().compareToIgnoreCase(o2.getNavio()) : retorno;
            }
        });
    }

    private void ordenarNaviosFundeados() {
        Collections.sort(naviosFundeados, new Comparator<SubrelatorioMovimentacaoEmbarcacaoVO>() {
            @Override
            public int compare(SubrelatorioMovimentacaoEmbarcacaoVO o1, SubrelatorioMovimentacaoEmbarcacaoVO o2) {
                if (o1.getDataChegada() == null && o2.getDataChegada() == null) {
                    return o1.getNavio().compareToIgnoreCase(o2.getNavio());
                } else if (o1.getDataChegada() != null && o2.getDataChegada() == null) {
                    return 1;
                } else if (o1.getDataChegada() == null && o2.getDataChegada() != null) {
                    return -1;
                }
                int retorno = o1.getDataChegada().compareTo(o2.getDataChegada());
                return retorno == 0 ? o1.getNavio().compareToIgnoreCase(o2.getNavio()) : retorno;
            }
        });
    }

    private void ordenarNaviosSaidos() {
        Collections.sort(naviosSaidos, new Comparator<SubrelatorioMovimentacaoEmbarcacaoVO>() {
            @Override
            public int compare(SubrelatorioMovimentacaoEmbarcacaoVO o1, SubrelatorioMovimentacaoEmbarcacaoVO o2) {
                if (o1.getDataSaida() == null && o2.getDataSaida() == null) {
                    return o1.getNavio().compareToIgnoreCase(o2.getNavio());
                } else if (o1.getDataSaida() != null && o2.getDataSaida() == null) {
                    return 1;
                } else if (o1.getDataSaida() == null && o2.getDataSaida() != null) {
                    return -1;
                }
                int retorno = o1.getDataSaida().compareTo(o2.getDataSaida());
                return retorno == 0 ? o1.getNavio().compareToIgnoreCase(o2.getNavio()) : retorno;
            }
        });
    }

    private void ordenarNaviosCancelados() {
        Collections.sort(naviosCancelados, new Comparator<SubrelatorioMovimentacaoEmbarcacaoVO>() {
            @Override
            public int compare(SubrelatorioMovimentacaoEmbarcacaoVO o1, SubrelatorioMovimentacaoEmbarcacaoVO o2) {
                if (o1.getCancelamento() == null && o2.getCancelamento() == null) {
                    return o1.getNavio().compareToIgnoreCase(o2.getNavio());
                } else if (o1.getCancelamento() != null && o2.getCancelamento() == null) {
                    return 1;
                } else if (o1.getCancelamento() == null && o2.getCancelamento() != null) {
                    return -1;
                }
                int retorno = o1.getCancelamento().compareTo(o2.getCancelamento());
                return retorno == 0 ? o1.getNavio().compareToIgnoreCase(o2.getNavio()) : retorno;
            }
        });
    }
}
