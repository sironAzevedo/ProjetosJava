/*
 * AveconUtils.java
 */
package br.com.petrobras.sistam.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public abstract class SistamDateUtils {

    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static final String DATE_HOUR_PATTERN = "dd/MM/yyyy HH:mm";
    public static final String HOUR_PATTERN = "HH:mm";

    /**
     * Mï¿½todo estï¿½tico capaz de retornar o ano corrente.
     *
     * @return Integer ano
     */
    public static Integer getCurrentYear(TimeZone zone) {
        if (zone != null) {
            return Calendar.getInstance(zone).get(Calendar.YEAR);
        } else {
            return Calendar.getInstance().get(Calendar.YEAR);
        }
    }

    public static Long getYearMonth(Date date, TimeZone zone) {
        Integer ano = getYearDate(date, zone);
        Integer mes = getMonthDate(date, zone);
        return Long.valueOf("" + ano + "" + String.format("%02d", mes));
    }

    public static String getMonthYear(Date date, TimeZone zone) {
        Integer mes = getMonthDate(date, zone);
        Integer ano = getYearDate(date, zone);
        return String.format("%02d", mes) + "/" + ano;
    }

    public static Integer getYearDate(Date date, TimeZone zone) {
        if (date == null) {
            return null;
        }
        Calendar c;
        if (zone != null) {
            c = Calendar.getInstance(zone);
        } else {
            c = Calendar.getInstance();
        }

        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static Integer getMonthDate(Date date, TimeZone zone) {
        if (date == null) {
            return null;
        }
        Calendar c;
        if (zone != null) {
            c = Calendar.getInstance(zone);
        } else {
            c = Calendar.getInstance();
        }

        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    public static Integer getDayDate(Date date, TimeZone zone) {
        if (date == null) {
            return null;
        }
        Calendar c;
        if (zone != null) {
            c = Calendar.getInstance(zone);
        } else {
            c = Calendar.getInstance();
        }

        c.setTime(date);
        return c.get(Calendar.DATE);
    }

    public static String formatDateByExtensive(Date d, TimeZone zone) {
        if (d == null) {
            return "";
        }

        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        if (zone != null) {
            df.setTimeZone(zone);
        }

        return df.format(d);
    }

    public static String formatShortDate(Date d, TimeZone zone) {
        if (d == null) {
            return "";
        }
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        if (zone != null) {
            df.setTimeZone(zone);
        }
        return df.format(d);
    }

    public static String formatDateComplete(Date d, TimeZone zone) {
        if (d == null) {
            return "";
        }
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Locale.getDefault());
        if (zone != null) {
            df.setTimeZone(zone);
        }
        return df.format(d);
    }

    public static String formatDate(Date d, String pattern, TimeZone timeZone) {
        if (d == null) {
            return "";
        }

        SimpleDateFormat df = new SimpleDateFormat(pattern);

        if (timeZone != null) {
            df.setTimeZone(timeZone);
        }

        return df.format(d);
    }

    public static Date truncateTime(Date d, TimeZone zone) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        if (zone != null) {
            c.setTimeZone(zone);
        }
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date truncateMonthTime(Date d, TimeZone zone) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        if (zone != null) {
            c.setTimeZone(zone);
        }
        c.setTime(d);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date informarData(Integer dia, Integer mes, Integer ano, TimeZone zone){
        return informarDataHora(dia, mes, ano, 12, 0, zone);
    }
    
    public static Date informarDataHora(Integer dia, Integer mes, Integer ano, Integer hora, Integer minuto, TimeZone zone) {
        Calendar data = GregorianCalendar.getInstance();
        if (zone != null) {
            data.setTimeZone(zone);
        }
        data.set(Calendar.DAY_OF_MONTH, dia);
        data.set(Calendar.HOUR_OF_DAY, hora);
        data.set(Calendar.MINUTE, minuto);
        data.set(Calendar.SECOND, 0);
        data.set(Calendar.MILLISECOND, 0);
        data.set(Calendar.MONTH, mes - 1);
        data.set(Calendar.YEAR, ano);

        return data.getTime();
    }

    public static Date alterarHorarioParaInicioDia(Date data, TimeZone zone) {
        return alterarHorario(data, 0, 0, 0, 0, zone);
    }

    public static Date alterarHorarioParaFimDia(Date data, TimeZone zone) {
        return alterarHorario(data, 23, 59, 59, 999, zone);
    }

    public static Date alterarHorario(Date data, int hora, int minuto, int segundo, int minisegundo, TimeZone zone) {
        Calendar novaData = Calendar.getInstance();
        if (zone != null) {
            novaData.setTimeZone(zone);
        }
        novaData.setTime(data);
        novaData.set(Calendar.HOUR_OF_DAY, hora);
        novaData.set(Calendar.MINUTE, minuto);
        novaData.set(Calendar.SECOND, segundo);
        novaData.set(Calendar.MILLISECOND, minisegundo);
        return novaData.getTime();
    }

    public static Date alterarSegundosMilisegundos(Date data,  int segundos, int milisegundos, TimeZone timeZone) {
        Calendar calendar = GregorianCalendar.getInstance();
        if (timeZone != null) {
            calendar.setTimeZone(timeZone);
        }
        calendar.setTime(data);
        calendar.set(Calendar.SECOND, segundos);
        calendar.set(Calendar.MILLISECOND, milisegundos);

        return calendar.getTime();
    }

    public static Date alterarParaMeioDia(Date data, TimeZone timeZone) {
        Calendar calendar = GregorianCalendar.getInstance();
        if (timeZone != null) {
            calendar.setTimeZone(timeZone);
        }
        calendar.setTime(data);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date somarDias(Date data, Integer dias, TimeZone timeZone) {
        Calendar calendar = GregorianCalendar.getInstance();
        if (timeZone != null) {
            calendar.setTimeZone(timeZone);
        }
        calendar.setTime(data);
        calendar.add(Calendar.DAY_OF_MONTH, dias);
        return calendar.getTime();
    }

    public static Date obterUltimoDiaMes(Date data, TimeZone timeZone) {
        Calendar calendar = GregorianCalendar.getInstance();
        if (timeZone != null) {
            calendar.setTimeZone(timeZone);
        }
        calendar.setTime(data);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static int diasCorridos(Date a, Date b, TimeZone timeZone) {
        Calendar dInicial = Calendar.getInstance();
        Calendar dFinal = Calendar.getInstance();
        if (timeZone != null) {
            dInicial.setTimeZone(timeZone);
            dFinal.setTimeZone(timeZone);
        }
        dInicial.setTime(truncateTime(a, timeZone));
        dFinal.setTime(truncateTime(b, timeZone));

        //if (dInicial.after(dFinal)) {
        //    throw new IllegalArgumentException("Data Inicio não pode ser maior que a Data Fim");
        //}

        int count = 0;
        while (dInicial.before(dFinal)) {
            dInicial.add(Calendar.DAY_OF_MONTH, 1);
            count++;
        }

        return count;
    }

    public static long diferencaEmSegundos(Date inicio, Date fim, TimeZone timeZone) {
        Calendar dInicial = GregorianCalendar.getInstance();
        Calendar dFinal = GregorianCalendar.getInstance();
        if (timeZone != null) {
            dInicial.setTimeZone(timeZone);
            dFinal.setTimeZone(timeZone);
        }
        dInicial.setTime(inicio);
        dFinal.setTime(fim);

        return (dFinal.getTimeInMillis() - dInicial.getTimeInMillis()) / 1000;
    }

    public static long diferencaEmMinutos(Date inicio, Date fim, TimeZone timeZone) {
        return diferencaEmSegundos(inicio, fim, timeZone) / 60;
    }
    
     public static Date incluirTimeZone(Date data, TimeZone zone) {
        Calendar novaData = Calendar.getInstance();
        if (zone != null) {
            novaData.setTimeZone(zone);
        }
        novaData.setTime(data);
        return novaData.getTime();
    }
}
