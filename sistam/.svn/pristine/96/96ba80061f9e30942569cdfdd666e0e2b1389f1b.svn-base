package br.com.petrobras.sistam.common.exception;

import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.snarf.common.exception.Pendency;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ExceÃ§Ã£o que carrega uma lista de pendÃªncias.
 * 
 */

public class SistamPendingException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    public static final String MESSAGE_SEPARATOR = ";";

    /**
     * Mensagens de erro armazenadas na exceÃ§Ã£o
     */
    private List<Pendency> pending = new ArrayList<Pendency>();
    private Map<TipoExcecao, List<Pendency>> pendingMap = new HashMap<TipoExcecao, List<Pendency>>();

    /**
     * Cria uma exceÃ§Ã£o representando uma lista de erros esperados de
     * negÃ³cio ou de sistema.
     * @param pending lista de pendÃªncias
     * @return a exceÃ§Ã£o
     */
    public SistamPendingException(List<Pendency> pending) {
        this(null, pending);
    }

    /**
     * Cria uma exceÃ§Ã£o representando um map de erros esperados de
     * negÃ³cio ou de sistema agrupados por tipo.
     * @param pendingMap map de pendÃªncias
     * @return a exceÃ§Ã£o
     */
    public SistamPendingException(Map<TipoExcecao, List<Pendency>> pendingMap) {
        this(null, pendingMap);
    }
    
    /**
     * @param type
     * @param msg
     * @param error
     * @param params
     */
    public SistamPendingException(Throwable error, List<Pendency> pending) {
        super("snarf.exception.errorlist", error);
        this.pending.addAll(pending);
    }

    public SistamPendingException(Throwable error, Map<TipoExcecao, List<Pendency>> pendingMap) {
        super("snarf.exception.errorlist", error);
        this.pendingMap.putAll(pendingMap);
        
        for ( Map.Entry< TipoExcecao, List<Pendency> > entry : pendingMap.entrySet() ) {
            this.pending.addAll(entry.getValue());
        }        
        
    }
    
    /**
     * Retorna a lista de pendÃªncias registradas na exceÃ§Ã£o.
     */
    public List<Pendency> getPending() {
        return Collections.unmodifiableList(pending);
    }

    /**
     * Retorna o map de pendÃªncias registradas na exceÃ§Ã£o.
     */
    public Map<TipoExcecao, List<Pendency>> getPendingMap() {
        return Collections.unmodifiableMap(pendingMap);
    }

    /**
     * Retorna uma mensagem com o texto original de todas as pendÃªncias concatenados.
     */
    @Override
    public String getMessage() {
        StringBuilder builder = new StringBuilder();
        for (Pendency pendency : pending) {
            if (builder.length() > 0) {
                builder.append(MESSAGE_SEPARATOR);
            }
            builder.append(pendency.getMessage());
        }
        return builder.toString();
    }

    /**
     * Retorna uma mensagem com o texto localizado de todas as pendÃªncias concatenados.
     */
    @Override
    public String getLocalizedMessage() {
        StringBuilder builder = new StringBuilder();
        for (Pendency pendency : pending) {
            if (builder.length() > 0) {
                builder.append(MESSAGE_SEPARATOR);
            }
            builder.append(pendency.getLocalizedMessage());
        }
        return builder.toString();
    }
    
    
}
