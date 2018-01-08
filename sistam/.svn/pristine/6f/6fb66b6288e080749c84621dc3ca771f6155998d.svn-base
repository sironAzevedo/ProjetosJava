package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroPedidoDespachoPorPeriodoVo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.swing.text.MaskFormatter;

public class PedidoDespachoPorPeriodoModel extends BasePresentationModel<SistamService> {

    private Agenciamento agenciamento;
    private FiltroPedidoDespachoPorPeriodoVo voFormulario = new FiltroPedidoDespachoPorPeriodoVo();
    private List<TipoSimNao> listaSimNao;
    private String cnpjArmador;
    private String numero_registro;
    private String observacao;
    private Date dataValidade;
    private Date dataDocumento;
    private AgenciaOrgaoDespacho agenciaOrgaoSelecionado;

    public PedidoDespachoPorPeriodoModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        listaSimNao = Collections.unmodifiableList(new ArrayList<TipoSimNao> (Arrays.asList(TipoSimNao.values())));
        voFormulario.setAtestadoInscricaoTemporariaEmbarcacao(TipoSimNao.NAO);
        voFormulario.setEmbarcacaoPrep(TipoSimNao.NAO);
    }
    
     //<editor-fold defaultstate="collapsed" desc="Getters e Setters">

    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, null);
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
        firePropertyChange("dataDocumento", null, null);
    }

    public FiltroPedidoDespachoPorPeriodoVo getVoFormulario() {
        return voFormulario;
    }
    
    public List<TipoSimNao> getListaSimNao() {
        return listaSimNao;
    }

    public String getCnpjArmador() {
        return cnpjArmador;
    }

    public void setCnpjArmador(String cnpjArmador) {
        this.cnpjArmador = cnpjArmador;
    }
    
    public String getArmadorCnpjComMascara() {
        return SistamUtils.formatMask("##.###.###/####-##", cnpjArmador);
    }

    public void setArmadorCnpjComMascara(String cnpj) {
        setCnpjArmador(cnpj == null ? null : cnpj.replaceAll("\\D", ""));
    }

    public String getNumero_registro() {
        return numero_registro;
    }

    public void setNumero_registro(String numero_registro) {
        this.numero_registro = numero_registro;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) { 
        Object old = this.dataValidade;
        this.dataValidade = dataValidade;
        firePropertyChange("dataValidade", old, this.dataValidade);
    } 

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public AgenciaOrgaoDespacho getAgenciaOrgaoSelecionado() {
        return agenciaOrgaoSelecionado;
    }
    
    public void setAgenciaOrgaoSelecionado(AgenciaOrgaoDespacho agenciaOrgaoSelecionado) {
        this.agenciaOrgaoSelecionado = agenciaOrgaoSelecionado;
        firePropertyChange("agenciaOrgaoSelecionado", null, this.agenciaOrgaoSelecionado);
    }
    
     //</editor-fold>

    public void registrarEmissao() {
        getService().registrarEmissaoDeDocumento(TipoDocumento.PEDIDO_DESPACHO_POR_PERIODO, agenciamento, false);
    }
    
    public void validarDadosDespacho(){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(getAgenciaOrgaoSelecionado()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.INFORME_O_ORGAO_DESPACHO);
        pm.verifyAll();
    } 
         
    public void carregarVO() { 
        validarDadosDespacho();
        String portoAgenciamento = agenciamento.getPorto().getNomeCompleto();
        String portoEstadoAgenciamento = "";
        
        voFormulario.setNomeAgencia(agenciamento.getAgencia().getNome());
        voFormulario.setDuv(agenciamento.getNumeroDUV() != null ? agenciamento.getNumeroDUV() : " - "); 
        agenciamento.getPorto().setComplementos(new HashSet<PortoComplemento>(getService().buscarPortosComplementosPorPorto(agenciamento.getPorto())));
        if (agenciamento.getPorto().getComplemento() != null) {
            portoEstadoAgenciamento = agenciamento.getPorto().getComplemento().getEstado();
        }
       
        String cidadeEstadoPortoAgenciamento = portoAgenciamento + "-" + portoEstadoAgenciamento;  
        voFormulario.setPortoAgenciamento(cidadeEstadoPortoAgenciamento);
        
        //DADOS DESPACHO
        voFormulario.setOrgaoDespachoAgencia(getAgenciaOrgaoSelecionado() != null ? getAgenciaOrgaoSelecionado().getNome() : "");

        //DADOS EMBARCAÇÃO        
        String nomeEmbarcacao = agenciamento.getEmbarcacao().getNomeCompleto()!= null ? agenciamento.getEmbarcacao().getNomeCompleto(): " - ";
        String vgm = agenciamento.getVgmSaida() != null ? agenciamento.getVgmSaida() : "-";
        voFormulario.setNomeEmbarcacao(nomeEmbarcacao + "/ VGM - " + vgm);
        voFormulario.setIrin(agenciamento.getEmbarcacao().getIrin() != null ? agenciamento.getEmbarcacao().getIrin() : " - ");
        voFormulario.setNumeroInscricao(agenciamento.getEmbarcacao().getInscricao() != null ? agenciamento.getEmbarcacao().getInscricao() : " - ");
        voFormulario.setImo(agenciamento.getEmbarcacao().getImo() != null ? agenciamento.getEmbarcacao().getImo() : " - ");
        
        String loa = String.valueOf(agenciamento.getEmbarcacao() != null ? agenciamento.getEmbarcacao().getLoa() : " - ");
        voFormulario.setComprimentoTotalLoa(loa);
        voFormulario.setAreaNavegacao(agenciamento.getAreaNavegacaoSaida() != null ? agenciamento.getAreaNavegacaoSaida().getPorExtenso() : " - ");
        voFormulario.setBandeiraEmbarcacao(agenciamento.getEmbarcacao().getBandeira() != null ? agenciamento.getEmbarcacao().getBandeira().getNomeCompleto() : " - ");
        
        voFormulario.setPorteBrutoDwt(agenciamento.getEmbarcacao().getDwt()); 
        voFormulario.setArqueacaoBrutaGrt(agenciamento.getEmbarcacao().getArqueacaoBruta());
        
        voFormulario.setNomeComadanteSaidaEmbarcao(agenciamento.getAgenciementoViagem().getComandanteSaida() != null ? agenciamento.getAgenciementoViagem().getComandanteSaida() : " - ");
        voFormulario.setArmadorEmbarcacao(agenciamento.getEmbarcacao().getArmador() != null ?agenciamento.getEmbarcacao().getArmador() : " - ");
        voFormulario.setNumeroRegistroCRA(numero_registro != null ? numero_registro : " - "); 
        
        String dataPreenchida = SistamDateUtils.formatDate(dataValidade, "dd/MM/yyyy", null); 
        voFormulario.setValidadeCRA(dataPreenchida != null ? dataPreenchida : " - "); 
        
        try {
            MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
            mask.setValueContainsLiteralCharacters(false);
            voFormulario.setCnpjArmador(cnpjArmador != null ? mask.valueToString(cnpjArmador) : " ");
        } catch (Exception e) {
        }
        
        String representanteLegalNome = agenciamento.getAgencia().getNomeCompleto();
        String representanteLegalEndereco = agenciamento.getAgencia().getEndereco();
        String representanteLegalEmail = agenciamento.getAgencia().getEmail();
        String representanteLegalTelefone = agenciamento.getAgencia().getTelefone();
        voFormulario.setRepresentanteLegalEmbarcacao(representanteLegalNome + "/ " + representanteLegalEndereco + "/ Tel: " + representanteLegalTelefone);  
        voFormulario.setRepresentanteLegalEmbarcacaoEmail(representanteLegalEmail);
        voFormulario.setObservacao(observacao != null ? observacao : "");
        
        String dataFormulario = SistamDateUtils.formatDate(dataDocumento, "dd/MM/yyyy", null);
        String dataSistema = SistamDateUtils.formatShortDate(new Date(), null); 
        voFormulario.setDataDocumento(!"".equals(dataFormulario) ? dataFormulario : dataSistema);
    }
}
