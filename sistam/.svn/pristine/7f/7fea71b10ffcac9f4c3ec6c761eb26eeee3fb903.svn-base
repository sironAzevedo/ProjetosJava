package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.TermoResponsabilidadeRepresentanteLegalCapitaniaVO;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.swing.text.MaskFormatter;

public class TermoResponsabilidadeRepresentanteLegalModel extends BasePresentationModel<SistamService> {
    
    private Agenciamento agenciamento;
    private TermoResponsabilidadeRepresentanteLegalCapitaniaVO voFormulario;
    private TipoSimNao registradaCartorio;
    private Date validadeProcuracao;
    private TimeZone zone;
    private Date dataDocumento;
    
    public TermoResponsabilidadeRepresentanteLegalModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        this.voFormulario = new TermoResponsabilidadeRepresentanteLegalCapitaniaVO();
        this.registradaCartorio = TipoSimNao.NAO;
    }
    
    public TimeZone getZone() {
        return zone;
    }

    public void setZone(TimeZone zone) {
        this.zone = zone;
        firePropertyChange("zone", null, this.zone);
    }
            
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public TermoResponsabilidadeRepresentanteLegalCapitaniaVO getVoFormulario() {
        return voFormulario;
    }
    
    public TipoSimNao getRegistradaCartorio() {
        return registradaCartorio;
    }

    public void setRegistradaCartorio(TipoSimNao registradaCartorio) {
        this.registradaCartorio = registradaCartorio;
        firePropertyChange("registradaCartorio", null, this.registradaCartorio);
    }
    
    public List<TipoSimNao> getListaSimNao() {
        return TipoSimNao.listValues();
    }

    public Date getValidadeProcuracao() {
        return validadeProcuracao;
    }

    public void setValidadeProcuracao(Date validadeProcuracao) {
        this.validadeProcuracao = validadeProcuracao;
        firePropertyChange("validadeProcuracao", null, this.validadeProcuracao);
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
        firePropertyChange("dataDocumento", null, null);
    }
    
    public void validar(){
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(validadeProcuracao).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_VALIDADE_PROCURACAO);
        pm.verifyAll();
    } 
    
    public void registrarEmissao() {
        getService().registrarEmissaoDeDocumento(TipoDocumento.TERMO_RESPONSABILIDADE_REPRESENTANTE, agenciamento, false);
    }

    public void carregarVO() {
        voFormulario.setNumeroProcessoDespacho(agenciamento.getNumeroProcessoDespacho());
        voFormulario.setNrDuv(agenciamento.getNumeroDUV());
        voFormulario.setPortoEstadia(agenciamento.getPorto().getNomeCompleto());
        voFormulario.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        voFormulario.setArmador(agenciamento.getEmbarcacao().getArmador());
        voFormulario.setProprietario(agenciamento.getEmbarcacao().getProprietario());
        voFormulario.setImo(agenciamento.getEmbarcacao().getImo());
        voFormulario.setBandeiraEmbarcacao(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto());
        voFormulario.setNomeComandanteEntrada(agenciamento.getAgenciementoViagem().getComandanteEntrada());
        voFormulario.setAgenteConsignatario(Agencia.PETROBRAS);
        voFormulario.setEnderecoTelefone(agenciamento.getAgencia().getEnderecoCompleto() + " / " + agenciamento.getAgencia().getTelefone());
        voFormulario.setEnderecoEletronico(agenciamento.getAgencia().getEmail());
       
        try {
            MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
            mask.setValueContainsLiteralCharacters(false);
            voFormulario.setCnpj(mask.valueToString(agenciamento.getAgencia().getCnpj()));
        } catch (ParseException ex) {}
        
        voFormulario.setProcuracaoRegistradaEmOficio(registradaCartorio.getId());
        voFormulario.setValidadeProcuracao(SistamDateUtils.formatShortDate(validadeProcuracao, zone));
        voFormulario.setMunicipioResponsavel(agenciamento.getAgencia().getCidade().trim() + ",");
        
        String data_documento = dataDocumento!=null?  SistamDateUtils.formatDateByExtensive(dataDocumento, null) : SistamDateUtils.formatDateByExtensive(new Date(), zone);        
        
        voFormulario.setData(data_documento);
    }

}
