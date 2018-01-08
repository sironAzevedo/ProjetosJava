package br.com.petrobras.sistam.desktop.caixaentrada;

import br.com.petrobras.fcorp.swing.components.STree;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.agenciamento.AgenciamentoEmbarcacaoFrame;
import br.com.petrobras.sistam.desktop.components.MenuContexto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class GerenciadorDeEventos {

    public static void aplicar(final STree treePane) {
        final int DOUBLE_CLICK = 2;
        final int ROW_NOT_FOUND = -1;

        treePane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (treePane.getRowForLocation(mouseEvent.getX(), mouseEvent.getY()) != ROW_NOT_FOUND) {
                    TreePath selectedPath = treePane.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();

                    if (!(node.getUserObject() instanceof Agenciamento)) {
                        return;
                    }

                    // Clique do botão esquerdo do mouse (BUTTON1)
                    if (mouseEvent.getButton() == MouseEvent.BUTTON1 && mouseEvent.getClickCount() == DOUBLE_CLICK) {
                        executarAcaoDefault(node);

                    } // Clique do botão direito do mouse (BUTTON3)
//                    else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
//                        MenuContexto menuContexto = criarMenuDeContexto(node);
//                        menuContexto.naPosicao(treePane, selectedPath, mouseEvent.getX(), mouseEvent.getY());
//                        
//                    }
                }
            }
        });
    }

    private static MenuContexto criarMenuDeContexto(DefaultMutableTreeNode node) {
        MenuContexto menuContexto = new MenuContexto(new JPopupMenu());
        if (node.getUserObject() instanceof Agenciamento) {
            Agenciamento agenciamento = (Agenciamento) node.getUserObject();
            menuContexto.criarItemsDeMenu(agenciamento);
        }
        return menuContexto;
    }

    private static void executarAcaoDefault(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Agenciamento) {
            Agenciamento agenciamento = (Agenciamento) node.getUserObject();
            agenciamento = SistamApp.getSistamApp().getService().buscarAgenciamentoPorId(agenciamento.getId());
            SistamApp.getSistamApp().getMainFrame().openInternalFrame(agenciamento, AgenciamentoEmbarcacaoFrame.class, true);            
        }
    }
}
