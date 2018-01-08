package br.com.petrobras.sistam.desktop.caixaentrada;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Porto;
import java.util.List;

public interface ListagemAgenciamentoSource {
    
    public List<Object> atualizarListagemAgenciamento(Agencia agencia, Porto porto);
}
