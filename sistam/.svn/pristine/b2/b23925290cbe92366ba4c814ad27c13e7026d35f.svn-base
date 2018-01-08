package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.util.ExtensionFileFilter;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Anexo;
import br.com.petrobras.sistam.common.entity.Arquivo;
import br.com.petrobras.sistam.common.enums.PastaAnexo;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.desktop.Icones;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.components.STreeNode;
import br.com.petrobras.sistam.desktop.util.DesktopUtil;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class AnexosPainel extends SPanel{
    private AnexosPainelModel model;
    private JFileChooser fileChooser;
    private TimeZone zone;
    
    public AnexosPainel() {
        this.model = new AnexosPainelModel();
        initComponents();
         
    }

    public AnexosPainelModel getModel() {
        return model;
    }
    
    public void setAgenciamento(Agenciamento agenciamento){
        model.setAgenciamento(agenciamento);
        zone = TimeZone.getTimeZone(model.getAgenciamento().getAgencia().getTimezone());
        montarArvore();
    }
    

    public void colocarEmModoVisualizacao() {
        botaoExcluir.setVisible(false);
        botaoAnexar.setVisible(false);
        DesktopUtil.habilitarComponentes(false, painelNovoAnexo);
    }
    
    public void anexar(){
        if (fileChooser == null){
            fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new ExtensionFileFilter("pdf", "txt", "xls", "doc", "odt", "ods"));
            fileChooser.setMultiSelectionEnabled(true);
        }
        int valorDeRetornar = fileChooser.showSaveDialog(SistamApp.getSistamApp().getMainFrame());
        
        if (valorDeRetornar == JFileChooser.APPROVE_OPTION){
            model.anexarArquivo(fileChooser.getSelectedFiles());
            montarArvore();
        }
    }
    
    public void visualizar() {
        Anexo anexo = model.getAnexoSelecionado();
        Arquivo arquivo = model.obterArquivoDoAnexoSelecionado();
        DesktopUtil.visualizarArquivo(arquivo.getConteudo(), anexo.getNome());
    }
    
    public void excluir(){
        model.exlcuir();
        montarArvore();
    }
    
    private void montarArvore(){
        Map<PastaAnexo, DefaultMutableTreeNode> nodeMap = new HashMap<PastaAnexo, DefaultMutableTreeNode>();
        List<Anexo> listaAnexos = new ArrayList<Anexo>(model.getAgenciamento().getAnexos());
        
        treeListingPanel1.getTreePane().setModel(null);
        
        //Cria a árvore
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("ROOT");
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        
        for (final Anexo anexo : listaAnexos){
            //Cria o node para as pasta
            DefaultMutableTreeNode nodePasta = nodeMap.get(anexo.getPasta());
            if (nodePasta == null) {
                nodePasta = criarNodeParaPasta(anexo.getPasta());
                root.add(nodePasta);
                nodeMap.put(anexo.getPasta(), nodePasta);
            }
            
            //cria o node para o anexo
            DefaultMutableTreeNode nodeAnexo = criarNodeParaAnexo(anexo);
            nodePasta.add(nodeAnexo);
        }
        
        //confirgura o model da árvore e a altura das linhas
        JTree tree = treeListingPanel1.getTreePane();
        tree.setModel(treeModel);
        tree.setRowHeight(24);
        
        //Listener que verifica se um anexo foi selecionado na árvore e carrega suas propriedades.
        tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                model.setAnexoSelecionado(obterAnexoSelecionado());
                carregarPropriedades();
            }
        });
        
        //Seleciona a pasta onde o anexos está após fazer inclusão ou exclusão de anexos
        DefaultMutableTreeNode node = treeListingPanel1.findNode(model.getPastaSelecionadaNaArvore());
        if (node != null) {
            TreePath path = new TreePath(node.getPath());
            tree.expandPath(path);
            tree.setSelectionPath(path);
            tree.scrollPathToVisible(path);
        }

    }
    
    private DefaultMutableTreeNode criarNodeParaPasta(final PastaAnexo pasta) {
        DefaultMutableTreeNode nodePai = new STreeNode(pasta) {
            @Override
            public Icon getExpandedIcon() {
                return Icones.iconeFolderAberto;
            }

            @Override
            public Icon getCollapsedIcon() {
                return Icones.iconeFolderFechado;
            }
        };
        return nodePai;
    }
    
    private DefaultMutableTreeNode criarNodeParaAnexo(final Anexo anexo) {
        DefaultMutableTreeNode nodeFilho = new STreeNode(anexo) {
            @Override
            public Icon getExpandedIcon() {
                return Icones.obterIconeParaAnexo(anexo);
            }

            @Override
            public Icon getCollapsedIcon() {
                return Icones.obterIconeParaAnexo(anexo);
            }
        };
        return nodeFilho;
    }
    
    private void carregarPropriedades(){
        
        painelDePropriedades.limparPropriedades();
        
        Anexo  anexoSelecionado = model.getAnexoSelecionado();
        if (anexoSelecionado != null){
            painelDePropriedades.adicionarPropriedade("Nome do Arquivo", anexoSelecionado.getNome());
            painelDePropriedades.adicionarPropriedade("Pasta", anexoSelecionado.getPasta().getPorExtenso());
            painelDePropriedades.adicionarPropriedade("Extensão", anexoSelecionado.getExtensao());
            painelDePropriedades.adicionarPropriedade("Tamanho", anexoSelecionado.getTamanhoFormatado());
            painelDePropriedades.adicionarPropriedade("Data de inclusão", SistamDateUtils.formatDateComplete(anexoSelecionado.getDataDeCriacao(), zone));
            painelDePropriedades.adicionarPropriedade("Responsável pela inclusão", anexoSelecionado.getUsuarioFormatado());
        }
        
    }
    
    private Anexo obterAnexoSelecionado() {
        TreePath[] selection = treeListingPanel1.getTreePane().getSelectionModel().getSelectionPaths();
        if (selection != null) {
            for (TreePath path : selection) {
                DefaultMutableTreeNode node = ((DefaultMutableTreeNode) path.getLastPathComponent());
                if (node.getUserObject() instanceof Anexo) {
                    return (Anexo) node.getUserObject();
                }
            }
        }
        return null;
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

        actionVisualizar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionAnexar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionExcluir = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        jSplitPane1 = new javax.swing.JSplitPane();
        painelEsquerdo = new br.com.petrobras.fcorp.swing.components.SPanel();
        treeListingPanel1 = new br.com.petrobras.sistam.desktop.components.TreeListingPanel();
        painelBotoes = new br.com.petrobras.fcorp.swing.components.SPanel();
        botaoVisualizar = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoExcluir = new br.com.petrobras.fcorp.swing.components.SButton();
        painelDireito = new br.com.petrobras.fcorp.swing.components.SPanel();
        painelDePropriedades = new br.com.petrobras.sistam.desktop.components.PropriedadesPainel();
        painelNovoAnexo = new br.com.petrobras.fcorp.swing.components.SPanel();
        sLabel1 = new br.com.petrobras.fcorp.swing.components.SLabel();
        sComboBox1 = new br.com.petrobras.fcorp.swing.components.SComboBox();
        botaoAnexar = new br.com.petrobras.fcorp.swing.components.SButton();
        sHelpLabel1 = new br.com.petrobras.fcorp.swing.components.SHelpLabel();
        sLabel2 = new br.com.petrobras.fcorp.swing.components.SLabel();
        sLabel3 = new br.com.petrobras.fcorp.swing.components.SLabel();

        actionVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.png"))); // NOI18N
        actionVisualizar.setMethodName("visualizar");
        actionVisualizar.setTarget(this);
        actionVisualizar.setText("Visualizar");
        actionVisualizar.setToolTipText("Visualiza o anexo selecionado");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.anexoSelecionado}"), actionVisualizar, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionAnexar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/anexar.gif"))); // NOI18N
        actionAnexar.setMethodName("anexar");
        actionAnexar.setTarget(this);
        actionAnexar.setText("Anexar");
        actionAnexar.setToolTipText("Anexa um novo arquivo");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.pastaSelecionada}"), actionAnexar, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionExcluir.setConfirm("Deseja realmente excluir o anexo selecionado?");
        actionExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluir.setMethodName("excluir");
        actionExcluir.setTarget(this);
        actionExcluir.setText("Excluir");
        actionExcluir.setToolTipText("Exclui o anexo selecionado");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.anexoSelecionado}"), actionExcluir, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setAutoscrolls(true);

        painelBotoes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelBotoes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        botaoVisualizar.setAction(actionVisualizar);
        painelBotoes.add(botaoVisualizar);

        botaoExcluir.setAction(actionExcluir);
        painelBotoes.add(botaoExcluir);

        javax.swing.GroupLayout painelEsquerdoLayout = new javax.swing.GroupLayout(painelEsquerdo);
        painelEsquerdo.setLayout(painelEsquerdoLayout);
        painelEsquerdoLayout.setHorizontalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
            .addComponent(treeListingPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        painelEsquerdoLayout.setVerticalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addComponent(treeListingPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane1.setLeftComponent(painelEsquerdo);

        painelNovoAnexo.setBorder(javax.swing.BorderFactory.createTitledBorder("Novo Anexo"));

        sLabel1.setText("Seleciona a Pasta:");

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${model.listaDePastas}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, sComboBox1);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.pastaSelecionada}"), sComboBox1, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        botaoAnexar.setAction(actionAnexar);

        sHelpLabel1.setText("Para anexar, selecione uma pasta!");

        javax.swing.GroupLayout painelNovoAnexoLayout = new javax.swing.GroupLayout(painelNovoAnexo);
        painelNovoAnexo.setLayout(painelNovoAnexoLayout);
        painelNovoAnexoLayout.setHorizontalGroup(
            painelNovoAnexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelNovoAnexoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelNovoAnexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelNovoAnexoLayout.createSequentialGroup()
                        .addComponent(sLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelNovoAnexoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(sHelpLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoAnexar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        painelNovoAnexoLayout.setVerticalGroup(
            painelNovoAnexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelNovoAnexoLayout.createSequentialGroup()
                .addGroup(painelNovoAnexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelNovoAnexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAnexar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sHelpLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        sLabel2.setText("Espaço disponível para anexos:");

        sLabel3.setRequiredField(true);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.agenciamento.espacoDiponvivelParaArquivosFormatado}"), sLabel3, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout painelDireitoLayout = new javax.swing.GroupLayout(painelDireito);
        painelDireito.setLayout(painelDireitoLayout);
        painelDireitoLayout.setHorizontalGroup(
            painelDireitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelDePropriedades, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(painelDireitoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDireitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelNovoAnexo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelDireitoLayout.createSequentialGroup()
                        .addComponent(sLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painelDireitoLayout.setVerticalGroup(
            painelDireitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDireitoLayout.createSequentialGroup()
                .addComponent(painelDePropriedades, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelDireitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(painelNovoAnexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(painelDireito);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionAnexar;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionExcluir;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionVisualizar;
    private br.com.petrobras.fcorp.swing.components.SButton botaoAnexar;
    private br.com.petrobras.fcorp.swing.components.SButton botaoExcluir;
    private br.com.petrobras.fcorp.swing.components.SButton botaoVisualizar;
    private javax.swing.JSplitPane jSplitPane1;
    private br.com.petrobras.fcorp.swing.components.SPanel painelBotoes;
    private br.com.petrobras.sistam.desktop.components.PropriedadesPainel painelDePropriedades;
    private br.com.petrobras.fcorp.swing.components.SPanel painelDireito;
    private br.com.petrobras.fcorp.swing.components.SPanel painelEsquerdo;
    private br.com.petrobras.fcorp.swing.components.SPanel painelNovoAnexo;
    private br.com.petrobras.fcorp.swing.components.SComboBox sComboBox1;
    private br.com.petrobras.fcorp.swing.components.SHelpLabel sHelpLabel1;
    private br.com.petrobras.fcorp.swing.components.SLabel sLabel1;
    private br.com.petrobras.fcorp.swing.components.SLabel sLabel2;
    private br.com.petrobras.fcorp.swing.components.SLabel sLabel3;
    private br.com.petrobras.sistam.desktop.components.TreeListingPanel treeListingPanel1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables


}
