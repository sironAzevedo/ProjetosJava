package br.com.petrobras.sistam.desktop.agenciamento;

import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.policiaFederal.FormularioControleFiscalizacaoUnicoDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.policiaFederal.FormularioPasseEntradaPoliciaFederalDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.policiaFederal.FormularioPasseSaidaPoliciaFederalDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.policiaFederal.FormularioPedidoVisitaPoliciaFederalDialog;
import br.com.petrobras.sistam.desktop.agenciamento.formularios.policiaFederal.FormularioRequerimentoPasseSaidaPoliciaFederalDialog;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;

/**
 * Classe MenuComunicacaoCaptania
 */
public class MenuPoliciaFederal extends JPopupMenu{
    public static final String PROP_ITEM_MENU_CLICADO = "PROP_ITEM_MENU_CLICADO";
    Agenciamento agenciamento;

    public MenuPoliciaFederal(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        initComponents(); 
        
        if(agenciamento.getAgencia().getNome().equals("SALVADOR"))  {
            add(actionPasseEntrada);  
            add(actionPasseSaida);  
            add(actionRequerimentoPasseSaida);  
//            add(actionPedidoVisita); 
          }else{
            add(actionFormularioControleFiscalizacaoUnico); 
          } 
         
    }

    //<editor-fold defaultstate="collapsed" desc="Emissão do Formulário">
    public void emitirFormularioControleFiscalizacaoUnico(){
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarFormularioControleFiscalizacaoUnico();
        FormularioControleFiscalizacaoUnicoDialog dialog = new FormularioControleFiscalizacaoUnicoDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);             
    } 
    
    public void emitirFormularioPasseDeEntrada(){
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarFormularioPasseDeEntrada();
        FormularioPasseEntradaPoliciaFederalDialog dialog = new FormularioPasseEntradaPoliciaFederalDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }
    
    public void emitirFormularioPasseDeSaida(){
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarFormularioPasseDeSaida();
        FormularioPasseSaidaPoliciaFederalDialog dialog = new FormularioPasseSaidaPoliciaFederalDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);  
    }
    
    public void emitirFormularioRequerimentoPasseSaida(){
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null);
        validarFormularioRequerimentoPasseSaida();
        FormularioRequerimentoPasseSaidaPoliciaFederalDialog dialog = new FormularioRequerimentoPasseSaidaPoliciaFederalDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }
    
    public void emitirFormularioPedidoVisita(){
        firePropertyChange(PROP_ITEM_MENU_CLICADO, null, null); 
        validarFormularioPedidoVisita();
        FormularioPedidoVisitaPoliciaFederalDialog dialog = new FormularioPedidoVisitaPoliciaFederalDialog(SistamApp.getSistamApp().getMainFrame(), agenciamento);
        dialog.setVisible(true);
    }
    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Validação do Formulário">
    public void validarFormularioControleFiscalizacaoUnico() {
        SistamPendencyManager pm = new SistamPendencyManager(); 
        pm.assertNotNull(agenciamento.getAreaNavegacao()).orRegister(TipoExcecao.CONTROLE_FISCALIZACAO_UNICO, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_INFORME_AREA_NAVEGACAO_ENTRADA);
        pm.assertNotNull(agenciamento.getAreaNavegacaoSaida()).orRegister(TipoExcecao.CONTROLE_FISCALIZACAO_UNICO, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_INFORME_AREA_NAVEGACAO_SAIDA);
        pm.verifyAll();                                        
    } 
    
    public void validarFormularioPasseDeEntrada(){
        
    }
    
    public void validarFormularioPasseDeSaida(){
        
    }
    
    public void validarFormularioRequerimentoPasseSaida(){
        
    }
    
    public void validarFormularioPedidoVisita(){
        
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="MENU FOURMULÁRIO POLÍCIA FEDERAL">
    public void menuFormularioParaAgenciaDeSalvador(){
        actionPasseEntrada.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionPasseEntrada.setMethodName("emitirFormularioPasseDeEntrada");
        actionPasseEntrada.setTarget(this);
        actionPasseEntrada.setText("Passe de Entrada");        

        actionPasseSaida.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionPasseSaida.setMethodName("emitirFormularioPasseDeSaida");
        actionPasseSaida.setTarget(this);
        actionPasseSaida.setText("Passe de Saída"); 

        actionRequerimentoPasseSaida.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionRequerimentoPasseSaida.setMethodName("emitirFormularioRequerimentoPasseSaida");
        actionRequerimentoPasseSaida.setTarget(this);
        actionRequerimentoPasseSaida.setText("Requerimento do Passe de Saída");  

//        actionPedidoVisita.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
//        actionPedidoVisita.setMethodName("emitirFormularioPedidoVisita");
//        actionPedidoVisita.setTarget(this);
//        actionPedidoVisita.setText("Pedido de Visita");
    } 
    
    public void menuFormularioParaAgencias(){
        actionFormularioControleFiscalizacaoUnico.setIcon(new ImageIcon(getClass().getResource("/icons/props.png"))); // NOI18N
        actionFormularioControleFiscalizacaoUnico.setMethodName("emitirFormularioControleFiscalizacaoUnico");
        actionFormularioControleFiscalizacaoUnico.setTarget(this);
        actionFormularioControleFiscalizacaoUnico.setText("Controle de Fiscalização Único");         
    }
    
    //</editor-fold> 
      
    private void initComponents(){
        actionFormularioControleFiscalizacaoUnico = new GenericAction(); 
        actionPasseEntrada = new GenericAction();
        actionPasseSaida = new GenericAction();
        actionRequerimentoPasseSaida = new GenericAction();
//        actionPedidoVisita = new GenericAction();
        
        menuFormularioParaAgenciaDeSalvador(); 
        menuFormularioParaAgencias(); 
    }  
    
    GenericAction actionFormularioControleFiscalizacaoUnico;
    GenericAction actionPasseEntrada;
    GenericAction actionPasseSaida;
    GenericAction actionRequerimentoPasseSaida;
//    GenericAction actionPedidoVisita;
    
    
}
