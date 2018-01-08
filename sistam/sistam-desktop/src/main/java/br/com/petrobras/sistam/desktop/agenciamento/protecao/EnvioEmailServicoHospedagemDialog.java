package br.com.petrobras.sistam.desktop.agenciamento.protecao;

import br.com.petrobras.fcorp.swing.base.SDialog;
import br.com.petrobras.fcorp.swing.components.util.GenericDocument;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.snarf.desktop.assync.AssyncInvoker;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class EnvioEmailServicoHospedagemDialog extends SDialog{
    
    private EnvioEmailServicoHospedagemModel model;

    public EnvioEmailServicoHospedagemDialog(java.awt.Frame parent, ServicoHospedagem servicoHospedagem) {
        super(parent, true);
        model = new EnvioEmailServicoHospedagemModel(servicoHospedagem);
        carregarCombos();
        
        initComponents();
        setLocationRelativeTo(parent);
    }
    
    private void carregarCombos() {
        AssyncInvoker.create(model, "carregarAgenciasViagem")
                .disabling(cboAgencias)
                .settingLoadingIconOn(lblAgencias)
                .schedule();
    }
    
    public EnvioEmailServicoHospedagemModel getModel() {
        return model;
    }
    
    public void enviar(){
        model.enviar();
        model.setEnviado(true);
        dispose();
    }
    
    public void previsualizar(){
        PrevisualizarDialog dialog = new PrevisualizarDialog(SistamApp.getSistamApp().getMainFrame(), model.getPrevisualizar());
        dialog.setVisible(true);
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"}) @SuppressFBWarnings
    //CHECKSTYLE:OFF
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        actionEnviar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionVisualizar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        br.com.petrobras.fcorp.swing.components.SPanel painelDados = new br.com.petrobras.fcorp.swing.components.SPanel();
        br.com.petrobras.fcorp.swing.components.SPanel sPanel1 = new br.com.petrobras.fcorp.swing.components.SPanel();
        br.com.petrobras.fcorp.swing.components.SCheckBox chkAgencia = new br.com.petrobras.fcorp.swing.components.SCheckBox();
        br.com.petrobras.fcorp.swing.components.SCheckBox chkHotel = new br.com.petrobras.fcorp.swing.components.SCheckBox();
        lblAgencias = new br.com.petrobras.fcorp.swing.components.SLabel();
        cboAgencias = new br.com.petrobras.fcorp.swing.components.SComboBox();
        br.com.petrobras.fcorp.swing.components.SLabel lblLotacao = new br.com.petrobras.fcorp.swing.components.SLabel();
        br.com.petrobras.fcorp.swing.components.STextField txtLotacao = new br.com.petrobras.fcorp.swing.components.STextField();
        br.com.petrobras.fcorp.swing.components.SLabel lblCentroCusto = new br.com.petrobras.fcorp.swing.components.SLabel();
        br.com.petrobras.fcorp.swing.components.SComboBox cboCentroCusto = new br.com.petrobras.fcorp.swing.components.SComboBox();
        painelBotoes = new br.com.petrobras.fcorp.swing.components.SPanel();
        br.com.petrobras.fcorp.swing.components.SButton btnPrevisualizar = new br.com.petrobras.fcorp.swing.components.SButton();
        br.com.petrobras.fcorp.swing.components.SButton sButton4 = new br.com.petrobras.fcorp.swing.components.SButton();

        actionEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/send.png"))); // NOI18N
        actionEnviar.setMethodName("enviar");
        actionEnviar.setTarget(this);
        actionEnviar.setText("Enviar");
        actionEnviar.setToolTipText("Envia e-mail");

        actionVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/listagem_busca.png"))); // NOI18N
        actionVisualizar.setMethodName("previsualizar");
        actionVisualizar.setTarget(this);
        actionVisualizar.setText("Pré-visualizar");
        actionVisualizar.setToolTipText("Pré-visualizar");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Envio Solicitação de Hospedagem");

        painelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        painelDados.setName("painelDados"); // NOI18N

        sPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Para"));
        sPanel1.setName("sPanel1"); // NOI18N

        chkAgencia.setName("chkAgencia"); // NOI18N
        chkAgencia.setOpaque(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.filtro.agenciaMaritima}"), chkAgencia, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.filtro.servicoHospedagem.servicoProtecao.agenciamento.agencia.nome} - ${model.filtro.servicoHospedagem.servicoProtecao.agenciamento.agencia.email}"), chkAgencia, org.jdesktop.beansbinding.BeanProperty.create("text"), "");
        binding.setSourceNullValue("Agência Marítima");
        binding.setSourceUnreadableValue("Agência Marítima");
        bindingGroup.addBinding(binding);

        chkHotel.setName("chkHotel"); // NOI18N
        chkHotel.setOpaque(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.filtro.hotel}"), chkHotel, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.filtro.servicoHospedagem.empresaServico.empresa.razaoSocial} - ${model.filtro.servicoHospedagem.empresaServico.empresa.email}"), chkHotel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("Hotel");
        binding.setSourceUnreadableValue("Hotel");
        bindingGroup.addBinding(binding);

        lblAgencias.setText("Agência Viagem:");
        lblAgencias.setName("lblAgencias"); // NOI18N

        cboAgencias.setDisplayProperty(Servico.PROP_EMPRESA);
        cboAgencias.setName("cboAgencias"); // NOI18N

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${model.agenciasViagem}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, cboAgencias);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.filtro.agenciaViagem}"), cboAgencias, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout sPanel1Layout = new javax.swing.GroupLayout(sPanel1);
        sPanel1.setLayout(sPanel1Layout);
        sPanel1Layout.setHorizontalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sPanel1Layout.createSequentialGroup()
                        .addComponent(lblAgencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboAgencias, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                    .addGroup(sPanel1Layout.createSequentialGroup()
                        .addGroup(sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkHotel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        sPanel1Layout.setVerticalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sPanel1Layout.createSequentialGroup()
                .addComponent(chkAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkHotel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboAgencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAgencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblLotacao.setText("Lotação/Empresa:");
        lblLotacao.setName("lblLotacao"); // NOI18N

        txtLotacao.setDocument(new GenericDocument(100));
        txtLotacao.setName("txtLotacao"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.filtro.lotacao}"), txtLotacao, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblCentroCusto.setText("Centro de Custo:");
        lblCentroCusto.setName("lblCentroCusto"); // NOI18N

        cboCentroCusto.setName("cboCentroCusto"); // NOI18N

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${model.tiposCentroCusto}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, cboCentroCusto);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.filtro.tipoCentroCusto}"), cboCentroCusto, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout painelDadosLayout = new javax.swing.GroupLayout(painelDados);
        painelDados.setLayout(painelDadosLayout);
        painelDadosLayout.setHorizontalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCentroCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLotacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLotacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(painelDadosLayout.createSequentialGroup()
                                .addComponent(cboCentroCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 227, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        painelDadosLayout.setVerticalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLotacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLotacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCentroCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCentroCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        painelBotoes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelBotoes.setName("painelBotoes"); // NOI18N

        btnPrevisualizar.setAction(actionVisualizar);
        btnPrevisualizar.setName("btnPrevisualizar"); // NOI18N
        painelBotoes.add(btnPrevisualizar);

        sButton4.setAction(actionEnviar);
        sButton4.setName("sButton4"); // NOI18N
        painelBotoes.add(sButton4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON

    // Variables declaration - do not modify//GEN-BEGIN:variables
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionEnviar;
    br.com.petrobras.fcorp.swing.base.action.GenericAction actionVisualizar;
    br.com.petrobras.fcorp.swing.components.SComboBox cboAgencias;
    br.com.petrobras.fcorp.swing.components.SLabel lblAgencias;
    br.com.petrobras.fcorp.swing.components.SPanel painelBotoes;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
