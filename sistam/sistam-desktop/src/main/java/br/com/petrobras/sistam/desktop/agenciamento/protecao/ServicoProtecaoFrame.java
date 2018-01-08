package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.SInternalFrame;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SLabel;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.STable;
import br.com.petrobras.fcorp.swing.components.activators.SActivator;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.ServicoAcessoPessoa;
import br.com.petrobras.sistam.common.entity.ServicoExecutado;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.entity.ServicoMedicoOdontologico;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.entity.ServicoSuprimento;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.enums.TipoAtendimentoServico;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.SistamFrame;
import br.com.petrobras.sistam.desktop.agenciamento.protecao.formularios.policiafederal.AcessoPessoaPoliciaFederalDialog;
import br.com.petrobras.sistam.desktop.agenciamento.protecao.formularios.receita.AcessoPessoaReceitaFederalDialog;
import br.com.petrobras.sistam.desktop.agenciamento.protecao.formularios.receita.FormulatorioReceitaDesEmbarqueTripulanteDialog;
import br.com.petrobras.sistam.desktop.agenciamento.protecao.formularios.receita.FormulatorioReceitaPrestacaoServicoDialog;
import br.com.petrobras.sistam.desktop.agenciamento.protecao.formularios.retiradaResiduoLixo.FormularioRetiradaResiduoLixoDialog;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

public class ServicoProtecaoFrame extends SInternalFrame {

    private ServicoProtecaoModel model;
    private JPopupMenu menuDocumentacao;
    private JPopupMenu menuFormulario;

    public ServicoProtecaoFrame() {
        this.model = new ServicoProtecaoModel();
        initComponents();
    }

    public ServicoProtecaoModel getModel() {
        return model;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        model.setAgenciamento(agenciamento);

        //Atualiza o título do frame.
        if (agenciamento != null) {
            setTitle("Serviço de Proteção - " + agenciamento.getNomeComposto());
        }

        //Atualizao o título da aba aberta
        for (SActivator activator : ((SistamFrame) SistamApp.getSistamApp().getMainFrame()).getBarraFramesAbertos().getActivators()) {
            if (this.equals(activator.getUserComponent())) {
                activator.updateDescriptionFromComponent();
            }
        }
    }

    public void cancelar() {
        ServicoExecutado servicoExecutado = model.getServicoExecutadoSelecionado();
        servicoExecutado.getServicoProtecao().setAgenciamento(model.getAgenciamento());
        CancelamentoDoServicoProtecaoDialog dialog = new CancelamentoDoServicoProtecaoDialog(SistamApp.getSistamApp().getMainFrame(), servicoExecutado);
        dialog.setVisible(true);
        model.carregarListaDeServicoExecutado();
        
    }

    public void exibirMenuServicoProtecao(ActionEvent evt) {
        menuDocumentacao = new JPopupMenu();

        menuDocumentacao.add(actionServicoProtecaoAcessoPessoa);;
        menuDocumentacao.add(actionServicoProtecaoMedicoOdontologico);
        menuDocumentacao.add(actionServicoProtecaoHospedagem);
        menuDocumentacao.add(actionServicoProtecaoTransporte);
        menuDocumentacao.add(actionServicoSuprimentosAosNavios);
        menuDocumentacao.add(actionServicoServicoResiduoLixo);
        menuDocumentacao.add(actionServicoProtecaoOutros);

        SButton button = (SButton) evt.getSource();
        menuDocumentacao.setVisible(true);
        menuDocumentacao.show(button.getParent(), button.getX() - 1, button.getY() - menuDocumentacao.getHeight() - 1);
    } 
    
    public void abrirServicoProtecaoOutros() {
        ServicoProtecao servicoProtecao = new ServicoProtecao();
        servicoProtecao.setAgenciamento(model.getAgenciamento());
        DetalheServicoProtecaoOutrosDialog dialog = new DetalheServicoProtecaoOutrosDialog(SistamApp.getSistamApp().getMainFrame(), servicoProtecao, true);
        dialog.setVisible(true);
        model.carregarListaDeServicoExecutado();
    }

    public void abrirServicoProtecaoTransporte() {
        ServicoTransporte servicoTransporte = new ServicoTransporte();
        ServicoProtecao servicoProtecao = new ServicoProtecao();
        servicoProtecao.setAgenciamento(model.getAgenciamento());
        servicoTransporte.setServicoProtecao(servicoProtecao);
        DetalheServicoProtecaoTransporteDialog dialog = new DetalheServicoProtecaoTransporteDialog(SistamApp.getSistamApp().getMainFrame(), servicoTransporte, true);
        dialog.setVisible(true);
        if (dialog.getModel().isGravado()) {
            model.carregarListaDeServicoExecutado();
        }
    }

    public void abrirServicoProtecaoMedicoOdontologico() {
        ServicoMedicoOdontologico servicoMedicoOdontologico = new ServicoMedicoOdontologico();
        ServicoProtecao servicoProtecao = new ServicoProtecao();
        servicoProtecao.setAgenciamento(model.getAgenciamento());
        servicoMedicoOdontologico.setServicoProtecao(servicoProtecao);
        DetalheServicoProtecaoMedicoOdontologicoDialog dialog = new DetalheServicoProtecaoMedicoOdontologicoDialog(SistamApp.getSistamApp().getMainFrame(), servicoMedicoOdontologico, true);
        dialog.setVisible(true);
        if (dialog.getModel().isGravado()) {
            model.carregarListaDeServicoExecutado();
        }
    }

    public void abrirServicoProtecaoHospedagem() {
        ServicoHospedagem servicoHospedagem = new ServicoHospedagem();
        ServicoProtecao servicoProtecao = new ServicoProtecao();
        servicoProtecao.setAgenciamento(model.getAgenciamento());
        servicoHospedagem.setServicoProtecao(servicoProtecao);
        DetalheServicoProtecaoHospedagemDialog dialog = new DetalheServicoProtecaoHospedagemDialog(SistamApp.getSistamApp().getMainFrame(), servicoHospedagem, true);
        dialog.setVisible(true);
        if (dialog.getModel().isGravado()) {
            model.carregarListaDeServicoExecutado();
        }
    }

    public void abrirServicoProtecaoAcessoPessoa() {
        ServicoAcessoPessoa servicoAcessoPessoa = new ServicoAcessoPessoa();
        ServicoProtecao servicoProtecao = new ServicoProtecao();
        servicoProtecao.setAgenciamento(model.getAgenciamento());
        servicoAcessoPessoa.setServicoProtecao(servicoProtecao);
        DetalheServicoProtecaoAcessoPessoaDialog dialog = new DetalheServicoProtecaoAcessoPessoaDialog(SistamApp.getSistamApp().getMainFrame(), servicoAcessoPessoa, true);
        dialog.setVisible(true);
        if (dialog.getModel().isGravado()) {
            model.carregarListaDeServicoExecutado();
        }
    }
    
    public void abrirServicoProtecaoSuprimento(){
        ServicoSuprimento servicoSuprimento = new ServicoSuprimento();
        ServicoProtecao servicoProtecao = new ServicoProtecao();
        servicoProtecao.setAgenciamento(model.getAgenciamento()); 
        servicoSuprimento.setServicoProtecao(servicoProtecao);
        DetalheServicoProtecaoSuprimentoAosNaviosDialog dialog = new DetalheServicoProtecaoSuprimentoAosNaviosDialog(SistamApp.getSistamApp().getMainFrame(), servicoSuprimento, true);
        dialog.setVisible(true);
        model.carregarListaDeServicoExecutado();
    }
    
    public void abrirServicoProtecaoRetiradaResiduoLixoDialog(){
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = new ServicoRetiradaResiduoLixo();
        ServicoProtecao servicoProtecao = new ServicoProtecao();
        servicoProtecao.setAgenciamento(model.getAgenciamento()); 
        servicoRetiradaResiduoLixo.setServicoProtecao(servicoProtecao);
        DetalheServicoProtecaoRetiradaResiduoLixoDialog dialog = new DetalheServicoProtecaoRetiradaResiduoLixoDialog(SistamApp.getSistamApp().getMainFrame(), servicoRetiradaResiduoLixo, true, false);
        dialog.setVisible(true);
        model.carregarListaDeServicoExecutado();
    }

    public void editarServicoExecutado() {
        editarServicoProtecaoMedicoOdontologico(true);
        editarServicoProtecaoTransporte(true);
        editarServicoProtecaoHospedagem(true);
        editarServicoProtecaoAcessoPessoa(true);
        editarServicoProtecaoSuprimento(true);
        editarServicoProtecaoRetiradaResiduoLixo(true, false);
        editarServicoProtecaoOutros(true);
    }

    public void consultarServicoExecutado() {
        editarServicoProtecaoMedicoOdontologico(false);
        editarServicoProtecaoTransporte(false);
        editarServicoProtecaoHospedagem(false);
        editarServicoProtecaoAcessoPessoa(false);
        editarServicoProtecaoSuprimento(false);
        editarServicoProtecaoRetiradaResiduoLixo(false, true);
        editarServicoProtecaoOutros(false);
    }

    public void editarServicoProtecaoOutros(boolean editar) {
        if (model.getServicoExecutadoSelecionado() == null || model.getServicoExecutadoSelecionado().getServicoProtecao() == null
                || model.getServicoExecutadoSelecionado().getServicoProtecao().getTipoAtendimentoServico() == null
                || model.getServicoExecutadoSelecionado().getServicoProtecao().getTipoAtendimentoServico().getImplementado()
                ) {
            return;
        }
        ServicoProtecao servicoProtecao = (ServicoProtecao) model.getServicoExecutadoSelecionado();
        servicoProtecao.setAgenciamento(model.getAgenciamento());
        DetalheServicoProtecaoOutrosDialog dialog = new DetalheServicoProtecaoOutrosDialog(SistamApp.getSistamApp().getMainFrame(), servicoProtecao, editar);
        dialog.setVisible(true);
        model.carregarListaDeServicoExecutado();
    }

    public void editarServicoProtecaoMedicoOdontologico(boolean editar) {
        if (model.getServicoExecutadoSelecionado() == null || model.getServicoExecutadoSelecionado().getServicoProtecao() == null
                || !TipoAtendimentoServico.MEDICO_ODONTOLOGICO.equals(model.getServicoExecutadoSelecionado().getServicoProtecao().getTipoAtendimentoServico())) {
            return;
        }
        ServicoMedicoOdontologico servicoMedicoOdontologico = (ServicoMedicoOdontologico) model.getServicoExecutadoSelecionado();
        servicoMedicoOdontologico.getServicoProtecao().setAgenciamento(model.getAgenciamento());
        DetalheServicoProtecaoMedicoOdontologicoDialog dialog = new DetalheServicoProtecaoMedicoOdontologicoDialog(SistamApp.getSistamApp().getMainFrame(), servicoMedicoOdontologico, editar);
        dialog.setVisible(true);
        if (dialog.getModel().isGravado()) {
            model.carregarListaDeServicoExecutado();
        }
    }

    public void editarServicoProtecaoTransporte(boolean editar) {
        if (model.getServicoExecutadoSelecionado() == null || model.getServicoExecutadoSelecionado().getServicoProtecao() == null
                || !model.getServicoExecutadoSelecionado().getServicoProtecao().isTipoTransporte()) {
            return;
        }
        ServicoTransporte servicoTransporte = (ServicoTransporte) model.getServicoExecutadoSelecionado();
        servicoTransporte.getServicoProtecao().setAgenciamento(model.getAgenciamento());
        DetalheServicoProtecaoTransporteDialog dialog = new DetalheServicoProtecaoTransporteDialog(SistamApp.getSistamApp().getMainFrame(), servicoTransporte, editar);
        dialog.setVisible(true);
        if (dialog.getModel().isGravado()) {
            model.carregarListaDeServicoExecutado();
        }
    }

    public void editarServicoProtecaoHospedagem(boolean editar) {
        if (model.getServicoExecutadoSelecionado() == null || model.getServicoExecutadoSelecionado().getServicoProtecao() == null
                || !model.getServicoExecutadoSelecionado().getServicoProtecao().isTipoHospedagem()) {
            return;
        }
        ServicoHospedagem servicoHospedagem = (ServicoHospedagem) model.getServicoExecutadoSelecionado();
        servicoHospedagem.getServicoProtecao().setAgenciamento(model.getAgenciamento());
        DetalheServicoProtecaoHospedagemDialog dialog = new DetalheServicoProtecaoHospedagemDialog(SistamApp.getSistamApp().getMainFrame(), servicoHospedagem, editar);
        dialog.setVisible(true);
        if (dialog.getModel().isGravado()) {
            model.carregarListaDeServicoExecutado();
        }
    }

    public void editarServicoProtecaoAcessoPessoa(boolean editar) {
        if (model.getServicoExecutadoSelecionado() == null || model.getServicoExecutadoSelecionado().getServicoProtecao() == null
                || !model.getServicoExecutadoSelecionado().getServicoProtecao().isTipoAcessoPessoa()) {
            return;
        }
        ServicoAcessoPessoa servicoAcessoPessoa = model.obterServicoAcessoPessoaParaEdicao();
        DetalheServicoProtecaoAcessoPessoaDialog dialog = new DetalheServicoProtecaoAcessoPessoaDialog(SistamApp.getSistamApp().getMainFrame(), servicoAcessoPessoa, editar);
        dialog.setVisible(true);
        if (dialog.getModel().isGravado()) {
            model.carregarListaDeServicoExecutado();
        }
    }
    
    public void editarServicoProtecaoSuprimento(boolean editar) {
        if (model.getServicoExecutadoSelecionado() == null || model.getServicoExecutadoSelecionado().getServicoProtecao() == null
                || !model.getServicoExecutadoSelecionado().getServicoProtecao().isTipoServicoSuprimento()) {
            return;
        }
        ServicoSuprimento servicoSuprimento = (ServicoSuprimento) model.obterServicoSuprimentoParaEdicao();
        DetalheServicoProtecaoSuprimentoAosNaviosDialog dialog = new DetalheServicoProtecaoSuprimentoAosNaviosDialog(SistamApp.getSistamApp().getMainFrame(), servicoSuprimento, editar);
        dialog.setVisible(true);
        if (dialog.getModel().isGravado()) {
            model.carregarListaDeServicoExecutado();
        }
    }
    
    public void editarServicoProtecaoRetiradaResiduoLixo(boolean editar, boolean consultar) {
        if (model.getServicoExecutadoSelecionado() == null || model.getServicoExecutadoSelecionado().getServicoProtecao() == null
                || !model.getServicoExecutadoSelecionado().getServicoProtecao().isTipoServicoRetiradaResiduoLixo()) {
            return;
        }
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = (ServicoRetiradaResiduoLixo) model.obterServicoRetiradaResiduoLixoParaEdicao();
        DetalheServicoProtecaoRetiradaResiduoLixoDialog dialog = new DetalheServicoProtecaoRetiradaResiduoLixoDialog(SistamApp.getSistamApp().getMainFrame(), servicoRetiradaResiduoLixo, editar, consultar);
        dialog.setVisible(true);
        if (dialog.getModel().isGravado()) {
            model.carregarListaDeServicoExecutado();
        }
    }

    public void excluirServicoExecutado() {
        model.excluir();
        model.carregarListaDeServicoExecutado();
    }

    public void emitirFormulario() {
        emitirFormularioTransporte();
    }

    public void enviarEmail() {
        enviarEmailTransporte();
        enviarEmailHospedagem();
        enviarEmailRetiradaResiduoLixo();
    }

    public void emitirFormularioTransporte() {
        if (model.getServicoExecutadoSelecionado() == null || model.getServicoExecutadoSelecionado().getServicoProtecao() == null
                || !model.getServicoExecutadoSelecionado().getServicoProtecao().isTipoTransporte()) {
            return;
        }
        model.emitirFormularioTransporte();
    }

    public void enviarEmailTransporte() {
        if (model.getServicoExecutadoSelecionado() == null || model.getServicoExecutadoSelecionado().getServicoProtecao() == null
                || !model.getServicoExecutadoSelecionado().getServicoProtecao().isTipoTransporte()) {
            return;
        }
        ServicoTransporte servicoTransporte = (ServicoTransporte) model.getServicoExecutadoSelecionado();
        servicoTransporte.getServicoProtecao().setAgenciamento(model.getAgenciamento());

        EnvioEmailServicoTransporteDialog dialog = new EnvioEmailServicoTransporteDialog(SistamApp.getSistamApp().getMainFrame(), servicoTransporte);
        dialog.setVisible(true);
        
        if(dialog.getModel().isEnviado())
            model.carregarListaDeServicoExecutado();
    }

    public void enviarEmailHospedagem() {
        if (model.getServicoExecutadoSelecionado() == null || model.getServicoExecutadoSelecionado().getServicoProtecao() == null
                || !model.getServicoExecutadoSelecionado().getServicoProtecao().isTipoHospedagem()) {
            return;

        }
        ServicoHospedagem servicoHospedagem = (ServicoHospedagem) model.getServicoExecutadoSelecionado();
        servicoHospedagem.getServicoProtecao().setAgenciamento(model.getAgenciamento());

        EnvioEmailServicoHospedagemDialog dialog = new EnvioEmailServicoHospedagemDialog(SistamApp.getSistamApp().getMainFrame(), servicoHospedagem);
        dialog.setVisible(true);
        
        if(dialog.getModel().isEnviado())
            model.carregarListaDeServicoExecutado();
    }
    
    public void enviarEmailRetiradaResiduoLixo(){
        if (model.getServicoExecutadoSelecionado() == null || model.getServicoExecutadoSelecionado().getServicoProtecao() == null
                || !model.getServicoExecutadoSelecionado().getServicoProtecao().isTipoServicoRetiradaResiduoLixo()) {
            return;

        }
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = (ServicoRetiradaResiduoLixo) model.getServicoExecutadoSelecionado();
        servicoRetiradaResiduoLixo.getServicoProtecao().setAgenciamento(model.getAgenciamento());

        EnvioEmailServicoRetiradaResiduoLixoDialog dialog = new EnvioEmailServicoRetiradaResiduoLixoDialog(SistamApp.getSistamApp().getMainFrame(), servicoRetiradaResiduoLixo);
        dialog.setVisible(true);
        
        if(dialog.getModel().isEnviado())
            model.carregarListaDeServicoExecutado();
    }

    public void exibirMenuFormulario(ActionEvent evt) {
        exibirMenuFormularioAcessoPessoa(evt);
        exibirMenuFormularioRetiradaResiduoLixo(evt);
    }

    public void exibirMenuFormularioAcessoPessoa(ActionEvent evt) {
        if (model.getServicoExecutadoSelecionado() == null || model.getServicoExecutadoSelecionado().getServicoProtecao() == null
                || !model.getServicoExecutadoSelecionado().getServicoProtecao().isTipoAcessoPessoa()) {
            return;

        }
        menuFormulario = new JPopupMenu();

        menuFormulario.add(actionFormularioPoliciaFederalAcessoPessoa);
        
        menuFormulario.add(actionFormularioReceitaFederalAcessoPessoa);
        menuFormulario.add(actionFormularioReceitaFederalDesEmbarqueTripulantes);
        menuFormulario.add(actionFormularioReceitaFederalPrestacaoServico);

        SButton button = (SButton) evt.getSource();
        menuFormulario.setVisible(true);
        menuFormulario.show(button.getParent(), button.getX() - 1, button.getY() - menuFormulario.getHeight() - 1);
    }
    
    public void exibirMenuFormularioRetiradaResiduoLixo(ActionEvent evt) {
        if (model.getServicoExecutadoSelecionado() == null || model.getServicoExecutadoSelecionado().getServicoProtecao() == null
                || !model.getServicoExecutadoSelecionado().getServicoProtecao().isTipoServicoRetiradaResiduoLixo()) {
            return;

        }
        menuFormulario = new JPopupMenu(); 
        menuFormulario.add(actionFormularioRetiradaResiduoLixo);

        SButton button = (SButton) evt.getSource();
        menuFormulario.setVisible(true);
        menuFormulario.show(button.getParent(), button.getX() - 1, button.getY() - menuFormulario.getHeight() - 1);
    }

    public void formularioPoliciaFederal() {
        ServicoAcessoPessoa servicoAcessoPessoa = (ServicoAcessoPessoa) model.getServicoExecutadoSelecionado();
        servicoAcessoPessoa.getServicoProtecao().setAgenciamento(model.getAgenciamento());
        AcessoPessoaPoliciaFederalDialog dialog = new AcessoPessoaPoliciaFederalDialog(SistamApp.getSistamApp().getMainFrame(), servicoAcessoPessoa);
        dialog.setVisible(true);
    }

    public void formularioReceita() {
        ServicoAcessoPessoa servicoAcessoPessoa = (ServicoAcessoPessoa) model.getServicoExecutadoSelecionado();
        servicoAcessoPessoa.getServicoProtecao().setAgenciamento(model.getAgenciamento());
        AcessoPessoaReceitaFederalDialog dialog = new AcessoPessoaReceitaFederalDialog(SistamApp.getSistamApp().getMainFrame(), servicoAcessoPessoa);
        dialog.setVisible(true);
    }

    public void formularioReceitaDesEmbarqueTripulantes(){
        ServicoAcessoPessoa servicoAcessoPessoa = (ServicoAcessoPessoa) model.getServicoExecutadoSelecionado();
        servicoAcessoPessoa.getServicoProtecao().setAgenciamento(model.getAgenciamento());
        FormulatorioReceitaDesEmbarqueTripulanteDialog dialog = new FormulatorioReceitaDesEmbarqueTripulanteDialog(SistamApp.getSistamApp().getMainFrame(), servicoAcessoPessoa);
        dialog.setVisible(true);
    }
    
    public void formularioReceitaPrestacaoServico(){
        ServicoAcessoPessoa servicoAcessoPessoa = (ServicoAcessoPessoa) model.getServicoExecutadoSelecionado();
        servicoAcessoPessoa.getServicoProtecao().setAgenciamento(model.getAgenciamento());
        FormulatorioReceitaPrestacaoServicoDialog dialog = new FormulatorioReceitaPrestacaoServicoDialog(SistamApp.getSistamApp().getMainFrame(), servicoAcessoPessoa);
        dialog.setVisible(true);
    }
    
    public void formularioRetiradaResiduoLixo(){
        
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = (ServicoRetiradaResiduoLixo) model.getServicoExecutadoSelecionado();
        servicoRetiradaResiduoLixo.getServicoProtecao().setAgenciamento(model.getAgenciamento());
        
        
        FormularioRetiradaResiduoLixoDialog dialog = new FormularioRetiradaResiduoLixoDialog(SistamApp.getSistamApp().getMainFrame(), servicoRetiradaResiduoLixo);
        dialog.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    @SuppressFBWarnings
    //CHECKSTYLE:OFF
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new BindingGroup();

        GenericAction actionEditar = new GenericAction();
        GenericAction actionAdicionar = new GenericAction();
        GenericAction actionExcluir = new GenericAction();
        GenericAction actionCancelar = new GenericAction();
        actionServicoProtecaoOutros = new GenericAction();
        actionServicoProtecaoTransporte = new GenericAction();
        actionServicoProtecaoMedicoOdontologico = new GenericAction();
        GenericAction actionConsultar = new GenericAction();
        GenericAction actionEnviarEmail = new GenericAction();
        actionServicoProtecaoHospedagem = new GenericAction();
        actionServicoProtecaoAcessoPessoa = new GenericAction();
        GenericAction actionFormulario = new GenericAction();
        actionFormularioPoliciaFederalAcessoPessoa = new GenericAction();
        actionFormularioReceitaFederalAcessoPessoa = new GenericAction();
        actionFormularioReceitaFederalDesEmbarqueTripulantes = new GenericAction();
        actionFormularioReceitaFederalPrestacaoServico = new GenericAction();
        actionServicoSuprimentosAosNavios = new GenericAction();
        actionServicoServicoResiduoLixo = new GenericAction();
        actionFormularioRetiradaResiduoLixo = new GenericAction();
        SPanel painelAgenciamento = new SPanel();
        SLabel sLabel7 = new SLabel();
        SLabel sLabel3 = new SLabel();
        SLabel sLabel8 = new SLabel();
        SLabel sLabel9 = new SLabel();
        SPanel pnlPrincipal = new SPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        STable sTable1 = new STable();
        SPanel pnlBotoes = new SPanel();
        SButton btnNovo = new SButton();
        SButton btnConsultar = new SButton();
        SButton btnEditar = new SButton();
        SButton btnExcluir = new SButton();
        SButton sButton1 = new SButton();
        btnEnviarEmail = new SButton();
        btnFormulario = new SButton();

        actionEditar.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditar.setMethodName("editarServicoExecutado");
        actionEditar.setTarget(this);
        actionEditar.setText("Editar");
        actionEditar.setToolTipText("Edita o serviço de proteção selecionado");

        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarEditar}"), actionEditar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionAdicionar.setIcon(new ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionar.setMethodName("exibirMenuServicoProtecao");
        actionAdicionar.setTarget(this);
        actionAdicionar.setText("Adicionar");
        actionAdicionar.setToolTipText("Adiciona um novo serviço de proteção");

        actionExcluir.setConfirm("Confirma a exclusão do serviço de proteção selecionado?");
        actionExcluir.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluir.setMethodName("excluirServicoExecutado");
        actionExcluir.setTarget(this);
        actionExcluir.setText("Excluir");
        actionExcluir.setToolTipText("Exclui o serviço de proteção selecionado");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarExcluir}"), actionExcluir, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionCancelar.setIcon(new ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        actionCancelar.setMethodName("cancelar");
        actionCancelar.setTarget(this);
        actionCancelar.setText("Cancelar");
        actionCancelar.setToolTipText("Cancela o serviço de proteção selecionado");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarCancelar}"), actionCancelar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionServicoProtecaoOutros.setMethodName("abrirServicoProtecaoOutros");
        actionServicoProtecaoOutros.setTarget(this);
        actionServicoProtecaoOutros.setText("Serviço Proteção - Outros");

        actionServicoProtecaoTransporte.setMethodName("abrirServicoProtecaoTransporte");
        actionServicoProtecaoTransporte.setTarget(this);
        actionServicoProtecaoTransporte.setText("Serviço de Transporte");

        actionServicoProtecaoMedicoOdontologico.setMethodName("abrirServicoProtecaoMedicoOdontologico");
        actionServicoProtecaoMedicoOdontologico.setTarget(this);
        actionServicoProtecaoMedicoOdontologico.setText("Serviço Médico Odontológico");

        actionConsultar.setIcon(new ImageIcon(getClass().getResource("/icons/view.png"))); // NOI18N
        actionConsultar.setMethodName("consultarServicoExecutado");
        actionConsultar.setTarget(this);
        actionConsultar.setText("Consultar");
        actionConsultar.setToolTipText("Consulta o serviço de proteção selecionado");

        actionEnviarEmail.setIcon(new ImageIcon(getClass().getResource("/icons/send.png"))); // NOI18N
        actionEnviarEmail.setMethodName("enviarEmail");
        actionEnviarEmail.setTarget(this);
        actionEnviarEmail.setText("Enviar E-mail");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarEnviarEmail}"), actionEnviarEmail, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionServicoProtecaoHospedagem.setMethodName("abrirServicoProtecaoHospedagem");
        actionServicoProtecaoHospedagem.setTarget(this);
        actionServicoProtecaoHospedagem.setText("Serviço de Hospedagem");

        actionServicoProtecaoAcessoPessoa.setMethodName("abrirServicoProtecaoAcessoPessoa");
        actionServicoProtecaoAcessoPessoa.setTarget(this);
        actionServicoProtecaoAcessoPessoa.setText("Serviço de Acesso a Pessoas");

        actionFormulario.setIcon(new ImageIcon(getClass().getResource("/icons/document.png"))); // NOI18N
        actionFormulario.setMethodName("exibirMenuFormulario");
        actionFormulario.setTarget(this);
        actionFormulario.setText("Formulário");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarFormulario}"), actionFormulario, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionFormularioPoliciaFederalAcessoPessoa.setIcon(new ImageIcon(getClass().getResource("/icons/policiafederal.png"))); // NOI18N
        actionFormularioPoliciaFederalAcessoPessoa.setMethodName("formularioPoliciaFederal");
        actionFormularioPoliciaFederalAcessoPessoa.setTarget(this);
        actionFormularioPoliciaFederalAcessoPessoa.setText("Polícia Federal");

        actionFormularioReceitaFederalAcessoPessoa.setIcon(new ImageIcon(getClass().getResource("/icons/receita16X16.png"))); // NOI18N
        actionFormularioReceitaFederalAcessoPessoa.setMethodName("formularioReceita");
        actionFormularioReceitaFederalAcessoPessoa.setTarget(this);
        actionFormularioReceitaFederalAcessoPessoa.setText("Receita");

        actionFormularioReceitaFederalDesEmbarqueTripulantes.setIcon(new ImageIcon(getClass().getResource("/icons/receita16X16.png"))); // NOI18N
        actionFormularioReceitaFederalDesEmbarqueTripulantes.setMethodName("formularioReceitaDesEmbarqueTripulantes");
        actionFormularioReceitaFederalDesEmbarqueTripulantes.setTarget(this);
        actionFormularioReceitaFederalDesEmbarqueTripulantes.setText("Pedido de Embarque e Desembarque de Tripulantes");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarFormularioReceitaDesEmbarqueTripulantes}"), actionFormularioReceitaFederalDesEmbarqueTripulantes, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionFormularioReceitaFederalPrestacaoServico.setIcon(new ImageIcon(getClass().getResource("/icons/receita16X16.png"))); // NOI18N
        actionFormularioReceitaFederalPrestacaoServico.setMethodName("formularioReceitaPrestacaoServico");
        actionFormularioReceitaFederalPrestacaoServico.setTarget(this);
        actionFormularioReceitaFederalPrestacaoServico.setText("Pedido para Prestação de Serviço");

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarFormularioReceitaPrestacaoServico}"), actionFormularioReceitaFederalPrestacaoServico, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionServicoSuprimentosAosNavios.setMethodName("abrirServicoProtecaoSuprimento");
        actionServicoSuprimentosAosNavios.setTarget(this);
        actionServicoSuprimentosAosNavios.setText("Serviço de Suprimento A Embarcação");
        actionServicoSuprimentosAosNavios.setToolTipText("Serviço de Suprimento A Embarcação");

        actionServicoServicoResiduoLixo.setMethodName("abrirServicoProtecaoRetiradaResiduoLixoDialog");
        actionServicoServicoResiduoLixo.setTarget(this);
        actionServicoServicoResiduoLixo.setText("Serviço de Retirada de Resíduo/Lixo");

        actionFormularioRetiradaResiduoLixo.setIcon(new ImageIcon(getClass().getResource("/icons/policiafederal.png"))); // NOI18N
        actionFormularioRetiradaResiduoLixo.setMethodName("formularioRetiradaResiduoLixo");
        actionFormularioRetiradaResiduoLixo.setTarget(this);
        actionFormularioRetiradaResiduoLixo.setText("Formulário Retirada de Resíduo/Lixo");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Serviço de Proteção");
        setFrameIcon(new ImageIcon(getClass().getResource("/icons/icon.jpg"))); // NOI18N

        painelAgenciamento.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        painelAgenciamento.setName("painelAgenciamento"); // NOI18N
        painelAgenciamento.setPreferredSize(new Dimension(838, 40));

        sLabel7.setText("Nome:");
        sLabel7.setName("sLabel7"); // NOI18N

        sLabel3.setName("sLabel3"); // NOI18N
        sLabel3.setRequiredField(true);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.agenciamento.embarcacao.nomeCompleto}"), sLabel3, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        sLabel8.setText("Viagem:");
        sLabel8.setName("sLabel8"); // NOI18N

        sLabel9.setName("sLabel9"); // NOI18N
        sLabel9.setRequiredField(true);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.agenciamento.vgm}"), sLabel9, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        GroupLayout painelAgenciamentoLayout = new GroupLayout(painelAgenciamento);
        painelAgenciamento.setLayout(painelAgenciamentoLayout);
        painelAgenciamentoLayout.setHorizontalGroup(
            painelAgenciamentoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelAgenciamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sLabel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(sLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(sLabel8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(sLabel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(441, 441, 441))
        );
        painelAgenciamentoLayout.setVerticalGroup(
            painelAgenciamentoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelAgenciamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelAgenciamentoLayout.createParallelGroup(Alignment.CENTER)
                    .addComponent(sLabel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel3, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sLabel9, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );

        pnlPrincipal.setBorder(BorderFactory.createTitledBorder("Lista de Serviços de Proteção"));
        pnlPrincipal.setName("pnlPrincipal"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        sTable1.setSortable(true);
        sTable1.setName("sTable1"); // NOI18N

        ELProperty eLProperty = ELProperty.create("${model.listaServicoExecutado}");
        JTableBinding jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, sTable1);
        JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${servicoProtecao.protocolo}"));
        columnBinding.setColumnName("Código");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${servicoProtecao.tipoAtendimentoServico.porExtenso}"));
        columnBinding.setColumnName("Serviço Executado");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${servicoProtecao.dataExecucao}"));
        columnBinding.setColumnName("Data");
        columnBinding.setColumnClass(Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${servicoProtecao.observacao}"));
        columnBinding.setColumnName("Observação");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${servicoProtecao.situacao}"));
        columnBinding.setColumnName("Situação");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${servicoProtecao.emailEnviado}"));
        columnBinding.setColumnName("E-mail Enviado");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${servicoProtecao.dataHoraEmailEnviadoFormatado}"));
        columnBinding.setColumnName("Data do E-mail Enviado");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.servicoExecutadoSelecionado}"), sTable1, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(sTable1);
        sTable1.getColumnModel().getColumn(0).setMinWidth(60);
        sTable1.getColumnModel().getColumn(0).setMaxWidth(70);
        sTable1.getColumnModel().getColumn(2).setMinWidth(60);
        sTable1.getColumnModel().getColumn(2).setMaxWidth(70);
        sTable1.getColumnModel().getColumn(4).setMinWidth(80);
        sTable1.getColumnModel().getColumn(4).setMaxWidth(100);
        sTable1.getColumnModel().getColumn(5).setMinWidth(100);
        sTable1.getColumnModel().getColumn(5).setMaxWidth(120);
        sTable1.getColumnModel().getColumn(6).setMinWidth(120);
        sTable1.getColumnModel().getColumn(6).setMaxWidth(150);

        GroupLayout pnlPrincipalLayout = new GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane1, Alignment.TRAILING)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlBotoes.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pnlBotoes.setName("pnlBotoes"); // NOI18N
        pnlBotoes.setPreferredSize(new Dimension(565, 40));

        btnNovo.setAction(actionAdicionar);
        btnNovo.setName("btnNovo"); // NOI18N
        pnlBotoes.add(btnNovo);

        btnConsultar.setAction(actionConsultar);
        btnConsultar.setName("btnConsultar"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.habilitarConsultar}"), btnConsultar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        pnlBotoes.add(btnConsultar);

        btnEditar.setAction(actionEditar);
        btnEditar.setName("btnEditar"); // NOI18N
        pnlBotoes.add(btnEditar);

        btnExcluir.setAction(actionExcluir);
        btnExcluir.setName("btnExcluir"); // NOI18N
        pnlBotoes.add(btnExcluir);

        sButton1.setAction(actionCancelar);
        sButton1.setName("sButton1"); // NOI18N
        pnlBotoes.add(sButton1);

        btnEnviarEmail.setAction(actionEnviarEmail);
        btnEnviarEmail.setName("btnEnviarEmail"); // NOI18N
        pnlBotoes.add(btnEnviarEmail);

        btnFormulario.setAction(actionFormulario);
        btnFormulario.setName("btnFormulario"); // NOI18N
        pnlBotoes.add(btnFormulario);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(painelAgenciamento, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBotoes, GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPrincipal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelAgenciamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnlPrincipal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(pnlBotoes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON
    // Variables declaration - do not modify//GEN-BEGIN:variables
    GenericAction actionFormularioPoliciaFederalAcessoPessoa;
    GenericAction actionFormularioReceitaFederalAcessoPessoa;
    GenericAction actionFormularioReceitaFederalDesEmbarqueTripulantes;
    GenericAction actionFormularioReceitaFederalPrestacaoServico;
    GenericAction actionFormularioRetiradaResiduoLixo;
    GenericAction actionServicoProtecaoAcessoPessoa;
    GenericAction actionServicoProtecaoHospedagem;
    GenericAction actionServicoProtecaoMedicoOdontologico;
    GenericAction actionServicoProtecaoOutros;
    GenericAction actionServicoProtecaoTransporte;
    GenericAction actionServicoServicoResiduoLixo;
    GenericAction actionServicoSuprimentosAosNavios;
    SButton btnEnviarEmail;
    SButton btnFormulario;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
