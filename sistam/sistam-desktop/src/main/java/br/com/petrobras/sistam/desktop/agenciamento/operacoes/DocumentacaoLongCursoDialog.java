package br.com.petrobras.sistam.desktop.agenciamento.operacoes;

import br.com.petrobras.fcorp.swing.base.SDialog;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.STable;
import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Pais;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.Produto;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.components.SistamNumberCellRenderer;
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.JTableBinding.ColumnBinding;
import org.jdesktop.swingbinding.SwingBindings;
public class DocumentacaoLongCursoDialog extends SDialog {
    private DocumentacaoLongoCursoModel model;
    
    public DocumentacaoLongCursoDialog(java.awt.Frame parent, Operacao operacao) {
        super(parent, true);
        this.model = new DocumentacaoLongoCursoModel(operacao);
        initComponents();
        setLocationRelativeTo(parent);
        
        AssyncInvoker
                .create(model, "carregarListaDeDocumentacao")
                .schedule();
    }

    public DocumentacaoLongoCursoModel getModel() {
        return model;
    }
    
    public void adicioanrDocumentacao(){
         DocumentacaoLongoCurso documentacao = model.obterNovaDocumentacao();
         DetalheDocumentacaoLongoCursoDialog dialog = new DetalheDocumentacaoLongoCursoDialog(SistamApp.getSistamApp().getMainFrame(), documentacao);
         dialog.setVisible(true);
         
         if (dialog.getModel().salvou()){
             model.adicionarDocumentacao(dialog.getModel().getDocumentacaoLongoCurso());
         }
    }
    
    public void editarDocumentacao(){
        DocumentacaoLongoCurso documentacao = model.obterDocumetacaoParaEdicao();
        DetalheDocumentacaoLongoCursoDialog dialog = new DetalheDocumentacaoLongoCursoDialog(SistamApp.getSistamApp().getMainFrame(), documentacao);
        dialog.setVisible(true);
        
        if (dialog.getModel().salvou()){
             model.atualizarDocumentacao(dialog.getModel().getDocumentacaoLongoCurso());
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
        GenericAction actionAdicionarDocumentacao = new GenericAction();
        GenericAction actionEditarDocumentacao = new GenericAction();
        GenericAction actionExcluirDocumentacao = new GenericAction();
        SistamNumberCellRenderer rendererQuantidade = new SistamNumberCellRenderer();
        SistamNumberCellRenderer rendererFrete = new SistamNumberCellRenderer();
        painelDocumentacoes = new SPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        STable sTable1 = new STable();
        SButton botaoAdicionarDocumentacao = new SButton();
        SButton botaoEditarDocumentacao = new SButton();
        SButton botaoEditarDocumentacao3 = new SButton();

        actionAdicionarDocumentacao.setIcon(new ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionarDocumentacao.setMethodName("adicioanrDocumentacao");
        actionAdicionarDocumentacao.setTarget(this);
        actionAdicionarDocumentacao.setText("Adicionar Documentação");
        actionAdicionarDocumentacao.setToolTipText("Adiciona uma nova documentação");

        actionEditarDocumentacao.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditarDocumentacao.setMethodName("editarDocumentacao");
        actionEditarDocumentacao.setTarget(this);
        actionEditarDocumentacao.setText("Editar Documentação");
        actionEditarDocumentacao.setToolTipText("Edita a documentação selecionada");

        Binding binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.documentacaoSelecionada}"), actionEditarDocumentacao, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionExcluirDocumentacao.setConfirm("Deseja realmente excluir a documentação selecionada?");
        actionExcluirDocumentacao.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluirDocumentacao.setMethodName("excluirDocumentacao");
        actionExcluirDocumentacao.setTarget(model);
        actionExcluirDocumentacao.setText("Excluir Documentação");
        actionExcluirDocumentacao.setToolTipText("Exclui a documentação selecionada");

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.documentacaoSelecionada}"), actionExcluirDocumentacao, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        rendererQuantidade.setDecimalsPlaces(new Short((short)4));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Documentação de Longo Curso");

        painelDocumentacoes.setBorder(BorderFactory.createTitledBorder("Lista de Documentações"));
        painelDocumentacoes.setName("painelDocumentacoes"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        sTable1.setName("sTable1"); // NOI18N
        ELProperty eLProperty = ELProperty.create("${model.listaDocumentacao}");
        JTableBinding jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, this, eLProperty, sTable1);
        ColumnBinding columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${porto}"));
        columnBinding.setColumnName("Porto");
        columnBinding.setColumnClass(Porto.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${cidade}"));
        columnBinding.setColumnName("Cidade");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${porto.pais}"));
        columnBinding.setColumnName("País");
        columnBinding.setColumnClass(Pais.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${manifestoEletronico}"));
        columnBinding.setColumnName("ME");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.documentacaoSelecionada}"), sTable1, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(sTable1);

        botaoAdicionarDocumentacao.setAction(actionAdicionarDocumentacao);
        botaoAdicionarDocumentacao.setName("botaoAdicionarDocumentacao"); // NOI18N

        botaoEditarDocumentacao.setAction(actionEditarDocumentacao);
        botaoEditarDocumentacao.setName("botaoEditarDocumentacao"); // NOI18N

        botaoEditarDocumentacao3.setAction(actionExcluirDocumentacao);
        botaoEditarDocumentacao3.setName("botaoEditarDocumentacao3"); // NOI18N

        GroupLayout painelDocumentacoesLayout = new GroupLayout(painelDocumentacoes);
        painelDocumentacoes.setLayout(painelDocumentacoesLayout);
        painelDocumentacoesLayout.setHorizontalGroup(
            painelDocumentacoesLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelDocumentacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDocumentacoesLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                    .addGroup(Alignment.TRAILING, painelDocumentacoesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoAdicionarDocumentacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(botaoEditarDocumentacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(botaoEditarDocumentacao3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        painelDocumentacoesLayout.setVerticalGroup(
            painelDocumentacoesLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelDocumentacoesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addGroup(painelDocumentacoesLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(botaoAdicionarDocumentacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEditarDocumentacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEditarDocumentacao3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelDocumentacoes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelDocumentacoes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON

    // Variables declaration - do not modify//GEN-BEGIN:variables
    SPanel painelDocumentacoes;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
