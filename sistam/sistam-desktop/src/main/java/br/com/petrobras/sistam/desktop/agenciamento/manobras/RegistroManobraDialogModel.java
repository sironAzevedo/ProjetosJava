package br.com.petrobras.sistam.desktop.agenciamento.manobras;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.ResponsavelCustoEntity;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class RegistroManobraDialogModel extends BasePresentationModel<SistamService> {

    private Manobra manobra;
    private List<FinalidadeManobra> finalidades;
    private List<PontoAtracacao> pontosAtracacao;
    private List<ResponsavelCustoEntity>  listaResponsavelCusto;
    private ServicoManobra servicoManobraSelecionado;
    private boolean salvo;
    private boolean modoVisualizacao;
    private boolean modoCancelamentoForaDoPrazo;
    private PontoAtracacao pontoAtracacaoInicialTemp;
    private PontoAtracacao pontoAtracacaoFinalTemp;
    private PontoAtracacao pontoAtracacaoEscalaTemp;
    private ResponsavelCustoEntity responsaveisCustoTemp;

    public RegistroManobraDialogModel(Manobra manobra) {
        this.manobra = manobra;

        pontoAtracacaoInicialTemp = manobra.getPontoAtracacaoOrigem();
        pontoAtracacaoFinalTemp = manobra.getPontoAtracacaoDestino();
        pontoAtracacaoEscalaTemp = manobra.getPontoAtracacaoEscala();
        responsaveisCustoTemp = manobra.getResponsavelCusto();

        finalidades = FinalidadeManobra.listValues();
        finalidades.add(0, null);  
       
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Manobra getManobra() {
        return manobra;
    }

    public void setManobra(Manobra manobra) {
        this.manobra = manobra;
        firePropertyChange("manobra", null, null);
    }

    public TimeZone getTimeZone() {
        if (manobra == null) {
            return null;
        }

        return TimeZone.getTimeZone(manobra.getAgenciamento().getAgencia().getTimezone());
    }

    public List<FinalidadeManobra> getFinalidades() {
        return finalidades;
    }

    public List<PontoAtracacao> getPontosAtracacao() {
        return pontosAtracacao;
    }

    public void setPontosAtracacao(List<PontoAtracacao> pontosAtracacao) {
        this.pontosAtracacao = pontosAtracacao;
        firePropertyChange("pontosAtracacao", null, null);
    }

    public List<ResponsavelCustoEntity> getListaResponsavelCusto() {
        return listaResponsavelCusto;
    }

    public void setListaResponsavelCusto(List<ResponsavelCustoEntity> listaResponsavelCusto) {
        this.listaResponsavelCusto = listaResponsavelCusto;
         firePropertyChange("listaResponsavelCusto", null, null);
    } 
    
    public ServicoManobra getServicoManobraSelecionado() {
        return servicoManobraSelecionado;
    }

    public void setServicoManobraSelecionado(ServicoManobra servicoManobraSelecionado) {
        this.servicoManobraSelecionado = servicoManobraSelecionado;
        firePropertyChange("servicoManobraSelecionado", null, null);
    }

    public boolean isModoVisualizacao() {
        return modoVisualizacao;
    }

    public void setModoVisualizacao(boolean modoVisualizacao) {
        this.modoVisualizacao = modoVisualizacao;
    }

    public boolean isModoCancelamentoForaDoPrazo() {
        return modoCancelamentoForaDoPrazo;
    }

    public void setModoCancelamentoForaDoPrazo(boolean modoCancelamentoForaDoPrazo) {
        this.modoCancelamentoForaDoPrazo = modoCancelamentoForaDoPrazo;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Método de Ação">
    public void registrar() {
        Manobra manobraAtualizada = getService().registrarManobra(manobra);
        setManobra(manobraAtualizada);
        salvo = true;
    }

    public void movimentar() {
        Manobra manobraAtualizada = getService().movimentarManobra(manobra);
        setManobra(manobraAtualizada);
        salvo = true;
    }

    public void cancelarForaDoPrazo(String motivoDoCancelamento) {
        Manobra manobraAtualizada = getService().cancelarManobraForaDoProazo(manobra, motivoDoCancelamento);
        setManobra(manobraAtualizada);
        salvo = true;
    }

    public void adicionarServico(ServicoManobra servicoManobra) {
        manobra.adicionarServico(servicoManobra);
        salvo = true;
    }

    public void atualizarServico(ServicoManobra servicoManobra) {
        manobra.removerServico(servicoManobraSelecionado);
        manobra.adicionarServico(servicoManobra);
        salvo = true;
    }

    public void excluirServico() {
        if (servicoManobraSelecionado.getId() != null) {
            getService().excluirServicoManobra(servicoManobraSelecionado);
        }

        manobra.removerServico(servicoManobraSelecionado);
        salvo = true;
    }

    //</editor-fold>
    public final void carregarPontosAtracacao() {
        if(getPontosAtracacao() == null){
            setPontosAtracacao(new ArrayList<PontoAtracacao>());
        }
        if(getPontosAtracacao().isEmpty()){
            List<PontoAtracacao> l = getService().buscarPontoAtracacaoPorAgencia(manobra.getAgenciamento().getAgencia());
            l.add(0, null);
            setPontosAtracacao(l);
        }
        manobra.setPontoAtracacaoOrigem(pontoAtracacaoInicialTemp);
        manobra.setPontoAtracacaoDestino(pontoAtracacaoFinalTemp);
        manobra.setPontoAtracacaoEscala(pontoAtracacaoEscalaTemp);
    }
    
     public void carregarResponsavelCusto() {
        List<ResponsavelCustoEntity> lista = new ArrayList<ResponsavelCustoEntity>();
        lista = getService().buscarTodosResponsavelCusto();
        lista.add(0, null);
        setListaResponsavelCusto(lista);
        manobra.setResponsavelCusto(responsaveisCustoTemp);
        firePropertyChange("manobra", null, this.manobra);  
    }

    public boolean isHabilitarBotaoMovimentar() {
        return manobra.isNova() || (!manobra.isNova() && !manobra.isRegistrada()) && !manobra.isCanceladaForaDoPrazo();
    }

    public ServicoManobra obterNovoServicoManobra() {
        ServicoManobra novo = new ServicoManobra();
        novo.setManobra(manobra);
        return novo;
    }

    public ServicoManobra obterServicoManobraParaEdicao() {
        if (servicoManobraSelecionado.getId() != null) {
            return getService().buscarServicoManobrasPorId(servicoManobraSelecionado.getId());
        }
        return (ServicoManobra) servicoManobraSelecionado.clone();
    }

    public boolean estaSalvo() {
        return salvo;
    }
}
