package br.com.petrobras.sistam.common.validator;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.enums.RecursoCA;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import java.util.List;


public class ValidadorPermissao {
    
    protected SistamCredentialsBean credentialsBean;

    public void setCredentialsBean(SistamCredentialsBean credentialsBean) {
        this.credentialsBean = credentialsBean;
    }
    
    public boolean podeEnviarSolicitacaoApoioManobra(Agencia agencia) {
        return credentialsBean.contemRecurso(agencia, RecursoCA.INFORMAR_MANOBRA);
    }
    
    public boolean podeSalvarAgenciamento(Agencia agencia) {
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_AGENCIAMENTO);
    }
    public boolean podeCancelarAgenciamento(Agencia agencia) {
        return credentialsBean.contemRecurso(agencia, RecursoCA.CANCELAR_AGENCIAMENTO);
    }
    
    public boolean podeSalvarManobra(Agencia agencia) {
        return credentialsBean.contemRecurso(agencia, RecursoCA.INFORMAR_MANOBRA);
    }
    
    public boolean podeConfirmarManobra(Agencia agencia) {
        return credentialsBean.contemRecurso(agencia, RecursoCA.INFORMAR_MANOBRA);
    }
    
    public boolean podeSalvarOperacao(Agencia agencia) {
        return credentialsBean.contemRecurso(agencia, RecursoCA.INFORMAR_OPERACAO);
    }
    public boolean podeExcluirOperacao(Agencia agencia) {
        return credentialsBean.contemRecurso(agencia, RecursoCA.EXCLUIR_OPERACAO);
    }
    public boolean podeSalvarDocumentacaoCabotagem(Agencia agencia) {
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_DOCUMENTACAO_CABOTAGEM);
    }
    public boolean podeExcluirDocumentacaoCabotagem(Agencia agencia) {
        return credentialsBean.contemRecurso(agencia, RecursoCA.EXCLUIR_DOCUMENTACAO_CABOTAGEM);
    }

    public boolean podeSalvarEmbarcacao() {
        return credentialsBean.contemRecurso(credentialsBean.getAgenciasAutorizadas().get(0), RecursoCA.SALVAR_EMBARCACAO);
    }
    
    public boolean podeSalvarTaxa(Agencia agencia) {
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_TAXA);
    }

    public boolean podeSalvarCertificado() {
        return credentialsBean.contemRecurso(credentialsBean.getAgenciasAutorizadas().get(0), RecursoCA.SALVAR_CERTIFICADO);
    }
    
    public boolean podeSalvarDocumento(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_DOCUMENTO);
    }
    
    public boolean podeExcluirDocumento(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.EXCLUIR_DOCUMENTO);
    }
    public boolean podeExcluirPendencia(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.EXCLUIR_PENDENCIA);
    }
    
    public boolean podeSalvarAnexo(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.ANEXAR_ARQUIVO);
    }
    
    public boolean podeSalvarVisita(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_VISITA);
    }

    public boolean podeSalvarTransporte(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_VISITA);
    }
    
    public boolean podeSalvarServicoProtecao(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
    
    public boolean podeSalvarEmpresa(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_EMPRESA);
    }

    public boolean podeSalvarAgencia(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_AGENCIA);
    }

    public boolean podeIncluirAgencia(){
        return credentialsBean.contemRecurso(credentialsBean.getAgenciasAutorizadas().get(0), RecursoCA.INCLUIR_AGENCIA);
    }
    
    public boolean podeSalvarAgenciaContato(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_AGENCIA);
    }

    public boolean podeSalvarAgenciaOrgaoDespacho(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_AGENCIA);
    }
    
    public boolean podeSalvarPorto(List<Agencia> agencias){
        boolean temPermissao = false;
        
        for (Agencia agencia : agencias){
            temPermissao = temPermissao || credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_PORTO);
        }
        
        return temPermissao;
    }
    
    public boolean podeSalvarAgenciaPorto(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_PORTO);
    }
    
    public boolean podeSalvarPortoComplemento() {
        return credentialsBean.contemRecurso(credentialsBean.getAgenciasAutorizadas().get(0), RecursoCA.SALVAR_PORTO_COMPLEMENTO);
    }

    public boolean podeSalvarAcompanhamento(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_AGENCIAMENTO);
    }
    
    public boolean podeSalvarServicoHospedagem(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
    
    public boolean podeSalvarHospede(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
    
    public boolean podeAtivarInativarHospede(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
    
    public boolean podeEnviarSolicitacaoTransporte(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
    
    public boolean podeEnviarSolicitacaoHospedagem(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
    public boolean podeEnviarSolicitacaoRetiradaResiduoLixo(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
        
    public boolean podeSalvarPassageiro(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
    
    public boolean podeAtivarInativarPassageiro(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
    
    public boolean podeSalvarServicoAcessoPessoa(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
    
    public boolean podeSalvarPessoa(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
    
    public boolean podeSalvarServicoSuprimento(Agencia agencia){
         return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
    
    public boolean podeSalvarServicoSuprimentoFornecedor(Agencia agencia){
         return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
    
    public boolean podeSalvarServicoSuprimentoVeiculo(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.SALVAR_SERVICO_PROTECAO);
    }
    
    public boolean podeEnviarEmailParaRelatorioTimesheet(Agencia agencia){
        return credentialsBean.contemRecurso(agencia, RecursoCA.RELATORIO_TIMESHEET);
    }
}
