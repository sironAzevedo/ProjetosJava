/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.common.support;

import br.com.spassu.nihil.common.exception.BusinessException;
import java.util.Collection;

/**
 * <p>Utilitários de assert
 * @author 
 */
// Classe utilitária nomeada no plural
@SuppressWarnings("PMD.Regra 5")
public abstract class AssertUtils {
    /**
     * Construtor privado
     */
    private AssertUtils(){
    }

    /**
     * Gera uma exceção conhecida se o objeto <b>objeto</b> é nulo
     * @param object Objeto que deve ser verificado
     * @param message Id da mensagem de erro
     */
    public static void assertNotNull(Object object, String message) {
        if (object == null) {
            throw new BusinessException(message);
        }
    }

    /**
     * Gera uma exceção se a String <b>string</b> é nula ou vazia com a mensagem <b>s</b>.
     * @param string A string que será verificada.
     * @param message  Id da mensagem de erro
     */
    public static void assertNotNullNotEmpty(String string, String message) {
        if (string == null || string.trim().length() == 0) {
            throw new BusinessException(message);
        }
    }

    /**
     * Gera uma exceção se a Collection <b>collection</b> é nula ou vazia com a mensagem <b>s</b>.
     * @param collection A collection que será verificada.
     * @param message Id da mensagem de erro
     */
    public static void assertNotNullNotEmpty(Collection collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new BusinessException(message);
        }
    }

    /**
     * Gera uma exceção se o objeto <b>o</b> é nulo com a mensagem <b>s</b>.
     * @param expression Condição a ser checada
     * @param message Id da mensagem de erro
     */
    public static void assertExpression(boolean expression, String message) {
        if (!expression) {
            throw new BusinessException(message);
        }
    }

    /**
     * Gera uma exceção se o objeto <b>o</b> é nulo com a mensagem <b>s</b>.
     * @param expression Condição a ser checada
     * @param message Id da mensagem de erro
     * @param params Parâmetros da mensagem
     */
    public static void assertExpression(boolean expression, String message, Object... params) {
        if (!expression) {
            throw new BusinessException(message, params);
        }
    }
}
