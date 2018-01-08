package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;
import br.com.petrobras.sistam.common.entity.Destinatario;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAgencia {
    
    public void validarSalvarAgencia(Agencia agencia){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotEmpty(agencia.getNome()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_NOME_OBRIGATORIO);
        pm.assertNotEmpty(agencia.getSigla()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_SIGLA_OBRIGATORIA);
        pm.assertNotEmpty(agencia.getEndereco()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_ENDERECO_OBRIGATORIO);
        pm.assertNotEmpty(agencia.getCidade()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_CIDADE_OBRIGATORIO);
        pm.assertNotEmpty(agencia.getEstado()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_ESTADO_OBRIGATORIO);
        pm.assertNotEmpty(agencia.getEmail()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_EMAIL_OBRIGATORIO);
        pm.assertNotEmpty(agencia.getTelefone()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_TELEFONE_OBRIGATORIO);
        pm.verifyAll();
    }
    
    public void validarSalvarAgenciaContato(RepresentanteLegal agenciaContato) {
        AssertUtils.assertNotNull(agenciaContato.getAgencia(), ConstantesI18N.AGENCIA_CONTATO_AGENCIA_NAO_PODE_SER_NULA);

        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotEmpty(agenciaContato.getNome()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_CONTATO_NOME_OBRIGATORIO);
        pm.assertNotNull(agenciaContato.getCpf()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_CONTATO_CPF_OBRIGATORIO);
        pm.verifyAll();
    }

    public void validarSalvarAgenciaOrgaoDespacho(AgenciaOrgaoDespacho agenciaOrgaoDespacho) {
        AssertUtils.assertNotNull(agenciaOrgaoDespacho.getAgencia(), ConstantesI18N.AGENCIA_ORGAO_DESPACHO_AGENCIA_NAO_PODE_SER_NULA);

        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotEmpty(agenciaOrgaoDespacho.getNome()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_ORGAO_DESPACHO_NOME_OBRIGATORIO);
        pm.verifyAll();
    }

    public void validarSalvarDestinatario(Destinatario destinatario) {
        SistamPendencyManager pm = new SistamPendencyManager();
        
        pm.assertNotNull(destinatario.getAgencia()).orRegister(TipoExcecao.DESTINATARIO_AGENCIA, ConstantesI18N.DESTINATARIO_AGENCIA_OBRIGATORIA);
        pm.assertNotEmpty(destinatario.getNome()).orRegister(TipoExcecao.DESTINATARIO_AGENCIA, ConstantesI18N.DESTINATARIO_NOME_OBRIGATORIO);
        pm.assertNotEmpty(destinatario.getEmail()).orRegister(TipoExcecao.DESTINATARIO_AGENCIA, ConstantesI18N.DESTINATARIO_EMAIL_OBRIGATORIO);
        
        pm.verifyAll();

    }

}
