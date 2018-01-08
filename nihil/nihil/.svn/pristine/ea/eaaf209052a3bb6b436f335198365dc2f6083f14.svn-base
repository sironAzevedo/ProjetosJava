/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LogServiceImpl {
    
    private @Value("${logpath}/nihil.log") String logpath;
    
    private Logger logger = LoggerFactory.getLogger(getClass());    
    
    public InputStream buscarLogApp() {
        InputStream is;
        try {
            File file = new File(logpath);
            is = new FileInputStream(file);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
        
        return is;
        
    }
    
}
