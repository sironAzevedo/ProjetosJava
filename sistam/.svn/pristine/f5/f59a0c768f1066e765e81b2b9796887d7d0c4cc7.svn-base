/*
 * Serviço de controle do serviço proteção.Todo o acesso à API do
 * serviço proteção deve ser feito através deste serviço. 
 * 
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.EmpresaProtecao;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransito;
import br.com.petrobras.sistam.common.entity.Hospede;
import br.com.petrobras.sistam.common.entity.Passageiro;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.entity.ServicoExecutado;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoEmpresa;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoVeiculo;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoHospedagem;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoTransporte;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioServicoSuprimentoAosNavios;
import br.com.petrobras.sistam.common.valueobjects.PessoaAcessoVO;
import java.util.List;
import java.util.Set;

public interface ServicoProtecaoService {

    @AuthorizedResource("")
    public List<PessoaAcesso> instanciarPessoasDaEmpresa(EmpresaProtecao empresa);

    @AuthorizedResource("")
    public List<ServicoExecutado> buscarServicosExecutados(Agenciamento agenciamento);

    @AuthorizedResource("")
    public ServicoExecutado salvarServicoExecutado(ServicoExecutado servicoExecutado);

    @AuthorizedResource("")
    public ServicoProtecao salvarServicoProtecao(ServicoProtecao servicoProtecao);

    @AuthorizedResource("")
    public ServicoProtecao confirmarEnvioEmailServicoProtecao(ServicoProtecao servicoProtecao);

    @AuthorizedResource("")
    public void salvarServicoProtecaoMedicoOdontologico(ServicoExecutado servicoExecutado);

    //<editor-fold defaultstate="collapsed" desc="Serviço Transporte">
    @AuthorizedResource("")
    public void salvarServicoProtecaoTransporte(ServicoExecutado servicoExecutado);

    @AuthorizedResource("")
    public void excluirServicoProtecao(ServicoProtecao servicoExecutado);

    @AuthorizedResource("")
    public void excluirServicoExecutado(ServicoExecutado servicoExecutado);

    @AuthorizedResource("")
    public ServicoExecutado cancelarServicoExecutado(ServicoExecutado servicoExecutado);

    @AuthorizedResource("")
    public void enviarFormularioSolicitacaoTransporte(FiltroEnvioSolicitacaoTransporte filtro);

    @AuthorizedResource("")
    public String montarCorpoEmailSolicitacaoTransporte(FiltroEnvioSolicitacaoTransporte filtro);

    @AuthorizedResource("")
    public Set<String> destinatariosEmailSolicitacaoTransporte(FiltroEnvioSolicitacaoTransporte filtro);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Serviço Hospedagem">
    @AuthorizedResource("")
    public void salvarServicoProtecaoHospedagem(ServicoExecutado servicoExecutado);

    @AuthorizedResource("")
    public Hospede salvarHospede(Hospede hospede);

    @AuthorizedResource("")
    void validarCamposObrigatoriosHospede(Hospede hospede);

    @AuthorizedResource("")
    ServicoHospedagem buscarServicoHospedagemPorId(Long id);

    @AuthorizedResource("")
    void enviarEmailSolicitacaoHospedagem(FiltroEnvioSolicitacaoHospedagem filtro);

    @AuthorizedResource("")
    public String montarCorpoEmailSolicitacaoHospedagem(FiltroEnvioSolicitacaoHospedagem filtro);

    @AuthorizedResource("")
    public Set<String> destinatariosEmailSolicitacaoHospedagem(FiltroEnvioSolicitacaoHospedagem filtro);

    @AuthorizedResource("")
    ServicoTransporte buscarServicoTransportePorId(Long id);

    @AuthorizedResource("")
    public Passageiro salvarPassageiro(Passageiro passageiro);

    @AuthorizedResource("")
    public void validarCamposObrigatoriosPassageiro(Passageiro passageiro);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Serviço Acesso Pessoa">
    @AuthorizedResource("")
    public void salvarServicoProtecaoAcessoPessoa(ServicoExecutado servicoExecutado);

    @AuthorizedResource("")
    public ServicoAcessoPessoa buscarServicoAcessoPessoaPorId(Long id);

    @AuthorizedResource("")
    public void validarCamposObrigatoriosPessoa(PessoaAcesso pessoa);

    @AuthorizedResource("")
    public void alterarPessoaStatusFormularioGeradoPolicia(List<PessoaAcessoVO> pessoaVOs);

    @AuthorizedResource("")
    public void alterarPessoaStatusFormularioGeradoDesEmbarqueTripulantes(List<PessoaAcessoVO> pessoaVOs);

    @AuthorizedResource("")
    public void alterarPessoaStatusFormularioGeradoPrestacaoServico(List<PessoaAcessoVO> pessoaVOs);

    @AuthorizedResource("")
    public void alterarPessoaStatusFormularioGeradoReceita(List<PessoaAcessoVO> pessoaVOs);

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Serviço Suprimento Ao Navio">
    @AuthorizedResource("")
    public void salvarServicoProtecaoSuprimentoAosNavios(ServicoExecutado servicoExecutado);

    @AuthorizedResource("")
    public ServicoSuprimento buscarServicoSuprimentoPorId(Long id);

    //</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="Serviço Suprimento Transito">
    @AuthorizedResource("")
    public ServicoSuprimentoTransito salvarServicoProtecaoTransito(ServicoSuprimentoTransito suprimentoTransito);

    @AuthorizedResource("")
    public void validarCamposObrigatoriosTransito(ServicoSuprimentoTransito suprimentoTransito);

    @AuthorizedResource("")
    public void alterarSuprimentoTransitoStatusFormularioGerado(ServicoSuprimentoTransito suprimentoTransito);
    
    @AuthorizedResource("")
    public void excluirServicoSuprimentoTransito(ServicoSuprimentoTransito suprimentoTransito);

    //</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="Serviço Suprimento Empresa">
    @AuthorizedResource("")
    public ServicoSuprimentoTransitoEmpresa salvarServicoProtecaoFornecedor(ServicoSuprimentoTransitoEmpresa suprimentoTransitoEmpresa);

    @AuthorizedResource("")
    public void excluirServicoProtecaoFornecedor(ServicoSuprimentoTransitoEmpresa suprimentoTransitoEmpresa);

    @AuthorizedResource("")
    public void validarCamposObrigatoriosFornecedor(ServicoSuprimentoTransitoEmpresa suprimentoTransitoEmpresa);

    @AuthorizedResource("")
    public List<ServicoSuprimentoTransitoEmpresa> buscarServicoSuprimentoTransitoEmpresaParaRelatorio(FiltroRelatorioServicoSuprimentoAosNavios filtro);

    //</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="Serviço Suprimento Condutor">
    @AuthorizedResource("")
    public ServicoSuprimentoTransitoVeiculo salvarServicoProtecaoVeiculo(ServicoSuprimentoTransitoVeiculo suprimentoTransitoVeiculo);

    @AuthorizedResource("")
    public void excluirServicoProtecaoVeiculo(ServicoSuprimentoTransitoVeiculo suprimentoTransitoVeiculo);

    @AuthorizedResource("")
    public void validarCamposObrigatoriosCondutor(ServicoSuprimentoTransitoVeiculo suprimentoTransitoVeiculo);
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Serviço Retirada de Resíduos/Lixo">
    @AuthorizedResource("")
    public void salvarServicoProtecaoRetiradaResiduoLixo(ServicoExecutado servicoExecutado);

    @AuthorizedResource("")
    public ServicoRetiradaResiduoLixo buscarServicoRetiradaResiduoLixoPorId(Long id);
    
    @AuthorizedResource("")
    public void enviarFormularioSolicitacaoRetiradaResiduoLixo(FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro);

    @AuthorizedResource("")
    public String montarCorpoEmailSolicitacaoRetiradaResiduoLixo(FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro);

    @AuthorizedResource("")
    public Set<String> destinatariosEmailSolicitacaoRetiradaResiduoLixo(FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro);  
                             
    //</editor-fold>    
}
