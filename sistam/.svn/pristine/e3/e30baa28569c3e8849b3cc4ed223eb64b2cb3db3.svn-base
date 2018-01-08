package br.com.petrobras.sistam.desktop;

import br.com.petrobras.fcorp.swing.base.SFrame;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SDesktop;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.SStatusPanel;
import br.com.petrobras.fcorp.swing.components.activators.SActivator;
import br.com.petrobras.fcorp.swing.components.activators.SActivatorBar;
import br.com.petrobras.fcorp.swing.exceptionhandler.SwingExceptionHandlerManager;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.RecursoCA;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.agencia.CadastroAgenciaFrame;
import br.com.petrobras.sistam.desktop.agenciamento.AgenciamentoIniciarDialog;
import br.com.petrobras.sistam.desktop.agenciamento.ConsultaAgenciamentoFrame;
import br.com.petrobras.sistam.desktop.caixaentrada.FiltroCaixaDeEntradadPainel;
import br.com.petrobras.sistam.desktop.caixaentrada.ListagemAgenciamentoPanel;
import br.com.petrobras.sistam.desktop.caixaentrada.ListagemAgenciamentoPanelListener;
import br.com.petrobras.sistam.desktop.components.PropriedadesPainel;
import br.com.petrobras.sistam.desktop.embarcacao.EmbarcacoesFrame;
import br.com.petrobras.sistam.desktop.empresa.EmpresaFrame;
import br.com.petrobras.sistam.desktop.exception.SistamPendingExceptionHandler;
import br.com.petrobras.sistam.desktop.pontoatracacao.PontoAtracacaoFrame;
import br.com.petrobras.sistam.desktop.porto.PortoFrame;
import br.com.petrobras.sistam.desktop.relatorio.RelatorioAgenciamentoAtendimentoDialog;
import br.com.petrobras.sistam.desktop.relatorio.RelatorioControlePagamentoTaxaDialog;
import br.com.petrobras.sistam.desktop.relatorio.RelatorioManobraDialog;
import br.com.petrobras.sistam.desktop.relatorio.RelatorioMovimentacaoEmbarcacaoDialog;
import br.com.petrobras.sistam.desktop.relatorio.RelatorioProdutividadeAgenciamentoDialog;
import br.com.petrobras.sistam.desktop.relatorio.RelatorioServicoSuprimentoAosNaviosDialog;
import br.com.petrobras.sistam.desktop.relatorio.RelatorioSiscomexDialog;
import br.com.petrobras.sistam.desktop.relatorio.RelatorioTaxaDialog;
import br.com.petrobras.sistam.desktop.relatorio.RelatorioTimesheetDialog;
import br.com.petrobras.sistam.desktop.relatorio.RelatorioVisitaDialog;
import br.com.petrobras.sistam.desktop.servicoprotecao.EmpresaProtecaoFrame;
import br.com.petrobras.sistam.desktop.servicoprotecao.PessoaProtecaoFrame;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 * Tela principal do sistema.
 */
public class SistamFrame extends SFrame implements PropertyChangeListener  {

    private static final int POSICAO_ESCONDIDA = 0;
    private SistamFrameModel model = new SistamFrameModel();
    
    private FiltroCaixaDeEntradadPainel filtroCaixaDeEntrada;
    private ListagemAgenciamentoPanel caixaEntrada;
    
    private transient ListagemAgenciamentoPanelListener caixaDeEntradaListener = new ListagemAgenciamentoPanelListener() {
        @Override
        public void esconderListagem(ListagemAgenciamentoPanel listagem) {
            SistamFrame.this.esconderListagem();
        }

        @Override
        public void selecionarEmbarcacao(Agenciamento agenciamento) {
            preencherPainelDePropriedades(agenciamento);
        }
    };

     /**
     * Construtor padrão
     */
    public SistamFrame() {
        initComponents();
        configurarComponentes();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public SistamFrameModel getModel() {
        return model;
    }
    
    public ListagemAgenciamentoPanel getCaixaEntrada() {
        return caixaEntrada;
    }
    
    public SActivatorBar getBarraFramesAbertos() {
        return barraFramesAbertos;
    }
    
    @Override
    public SActivatorBar getOpenedFrames() {
        return barraFramesAbertos;
    }
    
    @Override
    public SStatusPanel getStatusPanel() {
        return statusPanel;
    }
    
    @Override
    public SDesktop getDesktopPane() {
        return desktop;
    }
    
    //</editor-fold>
    
    public void novoAgenciamento() {
        AgenciamentoIniciarDialog dialog = new AgenciamentoIniciarDialog(SistamApp.getSistamApp().getMainFrame(), true);
        dialog.setVisible(true);
    }

    public void cadastrarEmbarcacao() {
        SistamApp.getSistamApp().getMainFrame().openInternalFrame(EmbarcacoesFrame.class, true);
    }

    public void consultarAgenciamento() {
        SistamApp.getSistamApp().getMainFrame().openInternalFrame(ConsultaAgenciamentoFrame.class, true);
    }

    public void abrirRelatorioTaxa() {
        RelatorioTaxaDialog dialog = new RelatorioTaxaDialog(SistamApp.getSistamApp().getMainFrame(), true);
        dialog.setVisible(true);
    }

    public void abrirRelatorioControlePagamentoTaxa() {
        RelatorioControlePagamentoTaxaDialog dialog = new RelatorioControlePagamentoTaxaDialog(SistamApp.getSistamApp().getMainFrame(), true);
        dialog.setVisible(true);
    }

    public void abrirRelatorioProdutividadeAgenciamento() {
        RelatorioProdutividadeAgenciamentoDialog dialog = new RelatorioProdutividadeAgenciamentoDialog(SistamApp.getSistamApp().getMainFrame(), true);
        dialog.setVisible(true);
    }
    
    public void abrirRelatorioMovimentacaoEmbarcacoes() {
        RelatorioMovimentacaoEmbarcacaoDialog dialog = new RelatorioMovimentacaoEmbarcacaoDialog(SistamApp.getSistamApp().getMainFrame());
        dialog.setVisible(true);
    }
    
    public void abrirRelatorioAtendimentoEmbarcacao() {
        RelatorioAgenciamentoAtendimentoDialog dialog = new RelatorioAgenciamentoAtendimentoDialog(SistamApp.getSistamApp().getMainFrame(), true);
        dialog.setVisible(true);
    }
    
    public void abrirRelatorioManobra() {
        RelatorioManobraDialog dialog = new RelatorioManobraDialog(SistamApp.getSistamApp().getMainFrame(), true);
        dialog.setVisible(true);
    }

    public void abrirFrameEmpresas() {
        SistamApp.getSistamApp().getMainFrame().openInternalFrame(EmpresaFrame.class, true);
    }

    public void abrirFrameAgencia() {
        SistamApp.getSistamApp().getMainFrame().openInternalFrame(CadastroAgenciaFrame.class, true);
    }
    
    public void abrirFramePontoAtracacao(){
        SistamApp.getSistamApp().getMainFrame().openInternalFrame(PontoAtracacaoFrame.class, true);
    }
    
    public void abrirFramePortos(){
        SistamApp.getSistamApp().getMainFrame().openInternalFrame(PortoFrame.class, true);
    }
    
    public void abrirRelatorioTimesheet(){
        RelatorioTimesheetDialog dialog = new RelatorioTimesheetDialog(SistamApp.getSistamApp().getMainFrame(), true);
        dialog.setVisible(true);
    }
    
    public void abrirRelatorioVisita(){
        RelatorioVisitaDialog dialog = new RelatorioVisitaDialog(SistamApp.getSistamApp().getMainFrame(), true);
        dialog.setVisible(true);
    }
    
    public void abrirRelatorioSiscomex(){
        RelatorioSiscomexDialog dialog = new RelatorioSiscomexDialog(SistamApp.getSistamApp().getMainFrame(), true);
        dialog.setVisible(true);
    }
    
    public void abrirRelatorioServicoSuprimentoNavio(){
        RelatorioServicoSuprimentoAosNaviosDialog dialog = new RelatorioServicoSuprimentoAosNaviosDialog(SistamApp.getSistamApp().getMainFrame(), true);
        dialog.setVisible(true);
    }
    
    public void abrirFrameProtecaoEmpresas(){
        SistamApp.getSistamApp().getMainFrame().openInternalFrame(EmpresaProtecaoFrame.class, true);
    }
    
    public void abrirFrameProtecaoPessoas(){
        SistamApp.getSistamApp().getMainFrame().openInternalFrame(PessoaProtecaoFrame.class, true);
    }
    
    
    public void abrePrototipo4() {
    }
    
    /**
     * Cria a caixa de entrada e exibe na tela.
     */
    @Override
    public void doAfterLogin() {
        SwingExceptionHandlerManager.getHandlerManager().getHandlerList().add(new SistamPendingExceptionHandler());
        atualizarActions();
        this.exibirCaixaDeEntrada();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource().equals(barraFramesAbertos) && evt.getPropertyName().equals("selectedActivator")) {
            if (evt.getOldValue() != null) {
                ((SActivator) evt.getOldValue()).setBorder(SistamTheme.frameInativoBorder);
                ((SActivator) evt.getOldValue()).setBackground(SistamTheme.frameInativoBackground);
                ((SActivator) evt.getOldValue()).setForeground(UIManager.getColor("Label.foreground"));
            }
            if (evt.getNewValue() != null) {
                ((SActivator) evt.getNewValue()).setBorder(SistamTheme.frameAtivoBorder);
                ((SActivator) evt.getNewValue()).setBackground(SistamTheme.frameAtivoBackground);
                ((SActivator) evt.getNewValue()).setForeground(Color.WHITE);
            }
        }
        
        if (evt.getPropertyName().equals(FiltroCaixaDeEntradadPainel.PROP_AGENCIA_SELECIONADA)){
            Agencia agenciaSelecionada = filtroCaixaDeEntrada.getAgenciaSelecionada();
            
            List<Porto> listaDePortos = new ArrayList<Porto>();
            if (agenciaSelecionada != null){
                listaDePortos = SistamApp.getSistamApp().getService().buscarPortosPorAgencia(agenciaSelecionada);
                listaDePortos.add(0, null);
            }
            
            filtroCaixaDeEntrada.setPortos(listaDePortos);
        }
        
        if (evt.getPropertyName().equals(FiltroCaixaDeEntradadPainel.PROP_PORTO_SELECIONADO)){
            caixaEntrada.atualizarCaixa();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Métodos sobre a Caixa de Entrada">
    private void exibirCaixaDeEntrada() {
        caixaEntrada = new ListagemAgenciamentoPanel(this.model);
        caixaEntrada.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/caixa_entrada.png")));
        caixaEntrada.setTitle("Agenciamento da Carga");
        caixaEntrada.adicionarListener(caixaDeEntradaListener);
        caixaEntrada.setFiltro(filtroCaixaDeEntrada);
        
        filtroCaixaDeEntrada = new FiltroCaixaDeEntradadPainel();
        caixaEntrada.setFiltro(filtroCaixaDeEntrada);
        
        filtroCaixaDeEntrada.addPropertyChangeListener(this);
        SistamCredentialsBean contexto = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        filtroCaixaDeEntrada.setAgencias(contexto.getAgenciasAutorizadas());
        
        
        this.painelEsquerdo.add(filtroCaixaDeEntrada, BorderLayout.NORTH);
        this.painelEsquerdo.add(caixaEntrada, BorderLayout.CENTER);
        this.painelEsquerdo.revalidate();
        this.painelEsquerdo.repaint();
        this.exibirListagemEscondida();
        caixaEntrada.requestFocusInWindow();
    }

    private void esconderListagem() {
        this.splitCentral.setDividerLocation(POSICAO_ESCONDIDA);
    }

    private void exibirListagemEscondida() {
        if (splitCentral.getDividerLocation() == POSICAO_ESCONDIDA) {
            splitCentral.setDividerLocation(splitCentral.getLastDividerLocation());
        }
    }
    
    //</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    @SuppressFBWarnings
    // CHECKSTYLE:OFF
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        actionNovoAgenciamento = new GenericAction();
        actionConsultarAgenciamento = new GenericAction();
        actionRelatorioTaxa = new GenericAction();
        actionRelatorioProdutividade = new GenericAction();
        actionRelatorioControlePagamentoTaxa = new GenericAction();
        actionEmbarcacao = new GenericAction();
        actionEmpresasServicos = new GenericAction();
        actionAgencia = new GenericAction();
        actionPontosDeAtracacao = new GenericAction();
        actionPortos = new GenericAction();
        actionMovimentacaoEmbarcacao = new GenericAction();
        actionRelatorioAtendimentoEmbarcacao = new GenericAction();
        actionRelatorioManobra = new GenericAction();
        actionRelatorioTimesheet = new GenericAction();
        actionRelatorioVisita = new GenericAction();
        actionProtecaoEmpresa = new GenericAction();
        actionProtecaoPessoa = new GenericAction();
        actionRelatorioSiscomex = new GenericAction();
        actionRelatorioServicoSuprimentoNavio = new GenericAction();
        splitCentral = new JSplitPane();
        splitEsquerdo = new JSplitPane();
        painelEsquerdo = new SPanel();
        painelPropriedades = new PropriedadesPainel();
        SPanel painelDireito = new SPanel();
        desktop = new SDesktop();
        barraFramesAbertos = new SActivatorBar();
        statusPanel = new SStatusPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu menuAgenciamentoCarga = new JMenu();
        JMenuItem menuAgenciamentoProtecao = new JMenuItem();
        JPopupMenu.Separator jSeparator1 = new JPopupMenu.Separator();
        JMenuItem menuConsultarAgenciamento = new JMenuItem();
        JMenu menuRelatorio = new JMenu();
        JMenuItem Produtividade = new JMenuItem();
        JMenuItem menuProdutividadeAtendimentorealizado = new JMenuItem();
        JMenuItem menuRelatorioManobras = new JMenuItem();
        JMenuItem menuRelatorioTimeSheet = new JMenuItem();
        JMenuItem menuRelatorioVisita = new JMenuItem();
        JMenuItem jMenuItem6 = new JMenuItem();
        JMenuItem jMenuItem7 = new JMenuItem();
        JPopupMenu.Separator jSeparator2 = new JPopupMenu.Separator();
        JMenuItem menuTaxa = new JMenuItem();
        JMenuItem menuControlePagamento = new JMenuItem();
        JPopupMenu.Separator jSeparator3 = new JPopupMenu.Separator();
        JMenuItem menuMovimentacaoEmbarcacaoResumido = new JMenuItem();
        JMenu menuEmbarcacao = new JMenu();
        JMenuItem jMenuItem1 = new JMenuItem();
        JMenu menuAdministracao = new JMenu();
        JMenu jMenu1 = new JMenu();
        JMenuItem jMenuItem4 = new JMenuItem();
        JMenuItem jMenuItem5 = new JMenuItem();
        JMenuItem menuEmpresa = new JMenuItem();
        JMenuItem menuAgencia = new JMenuItem();
        JMenuItem jMenuItem2 = new JMenuItem();
        JMenuItem jMenuItem3 = new JMenuItem();
        JMenu menuSobre = new JMenu();
        JMenuItem itemConteudoAjuda = new JMenuItem();
        JSeparator separator1 = new JSeparator();
        JMenuItem itemSobre = new JMenuItem();

        actionNovoAgenciamento.setIcon(new ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionNovoAgenciamento.setMethodName("novoAgenciamento");
        actionNovoAgenciamento.setNeededSecurityResources(new String[] {"SALVAR_AGENCIAMENTO"});
        actionNovoAgenciamento.setTarget(this);
        ResourceBundle bundle = ResourceBundle.getBundle("sistam-labels"); // NOI18N
        actionNovoAgenciamento.setText(bundle.getString("Exemplo_Componentes")); // NOI18N
        actionNovoAgenciamento.setToolTipText("Novo agenciamento");

        actionConsultarAgenciamento.setIcon(new ImageIcon(getClass().getResource("/icons/find.png"))); // NOI18N
        actionConsultarAgenciamento.setMethodName("consultarAgenciamento");
        actionConsultarAgenciamento.setNeededSecurityResources(new String[] {"CONSULTAR_AGENCIAMENTO"});
        actionConsultarAgenciamento.setTarget(this);
        actionConsultarAgenciamento.setText("Consultar");
        actionConsultarAgenciamento.setToolTipText("Consulta Por Filtro");

        actionRelatorioTaxa.setIcon(new ImageIcon(getClass().getResource("/icons/coins.png"))); // NOI18N
        actionRelatorioTaxa.setMethodName("abrirRelatorioTaxa");
        actionRelatorioTaxa.setNeededSecurityResources(new String[] {"RELATORIO_TAXAS"});
        actionRelatorioTaxa.setTarget(this);
        actionRelatorioTaxa.setText("Taxas Portuárias");

        actionRelatorioProdutividade.setIcon(new ImageIcon(getClass().getResource("/icons/tarefa.png"))); // NOI18N
        actionRelatorioProdutividade.setMethodName("abrirRelatorioProdutividadeAgenciamento");
        actionRelatorioProdutividade.setNeededSecurityResources(new String[] {"RELATORIO_PRODUTIVIDADE"});
        actionRelatorioProdutividade.setTarget(this);
        actionRelatorioProdutividade.setText("Produtividade/Embarcações Agenciadas");

        actionRelatorioControlePagamentoTaxa.setIcon(new ImageIcon(getClass().getResource("/icons/coins.png"))); // NOI18N
        actionRelatorioControlePagamentoTaxa.setMethodName("abrirRelatorioControlePagamentoTaxa");
        actionRelatorioControlePagamentoTaxa.setNeededSecurityResources(new String[] {"RELATORIO_TAXAS"});
        actionRelatorioControlePagamentoTaxa.setTarget(this);
        actionRelatorioControlePagamentoTaxa.setText("Controle de Pagamento de Taxas");
        actionRelatorioControlePagamentoTaxa.setToolTipText("Controle de Pagamento de Taxas Portuárias");

        actionEmbarcacao.setMethodName("cadastrarEmbarcacao");
        actionEmbarcacao.setNeededSecurityResources(new String[] {"SALVAR_EMBARCACAO"});
        actionEmbarcacao.setTarget(this);
        actionEmbarcacao.setText("Consultar/Editar");
        actionEmbarcacao.setToolTipText("Consultar e/ou Editar  Embarcações");

        actionEmpresasServicos.setIcon(new ImageIcon(getClass().getResource("/icons/building.png"))); // NOI18N
        actionEmpresasServicos.setMethodName("abrirFrameEmpresas");
        actionEmpresasServicos.setNeededSecurityResources(new String[] {"SALVAR_EMPRESA", "ADMINISTRACAO"});
        actionEmpresasServicos.setTarget(this);
        actionEmpresasServicos.setText("Empresas e Serviços");
        actionEmpresasServicos.setToolTipText("Permite consultar e cadastrar empresas e serviços");

        actionAgencia.setIcon(new ImageIcon(getClass().getResource("/icons/building.png"))); // NOI18N
        actionAgencia.setMethodName("abrirFrameAgencia");
        actionAgencia.setNeededSecurityResources(new String[] {"SALVAR_AGENCIA", "ADMINISTRACAO"});
        actionAgencia.setTarget(this);
        actionAgencia.setText("Dados da Agencia");
        actionAgencia.setToolTipText("Permite consultar e editar dados das agências");

        actionPontosDeAtracacao.setIcon(new ImageIcon(getClass().getResource("/icons/anchor.png"))); // NOI18N
        actionPontosDeAtracacao.setMethodName("abrirFramePontoAtracacao");
        actionPontosDeAtracacao.setNeededSecurityResources(new String[] {"SALVAR_PORTO", "ADMINISTRACAO"});
        actionPontosDeAtracacao.setTarget(this);
        actionPontosDeAtracacao.setText("Pontos de Atracação");
        actionPontosDeAtracacao.setToolTipText("Permite consultar, adicionar e editar pontos de atracação");

        actionPortos.setIcon(new ImageIcon(getClass().getResource("/icons/anchor.png"))); // NOI18N
        actionPortos.setMethodName("abrirFramePortos");
        actionPortos.setNeededSecurityResources(new String[] {"SALVAR_PORTO", "SALVAR_PORTO_COMPLEMENTO", "ADMINISTRACAO"});
        actionPortos.setTarget(this);
        actionPortos.setText("Portos");

        actionMovimentacaoEmbarcacao.setIcon(new ImageIcon(getClass().getResource("/icons/16x16navio.png"))); // NOI18N
        actionMovimentacaoEmbarcacao.setMethodName("abrirRelatorioMovimentacaoEmbarcacoes");
        actionMovimentacaoEmbarcacao.setNeededSecurityResources(new String[] {"MOVIMENTACAO_NAVIO"});
        actionMovimentacaoEmbarcacao.setTarget(this);
        actionMovimentacaoEmbarcacao.setText("Movimentação de Embarcações");

        actionRelatorioAtendimentoEmbarcacao.setIcon(new ImageIcon(getClass().getResource("/icons/tarefa.png"))); // NOI18N
        actionRelatorioAtendimentoEmbarcacao.setMethodName("abrirRelatorioAtendimentoEmbarcacao");
        actionRelatorioAtendimentoEmbarcacao.setNeededSecurityResources(new String[]{RecursoCA.RELATORIO_ATENDIMENTO_EMBARCACAO.name()});
        actionRelatorioAtendimentoEmbarcacao.setTarget(this);
        actionRelatorioAtendimentoEmbarcacao.setText("Produtividade/Atendimentos às Embarcações");

        actionRelatorioManobra.setIcon(new ImageIcon(getClass().getResource("/icons/16x16navio.png"))); // NOI18N
        actionRelatorioManobra.setMethodName("abrirRelatorioManobra");
        actionRelatorioManobra.setNeededSecurityResources(new String[]{RecursoCA.RELATORIO_MANOBRAS.name()});
        actionRelatorioManobra.setTarget(this);
        actionRelatorioManobra.setText("Relatório de Manobras");

        actionRelatorioTimesheet.setIcon(new ImageIcon(getClass().getResource("/icons/stimechooser.png"))); // NOI18N
        actionRelatorioTimesheet.setMethodName("abrirRelatorioTimesheet");
        actionRelatorioTimesheet.setNeededSecurityResources(new String[] {"RELATORIO_TIMESHEET"});
        actionRelatorioTimesheet.setTarget(this);
        actionRelatorioTimesheet.setText("Relatório Timesheet");

        actionRelatorioVisita.setMethodName("abrirRelatorioVisita");
        actionRelatorioVisita.setNeededSecurityResources(new String[]{RecursoCA.RELATORIO_VISITAS.name()});
        actionRelatorioVisita.setTarget(this);
        actionRelatorioVisita.setText("Relatório de Visitas");

        actionProtecaoEmpresa.setMethodName("abrirFrameProtecaoEmpresas");
        actionProtecaoEmpresa.setNeededSecurityResources(new String[]{RecursoCA.SALVAR_EMPRESA_PROTECAO.name()});
        actionProtecaoEmpresa.setTarget(this);
        actionProtecaoEmpresa.setText("Empresas");
        actionProtecaoEmpresa.setToolTipText("Permite consultar e cadastrar empresas");

        actionProtecaoPessoa.setMethodName("abrirFrameProtecaoPessoas");
        actionProtecaoPessoa.setNeededSecurityResources(new String[]{RecursoCA.SALVAR_PESSOA_PROTECAO.name()});
        actionProtecaoPessoa.setTarget(this);
        actionProtecaoPessoa.setText("Pessoas");
        actionProtecaoPessoa.setToolTipText("Permite consultar e cadastrar pessoas");

        actionRelatorioSiscomex.setMethodName("abrirRelatorioSiscomex");
        actionRelatorioSiscomex.setNeededSecurityResources(new String[]{RecursoCA.RELATORIO_ANALISE_GERAL_SISCOMEX_CARGA.name()});
        actionRelatorioSiscomex.setTarget(this);
        actionRelatorioSiscomex.setText("Relatório de Análise Geral do Siscomex Carga");

        actionRelatorioServicoSuprimentoNavio.setMethodName("abrirRelatorioServicoSuprimentoNavio");
        actionRelatorioServicoSuprimentoNavio.setNeededSecurityResources(new String[]{RecursoCA.RELATORIO_SERVICO_SUPRIMENTO_AOS_NAVIOS.name()});
        actionRelatorioServicoSuprimentoNavio.setTarget(this);
        actionRelatorioServicoSuprimentoNavio.setText("Relatório de Serviço de Suprimento aos Navios");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        splitCentral.setBorder(null);
        splitCentral.setDividerLocation(200);
        splitCentral.setDividerSize(4);
        splitCentral.setMinimumSize(new Dimension(48, 28));
        splitCentral.setPreferredSize(new Dimension(218, 100));

        splitEsquerdo.setBorder(null);
        splitEsquerdo.setDividerLocation(400);
        splitEsquerdo.setDividerSize(4);
        splitEsquerdo.setForeground(new Color(0, 0, 0));
        splitEsquerdo.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitEsquerdo.setFont(new Font("Tahoma", 0, 11)); // NOI18N
        splitEsquerdo.setMinimumSize(new Dimension(204, 37));
        splitEsquerdo.setOpaque(false);

        painelEsquerdo.setMinimumSize(new Dimension(0, 0));
        painelEsquerdo.setPreferredSize(new Dimension(0, 0));
        painelEsquerdo.setLayout(new BorderLayout());
        splitEsquerdo.setTopComponent(painelEsquerdo);

        painelPropriedades.setPreferredSize(new Dimension(0, 0));
        splitEsquerdo.setBottomComponent(painelPropriedades);

        splitCentral.setLeftComponent(splitEsquerdo);
        splitEsquerdo.getAccessibleContext().setAccessibleParent(splitCentral);

        painelDireito.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 4));
        painelDireito.setLayout(new BorderLayout());

        desktop.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        desktop.setFrameLocationStrategy(SDesktop.CASCADE);
        desktop.setImg(new ImageIcon(getClass().getResource("/icons/fundo.jpg")).getImage());
        painelDireito.add(desktop, BorderLayout.CENTER);

        barraFramesAbertos.setBorder(BorderFactory.createEtchedBorder());
        barraFramesAbertos.setPreferredSize(new Dimension(10, 25));
        painelDireito.add(barraFramesAbertos, BorderLayout.NORTH);

        splitCentral.setRightComponent(painelDireito);

        getContentPane().add(splitCentral, BorderLayout.CENTER);
        getContentPane().add(statusPanel, BorderLayout.SOUTH);

        menuAgenciamentoCarga.setText("Agenciamento");

        menuAgenciamentoProtecao.setAction(actionNovoAgenciamento);
        menuAgenciamentoCarga.add(menuAgenciamentoProtecao);
        menuAgenciamentoCarga.add(jSeparator1);

        menuConsultarAgenciamento.setAction(actionConsultarAgenciamento);
        menuConsultarAgenciamento.setText("Consulta");
        menuAgenciamentoCarga.add(menuConsultarAgenciamento);

        menuBar.add(menuAgenciamentoCarga);

        menuRelatorio.setText("Relatórios");

        Produtividade.setAction(actionRelatorioProdutividade);
        menuRelatorio.add(Produtividade);

        menuProdutividadeAtendimentorealizado.setAction(actionRelatorioAtendimentoEmbarcacao);
        menuRelatorio.add(menuProdutividadeAtendimentorealizado);

        menuRelatorioManobras.setAction(actionRelatorioManobra);
        menuRelatorio.add(menuRelatorioManobras);

        menuRelatorioTimeSheet.setAction(actionRelatorioTimesheet);
        menuRelatorio.add(menuRelatorioTimeSheet);

        menuRelatorioVisita.setAction(actionRelatorioVisita);
        menuRelatorio.add(menuRelatorioVisita);

        jMenuItem6.setAction(actionRelatorioSiscomex);
        menuRelatorio.add(jMenuItem6);

        jMenuItem7.setAction(actionRelatorioServicoSuprimentoNavio);
        jMenuItem7.setText("Relatório de Serviço de Suprimentos às Embarcações");
        jMenuItem7.setToolTipText("Relatório de Serviço de Suprimentos às Embarcações");
        menuRelatorio.add(jMenuItem7);
        menuRelatorio.add(jSeparator2);

        menuTaxa.setAction(actionRelatorioTaxa);
        menuRelatorio.add(menuTaxa);

        menuControlePagamento.setAction(actionRelatorioControlePagamentoTaxa);
        menuRelatorio.add(menuControlePagamento);
        menuRelatorio.add(jSeparator3);

        menuMovimentacaoEmbarcacaoResumido.setAction(actionMovimentacaoEmbarcacao);
        menuRelatorio.add(menuMovimentacaoEmbarcacaoResumido);

        menuBar.add(menuRelatorio);

        menuEmbarcacao.setText("Embarcação");

        jMenuItem1.setAction(actionEmbarcacao);
        menuEmbarcacao.add(jMenuItem1);

        menuBar.add(menuEmbarcacao);

        menuAdministracao.setText("Administração");

        jMenu1.setText("Empresas/Pessoas - Serviço Proteção");
        jMenu1.setToolTipText("Empresas/Pessoas - Serviço Proteção");

        jMenuItem4.setAction(actionProtecaoEmpresa);
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAction(actionProtecaoPessoa);
        jMenu1.add(jMenuItem5);

        menuAdministracao.add(jMenu1);

        menuEmpresa.setAction(actionEmpresasServicos);
        menuAdministracao.add(menuEmpresa);

        menuAgencia.setAction(actionAgencia);
        menuAgencia.setText("Dados da Agência");
        menuAdministracao.add(menuAgencia);

        jMenuItem2.setAction(actionPontosDeAtracacao);
        menuAdministracao.add(jMenuItem2);

        jMenuItem3.setAction(actionPortos);
        menuAdministracao.add(jMenuItem3);

        menuBar.add(menuAdministracao);

        menuSobre.setText(bundle.getString("Ajuda")); // NOI18N

        itemConteudoAjuda.setAction(getActionHelpContents());
        menuSobre.add(itemConteudoAjuda);
        menuSobre.add(separator1);

        itemSobre.setAction(getActionAbout());
        menuSobre.add(itemSobre);

        menuBar.add(menuSobre);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // CHECKSTYLE:ON

    private void configurarComponentes() {
        barraFramesAbertos.addPropertyChangeListener(this);

        // corrigi tamanho maximizado da tela
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle maximumWindowBounds = graphicsEnvironment.getMaximumWindowBounds();
        setMaximizedBounds(maximumWindowBounds);

        // remove a borda do split pane para melhorar o layout
        if (splitCentral.getUI() instanceof BasicSplitPaneUI) {
            BasicSplitPaneDivider divider = ((BasicSplitPaneUI) splitCentral.getUI()).getDivider();
            if (divider != null) {
                divider.setBorder(null);
            }
        }
        
        // remove a borda do split pane para melhorar o layout
        if (splitEsquerdo.getUI() instanceof BasicSplitPaneUI) {
            BasicSplitPaneDivider divider = ((BasicSplitPaneUI) splitEsquerdo.getUI()).getDivider();
            if (divider != null) {
                divider.setBorder(null);
            }
        }

        // esconde o painel de resultado de pesquisa ao abrir a tela
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                esconderListagem();
            }
        });
    }
    
    private void preencherPainelDePropriedades(Agenciamento agenciamento){
        painelPropriedades.limparPropriedades();
        
            if(agenciamento != null){
                
//                String vgmChegada = agenciamento.getVgm() != null ? agenciamento.getVgm() : "";
//                String vgmSaida = agenciamento.getVgmSaida()!= null ? " / " + agenciamento.getVgmSaida(): ""; 
                
                TimeZone zone = agenciamento.getAgencia().obterTimezone();
                painelPropriedades.adicionarPropriedade("Processo", agenciamento.getNumeroProcessoformatado());
                painelPropriedades.adicionarPropriedade("Nome", agenciamento.getEmbarcacao().getNomeCompleto());
//                painelPropriedades.adicionarPropriedade("VGM", vgmChegada.concat(vgmSaida));
                painelPropriedades.adicionarPropriedade("VGM Chegada", agenciamento.getVgm());
                painelPropriedades.adicionarPropriedade("VGM Saída", agenciamento.getVgmSaida());
                painelPropriedades.adicionarPropriedade("Origem", agenciamento.getPortoOrigem());
                painelPropriedades.adicionarPropriedade("ETA", SistamDateUtils.formatDateComplete(agenciamento.getEta(), zone));
                painelPropriedades.adicionarPropriedade("Calado Máximo", agenciamento.getEmbarcacao().getCaladoMaximo());
                painelPropriedades.adicionarPropriedade("Número DUV", agenciamento.getNumeroDUV());
                painelPropriedades.adicionarPropriedade("Situação", agenciamento.getStatusEmbarcacao());
                painelPropriedades.adicionarPropriedade("Porto", agenciamento.getPorto());
                painelPropriedades.adicionarPropriedade("Destino", agenciamento.getPortoDestino());
                painelPropriedades.adicionarPropriedade("Data Saída", SistamDateUtils.formatDateComplete(agenciamento.getDataSaida(), zone));
            }
    }
    
    public void atualizarActions() {
        actionNovoAgenciamento.recheckSecurityResources();
        actionConsultarAgenciamento.recheckSecurityResources();
        actionRelatorioTaxa.recheckSecurityResources();
        actionRelatorioProdutividade.recheckSecurityResources();
        actionRelatorioControlePagamentoTaxa.recheckSecurityResources();
        actionEmbarcacao.recheckSecurityResources();
        actionEmpresasServicos.recheckSecurityResources();
        actionAgencia.recheckSecurityResources();
        actionPontosDeAtracacao.recheckSecurityResources();
        actionPortos.recheckSecurityResources();
        actionMovimentacaoEmbarcacao.recheckSecurityResources();
        actionRelatorioTimesheet.recheckSecurityResources();
        actionRelatorioAtendimentoEmbarcacao.recheckSecurityResources();
        actionProtecaoEmpresa.recheckSecurityResources();
        actionProtecaoPessoa.recheckSecurityResources();
        actionRelatorioManobra.recheckSecurityResources();
        actionRelatorioVisita.recheckSecurityResources();
        actionRelatorioSiscomex.recheckSecurityResources();
        actionRelatorioServicoSuprimentoNavio.recheckSecurityResources();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    GenericAction actionAgencia;
    GenericAction actionConsultarAgenciamento;
    GenericAction actionEmbarcacao;
    GenericAction actionEmpresasServicos;
    GenericAction actionMovimentacaoEmbarcacao;
    GenericAction actionNovoAgenciamento;
    GenericAction actionPontosDeAtracacao;
    GenericAction actionPortos;
    GenericAction actionProtecaoEmpresa;
    GenericAction actionProtecaoPessoa;
    GenericAction actionRelatorioAtendimentoEmbarcacao;
    GenericAction actionRelatorioControlePagamentoTaxa;
    GenericAction actionRelatorioManobra;
    GenericAction actionRelatorioProdutividade;
    GenericAction actionRelatorioServicoSuprimentoNavio;
    GenericAction actionRelatorioSiscomex;
    GenericAction actionRelatorioTaxa;
    GenericAction actionRelatorioTimesheet;
    GenericAction actionRelatorioVisita;
    SActivatorBar barraFramesAbertos;
    private SDesktop desktop;
    SPanel painelEsquerdo;
    PropriedadesPainel painelPropriedades;
    JSplitPane splitCentral;
    JSplitPane splitEsquerdo;
    private SStatusPanel statusPanel;
    // End of variables declaration//GEN-END:variables
}
