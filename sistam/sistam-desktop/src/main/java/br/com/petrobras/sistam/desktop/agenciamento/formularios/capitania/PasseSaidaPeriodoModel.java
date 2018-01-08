package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.AgenciaOrgaoDespacho;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.PortoComplemento;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.PasseDeSaidaPeriodoVo;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;

public class PasseSaidaPeriodoModel extends BasePresentationModel<SistamService> {

    private Agenciamento agenciamento;
    private PasseDeSaidaPeriodoVo voFormulario;
    private AgenciaOrgaoDespacho agenciaOrgaoSelecionado;
    private String proximoPortoFormatado;
    private Date data;

    public PasseSaidaPeriodoModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        this.voFormulario = new PasseDeSaidaPeriodoVo();
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

    public PasseDeSaidaPeriodoVo getVoFormulario() {
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

    public String getProximoPortoFormatado() {
        return proximoPortoFormatado;
    }

    public void setProximoPortoFormatado(String proximoPortoFormatado) {
        this.proximoPortoFormatado = proximoPortoFormatado;
        firePropertyChange("proximoPortoFormatado", null, this.proximoPortoFormatado);

    }

    public void registrarEmissao() {
        getService().registrarEmissaoDeDocumento(TipoDocumento.PASSE_SAIDA, agenciamento, false);
    }

    public void validar() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(getAgenciaOrgaoSelecionado()).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.INFORME_O_ORGAO_DESPACHO);
        pm.verifyAll();
    }

    public void carregarVO() {
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());

        voFormulario.setNumeroProcessoDespacho(agenciamento.getNumeroProcessoDespacho());
        voFormulario.setDuv(agenciamento.getNumeroDUV());
        voFormulario.setOrgaoDespacho(getAgenciaOrgaoSelecionado().getNome());
        voFormulario.setDia(SistamDateUtils.formatShortDate(this.data, zone));

        voFormulario.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        voFormulario.setBandeira(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto());
        voFormulario.setComandanteSaida(agenciamento.getAgenciementoViagem().getComandanteSaida());
        
        
        if (agenciamento.getPorto() != null) {
            String portoAgenciamento = agenciamento.getPorto().getNomeCompleto();
            String estadoPortoAgenciamento = agenciamento.getPorto().getNomeCompleto();

            agenciamento.getPorto().setComplementos(new HashSet<PortoComplemento>(getService().buscarPortosComplementosPorPorto(agenciamento.getPorto())));
            if (!agenciamento.getPorto().getComplementos().isEmpty()) {
                estadoPortoAgenciamento = agenciamento.getPorto().getComplemento().getEstado();
                estadoPortoAgenciamento = " /" + estadoPortoAgenciamento; 
            } else{
                estadoPortoAgenciamento = "";
            }

            String cidadeEstadoPortoOrigem = portoAgenciamento.concat(estadoPortoAgenciamento);
            voFormulario.setMunicipio(cidadeEstadoPortoOrigem + ",");
        }  
        
        String data_documento = SistamDateUtils.formatDateByExtensive(voFormulario.getDataDocumento(), null);
        String data_assinatura = SistamDateUtils.formatDateByExtensive(new Date(), zone);

        voFormulario.setDataAssinatura(!"".equals(data_documento) ? data_documento : data_assinatura);

    }

}
