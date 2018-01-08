package br.com.spassu.nihil.service.tester;

import br.com.spassu.nihil.common.entity.Target;
import br.com.spassu.nihil.common.enums.StatusTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpTargetTester implements TargetTester {

    private static final int timeout = 10000;
    
    private Logger logger = LoggerFactory.getLogger(getClass());    
    
    @Override
    public StatusTarget executeTest(Target target) {
        boolean result;
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 " + target.getNome());
            int returnVal = p1.waitFor();
            result = (returnVal==0);
        } catch (Exception e) {
            logger.error("Erro ao testar IP: " + target.getNome(), e);
            result = false;
        }
        return result ? StatusTarget.ONLINE : StatusTarget.OFFLINE;
        
    }    
}
