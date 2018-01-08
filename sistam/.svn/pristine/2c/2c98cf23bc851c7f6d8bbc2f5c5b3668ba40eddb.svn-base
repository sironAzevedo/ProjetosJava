package br.com.petrobras.sistam.common.exception;

import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.snarf.common.exception.Pendency;
import br.com.petrobras.snarf.common.exception.PendencyManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/**
 * Objeto para encapsular a criaÃ§Ã£o de uma lista de pendÃªncias. Deve
 * ser usado na camada de serviÃ§o para auxiliar na realizaÃ§Ã£o das
 * validaÃ§Ãµes de negÃ³cio. Ao final de todas as validaÃ§Ãµes, Ã© preciso
 * chamar o mÃ©todo <code>verifyAll</code>. Caso exista alguma pendÃªncia
 * registrada, o manager lanÃ§arÃ¡ uma <code>PendingException</code> com
 * a lista de erros. Ex:
 * <blockquote><pre>
 * PendencyManager pm = new PendencyManager();
 *
 * pm.assertThat(pedido.getCliente().isValido()).orRegister("Cliente invÃ¡lido");
 * pm.assertNotEmpty(pedido.getItens()).orRegister("Pedido sem itens");
 * pm.assertNotNull(pedido.getFormaPagamento()).orRegister("Pedido sem forma de pagamento");
 * pm.verifyAll();
 * </pre></blockquote>
 */


public class SistamPendencyManager extends PendencyManager {

    private Map<TipoExcecao, List<Pendency>> pendingMap = new HashMap<TipoExcecao, List<Pendency>>();
    private List<Pendency> pending = new ArrayList<Pendency>();
    private boolean pendencyCondition = false;

    @Override
    public List<Pendency> getPending() {
        return Collections.unmodifiableList(pending);
    }

    public Map<TipoExcecao, List<Pendency>> getPendingMap() {
        return Collections.unmodifiableMap(pendingMap);
    }
    
    @Override
    public SistamPendencyManager assertThat(boolean condition) {
        this.pendencyCondition = !condition;
        return this;
    }

    @Override
    public SistamPendencyManager assertNotNull(Object object) {
        this.pendencyCondition = object == null;
        return this;
    }

    @Override
    public SistamPendencyManager assertNotEmpty(Collection collection) {
        this.pendencyCondition = collection == null || collection.isEmpty();
        return this;
    }

    @Override
    public SistamPendencyManager assertNotEmpty(String string) {
        this.pendencyCondition = string == null || string.isEmpty();
        return this;
    }

    public void orRegister(TipoExcecao tipoExcecao, String message, Object... additionalData) {
        if (this.pendencyCondition) {
            this.pending.add(new Pendency(message, additionalData));
            if (tipoExcecao != null) {
                if (pendingMap.containsKey(tipoExcecao)) {
                    pendingMap.get(tipoExcecao).add(new Pendency(message, additionalData));
                } else {
                   List<Pendency> l = new ArrayList<Pendency>(); 
                   l.add(new Pendency(message, additionalData));
                   pendingMap.put(tipoExcecao, l);
                }
            }
            this.pendencyCondition = false;            
        }
    }
    
    public  void verifyAll() {
        if (!this.pendingMap.isEmpty()) {
            throw new SistamPendingException(pendingMap);
        }
    }
    
}
