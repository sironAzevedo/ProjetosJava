package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.EmpresaMaritima;
import br.com.petrobras.sistam.common.entity.Servico;
import br.com.petrobras.sistam.common.entity.ServicoProtecao;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.enums.ResponsavelCusto;
import br.com.petrobras.sistam.common.enums.TipoResiduo;
import br.com.petrobras.sistam.common.enums.TipoUnidadeMedida;
import java.math.BigDecimal;
import java.util.Date;

public class RetiradaResiduoLixoBuilder {

    private ServicoRetiradaResiduoLixo retiradaResiduoLixo;
    
    private RetiradaResiduoLixoBuilder(ServicoRetiradaResiduoLixo retiradaResiduoLixo) {
        this.retiradaResiduoLixo = retiradaResiduoLixo;
    }
    
    public static RetiradaResiduoLixoBuilder novaRetiradaResiduoLixo() {
        return new RetiradaResiduoLixoBuilder(new ServicoRetiradaResiduoLixo());
    }
    
    public ServicoRetiradaResiduoLixo build() {
        return this.retiradaResiduoLixo;
    }
    
    public RetiradaResiduoLixoBuilder comId(Long id) {
        this.retiradaResiduoLixo.setId(id);
        return this;
    }

    public RetiradaResiduoLixoBuilder comResponsavelCusto(ResponsavelCusto responsavelCusto){
        this.retiradaResiduoLixo.setResponsavelCusto(responsavelCusto);
        return this;
    }

    public RetiradaResiduoLixoBuilder comEmpresaMaritima(EmpresaMaritima empresa) {
        this.retiradaResiduoLixo.setEmpresaMaritima(empresa);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comEmpresaServico(Servico servico) {
        this.retiradaResiduoLixo.setEmpresaServico(servico);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comEmpresaMaritimaRodoviaria(EmpresaMaritima empresa) {
        this.retiradaResiduoLixo.setEmpresaMaritimaRodoviaria(empresa);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comTipoResiduo(TipoResiduo tipoResiduo) {
        this.retiradaResiduoLixo.setTipoResiduo(tipoResiduo);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comCaracterizacao(String caracterizacao) {
        this.retiradaResiduoLixo.setCaracterizacao(caracterizacao);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comEstadoFisico(String estadoFisico) {
        this.retiradaResiduoLixo.setEstadoFisico(estadoFisico);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comClassificacao(String classificacao) {
        this.retiradaResiduoLixo.setClassificacao(classificacao);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comCodigoOnu(String codigoOnu) {
        this.retiradaResiduoLixo.setCodigoOnu(codigoOnu);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comQuantidadeResiduo(Integer quantidadeResiduo) {
        this.retiradaResiduoLixo.setQuantidadeResiduo(quantidadeResiduo);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comTipoUnidadeMedida(TipoUnidadeMedida tipoUnidadeMedida) {
        this.retiradaResiduoLixo.setTipoUnidadeMedida(tipoUnidadeMedida);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comValorServico(BigDecimal valorServico) {
        this.retiradaResiduoLixo.setValorServico(valorServico);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comDataInicioOperacao(Date dataInicioOperacao) {
        this.retiradaResiduoLixo.setDataInicioOperacao(dataInicioOperacao);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comDataFimOperacao(Date dataFimOperacao) {
        this.retiradaResiduoLixo.setDataFimOperacao(dataFimOperacao);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comLocalArmazenagem(String localArmazenagem) {
        this.retiradaResiduoLixo.setLocalArmazenagem(localArmazenagem);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comLonArmazenagem(String lonArmazenagem) {
        this.retiradaResiduoLixo.setLonArmazenagem(lonArmazenagem);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comDescCadri(String DescCadri) {
        this.retiradaResiduoLixo.setDescCadri(DescCadri);
        return this;
    } 
    
    public RetiradaResiduoLixoBuilder comLocalDestinoFinal(String localDestinoFinal) {
        this.retiradaResiduoLixo.setLocalDestinoFinal(localDestinoFinal);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comLonDestinoFinal (String lonDestinoFinal) {
        this.retiradaResiduoLixo.setLonDestinoFinal(lonDestinoFinal);
        return this;
    }
    
    public RetiradaResiduoLixoBuilder comCadriDestinoFinal (String cadriDestinoFinal) {
        this.retiradaResiduoLixo.setCadriDestinoFinal(cadriDestinoFinal);
        return this;
    } 
    
    public RetiradaResiduoLixoBuilder comServicoProtecao(ServicoProtecao servicoProtecao) {
        this.retiradaResiduoLixo.setServicoProtecao(servicoProtecao);
        return this;
    } 

}
