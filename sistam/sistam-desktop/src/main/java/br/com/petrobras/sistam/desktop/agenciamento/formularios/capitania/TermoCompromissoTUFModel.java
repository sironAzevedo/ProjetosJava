package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.TermoCompromissoTUFVO;
import java.util.Date;
import java.util.TimeZone;

public class TermoCompromissoTUFModel extends BasePresentationModel<SistamService> {
    
    private Agenciamento agenciamento;
    private TermoCompromissoTUFVO voFormulario;
    private Date data;
    private Double valorDolarTUR;
    private RepresentanteLegal representanteSelecionado;
      
    public TermoCompromissoTUFModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        this.voFormulario = new TermoCompromissoTUFVO();
    }
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
        firePropertyChange("data", null, this.data);
    }

    public Double getValorDolarTUR() {
        return valorDolarTUR;
    }

    public void setValorDolarTUR(Double valorDolarTUR) {
        this.valorDolarTUR = valorDolarTUR;
        firePropertyChange("valorDolarTUR", null, this.valorDolarTUR);
    }
    
             
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public TermoCompromissoTUFVO getVoFormulario() {
        return voFormulario;
    }
    
    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, this.agenciamento);
    }
     public RepresentanteLegal getRepresentanteSelecionado() {
        return representanteSelecionado;
    }

    public void setRepresentanteSelecionado(RepresentanteLegal representanteSelecionado) {
        this.representanteSelecionado = representanteSelecionado;
    }

    public void registrarEmissao() {
      getService().registrarEmissaoDeDocumento(TipoDocumento.TERMO_COMPROMISSO_RECOLHIMENTO_TUF, agenciamento, representanteSelecionado, false);
    }

    
    public void validar(){
        SistamPendencyManager pm = new SistamPendencyManager();
         pm.assertNotNull(representanteSelecionado)
                .orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.INFORME_O_REPRESENTANTE);
         pm.assertNotNull(data).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_DATA_DOLAR);
         pm.assertNotNull(valorDolarTUR).orRegister(TipoExcecao.COMUNICACAO_CAPITANIA, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_VALOR_TUF_DOLAR);
        pm.verifyAll();
    } 
    
    public void carregarVO() {
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());
        
        voFormulario.setNumeroProcessoDespacho(agenciamento.getNumeroProcessoDespacho());
        
        voFormulario.setNomePetroCNPJAgencia(agenciamento.getAgencia().getPetroCNPJAgencia());
        voFormulario.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        voFormulario.setImo(agenciamento.getEmbarcacao().getImo());
        voFormulario.setTpb(agenciamento.getEmbarcacao().getDwt());
        voFormulario.setValorTUFDolar(valorDolarTUR);
        voFormulario.setNomeCPFAgente(representanteSelecionado.getNomeCPFFormatado());
        voFormulario.setMunicipio(agenciamento.getAgencia().getCidade().trim() + ",");
        voFormulario.setDataAssinatura(SistamDateUtils.formatDateByExtensive(data, zone));
        
    }

}
