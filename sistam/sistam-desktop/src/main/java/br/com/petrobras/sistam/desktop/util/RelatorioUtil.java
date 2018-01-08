package br.com.petrobras.sistam.desktop.util;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoResiduo;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.AvisoDeSaidaCapitaniaVo;
import br.com.petrobras.sistam.common.valueobjects.CTACReceitaVo;
import br.com.petrobras.sistam.common.valueobjects.DeclaracaoGeralCapitaniaVO;
import br.com.petrobras.sistam.common.valueobjects.FiltroAvisoEntradaVo;
import br.com.petrobras.sistam.common.valueobjects.FiltroPedidoDespachoPorPeriodoVo;
import br.com.petrobras.sistam.common.valueobjects.FiltroTaxa;
import br.com.petrobras.sistam.common.valueobjects.FormularioControleFiscalizacaoUnicoVO;
import br.com.petrobras.sistam.common.valueobjects.FormularioPasseEntradaPoliciaFederalVO;
import br.com.petrobras.sistam.common.valueobjects.FormularioPasseSaidaPoliciaFederalVO;
import br.com.petrobras.sistam.common.valueobjects.FormularioRequerimentoPasseSaidaPoliciaFederalVO;
import br.com.petrobras.sistam.common.valueobjects.LiberacaoAnvisaVO;
import br.com.petrobras.sistam.common.valueobjects.NotaDespachoVo;
import br.com.petrobras.sistam.common.valueobjects.NotificacaoPrevisaoChegadaCapitaniaVo;
import br.com.petrobras.sistam.common.valueobjects.ParteDeEntradaCapitaniaVo;
import br.com.petrobras.sistam.common.valueobjects.ParteDeSaidaCapitaniaVo;
import br.com.petrobras.sistam.common.valueobjects.PasseDeSaidaCapitaniaVo;
import br.com.petrobras.sistam.common.valueobjects.PasseDeSaidaPeriodoVo;
import br.com.petrobras.sistam.common.valueobjects.PedidoDespachoProximoPortoCapitaniaVo;
import br.com.petrobras.sistam.common.valueobjects.PessoaAcessoVO;
import br.com.petrobras.sistam.common.valueobjects.RegistroDeAlteracaoDestinoCapitaniaVo;
import br.com.petrobras.sistam.common.valueobjects.RegistroDeMovimentacaoCapitaniaVo;
import br.com.petrobras.sistam.common.valueobjects.RelatorioAgenciamentoAtendimentoVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioAgenciamentoProdutividadeVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioMovimentacaoEmbarcacaoVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioPessoaPoliciaFederalVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioVisitaAEmbarcacaoPoliciaFederalVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioReceitaDesEmbarqueTripulanteVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioReceitaFederalVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioReceitaPrestacaoServicoVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioServicoRetiradaResiduoLixoVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioServicoSuprimentoTransitoVO;
import br.com.petrobras.sistam.common.valueobjects.TaxaMensalVO;
import br.com.petrobras.sistam.common.valueobjects.TermoCompromissoTUFVO;
import br.com.petrobras.sistam.common.valueobjects.TermoResponsabilidadeReceitaVO;
import br.com.petrobras.sistam.common.valueobjects.TermoResponsabilidadeRepresentanteLegalCapitaniaVO;
import br.com.petrobras.snarf.desktop.reports.ReportUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioUtil {
    //Imagens

    private static final String IMAGEM_REPUBLICA = "/icons/republica.png";
    private static final String IMAGEM_CAPITANIA = "/icons/capitania.jpg";
    private static final String IMAGEM_RECEITA = "/icons/logo-receita-federal50px.png";
    private static final String IMAGEM_ANVISA = "/icons/anvisa.png";
    private static final String IMAGEM_PETROBRAS = "/icons/petrobras_rel.jpg";
    private static final String IMAGEM_PETROLEO_BRASILEIRO_PETROBRAS = "/icons/petroleo_brasileiroSA.png";
    private static final String IMAGEM_PORTO_SAO_SEBASTIAO = "/icons/logo-sao-sebastiao.png";
    private static final String IMAGEM_RECEITA_MAIOR = "/icons/logo_receita_federal.png";
    private static final String IMAGEM_GUARDA = "/icons/logo_guarda_portuaria.png";
    private static final String IMAGEM_POLICIA_FEDERAL = "/icons/policia_federal.png";
    private static final String IMAGEM_NP1 = "/icons/NP-1.png";
    //Caminho dos relatórios
    private static final String RELATORIO_TERMO_RESPONSABILIDADE_REPRESENTANTE_LEGAL_CAPITANIA = "/reports/TermoResponsabilidadeRepresentanteLegal.jasper";
    private static final String RELATORIO_PASSE_SAIDA_CAPITANIA = "/reports/PasseDeSaidaCapitania.jasper";
    private static final String RELATORIO_NOTA_DESPACHO = "/reports/NotaDeDespachoMaritimo.jasper";
    private static final String RELATORIO_PASSE_SAIDA_PERIODO = "/reports/PasseDeSaidaPorPeriodo.jasper";
    private static final String RELATORIO_PEDIDO_DESPACHO_CAPITANIA = "/reports/PedidoDespachoProximoPortoCapitania.jasper";
    private static final String FORMULARIO_REGISTRO_MOVIMENTACAO_EMBARCACAO = "/reports/ForumularioRegistroMovimentacaoEmbarcacao.jasper";
    private static final String RELATORIO_PARTE_DE_ENTRADA_CAPITANIA = "/reports/ParteDeEntradaCapitania.jasper";
    private static final String RELATORIO_PARTE_DE_SAIDA_CAPITANIA = "/reports/ParteDeSaidaCapitania.jasper";
    private static final String RELATORIO_AVISO_DE_SAIDA_CAPITANIA = "/reports/AvisoDeSaidaCapitania.jasper";
    private static final String RELATORIO_AVISO_DE_ENTRADA_CAPITANIA = "/reports/FormularioAvisoEntrada.jasper";
    private static final String RELATORIO_PEDIDO_DE_DESPACHO_POR_PERIODO = "/reports/FormularioPedidoDespachoPorPeriodo.jasper";
    private static final String RELATORIO_COMUNICACAO_DE_CHEGADA = "/reports/RelatorioComunicacaoChegada.jasper";
    private static final String RELATORIO_LIVRE_PRATICA = "/reports/RelatorioLivrePratica.jasper";
    private static final String RELATORIO_REGISTRO_ALTERACAO_DESTINO_CAPITANIA = "/reports/RegistroAlteracaoDestinoCapitania.jasper";
    private static final String RELATORIO_DECLARACAO_GERAL_ENTRADA = "/reports/DeclaracaoGeralEntrada.jasper";
    private static final String RELATORIO_DECLARACAO_GERAL_DESPACHO = "/reports/DeclaracaoGeralDespacho.jasper";
    private static final String RELATORIO_NOTIFICACAO_PREVISAO_CHEGADA = "/reports/NotificacaoPrevisaoChegadaCapitania.jasper";
    private static final String RELATORIO_TERMO_RESPONSABILIDADE_RECEITA = "/reports/TermoResponsabilidadeReceita.jasper";
    private static final String RELATORIO_CONTROLE_FISCALIZACAO_UNICO_AGENCIAS = "/reports/FormularioControleFiscalizacaoUnicoAgencias.jasper";
    private static final String RELATORIO_CONTROLE_FISCALIZACAO_UNICO_ESPECIFICO_PARA_AGENCIA_MANAUS = "/reports/FormularioControleFiscalizacaoUnicoAgenciaManaus.jasper";
    private static final String RELATORIO_MENSAL_TAXAS = "/reports/RelatorioMensalTaxas.jasper";
    private static final String RELATORIO_AGENCIAMENTO_PRODUTIVIDADE = "/reports/RelatorioAgenciamentoProdutividade.jasper";
    private static final String RELATORIO_CONTROLE_PAGAMENTO_TAXA = "/reports/RelatorioControlePagamentoTaxas.jasper";
    private static final String RELATORIO_TERMO_COMPROMISSO_TUF_CAPITANIA = "/reports/TermoCompromissoTUFCapitania.jasper";
    private static final String RELATORIO_MOVIMENTACAO_EMBARCACAO_RESUMIDO = "/reports/RelatorioMovimentacaoEmbarcacaoResumido.jasper";
    private static final String RELATORIO_MOVIMENTACAO_EMBARCACAO = "/reports/RelatorioMovimentacaoEmbarcacao.jasper";
    private static final String RELATORIO_AGENCIAMENTO_PRODUTIVIDADE_ATENDIMENTOS_REALIZADOS = "/reports/RelatorioAgenciamentoProdutividadeAtendimentosRealizados.jasper";
    private static final String FORMULARIO_SOLICITACAO_TRANSPORTE = "/reports/FormularioTransporte.jasper";
    private static final String FORMULARIO_SOLICITACAO_ACESSO_PESSOA_POLICIA_FEDERAL = "/reports/FormularioAcessoPessoaPoliciaFederal.jasper";
    private static final String FORMULARIO_SOLICITACAO_ACESSO_PESSOA_POLICIA_FEDERAL_REQUERIMENTO_EMBARQUE = "/reports/FormularioAcessoPessoaPoliciaFederalRequerimentoDeEmbarque.jasper";    
    private static final String FORMULARIO_SOLICITACAO_ACESSO_PESSOA_POLICIA_FEDERAL_REQUERIMENTO_DESEMBARQUE = "/reports/FormularioAcessoPessoaPoliciaFederalRequerimentoDeDesembarque.jasper";    
    private static final String FORMULARIO_SOLICITACAO_ACESSO_PESSOA_POLICIA_FEDERAL_REQUERIMENTO_ACESSO_EMBARCACAO = "/reports/FormularioAcessoPessoaPoliciaFederalRequerimentoAcessoAEmbarcacao.jasper";    
    private static final String FORMULARIO_SOLICITACAO_ACESSO_PESSOA_POLICIA_FEDERAL_REQUERIMENTO_VISITA_EMBARCACAO = "/reports/FormularioAcessoPessoaPoliciaFederalRequerimentoDeVisitaAEmbarcacao.jasper";    
    private static final String FORMULARIO_SOLICITACAO_RETIRADA_RESIDUO_LIXO = "/reports/FormularioRetiradaResiduoLixo.jasper";
    private static final String FORMULARIO_SOLICITACAO_ACESSO_PESSOA_RECEITA_FEDERAL = "/reports/FormularioAcessoPessoaReceitaFederal.jasper";
    private static final String FORMULARIO_RECEITA_FEDERAL_EMBARQUE_DESEMBARQUE_TRIPULANTES = "/reports/FormularioReceitaDesEmbarqueTripulantes.jasper";
    private static final String FORMULARIO_SERVICO_SUPRIMENTO = "/reports/FormularioServicoSuprimento.jasper";
    private static final String FORMULARIO_PASSE_ENTRADA_POLICIA_FEDERAL = "/reports/FormularioPasseEntradaPoliciaFederal.jasper";
    private static final String FORMULARIO_PASSE_SAIDA_POLICIA_FEDERAL = "/reports/FormularioPasseSaidaPoliciaFederal.jasper";
    private static final String FORMULARIO_REQUERIMENTO_PASSE_SAIDA_POLICIA_FEDERAL = "/reports/FormularioRequerimentoPasseSaidaPoliciaFederal.jasper";
    private static final String SUB_FORMULARIO_RECEITA_FEDERAL_EMBARQUE_DESEMBARQUE_TRIPULANTES = "/reports/SubFormularioReceitaDesEmbarqueTripulantes.jasper";
    private static final String FORMULARIO_RECEITA_FEDERAL_PRESTACAO_SERVICO = "/reports/FormularioReceitaPrestacaoServico.jasper";
    private static final String SUB_FORMULARIO_RECEITA_FEDERAL_PRESTACAO_SERVICO_VEICULOS = "/reports/SubFormularioReceitaPrestacaoServicoVeiculos.jasper";
    private static final String SUB_FORMULARIO_RECEITA_FEDERAL_PRESTACAO_SERVICO_FUNCIONARIOS = "/reports/SubFormularioReceitaPrestacaoServicoFuncionarios.jasper";
    private static final String SUB_FORM_NOME_SOLICITACAO_ACESSO_PESSOA_RECEITA_FEDERAL = "/reports/SubRelatorioAcessoPessoaReceitaFederal.jasper";
    private static final String SUB_FORM_BAGAGEM_SOLICITACAO_ACESSO_PESSOA_RECEITA_FEDERAL = "/reports/SubRelatorioAcessoPessoaReceitaFederalBagagens.jasper";
    private static final String SUB_FORM_SOLICITACAO_RETIRADA_RESIDUO_LIXO_CLASSE_I = "/reports/SubRelatorioReetiradaResiduoLixoClasseI.jasper";
    private static final String SUB_FORM_SOLICITACAO_RETIRADA_RESIDUO_LIXO_CLASSE_II = "/reports/SubRelatorioReetiradaResiduoLixoClasseII.jasper";
    private static final String SUB_FORM_SERVICO_SUPRIMENTO_FORNECEDOR = "/reports/SubRelatorioServicoSuprimentoFornecedor.jasper";
    private static final String SUB_FORM_SERVICO_SUPRIMENTO_CONDUTOR = "/reports/SubRelatorioServicoSuprimentoCondutor.jasper";    
    private static final String SUB_FORM_ACESSO_PESSOA_POLICIA_FEDERAL_LISTA_PESSOAS = "/reports/SubFormularioAcessoPessoaPoliciaFederalListaPessoas.jasper";
    private static final String SUB_FORM_ACESSO_PESSOA_POLICIA_FEDERAL_LISTA_VISITANTES = "/reports/SubFormularioRequerimentoDeVisitaAEmbarcacaoListaVisitantes.jasper";
         
    
    private static final String RELATORIO_CTAC_RECEITA_POR_PRODUTOS = "/reports/CTACReceitaPorProdutos.jasper";
    private static final String RELATORIO_CTAC_RECEITA_COM_PRODUTOS = "/reports/CTACReceitaComProdutos.jasper";

    private static Map<String, Object> montarCabecalhoCapitania() {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("IMAGEM_REPUBLICA", ReportUtils.class.getResource(IMAGEM_REPUBLICA));
        parametros.put("IMAGEM_CAPITANIA", ReportUtils.class.getResource(IMAGEM_CAPITANIA));
        return parametros;
    }
    private static Map<String, Object> montarCabecalhoPetroleoBrasileiro() {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("IMAGEM_PETROLEO_BRASILEIRO_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROLEO_BRASILEIRO_PETROBRAS));
    
        return parametros;
    }

    private static Map<String, Object> montarCabecalhoAnvisa() {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("IMAGEM_REPUBLICA", ReportUtils.class.getResource(IMAGEM_REPUBLICA));
        parametros.put("IMAGEM_ANVISA", ReportUtils.class.getResource(IMAGEM_ANVISA));
        return parametros;
    }

    public static void abrirRelatorioTermoResponsabilidadeRepresentanteLegal(TermoResponsabilidadeRepresentanteLegalCapitaniaVO vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();
        List<TermoResponsabilidadeRepresentanteLegalCapitaniaVO> listaVo = new ArrayList<TermoResponsabilidadeRepresentanteLegalCapitaniaVO>();
        listaVo.add(vo);
        ReportUtils.exibirRelatorio(RELATORIO_TERMO_RESPONSABILIDADE_REPRESENTANTE_LEGAL_CAPITANIA, listaVo, parametros);
    }

    public static void abrirRelatorioPasseSaidaCapitania(PasseDeSaidaCapitaniaVo vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();
        List<PasseDeSaidaCapitaniaVo> listaVo = new ArrayList<PasseDeSaidaCapitaniaVo>();
        listaVo.add(vo);
        ReportUtils.exibirRelatorio(RELATORIO_PASSE_SAIDA_CAPITANIA, listaVo, parametros);
    }
    public static void abrirRelatorioNotaDespacho(NotaDespachoVo vo) {
        Map<String, Object> parametros = montarCabecalhoPetroleoBrasileiro();
        ReportUtils.exibirRelatorio(RELATORIO_NOTA_DESPACHO, Arrays.asList(vo), parametros);
    }
    public static void abrirRelatorioPasseSaidaPeriodo(PasseDeSaidaPeriodoVo vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();
        List<PasseDeSaidaPeriodoVo> listaVo = new ArrayList<PasseDeSaidaPeriodoVo>();
        listaVo.add(vo);
        ReportUtils.exibirRelatorio(RELATORIO_PASSE_SAIDA_PERIODO, listaVo, parametros);
    }

    public static void abrirRelatorioPedidoDespachoCapitania(PedidoDespachoProximoPortoCapitaniaVo vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();
        List<PedidoDespachoProximoPortoCapitaniaVo> listaVo = new ArrayList<PedidoDespachoProximoPortoCapitaniaVo>();
        listaVo.add(vo);
        ReportUtils.exibirRelatorio(RELATORIO_PEDIDO_DESPACHO_CAPITANIA, listaVo, parametros);
    }

    public static void abrirRelatorioParteDeSaidaCapitania(ParteDeSaidaCapitaniaVo vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();
        List<ParteDeSaidaCapitaniaVo> listaVo = new ArrayList<ParteDeSaidaCapitaniaVo>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_PARTE_DE_SAIDA_CAPITANIA, listaVo, parametros);
    }

    public static void abrirRelatorioParteDeEntradaCapitania(ParteDeEntradaCapitaniaVo vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();

        List<ParteDeEntradaCapitaniaVo> listaVo = new ArrayList<ParteDeEntradaCapitaniaVo>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_PARTE_DE_ENTRADA_CAPITANIA, listaVo, parametros);
    }
    
    public static void abrirRelatorioAvisoDeSaidaCapitania(AvisoDeSaidaCapitaniaVo vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();
        List<AvisoDeSaidaCapitaniaVo> listaVo = new ArrayList<AvisoDeSaidaCapitaniaVo>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_AVISO_DE_SAIDA_CAPITANIA, listaVo, parametros);
    }
    
    public static void abrirRelatorioAvisoDeEntradaCapitania(FiltroAvisoEntradaVo vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();
        List<FiltroAvisoEntradaVo> listaVo = new ArrayList<FiltroAvisoEntradaVo>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_AVISO_DE_ENTRADA_CAPITANIA, listaVo, parametros);
    }
    
    public static void abrirRelatorioPedidoDespachoPorPeriodo(FiltroPedidoDespachoPorPeriodoVo vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();
        List<FiltroPedidoDespachoPorPeriodoVo> listaVo = new ArrayList<FiltroPedidoDespachoPorPeriodoVo>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_PEDIDO_DE_DESPACHO_POR_PERIODO, listaVo, parametros);
    } 

    public static void abrirFormularioRegistroMovimentacaoEmbarcacao(RegistroDeMovimentacaoCapitaniaVo vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();
        List<RegistroDeMovimentacaoCapitaniaVo> listaVo = new ArrayList<RegistroDeMovimentacaoCapitaniaVo>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(FORMULARIO_REGISTRO_MOVIMENTACAO_EMBARCACAO, listaVo, parametros);
    }

    public static void abrirRelatorioRegistroAlteracaoDestinoCapitania(RegistroDeAlteracaoDestinoCapitaniaVo vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();
        List<RegistroDeAlteracaoDestinoCapitaniaVo> listaVo = new ArrayList<RegistroDeAlteracaoDestinoCapitaniaVo>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_REGISTRO_ALTERACAO_DESTINO_CAPITANIA, listaVo, parametros);

    }

    public static void abrirRelatorioDeclaracaoGeralEntrada(DeclaracaoGeralCapitaniaVO vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();
        List<DeclaracaoGeralCapitaniaVO> listaVo = new ArrayList<DeclaracaoGeralCapitaniaVO>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_DECLARACAO_GERAL_ENTRADA, listaVo, parametros);

    }

    public static void abrirRelatorioDeclaracaoGeralDespacho(DeclaracaoGeralCapitaniaVO vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();
        List<DeclaracaoGeralCapitaniaVO> listaVo = new ArrayList<DeclaracaoGeralCapitaniaVO>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_DECLARACAO_GERAL_DESPACHO, listaVo, parametros);

    }

    public static void abrirRelatorioComunicacaoDeChegada(LiberacaoAnvisaVO liberacaoAnvisaVO) {
        Map<String, Object> parametros = montarCabecalhoAnvisa();

        /*
         * O parametro VO precisa ser adicionado. O relatório precisa ser refatorado para 
         * pegar as informações diretamente da lista.
         */
        parametros.put("VO", liberacaoAnvisaVO);

        List<LiberacaoAnvisaVO> listaVo = new ArrayList<LiberacaoAnvisaVO>();
        listaVo.add(liberacaoAnvisaVO);

        ReportUtils.exibirRelatorio(RELATORIO_COMUNICACAO_DE_CHEGADA, listaVo, parametros);
    }

    public static void abrirRelatorioLivrePratica(LiberacaoAnvisaVO liberacaoAnvisaVO) {
        Map<String, Object> parametros = montarCabecalhoAnvisa();

        /*
         * O parametro VO precisa ser adicionado. O relatório precisa ser refatorado para 
         * pegar as informações diretamente da lista.
         */
        parametros.put("VO", liberacaoAnvisaVO);


        List<LiberacaoAnvisaVO> listaVo = new ArrayList<LiberacaoAnvisaVO>();
        listaVo.add(liberacaoAnvisaVO);

        ReportUtils.exibirRelatorio(RELATORIO_LIVRE_PRATICA, listaVo, parametros);
    }

    public static void abrirRelatoriNotificacaoDePrevisaoDeChegada(NotificacaoPrevisaoChegadaCapitaniaVo vo) {
        Map<String, Object> parametros = montarCabecalhoCapitania();
        List<NotificacaoPrevisaoChegadaCapitaniaVo> listaVo = new ArrayList<NotificacaoPrevisaoChegadaCapitaniaVo>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_NOTIFICACAO_PREVISAO_CHEGADA, listaVo, parametros);
    }

    public static void abrirRelatorioTermoDeResponsabilidadeReceita(TermoResponsabilidadeReceitaVO vo) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("IMAGEM_RECEITA", ReportUtils.class.getResource(IMAGEM_RECEITA));


        List<TermoResponsabilidadeReceitaVO> listaVo = new ArrayList<TermoResponsabilidadeReceitaVO>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_TERMO_RESPONSABILIDADE_RECEITA, listaVo, parametros);
    }

    public static void abrirRelatorioMensalTaxas(List<TaxaMensalVO> listaTaxas, FiltroTaxa filtro) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("IMAGEM_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROBRAS));
        parametros.put("IMAGEM_NP1", ReportUtils.class.getResource(IMAGEM_NP1));
        parametros.put("FILTRO", filtro);

        ReportUtils.exibirRelatorio(RELATORIO_MENSAL_TAXAS, listaTaxas, parametros);
    }

    public static void abrirRelatorioAgenciamentoProdutividade(RelatorioAgenciamentoProdutividadeVO vo, Map<String, Object> parametros) {
        parametros.put("IMAGEM_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROBRAS));
        parametros.put("IMAGEM_NP1", ReportUtils.class.getResource(IMAGEM_NP1));
        
        List<RelatorioAgenciamentoProdutividadeVO> lista = new ArrayList<RelatorioAgenciamentoProdutividadeVO>();
        lista.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_AGENCIAMENTO_PRODUTIVIDADE, lista, parametros);
    }

    public static void abrirRelatorioControlePagamentoTaxas(List<Taxa> listaTaxas, Map<String, Object> parametros) {
        parametros.put("IMAGEM_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROBRAS));
        parametros.put("IMAGEM_NP1", ReportUtils.class.getResource(IMAGEM_NP1));
        ReportUtils.exibirRelatorio(RELATORIO_CONTROLE_PAGAMENTO_TAXA, listaTaxas, parametros);
    }

    public static void abrirRelatorioTermoResponsabilidadeTUFCapitania(TermoCompromissoTUFVO vo) {
        // Map<String, Object> parametros = montarCabecalhoCapitania();
        List<TermoCompromissoTUFVO> listaVo = new ArrayList<TermoCompromissoTUFVO>();
        listaVo.add(vo);
        ReportUtils.exibirRelatorio(RELATORIO_TERMO_COMPROMISSO_TUF_CAPITANIA, listaVo, null);
    }

    public static void abrirRelatorioMovimentacaoEmbarcacaoResumido(RelatorioMovimentacaoEmbarcacaoVO vo, Map<String, Object> parametros) {
        parametros.put("IMAGEM_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROBRAS));
        parametros.put("IMAGEM_NP1", ReportUtils.class.getResource(IMAGEM_NP1));
        

        List<RelatorioMovimentacaoEmbarcacaoVO> listaVo = new ArrayList<RelatorioMovimentacaoEmbarcacaoVO>();
        listaVo.add(vo);
        ReportUtils.exibirRelatorio(RELATORIO_MOVIMENTACAO_EMBARCACAO_RESUMIDO, listaVo, parametros);
    }

    public static void abrirRelatorioMovimentacaoEmbarcacaoCompleto(RelatorioMovimentacaoEmbarcacaoVO vo, Map<String, Object> parametros) {
        parametros.put("IMAGEM_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROBRAS));
        parametros.put("IMAGEM_NP1", ReportUtils.class.getResource(IMAGEM_NP1));

        List<RelatorioMovimentacaoEmbarcacaoVO> listaVo = new ArrayList<RelatorioMovimentacaoEmbarcacaoVO>();
        listaVo.add(vo);
        ReportUtils.exibirRelatorio(RELATORIO_MOVIMENTACAO_EMBARCACAO, listaVo, parametros);
    }

    public static void abrirFormulairoCTACReceitaPorProdutos(List<CTACReceitaVo> vos) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("IMAGEM_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROBRAS));

        ReportUtils.exibirRelatorio(RELATORIO_CTAC_RECEITA_POR_PRODUTOS, vos, parametros);
    }

    public static void abrirFormulairoCTACReceitaComProdutos(CTACReceitaVo vo) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("IMAGEM_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROBRAS));

        List<CTACReceitaVo> listaVo = new ArrayList<CTACReceitaVo>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_CTAC_RECEITA_COM_PRODUTOS, listaVo, parametros);
    }

    public static void abrirRelatorioAgenciamentoProdutividadeAtendimentosRealizados(List<RelatorioAgenciamentoAtendimentoVO> lista, Map<String, Object> parametros) {
        parametros.put("IMAGEM_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROBRAS));
        parametros.put("IMAGEM_NP1", ReportUtils.class.getResource(IMAGEM_NP1));

        ReportUtils.exibirRelatorio(RELATORIO_AGENCIAMENTO_PRODUTIVIDADE_ATENDIMENTOS_REALIZADOS, lista, parametros);
    }

    public static void abrirFormularioSolicitacaoTransporte(List<ServicoProtecao> lista, Map<String, Object> parametros) {
        parametros.put("IMAGEM_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROBRAS));
        parametros.put("DATA_IMPRESSAO", SistamDateUtils.formatDate(new Date(), "dd/MM/yyyy HH:mm", null));

        ReportUtils.exibirRelatorio(FORMULARIO_SOLICITACAO_TRANSPORTE, lista, parametros);
    }

    public static void abrirFormularioSolicitacaoAcessoPessoaPoliciaFederal(List<PessoaAcessoVO> lista, Map<String, Object> parametros) {
        parametros.put("IMAGEM_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROBRAS));
        ReportUtils.exibirRelatorio(FORMULARIO_SOLICITACAO_ACESSO_PESSOA_POLICIA_FEDERAL, lista, parametros);
    }
    
    public static void abrirFormularioSolicitacaoAcessoPessoaPoliciaFederalRequerimentoEmbarque(List<RelatorioPessoaPoliciaFederalVO> lista, Map<String, Object> parametros){
        parametros.put("IMAGEM_POLICIA_FEDERAL", ReportUtils.class.getResource(IMAGEM_POLICIA_FEDERAL));
         parametros.put("SUB_FORM_ACESSO_PESSOA_POLICIA_FEDERAL_LISTA_PESSOAS", SUB_FORM_ACESSO_PESSOA_POLICIA_FEDERAL_LISTA_PESSOAS);
        ReportUtils.exibirRelatorio(FORMULARIO_SOLICITACAO_ACESSO_PESSOA_POLICIA_FEDERAL_REQUERIMENTO_EMBARQUE, lista, parametros);        
    }
    
    public static void abrirFormularioSolicitacaoAcessoPessoaPoliciaFederalRequerimentoDesembarque(List<RelatorioPessoaPoliciaFederalVO> lista, Map<String, Object> parametros){
        parametros.put("IMAGEM_POLICIA_FEDERAL", ReportUtils.class.getResource(IMAGEM_POLICIA_FEDERAL)); 
        parametros.put("SUB_FORM_ACESSO_PESSOA_POLICIA_FEDERAL_LISTA_PESSOAS", SUB_FORM_ACESSO_PESSOA_POLICIA_FEDERAL_LISTA_PESSOAS);
        ReportUtils.exibirRelatorio(FORMULARIO_SOLICITACAO_ACESSO_PESSOA_POLICIA_FEDERAL_REQUERIMENTO_DESEMBARQUE, lista, parametros); 
    }
    
    public static void abrirFormularioSolicitacaoAcessoPessoaPoliciaFederalRequerimentoAcessoEmbarcacao(List<RelatorioPessoaPoliciaFederalVO> lista, Map<String, Object> parametros){
        parametros.put("IMAGEM_POLICIA_FEDERAL", ReportUtils.class.getResource(IMAGEM_POLICIA_FEDERAL)); 
        parametros.put("SUB_FORM_ACESSO_PESSOA_POLICIA_FEDERAL_LISTA_PESSOAS", SUB_FORM_ACESSO_PESSOA_POLICIA_FEDERAL_LISTA_PESSOAS);
        ReportUtils.exibirRelatorio(FORMULARIO_SOLICITACAO_ACESSO_PESSOA_POLICIA_FEDERAL_REQUERIMENTO_ACESSO_EMBARCACAO, lista, parametros); 
    }
    
    public static void abrirFormularioSolicitacaoAcessoPessoaPoliciaFederalRequerimentoVisitaEmbarcacao(List<RelatorioVisitaAEmbarcacaoPoliciaFederalVO> lista, Map<String, Object> parametros){
        parametros.put("IMAGEM_POLICIA_FEDERAL", ReportUtils.class.getResource(IMAGEM_POLICIA_FEDERAL)); 
        parametros.put("SUB_FORM_ACESSO_PESSOA_POLICIA_FEDERAL_LISTA_VISITANTES", SUB_FORM_ACESSO_PESSOA_POLICIA_FEDERAL_LISTA_VISITANTES);
        ReportUtils.exibirRelatorio(FORMULARIO_SOLICITACAO_ACESSO_PESSOA_POLICIA_FEDERAL_REQUERIMENTO_VISITA_EMBARCACAO, lista, parametros); 
    }

    public static void abrirFormularioSolicitacaoAcessoPessoaReceitaFederal(List<RelatorioReceitaFederalVO> lista, Map<String, Object> parametros) {
        parametros.put("IMAGEM_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROBRAS));
        parametros.put("IMAGEM_RECEITA", ReportUtils.class.getResource(IMAGEM_RECEITA_MAIOR));
        parametros.put("IMAGEM_GUARDA", ReportUtils.class.getResource(IMAGEM_GUARDA));
        parametros.put("SUB_FORM_NOME_RECEITA_FEDERAL", SUB_FORM_NOME_SOLICITACAO_ACESSO_PESSOA_RECEITA_FEDERAL);
        parametros.put("SUB_FORM_BAGAGEM_RECEITA_FEDERAL", SUB_FORM_BAGAGEM_SOLICITACAO_ACESSO_PESSOA_RECEITA_FEDERAL);
        ReportUtils.exibirRelatorio(FORMULARIO_SOLICITACAO_ACESSO_PESSOA_RECEITA_FEDERAL, lista, parametros);
    }

    public static void abrirRelatorioReceitaFederalDesEmbarqueTripulates(List<RelatorioReceitaDesEmbarqueTripulanteVO> lista, Map<String, Object> parametros) {
        parametros.put("SUBREPORT_PATH", SUB_FORMULARIO_RECEITA_FEDERAL_EMBARQUE_DESEMBARQUE_TRIPULANTES);
        parametros.put("MINSTERIOFAZENDA_LOGO_PATH", ReportUtils.class.getResource("/icons/logo_ministerio_fazenda.jpg"));
        parametros.put("RECEITAFEDERAL_LOGO_PATH", ReportUtils.class.getResource("/icons/logo_receita_federal.jpg"));
        ReportUtils.exibirRelatorio(FORMULARIO_RECEITA_FEDERAL_EMBARQUE_DESEMBARQUE_TRIPULANTES, lista, parametros);
    }

    public static void abrirRelatorioReceitaFederalPrestacaoServico(List<RelatorioReceitaPrestacaoServicoVO> lista, Map<String, Object> parametros) {
        parametros.put("VEICULOS_SUBREPORT_PATH", SUB_FORMULARIO_RECEITA_FEDERAL_PRESTACAO_SERVICO_VEICULOS);
        parametros.put("FUNCIONARIOS_SUBREPORT_PATH", SUB_FORMULARIO_RECEITA_FEDERAL_PRESTACAO_SERVICO_FUNCIONARIOS);
        parametros.put("MINSTERIOFAZENDA_LOGO_PATH", ReportUtils.class.getResource("/icons/logo_ministerio_fazenda.jpg"));
        parametros.put("RECEITAFEDERAL_LOGO_PATH", ReportUtils.class.getResource("/icons/logo_receita_federal.jpg"));
        ReportUtils.exibirRelatorio(FORMULARIO_RECEITA_FEDERAL_PRESTACAO_SERVICO, lista, parametros);
    }

    public static void abrirFormularioSolicitacaoServicoSuprimento(List<RelatorioServicoSuprimentoTransitoVO> lista, Map<String, Object> parametros) {
        parametros.put("IMAGEM_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROBRAS));
        parametros.put("IMAGEM_RECEITA", ReportUtils.class.getResource(IMAGEM_RECEITA_MAIOR));
        parametros.put("IMAGEM_GUARDA", ReportUtils.class.getResource(IMAGEM_GUARDA));
        parametros.put("SUB_FORM_SERVICO_SUPRIMENTO_FORNECEDOR", SUB_FORM_SERVICO_SUPRIMENTO_FORNECEDOR);
        parametros.put("SUB_FORM_SERVICO_SUPRIMENTO_CONDUTOR", SUB_FORM_SERVICO_SUPRIMENTO_CONDUTOR);
        ReportUtils.exibirRelatorio(FORMULARIO_SERVICO_SUPRIMENTO, lista, parametros);
    }

    public static void abrirFormularioRetiradaResiduoLixo(Agencia agencia, TipoResiduo tipoResiduo, List<RelatorioServicoRetiradaResiduoLixoVO> lista, Map<String, Object> parametros) { 
        
        if(agencia.getNome().equals("SÃO SEBASTIÃO")){
            parametros.put("IMAGEM_AGENCIA", ReportUtils.class.getResource(IMAGEM_PORTO_SAO_SEBASTIAO));
            parametros.put("MOSTRAR_TITULO", true);
            parametros.put("MOSTRAR_RODAPE", true);
        }else{
            parametros.put("IMAGEM_AGENCIA", ReportUtils.class.getResource(IMAGEM_PETROBRAS));
            parametros.put("MOSTRAR_TITULO", false);
        } 
        if(tipoResiduo.equals(TipoResiduo.CLASSE_I)){
            parametros.put("SUB_FORM_SOLICITACAO_RETIRADA_RESIDUO_LIXO", SUB_FORM_SOLICITACAO_RETIRADA_RESIDUO_LIXO_CLASSE_I);
        } else {
            parametros.put("SUB_FORM_SOLICITACAO_RETIRADA_RESIDUO_LIXO", SUB_FORM_SOLICITACAO_RETIRADA_RESIDUO_LIXO_CLASSE_II);
        }
        
        ReportUtils.exibirRelatorio(FORMULARIO_SOLICITACAO_RETIRADA_RESIDUO_LIXO, lista, parametros);
    }
    
    public static void abrirFormularioControleFiscalizacaoUnicoAgencias(Agencia agencia, FormularioControleFiscalizacaoUnicoVO vo, Map<String, Object> parametros) { 
        
        if(agencia.getNome().equals("SÃO SEBASTIÃO")){
            parametros.put("IMAGEM_AGENCIA", ReportUtils.class.getResource(IMAGEM_REPUBLICA)); 
        }else{
            parametros.put("IMAGEM_AGENCIA", ReportUtils.class.getResource(IMAGEM_PETROBRAS));
            
        } 
        List<FormularioControleFiscalizacaoUnicoVO> listaVo = new ArrayList<FormularioControleFiscalizacaoUnicoVO>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_CONTROLE_FISCALIZACAO_UNICO_AGENCIAS, listaVo, parametros);
      
    }
    
    public static void abrirFormularioControleFiscalizacaoUnicoEspecifoParaAgenciaManaus(Agencia agencia, FormularioControleFiscalizacaoUnicoVO vo, Map<String, Object> parametros) { 
        
        parametros.put("IMAGEM_PETROBRAS_MANAUS", ReportUtils.class.getResource(IMAGEM_PETROBRAS));
        
        List<FormularioControleFiscalizacaoUnicoVO> listaVo = new ArrayList<FormularioControleFiscalizacaoUnicoVO>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(RELATORIO_CONTROLE_FISCALIZACAO_UNICO_ESPECIFICO_PARA_AGENCIA_MANAUS, listaVo, parametros);
      
    }
    
    public static void abrirFormularioPasseEntradaPoliciaFederalAgenciaSalvador(Agencia agencia, FormularioPasseEntradaPoliciaFederalVO vo, Map<String, Object> parametros) {
        
        parametros.put("IMAGEM_REPUBLICA", ReportUtils.class.getResource(IMAGEM_REPUBLICA)); 
        parametros.put("IMAGEM_POLICIA_FEDERAL", ReportUtils.class.getResource(IMAGEM_POLICIA_FEDERAL));
        
        List<FormularioPasseEntradaPoliciaFederalVO> listaVo = new ArrayList<FormularioPasseEntradaPoliciaFederalVO>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(FORMULARIO_PASSE_ENTRADA_POLICIA_FEDERAL, listaVo, parametros);
      
    }
    
    public static void abrirFormularioPasseSaidaPoliciaFederalAgenciaSalvador(Agencia agencia, FormularioPasseSaidaPoliciaFederalVO vo, Map<String, Object> parametros) {
        
        parametros.put("IMAGEM_REPUBLICA", ReportUtils.class.getResource(IMAGEM_REPUBLICA)); 
        parametros.put("IMAGEM_POLICIA_FEDERAL", ReportUtils.class.getResource(IMAGEM_POLICIA_FEDERAL));
        
        List<FormularioPasseSaidaPoliciaFederalVO> listaVo = new ArrayList<FormularioPasseSaidaPoliciaFederalVO>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(FORMULARIO_PASSE_SAIDA_POLICIA_FEDERAL, listaVo, parametros);
      
    }
    
    public static void abrirFormularioRequerimentoPasseSaidaPoliciaFederalAgenciaSalvador(Agencia agencia, FormularioRequerimentoPasseSaidaPoliciaFederalVO vo, Map<String, Object> parametros) {
        
        parametros.put("IMAGEM_PETROLEO_BRASILEIRO_PETROBRAS", ReportUtils.class.getResource(IMAGEM_PETROLEO_BRASILEIRO_PETROBRAS));
        
        List<FormularioRequerimentoPasseSaidaPoliciaFederalVO> listaVo = new ArrayList<FormularioRequerimentoPasseSaidaPoliciaFederalVO>();
        listaVo.add(vo);

        ReportUtils.exibirRelatorio(FORMULARIO_REQUERIMENTO_PASSE_SAIDA_POLICIA_FEDERAL, listaVo, parametros);
      
    }
        
}