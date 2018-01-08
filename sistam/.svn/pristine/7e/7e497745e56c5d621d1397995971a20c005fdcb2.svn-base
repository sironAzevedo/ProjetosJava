package br.com.petrobras.sistam.desktop.caixaentrada;

import br.com.petrobras.fcorp.common.exception.SystemException;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.STree;
import br.com.petrobras.fcorp.swing.components.activators.UserObjectChangeEvent;
import br.com.petrobras.fcorp.swing.components.activators.UserObjectChangeListener;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.valueobjects.GrupoStatusAgenciamento;
import br.com.petrobras.sistam.desktop.Icones;
import br.com.petrobras.sistam.desktop.components.STreeNode;
import br.com.petrobras.sistam.desktop.components.TitledPanel;
import br.com.petrobras.sistam.desktop.components.TreeListingPanel;
import br.com.petrobras.sistam.desktop.components.TreeNodeFactory;
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import org.springframework.util.ReflectionUtils;


public class ListagemAgenciamentoPanel extends TitledPanel implements UserObjectChangeListener {
    private List<ListagemAgenciamentoPanelListener> listeners;
    private ListagemAgenciamentoSource fonteDeDados;    
    private FiltroCaixaDeEntradadPainel filtroCaixaEntrada;
    
    public ListagemAgenciamentoPanel(ListagemAgenciamentoSource fonteDeDados) {
        this.fonteDeDados = fonteDeDados;
        initComponents();
    }

    public ListagemAgenciamentoSource getFonteDeDados() {
        return fonteDeDados;
    }

    public void setFiltro(FiltroCaixaDeEntradadPainel selecaoAgenciaPainel) {
        this.filtroCaixaEntrada = selecaoAgenciaPainel;
    }
    
    @Override
    public void userObjectChanged(UserObjectChangeEvent evt) {
        if (evt.getOldValue() != null && evt.getNewValue() != null) {
            for (DefaultMutableTreeNode parent : this.painelListagem.findRootChildren()) {
                DefaultMutableTreeNode node = this.painelListagem.findNode(parent, evt.getOldValue());
                if (node != null) {
                    node.setUserObject(evt.getNewValue());
                }
            }
            this.painelListagem.validate();
        }
    }

    public void adicionarListener(ListagemAgenciamentoPanelListener listener) {
        if (null == this.listeners) {
            this.listeners = new ArrayList<ListagemAgenciamentoPanelListener>();
        }
        this.listeners.add(listener);
    }

    public void removerListener(ListagemAgenciamentoPanelListener listener) {
        if (null != this.listeners) {
            this.listeners.remove(listener);
        }
    }

    protected void esconderPainel() {
        for (ListagemAgenciamentoPanelListener listener : listeners) {
            listener.esconderListagem(this);
        }
    }

    public void atualizarCaixa() {        
        atualizarAgenciamentos();
    }
    
    protected void atualizarAgenciamentos() {
        
        Agenciamento agenciamentoSelecionado = getAgenciamentoSelecionado(painelListagem.getTreePane());
        painelListagem.setSelectedElement(agenciamentoSelecionado);
        
        painelListagem.getTreePane().setModel(null);
        
        if (this.filtroCaixaEntrada.getAgenciaSelecionada() != null){
            AssyncInvoker.create(fonteDeDados, "atualizarListagemAgenciamento", this.filtroCaixaEntrada.getAgenciaSelecionada(), this.filtroCaixaEntrada.getPortoSelecionado())
                    .assigningResultTo(painelListagem, "elements")
                    .settingLoadingIconOn(super.headerLabel)
                    .disabling(this.filtroCaixaEntrada)
                    .schedule();
        }
        
    }
    
    private MutableTreeNode criarArvoreParaDocumento(Object objeto) {

        DefaultMutableTreeNode node = new DefaultMutableTreeNode(objeto, true);
        for (Agenciamento agenciamento : ((GrupoStatusAgenciamento) objeto).getAgenciamento()) {

            DefaultMutableTreeNode nodeFilho = new STreeNode(agenciamento) {
                @Override
                public Icon getExpandedIcon() {
                    return Icones.atribuirIconeAgenciamento((Agenciamento) this.getUserObject());
                }

                @Override
                public Icon getCollapsedIcon() {
                    return Icones.atribuirIconeAgenciamento((Agenciamento) this.getUserObject());
                }
            };

            node.add(nodeFilho);

        }
        return node;
    }
    
    private STree getArvore() {
        final String TREE_PANE_FIELD = "treePane";
        try {
            Field field = ReflectionUtils.findField(painelListagem.getClass(), TREE_PANE_FIELD);
            field.setAccessible(true);
            return (STree) field.get(painelListagem);
        } catch (Exception ex) {
            throw new SystemException(ex);
        }
    }
    
    private Agenciamento getAgenciamentoSelecionado(STree arvore) {
        TreePath[] selection = arvore.getSelectionModel().getSelectionPaths();
        if (selection != null) {
            for (TreePath path : selection) {
                DefaultMutableTreeNode node = ((DefaultMutableTreeNode) path.getLastPathComponent());
                if (node.getUserObject() instanceof Agenciamento) {
                    return (Agenciamento) node.getUserObject();
                }
            }
        }
        return null;
    }

    @SuppressWarnings({"unchecked", "PMD"})
    private void initComponents() {
        btnEsconder = new SButton();
        actionEsconder = new GenericAction();
        actionEsconder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/listagem_esconder.png")));
        actionEsconder.setMethodName("esconderPainel");
        actionEsconder.setTarget(this);
        btnEsconder.setAction(actionEsconder);
        addCommandButton(btnEsconder);

        btnAtualizar = new SButton();
        actionAtualizar = new GenericAction();
        actionAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/listagem_refresh.png")));
        actionAtualizar.setMethodName("atualizarAgenciamentos");
        actionAtualizar.setTarget(this);
        btnAtualizar.setAction(actionAtualizar);
        addCommandButton(btnAtualizar);

        painelListagem = new TreeListingPanel();
        painelListagem.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelListagem.setFactory(new TreeNodeFactory() {
            @Override public MutableTreeNode buildNode(Object userObject) {
                return criarArvoreParaDocumento(userObject);
            }
        });
        
        setContent(painelListagem);

        final STree arvore = getArvore();
        GerenciadorDeEventos.aplicar(arvore);
        
        arvore.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            @Override public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                for (ListagemAgenciamentoPanelListener listener : listeners) {
                    listener.selecionarEmbarcacao(getAgenciamentoSelecionado(arvore));
                }
            }
        });
    }

    private SButton btnEsconder;
    private SButton btnAtualizar;
    private GenericAction actionEsconder;
    private GenericAction actionAtualizar;
    private TreeListingPanel painelListagem;
}
