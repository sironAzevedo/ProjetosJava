package br.com.petrobras.sistam.desktop.agenciamento.operacoes;

import br.com.petrobras.fcorp.swing.base.SDialog;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SLabel;
import br.com.petrobras.fcorp.swing.components.SList;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.SRadioPanel;
import br.com.petrobras.fcorp.swing.components.STextField;
import br.com.petrobras.fcorp.swing.components.util.GenericDocument;
import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.desktop.components.lookups.porto.PortoLookup;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JListBinding;
import org.jdesktop.swingbinding.SwingBindings;

public class DetalheDocumentacaoLongoCursoDialog extends SDialog {
    private static final String TITULO_DESTINO_CARGA = "Destino da Carga";
    private static final String TITULO_ORIGEM_CARGA = "Origem da Carga";
    
    private DetalheDocumentacaoLongoCursoModel model;
    
    public DetalheDocumentacaoLongoCursoDialog(java.awt.Frame parent, DocumentacaoLongoCurso documentacaoLongoCurso) {
        super(parent, true);
        this.model = new DetalheDocumentacaoLongoCursoModel(documentacaoLongoCurso);
        initComponents();
        setLocationRelativeTo(parent);
        
        //Atualiza o título do painel com as informações da carga
        TitledBorder titledBorder = (TitledBorder)painelDestinoOrigemCarga.getBorder();
        if (TipoOperacao.CARGA_EXPORTACAO.equals(documentacaoLongoCurso.getOperacao().getTipoOperacao())){
            titledBorder.setTitle(TITULO_DESTINO_CARGA);
            labelBlRecebido.setVisible(false);
            radioPanelBlRecebido.setVisible(false);
        }
        else if (TipoOperacao.DESCARGA_IMPORTACAO.equals(documentacaoLongoCurso.getOperacao().getTipoOperacao())){
            titledBorder.setTitle(TITULO_ORIGEM_CARGA);
        }
    }

    public DetalheDocumentacaoLongoCursoModel getModel() {
        return model;
    }
    
    public void salvar(){
        model.salvar();
        dispose();
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
        bindingGroup = new BindingGroup();

        GenericAction actionSalvar = new GenericAction();
        GenericAction actionAdicionarCe = new GenericAction();
        GenericAction actionRemoverCe = new GenericAction();
        SPanel painelBotoes = new SPanel();
        SButton botaoSalvar = new SButton();
        SPanel painelInformacoes = new SPanel();
        SLabel sLabel1 = new SLabel();
        STextField sTextField1 = new STextField();
        painelDestinoOrigemCarga = new SPanel();
        SLabel sLabel2 = new SLabel();
        SLabel sLabel3 = new SLabel();
        STextField sTextField2 = new STextField();
        SLabel sLabel4 = new SLabel();
        STextField sTextField3 = new STextField();
        PortoLookup portoLookup1 = new PortoLookup();
        SPanel painelDocumentacao = new SPanel();
        SLabel sLabel5 = new SLabel();
        SLabel sLabel6 = new SLabel();
        labelBlRecebido = new SLabel();
        STextField sTextField5 = new STextField();
        radioPanelBlRecebido = new SRadioPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        SList sList1 = new SList();
        STextField sTextField7 = new STextField();
        SButton sButton1 = new SButton();
        SButton sButton2 = new SButton();
        SLabel sLabel8 = new SLabel();
        STextField sTextField6 = new STextField();

        actionSalvar.setIcon(new ImageIcon(getClass().getResource("/icons/disk.png"))); // NOI18N
        actionSalvar.setMethodName("salvar");
        actionSalvar.setTarget(this);
        actionSalvar.setText("Salvar");
        actionSalvar.setToolTipText("Salva as informações da documentação");

        actionAdicionarCe.setIcon(new ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionarCe.setMethodName("adicionarCE");
        actionAdicionarCe.setTarget(model);
        actionAdicionarCe.setToolTipText("Adicionar o CE informado");

        actionRemoverCe.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        actionRemoverCe.setMethodName("removerCE");
        actionRemoverCe.setTarget(model);
        actionRemoverCe.setToolTipText("Remove o CE selecionado");

        Binding binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${not empty model.CESelecionado}"), actionRemoverCe, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Documentação de Longo Curso");

        painelBotoes.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        painelBotoes.setName("painelBotoes"); // NOI18N

        botaoSalvar.setAction(actionSalvar);
        botaoSalvar.setName("botaoSalvar"); // NOI18N
        painelBotoes.add(botaoSalvar);

        painelInformacoes.setBorder(BorderFactory.createTitledBorder("Informações"));
        painelInformacoes.setName("painelInformacoes"); // NOI18N

        sLabel1.setText("Produto:");
        sLabel1.setName("sLabel1"); // NOI18N

        sTextField1.setEnabled(false);
        sTextField1.setName("sTextField1"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.documentacaoLongoCurso.operacao.produto.nomeCompleto}"), sTextField1, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        painelDestinoOrigemCarga.setBorder(BorderFactory.createTitledBorder("Destino/Origem da Carga"));
        painelDestinoOrigemCarga.setName("painelDestinoOrigemCarga"); // NOI18N

        sLabel2.setText("Porto:");
        sLabel2.setName("sLabel2"); // NOI18N

        sLabel3.setText("Cidade:");
        sLabel3.setName("sLabel3"); // NOI18N

        sTextField2.setDocument(new GenericDocument(60));
        sTextField2.setName("sTextField2"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.documentacaoLongoCurso.cidade}"), sTextField2, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        sLabel4.setText("País:");
        sLabel4.setName("sLabel4"); // NOI18N

        sTextField3.setEnabled(false);
        sTextField3.setName("sTextField3"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.pais}"), sTextField3, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        portoLookup1.setText("portoLookup1");
        portoLookup1.setName("portoLookup1"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.documentacaoLongoCurso.porto}"), portoLookup1, BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        GroupLayout painelDestinoOrigemCargaLayout = new GroupLayout(painelDestinoOrigemCarga);
        painelDestinoOrigemCarga.setLayout(painelDestinoOrigemCargaLayout);
        painelDestinoOrigemCargaLayout.setHorizontalGroup(
            painelDestinoOrigemCargaLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelDestinoOrigemCargaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDestinoOrigemCargaLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(sLabel2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel3, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel4, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(painelDestinoOrigemCargaLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(sTextField2, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(sTextField3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(portoLookup1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        painelDestinoOrigemCargaLayout.setVerticalGroup(
            painelDestinoOrigemCargaLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelDestinoOrigemCargaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDestinoOrigemCargaLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(sLabel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(portoLookup1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelDestinoOrigemCargaLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(sLabel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelDestinoOrigemCargaLayout.createParallelGroup(Alignment.CENTER)
                    .addComponent(sLabel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        painelDocumentacao.setBorder(BorderFactory.createTitledBorder("Documentos:"));
        painelDocumentacao.setName("painelDocumentacao"); // NOI18N

        sLabel5.setText("ME:");
        sLabel5.setName("sLabel5"); // NOI18N

        sLabel6.setText("CE:");
        sLabel6.setName("sLabel6"); // NOI18N

        labelBlRecebido.setText("BL Recebido?");
        labelBlRecebido.setName("labelBlRecebido"); // NOI18N

        sTextField5.setDocument(new GenericDocument(13, GenericDocument.OPTION_APPLY_UPPERCASE ));
        sTextField5.setName("sTextField5"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.documentacaoLongoCurso.manifestoEletronico}"), sTextField5, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        radioPanelBlRecebido.setName("radioPanelBlRecebido"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.listaSimNao}"), radioPanelBlRecebido, BeanProperty.create("elements"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.blRecebido}"), radioPanelBlRecebido, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        radioPanelBlRecebido.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        sList1.setName("sList1"); // NOI18N

        ELProperty eLProperty = ELProperty.create("${model.listaCE}");
        JListBinding jListBinding = SwingBindings.createJListBinding(UpdateStrategy.READ_WRITE, this, eLProperty, sList1);
        bindingGroup.addBinding(jListBinding);
        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.CESelecionado}"), sList1, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(sList1);

        sTextField7.setDocument(new GenericDocument(15, GenericDocument.OPTION_APPLY_UPPERCASE ));
        sTextField7.setName("sTextField7"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.CENovo}"), sTextField7, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        sButton1.setAction(actionAdicionarCe);
        sButton1.setName("sButton1"); // NOI18N

        sButton2.setAction(actionRemoverCe);
        sButton2.setName("sButton2"); // NOI18N

        GroupLayout painelDocumentacaoLayout = new GroupLayout(painelDocumentacao);
        painelDocumentacao.setLayout(painelDocumentacaoLayout);
        painelDocumentacaoLayout.setHorizontalGroup(
            painelDocumentacaoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelDocumentacaoLayout.createSequentialGroup()
                .addGroup(painelDocumentacaoLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(Alignment.TRAILING, painelDocumentacaoLayout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(sButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelDocumentacaoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(painelDocumentacaoLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(sLabel5, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(sLabel6, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(painelDocumentacaoLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(sTextField5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(painelDocumentacaoLayout.createSequentialGroup()
                                .addComponent(sTextField7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(sButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(painelDocumentacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelBlRecebido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(radioPanelBlRecebido, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelDocumentacaoLayout.setVerticalGroup(
            painelDocumentacaoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelDocumentacaoLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelDocumentacaoLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(sLabel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sTextField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(painelDocumentacaoLayout.createParallelGroup(Alignment.CENTER)
                    .addComponent(sLabel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sTextField7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(sButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(painelDocumentacaoLayout.createParallelGroup(Alignment.CENTER)
                    .addComponent(labelBlRecebido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioPanelBlRecebido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );

        sLabel8.setText("Tipo:");
        sLabel8.setName("sLabel8"); // NOI18N

        sTextField6.setEnabled(false);
        sTextField6.setName("sTextField6"); // NOI18N

        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.documentacaoLongoCurso.operacao.tipoOperacao.porExtenso}"), sTextField6, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        GroupLayout painelInformacoesLayout = new GroupLayout(painelInformacoes);
        painelInformacoes.setLayout(painelInformacoesLayout);
        painelInformacoesLayout.setHorizontalGroup(
            painelInformacoesLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelInformacoesLayout.createSequentialGroup()
                .addGroup(painelInformacoesLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(painelInformacoesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(painelInformacoesLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(sLabel1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(sLabel8, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(painelInformacoesLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(sTextField1, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(sTextField6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(painelInformacoesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(painelDestinoOrigemCarga, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(painelDocumentacao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelInformacoesLayout.setVerticalGroup(
            painelInformacoesLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(painelInformacoesLayout.createSequentialGroup()
                .addGroup(painelInformacoesLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(Alignment.TRAILING, painelInformacoesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(painelInformacoesLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(sLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(sTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(painelInformacoesLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(sLabel8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(sTextField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(painelDestinoOrigemCarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(painelDocumentacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(painelInformacoes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelBotoes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelInformacoes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(painelBotoes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON

    // Variables declaration - do not modify//GEN-BEGIN:variables
    SLabel labelBlRecebido;
    SPanel painelDestinoOrigemCarga;
    SRadioPanel radioPanelBlRecebido;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
