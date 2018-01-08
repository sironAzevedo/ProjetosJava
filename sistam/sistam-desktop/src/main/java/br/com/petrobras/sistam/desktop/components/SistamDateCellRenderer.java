package br.com.petrobras.sistam.desktop.components;

import br.com.petrobras.fcorp.swing.components.renderers.DateCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JLabel;
import javax.swing.JTable;

public class SistamDateCellRenderer extends DateCellRenderer{
    private TimeZone timeZone;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        Date d = (Date) value;
        
        if (isSelected){
            label.setBackground(Color.decode("#fdd898"));
            label.setForeground(Color.black);
        } else {
            label.setBackground(Color.decode("#e2ecf9"));      
        }
        
        if (value == null) {
            label.setText(null);
        } else if (getPattern() != null) {
            SimpleDateFormat df = new SimpleDateFormat(getPattern(), Locale.getDefault());
            df.setTimeZone(timeZone);
            label.setText(df.format(d));
        } else {
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
            df.setTimeZone(timeZone);
            label.setText(df.format(d));
        }
        
        return label;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }
}
