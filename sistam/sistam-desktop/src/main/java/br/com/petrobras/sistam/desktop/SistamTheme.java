package br.com.petrobras.sistam.desktop;

import br.com.petrobras.fcorp.swing.base.SInternalFrame;
import br.com.petrobras.snarf.desktop.laf.SnarfDesktopTheme;
import com.jgoodies.looks.plastic.PlasticTheme;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.Color;
import java.util.Map.Entry;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author x4rc
 */
public class SistamTheme extends PlasticTheme {
    
    public static Border listagemOverBorder;
    public static Border listagemPressedBorder;
    public static Border listagemOutBorder;
    public static Color frameAtivoBackground;
    public static Border frameAtivoBorder;
    public static Color frameInativoBackground;
    public static Border frameInativoBorder;
    
    public static Color lightGray;
    
    static {
        frameAtivoBackground = javax.swing.UIManager.getDefaults().getColor("Button.shadow");
        frameAtivoBorder = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, javax.swing.UIManager.getDefaults().getColor("Button.highlight"))), javax.swing.BorderFactory.createEmptyBorder(2, 4, 2, 10));
        frameInativoBackground = javax.swing.UIManager.getDefaults().getColor("Button.light");
        frameInativoBorder = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, javax.swing.UIManager.getDefaults().getColor("Button.light"))), javax.swing.BorderFactory.createEmptyBorder(2, 4, 2, 10));
    }
    
    public static void aplicarTema() {
         Color corTextoPadrao = Color.BLACK;
            Color corBackgroundPadrao = Color.decode("#ffffff");
            Color corCampoSuperior = Color.decode("#ffffff");
            Color corCampoInferior = Color.decode("#cccccc");
            Color corFonteSistema = Color.decode("#005197");
            Color bordaTextField = Color.decode("#a19961");
            Color bordaTabbed = Color.decode("#000000");
            Color corTextoDesabilitado = Color.decode("#888888");
            Color corTextoHabilitado = Color.decode("#274f80");
            Color corHeaderTableSuperior = Color.decode("#bddafd");
            Color corHeaderTableInferior = Color.decode("#4f9cf6");
            Color corBgMenuSuperior = Color.decode("#8ccff3"); 
            Color corBgMenuInferior = Color.decode("#4193c0");
            lightGray = Color.decode("#ededfc");
        try {
            
            /* TÃƒÂ­tulo */
            SnarfDesktopTheme.setTitledBorderTitleColor(Color.decode("#144d8e"));

            /* PANEL */
            SnarfDesktopTheme.setPanelBackground(corBackgroundPadrao);
            SnarfDesktopTheme.setPanelForeground(corFonteSistema);

            /* MENU */
            SnarfDesktopTheme.setMenuBarForeground(Color.decode("#0a407d"));
            SnarfDesktopTheme.setMenuForeground(Color.decode("#000000"));

            /* MENU BAR */
            SnarfDesktopTheme.setMenuBarBackgroundGradient(corBgMenuSuperior, corBgMenuInferior);
            SnarfDesktopTheme.setMenuBarForeground(Color.decode("#1f77a7"));
            SnarfDesktopTheme.setMenuSelectionBackground(Color.decode("#ffe898"));
            SnarfDesktopTheme.setMenuSelectionForeground(Color.decode("#1f77a7"));
            SnarfDesktopTheme.setMenuItemSelectionBackground(Color.decode("#cefcfe"));
            SnarfDesktopTheme.setMenuItemSelectionForeground(Color.decode("#3c89b2"));
            SnarfDesktopTheme.setMenuItemBackground(Color.decode("#eeeeee"));

            /* Cor de fundo de tela de erro */
            SnarfDesktopTheme.setOptionPaneBackground(corBackgroundPadrao);

            /* PROGRESS BAR*/
            SnarfDesktopTheme.setProgressBarBackground(Color.decode("#d6eefa"));
            SnarfDesktopTheme.setProgressBarForeground(Color.decode("#8bcef3"));

            /* TEXTFIELD */
            SnarfDesktopTheme.setTextFieldBackgroundGradient(corCampoSuperior, corCampoInferior);
            SnarfDesktopTheme.setTextFieldForeground(corTextoPadrao);
            SnarfDesktopTheme.setTextFieldInactiveForeground(corTextoDesabilitado);
            SnarfDesktopTheme.setTextFieldInactiveBackgroundGradient(corCampoSuperior, corCampoInferior);
            SnarfDesktopTheme.setFormattedTextFieldBackground(corCampoSuperior);
            SnarfDesktopTheme.setFormattedTextFieldForeground(corTextoPadrao);

            /* BORDA TEXTFIELD */
            SnarfDesktopTheme.setTextFieldDarkShadow(corTextoPadrao);
            SnarfDesktopTheme.setTextFieldShadow(bordaTextField);
            SnarfDesktopTheme.setTextFieldHighlight(bordaTextField);
            SnarfDesktopTheme.setTextFieldLight(bordaTextField);

            /* SIMILARES TEXTFIELD */
            SnarfDesktopTheme.setComboBoxBackgroundGradient(corCampoSuperior, corCampoInferior);
            SnarfDesktopTheme.setTextAreaBackgroundGradient(corCampoSuperior, corCampoInferior);
            SnarfDesktopTheme.setTextAreaInactiveForeground(corTextoDesabilitado);
            SnarfDesktopTheme.setPasswordFieldBackgroundGradient(corCampoSuperior, corCampoInferior);

            /* BUTTON */
            SnarfDesktopTheme.setButtonBackground(Color.decode("#9bbfe8"));
            SnarfDesktopTheme.setButtonForeground(Color.decode("#022f62"));
            SnarfDesktopTheme.setButtonSelect(Color.decode("#65adfe"));
            SnarfDesktopTheme.setButtonDisabledText(corTextoDesabilitado);

            /* COMBOBOX */
            SnarfDesktopTheme.setComboBoxBackgroundGradient(corCampoSuperior, corCampoInferior);
            SnarfDesktopTheme.setComboBoxSelectionBackground(Color.decode("#fdd898"));
            SnarfDesktopTheme.setComboBoxDisabledForeground(corTextoDesabilitado);
            SnarfDesktopTheme.setComboBoxDisabledBackground(Color.decode("#eeeeee"));

            /* LABEL */
            SnarfDesktopTheme.setLabelForeground(corTextoHabilitado);
            SnarfDesktopTheme.setLabelDisabledForeground(corTextoDesabilitado);

            /* CHECKBOX */
            SnarfDesktopTheme.setCheckBoxForeground(corTextoHabilitado);
            SnarfDesktopTheme.setCheckBoxDisabledText(corTextoDesabilitado);

            /* TABLE */
            SnarfDesktopTheme.setTableGridColor(Color.decode("#113d6f"));
            SnarfDesktopTheme.setTableBackground(Color.decode("#e2ecf9"));
            SnarfDesktopTheme.setTableForeground(Color.BLACK);
            SnarfDesktopTheme.setTableDropLineColor(Color.decode("#ff0000"));
            SnarfDesktopTheme.setTableDropLineShortColor(corTextoPadrao);
            SnarfDesktopTheme.setTableSelectionForeground(Color.BLACK);
            SnarfDesktopTheme.setTableSelectionBackground(Color.decode("#fdd898"));
            SnarfDesktopTheme.setTableGridColor(Color.WHITE);
            SnarfDesktopTheme.setTableFocusCellBackground(Color.decode("#ebdab6"));
            SnarfDesktopTheme.setTableFocusCellForeground(Color.BLACK);
            SnarfDesktopTheme.setTableHeaderBackgroundGradient(corHeaderTableSuperior, corHeaderTableInferior);
            SnarfDesktopTheme.setTableHeaderFocusCellBackground(Color.decode("#0000ff"));
            SnarfDesktopTheme.setTableHeaderForeground(corTextoPadrao);
            
            

            /* TABBEDPANE */
            SnarfDesktopTheme.setTabbedPaneBackground(Color.decode("#666666"));
            SnarfDesktopTheme.setTabbedPaneForeground(Color.decode("#000000"));
            SnarfDesktopTheme.setTabbedPaneDarkShadow(bordaTabbed);
            SnarfDesktopTheme.setTabbedPaneHighlight(bordaTabbed);
            SnarfDesktopTheme.setTabbedPaneLight(bordaTabbed);
            SnarfDesktopTheme.setTabbedPaneShadow(bordaTabbed);
            SnarfDesktopTheme.setTabbedPaneSelectHighlight(Color.decode("#666666"));
            //SnarfDesktopTheme.setTabbedPaneSelected(WHITE);
            SnarfDesktopTheme.setTabbedPaneSelected(Color.decode("#b2d2f7"));
            SnarfDesktopTheme.setTabbedPaneFocus(Color.decode("#ffa800"));

            /* TITLE DOS FRAMES */
            SnarfDesktopTheme.setactiveCaption(Color.decode("#4193c0"));
            SnarfDesktopTheme.setactiveCaptionText(Color.WHITE);
            
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            SInternalFrame.setDefaultIcon(new ImageIcon(SistamTheme.class.getResource("/icons/icon.jpg")));
            
            UIManager.put("InternalFrame.closeIcon", new ImageIcon(SistamTheme.class.getResource("/icons/window-close.png")));
            UIManager.put("InternalFrame.maximizeIcon", new ImageIcon(SistamTheme.class.getResource("/icons/window-max.png")));
            UIManager.put("InternalFrame.minimizeIcon", new ImageIcon(SistamTheme.class.getResource("/icons/window-res.png")));
            UIManager.put("InternalFrame.iconifyIcon", new ImageIcon(SistamTheme.class.getResource("/icons/window-min.png")));
            UIManager.put("FileChooser.newFolderIcon", new ImageIcon(SistamTheme.class.getResource("/icons/folder-new.png")));
            UIManager.put("FileChooser.homeFolderIcon", new ImageIcon(SistamTheme.class.getResource("/icons/folder-home.png")));
            UIManager.put("FileChooser.upFolderIcon", new ImageIcon(SistamTheme.class.getResource("/icons/folder-up.png")));
            
            listagemOutBorder = javax.swing.BorderFactory.createEmptyBorder(4, 3, 3, 3);
            listagemOverBorder = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, javax.swing.UIManager.getDefaults().getColor("Button.highlight")), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, javax.swing.UIManager.getDefaults().getColor("Button.shadow"))), javax.swing.BorderFactory.createEmptyBorder(3, 2, 2, 2));
            listagemPressedBorder = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, javax.swing.UIManager.getDefaults().getColor("Button.shadow")), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, javax.swing.UIManager.getDefaults().getColor("Button.highlight"))), javax.swing.BorderFactory.createEmptyBorder(3, 2, 2, 2));
            frameAtivoBackground = Color.decode("#307bd0");
            frameAtivoBorder = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, javax.swing.UIManager.getDefaults().getColor("Button.highlight"))), javax.swing.BorderFactory.createEmptyBorder(2, 4, 2, 10));
            frameInativoBackground = Color.WHITE.darker();
            frameInativoBorder = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, javax.swing.UIManager.getDefaults().getColor("Button.light"))), javax.swing.BorderFactory.createEmptyBorder(2, 4, 2, 10));
            
                        
            SnarfDesktopTheme.activate(new SistamTheme());
        } 
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private ColorUIResource corTitleJanela = new ColorUIResource(Color.decode("#4193c0"));
    private ColorUIResource branco = new ColorUIResource(Color.WHITE);

    @Override
    public ColorUIResource getWindowTitleBackground() {
        return corTitleJanela;
    }

    @Override
    public ColorUIResource getWindowTitleForeground() {
        return branco;
    }

    @Override
    public ColorUIResource getPrimaryControl() {
        return corTitleJanela;
    }
    
    @SuppressWarnings({"unchecked", "PMD"}) @SuppressFBWarnings
    // CHECKSTYLE:OFF
    public static void main(String[] args) throws Exception {
        aplicarTema();
        
        for (Entry<Object, Object> entry : UIManager.getLookAndFeelDefaults().entrySet()) {
            if (entry.getKey().toString().startsWith("control"))
                System.out.println(entry.getKey().toString() + ": " + entry.getValue().toString());
        }
    }
    // CHECKSTYLE:ON
}
