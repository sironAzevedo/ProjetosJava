package br.com.petrobras.sistam.desktop.agenciamento;

import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.AvisoEntradaDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.AvisoSaidaDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.DeclaracaoGeralDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.EmitirRegistroAlteracaoDestinoDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.EmitirRegistroMovimentacaoDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.NotaDespachoDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.NotificacaoDePrevisaoModel;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.ParteEntradaDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.ParteSaidaDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.PasseSaidaDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.PedidoDespachoDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.PedidoDespachoPorPeriodoDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.TermoCompromissoTUFDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.TermoResponsabilidadeRepresentanteLegalDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania.PasseSaidaPeriodoDialog;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;

/**
 * Classe MenuComunicacaoCaptania
 */
public class MenuComunicacaoCaptania extends JPopupMenu {

    public static final String PROP_ITEM_MENU_CLICADO = "PROP_ITEM_MENU_CLICADO";
    Agenciamento agenciamento;

    public MenuComunicacaoCaptania(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        initComponents();

        add(actionAlteracaoDestino);
        add(actionAvisoEntrada);
        add(actionAvisoSaida);
        add(actionDeclaracaoGeralEntrada);
        add(actionNotaDeDespachoMaritimo);
        add(actionNotificaoPrevisaoChegada);
        add(actionParteEntrada);
        add(actionParteSaida);
        add(actionPasseSaida);
        add(actionPasseSaidaPorPeriodo);
        add(actionPedidoDespacho);
        add(actionPedidoDespachoPorPeriodo);
        add(actionMovimentacao);
        add(actionTermoCompromissoTUF);
        add(actionTermoResponsabilidadeRepresentanteLegal);

    }

    public void emitirRelatorioTermoResponsabilidadeRepresentanteLegal() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarTermoResponsabilidadeRepresentanteLegal();
        TermoResponsabilidadeRepresentanteLegalDialog dialog = new TermoResponsabilidadeRepresentanteLegalDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    public void emitirRelatorioPasseSaida() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarPasseDeSaida();
        PasseSaidaDialog dialog = new PasseSaidaDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    public void emitirRelatorioParteDeEntrada() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarParteEntrada();
        ParteEntradaDialog dialog = new ParteEntradaDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    public void emitirRelatorioPedidoDespacho() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarPedidoDespacho();
        PedidoDespachoDialog dialog = new PedidoDespachoDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    public void emitirRelatorioParteDeSaida() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarParteDeSaida();
        ParteSaidaDialog dialog = new ParteSaidaDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    public void emitirRelatorioMovimentacao() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarRegistroMovimentacao();
        EmitirRegistroMovimentacaoDialog dialog = new EmitirRegistroMovimentacaoDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    public void emitirRelatorioAlteracaoDestino() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarAlteracaoDestino();
        EmitirRegistroAlteracaoDestinoDialog dialog = new EmitirRegistroAlteracaoDestinoDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    public void emitirDeclaracaoGeralEntrada() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        DeclaracaoGeralDialog dialog = new DeclaracaoGeralDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    public void emitirRelatorioNotificacaoDePrevisaoDeChegada() {
        NotificacaoDePrevisaoModel model = new NotificacaoDePrevisaoModel(agenciamento);
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarNotificacaoDePrevisaoDeChegada();
        model.carregarVO();
        model.registrarEmissao();
        RelatorioUtil.abrirRelatoriNotificacaoDePrevisaoDeChegada(model.getVoFormulario());
    }

    public void emitirRelatorioTermoCompromissoTUF() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarTermoResponsabilidadeTUF();
        TermoCompromissoTUFDialog dialog = new TermoCompromissoTUFDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    public void emitirRelatorioAvisoDeSaida() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarAvisoDeSaida();
        AvisoSaidaDialog dialog = new AvisoSaidaDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    public void emitirRelatorioAvisoDeEntrada() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarAvisoDeEntrada();
        AvisoEntradaDialog dialog = new AvisoEntradaDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    public void emitirRelatorioPedidoDespachoPorPeriodo() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarPedidoDespachoPorPeriodo();
        PedidoDespachoPorPeriodoDialog dialog = new PedidoDespachoPorPeriodoDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    public void emitirRelatorioPasseSaidaPorPeriodo() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarPasseDeSaidaPeriodo();
        PasseSaidaPeriodoDialog dialog = new PasseSaidaPeriodoDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    public void emitirNotaDeDespachoMaritimo() {
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarNotaDeDespachoMaritimo();
        NotaDespachoDialog dialog = new NotaDespachoDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }

    //<editor-fold defaultstate="collapsed" desc="Validação">
    public void validarTermoResponsabilidadeRepresentanteLegal() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getNomeCompleto()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_NOME_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getBandeira()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_BANDEIRA_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getImo()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_IMO_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getProprietario()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_PROPRIETARIO_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getArmador()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_ARMADOR_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getPorto()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_INFORME_PORTO_ATUAL);
        pm.assertNotEmpty(agenciamento.getAgenciementoViagem().getComandanteEntrada()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_VIAGEM_INFORME_COMANDANTE_ENTRADA);
        pm.assertNotEmpty(agenciamento.getAgencia().getEndereco()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_ENDERECO_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getAgencia().getEmail()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_EMAIL_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getAgencia().getTelefone()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_TELEFONE_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getAgencia().getCnpj()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_CNPJ_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getAgencia().getCidade()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_CIDADE_OBRIGATORIO);
        pm.verifyAll();
    }

    public void validarPedidoDespacho() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getDataEstimadaSaida()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_DATA_ESTIMADA_SAIDA);
        pm.assertNotNull(agenciamento.getDestinoFormatado()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_PORTO_DESTINO_OU_DESTINO_INTERMEDIARIO_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getNomeCompleto()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_NOME_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getIrin()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_IRIN_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getBandeira()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_BANDEIRA_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getClassificacao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_CLASSIFICACAO_OBRIGATORIA);
        pm.assertNotNull(agenciamento.getAreaNavegacaoSaida()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_INFORME_AREA_NAVEGACAO_SAIDA);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getImo()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_IMO_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getArqueacaoBruta()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_ARQUEACAO_BRUTA_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getDwt()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_DWT_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getDataConstrucao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_DT_CONSTRUCAO_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getArmador()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_ARMADOR_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getAgencia().getNome()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_NOME_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getAgencia().getEndereco()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_ENDERECO_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getAgencia().getEmail()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_EMAIL_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getAgencia().getTelefone()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_TELEFONE_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getAgencia().getCidade()).orRegister(TipoExcecao.AGENCIA, ConstantesI18N.AGENCIA_CIDADE_OBRIGATORIO);
        pm.verifyAll();
    }

    public void validarPasseDeSaida() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getDataEstimadaSaida()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_DATA_ESTIMADA_SAIDA);
        pm.assertNotNull(agenciamento.getDestinoFormatado()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_PORTO_DESTINO_OU_DESTINO_INTERMEDIARIO_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getAgenciementoViagem().getComandanteSaida()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_VIAGEM_INFORME_COMANDANTE_SAIDA);
        pm.verifyAll();
    }

    public void validarParteDeSaida() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getAreaNavegacaoSaida()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_PARTE_SAIDA_INFORME_AREA_NAVEGACAO_SAIDA);
        pm.assertNotNull(agenciamento.getDataSaida()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_DATA_SAIDA);
        pm.assertNotNull(agenciamento.getCaladoSaidaVante()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_CALADO_SAIDA_VANTE);
        pm.assertNotNull(agenciamento.getCaladoSaidaRe()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_CALADO_SAIDA_RE);
        pm.assertNotNull(agenciamento.getDestinoFormatado()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_PORTO_DESTINO_OU_DESTINO_INTERMEDIARIO_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEtaProximoPorto()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_DATA_CHEGADA_ESTIMADA);
        pm.assertNotNull(agenciamento.getEmbarcacao().getClassificacao()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.EMBARCACAO_CLASSIFICACAO_OBRIGATORIA);
        pm.verifyAll();
    }

    private void validarParteEntrada() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getAreaNavegacao()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_PARTE_ENTRADA_INFORME_AREA_NAVEGACAO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getClassificacao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_CLASSIFICACAO_OBRIGATORIA);
        pm.assertNotNull(agenciamento.getEmbarcacao().getDataUltimaInspecao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.COMUNICACAO_CAPITANIA_SELECIONE_DATA_ULTIMA_INSPECAO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getPaisInspecao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.COMUNICACAO_CAPITANIA_SELECIONE_PAIS_ULTIMA_INSPECAO);
        pm.assertThat(agenciamento.getEmbarcacao().getDataValidadeDeclaracaoConformidade() != null || agenciamento.getEmbarcacao().getDataValidadeDeclaracaoProvisoria() != null)
                .orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.COMUNICACAO_CAPITANIA_SELECIONE_VALIDADE_DECLARACAO_CONFORMIDADE_OU_PROVISORIA);
        pm.verifyAll();
    }

    public void validarRegistroMovimentacao() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_INFORME_NUMERO_PASSAGEIROS);
        pm.assertNotNull(agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_INFORME_NUMERO_TRIPULANTES);
        pm.verifyAll();
    }

    public void validarAlteracaoDestino() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertThat(StatusEmbarcacao.DESVIADO.equals(agenciamento.getStatusEmbarcacao())).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.COMUNICACAO_CAPITANIA_EMBARCACAO_NAO_FOI_DESVIADA);
        pm.verifyAll();

    }

    public void validarNotificacaoDePrevisaoDeChegada() {
        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertNotEmpty(agenciamento.getEmbarcacao().getIrin()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_IRIN_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getBandeira()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_BANDEIRA_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getImo()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_IMO_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getClassificacao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_CLASSIFICACAO_OBRIGATORIA);
        pm.assertNotNull(agenciamento.getEmbarcacao().getClassificacao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_CLASSIFICACAO_OBRIGATORIA);
        pm.assertNotNull(agenciamento.getEmbarcacao().getArqueacaoBruta()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_ARQUEACAO_BRUTA_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getArmador()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_ARMADOR_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getSociedade_classificadora()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.CUMINICACAO_CAPITANIA_INFORME_SOCIEDADE_CLASSIFICATORIA);

        pm.verifyAll();
    }

    public void validarTermoResponsabilidadeTUF() {
        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertNotEmpty(agenciamento.getEmbarcacao().getImo()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_IMO_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getDwt()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_DWT_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getNumeroProcessoDespacho()).orRegister(TipoExcecao.INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_PROCESSO_DESPACHO);
        pm.verifyAll();
    }

    public void validarAvisoDeSaida() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getCaladoSaidaVante()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_CALADO_SAIDA_VANTE);
        pm.assertNotNull(agenciamento.getCaladoSaidaRe()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_CALADO_SAIDA_RE);
        pm.assertNotNull(agenciamento.getNumeroDUV()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.INFORME_DUV);
        pm.assertNotNull(agenciamento.getNumeroProcessoDespacho()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_PROCESSO_DESPACHO);
        pm.assertNotNull(agenciamento.getPortoDestino()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.INFORME_PONTO_ATRACACAO_DESTINO);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getImo()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_IMO_OBRIGATORIO);

        pm.verifyAll();
    }

    public void validarPasseDeSaidaPeriodo() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getDataEstimadaSaida()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_DATA_ESTIMADA_SAIDA);
        pm.assertNotNull(agenciamento.getDestinoFormatado()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_PORTO_DESTINO_OU_DESTINO_INTERMEDIARIO_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getAgenciementoViagem().getComandanteSaida()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_VIAGEM_INFORME_COMANDANTE_SAIDA);
        pm.verifyAll();
    }

    public void validarNotaDeDespachoMaritimo() {

        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getDataEstimadaSaida()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_DATA_ESTIMADA_SAIDA);
        pm.assertNotNull(agenciamento.getDestinoFormatado()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.AGENCIAMENTO_PORTO_DESTINO_OU_DESTINO_INTERMEDIARIO_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getAgenciementoViagem().getComandanteSaida()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_VIAGEM_INFORME_COMANDANTE_SAIDA);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getImo()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_IMO_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getBandeira()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_BANDEIRA_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getArmador()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_ARMADOR_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getArqueacaoBruta()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_ARQUEACAO_BRUTA_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getArqueacaoLiquida()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_ARQUEACAO_BRUTA_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getDwt()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_DWT_OBRIGATORIO);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getIrin()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_IRIN_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getAgencia().getAgenciaOrgao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.INFORME_O_ORGAO_DESPACHO);
        pm.assertNotNull(agenciamento.getNumeroDUV()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.INFORME_DUV);
        pm.assertNotNull(agenciamento.getAgencia().getAgenciaOrgao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.DOCUMENTACAO_OPERACAO_INFORME_AGENCIA_ORIGEM);
        pm.assertNotNull(agenciamento.getPortoOrigem()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.AGENCIAMENTO_INFORME_PORTO_ORIGEM);
        pm.assertNotNull(agenciamento.getPortoDestino()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.AGENCIAMENTO_INFORME_PORTO_DESTINO);
        pm.assertNotNull(agenciamento.getEta()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.AGENCIAMENTO_INFORME_ETA);
        pm.assertNotNull(agenciamento.getDataEstimadaSaida()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.AGENCIAMENTO_INFORME_ETS);
        pm.assertNotNull(agenciamento.getAgenciementoViagem().getNumeroTripulantesSaida()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.AGENCIAMENTO_INFORME_NUMERO_TRIPULANTES_SAIDA);
        pm.assertThat(verificarSeTemTUF(agenciamento)).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_TUF);
                
                
        pm.verifyAll();
    }

    private boolean verificarSeTemTUF(Agenciamento agenciamento) {
        boolean verificaTUF = false;
        Set<Taxa> t = agenciamento.getTaxas();
        for (Taxa taxa : t) {

            if (TipoTaxa.TUF_TAXA_UTILIZACAO_FAROL_CAPITANIA.equals(taxa.getTipoTaxa())) {
                Double valorTaxa = taxa.getValor();
                Date dataTaxa = taxa.getDataPagamento();
                Long numeroTaxa = taxa.getNumeroDocumento();
                verificaTUF = valorTaxa != null && dataTaxa != null && numeroTaxa != null;
                break;
            }
        }

        return verificaTUF;
    }

    private void validarAvisoDeEntrada() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getAreaNavegacao()).orRegister(TipoExcecao.AGENCIAMENTO_INFORMACOES_GERAIS, ConstantesI18N.COMUNICACAO_CAPITANIA_PARTE_ENTRADA_INFORME_AREA_NAVEGACAO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getBandeira()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_BANDEIRA_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getClassificacao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_CLASSIFICACAO_OBRIGATORIA);
        pm.assertNotNull(agenciamento.getEmbarcacao().getDataUltimaInspecao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.COMUNICACAO_CAPITANIA_SELECIONE_DATA_ULTIMA_INSPECAO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getPaisInspecao()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.COMUNICACAO_CAPITANIA_SELECIONE_PAIS_ULTIMA_INSPECAO);
        pm.assertThat(agenciamento.getEmbarcacao().getDataValidadeDeclaracaoConformidade() != null || agenciamento.getEmbarcacao().getDataValidadeDeclaracaoProvisoria() != null).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.COMUNICACAO_CAPITANIA_SELECIONE_VALIDADE_DECLARACAO_CONFORMIDADE_OU_PROVISORIA);
        pm.assertNotNull(agenciamento.getEmbarcacao().getArqueacaoBruta()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_ARQUEACAO_BRUTA_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getPortoDestino()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.AGENCIAMENTO_INFORME_PORTO_DESTINO);
        pm.verifyAll();
    }

    private void validarPedidoDespachoPorPeriodo() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(agenciamento.getAgenciementoViagem().getComandanteSaida()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_VIAGEM_INFORME_COMANDANTE_SAIDA);
        pm.assertNotEmpty(agenciamento.getEmbarcacao().getArmador()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_ARMADOR_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getArqueacaoBruta()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_ARQUEACAO_BRUTA_OBRIGATORIO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getDwt()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_DWT_OBRIGATORIO);
        pm.verifyAll();
    }

    //</editor-fold> 
    private void initComponents() {
        actionTermoResponsabilidadeRepresentanteLegal = new GenericAction();
        actionPasseSaida = new GenericAction();
        actionPedidoDespacho = new GenericAction();
        actionParteEntrada = new GenericAction();
        actionParteSaida = new GenericAction();
        actionMovimentacao = new GenericAction();
        actionAlteracaoDestino = new GenericAction();
        actionDeclaracaoGeralEntrada = new GenericAction();
        actionNotificaoPrevisaoChegada = new GenericAction();
        actionTermoCompromissoTUF = new GenericAction();
        actionAvisoSaida = new GenericAction();
        actionAvisoEntrada = new GenericAction();
        actionPedidoDespachoPorPeriodo = new GenericAction();
        actionPasseSaidaPorPeriodo = new GenericAction();
        actionNotaDeDespachoMaritimo = new GenericAction();

        actionTermoResponsabilidadeRepresentanteLegal.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionTermoResponsabilidadeRepresentanteLegal.setMethodName("emitirRelatorioTermoResponsabilidadeRepresentanteLegal");
        actionTermoResponsabilidadeRepresentanteLegal.setTarget(this);
        actionTermoResponsabilidadeRepresentanteLegal.setText("Termo De Responsabilidade do Representante Legal");

        actionPasseSaida.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionPasseSaida.setMethodName("emitirRelatorioPasseSaida");
        actionPasseSaida.setTarget(this);
        actionPasseSaida.setText("Passe De Saída");

        actionPedidoDespacho.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionPedidoDespacho.setMethodName("emitirRelatorioPedidoDespacho");
        actionPedidoDespacho.setTarget(this);
        actionPedidoDespacho.setText("Pedido De Despacho");

        actionParteEntrada.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionParteEntrada.setMethodName("emitirRelatorioParteDeEntrada");
        actionParteEntrada.setTarget(this);
        actionParteEntrada.setText("Parte De Entrada");

        actionMovimentacao.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionMovimentacao.setMethodName("emitirRelatorioMovimentacao");
        actionMovimentacao.setTarget(this);
        actionMovimentacao.setText("Registro De Movimentação");

        actionDeclaracaoGeralEntrada.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionDeclaracaoGeralEntrada.setMethodName("emitirDeclaracaoGeralEntrada");
        actionDeclaracaoGeralEntrada.setTarget(this);
        actionDeclaracaoGeralEntrada.setText("Declaração Geral");

        actionParteSaida.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionParteSaida.setMethodName("emitirRelatorioParteDeSaida");
        actionParteSaida.setTarget(this);
        actionParteSaida.setText("Parte De Saída");

        actionAlteracaoDestino.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionAlteracaoDestino.setMethodName("emitirRelatorioAlteracaoDestino");
        actionAlteracaoDestino.setTarget(this);
        actionAlteracaoDestino.setText("Alteração De Destino");

        actionNotificaoPrevisaoChegada.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionNotificaoPrevisaoChegada.setMethodName("emitirRelatorioNotificacaoDePrevisaoDeChegada");
        actionNotificaoPrevisaoChegada.setTarget(this);
        actionNotificaoPrevisaoChegada.setText("Notificação De Previsão De Chegada");

        actionTermoCompromissoTUF.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionTermoCompromissoTUF.setMethodName("emitirRelatorioTermoCompromissoTUF");
        actionTermoCompromissoTUF.setTarget(this);
        actionTermoCompromissoTUF.setText("Termo Compromisso Recolhimento TUF");

        actionAvisoSaida.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionAvisoSaida.setMethodName("emitirRelatorioAvisoDeSaida");
        actionAvisoSaida.setTarget(this);
        actionAvisoSaida.setText("Aviso De Saída");

        actionAvisoEntrada.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionAvisoEntrada.setMethodName("emitirRelatorioAvisoDeEntrada");
        actionAvisoEntrada.setTarget(this);
        actionAvisoEntrada.setText("Aviso De Entrada");

        actionPedidoDespachoPorPeriodo.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionPedidoDespachoPorPeriodo.setMethodName("emitirRelatorioPedidoDespachoPorPeriodo");
        actionPedidoDespachoPorPeriodo.setTarget(this);
        actionPedidoDespachoPorPeriodo.setText("Pedido De Despacho por Periodo");

        actionPasseSaidaPorPeriodo.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionPasseSaidaPorPeriodo.setMethodName("emitirRelatorioPasseSaidaPorPeriodo");
        actionPasseSaidaPorPeriodo.setTarget(this);
        actionPasseSaidaPorPeriodo.setText("Passe De Saída por Período");

        actionNotaDeDespachoMaritimo.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionNotaDeDespachoMaritimo.setMethodName("emitirNotaDeDespachoMaritimo");
        actionNotaDeDespachoMaritimo.setTarget(this);
        actionNotaDeDespachoMaritimo.setText("Nota De despacho Marítimo");

    }

    GenericAction actionTermoResponsabilidadeRepresentanteLegal;
    GenericAction actionPasseSaida;
    GenericAction actionPedidoDespacho;
    GenericAction actionParteEntrada;
    GenericAction actionParteSaida;
    GenericAction actionMovimentacao;
    GenericAction actionAlteracaoDestino;
    GenericAction actionDeclaracaoGeralEntrada;
    GenericAction actionNotificaoPrevisaoChegada;
    GenericAction actionTermoCompromissoTUF;
    GenericAction actionAvisoSaida;
    GenericAction actionAvisoEntrada;
    GenericAction actionPedidoDespachoPorPeriodo;
    GenericAction actionPasseSaidaPorPeriodo;
    GenericAction actionNotaDeDespachoMaritimo;

}
