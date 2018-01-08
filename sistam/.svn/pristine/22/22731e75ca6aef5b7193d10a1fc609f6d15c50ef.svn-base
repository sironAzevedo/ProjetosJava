package br.com.petrobras.sistam.test;

import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;

/**
 * Permite o uso de um arquivo CSS customizado nos HTMLs processados pelo Concordion.
 */
public class StyleExtension implements ConcordionExtension {

    @Override
    public void addTo(ConcordionExtender concordionExtender) {
        concordionExtender.withLinkedCSS("/css/specs.css", new Resource("/css/specs.css"));
        concordionExtender.withResource("/specs/index.html", new Resource("/index.html"));
    }
    
}
