package br.com.petrobras.sistam.desktop.components.lookups.timezone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import javax.swing.ImageIcon;

public class TimeZoneLookup extends TimeZoneInput {

    private static final List<TimeZone> TIMEZONES;

    static {
        List<TimeZone> timeZones = new ArrayList<TimeZone>();
        String[] ids = TimeZone.getAvailableIDs();
        for (String id : ids) {
            timeZones.add(TimeZone.getTimeZone(id));
        }
        TIMEZONES = Collections.unmodifiableList(timeZones);
    }

    public TimeZoneLookup() {
        super();
    }

    @Override
    protected List<TimeZone> buscarPorNome(String nome) {

        List<TimeZone> filtrado = new ArrayList<TimeZone>();
        for (TimeZone zone : TIMEZONES) {
            if (zone.getID().toLowerCase().contains(nome.toLowerCase())) {
                filtrado.add(zone);
            }
        }

        return filtrado;
    }

    @Override
    protected ImageIcon getValueIcon(TimeZone aValue) {
        return null;
    }

    @Override
    protected TimeZone buscarPorId(TimeZone aValue) {
        return null;
    }

    @Override
    protected String valueToId(TimeZone value) {
        return value.getID();
    }
}
