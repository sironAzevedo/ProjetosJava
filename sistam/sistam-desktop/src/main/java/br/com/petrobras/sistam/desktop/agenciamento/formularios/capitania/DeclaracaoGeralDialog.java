package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.SDialog;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SComboBox;
import br.com.petrobras.fcorp.swing.components.SHelpLabel;
import br.com.petrobras.fcorp.swing.components.SLabel;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.SRadioPanel;
import br.com.petrobras.fcorp.swing.components.STextArea;
import br.com.petrobras.fcorp.swing.components.util.GenericDocument;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;

public class DeclaracaoGeralDialog extends SDialog {
    private DeclaracaoGeralModel model;
    
    public DeclaracaoGeralDialog(java.awt.Frame parent, Agenciamento agenciamento) {
        super(parent, true);
        model = new DeclaracaoGeralModel(agenciamento);
        initComponents();
        setLocationRelativeTo(parent);
        carregarCombos();
    }

    public DeclaracaoGeralModel getModel() {
        return model;
    }
    
    private void carregarCombos() {
        AssyncInvoker.create(model,"carregarPontosAtracacao")
                .disabling(cboPonto)
                .settingLoadingIconOn(lblPonto)
                .schedule();
    }
    
    public void gerarFormularioEntrada(){
        model.validarDeclaracaoGeralEntrada();
        model.carregarVO();
        model.carregarEntradaVO();
        model.registrarEmissaoEntrada();
        RelatorioUtil.abrirRelatorioDeclaracaoGeralEntrada(model.getDeclaracaoGeralCapitaniaVO());
    }
    public void gerarFormularioPedidoDespacho(){
        model.validarDeclaracaoGeralDespacho();
        model.carregarVO();
        model.carregarDespachoVO();
        model.registrarEmissaoPedidoDespacho();
        RelatorioUtil.abrirRelatorioDeclaracaoGeralDespacho(model.getDeclaracaoGeralCapitaniaVO());
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

        GenericAction actionGerarFormularioEntrada = new GenericAction();
        GenericAction actionGerarFormularioDespacho = new GenericAction();
        SPanel pnlCabecalho = new SPanel();
        SLabel labelNome = new SLabel();
        SLabel labelViagem = new SLabel();
        SLabel labelNomeConteudo = new SLabel();
        SLabel labelViagemConteudo = new SLabel();
        SPanel pnlCentral = new SPanel();
        SPanel pnlInfComplementares = new SPanel();
        SLabel sLabel9 = new SLabel();
        SRadioPanel radioPanelTripulantes = new SRadioPanel();
        SLabel sLabel10 = new SLabel();
        SRadioPanel radioPanelPassageiros = new SRadioPanel();
        SRadioPanel radioPanelPassageiros1 = new SRadioPanel();
        SLabel sLabel11 = new SLabel();
        SLabel sLabel3 = new SLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        STextArea sTextArea1 = new STextArea();
        SPanel sPanel1 = new SPanel();
        JScrollPane jScrollPane2 = new JScrollPane();
        STextArea sTextArea2 = new STextArea();
        SHelpLabel sHelpLabel1 = new SHelpLabel();
        lblPonto = new SLabel();
        cboPonto = new SComboBox();
        SPanel pnlBotoes = new SPanel();
        SButton btImprimirEntrada = new SButton();
        SButton btImprimirPedidoDespacho = new SButton();

        actionGerarFormularioEntrada.setIcon(new ImageIcon(getClass().getResource("/icons/print.png"))); // NOI18N
        actionGerarFormularioEntrada.setMethodName("gerarFormularioEntrada");
        actionGerarFormularioEntrada.setTarget(this);
        actionGerarFormularioEntrada.setText("Gerar Formulário (Entrada)");
        actionGerarFormularioEntrada.setToolTipText("Declaração Geral (Entrada)");

        actionGerarFormularioDespacho.setIcon(new ImageIcon(getClass().getResource("/icons/print.png"))); // NOI18N
        actionGerarFormularioDespacho.setMethodName("gerarFormularioPedidoDespacho");
        actionGerarFormularioDespacho.setTarget(this);
        actionGerarFormularioDespacho.setText("Gerar Formulário (Pedido Despacho)");
        actionGerarFormularioDespacho.setToolTipText("Declaração Geral (Pedido de Despacho)");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Emitir Declaração Geral ");

        pnlCabecalho.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        pnlCabecalho.setName("pnlCabecalho"); // NOI18N
        pnlCabecalho.setPreferredSize(new Dimension(400, 40));

        ResourceBundle bundle = ResourceBundle.getBundle("sistam-labels"); // NOI18N
        labelNome.setText(bundle.getString("lblNome")); // NOI18N
        labelNome.setName("labelNome"); // NOI18N

        labelViagem.setText(bundle.getString("lblViagem")); // NOI18N
        labelViagem.setName("labelViagem"); // NOI18N

        labelNomeConteudo.setName("labelNomeConteudo"); // NOI18N
        labelNomeConteudo.setRequiredField(true);

        Binding binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.agenciamento.embarcacao.nomeCompleto}"), labelNomeConteudo, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        labelViagemConteudo.setName("labelViagemConteudo"); // NOI18N
        labelViagemConteudo.setRequiredField(true);

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.agenciamento.vgm}"), labelViagemConteudo, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        GroupLayout pnlCabecalhoLayout = new GroupLayout(pnlCabecalho);
        pnlCabecalho.setLayout(pnlCabecalhoLayout);
        pnlCabecalhoLayout.setHorizontalGroup(
            pnlCabecalhoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(pnlCabecalhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(labelNomeConteudo, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(labelViagem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(labelViagemConteudo, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCabecalhoLayout.setVerticalGroup(
            pnlCabecalhoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(pnlCabecalhoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCabecalhoLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(labelNomeConteudo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelViagemConteudo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlCabecalhoLayout.createSequentialGroup()
                        .addGroup(pnlCabecalhoLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(labelNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelViagem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(pnlCabecalho, BorderLayout.PAGE_START);

        pnlCentral.setName("pnlCentral"); // NOI18N

        pnlInfComplementares.setBorder(BorderFactory.createTitledBorder(bundle.getString("anexos"))); // NOI18N
        pnlInfComplementares.setName("pnlInfComplementares"); // NOI18N

        sLabel9.setText(bundle.getString("lbListaTripulantesAlteracao")); // NOI18N
        sLabel9.setName("sLabel9"); // NOI18N

        radioPanelTripulantes.setName("radioPanelTripulantes"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.listaSimNao}"), radioPanelTripulantes, BeanProperty.create("elements"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.tripulantes}"), radioPanelTripulantes, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        GroupLayout radioPanelTripulantesLayout = new GroupLayout(radioPanelTripulantes);
        radioPanelTripulantes.setLayout(radioPanelTripulantesLayout);
        radioPanelTripulantesLayout.setHorizontalGroup(
            radioPanelTripulantesLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
        );
        radioPanelTripulantesLayout.setVerticalGroup(
            radioPanelTripulantesLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        sLabel10.setText(bundle.getString("lblListaPassageirosAlteracao")); // NOI18N
        sLabel10.setName("sLabel10"); // NOI18N

        radioPanelPassageiros.setName("radioPanelPassageiros"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.listaSimNao}"), radioPanelPassageiros, BeanProperty.create("elements"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.passageiros}"), radioPanelPassageiros, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        GroupLayout radioPanelPassageirosLayout = new GroupLayout(radioPanelPassageiros);
        radioPanelPassageiros.setLayout(radioPanelPassageirosLayout);
        radioPanelPassageirosLayout.setHorizontalGroup(
            radioPanelPassageirosLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
        );
        radioPanelPassageirosLayout.setVerticalGroup(
            radioPanelPassageirosLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        radioPanelPassageiros1.setName("radioPanelPassageiros1"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.listaSimNao}"), radioPanelPassageiros1, BeanProperty.create("elements"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.planilhaGMDSS}"), radioPanelPassageiros1, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        GroupLayout radioPanelPassageiros1Layout = new GroupLayout(radioPanelPassageiros1);
        radioPanelPassageiros1.setLayout(radioPanelPassageiros1Layout);
        radioPanelPassageiros1Layout.setHorizontalGroup(
            radioPanelPassageiros1Layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
        );
        radioPanelPassageiros1Layout.setVerticalGroup(
            radioPanelPassageiros1Layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        sLabel11.setText(bundle.getString("lblPlanilhaGMDSS")); // NOI18N
        sLabel11.setName("sLabel11"); // NOI18N

        GroupLayout pnlInfComplementaresLayout = new GroupLayout(pnlInfComplementares);
        pnlInfComplementares.setLayout(pnlInfComplementaresLayout);
        pnlInfComplementaresLayout.setHorizontalGroup(
            pnlInfComplementaresLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(pnlInfComplementaresLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlInfComplementaresLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(sLabel11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(pnlInfComplementaresLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(pnlInfComplementaresLayout.createSequentialGroup()
                        .addComponent(radioPanelPassageiros, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(Alignment.TRAILING, pnlInfComplementaresLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlInfComplementaresLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(radioPanelTripulantes, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioPanelPassageiros1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlInfComplementaresLayout.setVerticalGroup(
            pnlInfComplementaresLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(pnlInfComplementaresLayout.createSequentialGroup()
                .addGroup(pnlInfComplementaresLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(radioPanelTripulantes, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel9, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(pnlInfComplementaresLayout.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(sLabel10, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(radioPanelPassageiros, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(pnlInfComplementaresLayout.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(sLabel11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radioPanelPassageiros1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
        );

        sLabel3.setText(bundle.getString("lblObservacao")); // NOI18N
        sLabel3.setName("sLabel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        sTextArea1.setColumns(20);
        sTextArea1.setDocument(new GenericDocument(100) );
        sTextArea1.setLineWrap(true);
        sTextArea1.setRows(2);
        sTextArea1.setWrapStyleWord(true);
        sTextArea1.setName("sTextArea1"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.declaracaoGeralCapitaniaVO.obs}"), sTextArea1, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(sTextArea1);

        sPanel1.setBorder(BorderFactory.createTitledBorder("Informações resumidas da Viagem (portos de escalas anteriores e subsequentes)"));
        sPanel1.setName("sPanel1"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        sTextArea2.setColumns(20);
        sTextArea2.setDocument(new GenericDocument(150) );
        sTextArea2.setLineWrap(true);
        sTextArea2.setRows(3);
        sTextArea2.setWrapStyleWord(true);
        sTextArea2.setName("sTextArea2"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.escalas}"), sTextArea2, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane2.setViewportView(sTextArea2);

        sHelpLabel1.setText("Consulta 2 últimos portos do SIGO + porto atual + porto destino");
        sHelpLabel1.setName("sHelpLabel1"); // NOI18N

        GroupLayout sPanel1Layout = new GroupLayout(sPanel1);
        sPanel1.setLayout(sPanel1Layout);
        sPanel1Layout.setHorizontalGroup(
            sPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(sPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sPanel1Layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(Alignment.TRAILING, sPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(sHelpLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        sPanel1Layout.setVerticalGroup(
            sPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(sPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(sHelpLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        lblPonto.setText("Posição da Embarcação no Porto:");
        lblPonto.setName("lblPonto"); // NOI18N

        cboPonto.setName("cboPonto"); // NOI18N

        ELProperty eLProperty = ELProperty.create("${model.pontosAtracacao}");
        JComboBoxBinding jComboBoxBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, this, eLProperty, cboPonto);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.pontoAtracacaoSelecionado}"), cboPonto, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        GroupLayout pnlCentralLayout = new GroupLayout(pnlCentral);
        pnlCentral.setLayout(pnlCentralLayout);
        pnlCentralLayout.setHorizontalGroup(
            pnlCentralLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(pnlCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCentralLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(sPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlCentralLayout.createSequentialGroup()
                        .addComponent(pnlInfComplementares, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(pnlCentralLayout.createParallelGroup(Alignment.LEADING)
                            .addGroup(pnlCentralLayout.createSequentialGroup()
                                .addComponent(sLabel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(pnlCentralLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblPonto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(cboPonto, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCentralLayout.setVerticalGroup(
            pnlCentralLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, pnlCentralLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(pnlCentralLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lblPonto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPonto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(sPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(pnlCentralLayout.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(pnlInfComplementares, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCentralLayout.createSequentialGroup()
                        .addComponent(sLabel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        getContentPane().add(pnlCentral, BorderLayout.CENTER);

        pnlBotoes.setBorder(BorderFactory.createEtchedBorder());
        pnlBotoes.setName("pnlBotoes"); // NOI18N
        pnlBotoes.setPreferredSize(new Dimension(400, 40));

        btImprimirEntrada.setAction(actionGerarFormularioEntrada);
        btImprimirEntrada.setName("btImprimirEntrada"); // NOI18N
        pnlBotoes.add(btImprimirEntrada);

        btImprimirPedidoDespacho.setAction(actionGerarFormularioDespacho);
        btImprimirPedidoDespacho.setName("btImprimirPedidoDespacho"); // NOI18N
        pnlBotoes.add(btImprimirPedidoDespacho);

        getContentPane().add(pnlBotoes, BorderLayout.PAGE_END);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON

    // Variables declaration - do not modify//GEN-BEGIN:variables
    SComboBox cboPonto;
    SLabel lblPonto;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
