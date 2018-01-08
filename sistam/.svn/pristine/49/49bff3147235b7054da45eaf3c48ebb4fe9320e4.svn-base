package br.com.petrobras.sistam.desktop.agenciamento.manobras;

import br.com.petrobras.fcorp.swing.base.SDialog;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SComboBox;
import br.com.petrobras.fcorp.swing.components.SLabel;
import br.com.petrobras.fcorp.swing.components.SNumericTextField;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.SRadioPanel;
import br.com.petrobras.fcorp.swing.components.STable;
import br.com.petrobras.fcorp.swing.components.STextArea;
import br.com.petrobras.fcorp.swing.components.dialog.DialogMessages;
import br.com.petrobras.fcorp.swing.components.util.GenericDocument;
import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.components.DialogObservacao;
import br.com.petrobras.sistam.desktop.components.SistamDateCellRenderer;
import br.com.petrobras.sistam.desktop.components.SistamDateChooser;
import br.com.petrobras.sistam.desktop.components.SistamTimeChooser;
import br.com.petrobras.sistam.desktop.components.TimeZoneComboBox;
import br.com.petrobras.sistam.desktop.util.DesktopUtil;
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.JTableBinding.ColumnBinding;
import org.jdesktop.swingbinding.SwingBindings;

public class RegistroManobraDialog extends SDialog implements PropertyChangeListener{
    private RegistroManobraDialogModel model;
    
    public RegistroManobraDialog(java.awt.Frame parent, Manobra manobra) {
        super(parent, true);
        model = new RegistroManobraDialogModel(manobra);
        initComponents();
        setLocationRelativeTo(parent); 
        
        carregarDados();
        
        timeZoneComboBox.setSelectedItem(model.getTimeZone());
        
        //Pergunta se o usuário deseja registrar quando sair sem a manobra estar salva para manobras com id igual a null
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (model.getManobra().getId() == null
                        && DialogMessages.confirm(RegistroManobraDialog.this, "Deseja salvar possíveis alterações antes de sair?")){
                    
                    if (model.isModoCancelamentoForaDoPrazo()){
                        cancelarForaDoPrazo();
                    }else{
                        registrar();
                    }
                }
            }
        });
    }

    public RegistroManobraDialogModel getModel() {
        return model;
    }
    
    public void registrar(){
        model.registrar();
        dispose();
    }
    
    public void movimentar(){
        model.movimentar();
        dispose();
    }
    
    public void cancelarForaDoPrazoESair(){
        DialogObservacao dialog = new DialogObservacao(SistamApp.getApplication().getMainFrame(), "Motivo do Cancelamento" , "Motivo:", 512);
        dialog.setVisible(true);
        
        if (dialog.isConfirmado()){
            model.cancelarForaDoPrazo(dialog.getText());
            DialogMessages.info(this, "Manobra cancelada com sucesso!");
            dispose();
        }
    }
    
    public void cancelarForaDoPrazo(){
        DialogObservacao dialog = new DialogObservacao(SistamApp.getApplication().getMainFrame(), "Motivo do Cancelamento" , "Motivo:", 512);
        dialog.setVisible(true);
        
        if (dialog.isConfirmado()){
            model.cancelarForaDoPrazo(dialog.getText());
        }
    }
    
    public void adicionarServico(){
        ServicoManobra servicoManobra = model.obterNovoServicoManobra();
        ServicoManobraDialog dialog = new ServicoManobraDialog(SistamApp.getApplication().getMainFrame(), servicoManobra);
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);
    }
    
    public void editarServico(){
        ServicoManobra servicoManobra = model.obterServicoManobraParaEdicao();
        ServicoManobraDialog dialog = new ServicoManobraDialog(SistamApp.getApplication().getMainFrame(), servicoManobra);
        dialog.getModel().setEdicao(true);
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);
    }
    
    public void visualizarServico(){
        ServicoManobra servicoManobra = model.obterServicoManobraParaEdicao();
        ServicoManobraDialog dialog = new ServicoManobraDialog(SistamApp.getApplication().getMainFrame(), servicoManobra);
        dialog.prepararParaVisualizacao();
        dialog.setVisible(true);
    }
    
    
    private void carregarDados() {
        AssyncInvoker
                .create(model, "carregarPontosAtracacao")
                .disabling(cboPontoIncial)
                .settingLoadingIconOn(lblPontoIncial)
                .schedule();

        AssyncInvoker
                .create(model, "carregarPontosAtracacao")
                .disabling(cboPontoFinal)
                .settingLoadingIconOn(lblPontiFinal)
                .schedule();

        AssyncInvoker
                .create(model, "carregarPontosAtracacao")
                .disabling(cboEscala)
                .settingLoadingIconOn(lblEscala)
                .schedule();
        
        AssyncInvoker
                .create(model, "carregarResponsavelCusto")
                .disabling(cboResponsavelCusto)
                .settingLoadingIconOn(lblResponsavelCusto)
                .schedule();
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ServicoManobraDialog.SERVICO_INSERIDO)){
            ServicoManobra servico = ((ServicoManobraDialog)evt.getSource()).getModel().getServicoManobra();
            model.adicionarServico(servico);
        }
        
        if (evt.getPropertyName().equals(ServicoManobraDialog.SERVICO_ALTERADO)){
            ServicoManobra servico = ((ServicoManobraDialog)evt.getSource()).getModel().getServicoManobra();
            model.atualizarServico(servico);
        }
    }
    
    public void prepararParaVisualizacao() {
        setTitle("Visualização de Manobra");
        pnlBotoes.setVisible(false);
        btnEditarServico.setVisible(false);
        btnRemoverServico.setVisible(false);
        btnAdicionarServico.setVisible(false);
        DesktopUtil.habilitarComponentes(false, painelManobras );
        DesktopUtil.habilitarComponentes(false, painelRegistro);
        model.setModoVisualizacao(true);
    }
    
    public void prepararParaRegistro() {
        setTitle("Registro de Manobra");
        btnCancelarForaDoPrazo.setVisible(false);
        btnVisualizar.setVisible(false);
    }
    
    public void prepararParaCancelamento(){
        setTitle("Cancelamento Fora de Prazo");
        btnRegistrar.setVisible(false);
        btnVisualizar.setVisible(false);
        model.setModoCancelamentoForaDoPrazo(true);
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
        bindingGroup = new BindingGroup();

        GenericAction actionAdicionar = new GenericAction();
        GenericAction actionRegistrar = new GenericAction();
        GenericAction actionExcluir = new GenericAction();
        GenericAction actionEditar = new GenericAction();
        GenericAction actionCancelarForaDoPrazo = new GenericAction();
        GenericAction actionVisualizar = new GenericAction();
        SistamDateCellRenderer sistamDateCellRenderer1 = new SistamDateCellRenderer();
        GenericAction actionMovimentar = new GenericAction();
        SPanel pnlCabecalho = new SPanel();
        SLabel sLabel7 = new SLabel();
        SLabel sLabel8 = new SLabel();
        SLabel sLabel3 = new SLabel();
        SLabel sLabel9 = new SLabel();
        SLabel sLabel1 = new SLabel();
        timeZoneComboBox = new TimeZoneComboBox();
        pnlBotoes = new SPanel();
        btnRegistrar = new SButton();
        btnCancelarForaDoPrazo = new SButton();
        btnRegistrar1 = new SButton();
        SPanel pnlTipoServico = new SPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        tblServicosManobra = new STable();
        btnEditarServico = new SButton();
        btnRemoverServico = new SButton();
        btnAdicionarServico = new SButton();
        btnVisualizar = new SButton();
        painelManobras = new SPanel();
        labelFinalidades = new SLabel();
        cboFinalidade = new SComboBox();
        SLabel sLabel6 = new SLabel();
        JScrollPane jScrollPane2 = new JScrollPane();
        STextArea txtObservacao = new STextArea();
        cboPontoIncial = new SComboBox();
        lblPontoIncial = new SLabel();
        lblPontiFinal = new SLabel();
        cboPontoFinal = new SComboBox();
        lblPontiFinal1 = new SLabel();
        lblPontiFinal2 = new SLabel();
        SNumericTextField sNumericTextField1 = new SNumericTextField();
        SNumericTextField sNumericTextField2 = new SNumericTextField();
        cboEscala = new SComboBox();
        lblEscala = new SLabel();
        lblResponsavelCusto = new SLabel();
        cboResponsavelCusto = new SComboBox();
        painelRegistro = new SPanel();
        SLabel labelDataPartida = new SLabel();
        SistamDateChooser dtPartida = new SistamDateChooser();
        SistamTimeChooser horaPartida = new SistamTimeChooser();
        SLabel lblTermino = new SLabel();
        SLabel labelDataTermino = new SLabel();
        SistamDateChooser dtChegada = new SistamDateChooser();
        SistamDateChooser dtTermino = new SistamDateChooser();
        SistamTimeChooser horaChegada = new SistamTimeChooser();
        SistamTimeChooser horaTermino = new SistamTimeChooser();
        SLabel labelDataInicio = new SLabel();
        SistamDateChooser dtInicio = new SistamDateChooser();
        SistamTimeChooser horaInicio = new SistamTimeChooser();
        SLabel lblInicioOperacao = new SLabel();
        SLabel lblFimOperacao = new SLabel();
        SistamDateChooser dtFimOperacao = new SistamDateChooser();
        SistamDateChooser dtInicioOperacao = new SistamDateChooser();
        SistamTimeChooser horaInicioOperacao = new SistamTimeChooser();
        SistamTimeChooser horaFimOperacao = new SistamTimeChooser();

        actionAdicionar.setIcon(new ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionar.setMethodName("adicionarServico");
        actionAdicionar.setTarget(this);
        actionAdicionar.setText("Adicionar");
        actionAdicionar.setToolTipText("Adiciona um novo serviço para a manobra");

        Binding binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.tipoServicoSelecionado}"), actionAdicionar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionRegistrar.setIcon(new ImageIcon(getClass().getResource("/icons/concluir.png"))); // NOI18N
        actionRegistrar.setMethodName("registrar");
        actionRegistrar.setTarget(this);
        actionRegistrar.setText("Registrar");
        actionRegistrar.setToolTipText("Registra a manobra");

        actionExcluir.setConfirm("Deseja realmente excluir o serviço selecionado e seus responsáveis?");
        actionExcluir.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluir.setMethodName("excluirServico");
        actionExcluir.setTarget(model);
        actionExcluir.setText("Excluir");
        actionExcluir.setToolTipText("Excluir o serviço selecionado");

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.servicoManobraSelecionado}"), actionExcluir, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionEditar.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditar.setMethodName("editarServico");
        actionEditar.setTarget(this);
        actionEditar.setText("Editar");
        actionEditar.setToolTipText("Edita o serviço selecionado");

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.servicoManobraSelecionado}"), actionEditar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionCancelarForaDoPrazo.setIcon(new ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        actionCancelarForaDoPrazo.setMethodName("cancelarForaDoPrazoESair");
        actionCancelarForaDoPrazo.setTarget(this);
        actionCancelarForaDoPrazo.setText("Cancelar Fora do Prazo");
        actionCancelarForaDoPrazo.setToolTipText("Registra a manobra como Cancelada Fora do Prazo");

        actionVisualizar.setIcon(new ImageIcon(getClass().getResource("/icons/find.png"))); // NOI18N
        actionVisualizar.setMethodName("visualizarServico");
        actionVisualizar.setTarget(this);
        actionVisualizar.setText("Visualizar");
        actionVisualizar.setToolTipText("Visualiza o serviço selecionado");

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.servicoManobraSelecionado}"), actionVisualizar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        sistamDateCellRenderer1.setPattern("dd/MM/yyyy HH:mm");

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.timeZone}"), sistamDateCellRenderer1, BeanProperty.create("timeZone"));
        bindingGroup.addBinding(binding);

        actionMovimentar.setConfirm("Deseja realmente movimentar a embarcação?");
        actionMovimentar.setIcon(new ImageIcon(getClass().getResource("/icons/escalas.png"))); // NOI18N
        actionMovimentar.setMethodName("movimentar");
        actionMovimentar.setTarget(this);
        actionMovimentar.setText("Movimentar");
        actionMovimentar.setToolTipText("Movimentar a embarcação");

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarBotaoMovimentar}"), actionMovimentar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ResourceBundle bundle = ResourceBundle.getBundle("sistam-labels"); // NOI18N
        setTitle(bundle.getString("programacaoManobra")); // NOI18N

        pnlCabecalho.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        pnlCabecalho.setName("pnlCabecalho"); // NOI18N

        sLabel7.setText(bundle.getString("lblNome")); // NOI18N
        sLabel7.setName("sLabel7"); // NOI18N

        sLabel8.setText(bundle.getString("lblViagem")); // NOI18N
        sLabel8.setName("sLabel8"); // NOI18N

        sLabel3.setName("sLabel3"); // NOI18N
        sLabel3.setRequiredField(true);

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.agenciamento.embarcacao.nomeCompleto}"), sLabel3, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        sLabel9.setName("sLabel9"); // NOI18N
        sLabel9.setRequiredField(true);

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.agenciamento.vgm}"), sLabel9, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        sLabel1.setText(bundle.getString("fusoHorario")); // NOI18N
        sLabel1.setName("sLabel1"); // NOI18N

        timeZoneComboBox.setEnabled(false);
        timeZoneComboBox.setName("timeZoneComboBox"); // NOI18N

        GroupLayout pnlCabecalhoLayout = new GroupLayout(pnlCabecalho);
        pnlCabecalho.setLayout(pnlCabecalhoLayout);
        pnlCabecalhoLayout.setHorizontalGroup(
            pnlCabecalhoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(pnlCabecalhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sLabel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(sLabel3, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(sLabel8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(sLabel9, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(timeZoneComboBox, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCabecalhoLayout.setVerticalGroup(
            pnlCabecalhoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, pnlCabecalhoLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCabecalhoLayout.createParallelGroup(Alignment.TRAILING)
                    .addGroup(pnlCabecalhoLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(sLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(timeZoneComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCabecalhoLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(sLabel9, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlCabecalhoLayout.createParallelGroup(Alignment.LEADING, false)
                            .addComponent(sLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(Alignment.TRAILING, pnlCabecalhoLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(sLabel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sLabel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        pnlBotoes.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pnlBotoes.setName("pnlBotoes"); // NOI18N

        btnRegistrar.setAction(actionRegistrar);
        btnRegistrar.setName("btnRegistrar"); // NOI18N
        pnlBotoes.add(btnRegistrar);

        btnCancelarForaDoPrazo.setAction(actionCancelarForaDoPrazo);
        btnCancelarForaDoPrazo.setName("btnCancelarForaDoPrazo"); // NOI18N
        pnlBotoes.add(btnCancelarForaDoPrazo);

        btnRegistrar1.setAction(actionMovimentar);
        btnRegistrar1.setName("btnRegistrar1"); // NOI18N
        pnlBotoes.add(btnRegistrar1);

        pnlTipoServico.setBorder(BorderFactory.createTitledBorder("Lista de Serviços"));
        pnlTipoServico.setName("pnlTipoServico"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblServicosManobra.setName("tblServicosManobra"); // NOI18N

        ELProperty eLProperty = ELProperty.create("${model.manobra.servicos}");
        JTableBinding jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, this, eLProperty, tblServicosManobra);
        ColumnBinding columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${tipoDoServico}"));
        columnBinding.setColumnName("Tipo");
        columnBinding.setColumnClass(TipoServico.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${empresaMaritima}"));
        columnBinding.setColumnName("Empresa");
        columnBinding.setColumnClass(EmpresaMaritima.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${dataProgramada}"));
        columnBinding.setColumnName("Data Programada");
        columnBinding.setColumnClass(Date.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoManobraSelecionado}"), tblServicosManobra, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(tblServicosManobra);
        tblServicosManobra.getColumnModel().getColumn(2).setCellRenderer(sistamDateCellRenderer1);

        btnEditarServico.setAction(actionEditar);
        btnEditarServico.setName("btnEditarServico"); // NOI18N

        btnRemoverServico.setAction(actionExcluir);
        btnRemoverServico.setName("btnRemoverServico"); // NOI18N

        btnAdicionarServico.setAction(actionAdicionar);
        btnAdicionarServico.setName("btnAdicionarServico"); // NOI18N

        btnVisualizar.setAction(actionVisualizar);
        btnVisualizar.setName("btnVisualizar"); // NOI18N

        GroupLayout pnlTipoServicoLayout = new GroupLayout(pnlTipoServico);
        pnlTipoServico.setLayout(pnlTipoServicoLayout);
        pnlTipoServicoLayout.setHorizontalGroup(
            pnlTipoServicoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(pnlTipoServicoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTipoServicoLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                    .addGroup(Alignment.TRAILING, pnlTipoServicoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdicionarServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(btnEditarServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(btnRemoverServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(btnVisualizar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlTipoServicoLayout.setVerticalGroup(
            pnlTipoServicoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, pnlTipoServicoLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(pnlTipoServicoLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(pnlTipoServicoLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnRemoverServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVisualizar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTipoServicoLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnEditarServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdicionarServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        );

        painelManobras.setBorder(BorderFactory.createTitledBorder("Informações da Manobra"));
        painelManobras.setName("painelManobras"); // NOI18N

        labelFinalidades.setHorizontalAlignment(SwingConstants.RIGHT);
        labelFinalidades.setText(bundle.getString("lblFinalidade")); // NOI18N
        labelFinalidades.setName("labelFinalidades"); // NOI18N

        cboFinalidade.setName("cboFinalidade"); // NOI18N
        cboFinalidade.setPopupResizeEnabled(true);

        eLProperty = ELProperty.create("${model.finalidades}");
        JComboBoxBinding jComboBoxBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, this, eLProperty, cboFinalidade);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.finalidadeManobra}"), cboFinalidade, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        sLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
        sLabel6.setText(bundle.getString("lblObservacao")); // NOI18N
        sLabel6.setName("sLabel6"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtObservacao.setColumns(20);
        txtObservacao.setDocument(new GenericDocument(2000) );
        txtObservacao.setLineWrap(true);
        txtObservacao.setRows(2);
        txtObservacao.setTabSize(4);
        txtObservacao.setWrapStyleWord(true);
        txtObservacao.setName("txtObservacao"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.observacaoInterna}"), txtObservacao, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane2.setViewportView(txtObservacao);

        cboPontoIncial.setDisplayProperty("nomeFormatado");
        cboPontoIncial.setName("cboPontoIncial"); // NOI18N
        cboPontoIncial.setPopupResizeEnabled(true);

        eLProperty = ELProperty.create("${model.pontosAtracacao}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, this, eLProperty, cboPontoIncial);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.pontoAtracacaoOrigem}"), cboPontoIncial, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        lblPontoIncial.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPontoIncial.setText(bundle.getString("lblPontoInicial")); // NOI18N
        lblPontoIncial.setName("lblPontoIncial"); // NOI18N

        lblPontiFinal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPontiFinal.setText(bundle.getString("lblPontoFinal")); // NOI18N
        lblPontiFinal.setName("lblPontiFinal"); // NOI18N

        cboPontoFinal.setDisplayProperty("nomeFormatado");
        cboPontoFinal.setName("cboPontoFinal"); // NOI18N
        cboPontoFinal.setPopupResizeEnabled(true);

        eLProperty = ELProperty.create("${model.pontosAtracacao}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, this, eLProperty, cboPontoFinal);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.pontoAtracacaoDestino}"), cboPontoFinal, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        lblPontiFinal1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPontiFinal1.setText(bundle.getString("lblCaladoRe")); // NOI18N
        lblPontiFinal1.setName("lblPontiFinal1"); // NOI18N

        lblPontiFinal2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPontiFinal2.setText(bundle.getString("lblCaladoVante")); // NOI18N
        lblPontiFinal2.setName("lblPontiFinal2"); // NOI18N

        sNumericTextField1.setIntegerPlaces(new Short((short)2));
        sNumericTextField1.setNumberClass(Double.class);
        sNumericTextField1.setText("sNumericTextField1");
        sNumericTextField1.setName("sNumericTextField1"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.caladoRe}"), sNumericTextField1, BeanProperty.create("doubleValue"));
        bindingGroup.addBinding(binding);

        sNumericTextField2.setIntegerPlaces(new Short((short)2));
        sNumericTextField2.setNumberClass(Double.class);
        sNumericTextField2.setText("sNumericTextField1");
        sNumericTextField2.setName("sNumericTextField2"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.caladoVante}"), sNumericTextField2, BeanProperty.create("doubleValue"));
        bindingGroup.addBinding(binding);

        cboEscala.setDisplayProperty("nomeFormatado");
        cboEscala.setName("cboEscala"); // NOI18N
        cboEscala.setPopupResizeEnabled(true);

        eLProperty = ELProperty.create("${model.pontosAtracacao}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, this, eLProperty, cboEscala);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.pontoAtracacaoEscala}"), cboEscala, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        lblEscala.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEscala.setText(bundle.getString("lblEscala")); // NOI18N
        lblEscala.setName("lblEscala"); // NOI18N

        lblResponsavelCusto.setHorizontalAlignment(SwingConstants.RIGHT);
        lblResponsavelCusto.setText("Responsável pelo custo:");
        lblResponsavelCusto.setName("lblResponsavelCusto"); // NOI18N

        cboResponsavelCusto.setName("cboResponsavelCusto"); // NOI18N
        cboResponsavelCusto.setPopupResizeEnabled(true);

        eLProperty = ELProperty.create("${model.listaResponsavelCusto}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, this, eLProperty, cboResponsavelCusto);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.responsavelCusto}"), cboResponsavelCusto, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        GroupLayout painelManobrasLayout = new GroupLayout(painelManobras);
        painelManobras.setLayout(painelManobrasLayout);
        painelManobrasLayout.setHorizontalGroup(
            painelManobrasLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelManobrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelManobrasLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(labelFinalidades, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPontoIncial, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPontiFinal, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEscala, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResponsavelCusto, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPontiFinal2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel6, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(painelManobrasLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(painelManobrasLayout.createSequentialGroup()
                        .addGroup(painelManobrasLayout.createParallelGroup(Alignment.LEADING)
                            .addGroup(painelManobrasLayout.createSequentialGroup()
                                .addGroup(painelManobrasLayout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(cboPontoFinal, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                                    .addComponent(cboPontoIncial, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboFinalidade, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboEscala, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(365, 365, 365))
                            .addComponent(jScrollPane2))
                        .addContainerGap())
                    .addGroup(painelManobrasLayout.createSequentialGroup()
                        .addComponent(cboResponsavelCusto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(375, 375, 375))
                    .addGroup(painelManobrasLayout.createSequentialGroup()
                        .addComponent(sNumericTextField2, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(lblPontiFinal1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(sNumericTextField1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        painelManobrasLayout.setVerticalGroup(
            painelManobrasLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelManobrasLayout.createSequentialGroup()
                .addGroup(painelManobrasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labelFinalidades, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboFinalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(painelManobrasLayout.createParallelGroup(Alignment.CENTER)
                    .addComponent(lblPontoIncial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPontoIncial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(painelManobrasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(cboPontoFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPontiFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(painelManobrasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(cboEscala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEscala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(painelManobrasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(cboResponsavelCusto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResponsavelCusto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelManobrasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lblPontiFinal2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sNumericTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPontiFinal1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sNumericTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(painelManobrasLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(sLabel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        painelRegistro.setBorder(BorderFactory.createTitledBorder("Informações do Registro da Manobra"));
        painelRegistro.setName("painelRegistro"); // NOI18N

        labelDataPartida.setText(bundle.getString("lblDtPartida")); // NOI18N
        labelDataPartida.setName("labelDataPartida"); // NOI18N

        dtPartida.setAutoscrolls(false);
        dtPartida.setName("dtPartida"); // NOI18N
        dtPartida.setTimeChooser(horaPartida);
        dtPartida.setTimeZoneComboBox(timeZoneComboBox);

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.dataPartidaOrigem}"), dtPartida, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarCampoNaEdicao}"), dtPartida, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        horaPartida.setAutoscrolls(false);
        horaPartida.setName("horaPartida"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarCampoNaEdicao}"), horaPartida, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        lblTermino.setText(bundle.getString("lblDtChegada")); // NOI18N
        lblTermino.setName("lblTermino"); // NOI18N

        labelDataTermino.setText(bundle.getString("lblTerminoManobra")); // NOI18N
        labelDataTermino.setName("labelDataTermino"); // NOI18N

        dtChegada.setToolTipText("");
        dtChegada.setAutoscrolls(false);
        dtChegada.setName("dtChegada"); // NOI18N
        dtChegada.setTimeChooser(horaChegada);
        dtChegada.setTimeZoneComboBox(timeZoneComboBox);

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.dataChegadaDestino}"), dtChegada, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarCampoNaEdicao}"), dtChegada, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        dtTermino.setAutoscrolls(false);
        dtTermino.setName("dtTermino"); // NOI18N
        dtTermino.setTimeChooser(horaTermino);
        dtTermino.setTimeZoneComboBox(timeZoneComboBox);

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.dataTermino}"), dtTermino, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarCampoNaEdicao}"), dtTermino, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        horaChegada.setAutoscrolls(false);
        horaChegada.setName("horaChegada"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarCampoNaEdicao}"), horaChegada, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        horaTermino.setAutoscrolls(false);
        horaTermino.setName("horaTermino"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarCampoNaEdicao}"), horaTermino, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        labelDataInicio.setText(bundle.getString("lblDtInicioManobra")); // NOI18N
        labelDataInicio.setName("labelDataInicio"); // NOI18N

        dtInicio.setAutoscrolls(false);
        dtInicio.setName("dtInicio"); // NOI18N
        dtInicio.setTimeChooser(horaInicio);
        dtInicio.setTimeZoneComboBox(timeZoneComboBox);

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.dataInicio}"), dtInicio, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarCampoNaEdicao}"), dtInicio, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        horaInicio.setAutoscrolls(false);
        horaInicio.setName("horaInicio"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarCampoNaEdicao}"), horaInicio, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        lblInicioOperacao.setText(bundle.getString("lblInicioOperacao")); // NOI18N
        lblInicioOperacao.setName("lblInicioOperacao"); // NOI18N

        lblFimOperacao.setText(bundle.getString("lblFimOperacao")); // NOI18N
        lblFimOperacao.setName("lblFimOperacao"); // NOI18N

        dtFimOperacao.setAutoscrolls(false);
        dtFimOperacao.setName("dtFimOperacao"); // NOI18N
        dtFimOperacao.setTimeChooser(horaFimOperacao);
        dtFimOperacao.setTimeZoneComboBox(timeZoneComboBox);

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.dataFimOperacao}"), dtFimOperacao, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarCampoNaEdicao}"), dtFimOperacao, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        dtInicioOperacao.setAutoscrolls(false);
        dtInicioOperacao.setName("dtInicioOperacao"); // NOI18N
        dtInicioOperacao.setTimeChooser(horaInicioOperacao);
        dtInicioOperacao.setTimeZoneComboBox(timeZoneComboBox);

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.dataInicioOperacao}"), dtInicioOperacao, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarCampoNaEdicao}"), dtInicioOperacao, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        horaInicioOperacao.setAutoscrolls(false);
        horaInicioOperacao.setName("horaInicioOperacao"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarCampoNaEdicao}"), horaInicioOperacao, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        horaFimOperacao.setAutoscrolls(false);
        horaFimOperacao.setName("horaFimOperacao"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarCampoNaEdicao}"), horaFimOperacao, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        GroupLayout painelRegistroLayout = new GroupLayout(painelRegistro);
        painelRegistro.setLayout(painelRegistroLayout);
        painelRegistroLayout.setHorizontalGroup(
            painelRegistroLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelRegistroLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(labelDataPartida, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTermino, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(painelRegistroLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(painelRegistroLayout.createSequentialGroup()
                        .addComponent(dtPartida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(horaPartida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelRegistroLayout.createSequentialGroup()
                        .addComponent(dtChegada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(horaChegada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(painelRegistroLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(labelDataInicio, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataTermino, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(painelRegistroLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(Alignment.TRAILING, painelRegistroLayout.createSequentialGroup()
                        .addComponent(dtTermino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(horaTermino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(Alignment.TRAILING, painelRegistroLayout.createSequentialGroup()
                        .addComponent(dtInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(horaInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(painelRegistroLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(lblInicioOperacao, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFimOperacao, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(painelRegistroLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(Alignment.TRAILING, painelRegistroLayout.createSequentialGroup()
                        .addComponent(dtFimOperacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(horaFimOperacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(Alignment.TRAILING, painelRegistroLayout.createSequentialGroup()
                        .addComponent(dtInicioOperacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(horaInicioOperacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        painelRegistroLayout.setVerticalGroup(
            painelRegistroLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelRegistroLayout.createSequentialGroup()
                .addGroup(painelRegistroLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labelDataPartida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtPartida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(horaPartida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(painelRegistroLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(dtChegada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(horaChegada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTermino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
            .addGroup(painelRegistroLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(painelRegistroLayout.createSequentialGroup()
                    .addGroup(painelRegistroLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblInicioOperacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(dtInicioOperacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(horaInicioOperacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(painelRegistroLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblFimOperacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(dtFimOperacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(horaFimOperacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGroup(painelRegistroLayout.createSequentialGroup()
                    .addGroup(painelRegistroLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(labelDataInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(dtInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(horaInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(painelRegistroLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(labelDataTermino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(dtTermino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(horaTermino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(pnlCabecalho, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBotoes, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(painelManobras, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTipoServico, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelRegistro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCabecalho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(painelManobras, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(painelRegistro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(pnlTipoServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(pnlBotoes, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //CHECKSTYLE:ON

    // Variables declaration - do not modify//GEN-BEGIN:variables
    SButton btnAdicionarServico;
    SButton btnCancelarForaDoPrazo;
    SButton btnEditarServico;
    SButton btnRegistrar;
    SButton btnRegistrar1;
    SButton btnRemoverServico;
    SButton btnVisualizar;
    SComboBox cboEscala;
    SComboBox cboFinalidade;
    SComboBox cboPontoFinal;
    SComboBox cboPontoIncial;
    SComboBox cboResponsavelCusto;
    SLabel labelFinalidades;
    SLabel lblEscala;
    SLabel lblPontiFinal;
    SLabel lblPontiFinal1;
    SLabel lblPontiFinal2;
    SLabel lblPontoIncial;
    SLabel lblResponsavelCusto;
    SPanel painelManobras;
    SPanel painelRegistro;
    SPanel pnlBotoes;
    STable tblServicosManobra;
    TimeZoneComboBox timeZoneComboBox;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
