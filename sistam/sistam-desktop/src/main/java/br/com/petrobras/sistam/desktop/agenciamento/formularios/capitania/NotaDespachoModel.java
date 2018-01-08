package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.sistam.common.valueobjects.NotaDespachoVo;
import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;

public class NotaDespachoModel extends BasePresentationModel<SistamService> {

    private Agenciamento agenciamento;
    private NotaDespachoVo voFormulario;
    private AgenciaOrgaoDespacho agenciaOrgaoSelecionado;
    private List<TipoSimNao> tipoSimNao;
    private Date data;
    private boolean isento;
    private boolean carga;
    private String numeroOficiais;
    private String atracacao;
    private Date dataTaxa;
    private Double valorTaxa;
    private String numeroTaxa;
    private RepresentanteLegal representanteSelecionado;

    public NotaDespachoModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        this.voFormulario = new NotaDespachoVo();
        tipoSimNao = Collections.unmodifiableList(new ArrayList<TipoSimNao>(Arrays.asList(TipoSimNao.values())));
        this.voFormulario.setIsento(TipoSimNao.NAO);
        this.voFormulario.setCarga(TipoSimNao.NAO);
    }

    public boolean isIsento() {
        return isento;
    }

    public void setIsento(boolean isento) {
        this.isento = isento;
    }

    public boolean isCarga() {
        return carga;
    }

    public void setCarga(boolean carga) {
        this.carga = carga;
    }

    public String getNumeroOficiais() {
        return numeroOficiais;
    }

    public void setNumeroOficiais(String oficiais) {
        this.numeroOficiais = oficiais;
    }

    public String getAtracacao() {
        return atracacao;
    }

    public void setAtracacao(String atracacao) {
        this.atracacao = atracacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
        firePropertyChange("data", null, this.data);
    }

    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public NotaDespachoVo getVoFormulario() {
        return voFormulario;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, this.agenciamento);
    }

    public AgenciaOrgaoDespacho getAgenciaOrgaoSelecionado() {
        return agenciaOrgaoSelecionado;
    }

    public void setAgenciaOrgaoSelecionado(AgenciaOrgaoDespacho agenciaOrgaoSelecionado) {
        this.agenciaOrgaoSelecionado = agenciaOrgaoSelecionado;
        firePropertyChange("agenciaOrgaoSelecionado", null, this.agenciaOrgaoSelecionado);
    }

    public List<TipoSimNao> getTipoSimNao() {
        return tipoSimNao;
    }

    public void setTipoSimNao(List<TipoSimNao> tipoSimNao) {
        this.tipoSimNao = tipoSimNao;
    }

    public RepresentanteLegal getRepresentanteSelecionado() {
        return representanteSelecionado;
    }

    public void setRepresentanteSelecionado(RepresentanteLegal representanteSelecionado) {
        this.representanteSelecionado = representanteSelecionado;
    }

    public void registrarEmissao() {
        getService().registrarEmissaoDeDocumento(TipoDocumento.NOTA_DE_DESPACHO_MARITIMO, agenciamento, representanteSelecionado, false);
    }

    public void validar() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(representanteSelecionado).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.INFORME_O_REPRESENTANTE);
        pm.assertNotNull(getAtracacao()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.MANOBRA_PARADA_INFORME_PONTO_ATRACACAO);
        pm.assertNotNull(getNumeroOficiais()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.AGENCIAMENTO_INFORME_NUMERO_OFICIAIS);
        pm.assertNotNull(voFormulario.getIsento()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.AGENCIAMENTO_INFORME_ISENTO);
        pm.assertNotNull(voFormulario.getCarga()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.AGENCIAMENTO_INFORME_CONDUZ_CARGA);
         pm.assertNotNull(getAgenciaOrgaoSelecionado()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.INFORME_O_ORGAO_DESPACHO);
        pm.verifyAll();
    }

    public void carregarVO() {
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());

        List<Taxa> t = getService().buscarTaxaPorAgenciamentoETipo(agenciamento, TipoTaxa.TUF_TAXA_UTILIZACAO_FAROL_CAPITANIA);
        for (Taxa taxa : t) {
            valorTaxa = taxa.getValor();
            dataTaxa = taxa.getDataPagamento();
            numeroTaxa = taxa.getNumeroDocumento().toString();
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        NumberFormat format = NumberFormat.getInstance();

        voFormulario.setNumeroProcessoDespacho(agenciamento.getNumeroProcessoDespacho());
        voFormulario.setDuv(agenciamento.getNumeroDUV());
        voFormulario.setAtracacao(getAtracacao());
        voFormulario.setNumeroOficiais(getNumeroOficiais());
        voFormulario.setValor(agenciamento.getDestinoFormatado());
        voFormulario.setArmador(agenciamento.getEmbarcacao().getArmador());
        voFormulario.setProcedencia(agenciamento.getPortoOrigem().getNomeCompleto());
        voFormulario.setDia(SistamDateUtils.formatShortDate(this.data, zone));
        voFormulario.setPlaca(agenciamento.getEmbarcacao().getIrin());
        voFormulario.setDWT(format.format(agenciamento.getEmbarcacao().getDwt()));
        voFormulario.setDestino(agenciamento.getDestinoFormatado());
        voFormulario.setTonBruta(format.format(agenciamento.getEmbarcacao().getArqueacaoBruta()));
        voFormulario.setTonLiquida(format.format(agenciamento.getEmbarcacao().getArqueacaoLiquida()));
        voFormulario.setDataEntrada(SistamDateUtils.formatDateComplete(agenciamento.getEta(), null));
        voFormulario.setDataSaida(SistamDateUtils.formatDateComplete(agenciamento.getDataEstimadaSaida(), null));
        voFormulario.setConsignatario(agenciamento.getAgencia().getNome());
        voFormulario.setTripulacao(agenciamento.getAgenciementoViagem().getNumeroTripulantesSaida().toString());
        voFormulario.setDataTUF(SistamDateUtils.formatShortDate(dataTaxa, null));
        voFormulario.setValorTUF(formatter.format(valorTaxa));
        voFormulario.setNumeroTUF(numeroTaxa);
        voFormulario.setValorTUFExtenso(SistamUtils.transformarValor(valorTaxa));
        voFormulario.setNomeCPFAgente(representanteSelecionado.getNomeCPFFormatado());

//        Date dataAlteracao = new Date();
//        for (Taxa taxa : agenciamento.getTaxas()) {
//            dataAlteracao = taxa.getDataAlteracao();
//        }
//        System.out.print(dataAlteracao);
        voFormulario.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        voFormulario.setBandeira(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto());
        voFormulario.setComandanteSaida(agenciamento.getAgenciementoViagem().getComandanteSaida());
        voFormulario.setMunicipio(agenciamento.getAgencia().getCidade().trim() + ",");

        String data_documento = SistamDateUtils.formatDateByExtensive(voFormulario.getDataDocumento(), null);
        String data_assinatura = SistamDateUtils.formatDateByExtensive(new Date(), zone);

        voFormulario.setDataAssinatura(!"".equals(data_documento) ? data_documento : data_assinatura);

        
        voFormulario.setOrgaoDespacho(getAgenciaOrgaoSelecionado() != null ? getAgenciaOrgaoSelecionado().getNome() : "");
        

    }
}
