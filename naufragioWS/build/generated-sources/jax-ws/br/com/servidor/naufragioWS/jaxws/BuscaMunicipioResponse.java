
package br.com.servidor.naufragioWS.jaxws;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import br.com.valueobjects.NaufragioVO;

@XmlRootElement(name = "inserirUsuarioResponse", namespace = "http://naufragioWS.servidor.com.br/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserirUsuarioResponse", namespace = "http://naufragioWS.servidor.com.br/")
public class BuscaMunicipioResponse {

    @XmlElement(name = "return", namespace = "")
    private ArrayList<NaufragioVO> _return;

    /**
     * 
     * @return
     *     returns ArrayList<NaufragioVO>
     */
    public ArrayList<NaufragioVO> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(ArrayList<NaufragioVO> _return) {
        this._return = _return;
    }

}
