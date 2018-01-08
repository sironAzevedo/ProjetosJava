package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransito;
import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoEmpresa;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoVeiculo;
import br.com.petrobras.sistam.common.enums.TipoMaterial;
import br.com.petrobras.sistam.common.enums.TipoMercadorias;
import br.com.petrobras.sistam.common.enums.TipoVeiculo;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import java.util.List;

public final class RegistroDeSolicitacaoDeTransitoModel extends BasePresentationModel<SistamService> {

    private ServicoSuprimento servicoSuprimento = new ServicoSuprimento();
    private ServicoSuprimentoTransito suprimentoTransito;
    private ServicoSuprimentoTransitoEmpresa suprimentoTransitoEmpresa = new ServicoSuprimentoTransitoEmpresa(); 
    private ServicoSuprimentoTransitoEmpresa suprimentoTransitoEmpresaSelecionado;
    private ServicoSuprimentoTransitoVeiculo suprimentoTransitoVeiculo = new ServicoSuprimentoTransitoVeiculo();
    private ServicoSuprimentoTransitoVeiculo suprimentoTransitoVeiculoSelecionado;
    private EmpresaProtecao prestadorServico;
    private PessoaAcesso pessoaAcesso = new PessoaAcesso();
    private List<TipoMercadorias> listaTipoMercadorias;
    private List<TipoMaterial> listaTipoMaterial;
    private List<TipoVeiculo> listaVeiculos;
    private boolean continuarInserindo = true;    
    
    private Pessoa pessoa;
    private boolean edicao = false;
    private boolean editarTerminal;

    public RegistroDeSolicitacaoDeTransitoModel(ServicoSuprimentoTransito suprimentoTransito, boolean edicao,  boolean editarTerminal) {
        carregarCombos();
         
        this.suprimentoTransito = suprimentoTransito; 
        this.edicao = edicao;
        this.editarTerminal = editarTerminal; //VARIAVEL PARA O COMPONENTE DESCRICAO TERMINAL SER EDITADO
        if (StringUtils.isNotBlank(suprimentoTransitoEmpresa.getNomePrestadorServico()) && StringUtils.isNotBlank(suprimentoTransitoVeiculo.getNomeCondutor())) {
            setContinuarInserindo(false);
        }
        if (edicao) {
            carregarPrestadoServicoEmEmpresa();
            carregarCondutorEmVeiculo();
        }
    }
 
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public ServicoSuprimentoTransito getSuprimentoTransito() {
        return suprimentoTransito;
    }

    public void setSuprimentoTransito(ServicoSuprimentoTransito suprimentoTransito) {
        this.suprimentoTransito = suprimentoTransito;
        firePropertyChange("suprimentoTransito", null, null);
    } 
    
    public ServicoSuprimento getServicoSuprimento() {
        return servicoSuprimento;
    }

    public void setServicoSuprimento(ServicoSuprimento servicoSuprimento) {
        this.servicoSuprimento = servicoSuprimento;
        firePropertyChange("servicoSuprimento", null, null);
    }    
    
     public List<TipoMercadorias> getListaTipoMercadorias() {
        return listaTipoMercadorias;
    }

    public void setListaTipoMercadorias(List<TipoMercadorias> listaTipoMercadorias) {
        this.listaTipoMercadorias = listaTipoMercadorias;
        firePropertyChange("listaTipoMercadorias", null, null);
    }          

    public List<TipoMaterial> getListaTipoMaterial() {
        return listaTipoMaterial;
    }

    public void setListaTipoMaterial(List<TipoMaterial> listaTipoMaterial) {
        this.listaTipoMaterial = listaTipoMaterial;
        firePropertyChange("listaTipoMaterial", null, null);
    }

    public List<TipoVeiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<TipoVeiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
        firePropertyChange("listaVeiculos", null, null);
    }   

    public boolean isContinuarInserindo() {
        return continuarInserindo;
    }

    public void setContinuarInserindo(boolean continuarInserindo) {
        this.continuarInserindo = continuarInserindo;
        firePropertyChange("continuarInserindo", null, null);
    }

    public boolean isEdicao() {
        return edicao;
    }

    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
        firePropertyChange("edicao", null, null);
    }

    public boolean isEditarTerminal() {
        return editarTerminal;
    }

    public void setEditarTerminal(boolean editarTerminal) {
        this.editarTerminal = editarTerminal;
        firePropertyChange("editarTerminal", null, null);
    }
    
    public PessoaAcesso getPessoaAcesso() {
        return pessoaAcesso;
    }

    public EmpresaProtecao getPrestadorServico() {
        return prestadorServico;
    }

    public void setPrestadorServico(EmpresaProtecao prestadorServico) {
        this.prestadorServico = prestadorServico;
        firePropertyChange("prestadorServico", null, null);
//        servicoFornecedor.getPessoas().clear(); 
        suprimentoTransitoEmpresa.setNomePrestadorServico(null);
        suprimentoTransitoEmpresa.setCnpjPrestadorServicoComMascara(null);
        if (prestadorServico != null) {
            suprimentoTransitoEmpresa.setNomePrestadorServico(prestadorServico.getRazaoSocial());
            suprimentoTransitoEmpresa.setCnpjPrestadorServicoComMascara(prestadorServico.getCnpjComMascara()); 
//            suprimentoTransitoEmpresa.adicionarPessoas(getService().instanciarPessoasDaEmpresa(prestadorServico));
        }
        firePropertyChange("suprimentoTransitoEmpresa", null, null);
    }
    
    public void setPessoaAcesso(PessoaAcesso pessoaAcesso) {
        this.pessoaAcesso = pessoaAcesso;
        firePropertyChange("pessoaAcesso", null, null);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        firePropertyChange("pessoa", null, null);
        suprimentoTransitoVeiculo.setNomeCondutor(null);
        suprimentoTransitoVeiculo.setCpfComMascara(null); 
        if (pessoa != null) {
            suprimentoTransitoVeiculo.setNomeCondutor(pessoa.getNome());
            suprimentoTransitoVeiculo.setCpfComMascara(pessoa.getCpfComMascara()); 
        }
    }
    
    public ServicoSuprimentoTransitoEmpresa getSuprimentoTransitoEmpresa() {
        return suprimentoTransitoEmpresa;
    }

    public void setSuprimentoTransitoEmpresa(ServicoSuprimentoTransitoEmpresa suprimentoTransitoEmpresa) {
        this.suprimentoTransitoEmpresa = suprimentoTransitoEmpresa;
        firePropertyChange("suprimentoTransitoEmpresa", null, null);
    }  
    
    public ServicoSuprimentoTransitoEmpresa getSuprimentoTransitoEmpresaSelecionado() {
        return suprimentoTransitoEmpresaSelecionado;
    }

    public void setSuprimentoTransitoEmpresaSelecionado(ServicoSuprimentoTransitoEmpresa suprimentoTransitoEmpresaSelecionado) {
        this.suprimentoTransitoEmpresaSelecionado = suprimentoTransitoEmpresaSelecionado;
        firePropertyChange("suprimentoTransitoEmpresaSelecionado", null, null);
    } 

    public ServicoSuprimentoTransitoVeiculo getSuprimentoTransitoVeiculo() {
        return suprimentoTransitoVeiculo;
    }

    public void setSuprimentoTransitoVeiculo(ServicoSuprimentoTransitoVeiculo suprimentoTransitoVeiculo) {
        this.suprimentoTransitoVeiculo = suprimentoTransitoVeiculo;
        firePropertyChange("suprimentoTransitoVeiculo", null, null);
    } 

    public ServicoSuprimentoTransitoVeiculo getSuprimentoTransitoVeiculoSelecionado() {
        return suprimentoTransitoVeiculoSelecionado;
    }

    public void setSuprimentoTransitoVeiculoSelecionado(ServicoSuprimentoTransitoVeiculo suprimentoTransitoVeiculoSelecionado) {
        this.suprimentoTransitoVeiculoSelecionado = suprimentoTransitoVeiculoSelecionado;
        firePropertyChange("suprimentoTransitoVeiculoSelecionado", null, null);
    } 
    
    //</editor-fold>
    
    private void carregarListaTipoMercadorias() {
        List<TipoMercadorias> lista = new ArrayList<TipoMercadorias>();
        lista.add(0, null);
        lista.addAll(1,Arrays.asList(TipoMercadorias.values()));
        setListaTipoMercadorias(lista);
    }
    
    private void carregarListaTipoMaterial() {
        List<TipoMaterial> lista = new ArrayList<TipoMaterial>();
        lista.add(0, null);
        lista.addAll(1, Arrays.asList(TipoMaterial.values()));
        setListaTipoMaterial(lista);
    } 
    
    private void carregarListaTipoVeiculo(){
        List<TipoVeiculo> lista = new ArrayList<TipoVeiculo>();
        lista.add(0, null);
        lista.addAll(1, Arrays.asList(TipoVeiculo.values()));
        setListaVeiculos(lista);
    }

    private void carregarPrestadoServicoEmEmpresa() {
        prestadorServico = new EmpresaProtecao();
        prestadorServico.setRazaoSocial(suprimentoTransitoEmpresa.getNomePrestadorServico());
        prestadorServico.setCnpjComMascara(suprimentoTransitoEmpresa.getCnpjPrestadorServicoComMascara()); 
    }
    
     private void carregarCondutorEmVeiculo(){
         String cpf = suprimentoTransitoVeiculo.getCpfCondutor();
            Pessoa pessoaAtual = null;
            if(StringUtils.isNotBlank(cpf)){
                pessoaAtual = getService().buscarPessoaProtecaoPorCpf(cpf);
            }
            pessoa = new Pessoa();
            if(pessoaAtual != null){
                pessoa.setId(pessoaAtual.getId());
            }
            pessoa.setNome(suprimentoTransitoVeiculo.getNomeCondutor());
            pessoa.setCpfComMascara(suprimentoTransitoVeiculo.getCpfComMascara());
    } 
     
     public void validar() {
         getService().validarCamposObrigatoriosTransito(suprimentoTransito);
     }
     
     public void salvar(){
         suprimentoTransito = (ServicoSuprimentoTransito) getService().salvarServicoProtecaoTransito(suprimentoTransito);
     }
     
     public void adicionarFornecedor(){
         suprimentoTransitoEmpresa.setSolicitacaoTransito(suprimentoTransito);
        if(suprimentoTransito.getId()== null){
            getService().validarCamposObrigatoriosFornecedor(suprimentoTransitoEmpresa);
        }else{
            getService().salvarServicoProtecaoFornecedor(suprimentoTransitoEmpresa);
        }
        
        suprimentoTransito.adicionarSuprimentoTransitoFornecedor(suprimentoTransitoEmpresa);
        prepararNovoFornecedor();  
    }  
    
    public void excluirFornecedor(){
         if(suprimentoTransito.getId()==null){
             getService().excluirServicoProtecaoFornecedor(suprimentoTransitoEmpresaSelecionado);
         }
         suprimentoTransito.removerSuprimentoTransitoFornecedo(suprimentoTransitoEmpresaSelecionado);
    }
    
    public void adicionarVeiculo(){
        suprimentoTransitoVeiculo.setSolicitacaoTransito(suprimentoTransito);
         if(suprimentoTransito.getId()==null){
            getService().validarCamposObrigatoriosCondutor(suprimentoTransitoVeiculo);
        }else{
            getService().salvarServicoProtecaoVeiculo(suprimentoTransitoVeiculo);
        }
        
        suprimentoTransito.adicionarSuprimentoTransitoVeiculo(suprimentoTransitoVeiculo);
        prepararNovoVeiculo();  
    }
    
    public void excluirVeiculo(){
        if(suprimentoTransito.getId()==null){
            getService().excluirServicoProtecaoVeiculo(suprimentoTransitoVeiculoSelecionado);
        }
        suprimentoTransito.removerSuprimentoTransitoVeiculo(suprimentoTransitoVeiculoSelecionado);
    }
    
    public void prepararNovaSolicitaçãoTransito(){
        ServicoSuprimentoTransito novo = new ServicoSuprimentoTransito();
        novo.setServicoProtecao(suprimentoTransito.getServicoProtecao());
        novo.addPropertyChangeListener(this);        
        setSuprimentoTransito(novo);        
    }
    
    public void prepararNovoFornecedor() {
        ServicoSuprimentoTransitoEmpresa novo = new ServicoSuprimentoTransitoEmpresa();
        novo.setSolicitacaoTransito(suprimentoTransito);
        novo.addPropertyChangeListener(this);
        setSuprimentoTransitoEmpresa(novo); 
        setPrestadorServico(null);
    } 
    
     public void prepararNovoVeiculo(){
         ServicoSuprimentoTransitoVeiculo novo = new ServicoSuprimentoTransitoVeiculo();
         novo.setSolicitacaoTransito(suprimentoTransito);
         novo.addPropertyChangeListener(this);
         setSuprimentoTransitoVeiculo(novo);  
         setPessoa(null);
     }
     
     public final void carregarCombos() {
        carregarListaTipoMaterial();
        carregarListaTipoMercadorias();
        carregarListaTipoVeiculo();
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}

