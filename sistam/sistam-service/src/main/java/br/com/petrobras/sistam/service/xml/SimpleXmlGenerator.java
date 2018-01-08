package br.com.petrobras.sistam.service.xml;

import br.com.petrobras.sistam.common.valueobjects.SerializableXml;
import java.io.ByteArrayOutputStream;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.stereotype.Component;

@Component
public class SimpleXmlGenerator implements XmlGenerator {

    @Override
    public byte[] generate(SerializableXml target) {
        Serializer serializer = new Persister();
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        try {
            serializer.write(target, result);        
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result.toByteArray();
    }
    
}
