package br.com.petrobras.sistam.service.mock;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import java.util.List;


public class ValidadorPermissaoMock extends ValidadorPermissao {
    
    @Override
    public boolean podeEnviarSolicitacaoApoioManobra(Agencia agencia) {
        return true;
    }
    
    @Override
    public boolean podeSalvarAgenciamento(Agencia agencia) {
        return true;
    }
    
    @Override
    public boolean podeCancelarAgenciamento(Agencia agencia) {
        return true;
    }

    
    @Override
    public boolean podeSalvarManobra(Agencia agencia) {
        return true;
    }
    
    @Override
    public boolean podeConfirmarManobra(Agencia agencia) {
        return true;
    }
    
    @Override
     public boolean podeSalvarOperacao(Agencia agencia) {
        return true;
    }
    
    @Override
     public boolean podeExcluirOperacao(Agencia agencia) {
        return true;
    }
    @Override
    public boolean podeSalvarEmbarcacao() {
        return true;
    }
    
    @Override
    public boolean podeSalvarTaxa(Agencia agencia) {
        return true;
    }
    
    @Override
    public boolean podeSalvarCertificado() {
        return true;
    }
    
   @Override
    public boolean podeSalvarDocumento(Agencia agencia) {
        return true;
    }
   
    @Override
   public boolean podeSalvarAnexo(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarVisita(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarTransporte(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarServicoProtecao(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarEmpresa(Agencia agencia){
        return true;
    }

    @Override
    public boolean podeSalvarAgencia(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeIncluirAgencia(){
        return true;
    }
    
    @Override
    public boolean podeSalvarAgenciaContato(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarAgenciaOrgaoDespacho(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarPorto(List<Agencia> agencias){
        return true;
    }
    
    @Override
    public boolean podeSalvarDocumentacaoCabotagem(Agencia agencia) {
        return true;
    }
    
    
    @Override
    public boolean podeExcluirDocumentacaoCabotagem(Agencia agencia) {
        return true;
    }
    
    @Override
     public boolean podeExcluirDocumento(Agencia agencia){
        return true;
    }
    
    @Override
        public boolean podeExcluirPendencia(Agencia agencia){
        return true;
    }

    @Override
    public boolean podeSalvarAcompanhamento(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarServicoHospedagem(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarHospede(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeAtivarInativarHospede(Agencia agencia){
        return true;
    }
    
    public boolean podeEnviarSolicitacaoTransporte(Agencia agencia){
        return true;
    }
    
    public boolean podeEnviarSolicitacaoHospedagem(Agencia agencia){
        return true;
    }
    
    public boolean podeEnviarSolicitacaoRetiradaResiduoLixo(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarPassageiro(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeAtivarInativarPassageiro(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarPessoa(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarServicoAcessoPessoa(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarServicoSuprimento(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarServicoSuprimentoFornecedor(Agencia agencia){
        return true;
    }
    
    @Override
    public boolean podeSalvarServicoSuprimentoVeiculo(Agencia agencia){
        return true;
    }
    
    
    
    
}
