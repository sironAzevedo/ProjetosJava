package br.com.petrobras.sistam.desktop.agenciamento.formularios.receita;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.CTACProdutoVo;
import br.com.petrobras.sistam.common.valueobjects.CTACReceitaVo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.LoggerFactory;

public class EmitirCTACReceitaModel extends BasePresentationModel<SistamService> {

    private Agenciamento agenciamento;
    private CTACReceitaVo voFormulario;
    private List<CTACReceitaVo> listaVoFormulario = new ArrayList<CTACReceitaVo>();
    private Porto portoEmbarque;
    private List<PortoComplemento> complementosEmbarque = new ArrayList<PortoComplemento>();
    private List<PortoComplemento> complementosDesembarque = new ArrayList<PortoComplemento>();
    private PortoComplemento complementoPortoEmbarque;
    private PortoComplemento complementoPortoDesembarque;
    private String observacao;
    private Date dataEmissao;
    private DocumentacaoCabotagem cabotagem;

    public EmitirCTACReceitaModel(Agenciamento agenciamento, CTACReceitaVo voFormulario, DocumentacaoCabotagem cabotagem) {
        this.agenciamento = agenciamento;
        this.voFormulario = voFormulario;
        this.cabotagem = cabotagem;
    }

    public DocumentacaoCabotagem getCabotagem() {
        return cabotagem;
    }
    
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public CTACReceitaVo getVoFormulario() {
        return voFormulario;
    }

    public void setVoFormulario(CTACReceitaVo voFormulario) {
        this.voFormulario = voFormulario;
    }

    public Porto getPortoEmbarque() {
        return portoEmbarque;
    }

    public void setPortoEmbarque(Porto portoEmbarque) {
        this.portoEmbarque = portoEmbarque;
        firePropertyChange("portoEmbarque", null, null);
        carregarComplementosPorPortoEmbarque();
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public PortoComplemento getComplementoPortoEmbarque() {
        return complementoPortoEmbarque;
    }

    public void setComplementoPortoEmbarque(PortoComplemento complementoPortoEmbarque) {
        this.complementoPortoEmbarque = complementoPortoEmbarque;
        firePropertyChange("complementoPortoEmbarque", null, null);
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void registrarEmissao() {
        if (cabotagem.isPermitidoEditarCTAC()) {
            getService().registrarEmissaoDeDocumentosConhecimentoEmbarque(agenciamento, dataEmissao, listaVoFormulario);
        }else{
            getService().registrarEmissaoDeDocumentoConhecimentoEmbarque(agenciamento, dataEmissao, voFormulario);
        }
    }

    public PortoComplemento getComplementoPortoDesembarque() {
        return complementoPortoDesembarque;
    }

    public List<PortoComplemento> getComplementosDesembarque() {
        return complementosDesembarque;
    }

    public List<PortoComplemento> getComplementosEmbarque() {
        return complementosEmbarque;
    }

    public List<CTACReceitaVo> getListaVoFormulario() {
        return listaVoFormulario;
    }
    
    public void setComplementoPortoDesembarque(PortoComplemento complementoPortoDesembarque) {
        this.complementoPortoDesembarque = complementoPortoDesembarque;
        firePropertyChange("complementoPortoDesembarque", null, null);
    }

    public void setComplementosDesembarque(List<PortoComplemento> complementosDesembarque) {
        this.complementosDesembarque = complementosDesembarque;
        firePropertyChange("complementosDesembarque", null, null);
    }

    public void setComplementosEmbarque(List<PortoComplemento> complementosEmbarque) {
        this.complementosEmbarque = complementosEmbarque;
        firePropertyChange("complementosEmbarque", null, null);
    }

    public void carregarComplementosPorPortoEmbarque() {
        setComplementoPortoEmbarque(null);
        setComplementosEmbarque(Collections.EMPTY_LIST);
        if (portoEmbarque != null) {
            setComplementosEmbarque(getService().buscarPortosComplementosPorPorto(portoEmbarque));
        }
    }

    public void carregarComplementosPorPortoDesembarque() {
        setComplementoPortoDesembarque(null);
        setComplementosDesembarque(Collections.EMPTY_LIST);
        Porto porto = agenciamento.getPortoOrigem();
        if (porto != null) {
            setComplementosDesembarque(getService().buscarPortosComplementosPorPorto(cabotagem.getPorto()));
        }
    }

    public void validar() {
        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertNotNull(dataEmissao).orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.INFORMACOES_RELATORIO_DATA_EMISSAO_OBRIGATORIA);
        pm.assertNotNull(complementoPortoDesembarque).orRegister(TipoExcecao.PORTO_COMPLEMENTO, ConstantesI18N.CTAC_INFORME_PORTO_ENDERECO_DESEMBARQUE);
        pm.assertNotNull(portoEmbarque)
                .orRegister(TipoExcecao.RELATORIO_INFO, ConstantesI18N.CTAC_INFORME_PORTO_EMBARQUE);
        if(portoEmbarque != null){
            pm.assertNotNull(complementoPortoEmbarque).orRegister(TipoExcecao.PORTO_COMPLEMENTO, ConstantesI18N.CTAC_INFORME_PORTO_ENDERECO_EMBARQUE, agenciamento.getPorto().getNomeCompleto());
        }

        pm.verifyAll();
    }

    public void carregarVO() {

        voFormulario.setPortoDesembarque(complementoPortoDesembarque.getNomePortoCtacFormatado());
        voFormulario.setPortoDesembarqueDestino(complementoPortoDesembarque.getPorto());
        
        //embarcador é um porto selecionado
        voFormulario.setPortoEmbarque(complementoPortoEmbarque.getNomePortoCtacFormatado());
        voFormulario.setEmbarcadorEndereco(complementoPortoEmbarque.getEndereco());
        voFormulario.setEmbarcadorMunicipio(complementoPortoEmbarque.getCidade());
        voFormulario.setEmbarcadorUF(complementoPortoEmbarque.getEstado());
        voFormulario.setEmbarcadorCNPJ(complementoPortoEmbarque.getCnpjComMascara());
        voFormulario.setEmbarcadorInscEstadual(complementoPortoEmbarque.getInscricaoEstadualFormatado());

        //destinatario é o porto destino da carga
        voFormulario.setDestinatarioEndereco(complementoPortoDesembarque.getEndereco());
        voFormulario.setDestinatarioMunicipio(complementoPortoDesembarque.getCidade());
        voFormulario.setDestinatarioUF(complementoPortoDesembarque.getEstado());
        voFormulario.setDestinatarioCNPJ(complementoPortoDesembarque.getCnpjComMascara());
        voFormulario.setDestinatarioInscEstadual(complementoPortoDesembarque.getInscricaoEstadualFormatado());

        voFormulario.setObservacao(observacao);

        voFormulario.setDiaEmissao(SistamDateUtils.getDayDate(dataEmissao, TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone())));
        voFormulario.setMesEmissao(SistamDateUtils.getMonthDate(dataEmissao, TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone())));
        voFormulario.setAnoEmissao(SistamDateUtils.getYearDate(dataEmissao, TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone())));
        
        voFormulario.setConhecimentoEmbarque(cabotagem.getNumeroCTAC());
        
        listaVoFormulario.clear();
        if (cabotagem.isPermitidoEditarCTAC()) {
            for (CTACProdutoVo produto : voFormulario.getProdutos()) {
                try {
                    CTACReceitaVo receita = (CTACReceitaVo) BeanUtils.cloneBean(voFormulario);
                    receita.setDescricaoProduto(produto.getDescricaoProduto());
                    receita.setPeso(produto.getPeso());
                    receita.setFrete(produto.getFrete());
                    receita.setVolume(produto.getVolume());
                    receita.setConhecimentoEmbarque(produto.getConhecimentoEmbarque());
                    listaVoFormulario.add(receita);

                } catch (Exception ex) {
                    LoggerFactory.getLogger(EmitirCTACReceitaModel.class.getName()).error(ex.getMessage(), ex);
                    throw new BusinessException(ex.getMessage(), ex);
                }
            }
        }
    }
}
