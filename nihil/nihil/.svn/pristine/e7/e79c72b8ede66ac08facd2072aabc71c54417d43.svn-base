/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spassu.nihil.rest.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import java.util.TimeZone;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 *
 * @author ADM
 */
public class NihilJacksonFeature implements Feature {
    
    /**
     * This method is what actually gets called, when your ResourceConfig
     * registers a Feature.
     * @param context
     * @return 
     */
    @Override
    public boolean configure(FeatureContext context) {

        if (!context.getConfiguration().isRegistered(NihilJacksonJaxbJsonProvider.class)) {
            context.register(NihilJacksonJaxbJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);
        }
        return true;
    }

    public static class NihilJacksonJaxbJsonProvider extends JacksonJaxbJsonProvider {

        public NihilJacksonJaxbJsonProvider() {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule testModule = new SimpleModule("NihilModule", new Version(1, 0, 0, null, null, null));
            // Inclusao dos Custom Deserializer para Enums
//            testModule.addDeserializer(SituacaoFolgaPermuta.class, new EnumDeserializer(ReflectionUtils.findMethod(SituacaoFolgaPermuta.class, "from", String.class)));
            mapper.registerModule(testModule);
            //mapper.registerModule(new Hibernate3Module());
            mapper.setTimeZone(TimeZone.getDefault());
            setMapper(mapper);
        }

    }
    
}
