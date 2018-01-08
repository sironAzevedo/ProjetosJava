package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.sistam.common.entity.Agencia;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Armazena os dados do usuário corrente. Este objeto deve ser alocado na
 * sessão do usuário para ser disponibilizado às classes de serviço. Seu
 * objetivo é guardar informações referentes ao usuário em tempo de execução,
 * desde que essas informações não possam ser obtidas através do controle de
 * acesso corporativo.
 */
public class ContextoUsuario implements Serializable {
    private Map<Agencia, Set<String>> credenciais;
    private Agencia agenciaSelecionada;

    public Map<Agencia, Set<String>> getCredenciais() {
        return credenciais;
    }

    public void setCredenciais(Map<Agencia, Set<String>> credenciais) {
        this.credenciais = credenciais;
    }

    public Agencia getAgenciaSelecionada() {
        return agenciaSelecionada;
    }

    public void setAgenciaSelecionada(Agencia agenciaSelecionada) {
        this.agenciaSelecionada = agenciaSelecionada;
    }
    
    @Override
    public ContextoUsuario clone() {
        ContextoUsuario clone =  new ContextoUsuario();
        clone.setCredenciais(getCredenciais());
        return clone;
    }
}
