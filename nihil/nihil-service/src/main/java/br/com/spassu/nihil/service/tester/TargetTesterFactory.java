/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.service.tester;

import br.com.spassu.nihil.common.entity.Target;
import br.com.spassu.nihil.common.enums.TipoTarget;
import org.springframework.stereotype.Component;

@Component
public class TargetTesterFactory {
    
    public TargetTester createTester(Target target) {
        if (TipoTarget.URL.equals(target.getTipo())) {
            return new UrlTargetTester();
        } else if (TipoTarget.IP.equals(target.getTipo())) {
            return new IpTargetTester();
        } else {
            throw new RuntimeException("Tester não encontrado para este tipo de target");
        }
    }
    
}
