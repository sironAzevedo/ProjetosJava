package br.com.petrobras.sistam.desktop.components;

import br.com.petrobras.fcorp.swing.base.SFrame;
import br.com.petrobras.fcorp.swing.components.STree;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.agenciamento.AgenciamentoPendenciaDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.tree.TreePath;

public class MenuContexto {

    private final JPopupMenu popupMenu;
    private boolean temItensAMostrar;

    public MenuContexto(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
    }

    private SFrame getMainFrame() {
        return SistamApp.getApplication().getMainFrame();
    }

    protected JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    protected JMenuItem criarMenuItem(String titulo) {
        JMenuItem menuItem = new JMenuItem(titulo);
        popupMenu.add(menuItem);
        return menuItem;
    }

    protected JMenuItem criarMenuItem(String titulo, javax.swing.Icon icon) {
        JMenuItem menuItem = this.criarMenuItem(titulo);
        menuItem.setIcon(icon);
        return menuItem;
    }

    private void separator() {
        JSeparator separator = new javax.swing.JSeparator();
        popupMenu.add(separator);
    }

    public void naPosicao(STree treePane, TreePath selectedPath, int x, int y) {
        if (temItensAMostrar) {
            this.getPopupMenu().show(treePane, x, y);
        }
        treePane.setSelectionPath(selectedPath);
    }

    public void criarItemsDeMenu(final Agenciamento agenciamento) {
        if (!agenciamento.getPendenciasNaoResolvidas().isEmpty()) {
            criarMenuItemPendencias(agenciamento);
            temItensAMostrar = true;
        }
    }

    private void criarMenuItemPendencias(final Agenciamento agenciamento) {
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                AgenciamentoPendenciaDialog dialog = new AgenciamentoPendenciaDialog(getMainFrame(), agenciamento);
                dialog.setVisible(true);
            }
        };
        JMenuItem menuItem = this.criarMenuItem("Resolver Pendências");
        menuItem.addMouseListener(mouseListener);
    }
}
