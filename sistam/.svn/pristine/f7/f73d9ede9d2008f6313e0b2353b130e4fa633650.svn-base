package br.com.petrobras.sistam.desktop.components;

import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.STree;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author x4rc
 */
public class TreeListingPanel extends SPanel {
    private TreeNodeFactory factory;
    private Object selectedElement;

    public TreeListingPanel() {
        initComponents();
    }

    public STree getTreePane() {
        return treePane;
    }
    
    public void setFactory(TreeNodeFactory factory) {
        this.factory = factory;
    }

    public void setElements(List<? extends Object> elements) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("ROOT");
        DefaultTreeModel model = new DefaultTreeModel(root);

        for(Object element : elements) {
            root.add(this.factory.buildNode(element));
        }

        treePane.setModel(model);
        
        if (selectedElement != null) {
            highlightTheSelectedElement();
        }
    }
    
    public void setSelectedElement(Object selectedElement) {
        this.selectedElement = selectedElement;
    }
    
    private void highlightTheSelectedElement() {
        TreePath path = null;
        DefaultMutableTreeNode node = findNode(selectedElement);
        if (node != null) {
            path = new TreePath(node.getPath());
            treePane.setSelectionPath(path);
            treePane.scrollPathToVisible(path);
        }
    }
    
    public void expandAll() {
        Object root = treePane.getModel().getRoot();
        if (root != null) {
            for (int i= 0; i < treePane.getModel().getChildCount(root); i++) {
                Object child = treePane.getModel().getChild(root, i);
                expandNode(child, new Object[]{root});
            }
        }
    }

    public void expandNode(Object node, Object[] ancestors) {
        if (node instanceof LazyLoadingTreeNode && !((LazyLoadingTreeNode)node).isLoaded()) {
            return;
        }
        
        List<Object> path = new ArrayList<Object>(Arrays.asList(ancestors));
        path.add(node);

        treePane.expandPath(new TreePath(path.toArray()));

        for (int i= 0; i < treePane.getModel().getChildCount(node); i++) {
            Object child = treePane.getModel().getChild(node, i);
            expandNode(child, path.toArray());
        }
    }
    
    public DefaultMutableTreeNode findNode(Object element) {
        return this.findNode(treePane.getModel().getRoot(), element);
    }

    public DefaultMutableTreeNode findNode(Object parent, Object element) {
        DefaultMutableTreeNode node = null;
        for (int i = 0; i < this.treePane.getModel().getChildCount(parent); i++) {
            node = (DefaultMutableTreeNode) this.treePane.getModel().getChild(parent, i);
            if (node.getUserObject().equals(element)) {
                return node;
            }
            if (!node.isLeaf()) {
                node = findNode(node, element);
                if (node != null) {
                    return node;
                }
            }
        }
        return null;
    }
    
    public DefaultMutableTreeNode[] findRootChildren() {
        return this.findChildren(treePane.getModel().getRoot());
    }
    
    public DefaultMutableTreeNode[] findChildren(Object parent) {
        int childCount = this.treePane.getModel().getChildCount(parent);
        DefaultMutableTreeNode[] nodes = new DefaultMutableTreeNode[childCount];
        for (int i = 0; i < childCount; i++) {
            nodes[i] = (DefaultMutableTreeNode) this.treePane.getModel().getChild(parent, i);
        }
        return nodes;
    }

    private void initComponents() {
        treeScroller = new javax.swing.JScrollPane();
        treePane = new br.com.petrobras.fcorp.swing.components.STree();

        treePane.setRootVisible(false);
        treePane.setRowHeight(0);
        treePane.setShowsRootHandles(true);
        treePane.setBorder(null);
        treePane.setCellRenderer(new TreeListingPaneRenderer());
        treePane.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                if (event.getPath().getLastPathComponent() instanceof LazyLoadingTreeNode) {
                    boolean updated = ((LazyLoadingTreeNode) event.getPath().getLastPathComponent()).expand();
                    if (updated && treePane.getModel() instanceof DefaultTreeModel) {
                        ((DefaultTreeModel)treePane.getModel()).nodeStructureChanged((TreeNode)event.getPath().getLastPathComponent());
                    }
                }
            }
            @Override public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {}
        });
        
        treeScroller.setBorder(null);
        treeScroller.setViewportView(treePane);
        
        setLayout(new BorderLayout());
        add(treeScroller, BorderLayout.CENTER);
    }

    private JScrollPane treeScroller;
    private STree treePane;

    private class TreeListingPaneRenderer extends DefaultTreeCellRenderer {
        @Override public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            DefaultTreeCellRenderer ren = (DefaultTreeCellRenderer) super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

            if (value instanceof STreeNode) {
                STreeNode node = ((STreeNode) value);
                
                Icon icon;
                if (expanded){
                    icon = node.getExpandedIcon();
                } else{
                    icon = node.getCollapsedIcon();
                    
                }
                if (icon != null){
                     ren.setIcon(icon);
                }
            }
            
            return ren;
        }
    }
}
