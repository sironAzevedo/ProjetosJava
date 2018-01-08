package br.com.petrobras.sistam.desktop.agenciamento.formularios.receita;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.TermoResponsabilidadeReceitaVO;
import java.text.ParseException;
import java.util.Date;
import java.util.Formatter;
import java.util.TimeZone;
import javax.swing.text.MaskFormatter;

public class TermoDeResponsabilidadeReceitaModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private TermoResponsabilidadeReceitaVO voFormulario;
    private RepresentanteLegal representanteSelecionado;
    
    
    public TermoDeResponsabilidadeReceitaModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        this.voFormulario = new TermoResponsabilidadeReceitaVO();
        
    }
            
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public TermoResponsabilidadeReceitaVO getVoFormulario() {
        return voFormulario;
    }

    public void setVoFormulario(TermoResponsabilidadeReceitaVO voFormulario) {
        this.voFormulario = voFormulario;
    }

    public RepresentanteLegal getRepresentanteSelecionado() {
        return representanteSelecionado;
    }

    public void setRepresentanteSelecionado(RepresentanteLegal representanteSelecionado) {
        this.representanteSelecionado = representanteSelecionado;
    }

    public void registrarEmissao() {
        getService().registrarEmissaoDeDocumento(TipoDocumento.TERMO_RESPONSABILIDADE_EMPRESA, agenciamento, representanteSelecionado, false);
    }

    public void validar(){
        SistamPendencyManager pm = new SistamPendencyManager();
        
        pm.assertNotNull(representanteSelecionado)
                .orRegister(TipoExcecao.RECEITA, ConstantesI18N.INFORME_O_REPRESENTANTE);
        
        pm.verifyAll();
    } 
    
    public void carregarVO() {
        TimeZone zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());
        Agencia agencia = agenciamento.getAgencia();

        voFormulario.setPortoMunicipio(agencia.getMunicipioDoPorto(agenciamento.getPorto()));
        try {
            MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
            mask.setValueContainsLiteralCharacters(false);
            voFormulario.setCnpjAgente(mask.valueToString(agencia.getCnpj()));
        } catch (ParseException ex) {}
        
        voFormulario.setNomePetrobras(Agencia.PETROBRAS);
        voFormulario.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        voFormulario.setVgm(agenciamento.getVgm());
        voFormulario.setImo(agenciamento.getEmbarcacao().getImo());
        voFormulario.setSedePetrobras(Agencia.SEDE_PETROBRAS);
        voFormulario.setBandeiraEmbarcacao(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto());
        voFormulario.setNomeAgente(Agencia.PETROBRAS);
        voFormulario.setEnderecoAgente(agencia.getEnderecoCompleto());
        voFormulario.setEmailAgente(agencia.getEmail());
        voFormulario.setTelfoneAgente(agencia.getTelefone());
        voFormulario.setNomeRepresentanteLegal(representanteSelecionado.getNome());
        
        try {
            Formatter f = new Formatter().format("%011d", Long.parseLong(representanteSelecionado.getCpf()));
            String cpf = f.toString();
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.setValueContainsLiteralCharacters(false);
            voFormulario.setCpfRepresentanteLegal(mask.valueToString(cpf));
        } catch (ParseException ex) {}
        
        voFormulario.setNomeComandanteEntrada(agenciamento.getAgenciementoViagem().getComandanteEntrada());
        voFormulario.setNrEscala(agenciamento.getNumeroEscalaMercante());
        voFormulario.setMunicipioResponsavel(agenciamento.getAgencia().getCidade().trim() + ",");
        voFormulario.setData(SistamDateUtils.formatDateByExtensive(new Date(), zone));
    }

}
