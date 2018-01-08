package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.SDialog;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SCheckBox;
import br.com.petrobras.fcorp.swing.components.SComboBox;
import br.com.petrobras.fcorp.swing.components.SFormattedTextField;
import br.com.petrobras.fcorp.swing.components.SLabel;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.STable;
import br.com.petrobras.fcorp.swing.components.STextArea;
import br.com.petrobras.fcorp.swing.components.STextField;
import br.com.petrobras.fcorp.swing.components.renderers.ImageBooleanRenderer;
import br.com.petrobras.fcorp.swing.components.util.GenericDocument;
import br.com.petrobras.sistam.common.entity.PessoaAcesso;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.enums.TipoCategoria;
import br.com.petrobras.sistam.common.enums.TipoServicoProtecao;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.components.SistamDateChooser;
import br.com.petrobras.sistam.desktop.components.lookups.servicoprotecao.EmpresaProtecaoLookup;
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

public class DetalheServicoProtecaoAcessoPessoaDialog extends SDialog implements PropertyChangeListener {

    private DetalheServicoProtecaoAcessoPessoaModel model;

    public DetalheServicoProtecaoAcessoPessoaDialog(java.awt.Frame parent, ServicoAcessoPessoa servicoAcessoPessoa, boolean editar) {
        super(parent, true);
        model = new DetalheServicoProtecaoAcessoPessoaModel(servicoAcessoPessoa, editar);
        model.setDialog(this);

        initComponents();
        setLocationRelativeTo(parent);
        visualizarCampos(model.getServicoAcessoPessoa().getTipoCategoria());

        if (!editar) {
            DesktopUtil.habilitarComponentes(editar, this.pnlEscalas);
        }

        comboTipoCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizarCampos((TipoCategoria) comboTipoCategoria.getSelectedItem());
            }
        });
        
        prestadorServicoLookup.setTipoServicoProtecao(TipoServicoProtecao.ACESSO_PESSOAS);
    }

    public DetalheServicoProtecaoAcessoPessoaModel getModel() {
        return model;
    }

    public void salvar() {
        model.salvar();
        dispose();
    }

    private void visualizarCampos(TipoCategoria tipoSelecionado) {
        if (tipoSelecionado != null && tipoSelecionado.equals(TipoCategoria.PRESTADOR_SERVICO)) {
            panelPrestadorServico.setVisible(true);
        } else {
            panelPrestadorServico.setVisible(false);
        }
    }

    public void adicionarPessoa() {
        PessoaAcesso pessoaAcesso = model.obterNovoPessoa();
        RegistroPessoaDialog dialog = new RegistroPessoaDialog(SistamApp.getSistamApp().getMainFrame(), pessoaAcesso, false);
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);
    }

    public void editarPessoa() {
        PessoaAcesso pessoaAcesso = model.obterPessoaParaEdicao();
        RegistroPessoaDialog dialog = new RegistroPessoaDialog(SistamApp.getApplication().getMainFrame(), pessoaAcesso, true);
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(RegistroPessoaDialog.PESSOA_INSERIDO)) {
            PessoaAcesso pessoa = ((RegistroPessoaDialog) evt.getSource()).getModel().getPessoaAcesso();
            model.adicionarPessoa(pessoa);
        }

        if (evt.getPropertyName().equals(RegistroPessoaDialog.PESSOA_ALTERADO)) {
            PessoaAcesso pessoa = ((RegistroPessoaDialog) evt.getSource()).getModel().getPessoaAcesso();
            model.atualizarPessoa(pessoa);
        }
    }

    public void ativarDesativar() {
        if (model.getPessoaSelecionada() == null || model.getPessoaSelecionada().isAtivo()) {
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

        actionSalvar = new GenericAction();
        actionCancelar = new GenericAction();
        actionAtivar = new GenericAction();
        GenericAction actionExcluir = new GenericAction();
        GenericAction actionEditar = new GenericAction();
        GenericAction actionAdicionar = new GenericAction();
        ImageBooleanRenderer imageBooleanAtivarDesativar = new ImageBooleanRenderer();
        campoCNPJ = new SFormattedTextField();
        pnlEscalas = new SPanel();
        SPanel pnlManobras = new SPanel();
        labelObservacao = new SLabel();
        scrollObservacao = new JScrollPane();
        txtObservacao = new STextArea();
        labelAgente = new SLabel();
        lblDatasolicitacao = new SLabel();
        sistamDateChooserInicio = new SistamDateChooser();
        labelLocaisAcesso = new SLabel();
        labelTipoSoliTransito = new SLabel();
        txtServicoExecutado = new STextField();
        comboTipoSoliTransito = new SComboBox();
        panelPessoa = new SPanel();
        scrollTripulante = new JScrollPane();
        tabelaTripulante = new STable();
        btnAdicionar = new SButton();
        btnEditar = new SButton();
        btnAtivarDesativar = new SButton();
        btnExcluir = new SButton();
        labelTipoAcesso = new SLabel();
        labelCategoria = new SLabel();
        comboTipoCategoria = new SComboBox();
        comboTipoAcesso = new SComboBox();
        chkCompanhia = new SCheckBox();
        chkTerminal = new SCheckBox();
        txtTerminal = new STextField();
        labelNacionalidade = new SLabel();
        comboTipoNacionalidade = new SComboBox();
        panelPrestadorServico = new SPanel();
        labelNome = new SLabel();
        labelCNPJ = new SLabel();
        labelAtividadeBordo = new SLabel();
        JScrollPane scrollAtividadeBordo = new JScrollPane();
        txtAtividadeBordo = new STextArea();
        prestadorServicoLookup = new EmpresaProtecaoLookup();
        STextField sTextField1 = new STextField();
        pnlBotoes = new SPanel();
        btnSalvar = new SButton();

        actionSalvar.setIcon(new ImageIcon(getClass().getResource("/icons/disk.png"))); // NOI18N
        actionSalvar.setMethodName("salvar");
        actionSalvar.setTarget(this);
        actionSalvar.setText("Salvar");
        actionSalvar.setToolTipText("Salva as informações do serviço de proteção");

        actionCancelar.setIcon(new ImageIcon(getClass().getResource("/icons/listagem_fechar.png"))); // NOI18N
        actionCancelar.setMethodName("ativarCancelarPessoa");
        actionCancelar.setTarget(model);
        actionCancelar.setText("Cancelar");
        actionCancelar.setToolTipText("Cancela pessoa");

        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.pessoaSelecionada && model.pessoaSelecionada.id!=null && model.editar}"), actionCancelar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionAtivar.setIcon(new ImageIcon(getClass().getResource("/icons/concluir.png"))); // NOI18N
        actionAtivar.setMethodName("ativarCancelarPessoa");
        actionAtivar.setTarget(model);
        actionAtivar.setText("Ativar");
        actionAtivar.setToolTipText("Ativa pessoa");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.pessoaSelecionada && model.pessoaSelecionada.id!=null && model.editar}"), actionAtivar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionExcluir.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluir.setMethodName("excluirPessoa");
        actionExcluir.setTarget(model);
        actionExcluir.setText("Excluir");
        actionExcluir.setToolTipText("Exclui Pessoa");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.pessoaSelecionada  && model.editar && model.pessoaSelecionada.id==null}"), actionExcluir, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionEditar.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditar.setMethodName("editarPessoa");
        actionEditar.setTarget(this);
        actionEditar.setText("Editar");
        actionEditar.setToolTipText("Edita dados da pessoa");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.pessoaSelecionada  && model.editar}"), actionEditar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionAdicionar.setIcon(new ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionar.setMethodName("adicionarPessoa");
        actionAdicionar.setTarget(this);
        actionAdicionar.setText("Adicionar");
        actionAdicionar.setToolTipText("Adiciona pessoa");

        imageBooleanAtivarDesativar.setFalseIcon(new ImageIcon(getClass().getResource("/icons/listagem_fechar.png"))); // NOI18N
        imageBooleanAtivarDesativar.setTrueIcon(new ImageIcon(getClass().getResource("/icons/concluir.png"))); // NOI18N

        campoCNPJ.setName("campoCNPJ"); // NOI18N

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

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.servicoProtecao.observacao}"), txtObservacao, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        scrollObservacao.setViewportView(txtObservacao);

        labelAgente.setText("Serviço Executado:");
        labelAgente.setName("labelAgente"); // NOI18N

        lblDatasolicitacao.setText("Data da Solicitação:");
        lblDatasolicitacao.setName("lblDatasolicitacao"); // NOI18N

        sistamDateChooserInicio.setName("sistamDateChooserInicio"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.servicoProtecao.dataExecucao}"), sistamDateChooserInicio, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        labelLocaisAcesso.setText("Local(ais) do acesso:");
        labelLocaisAcesso.setName("labelLocaisAcesso"); // NOI18N

        labelTipoSoliTransito.setText("Tipo de Solicitação de Trânsito:");
        labelTipoSoliTransito.setName("labelTipoSoliTransito"); // NOI18N

        txtServicoExecutado.setEnabled(false);
        txtServicoExecutado.setName("txtServicoExecutado"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoExecutado}"), txtServicoExecutado, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        comboTipoSoliTransito.setName("comboTipoSoliTransito"); // NOI18N

        ELProperty eLProperty = ELProperty.create("${model.listaTipoSolicitacaoTransito}");
        JComboBoxBinding jComboBoxBinding = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, comboTipoSoliTransito);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.tipoSolicitacaoTransito}"), comboTipoSoliTransito, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.id==null}"), comboTipoSoliTransito, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        panelPessoa.setBorder(BorderFactory.createTitledBorder("Pessoas"));
        panelPessoa.setName("panelPessoa"); // NOI18N

        scrollTripulante.setName("scrollTripulante"); // NOI18N

        tabelaTripulante.setSortable(true);
        tabelaTripulante.setName("tabelaTripulante"); // NOI18N
        tabelaTripulante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        eLProperty = ELProperty.create("${model.servicoAcessoPessoa.pessoasAsList}");
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
        columnBinding.setColumnName("Nº Documento");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${cpfComMascara}"));
        columnBinding.setColumnName("CPF");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.pessoaSelecionada}"), tabelaTripulante, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        scrollTripulante.setViewportView(tabelaTripulante);
        tabelaTripulante.getColumnModel().getColumn(0).setMaxWidth(20);
        tabelaTripulante.getColumnModel().getColumn(0).setCellRenderer(imageBooleanAtivarDesativar);
        tabelaTripulante.getColumnModel().getColumn(2).setMinWidth(120);
        tabelaTripulante.getColumnModel().getColumn(2).setMaxWidth(120);
        tabelaTripulante.getColumnModel().getColumn(3).setMinWidth(120);
        tabelaTripulante.getColumnModel().getColumn(3).setMaxWidth(120);

        btnAdicionar.setAction(actionAdicionar);
        btnAdicionar.setName("btnAdicionar"); // NOI18N

        btnEditar.setAction(actionEditar);
        btnEditar.setName("btnEditar"); // NOI18N

        btnAtivarDesativar.setAction(actionCancelar);
        btnAtivarDesativar.setName("btnAtivarDesativar"); // NOI18N

        btnExcluir.setAction(actionExcluir);
        btnExcluir.setName("btnExcluir"); // NOI18N

        GroupLayout panelPessoaLayout = new GroupLayout(panelPessoa);
        panelPessoa.setLayout(panelPessoaLayout);
        panelPessoaLayout.setHorizontalGroup(
            panelPessoaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelPessoaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPessoaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTripulante, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, panelPessoaLayout.createSequentialGroup()
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
        panelPessoaLayout.setVerticalGroup(
            panelPessoaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelPessoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollTripulante, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelPessoaLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtivarDesativar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelTipoAcesso.setText("Tipo de Acesso:");
        labelTipoAcesso.setName("labelTipoAcesso"); // NOI18N

        labelCategoria.setText("Categoria:");
        labelCategoria.setName("labelCategoria"); // NOI18N

        comboTipoCategoria.setName("comboTipoCategoria"); // NOI18N

        eLProperty = ELProperty.create("${model.listaTipoCategoria}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, comboTipoCategoria);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.tipoCategoria}"), comboTipoCategoria, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.id==null}"), comboTipoCategoria, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        comboTipoAcesso.setName("comboTipoAcesso"); // NOI18N

        eLProperty = ELProperty.create("${model.listaTipoAcesso}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, comboTipoAcesso);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.tipoAcesso}"), comboTipoAcesso, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        chkCompanhia.setText("Companhia Docas");
        chkCompanhia.setName("chkCompanhia"); // NOI18N
        chkCompanhia.setOpaque(false);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.companhiaDocas}"), chkCompanhia, BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        chkTerminal.setText("Terminal");
        chkTerminal.setName("chkTerminal"); // NOI18N
        chkTerminal.setOpaque(false);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.terminal}"), chkTerminal, BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        txtTerminal.setDocument(new GenericDocument(30));
        txtTerminal.setName("txtTerminal"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.descricaoTerminal}"), txtTerminal, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.terminal && model.editar}"), txtTerminal, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        labelNacionalidade.setText("Nacionalidade:");
        labelNacionalidade.setName("labelNacionalidade"); // NOI18N

        comboTipoNacionalidade.setName("comboTipoNacionalidade"); // NOI18N

        eLProperty = ELProperty.create("${model.listaTipoNacionalidade}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, comboTipoNacionalidade);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.tipoNacionalidade}"), comboTipoNacionalidade, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.id==null}"), comboTipoNacionalidade, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        panelPrestadorServico.setBorder(BorderFactory.createTitledBorder("Prestador de Serviço"));
        panelPrestadorServico.setName("panelPrestadorServico"); // NOI18N
        panelPrestadorServico.setOpaque(false);

        labelNome.setText("Empresa:");
        labelNome.setName("labelNome"); // NOI18N

        labelCNPJ.setText("CNPJ:");
        labelCNPJ.setName("labelCNPJ"); // NOI18N

        labelAtividadeBordo.setText("Atividade de Bordo:");
        labelAtividadeBordo.setName("labelAtividadeBordo"); // NOI18N

        scrollAtividadeBordo.setName("scrollAtividadeBordo"); // NOI18N

        txtAtividadeBordo.setColumns(20);
        txtAtividadeBordo.setDocument(new GenericDocument(500));
        txtAtividadeBordo.setLineWrap(true);
        txtAtividadeBordo.setRows(2);
        txtAtividadeBordo.setWrapStyleWord(true);
        txtAtividadeBordo.setName("txtAtividadeBordo"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.prestadorServicoAtividadeBordo}"), txtAtividadeBordo, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        scrollAtividadeBordo.setViewportView(txtAtividadeBordo);

        prestadorServicoLookup.setName("prestadorServicoLookup"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.id==null}"), prestadorServicoLookup, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.prestadorServico}"), prestadorServicoLookup, BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        sTextField1.setEditable(false);
        sTextField1.setName("sTextField1"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.cnpjPrestadorServicoComMascara}"), sTextField1, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoAcessoPessoa.id==null}"), sTextField1, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        GroupLayout panelPrestadorServicoLayout = new GroupLayout(panelPrestadorServico);
        panelPrestadorServico.setLayout(panelPrestadorServicoLayout);
        panelPrestadorServicoLayout.setHorizontalGroup(
            panelPrestadorServicoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelPrestadorServicoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrestadorServicoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrestadorServicoLayout.createSequentialGroup()
                        .addComponent(labelAtividadeBordo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollAtividadeBordo)
                    .addGroup(panelPrestadorServicoLayout.createSequentialGroup()
                        .addGroup(panelPrestadorServicoLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrestadorServicoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(prestadorServicoLookup, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelPrestadorServicoLayout.createSequentialGroup()
                                .addComponent(sTextField1, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panelPrestadorServicoLayout.setVerticalGroup(
            panelPrestadorServicoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelPrestadorServicoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrestadorServicoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(prestadorServicoLookup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrestadorServicoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAtividadeBordo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(scrollAtividadeBordo, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout pnlManobrasLayout = new GroupLayout(pnlManobras);
        pnlManobras.setLayout(pnlManobrasLayout);
        pnlManobrasLayout.setHorizontalGroup(
            pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlManobrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnlManobrasLayout.createSequentialGroup()
                        .addComponent(labelObservacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, pnlManobrasLayout.createSequentialGroup()
                        .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollObservacao, GroupLayout.Alignment.LEADING)
                            .addComponent(panelPessoa, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelPrestadorServico, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.LEADING, pnlManobrasLayout.createSequentialGroup()
                                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelNacionalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelTipoAcesso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelLocaisAcesso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelAgente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelTipoSoliTransito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlManobrasLayout.createSequentialGroup()
                                        .addComponent(chkTerminal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTerminal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlManobrasLayout.createSequentialGroup()
                                        .addComponent(chkCompanhia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(comboTipoSoliTransito, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboTipoAcesso, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboTipoCategoria, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboTipoNacionalidade, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pnlManobrasLayout.createSequentialGroup()
                                        .addComponent(txtServicoExecutado, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblDatasolicitacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(sistamDateChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())))
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
                    .addComponent(labelLocaisAcesso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCompanhia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(chkTerminal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTerminal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipoSoliTransito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipoSoliTransito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipoAcesso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipoAcesso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipoCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNacionalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTipoNacionalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPrestadorServico, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPessoa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelObservacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollObservacao, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            .addComponent(pnlBotoes, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(pnlEscalasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlManobras, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlEscalasLayout.setVerticalGroup(
            pnlEscalasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlEscalasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlManobras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
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
    GenericAction actionSalvar;
    SButton btnAdicionar;
    SButton btnAtivarDesativar;
    SButton btnEditar;
    SButton btnExcluir;
    SButton btnSalvar;
    SFormattedTextField campoCNPJ;
    SCheckBox chkCompanhia;
    SCheckBox chkTerminal;
    SComboBox comboTipoAcesso;
    SComboBox comboTipoCategoria;
    SComboBox comboTipoNacionalidade;
    SComboBox comboTipoSoliTransito;
    SLabel labelAgente;
    SLabel labelAtividadeBordo;
    SLabel labelCNPJ;
    SLabel labelCategoria;
    SLabel labelLocaisAcesso;
    SLabel labelNacionalidade;
    SLabel labelNome;
    SLabel labelObservacao;
    SLabel labelTipoAcesso;
    SLabel labelTipoSoliTransito;
    SLabel lblDatasolicitacao;
    SPanel panelPessoa;
    SPanel panelPrestadorServico;
    SPanel pnlBotoes;
    SPanel pnlEscalas;
    EmpresaProtecaoLookup prestadorServicoLookup;
    JScrollPane scrollObservacao;
    JScrollPane scrollTripulante;
    SistamDateChooser sistamDateChooserInicio;
    STable tabelaTripulante;
    STextArea txtAtividadeBordo;
    STextArea txtObservacao;
    STextField txtServicoExecutado;
    STextField txtTerminal;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
