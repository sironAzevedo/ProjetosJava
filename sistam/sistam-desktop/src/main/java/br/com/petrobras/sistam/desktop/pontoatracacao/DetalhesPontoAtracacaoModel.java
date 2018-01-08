package br.com.petrobras.sistam.desktop.pontoatracacao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.PontoOperacional;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import java.util.ArrayList;
import java.util.List;

public class DetalhesPontoAtracacaoModel extends BasePresentationModel<SistamService> {
    private PontoAtracacao pontoAtracacao;
    
    private List<Porto> listaPorto;
    private List<PontoOperacional> listaPontoOperacional;
    
    private Porto portoSelecionado;
    private PontoOperacional pontoOperacionalSelecionado;
    
    private boolean salvo;
    
    public DetalhesPontoAtracacaoModel(PontoAtracacao pontoAtracacao){
        this.pontoAtracacao = pontoAtracacao;
        carregarListaPorto();
        
        if (pontoAtracacao.getId()!= null){
            setPortoSelecionado(pontoAtracacao.getPontoOperacional().getPorto());
            setPontoOperacionalSelecionado(pontoAtracacao.getPontoOperacional());
        }
    }

    public PontoAtracacao getPontoAtracacao() {
        return pontoAtracacao;
    }

    public List<Porto> getListaPorto() {
        return listaPorto;
    }

    public void setListaPorto(List<Porto> listaPorto) {
        this.listaPorto = listaPorto;
    }

    public List<PontoOperacional> getListaPontoOperacional() {
        return listaPontoOperacional;
    }

    public void setListaPontoOperacional(List<PontoOperacional> listaPontoOperacional) {
        this.listaPontoOperacional = listaPontoOperacional;
        firePropertyChange("listaPontoOperacional", null, null);
    }

    public Porto getPortoSelecionado() {
        return portoSelecionado;
    }

    final public void setPortoSelecionado(Porto portoSelecionado) {
        this.portoSelecionado = portoSelecionado;
        firePropertyChange("portoSelecionado", null, null);
        carregarListPontoOperacional();
    }

    public PontoOperacional getPontoOperacionalSelecionado() {
        return pontoOperacionalSelecionado;
    }

    final public void setPontoOperacionalSelecionado(PontoOperacional pontoOperacionalSelecionado) {
        this.pontoOperacionalSelecionado = pontoOperacionalSelecionado;
        pontoAtracacao.setPontoOperacional(pontoOperacionalSelecionado);
    }

    public boolean isSalvo() {
        return salvo;
    }
    
    private void carregarListaPorto(){
        List<Agencia> agenciasAutorizadas = ((SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean())).getAgenciasAutorizadas();
        List<Porto> lista = getService().buscarPortosPorAgencia(agenciasAutorizadas.toArray(new Agencia[agenciasAutorizadas.size()]));
        setListaPorto(lista);
    }
    
    private void carregarListPontoOperacional(){
        List<PontoOperacional> lista = new ArrayList<PontoOperacional>();
        
        if (portoSelecionado != null){
            lista.addAll(portoSelecionado.getPontosOperacionais());
            
            SistamUtils.sort(lista, "nome");
        }
        lista.add(0, null);
        setListaPontoOperacional(lista);
    }
    
    public void salvar() {
        pontoAtracacao = getService().salvarPontoAtracacao(pontoAtracacao);
        salvo = true;
    }

}
