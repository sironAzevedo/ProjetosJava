package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Embarcacao;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.enums.SituacaoProcesso;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoArmador;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoFrota;
import java.lang.reflect.Field;
import java.util.Date;
import org.unitils.util.ReflectionUtils;

public class AgenciamentoBuilder {

    private Agenciamento agenciamento;
    
    
    private AgenciamentoBuilder(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        agenciamento.setAgenciamentoCarga(true);
        agenciamento.setAgenciamentoProtecao(true);
        agenciamento.setAgenciamentoPlataforma(false);
        agenciamento.setTipoArmador(TipoArmador.TRASNPETRO); 
        agenciamento.setTipoContrato(TipoContrato.TCP);
        agenciamento.setAgenciamentoCarga(true);
        agenciamento.setSituacaoProcesso(SituacaoProcesso.EM_ANDAMENTO);
        agenciamento.setDataInclusao(new Date());
        comVersao(1L);
    }
    
    public static AgenciamentoBuilder novoAgenciamento() {
        return new AgenciamentoBuilder(new Agenciamento());
    }
    
    public Agenciamento build() {
        return this.agenciamento;
    }

    public AgenciamentoBuilder comId(Long id) {
        this.agenciamento.setId(id);
        return this;
    }
    
    public AgenciamentoBuilder comVersao(Long id){
        Field field = ReflectionUtils.getFieldWithName(Agenciamento.class, "versao", false);
        ReflectionUtils.setFieldValue(agenciamento, field, id);
        return this;
    }
    
    public AgenciamentoBuilder comAgencia(Agencia agencia) {
        this.agenciamento.setAgencia(agencia);
        return this;
    }
    
    public AgenciamentoBuilder comPortoOrigem(Porto porto) {
        this.agenciamento.setPortoOrigem(porto);
        return this;
    }

    public AgenciamentoBuilder comPortoDestino(Porto porto) {
        this.agenciamento.setPortoDestino(porto);
        return this;
    }
    public AgenciamentoBuilder comDestinoIntermediario(String destinoIntermediario) {
        this.agenciamento.setDestinoIntermediario(destinoIntermediario);
        return this;
    }
    
    public AgenciamentoBuilder comStatusEmbarcacao(StatusEmbarcacao staus) {
        this.agenciamento.setStatusEmbarcacao(staus);
        return this;
    }
    
    public AgenciamentoBuilder comTipoFrota(TipoFrota tipoFrota) {
        this.agenciamento.setTipoFrota(tipoFrota);
        return this;
    }
    
    public AgenciamentoBuilder comDataChegada(Date dataChegada) {
        this.agenciamento.setDataChegada(dataChegada);
        return this;
    }    
    
     public AgenciamentoBuilder comEmbarcacao(Embarcacao embarcacao) {
        this.agenciamento.setEmbarcacao(embarcacao);
        return this;
    }    
     
      public AgenciamentoBuilder comDataSaida(Date dataSaida) {
        this.agenciamento.setDataSaida(dataSaida);
        return this;
    } 
      
    public AgenciamentoBuilder comViagem(String viagem){
        this.agenciamento.setVgm(viagem);
        return this;
    }
    
    public AgenciamentoBuilder comViagemSaida(String viagem){
        this.agenciamento.setVgmSaida(viagem);
        return this;
    }
    
    public AgenciamentoBuilder comPortoAtual(Porto porto){
        this.agenciamento.setPorto(porto);
        return this;
    }
    
    public AgenciamentoBuilder comDataEstimadaDeChegada(Date data){
        this.agenciamento.setEta(data);
        return this;
    }
    
    public AgenciamentoBuilder comAnoProcesso(Integer anoProcesso){
        this.agenciamento.setAnoProcesso(anoProcesso);
        return this;
    }
    
    public AgenciamentoBuilder comCodigo(Long codigo){
        this.agenciamento.setCodigo(codigo);
        return this;
    }
    
    public AgenciamentoBuilder comEta(Date eta){
        this.agenciamento.setEta(eta);
        return this;
    }

    public AgenciamentoBuilder comDataInclusao(Date data){
        this.agenciamento.setDataInclusao(data);
        return this;
    }

    public AgenciamentoBuilder comChaveCadastrador(String chave){
        this.agenciamento.setChaveCadastrador(chave);
        return this;
    }

    public AgenciamentoBuilder comNomeCadastrador(String nome){
        this.agenciamento.setNomeCadastrador(nome);
        return this;
    }
    
    public AgenciamentoBuilder comTipoContrato(TipoContrato tipo){
        this.agenciamento.setTipoContrato(tipo);
        return this;
    }
    
    public AgenciamentoBuilder comAgenciamentoCarga(){
        this.agenciamento.setAgenciamentoCarga(true);
        return this;
    }   
    
    
    public AgenciamentoBuilder comCodigo(long codigo){
        this.agenciamento.setCodigo(codigo);
        return this;
    }
     
    public AgenciamentoBuilder comAnoPorcesso(Integer anoProcesso){
        this.agenciamento.setAnoProcesso(anoProcesso);
        return this;
    }
}
