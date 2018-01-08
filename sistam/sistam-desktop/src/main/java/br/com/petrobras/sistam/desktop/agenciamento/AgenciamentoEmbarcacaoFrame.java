package br.com.petrobras.sistam.desktop.agenciamento;

import br.com.petrobras.fcorp.swing.base.SInternalFrame;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SLabel;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.STabbedPane;
import br.com.petrobras.fcorp.swing.components.dialog.DialogMessages;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.CancelamentoAgenciamento;
import br.com.petrobras.sistam.common.entity.Desvio;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import static br.com.petrobras.sistam.common.util.SistamDateUtils.truncateTime;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.anvisa.LiberacaoAnvisaDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.receita.TermoDeResponsabilidadeReceitaDialog;
import br.com.petrobras.sistam.desktop.agenciamento.panels.AgenciamentoSanitariaPainel;
import br.com.petrobras.sistam.desktop.agenciamento.panels.AgenciamentoViagemPainel;
import br.com.petrobras.sistam.desktop.agenciamento.panels.AnexosPainel;
import br.com.petrobras.sistam.desktop.agenciamento.panels.DocumentosPainel;
import br.com.petrobras.sistam.desktop.agenciamento.panels.InformacoesGeraisPainel;
import br.com.petrobras.sistam.desktop.agenciamento.panels.ManobrasPainel;
import br.com.petrobras.sistam.desktop.agenciamento.panels.OperacoesPainel;
import br.com.petrobras.sistam.desktop.agenciamento.panels.PendenciasPainel;
import br.com.petrobras.sistam.desktop.agenciamento.panels.TaxasPainel;
import br.com.petrobras.sistam.desktop.agenciamento.panels.VisitasPainel;
import br.com.petrobras.sistam.desktop.agenciamento.protecao.ServicoProtecaoFrame;
import br.com.petrobras.sistam.desktop.embarcacao.DetalheEmbarcacaoDialog;
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import org.apache.commons.beanutils.PropertyUtils;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;

public class AgenciamentoEmbarcacaoFrame extends SInternalFrame implements PropertyChangeListener{    
    private MenuComunicacaoCaptania menuComunicacaoCapitania;
    private MenuPoliciaFederal menuPoliciaFederal;
    private AgenciamentoEmbarcacaoModel model;
    
    public AgenciamentoEmbarcacaoFrame(Agenciamento agenciamento) {
        this.model = new AgenciamentoEmbarcacaoModel(agenciamento);
        initComponents();
        this.setTitle(agenciamento.toString());
        
        //Adiciona o frame como listener de alguns painéis
        manobrasPainel.addPropertyChangeListener(this);
        documentosPainel.addPropertyChangeListener(this);
        taxasPainel.addPropertyChangeListener(this);

        carregarDadosDoAgenciamento();
        aplicarEfeitoNoBotaoEmbarcacao();
        
        //Se o agenciamento estiver cancelado, coloca a tela em modo de visualizacao e deixa apenas o botao de cancelamento
        if (StatusEmbarcacao.CANCELADO.equals(agenciamento.getStatusEmbarcacao())){
            colocarEmModoVisualizacao();
            botaoCancelamento.setVisible(true);
        }
    }

    public AgenciamentoEmbarcacaoModel getModel() {
        return model;
    }

    public void salvar() {
        if (validarSaidaEmbarcacao()) {
            Agenciamento agenciamento = model.getAgenciamento();
            if (model.getAgenciamento().getEta() != null && agenciamento.getDataChegada() != null && !agenciamento.getDataChegada().after(agenciamento.getEta())) {
                if(SistamDateUtils.diasCorridos(truncateTime(agenciamento.getDataChegada(), null), truncateTime(agenciamento.getEta(),null), null) >= 5){
                    DialogMessages.info(this, SistamUtils.getResourceMessages().getString(ConstantesI18N.AGENCIAMENTO_DATA_CHEGADA_MAIOR_CINCODIAS_DATA_ETA));
                }
            }
            model.salvar();
            carregarDadosDoAgenciamento();
            SistamApp.getSistamApp().atualizarCaixaEntrada();
        }
    }
    
    public void abrirDialogEmbarcacao(){
        Embarcacao embarcacao = (Embarcacao)model.getAgenciamento().getEmbarcacao().clone();
        DetalheEmbarcacaoDialog dialog = new DetalheEmbarcacaoDialog(SistamApp.getSistamApp().getMainFrame(), embarcacao, false);
        dialog.setVisible(true);
        
        if (dialog.getModel().salvo()){
            model.getAgenciamento().setEmbarcacao(embarcacao);
            this.setTitle(model.getAgenciamento().toString());
        }
    }
            
    public void abrirLivrePratica() {
        boolean continuar = true;
        
        if (!model.jaPagouTaxaAnvisa()){
            continuar = DialogMessages.confirm(this, "A taxa de livre prática / ANVISA ainda não foi paga. Deseja continuar?");
        }
        
        if (continuar){
            model.validaLiberacaoAnvisa();
            salvar();
            LiberacaoAnvisaDialog dialog = new LiberacaoAnvisaDialog(SistamApp.getSistamApp().getMainFrame(), true);
            dialog.getModel().setAgenciamento(model.getAgenciamento());
            dialog.setVisible(true);
        }
    }

    public void abrirDesvioDeRota(){
        model.verificarPossibilidadeParaDesvioDeRota();
        salvar();
        Desvio desvio = model.obterDesvio();
        DesvioDialog dialog = new DesvioDialog(SistamApp.getSistamApp().getMainFrame(), desvio);
        
        if (model.emModoVisualizacao()){
            dialog.colocarEmModoVisualizacao();
        }
        
        dialog.setVisible(true);
        
        if (dialog.getModel().confirmou()){
            atualizarAgenciamento();
        }
    }
    
    public void abrirCancelamento(){
        model.verificarPossibilidadeParaCancelamento();
        CancelamentoAgenciamento cancelamento = model.obterCancelamento();
        CancelamentoDoAgenciamentoDialog dialog = new CancelamentoDoAgenciamentoDialog(SistamApp.getSistamApp().getMainFrame(), cancelamento);
        
        if (model.emModoVisualizacao()){
            dialog.colocarEmModoVisualizacao();
        }
        dialog.setVisible(true);
        
        if (dialog.getModel().cancelou()){
            atualizarAgenciamento();
        }
    }

    public void exibirMenuComunicacaoCapitania(ActionEvent evt){
        menuComunicacaoCapitania = new MenuComunicacaoCaptania(model.getAgenciamento());
        menuComunicacaoCapitania.addPropertyChangeListener(this);
        
        SButton button = (SButton) evt.getSource();
        menuComunicacaoCapitania.setVisible(true);
        menuComunicacaoCapitania.show(button.getParent(), button.getX() - 1, button.getY() - this.menuComunicacaoCapitania.getHeight() - 1);
    }
    
    public void exibirTermoResponsabilidadeReceita(){
        //Salva primeira as informações do agenciamento
        model.validarTermoDeResponsabilidadeReceita();    
        salvar();
        TermoDeResponsabilidadeReceitaDialog dialog = new TermoDeResponsabilidadeReceitaDialog(SistamApp.getSistamApp().getMainFrame(), model.getAgenciamento());
        dialog.setVisible(true);

    }
    
    public void exibirMunuPoliciaFederal(ActionEvent evt){
        menuPoliciaFederal = new MenuPoliciaFederal(model.getAgenciamento());
        menuPoliciaFederal.addPropertyChangeListener(this);
        
        SButton button = (SButton) evt.getSource();
        menuPoliciaFederal.setVisible(true);
        menuPoliciaFederal.show(button.getParent(), button.getX() - 1, button.getY() - this.menuPoliciaFederal.getHeight() - 1);
    }
    
    public void exibirServicosDeProtecao(){
        ServicoProtecaoFrame frame = (ServicoProtecaoFrame) SistamApp.getSistamApp().getMainFrame().openInternalFrame(ServicoProtecaoFrame.class, true);
        frame.setAgenciamento(model.getAgenciamento());
    }
    
    public void desabilitarAbas() {
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlInfViagem), false);
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlInfSanitarias), false);
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlManobras), false);
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlOperacoes), false);
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlTaxas), false);
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlDocumentos), false);
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlAnexos), false);
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlPendencias), false);
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlVisitas), false);

        actionLivrePratica.setEnabled(false);
        actionComunicarCapitania.setEnabled(false);
        actionReceita.setEnabled(false);
    }

  

    public void atualizarAgenciamento(){
        AssyncInvoker
                .create(model, "carregarAgenciamento")
                .schedule();
        carregarDadosDoAgenciamento();
        SistamApp.getSistamApp().atualizarCaixaEntrada();
    }
    
    public final void carregarDadosDoAgenciamento() {
        desabilitarAbas();

        carregarInformacoesGerais();
        
        AssyncInvoker
                .create(this, "carregarAgenciamentoViagem")
                .schedule();

        AssyncInvoker
                .create(this, "carregarAgenciamentoSanitaria")
                .schedule();

        AssyncInvoker
                .create(this, "carregarManobras")
                .settingLoadingIconOn(labelLocalizaçãoAtual)
                .schedule();

        AssyncInvoker
                .create(this, "carregarOperacoes")
                .schedule();

        AssyncInvoker
                .create(this, "carregarTaxas")
                .schedule();

        AssyncInvoker
                .create(this, "carregarDocumentos")
                .schedule();

        AssyncInvoker
                .create(this, "carregarAnexos")
                .schedule();

        AssyncInvoker
                .create(this, "carregarPendencias")
                .schedule();
        
        AssyncInvoker
                .create(this, "carregarVisitas")
                .schedule();

    }

    public void carregarInformacoesGerais(){
        informacoesGeraisPainel.setAgenciamento(model.getAgenciamento());
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlInfGeral), true);
    }
    
    public void carregarAgenciamentoViagem() {
        getModel().carregarAgenciamentoViagem();
        agenciamentoViagemPainel.setAgenciamento(getModel().getAgenciamento());
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlInfViagem), true);
        habilitarActions();
    }

    public void carregarAgenciamentoSanitaria() {
        getModel().carregarAgenciamentoSanitaria();
        agenciamentoSanitariaPainel.setAgenciamento(getModel().getAgenciamento());
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlInfSanitarias), true);
        habilitarActions();
    }

    public void carregarManobras() {
        getModel().carregarManobras();
        manobrasPainel.setAgenciamento(getModel().getAgenciamento());
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlManobras), true);
        habilitarActions();
    }

    public void carregarOperacoes() {
        getModel().carregarOperacoes();
        operacoesPainel.setAgenciamento(getModel().getAgenciamento());
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlOperacoes), true);
        habilitarActions();
    }
    
    public void carregarTaxas(){
        model.carregarTaxas();
        taxasPainel.setAgenciamento(model.getAgenciamento());
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlTaxas), true);
    }

    public void carregarDocumentos(){
        model.carregarDocumentos();
        documentosPainel.setAgenciamento(model.getAgenciamento());
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlDocumentos), true);
    }
    
    public void carregarAnexos(){
        model.carregarAnexos();
        anexosPainel.setAgenciamento(model.getAgenciamento());
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlAnexos), true);
    }
    
    public void carregarPendencias(){
        model.carregarPendencias();
        pendenciasPainel.setAgenciamento(model.getAgenciamento());
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlPendencias), true);
    }
    
    public void carregarVisitas(){
        model.carregarVisitas();
        visitasPainel.setAgenciamento(model.getAgenciamento());
        tabAbas.setEnabledAt(tabAbas.getComponentZOrder(pnlVisitas), true);
    }
    
    public final void colocarEmModoVisualizacao(){
        model.setEmModoVisualizacao(true);
        
        botaoComunicacaoCapitania.setVisible(false);
        botaoLiberacaoAnvisa.setVisible(false);
        botaoSalvar.setVisible(false);
        botaoServicoDeProtecao.setVisible(false);
        botaoReceitaFederal.setVisible(false);
        botaoCancelamento.setVisible(false);
        botaoDesvioDeRota.setVisible(false);
        botaoPoliciaFederal.setVisible(false);
        
        informacoesGeraisPainel.colocarEmModoVisualizacao();
        agenciamentoViagemPainel.colocarEmModoVisualizacao();
        agenciamentoSanitariaPainel.colocarEmModoVisualizacao();
        manobrasPainel.colocarEmModoVisualizacao();
        operacoesPainel.colocarEmModoVisualizacao();
        taxasPainel.colocarEmModoVisualizacao();
        documentosPainel.colocarEmModoVisualizacao();
        anexosPainel.colocarEmModoVisualizacao();
        pendenciasPainel.colocarEmModoVisualizacao();
        visitasPainel.colocarEmModoVisualizacao();
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        if (evt.getPropertyName().equals(ManobrasPainel.ATUALIZOU_AGENCIAMENTO)){
            atualizarAgenciamento();
        } 
        
        else if (evt.getPropertyName().equals(DocumentosPainel.PROP_DOCUMENTO_PROTOCOLADO)
                || evt.getPropertyName().equals(TaxasPainel.PROP_TAXA_ADICIONADA)
                || evt.getPropertyName().equals(TaxasPainel.PROP_TAXA_EXCLUIDA)) {
            
            AssyncInvoker.create(this, "carregarPendencias")
                         .schedule();

            SistamApp.getSistamApp().atualizarCaixaEntrada();
        }
        
        else if (evt.getPropertyName().equals(MenuComunicacaoCaptania.PROP_ITEM_MENU_CLICADO)){
            salvar();
        }
    }

    private void habilitarActions() {
        boolean viagemIsEnabled = tabAbas.isEnabledAt(tabAbas.getComponentZOrder(pnlInfViagem));
        boolean sanitariaIsEnabled = tabAbas.isEnabledAt(tabAbas.getComponentZOrder(pnlInfSanitarias));
        boolean manobrasIsEnabled = tabAbas.isEnabledAt(tabAbas.getComponentZOrder(pnlManobras));
        boolean operacoesIsEnabled = tabAbas.isEnabledAt(tabAbas.getComponentZOrder(pnlOperacoes));

        actionLivrePratica.setEnabled(viagemIsEnabled && sanitariaIsEnabled);
        actionReceita.setEnabled(viagemIsEnabled && sanitariaIsEnabled);        
        actionComunicarCapitania.setEnabled(viagemIsEnabled && sanitariaIsEnabled && manobrasIsEnabled);
        actionSalvar.setEnabled(viagemIsEnabled && sanitariaIsEnabled);
        actionDesviarRota.setEnabled(operacoesIsEnabled);
    }
      
    private Boolean validarSaidaEmbarcacao() {
        if (model.getAgenciamento().getDataSaida() != null && model.getAgenciamento().getStatusEmbarcacao() != StatusEmbarcacao.SAIDO && model.getAgenciamento().getStatusEmbarcacao() != StatusEmbarcacao.DESVIADO) {
            return DialogMessages.confirm(SistamApp.getApplication().getMainFrame(), "A data de saída foi informada, confirma a saída da embarcação?");
        }
        return true;
    }
    
    private void aplicarEfeitoNoBotaoEmbarcacao() {
        btnEmbarcacao.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnEmbarcacao.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btnEmbarcacao.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
            }
        });
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    @SuppressFBWarnings
    //CHECKSTYLE:OFF
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        actionSalvar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionLivrePratica = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionGerarDUV = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionComunicarCapitania = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionDesviarRota = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        br.com.petrobras.fcorp.swing.base.action.GenericAction actionEmbarcacao = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionCancelamento = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionReceita = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionServicoProtecao = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionPoliciaFederal = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        br.com.petrobras.fcorp.swing.components.SPanel pnlCabecalho = new br.com.petrobras.fcorp.swing.components.SPanel();
        br.com.petrobras.fcorp.swing.components.SLabel sLabel2 = new br.com.petrobras.fcorp.swing.components.SLabel();
        br.com.petrobras.fcorp.swing.components.SLabel sLabel3 = new br.com.petrobras.fcorp.swing.components.SLabel();
        br.com.petrobras.fcorp.swing.components.SLabel sLabel10 = new br.com.petrobras.fcorp.swing.components.SLabel();
        br.com.petrobras.fcorp.swing.components.SLabel sLabel12 = new br.com.petrobras.fcorp.swing.components.SLabel();
        labelLocalizaçãoAtual = new br.com.petrobras.fcorp.swing.components.SLabel();
        br.com.petrobras.fcorp.swing.components.SLabel sLabel21 = new br.com.petrobras.fcorp.swing.components.SLabel();
        br.com.petrobras.fcorp.swing.components.SPanel sPanel1 = new br.com.petrobras.fcorp.swing.components.SPanel();
        br.com.petrobras.fcorp.swing.components.SLabel sLabel4 = new br.com.petrobras.fcorp.swing.components.SLabel();
        btnEmbarcacao = new br.com.petrobras.fcorp.swing.components.SButton();
        br.com.petrobras.fcorp.swing.components.SLabel sLabel13 = new br.com.petrobras.fcorp.swing.components.SLabel();
        br.com.petrobras.fcorp.swing.components.SLabel sLabel11 = new br.com.petrobras.fcorp.swing.components.SLabel();
        tabAbas = new br.com.petrobras.fcorp.swing.components.STabbedPane();
        pnlInfGeral = new br.com.petrobras.fcorp.swing.components.SPanel();
        informacoesGeraisPainel = new br.com.petrobras.sistam.desktop.agenciamento.panels.InformacoesGeraisPainel();
        pnlInfViagem = new br.com.petrobras.fcorp.swing.components.SPanel();
        agenciamentoViagemPainel = new br.com.petrobras.sistam.desktop.agenciamento.panels.AgenciamentoViagemPainel();
        pnlInfSanitarias = new br.com.petrobras.fcorp.swing.components.SPanel();
        agenciamentoSanitariaPainel = new br.com.petrobras.sistam.desktop.agenciamento.panels.AgenciamentoSanitariaPainel();
        pnlManobras = new br.com.petrobras.fcorp.swing.components.SPanel();
        manobrasPainel = new br.com.petrobras.sistam.desktop.agenciamento.panels.ManobrasPainel();
        pnlOperacoes = new br.com.petrobras.fcorp.swing.components.SPanel();
        operacoesPainel = new br.com.petrobras.sistam.desktop.agenciamento.panels.OperacoesPainel();
        pnlTaxas = new br.com.petrobras.fcorp.swing.components.SPanel();
        taxasPainel = new br.com.petrobras.sistam.desktop.agenciamento.panels.TaxasPainel();
        pnlAnexos = new br.com.petrobras.fcorp.swing.components.SPanel();
        anexosPainel = new br.com.petrobras.sistam.desktop.agenciamento.panels.AnexosPainel();
        pnlDocumentos = new br.com.petrobras.fcorp.swing.components.SPanel();
        documentosPainel = new br.com.petrobras.sistam.desktop.agenciamento.panels.DocumentosPainel();
        pnlPendencias = new br.com.petrobras.fcorp.swing.components.SPanel();
        pendenciasPainel = new br.com.petrobras.sistam.desktop.agenciamento.panels.PendenciasPainel();
        pnlVisitas = new br.com.petrobras.fcorp.swing.components.SPanel();
        visitasPainel = new br.com.petrobras.sistam.desktop.agenciamento.panels.VisitasPainel();
        painelBotoes = new br.com.petrobras.fcorp.swing.components.SPanel();
        botaoSalvar = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoLiberacaoAnvisa = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoReceitaFederal = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoPoliciaFederal = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoComunicacaoCapitania = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoDesvioDeRota = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoCancelamento = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoServicoDeProtecao = new br.com.petrobras.fcorp.swing.components.SButton();

        actionSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/disk.png"))); // NOI18N
        actionSalvar.setMethodName("salvar");
        actionSalvar.setTarget(this);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("sistam-labels"); // NOI18N
        actionSalvar.setText(bundle.getString("salvar")); // NOI18N

        actionLivrePratica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        actionLivrePratica.setMethodName("abrirLivrePratica");
        actionLivrePratica.setTarget(this);
        actionLivrePratica.setText(bundle.getString("liberacaoAnvisa")); // NOI18N

        actionGerarDUV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/folder-up.png"))); // NOI18N
        actionGerarDUV.setText(bundle.getString("gerarDUV")); // NOI18N

        actionComunicarCapitania.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/divulgar.png"))); // NOI18N
        actionComunicarCapitania.setMethodName("exibirMenuComunicacaoCapitania");
        actionComunicarCapitania.setTarget(this);
        actionComunicarCapitania.setText(bundle.getString("comunicacaoCapitania")); // NOI18N

        actionDesviarRota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrow-branch-icon.png"))); // NOI18N
        actionDesviarRota.setMethodName("abrirDesvioDeRota");
        actionDesviarRota.setTarget(this);
        actionDesviarRota.setText("Desvio de Rota");
        actionDesviarRota.setToolTipText("Informa e consulta o desvio de rota da embarcação");

        actionEmbarcacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/listagem_busca.png"))); // NOI18N
        actionEmbarcacao.setMethodName("abrirDialogEmbarcacao");
        actionEmbarcacao.setTarget(this);
        actionEmbarcacao.setToolTipText("Consultar/Alterar dados da Embarcação");

        actionCancelamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/listagem_fechar.png"))); // NOI18N
        actionCancelamento.setMethodName("abrirCancelamento");
        actionCancelamento.setTarget(this);
        actionCancelamento.setText("Cancelamento");
        actionCancelamento.setToolTipText("Informa e consulta o cancelamento do agenciamento");

        actionReceita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/receita16X16.png"))); // NOI18N
        actionReceita.setMethodName("exibirTermoResponsabilidadeReceita");
        actionReceita.setTarget(this);
        actionReceita.setText("Receita Federal");

        actionServicoProtecao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/folder-home.png"))); // NOI18N
        actionServicoProtecao.setMethodName("exibirServicosDeProtecao");
        actionServicoProtecao.setTarget(this);
        actionServicoProtecao.setText("Serviço de Proteção");
        actionServicoProtecao.setToolTipText("Permite visualizar os serviços de proteção do agenciamento.");

        actionPoliciaFederal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/policiafederal.png"))); // NOI18N
        actionPoliciaFederal.setMethodName("exibirMunuPoliciaFederal");
        actionPoliciaFederal.setTarget(this);
        actionPoliciaFederal.setText("Receita Federal");

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        pnlCabecalho.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlCabecalho.setName("pnlCabecalho"); // NOI18N
        pnlCabecalho.setPreferredSize(new java.awt.Dimension(607, 30));

        sLabel2.setText(bundle.getString("lblSituacao")); // NOI18N
        sLabel2.setName("sLabel2"); // NOI18N

        sLabel3.setName("sLabel3"); // NOI18N
        sLabel3.setRequiredField(true);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.agenciamento.statusEmbarcacao.porExtenso}"), sLabel3, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        sLabel10.setText("Processo:");
        sLabel10.setName("sLabel10"); // NOI18N

        sLabel12.setName("sLabel12"); // NOI18N
        sLabel12.setRequiredField(true);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.agenciamento.numeroProcessoformatado}"), sLabel12, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        labelLocalizaçãoAtual.setText("Localização Atual:");
        labelLocalizaçãoAtual.setName("labelLocalizaçãoAtual"); // NOI18N

        sLabel21.setName("sLabel21"); // NOI18N
        sLabel21.setRequiredField(true);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.localizacaoAtual}"), sLabel21, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        sPanel1.setName("sPanel1"); // NOI18N

        sLabel4.setForeground(new java.awt.Color(0, 51, 0));
        sLabel4.setName("sLabel4"); // NOI18N
        sLabel4.setRequiredField(true);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.agenciamento.nomeComposto}"), sLabel4, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        btnEmbarcacao.setAction(actionEmbarcacao);
        btnEmbarcacao.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEmbarcacao.setContentAreaFilled(false);
        btnEmbarcacao.setFocusable(false);

        javax.swing.GroupLayout sPanel1Layout = new javax.swing.GroupLayout(sPanel1);
        sPanel1.setLayout(sPanel1Layout);
        sPanel1Layout.setHorizontalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sPanel1Layout.createSequentialGroup()
                .addComponent(btnEmbarcacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sPanel1Layout.setVerticalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnEmbarcacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        sLabel13.setName("sLabel13"); // NOI18N
        sLabel13.setRequiredField(true);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.agenciamento.dataInclusaoProcessoFormatado}"), sLabel13, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        sLabel11.setText("-");
        sLabel11.setName("sLabel11"); // NOI18N

        javax.swing.GroupLayout pnlCabecalhoLayout = new javax.swing.GroupLayout(pnlCabecalho);
        pnlCabecalho.setLayout(pnlCabecalhoLayout);
        pnlCabecalhoLayout.setHorizontalGroup(
            pnlCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabecalhoLayout.createSequentialGroup()
                .addComponent(sPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(sLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(sLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelLocalizaçãoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(sLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        pnlCabecalhoLayout.setVerticalGroup(
            pnlCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabecalhoLayout.createSequentialGroup()
                .addGroup(pnlCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(sPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLocalizaçãoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabAbas.setName(bundle.getString("informacaoViagem")); // NOI18N

        pnlInfGeral.setName("pnlInfGeral"); // NOI18N

        informacoesGeraisPainel.setName("informacoesGeraisPainel"); // NOI18N

        javax.swing.GroupLayout pnlInfGeralLayout = new javax.swing.GroupLayout(pnlInfGeral);
        pnlInfGeral.setLayout(pnlInfGeralLayout);
        pnlInfGeralLayout.setHorizontalGroup(
            pnlInfGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfGeralLayout.createSequentialGroup()
                .addComponent(informacoesGeraisPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 288, Short.MAX_VALUE))
        );
        pnlInfGeralLayout.setVerticalGroup(
            pnlInfGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfGeralLayout.createSequentialGroup()
                .addComponent(informacoesGeraisPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 68, Short.MAX_VALUE))
        );

        tabAbas.addTab(bundle.getString("informacaoGeral"), pnlInfGeral); // NOI18N

        pnlInfViagem.setName("pnlInfViagem"); // NOI18N
        pnlInfViagem.setPreferredSize(new java.awt.Dimension(740, 598));

        agenciamentoViagemPainel.setName("agenciamentoViagemPainel"); // NOI18N

        javax.swing.GroupLayout pnlInfViagemLayout = new javax.swing.GroupLayout(pnlInfViagem);
        pnlInfViagem.setLayout(pnlInfViagemLayout);
        pnlInfViagemLayout.setHorizontalGroup(
            pnlInfViagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(agenciamentoViagemPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 1102, Short.MAX_VALUE)
        );
        pnlInfViagemLayout.setVerticalGroup(
            pnlInfViagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfViagemLayout.createSequentialGroup()
                .addComponent(agenciamentoViagemPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
        );

        tabAbas.addTab(bundle.getString("informacaoViagem"), pnlInfViagem); // NOI18N

        pnlInfSanitarias.setName("pnlInfSanitarias"); // NOI18N

        agenciamentoSanitariaPainel.setName("agenciamentoSanitariaPainel"); // NOI18N

        javax.swing.GroupLayout pnlInfSanitariasLayout = new javax.swing.GroupLayout(pnlInfSanitarias);
        pnlInfSanitarias.setLayout(pnlInfSanitariasLayout);
        pnlInfSanitariasLayout.setHorizontalGroup(
            pnlInfSanitariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfSanitariasLayout.createSequentialGroup()
                .addComponent(agenciamentoSanitariaPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 338, Short.MAX_VALUE))
        );
        pnlInfSanitariasLayout.setVerticalGroup(
            pnlInfSanitariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfSanitariasLayout.createSequentialGroup()
                .addComponent(agenciamentoSanitariaPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 92, Short.MAX_VALUE))
        );

        tabAbas.addTab(bundle.getString("informacaoSanitaria"), pnlInfSanitarias); // NOI18N

        pnlManobras.setName("pnlManobras"); // NOI18N

        manobrasPainel.setName("manobrasPainel"); // NOI18N

        javax.swing.GroupLayout pnlManobrasLayout = new javax.swing.GroupLayout(pnlManobras);
        pnlManobras.setLayout(pnlManobrasLayout);
        pnlManobrasLayout.setHorizontalGroup(
            pnlManobrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(manobrasPainel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1102, Short.MAX_VALUE)
        );
        pnlManobrasLayout.setVerticalGroup(
            pnlManobrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(manobrasPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );

        tabAbas.addTab("Manobras", pnlManobras);

        pnlOperacoes.setName("pnlOperacoes"); // NOI18N

        operacoesPainel.setName("operacoesPainel"); // NOI18N

        javax.swing.GroupLayout pnlOperacoesLayout = new javax.swing.GroupLayout(pnlOperacoes);
        pnlOperacoes.setLayout(pnlOperacoesLayout);
        pnlOperacoesLayout.setHorizontalGroup(
            pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(operacoesPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 1102, Short.MAX_VALUE)
        );
        pnlOperacoesLayout.setVerticalGroup(
            pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(operacoesPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );

        tabAbas.addTab(bundle.getString("operacoes"), pnlOperacoes); // NOI18N
        pnlOperacoes.getAccessibleContext().setAccessibleName("");

        pnlTaxas.setName("pnlTaxas"); // NOI18N

        taxasPainel.setName("taxasPainel"); // NOI18N

        javax.swing.GroupLayout pnlTaxasLayout = new javax.swing.GroupLayout(pnlTaxas);
        pnlTaxas.setLayout(pnlTaxasLayout);
        pnlTaxasLayout.setHorizontalGroup(
            pnlTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taxasPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 1102, Short.MAX_VALUE)
        );
        pnlTaxasLayout.setVerticalGroup(
            pnlTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taxasPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );

        tabAbas.addTab("Taxas", pnlTaxas);

        pnlAnexos.setName("pnlAnexos"); // NOI18N

        anexosPainel.setName("anexosPainel"); // NOI18N

        javax.swing.GroupLayout pnlAnexosLayout = new javax.swing.GroupLayout(pnlAnexos);
        pnlAnexos.setLayout(pnlAnexosLayout);
        pnlAnexosLayout.setHorizontalGroup(
            pnlAnexosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnexosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anexosPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlAnexosLayout.setVerticalGroup(
            pnlAnexosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnexosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anexosPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabAbas.addTab("Anexos", pnlAnexos);

        pnlDocumentos.setName("pnlDocumentos"); // NOI18N

        documentosPainel.setName("documentosPainel"); // NOI18N

        javax.swing.GroupLayout pnlDocumentosLayout = new javax.swing.GroupLayout(pnlDocumentos);
        pnlDocumentos.setLayout(pnlDocumentosLayout);
        pnlDocumentosLayout.setHorizontalGroup(
            pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1102, Short.MAX_VALUE)
            .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDocumentosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(documentosPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlDocumentosLayout.setVerticalGroup(
            pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
            .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDocumentosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(documentosPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        tabAbas.addTab("Documentos", pnlDocumentos);

        pnlPendencias.setName("pnlPendencias"); // NOI18N

        pendenciasPainel.setName("pendenciasPainel"); // NOI18N

        javax.swing.GroupLayout pnlPendenciasLayout = new javax.swing.GroupLayout(pnlPendencias);
        pnlPendencias.setLayout(pnlPendenciasLayout);
        pnlPendenciasLayout.setHorizontalGroup(
            pnlPendenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pendenciasPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 1102, Short.MAX_VALUE)
        );
        pnlPendenciasLayout.setVerticalGroup(
            pnlPendenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendenciasLayout.createSequentialGroup()
                .addComponent(pendenciasPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabAbas.addTab("Pendências", pnlPendencias);

        pnlVisitas.setName("pnlVisitas"); // NOI18N

        visitasPainel.setName("visitasPainel"); // NOI18N

        javax.swing.GroupLayout pnlVisitasLayout = new javax.swing.GroupLayout(pnlVisitas);
        pnlVisitas.setLayout(pnlVisitasLayout);
        pnlVisitasLayout.setHorizontalGroup(
            pnlVisitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(visitasPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 1102, Short.MAX_VALUE)
        );
        pnlVisitasLayout.setVerticalGroup(
            pnlVisitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(visitasPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );

        tabAbas.addTab("Visitas", pnlVisitas);

        painelBotoes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelBotoes.setName("painelBotoes"); // NOI18N

        botaoSalvar.setAction(actionSalvar);
        botaoSalvar.setName("botaoSalvar"); // NOI18N
        painelBotoes.add(botaoSalvar);

        botaoLiberacaoAnvisa.setAction(actionLivrePratica);
        botaoLiberacaoAnvisa.setName("botaoLiberacaoAnvisa"); // NOI18N
        painelBotoes.add(botaoLiberacaoAnvisa);

        botaoReceitaFederal.setAction(actionReceita);
        botaoReceitaFederal.setName("botaoReceitaFederal"); // NOI18N
        painelBotoes.add(botaoReceitaFederal);

        botaoPoliciaFederal.setAction(actionPoliciaFederal);
        botaoPoliciaFederal.setText("Polícia Federal");
        botaoPoliciaFederal.setToolTipText("Polícia Federal");
        botaoPoliciaFederal.setName("botaoPoliciaFederal"); // NOI18N
        painelBotoes.add(botaoPoliciaFederal);

        botaoComunicacaoCapitania.setAction(actionComunicarCapitania);
        botaoComunicacaoCapitania.setName("botaoComunicacaoCapitania"); // NOI18N
        painelBotoes.add(botaoComunicacaoCapitania);

        botaoDesvioDeRota.setAction(actionDesviarRota);
        botaoDesvioDeRota.setName("botaoDesvioDeRota"); // NOI18N
        painelBotoes.add(botaoDesvioDeRota);

        botaoCancelamento.setAction(actionCancelamento);
        botaoCancelamento.setName("botaoCancelamento"); // NOI18N
        painelBotoes.add(botaoCancelamento);

        botaoServicoDeProtecao.setAction(actionServicoProtecao);
        botaoServicoDeProtecao.setName("botaoServicoDeProtecao"); // NOI18N
        painelBotoes.add(botaoServicoDeProtecao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCabecalho, javax.swing.GroupLayout.DEFAULT_SIZE, 1107, Short.MAX_VALUE)
            .addComponent(tabAbas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCabecalho, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabAbas, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabAbas.getAccessibleContext().setAccessibleName(bundle.getString("informacaoViagem")); // NOI18N

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        if (! model.emModoVisualizacao()
                && DialogMessages.confirm(SistamApp.getApplication().getMainFrame(), "Deseja salvar possíveis alterações antes de sair?")) {
            model.salvar();
        }
    }//GEN-LAST:event_formInternalFrameClosing
    //CHECKSTYLE:ON
    // Variables declaration - do not modify//GEN-BEGIN:variables
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionCancelamento;
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionComunicarCapitania;
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionDesviarRota;
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionGerarDUV;
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionLivrePratica;
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionPoliciaFederal;
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionReceita;
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionSalvar;
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionServicoProtecao;
    br.com.petrobras.sistam.desktop.agenciamento.panels.AgenciamentoSanitariaPainel agenciamentoSanitariaPainel;
    br.com.petrobras.sistam.desktop.agenciamento.panels.AgenciamentoViagemPainel agenciamentoViagemPainel;
    br.com.petrobras.sistam.desktop.agenciamento.panels.AnexosPainel anexosPainel;
    br.com.petrobras.fcorp.swing.components.SButton botaoCancelamento;
    br.com.petrobras.fcorp.swing.components.SButton botaoComunicacaoCapitania;
    br.com.petrobras.fcorp.swing.components.SButton botaoDesvioDeRota;
    br.com.petrobras.fcorp.swing.components.SButton botaoLiberacaoAnvisa;
    br.com.petrobras.fcorp.swing.components.SButton botaoPoliciaFederal;
    br.com.petrobras.fcorp.swing.components.SButton botaoReceitaFederal;
    br.com.petrobras.fcorp.swing.components.SButton botaoSalvar;
    br.com.petrobras.fcorp.swing.components.SButton botaoServicoDeProtecao;
    br.com.petrobras.fcorp.swing.components.SButton btnEmbarcacao;
    br.com.petrobras.sistam.desktop.agenciamento.panels.DocumentosPainel documentosPainel;
    br.com.petrobras.sistam.desktop.agenciamento.panels.InformacoesGeraisPainel informacoesGeraisPainel;
    br.com.petrobras.fcorp.swing.components.SLabel labelLocalizaçãoAtual;
    br.com.petrobras.sistam.desktop.agenciamento.panels.ManobrasPainel manobrasPainel;
    br.com.petrobras.sistam.desktop.agenciamento.panels.OperacoesPainel operacoesPainel;
    br.com.petrobras.fcorp.swing.components.SPanel painelBotoes;
    br.com.petrobras.sistam.desktop.agenciamento.panels.PendenciasPainel pendenciasPainel;
    br.com.petrobras.fcorp.swing.components.SPanel pnlAnexos;
    br.com.petrobras.fcorp.swing.components.SPanel pnlDocumentos;
    br.com.petrobras.fcorp.swing.components.SPanel pnlInfGeral;
    br.com.petrobras.fcorp.swing.components.SPanel pnlInfSanitarias;
    br.com.petrobras.fcorp.swing.components.SPanel pnlInfViagem;
    br.com.petrobras.fcorp.swing.components.SPanel pnlManobras;
    br.com.petrobras.fcorp.swing.components.SPanel pnlOperacoes;
    br.com.petrobras.fcorp.swing.components.SPanel pnlPendencias;
    br.com.petrobras.fcorp.swing.components.SPanel pnlTaxas;
    br.com.petrobras.fcorp.swing.components.SPanel pnlVisitas;
    br.com.petrobras.fcorp.swing.components.STabbedPane tabAbas;
    br.com.petrobras.sistam.desktop.agenciamento.panels.TaxasPainel taxasPainel;
    br.com.petrobras.sistam.desktop.agenciamento.panels.VisitasPainel visitasPainel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
