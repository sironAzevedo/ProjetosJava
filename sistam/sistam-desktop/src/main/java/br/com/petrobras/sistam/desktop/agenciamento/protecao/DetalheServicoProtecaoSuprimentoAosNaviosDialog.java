package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.SDialog;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SComboBox;
import br.com.petrobras.fcorp.swing.components.SLabel;
import br.com.petrobras.fcorp.swing.components.SNumericTextField;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.STable;
import br.com.petrobras.fcorp.swing.components.STextArea;
import br.com.petrobras.fcorp.swing.components.STextField;
import br.com.petrobras.fcorp.swing.components.renderers.ImageBooleanRenderer;
import br.com.petrobras.fcorp.swing.components.util.GenericDocument;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoSuprimentoTransito;
import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.sistam.common.enums.TipoMaterial;
import br.com.petrobras.sistam.common.enums.TipoMercadorias;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.components.SistamDateChooser;
import br.com.petrobras.sistam.desktop.components.SistamTimeChooser;
import br.com.petrobras.sistam.desktop.util.DesktopUtil;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
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

public class DetalheServicoProtecaoSuprimentoAosNaviosDialog extends SDialog implements PropertyChangeListener{

    private DetalheServicoProtecaoSuprimentoAosNaviosModel model;

    public DetalheServicoProtecaoSuprimentoAosNaviosDialog(java.awt.Frame parent, ServicoSuprimento servicoSuprimento, boolean editar) {
        super(parent, true);
        model = new DetalheServicoProtecaoSuprimentoAosNaviosModel(servicoSuprimento, editar);
        model.setDialog(this);
        
        initComponents();
        setLocationRelativeTo(parent);

        if (!editar) {
            DesktopUtil.habilitarComponentes(editar, this.pnlSuprimentoAoNavio);
        }
    }

    public DetalheServicoProtecaoSuprimentoAosNaviosModel getModel() {
        return model;
    } 
    
     public void adicionarSolicitacaoDeTransito() {
        ServicoSuprimentoTransito suprimentoTransito = model.obterNovaSolicitacaoDeTransito(); 
        RegistroDeSolicitacaoDeTransitoDialog dialog = new RegistroDeSolicitacaoDeTransitoDialog(SistamApp.getSistamApp().getMainFrame(), suprimentoTransito, false, true);
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);
    }
     
     public void editarSolicitacaoDeTransito(){
         ServicoSuprimentoTransito suprimentoTransito = model.obterSolicitacaoDeTransiroParaEdicao();
         RegistroDeSolicitacaoDeTransitoDialog dialog = new RegistroDeSolicitacaoDeTransitoDialog(SistamApp.getSistamApp().getMainFrame(), suprimentoTransito, true, true);
         dialog.addPropertyChangeListener(this);
         dialog.setVisible(true);
     }
     
     @Override
     public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(RegistroDeSolicitacaoDeTransitoDialog.SOLICITACAO_TRANSITO_INSERIDO)) {
            ServicoSuprimentoTransito suprimentoTransito = ((RegistroDeSolicitacaoDeTransitoDialog) evt.getSource()).getModel().getSuprimentoTransito();
            model.adicionarSolicitacaoDeTransito(suprimentoTransito);
            model.getServicoSuprimento().calcularValorTotalMercadoria();
        }

        if (evt.getPropertyName().equals(RegistroDeSolicitacaoDeTransitoDialog.SOLICITACAO_TRANSITO_ALTERADO)) {
            ServicoSuprimentoTransito suprimentoTransito = ((RegistroDeSolicitacaoDeTransitoDialog) evt.getSource()).getModel().getSuprimentoTransito();
            suprimentoTransito.setFormularioGeradoReceitaFederal(false);
            model.editarSolicitacaoDeTransito(suprimentoTransito);
            model.getServicoSuprimento().calcularValorTotalMercadoria();
        }
    }
     
     public void gerarFormularioSolicitacaoDeTransito(){
         model.validarFormulario();
         model.gerarFormulario();
     }

    public void salvar() {
        model.salvar();
        dispose();
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
        GenericAction actionAdicionar = new GenericAction();
        GenericAction actionEditar = new GenericAction();
        GenericAction actionExcluir = new GenericAction();
        GenericAction actionGerarFormulario = new GenericAction();
        ImageBooleanRenderer imageBooleanGerado = new ImageBooleanRenderer();
        pnlSuprimentoAoNavio = new SPanel();
        SPanel pnlBotoes = new SPanel();
        SButton btnSalvar = new SButton();
        SPanel pnlSuprimentosAosNavios = new SPanel();
        labelAgente = new SLabel();
        txtServicoExecutado = new STextField();
        lblDatasolicitacao = new SLabel();
        sistamDateChooserServicoExecutado = new SistamDateChooser();
        SistamDateChooser sistamDateChooserInicio = new SistamDateChooser();
        SistamTimeChooser sistamTimeChooserInicio = new SistamTimeChooser();
        labelAgente1 = new SLabel();
        labelAgente2 = new SLabel();
        SistamDateChooser sistamDateChooserTermino = new SistamDateChooser();
        SistamTimeChooser sistamTimeChooserTermino = new SistamTimeChooser();
        SPanel pnlCustoDaOperacao = new SPanel();
        labelMercadorias1 = new SLabel();
        labelMercadorias2 = new SLabel();
        labelEmpresa1 = new SLabel();
        SPanel pnlCustoDaOperacaoPortuaria = new SPanel();
        labelMercadorias3 = new SLabel();
        labelMercadorias4 = new SLabel();
        labelMercadorias5 = new SLabel();
        labelEmpresa2 = new SLabel();
        SNumericTextField sntfOGMO = new SNumericTextField();
        SPanel pnlCustoDaOperacaoPortuaria1 = new SPanel();
        labelEmpresa3 = new SLabel();
        JScrollPane jScrollPane2 = new JScrollPane();
        STextArea txtObservacao = new STextArea();
        labelMercadorias7 = new SLabel();
        SNumericTextField sNumericTextFieldDO = new SNumericTextField();
        SLabel sLabel1 = new SLabel();
        STextField txtCentroCustoOperacaoDO = new STextField();
        SNumericTextField sNumericTextFieldOGMODOBRA = new SNumericTextField();
        SNumericTextField sntfHExcedenteOpPortuario = new SNumericTextField();
        SNumericTextField sntfOperadorPortuaria1 = new SNumericTextField();
        SNumericTextField sntfTotalOperacaoPortuaria1 = new SNumericTextField();
        SLabel sLabel3 = new SLabel();
        SLabel sLabel2 = new SLabel();
        SNumericTextField sntfTotalOperacaoPortuaria = new SNumericTextField();
        SNumericTextField sntfTransporteMaritimo = new SNumericTextField();
        SNumericTextField sntfTransporteRodoviario = new SNumericTextField();
        STextField txtCentroCustoOperacao = new STextField();
        SPanel pnlSolicitacaoDeTransito = new SPanel();
        scrollTripulante = new JScrollPane();
        tabelaSolicitacaoTransito = new STable();
        btnExcluir = new SButton();
        btnEditar = new SButton();
        btnAdicionarFornecedor = new SButton();
        SButton sButton1 = new SButton();
        cboServico = new SComboBox();
        labelAgente3 = new SLabel();
        labelEmpresa = new SLabel();
        cboEmpresa = new SComboBox();
        labelAgente4 = new SLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        STextArea sTextArea1 = new STextArea();

        actionSalvar.setIcon(new ImageIcon(getClass().getResource("/icons/disk.png"))); // NOI18N
        actionSalvar.setMethodName("salvar");
        actionSalvar.setTarget(this);
        actionSalvar.setText("Salvar");
        actionSalvar.setToolTipText("Salva as informações do serviço de proteção");

        actionAdicionar.setIcon(new ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionar.setMethodName("adicionarSolicitacaoDeTransito");
        actionAdicionar.setTarget(this);
        actionAdicionar.setText("Adicionar");
        actionAdicionar.setToolTipText("Adiciona Solicitação de Trânsito");

        actionEditar.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditar.setMethodName("editarSolicitacaoDeTransito");
        actionEditar.setTarget(this);
        actionEditar.setText("Editar");
        actionEditar.setToolTipText("Editar dados da Solicitação de Trânsito");

        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.suprimentoTransitoSelecionado && model.editar}"), actionEditar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionExcluir.setConfirm("Confirma exclusão da Solicitação de Trânsito?");
        actionExcluir.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluir.setMethodName("excluirSolicitacaoDeTransito");
        actionExcluir.setTarget(model);
        actionExcluir.setText("Excluir");
        actionExcluir.setToolTipText("Exclui Solicitação de Trânsito");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.suprimentoTransitoSelecionado && model.editar}"), actionExcluir, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionGerarFormulario.setIcon(new ImageIcon(getClass().getResource("/icons/print.png"))); // NOI18N
        actionGerarFormulario.setMethodName("gerarFormularioSolicitacaoDeTransito");
        actionGerarFormulario.setTarget(this);
        actionGerarFormulario.setText("Gerar Formulário");
        actionGerarFormulario.setToolTipText("Gera o formulário");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.suprimentoTransitoSelecionado &&model.suprimentoTransitoSelecionado.id!=null}"), actionGerarFormulario, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        imageBooleanGerado.setFalseIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        imageBooleanGerado.setTrueIcon(new ImageIcon(getClass().getResource("/icons/document-accept.png"))); // NOI18N

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Serviço de Proteção");

        pnlSuprimentoAoNavio.setMaximumSize(new Dimension(2147483647, 2147483647));
        pnlSuprimentoAoNavio.setName("pnlSuprimentoAoNavio"); // NOI18N
        pnlSuprimentoAoNavio.setPreferredSize(new Dimension(760, 790));

        pnlBotoes.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pnlBotoes.setName("pnlBotoes"); // NOI18N

        btnSalvar.setAction(actionSalvar);
        btnSalvar.setName("btnSalvar"); // NOI18N
        pnlBotoes.add(btnSalvar);

        pnlSuprimentosAosNavios.setBorder(BorderFactory.createTitledBorder(""));
        pnlSuprimentosAosNavios.setName("pnlSuprimentosAosNavios"); // NOI18N
        pnlSuprimentosAosNavios.setPreferredSize(new Dimension(840, 790));

        labelAgente.setText("Serviço Executado:");
        labelAgente.setName("labelAgente"); // NOI18N

        txtServicoExecutado.setEnabled(false);
        txtServicoExecutado.setName("txtServicoExecutado"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoExecutado}"), txtServicoExecutado, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblDatasolicitacao.setText("Data da Solicitação:");
        lblDatasolicitacao.setName("lblDatasolicitacao"); // NOI18N

        sistamDateChooserServicoExecutado.setName("sistamDateChooserServicoExecutado"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.servicoProtecao.dataExecucao}"), sistamDateChooserServicoExecutado, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        sistamDateChooserInicio.setName("sistamDateChooserInicio"); // NOI18N
        sistamDateChooserInicio.setTimeChooser(sistamTimeChooserInicio);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.dataInicioOperacao}"), sistamDateChooserInicio, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        sistamTimeChooserInicio.setName("sistamTimeChooserInicio"); // NOI18N
        sistamTimeChooserInicio.setTimeZone(model.getTimeZone());

        labelAgente1.setText("Data/Hora Início da Operação:");
        labelAgente1.setName("labelAgente1"); // NOI18N

        labelAgente2.setText("Data/Hora Término da Operação:");
        labelAgente2.setName("labelAgente2"); // NOI18N

        sistamDateChooserTermino.setName("sistamDateChooserTermino"); // NOI18N
        sistamDateChooserTermino.setTimeChooser(sistamTimeChooserTermino);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.dataFimOperacao}"), sistamDateChooserTermino, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        sistamTimeChooserTermino.setName("sistamTimeChooserTermino"); // NOI18N
        sistamTimeChooserTermino.setTimeZone(model.getTimeZone());

        pnlCustoDaOperacao.setBorder(BorderFactory.createTitledBorder("Custo da Operação"));
        pnlCustoDaOperacao.setName("pnlCustoDaOperacao"); // NOI18N

        labelMercadorias1.setText("Transporte Rodoviário:");
        labelMercadorias1.setName("labelMercadorias1"); // NOI18N

        labelMercadorias2.setText("Transporte Marítimo:");
        labelMercadorias2.setName("labelMercadorias2"); // NOI18N

        labelEmpresa1.setText("Centro de Custo Op.:");
        labelEmpresa1.setName("labelEmpresa1"); // NOI18N

        pnlCustoDaOperacaoPortuaria.setBorder(BorderFactory.createTitledBorder("Custo da Operação Portuária"));
        pnlCustoDaOperacaoPortuaria.setName("pnlCustoDaOperacaoPortuaria"); // NOI18N

        labelMercadorias3.setText("Operador Portuário:");
        labelMercadorias3.setName("labelMercadorias3"); // NOI18N

        labelMercadorias4.setText("Hora Excedente Op. Portuária:");
        labelMercadorias4.setName("labelMercadorias4"); // NOI18N

        labelMercadorias5.setText("OGMO:");
        labelMercadorias5.setName("labelMercadorias5"); // NOI18N

        labelEmpresa2.setText("OGMO - DOBRA:");
        labelEmpresa2.setName("labelEmpresa2"); // NOI18N

        sntfOGMO.setIntegerPlaces(new Short((short)13));
        sntfOGMO.setNumberClass(BigDecimal.class);
        sntfOGMO.setText("sNumericTextField2");
        sntfOGMO.setName("sntfOGMO"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.custoOGMO}"), sntfOGMO, BeanProperty.create("bigDecimalValue"));
        bindingGroup.addBinding(binding);

        pnlCustoDaOperacaoPortuaria1.setBorder(BorderFactory.createTitledBorder("Custo (D.O)"));
        pnlCustoDaOperacaoPortuaria1.setName("pnlCustoDaOperacaoPortuaria1"); // NOI18N

        labelEmpresa3.setText("Centro de Custo:");
        labelEmpresa3.setName("labelEmpresa3"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtObservacao.setColumns(20);
        txtObservacao.setDocument(new GenericDocument(500) );
        txtObservacao.setLineWrap(true);
        txtObservacao.setRows(4);
        txtObservacao.setWrapStyleWord(true);
        txtObservacao.setName("txtObservacao"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.justificativaDo}"), txtObservacao, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane2.setViewportView(txtObservacao);

        labelMercadorias7.setText("Justificativa:");
        labelMercadorias7.setName("labelMercadorias7"); // NOI18N

        sNumericTextFieldDO.setIntegerPlaces(new Short((short)13));
        sNumericTextFieldDO.setNumberClass(BigDecimal.class);
        sNumericTextFieldDO.setText("sNumericTextField2");
        sNumericTextFieldDO.setName("sNumericTextFieldDO"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.portuariaAnteriorDo}"), sNumericTextFieldDO, BeanProperty.create("bigDecimalValue"));
        bindingGroup.addBinding(binding);

        sLabel1.setText("Op. Portuária Anterior:");
        sLabel1.setName("sLabel1"); // NOI18N

        txtCentroCustoOperacaoDO.setDocument(new GenericDocument(50));
        txtCentroCustoOperacaoDO.setName("txtCentroCustoOperacaoDO"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.centroCustoDo}"), txtCentroCustoOperacaoDO, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        GroupLayout pnlCustoDaOperacaoPortuaria1Layout = new GroupLayout(pnlCustoDaOperacaoPortuaria1);
        pnlCustoDaOperacaoPortuaria1.setLayout(pnlCustoDaOperacaoPortuaria1Layout);
        pnlCustoDaOperacaoPortuaria1Layout.setHorizontalGroup(
            pnlCustoDaOperacaoPortuaria1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlCustoDaOperacaoPortuaria1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCustoDaOperacaoPortuaria1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(labelEmpresa3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCustoDaOperacaoPortuaria1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCustoDaOperacaoPortuaria1Layout.createSequentialGroup()
                        .addComponent(sNumericTextFieldDO, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelMercadorias7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCentroCustoOperacaoDO, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        pnlCustoDaOperacaoPortuaria1Layout.setVerticalGroup(
            pnlCustoDaOperacaoPortuaria1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlCustoDaOperacaoPortuaria1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCustoDaOperacaoPortuaria1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, pnlCustoDaOperacaoPortuaria1Layout.createSequentialGroup()
                        .addGroup(pnlCustoDaOperacaoPortuaria1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(sLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(sNumericTextFieldDO, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMercadorias7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCustoDaOperacaoPortuaria1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelEmpresa3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCentroCustoOperacaoDO, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sNumericTextFieldOGMODOBRA.setIntegerPlaces(new Short((short)13));
        sNumericTextFieldOGMODOBRA.setNumberClass(BigDecimal.class);
        sNumericTextFieldOGMODOBRA.setText("sNumericTextField2");
        sNumericTextFieldOGMODOBRA.setName("sNumericTextFieldOGMODOBRA"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.custoOgmoDobra}"), sNumericTextFieldOGMODOBRA, BeanProperty.create("bigDecimalValue"));
        bindingGroup.addBinding(binding);

        sntfHExcedenteOpPortuario.setIntegerPlaces(new Short((short)13));
        sntfHExcedenteOpPortuario.setNumberClass(BigDecimal.class);
        sntfHExcedenteOpPortuario.setText("sNumericTextField2");
        sntfHExcedenteOpPortuario.setName("sntfHExcedenteOpPortuario"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.custoHoraExcedente}"), sntfHExcedenteOpPortuario, BeanProperty.create("bigDecimalValue"));
        bindingGroup.addBinding(binding);

        sntfOperadorPortuaria1.setIntegerPlaces(new Short((short)13));
        sntfOperadorPortuaria1.setNumberClass(BigDecimal.class);
        sntfOperadorPortuaria1.setText("sNumericTextField2");
        sntfOperadorPortuaria1.setName("sntfOperadorPortuaria1"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.custoOperadorPortuario}"), sntfOperadorPortuaria1, BeanProperty.create("bigDecimalValue"));
        bindingGroup.addBinding(binding);

        sntfTotalOperacaoPortuaria1.setEditable(false);
        sntfTotalOperacaoPortuaria1.setIntegerPlaces(new Short((short)13));
        sntfTotalOperacaoPortuaria1.setNumberClass(BigDecimal.class);
        sntfTotalOperacaoPortuaria1.setText("sNumericTextField2");
        sntfTotalOperacaoPortuaria1.setEnabled(false);
        sntfTotalOperacaoPortuaria1.setName("sntfTotalOperacaoPortuaria1"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.valorTotalMercadorias}"), sntfTotalOperacaoPortuaria1, BeanProperty.create("bigDecimalValue"));
        bindingGroup.addBinding(binding);

        sLabel3.setText("Total Mercadorias:");
        sLabel3.setName("sLabel3"); // NOI18N

        sLabel2.setText("Total Op. Portuária:");
        sLabel2.setName("sLabel2"); // NOI18N

        sntfTotalOperacaoPortuaria.setEditable(false);
        sntfTotalOperacaoPortuaria.setIntegerPlaces(new Short((short)13));
        sntfTotalOperacaoPortuaria.setNumberClass(BigDecimal.class);
        sntfTotalOperacaoPortuaria.setText("sNumericTextField2");
        sntfTotalOperacaoPortuaria.setEnabled(false);
        sntfTotalOperacaoPortuaria.setName("sntfTotalOperacaoPortuaria"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.valorTotalOperacaoPortuaria}"), sntfTotalOperacaoPortuaria, BeanProperty.create("bigDecimalValue"));
        bindingGroup.addBinding(binding);

        GroupLayout pnlCustoDaOperacaoPortuariaLayout = new GroupLayout(pnlCustoDaOperacaoPortuaria);
        pnlCustoDaOperacaoPortuaria.setLayout(pnlCustoDaOperacaoPortuariaLayout);
        pnlCustoDaOperacaoPortuariaLayout.setHorizontalGroup(
            pnlCustoDaOperacaoPortuariaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlCustoDaOperacaoPortuariaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCustoDaOperacaoPortuariaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCustoDaOperacaoPortuariaLayout.createSequentialGroup()
                        .addComponent(sLabel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sntfTotalOperacaoPortuaria, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlCustoDaOperacaoPortuariaLayout.createSequentialGroup()
                        .addGroup(pnlCustoDaOperacaoPortuariaLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(labelMercadorias5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMercadorias3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(sLabel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCustoDaOperacaoPortuariaLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(sntfOperadorPortuaria1, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(sntfOGMO, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(sntfTotalOperacaoPortuaria1, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlCustoDaOperacaoPortuariaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, pnlCustoDaOperacaoPortuariaLayout.createSequentialGroup()
                                .addComponent(labelMercadorias4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sntfHExcedenteOpPortuario, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, pnlCustoDaOperacaoPortuariaLayout.createSequentialGroup()
                                .addComponent(labelEmpresa2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sNumericTextFieldOGMODOBRA, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))))
                    .addComponent(pnlCustoDaOperacaoPortuaria1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlCustoDaOperacaoPortuariaLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {sntfOGMO, sntfOperadorPortuaria1});

        pnlCustoDaOperacaoPortuariaLayout.setVerticalGroup(
            pnlCustoDaOperacaoPortuariaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlCustoDaOperacaoPortuariaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCustoDaOperacaoPortuariaLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMercadorias3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sntfOperadorPortuaria1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMercadorias4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sntfHExcedenteOpPortuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCustoDaOperacaoPortuariaLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMercadorias5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sntfOGMO, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sNumericTextFieldOGMODOBRA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpresa2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCustoDaOperacaoPortuariaLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(sLabel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sntfTotalOperacaoPortuaria1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCustoDaOperacaoPortuaria1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCustoDaOperacaoPortuariaLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(sLabel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sntfTotalOperacaoPortuaria, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );

        pnlCustoDaOperacaoPortuariaLayout.linkSize(SwingConstants.VERTICAL, new Component[] {sntfOGMO, sntfOperadorPortuaria1});

        sntfTransporteMaritimo.setIntegerPlaces(new Short((short)13));
        sntfTransporteMaritimo.setNumberClass(BigDecimal.class);
        sntfTransporteMaritimo.setText("sNumericTextField2");
        sntfTransporteMaritimo.setName("sntfTransporteMaritimo"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.valorTransporteMaritimo}"), sntfTransporteMaritimo, BeanProperty.create("bigDecimalValue"));
        bindingGroup.addBinding(binding);

        sntfTransporteRodoviario.setIntegerPlaces(new Short((short)13));
        sntfTransporteRodoviario.setNumberClass(BigDecimal.class);
        sntfTransporteRodoviario.setText("sNumericTextField2");
        sntfTransporteRodoviario.setName("sntfTransporteRodoviario"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.valorTransporteRodoviario}"), sntfTransporteRodoviario, BeanProperty.create("bigDecimalValue"));
        bindingGroup.addBinding(binding);

        txtCentroCustoOperacao.setDocument(new GenericDocument(50));
        txtCentroCustoOperacao.setName("txtCentroCustoOperacao"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.centroCusto}"), txtCentroCustoOperacao, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        GroupLayout pnlCustoDaOperacaoLayout = new GroupLayout(pnlCustoDaOperacao);
        pnlCustoDaOperacao.setLayout(pnlCustoDaOperacaoLayout);
        pnlCustoDaOperacaoLayout.setHorizontalGroup(
            pnlCustoDaOperacaoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlCustoDaOperacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCustoDaOperacaoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCustoDaOperacaoPortuaria, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlCustoDaOperacaoLayout.createSequentialGroup()
                        .addComponent(labelMercadorias2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sntfTransporteMaritimo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelMercadorias1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sntfTransporteRodoviario, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelEmpresa1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCentroCustoOperacao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlCustoDaOperacaoLayout.setVerticalGroup(
            pnlCustoDaOperacaoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlCustoDaOperacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCustoDaOperacaoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMercadorias2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMercadorias1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpresa1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sntfTransporteMaritimo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sntfTransporteRodoviario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCentroCustoOperacao, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCustoDaOperacaoPortuaria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlSolicitacaoDeTransito.setBorder(BorderFactory.createTitledBorder("Solicitação de Trânsito"));
        pnlSolicitacaoDeTransito.setName("pnlSolicitacaoDeTransito"); // NOI18N

        scrollTripulante.setName("scrollTripulante"); // NOI18N

        tabelaSolicitacaoTransito.setSortable(true);
        tabelaSolicitacaoTransito.setName("tabelaSolicitacaoTransito"); // NOI18N
        tabelaSolicitacaoTransito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ELProperty eLProperty = ELProperty.create("${model.servicoSuprimento.transitos}");
        JTableBinding jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tabelaSolicitacaoTransito);
        JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${local}"));
        columnBinding.setColumnName("Local");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${tipoMercadorias}"));
        columnBinding.setColumnName("Tipo Mercadorias");
        columnBinding.setColumnClass(TipoMercadorias.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${tipoMaterial}"));
        columnBinding.setColumnName("Tipo Material");
        columnBinding.setColumnClass(TipoMaterial.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${nomeFornecedor}"));
        columnBinding.setColumnName("Fornecedor");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${formularioGeradoReceitaFederal}"));
        columnBinding.setColumnName("Formulário Gerado");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.suprimentoTransitoSelecionado}"), tabelaSolicitacaoTransito, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        scrollTripulante.setViewportView(tabelaSolicitacaoTransito);
        tabelaSolicitacaoTransito.getColumnModel().getColumn(4).setCellRenderer(imageBooleanGerado);

        btnExcluir.setAction(actionExcluir);
        btnExcluir.setName("btnExcluir"); // NOI18N

        btnEditar.setAction(actionEditar);
        btnEditar.setName("btnEditar"); // NOI18N

        btnAdicionarFornecedor.setAction(actionAdicionar);
        btnAdicionarFornecedor.setName("btnAdicionarFornecedor"); // NOI18N

        sButton1.setAction(actionGerarFormulario);
        sButton1.setName("sButton1"); // NOI18N

        GroupLayout pnlSolicitacaoDeTransitoLayout = new GroupLayout(pnlSolicitacaoDeTransito);
        pnlSolicitacaoDeTransito.setLayout(pnlSolicitacaoDeTransitoLayout);
        pnlSolicitacaoDeTransitoLayout.setHorizontalGroup(
            pnlSolicitacaoDeTransitoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlSolicitacaoDeTransitoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSolicitacaoDeTransitoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTripulante)
                    .addGroup(GroupLayout.Alignment.TRAILING, pnlSolicitacaoDeTransitoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdicionarFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnlSolicitacaoDeTransitoLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnAdicionarFornecedor, btnEditar, btnExcluir, sButton1});

        pnlSolicitacaoDeTransitoLayout.setVerticalGroup(
            pnlSolicitacaoDeTransitoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlSolicitacaoDeTransitoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollTripulante, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSolicitacaoDeTransitoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdicionarFornecedor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cboServico.setDisplayProperty(Servico.PROP_NOME_SERVICO);
        cboServico.setName("cboServico"); // NOI18N

        eLProperty = ELProperty.create("${model.servicos}");
        JComboBoxBinding jComboBoxBinding = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, cboServico);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.empresaServico}"), cboServico, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        labelAgente3.setText("Embarcação:");
        labelAgente3.setName("labelAgente3"); // NOI18N

        labelEmpresa.setText("Empresa:");
        labelEmpresa.setName("labelEmpresa"); // NOI18N

        cboEmpresa.setName("cboEmpresa"); // NOI18N
        cboEmpresa.setPopupResizeEnabled(true);

        eLProperty = ELProperty.create("${model.empresasMaritimas}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, cboEmpresa);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.empresaMaritima}"), cboEmpresa, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        labelAgente4.setText("Observações:");
        labelAgente4.setName("labelAgente4"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        sTextArea1.setColumns(20);
        sTextArea1.setDocument(new GenericDocument(500));
        sTextArea1.setLineWrap(true);
        sTextArea1.setRows(5);
        sTextArea1.setWrapStyleWord(true);
        sTextArea1.setName("sTextArea1"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoSuprimento.servicoProtecao.observacao}"), sTextArea1, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(sTextArea1);

        GroupLayout pnlSuprimentosAosNaviosLayout = new GroupLayout(pnlSuprimentosAosNavios);
        pnlSuprimentosAosNavios.setLayout(pnlSuprimentosAosNaviosLayout);
        pnlSuprimentosAosNaviosLayout.setHorizontalGroup(
            pnlSuprimentosAosNaviosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuprimentosAosNaviosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSuprimentosAosNaviosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSuprimentosAosNaviosLayout.createSequentialGroup()
                        .addGroup(pnlSuprimentosAosNaviosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(labelAgente1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAgente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAgente3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlSuprimentosAosNaviosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, pnlSuprimentosAosNaviosLayout.createSequentialGroup()
                                .addComponent(txtServicoExecutado, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lblDatasolicitacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sistamDateChooserServicoExecutado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, pnlSuprimentosAosNaviosLayout.createSequentialGroup()
                                .addComponent(sistamDateChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sistamTimeChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelAgente2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sistamDateChooserTermino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sistamTimeChooserTermino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cboServico, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboEmpresa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(pnlCustoDaOperacao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSolicitacaoDeTransito, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlSuprimentosAosNaviosLayout.createSequentialGroup()
                        .addComponent(labelAgente4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        pnlSuprimentosAosNaviosLayout.setVerticalGroup(
            pnlSuprimentosAosNaviosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuprimentosAosNaviosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSuprimentosAosNaviosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAgente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtServicoExecutado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sistamDateChooserServicoExecutado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDatasolicitacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuprimentosAosNaviosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAgente1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sistamDateChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sistamTimeChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sistamTimeChooserTermino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sistamDateChooserTermino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAgente2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuprimentosAosNaviosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuprimentosAosNaviosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAgente3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCustoDaOperacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSolicitacaoDeTransito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSuprimentosAosNaviosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(labelAgente4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                .addContainerGap())
        );

        GroupLayout pnlSuprimentoAoNavioLayout = new GroupLayout(pnlSuprimentoAoNavio);
        pnlSuprimentoAoNavio.setLayout(pnlSuprimentoAoNavioLayout);
        pnlSuprimentoAoNavioLayout.setHorizontalGroup(
            pnlSuprimentoAoNavioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(pnlBotoes, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
            .addGroup(pnlSuprimentoAoNavioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSuprimentosAosNavios, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSuprimentoAoNavioLayout.setVerticalGroup(
            pnlSuprimentoAoNavioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuprimentoAoNavioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSuprimentosAosNavios, GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBotoes, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(pnlSuprimentoAoNavio, BorderLayout.CENTER);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON
    // Variables declaration - do not modify//GEN-BEGIN:variables
    SButton btnAdicionarFornecedor;
    SButton btnEditar;
    SButton btnExcluir;
    SComboBox cboEmpresa;
    SComboBox cboServico;
    SLabel labelAgente;
    SLabel labelAgente1;
    SLabel labelAgente2;
    SLabel labelAgente3;
    SLabel labelAgente4;
    SLabel labelEmpresa;
    SLabel labelEmpresa1;
    SLabel labelEmpresa2;
    SLabel labelEmpresa3;
    SLabel labelMercadorias1;
    SLabel labelMercadorias2;
    SLabel labelMercadorias3;
    SLabel labelMercadorias4;
    SLabel labelMercadorias5;
    SLabel labelMercadorias7;
    SLabel lblDatasolicitacao;
    SPanel pnlSuprimentoAoNavio;
    JScrollPane scrollTripulante;
    SistamDateChooser sistamDateChooserServicoExecutado;
    STable tabelaSolicitacaoTransito;
    STextField txtServicoExecutado;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
