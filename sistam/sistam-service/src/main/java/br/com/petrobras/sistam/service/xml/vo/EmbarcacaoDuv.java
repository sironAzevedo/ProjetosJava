package br.com.petrobras.sistam.service.xml.vo;

import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.sistam.common.valueobjects.SerializableXml;
import java.util.Locale;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name="embarcacaoDUV")
public class EmbarcacaoDuv implements SerializableXml{
    
    @Element(required=false)
    private CaladoEmbarcacao calado = new CaladoEmbarcacao();
    
    public void setCaladoEntradaPopa(Double caladoEntradaPopa) {
        calado.validarCalado(caladoEntradaPopa);
        calado.setCaladoEntradaPopa(String.format(new Locale("en"),"%.2f", caladoEntradaPopa));
    }

    public void setCaladoEntradaProa(Double caladoEntradaProa) {
        calado.validarCalado(caladoEntradaProa);
        calado.setCaladoEntradaProa(String.format(new Locale("en"),"%.2f", caladoEntradaProa));
    }

    public void setCaladoSaidaPopa(Double caladoSaidaPopa) {
        calado.validarCalado(caladoSaidaPopa);
        calado.setCaladoSaidaPopa(String.format(new Locale("en"),"%.2f", caladoSaidaPopa));
    }

    public void setCaladoSaidaProa(Double caladoSaidaProa) {
        calado.validarCalado(caladoSaidaProa);
        calado.setCaladoSaidaProa(String.format(new Locale("en"),"%.2f", caladoSaidaProa));
    }
}

@Root(name = "caladosEmbarcacao")
class CaladoEmbarcacao {
    private static final double MAX_CALADO = 99.99D;
    
    @Element(required=false)
    private String caladoEntradaPopa;
    
    @Element(required=false)
    private String caladoEntradaProa;
    
    @Element(required=false)
    private String caladoSaidaPopa;
    
    @Element(required=false)
    private String caladoSaidaProa;

    public void setCaladoEntradaPopa(String caladoEntradaPopa) {
        this.caladoEntradaPopa = caladoEntradaPopa;
    }

    public void setCaladoEntradaProa(String caladoEntradaProa) {
        this.caladoEntradaProa = caladoEntradaProa;
    }

    public void setCaladoSaidaPopa(String caladoSaidaPopa) {
        this.caladoSaidaPopa = caladoSaidaPopa;
    }

    public void setCaladoSaidaProa(String caladoSaidaProa) {
        this.caladoSaidaProa = caladoSaidaProa;
    }
    
    public void validarCalado(double calado){
        AssertUtils.assertExpression(calado <= CaladoEmbarcacao.MAX_CALADO, "O valor máximo para o calado é " + String.format("%.2f", CaladoEmbarcacao.MAX_CALADO));
    }
}

 
