package br.com.petrobras.sistam.desktop.util;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SCheckBox;
import br.com.petrobras.fcorp.swing.components.SComboBox;
import br.com.petrobras.fcorp.swing.components.SDateChooser;
import br.com.petrobras.fcorp.swing.components.SFormattedTextField;
import br.com.petrobras.fcorp.swing.components.SLabel;
import br.com.petrobras.fcorp.swing.components.SNumericTextField;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.SRadioPanel;
import br.com.petrobras.fcorp.swing.components.STextArea;
import br.com.petrobras.fcorp.swing.components.STextField;
import br.com.petrobras.fcorp.swing.components.STimeChooser;
import br.com.petrobras.fcorp.swing.components.dialog.DialogMessages;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.desktop.components.SistamDateChooser;
import br.com.petrobras.sistam.desktop.components.SistamTimeChooser;
import br.com.petrobras.sistam.desktop.components.lookups.porto.PortoLookup;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.text.MaskFormatter;

/**
 * Classe DesktopUtil
 */
public class DesktopUtil {

    private static final List<Class> componentesHabilitaveis = new ArrayList<Class>(Arrays.asList(new Class[]{
        SNumericTextField.class,
        STextArea.class,
        STextField.class,
        SComboBox.class,
        SRadioPanel.class,
        SDateChooser.class,
        STimeChooser.class,
        PortoLookup.class,
        SistamDateChooser.class,
        SistamTimeChooser.class,
        SFormattedTextField.class,
        SButton.class,
        SCheckBox.class
    }));
    private static final List<Class> componentesVisiveis = new ArrayList<Class>(Arrays.asList(new Class[]{
        SNumericTextField.class,
        STextArea.class,
        STextField.class,
        SComboBox.class,
        SRadioPanel.class,
        SDateChooser.class,
        STimeChooser.class,
        PortoLookup.class,
        SistamDateChooser.class,
        SistamTimeChooser.class,
        SFormattedTextField.class,
        SButton.class,
        SCheckBox.class,
        SLabel.class
    }));

    public static void habilitarComponentes(boolean habilitar, JComponent component, Component... componentesADesconsiderar) {
        List<Component> listaADesconsiderar = new ArrayList<Component>(Arrays.asList(componentesADesconsiderar));

        for (Component c : component.getComponents()) {
            if (!listaADesconsiderar.contains(c)) {

                if (c instanceof SPanel || c instanceof JScrollPane || c instanceof JViewport) {
                    habilitarComponentes(habilitar, (JComponent) c, componentesADesconsiderar);
                }

                if (componentesHabilitaveis.contains(c.getClass())) {
                    c.setEnabled(habilitar);
                }
            }
        }
    }

    public static void enableDisableAllInContainer(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableDisableAllInContainer((Container) component, enable);
            }
        }
    }

    public static void visualizarArquivo(byte[] bytes, String nomeArquivo) {
        try {
            String tempDir = System.getProperty("java.io.tmpdir");
            File f = new File(tempDir + "/" + nomeArquivo);

            f.deleteOnExit();
            if (null != bytes) {
                FileOutputStream fout = new FileOutputStream(f);
                fout.write(bytes);
                fout.close();
                DesktopUtil.openURL(f.toURI().toURL());
            } else {
                throw new IOException();
            }
        } catch (IOException ex) {
            throw new BusinessException(ConstantesI18N.ANEXO_ERRO_AO_LER_ARQUIVO);
        }
    }
    
    public static byte[] transformarBytes(File file) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read;
        InputStream inputStream = null;
        if (file.length() > 0) {
            byte[] buf = new byte[(int) file.length()];
            try {
                inputStream = new FileInputStream(file);
                while ((read = inputStream.read(buf, 0, buf.length)) != -1) {
                    buffer.write(buf, 0, read);
                }
                buffer.flush();
            } catch (FileNotFoundException ex) {
                throw new BusinessException(ConstantesI18N.ANEXO_ERRO_AO_LER_ARQUIVO + ex.getMessage());
            } catch (IOException ex) {
                throw new BusinessException(ConstantesI18N.ANEXO_ERRO_AO_LER_ARQUIVO + ex.getMessage());
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        throw new BusinessException(ConstantesI18N.ANEXO_ERRO_AO_LER_ARQUIVO + ex.getMessage());
                    }
                }
            }
            return buffer.toByteArray();
        } else {
            return new byte[0];
        }
    }

    public static void openURL(URL url) {
        String osName = System.getProperty("os.name");
        try {
            if (osName.startsWith("Mac OS")) {
                Class fileMgr = Class.forName("com.apple.eio.FileManager");
                Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[]{String.class});
                openURL.invoke(null, new Object[]{url.toString()});
            } else if (osName.startsWith("Windows")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url.toString());
            } else { //assume Unix or Linux
                String[] browsers = {"firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"};
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null; count++) {
                    if (Runtime.getRuntime().exec(new String[]{"which", browsers[count]}).waitFor() == 0) {
                        browser = browsers[count];
                    }
                }
                if (browser == null) {
                    throw new BusinessException(ConstantesI18N.ANEXO_ERRO_AO_LER_ARQUIVO);
                } else {
                    Runtime.getRuntime().exec(new String[]{browser, url.toString()});
                }
            }
        } catch (Exception e) {
            throw new BusinessException(ConstantesI18N.ANEXO_ERRO_AO_LER_ARQUIVO);
        }
    }

    public static void adicionarMascaraLatitude(SFormattedTextField textFieldLatitude) {

        try {
            MaskFormatter maskLatitude = new MaskFormatter("##º ##'' ##\" U");
            maskLatitude.setValidCharacters("0123456789NS");
            textFieldLatitude.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(maskLatitude));
            textFieldLatitude.addFocusListener(obterLatitudeLongitudeFocusListener());

        } catch (ParseException pe) {
        }
    }

    public static void adicionarMascaraLongititude(SFormattedTextField textFieldLongitude) {

        try {
            MaskFormatter maskLongitude = new MaskFormatter("###º ##'' ##\" U");
            maskLongitude.setValidCharacters("0123456789WE");
            textFieldLongitude.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(maskLongitude));
            textFieldLongitude.addFocusListener(obterLatitudeLongitudeFocusListener());
        } catch (ParseException pe) {
        }
    }

    /**
     * Configura um SFormattedTextField com a máscara de cnpj e faz alguns
     * ajustes adicionais para seu correto funcionamento. Recebe também uma
     * action que será desabilitada quando o componente estiver com foco.
     *
     * @param componenteCPNJ
     * @param action
     */
    public static void configurarComponenteCNPJ(SFormattedTextField componenteCPNJ, final GenericAction action) {
        componenteCPNJ.setTemplate(SFormattedTextField.Template.CNPJ);

        componenteCPNJ.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (action != null) {
                    action.setEnabled(false);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (action != null) {
                    action.setEnabled(true);
                }
            }
        });

        Action actionErroCnpj = new GenericAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogMessages.error(null, "Cnpj inválido.");
            }
        };
        componenteCPNJ.getActionMap().put("postTip", actionErroCnpj);
    }
    
    /**
     * Configura um SFormattedTextField com a máscara de cnpj e faz alguns
     * ajustes adicionais para seu correto funcionamento. Recebe também uma
     * action que será desabilitada quando o componente estiver com foco.
     *
     * @param componenteCPF
     * @param action
     */
    public static void configurarComponenteCPF(SFormattedTextField componenteCPF, final GenericAction action) {
        componenteCPF.setTemplate(SFormattedTextField.Template.CPF);

        componenteCPF.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (action != null) {
                    action.setEnabled(false);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (action != null) {
                    action.setEnabled(true);
                }
            }
        });

        Action actionErroCnpj = new GenericAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogMessages.error(null, "Cpf inválido.");
            }
        };
        componenteCPF.getActionMap().put("postTip", actionErroCnpj);
    }

    public static void adicionarMascaraInscricaoEstadual(SFormattedTextField textFieldIncricao) {
        try {
            MaskFormatter mask = new MaskFormatter("###.###.###");
            mask.setValidCharacters("0123456789");
            textFieldIncricao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));

            textFieldIncricao.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    SFormattedTextField ftf = (SFormattedTextField) e.getSource();

                    if (ftf.getText().replace(".", "").trim().isEmpty()) {
                        ftf.setValue(null);
                    }
                }
            });
        } catch (ParseException pe) {
        }
    }

    private static FocusListener obterLatitudeLongitudeFocusListener() {
        FocusListener focusListener = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                SFormattedTextField ftf = (SFormattedTextField) e.getSource();

                if (ftf.getText().replace("º", "").replace("'", "").replace("\"", "").trim().isEmpty()) {
                    ftf.setValue(null);

                }
            }
        };
        return focusListener;
    }

    public static void visualizarComponentes(boolean visualizar, JComponent component, Component... componentesADesconsiderar) {
        List<Component> listaADesconsiderar = new ArrayList<Component>(Arrays.asList(componentesADesconsiderar));

        for (Component c : component.getComponents()) {
            if (!listaADesconsiderar.contains(c)) {
                if (c instanceof SPanel || c instanceof JScrollPane || c instanceof JViewport) {
                    visualizarComponentes(visualizar, (JComponent) c, componentesADesconsiderar);
                }

                if (componentesVisiveis.contains(c.getClass())) {
                    c.setVisible(visualizar);
                }
            }
        }
    }
    
    public static URL getUrlDaLogoPetrobras(){
        return DesktopUtil.class.getResource("/icons/petrobras_rel.jpg");
    }
    
    public static URL getUrlDaLogoNP1(){
        return DesktopUtil.class.getResource("/icons/NP-1.png");
    }
}
