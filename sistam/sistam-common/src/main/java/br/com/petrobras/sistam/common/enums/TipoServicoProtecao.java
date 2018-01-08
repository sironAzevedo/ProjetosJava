package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum TipoServicoProtecao implements Serializable {

    HOSPEDAGEM              (1, "Hospedagem", null, 2),
    TRANSPORTE              (2, "Transporte", null, 5),
    ACESSO_PESSOAS          (3, "Acesso a Pessoas", TipoAtendimentoServico.ACESSO_PESSOA, 1),
    SUPRIMENTOS             (4, "Suprimentos", null, 4),
    SERVICOS_SUPLEMENTARES  (5, "Serviços Suplementares", null, 3);
    
    private Integer id;
    private String porExtenso;
    private TipoAtendimentoServico tipoAtendimentoServico;
    private Integer ordem;

    private TipoServicoProtecao(Integer id, String porExtenso, TipoAtendimentoServico tipoAtendimentoServico, Integer ordem) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.tipoAtendimentoServico = tipoAtendimentoServico;
        this.ordem = ordem;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public TipoAtendimentoServico getTipoAtendimentoServico() {
        return tipoAtendimentoServico;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public static List<TipoServicoProtecao> valuesToListOrdered() {
        List<TipoServicoProtecao> tipos = Arrays.asList(values());
        Collections.sort(tipos, new Comparator<TipoServicoProtecao>() {
            @Override
            public int compare(TipoServicoProtecao t1, TipoServicoProtecao t2) {
                return t1.getOrdem().compareTo(t2.getOrdem());
            }
        });
        return tipos;
    }

    public static TipoServicoProtecao from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoServicoProtecao tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    public static TipoServicoProtecao from(TipoAtendimentoServico tipoAtendimentoServico) {
        if (tipoAtendimentoServico == null) {
            return null;
        }
        for (TipoServicoProtecao tipo : values()) {
            if (tipo.getTipoAtendimentoServico() != null && tipoAtendimentoServico.equals(tipo.getTipoAtendimentoServico())) {
                return tipo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return porExtenso;
    }
}
