package br.com.petrobras.sistam.desktop.components;

import javax.swing.tree.MutableTreeNode;

/**
 *
 * @author x4rc
 */
public abstract class LazyLoadingTreeNode extends STreeNode {
    protected boolean loaded;

    public LazyLoadingTreeNode(Object userObject) {
        super(userObject);
        this.loaded = false;
    }

    public boolean expand() {
        if (this.loaded) {
            return false;
        }
        loaded = true;
        MutableTreeNode[] nodes = null;
        try {
            nodes = loadChildren();
        } finally {
            setAllowsChildren(nodes != null && nodes.length > 0);
            setChildren(nodes);
            return getAllowsChildren();
        }
    }

    protected void setChildren(MutableTreeNode... nodes) {
        this.removeAllChildren();

        if (nodes != null) {
            for (MutableTreeNode mutableTreeNode : nodes) {
                this.add(mutableTreeNode);
            }
        }
    }

    protected boolean areChildrenLoaded() {
        return getChildCount() > 0 && getAllowsChildren();
    }

    @Override
    public boolean isLeaf() {
        return !getAllowsChildren();
    }

    public boolean isLoaded() {
        return loaded;
    }

    protected abstract MutableTreeNode[] loadChildren();
}
