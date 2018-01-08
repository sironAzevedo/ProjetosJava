/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.petrobras.sistam.common.enums.serializer;

import br.com.petrobras.sistam.common.enums.EnumSistamFinalidadeManobra;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 *
 * @author x4ij
 */
public class EnumSistamFinalidadeManobraSerializer extends JsonSerializer<EnumSistamFinalidadeManobra> {

    @Override
    public void serialize(EnumSistamFinalidadeManobra value, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
        
       // output the custom Json
        generator.writeStartObject();
        
        // the id
        generator.writeFieldName("id");
        generator.writeNumber(value.getId());

        // the porExtenso
        generator.writeFieldName("porExtenso");
        generator.writeString(value.getPorExtenso());
        
        // the StatusEmbarcacao
        generator.writeFieldName("statusEmbarcacao");
        generator.writeObject(value.statusEmbarcacao());
 
        generator.writeEndObject();
        
    }
    
}
