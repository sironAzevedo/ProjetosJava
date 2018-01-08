package br.com.petrobras.sistam.desktop.agenciamento.documento;

import br.com.petrobras.fcorp.swing.base.SDialog;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SCheckBox;
import br.com.petrobras.fcorp.swing.components.SComboBox;
import br.com.petrobras.fcorp.swing.components.SLabel;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.STextField;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.components.SistamDateChooser;
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;

public class DetalheDocumentoDialog extends SDialog  implements PropertyChangeListener {
    private static final String LABEL_MANOBRA_SELECIONADA = "Manobra Selecionada:";
    private static final String LABEL_PRODUTO_SELECIONADO = "Produto Selecionado:";
    
    public static final String DOCUMENTO_INSERIDO = "DOCUMENTO_INSERIDO";
    public static final String DOCUMENTO_ALTERADO = "DOCUMENTO_ALTERADO";
    
    
    
    private DetalheDocumentoDialogModel model;
  
    public DetalheDocumentoDialog(java.awt.Frame parent, Documento documento) {
        super(parent, true);
        this.model = new DetalheDocumentoDialogModel(documento);
        initComponents();
        setLocationRelativeTo(parent);
       
        AssyncInvoker
                .create(model, "carregarListaDeEmitentes")
                .disabling(comboEmitente)
                .settingLoadingIconOn(labelEmitente)
                .schedule();

        model.getDocumento().addPropertyChangeListener(this);

        if (TipoDocumento.REGISTRO_MOVIMENTACAO.equals(documento.getTipoDocumento())) {
            mostrarComponentesManobraOperacaoSelecionada(true, LABEL_MANOBRA_SELECIONADA);
        } 
        else if (TipoDocumento.BILL_OF_LADING.equals(documento.getTipoDocumento())) {
            mostrarComponentesManobraOperacaoSelecionada(true, LABEL_PRODUTO_SELECIONADO);
        } 
        else {
            mostrarComponentesManobraOperacaoSelecionada(false, null);
        }
    }
    
    public DetalheDocumentoDialogModel getModel() {
        return model;
    }
    
    public void salvar() {
        String property = model.getDocumento().getId() == null ? DOCUMENTO_INSERIDO : DOCUMENTO_ALTERADO;

        model.salvar();
        firePropertyChange(property, null, null);
        
        if (model.isContinuarInserindo()){
            model.prepararNovoDocumento();
            model.getDocumento().addPropertyChangeListener(this);
            mostrarComponentesManobraOperacaoSelecionada(false, null);
        }else{
            dispose();
        }
    }
    
    public void alterarManobraOperacaoSelecionada(){
        if (TipoDocumento.REGISTRO_MOVIMENTACAO.equals(model.getDocumento().getTipoDocumento())) {
            mostrarListaDeManobrasDisponiveis();
        }
        else if (TipoDocumento.BILL_OF_LADING.equals(model.getDocumento().getTipoDocumento())) {
            mostrarListaDeOperacoesDisponiveis();
        }
    }
    
    private void mostrarListaDeManobrasDisponiveis() {
        List<Manobra> manobras = model.getService().buscarManobrasPorAgenciamento(model.getDocumento().getAgenciamento());
        SelecionarManobraEncerradaDialog dialog = new SelecionarManobraEncerradaDialog(SistamApp.getSistamApp().getMainFrame(), manobras);
        dialog.getModel().setManobraSelecionada(model.getDocumento().getManobra());
        dialog.setVisible(true);

        if (dialog.getModel().getManobraSelecionada() != null) {
            model.getDocumento().setManobra(dialog.getModel().getManobraSelecionada());
            mostrarComponentesManobraOperacaoSelecionada(true, LABEL_MANOBRA_SELECIONADA);
            
        } else {
            model.getDocumento().setTipoDocumento(null);
            comboTipo.setSelectedItem(null);
        }
    }
    
    private void mostrarListaDeOperacoesDisponiveis() {
        List<Operacao> listaOperacoes = model.getService().buscarOperacoesPorAgenciamento(model.getDocumento().getAgenciamento());
        SelecionarOperacaoDialog dialog = new SelecionarOperacaoDialog(SistamApp.getSistamApp().getMainFrame(), listaOperacoes);
        dialog.getModel().setOperacaoSelecionada(model.getDocumento().getOperacao());
        dialog.setVisible(true);

        if (dialog.getModel().getOperacaoSelecionada() != null) {
            model.getDocumento().setOperacao(dialog.getModel().getOperacaoSelecionada());
            mostrarComponentesManobraOperacaoSelecionada(true, LABEL_PRODUTO_SELECIONADO);
        } else {
            model.getDocumento().setTipoDocumento(null);
            comboTipo.setSelectedItem(null);
        }

    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Documento.PROP_TIPO)) {
            
            if (TipoDocumento.REGISTRO_MOVIMENTACAO.equals(model.getDocumento().getTipoDocumento())) {
                mostrarListaDeManobrasDisponiveis();
            } 
            else if (TipoDocumento.BILL_OF_LADING.equals(model.getDocumento().getTipoDocumento())) {
                mostrarListaDeOperacoesDisponiveis();
            } 
            else {
                mostrarComponentesManobraOperacaoSelecionada(false, null);
                model.getDocumento().setOperacao(null);
                model.getDocumento().setManobra(null);
            }
        }

    }
    
    private void mostrarComponentesManobraOperacaoSelecionada(boolean mostrar, String label) {
        labelManobraOperacaoSelecionada.setText(label);
        labelManobraOperacaoSelecionada.setVisible(mostrar);
        campoManobraOperacao.setVisible(mostrar);
        model.carregarDescricaoManobraOperacaoSelecionada();
        botaoEditar.setVisible(mostrar);
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

        GenericAction actionSalvar = new GenericAction();
        GenericAction actionVisualizarManobraOperacao = new GenericAction();
        SPanel pnlEscalas = new SPanel();
        SLabel sLabel1 = new SLabel();
        comboTipo = new SComboBox();
        labelEmitente = new SLabel();
        SLabel sLabel3 = new SLabel();
        SistamDateChooser sistamDateChooser1 = new SistamDateChooser();
        SLabel sLabel4 = new SLabel();
        SComboBox sComboBox2 = new SComboBox();
        comboEmitente = new SComboBox();
        labelManobraOperacaoSelecionada = new SLabel();
        campoManobraOperacao = new STextField();
        SLabel labelAutoridadeMaritima = new SLabel();
        comboAutoridadeMaritima = new SComboBox();
        botaoEditar = new SButton();
        SPanel pnlBotoes = new SPanel();
        SButton btnSalvar = new SButton();
        SCheckBox sCheckBox1 = new SCheckBox();

        actionSalvar.setIcon(new ImageIcon(getClass().getResource("/icons/disk.png"))); // NOI18N
        actionSalvar.setMethodName("salvar");
        actionSalvar.setTarget(this);
        ResourceBundle bundle = ResourceBundle.getBundle("sistam-labels"); // NOI18N
        actionSalvar.setText(bundle.getString("salvar")); // NOI18N
        actionSalvar.setToolTipText("Salva os dados informados do documento");

        actionVisualizarManobraOperacao.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionVisualizarManobraOperacao.setMethodName("alterarManobraOperacaoSelecionada");
        actionVisualizarManobraOperacao.setTarget(this);
        actionVisualizarManobraOperacao.setToolTipText("Altera o item selecionado");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Documento");

        pnlEscalas.setBorder(BorderFactory.createTitledBorder("Informações do Documento"));
        pnlEscalas.setName("pnlEscalas"); // NOI18N

        sLabel1.setText("Tipo:");
        sLabel1.setName("sLabel1"); // NOI18N

        comboTipo.setName("comboTipo"); // NOI18N
        comboTipo.setPopupResizeEnabled(true);

        ELProperty eLProperty = ELProperty.create("${model.listaTipoDocumento}");
        JComboBoxBinding jComboBoxBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, this, eLProperty, comboTipo);
        bindingGroup.addBinding(jComboBoxBinding);
        Binding binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.documento.tipoDocumento}"), comboTipo, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        labelEmitente.setText("Emitente:");
        labelEmitente.setName("labelEmitente"); // NOI18N

        sLabel3.setText("Data Emissão:");
        sLabel3.setName("sLabel3"); // NOI18N

        sistamDateChooser1.setName("sistamDateChooser1"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.documento.dataEmissao}"), sistamDateChooser1, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        sLabel4.setText("Representante Legal:");
        sLabel4.setName("sLabel4"); // NOI18N

        sComboBox2.setName("sComboBox2"); // NOI18N

        eLProperty = ELProperty.create("${model.listaRepresentante}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, this, eLProperty, sComboBox2);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.documento.representante}"), sComboBox2, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        comboEmitente.setName("comboEmitente"); // NOI18N

        eLProperty = ELProperty.create("${model.listaEmitentes}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, this, eLProperty, comboEmitente);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.emitenteSelecionado}"), comboEmitente, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        labelManobraOperacaoSelecionada.setText("manobraOperacao:");
        labelManobraOperacaoSelecionada.setName("labelManobraOperacaoSelecionada"); // NOI18N

        campoManobraOperacao.setName("campoManobraOperacao"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.manobraOperacaoSelecionada}"), campoManobraOperacao, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        labelAutoridadeMaritima.setText("Autoridade Marítima:");
        labelAutoridadeMaritima.setName("labelAutoridadeMaritima"); // NOI18N

        comboAutoridadeMaritima.setName("comboAutoridadeMaritima"); // NOI18N

        eLProperty = ELProperty.create("${model.listaAutoridades}");
        jComboBoxBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, this, eLProperty, comboAutoridadeMaritima);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.autoridadeSelecionada}"), comboAutoridadeMaritima, BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        botaoEditar.setAction(actionVisualizarManobraOperacao);
        botaoEditar.setName("botaoEditar"); // NOI18N

        GroupLayout pnlEscalasLayout = new GroupLayout(pnlEscalas);
        pnlEscalas.setLayout(pnlEscalasLayout);
        pnlEscalasLayout.setHorizontalGroup(
            pnlEscalasLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(pnlEscalasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEscalasLayout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(labelAutoridadeMaritima, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelManobraOperacaoSelecionada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmitente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(pnlEscalasLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(comboEmitente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sComboBox2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlEscalasLayout.createSequentialGroup()
                        .addComponent(campoManobraOperacao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(botaoEditar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEscalasLayout.createSequentialGroup()
                        .addGroup(pnlEscalasLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(sistamDateChooser1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboAutoridadeMaritima, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 215, Short.MAX_VALUE))
                    .addComponent(comboTipo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        pnlEscalasLayout.setVerticalGroup(
            pnlEscalasLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, pnlEscalasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEscalasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labelAutoridadeMaritima, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboAutoridadeMaritima, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(pnlEscalasLayout.createParallelGroup(Alignment.CENTER)
                    .addComponent(sLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(pnlEscalasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labelManobraOperacaoSelecionada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoManobraOperacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEditar, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(pnlEscalasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(sistamDateChooser1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(pnlEscalasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labelEmitente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboEmitente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(pnlEscalasLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(sComboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBotoes.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pnlBotoes.setName("pnlBotoes"); // NOI18N

        btnSalvar.setAction(actionSalvar);
        btnSalvar.setText("Salvar");
        btnSalvar.setName("btnSalvar"); // NOI18N
        pnlBotoes.add(btnSalvar);

        sCheckBox1.setText("Após salvar, continuar inserindo.");
        sCheckBox1.setName("sCheckBox1"); // NOI18N
        sCheckBox1.setOpaque(false);

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.continuarInserindo}"), sCheckBox1, BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(pnlEscalas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sCheckBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlBotoes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEscalas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(sCheckBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(pnlBotoes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON

    // Variables declaration - do not modify//GEN-BEGIN:variables
    SButton botaoEditar;
    STextField campoManobraOperacao;
    SComboBox comboAutoridadeMaritima;
    SComboBox comboEmitente;
    SComboBox comboTipo;
    SLabel labelEmitente;
    SLabel labelManobraOperacaoSelecionada;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
