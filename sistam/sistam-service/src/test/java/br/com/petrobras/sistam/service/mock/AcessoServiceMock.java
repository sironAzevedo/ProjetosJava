package br.com.petrobras.sistam.service.mock;

import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.service.impl.AcessoServiceImpl;
import org.mockito.Mockito;

/**
 * Classe AcessoServiceMock
 */
public class AcessoServiceMock extends AcessoServiceImpl{
    private String chave;
    private String nome;

    public void setChave(String chave) {
        this.chave = chave;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public SistamCredentialsBean buscarDadosDeAutenticacao() {
        SistamCredentialsBean credentialsBean = Mockito.mock(SistamCredentialsBean.class);
        Mockito.when(credentialsBean.getLogon()).thenReturn(chave);
        Mockito.when((credentialsBean.getUsername())).thenReturn(nome);
        return credentialsBean;
    }
}
