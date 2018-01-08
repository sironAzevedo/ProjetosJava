package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.dialog.DialogMessages;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.agenciamento.manobras.InformacoesDoAgenciamentoDialog;
import br.com.petrobras.sistam.desktop.agenciamento.manobras.RegistroManobraDialog;
import br.com.petrobras.sistam.desktop.agenciamento.manobras.SolicitacaoManobraDialog;
import br.com.petrobras.sistam.desktop.components.DialogObservacao;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

public class ManobrasPainel extends SPanel{
    public static final String ATUALIZOU_AGENCIAMENTO = "ATUALIZOU_AGENCIAMENTO";
    private JPopupMenu menuRegistro;
    
    private ManobrasModel model = new ManobrasModel();

    public ManobrasPainel() {
        initComponents();   
        prepararTela();
    }
    
    public void setAgenciamento(Agenciamento agenciamento){
        model.setAgenciamento(agenciamento);
        if (agenciamento != null && StatusEmbarcacao.CANCELADO.equals(agenciamento.getStatusEmbarcacao())){
            colocarEmModoVisualizacao();
        }
    }
    
    public void solicitarManobra() {
        Manobra manobra = model.obterNovaManobraParaSolicitacao();
        SolicitacaoManobraDialog dialog = new SolicitacaoManobraDialog(SistamApp.getSistamApp().getMainFrame(), manobra);  
        dialog.setVisible(true);
        model.carregarManobrasDoAgenciamento();
    }
    
    public void editarManobraSolicitada() {
        Manobra manobra = model.obterManobraSolicitadaParaEdicao();
        SolicitacaoManobraDialog dialog = new SolicitacaoManobraDialog(SistamApp.getSistamApp().getMainFrame(), manobra);
        dialog.setVisible(true);
        model.carregarManobrasDoAgenciamento();
    }
  
    public ManobrasModel getModel() {
        return model;
    }
   
    public void registrarNovaManobra(){
        Manobra manobra = model.obterNovaManobraParaRegistro();
        RegistroManobraDialog dialog = new RegistroManobraDialog(SistamApp.getSistamApp().getMainFrame(), manobra); 
        dialog.prepararParaRegistro();
        dialog.setVisible(true); 
        
        if (dialog.getModel().estaSalvo()){
            this.model.adicionarManobraRegistrada(dialog.getModel().getManobra());
            if(dialog.getModel().getManobra().isPreRegistrada()){
                //Avisa a outros componentes de uma possível atualização no agenciamento.
                firePropertyChange(ATUALIZOU_AGENCIAMENTO, null, null);
            }
        }
    }
    
    public void editarManobraRegistrada(){
        Manobra manobra = model.obterManobraParaEdicaoRegistro();
        RegistroManobraDialog dialog = new RegistroManobraDialog(SistamApp.getSistamApp().getMainFrame(), manobra);
        dialog.prepararParaRegistro();
        dialog.setVisible(true); 
        
        if (dialog.getModel().estaSalvo()){
            this.model.atualizarManobraRegistrada(dialog.getModel().getManobra());
            if(dialog.getModel().getManobra().isPreRegistrada()){
                //Avisa a outros componentes de uma possível atualização no agenciamento.
                firePropertyChange(ATUALIZOU_AGENCIAMENTO, null, null);
            }
        }
    }
    
    public void registrarManobraSolicitada(){
        Manobra manobra = model.obterManobraSolicitadaParaEdicao();
        RegistroManobraDialog dialog = new RegistroManobraDialog(SistamApp.getSistamApp().getMainFrame(), manobra);
        dialog.prepararParaRegistro();
        dialog.setVisible(true); 
        
        if (dialog.getModel().estaSalvo()){
            this.model.atualizarManobraSolicitada(dialog.getModel().getManobra());
            
            //Avisa a outros componentes de uma possível atualização no agenciamento.
            firePropertyChange(ATUALIZOU_AGENCIAMENTO, null, null);
        }
    }
    
    public void cancelarNovaManobraForaDePrazo(){
        Manobra manobra = model.obterNovaManobraParaRegistro();
        RegistroManobraDialog dialog = new RegistroManobraDialog(SistamApp.getSistamApp().getMainFrame(), manobra);
        dialog.prepararParaCancelamento();
        dialog.setVisible(true); 
        
        if (dialog.getModel().estaSalvo()){
            this.model.adicionarManobraRegistrada(dialog.getModel().getManobra());
        }
    }
    
    public void cancelarManobraSolicitadaForaDePrazo(){
        Manobra manobra = model.obterManobraSolicitadaParaEdicao();
        RegistroManobraDialog dialog = new RegistroManobraDialog(SistamApp.getSistamApp().getMainFrame(), manobra);
        dialog.prepararParaCancelamento();
        dialog.setVisible(true); 
        
        if (dialog.getModel().estaSalvo()){
            this.model.atualizarManobraSolicitada(dialog.getModel().getManobra());
        }
    }
    
    public void visualizarManobraRegistrada(){
        Manobra manobra = model.obterManobraParaEdicaoRegistro();
        RegistroManobraDialog dialog = new RegistroManobraDialog(SistamApp.getSistamApp().getMainFrame(), manobra);
        dialog.prepararParaVisualizacao();
        dialog.setVisible(true); 
    }
    
    public void encerrarManobra() {

        if (model.getManobraRegistradaSelecionada().getServicos() == null || model.getManobraRegistradaSelecionada().getServicos().isEmpty()) {
            if (!DialogMessages.confirm(this, "A manobra não tem serviço informado, após encerrar não será possível fazer alterações. Deseja continuar ?")) {
                return;
            }
        }
        
        FinalidadeManobra finalidadeDaManobra = model.getManobraRegistradaSelecionada().getFinalidadeManobra();
        
        if (FinalidadeManobra.DESATRACACAO_SAIDA.equals(finalidadeDaManobra)
                || FinalidadeManobra.SAIDA_FUNDEIO.equals(finalidadeDaManobra)){
            
            InformacoesDoAgenciamentoDialog dialog = new InformacoesDoAgenciamentoDialog(SistamApp.getSistamApp().getMainFrame());
            dialog.setVisible(true);
            
            if (dialog.getModel().foiConfirmado()){
                model.encerrarManobra(dialog.getModel().getPortoDestino(), dialog.getModel().getEta());
                
                //Avisa a outros componentes de uma possível atualização no agenciamento.
                firePropertyChange(ATUALIZOU_AGENCIAMENTO, null, null);
            }
        } else{
            model.encerrarManobra(null, null);
            
            //Avisa a outros componentes de uma possível atualização no agenciamento.
            firePropertyChange(ATUALIZOU_AGENCIAMENTO, null, null);
        }
    }
    
     public void exibirMenuDeNovoRegistro(ActionEvent evt){
        menuRegistro = new JPopupMenu();
        
        menuRegistro.add(actionRegistrarNovaManobra);
        menuRegistro.add(actionCancelarNovaManobraForaDePrazo);
        
        SButton button = (SButton) evt.getSource();
        menuRegistro.setVisible(true);
        menuRegistro.show(button.getParent(), button.getX() - 1, button.getY() - this.menuRegistro.getHeight() - 1);
    }
     
    public void exibirMenuDeRegistroSolicitada(ActionEvent evt){
        menuRegistro = new JPopupMenu();
        
        menuRegistro.add(actionRegistrarManobraSolicitada);
        menuRegistro.add(actionCancelarManobraSolicitadaForaDePrazo);
        
        SButton button = (SButton) evt.getSource();
        menuRegistro.setVisible(true);
        menuRegistro.show(button.getParent(), button.getX() - 1, button.getY() - this.menuRegistro.getHeight() - 1);
    }
     
     public void cancelarManobraSolicitada() {
        DialogObservacao dialog = new DialogObservacao(SistamApp.getApplication().getMainFrame(), "Motivo do Cancelamento" , "Motivo:", 512);
        dialog.setVisible(true);
        
        if (dialog.isConfirmado()){
            model.cancelarManobraSolicitada(dialog.getText());
            DialogMessages.info(this, "Manobra cancelada com sucesso!");
            model.carregarManobrasDoAgenciamento();
        }
     }
     
    private void prepararTela() {
        //seta o label da aba de manobras solicitadas
        ImageIcon iconeSolicitada = new ImageIcon(getClass().getResource("/icons/manobra_solicitada.png"));
        JLabel labelSolicitada = new JLabel("Solicitadas", iconeSolicitada, SwingConstants.LEFT);
        labelSolicitada.setHorizontalTextPosition(SwingConstants.CENTER);
        labelSolicitada.setVerticalTextPosition(SwingConstants.TOP);
        int index = tabManobras.indexOfComponent(painelManobrasSolicitadas);
        tabManobras.setTitleAt(index, null);
        tabManobras.setTabComponentAt(index, labelSolicitada);

        //seta o label da aba de manobras registradas
        ImageIcon iconeRegistrada = new ImageIcon(getClass().getResource("/icons/manobra_registrada.png"));
        JLabel labelRegistrada = new JLabel("Registradas", iconeRegistrada, SwingConstants.LEFT);
        labelRegistrada.setHorizontalTextPosition(SwingConstants.CENTER);
        labelRegistrada.setVerticalTextPosition(SwingConstants.TOP);
        labelRegistrada.setIconTextGap(10);
        int index2 = tabManobras.indexOfComponent(painelManobrasRegistradas);
        tabManobras.setTitleAt(index2, null);
        tabManobras.setTabComponentAt(index2, labelRegistrada);

    }

    public void colocarEmModoVisualizacao(){
        botaoSolicitar.setVisible(false);
        botaoEditarSolicitado.setVisible(false);
        botaoRegistrarSolicitado.setVisible(false);
        botaoCancelarSolicitado.setVisible(false);
        botaoRegistrar.setVisible(false);
        botaoEditarRegistrado.setVisible(false);
        botaoEncerrar.setVisible(false);
    }
    
   
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"}) @SuppressFBWarnings
    //CHECKSTYLE:OFF
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        actionSolicitar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionEditarManobraSolicitada = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionExcluirManobraSolicitada = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionCancelarManobraSolicitada = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionCancelarManobraSolicitadaForaDePrazo = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionRegistrarManobraSolicitada = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionRegistrarNovaManobra = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionRegistrar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionRegistrarSolicitada = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionEditarManobraRegistrada = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionCancelarNovaManobraForaDePrazo = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionVisualizarManobraRegistrada = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionEncerrar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        sistamDateCellRenderer1 = new br.com.petrobras.sistam.desktop.components.SistamDateCellRenderer();
        tabManobras = new br.com.petrobras.fcorp.swing.components.STabbedPane();
        painelManobrasSolicitadas = new br.com.petrobras.fcorp.swing.components.SPanel();
        painelManobras = new br.com.petrobras.fcorp.swing.components.SPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sTable1 = new br.com.petrobras.fcorp.swing.components.STable();
        botaoRegistrarSolicitado = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoEditarSolicitado = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoSolicitar = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoCancelarSolicitado = new br.com.petrobras.fcorp.swing.components.SButton();
        painelServicos = new br.com.petrobras.fcorp.swing.components.SPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sTable2 = new br.com.petrobras.fcorp.swing.components.STable();
        painelManobrasRegistradas = new br.com.petrobras.fcorp.swing.components.SPanel();
        painelManobras1 = new br.com.petrobras.fcorp.swing.components.SPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        sTable3 = new br.com.petrobras.fcorp.swing.components.STable();
        botaoRegistrar = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoEncerrar = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoEditarRegistrado = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoVisualizar = new br.com.petrobras.fcorp.swing.components.SButton();
        sHelpLabel1 = new br.com.petrobras.fcorp.swing.components.SHelpLabel();
        painelServicos1 = new br.com.petrobras.fcorp.swing.components.SPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        sTable4 = new br.com.petrobras.fcorp.swing.components.STable();

        actionSolicitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionSolicitar.setMethodName("solicitarManobra");
        actionSolicitar.setTarget(this);
        actionSolicitar.setText("Solicitar");
        actionSolicitar.setToolTipText("Solicita uma nova manobra");

        actionEditarManobraSolicitada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditarManobraSolicitada.setMethodName("editarManobraSolicitada");
        actionEditarManobraSolicitada.setTarget(this);
        actionEditarManobraSolicitada.setText("Editar");
        actionEditarManobraSolicitada.setToolTipText("Edita a manobra selecionada");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.habilitarEditarManobraSolicitada}"), actionEditarManobraSolicitada, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionExcluirManobraSolicitada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluirManobraSolicitada.setText("Excluir");
        actionExcluirManobraSolicitada.setToolTipText("Exclui a manobra selecionada");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.manobraSolicitadaSelecionada}"), actionExcluirManobraSolicitada, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionCancelarManobraSolicitada.setConfirm("Confirma cancelamento da manobra?");
        actionCancelarManobraSolicitada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel1.png"))); // NOI18N
        actionCancelarManobraSolicitada.setMethodName("cancelarManobraSolicitada");
        actionCancelarManobraSolicitada.setTarget(this);
        actionCancelarManobraSolicitada.setText("Cancelar");
        actionCancelarManobraSolicitada.setToolTipText("Cancela a manobra selecionada");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.habilitarCancelarManobraSolicitada}"), actionCancelarManobraSolicitada, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionCancelarManobraSolicitadaForaDePrazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        actionCancelarManobraSolicitadaForaDePrazo.setMethodName("cancelarManobraSolicitadaForaDePrazo");
        actionCancelarManobraSolicitadaForaDePrazo.setTarget(this);
        actionCancelarManobraSolicitadaForaDePrazo.setText("Cancelada Fora do Prazo");
        actionCancelarManobraSolicitadaForaDePrazo.setToolTipText("Registra a manobra como Cancelada Fora do Prazo");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.habilitarRegistrarManobraSolicitada}"), actionCancelarManobraSolicitadaForaDePrazo, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionRegistrarManobraSolicitada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/concluir.png"))); // NOI18N
        actionRegistrarManobraSolicitada.setMethodName("registrarManobraSolicitada");
        actionRegistrarManobraSolicitada.setTarget(this);
        actionRegistrarManobraSolicitada.setText("Manobra Efetuada");
        actionRegistrarManobraSolicitada.setToolTipText("Efetua o registro da  manobra selecionada");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.habilitarRegistrarManobraSolicitada}"), actionRegistrarManobraSolicitada, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionRegistrarNovaManobra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/concluir.png"))); // NOI18N
        actionRegistrarNovaManobra.setMethodName("registrarNovaManobra");
        actionRegistrarNovaManobra.setTarget(this);
        actionRegistrarNovaManobra.setText("Manobra Efetuada");
        actionRegistrarNovaManobra.setToolTipText("Registra uma nova manobra");

        actionRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/document.png"))); // NOI18N
        actionRegistrar.setMethodName("exibirMenuDeNovoRegistro");
        actionRegistrar.setTarget(this);
        actionRegistrar.setText("Registrar");
        actionRegistrar.setToolTipText("Registra uma nova manobra");

        actionRegistrarSolicitada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/document.png"))); // NOI18N
        actionRegistrarSolicitada.setMethodName("exibirMenuDeRegistroSolicitada");
        actionRegistrarSolicitada.setTarget(this);
        actionRegistrarSolicitada.setText("Registrar");
        actionRegistrarSolicitada.setToolTipText("Registra a manobra solicitada selecionada");

        actionEditarManobraRegistrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditarManobraRegistrada.setMethodName("editarManobraRegistrada");
        actionEditarManobraRegistrada.setTarget(this);
        actionEditarManobraRegistrada.setText("Editar");
        actionEditarManobraRegistrada.setToolTipText("Edita a manobra selecionada");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.habilitarEditarManobraRegistrada}"), actionEditarManobraRegistrada, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionCancelarNovaManobraForaDePrazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        actionCancelarNovaManobraForaDePrazo.setMethodName("cancelarNovaManobraForaDePrazo");
        actionCancelarNovaManobraForaDePrazo.setTarget(this);
        actionCancelarNovaManobraForaDePrazo.setText("Cancelada Fora do Prazo");
        actionCancelarNovaManobraForaDePrazo.setToolTipText("Registra a manobra como Cancelada Fora do Prazo");

        actionVisualizarManobraRegistrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.png"))); // NOI18N
        actionVisualizarManobraRegistrada.setMethodName("visualizarManobraRegistrada");
        actionVisualizarManobraRegistrada.setTarget(this);
        actionVisualizarManobraRegistrada.setText("Visualizar");
        actionVisualizarManobraRegistrada.setToolTipText("Visualiza a manobra selecionada");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.manobraRegistradaSelecionada}"), actionVisualizarManobraRegistrada, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionEncerrar.setConfirm("Deseja realmente encerrar a manobra?");
        actionEncerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/decisao.png"))); // NOI18N
        actionEncerrar.setMethodName("encerrarManobra");
        actionEncerrar.setTarget(this);
        actionEncerrar.setText("Encerrar");
        actionEncerrar.setToolTipText("Encerra a manobra selecionada");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.habilitarEncerrarManobra}"), actionEncerrar, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        sistamDateCellRenderer1.setPattern("dd/MM/yyyy HH:mm");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.timeZone}"), sistamDateCellRenderer1, org.jdesktop.beansbinding.BeanProperty.create("timeZone"));
        bindingGroup.addBinding(binding);

        tabManobras.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        painelManobras.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Manobras"));

        sTable1.setSortable(true);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${model.listaManobrasSolicitadas}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, sTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${finalidadeManobra}"));
        columnBinding.setColumnName("Finalidade");
        columnBinding.setColumnClass(br.com.petrobras.sistam.common.enums.FinalidadeManobra.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pontoAtracacaoOrigem.nomeFormatado}"));
        columnBinding.setColumnName("Ponto Inicial");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pontoAtracacaoDestino.nomeFormatado}"));
        columnBinding.setColumnName("Ponto Final");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${status}"));
        columnBinding.setColumnName("Status");
        columnBinding.setColumnClass(br.com.petrobras.sistam.common.enums.StatusManobra.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.manobraSolicitadaSelecionada}"), sTable1, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(sTable1);

        botaoRegistrarSolicitado.setAction(actionRegistrarSolicitada);

        botaoEditarSolicitado.setAction(actionEditarManobraSolicitada);

        botaoSolicitar.setAction(actionSolicitar);

        botaoCancelarSolicitado.setAction(actionCancelarManobraSolicitada);

        javax.swing.GroupLayout painelManobrasLayout = new javax.swing.GroupLayout(painelManobras);
        painelManobras.setLayout(painelManobrasLayout);
        painelManobrasLayout.setHorizontalGroup(
            painelManobrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelManobrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelManobrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(painelManobrasLayout.createSequentialGroup()
                        .addGap(0, 351, Short.MAX_VALUE)
                        .addComponent(botaoSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoEditarSolicitado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelarSolicitado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoRegistrarSolicitado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        painelManobrasLayout.setVerticalGroup(
            painelManobrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelManobrasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelManobrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoRegistrarSolicitado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEditarSolicitado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCancelarSolicitado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        painelServicos.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Serviços"));

        sTable2.setSortable(true);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${model.listaServicoManobraSolicitada}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, sTable2);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataProgramada}"));
        columnBinding.setColumnName("Data Programada");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${empresaMaritima}"));
        columnBinding.setColumnName("Empresa");
        columnBinding.setColumnClass(br.com.petrobras.sistam.common.entity.EmpresaMaritima.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tipoDoServico}"));
        columnBinding.setColumnName("Serviço");
        columnBinding.setColumnClass(br.com.petrobras.sistam.common.enums.TipoServico.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataEnvio}"));
        columnBinding.setColumnName("Data Envio");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(sTable2);

        javax.swing.GroupLayout painelServicosLayout = new javax.swing.GroupLayout(painelServicos);
        painelServicos.setLayout(painelServicosLayout);
        painelServicosLayout.setHorizontalGroup(
            painelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelServicosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        painelServicosLayout.setVerticalGroup(
            painelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelServicosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout painelManobrasSolicitadasLayout = new javax.swing.GroupLayout(painelManobrasSolicitadas);
        painelManobrasSolicitadas.setLayout(painelManobrasSolicitadasLayout);
        painelManobrasSolicitadasLayout.setHorizontalGroup(
            painelManobrasSolicitadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelManobrasSolicitadasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelManobrasSolicitadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelServicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelManobras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        painelManobrasSolicitadasLayout.setVerticalGroup(
            painelManobrasSolicitadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelManobrasSolicitadasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelManobras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelServicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabManobras.addTab("Solicitadas", painelManobrasSolicitadas);

        painelManobras1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Manobras"));

        sTable3.setSortable(true);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${model.listaManobrasRegistradas}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, sTable3);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${finalidadeManobra}"));
        columnBinding.setColumnName("Finalidade");
        columnBinding.setColumnClass(br.com.petrobras.sistam.common.enums.FinalidadeManobra.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataTermino}"));
        columnBinding.setColumnName("Dt Término");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pontoAtracacaoOrigem.nomeFormatado}"));
        columnBinding.setColumnName("Ponto Inicial");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pontoAtracacaoDestino.nomeFormatado}"));
        columnBinding.setColumnName("Ponto Final");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${status}"));
        columnBinding.setColumnName("Status");
        columnBinding.setColumnClass(br.com.petrobras.sistam.common.enums.StatusManobra.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${movimentadaDescricao}"));
        columnBinding.setColumnName("Movimentada?");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.manobraRegistradaSelecionada}"), sTable3, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane3.setViewportView(sTable3);
        sTable3.getColumnModel().getColumn(1).setCellRenderer(sistamDateCellRenderer1);
        sTable3.getColumnModel().getColumn(5).setPreferredWidth(70);

        botaoRegistrar.setAction(actionRegistrar);

        botaoEncerrar.setAction(actionEncerrar);

        botaoEditarRegistrado.setAction(actionEditarManobraRegistrada);

        botaoVisualizar.setAction(actionVisualizarManobraRegistrada);

        sHelpLabel1.setText("Para mudar o status da embarcação, a manobra deve ser encerrada ");

        javax.swing.GroupLayout painelManobras1Layout = new javax.swing.GroupLayout(painelManobras1);
        painelManobras1.setLayout(painelManobras1Layout);
        painelManobras1Layout.setHorizontalGroup(
            painelManobras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelManobras1Layout.createSequentialGroup()
                .addGroup(painelManobras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelManobras1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(painelManobras1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(sHelpLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoEditarRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        painelManobras1Layout.setVerticalGroup(
            painelManobras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelManobras1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelManobras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEditarRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sHelpLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        painelServicos1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Serviços"));

        sTable4.setSortable(true);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${model.listaServicoManobraRegistrada}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, sTable4);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tipoDoServico}"));
        columnBinding.setColumnName("Tipo");
        columnBinding.setColumnClass(br.com.petrobras.sistam.common.enums.TipoServico.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${empresaMaritima}"));
        columnBinding.setColumnName("Empresa");
        columnBinding.setColumnClass(br.com.petrobras.sistam.common.entity.EmpresaMaritima.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataProgramada}"));
        columnBinding.setColumnName("Data Programada");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane4.setViewportView(sTable4);
        sTable4.getColumnModel().getColumn(2).setCellRenderer(sistamDateCellRenderer1);

        javax.swing.GroupLayout painelServicos1Layout = new javax.swing.GroupLayout(painelServicos1);
        painelServicos1.setLayout(painelServicos1Layout);
        painelServicos1Layout.setHorizontalGroup(
            painelServicos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelServicos1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        painelServicos1Layout.setVerticalGroup(
            painelServicos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelServicos1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout painelManobrasRegistradasLayout = new javax.swing.GroupLayout(painelManobrasRegistradas);
        painelManobrasRegistradas.setLayout(painelManobrasRegistradasLayout);
        painelManobrasRegistradasLayout.setHorizontalGroup(
            painelManobrasRegistradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelManobrasRegistradasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelManobrasRegistradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelServicos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelManobras1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        painelManobrasRegistradasLayout.setVerticalGroup(
            painelManobrasRegistradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelManobrasRegistradasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelManobras1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelServicos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabManobras.addTab("Registradas", painelManobrasRegistradas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabManobras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabManobras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionCancelarManobraSolicitada;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionCancelarManobraSolicitadaForaDePrazo;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionCancelarNovaManobraForaDePrazo;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionEditarManobraRegistrada;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionEditarManobraSolicitada;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionEncerrar;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionExcluirManobraSolicitada;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionRegistrar;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionRegistrarManobraSolicitada;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionRegistrarNovaManobra;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionRegistrarSolicitada;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionSolicitar;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionVisualizarManobraRegistrada;
    private br.com.petrobras.fcorp.swing.components.SButton botaoCancelarSolicitado;
    private br.com.petrobras.fcorp.swing.components.SButton botaoEditarRegistrado;
    private br.com.petrobras.fcorp.swing.components.SButton botaoEditarSolicitado;
    private br.com.petrobras.fcorp.swing.components.SButton botaoEncerrar;
    private br.com.petrobras.fcorp.swing.components.SButton botaoRegistrar;
    private br.com.petrobras.fcorp.swing.components.SButton botaoRegistrarSolicitado;
    private br.com.petrobras.fcorp.swing.components.SButton botaoSolicitar;
    private br.com.petrobras.fcorp.swing.components.SButton botaoVisualizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private br.com.petrobras.fcorp.swing.components.SPanel painelManobras;
    private br.com.petrobras.fcorp.swing.components.SPanel painelManobras1;
    private br.com.petrobras.fcorp.swing.components.SPanel painelManobrasRegistradas;
    private br.com.petrobras.fcorp.swing.components.SPanel painelManobrasSolicitadas;
    private br.com.petrobras.fcorp.swing.components.SPanel painelServicos;
    private br.com.petrobras.fcorp.swing.components.SPanel painelServicos1;
    private br.com.petrobras.fcorp.swing.components.SHelpLabel sHelpLabel1;
    private br.com.petrobras.fcorp.swing.components.STable sTable1;
    private br.com.petrobras.fcorp.swing.components.STable sTable2;
    private br.com.petrobras.fcorp.swing.components.STable sTable3;
    private br.com.petrobras.fcorp.swing.components.STable sTable4;
    private br.com.petrobras.sistam.desktop.components.SistamDateCellRenderer sistamDateCellRenderer1;
    private br.com.petrobras.fcorp.swing.components.STabbedPane tabManobras;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
