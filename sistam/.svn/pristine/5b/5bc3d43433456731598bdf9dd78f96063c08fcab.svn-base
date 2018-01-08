package br.com.petrobras.sistam.desktop.agenciamento.formularios.capitania;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Escala;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoSimNao;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.DeclaracaoGeralCapitaniaVO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DeclaracaoGeralModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private DeclaracaoGeralCapitaniaVO declaracaoGeralCapitaniaVO;
    private List<PontoAtracacao> pontosAtracacao;
    private PontoAtracacao pontoAtracacaoSelecionado;
    private String escalas;
    private TipoSimNao tripulantes;
    private TipoSimNao passageiros;
    private TipoSimNao planilhaGMDSS;
    private TimeZone zone ;
    private String resumoCarga;

    public DeclaracaoGeralModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        declaracaoGeralCapitaniaVO = new DeclaracaoGeralCapitaniaVO();
        carregarEscalasFormatadas();
        
        this.tripulantes = TipoSimNao.NAO;
        this.passageiros = TipoSimNao.NAO;
        this.planilhaGMDSS = TipoSimNao.NAO;
        
        zone = TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone());
    }
    
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public DeclaracaoGeralCapitaniaVO getDeclaracaoGeralCapitaniaVO() {
        return declaracaoGeralCapitaniaVO;
    }
    public List<TipoSimNao> getListaSimNao() {
        return Collections.unmodifiableList(new ArrayList<TipoSimNao> (Arrays.asList(TipoSimNao.values())));
    }
    
    public TipoSimNao getTripulantes() {
        return tripulantes;
    }
    
    public void setTripulantes(TipoSimNao tripulantes) {
        this.tripulantes = tripulantes;
        firePropertyChange("tripulantes", null, this.tripulantes);
    }
    
    public TipoSimNao getPassageiros() {
        return passageiros;
    }
    
    public void setPassageiros(TipoSimNao passageiros) {
        this.passageiros = passageiros;
        firePropertyChange("passageiros", null, this.passageiros);
                
    }
    public TipoSimNao getPlanilhaGMDSS() {
        return planilhaGMDSS;
    }
    
    public void setPlanilhaGMDSS(TipoSimNao planilhaGMDSS) {
        this.planilhaGMDSS = planilhaGMDSS;
        firePropertyChange("planilhaGMDSS", null, this.planilhaGMDSS);
    }
    
    public List<PontoAtracacao> getPontosAtracacao() {
        return pontosAtracacao;
    }

    public String getResumoCarga() {
        return resumoCarga;
    }

    public void setResumoCarga(String resumoCarga) {
        
        this.resumoCarga = !resumoCarga.isEmpty() ? resumoCarga : "EM LASTRO";
    }
    

    public void setPontosAtracacao(List<PontoAtracacao> pontosAtracacao) {
        this.pontosAtracacao = pontosAtracacao;
        firePropertyChange("pontosAtracacao", null, this.pontosAtracacao);
    }

    public PontoAtracacao getPontoAtracacaoSelecionado() {
        return pontoAtracacaoSelecionado;
    }

    public void setPontoAtracacaoSelecionado(PontoAtracacao pontoAtracacaoSelecionado) {
        this.pontoAtracacaoSelecionado = pontoAtracacaoSelecionado;
        firePropertyChange("pontoAtracacaoSelecionado", null, this.pontoAtracacaoSelecionado);
    }
    

    public String getEscalas() {
        return escalas;
    }

    public void setEscalas(String escalas) {
        this.escalas = escalas;
        firePropertyChange("escalas", null, null);
    }
    
    public void carregarVO(){
        
        declaracaoGeralCapitaniaVO.setNrDuv(agenciamento.getNumeroDUV());
        declaracaoGeralCapitaniaVO.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        declaracaoGeralCapitaniaVO.setPortoChegada(agenciamento.getPorto().getNomeCompleto());
        declaracaoGeralCapitaniaVO.setBandeiraEmbarcacao(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto());
        
        declaracaoGeralCapitaniaVO.setPortoOrigem(agenciamento.getPortoOrigem().getNomeCompleto());
        String destinoFormatado = (!agenciamento.getDestinoFormatado().isEmpty()) ? agenciamento.getDestinoFormatado() : "A confirmar";
        declaracaoGeralCapitaniaVO.setPortoDestino(destinoFormatado);
        
        StringBuilder sbInfoRegistro = new StringBuilder();
        if (agenciamento.getEmbarcacao().getNumeroRegistro() != null){
            sbInfoRegistro.append(agenciamento.getEmbarcacao().getNumeroRegistro());
        }
        
        if (agenciamento.getEmbarcacao().getDataRegistro() != null){
            sbInfoRegistro.append(sbInfoRegistro.toString().isEmpty() ? "" : ", ");  
            sbInfoRegistro.append(SistamDateUtils.formatShortDate(agenciamento.getEmbarcacao().getDataRegistro(), zone));
        }
        
        if (agenciamento.getEmbarcacao().getPortoRegistro() != null){
            sbInfoRegistro.append(sbInfoRegistro.toString().isEmpty() ? "" : ", ");  
            sbInfoRegistro.append(agenciamento.getEmbarcacao().getPortoRegistro().getNomeCompleto());
        }
        
        declaracaoGeralCapitaniaVO.setInfoRegistro(sbInfoRegistro.toString());
        
        declaracaoGeralCapitaniaVO.setAgente(agenciamento.getAgencia().getNomeEnderecoAgente());
        declaracaoGeralCapitaniaVO.setArqueacaoBruta(agenciamento.getEmbarcacao().getArqueacaoBruta());     
        declaracaoGeralCapitaniaVO.setArqueacaoLiquida(agenciamento.getEmbarcacao().getArqueacaoLiquida());

        declaracaoGeralCapitaniaVO.setLocalizacaoAtual(pontoAtracacaoSelecionado.getNome());
        
        this.setResumoCarga(agenciamento.getResumoOperacoes());

        declaracaoGeralCapitaniaVO.setResumoCarga(resumoCarga);
        declaracaoGeralCapitaniaVO.setResumoViagem(escalas);
        
        declaracaoGeralCapitaniaVO.setListaTripulantes(tripulantes.getId());
        declaracaoGeralCapitaniaVO.setListaPassageiros(passageiros.getId());
        declaracaoGeralCapitaniaVO.setPlanilhaGMDSS(planilhaGMDSS.getId());
        
        declaracaoGeralCapitaniaVO.setMunicipioResponsavel(agenciamento.getAgencia().getCidade().toString().trim());
        declaracaoGeralCapitaniaVO.setData(SistamDateUtils.formatDateByExtensive(new Date(), zone));
        
        declaracaoGeralCapitaniaVO.setNumeroProcessoDespacho(agenciamento.getNumeroProcessoDespacho());
       
    }
    
    
    public void carregarPontosAtracacao(){
        List<PontoAtracacao> pontos = getService().buscarPontosAtracacaolPorPorto(agenciamento.getPorto());
        pontos.add(0, null);
        setPontosAtracacao(pontos);
    }
    
    public void validarDeclaracaoGeralEntrada() {
        
        SistamPendencyManager pm = new SistamPendencyManager();
        validarInfoRegistro(pm);
        pm.assertNotNull(agenciamento.getAgenciementoViagem().getComandanteEntrada()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_VIAGEM_INFORME_COMANDANTE_ENTRADA);
        pm.assertNotNull(agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_INFORME_NUMERO_TRIPULANTES);
        pm.assertNotNull(agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_INFORME_NUMERO_PASSAGEIROS);
        pm.assertNotNull(pontoAtracacaoSelecionado).orRegister(TipoExcecao.DECLARACAO_GERAL_ENTRADA, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_POSICAO_NAVIO_NO_PORTO);
        pm.verifyAll();
    }
    
    
    public void validarDeclaracaoGeralDespacho() {
        
        SistamPendencyManager pm = new SistamPendencyManager();
        validarInfoRegistro(pm);
        pm.assertNotNull(agenciamento.getAgenciementoViagem().getComandanteSaida()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_VIAGEM_INFORME_COMANDANTE_SAIDA);
        pm.assertNotNull(agenciamento.getAgenciementoViagem().getNumeroPassageirosSaida()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_INFORME_NUMERO_PASSAGEIROS_SAIDA);
        pm.assertNotNull(agenciamento.getAgenciementoViagem().getNumeroTripulantesSaida()).orRegister(TipoExcecao.AGENCIAMENTO_VIAGEM, ConstantesI18N.AGENCIAMENTO_INFORME_NUMERO_TRIPULANTES_SAIDA);
        pm.assertNotNull(pontoAtracacaoSelecionado).orRegister(TipoExcecao.DECLARACAO_GERAL_ENTRADA, ConstantesI18N.COMUNICACAO_CAPITANIA_INFORME_POSICAO_NAVIO_NO_PORTO);
        pm.verifyAll();
    }
    
    private void carregarEscalasFormatadas(){
        List<Escala> listaEscala = getService().buscarUltimasEscalasDaEmbarcacao(agenciamento.getEmbarcacao(), 2);
        
        StringBuilder escalasFormatadas = new StringBuilder();
        for (Escala escala : listaEscala){
            if (escalasFormatadas.toString().isEmpty()){
                escalasFormatadas.append(escala.getId().getPorto().getNomeCompleto());
            }
            else{
                escalasFormatadas.append(" - ").append(escala.getId().getPorto().getNomeCompleto());
            }
        }
        escalasFormatadas.append("-").append(agenciamento.getPorto().getNomeCompleto());
        if (agenciamento.getPortoDestino() !=null){
            escalasFormatadas.append("-").append(agenciamento.getPortoDestino().getNomeCompleto());
        }
        setEscalas(escalasFormatadas.toString());
    }

    public void carregarEntradaVO() {
        declaracaoGeralCapitaniaVO.setDataHoraChegada(SistamDateUtils.formatDateComplete(agenciamento.getDataChegada(), zone));
        declaracaoGeralCapitaniaVO.setNomeComandante(agenciamento.getAgenciementoViagem().getComandanteEntrada());
        declaracaoGeralCapitaniaVO.setNrTripulanteEntrada( agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada().toString()  );
        declaracaoGeralCapitaniaVO.setNrPassageiroEntrada(agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada().toString());
    }
    public void carregarDespachoVO() {
        declaracaoGeralCapitaniaVO.setDataHoraSaida(SistamDateUtils.formatDateComplete(agenciamento.getDataSaida(), zone));
        declaracaoGeralCapitaniaVO.setNomeComandanteSaida(agenciamento.getAgenciementoViagem().getComandanteSaida());
        declaracaoGeralCapitaniaVO.setNrTripulanteSaida( agenciamento.getAgenciementoViagem().getNumeroTripulantesSaida().toString()  );
        declaracaoGeralCapitaniaVO.setNrPassageiroSaida(agenciamento.getAgenciementoViagem().getNumeroPassageirosSaida().toString());
    }
    
    public void registrarEmissaoEntrada(){
        getService().registrarEmissaoDeDocumento(TipoDocumento.DECLARACAO_GERAL_ENTRADA, agenciamento, false);
    }
    
    public void registrarEmissaoPedidoDespacho(){
        getService().registrarEmissaoDeDocumento(TipoDocumento.DECLARACAO_GERAL_PEDIDO_DESPACHO, agenciamento, false);
    }

    private void validarInfoRegistro(SistamPendencyManager pm) {
        pm.assertNotNull(agenciamento.getEmbarcacao().getNumeroRegistro()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_NR_REGISTRO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getDataRegistro()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_DT_REGISTRO);
        pm.assertNotNull(agenciamento.getEmbarcacao().getPortoRegistro()).orRegister(TipoExcecao.EMBARCACAO, ConstantesI18N.EMBARCACAO_PORTO_REGISTRO);
    }
    
}
