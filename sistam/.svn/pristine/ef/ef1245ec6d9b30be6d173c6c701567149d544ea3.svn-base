package br.com.petrobras.sistam.desktop.porto;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.AgenciaPorto;
import br.com.petrobras.sistam.common.valueobjects.SistamCredentialsBean;
import br.com.petrobras.sistam.desktop.SistamApp;
import java.util.List;

public class DetalhesPortoModel extends BasePresentationModel<SistamService> {
    private AgenciaPorto porto;
    private List<Agencia> listaAgencia;
    public String municipio;
    public String estado;
    private boolean salvo = false;
        
    public DetalhesPortoModel(AgenciaPorto porto){
        this.porto = porto;
        
        if (porto.getId() != null){
            String[] municipioEstado = porto.getMunicipio().split("/");
            municipio = municipioEstado[0];
            estado = municipioEstado[1];
        }
        
        SistamCredentialsBean contexto = (SistamCredentialsBean) (SistamApp.getApplication().getCredentialsBean());
        listaAgencia = contexto.getAgenciasAutorizadas();
        listaAgencia.add(0, null);
    }
    
    public List<Agencia> getListaAgencia(){
        return this.listaAgencia;
    }

    public AgenciaPorto getPorto() {
        return porto;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public boolean estaSalvo(){
        return this.salvo;
    }   
    
    public void salvar(){
        porto.setMunicipio(obterMunicipioEstado());
        porto = getService().salvarAgenciaPorto(porto);
        salvo = true;   
    }
    
    private String obterMunicipioEstado(){
        String municipioEstado = "";
        
        municipioEstado = municipio != null ? municipio : "";
        municipioEstado += "/";
        municipioEstado += estado != null ? estado : "";
        
        return municipioEstado;
    }
}