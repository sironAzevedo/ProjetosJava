package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import java.lang.reflect.Field;
import java.util.Date;
import org.unitils.util.ReflectionUtils;

public class ServicoProtecaoBuilder {

    private ServicoProtecao servicoProtecao;

    private ServicoProtecaoBuilder(ServicoProtecao servicoProtecao) {
        this.servicoProtecao = servicoProtecao;
    }

    public static ServicoProtecaoBuilder novoServicoProtecao() {
        return new ServicoProtecaoBuilder(new ServicoProtecao());
    }

    public static ServicoProtecaoBuilder novoServicoProtecao(ServicoProtecao servicoProtecao) {
        return new ServicoProtecaoBuilder(servicoProtecao);
    }

    public ServicoProtecao build() {
        return this.servicoProtecao;
    }

    public ServicoProtecaoBuilder comId(Long id) {
        Field field = ReflectionUtils.getFieldWithName(ServicoProtecao.class, "id", false);
        ReflectionUtils.setFieldValue(servicoProtecao, field, id);
        return this;
    }

    public ServicoProtecaoBuilder doAgenciamento(Agenciamento agenciamento) {
        this.servicoProtecao.setAgenciamento(agenciamento);
        return this;
    }

    public ServicoProtecaoBuilder comTipoAtendimentoServico(TipoAtendimentoServico tipo) {
        this.servicoProtecao.setTipoAtendimentoServico(tipo);
        return this;
    }

    public ServicoProtecaoBuilder comDataExecucao(Date dataExecucao) {

        this.servicoProtecao.setDataExecucao(dataExecucao);
        return this;
    }

    public ServicoProtecaoBuilder comServicoHospedagem(ServicoHospedagem servicoHospedagem) {
        this.servicoProtecao.setServicoHospedagem(servicoHospedagem);
        return this;
    }

    public ServicoProtecaoBuilder comChaveUsuarioIncusao(String chave) {
        this.servicoProtecao.setChaveUsuarioInclusao(chave);
        return this;
    }

    public ServicoProtecaoBuilder comNomeUsuarioIncusao(String nome) {
        this.servicoProtecao.setNomeUsuarioInclusao(nome);
        return this;
    }

    public ServicoProtecaoBuilder comDataAlteracao(Date dataAltaracao) {
        this.servicoProtecao.setDataAlteracao(dataAltaracao);
        return this;
    }

    public ServicoProtecaoBuilder comChaveUsuarioAlteracao(String chave) {
        this.servicoProtecao.setChaveUsuarioAlteracao(chave);
        return this;
    }

    public ServicoProtecaoBuilder comNomeUsuarioAlteracao(String nome) {
        this.servicoProtecao.setNomeUsuarioAlteracao(nome);
        return this;
    }

    public ServicoProtecaoBuilder comDataCancelamento(Date dataCancelamento) {
        this.servicoProtecao.setDataCancelamento(dataCancelamento);
        return this;
    }

    public ServicoProtecaoBuilder comChaveUsuarioCancelamento(String chave) {
        this.servicoProtecao.setChaveUsuarioCancelamento(chave);
        return this;
    }

    public ServicoProtecaoBuilder comNomeUsuarioCancelamento(String nome) {
        this.servicoProtecao.setNomeUsuarioCancelamento(nome);
        return this;
    }

    public ServicoProtecaoBuilder comJustificativa(String justificativa) {
        this.servicoProtecao.setJustificativa(justificativa);
        return this;
    }

    public ServicoProtecaoBuilder comObservacao(String observacao) {
        this.servicoProtecao.setObservacao(observacao);
        return this;
    }

    public ServicoProtecaoBuilder isNovo(boolean novo) {
        this.servicoProtecao.setNovo(novo);
        return this;
    }
}
