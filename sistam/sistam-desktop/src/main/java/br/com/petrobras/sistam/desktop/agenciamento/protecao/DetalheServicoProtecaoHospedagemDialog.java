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
import br.com.petrobras.sistam.common.entity.Hospede;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.components.SistamDateChooser;
import br.com.petrobras.sistam.desktop.util.DesktopUtil;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.BorderLayout;
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

public class DetalheServicoProtecaoHospedagemDialog extends SDialog implements PropertyChangeListener {

    private DetalheServicoProtecaoHospedagemModel model;

    public DetalheServicoProtecaoHospedagemModel getModel() {
        return model;
    }
    
    public DetalheServicoProtecaoHospedagemDialog(java.awt.Frame parent, ServicoHospedagem servicoHospedagem, boolean editar) {
        super(parent, true);
        model = new DetalheServicoProtecaoHospedagemModel(servicoHospedagem);
        model.setDialog(this);
        initComponents();
        setLocationRelativeTo(parent);
        model.setEditar(editar);

        if (!editar) {
            DesktopUtil.habilitarComponentes(editar, this.pnlHospedagem);
        }
    }

    public void salvar() {
        model.salvar();
        dispose();
    }

    public void adicionarHospede() {
        Hospede hospede = model.obterNovoHospede();
        RegistroHospedeDialog dialog = new RegistroHospedeDialog(SistamApp.getSistamApp().getMainFrame(), hospede);
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);
    }

    public void editarHospede() {
        Hospede hospede = model.obterHospedeParaEdicao();
        RegistroHospedeDialog dialog = new RegistroHospedeDialog(SistamApp.getApplication().getMainFrame(), hospede);
        dialog.getModel().setEdicao(true);
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(RegistroHospedeDialog.HOSPEDE_INSERIDO)) {
            Hospede hospede = ((RegistroHospedeDialog) evt.getSource()).getModel().getHospede();
            model.adicionarHospede(hospede);
        }

        if (evt.getPropertyName().equals(RegistroHospedeDialog.HOSPEDE_ALTERADO)) {
            Hospede hospede = ((RegistroHospedeDialog) evt.getSource()).getModel().getHospede();
            model.atualizarHospede(hospede);
        }
    }

    public void ativarDesativar() {
        if (model.getHospedeSelecionado()==null || model.getHospedeSelecionado().isAtivo()) {
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

        ImageBooleanRenderer imageBooleanAtivarDesativar = new ImageBooleanRenderer();
        GenericAction actionSalvar = new GenericAction();
        actionCancelar = new GenericAction();
        actionAtivar = new GenericAction();
        GenericAction actionExcluir = new GenericAction();
        GenericAction actionEditar = new GenericAction();
        GenericAction actionAdicionar = new GenericAction();
        pnlHospedagem = new SPanel();
        SPanel pnlDados = new SPanel();
        SLabel sLabel6 = new SLabel();
        JScrollPane jScrollPane2 = new JScrollPane();
        STextArea txtObservacao = new STextArea();
        labelAgente = new SLabel();
        SLabel sLabel2 = new SLabel();
        SistamDateChooser sistamDateChooserInicio = new SistamDateChooser();
        lblHotel = new SLabel();
        labelHoraTransporte = new SLabel();
        dataChegada = new SistamDateChooser();
        labelPeriodo = new SLabel();
        STextField sTextField1 = new STextField();
        dataSaida = new SistamDateChooser();
        SPanel sPanel1 = new SPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        tabelaHospede = new STable();
        SButton btnAdicionar = new SButton();
        SButton btnEditar = new SButton();
        btnAtivarDesativar = new SButton();
        SButton btnExcluir = new SButton();
        labelPeriodo1 = new SLabel();
        txtAutorizacao = new STextField();
        cboHotel = new SComboBox();
        SPanel pnlBotoes = new SPanel();
        SButton btnSalvar = new SButton();

        imageBooleanAtivarDesativar.setFalseIcon(new ImageIcon(getClass().getResource("/icons/listagem_fechar.png"))); // NOI18N
        imageBooleanAtivarDesativar.setTrueIcon(new ImageIcon(getClass().getResource("/icons/concluir.png"))); // NOI18N

        actionSalvar.setIcon(new ImageIcon(getClass().getResource("/icons/disk.png"))); // NOI18N
        actionSalvar.setMethodName("salvar");
        actionSalvar.setTarget(this);
        actionSalvar.setText("Salvar");
        actionSalvar.setToolTipText("Salva as informações do serviço de proteção");

        actionCancelar.setIcon(new ImageIcon(getClass().getResource("/icons/listagem_fechar.png"))); // NOI18N
        actionCancelar.setMethodName("ativarCancelarHospede");
        actionCancelar.setTarget(model);
        actionCancelar.setText("Cancelar");
        actionCancelar.setToolTipText("cancela hospede");

        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.hospedeSelecionado && model.hospedeSelecionado.id!=null && model.editar}"), actionCancelar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionAtivar.setIcon(new ImageIcon(getClass().getResource("/icons/concluir.png"))); // NOI18N
        actionAtivar.setMethodName("ativarCancelarHospede");
        actionAtivar.setTarget(model);
        actionAtivar.setText("Ativar");
        actionAtivar.setToolTipText("Ativa hospede");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.hospedeSelecionado && model.hospedeSelecionado.id!=null && model.editar}"), actionAtivar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionExcluir.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluir.setMethodName("excluirHospede");
        actionExcluir.setTarget(model);
        actionExcluir.setText("Excluir");
        actionExcluir.setToolTipText("Exclui Hospede");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.hospedeSelecionado && model.hospedeSelecionado.id==null}"), actionExcluir, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionEditar.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditar.setMethodName("editarHospede");
        actionEditar.setTarget(this);
        actionEditar.setText("Editar");
        actionEditar.setToolTipText("Edita dados do hospede");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.hospedeSelecionado && model.editar}"), actionEditar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionAdicionar.setIcon(new ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionar.setMethodName("adicionarHospede");
        actionAdicionar.setTarget(this);
        actionAdicionar.setText("Adicionar");
        actionAdicionar.setToolTipText("Adiciona hospede");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Serviço de Proteção");

        pnlHospedagem.setName("pnlHospedagem"); // NOI18N

        pnlDados.setBorder(BorderFactory.createTitledBorder(""));
        pnlDados.setName("pnlDados"); // NOI18N

        sLabel6.setText("Observação:");
        sLabel6.setName("sLabel6"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtObservacao.setColumns(20);
        txtObservacao.setDocument(new GenericDocument(512));
        txtObservacao.setLineWrap(true);
        txtObservacao.setRows(3);
        txtObservacao.setWrapStyleWord(true);
        txtObservacao.setName("txtObservacao"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoHospedagem.servicoProtecao.observacao}"), txtObservacao, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane2.setViewportView(txtObservacao);

        labelAgente.setText("Serviço Executado:");
        labelAgente.setName("labelAgente"); // NOI18N

        sLabel2.setText("Data da Solicitação:");
        sLabel2.setName("sLabel2"); // NOI18N

        sistamDateChooserInicio.setName("sistamDateChooserInicio"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoHospedagem.servicoProtecao.dataExecucao}"), sistamDateChooserInicio, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        lblHotel.setText("Hotel:");
        lblHotel.setName("lblHotel"); // NOI18N

        labelHoraTransporte.setText("à");
        labelHoraTransporte.setName("labelHoraTransporte"); // NOI18N

        dataChegada.setName("dataChegada"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoHospedagem.dataChegada}"), dataChegada, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        labelPeriodo.setText("Período:");
        labelPeriodo.setName("labelPeriodo"); // NOI18N

        sTextField1.setEnabled(false);
        sTextField1.setName("sTextField1"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoExecutado}"), sTextField1, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        dataSaida.setName("dataSaida"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoHospedagem.dataSaida}"), dataSaida, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        sPanel1.setBorder(BorderFactory.createTitledBorder("Hóspedes"));
        sPanel1.setName("sPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tabelaHospede.setSortable(true);
        tabelaHospede.setName("tabelaHospede"); // NOI18N
        tabelaHospede.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ELProperty eLProperty = ELProperty.create("${model.servicoHospedagem.hospedes}");
        JTableBinding jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tabelaHospede);
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
        jTableBinding.bind();binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.hospedeSelecionado}"), tabelaHospede, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(tabelaHospede);
        tabelaHospede.getColumnModel().getColumn(0).setMaxWidth(20);
        tabelaHospede.getColumnModel().getColumn(0).setCellRenderer(imageBooleanAtivarDesativar);

        btnAdicionar.setAction(actionAdicionar);
        btnAdicionar.setName("btnAdicionar"); // NOI18N

        btnEditar.setAction(actionEditar);
        btnEditar.setName("btnEditar"); // NOI18N

        btnAtivarDesativar.setAction(actionCancelar);
        btnAtivarDesativar.setName("btnAtivarDesativar"); // NOI18N

        btnExcluir.setAction(actionExcluir);
        btnExcluir.setName("btnExcluir"); // NOI18N

        GroupLayout sPanel1Layout = new GroupLayout(sPanel1);
        sPanel1.setLayout(sPanel1Layout);
        sPanel1Layout.setHorizontalGroup(
            sPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(sPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, sPanel1Layout.createSequentialGroup()
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
        sPanel1Layout.setVerticalGroup(
            sPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(sPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtivarDesativar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelPeriodo1.setText("Autorização:");
        labelPeriodo1.setName("labelPeriodo1"); // NOI18N

        txtAutorizacao.setDocument(new GenericDocument(200));
        txtAutorizacao.setName("txtAutorizacao"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoHospedagem.autorizacao}"), txtAutorizacao, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        cboHotel.setDisplayProperty(Servico.PROP_EMPRESA);
        cboHotel.setName("cboHotel"); // NOI18N

        eLProperty = ELProperty.create("${model.servicos}");
        JComboBoxBinding jComboBoxBinding = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, cboHotel);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoHospedagem.empresaServico}"), cboHotel, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        GroupLayout pnlDadosLayout = new GroupLayout(pnlDados);
        pnlDados.setLayout(pnlDadosLayout);
        pnlDadosLayout.setHorizontalGroup(
            pnlDadosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnlDadosLayout.createSequentialGroup()
                        .addComponent(sLabel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(sPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(pnlDadosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(labelPeriodo1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPeriodo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHotel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAgente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDadosLayout.createSequentialGroup()
                        .addComponent(sTextField1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sLabel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sistamDateChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
                    .addGroup(pnlDadosLayout.createSequentialGroup()
                        .addComponent(cboHotel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlDadosLayout.createSequentialGroup()
                        .addComponent(dataChegada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelHoraTransporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataSaida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlDadosLayout.createSequentialGroup()
                        .addComponent(txtAutorizacao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pnlDadosLayout.setVerticalGroup(
            pnlDadosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAgente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sistamDateChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHotel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHotel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPeriodo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataChegada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataSaida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHoraTransporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPeriodo1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAutorizacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(sPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sLabel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        sPanel1.getAccessibleContext().setAccessibleName("Hóspedes");

        pnlBotoes.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pnlBotoes.setName("pnlBotoes"); // NOI18N

        btnSalvar.setAction(actionSalvar);
        btnSalvar.setName("btnSalvar"); // NOI18N
        pnlBotoes.add(btnSalvar);

        GroupLayout pnlHospedagemLayout = new GroupLayout(pnlHospedagem);
        pnlHospedagem.setLayout(pnlHospedagemLayout);
        pnlHospedagemLayout.setHorizontalGroup(
            pnlHospedagemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlHospedagemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHospedagemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHospedagemLayout.createSequentialGroup()
                        .addComponent(pnlDados, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlBotoes, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlHospedagemLayout.setVerticalGroup(
            pnlHospedagemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlHospedagemLayout.createSequentialGroup()
                .addComponent(pnlDados, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBotoes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(pnlHospedagem, BorderLayout.CENTER);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON
    // Variables declaration - do not modify//GEN-BEGIN:variables
    GenericAction actionAtivar;
    GenericAction actionCancelar;
    SButton btnAtivarDesativar;
    SComboBox cboHotel;
    SistamDateChooser dataChegada;
    SistamDateChooser dataSaida;
    SLabel labelAgente;
    SLabel labelHoraTransporte;
    SLabel labelPeriodo;
    SLabel labelPeriodo1;
    SLabel lblHotel;
    SPanel pnlHospedagem;
    STable tabelaHospede;
    STextField txtAutorizacao;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
