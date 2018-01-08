package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.agenciamento.documento.DetalheDocumentoDialog;
import br.com.petrobras.sistam.desktop.agenciamento.documento.InformacaoNumeroCertificadoDialog;
import br.com.petrobras.sistam.desktop.agenciamento.documento.InformacoesDoProtocoloDialog;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DocumentosPainel extends SPanel implements PropertyChangeListener{
    public static final String PROP_DOCUMENTO_PROTOCOLADO = "PROP_DOCUMENTO_PROTOCOLADO";
    
    private DocumentosPainelModel model;
    
    public DocumentosPainel() {
        this.model = new DocumentosPainelModel();
        initComponents();
        
        /*TableUtils.applyDecorator(tabelaDocumentos, new TableCellRendererDecorator() {
            @Override
            protected void decorateLabel(JLabel label, JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                
                System.out.println(isSelected ? "Selecionado -" : "Não selecionado -");
                System.out.println("Foreground: " + label.getForeground());
                System.out.println("Background: " + label.getBackground());
                System.out.println("--------------------------");
                
                
                if (model.getListaDeDocumentos().get(row).getManobra() != null) {
                    label.setForeground(isSelected? Color.WHITE : Color.BLUE);
                    label.setBackground(isSelected? Color.BLUE : Color.WHITE);
                } else {
                    label.setForeground(isSelected? Color.BLACK : Color.BLACK);
                    label.setBackground(isSelected? Color.decode("#BCD2E4") : Color.WHITE);
                }
            }
        });*/
        
    }
    

    public DocumentosPainelModel getModel() {
        return model;
    }
    
    public void setAgenciamento(Agenciamento agenciamento){
        model.setAgenciamento(agenciamento);
        if (agenciamento != null && StatusEmbarcacao.CANCELADO.equals(agenciamento.getStatusEmbarcacao())){
            colocarEmModoVisualizacao();
        }
    }
    
    public void adicionarNova(){
        Documento nova = model.obterNovoDocumento();
        DetalheDocumentoDialog dialog = new DetalheDocumentoDialog(SistamApp.getSistamApp().getMainFrame(), nova);
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);
    }
    
    public void editar(){
        Documento documento = model.obterDocumentoParaEdicaoVisualizacao();
        DetalheDocumentoDialog dialog = new DetalheDocumentoDialog(SistamApp.getSistamApp().getMainFrame(), documento);
        dialog.addPropertyChangeListener(this);
        dialog.setVisible(true);
    }
    
    public void informarProtocolo(){
        Documento documento = model.obterDocumentoParaEdicaoVisualizacao();
        InformacoesDoProtocoloDialog dialog = new InformacoesDoProtocoloDialog(SistamApp.getSistamApp().getMainFrame(), documento);
        dialog.setVisible(true);
        
        if (dialog.getModel().operacaoConfirmada()){
            model.atualizarDocumento(dialog.getModel().getDocumento());
            firePropertyChange(PROP_DOCUMENTO_PROTOCOLADO, null, null);
        }
    }
    
    public void informarNumeroCertificado(){
        Documento documento = model.obterDocumentoParaEdicaoVisualizacao();
        InformacaoNumeroCertificadoDialog dialog = new InformacaoNumeroCertificadoDialog(SistamApp.getSistamApp().getMainFrame(), documento);
        dialog.setVisible(true);
        
        if (dialog.getModel().operacaoConfirmada()){
            model.atualizarDocumento(documento);
            firePropertyChange(PROP_DOCUMENTO_PROTOCOLADO, null, null);
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
         if (evt.getPropertyName().equals(DetalheDocumentoDialog.DOCUMENTO_INSERIDO)){
            Documento documento = ((DetalheDocumentoDialog)evt.getSource()).getModel().getDocumento();
            model.adicionarDocumento(documento);
        }
        
        if (evt.getPropertyName().equals(DetalheDocumentoDialog.DOCUMENTO_ALTERADO)){
            Documento documento = ((DetalheDocumentoDialog)evt.getSource()).getModel().getDocumento();
            model.atualizarDocumento(documento);
        }
          
    }

    public void colocarEmModoVisualizacao() {
        painelBotoes.setVisible(false);
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

        actionAdicionar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionExcluir = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionEditar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionAtualizar = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        actionInformarProtocolo = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        sistamDateCellRenderer1 = new br.com.petrobras.sistam.desktop.components.SistamDateCellRenderer();
        actionInformarCertificado = new br.com.petrobras.fcorp.swing.base.action.GenericAction();
        br.com.petrobras.fcorp.swing.components.renderers.DateCellRenderer dateCellRenderer = new br.com.petrobras.fcorp.swing.components.renderers.DateCellRenderer();
        painelListaTaxas = new br.com.petrobras.fcorp.swing.components.SPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDocumentos = new br.com.petrobras.fcorp.swing.components.STable();
        painelBotoes = new br.com.petrobras.fcorp.swing.components.SPanel();
        botaoAdicionar = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoEditar = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoInformarProtocolo = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoAtualizar = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoExcluir1 = new br.com.petrobras.fcorp.swing.components.SButton();
        botaoInformarCertificado = new br.com.petrobras.fcorp.swing.components.SButton();

        actionAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionar.setMethodName("adicionarNova");
        actionAdicionar.setTarget(this);
        actionAdicionar.setText("Adicionar");
        actionAdicionar.setToolTipText("Adiciona um novo documento");

        actionExcluir.setConfirm("Confirma a exclusão do documento?");
        actionExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionExcluir.setMethodName("excluirDocumento");
        actionExcluir.setTarget(model);
        actionExcluir.setText("Excluir");
        actionExcluir.setToolTipText("Exclui o documento selecionado");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.documentoSelecionado}"), actionExcluir, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditar.setMethodName("editar");
        actionEditar.setTarget(this);
        actionEditar.setText("Editar");
        actionEditar.setToolTipText("Edita o documento selecionado");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.documentoSelecionado}"), actionEditar, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/listagem_refresh.png"))); // NOI18N
        actionAtualizar.setMethodName("atualizarListaDeDocumento");
        actionAtualizar.setTarget(model);
        actionAtualizar.setText("Atualizar");
        actionAtualizar.setToolTipText("Atualiza a lista de documentos");

        actionInformarProtocolo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/document.png"))); // NOI18N
        actionInformarProtocolo.setMethodName("informarProtocolo");
        actionInformarProtocolo.setTarget(this);
        actionInformarProtocolo.setText("Informar Protocolo");
        actionInformarProtocolo.setToolTipText("Informa a data e/ou número de protocolo do documento");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${not empty model.documentoSelecionado}"), actionInformarProtocolo, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        sistamDateCellRenderer1.setPattern("dd/MM/yyyy HH:mm");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.timeZone}"), sistamDateCellRenderer1, org.jdesktop.beansbinding.BeanProperty.create("timeZone"));
        bindingGroup.addBinding(binding);

        actionInformarCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/document.png"))); // NOI18N
        actionInformarCertificado.setMethodName("informarNumeroCertificado");
        actionInformarCertificado.setTarget(this);
        actionInformarCertificado.setText("Informar Certificado");
        actionInformarCertificado.setToolTipText("Informar número certificado");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.habilitarInformarCertificado}"), actionInformarCertificado, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        dateCellRenderer.setPattern("dd/MM/yyyy HH:mm");

        painelListaTaxas.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Documentos do Agenciamento"));

        tabelaDocumentos.setSortable(true);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${model.listaDeDocumentos}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tabelaDocumentos);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tipoDocumento}"));
        columnBinding.setColumnName("Tipo Documento");
        columnBinding.setColumnClass(br.com.petrobras.sistam.common.enums.TipoDocumento.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tipoDocumento.autoridade}"));
        columnBinding.setColumnName("Autoridade");
        columnBinding.setColumnClass(br.com.petrobras.sistam.common.enums.Autoridade.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataEmissao}"));
        columnBinding.setColumnName("Data Emissao");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${eta}"));
        columnBinding.setColumnName("Data do ETA");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataHoraParteEntrada}"));
        columnBinding.setColumnName("Data Oficial de Entrada");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataProtocolo}"));
        columnBinding.setColumnName("Data Protocolo");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${numeroDocumento}"));
        columnBinding.setColumnName("Numero Documento");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${numeroCTAC}"));
        columnBinding.setColumnName("Numero CTAC");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${emitenteFormatado}"));
        columnBinding.setColumnName("Emitente Formatado");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${model.documentoSelecionado}"), tabelaDocumentos, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(tabelaDocumentos);
        tabelaDocumentos.getColumnModel().getColumn(3).setMinWidth(100);
        tabelaDocumentos.getColumnModel().getColumn(3).setMaxWidth(100);
        tabelaDocumentos.getColumnModel().getColumn(4).setCellRenderer(null);
        tabelaDocumentos.getColumnModel().getColumn(5).setMinWidth(150);
        tabelaDocumentos.getColumnModel().getColumn(5).setMaxWidth(150);
        tabelaDocumentos.getColumnModel().getColumn(5).setCellRenderer(sistamDateCellRenderer1);
        tabelaDocumentos.getColumnModel().getColumn(6).setResizable(false);
        tabelaDocumentos.getColumnModel().getColumn(7).setResizable(false);

        javax.swing.GroupLayout painelListaTaxasLayout = new javax.swing.GroupLayout(painelListaTaxas);
        painelListaTaxas.setLayout(painelListaTaxasLayout);
        painelListaTaxasLayout.setHorizontalGroup(
            painelListaTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelListaTaxasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        painelListaTaxasLayout.setVerticalGroup(
            painelListaTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelListaTaxasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addContainerGap())
        );

        painelBotoes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        botaoAdicionar.setAction(actionAdicionar);

        botaoEditar.setAction(actionEditar);

        botaoInformarProtocolo.setAction(actionInformarProtocolo);

        botaoAtualizar.setAction(actionAtualizar);

        botaoExcluir1.setAction(actionExcluir);

        botaoInformarCertificado.setAction(actionInformarCertificado);

        javax.swing.GroupLayout painelBotoesLayout = new javax.swing.GroupLayout(painelBotoes);
        painelBotoes.setLayout(painelBotoesLayout);
        painelBotoesLayout.setHorizontalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(botaoAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoInformarProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoInformarCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        painelBotoesLayout.setVerticalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoInformarProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoInformarCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelListaTaxas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelListaTaxas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionAdicionar;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionAtualizar;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionEditar;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionExcluir;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionInformarCertificado;
    private br.com.petrobras.fcorp.swing.base.action.GenericAction actionInformarProtocolo;
    private br.com.petrobras.fcorp.swing.components.SButton botaoAdicionar;
    private br.com.petrobras.fcorp.swing.components.SButton botaoAtualizar;
    private br.com.petrobras.fcorp.swing.components.SButton botaoEditar;
    private br.com.petrobras.fcorp.swing.components.SButton botaoExcluir1;
    private br.com.petrobras.fcorp.swing.components.SButton botaoInformarCertificado;
    private br.com.petrobras.fcorp.swing.components.SButton botaoInformarProtocolo;
    private javax.swing.JScrollPane jScrollPane1;
    private br.com.petrobras.fcorp.swing.components.SPanel painelBotoes;
    private br.com.petrobras.fcorp.swing.components.SPanel painelListaTaxas;
    private br.com.petrobras.sistam.desktop.components.SistamDateCellRenderer sistamDateCellRenderer1;
    private br.com.petrobras.fcorp.swing.components.STable tabelaDocumentos;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables


}
