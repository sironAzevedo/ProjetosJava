package br.com.petrobras.sistam.desktop.components;

import br.com.petrobras.fcorp.i18n.I18nManager;
import br.com.petrobras.fcorp.swing.components.action.ConfigurableAction;
import br.com.petrobras.fcorp.swing.components.dialog.FinalizeOnDisposeDialog;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.MaskFormatter;
import org.slf4j.LoggerFactory;

public class SistamDateChooser extends JFormattedTextField implements PropertyChangeListener, FocusListener, CaretListener, ActionListener {
    private FinalizeOnDisposeDialog dialog;
    
    protected final SimpleDateFormat dateFormatter;
    protected final SimpleDateFormat timeFormatter;
    private JButton calendarButton;
    protected JCalendar jcalendar;
    private MaskFormatter mask;
    
    protected SistamTimeChooser timeChooser;
    protected TimeZoneComboBox timeZoneComboBox;
    protected TimeZone timeZone = TimeZone.getDefault();
    private boolean ignoreTimeChange;
    
    private Long date;
    
    /**
     * Construtor
     */
    public SistamDateChooser() {
        setText("");
        date = null;
        dateFormatter = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM);
        timeFormatter = (SimpleDateFormat) DateFormat.getTimeInstance(DateFormat.SHORT);
        dateFormatter.setLenient(false);
        timeFormatter.setLenient(false);

        try {
            mask = new MaskFormatter();
            mask.setMask(dateFormatter.format(new Date()).replaceAll("[a-zA-Z]", "L")
                                                         .replaceAll("\\d", "#"));
            mask.setPlaceholderCharacter('_');
            mask.install(this);
        } catch (ParseException ex) {
            LoggerFactory.getLogger(getClass()).error(ex.getMessage(), ex);
        }

        this.setLayout(new BorderLayout());
        calendarButton = new JButton(new ConfigurableAction(new ImageIcon(getClass().getResource("/com/toedter/calendar/images/JDateChooserIcon.gif")),
                                     "", "showCalendar", this));
        calendarButton.setOpaque(false);
        calendarButton.setFocusable(false);
        calendarButton.setContentAreaFilled(false);
        calendarButton.setBorder(null);
        final int defaultSize = 18;
        calendarButton.setPreferredSize(new Dimension(defaultSize, defaultSize));
        calendarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(calendarButton, BorderLayout.EAST);

        jcalendar = new JCalendar();
        jcalendar.getDayChooser().addPropertyChangeListener(this);
        jcalendar.getDayChooser().setAlwaysFireDayProperty(true);

        final int defaultWidth = 90;
        setPreferredSize(new Dimension(defaultWidth, getPreferredSize().height));

        addActionListener(this);
        addFocusListener(this);
        addCaretListener(this);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("day") && dialog!=null) {
            dialog.setVisible(false);
        } else if (evt.getPropertyName().equals("time") && !ignoreTimeChange) {
            if (date != null) {
                setDate(new Date(date.longValue()), true);
            } else if (timeChooser.getTime() != null) {
                setDate(new Date(), true);
            }
            //setDate((date!=null) ? new Date(date.longValue()) : new Date(), true);
        } else if (evt.getPropertyName().equals("timeZoneSelecionado")) {
            if (timeZoneComboBox  != null) {
                this.timeZone = timeZoneComboBox.getTimeZoneSelecionado();
                dateFormatter.setTimeZone(timeZoneComboBox.getTimeZoneSelecionado());
                timeFormatter.setTimeZone(timeZoneComboBox.getTimeZoneSelecionado());
                jcalendar.getCalendar().setTimeZone(timeZoneComboBox.getTimeZoneSelecionado());
                if (timeChooser != null) {
                    timeChooser.setTimeZone(timeZoneComboBox.getTimeZoneSelecionado());
                }
                
                if (getDate() != null) {
                    setText(dateFormatter.format(getDate()));
                    if (timeChooser != null) {
                        timeChooser.setText(timeFormatter.format(getDate()));
                    }
                }    
            }
        }
    }

    public JCalendar getJCalendar() {
        return jcalendar;
    }
    
    public Date getDate() {
        return (date == null) ? null : new Date(date);
    }

    /**
     * @param date data
     */
    public void setDate(Date date) {
        setDate(date, false);
    }

    /**
     * Define a data
     * @param date data
     * @param appendTime appendTime
     */
    public void setDate(Date date, boolean appendTime) {
        Object old = getDate();
        if (date == null) {
            this.date = null;
            setText("");
            if (timeChooser!=null) {
                try {
                    ignoreTimeChange = true;
                    timeChooser.setTime(null);
                } finally {
                    ignoreTimeChange = false;
                }
            }
        } else {
            if (appendTime) {
                Calendar calDate = Calendar.getInstance(timeZone);
                calDate.setTime(date);

                if (timeChooser != null) {
                    Date time = timeChooser.getTime();
                    if (time==null) {
                        time=new Date();
                    }
                    Calendar calTime = Calendar.getInstance(timeZone);
                    calTime.setTime(time);
                    calDate.set(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
                    calDate.set(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
                    calDate.set(Calendar.SECOND, calTime.get(Calendar.SECOND));
                    calDate.set(Calendar.MILLISECOND, calTime.get(Calendar.MILLISECOND));
                }

                date = calDate.getTime();
            }
            if (timeChooser!=null) {
                try {
                    ignoreTimeChange = true;
                    timeChooser.setTime(date);
                } finally {
                    ignoreTimeChange = false;
                }
            } else {
                Calendar cal = Calendar.getInstance(timeZone);
                cal.setTime(date);
                cal.set(Calendar.HOUR_OF_DAY, 12);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                date = cal.getTime();
            }
            this.date = date.getTime();
            String formattedDate = dateFormatter.format(date);
            setText(formattedDate);
        }
        setForeground(Color.BLACK);
        firePropertyChange("date", old, date);
    }

    public SistamTimeChooser getTimeChooser() {
        return timeChooser;
    }

    /**
     * Define o timeChooser conectado a este dateChooser
     * @param timeChooser timeChooser
     */
    public void setTimeChooser(SistamTimeChooser timeChooser) {
        if (this.timeChooser!=null) {
            this.timeChooser.removePropertyChangeListener(this);
        }

        Object old = this.timeChooser;
        this.timeChooser = timeChooser;
        firePropertyChange("timeChooser", old, this.timeChooser);

        if (this.timeChooser!=null) {
            this.timeChooser.addPropertyChangeListener(this);
        }
    }

    public TimeZoneComboBox getTimeZoneComboBox() {
        return timeZoneComboBox;
    }

    public void setTimeZoneComboBox(TimeZoneComboBox timeZoneComboBox) {
        if (this.timeZoneComboBox!=null) {
            this.timeZoneComboBox.removePropertyChangeListener(this);
        }

        Object old = this.timeZoneComboBox;
        this.timeZoneComboBox = timeZoneComboBox;
        firePropertyChange("timeZoneComboBox", old, this.timeZoneComboBox);

        if (this.timeZoneComboBox!=null) {
            this.timeZoneComboBox.addPropertyChangeListener(this);
        }
    }
    
    
    
    @Override
    public void setLocale(Locale l) {
        super.setLocale(l);
        jcalendar.setLocale(l);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        applyDate();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void focusGained(FocusEvent e) {}
    /**
     * {@inheritDoc}
     */
    @Override
    public void focusLost(FocusEvent focusEvent) {
        applyDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void caretUpdate(CaretEvent event) {
        String text = getText().trim();

        if (text.length() == 0) {
            setForeground(Color.BLACK);
            return;
        }

        try {
            dateFormatter.parse(getText());
            setForeground(Color.GREEN.darker().darker());
        } catch (ParseException e) {
            setForeground(Color.RED.darker());
        }
    }

    /**
     * Exibe o calendÃ¡rio
     */
    public void showCalendar() {
        Calendar calendar = Calendar.getInstance(timeZone);

        Date d = getDate();
        if (d != null) {
            calendar.setTime(d);
        }
        
        jcalendar.setCalendar(calendar);

        removeFocusListener(this);
        showDialog();
        addFocusListener(this);
        setDate(jcalendar.getCalendar().getTime(), true);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        calendarButton.setEnabled(enabled);
    }

    /**
     * Define o Ã­cone do botÃ£o do calendar
     * @param icon icone
     */
    public void setIcon(ImageIcon icon) {
        calendarButton.setIcon(icon);
    }

    /**
     * Exibe o diÃ¡logo
     */
    private void showDialog() {
        Component c = SwingUtilities.getRoot(this);
        final String msg = I18nManager.getString("fcorp.swing.components.sdatechooser.dialogMessage");
        if (c instanceof Frame) {
            dialog = new FinalizeOnDisposeDialog((Frame)c, msg, true);
        } else if (c instanceof Dialog) {
            dialog = new FinalizeOnDisposeDialog((Dialog)c, msg, true);
        } else {
            dialog = new FinalizeOnDisposeDialog((Frame)null, msg, true);
        }
        dialog.setLayout(new BorderLayout());
        dialog.add(jcalendar, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(calendarButton);
        dialog.setVisible(true);
    }

    /**
     * Aplica o texto digitado Ã  data
     */
    public void applyDate() {
        if (getText() == null || getText().trim().equals("")) {
            setDate(null);
        } else {
            try {
                Date d = dateFormatter.parse(getText());
                setDate(d, true);
            } catch (ParseException ex) {
                setDate(null);
            }
        }
    }
}
