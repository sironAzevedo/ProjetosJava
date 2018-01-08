/*
 * AveconUtils.java
 */
package br.com.petrobras.sistam.common.util;

import br.com.petrobras.fcorp.common.exception.SystemException;
import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.MaskFormatter;
import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.jdesktop.beansbinding.BeanProperty;

/**
 *
 * @author x4rb
 */
public abstract class SistamUtils {

    public static String calcularHash(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(string.getBytes());
            BigInteger hash = new BigInteger(1, b);
            String s = hash.toString(16);

            // Colocar os zeros da esquerda que foram limpados do hash.
            for (int i = 0; i < 32 - s.length(); i++) {
                s = "0" + s;
            }
            return s;
        } catch (NoSuchAlgorithmException ex) {
            throw new SystemException("algoritmo MD5 não está disponível", ex);
        }
    }

    public static String getFileNameExtension(String name) {
        int i = name.lastIndexOf(".");
        if (i < 0) {
            return "";
        }
        return name.substring(i + 1);
    }

    public static String removeHtmlHeaders(String html) {
        if (html == null) {
            return null;
        }
        return html.replace("<html>", "").replace("</html>", "").replace("<head>", "").replace("</head>", "").replace("<body>", "").replace("</body>", "");
    }

    public static String removeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replace("\\<.*?\\>", "");
    }

    public static void openURL(File file) {
        try {
            openURL(file.toURL());
        } catch (MalformedURLException ex) {
            throw new SystemException("URL inválida", ex);
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
                    throw new Exception("Could not find web browser");
                } else {
                    Runtime.getRuntime().exec(new String[]{browser, url.toString()});
                }
            }
        } catch (Exception e) {
            throw new SystemException("não foi possível abrir o navegador de internet", e);
        }
    }

    public static String replaceGroup(String s, Pattern pattern, Object[] values) {
        Matcher m = pattern.matcher(s);
        m.find();
        StringBuilder sb = new StringBuilder();
        int last = 0;
        for (int i = 1; i <= m.groupCount() && i <= values.length; i++) {
            if (m.start(i) > -1 && values[i - 1] != null) {
                sb.append(s.substring(last, m.start(i)));
                sb.append(values[i - 1]);
                last = m.end(i);
            }
        }
        sb.append(s.substring(last));
        return sb.toString();
    }

    public static double roundNumber(double value, int precision) {
        double multiplicador = Math.pow(10, precision);
        return Math.round(value * multiplicador) / multiplicador;
    }

    public static Map createMap(Object... args) {
        if (args == null) {
            return new HashMap();
        }
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("createMap: Deve ter um número par de argumentos");
        }
        Map<Object, Object> m = new HashMap<Object, Object>();
        for (int i = 0; i < args.length; i += 2) {
            m.put(args[i], args[i + 1]);
        }
        return m;
    }

    public static String getValorDecimalComPrecisao(BigDecimal valor, int precisao) {
        if (valor == null) {
            return "";
        }
        return getValorDecimalComPrecisao(valor.floatValue(), precisao);
    }

    public static String getValorDecimalComPrecisao(Float valor, int precisao) {
        if (valor == null) {
            return "";
        } else {
            return getValorDecimalComPrecisao(valor.floatValue(), precisao);
        }
    }

    public static String getValorDecimalComPrecisao(float valor, int precisao) {
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        nf.setMaximumFractionDigits(precisao);
        nf.setMinimumFractionDigits(precisao);

        return nf.format(valor);
    }

    public static String getValorPorcentagemZeroACem(Float valor, int precisao) {
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        nf.setMaximumFractionDigits(precisao);
        nf.setMinimumFractionDigits(precisao);
        valor = valor * 100F;

        return nf.format(valor);
    }

    public static void sort(List lista, String propriedade) {
        sort(lista, propriedade, false);
    }

    public static void sort(List lista, String propriedade, boolean desc) {
        final BeanProperty<Object, Object> prop = BeanProperty.create(propriedade);
        final int index = desc ? -1 : 1;
        Collections.sort(lista, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Comparable v1 = (Comparable) prop.getValue(o1);
                Comparable v2 = (Comparable) prop.getValue(o2);
                if (v1 == null && v2 != null) {
                    return 1 * index;
                }
                if (v1 != null && v2 == null) {
                    return -1 * index;
                }
                if (v1 == null && v2 == null) {
                    return 0;
                }
                if (v1 instanceof String && v2 instanceof String) {
                    return ((String) v1).compareToIgnoreCase((String) v2) * index;
                }

                return v1 != null ? v1.compareTo(v2) * index : 0;
            }
        });
    }

    public static String removeAcentos(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }

    public static String converterParaHtml(String texto) {

        String textoHtml = texto;
        for (int i = 0; i < texto.length(); i++) {

            int ascii = texto.charAt(i);
            if (ascii > 125) {
                String valorAntigo = texto.substring(i, i + 1);
                textoHtml = textoHtml.replace(valorAntigo, "&#" + ascii + ";");
            }
        }
        textoHtml = textoHtml.replaceAll("\\n", "<br>");
        return textoHtml;
    }

    public static boolean validarEmail(String email) {
        boolean isEmailValid = false;

        if (email != null && email.length() > 0) {
            String expression = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailValid = true;
            }
        }

        return isEmailValid;
    }

    public static String stackTraceToString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String formatMask(String pattern, Object value) {

        if (value == null) {
            return null;
        }

        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public static Collection obterItemsDaListaComValorEspecifico(Collection collection, String propertyName, Object propertyValue) {
        BeanPropertyValueEqualsPredicate predicate = new BeanPropertyValueEqualsPredicate(propertyName, propertyValue);
        return CollectionUtils.select(collection, predicate);
    }

    public static ResourceBundle getResourceMessages() {
        return ResourceBundle.getBundle("sistam-messages");
    }

    /**
     * Procura por argumentos no formato A=1&B=2 e cria systems properties para
     * eles.
     *
     * @param args
     */
    public static void transformarArgumentosEmSystemProperties(String[] args) {
        System.out.println(Arrays.asList(args));
        for (String arg : args) {
            if (arg.contains("=")) {
                String[] arrayArgs = arg.split("&");
                for (String arrayArg : arrayArgs) {
                    String[] params = arrayArg.split("=");
                    System.setProperty(params[0], params[1]);
                    System.out.println("Propriedade: " + params[0] + " = " + params[1]);
                }
            }
        }
    }

    public static String transformarValor(double valor) {

        try {

            return valorPorExtenso(valor);

        } catch (InputMismatchException e) {
            return ("\nErro: valor informado incompatível.\n");
        }
    }

    ;

  public static String valorPorExtenso(double vlr) {
        if (vlr == 0) {
            return ("zero");
        }

        long inteiro = (long) Math.abs(vlr); // parte inteira do valor
        double resto = vlr - inteiro;       // parte fracionÃ¡ria do valor

        String vlrS = String.valueOf(inteiro);
        if (vlrS.length() > 15) {
            return ("Erro: valor superior a 999 trilhões.");
        }

        String s = "", saux, vlrP;
        String centavos = String.valueOf((int) Math.round(resto * 100));

        String[] unidade = {"", "um", "dois", "três", "quatro", "cinco",
            "seis", "sete", "oito", "nove", "dez", "onze",
            "doze", "treze", "quatorze", "quinze", "dezesseis",
            "dezessete", "dezoito", "dezenove"};
        String[] centena = {"", "cento", "duzentos", "trezentos",
            "quatrocentos", "quinhentos", "seiscentos",
            "setecentos", "oitocentos", "novecentos"};
        String[] dezena = {"", "", "vinte", "trinta", "quarenta", "cinquenta",
            "sessenta", "setenta", "oitenta", "noventa"};
        String[] qualificaS = {"", "mil", "milhão", "bilhão", "trilhão"};
        String[] qualificaP = {"", "mil", "milhões", "bilhões", "trilhões"};

// definindo o extenso da parte inteira do valor
        int n, unid, dez, cent, tam, i = 0;
        boolean umReal = false, tem = false;
        while (!vlrS.equals("0")) {
            tam = vlrS.length();
// retira do valor a 1a. parte, 2a. parte, por exemplo, para 123456789:
// 1a. parte = 789 (centena)
// 2a. parte = 456 (mil)
// 3a. parte = 123 (milhões)
            if (tam > 3) {
                vlrP = vlrS.substring(tam - 3, tam);
                vlrS = vlrS.substring(0, tam - 3);
            } else { // Ãºltima parte do valor
                vlrP = vlrS;
                vlrS = "0";
            }
            if (!vlrP.equals("000")) {
                saux = "";
                if (vlrP.equals("100")) {
                    saux = "cem";
                } else {
                    n = Integer.parseInt(vlrP, 10);  // para n = 371, tem-se:
                    cent = n / 100;                  // cent = 3 (centena trezentos)
                    dez = (n % 100) / 10;            // dez  = 7 (dezena setenta)
                    unid = (n % 100) % 10;           // unid = 1 (unidade um)
                    if (cent != 0) {
                        saux = centena[cent];
                    }
                    if ((dez != 0) || (unid != 0)) {
                        if ((n % 100) <= 19) {
                            if (saux.length() != 0) {
                                saux = saux + " e " + unidade[n % 100];
                            } else {
                                saux = unidade[n % 100];
                            }
                        } else {
                            if (saux.length() != 0) {
                                saux = saux + " e " + dezena[dez];
                            } else {
                                saux = dezena[dez];
                            }
                            if (unid != 0) {
                                if (saux.length() != 0) {
                                    saux = saux + " e " + unidade[unid];
                                } else {
                                    saux = unidade[unid];
                                }
                            }
                        }
                    }
                }
                if (vlrP.equals("1") || vlrP.equals("001")) {
                    if (i == 0) // 1a. parte do valor (um real)
                    {
                        umReal = true;
                    } else {
                        saux = saux + " " + qualificaS[i];
                    }
                } else if (i != 0) {
                    saux = saux + " " + qualificaP[i];
                }
                if (s.length() != 0) {
                    s = saux + ", " + s;
                } else {
                    s = saux;
                }
            }
            if (((i == 0) || (i == 1)) && s.length() != 0) {
                tem = true; // tem centena ou mil no valor
            }
            i = i + 1; // prÃ³ximo qualificador: 1- mil, 2- milhão, 3- bilhão, ...
        }

        if (s.length() != 0) {
            if (umReal) {
                s = s + " real";
            } else if (tem) {
                s = s + " reais";
            } else {
                s = s + " de reais";
            }
        }

// definindo o extenso dos centavos do valor
        if (!centavos.equals("0")) { // valor com centavos
            if (s.length() != 0) // se não Ã© valor somente com centavos
            {
                s = s + " e ";
            }
            if (centavos.equals("1")) {
                s = s + "um centavo";
            } else {
                n = Integer.parseInt(centavos, 10);
                if (n <= 19) {
                    s = s + unidade[n];
                } else {             // para n = 37, tem-se:
                    unid = n % 10;   // unid = 37 % 10 = 7 (unidade sete)
                    dez = n / 10;    // dez  = 37 / 10 = 3 (dezena trinta)
                    s = s + dezena[dez];
                    if (unid != 0) {
                        s = s + " e " + unidade[unid];
                    }
                }
                s = s + " centavos";
            }
        }
        return (Maiuscula(s));
    }

    public static String Maiuscula(String minuscula) {
        String posicao = "";
        String mais = "";
        String maiuscula2 = "";
        String tudo = "";

        posicao = "" + minuscula.charAt(0);//pega a primeira letra que sera maiuscula  
        String pos = posicao.toUpperCase();    //transforma em maiuscula  

        for (int i = 1; i < minuscula.length(); i++) {  //coloca o for de acordo com o tamanho  
            mais = mais + minuscula.charAt(i); //acrescenta as letras  
        }

        tudo = pos + mais;
        return tudo;

    }
}
