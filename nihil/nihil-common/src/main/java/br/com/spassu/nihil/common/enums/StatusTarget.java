package br.com.spassu.nihil.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enumeração Tipo Target
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusTarget {

    ONLINE  ("ON", "Online", TipoStatusTarget.SUCCESS),
    OFFLINE ("OF", "Fora do ar", TipoStatusTarget.FAIL);


    /**
     * Id.
     */
    private String id;
    private String descricao;
    private TipoStatusTarget tipo; 

    /**
     * Construtor padrao.
     *
     * @param newId O identificador desta Enumeração
     */
    private StatusTarget(final String newId, final String descricao, TipoStatusTarget tipo) {
        this.id = newId;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    /**
     * Retorna o identificador desta Enumeração.
     * @return id
     */
    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public String getName() {
        return name();
    }

    public TipoStatusTarget getTipo() {
        return tipo;
    }

    public void setTipo(TipoStatusTarget tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Obtém uma instância desta classe a partir do valor de um outro objeto.
     *
     * @param valor
     *          O valor a partir do qual se obterá a instância desta classe.
     * @return  Uma instância desta classe correspondente ao valor passado
     *          como parâmetro ou exceção caso o parâmetro passado for
     *          <code>null</code> ou não estiver dentro dos valores
     *          possÃ­veis da enumeração.
     */
    public static StatusTarget from(final String valor) {
        if (valor == null) {
            throw new NullPointerException();
        }

        for (StatusTarget e : StatusTarget.values()) {
            if (valor.equals(e.id) || valor.equals(e.name())) {
                return e;
            }
        }

        final StringBuilder msg = new StringBuilder("");
        msg.append("Cannot parse into an element of StatusTarget: '");
        msg.append(valor);
        msg.append("'");

        throw new IllegalArgumentException(msg.toString());
    }

}
