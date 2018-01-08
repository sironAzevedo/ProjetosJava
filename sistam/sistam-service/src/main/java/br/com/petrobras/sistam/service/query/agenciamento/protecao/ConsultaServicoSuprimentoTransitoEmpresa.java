/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.petrobras.sistam.service.query.agenciamento.protecao;

import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransitoEmpresa;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioServicoSuprimentoAosNavios;
import br.com.petrobras.snarf.persistence.BusinessQuery;
import java.util.Date;

/**
 *
 * @author adzk
 */
public class ConsultaServicoSuprimentoTransitoEmpresa extends BusinessQuery<ServicoSuprimentoTransitoEmpresa> {

    public ConsultaServicoSuprimentoTransitoEmpresa(FiltroRelatorioServicoSuprimentoAosNavios filtro) {
        super(false);

        appendText(" SELECT sste FROM ", ServicoSuprimentoTransitoEmpresa.class.getSimpleName(), " sste");
        appendText(" JOIN FETCH sste.solicitacaoTransito st");
        appendText(" JOIN FETCH st.servicoSuprimento ss");
        appendText(" JOIN FETCH ss.transitos");
        appendText(" JOIN FETCH ss.empresaMaritima empm");
        appendText(" JOIN FETCH ss.empresaServico emps");
        appendText(" JOIN FETCH ss.servicoProtecao sp");
        appendText(" JOIN FETCH sp.agenciamento agm");
        appendText(" JOIN FETCH agm.embarcacao emb");
        appendText(" JOIN FETCH agm.agencia ag");
        appendText(" JOIN FETCH agm.porto p ");
        appendText(" WHERE 1 = 1");

        if (filtro.isAgenciaPreenchido()) {
            appendText(" AND ag.id = :agencia");
            addParameter("agencia", filtro.getAgencia().getId());
        }

        if (filtro.isFornecedorPreenchido()) {
            appendText(" AND sste.cnpjPrestadorServico = :cnpjPrestadorServico");
            addParameter("cnpjPrestadorServico", filtro.getFornecedor().getCnpj());
        }

        if (filtro.isNavioPreenchido()) {
            appendText(" AND emb.id = :embarcacao");
            addParameter("embarcacao", filtro.getNavio().getId());
        }
        
        if (filtro.isPortoPreenchido()) {
            appendText(" AND p.id = :porto ");
            addParameter("porto", filtro.getPorto().getId());
        }

        if (filtro.isNotaFiscalPreenchido()) {
            appendText(" AND UPPER(sste.descNotaFiscal) LIKE UPPER(:notaFiscal)");
            addParameter("notaFiscal", "%" + filtro.getNotaFiscal() + "%");
        }

        if (filtro.isNumeroViagemPreenchido()) {
            appendText(" AND UPPER(agm.vgm) = UPPER(:numeroViagem)");
            addParameter("numeroViagem", filtro.getNumeroViagem());
        } 

        Date inicioOperacao = filtro.getPeriodoOperacao().getDataInicio();
        if (inicioOperacao != null) {
            inicioOperacao = SistamDateUtils.alterarSegundosMilisegundos(inicioOperacao, 0, 0, null);
        }
        Date fimOperacao = filtro.getPeriodoOperacao().getDataFim();
        if (fimOperacao != null) {
            fimOperacao = SistamDateUtils.alterarSegundosMilisegundos(fimOperacao, 59, 999, null);
        }

        if (inicioOperacao != null && fimOperacao != null) {
            appendText(" AND ((ss.dataInicioOperacao >= :inicioOperacao AND ss.dataInicioOperacao < :fimOperacao)");
            appendText(" OR (ss.dataInicioOperacao > :inicioOperacao AND ss.dataInicioOperacao <= :fimOperacao)");
            appendText(" OR (ss.dataInicioOperacao = :inicioOperacao AND ss.dataInicioOperacao = :fimOperacao)");
            appendText(")");
            addParameter("inicioOperacao", inicioOperacao);
            addParameter("fimOperacao", fimOperacao);

        } else if (inicioOperacao != null) {
            appendText(" AND ss.dataInicioOperacao >= :inicioOperacao");
            addParameter("inicioOperacao", inicioOperacao);

        } else if (fimOperacao != null) {
            appendText(" AND ss.dataInicioOperacao <= :fimOperacao");
            addParameter("fimOperacao", fimOperacao);
        }

        if (filtro.isCompanhiaDocas()) {
            appendText(" AND st.companhiaDocas = :companhiaDocas");
            addParameter("companhiaDocas", filtro.isCompanhiaDocas());
        }

        if (filtro.isTerminal()) {
            appendText(" AND st.terminal = :terminal");
            addParameter("terminal", filtro.isTerminal());
        }

        if (filtro.isTipoMaterialPreenchido()) {
            appendText(" AND st.tipoMaterial = :tipoMaterial");
            addParameter("tipoMaterial", filtro.getTipoMaterial());
        }

        if (filtro.isTipoAcessoPreenchido()) {
            appendText(" AND st.tipoMercadorias = :tipoMercadoria");
            addParameter("tipoMercadoria", filtro.getTipoAcesso());
        }

        if (filtro.isEmpresaMaritimaPreenchido()) {
            appendText(" AND empm.id = :empresaMaritima");
            addParameter("empresaMaritima", filtro.getEmpresaMaritima().getId());
        }

        if (filtro.isServicoPreenchido()) {
            appendText(" AND emps.id = :servico");
            addParameter("servico", filtro.getServico().getId());
        }

    }
}
