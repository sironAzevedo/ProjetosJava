package br.com.petrobras.sistam.desktop.components.lookups.embarcacao;

import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import java.util.List;
import javax.swing.ImageIcon;

public class EmbarcacaoLookup extends EmbarcacaoInput {

    public EmbarcacaoLookup() {
        super();
    }

    @Override
    protected List<Embarcacao> buscarPorNome(String nome) {
        return ((SistamService) BaseApp.getApplication().getService()).buscarEmbarcacoes(nome);
    }

    @Override
    protected ImageIcon getValueIcon(Embarcacao aValue) {
        return null;
    }

    @Override
    protected Embarcacao buscarPorId(Embarcacao embarcacao) {
        return ((SistamService) BaseApp.getApplication().getService()).buscarEmbarcacaoPorId(embarcacao.getId());
    }

    @Override
    protected String valueToId(Embarcacao value) {
        return value.getId();
    }
}
