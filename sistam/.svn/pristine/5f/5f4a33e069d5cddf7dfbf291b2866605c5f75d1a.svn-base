package br.com.petrobras.sistam.service.validator;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransito;
import br.com.petrobras.sistam.common.entity.Hospede;
import br.com.petrobras.sistam.common.entity.Passageiro;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.entity.ServicoExecutado;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.entity.ServicoMedicoOdontologico;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoEmpresa;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoVeiculo;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.enums.TipoCategoria;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoResiduo;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioServicoSuprimentoAosNavios;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ValidadorServicoProtecao {

    public void validarSalvarServicoProtecao(ServicoProtecao servicoProtecao) {
        AssertUtils.assertNotNull(servicoProtecao.getAgenciamento(), ConstantesI18N.SERVICO_PROTECAO_AGENCIAMENTO_OBRIGATORIO);

        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(servicoProtecao.getTipoAtendimentoServico()).orRegister(TipoExcecao.SERVICO_PROTECAO, ConstantesI18N.SERVICO_PROTECAO_SERVICO_EXECUTADO_OBRIGATORIO);
        pm.assertNotNull(servicoProtecao.getDataExecucao()).orRegister(TipoExcecao.SERVICO_PROTECAO, ConstantesI18N.SERVICO_PROTECAO_DATA_EXECUCAO_OBRIGATORIO);
        pm.assertNotNull(servicoProtecao.getId() == null && StringUtils.isNotBlank(servicoProtecao.getObservacao())).orRegister(TipoExcecao.SERVICO_PROTECAO, ConstantesI18N.SERVICO_PROTECAO_OBSERVACAO_OBRIGATORIA_NA_ALTERACAO);
        pm.verifyAll();
    }

    public void validarCancelamentoServicoExecutado(ServicoExecutado servicoExecutado) {
        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertNotNull(servicoExecutado.getServicoProtecao().getDataCancelamento()).orRegister(TipoExcecao.SERVICO_PROTECAO,
                ConstantesI18N.SERVICO_PROTECAO_DATA_CANCELAMENTO_OBRIGATORIA);

        pm.assertNotEmpty(servicoExecutado.getServicoProtecao().getJustificativa()).orRegister(TipoExcecao.SERVICO_PROTECAO,
                ConstantesI18N.SERVICO_PROTECAO_JUSTIFICATIVA_CANCELAMENTO_OBRIGATORIA);

        pm.verifyAll();
    }

    public void validarSalvarServicoMedicoOdontologico(ServicoMedicoOdontologico servicoMedicoOdontologico) {
        AssertUtils.assertNotNull(servicoMedicoOdontologico.getServicoProtecao(), ConstantesI18N.SERVICO_MEDICO_ODONTOLOGICO_SERVICO_PROTECAO_OBRIGATORIO);

        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(servicoMedicoOdontologico.getNomeTripulante()).orRegister(TipoExcecao.SERVICO_MEDICO_ODONTOLOGICO, ConstantesI18N.SERVICO_MEDICO_ODONTOLOGICO_NOME_TRIPULANTE_OBRIGATORIO);

        pm.verifyAll();
    }

    public void validarSalvarServicoTransporte(ServicoTransporte servicoTransporte) {

        AssertUtils.assertNotNull(servicoTransporte.getServicoProtecao(), ConstantesI18N.SERVICO_TRANSPORTE_SERVICO_PROTECAO_OBRIGATORIO);

        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertNotNull(servicoTransporte.getDataServico()).orRegister(TipoExcecao.SERVICO_TRANSPORTE, ConstantesI18N.SERVICO_TRANSPORTE_DATA_HORA_SERVICO_OBRIGATORIO);
        pm.assertNotNull(servicoTransporte.getTipoTransporte()).orRegister(TipoExcecao.SERVICO_TRANSPORTE, ConstantesI18N.SERVICO_TRANSPORTE_TIPO_TRANSPORTE_OBRIGATORIO);

        if (TipoTransporte.AEREO.equals(servicoTransporte.getTipoTransporte())) {
            validarSalvarServicoTransporteAereo(pm, servicoTransporte);
        } else {
            validarSalvarServicoTransporteOutros(pm, servicoTransporte);
        }

        if (!servicoTransporte.getServicoProtecao().isNovo()) {
            pm.assertNotEmpty(servicoTransporte.getServicoProtecao().getObservacao()).orRegister(TipoExcecao.SERVICO_TRANSPORTE, ConstantesI18N.SERVICO_TRANSPORTE_OBSERVACAO_OBRIGATORIO);
        }

        pm.verifyAll();
    }

    private void validarSalvarServicoTransporteAereo(SistamPendencyManager pm, ServicoTransporte servicoTransporte) {
        pm.assertThat(servicoTransporte.getPassageiros() != null && !servicoTransporte.getPassageiros().isEmpty()).orRegister(TipoExcecao.SERVICO_TRANSPORTE, ConstantesI18N.SERVICO_TRANSPORTE_AEREO_OBRIGATORIO_PASSAGEIROS);
    }

    private void validarSalvarServicoTransporteOutros(SistamPendencyManager pm, ServicoTransporte servicoTransporte) {
        pm.assertNotNull(servicoTransporte.getEmpresaServico()).orRegister(TipoExcecao.SERVICO_TRANSPORTE, ConstantesI18N.SERVICO_TRANSPORTE_NOME_EMPRESA_OBRIGATORIO);
        pm.assertThat(!TipoTransporte.TERRESTRE.equals(servicoTransporte.getTipoTransporte()) || (servicoTransporte.getPassageiros() != null && !servicoTransporte.getPassageiros().isEmpty())).orRegister(TipoExcecao.SERVICO_TRANSPORTE, ConstantesI18N.SERVICO_TRANSPORTE_TERRESTRE_OBRIGATORIO_PASSAGEIROS);
        pm.assertThat(!TipoTransporte.FLUVIAL.equals(servicoTransporte.getTipoTransporte()) || (servicoTransporte.getPassageiros() != null && !servicoTransporte.getPassageiros().isEmpty())).orRegister(TipoExcecao.SERVICO_TRANSPORTE, ConstantesI18N.SERVICO_TRANSPORTE_FLUVIAL_OBRIGATORIO_PASSAGEIROS);
        pm.assertNotEmpty(servicoTransporte.getOrigem()).orRegister(TipoExcecao.SERVICO_TRANSPORTE, ConstantesI18N.SERVICO_TRANSPORTE_ORIGEM_OBRIGATORIO);
        pm.assertNotEmpty(servicoTransporte.getDestino()).orRegister(TipoExcecao.SERVICO_TRANSPORTE, ConstantesI18N.SERVICO_TRANSPORTE_DESTINO_OBRIGATORIO);
    }

    public void validarSalvarServicoHospedagem(ServicoHospedagem servicoHospedagem) {
        AssertUtils.assertNotNull(servicoHospedagem.getServicoProtecao(), ConstantesI18N.SERVICO_HOSPEDAGEM_SERVICO_PROTECAO_OBRIGATORIO);

        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(servicoHospedagem.getEmpresaServico()).orRegister(TipoExcecao.SERVICO_HOSPEDAGEM, ConstantesI18N.SERVICO_HOSPEDAGEM_HOTEL_OBRIGATORIO);
        pm.assertNotNull(servicoHospedagem.getDataChegada()).orRegister(TipoExcecao.SERVICO_HOSPEDAGEM, ConstantesI18N.SERVICO_HOSPEDAGEM_DATA_CHEGADA_OBRIGATORIO);
        pm.assertNotNull(servicoHospedagem.getDataSaida()).orRegister(TipoExcecao.SERVICO_HOSPEDAGEM, ConstantesI18N.SERVICO_HOSPEDAGEM_DATA_SAIDA_OBRIGATORIO);

        if (servicoHospedagem.getDataChegada() != null && servicoHospedagem.getDataSaida() != null) {
            pm.assertThat(!SistamDateUtils.truncateTime(servicoHospedagem.getDataChegada(), null).after(SistamDateUtils.truncateTime(servicoHospedagem.getDataSaida(), null))).orRegister(TipoExcecao.SERVICO_HOSPEDAGEM, ConstantesI18N.SERVICO_HOSPEDAGEM_DATA_CHEGADA_NAO_DEVE_SER_POSTERIOR_DATA_SAIDA);
        }

        pm.assertNotEmpty(servicoHospedagem.getHospedes()).orRegister(TipoExcecao.SERVICO_HOSPEDAGEM, ConstantesI18N.SERVICO_HOSPEDAGEM_INFORME_PELO_MENOS_UM_HOSPEDE);

        if (!servicoHospedagem.getServicoProtecao().isNovo()) {
            pm.assertNotEmpty(servicoHospedagem.getServicoProtecao().getObservacao()).orRegister(TipoExcecao.SERVICO_HOSPEDAGEM, ConstantesI18N.SERVICO_HOSPEDAGEM_OBSERVACAO_OBRIGATORIO);
        }

        pm.verifyAll();
    }

    public void validarSalvarServicoAcessoPessoa(ServicoAcessoPessoa servicoAcessoPessoa) {

        AssertUtils.assertNotNull(servicoAcessoPessoa.getServicoProtecao(), ConstantesI18N.SERVICO_ACESSO_PESSOA_SERVICO_PROTECAO_OBRIGATORIO);

        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertThat(servicoAcessoPessoa.isCompanhiaDocas() || servicoAcessoPessoa.isTerminal()).orRegister(TipoExcecao.SERVICO_ACESSO_PESSOA, ConstantesI18N.SERVICO_ACESSO_PESSOA_SELECIONE_LOCAL_DO_ACESSO);
        if (servicoAcessoPessoa.isTerminal()) {
            pm.assertNotEmpty(servicoAcessoPessoa.getDescricaoTerminal()).orRegister(TipoExcecao.SERVICO_ACESSO_PESSOA, ConstantesI18N.SERVICO_ACESSO_PESSOA_INFORME_TERMINAL);
        }
        pm.assertNotNull(servicoAcessoPessoa.getTipoSolicitacaoTransito()).orRegister(TipoExcecao.SERVICO_ACESSO_PESSOA, ConstantesI18N.SERVICO_ACESSO_PESSOA_SELECIONE_TIPO_SOLI_TRANSITO_OBRIGATORIO);
        pm.assertNotNull(servicoAcessoPessoa.getTipoAcesso()).orRegister(TipoExcecao.SERVICO_ACESSO_PESSOA, ConstantesI18N.SERVICO_ACESSO_PESSOA_SELECIONE_TIPO_ACESSO_OBRIGATORIO);
        pm.assertNotNull(servicoAcessoPessoa.getTipoCategoria()).orRegister(TipoExcecao.SERVICO_ACESSO_PESSOA, ConstantesI18N.SERVICO_ACESSO_PESSOA_SELECIONE_TIPO_CATEGORIA_OBRIGATORIO);
        pm.assertNotNull(servicoAcessoPessoa.getTipoNacionalidade()).orRegister(TipoExcecao.SERVICO_ACESSO_PESSOA, ConstantesI18N.SERVICO_ACESSO_PESSOA_SELECIONE_TIPO_NACIONALIDADE_OBRIGATORIO);

        if (servicoAcessoPessoa.getTipoCategoria() != null && servicoAcessoPessoa.getTipoCategoria().equals(TipoCategoria.PRESTADOR_SERVICO)) {
            pm.assertNotEmpty(servicoAcessoPessoa.getCnpjPrestadorServico()).orRegister(TipoExcecao.SERVICO_ACESSO_PESSOA, ConstantesI18N.SERVICO_ACESSO_PESSOA_EMPRESA_OBRIGATORIO);
            pm.assertNotEmpty(servicoAcessoPessoa.getPrestadorServicoAtividadeBordo()).orRegister(TipoExcecao.SERVICO_ACESSO_PESSOA, ConstantesI18N.SERVICO_ACESSO_PESSOA_ATIVIDADE_BORDO_OBRIGATORIO);

            if (StringUtils.isNotBlank(servicoAcessoPessoa.getCnpjPrestadorServico())) {
                Map<String, Integer> documentos = new HashMap<String, Integer>();
                Map<String, Integer> cpfs = new HashMap<String, Integer>();
                boolean pessoaDaEmpresa = true;
                for (PessoaAcesso pessoaAcesso : servicoAcessoPessoa.getPessoasAsList()) {
                    String cnpjEmpresa = pessoaAcesso.getCnpjEmpresa();
                    if (!servicoAcessoPessoa.getCnpjPrestadorServico().equalsIgnoreCase(cnpjEmpresa)) {
                        pessoaDaEmpresa = false;
                    }

                    String documento = pessoaAcesso.getDocumento();
                    int total = 0;
                    if (StringUtils.isNotBlank(documento)) {
                        if (documentos.containsKey(documento)) {
                            total = documentos.get(documento);
                        }
                        total++;
                        documentos.put(documento, total);
                    }

                    String cpf = pessoaAcesso.getCpf();
                    total = 0;
                    if (StringUtils.isNotBlank(cpf)) {
                        if (cpfs.containsKey(cpf)) {
                            total = cpfs.get(cpf);
                        }
                        total++;
                        cpfs.put(cpf, total);
                    }
                }

                boolean documentosRepetidos = false;
                for (Map.Entry<String, Integer> map : documentos.entrySet()) {
                    Integer total = map.getValue();
                    if (total > 1) {
                        documentosRepetidos = true;
                        break;
                    }
                }

                boolean cpfsRepetidos = false;
                for (Map.Entry<String, Integer> map : cpfs.entrySet()) {
                    Integer total = map.getValue();
                    if (total > 1) {
                        cpfsRepetidos = true;
                        break;
                    }
                }

                pm.assertThat(!documentosRepetidos).orRegister(TipoExcecao.SERVICO_ACESSO_PESSOA, ConstantesI18N.SERVICO_ACESSO_PESSOA_PESSOAS_DOCUMENTO_JA_EXISTENTE);
                pm.assertThat(!cpfsRepetidos).orRegister(TipoExcecao.SERVICO_ACESSO_PESSOA, ConstantesI18N.SERVICO_ACESSO_PESSOA_PESSOAS_CPF_JA_EXISTENTE);
                pm.assertThat(pessoaDaEmpresa).orRegister(TipoExcecao.SERVICO_ACESSO_PESSOA, ConstantesI18N.SERVICO_ACESSO_PESSOA_PESSOA_NAO_PERTENCE_EMPRESA_PRESTADOR);
            }
        }

        pm.assertNotEmpty(servicoAcessoPessoa.getPessoasAsList()).orRegister(TipoExcecao.SERVICO_ACESSO_PESSOA, ConstantesI18N.SERVICO_ACESSO_PESSOA_INFORME_PELO_MENOS_UMA_PESSOA);

        if (!servicoAcessoPessoa.getServicoProtecao().isNovo()) {
            pm.assertNotEmpty(servicoAcessoPessoa.getServicoProtecao().getObservacao()).orRegister(TipoExcecao.SERVICO_ACESSO_PESSOA, ConstantesI18N.SERVICO_ACESSO_PESSOA_OBSERVACAO_OBRIGATORIO);
        }

        pm.verifyAll();
    }

    public void validarSalvarFornecedor(ServicoSuprimentoTransitoEmpresa servicoSuprimentoTransitoEmpresa) {
    }

    public void validarSalvarCondutor(ServicoSuprimentoTransitoVeiculo servicoSuprimentoTransitoVeiculo) {
    } 
    
    public void validarCamposObrigatoriosSuprimentoTransito(ServicoSuprimentoTransito servicoSuprimentoTransito) {
        SistamPendencyManager pm = new SistamPendencyManager();
        
        if(!servicoSuprimentoTransito.isCompanhiaDocas() && !servicoSuprimentoTransito.isTerminal()){
            pm.assertThat(false).orRegister(TipoExcecao.SERVICO_SUPRIMENTO_TRANSITO, ConstantesI18N.SERVICO_SUPRIMENTO_TRANSITO_LOCAL_ACESSO_OBRIGATORIO);
        }
        if(servicoSuprimentoTransito.isTerminal()){
            pm.assertThat(StringUtils.isNotBlank(servicoSuprimentoTransito.getDescricaoTerminal())).orRegister(TipoExcecao.SERVICO_SUPRIMENTO_TRANSITO, ConstantesI18N.SERVICO_SUPRIMENTO_TRANSITO_LOCAL_ACESSO_DESC_TERMINAL_OBRIGATORIO);
        }
        pm.assertNotNull(servicoSuprimentoTransito.getTipoMercadorias()).orRegister(TipoExcecao.SERVICO_SUPRIMENTO_TRANSITO, ConstantesI18N.SERVICO_SUPRIMENTO_TRANSITO_TIPO_ACESSO_OBRIGATORIO);
        pm.assertNotNull(servicoSuprimentoTransito.getTipoMaterial()).orRegister(TipoExcecao.SERVICO_SUPRIMENTO_TRANSITO, ConstantesI18N.SERVICO_SUPRIMENTO_TRANSITO_TIPO_MATERIAL_OBRIGATORIO);
        pm.assertThat(!servicoSuprimentoTransito.getTransitosEmpresas().isEmpty()).orRegister(TipoExcecao.SERVICO_SUPRIMENTO_TRANSITO, ConstantesI18N.SERVICO_SUPRIMENTO_TRANSITO_PELO_MENOS_UM_FORNECEDOR);
        pm.verifyAll();
    }

    public void validarCamposObrigatoriosSuprimentoFornecedor(ServicoSuprimentoTransitoEmpresa servicoSuprimentoTransitoEmpresa) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertThat(StringUtils.isNotBlank(servicoSuprimentoTransitoEmpresa.getCnpjPrestadorServico())).orRegister(TipoExcecao.SERVICO_SUPRIMENTO_FORNECEDOR, ConstantesI18N.SERVICO_SUPRIMENTO_FORNECEDOR_EMPRESA_OBRIGATORIO);
        pm.assertNotNull(servicoSuprimentoTransitoEmpresa.getValorPesoBruto()).orRegister(TipoExcecao.SERVICO_SUPRIMENTO_FORNECEDOR, ConstantesI18N.SERVICO_SUPRIMENTO_FORNECEDOR_PESO_BRUTO_OBRIGATORIO);
        pm.assertNotNull(servicoSuprimentoTransitoEmpresa.getQuantidadeVolume()).orRegister(TipoExcecao.SERVICO_SUPRIMENTO_FORNECEDOR, ConstantesI18N.SERVICO_SUPRIMENTO_FORNECEDOR_VOLUME_OBRIGATORIO);
        pm.assertThat(StringUtils.isNotBlank(servicoSuprimentoTransitoEmpresa.getDescNotaFiscal())).orRegister(TipoExcecao.SERVICO_SUPRIMENTO_FORNECEDOR, ConstantesI18N.SERVICO_SUPRIMENTO_FORNECEDOR_NOTA_FISCAL_OBRIGATORIO);
        pm.assertNotNull(servicoSuprimentoTransitoEmpresa.getValorMercadorias()).orRegister(TipoExcecao.SERVICO_SUPRIMENTO_FORNECEDOR, ConstantesI18N.SERVICO_SUPRIMENTO_FORNECEDOR_VALOR_MERCADORIA_OBRIGATORIO);
        pm.verifyAll();
    }

    public void validarCamposObrigatoriosSuprimentoCondutor(ServicoSuprimentoTransitoVeiculo suprimentoTransitoVeiculo) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertThat(StringUtils.isNotBlank(suprimentoTransitoVeiculo.getNomeCondutor())).orRegister(TipoExcecao.SERVICO_SUPRIMENTO_CONDUTOR, ConstantesI18N.SERVICO_SUPRIMENTO_CONDUTOR_NOME_OBRIGATORIO);
        pm.assertNotNull(suprimentoTransitoVeiculo.getTipoVeiculo()).orRegister(TipoExcecao.SERVICO_SUPRIMENTO_CONDUTOR, ConstantesI18N.SERVICO_SUPRIMENTO_CONDUTOR_VEICULO_OBRIGATORIO);
        pm.assertThat(StringUtils.isNotBlank(suprimentoTransitoVeiculo.getPlacaVeiculo())).orRegister(TipoExcecao.SERVICO_SUPRIMENTO_CONDUTOR, ConstantesI18N.SERVICO_SUPRIMENTO_CONDUTOR_PLACA_OBRIGATORIO);
        pm.verifyAll();
    }

    public void validarCamposObrigatoriosHospede(Hospede hospede) {

        AssertUtils.assertNotNull(hospede.getServicoProtecao(), ConstantesI18N.HOSPEDE_SEM_HOSPEDAGEM);

        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertNotEmpty(hospede.getNome()).orRegister(TipoExcecao.HOSPEDE, ConstantesI18N.HOSPEDE_NOME_OBRIGATORIO);
        pm.assertThat((hospede.getDocumento() != null && !hospede.getDocumento().isEmpty()) || (hospede.getCpf() != null && !hospede.getCpf().isEmpty())).orRegister(TipoExcecao.HOSPEDE, ConstantesI18N.HOSPEDE_IDENTIFICADOR_OBRIGATORIA);

        int valorInit = hospede.getId() == null ? 0 : 1;
        int copiasCpf = valorInit;
        int copiasDoc = valorInit;

        if (hospede.getServicoProtecao().getServicoHospedagem() != null && hospede.getServicoProtecao().getServicoHospedagem().getHospedes() != null) {
            for (Hospede hospedeCadastrado : hospede.getServicoProtecao().getServicoHospedagem().getHospedes()) {
                boolean idIgual = hospede.getId() != null && hospedeCadastrado.getId() != null && hospede.getId().equals(hospedeCadastrado.getId());

                if (!idIgual) {
                    if (hospede.getCpf() != null && !hospede.getCpf().isEmpty() && hospede.getCpf().equals(hospedeCadastrado.getCpf())) {
                        copiasCpf++;
                    }
                    if (hospede.getDocumento() != null && !hospede.getDocumento().isEmpty() && hospede.getDocumento().equals(hospedeCadastrado.getDocumento())) {
                        copiasDoc++;
                    }
                }
            }
        }
        pm.assertThat(copiasCpf < 2).orRegister(TipoExcecao.HOSPEDE, ConstantesI18N.HOSPEDE_CPF_JA_EXISTENTE);
        pm.assertThat(copiasDoc < 2).orRegister(TipoExcecao.HOSPEDE, ConstantesI18N.HOSPEDE_DOCUMENTO_JA_EXISTENTE);
        pm.verifyAll();
    }

    public void validarCamposObrigatoriosPassageiro(Passageiro passageiro) {

        AssertUtils.assertNotNull(passageiro.getServicoProtecao(), ConstantesI18N.PASSAGEIRO_SEM_TRANSPORTE);

        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertNotEmpty(passageiro.getNome()).orRegister(TipoExcecao.PASSAGEIRO, ConstantesI18N.PASSAGEIRO_NOME_OBRIGATORIO);
        pm.assertThat((passageiro.getDocumento() != null && !passageiro.getDocumento().isEmpty()) || (passageiro.getCpf() != null && !passageiro.getCpf().isEmpty())).orRegister(TipoExcecao.PASSAGEIRO, ConstantesI18N.PASSAGEIRO_IDENTIFICADOR_OBRIGATORIA);

        int valorInit = passageiro.getId() == null ? 0 : 1;
        int copiasCpf = valorInit;
        int copiasDoc = valorInit;

        if (passageiro.getServicoProtecao().getServicoTransporte() != null && passageiro.getServicoProtecao().getServicoTransporte().getPassageiros() != null) {
            for (Passageiro passageiroCadastrado : passageiro.getServicoProtecao().getServicoTransporte().getPassageiros()) {

                boolean idIgual = passageiro.getId() != null && passageiroCadastrado.getId() != null && passageiro.getId().equals(passageiroCadastrado.getId());

                if (!idIgual) {
                    if (passageiro.getCpf() != null && !passageiro.getCpf().isEmpty() && passageiro.getCpf().equals(passageiroCadastrado.getCpf())) {
                        copiasCpf++;
                    }
                    if (passageiro.getDocumento() != null && !passageiro.getDocumento().isEmpty() && passageiro.getDocumento().equals(passageiroCadastrado.getDocumento())) {
                        copiasDoc++;
                    }
                }
            }
        }
        pm.assertThat(copiasCpf < 2).orRegister(TipoExcecao.PASSAGEIRO, ConstantesI18N.PASSAGEIRO_CPF_JA_EXISTENTE);
        pm.assertThat(copiasDoc < 2).orRegister(TipoExcecao.PASSAGEIRO, ConstantesI18N.PASSAGEIRO_DOCUMENTO_JA_EXISTENTE);
        pm.verifyAll();

    }

    public void validarCamposObrigatoriosPessoa(PessoaAcesso pessoa) {
        AssertUtils.assertNotNull(pessoa.getServicoProtecao(), ConstantesI18N.SERVICO_ACESSO_PESSOA_SERVICO_PROTECAO_OBRIGATORIO);

        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotEmpty(pessoa.getNome()).orRegister(TipoExcecao.PESSOA, ConstantesI18N.PESSOA_OBRIGATORIO);

        if (TipoCategoria.VISITANTES.equals(pessoa.getServicoProtecao().getServicoAcessoPessoa().getTipoCategoria())) {
            pm.assertNotEmpty(pessoa.getResponsavel()).orRegister(TipoExcecao.PESSOA, ConstantesI18N.PESSOA_RESPONSAVEL_OBRIGATORIO);
        }
        ServicoAcessoPessoa servicoAcessoPessoa = pessoa.getServicoProtecao().getServicoAcessoPessoa();

        int valorInit = pessoa.getId() == null ? 0 : 1;
        int copiasCpf = valorInit;
        int copiasDoc = valorInit;

        if (pessoa.getNome() != null) {
            for (PessoaAcesso pessoaCadastrado : servicoAcessoPessoa.getPessoasAsList()) {
                boolean idIgual = pessoa.getId() != null && pessoaCadastrado.getId() != null && pessoa.getId().equals(pessoaCadastrado.getId());

                if (!idIgual) {
                    if (StringUtils.isNotBlank(pessoa.getCpf()) && pessoa.getCpf().equals(pessoaCadastrado.getCpf())) {
                        copiasCpf++;
                    }
                    if (StringUtils.isNotBlank(pessoa.getDocumento()) && pessoa.getDocumento().equals(pessoaCadastrado.getDocumento())) {
                        copiasDoc++;
                    }
                }
            }
            pm.assertThat(copiasCpf < 2).orRegister(TipoExcecao.PESSOA, ConstantesI18N.PESSOA_CPF_JA_EXISTENTE);
            pm.assertThat(copiasDoc < 2).orRegister(TipoExcecao.PESSOA, ConstantesI18N.PESSOA_DOCUMENTO_JA_EXISTENTE);
        }

        if (pessoa.getVolume() != null) {
            pm.assertThat(pessoa.getVolume() > 0).orRegister(TipoExcecao.PESSOA, ConstantesI18N.PESSOA_VOLUMES_MAIOR_QUE_ZERO);
            if (pessoa.getVolume() > 0) {
                pm.assertNotEmpty(pessoa.getBagagem()).orRegister(TipoExcecao.PESSOA, ConstantesI18N.PESSOA_BAGAGEM_OBRIGADO);
            }
        }

        if (StringUtils.isNotEmpty(pessoa.getBagagem())) {
            pm.assertThat(pessoa.getVolume() != null && pessoa.getVolume() > 0).orRegister(TipoExcecao.PESSOA, ConstantesI18N.PESSOA_VOLUMES_OBRIGADO);
        }
        pm.verifyAll();
    }

    public void validarSalvarServicoSuprimentoAosNavios(ServicoSuprimento servicoSuprimento) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(servicoSuprimento.getDataInicioOperacao()).orRegister(TipoExcecao.SERVICO_SUPRIMENTO, ConstantesI18N.SERVICO_SUPRIMENTO_DT_INICIO_OPERACAO_OBRIGATORIO);
        pm.assertNotNull(servicoSuprimento.getDataFimOperacao()).orRegister(TipoExcecao.SERVICO_SUPRIMENTO, ConstantesI18N.SERVICO_SUPRIMENTO_DT_TERMINO_OPERACAO_OBRIGATORIO);
        if (servicoSuprimento.getDataInicioOperacao() != null && servicoSuprimento.getDataFimOperacao() != null) {
            pm.assertThat(!SistamDateUtils.alterarSegundosMilisegundos(servicoSuprimento.getDataInicioOperacao(), 0, 0, null).after(SistamDateUtils.alterarSegundosMilisegundos(servicoSuprimento.getDataFimOperacao(), 0, 0, null))).orRegister(TipoExcecao.SERVICO_SUPRIMENTO, ConstantesI18N.SERVICO_SUPRIMENTO_DT_INICIO_MAIOR_DT_TERMINO_OPERACAO);
        }
        pm.assertNotNull(servicoSuprimento.getEmpresaMaritima()).orRegister(TipoExcecao.SERVICO_SUPRIMENTO, ConstantesI18N.SERVICO_SUPRIMENTO_EMPRESA_OBRIGATORIO);
        pm.assertNotNull(servicoSuprimento.getEmpresaServico()).orRegister(TipoExcecao.SERVICO_SUPRIMENTO, ConstantesI18N.SERVICO_SUPRIMENTO_EMBARCACAO_OBRIGATORIO);
        if(servicoSuprimento.getId() != null){
            pm.assertThat(StringUtils.isNotBlank(servicoSuprimento.getServicoProtecao().getObservacao())).orRegister(TipoExcecao.SERVICO_SUPRIMENTO, ConstantesI18N.SERVICO_SUPRIMENTO_OBSERVACAO_OBRIGATORIA_NA_ALTERACAO);
        }
        pm.verifyAll();
    }
    
    public void validarSalvarServicoProtecaoRetiradaResiduoLixo(ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo){
       SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(servicoRetiradaResiduoLixo.getResponsavelCusto()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_EMPRESA_REQUERENTE_OBRIGATORIO);
        pm.assertNotNull(servicoRetiradaResiduoLixo.getEmpresaMaritima()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_EMPRESA_RESPONSAVEL_TRANSBORDO_OBRIGATORIO);
        pm.assertNotNull(servicoRetiradaResiduoLixo.getEmpresaServico()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_EMBARCACAO_TRANSBORDO_OBRIGATORIO);
        pm.assertNotNull(servicoRetiradaResiduoLixo.getEmpresaMaritimaRodoviaria()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_TRANSPORTADORA_RODOVIARIA_OBRIGATORIO);
        pm.assertNotNull(servicoRetiradaResiduoLixo.getTipoResiduo()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_TIPO_RESIDUO_OBRIGATORIO);
        pm.assertNotEmpty(servicoRetiradaResiduoLixo.getCaracterizacao()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_CARACTERIZACAO);
        pm.assertNotEmpty(servicoRetiradaResiduoLixo.getEstadoFisico()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_ESTADO_FISICO);
        pm.assertNotEmpty(servicoRetiradaResiduoLixo.getClassificacao()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_CLASSIFICACAO);
        pm.assertNotEmpty(servicoRetiradaResiduoLixo.getCodigoOnu()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_CODIGO_ONU);
        pm.assertNotNull(servicoRetiradaResiduoLixo.getQuantidadeResiduo()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_QUANTIDADE_RESIDUO);
        pm.assertNotNull(servicoRetiradaResiduoLixo.getTipoUnidadeMedida()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_TIPO_UNIDADE_MEDIDA);
        pm.assertNotNull(servicoRetiradaResiduoLixo.getDataInicioOperacao()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_DT_INICIO_OPERACAO_OBRIGATORIO);
        pm.assertNotNull(servicoRetiradaResiduoLixo.getDataFimOperacao()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_DT_TERMINO_OPERACAO_OBRIGATORIO);
        
        if (servicoRetiradaResiduoLixo.getDataInicioOperacao() != null && servicoRetiradaResiduoLixo.getDataFimOperacao() != null) {
            pm.assertThat(!SistamDateUtils.alterarSegundosMilisegundos(servicoRetiradaResiduoLixo.getDataInicioOperacao(), 0, 0, null).after(SistamDateUtils.alterarSegundosMilisegundos(servicoRetiradaResiduoLixo.getDataFimOperacao(), 0, 0, null))).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_DT_INICIO_MAIOR_DT_TERMINO_OPERACAO);
        }
        
        if(TipoResiduo.CLASSE_I.equals(servicoRetiradaResiduoLixo.getTipoResiduo())){
            pm.assertNotEmpty(servicoRetiradaResiduoLixo.getLocalArmazenagem()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_LOCAL_ARMAZENAGEM);
            pm.assertNotEmpty(servicoRetiradaResiduoLixo.getLonArmazenagem()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_LON_ARMAZENAGEM);
            pm.assertNotEmpty(servicoRetiradaResiduoLixo.getDescCadri()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_DESCRICAO_CADRI);
             
        }
        
        pm.assertNotNull(servicoRetiradaResiduoLixo.getValorServico()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_VALOR_SERVICO);
        pm.assertNotEmpty(servicoRetiradaResiduoLixo.getLocalDestinoFinal()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_LOCAL_DESTINO_FINAL);
        pm.assertNotEmpty(servicoRetiradaResiduoLixo.getLonDestinoFinal()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_LON_DESTINO_FINAL);
        pm.assertNotEmpty(servicoRetiradaResiduoLixo.getCadriDestinoFinal()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_CADRI_DESTINO_FINAL);
        
        if(servicoRetiradaResiduoLixo.getId() != null){
            pm.assertThat(StringUtils.isNotBlank(servicoRetiradaResiduoLixo.getServicoProtecao().getObservacao())).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_OBSERVACAO_OBRIGATORIA_NA_ALTERACAO);
        }
        
        pm.verifyAll();
    }
    
    public void validarCamposObrigatoriosBuscaServicoSuprimentoTransitoEmpresaParaRelatorio(FiltroRelatorioServicoSuprimentoAosNavios filtro) {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(filtro.getAgencia()).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.RELATORIO_SUPRIMENTO_NAVIOS_AGENICA_OBRIGATORIA);
        pm.verifyAll();
    }
}
