package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoTransporte;
import br.com.petrobras.sistam.common.valueobjects.PrevisualizacaoEmailVO;
import br.com.petrobras.sistam.desktop.util.DesktopUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnvioEmailServicoTransporteModel extends BasePresentationModel<SistamService> {
    
    private List<ResponsavelCusto> listaResponsavelCustos;
    private FiltroEnvioSolicitacaoTransporte filtro = new FiltroEnvioSolicitacaoTransporte();
    private boolean enviado = false;
    
    public EnvioEmailServicoTransporteModel(ServicoTransporte servicoTransporte) {
        filtro.setServicoTransporte(getService().buscarServicoTransportePorId(servicoTransporte.getId()));
        carregarCombos();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    
    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }
    
    public List<ResponsavelCusto> getListaResponsavelCustos() {
        return listaResponsavelCustos;
    }

    public void setListaResponsavelCustos(List<ResponsavelCusto> listaResponsavelCustos) {
        this.listaResponsavelCustos = listaResponsavelCustos;
    }
    
    
    public FiltroEnvioSolicitacaoTransporte getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroEnvioSolicitacaoTransporte filtro) {
        this.filtro = filtro;
        firePropertyChange("filtro", null, null);
    }
    
    //</editor-fold>
    
    public void carregarCombos(){
        List<ResponsavelCusto> lista = new ArrayList<ResponsavelCusto>();
        lista.add(0,null);
        lista.addAll(1, Arrays.asList(ResponsavelCusto.values()));
        
        setListaResponsavelCustos(lista);
    }
    
    public void enviar(){
        getService().enviarFormularioSolicitacaoTransporte(filtro);
    }
    
    public PrevisualizacaoEmailVO getPrevisualizar(){
        PrevisualizacaoEmailVO previsualizacaoEmailVO = new PrevisualizacaoEmailVO();
        previsualizacaoEmailVO.setCorpo(getService().montarCorpoEmailSolicitacaoTransporte(filtro));
        previsualizacaoEmailVO.setPara(new ArrayList(getService().destinatariosEmailSolicitacaoTransporte(filtro)));
        return previsualizacaoEmailVO;
    }
    
}