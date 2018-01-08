package br.com.petrobras.sistam.desktop.components;

import br.com.petrobras.fcorp.common.exception.SystemException;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.desktop.SistamApp;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicArrowButton;
import org.slf4j.LoggerFactory;

/**
 * Componente base para campos de lookup
 * A system property "fcorp.swing.userDataDirectory" Ã© usada para definir o diretÃ³rio padrÃ£o
 * onde Ã© gravado o cache de consultas
 * @param <T> Tipo de dado associado ao componente
 * @author x4rb
 */

public abstract class SistamLookupTextfield <T> extends JTextField {

    private static final String HISTORY_FILE_FORMAT = "%s.ser";
    private String sourceGuess;
    private T value;
    private List<T> lastValues;
    private JPopupMenu popUp;
    private JButton historyButton;
    private Color bgDefault;
    private boolean openDialogOnEnter = true;
    private boolean historyEnabled;
    private boolean guessing;
    private int insetLeft = 20;
    private final int insetOther = 1;
    
    /**
     * Construtor default. Cria um lookup textfield com histÃ³rico habilitado.
     */
    public SistamLookupTextfield() {
        this(true);
    }
    
    /**
     * Construtor que permite definir a ativaÃ§Ã£o da funcionalidade de histÃ³rico.
     * @param historyEnabled indica se o historico deve ser ativado ou nao
     */
    public SistamLookupTextfield(boolean historyEnabled) {
        this.guessing = false;
        this.bgDefault = getBackground();
        this.popUp = new JPopupMenu();
        this.popUp.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
                guessing = true;
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
                guessing = false;
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent arg0) {
                guessing = false;
            }
        });

        
        
        setMargin(new Insets(insetOther, insetLeft, insetOther, insetOther));
        setLayout(new BorderLayout());
        setHistoryEnabled(historyEnabled);
        setValue(null);

        this.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER && isEnabled() && openDialogOnEnter) {
                    if ((value != null && sourceGuess.equals(getText().trim().toUpperCase())) || getText().trim().equals("")) {
                        showDialog();
                    } else {
                        applyText();
                    }
                }
                if (ke.getKeyCode() == KeyEvent.VK_ESCAPE && isEnabled()) {
                    setValue(null);
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent me) {
                final int doubleClick = 2;
                if (me.getClickCount() == doubleClick && isEnabled()) {
                    if ((value != null && sourceGuess.equals(getText().trim().toUpperCase())) || getText().trim().equals("")) {
                        showDialog();
                    } else {
                        applyText();
                    }
                }
            }
        });

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                if (value == null || getText().trim().equals("") && isEnabled()) {
                    setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent fe) {
                if (isEnabled()) {
                    applyText();
                }
            }
        });
    }
    
    public T getValue() {
        return value;
    }
        
    /**
     * @param value Valor do campo
     */
    public void setValue(T value) {
        Object old = this.value;
        this.value = value;
        sourceGuess = getText().trim().toUpperCase();
        if (value != null) {
            setText(valueToText(value));
            setBackground(SystemColor.info);
            setForeground(SystemColor.infoText);
        } else {
            setText("");
            setBackground(bgDefault);
            setForeground(SystemColor.textInactiveText);
            sourceGuess = "";
        }
        if (historyEnabled && old != this.value && this.value != null) {
            saveLastValues();
        }
        firePropertyChange("value", old, this.value);
    }

    public boolean isOpenDialogOnEnter() {
        return openDialogOnEnter;
    }
    
    /**
     * Define se o diÃ¡logo deve ser aberto ao pressionar Enter.
     * @param openDialogOnEnter true, para ativar o recurso
     */
    public void setOpenDialogOnEnter(boolean openDialogOnEnter) {
        Object old = this.openDialogOnEnter;
        this.openDialogOnEnter = openDialogOnEnter;
        firePropertyChange("openDialogOnEnter", old, this.openDialogOnEnter);
    }

    public final boolean isHistoryEnabled() {
        return historyEnabled;
    }

    /**
     * Acessor que sinaliza se o historico deve ser ativado.
     * @param historyEnabled indica se o historico deve ser ativado ou nao
     */
    protected final void setHistoryEnabled(boolean historyEnabled) {
        boolean old = this.historyEnabled;
        this.historyEnabled = historyEnabled;
        firePropertyChange("historyEnabled", old, this.historyEnabled);
        
        if (historyEnabled && old != historyEnabled) {
            this.historyButton = new BasicArrowButton(BasicArrowButton.SOUTH);
            this.historyButton.setName("SLookupTextfield.historyButton");
            this.historyButton.setAction(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    showPopUp();
                }
            });
            add(this.historyButton, BorderLayout.EAST);
            loadLastValues();
        }
    }
    
    public final boolean isGuessing() {
        return guessing;
    }

    /**
     * @return DiretÃ³rio que contÃ©m o arquivo de histÃ³rico. Pode ser alterado
     *         definindo a System property fcorp.swing.userDataDirectory
     */
    public static File getLastValuesDirectory() {
        String dir = System.getProperty("fcorp.swing.userDataDirectory");
        if (dir!=null) {
            return new File(dir);
        } else {
            return new File(System.getProperty("user.home"));
        }
    }

    /**
     * Define o nome do arquivo usado para armazenar o histÃ³rico de pesquisa. Por
     * default, Ã© usado o nome da classe concreta. Devido a isto, o mesmo 
     * histÃ³rico Ã© utilizado para todas as instÃ¢ncias desta classe que existirem
     * na aplicaÃ§Ã£o. Subclasses podem sobrescrever este mÃ©todo para definir uma
     * polÃ­tica prÃ³pria de geraÃ§Ã£o do nome e, desta forma, levar em consideraÃ§Ã£o
     * as regras de negÃ³cio do sistema.
     * @return nome do arquivo
     */
    public String getLastValuesFileName() {
        return getClass().getSimpleName();
    }

    /**
     * Carrega o histÃ³rico de valores.
     */
    private void loadLastValues() {
        lastValues = new LinkedList<T>();
        ObjectInputStream ois = null;
        try {
            File f = new File(getLastValuesDirectory(), String.format(HISTORY_FILE_FORMAT, getLastValuesFileName()));
            FileInputStream fin = new FileInputStream(f);
            ois = new ObjectInputStream(fin);
            T persistedValue = null;
            do {
                persistedValue = (T) ois.readObject();
                if (persistedValue != null) {
                    lastValues.add(persistedValue);
                }
            } while (persistedValue != null);
        } catch (Exception e) { // SUPPRESS CHECKSTYLE Illegal Catch - deve ignorar qualquer exceÃ§Ã£o
            LoggerFactory.getLogger(getClass()).error("Can't load hisory", e);
            return;
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e)     {
                LoggerFactory.getLogger(getClass()).error("Can't load hisory", e);
            }
        }
    }

    /**
     * Salva o histÃ³rico de valores.
     */
    private void saveLastValues() {
        final int historySize = 5;
        if (value == null || lastValues.contains(value)) {
            return;
        }
        if (lastValues.size() == historySize) {
            lastValues.remove(historySize-1);
        }
        lastValues.add(0, value);
        ObjectOutputStream ous = null;
        try {
            FileOutputStream fout = new FileOutputStream(new File(getLastValuesDirectory(), String.format(HISTORY_FILE_FORMAT, getLastValuesFileName())), false);
            ous = new ObjectOutputStream(fout);
            for (T aValue : lastValues) {
                ous.writeObject(aValue);
            }
            fout.close();
        } catch (Exception e) { // SUPPRESS CHECKSTYLE Illegal Catch - deve ignorar qualquer exceÃ§Ã£o
            LoggerFactory.getLogger(getClass()).error("Can't save hisory", e);
            return;
        } finally {
            try {
                if (ous != null) {
                    ous.close();
                }
            } catch (IOException e)     {
                LoggerFactory.getLogger(getClass()).error("Can't load hisory", e);
            }
        }
    }
    
    private void removeValue(T value) {
        lastValues.remove(value);
        ObjectOutputStream ous = null;
        try {
            FileOutputStream fout = new FileOutputStream(new File(getLastValuesDirectory(), String.format(HISTORY_FILE_FORMAT, getLastValuesFileName())), false);
            ous = new ObjectOutputStream(fout);
            for (T aValue : lastValues) {
                ous.writeObject(aValue);
            }
            fout.close();
        } catch (Exception e) { // SUPPRESS CHECKSTYLE Illegal Catch - deve ignorar qualquer exceÃ§Ã£o
            LoggerFactory.getLogger(getClass()).error("Can't save hisory", e);
            return;
        } finally {
            try {
                if (ous != null) {
                    ous.close();
                }
            } catch (IOException e)     {
                LoggerFactory.getLogger(getClass()).error("Can't load hisory", e);
            }
        }
    }
    
    /**
     * Realiza transformaÃ§Ã£o do objeto para representaÃ§Ã£o textual.
     * O default Ã© chamar o toString()
     * @param value valor
     * @return representaÃ§Ã£o textual
     */
    protected String valueToText(T value) {
        return value.toString();
    }
    
    /**
     * Exibe o diÃ¡logo popup
     */
    private void showPopUp() {
        popUp.removeAll();
        for (final T aValue : lastValues) {
            if (valueToId(aValue) != null) {
                if (null != getValueIcon(aValue)) {
                    popUp.add(new AbstractAction(valueToText(aValue), getValueIcon(aValue)) {
                        public void actionPerformed(ActionEvent e) {
                            doActionPerformed(aValue);
                        }
                    });
                } else {
                    popUp.add(new AbstractAction(valueToText(aValue)) {
                        public void actionPerformed(ActionEvent e) {
                            doActionPerformed(aValue);
                        }
                    });
                }
            }
        }
        popUp.setVisible(true);
        popUp.show(this, this.getWidth() - popUp.getWidth(), this.getHeight() + 1);
    }
    
    private void doActionPerformed(T aValue){
        T t = buscarPorId(aValue);
        if (t == null) {
            removeValue(aValue);
            AssertUtils.assertExpression(false, ConstantesI18N.SISTAM_LOOKUP_TEXTFIELD_HISTORICO_EXCLUIDO);
        } else {
            setValue(t);
            sourceGuess = (aValue != null) ? valueToText(aValue) : "";
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (historyButton != null) {
            historyButton.setEnabled(enabled);
        }
    }

    /**
     * Limpa o arquivo de histÃ³rico para componentes da classe clazz
     * @param clazz classe
     */
    public static void cleanHistory(Class clazz) {
        File f = new File(getLastValuesDirectory() , String.format(HISTORY_FILE_FORMAT, clazz.getSimpleName()));
        if (!f.delete()) {
            throw new SystemException("fcorp.swing.lookup.deleteHistoryError");
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paint(g);
        
        if (null != getValueIcon(value)) {
            ImageIcon i = getValueIcon(value);

            final float alpha = 0.8f;
            final int spacingLeft = 3;
            final int spacingTop = 1;
            Composite com = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.drawImage(i.getImage(), spacingLeft, spacingTop, null);
            g2.setComposite(com);
            setMargin(new Insets(insetOther, insetLeft, insetOther, insetOther));
        } else {
            setMargin(new Insets(insetOther, insetOther, insetOther, insetOther));
        }
    }
    /**
    * Aplica o valor textual ao lookup
    */
    private void applyText() {
        if (guessing)  {
            return;
        }
        try {
            guessing = true;
            String texto = this.getText().trim().toUpperCase();
            String valueAsText = (value!=null) ? valueToText(value) : "";
            valueAsText = valueAsText.trim().toUpperCase();
            //valueAsText = (valueAsText != null) ? valueAsText.trim().toUpperCase() : "";

            if (texto.equals(sourceGuess) || (value!=null && texto.equals(valueAsText))) {
                // Evitar repetir a consulta para valores iguais
                setValue(value); 
            } else if (texto.length() == 0) {
                setValue(null);
            } else {
                guessValue();
            }
        } finally {
            guessing = false;
        }    
    }

    /**
     * Deve realizar o processo de busca do valor baseado no texto do campo.
     */
    public abstract void guessValue();
    /**
     * Deve mostrar o diÃ¡logo de busca customizado e setar o valor no campo.
     */
    public abstract void showDialog();
    /**
     * Deve retornar o Ã­cone que deve ser exibido para o valor selecionado.
     * @param aValue valor do campo
     * @return Ã�cone do valor
     */
    protected abstract ImageIcon getValueIcon(T aValue);
    
    /**
     * Deve retornar um registro
     * @param aValue
     * @return 
     */
    protected abstract T buscarPorId(T aValue);
    
    protected abstract Object valueToId(T value);
    
    protected SistamService getService() {
        return SistamApp.getSistamApp().getService();
    }
    
}