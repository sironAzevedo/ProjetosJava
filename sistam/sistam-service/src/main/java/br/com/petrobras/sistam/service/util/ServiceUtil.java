package br.com.petrobras.sistam.service.util;

import java.net.URL;

public class ServiceUtil {

    public static URL getUrlDaLogoPetrobras() {
        return ServiceUtil.class.getResource("/icons/petrobras_rel.jpg");
    }

    public static URL getUrlDaLogoNP1() {
        return ServiceUtil.class.getResource("/icons/NP-1.png");
    }
}