package br.com.spassu.nihil.service.tester;

import br.com.spassu.nihil.common.entity.Target;
import br.com.spassu.nihil.common.enums.StatusTarget;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlTargetTester implements TargetTester {

    private static final int timeout = 10000;
    
    @Override
    public StatusTarget executeTest(Target target) {
        String url = target.getNome().replaceFirst("^https", "http");
        boolean result;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            result = (200 <= responseCode && responseCode <= 399);
        } catch (IOException exception) {
            result = false;
        }
        
        return result ? StatusTarget.ONLINE : StatusTarget.OFFLINE;
    }
    
}
