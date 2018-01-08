package br.com.petrobras.sistam.common.valueobjects;

import java.io.Serializable;

public class LiberacaoAnvisaVO implements Serializable {

    private String cvpaf;
    private String porto;
    private Boolean livrePratica;
    private Boolean controleSanitarioBordo;
    private Boolean nacionalControleSanitarioBordo;
    private String nomeResponsavel;
    private String cnpjCpfPassaporteResponsavel;
    private String enderecoResponsavel;
    private String bairroResponsavel;
    private String telefoneResponsavel;
    private String afeAnvisa;
    private String cepResponsavel;
    private String municipioResponsavel;
    private String ufResponsavel;
    private String faxResponsavel;
    private String emailResponsavel;
    private String imo;
    private String finalidadeEmbarcacao;
    private Boolean possuiCcsbCicsbValido;
    private Boolean existePendenciaCCSB;

  
    private Long arqueacaoLiquida;
    private Long arqueacaoBruta;
    private String nomeEmbarcacao;
    private String bandeiraEmbarcacao;
    private Boolean possuiCncsbCnicsbValido;
    private Boolean possuiClpValido;
    private String dataPortoEmissaoCcsbCicsb;

    private String dataEmissaoCcsbCicsb;
    private String portoEmissaoCcsbCicsb;
    private String dataValidadeCcsbCicsb;
    private String nrCertificadoCcsbCicsb;

    private String dataEmissaoCncsbCnicsb;
    private String portoEmissaoCncsbCnicsb;
    private String dataValidadeCncsbCnicsb;
    private String nrCertificadoCncsbCnicsb;
    
    private String dataPortoEmissaoCnCsbCnicsb;
    
    private String dataPortoEmissaoClp;
    private String dataEmissaoClp;
    private String dataValidadeClp;
    private String nrCertificadoClp;
    private String portoEmissaoClp;
    
    private String dataChegada;
    private String dataEstimadaSaida;
    private String portoDestino;
    private String numeroTripulantes;
    private String eta;
    private String paisPortoDestino;
    private String numeroPassageiros;
    private String nomeComandante;
    private String nacionalidadeComandante;
    private Boolean ocorrenciaObito;
    private Boolean houveSepultamento;
    private Boolean ocorrenciaDoenca;
    private Boolean febreHemorragia;
    private Boolean sinaisIctericia;
    private Boolean sinaisDiarreia;
    private Boolean sinaisDisfuncoesNeurologicas;
    private Boolean sinaisTosseDificuldadeRespiratoria;
    private Boolean ocorrenciaAcidenteBordo;
    private String acidenteOcorridoBordo;
    private Boolean ocorrenciaMortandadeRoedores;
    private String compartimentoMortandadeRoedores;
    private Boolean ocorrenciaConsumoMedicamentos;
    private String nomeMedicamento;
    private String portoUltimoConsumoAguaPotavel;
    private Boolean produzAguaPotavel;
    private Boolean possuiSistemaTratamentoAguaPotavel;

     private Double capacidadeMaximaArmazenamentoAguaPotavel;
    private Boolean possuiAguaLastroBordo;
    private Boolean efetuadaSubstituicaoAguaLastro;
    private String localUltimaSubstituicao;
    private String latitudeUltimaSubstituicaoAguaLastro;
    private String longitudeUltimaSubstituicaoAguaLastro;
    private Boolean haveraDelastro;   
    private Boolean possuiTanqueRetencaoTratamentoAfluentes;
    private Double capacidadeArmazenamentoAfluentes;
    private Double autonomiaRetencao;
    private Boolean transportaCargaPerigosa;
    private Boolean ocorrenciaDesinsetizacao;
    private String produtoDesinsetizacao;
    private String dataDesinsetizacao;
    private Boolean haveraRetiradaResiduosSolidos;
    private Boolean haveraAbastecimentoAguaPotavel;
    private Boolean haveraAbastecimentoAlimentos;
    private String dataDeposito;
    private String localRepresentante;
    private String nomeRepresentante;
    private String dataRepresentante;
    private String passaporteRepresentante;
    private boolean tratamentoAguaPotavel;
    private boolean aguaLastro;
    private boolean subAguaLastro;
    private String longitudeSubstAgua;
    private String latitudeSubstAgua;
    private boolean deslastro;
    private String escalaFormatada;

    

    public String getCvpaf() {
        return cvpaf;
    }

    public void setCvpaf(String cvpaf) {
        this.cvpaf = cvpaf;
    }

    public Boolean getLivrePratica() {
        return livrePratica;
    }

    public void setLivrePratica(Boolean livrePratica) {
        this.livrePratica = livrePratica;
    }

    public Boolean getControleSanitarioBordo() {
        return controleSanitarioBordo;
    }

    public void setControleSanitarioBordo(Boolean controleSanitarioBordo) {
        this.controleSanitarioBordo = controleSanitarioBordo;
    }

    public Boolean getNacionalControleSanitarioBordo() {
        return nacionalControleSanitarioBordo;
    }

    public void setNacionalControleSanitarioBordo(Boolean nacionalControleSanitarioBordo) {
        this.nacionalControleSanitarioBordo = nacionalControleSanitarioBordo;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCnpjCpfPassaporteResponsavel() {
        return cnpjCpfPassaporteResponsavel;
    }

    public void setCnpjCpfPassaporteResponsavel(String cnpjCpfPassaporteResponsavel) {
        this.cnpjCpfPassaporteResponsavel = cnpjCpfPassaporteResponsavel;
    }

    public String getEnderecoResponsavel() {
        return enderecoResponsavel;
    }

    public void setEnderecoResponsavel(String enderecoResponsavel) {
        this.enderecoResponsavel = enderecoResponsavel;
    }

    public String getBairroResponsavel() {
        return bairroResponsavel;
    }

    public void setBairroResponsavel(String bairroResponsavel) {
        this.bairroResponsavel = bairroResponsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public String getAfeAnvisa() {
        return afeAnvisa;
    }

    public void setAfeAnvisa(String afeAnvisa) {
        this.afeAnvisa = afeAnvisa;
    }

    public String getCepResponsavel() {
        return cepResponsavel;
    }

    public void setCepResponsavel(String cepResponsavel) {
        this.cepResponsavel = cepResponsavel;
    }

    public String getMunicipioResponsavel() {
        return municipioResponsavel;
    }

    public void setMunicipioResponsavel(String municipioResponsavel) {
        this.municipioResponsavel = municipioResponsavel;
    }

    public String getUfResponsavel() {
        return ufResponsavel;
    }

    public void setUfResponsavel(String ufResponsavel) {
        this.ufResponsavel = ufResponsavel;
    }

    public String getFaxResponsavel() {
        return faxResponsavel;
    }

    public void setFaxResponsavel(String faxResponsavel) {
        this.faxResponsavel = faxResponsavel;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getFinalidadeEmbarcacao() {
        return finalidadeEmbarcacao;
    }

    public void setFinalidadeEmbarcacao(String finalidadeEmbarcacao) {
        this.finalidadeEmbarcacao = finalidadeEmbarcacao;
    }

    public Boolean getPossuiCcsbCicsbValido() {
        return possuiCcsbCicsbValido;
    }

    public void setPossuiCcsbCicsbValido(Boolean possuiCcsbCicsbValido) {
        this.possuiCcsbCicsbValido = possuiCcsbCicsbValido;
    }
     
    public Boolean getExistePendenciaCCSB() {
        return existePendenciaCCSB;
    }

    public void setExistePendenciaCCSB(Boolean existePendenciaCCSB) {
        this.existePendenciaCCSB = existePendenciaCCSB;
    }

    public Long getArqueacaoLiquida() {
        return arqueacaoLiquida;
    }

    public void setArqueacaoLiquida(Long arqueacaoLiquida) {
        this.arqueacaoLiquida = arqueacaoLiquida;
    }

    public Long getArqueacaoBruta() {
        return arqueacaoBruta;
    }

    public void setArqueacaoBruta(Long arqueacaoBruta) {
        this.arqueacaoBruta = arqueacaoBruta;
    }

    public String getPorto() {
        return porto;
    }

    public void setPorto(String porto) {
        this.porto = porto;
    }

    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        this.nomeEmbarcacao = nomeEmbarcacao;
    }

    public String getBandeiraEmbarcacao() {
        return bandeiraEmbarcacao;
    }

    public void setBandeiraEmbarcacao(String bandeiraEmbarcacao) {
        this.bandeiraEmbarcacao = bandeiraEmbarcacao;
    }

    public Boolean getPossuiCncsbCnicsbValido() {
        return possuiCncsbCnicsbValido;
    }

    public void setPossuiCncsbCnicsbValido(Boolean possuiCncsbCnicsbValido) {
        this.possuiCncsbCnicsbValido = possuiCncsbCnicsbValido;
    }

    public Boolean getPossuiClpValido() {
        return possuiClpValido;
    }

    public void setPossuiClpValido(Boolean possuiClpValido) {
        this.possuiClpValido = possuiClpValido;
    }

    public String getDataPortoEmissaoCcsbCicsb() {
        return dataPortoEmissaoCcsbCicsb;
    }

    public void setDataPortoEmissaoCcsbCicsb(String dataPortoEmissaoCcsbCicsb) {
        this.dataPortoEmissaoCcsbCicsb = dataPortoEmissaoCcsbCicsb;
    }

    public String getDataPortoEmissaoCnCsbCnicsb() {
        return dataPortoEmissaoCnCsbCnicsb;
    }

    public void setDataPortoEmissaoCnCsbCnicsb(String dataPortoEmissaoCnCsbCnicsb) {
        this.dataPortoEmissaoCnCsbCnicsb = dataPortoEmissaoCnCsbCnicsb;
    }

    public String getDataPortoEmissaoClp() {
        return dataPortoEmissaoClp;
    }

    public void setDataPortoEmissaoClp(String dataPortoEmissaoClp) {
        this.dataPortoEmissaoClp = dataPortoEmissaoClp;
    }
    public String getDataValidadeCcsbCicsb() {
        return dataValidadeCcsbCicsb;
    }

    public void setDataValidadeCcsbCicsb(String dataValidadeCcsbCicsb) {
        this.dataValidadeCcsbCicsb = dataValidadeCcsbCicsb;
    }

    public String getNrCertificadoCcsbCicsb() {
        return nrCertificadoCcsbCicsb;
    }

    public void setNrCertificadoCcsbCicsb(String nrCertificadoCcsbCicsb) {
        this.nrCertificadoCcsbCicsb = nrCertificadoCcsbCicsb;
    }

    public String getDataValidadeClp() {
        return dataValidadeClp;
    }

    public void setDataValidadeClp(String dataValidadeClp) {
        this.dataValidadeClp = dataValidadeClp;
    }

    public String getNrCertificadoClp() {
        return nrCertificadoClp;
    }

    public void setNrCertificadoClp(String nrCertificadoClp) {
        this.nrCertificadoClp = nrCertificadoClp;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }

    public String getDataEstimadaSaida() {
        return dataEstimadaSaida;
    }

    public void setDataEstimadaSaida(String dataEstimadaSaida) {
        this.dataEstimadaSaida = dataEstimadaSaida;
    }

    public String getPortoDestino() {
        return portoDestino;
    }

    public void setPortoDestino(String portoDestino) {
        this.portoDestino = portoDestino;
    }

    public String getNumeroTripulantes() {
        return numeroTripulantes;
    }

    public void setNumeroTripulantes(String numeroTripulantes) {
        this.numeroTripulantes = numeroTripulantes;
    }
   
public String getDataEmissaoCcsbCicsb() {
        return dataEmissaoCcsbCicsb;
    }

    public void setDataEmissaoCcsbCicsb(String dataEmissaoCcsbCicsb) {
        this.dataEmissaoCcsbCicsb = dataEmissaoCcsbCicsb;
    }

    public String getPortoEmissaoCcsbCicsb() {
        return portoEmissaoCcsbCicsb;
    }

    public void setPortoEmissaoCcsbCicsb(String portoEmissaoCcsbCicsb) {
        this.portoEmissaoCcsbCicsb = portoEmissaoCcsbCicsb;
    }

    public String getDataEmissaoCncsbCnicsb() {
        return dataEmissaoCncsbCnicsb;
    }

    public void setDataEmissaoCncsbCnicsb(String dataEmissaoCncsbCnicsb) {
        this.dataEmissaoCncsbCnicsb = dataEmissaoCncsbCnicsb;
    }

    public String getPortoEmissaoCncsbCnicsb() {
        return portoEmissaoCncsbCnicsb;
    }

    public void setPortoEmissaoCncsbCnicsb(String portoEmissaoCncsbCnicsb) {
        this.portoEmissaoCncsbCnicsb = portoEmissaoCncsbCnicsb;
    }

    public String getDataValidadeCncsbCnicsb() {
        return dataValidadeCncsbCnicsb;
    }

    public void setDataValidadeCncsbCnicsb(String dataValidadeCncsbCnicsb) {
        this.dataValidadeCncsbCnicsb = dataValidadeCncsbCnicsb;
    }

    public String getNrCertificadoCncsbCnicsb() {
        return nrCertificadoCncsbCnicsb;
    }

    public void setNrCertificadoCncsbCnicsb(String nrCertificadoCncsbCnicsb) {
        this.nrCertificadoCncsbCnicsb = nrCertificadoCncsbCnicsb;
    }

    public String getDataEmissaoClp() {
        return dataEmissaoClp;
    }

    public void setDataEmissaoClp(String dataEmissaoClp) {
        this.dataEmissaoClp = dataEmissaoClp;
    }

    public String getPortoEmissaoClp() {
        return portoEmissaoClp;
    }

    public void setPortoEmissaoClp(String portoEmissaoClp) {
        this.portoEmissaoClp = portoEmissaoClp;
    }
    
    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getPaisPortoDestino() {
        return paisPortoDestino;
    }

    public void setPaisPortoDestino(String paisPortoDestino) {
        this.paisPortoDestino = paisPortoDestino;
    }

    public String getNumeroPassageiros() {
        return numeroPassageiros;
    }

    public void setNumeroPassageiros(String numeroPassageiros) {
        this.numeroPassageiros = numeroPassageiros;
    }

    public String getNomeComandante() {
        return nomeComandante;
    }

    public void setNomeComandante(String nomeComandante) {
        this.nomeComandante = nomeComandante;
    }

    public String getNacionalidadeComandante() {
        return nacionalidadeComandante;
    }

    public void setNacionalidadeComandante(String nacionalidadeComandante) {
        this.nacionalidadeComandante = nacionalidadeComandante;
    }

    public Boolean getOcorrenciaObito() {
        return ocorrenciaObito;
    }

    public void setOcorrenciaObito(Boolean ocorrenciaObito) {
        this.ocorrenciaObito = ocorrenciaObito;
    }

    public Boolean getHouveSepultamento() {
        return houveSepultamento;
    }

    public void setHouveSepultamento(Boolean houveSepultamento) {
        this.houveSepultamento = houveSepultamento;
    }

    public Boolean getOcorrenciaDoenca() {
        return ocorrenciaDoenca;
    }

    public void setOcorrenciaDoenca(Boolean ocorrenciaDoenca) {
        this.ocorrenciaDoenca = ocorrenciaDoenca;
    }

    public Boolean getFebreHemorragia() {
        return febreHemorragia;
    }

    public void setFebreHemorragia(Boolean febreHemorragia) {
        this.febreHemorragia = febreHemorragia;
    }

    public Boolean getSinaisIctericia() {
        return sinaisIctericia;
    }

    public void setSinaisIctericia(Boolean sinaisIctericia) {
        this.sinaisIctericia = sinaisIctericia;
    }

    public Boolean getSinaisDiarreia() {
        return sinaisDiarreia;
    }

    public void setSinaisDiarreia(Boolean sinaisDiarreia) {
        this.sinaisDiarreia = sinaisDiarreia;
    }

    public Boolean getSinaisDisfuncoesNeurologicas() {
        return sinaisDisfuncoesNeurologicas;
    }

    public void setSinaisDisfuncoesNeurologicas(Boolean sinaisDisfuncoesNeurologicas) {
        this.sinaisDisfuncoesNeurologicas = sinaisDisfuncoesNeurologicas;
    }

    public Boolean getSinaisTosseDificuldadeRespiratoria() {
        return sinaisTosseDificuldadeRespiratoria;
    }

    public void setSinaisTosseDificuldadeRespiratoria(Boolean sinaisTosseDificuldadeRespiratoria) {
        this.sinaisTosseDificuldadeRespiratoria = sinaisTosseDificuldadeRespiratoria;
    }

    public Boolean getOcorrenciaAcidenteBordo() {
        return ocorrenciaAcidenteBordo;
    }

    public void setOcorrenciaAcidenteBordo(Boolean ocorrenciaAcidenteBordo) {
        this.ocorrenciaAcidenteBordo = ocorrenciaAcidenteBordo;
    }

    public String getAcidenteOcorridoBordo() {
        return acidenteOcorridoBordo;
    }

    public void setAcidenteOcorridoBordo(String acidenteOcorridoBordo) {
        this.acidenteOcorridoBordo = acidenteOcorridoBordo;
    }

    public Boolean getOcorrenciaMortandadeRoedores() {
        return ocorrenciaMortandadeRoedores;
    }

    public void setOcorrenciaMortandadeRoedores(Boolean ocorrenciaMortandadeRoedores) {
        this.ocorrenciaMortandadeRoedores = ocorrenciaMortandadeRoedores;
    }

    public String getCompartimentoMortandadeRoedores() {
        return compartimentoMortandadeRoedores;
    }

    public void setCompartimentoMortandadeRoedores(String compartimentoMortandadeRoedores) {
        this.compartimentoMortandadeRoedores = compartimentoMortandadeRoedores;
    }

    public Boolean getOcorrenciaConsumoMedicamentos() {
        return ocorrenciaConsumoMedicamentos;
    }

    public void setOcorrenciaConsumoMedicamentos(Boolean ocorrenciaConsumoMedicamentos) {
        this.ocorrenciaConsumoMedicamentos = ocorrenciaConsumoMedicamentos;
    }

    public String getPortoUltimoConsumoAguaPotavel() {
        return portoUltimoConsumoAguaPotavel;
    }

    public void setPortoUltimoConsumoAguaPotavel(String portoUltimoConsumoAguaPotavel) {
        this.portoUltimoConsumoAguaPotavel = portoUltimoConsumoAguaPotavel;
    }

    public Boolean getProduzAguaPotavel() {
        return produzAguaPotavel;
    }

    public void setProduzAguaPotavel(Boolean produzAguaPotavel) {
        this.produzAguaPotavel = produzAguaPotavel;
    }

    public Boolean getPossuiSistemaTratamentoAguaPotavel() {
        return possuiSistemaTratamentoAguaPotavel;
    }

    public void setPossuiSistemaTratamentoAguaPotavel(Boolean possuiSistemaTratamentoAguaPotavel) {
        this.possuiSistemaTratamentoAguaPotavel = possuiSistemaTratamentoAguaPotavel;
    }

    public Double getCapacidadeMaximaArmazenamentoAguaPotavel() {
        return capacidadeMaximaArmazenamentoAguaPotavel;
    }

    public void setCapacidadeMaximaArmazenamentoAguaPotavel(Double capacidadeMaximaArmazenamentoAguaPotavel) {
        this.capacidadeMaximaArmazenamentoAguaPotavel = capacidadeMaximaArmazenamentoAguaPotavel;
    }

    public Boolean getPossuiAguaLastroBordo() {
        return possuiAguaLastroBordo;
    }

    public void setPossuiAguaLastroBordo(Boolean possuiAguaLastroBordo) {
        this.possuiAguaLastroBordo = possuiAguaLastroBordo;
    }

    public Boolean getEfetuadaSubstituicaoAguaLastro() {
        return efetuadaSubstituicaoAguaLastro;
    }

    public void setEfetuadaSubstituicaoAguaLastro(Boolean efetuadaSubstituicaoAguaLastro) {
        this.efetuadaSubstituicaoAguaLastro = efetuadaSubstituicaoAguaLastro;
    }

    public String getLocalUltimaSubstituicao() {
        return localUltimaSubstituicao;
    }

    public void setLocalUltimaSubstituicao(String localUltimaSubstituicao) {
        this.localUltimaSubstituicao = localUltimaSubstituicao;
    }

    public String getLatitudeUltimaSubstituicaoAguaLastro() {
        return latitudeUltimaSubstituicaoAguaLastro;
    }

    public void setLatitudeUltimaSubstituicaoAguaLastro(String latitudeUltimaSubstituicaoAguaLastro) {
        this.latitudeUltimaSubstituicaoAguaLastro = latitudeUltimaSubstituicaoAguaLastro;
    }

    public String getLongitudeUltimaSubstituicaoAguaLastro() {
        return longitudeUltimaSubstituicaoAguaLastro;
    }

    public void setLongitudeUltimaSubstituicaoAguaLastro(String longitudeUltimaSubstituicaoAguaLastro) {
        this.longitudeUltimaSubstituicaoAguaLastro = longitudeUltimaSubstituicaoAguaLastro;
    }

    public Boolean getHaveraDelastro() {
        return haveraDelastro;
    }

    public void setHaveraDelastro(Boolean haveraDelastro) {
        this.haveraDelastro = haveraDelastro;
    }

    public Boolean getPossuiTanqueRetencaoTratamentoAfluentes() {
        return possuiTanqueRetencaoTratamentoAfluentes;
    }

    public void setPossuiTanqueRetencaoTratamentoAfluentes(Boolean possuiTanqueRetencaoTratamentoAfluentes) {
        this.possuiTanqueRetencaoTratamentoAfluentes = possuiTanqueRetencaoTratamentoAfluentes;
    }

    public Double getCapacidadeArmazenamentoAfluentes() {
        return capacidadeArmazenamentoAfluentes;
    }

    public void setCapacidadeArmazenamentoAfluentes(Double capacidadeArmazenamentoAfluentes) {
        this.capacidadeArmazenamentoAfluentes = capacidadeArmazenamentoAfluentes;
    }

    public Double getAutonomiaRetencao() {
        return autonomiaRetencao;
    }

    public void setAutonomiaRetencao(Double autonomiaRetencao) {
        this.autonomiaRetencao = autonomiaRetencao;
    }

    public Boolean getTransportaCargaPerigosa() {
        return transportaCargaPerigosa;
    }

    public void setTransportaCargaPerigosa(Boolean transportaCargaPerigosa) {
        this.transportaCargaPerigosa = transportaCargaPerigosa;
    }

    public Boolean getOcorrenciaDesinsetizacao() {
        return ocorrenciaDesinsetizacao;
    }

    public void setOcorrenciaDesinsetizacao(Boolean ocorrenciaDesinsetizacao) {
        this.ocorrenciaDesinsetizacao = ocorrenciaDesinsetizacao;
    }

    public String getProdutoDesinsetizacao() {
        return produtoDesinsetizacao;
    }

    public void setProdutoDesinsetizacao(String produtoDesinsetizacao) {
        this.produtoDesinsetizacao = produtoDesinsetizacao;
    }

    public String getDataDesinsetizacao() {
        return dataDesinsetizacao;
    }

    public void setDataDesinsetizacao(String dataDesinsetizacao) {
        this.dataDesinsetizacao = dataDesinsetizacao;
    }

    public Boolean getHaveraRetiradaResiduosSolidos() {
        return haveraRetiradaResiduosSolidos;
    }

    public void setHaveraRetiradaResiduosSolidos(Boolean haveraRetiradaResiduosSolidos) {
        this.haveraRetiradaResiduosSolidos = haveraRetiradaResiduosSolidos;
    }

    public Boolean getHaveraAbastecimentoAguaPotavel() {
        return haveraAbastecimentoAguaPotavel;
    }

    public void setHaveraAbastecimentoAguaPotavel(Boolean haveraAbastecimentoAguaPotavel) {
        this.haveraAbastecimentoAguaPotavel = haveraAbastecimentoAguaPotavel;
    }

    public Boolean getHaveraAbastecimentoAlimentos() {
        return haveraAbastecimentoAlimentos;
    }

    public void setHaveraAbastecimentoAlimentos(Boolean haveraAbastecimentoAlimentos) {
        this.haveraAbastecimentoAlimentos = haveraAbastecimentoAlimentos;
    }

    public String getDataDeposito() {
        return dataDeposito;
    }

    public void setDataDeposito(String dataDeposito) {
        this.dataDeposito = dataDeposito;
    }

    public String getLocalRepresentante() {
        return localRepresentante;
    }

    public void setLocalRepresentante(String localRepresentante) {
        this.localRepresentante = localRepresentante;
    }

    public String getNomeRepresentante() {
        return nomeRepresentante;
    }

    public void setNomeRepresentante(String nomeRepresentante) {
        this.nomeRepresentante = nomeRepresentante;
    }

    public String getDataRepresentante() {
        return dataRepresentante;
    }

    public void setDataRepresentante(String dataRepresentante) {
        this.dataRepresentante = dataRepresentante;
    }

    public String getPassaporteRepresentante() {
        return passaporteRepresentante;
    }

    public void setPassaporteRepresentante(String passaporteRepresentante) {
        this.passaporteRepresentante = passaporteRepresentante;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public boolean isTratamentoAguaPotavel() {
        return tratamentoAguaPotavel;
    }

    public void setTratamentoAguaPotavel(boolean tratamentoAguaPotavel) {
        this.tratamentoAguaPotavel = tratamentoAguaPotavel;
    }

    public boolean isAguaLastro() {
        return aguaLastro;
    }

    public void setAguaLastro(boolean aguaLastro) {
        this.aguaLastro = aguaLastro;
    }

    public boolean isSubAguaLastro() {
        return subAguaLastro;
    }

    public void setSubAguaLastro(boolean subAguaLastro) {
        this.subAguaLastro = subAguaLastro;
    }

    public String getLongitudeSubstAgua() {
        return longitudeSubstAgua;
    }

    public void setLongitudeSubstAgua(String longitudeSubstAgua) {
        this.longitudeSubstAgua = longitudeSubstAgua;
    }

    public String getLatitudeSubstAgua() {
        return latitudeSubstAgua;
    }

    public void setLatitudeSubstAgua(String latitudeSubstAgua) {
        this.latitudeSubstAgua = latitudeSubstAgua;
    }

    public boolean isDeslastro() {
        return deslastro;
    }

    public void setDeslastro(boolean deslastro) {
        this.deslastro = deslastro;
    }

    public String getEscalaFormatada() {
        return escalaFormatada;
    }

    public void setEscalaFormatada(String escalaFormatada) {
        this.escalaFormatada = escalaFormatada;
    }

    
    
}
