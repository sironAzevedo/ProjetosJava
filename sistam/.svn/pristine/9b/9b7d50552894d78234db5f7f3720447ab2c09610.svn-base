package br.com.petrobras.sistam.desktop.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.MaskFormatter;

public class SistamTimeChooser extends JFormattedTextField implements FocusListener, ActionListener, CaretListener {
    private static final Icon ICO = new javax.swing.ImageIcon(SistamTimeChooser.class.getResource("/icons/stimechooser.png"));
    private static final Color DARK_GREEN = Color.GREEN.darker().darker();
    private static final String TIME_PATTERN = "HH:mm";
    private static final String TIME_MASK = "##:##";
    private static final String EMPTY_MASK = "__:__";

    protected MaskFormatter mask;
    private Long time;
    protected DateFormat formatter;
    private JList list;
    private JPopupMenu popup;
    private JToggleButton btnTime;
    protected TimeZone timeZone = TimeZone.getDefault();

    /**
     * Construtor
     */
    public SistamTimeChooser() {
        final int defaultButtonWidth = 18;
        final int defaultWidth = 60;
        final int doubleClick = 2;
        setText("");

        formatter = new SimpleDateFormat(TIME_PATTERN, Locale.getDefault());
        formatter.setLenient(false);

        addFocusListener(this);
        addActionListener(this);
        addCaretListener(this);

        try {
            mask = new MaskFormatter(TIME_MASK);
            mask.setPlaceholderCharacter('_');
            mask.install(this);
        }catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        DefaultListModel listData = new DefaultListModel();
        int m = 0;
        final int hoursInDay = 24;
        final int minutesInHour = 60;
        final int minutesStep = 15;
        for (int h = 0; h < hoursInDay; h++) {
            for (m = 0; m < minutesInHour; m += minutesStep) {
                listData.addElement(String.format("%02d:%02d", h, m));
            }
        }
            
        
        list = new JList(listData);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == doubleClick) {
                    confirmPopup();
                }
            }
        });
        list.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    confirmPopup();
                }
            }
        });

        popup = new JPopupMenu();
        popup.setLayout(new BorderLayout());
        popup.add(new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
        popup.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
            public void popupMenuCanceled(PopupMenuEvent e) {
                btnTime.setSelected(false);
            }
        });

        btnTime = new JToggleButton();
        btnTime.setIcon(ICO);
        btnTime.setOpaque(false);
        btnTime.setFocusable(false);
        btnTime.setFocusPainted(false);
        btnTime.setContentAreaFilled(false);
        btnTime.setBorder(null);
        btnTime.setPreferredSize(new Dimension(defaultButtonWidth, defaultButtonWidth));
        btnTime.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (btnTime.isSelected()) {
                    showPopup();
                } else {
                    popup.setVisible(false);
                }
            }
        });

        this.setLayout(new BorderLayout());
        this.add(this.btnTime, BorderLayout.EAST);
        setPreferredSize(new Dimension(defaultWidth, getPreferredSize().height));
    }
    

    /**
     * {@inheritDoc}
     */
    public void focusGained(FocusEvent e) {
        paintValue();
    }
    

    /**
     * {@inheritDoc}
     */
    public void focusLost(FocusEvent fe) {
        applyValue();
    }


    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent ae) {
        applyValue();
    }

    /**
     * {@inheritDoc}
     */
    public void caretUpdate(CaretEvent e) {
        if (SistamTimeChooser.this.hasFocus()){
            paintValue();
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        btnTime.setEnabled(enabled);
    }

    public Date getTime() {
        return (time==null) ? null : new Date(time.longValue());
    }

    /**
     * Define o valor da hora do campo
     * @param time date contendo a hora
     */
    public void setTime(Date time) {
        Object old = getTime();
        if (time==null) {
            this.time = null;
            setText("");
        } else {
            this.time = time.getTime();
            setText(formatter.format(time));
        }
        firePropertyChange("time", old, getTime());
    }

    /**
     * Aplica o valor textual ao campo
     */
    private void applyValue() {
        String text = getText().trim();
        if ("".equals(text)){
            setTime(null);
        } else {
            try {
                Calendar cal = Calendar.getInstance(timeZone);
                cal.setTime(formatter.parse(text));
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                setTime(cal.getTime());
            } catch (ParseException e) {
                setTime(null);
            }
        }
        setForeground(Color.BLACK);
    }

    /**
     * Pinta o valor atual do campo
     */
    private void paintValue() {
        String text = getText().trim();
        if ("".equals(text) || text.equals(EMPTY_MASK)) {
            setForeground(Color.BLACK);
            return;
        }
        try {
            formatter.parse(text);
            setForeground(DARK_GREEN);
        } catch (ParseException e) {
            setForeground(Color.RED);
        }
    }

    /**
     * Exibe o popup
     */
    private void showPopup() {
        final int popupWidth = 150;
        list.setSelectedValue(getText(), true);
        popup.setPopupSize(this.getWidth(), popupWidth);
        popup.show(SistamTimeChooser.this, 0, SistamTimeChooser.this.getHeight()+1);
    }

    /**
     * Exibe popup de confirmaÃ§Ã£o
     */
    private void confirmPopup() {
        setText(list.getSelectedValue().toString());
        applyValue();
        popup.setVisible(false);
        btnTime.setSelected(false);
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        formatter.setTimeZone(timeZone);
    }
    
    
}