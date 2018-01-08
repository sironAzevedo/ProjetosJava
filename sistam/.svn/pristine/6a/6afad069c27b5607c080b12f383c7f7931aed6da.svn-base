package br.com.petrobras.sistam.desktop.components;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;

public class JMesPanel extends JPanel implements  PropertyChangeListener {

    protected JButton calendarButton;
    protected JTextFieldDateEditor dateEditor;
    protected JPopupMenu popup;
    protected JPanelMonthChoose jPanelChoose;
    protected boolean isInitialized;
    protected boolean dateSelected;
    public static final Integer MAX_ANO = 2050;
    public static final Integer MIN_ANO = 1900;
    private SimpleDateFormat dateFormatFull = new SimpleDateFormat("MM/yyyy");
    private SimpleDateFormat dateFormatOnlyYear = new SimpleDateFormat("yyyy");

    private ChangeListener changeListener;
    private boolean onlyYear = false;

    public JMesPanel() {

        iniComponent();



    }

    private void applyDate(boolean setNull) {
        String s = dateEditor.getText();
        Date d = null;
        s = s.replace("_", "").trim();

        dateEditor.setForeground(Color.BLACK);
        dateFormatFull.setLenient(false);
        try {
            if(!onlyYear){
                d = dateFormatFull.parse(s);
            } else {
                d = dateFormatOnlyYear.parse(s);
            }
        } catch (ParseException ex) {
        }


        if (d == null || (s.length() < 7 && !onlyYear) || (s.length() < 4 && onlyYear)) { // o usuario deve informar MM/YYYY
            if (setNull) {
                setDate(null);
            }
        } else {


            Calendar c = Calendar.getInstance();
            c.clear();
            c.setTime(d);

            int year = c.get(Calendar.YEAR);

            dateEditor.setForeground(Color.GREEN);
            if (year < MAX_ANO && year > MIN_ANO) {
                setDate(d);
            } else {
                dateEditor.setForeground(Color.RED);
            }


        }

    }


    /**
     * Listens for a "date" property change or a "day" property change event
     * from the JCalendar. Updates the date editor and closes the popup.
     *
     * @param evt
     *            the event
     */
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals("month")) {
            if (popup.isVisible()) {

                Calendar calendar = Calendar.getInstance();
                calendar.clear();
                calendar.set(Calendar.YEAR, jPanelChoose.getYear());
                calendar.set(Calendar.MONTH, jPanelChoose.getMonth());
                calendar.set(Calendar.HOUR_OF_DAY, 12);
                setDate(calendar.getTime());
            }
        } else if (evt.getPropertyName().equals("year")) {
            if (popup.isVisible()) {
                Calendar calendar = Calendar.getInstance();
                calendar.clear();
                int month = jPanelChoose.getMonth();

                if (month > -1) {
                    calendar.set(Calendar.YEAR, jPanelChoose.getYear());
                    calendar.set(Calendar.MONTH, jPanelChoose.getMonth());
                    calendar.set(Calendar.HOUR_OF_DAY, 12);
                    setDate(calendar.getTime());
                }
            }
        } else if (evt.getPropertyName().equals("finished")) {
            if (popup.isVisible() && jPanelChoose.isFinished()) {
                dateSelected = true;
                popup.setVisible(false);
            }
        }

    }

    /**
     * Sets the locale.
     *
     * @param l
     *            The new locale value
     */
    @Override
    public void setLocale(Locale l) {
        super.setLocale(l);
        dateEditor.setLocale(l);
    }

    /**
     * Returns the date. If the JDateChooser is started with a null date and no
     * date was set by the user, null is returned.
     *
     * @return the current date
     */
    public Date getDate() {
        return dateEditor.getDate();
    }

    /**
     * Sets the date. Fires the property change "date" if date != null.
     *
     * @param date
     *            the new date.
     */
    public void setDate(Date date) {


        Object old = dateEditor.getDate();
        dateEditor.setDate(date);
        if (old != null && old.equals(date)) {
            old = null;
        }
        firePropertyChange("date", old, date);

        if (getParent() != null) {
            getParent().invalidate();
        }
    }

    /**
     * Returns the calendar. If the JDateChooser is started with a null date (or
     * null calendar) and no date was set by the user, null is returned.
     *
     * @return the current calendar
     */
    public Calendar getCalendar() {
        Date date = getDate();
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Sets the calendar. Value null will set the null date on the date editor.
     *
     * @param calendar
     *            the calendar.
     */
    public void setCalendar(Calendar calendar) {
        if (calendar == null) {
            dateEditor.setDate(null);
        } else {
            dateEditor.setDate(calendar.getTime());
        }
    }

    /**
     * Enable or disable the JDateChooser.
     *
     * @param enabled
     *            the new enabled value
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (dateEditor != null) {
            dateEditor.setEnabled(enabled);
            calendarButton.setEnabled(enabled);
        }
    }

    /**
     * Returns true, if enabled.
     *
     * @return true, if enabled.
     */
    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }

    /**
     * Sets the icon of the buuton.
     *
     * @param icon
     *            The new icon
     */
    public void setIcon(ImageIcon icon) {
        calendarButton.setIcon(icon);
    }

    /**
     * Sets the font of all subcomponents.
     *
     * @param font
     *            the new font
     */
    public void setFont(java.awt.Font font) {
        if (isInitialized) {
            dateEditor.getUiComponent().setFont(font);
        }
        super.setFont(font);
    }

    /**
     * Returns the calendar button.
     *
     * @return the calendar button
     */
    public JButton getCalendarButton() {
        return calendarButton;
    }

    /**
     * Returns the date editor.
     *
     * @return the date editor
     */
    public IDateEditor getDateEditor() {
        return dateEditor;
    }
    /*
     * Should only be invoked if the JDateChooser is not used anymore. Due to popup
     * handling it had to register a change listener to the default menu
     * selection manager which will be unregistered here. Use this method to
     * cleanup possible memory leaks.
     */

    public void cleanup() {
        /*MenuSelectionManager.defaultManager().removeChangeListener(changeListener);
        changeListener = null;*/
    }

    public void setOnlyYear(boolean onlyYear) {
        boolean old = this.onlyYear;
        this.onlyYear = onlyYear;
        if (old != onlyYear) {


            int yearOld = -1;
            int month = -1;
            boolean dateExist = false;
            Calendar calendar = Calendar.getInstance();
            calendar.clear();

            if(dateEditor.getDate() != null){

                calendar.setTime(dateEditor.getDate());

                yearOld = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dateExist = true;
                calendar.clear();
            }
            

            remove(dateEditor.getUiComponent());
            remove(calendarButton);

            if(!onlyYear){
                createCalendarButton();
            }

            if(yearOld > -1){
                calendar.set(Calendar.YEAR, yearOld);
            }
            if(month > -1){
                calendar.set(Calendar.MONTH, month);
            }
            createDateEditor();


            if(dateExist){
                setDate(calendar.getTime());
            }

            updateUI();
        }
    }


    private void createPopupDateChoose(){


        popup = new JPopupMenu() {

            private static final long serialVersionUID = -6078272560337577761L;

            @Override
            public void setVisible(boolean b) {
                Boolean isCanceled = (Boolean) getClientProperty("JPopupMenu.firePopupMenuCanceled");
                if (b || (!b && dateSelected)
                        || ((isCanceled != null) && !b && isCanceled.booleanValue())) {
                    super.setVisible(b);
                }
            }
        };
        popup.setLightWeightPopupEnabled(true);


        jPanelChoose = new JPanelMonthChoose();
        jPanelChoose.addPropertyChangeListener("month", this);
        jPanelChoose.addPropertyChangeListener("year", this);
        jPanelChoose.addPropertyChangeListener("finished", this);

        Dimension d = new java.awt.Dimension(100, 80);
        jPanelChoose.setPreferredSize(d);
        jPanelChoose.setMaximumSize(d);
        jPanelChoose.setMinimumSize(d);
        popup.add(jPanelChoose);
    }

    

    private void iniComponent() {

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setLayout(new BorderLayout());



        createDateEditor();

        createCalendarButton();

        createPopupDateChoose();


        changeListener = new ChangeListener() {

            boolean hasListened = false;

            public void stateChanged(ChangeEvent e) {
                if (hasListened) {
                    hasListened = false;
                    return;
                }
                if (popup.isVisible()) {
                    MenuElement[] me = MenuSelectionManager.defaultManager().getSelectedPath();
                    MenuElement[] newMe = new MenuElement[me.length + 1];
                    newMe[0] = popup;
                    for (int i = 0; i < me.length; i++) {
                        newMe[i + 1] = me[i];
                    }
                    hasListened = true;
                    MenuSelectionManager.defaultManager().setSelectedPath(newMe);
                }
            }
        };
        MenuSelectionManager.defaultManager().addChangeListener(changeListener);



        isInitialized = true;
    }



    private void createCalendarButton(){

        // Display a calendar button with an icon
        URL iconURL = getClass().getResource("/icons/JDateChooserNullable.png");

        ImageIcon icon = new ImageIcon(iconURL);

        calendarButton = new JButton(icon) {

            @Override
            public boolean isFocusable() {
                return false;
            }
        };
        calendarButton.setMargin(new Insets(0, 0, 0, 0));
        calendarButton.addActionListener(new ActionListener() {


                        /**
                         * Called when the jCalendar button was pressed.
                         *
                         * @param e
                         *            the action event
                         */
                        public void actionPerformed(ActionEvent e) {
                            int x = calendarButton.getWidth() - (int) popup.getPreferredSize().getWidth();
                            int y = calendarButton.getY() + calendarButton.getHeight();

                            Calendar calendar = Calendar.getInstance();
                            Date date = dateEditor.getDate();
                            if (date != null) {
                                calendar.setTime(date);
                            }
                            popup.show(calendarButton, x, y);

                            jPanelChoose.setDates(date);
                            dateSelected = false;

                        }
        });
        calendarButton.setMnemonic(KeyEvent.VK_DOWN);
        calendarButton.setMargin(new Insets(0, 0, 0, 0));


        add(calendarButton, BorderLayout.EAST);

    }

    

    private void createDateEditor(){

        String datePattern = (onlyYear) ? "yyyy" : "MM/yyyy";
        String maskPattern = (onlyYear) ? "####" : "##/####";
        char placeholder = '_';
        dateEditor = new JTextFieldDateEditor(datePattern, maskPattern, placeholder);

        // Center the text
        dateEditor.setHorizontalAlignment(JTextFieldDateEditor.RIGHT);
        add(this.dateEditor.getUiComponent(), BorderLayout.CENTER);


        dateEditor.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent fe) {
                if (dateEditor.getDate() == null || dateEditor.getText().trim().equals("") && dateEditor.isEnabled()) {
                    dateEditor.setText("");
                }
            }

            public void focusLost(FocusEvent fe) {
                if (dateEditor.isEnabled()) {
                    applyDate(true);
                }
            }
        });
        dateEditor.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    dateEditor.setText("");
                }

                applyDate(false);

            }
        });
    }


    public static void main(String[] s) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, InterruptedException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        JFrame frame = new JFrame("JDateChooser");
        JMesPanel dateChooser = new JMesPanel();
        // JDateChooser dateChooser = new JDateChooser(null, new Date(), null,
        // null);
        // dateChooser.setLocale(new Locale("de"));
        // dateChooser.setDateFormatString("dd. MMMM yyyy");

        // dateChooser.setPreferredSize(new Dimension(130, 20));
        // dateChooser.setFont(new Font("Verdana", Font.PLAIN, 10));
        // dateChooser.setDateFormatString("yyyy-MM-dd HH:mm");

        // URL iconURL = dateChooser.getClass().getResource(
        // "/com/toedter/calendar/images/JMonthChooserColor32.gif");
        // ImageIcon icon = new ImageIcon(iconURL);
        // dateChooser.setIcon(icon);

        Calendar c = Calendar.getInstance();
        dateChooser.setDate(c.getTime());
        frame.getContentPane().add(dateChooser);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dateChooser.setOnlyYear(true);
        dateChooser.setOnlyYear(false);

        /*
        for (int i = 0; i <= 255; i++) {
        for (int j = 0; j <= 255; j++) {
        for (int k = 0; k <= 255; k++) {
        Color backGroundColor = Color.YELLOW;//new Color(i,j,k);
        d.setBackground(backGroundColor);
        Color fg = new Color(255,255,255);
        //Color bc = d.getForeground();
        Color bc = d.getBackground();

        Color novaCor = getColor(bc);
        System.out.println("BACK COR: "+bc);
        System.out.println("FORE COR: "+novaCor);
        d.setForeground(novaCor);

        }

         */
    }
}

class JPanelMonthChoose extends JPanel {

    protected Integer year = Calendar.getInstance().get(Calendar.YEAR);
    protected Integer month = -1;
    protected boolean finished;
    protected Color selectedColor;
    protected Color oldMonthBackgroundColor;
    protected Color oldMonthForegroundColor;
    protected Color darkGreen;

    {
        selectedColor = new Color(160, 160, 160);
        JButton testeJButton = new JButton();
        oldMonthBackgroundColor = (testeJButton.getBackground());
        oldMonthForegroundColor = Color.BLACK;//(testeJButton.getForeground());
        finished = false;
        darkGreen = new Color(0, 150, 0);
    }

    public JPanelMonthChoose() {
        initComponents();
    }

    public JPanelMonthChoose(Integer ano) {
        this();
        this.year = ano;
    }

    private void initComponents() {
        pnlDataYear = new javax.swing.JPanel();
        btnSubtractYear = new javax.swing.JButton();
        try {
            txtYearField = new javax.swing.JFormattedTextField(new MaskFormatter("####"));
        } catch (ParseException e) {
            txtYearField = new javax.swing.JFormattedTextField();
        }
        btnAddYear = new javax.swing.JButton();
        pnlDataMonth = new javax.swing.JPanel();
        btnMonths = initMonthButtons();

        //setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setLayout(new java.awt.BorderLayout());



        URL iconURL = getClass().getResource("/icons/draw-triangle_left.png");

        ImageIcon icon = new ImageIcon(iconURL);
        btnSubtractYear.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSubtractYear.setContentAreaFilled(false);
        btnSubtractYear.setIcon(icon);
        btnSubtractYear.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOneYear(-1);
            }
        });

        txtYearField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtYearField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtYearField.setText(year.toString());
        txtYearField.setMinimumSize(new java.awt.Dimension(45, 16));



        iconURL = getClass().getResource("/icons/draw-triangle_right.png");
        icon = new ImageIcon(iconURL);
        btnAddYear.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnAddYear.setContentAreaFilled(false);
        btnAddYear.setIcon(icon);
        btnAddYear.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOneYear(1);
            }
        });


        pnlDataYear.setLayout(new java.awt.GridLayout());
        pnlDataYear.add(btnSubtractYear);
        pnlDataYear.add(txtYearField);
        pnlDataYear.add(btnAddYear);


        //add(pnlDataYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, -1));

        add(pnlDataYear, java.awt.BorderLayout.PAGE_START);
        pnlDataMonth.setLayout(new java.awt.GridLayout(4, 3, 0, 0));



        for (JButton jButton : btnMonths) {
            pnlDataMonth.add(jButton);
        }

        add(pnlDataMonth, java.awt.BorderLayout.PAGE_END);//, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 33, 110, 70));



        txtYearField.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                String text = txtYearField.getText();

                if (text == null || text.trim().equals("")) {
                    return;
                }
                int year = Integer.parseInt(text.trim());
                txtYearField.setForeground(darkGreen);
                if (!isValidYear(year)) {
                    txtYearField.setForeground(Color.RED);
                } else {
                    setYear(year);
                    verifyMonthActualy();
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtYearField.setForeground(Color.BLACK);
                    if (isValidYear(year)) {

                        if (getMonth() == -1) {
                            setMonth(0);
                        }
                        setFinished(true);
                    }

                }
            }
        });


        btnAddYear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubtractYear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //btnUpItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        updateUI();
    }

    public void setDates(Date date) {


        Calendar c = Calendar.getInstance();
        if (date != null) {

            c.clear();
            c.setTime(date);

            setYear(c.get(Calendar.YEAR));
            setMonth(c.get(Calendar.MONTH));

        } else {

            setYear(c.get(Calendar.YEAR));
            //modifyMonthToggleButtons(-1);
            setMonth(-1);
        }
        txtYearField.setForeground(Color.BLACK);
        setFinished(false);
    }

    private void addOneYear(int value) {
        setYear(getYear() + value);
    }

    public Integer getYear() {
        return year;
    }

    private boolean isValidYear(int year) {
        if (year > JMesPanel.MAX_ANO || year < JMesPanel.MIN_ANO) {
            return false;
        }
        return true;
    }

    public void setYear(Integer year) {
        btnAddYear.setEnabled(true);
        btnSubtractYear.setEnabled(true);
        if (year > JMesPanel.MAX_ANO || year < JMesPanel.MIN_ANO) {
            return;
        } else if (year == JMesPanel.MAX_ANO && btnAddYear != null) {
            btnAddYear.setEnabled(false);
        } else if (year == JMesPanel.MIN_ANO && btnSubtractYear != null) {
            btnSubtractYear.setEnabled(false);
        }





        Object old = this.year;
        this.year = year;
        firePropertyChange("year", old, year);


        if (txtYearField != null) {
            txtYearField.setText(this.year.toString());
        }
        verifyMonthActualy();

    }

    public Integer getMonth() {
        return month;
    }

    private void verifyMonthActualy() {
        for (int i = 0; i < btnMonths.length; i++) {
            btnMonths[i].setForeground(oldMonthForegroundColor);
            if (monthActualy(i)) {
                btnMonths[i].setForeground(Color.BLUE);
            }
        }
    }

    private void modifyMonthToggleButtons(Integer month) {


        for (int i = 0; i < btnMonths.length; i++) {

            btnMonths[i].setSelected(false);
            btnMonths[i].setBackground(oldMonthBackgroundColor);


        }




        if (month >= 0 && month < btnMonths.length - 1) {
            btnMonths[month].setSelected(true);
            btnMonths[month].setBackground(selectedColor);
        }

        verifyMonthActualy();
    }

    private boolean monthActualy(int monthForTest) {
        Calendar c = Calendar.getInstance(Locale.getDefault());


        if (c.get(Calendar.YEAR) != this.year || c.get(Calendar.MONTH) != monthForTest) {
            return false;
        }

        return true;
    }

    public void setMonth(Integer month) {

        modifyMonthToggleButtons(month);


        Object old = this.month;
        this.month = month;
        firePropertyChange("month", old, month);
    }

    private JButton[] initMonthButtons() {
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(Locale.getDefault());
        String[] monthNames = dateFormatSymbols.getShortMonths();
        JButton[] buttons = new JButton[12];



        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton() {

                private static final long serialVersionUID = -7433645992591669725L;

                @Override
                public void paint(Graphics g) {
                    if ("Windows".equals(UIManager.getLookAndFeel().getID())) {
                        // this is a hack to get the background painted
                        // when using Windows Look & Feel
                        if (selectedDay(this)) {
                            g.setColor(selectedColor);
                            g.fillRect(0, 0, getWidth(), getHeight());
                        }
                    }
                    super.paint(g);
                }
            };
            buttons[i].setMargin(new Insets(0, 0, 0, 0));
            buttons[i].setFocusPainted(false);
            buttons[i].setText(monthNames[i]);
            //buttons[i].setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
            buttons[i].setBorder(null);

            buttons[i].addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    JButton buttoCliked = (JButton) (evt.getSource());
                    for (int i = 0; i < btnMonths.length; i++) {
                        if (buttoCliked == btnMonths[i]) {
                            setMonth(i);
                            setFinished(true);
                        }
                    }
                }
            });
            buttons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(true);


            buttons[i].invalidate();
            buttons[i].repaint();


        }
        return buttons;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        Object old = this.finished;
        this.finished = finished;
        firePropertyChange("finished", old, finished);
    }

    private boolean selectedDay(JButton aThis) {
        if (month == null || month == -1 || btnMonths[month] != aThis) {
            return false;
        }

        return true;
    }
    private javax.swing.JButton btnSubtractYear;
    private javax.swing.JButton btnAddYear;
    private javax.swing.JFormattedTextField txtYearField;
    private javax.swing.JPanel pnlDataYear;
    private javax.swing.JPanel pnlDataMonth;
    private javax.swing.JButton[] btnMonths;
}