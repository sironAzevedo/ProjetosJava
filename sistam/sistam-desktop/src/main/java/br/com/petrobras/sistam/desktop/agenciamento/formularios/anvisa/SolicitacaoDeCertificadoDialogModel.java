package br.com.petrobras.sistam.desktop.agenciamento.formularios.anvisa;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.LiberacaoAnvisaVO;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class SolicitacaoDeCertificadoDialogModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private LiberacaoAnvisaVO liberacaoAnvisaVO;
    private RepresentanteLegal representanteLegalSelecionado;
    private boolean livrePratica;
    private boolean controleSanitarioBordo;
    private boolean nacionalControleSanitarioBordo;
    private Date dataDeposito;
    private Date data;
    private TimeZone zone;
    private String finalidadeEmbarcacao;
    private Porto portoSelecionado;
    private List<Porto> portos;

    public SolicitacaoDeCertificadoDialogModel(Agenciamento agenciamento, LiberacaoAnvisaVO liberacaoAnvisaVO) {
        this.agenciamento = agenciamento;
        this.liberacaoAnvisaVO = liberacaoAnvisaVO;
        
        zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());
        setPortoSelecionado(agenciamento.getPorto());
        setFinalidadeEmbarcacao("TRANSPORTE DE PETRÓLEO E DERIVADOS");
        
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public LiberacaoAnvisaVO getLiberacaoAnvisaVO() {
        return liberacaoAnvisaVO;
    }
    
    public RepresentanteLegal getRepresentanteLegalSelecionado() {
        return representanteLegalSelecionado;
    }
    
    public void setRepresentanteLegalSelecionado(RepresentanteLegal representanteLegalSelecionado) {
        this.representanteLegalSelecionado = representanteLegalSelecionado;
        firePropertyChange("representanteLegalSelecionado", null, null);
    }

     public List<Porto> getPortos() {
        return portos;
    }

    public void setPortos(List<Porto> portos) {
        this.portos = portos;
        firePropertyChange("portos", null, this.portos);
    }       

    public Porto getPortoSelecionado() {
        return portoSelecionado;
    }

    public void setPortoSelecionado(Porto portoSelecionado) {
        this.portoSelecionado = portoSelecionado;
        firePropertyChange("portoSelecionado", null, this.portoSelecionado);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
        firePropertyChange("data", null, null);
    }
    
    
    public boolean isLivrePratica() {
        return livrePratica;
    }

    public void setLivrePratica(boolean livrePratica) {
        this.livrePratica = livrePratica;
        firePropertyChange("livrePratica", null, null);
    }

    public boolean isControleSanitarioBordo() {
        return controleSanitarioBordo;
    }

    public void setControleSanitarioBordo(boolean controleSanitarioBordo) {
        this.controleSanitarioBordo = controleSanitarioBordo;
        firePropertyChange("controleSanitarioBordo", null, null);
    }

    public Date getDataDeposito() {
        return dataDeposito;
    }

    public void setDataDeposito(Date dataDeposito) {
        this.dataDeposito = dataDeposito;
        firePropertyChange("dataDeposito", null, null);
    }

    public String getFinalidadeEmbarcacao() {
        return finalidadeEmbarcacao;
    }

    public void setFinalidadeEmbarcacao(String finalidadeEmbarcacao) {
        this.finalidadeEmbarcacao = finalidadeEmbarcacao;
        firePropertyChange("finalidadeEmbarcacao", null, null);
    }


    public boolean isNacionalControleSanitarioBordo() {
        return nacionalControleSanitarioBordo;
    }

    public void setNacionalControleSanitarioBordo(boolean nacionalControleSanitarioBordo) {
        this.nacionalControleSanitarioBordo = nacionalControleSanitarioBordo;
        firePropertyChange("nacionalControleSanitarioBordo", null, null);
    }
    
    public List<TipoOperacao> getListaFinalidade(){
        List<TipoOperacao> lista = new ArrayList<TipoOperacao>();
        lista.addAll(Arrays.asList(TipoOperacao.values()));
        lista.add(0, null);
        return lista;
    }
    
    //</editor-fold>
    
    public void gerarFormulario(){
        validarGeracaoDoFormulario();
        preencherInformacoesRestantesDoVo();
        
        getService().registrarEmissaoDeDocumento(TipoDocumento.SOLICITACAO_CERTIFICADO, agenciamento, representanteLegalSelecionado, false);
        RelatorioUtil.abrirRelatorioLivrePratica(liberacaoAnvisaVO);
    }
    
    public void validarGeracaoDoFormulario(){
      SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertThat(livrePratica || controleSanitarioBordo || nacionalControleSanitarioBordo).orRegister(TipoExcecao.CERTIFICADO_ANVISA, ConstantesI18N.SELECIONE_TIPO_DO_RELATORIO);
        pm.assertNotEmpty(finalidadeEmbarcacao).orRegister(TipoExcecao.CERTIFICADO_ANVISA, ConstantesI18N.INFORME_A_FINALIDADE);
        pm.assertNotNull(getRepresentanteLegalSelecionado()).orRegister(TipoExcecao.CERTIFICADO_ANVISA, ConstantesI18N.INFORME_O_REPRESENTANTE);
        pm.assertNotNull(getPortoSelecionado()).orRegister(TipoExcecao.CERTIFICADO_ANVISA, ConstantesI18N.LIBERACAO_ANVISA_POSTO_PORTUARIO);
        pm.assertNotNull(data).orRegister(TipoExcecao.CERTIFICADO_ANVISA, ConstantesI18N.LIBERACAO_ANVISA_DATA_DOCUMENTO);

        pm.verifyAll();
    }
    
    private void preencherInformacoesRestantesDoVo() {
        liberacaoAnvisaVO.setNomeRepresentante(representanteLegalSelecionado.getNome().toString().trim());
        liberacaoAnvisaVO.setPassaporteRepresentante(representanteLegalSelecionado.getCpfComMascara());
        liberacaoAnvisaVO.setLivrePratica(livrePratica);
        liberacaoAnvisaVO.setControleSanitarioBordo(controleSanitarioBordo);
        liberacaoAnvisaVO.setNacionalControleSanitarioBordo(nacionalControleSanitarioBordo);          
        liberacaoAnvisaVO.setPorto(portoSelecionado.getNomeCompleto().toString());
        liberacaoAnvisaVO.setDataRepresentante(SistamDateUtils.formatShortDate(this.data, zone));

        Date dataEscolhida = null;
        
        Date dataLP = null;
        if (livrePratica) {
            List<Taxa> taxaLP = getService().buscarTaxaPorAgenciamentoETipo(agenciamento, TipoTaxa.LIVRE_PRATICA_ANVISA);
            if (!taxaLP.isEmpty()) {
                dataLP = taxaLP.get(0).getDataPagamento();
            }
        }
        
        Date dataCSB = null;
        if (controleSanitarioBordo) {
            List<Taxa> taxaCSB = getService().buscarTaxaPorAgenciamentoETipo(agenciamento, TipoTaxa.SOLICITACAO_CONTROLE_SANITARIO_ANVISA);
            if (!taxaCSB.isEmpty()) {
                dataCSB = taxaCSB.get(0).getDataPagamento();
            }
        }
        
        if (livrePratica && controleSanitarioBordo  ) {
            if (dataCSB != null && dataLP != null){
                dataEscolhida = dataCSB.before(dataLP) ? dataCSB : dataLP;
            }
            else if (dataLP != null){
                dataEscolhida = dataLP;
            }
            else if (dataCSB != null){
                dataEscolhida = dataCSB;
            }
        } 
        else if (livrePratica){
            dataEscolhida = dataLP;
        }
        else if (controleSanitarioBordo){
            dataEscolhida =dataCSB;
        }
        
        liberacaoAnvisaVO.setDataDeposito(SistamDateUtils.formatShortDate(dataEscolhida, null));
        liberacaoAnvisaVO.setFinalidadeEmbarcacao(finalidadeEmbarcacao.toString().trim());
    }    
}
