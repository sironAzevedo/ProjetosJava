package br.com.petrobras.sistam.web;

import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.RecursoCA;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.web.valueobjects.AgenciaWebVO;
import br.com.petrobras.sistam.web.valueobjects.AgenciamentoWebVO;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Classe PainelController
 */
@Controller
public class PainelController {

    @Autowired
    SistamService sistamService;

    @RequestMapping(value = "/painel.do", method = RequestMethod.GET)
    public String painelInterface() {
        return "painel";
    }

    @RequestMapping(value = "/agencias/sistam.do", method = RequestMethod.GET)
    public @ResponseBody
    List<AgenciaWebVO> listarAgencias() {

        List<AgenciaWebVO> agencias = new ArrayList<AgenciaWebVO>();

        SistamCredentialsBean credentials = (SistamCredentialsBean) sistamService.buscarDadosDeAutenticacao();

        for (Agencia agencia : credentials.getAgenciasAutorizadas(RecursoCA.PAINEL_COMPLETO)) {
            agencias.add(new AgenciaWebVO(agencia.getSigla(), agencia.getNome()));
        }

        return agencias;
    }

    @RequestMapping(value = "/agenciamentos/agencia/{sigla}/statusEmbarcacao/{statusEmbarcacao}/sistam.do", method = RequestMethod.GET)
    public @ResponseBody
    List<AgenciamentoWebVO> listarAgenciamentos(@PathVariable String sigla, @PathVariable Integer... statusEmbarcacao) {
        Agencia agencia = sistamService.buscarAgenciaPorSigla(sigla);
        List<StatusEmbarcacao> listaStatus = new ArrayList<StatusEmbarcacao>();

        for (Integer status : statusEmbarcacao) {
            listaStatus.add(StatusEmbarcacao.from(status));

        }
        List<Agenciamento> agenciamentos = sistamService.buscarAgenciamentosParaPainel(agencia, listaStatus);
        List<AgenciamentoWebVO> vos = new ArrayList<AgenciamentoWebVO>();
        for (Agenciamento agenciamento : agenciamentos) {
            AgenciamentoWebVO vo = new AgenciamentoWebVO();
            vo.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());


            String vgmChegada = agenciamento.getVgm() != null ? agenciamento.getVgm() : "";
            String vgmSaida = agenciamento.getVgmSaida() != null ? " / " + agenciamento.getVgmSaida() : "";
            String vgm;
            if (!agenciamento.getVgm().equals(agenciamento.getVgmSaida())) {
                vgm = vgmChegada.concat(vgmSaida);
            } else {
                vgm = vgmChegada;
            }
            vo.setVgm(vgm);

            vo.setEta(SistamDateUtils.formatDate(agenciamento.getEta(), "dd/MM HH:mm", TimeZone.getTimeZone(agencia.getTimezone())));
            vo.setEtaProxPorto(SistamDateUtils.formatDate(agenciamento.getEtaProximoPorto(), "dd/MM HH:mm", agencia.obterTimezone()));
            vo.setPortoOrigem(agenciamento.getPortoOrigem().getId());

            String caladoChegadaVante = String.valueOf(agenciamento.getCaladoChegadaVante() != null ? agenciamento.getCaladoChegadaVante() : "-");
            String caladoChegadaARe = String.valueOf(agenciamento.getCaladoChegadaRe() != null ? agenciamento.getCaladoChegadaRe() : "-");

            String caladoSaidaVante = String.valueOf(agenciamento.getCaladoSaidaVante() != null ? agenciamento.getCaladoSaidaVante() : "-");
            String caladoSaidaARe = String.valueOf(agenciamento.getCaladoSaidaRe() != null ? agenciamento.getCaladoSaidaRe() : "-");

            vo.setCaladoChegadaVante(caladoChegadaVante);
            vo.setCaladoChegadaRe(caladoChegadaARe);

            vo.setCaladoSaidaVante(caladoSaidaVante);
            vo.setCaladoSaidaRe(caladoSaidaARe);

            vo.setPortoDestino(agenciamento.getPortoDestino() != null ? agenciamento.getPortoDestino().getId() : " - ");

            vo.setDataAtracacao(SistamDateUtils.formatDate(agenciamento.getDataHoraAtracacao(), "dd/MM HH:mm", TimeZone.getTimeZone(agencia.getTimezone())));

            PontoAtracacao ponto = agenciamento.obterLocalizacaoAtual();
            vo.setLocalizacaoAtual(ponto != null && ponto.getPontoOperacional() != null && ponto.getPontoOperacional().getPorto() != null ? ponto.getSiglaFormatada() : " - ");


            String resumoCarga = agenciamento.getOperacoesFormatadas();
            vo.setResumoCarga(resumoCarga != null && !resumoCarga.isEmpty() ? resumoCarga : " - ");

            String resumoQuantidades = agenciamento.getQuantidadesProdutosFormatadas();
            vo.setResumoQuantidades(resumoQuantidades != null && !resumoQuantidades.isEmpty() ? resumoQuantidades : " - ");

            vo.setDataHoraOficialChegada(SistamDateUtils.formatDate(agenciamento.getDataChegada(), "dd/MM HH:mm", TimeZone.getTimeZone(agencia.getTimezone())));
            vo.setObservacao(agenciamento.getObservacoes() != null && !agenciamento.getObservacoes().isEmpty() ? agenciamento.getObservacoes() : " - ");

            vo.setTipo(agenciamento.getSituacaoLivrePratica() != null ? agenciamento.getSituacaoLivrePratica().getId() : " - ");

            vo.setDataSaida(SistamDateUtils.formatDate(agenciamento.getDataSaida(), "dd/MM HH:mm", TimeZone.getTimeZone(agencia.getTimezone())));

            // porto do desvio
            if (null != agenciamento.getDesvio()) {
                Porto destinoAnterior = agenciamento.getDesvio().getPortoDestinoAlterado();
                vo.setDestinoAnterior(destinoAnterior != null ? destinoAnterior.getId() : " - ");
            }


            vos.add(vo);


        }


        return vos;


    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.setIgnoreInvalidFields(true);
        binder.registerCustomEditor(Long.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.equals("null")) {
                    setValue(null);
                } else {
                    setValue(Long.parseLong(text));
                }
            }
        });
        binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.equals("null")) {
                    setValue(null);
                } else {
                    setValue(Integer.parseInt(text));
                }
            }
        });
    }
}
