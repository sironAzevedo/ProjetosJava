package br.com.petrobras.sistam.desktop.agenciamento.protecao.formularios.retiradaResiduoLixo;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.FiltroFormularioRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.valueobjects.RelatorioRetiradaResiduoLixoVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioServicoRetiradaResiduoLixoVO;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormularioRetiradaResiduoLixoModel extends BasePresentationModel<SistamService> {

    private FiltroFormularioRetiradaResiduoLixo filtro = new FiltroFormularioRetiradaResiduoLixo();
    private List<RepresentanteLegal> listaRepresentante;
    private RepresentanteLegal representanteLegalSelecionado;
    
    public FormularioRetiradaResiduoLixoModel(ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo) {
       filtro.setServicoRetiradaResiduoLixo(getService().buscarServicoRetiradaResiduoLixoPorId(servicoRetiradaResiduoLixo.getId()));
       
       carregarListaDeRepresentante();
    }   

    public FiltroFormularioRetiradaResiduoLixo getFiltro() {
        return filtro;
    }

    public List<RepresentanteLegal> getListaRepresentante() {
        return listaRepresentante;
    }

    public void setListaRepresentante(List<RepresentanteLegal> listaRepresentante) {
        this.listaRepresentante = listaRepresentante;
         firePropertyChange("listaRepresentante", null, null);
    } 

    public RepresentanteLegal getRepresentanteLegalSelecionado() {
        return representanteLegalSelecionado;
    }

    public void setRepresentanteLegalSelecionado(RepresentanteLegal representanteLegalSelecionado) {
        this.representanteLegalSelecionado = representanteLegalSelecionado;
        firePropertyChange("representanteLegalSelecionado", null, null);
    }  
    
     private void carregarListaDeRepresentante(){
        listaRepresentante = new ArrayList<RepresentanteLegal>();
        listaRepresentante.add(0, null);
        listaRepresentante.addAll(1, filtro.getServicoRetiradaResiduoLixo().getServicoProtecao().getAgenciamento().getAgencia().getRepresentantes());
    } 
    
    public void validar() {
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertNotNull(filtro.getListaRepresentante()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.INFORME_O_REPRESENTANTE);
        pm.assertNotNull(filtro.getDataPeriodo()).orRegister(TipoExcecao.SERVICO_RETIRADA_RESIDUO_LIXO, ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_DATA_PERIODO_OBRIGATORIA);
        pm.verifyAll(); 
    }
    
    
    
    public Map<String, Object> obterParametros(){
        Map<String, Object> parametros = new HashMap<String, Object>();
        
        StringBuilder solicitacao = new StringBuilder();
        String agencia = filtro.getServicoRetiradaResiduoLixo().getServicoProtecao().getAgenciamento().getAgencia().toString();
        String empresaRequerente = filtro.getServicoRetiradaResiduoLixo().getResponsavelCusto().getNomeCompleto();
        
        solicitacao.append("Requerimento para retirada de resíduos de embarcações na Área do Porto Organizado de  ").append(agencia)
                   .append("\n")
                   .append("\n")
                   .append("Empresa requerente: ").append(empresaRequerente)
                   .append("\n");
        
        
        parametros.put("SOLICITACAO", solicitacao.toString());
        parametros.put("REPRESENTANTE", filtro.getListaRepresentante().getNome());
        parametros.put("DATA_PERIODO", SistamDateUtils.formatDate(filtro.getDataPeriodo(), "dd/MM/yyyy", filtro.getServicoRetiradaResiduoLixo().getServicoProtecao().getAgenciamento().getAgencia().obterTimezone()));
        return parametros;
        
    }
    
    private List<RelatorioServicoRetiradaResiduoLixoVO> obterDadosParaOFormulario(){
        List<RelatorioServicoRetiradaResiduoLixoVO> resultado = new ArrayList<RelatorioServicoRetiradaResiduoLixoVO>();
        RelatorioServicoRetiradaResiduoLixoVO relatorioServicoRetiradaResiduoLixoVO = new RelatorioServicoRetiradaResiduoLixoVO();
        resultado.add(relatorioServicoRetiradaResiduoLixoVO);
        
        List<RelatorioRetiradaResiduoLixoVO> relatorios = new ArrayList<RelatorioRetiradaResiduoLixoVO>();
        RelatorioRetiradaResiduoLixoVO relatorioRetiradaResiduoLixoVO = new RelatorioRetiradaResiduoLixoVO();  
            
        relatorioRetiradaResiduoLixoVO.setNomeEmbarcacao(filtro.getServicoRetiradaResiduoLixo().getServicoProtecao().getAgenciamento().getEmbarcacao().getNomeCompleto());
        relatorioRetiradaResiduoLixoVO.setOrigemEmbarcacao(filtro.getServicoRetiradaResiduoLixo().getServicoProtecao().getAgenciamento().getPortoOrigem().getNomeCompleto());
        relatorioRetiradaResiduoLixoVO.setEmpresaResponsavel(filtro.getServicoRetiradaResiduoLixo().getEmpresaMaritima().getRazaoSocial());
        relatorioRetiradaResiduoLixoVO.setNomeEmbarcacaoTransbordo(filtro.getServicoRetiradaResiduoLixo().getEmpresaServico().getNomeServico());
        relatorioRetiradaResiduoLixoVO.setTransportadoraRodoviaria(filtro.getServicoRetiradaResiduoLixo().getEmpresaMaritimaRodoviaria().getRazaoSocial());
        relatorioRetiradaResiduoLixoVO.setTipoResiduo(filtro.getServicoRetiradaResiduoLixo().getTipoResiduo().getPorExtenso());
        relatorioRetiradaResiduoLixoVO.setCaracterizacao(filtro.getServicoRetiradaResiduoLixo().getCaracterizacao());
        relatorioRetiradaResiduoLixoVO.setEstadoFisico(filtro.getServicoRetiradaResiduoLixo().getEstadoFisico());
        relatorioRetiradaResiduoLixoVO.setClassificacao(filtro.getServicoRetiradaResiduoLixo().getClassificacao());
        relatorioRetiradaResiduoLixoVO.setCodigoOnu(filtro.getServicoRetiradaResiduoLixo().getCodigoOnu());
        relatorioRetiradaResiduoLixoVO.setQuantidadeResiduo(filtro.getServicoRetiradaResiduoLixo().getQuantidadeResiduo()); 
        relatorioRetiradaResiduoLixoVO.setUnidadeMedida(filtro.getServicoRetiradaResiduoLixo().getTipoUnidadeMedida().getAbreviado());
        relatorioRetiradaResiduoLixoVO.setDataOperacao(filtro.getServicoRetiradaResiduoLixo().getDataInicioOperacao());
        relatorioRetiradaResiduoLixoVO.setHorarioInicioOperacao(filtro.getServicoRetiradaResiduoLixo().getDataInicioOperacao()); 
        relatorioRetiradaResiduoLixoVO.setHorarioTerminoOperacao(filtro.getServicoRetiradaResiduoLixo().getDataFimOperacao()); 
        relatorioRetiradaResiduoLixoVO.setArmazenamentoTemporarioResiduo(filtro.getServicoRetiradaResiduoLixo().getLocalArmazenagem());
        relatorioRetiradaResiduoLixoVO.setLoTemporario(filtro.getServicoRetiradaResiduoLixo().getLonArmazenagem());
        relatorioRetiradaResiduoLixoVO.setCadriTemporario(filtro.getServicoRetiradaResiduoLixo().getDescCadri());
        relatorioRetiradaResiduoLixoVO.setDestinoFinalResiduo(filtro.getServicoRetiradaResiduoLixo().getLocalDestinoFinal());
        relatorioRetiradaResiduoLixoVO.setLoFinal(filtro.getServicoRetiradaResiduoLixo().getLonDestinoFinal());
        relatorioRetiradaResiduoLixoVO.setCadriFinal(filtro.getServicoRetiradaResiduoLixo().getCadriDestinoFinal());
      
        relatorios.add(relatorioRetiradaResiduoLixoVO);
        
        resultado.get(0).setRetiradaResiduoLixoVOs(relatorios);
                
        return resultado;
 
    }   
     
    
    public void gerarFormulario() {
        RelatorioUtil.abrirFormularioRetiradaResiduoLixo(filtro.getServicoRetiradaResiduoLixo().getServicoProtecao().getAgenciamento().getAgencia(), filtro.getServicoRetiradaResiduoLixo().getTipoResiduo(), obterDadosParaOFormulario(), obterParametros()); 
    }
}
