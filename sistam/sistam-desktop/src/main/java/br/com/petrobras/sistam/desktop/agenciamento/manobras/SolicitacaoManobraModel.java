package br.com.petrobras.sistam.desktop.agenciamento.manobras;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SolicitacaoManobraModel extends BasePresentationModel<SistamService> {

    private Manobra manobra;
    private List<FinalidadeManobra> finalidades;
    private List<PontoAtracacao> pontosAtracacaoOrigem;
    private List<PontoAtracacao> pontosAtracacaoDestino;
    private List<PontoAtracacao> pontosAtracacaoEscala;
    private List<ResponsavelCustoEntity> responsaveisCusto;
    private ServicoManobra sevicoSelecionado;
    private PontoAtracacao pontoAtracacaoOrigemCopia;
    private PontoAtracacao pontoAtracacaoDestinoCopia;
    private PontoAtracacao pontoAtracacaoEscalaCopia;
    private ResponsavelCustoEntity responsaveisCustoCopia;

    public SolicitacaoManobraModel(Manobra manobra) {
        
        pontoAtracacaoOrigemCopia = manobra.getPontoAtracacaoOrigem();
        pontoAtracacaoDestinoCopia = manobra.getPontoAtracacaoDestino();
        pontoAtracacaoEscalaCopia = manobra.getPontoAtracacaoEscala();
        responsaveisCustoCopia = manobra.getResponsavelCusto();
        
        this.manobra = manobra;
        
        finalidades = new ArrayList<FinalidadeManobra> (Arrays.asList(FinalidadeManobra.values()));
        finalidades.add(0, null);
        finalidades = Collections.unmodifiableList(finalidades);
    }
    
    public void salvarManobra() {
        setManobra(getService().salvarManobra(manobra));
    }
    
    public void adicionarServico(ServicoManobra servicoManobra){
        manobra.adicionarServico(servicoManobra);
    }
    
    public void atualizarServico(ServicoManobra servicoManobra){
        manobra.removerServico(sevicoSelecionado);
        manobra.adicionarServico(servicoManobra);
    }
    
    public void excluirServicoManobra() {
        if (sevicoSelecionado.getId() != null){
            getService().excluirServicoManobra(sevicoSelecionado);
        }
        manobra.removerServico(sevicoSelecionado);
    }
    
    public void solicitarServicos() {
        getService().solicitarServicoManobra(manobra);
    } 

    public void carregarPontosAtracaocaoOrigem() {
        List<PontoAtracacao> l = getService().buscarPontoAtracacaoPorAgencia(manobra.getAgenciamento().getAgencia());
        l.add(0, null);
        setPontosAtracacaoOrigem(l);
        manobra.setPontoAtracacaoOrigem(pontoAtracacaoOrigemCopia);
        firePropertyChange("manobra", null, this.manobra);
    }

    public void carregarPontosAtracaocaoDestino() {
        List<PontoAtracacao> l = getService().buscarPontoAtracacaoPorAgencia(manobra.getAgenciamento().getAgencia());
        l.add(0, null);
        setPontosAtracacaoDestino(l);
        manobra.setPontoAtracacaoDestino(pontoAtracacaoDestinoCopia);
        firePropertyChange("manobra", null, this.manobra);
    }

    public void carregarPontosAtracaocaoEscala() {
        List<PontoAtracacao> l = getService().buscarPontoAtracacaoPorAgencia(manobra.getAgenciamento().getAgencia());
        l.add(0, null);
        setPontosAtracacaoEscala(l);
        manobra.setPontoAtracacaoEscala(pontoAtracacaoEscalaCopia);
        firePropertyChange("manobra", null, this.manobra);
    }
    
     public void carregarResponsavelCusto(){
        List<ResponsavelCustoEntity> lista = getService().buscarTodosResponsavelCusto();
        lista.add(0, null);
        setResponsaveisCusto(lista);
        manobra.setResponsavelCusto(responsaveisCustoCopia);
        firePropertyChange("manobra", null, this.manobra); 
    }
    
    public  void atualizarManobra() {
        setManobra(getService().buscarManobrasPorId(manobra.getId()));
    }
    
    
    public Manobra getManobra() {
        return manobra;
    }

    public void setManobra(Manobra manobra) {
        this.manobra = manobra;
        firePropertyChange("manobra", null, this.manobra);
    }

    public List<FinalidadeManobra> getFinalidades() {
        return finalidades;
    }

    public void setFinalidades(List<FinalidadeManobra> finalidades) {
        this.finalidades = finalidades;
    }

    public List<PontoAtracacao> getPontosAtracacaoOrigem() {
        return pontosAtracacaoOrigem;
    }

    public void setPontosAtracacaoOrigem(List<PontoAtracacao> pontosAtracacaoOrigem) {
        this.pontosAtracacaoOrigem = pontosAtracacaoOrigem;
        firePropertyChange("pontosAtracacaoOrigem", null, this.pontosAtracacaoOrigem);
    }

    public List<PontoAtracacao> getPontosAtracacaoDestino() {
        return pontosAtracacaoDestino;
    }

    public void setPontosAtracacaoDestino(List<PontoAtracacao> pontosAtracacaoDestino) {
        this.pontosAtracacaoDestino = pontosAtracacaoDestino;
        firePropertyChange("pontosAtracacaoDestino", null, this.pontosAtracacaoDestino);
    }

    public List<PontoAtracacao> getPontosAtracacaoEscala() {
        return pontosAtracacaoEscala;
    }

    public void setPontosAtracacaoEscala(List<PontoAtracacao> pontosAtracacaoEscala) {
        this.pontosAtracacaoEscala = pontosAtracacaoEscala;
        firePropertyChange("pontosAtracacaoEscala", null, this.pontosAtracacaoEscala);
    }
    
    public List<ResponsavelCustoEntity> getResponsaveisCusto() {
        return responsaveisCusto;
    }

    public void setResponsaveisCusto(List<ResponsavelCustoEntity> responsaveisCusto) {
        this.responsaveisCusto = responsaveisCusto;
        firePropertyChange("responsaveisCusto", null, null);
    }

    public ServicoManobra getSevicoSelecionado() {
        return sevicoSelecionado;
    }

    public void setSevicoSelecionado(ServicoManobra sevicoSelecionado) {
        this.sevicoSelecionado = sevicoSelecionado;
        firePropertyChange("sevicoSelecionado", null, this.sevicoSelecionado);
    }
    
    public ServicoManobra obterNovoServico(){
        ServicoManobra novo = new  ServicoManobra();
        novo.setManobra(manobra);
        return novo;
    }
    
    public ServicoManobra obterServicoParaEdicao(){
        if (sevicoSelecionado.getId() != null){
            return getService().buscarServicoManobrasPorId(sevicoSelecionado.getId());
        }
        return sevicoSelecionado.clone();
    }
      
    
    
    
    
    
}
