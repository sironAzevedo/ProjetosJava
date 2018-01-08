package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import java.beans.PropertyChangeEvent;
import org.apache.commons.lang.StringUtils;

public final class RegistroPessoaModel extends BasePresentationModel<SistamService> {

    private boolean continuarInserindo = true;
    private boolean edicao = false;
    private PessoaAcesso pessoaAcesso;
    private Pessoa pessoa;

    public RegistroPessoaModel(PessoaAcesso pessoaAcesso, boolean edicao) {
        this.pessoaAcesso = pessoaAcesso;
        this.edicao = edicao;
        if (StringUtils.isNotBlank(pessoaAcesso.getNome())) {
            setContinuarInserindo(false);
        }
        if(edicao){
            carregarPessoaAcessoEmPessoa();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
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

    public PessoaAcesso getPessoaAcesso() {
        return pessoaAcesso;
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
        pessoaAcesso.setNome(null);
        pessoaAcesso.setCpfComMascara(null);
        pessoaAcesso.setDocumento(null);
        pessoaAcesso.setTipoDocumentoIdentificacao(null);
        pessoaAcesso.setDataNascimento(null);
        pessoaAcesso.setNacionalidade(null);
        pessoaAcesso.setNomeEmpresa(null);
        pessoaAcesso.setCnpjEmpresa(null);
        if (pessoa != null) {
            pessoaAcesso.setNome(pessoa.getNome());
            pessoaAcesso.setCpfComMascara(pessoa.getCpfComMascara());
            pessoaAcesso.setDocumento(pessoa.getDocumento());
            pessoaAcesso.setTipoDocumentoIdentificacao(pessoa.getTipoDocumentoIdentificacao());
            pessoaAcesso.setDataNascimento(pessoa.getDataNascimento());
            pessoaAcesso.setNacionalidade(pessoa.getNacionalidade());
            if (pessoa.getEmpresa() != null) {
                pessoaAcesso.setNomeEmpresa(pessoa.getEmpresa().getRazaoSocial());
                pessoaAcesso.setCnpjEmpresa(pessoa.getEmpresa().getCnpj());
            }
        }
    }

    //</editor-fold>

    private void carregarPessoaAcessoEmPessoa(){
        String cpf = pessoaAcesso.getCpf();
            Pessoa pessoaAtual = null;
            if(StringUtils.isNotBlank(cpf)){
                pessoaAtual = getService().buscarPessoaProtecaoPorCpf(cpf);
            }
            pessoa = new Pessoa();
            if(pessoaAtual != null){
                pessoa.setId(pessoaAtual.getId());
            }
            pessoa.setNome(pessoaAcesso.getNome());
            pessoa.setDocumento(pessoaAcesso.getDocumento());
            pessoa.setTipoDocumentoIdentificacao(pessoaAcesso.getTipoDocumentoIdentificacao());
            pessoa.setCpfComMascara(pessoaAcesso.getCpfComMascara());
            pessoa.setDataNascimento(pessoaAcesso.getDataNascimento());
            pessoa.setNacionalidade(pessoaAcesso.getNacionalidade());
            if(StringUtils.isNotBlank(pessoaAcesso.getCnpjEmpresa())){
                EmpresaProtecao empresa = new EmpresaProtecao();
                empresa.setRazaoSocial(pessoaAcesso.getNomeEmpresa());
                empresa.setCnpj(pessoaAcesso.getCnpjEmpresa());
                pessoa.setEmpresa(empresa);
            }
    }
    
    public void validar() {
        getService().validarCamposObrigatoriosPessoa(pessoaAcesso);
    }

    public void prepararNovoPessoa() {
        PessoaAcesso novo = new PessoaAcesso();
        novo.setAtivo(Boolean.TRUE);
        novo.setServicoProtecao(pessoaAcesso.getServicoProtecao());
        novo.addPropertyChangeListener(this);
        setPessoaAcesso(novo);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
    
}