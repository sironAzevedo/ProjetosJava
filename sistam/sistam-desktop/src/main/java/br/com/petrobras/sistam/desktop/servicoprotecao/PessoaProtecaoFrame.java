package br.com.petrobras.sistam.desktop.servicoprotecao;

import br.com.petrobras.fcorp.swing.base.SInternalFrame;
import br.com.petrobras.fcorp.swing.base.action.GenericAction;
import br.com.petrobras.fcorp.swing.components.SButton;
import br.com.petrobras.fcorp.swing.components.SDateChooser;
import br.com.petrobras.fcorp.swing.components.SFormattedTextField;
import br.com.petrobras.fcorp.swing.components.SLabel;
import br.com.petrobras.fcorp.swing.components.SPanel;
import br.com.petrobras.fcorp.swing.components.STable;
import br.com.petrobras.fcorp.swing.components.STextField;
import br.com.petrobras.fcorp.swing.components.util.GenericDocument;
import br.com.petrobras.sistam.common.entity.Pessoa;
import br.com.petrobras.sistam.desktop.SistamApp;
import br.com.petrobras.sistam.desktop.components.lookups.servicoprotecao.EmpresaProtecaoLookup;
import br.com.petrobras.sistam.desktop.util.DesktopUtil;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

/**
 * @author adzk
 */
public class PessoaProtecaoFrame extends SInternalFrame {

    private PessoaProtecaoModel model;

    /**
     * Construtor padrão.
     */
    public PessoaProtecaoFrame() {
        this.model = new PessoaProtecaoModel();
        initComponents();
        DesktopUtil.configurarComponenteCPF(campoCpf, actionPesquisar);
    }

    public PessoaProtecaoModel getModel() {
        return model;
    }

    public void pesquisar() {
        model.pesquisar();
    }

    public void limparPesquisa() {
        model.limparPesquisa();
    }

    public void adicionar() {
        Pessoa pessoa = model.obterNovaPessoa();
        DetalhesPessoaProtecaoDialog dialog = new DetalhesPessoaProtecaoDialog(SistamApp.getSistamApp().getMainFrame(), pessoa);
        dialog.setVisible(true);

        if (dialog.getModel().isSalvo()) {
            limparPesquisa();
            pesquisar();
        }
    }

    public void editar() {
        Pessoa pessoa = model.obterPessoaParaEdicao();
        DetalhesPessoaProtecaoDialog dialog = new DetalhesPessoaProtecaoDialog(SistamApp.getSistamApp().getMainFrame(), pessoa);
        dialog.setVisible(true);

        if (dialog.getModel().isSalvo()) {
            limparPesquisa();
            pesquisar();
        }
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

        GenericAction actionAdicionar = new GenericAction();
        GenericAction actionEditar = new GenericAction();
        actionPesquisar = new GenericAction();
        GenericAction actionLimpar = new GenericAction();
        SPanel panelFiltros = new SPanel();
        SLabel labelNome = new SLabel();
        SLabel labelRg = new SLabel();
        SButton botaoLimpar = new SButton();
        SButton botaoPesquisar = new SButton();
        SLabel labelCpf = new SLabel();
        SLabel labelData = new SLabel();
        STextField campoNome = new STextField();
        STextField campoDocumento = new STextField();
        SLabel labelEmpresa = new SLabel();
        SDateChooser campoData = new SDateChooser();
        campoCpf = new SFormattedTextField();
        EmpresaProtecaoLookup empresaProtecaoLookup1 = new EmpresaProtecaoLookup();
        SPanel panelLista = new SPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        STable sTable1 = new STable();
        SPanel panelBotos = new SPanel();
        SButton botaoAdicionar = new SButton();
        SButton botaoEditar = new SButton();

        actionAdicionar.setIcon(new ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        actionAdicionar.setMethodName("adicionar");
        actionAdicionar.setTarget(this);
        actionAdicionar.setText("Adicionar");

        actionEditar.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        actionEditar.setMethodName("editar");
        actionEditar.setTarget(this);
        actionEditar.setText("Editar");

        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.pessoaSelecionada != null}"), actionEditar, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        actionPesquisar.setIcon(new ImageIcon(getClass().getResource("/icons/listagem_busca.png"))); // NOI18N
        actionPesquisar.setMethodName("pesquisar");
        actionPesquisar.setTarget(this);
        actionPesquisar.setText("Pesquisar");

        actionLimpar.setIcon(new ImageIcon(getClass().getResource("/icons/limpar1.png"))); // NOI18N
        actionLimpar.setMethodName("limparPesquisa");
        actionLimpar.setTarget(this);
        actionLimpar.setText("Limpar");
        actionLimpar.setToolTipText("Limpar filltros de pesquisa");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pessoas do Serviço de Proteção");

        panelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros"));
        panelFiltros.setName("panelFiltros"); // NOI18N

        labelNome.setText("Nome:");
        labelNome.setName("labelNome"); // NOI18N

        labelRg.setText("Nº Documento:");
        labelRg.setName("labelRg"); // NOI18N

        botaoLimpar.setAction(actionLimpar);
        botaoLimpar.setName("botaoLimpar"); // NOI18N

        botaoPesquisar.setAction(actionPesquisar);
        botaoPesquisar.setName("botaoPesquisar"); // NOI18N

        labelCpf.setText("CPF:");
        labelCpf.setName("labelCpf"); // NOI18N

        labelData.setText("Data de Nascimento:");
        labelData.setName("labelData"); // NOI18N

        campoNome.setDocument(new GenericDocument(100));
        campoNome.setName("campoNome"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.filtro.nome}"), campoNome, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        campoDocumento.setDocument(new GenericDocument(50));
        campoDocumento.setName("campoDocumento"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.filtro.documento}"), campoDocumento, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        labelEmpresa.setText("Empresa:");
        labelEmpresa.setName("labelEmpresa"); // NOI18N

        campoData.setName("campoData"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.filtro.dataNascimento}"), campoData, BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        campoCpf.setName("campoCpf"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.filtro.cpfComMascara}"), campoCpf, BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        empresaProtecaoLookup1.setText("empresaProtecaoLookup1");
        empresaProtecaoLookup1.setName("empresaProtecaoLookup1"); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.filtro.empresa}"), empresaProtecaoLookup1, BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        GroupLayout panelFiltrosLayout = new GroupLayout(panelFiltros);
        panelFiltros.setLayout(panelFiltrosLayout);
        panelFiltrosLayout.setHorizontalGroup(
            panelFiltrosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addGroup(panelFiltrosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoLimpar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.LEADING, panelFiltrosLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(panelFiltrosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFiltrosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(panelFiltrosLayout.createSequentialGroup()
                                .addComponent(campoNome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(labelData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelFiltrosLayout.createSequentialGroup()
                                .addComponent(campoCpf, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoDocumento, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(empresaProtecaoLookup1, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panelFiltrosLayout.setVerticalGroup(
            panelFiltrosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFiltrosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFiltrosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDocumento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(empresaProtecaoLookup1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFiltrosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLimpar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelLista.setBorder(BorderFactory.createTitledBorder("Lista de Pessoas"));
        panelLista.setName("panelLista"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        sTable1.setSortable(true);
        sTable1.setName("sTable1"); // NOI18N

        ELProperty eLProperty = ELProperty.create("${model.pessoas}");
        JTableBinding jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, sTable1);
        JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${nome}"));
        columnBinding.setColumnName("Nome");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${tipoDocumentoIdentificacao}"));
        columnBinding.setColumnName("Tipo Documento");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${documento}"));
        columnBinding.setColumnName("Nº Documento");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${cpf}"));
        columnBinding.setColumnName("Cpf");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${dataNascimentoFormatada}"));
        columnBinding.setColumnName("Data Nascimento");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${nomeEmpresa}"));
        columnBinding.setColumnName("Nome Empresa");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${ativa}"));
        columnBinding.setColumnName("Ativa");
        columnBinding.setColumnClass(Boolean.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, this, ELProperty.create("${model.pessoaSelecionada}"), sTable1, BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(sTable1);
        sTable1.getColumnModel().getColumn(1).setMinWidth(120);
        sTable1.getColumnModel().getColumn(1).setMaxWidth(120);
        sTable1.getColumnModel().getColumn(2).setMinWidth(120);
        sTable1.getColumnModel().getColumn(2).setMaxWidth(120);
        sTable1.getColumnModel().getColumn(3).setMinWidth(120);
        sTable1.getColumnModel().getColumn(3).setMaxWidth(120);
        sTable1.getColumnModel().getColumn(4).setMinWidth(120);
        sTable1.getColumnModel().getColumn(4).setMaxWidth(120);
        sTable1.getColumnModel().getColumn(5).setMinWidth(120);
        sTable1.getColumnModel().getColumn(5).setMaxWidth(120);
        sTable1.getColumnModel().getColumn(6).setMinWidth(120);
        sTable1.getColumnModel().getColumn(6).setMaxWidth(120);

        GroupLayout panelListaLayout = new GroupLayout(panelLista);
        panelLista.setLayout(panelListaLayout);
        panelListaLayout.setHorizontalGroup(
            panelListaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        panelListaLayout.setVerticalGroup(
            panelListaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelBotos.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        panelBotos.setName("panelBotos"); // NOI18N

        botaoAdicionar.setAction(actionAdicionar);
        botaoAdicionar.setName("botaoAdicionar"); // NOI18N
        panelBotos.add(botaoAdicionar);

        botaoEditar.setAction(actionEditar);
        botaoEditar.setName("botaoEditar"); // NOI18N
        panelBotos.add(botaoEditar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panelBotos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFiltros, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFiltros, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE:ON
    // Variables declaration - do not modify//GEN-BEGIN:variables
    GenericAction actionPesquisar;
    SFormattedTextField campoCpf;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
