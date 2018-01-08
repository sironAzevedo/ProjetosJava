package br.com.petrobras.sistam.service.xml;

import br.com.petrobras.sistam.common.valueobjects.SerializableXml;

public interface XmlGenerator {
    
    public byte[] generate(SerializableXml target);

}
