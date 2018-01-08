package br.com.petrobras.sistam.common.valueobjects;

import br.com.petrobras.fcorp.common.beans.AbstractBean;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.enums.TipoControleFiscalizacaoUnico;
import br.com.petrobras.sistam.common.enums.TipoControleFiscalizacaoUnicoEntradaSaida;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import java.io.Serializable;
import java.util.Date;

public class FormularioControleFiscalizacaoUnicoVO extends AbstractBean implements Serializable {

  //INFORMAÇÕES COMPLEMENTARES
    private String armadorCnpj;
    private String agenteArmador;
    private String nrEscala;
    private String produtoCarga;
    private Integer saidaTransito;
    private Integer clandestinoImpedidos;
    //DADOS EM COMUN AGENCIA DE MANAUS E OUTROS
    private String nomeEmbarcacao;
    private String nacionalidade;
    private String consignatarioCnpj;
    private Date dataChegadaPorto;
    private Date dataPartidaPorto;
    private Date horaChegadaPorto;
    private Date horaPartidaPorto;
    private String procedencia;
    private String destino;
    private String tonelagemLiquida;
    private String tonelagemBruta;
    private String despachado;
    private TipoControleFiscalizacaoUnico tipoControleFiscalizacaoUnico;
    private TipoControleFiscalizacaoUnicoEntradaSaida controleFiscalizacaoUnicoEntradaSaida;
    private boolean taxaPagaEntrada;
    private boolean taxaPagaSaida;
    private Date dataDocumento;
    private String agenteConsignatarioCnpj;
    //DADOS ESPECIFICO PARA AGENCIA DE MANAUS
    private String imo;
    private String irin;
    private String classe;
    private String portoEntradaBrasil;
    private Date portoEntradaBrasilData;
    private Date chegadaPortoEta;
    private String armadorEmbarcacao;
    
    
    
//<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        this.nomeEmbarcacao = nomeEmbarcacao;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getConsignatarioCnpj() {
        return consignatarioCnpj;
    }

    public void setConsignatarioCnpj(String consignatarioCnpj) {
        this.consignatarioCnpj = consignatarioCnpj;
    }

    public String getArmadorCnpj() {
        return armadorCnpj;
    }

    public void setArmadorCnpj(String armadorCnpj) {
        this.armadorCnpj = armadorCnpj;
    }

    public String getAgenteArmador() {
        return agenteArmador;
    }

    public void setAgenteArmador(String agenteArmador) {
        this.agenteArmador = agenteArmador;
    }

    public String getDataChegadaPorto() {
        return dataChegadaPorto == null ? " - " : SistamDateUtils.formatDate(dataChegadaPorto, SistamDateUtils.DATE_HOUR_PATTERN, null);
    }

    public void setDataChegadaPorto(Date dataChegadaPorto) {
        this.dataChegadaPorto = dataChegadaPorto;
    }

    public String getDataPartidaPorto() {
        return dataPartidaPorto == null ? " - " : SistamDateUtils.formatDate(dataPartidaPorto, SistamDateUtils.DATE_HOUR_PATTERN, null);
    }

    public void setDataPartidaPorto(Date dataPartidaPorto) {
        this.dataPartidaPorto = dataPartidaPorto;
    }

    public String getHoraChegadaPorto() {
        return horaChegadaPorto == null ? " - " : (SistamDateUtils.formatDate(horaChegadaPorto, SistamDateUtils.HOUR_PATTERN, null) + "h");
    }

    public void setHoraChegadaPorto(Date horaChegadaPorto) {
        this.horaChegadaPorto = horaChegadaPorto;
    }

    public String getHoraPartidaPorto() {
        return horaPartidaPorto == null ? " - " : (SistamDateUtils.formatDate(horaPartidaPorto, SistamDateUtils.HOUR_PATTERN, null) + "h");
    }

    public void setHoraPartidaPorto(Date horaPartidaPorto) {
        this.horaPartidaPorto = horaPartidaPorto;
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    } 
    
    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getNrEscala() {
        return nrEscala;
    }

    public void setNrEscala(String nrEscala) {
        this.nrEscala = nrEscala;
    }

    public String getProdutoCarga() {
        return produtoCarga;
    }

    public void setProdutoCarga(String produtoCarga) {
        this.produtoCarga = produtoCarga;
    }

    public String getTonelagemLiquida() {
        return tonelagemLiquida;
    }

    public void setTonelagemLiquida(String tonelagemLiquida) {
        this.tonelagemLiquida = tonelagemLiquida;
    }

    public String getTonelagemBruta() {
        return tonelagemBruta;
    }

    public void setTonelagemBruta(String tonelagemBruta) {
        this.tonelagemBruta = tonelagemBruta;
    }  

    public Integer getSaidaTransito() {
        return saidaTransito;
    }

    public void setSaidaTransito(Integer saidaTransito) {
        this.saidaTransito = saidaTransito;
    }

    public Integer getClandestinoImpedidos() {
        return clandestinoImpedidos;
    }

    public void setClandestinoImpedidos(Integer clandestinoImpedidos) {
        this.clandestinoImpedidos = clandestinoImpedidos;
    }

    public String getDespachado() {
        return despachado;
    }

    public void setDespachado(String despachado) {
        this.despachado = despachado;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getIrin() {
        return irin;
    }

    public void setIrin(String irin) {
        this.irin = irin;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getPortoEntradaBrasil() {
        return portoEntradaBrasil;
    }

    public void setPortoEntradaBrasil(String portoEntradaBrasil) {
        this.portoEntradaBrasil = portoEntradaBrasil;
    }

    public String getPortoEntradaBrasilData() {
        return portoEntradaBrasilData == null ? "-" : SistamDateUtils.formatDate(portoEntradaBrasilData, SistamDateUtils.DATE_PATTERN, null);
    }

    public void setPortoEntradaBrasilData(Date portoEntradaBrasilData) {
        this.portoEntradaBrasilData = portoEntradaBrasilData;
    }

    public String getChegadaPortoEta() {
        return chegadaPortoEta == null ? "-" : SistamDateUtils.formatDate(chegadaPortoEta, SistamDateUtils.DATE_PATTERN, null);
    }

    public void setChegadaPortoEta(Date chegadaPortoEta) {
        this.chegadaPortoEta = chegadaPortoEta;
    }

    public TipoControleFiscalizacaoUnico getTipoControleFiscalizacaoUnico() {
        return tipoControleFiscalizacaoUnico;
    }

    public void setTipoControleFiscalizacaoUnico(TipoControleFiscalizacaoUnico tipoControleFiscalizacaoUnico) {
        this.tipoControleFiscalizacaoUnico = tipoControleFiscalizacaoUnico;
        firePropertyChange("tipoControleFiscalizacaoUnico", null, null);
    }

    public TipoControleFiscalizacaoUnicoEntradaSaida getControleFiscalizacaoUnicoEntradaSaida() {
        return controleFiscalizacaoUnicoEntradaSaida;
    }

    public void setControleFiscalizacaoUnicoEntradaSaida(TipoControleFiscalizacaoUnicoEntradaSaida controleFiscalizacaoUnicoEntradaSaida) {
        this.controleFiscalizacaoUnicoEntradaSaida = controleFiscalizacaoUnicoEntradaSaida;
    }

    public boolean isTaxaPagaEntrada() {
        return taxaPagaEntrada;
    }

    public void setTaxaPagaEntrada(boolean taxaPagaEntrada) {
        this.taxaPagaEntrada = taxaPagaEntrada;
    }

    public boolean isTaxaPagaSaida() {
        return taxaPagaSaida;
    }

    public void setTaxaPagaSaida(boolean taxaPagaSaida) {
        this.taxaPagaSaida = taxaPagaSaida;
    }

    public String getArmadorEmbarcacao() {
        return armadorEmbarcacao;
    }

    public void setArmadorEmbarcacao(String armadorEmbarcacao) {
        this.armadorEmbarcacao = armadorEmbarcacao;
    }

    public String getAgenteConsignatarioCnpj() {
        return agenteConsignatarioCnpj;
    }

    public void setAgenteConsignatarioCnpj(String agenteConsignatarioCnpj) {
        this.agenteConsignatarioCnpj = agenteConsignatarioCnpj;
    }  
    
    //</editor-fold> 
    

    public boolean isPasseEntrada() {
        return TipoControleFiscalizacaoUnico.PASSE_ENTRADA.equals(tipoControleFiscalizacaoUnico); 
    }
    
    public boolean isPasseSaida(){
        return TipoControleFiscalizacaoUnico.PASSE_SAIDA.equals(tipoControleFiscalizacaoUnico);
    }
    
    public boolean isSolicitacaoPasseEntrada(){
        return TipoControleFiscalizacaoUnico.SOLICITACAO_PASSE_ENTRADA.equals(tipoControleFiscalizacaoUnico);
    }
    
    public boolean isSolicitacaoPasseSaida(){
        return TipoControleFiscalizacaoUnico.SOLICITACAO_PASSE_SAIDA.equals(tipoControleFiscalizacaoUnico);
    }
    
    public boolean isRegistroCabotagem(){
        return TipoControleFiscalizacaoUnico.REGISTRO_CABOTAGEM.equals(tipoControleFiscalizacaoUnico);
    }
    
    public boolean isComunicacaoRegistroCabotagem(){
        return TipoControleFiscalizacaoUnico.COMUNICAO_CABOTAGEM_LONGO_CURSO.equals(tipoControleFiscalizacaoUnico);
    }
    
    public boolean isPedidoVisita(){
        return TipoControleFiscalizacaoUnico.PEDIDO_VISITA.equals(tipoControleFiscalizacaoUnico);
    }
    
    public boolean isEntrada(){
        return TipoControleFiscalizacaoUnicoEntradaSaida.ENTRADA.equals(controleFiscalizacaoUnicoEntradaSaida);
    }
    
    public boolean isSaida(){
        return TipoControleFiscalizacaoUnicoEntradaSaida.SAIDA.equals(controleFiscalizacaoUnicoEntradaSaida);
    }
    
    public boolean isRegistroCabotagemOuLongoCursoEntrada(){
        return (this.isRegistroCabotagem() || this.isComunicacaoRegistroCabotagem()) && this.isEntrada();
    }

    public boolean isRegistroCabotagemOuLongoCursoSaida(){
        return (this.isRegistroCabotagem() || this.isComunicacaoRegistroCabotagem()) && this.isSaida();
    }    

    public boolean isPasseEntradaOuSolicitacaoPasseEntrada(){
        return (this.isPasseEntrada() || this.isSolicitacaoPasseEntrada());
    }

    public boolean isPasseSaidaOuSolicitacaoPasseSaida(){
        return (this.isPasseSaida()|| this.isSolicitacaoPasseSaida());
    }    

}
