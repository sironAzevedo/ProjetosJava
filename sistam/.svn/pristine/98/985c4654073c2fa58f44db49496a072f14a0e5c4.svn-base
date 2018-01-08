package br.com.petrobras.sistam.desktop.components;

import br.com.petrobras.fcorp.swing.components.SComboBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;


public class TimeZoneComboBox extends SComboBox {
    

    private static final List<TimeZone> TIMEZONES;
    private TimeZone timeZoneSelecionado;
        
    static {  
        List<TimeZone> timeZones = new ArrayList<TimeZone>();  
        String[] ids = TimeZone.getAvailableIDs();
        for(String id : ids) {
            timeZones.add(TimeZone.getTimeZone(id));
        }
        TIMEZONES = Collections.unmodifiableList(timeZones);  
   }
    
   public TimeZoneComboBox() {
       super();
       this.setDisplayProperty("ID");
       for (TimeZone zone : TIMEZONES) {
           this.addItem(zone);
       }
       
       this.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
               if(e.getStateChange() == ItemEvent.SELECTED) {
                setTimeZoneSelecionado((TimeZone)e.getItem());
               }
           }
       });
       
   }

    public TimeZone getTimeZoneSelecionado() {
        return timeZoneSelecionado;
    }

    private void setTimeZoneSelecionado(TimeZone timeZoneSelecionado) {
        this.timeZoneSelecionado = timeZoneSelecionado;
        firePropertyChange("timeZoneSelecionado", null, this.timeZoneSelecionado);
    }
   
   
    
    
    
}
