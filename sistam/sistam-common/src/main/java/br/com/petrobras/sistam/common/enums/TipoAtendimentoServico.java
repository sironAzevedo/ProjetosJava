package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public enum TipoAtendimentoServico implements Serializable {

    MEDICO_ODONTOLOGICO(1, "Serviço médico/odontológico", TipoAtendimento.ATENDIMENTO_TRIPULACAO, true),
    HOSPEDAGEM(2, "Serviço de hospedagem", TipoAtendimento.ATENDIMENTO_TRIPULACAO, true),
    TRANSPORTE(3, "Serviço de transporte", TipoAtendimento.ATENDIMENTO_TRIPULACAO, true),
    ACESSO_PESSOA(4, "Serviço de acesso a pessoas", TipoAtendimento.ATENDIMENTO_TRIPULACAO, true),
    ENTREGA_LUBRIFICANTES(5, "Entrega de Lubrificantes", TipoAtendimento.SUPRIMENTO_EMBARCACAO, true),
    RETIRADA_LIXO(6, "Retirada de Lixo ", TipoAtendimento.SUPRIMENTO_EMBARCACAO, true),
    RETIRADA_RESIDUO(7, "Retirada de Resíduos", TipoAtendimento.SUPRIMENTO_EMBARCACAO, true),
    FORNECIMENTO_AGUA(8, "Fornecimento de Água", TipoAtendimento.SUPRIMENTO_EMBARCACAO, false),
    FORNECIMENTO_RANCHO(9, "Fornecimento de Rancho", TipoAtendimento.SUPRIMENTO_EMBARCACAO, true),
    OUTROS_ATENDIMENTOS(10, "Outros Atendimentos", TipoAtendimento.SUPRIMENTO_EMBARCACAO, false),
    CONTROLE_PRAGAS(11, "Controle de Pragas", TipoAtendimento.SERVICOS_SUPLEMENTARES, false),
    RENOVACAO_CICSB(12, "Renovação do Certificado Internacional de Controle Sanitário de Bordo", TipoAtendimento.SERVICOS_SUPLEMENTARES, false),
    INSPECAO_CAPITANIA(13, "Inspeção da capitania dos portos", TipoAtendimento.SERVICOS_SUPLEMENTARES, false),
    SERVICO_SUPRIMENTO(14, "Serviço suprimento as Embarcações", TipoAtendimento.SUPRIMENTO_EMBARCACAO, true),
    SERVICO_RETIRADA_RESIDUO_LIXO(15, "Serviço Retirada de Resíduos/Lixo", TipoAtendimento.SUPRIMENTO_EMBARCACAO, true);
    private Integer id;
    private String porExtenso;
    private TipoAtendimento tipoAtendimento;
    private Boolean implementado;

    private TipoAtendimentoServico(Integer id, String porExtenso, TipoAtendimento tipoAtendimento, Boolean implementado) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.tipoAtendimento = tipoAtendimento;
        this.implementado = implementado;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public Boolean getImplementado() {
        return implementado;
    }

    public static TipoAtendimentoServico from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoAtendimentoServico tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    public static List<TipoAtendimentoServico> obterPor(TipoAtendimento tipoAtendimento, Boolean implementado) {
        List<TipoAtendimentoServico> lista = new ArrayList<TipoAtendimentoServico>();
        for (TipoAtendimentoServico tipo : values()) {
            if (tipo.tipoAtendimento.equals(tipoAtendimento) && (implementado == null || tipo.getImplementado().equals(implementado))) {
                lista.add(tipo);
            }
        }
        return lista;
    }

    @Override
    public String toString() {
        return porExtenso;
    }
}
