package br.com.petrobras.sistam.desktop.embarcacao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.enums.ClassificacaoEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoEmbarcacao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DetalheEmbarcacaoModel extends BasePresentationModel<SistamService> {
    private Embarcacao embarcacao;
    private List<ClassificacaoEmbarcacao> classificacoes;
    private List<TipoEmbarcacao> tipos;
    private List<Pais> paises;
    private boolean salvo = false;
    
    public DetalheEmbarcacaoModel(Embarcacao embarcacao){
        this.embarcacao = embarcacao;
        
        classificacoes = new ArrayList<ClassificacaoEmbarcacao> (Arrays.asList(ClassificacaoEmbarcacao.values()));
        classificacoes.add(0, null);

        tipos = new ArrayList<TipoEmbarcacao> (Arrays.asList(TipoEmbarcacao.values()));
        tipos.add(0, null);
    }
    
    public void salvar() {
        embarcacao = getService().salvarEmbarcacao(embarcacao);
        salvo = true;
    }
    
    public void carregarPaises(){
        List<Pais> paises = getService().buscarPaises(null);
        paises.add(0, null);
        setPaises(paises);
    }
    
    public boolean salvo(){
        return salvo;
    }

    public Embarcacao getEmbarcacao() {
        return embarcacao;
    }

    public void setEmbarcacao(Embarcacao embarcacao) {
        this.embarcacao = embarcacao;
        firePropertyChange("embarcacao", null, this.embarcacao);
    }

    public List<ClassificacaoEmbarcacao> getClassificacoes() {
        return Collections.unmodifiableList(classificacoes);
    }

    public List<TipoEmbarcacao> getTipos() {
        return Collections.unmodifiableList(tipos);
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
        firePropertyChange("paises", null, this.paises);
    }
    
    public void verificarCodigo() {
        Embarcacao e = getService().buscarEmbarcacaoPorId(embarcacao.getId());
        if (e != null) {
            setEmbarcacao(e);
        }
    }
    
}
