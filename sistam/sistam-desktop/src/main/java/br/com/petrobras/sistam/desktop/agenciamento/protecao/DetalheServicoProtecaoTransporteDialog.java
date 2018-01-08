package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.SDialog;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SComboBox;
import br.com.petrobras.fcorp.swing.components.SLabel;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.STable;
import br.com.petrobras.fcorp.swing.components.STextArea;
import br.com.petrobras.fcorp.swing.components.STextField;
import br.com.petrobras.fcorp.swing.components.renderers.ImageBooleanRenderer;
import br.com.petrobras.fcorp.swing.components.util.GenericDocument;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.entity.Passageiro;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.components.SistamDateChooser;
import br.com.petrobras.sistam.desktop.components.SistamTimeChooser;
import br.com.petrobras.sistam.desktop.components.TimeZoneComboBox;
import br.com.petrobras.sistam.desktop.util.DesktopUtil;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

public class DetalheServicoProtecaoTransporteDialog extends SDialog implements PropertyChangeListener{

    private DetalheServicoProtecaoTransporteModel model;

    public DetalheServicoProtecaoTransporteDialog(java.awt.Frame parent, ServicoTransporte servicoTransporte, boolean editar) {
        super(parent, true);
        model = new DetalheServicoProtecaoTransporteModel(servicoTransporte);
        model.setDialog(this);
        model.setEditar(editar);
        
        initComponents();
        setLocationRelativeTo(parent);
        
        timeZoneComboBox1.setSelectedItem(servicoTransporte.getServicoProtecao().getAgenciamento().getAgencia().obterTimezone());

        if(model.getServicoTransporte().getId()!=null){
            visualizarCampos(model.getServicoTransporte().getTipoTransporte());
        }
        if (!editar) {
            DesktopUtil.habilitarComponentes(editar, this.pnlEscalas);
        }

        comboTransporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizarCampos((TipoTransporte)comboTransporte.getSelectedItem());
            }
        });
    }

    public DetalheServicoProtecaoTransporteModel getModel() {
        return model;
    }

    public void salvar() {
        model.salvar();
        dispose();
    }

    private void visualizarCampos(TipoTransporte tipoSelecionado) {
        
        if (tipoSelecionado!=null && tipoSelecionado.equals(TipoTransporte.AEREO)) {
            DesktopUtil.visualizarComponentes(false, pnlEscalas, labelAgente, txtServicoExecutado, lblDatasolicitacao,  sistamDateChooserInicio, labelTransporte, comboTransporte, labelObservacao, txtObservacao, scrollObservacao, labelDataTransporte, sistamDateTransporte, labelHoraTransporte, sistamTimeTransporte, pnlBotoes, btnSalvar, btnEditar, btnAdicionar, btnExcluir, btnAtivarDesativar);
        } else {
            DesktopUtil.visualizarComponentes(true, pnlEscalas);
        }
    }
    
    public void adicionarPassageiro() {
        Passageiro tripulante = model.obterNovoPassageiro();
        RegistroPassageiroDialog dialog = new RegistroPassageiroDialog(SistamApp.getSistamApp().getMainFrame(), tripulante);
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);
    }

    public void editarPassageiro() {
        Passageiro passageiro = model.obterPassageiroParaEdicao();
        RegistroPassageiroDialog dialog = new RegistroPassageiroDialog(SistamApp.getApplication().getMainFrame(), passageiro);
        dialog.getModel().setEdicao(true);
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(RegistroPassageiroDialog.PASSAGEIRO_INSERIDO)) {
            Passageiro passageiro = ((RegistroPassageiroDialog) evt.getSource()).getModel().getPassageiro();
            model.adicionarPassageiro(passageiro);
        }

        if (evt.getPropertyName().equals(RegistroPassageiroDialog.PASSAGEIRO_ALTERADO)) {
            Passageiro passageiro = ((RegistroPassageiroDialog) evt.getSource()).getModel().getPassageiro();
            model.atualizarPassageiro(passageiro);
        }
    }

    public void ativarDesativar() {
        if (model.getPassageiroSelecionado()==null || model.getPassageiroSelecionado().isAtivo()) {
            btnAtivarDesativar.setAction(actionCancelar);
        } else {
            btnAtivarDesativar.setAction(actionAtivar);
        }
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
        bindingGroup = new BindingGroup();

        GenericAction actionSalvar = new GenericAction();
        actionCancelar = new GenericAction();
        actionAtivar = new GenericAction();
        GenericAction actionExcluir = new GenericAction();
        GenericAction actionEditar = new GenericAction();
        GenericAction actionAdicionar = new GenericAction();
        ImageBooleanRenderer imageBooleanAtivarDesativar = new ImageBooleanRenderer();
        timeZoneComboBox1 = new TimeZoneComboBox();
        pnlEscalas = new SPanel();
        SPanel pnlManobras = new SPanel();
        labelObservacao = new SLabel();
        scrollObservacao = new JScrollPane();
        txtObservacao = new STextArea();
        labelAgente = new SLabel();
        lblDatasolicitacao = new SLabel();
        sistamDateChooserInicio = new SistamDateChooser();
        labelTransporte = new SLabel();
        comboTransporte = new SComboBox();
        labelEmpresa = new SLabel();
        labelDataTransporte = new SLabel();
        labelHoraTransporte = new SLabel();
        txtAutorizacao = new STextField();
        sistamDateTransporte = new SistamDateChooser();
        sistamTimeTransporte = new SistamTimeChooser();
        labelAutorizacao = new SLabel();
        labelOrigem = new SLabel();
        txtOrigem = new STextField();
        labelDestino = new SLabel();
        txtDestino = new STextField();
        labelVoo = new SLabel();
        txtVoo = new STextField();
        txtServicoExecutado = new STextField();
        comboEmpresa = new SComboBox();
        panelTripulantes = new SPanel();
        scrollTripulante = new JScrollPane();
        tabelaTripulante = new STable();
        btnAdicionar = new SButton();
        btnEditar = new SButton();
        btnAtivarDesativar = new SButton();
        btnExcluir = new SButton();
        pnlBotoes = new SPanel();
        btnSalvar = new SButton();

        actionSalvar.setIcon(new ImageIcon(getClass().getResource("/icons/disk.png"))); // NOI18N
        actionSalvar.setMethodName("salvar");
        actionSalvar.setTarget(this);
        actionSalvar.setText("Salvar");
        actionSalvar.setToolTipText("Salva as informações do serviço de proteção");

        actionCancelar.setIcon(new ImageIcon(getClass().getResource("/icons/listagem_fechar.png"))); // NOI18N
        actionCancelar.setMethodName("ativarCancelarPassageiro");
        actionCancelar.setTarget(model);
        actionCancelar.setText("Cancelar");
        actionCancelar.setToolTipText("cancela passageiro");

        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.passageiroSelecionado && model.passageiroSelecionado.id!=null && model.editar}"), actionCancelar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionAtivar.setIcon(new ImageIcon(getClass().getResource("/icons/concluir.png"))); // NOI18N
        actionAtivar.setMethodName("ativarCancelarPassageiro");
        actionAtivar.setTarget(model);
        actionAtivar.setText("Ativar");
        actionAtivar.setToolTipText("Ativa passageiro");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.passageiroSelecionado && model.passageiroSelecionado.id!=null && model.editar}"), actionAtivar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionExcluir.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluir.setMethodName("excluirPassageiro");
        actionExcluir.setTarget(model);
        actionExcluir.setText("Excluir");
        actionExcluir.setToolTipText("Exclui Passageiro");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.passageiroSelecionado && model.passageiroSelecionado.id==null}"), actionExcluir, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionEditar.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditar.setMethodName("editarPassageiro");
        actionEditar.setTarget(this);
        actionEditar.setText("Editar");
        actionEditar.setToolTipText("Edita dados do passageiro");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.passageiroSelecionado && model.editar}"), actionEditar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionAdicionar.setIcon(new ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionar.setMethodName("adicionarPassageiro");
        actionAdicionar.setTarget(this);
        actionAdicionar.setText("Adicionar");
        actionAdicionar.setToolTipText("Adiciona passageiro");

        imageBooleanAtivarDesativar.setFalseIcon(new ImageIcon(getClass().getResource("/icons/listagem_fechar.png"))); // NOI18N
        imageBooleanAtivarDesativar.setTrueIcon(new ImageIcon(getClass().getResource("/icons/concluir.png"))); // NOI18N

        timeZoneComboBox1.setName("timeZoneComboBox1"); // NOI18N

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Serviço de Proteção");

        pnlEscalas.setName("pnlEscalas"); // NOI18N

        pnlManobras.setBorder(BorderFactory.createTitledBorder(""));
        pnlManobras.setName("pnlManobras"); // NOI18N

        labelObservacao.setText("Observação:");
        labelObservacao.setName("labelObservacao"); // NOI18N

        scrollObservacao.setName("scrollObservacao"); // NOI18N

        txtObservacao.setColumns(20);
        txtObservacao.setDocument(new GenericDocument(512));
        txtObservacao.setLineWrap(true);
        txtObservacao.setRows(3);
        txtObservacao.setWrapStyleWord(true);
        txtObservacao.setName("txtObservacao"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoTransporte.servicoProtecao.observacao}"), txtObservacao, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        scrollObservacao.setViewportView(txtObservacao);

        labelAgente.setText("Serviço Executado:");
        labelAgente.setName("labelAgente"); // NOI18N

        lblDatasolicitacao.setText("Data da Solicitação:");
        lblDatasolicitacao.setName("lblDatasolicitacao"); // NOI18N

        sistamDateChooserInicio.setName("sistamDateChooserInicio"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoTransporte.servicoProtecao.dataExecucao}"), sistamDateChooserInicio, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        labelTransporte.setText("Transporte:");
        labelTransporte.setName("labelTransporte"); // NOI18N

        comboTransporte.setName("comboTransporte"); // NOI18N

        ELProperty eLProperty = ELProperty.create("${model.listaTipoTransporte}");
        JComboBoxBinding jComboBoxBinding = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, comboTransporte, "");
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoTransporte.tipoTransporte}"), comboTransporte, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoTransporte.id == null}"), comboTransporte, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        labelEmpresa.setText("Empresa:");
        labelEmpresa.setName("labelEmpresa"); // NOI18N

        labelDataTransporte.setText("Data:");
        labelDataTransporte.setName("labelDataTransporte"); // NOI18N

        labelHoraTransporte.setText("Hora:");
        labelHoraTransporte.setName("labelHoraTransporte"); // NOI18N

        txtAutorizacao.setDocument(new GenericDocument(200));
        txtAutorizacao.setName("txtAutorizacao"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoTransporte.autorizacao}"), txtAutorizacao, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        sistamDateTransporte.setName("sistamDateTransporte"); // NOI18N
        sistamDateTransporte.setTimeChooser(sistamTimeTransporte);
        sistamDateTransporte.setTimeZoneComboBox(timeZoneComboBox1);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoTransporte.dataServico}"), sistamDateTransporte, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        sistamTimeTransporte.setName("sistamTimeTransporte"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoTransporte.dataServico}"), sistamTimeTransporte, BeanProperty.create("time"));
        bindingGroup.addBinding(binding);

        labelAutorizacao.setText("Autorização:");
        labelAutorizacao.setName("labelAutorizacao"); // NOI18N

        labelOrigem.setText("Origem:");
        labelOrigem.setName("labelOrigem"); // NOI18N

        txtOrigem.setDocument(new GenericDocument(200));
        txtOrigem.setName("txtOrigem"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoTransporte.origem}"), txtOrigem, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        labelDestino.setText("Destino:");
        labelDestino.setName("labelDestino"); // NOI18N

        txtDestino.setDocument(new GenericDocument(200));
        txtDestino.setName("txtDestino"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoTransporte.destino}"), txtDestino, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        labelVoo.setText("Voo:");
        labelVoo.setName("labelVoo"); // NOI18N

        txtVoo.setDocument(new GenericDocument(100));
        txtVoo.setName("txtVoo"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoTransporte.voo}"), txtVoo, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        txtServicoExecutado.setEnabled(false);
        txtServicoExecutado.setName("txtServicoExecutado"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoExecutado}"), txtServicoExecutado, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        comboEmpresa.setDisplayProperty(Servico.PROP_EMPRESA);
        comboEmpresa.setName("comboEmpresa"); // NOI18N

        eLProperty = ELProperty.create("${model.empresasTransporte}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, comboEmpresa);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoTransporte.empresaServico}"), comboEmpresa, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        panelTripulantes.setBorder(BorderFactory.createTitledBorder("Passageiros"));
        panelTripulantes.setName("panelTripulantes"); // NOI18N

        scrollTripulante.setName("scrollTripulante"); // NOI18N

        tabelaTripulante.setSortable(true);
        tabelaTripulante.setName("tabelaTripulante"); // NOI18N
        tabelaTripulante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        eLProperty = ELProperty.create("${model.servicoTransporte.passageiros}");
        JTableBinding jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tabelaTripulante);
        JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${ativo}"));
        columnBinding.setColumnName("");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${nome}"));
        columnBinding.setColumnName("Nome");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${documento}"));
        columnBinding.setColumnName("RG/MAT/CIR/PASS");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${cpfComMascara}"));
        columnBinding.setColumnName("CPF");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.passageiroSelecionado}"), tabelaTripulante, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        scrollTripulante.setViewportView(tabelaTripulante);
        tabelaTripulante.getColumnModel().getColumn(0).setMaxWidth(20);
        tabelaTripulante.getColumnModel().getColumn(0).setCellRenderer(imageBooleanAtivarDesativar);

        btnAdicionar.setAction(actionAdicionar);
        btnAdicionar.setName("btnAdicionar"); // NOI18N

        btnEditar.setAction(actionEditar);
        btnEditar.setName("btnEditar"); // NOI18N

        btnAtivarDesativar.setAction(actionCancelar);
        btnAtivarDesativar.setName("btnAtivarDesativar"); // NOI18N

        btnExcluir.setAction(actionExcluir);
        btnExcluir.setName("btnExcluir"); // NOI18N

        GroupLayout panelTripulantesLayout = new GroupLayout(panelTripulantes);
        panelTripulantes.setLayout(panelTripulantesLayout);
        panelTripulantesLayout.setHorizontalGroup(
            panelTripulantesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelTripulantesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTripulantesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTripulante)
                    .addGroup(GroupLayout.Alignment.TRAILING, panelTripulantesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAtivarDesativar, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelTripulantesLayout.setVerticalGroup(
            panelTripulantesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelTripulantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollTripulante, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTripulantesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtivarDesativar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout pnlManobrasLayout = new GroupLayout(pnlManobras);
        pnlManobras.setLayout(pnlManobrasLayout);
        pnlManobrasLayout.setHorizontalGroup(
            pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlManobrasLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(labelVoo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataTransporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTransporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOrigem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAutorizacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAgente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnlManobrasLayout.createSequentialGroup()
                        .addComponent(txtServicoExecutado, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDatasolicitacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sistamDateChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
                    .addGroup(GroupLayout.Alignment.TRAILING, pnlManobrasLayout.createSequentialGroup()
                        .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtDestino, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAutorizacao, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtOrigem, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, pnlManobrasLayout.createSequentialGroup()
                        .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(comboTransporte, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtVoo, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.LEADING, pnlManobrasLayout.createSequentialGroup()
                                .addComponent(sistamDateTransporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelHoraTransporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sistamTimeTransporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(comboEmpresa, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10))))
            .addGroup(pnlManobrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panelTripulantes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollObservacao)
                    .addComponent(labelObservacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlManobrasLayout.setVerticalGroup(
            pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlManobrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAgente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDatasolicitacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sistamDateChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtServicoExecutado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTransporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTransporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(comboEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTripulantes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAutorizacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAutorizacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrigem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOrigem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(sistamDateTransporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sistamTimeTransporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataTransporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHoraTransporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelVoo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVoo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(labelObservacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollObservacao, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBotoes.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pnlBotoes.setName("pnlBotoes"); // NOI18N

        btnSalvar.setAction(actionSalvar);
        btnSalvar.setName("btnSalvar"); // NOI18N
        pnlBotoes.add(btnSalvar);

        GroupLayout pnlEscalasLayout = new GroupLayout(pnlEscalas);
        pnlEscalas.setLayout(pnlEscalasLayout);
        pnlEscalasLayout.setHorizontalGroup(
            pnlEscalasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlEscalasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlManobras, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(pnlBotoes, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlEscalasLayout.setVerticalGroup(
            pnlEscalasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlEscalasLayout.createSequentialGroup()
                .addComponent(pnlManobras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnlBotoes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlEscalas, BorderLayout.CENTER);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeTripulanteActionPerformed(ActionEvent evt) {//GEN-FIRST:event_txtNomeTripulanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeTripulanteActionPerformed
    //CHECKSTYLE:ON
    // Variables declaration - do not modify//GEN-BEGIN:variables
    GenericAction actionAtivar;
    GenericAction actionCancelar;
    SButton btnAdicionar;
    SButton btnAtivarDesativar;
    SButton btnEditar;
    SButton btnExcluir;
    SButton btnSalvar;
    SComboBox comboEmpresa;
    SComboBox comboTransporte;
    SLabel labelAgente;
    SLabel labelAutorizacao;
    SLabel labelDataTransporte;
    SLabel labelDestino;
    SLabel labelEmpresa;
    SLabel labelHoraTransporte;
    SLabel labelObservacao;
    SLabel labelOrigem;
    SLabel labelTransporte;
    SLabel labelVoo;
    SLabel lblDatasolicitacao;
    SPanel panelTripulantes;
    SPanel pnlBotoes;
    SPanel pnlEscalas;
    JScrollPane scrollObservacao;
    JScrollPane scrollTripulante;
    SistamDateChooser sistamDateChooserInicio;
    SistamDateChooser sistamDateTransporte;
    SistamTimeChooser sistamTimeTransporte;
    STable tabelaTripulante;
    TimeZoneComboBox timeZoneComboBox1;
    STextField txtAutorizacao;
    STextField txtDestino;
    STextArea txtObservacao;
    STextField txtOrigem;
    STextField txtServicoExecutado;
    STextField txtVoo;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
