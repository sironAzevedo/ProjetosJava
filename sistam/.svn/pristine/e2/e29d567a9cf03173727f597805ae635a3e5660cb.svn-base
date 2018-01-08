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
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.SoftBevelBorder;
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

public class SolicitacaoManobraDialog extends SDialog implements PropertyChangeListener{
    
    private SolicitacaoManobraModel model;
    
    public SolicitacaoManobraDialog(java.awt.Frame parent, Manobra manobra) {
        super(parent, true);
        model = new SolicitacaoManobraModel(manobra);
        initComponents();
        setLocationRelativeTo(parent); 
        
        carregarDados();
    }

    public SolicitacaoManobraModel getModel() {
        return model;
    }
    
    public void adicionarServico() {
        ServicoManobra servicoManobra = model.obterNovoServico();
        ServicoManobraDialog dialog = new ServicoManobraDialog(SistamApp.getSistamApp().getMainFrame(), servicoManobra);  
        dialog.prepararParaSolicitacao();
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);  
    }

    public void editarServico() {
        ServicoManobra servicoManobra = model.obterServicoParaEdicao();
        ServicoManobraDialog dialog = new ServicoManobraDialog(SistamApp.getSistamApp().getMainFrame(), servicoManobra); 
        dialog.prepararParaSolicitacao();
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);  
    }
    
    public void excluirServico() {
        model.excluirServicoManobra();
    }
    
    public void solicitarServicos() {
        model.solicitarServicos();
        dispose();
    }
    
    public void salvar(){
        model.salvarManobra();
        DialogMessages.info(this, "Manobra salva com sucesso!");
        dispose();
    }
    
    private void carregarDados() {
        AssyncInvoker
                .create(this, "carregarPontosAtracacaoOrigem")
                .disabling(cboPontoIncial)
                .settingLoadingIconOn(lblPontoIncial)
                .schedule();

        AssyncInvoker
                .create(this, "carregarPontosAtracacaoDestino")
                .disabling(cboPontoFinal)
                .settingLoadingIconOn(lblPontiFinal)
                .schedule();

        AssyncInvoker
                .create(this, "carregarPontosAtracacaoEscala")
                .disabling(cboEscala)
                .settingLoadingIconOn(lblEscala)
                .schedule();
        
        AssyncInvoker
                .create(this, "carregarResponsavelCusto")
                .disabling(cboResponsavelCusto)
                .settingLoadingIconOn(lblResponsavelCusto)
                .schedule();
    }
    
    public void carregarPontosAtracacaoOrigem() {
        getModel().carregarPontosAtracaocaoOrigem();
    }
    
    public void carregarPontosAtracacaoDestino() {
        getModel().carregarPontosAtracaocaoDestino();
    }
    
    public void carregarPontosAtracacaoEscala() {
        getModel().carregarPontosAtracaocaoEscala();
    }
    
    public void carregarResponsavelCusto() {
        getModel().carregarResponsavelCusto();
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
        GenericAction actionSolicitar = new GenericAction();
        GenericAction actionExcluir = new GenericAction();
        GenericAction actionEditar = new GenericAction();
        GenericAction actionSalvar = new GenericAction();
        SPanel pnlCabecalho = new SPanel();
        SLabel sLabel7 = new SLabel();
        SLabel sLabel8 = new SLabel();
        SLabel sLabel3 = new SLabel();
        SLabel sLabel9 = new SLabel();
        SPanel pnlBotoes = new SPanel();
        SButton btnSalvar = new SButton();
        SButton btnSolicitar = new SButton();
        SPanel pnlManobras = new SPanel();
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
        SPanel pnlTipoServico = new SPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        tblServicosManobra = new STable();
        SButton btnAdicionarServico = new SButton();
        SButton btnRemoverServico = new SButton();
        SButton btnAdicionarServico1 = new SButton();
        lblEscala = new SLabel();
        cboEscala = new SComboBox();
        cboResponsavelCusto = new SComboBox();
        lblResponsavelCusto = new SLabel();

        actionAdicionar.setIcon(new ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionar.setMethodName("adicionarServico");
        actionAdicionar.setTarget(this);
        actionAdicionar.setText("Adicionar");
        actionAdicionar.setToolTipText("Adiciona um novo serviço para a manobra");

        Binding binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.tipoServicoSelecionado}"), actionAdicionar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionSolicitar.setConfirm("Confirma a emissão de e-mail para a relação de empresas?");
        actionSolicitar.setIcon(new ImageIcon(getClass().getResource("/icons/send.png"))); // NOI18N
        actionSolicitar.setMethodName("solicitarServicos");
        actionSolicitar.setTarget(this);
        actionSolicitar.setText("Solicitar");
        actionSolicitar.setToolTipText("Solicitar serviços");

        actionExcluir.setConfirm("Deseja realmente excluir o serviço selecionado e seus responsáveis?");
        actionExcluir.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluir.setMethodName("excluirServico");
        actionExcluir.setTarget(this);
        actionExcluir.setText("Excluir");
        actionExcluir.setToolTipText("Excluir o serviço selecionado");

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.sevicoSelecionado}"), actionExcluir, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionEditar.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditar.setMethodName("editarServico");
        actionEditar.setTarget(this);
        actionEditar.setText("Editar");
        actionEditar.setToolTipText("Edita o serviço selecionado");

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.sevicoSelecionado}"), actionEditar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionSalvar.setIcon(new ImageIcon(getClass().getResource("/icons/disk.png"))); // NOI18N
        actionSalvar.setMethodName("salvar");
        actionSalvar.setTarget(this);
        actionSalvar.setText("Salvar");
        actionSalvar.setToolTipText("Salva a manobra");

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
                .addContainerGap(597, Short.MAX_VALUE))
        );
        pnlCabecalhoLayout.setVerticalGroup(
            pnlCabecalhoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(pnlCabecalhoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCabecalhoLayout.createParallelGroup(Alignment.CENTER)
                    .addComponent(sLabel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sLabel3, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel8, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(sLabel9, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
        );

        pnlBotoes.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        pnlBotoes.setName("pnlBotoes"); // NOI18N

        btnSalvar.setAction(actionSalvar);
        btnSalvar.setName("btnSalvar"); // NOI18N
        pnlBotoes.add(btnSalvar);

        btnSolicitar.setAction(actionSolicitar);
        btnSolicitar.setName("btnSolicitar"); // NOI18N
        pnlBotoes.add(btnSolicitar);

        pnlManobras.setBorder(BorderFactory.createTitledBorder(bundle.getString("dadosManobra"))); // NOI18N
        pnlManobras.setName("pnlManobras"); // NOI18N

        labelFinalidades.setHorizontalAlignment(SwingConstants.RIGHT);
        labelFinalidades.setText(bundle.getString("lblFinalidade")); // NOI18N
        labelFinalidades.setName("labelFinalidades"); // NOI18N

        cboFinalidade.setName("cboFinalidade"); // NOI18N
        cboFinalidade.setPopupResizeEnabled(true);

        ELProperty eLProperty = ELProperty.create("${model.finalidades}");
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

        eLProperty = ELProperty.create("${model.pontosAtracacaoOrigem}");
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

        eLProperty = ELProperty.create("${model.pontosAtracacaoDestino}");
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

        sNumericTextField1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sNumericTextField1ActionPerformed(evt);
            }
        });
        sNumericTextField1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sNumericTextField1ActionPerformed(evt);
            }
        });

        sNumericTextField2.setIntegerPlaces(new Short((short)2));
        sNumericTextField2.setNumberClass(Double.class);
        sNumericTextField2.setText("sNumericTextField1");
        sNumericTextField2.setName("sNumericTextField2"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.caladoVante}"), sNumericTextField2, BeanProperty.create("doubleValue"));
        bindingGroup.addBinding(binding);

        pnlTipoServico.setBorder(BorderFactory.createTitledBorder("Lista de Serviços"));
        pnlTipoServico.setName("pnlTipoServico"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblServicosManobra.setName("tblServicosManobra"); // NOI18N

        eLProperty = ELProperty.create("${model.manobra.servicos}");
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
        jTableBinding.bind();binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.sevicoSelecionado}"), tblServicosManobra, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(tblServicosManobra);

        btnAdicionarServico.setAction(actionEditar);
        btnAdicionarServico.setName("btnAdicionarServico"); // NOI18N

        btnRemoverServico.setAction(actionExcluir);
        btnRemoverServico.setName("btnRemoverServico"); // NOI18N

        btnAdicionarServico1.setAction(actionAdicionar);
        btnAdicionarServico1.setName("btnAdicionarServico1"); // NOI18N

        GroupLayout pnlTipoServicoLayout = new GroupLayout(pnlTipoServico);
        pnlTipoServico.setLayout(pnlTipoServicoLayout);
        pnlTipoServicoLayout.setHorizontalGroup(
            pnlTipoServicoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(pnlTipoServicoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTipoServicoLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(pnlTipoServicoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdicionarServico1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(btnAdicionarServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(btnRemoverServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTipoServicoLayout.setVerticalGroup(
            pnlTipoServicoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, pnlTipoServicoLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(pnlTipoServicoLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(btnRemoverServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTipoServicoLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnAdicionarServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdicionarServico1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        );

        lblEscala.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEscala.setText(bundle.getString("lblEscala")); // NOI18N
        lblEscala.setName("lblEscala"); // NOI18N

        cboEscala.setDisplayProperty("nomeFormatado");
        cboEscala.setName("cboEscala"); // NOI18N
        cboEscala.setPopupResizeEnabled(true);

        eLProperty = ELProperty.create("${model.pontosAtracacaoEscala}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, this, eLProperty, cboEscala);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.pontoAtracacaoEscala}"), cboEscala, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cboResponsavelCusto.setName("cboResponsavelCusto"); // NOI18N
        cboResponsavelCusto.setPopupResizeEnabled(true);

        eLProperty = ELProperty.create("${model.responsaveisCusto}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, this, eLProperty, cboResponsavelCusto);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobra.responsavelCusto}"), cboResponsavelCusto, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        lblResponsavelCusto.setHorizontalAlignment(SwingConstants.RIGHT);
        lblResponsavelCusto.setText("Responsável pelo custo:");
        lblResponsavelCusto.setName("lblResponsavelCusto"); // NOI18N

        GroupLayout pnlManobrasLayout = new GroupLayout(pnlManobras);
        pnlManobras.setLayout(pnlManobrasLayout);
        pnlManobrasLayout.setHorizontalGroup(
            pnlManobrasLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(pnlManobrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManobrasLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(labelFinalidades, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Alignment.TRAILING, pnlManobrasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlManobrasLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(lblPontoIncial, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPontiFinal, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEscala, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPontiFinal2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(sLabel6, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblResponsavelCusto, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnlManobrasLayout.createSequentialGroup()
                        .addComponent(sNumericTextField2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(lblPontiFinal1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(sNumericTextField1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Alignment.TRAILING, pnlManobrasLayout.createSequentialGroup()
                        .addGroup(pnlManobrasLayout.createParallelGroup(Alignment.TRAILING)
                            .addComponent(cboResponsavelCusto, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboEscala, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                            .addComponent(cboPontoFinal, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboPontoIncial, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboFinalidade, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(396, 396, 396)))
                .addContainerGap())
            .addGroup(pnlManobrasLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pnlTipoServico, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        pnlManobrasLayout.setVerticalGroup(
            pnlManobrasLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(pnlManobrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManobrasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labelFinalidades, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboFinalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(cboPontoIncial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPontoIncial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(cboPontoFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPontiFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(cboEscala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEscala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(cboResponsavelCusto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResponsavelCusto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(pnlManobrasLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(pnlManobrasLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPontiFinal2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(sNumericTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlManobrasLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPontiFinal1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(sNumericTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlManobrasLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(sLabel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlTipoServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(pnlCabecalho, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBotoes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlManobras, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCabecalho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(pnlManobras, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(pnlBotoes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sNumericTextField1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_sNumericTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sNumericTextField1ActionPerformed

    //CHECKSTYLE:ON

    // Variables declaration - do not modify//GEN-BEGIN:variables
    SComboBox cboEscala;
    SComboBox cboFinalidade;
    SComboBox cboPontoFinal;
    SComboBox cboPontoIncial;
    private SComboBox cboResponsavelCusto;
    SLabel labelFinalidades;
    SLabel lblEscala;
    SLabel lblPontiFinal;
    SLabel lblPontiFinal1;
    SLabel lblPontiFinal2;
    SLabel lblPontoIncial;
    SLabel lblResponsavelCusto;
    STable tblServicosManobra;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
