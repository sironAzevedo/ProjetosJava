package br.com.petrobras.sistam.desktop.agenciamento.formularios.anvisa;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.LiberacaoAnvisaVO;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ComunicacaoChegadaDialogModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private LiberacaoAnvisaVO liberacaoAnvisaVO;
    private RepresentanteLegal representanteLegalSelecionado;   
    private Porto portoSelecionado;
    private List<Porto> portos;
    private Date data;


    public ComunicacaoChegadaDialogModel(Agenciamento agenciamento, LiberacaoAnvisaVO liberacaoAnvisaVO) {
        this.agenciamento = agenciamento;
        this.liberacaoAnvisaVO = liberacaoAnvisaVO;
        setPortoSelecionado(agenciamento.getPorto());
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

    public Porto getPortoSelecionado() {
        return portoSelecionado;
    }

    public void setPortoSelecionado(Porto portoSelecionado) {
        this.portoSelecionado = portoSelecionado;
        firePropertyChange("portoSelecionado", null, this.portoSelecionado);
    }

    public List<Porto> getPortos() {
        return portos;
    }

    public void setPortos(List<Porto> portos) {
        this.portos = portos;
        firePropertyChange("portos", null, this.portos);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
        firePropertyChange("data", null, null);
    }
    
    
    //</editor-fold>
    
    public void gerarFormulario(){
        validarGeracaoDoFormulario();
        preencherInformacoesRestantesDoVo();
        
        getService().registrarEmissaoDeDocumento(TipoDocumento.COMUNICACAO_CHEGADA, agenciamento, representanteLegalSelecionado, false);
        RelatorioUtil.abrirRelatorioComunicacaoDeChegada(liberacaoAnvisaVO);
    }
    
    public void validarGeracaoDoFormulario(){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(getRepresentanteLegalSelecionado()).orRegister(TipoExcecao.CERTIFICADO_ANVISA, ConstantesI18N.INFORME_O_REPRESENTANTE);
        pm.assertNotNull(getPortoSelecionado()).orRegister(TipoExcecao.CERTIFICADO_ANVISA, ConstantesI18N.LIBERACAO_ANVISA_POSTO_PORTUARIO);
        pm.assertNotNull(data).orRegister(TipoExcecao.CERTIFICADO_ANVISA, ConstantesI18N.LIBERACAO_ANVISA_DATA_DOCUMENTO);
        pm.verifyAll();
    }
    
    private void preencherInformacoesRestantesDoVo() {
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());
        liberacaoAnvisaVO.setNomeRepresentante(representanteLegalSelecionado.getNome());
        liberacaoAnvisaVO.setLocalRepresentante(agenciamento.getAgencia().getCidade().trim() + " - " + agenciamento.getAgencia().getEstado());
        liberacaoAnvisaVO.setDataRepresentante(SistamDateUtils.formatShortDate(new Date(), zone));
        liberacaoAnvisaVO.setPassaporteRepresentante(representanteLegalSelecionado.getCpf().toString());
        liberacaoAnvisaVO.setPorto(portoSelecionado.getNomeCompleto().toString());
        liberacaoAnvisaVO.setDataRepresentante(SistamDateUtils.formatShortDate(this.data, zone));
        
    }    
}
