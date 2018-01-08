package br.com.petrobras.sistam.desktop.agencia;

import br.com.petrobras.fcorp.swing.base.SDialog;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SLabel;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.STable;
import br.com.petrobras.fcorp.swing.components.STextField;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Destinatario;
import br.com.petrobras.sistam.desktop.SistamApp;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

public class DestinatariosDialog extends SDialog {
    private DestinatariosModel model;
    
    public DestinatariosDialog(java.awt.Frame parent, Agencia agencia) {
        super(parent, true);
        this.model = new DestinatariosModel(agencia);
        initComponents();
        setLocationRelativeTo(parent);
    }
    
    public DestinatariosModel getModel() {
        return model;
    }
    
    public void adicionar(){
        Destinatario destinatario = model.obterNovoDestinatario();
        DetalhesDestinatarioDialog dialog = new DetalhesDestinatarioDialog(SistamApp.getApplication().getMainFrame(), destinatario);
        dialog.setVisible(true);
        
        if (dialog.getModel().estaSalvo()){
            model.adicionarDestinatario(dialog.getModel().getDestinatario());
        }
        
    }
    
    public void alterar(){
        Destinatario destinatario = model.obterDestinatarioParaEdicao();
        DetalhesDestinatarioDialog dialog = new DetalhesDestinatarioDialog(SistamApp.getApplication().getMainFrame(), destinatario);
        dialog.setVisible(true);
        
        if (dialog.getModel().estaSalvo()){
            model.atualizarDestinatario(dialog.getModel().getDestinatario());
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        actionAdicionar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionEditar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionExcluir = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        br.com.petrobras.fcorp.swing.components.SLabel sLabel1 = new br.com.petrobras.fcorp.swing.components.SLabel();
        br.com.petrobras.fcorp.swing.components.STextField sTextField1 = new br.com.petrobras.fcorp.swing.components.STextField();
        br.com.petrobras.fcorp.swing.components.SPanel sPanel1 = new br.com.petrobras.fcorp.swing.components.SPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        br.com.petrobras.fcorp.swing.components.STable sTable1 = new br.com.petrobras.fcorp.swing.components.STable();
        br.com.petrobras.fcorp.swing.components.SButton botaoSalvar = new br.com.petrobras.fcorp.swing.components.SButton();
        br.com.petrobras.fcorp.swing.components.SButton botaoSalvar1 = new br.com.petrobras.fcorp.swing.components.SButton();
        br.com.petrobras.fcorp.swing.components.SButton botaoExcluir = new br.com.petrobras.fcorp.swing.components.SButton();

        actionAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionar.setMethodName("adicionar");
        actionAdicionar.setTarget(this);
        actionAdicionar.setText("Adicionar");
        actionAdicionar.setToolTipText("Adiciona um novo destinatário");

        actionEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditar.setMethodName("alterar");
        actionEditar.setTarget(this);
        actionEditar.setText("Alterar");
        actionEditar.setToolTipText("Altera o destinatário selecionado");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.destinatarioSelecionado}"), actionEditar, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionExcluir.setConfirm("Deseja realmente excluir o destinatário selecionado?");
        actionExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluir.setMethodName("excluirDestinatario");
        actionExcluir.setTarget(model);
        actionExcluir.setText("Excluir");
        actionExcluir.setToolTipText("Exclui o destinatário selecionado");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.destinatarioSelecionado}"), actionExcluir, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Destinatários da Agência");

        sLabel1.setText("Agência:");
        sLabel1.setName("sLabel1"); // NOI18N

        sTextField1.setEnabled(false);
        sTextField1.setName("sTextField1"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.agencia.nome}"), sTextField1, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        sPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Destinatários"));
        sPanel1.setName("sPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        sTable1.setSortable(true);
        sTable1.setName("sTable1"); // NOI18N

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${model.listaDestinatario}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, sTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nome}"));
        columnBinding.setColumnName("Nome");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${email}"));
        columnBinding.setColumnName("E-mail");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.destinatarioSelecionado}"), sTable1, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(sTable1);

        botaoSalvar.setAction(actionAdicionar);
        botaoSalvar.setName("botaoSalvar"); // NOI18N

        botaoSalvar1.setAction(actionEditar);
        botaoSalvar1.setName("botaoSalvar1"); // NOI18N

        botaoExcluir.setAction(actionExcluir);
        botaoExcluir.setName("botaoExcluir"); // NOI18N

        javax.swing.GroupLayout sPanel1Layout = new javax.swing.GroupLayout(sPanel1);
        sPanel1.setLayout(sPanel1Layout);
        sPanel1Layout.setHorizontalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        sPanel1Layout.setVerticalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON

    // Variables declaration - do not modify//GEN-BEGIN:variables
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionAdicionar;
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionEditar;
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionExcluir;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
