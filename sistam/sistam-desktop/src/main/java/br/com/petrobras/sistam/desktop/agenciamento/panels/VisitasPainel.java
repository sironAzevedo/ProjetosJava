package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Visita;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.agenciamento.visita.DetalheVisitaDialog;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VisitasPainel extends SPanel implements PropertyChangeListener {

    private VisitasPainelModel model;

    public VisitasPainel() {
        model = new VisitasPainelModel();
        initComponents();
    }

    public VisitasPainelModel getModel() {
        return model;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        model.setAgenciamento(agenciamento);
    }

    public void adicionar() {
        Visita nova = model.obterNovaVisita();
        DetalheVisitaDialog dialog = new DetalheVisitaDialog(SistamApp.getSistamApp().getMainFrame(), nova);
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);
    }

    public void editar() {
        Visita visitaSelecionada = model.obterVisitaParaEdicaoVisualizacao();
        DetalheVisitaDialog dialog = new DetalheVisitaDialog(SistamApp.getSistamApp().getMainFrame(), visitaSelecionada);
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);
    }

    public void colocarEmModoVisualizacao() {
        painelBotoes.setVisible(false);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(DetalheVisitaDialog.VISITA_INSERIDO)) {
            Visita visita = ((DetalheVisitaDialog) evt.getSource()).getModel().getVisita();
            model.adicionarVisita(visita);
        } else if (evt.getPropertyName().equals(DetalheVisitaDialog.VISITA_ALTERADO)) {
            Visita visita = ((DetalheVisitaDialog) evt.getSource()).getModel().getVisita();
            model.atualizarVisita(visita);
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        actionAdicionar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionExcluir = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionEditar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        sistamDateCellRenderer1 = new br.com.petrobras.sistam.desktop.components.SistamDateCellRenderer();
        painelListaTaxas = new br.com.petrobras.fcorp.swing.components.SPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaTaxas = new br.com.petrobras.fcorp.swing.components.STable();
        painelBotoes = new br.com.petrobras.fcorp.swing.components.SPanel();
        botaoAdicionar = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoEditar = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoExcluir1 = new br.com.petrobras.fcorp.swing.components.SButton();

        actionAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionar.setMethodName("adicionar");
        actionAdicionar.setTarget(this);
        actionAdicionar.setText("Adicionar");
        actionAdicionar.setToolTipText("Adiciona uma nova visita");

        actionExcluir.setConfirm("Confirma a exclusão da visita?");
        actionExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluir.setMethodName("excluirVisita");
        actionExcluir.setTarget(model);
        actionExcluir.setText("Excluir");
        actionExcluir.setToolTipText("Exclui a visita selecionada");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.visitaSelecionada}"), actionExcluir, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditar.setMethodName("editar");
        actionEditar.setTarget(this);
        actionEditar.setText("Editar");
        actionEditar.setToolTipText("Edita a visita selecionada");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.visitaSelecionada}"), actionEditar, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        sistamDateCellRenderer1.setPattern("dd/MM/yyyy HH:mm");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.timeZone}"), sistamDateCellRenderer1, org.jdesktop.beansbinding.BeanProperty.create("timeZone"));
        bindingGroup.addBinding(binding);

        painelListaTaxas.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Visitas do Agenciamento"));

        tabelaTaxas.setSortable(true);
        tabelaTaxas.setColumnSelectionAllowed(true);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${model.listaDeVisitas}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tabelaTaxas);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${chaveNomeAgente}"));
        columnBinding.setColumnName("Agente");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataInicio}"));
        columnBinding.setColumnName("Data Início");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataTermino}"));
        columnBinding.setColumnName("Data Término");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${observacao}"));
        columnBinding.setColumnName("Observação");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nomeUsuarioAlteracao}"));
        columnBinding.setColumnName("Alterado por");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataAlteracao}"));
        columnBinding.setColumnName("Dt Alteração");
        columnBinding.setColumnClass(java.util.Date.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.visitaSelecionada}"), tabelaTaxas, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(tabelaTaxas);
        tabelaTaxas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelaTaxas.getColumnModel().getColumn(1).setCellRenderer(sistamDateCellRenderer1);
        tabelaTaxas.getColumnModel().getColumn(2).setCellRenderer(sistamDateCellRenderer1);

        javax.swing.GroupLayout painelListaTaxasLayout = new javax.swing.GroupLayout(painelListaTaxas);
        painelListaTaxas.setLayout(painelListaTaxasLayout);
        painelListaTaxasLayout.setHorizontalGroup(
            painelListaTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 573, Short.MAX_VALUE)
            .addGroup(painelListaTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
        );
        painelListaTaxasLayout.setVerticalGroup(
            painelListaTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
            .addGroup(painelListaTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
        );

        painelBotoes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelBotoes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        botaoAdicionar.setAction(actionAdicionar);
        painelBotoes.add(botaoAdicionar);

        botaoEditar.setAction(actionEditar);
        painelBotoes.add(botaoEditar);

        botaoExcluir1.setAction(actionExcluir);
        painelBotoes.add(botaoExcluir1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelListaTaxas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelListaTaxas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionAdicionar;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionEditar;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionExcluir;
    private br.com.petrobras.fcorp.swing.components.SButton botaoAdicionar;
    private br.com.petrobras.fcorp.swing.components.SButton botaoEditar;
    private br.com.petrobras.fcorp.swing.components.SButton botaoExcluir1;
    private javax.swing.JScrollPane jScrollPane1;
    private br.com.petrobras.fcorp.swing.components.SPanel painelBotoes;
    private br.com.petrobras.fcorp.swing.components.SPanel painelListaTaxas;
    private br.com.petrobras.sistam.desktop.components.SistamDateCellRenderer sistamDateCellRenderer1;
    private br.com.petrobras.fcorp.swing.components.STable tabelaTaxas;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
