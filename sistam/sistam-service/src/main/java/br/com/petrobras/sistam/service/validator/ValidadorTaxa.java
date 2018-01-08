package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.valueobjects.FiltroTaxa;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTaxa {
    public void validarSalvarTaxa(Taxa taxa){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(taxa.getAgenciamento()).orRegister(TipoExcecao.TAXA, ConstantesI18N.TAXA_SEM_AGENCIAMENTO);
        pm.assertNotNull(taxa.getTipoTaxa()).orRegister(TipoExcecao.TAXA, ConstantesI18N.TAXA_SEM_TIPO_TAXA);
        pm.assertNotNull(taxa.getValor()).orRegister(TipoExcecao.TAXA, ConstantesI18N.TAXA_SEM_VALOR);
        pm.assertNotNull(taxa.getDataPagamento()).orRegister(TipoExcecao.TAXA, ConstantesI18N.TAXA_SEM_DATA_PAGAMENTO);
        pm.assertNotNull(taxa.getResponsavelCusto()).orRegister(TipoExcecao.TAXA, ConstantesI18N.TAXA_SEM_RESPONSAVEL_CUSTO);
        pm.verifyAll();
    }
    
    public void validarBuscarTaxasValorAcumulado(FiltroTaxa filtro){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(filtro.getAgencia()).orRegister(TipoExcecao.TAXA, ConstantesI18N.RELATORIO_TAXAS_AGENCIA_OBRIGATORIA);
        pm.assertNotNull(filtro.getDataPagamentoInicial()).orRegister(TipoExcecao.TAXA, ConstantesI18N.RELATORIO_TAXAS_PERIODO_OBRIGATORIO);
        pm.assertNotNull(filtro.getDataPagamentoFinal()).orRegister(TipoExcecao.TAXA, ConstantesI18N.RELATORIO_TAXAS_PERIODO_OBRIGATORIO);
        pm.verifyAll();
    }
    
}
