package br.com.petrobras.sistam.desktop.components;

import br.com.petrobras.fcorp.swing.components.renderers.NumberCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.util.TimeZone;
import javax.swing.JLabel;
import javax.swing.JTable;

public class SistamNumberCellRenderer extends NumberCellRenderer{
    private TimeZone timeZone;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if (isSelected){
            label.setBackground(Color.decode("#fdd898"));
            label.setForeground(Color.black);
        } else {
            label.setBackground(Color.decode("#e2ecf9"));      
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
