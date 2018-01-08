package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.dialog.DialogMessages;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.agenciamento.operacoes.DetalheOperacaoProdutoDialog;
import br.com.petrobras.sistam.desktop.agenciamento.operacoes.DocumentacaoCargaCabotagemDialog;
import br.com.petrobras.sistam.desktop.agenciamento.operacoes.DocumentacaoDescargaCabotagemDialog;
import br.com.petrobras.sistam.desktop.agenciamento.operacoes.DocumentacaoLongCursoDialog;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;

public class OperacoesPainel extends SPanel {
    private OperacoesModel model;
    private JPopupMenu menuDocumentacao;
    
    public OperacoesPainel() {
        model = new OperacoesModel();                
        initComponents();   
        
        tlbProdutos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    editar();
                }
            }
        });
    }
  
    public OperacoesModel getModel() {
        return model;
    }
   
    public void setAgenciamento(Agenciamento agenciamento){
        model.setAgenciamento(agenciamento);
        if (agenciamento != null && StatusEmbarcacao.CANCELADO.equals(agenciamento.getStatusEmbarcacao())){
            colocarEmModoVisualizacao();
        }
    }
    public void adicionar() {
        Operacao nova = model.obterNovaOperacao();
        DetalheOperacaoProdutoDialog dialog = new DetalheOperacaoProdutoDialog(SistamApp.getSistamApp().getMainFrame(), nova);            
        dialog.setVisible(true); 
        
        if (dialog.getModel().isSalvo()){
            model.adicioanarNovaOperacao(dialog.getModel().getOperacao());
        }
    }
    
    public void editar() {
        Operacao operacao = model.obterOperacaoParaEdicaoVisualizacao();
        DetalheOperacaoProdutoDialog dialog = new DetalheOperacaoProdutoDialog(SistamApp.getSistamApp().getMainFrame(), operacao);                    
        dialog.setVisible(true);  
        
        if (dialog.getModel().isSalvo()){
            model.atualizarOperacao(dialog.getModel().getOperacao());
        }
    }
    
    public void abrirDocumentacaoCargaCabotagem(){
        if (model.temOperacaoCargaCabotagem()){
            DocumentacaoCargaCabotagemDialog dialog = new DocumentacaoCargaCabotagemDialog(SistamApp.getSistamApp().getMainFrame(), model.getAgenciamento());
            dialog.setVisible(true);  
        }
        else{
            DialogMessages.info(this, "Cadastre operações do tipo Carga Cabotagem para inserir a documentação!");
        }
            
    }
    
    public void abrirDocumentacaoDescargaCabotagem(){
        if (model.temOperacaoDescargaCabotagem()){
            DocumentacaoDescargaCabotagemDialog dialog = new DocumentacaoDescargaCabotagemDialog(SistamApp.getSistamApp().getMainFrame(), model.getAgenciamento());
            dialog.setVisible(true);
        }
        else{
            DialogMessages.info(this, "Cadastre operações do tipo Descarga Cabotagem para inserir a documentação!");
        }
    }
    
    public void abrirDocumentacaoLongoCurso(){
        Operacao operacaoSelecionada = model.getOperacaoSelecionada();
        if (operacaoSelecionada != null 
                && (TipoOperacao.CARGA_EXPORTACAO.equals(operacaoSelecionada.getTipoOperacao()) || TipoOperacao.DESCARGA_IMPORTACAO.equals(operacaoSelecionada.getTipoOperacao()))){
            
            DocumentacaoLongCursoDialog dialog = new DocumentacaoLongCursoDialog(SistamApp.getSistamApp().getMainFrame(), model.getOperacaoSelecionada());
            dialog.setVisible(true);
        }
        else{
            DialogMessages.info(this, "Selecione uma operação do tipo Descarga Importação ou Carga Exportação para inserir a documentação!");
        }
    }
    
    public void colocarEmModoVisualizacao() {
        botaoAdicionar.setVisible(false);
        botaoEditar.setVisible(false);
        botaoExcluir.setVisible(false);
    }
    
    public void exibirMenuDeDocumentacao(ActionEvent evt){
        menuDocumentacao = new JPopupMenu();
        
        menuDocumentacao.add(actionDocumentacaoCargaCabotagem);
        menuDocumentacao.add(actionDocumentacaoDescargaCabotagem);
        menuDocumentacao.add(actionDocumentacaoLongoCurso);
        
        SButton button = (SButton) evt.getSource();
        menuDocumentacao.setVisible(true);
        menuDocumentacao.show(button.getParent(), button.getX() - 1, button.getY() - menuDocumentacao.getHeight() - 1);
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
        actionDocumentacaoCargaCabotagem = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionDocumentacaoDescargaCabotagem = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionDocumentacaoCarga = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionExcluir = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionDocumentacaoLongoCurso = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        botaoDocumentacao = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoAdicionar = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoEditar = new br.com.petrobras.fcorp.swing.components.SButton();
        sPanel1 = new br.com.petrobras.fcorp.swing.components.SPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tlbProdutos = new br.com.petrobras.fcorp.swing.components.STable();
        botaoExcluir = new br.com.petrobras.fcorp.swing.components.SButton();

        actionAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionar.setMethodName("adicionar");
        actionAdicionar.setTarget(this);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("sistam-labels"); // NOI18N
        actionAdicionar.setText(bundle.getString("novaOperacao")); // NOI18N
        actionAdicionar.setToolTipText("Adiciona uma operação");

        actionEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditar.setMethodName("editar");
        actionEditar.setTarget(this);
        actionEditar.setText("Editar");
        actionEditar.setToolTipText("Edita a operação selecionada");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.operacaoSelecionada}"), actionEditar, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionDocumentacaoCargaCabotagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/document.png"))); // NOI18N
        actionDocumentacaoCargaCabotagem.setMethodName("abrirDocumentacaoCargaCabotagem");
        actionDocumentacaoCargaCabotagem.setTarget(this);
        actionDocumentacaoCargaCabotagem.setText("Operação Carga Cabotagem");
        actionDocumentacaoCargaCabotagem.setToolTipText("Permite consultar e adicionar a documentação de carga");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.habilitarBotaoDocumentacao}"), actionDocumentacaoCargaCabotagem, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionDocumentacaoDescargaCabotagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/document.png"))); // NOI18N
        actionDocumentacaoDescargaCabotagem.setMethodName("abrirDocumentacaoDescargaCabotagem");
        actionDocumentacaoDescargaCabotagem.setTarget(this);
        actionDocumentacaoDescargaCabotagem.setText("Operação Descarga Cabotagem");
        actionDocumentacaoDescargaCabotagem.setToolTipText("Permite consultar e adicionar a documentação de descarga");

        actionDocumentacaoCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/document.png"))); // NOI18N
        actionDocumentacaoCarga.setMethodName("exibirMenuDeDocumentacao");
        actionDocumentacaoCarga.setTarget(this);
        actionDocumentacaoCarga.setText("Documentação da Carga");
        actionDocumentacaoCarga.setToolTipText("");

        actionExcluir.setConfirm("Deseja realmente excluir o produto selecionado?");
        actionExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluir.setMethodName("excluir");
        actionExcluir.setTarget(model);
        actionExcluir.setText("Excluir");
        actionExcluir.setToolTipText("Exclui a operação selecionada");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.operacaoSelecionada}"), actionExcluir, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionDocumentacaoLongoCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/document.png"))); // NOI18N
        actionDocumentacaoLongoCurso.setMethodName("abrirDocumentacaoLongoCurso");
        actionDocumentacaoLongoCurso.setTarget(this);
        actionDocumentacaoLongoCurso.setText("Longo Curso");
        actionDocumentacaoLongoCurso.setToolTipText("Permite consultar e adicionar a documentação de longo curso");

        setPreferredSize(new java.awt.Dimension(659, 497));

        botaoDocumentacao.setAction(actionDocumentacaoCarga);

        botaoAdicionar.setAction(actionAdicionar);

        botaoEditar.setAction(actionEditar);

        sPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Produtos"));

        tlbProdutos.setSortable(true);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${model.listaOperacao}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tlbProdutos);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tipoOperacao}"));
        columnBinding.setColumnName("Tipo Operação");
        columnBinding.setColumnClass(br.com.petrobras.sistam.common.enums.TipoOperacao.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${produto}"));
        columnBinding.setColumnName("Produto");
        columnBinding.setColumnClass(br.com.petrobras.sistam.common.entity.Produto.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${quantidadeFormatada}"));
        columnBinding.setColumnName("Qt. Estimada");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${unidadeMedida}"));
        columnBinding.setColumnName("Unidade de Medida");
        columnBinding.setColumnClass(br.com.petrobras.sistam.common.enums.UnidadeMedida.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataHoraInicioFormatada}"));
        columnBinding.setColumnName("Data Início");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataHoraFimFormatada}"));
        columnBinding.setColumnName("Data Fim");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${anexoUnico}"));
        columnBinding.setColumnName("Anexo Único");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.operacaoSelecionada}"), tlbProdutos, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(tlbProdutos);

        javax.swing.GroupLayout sPanel1Layout = new javax.swing.GroupLayout(sPanel1);
        sPanel1.setLayout(sPanel1Layout);
        sPanel1Layout.setHorizontalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                .addContainerGap())
        );
        sPanel1Layout.setVerticalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );

        botaoExcluir.setAction(actionExcluir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoDocumentacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoDocumentacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionAdicionar;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionDocumentacaoCarga;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionDocumentacaoCargaCabotagem;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionDocumentacaoDescargaCabotagem;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionDocumentacaoLongoCurso;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionEditar;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionExcluir;
    private br.com.petrobras.fcorp.swing.components.SButton botaoAdicionar;
    private br.com.petrobras.fcorp.swing.components.SButton botaoDocumentacao;
    private br.com.petrobras.fcorp.swing.components.SButton botaoEditar;
    private br.com.petrobras.fcorp.swing.components.SButton botaoExcluir;
    private javax.swing.JScrollPane jScrollPane1;
    private br.com.petrobras.fcorp.swing.components.SPanel sPanel1;
    private br.com.petrobras.fcorp.swing.components.STable tlbProdutos;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
