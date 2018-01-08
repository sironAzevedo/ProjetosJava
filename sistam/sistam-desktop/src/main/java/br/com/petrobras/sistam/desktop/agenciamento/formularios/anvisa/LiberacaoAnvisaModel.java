package br.com.petrobras.sistam.desktop.agenciamento.formularios.anvisa;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Certificado;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.enums.TipoCertificado;
import br.com.petrobras.sistam.common.enums.TipoContrato;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.Petrobras;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.valueobjects.LiberacaoAnvisaVO;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class LiberacaoAnvisaModel extends BasePresentationModel<SistamService> {
    
        
    private Agenciamento agenciamento;
    private Certificado certificadoLivrePratica;
    private Certificado certificadoControleSanitario;
    private Certificado certificadoNacionalControleSanitario;
    private LiberacaoAnvisaVO liberacaoAnvisaVO;
    private boolean possuiLivrePraticaValido;
    private boolean possuiCCSBValido;
    private boolean possuiCNCSBValido;
    private boolean possuiPendenciaCCSB = false;
    private TimeZone zone;
    

    public LiberacaoAnvisaModel(){
        setCertificadoLivrePratica(new Certificado(TipoCertificado.CLP));
        setCertificadoControleSanitario(new Certificado(TipoCertificado.CCSB_CICSB));
        setCertificadoNacionalControleSanitario(new Certificado(TipoCertificado.CNCSB_CNICSB));
    }
    
    public void salvar(){
        List<Certificado> certificadosASalvar = new ArrayList<Certificado>();
        
        if (this.possuiLivrePraticaValido) {
            certificadoLivrePratica.setAgenciamento(agenciamento);
            certificadoLivrePratica.setEmbarcacao(agenciamento.getEmbarcacao());
            certificadosASalvar.add(certificadoLivrePratica);
        }
        if (this.possuiCCSBValido) {
            certificadoControleSanitario.setAgenciamento(agenciamento);
            certificadoControleSanitario.setEmbarcacao(agenciamento.getEmbarcacao());
            certificadosASalvar.add(certificadoControleSanitario);
        }
        if (this.possuiCNCSBValido) {
            certificadoNacionalControleSanitario.setAgenciamento(agenciamento);
            certificadoNacionalControleSanitario.setEmbarcacao(agenciamento.getEmbarcacao());
            certificadosASalvar.add(certificadoNacionalControleSanitario);
        }
        
        certificadosASalvar = getService().salvarCertificados(certificadosASalvar);
        
        for (Certificado certificado : certificadosASalvar) {
            if (TipoCertificado.CLP.equals(certificado.getTipo())) {
                setCertificadoLivrePratica(certificado);
            } else if (TipoCertificado.CCSB_CICSB.equals(certificado.getTipo())) {
                setCertificadoControleSanitario(certificadoControleSanitario);
            } else if (TipoCertificado.CNCSB_CNICSB.equals(certificado.getTipo())) {
                setCertificadoNacionalControleSanitario(certificado);
            }
        }
        
    }
    
    public void criarPendencia(){
        if (agenciamento.getTipoContrato().equals(TipoContrato.TCP)){
            getService().criarPendencia(agenciamento, TipoPendencia.CONTROLE_SANITARIO);
        }
        possuiPendenciaCCSB = true;
    }
    
    private void buscarCertificadosValidos() {
        Certificado certificadoLP =    getService().buscarCertificadoValidoPorTipo(TipoCertificado.CLP, agenciamento, null, agenciamento.getDataEstimadaSaida());
        Certificado certificadoCCSB_CICSB = getService().buscarCertificadoValidoPorTipo(TipoCertificado.CCSB_CICSB, null, agenciamento.getEmbarcacao(), agenciamento.getDataEstimadaSaida());
        Certificado certificadoCNCSB_CNICSB = getService().buscarCertificadoValidoPorTipo(TipoCertificado.CNCSB_CNICSB, null, agenciamento.getEmbarcacao(), agenciamento.getDataEstimadaSaida());

        if(certificadoLP != null) {
            setPossuiLivrePraticaValido(true);
            setCertificadoLivrePratica(certificadoLP);
        }
        if (certificadoCCSB_CICSB !=null){
            setPossuiCCSBValido(true);
            setCertificadoControleSanitario(certificadoCCSB_CICSB);
        }
        if (certificadoCNCSB_CNICSB !=null){
            setPossuiCNCSBValido(true);
            setCertificadoNacionalControleSanitario(certificadoCNCSB_CNICSB);
        }
    }
    
    public void validarSolicitacaoCertificados() {
        
        SistamPendencyManager pm = new SistamPendencyManager();
        
        if (isPossuiCCSBValido()){
            pm.assertNotNull(certificadoControleSanitario.getData()).orRegister(TipoExcecao.CCSB_CICSB, ConstantesI18N.INFORME_DATA_EMISSAO_CERTIFICADO);
            pm.assertNotNull(certificadoControleSanitario.getNomePorto()).orRegister(TipoExcecao.CCSB_CICSB, ConstantesI18N.INFORME_PORTO_EMISSAO_CERTIFICADO);
        }        
        
        if (isPossuiCNCSBValido()){
            pm.assertNotNull(certificadoNacionalControleSanitario.getData()).orRegister(TipoExcecao.CNCSB_CNICSB, ConstantesI18N.INFORME_DATA_EMISSAO_CERTIFICADO);
            pm.assertNotNull(certificadoNacionalControleSanitario.getNomePorto()).orRegister(TipoExcecao.CNCSB_CNICSB, ConstantesI18N.INFORME_PORTO_EMISSAO_CERTIFICADO);
        }

        if (isPossuiLivrePraticaValido()){
            pm.assertNotNull(certificadoLivrePratica.getData()).orRegister(TipoExcecao.CLP, ConstantesI18N.INFORME_DATA_EMISSAO_CERTIFICADO);
            pm.assertNotNull(certificadoLivrePratica.getNomePorto()).orRegister(TipoExcecao.CLP, ConstantesI18N.INFORME_PORTO_EMISSAO_CERTIFICADO);
        }
        
        pm.verifyAll();
        
    }
    
    public void validarComunicacaoChegada() {
        
        SistamPendencyManager pm = new SistamPendencyManager();
        pm.assertThat(possuiCCSBValido).orRegister(TipoExcecao.CCSB_CICSB, ConstantesI18N.LIBERACAO_ANVISA_CCSB_PRECISA_ESTAR_MARCADO);
        pm.assertNotNull(certificadoControleSanitario.getNumeroCertificado()).orRegister(TipoExcecao.CCSB_CICSB, ConstantesI18N.INFORME_NUMERO_CERTIFICADO);
        pm.assertNotNull(certificadoControleSanitario.getDataValidade()).orRegister(TipoExcecao.CCSB_CICSB, ConstantesI18N.INFORME_DATA_VALIDADE_CERTIFICADO);
        pm.assertNotNull(certificadoControleSanitario.getData()).orRegister(TipoExcecao.CCSB_CICSB, ConstantesI18N.INFORME_DATA_EMISSAO_CERTIFICADO);
        pm.assertNotNull(certificadoControleSanitario.getNomePorto()).orRegister(TipoExcecao.CCSB_CICSB, ConstantesI18N.INFORME_PORTO_EMISSAO_CERTIFICADO);
        
        pm.assertThat(possuiCNCSBValido).orRegister(TipoExcecao.CNCSB_CNICSB, ConstantesI18N.LIBERACAO_ANVISA_CNCSB_PRECISA_ESTAR_MARCADO);
        pm.assertNotNull(certificadoNacionalControleSanitario.getNumeroCertificado()).orRegister(TipoExcecao.CNCSB_CNICSB, ConstantesI18N.INFORME_NUMERO_CERTIFICADO);
        pm.assertNotNull(certificadoNacionalControleSanitario.getDataValidade()).orRegister(TipoExcecao.CNCSB_CNICSB, ConstantesI18N.INFORME_DATA_VALIDADE_CERTIFICADO);
        pm.assertNotNull(certificadoNacionalControleSanitario.getData()).orRegister(TipoExcecao.CNCSB_CNICSB, ConstantesI18N.INFORME_DATA_EMISSAO_CERTIFICADO);
        pm.assertNotNull(certificadoNacionalControleSanitario.getNomePorto()).orRegister(TipoExcecao.CNCSB_CNICSB, ConstantesI18N.INFORME_PORTO_EMISSAO_CERTIFICADO);

        pm.assertThat(possuiLivrePraticaValido).orRegister(TipoExcecao.CLP, ConstantesI18N.LIBERACAO_ANVISA_LIVRE_PRATICA_PRECISA_ESTAR_MARCADO);
        pm.assertNotNull(certificadoLivrePratica.getNumeroCertificado()).orRegister(TipoExcecao.CLP, ConstantesI18N.INFORME_NUMERO_CERTIFICADO);
        pm.assertNotNull(certificadoLivrePratica.getDataValidade()).orRegister(TipoExcecao.CLP, ConstantesI18N.INFORME_DATA_VALIDADE_CERTIFICADO);
        pm.assertNotNull(certificadoLivrePratica.getData()).orRegister(TipoExcecao.CLP, ConstantesI18N.INFORME_DATA_EMISSAO_CERTIFICADO);
        pm.assertNotNull(certificadoLivrePratica.getNomePorto()).orRegister(TipoExcecao.CLP, ConstantesI18N.INFORME_PORTO_EMISSAO_CERTIFICADO);
        
        pm.verifyAll();
    }
    
    /**
     * Verifica se já tem uma pendência registrada no Certificado de Controle Sanitário
     * @return 
     */
    public boolean jaTemPendenciaRegistrada(){
        Pendencia pendencia = getService().buscarPendenciaNaoResolvidaDoAgenciamentoPorTipo(agenciamento, TipoPendencia.CONTROLE_SANITARIO);
        if (pendencia != null){
            possuiPendenciaCCSB = true;
            return true;
        }
        return false;
    }
    
    private void refreshCampos() {
        firePropertyChange("possuiLivrePraticaValido", null, this.possuiLivrePraticaValido);
        firePropertyChange("possuiCCSBValido", null, this.possuiCCSBValido);
        firePropertyChange("possuiCNCSBValido", null, this.possuiCNCSBValido);
    }
    

    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, this.agenciamento);
        
        setZone(TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone()));
        
        buscarCertificadosValidos();
        
        refreshCampos();
    }

    public TimeZone getZone() {
        return zone;
    }

    public void setZone(TimeZone zone) {
        this.zone = zone;
        firePropertyChange("zone", null, this.zone);
    }

    public LiberacaoAnvisaVO getLiberacaoAnvisaVO() {
        return liberacaoAnvisaVO;
    }
    
    public Certificado getCertificadoLivrePratica() {
        return certificadoLivrePratica;
    }

    public void setCertificadoLivrePratica(Certificado certificadoLivrePratica) {
        this.certificadoLivrePratica = certificadoLivrePratica;
        firePropertyChange("certificadoLivrePratica", null, this.certificadoLivrePratica);
    }

    public Certificado getCertificadoControleSanitario() {
        return certificadoControleSanitario;
    }

    public void setCertificadoControleSanitario(Certificado certificadoControleSanitario) {
        this.certificadoControleSanitario = certificadoControleSanitario;
        firePropertyChange("certificadoControleSanitario", null, this.certificadoControleSanitario);
    }

    public boolean isPossuiLivrePraticaValido() {
        return possuiLivrePraticaValido;
    }

    public void setPossuiLivrePraticaValido(boolean possuiLivrePraticaValido) {
        this.possuiLivrePraticaValido = possuiLivrePraticaValido;
        firePropertyChange("possuiLivrePraticaValido", null, this.possuiLivrePraticaValido);
        if (!possuiLivrePraticaValido) {
            certificadoLivrePratica.setNumeroCertificado(null);
            certificadoLivrePratica.setDataValidade(null);
            certificadoLivrePratica.setData(null);
            certificadoLivrePratica.setNomePorto(null);
            firePropertyChange("certificadoLivrePratica", null, this.certificadoLivrePratica);
        }
    }

    public boolean isPossuiCCSBValido() {
        return possuiCCSBValido;
    }

    public void setPossuiCCSBValido(boolean possuiCCSBValido) {
        this.possuiCCSBValido = possuiCCSBValido;
        firePropertyChange("possuiCCSBValido", null, this.possuiCCSBValido);
        
        if (!possuiCCSBValido) {
            certificadoControleSanitario.setNumeroCertificado(null);
            certificadoControleSanitario.setDataValidade(null);
            certificadoControleSanitario.setData(null);
            certificadoControleSanitario.setNomePorto(null);
            firePropertyChange("certificadoControleSanitario", null, this.certificadoControleSanitario);
        }
    }

    public boolean isPossuiCNCSBValido() {
        return possuiCNCSBValido;
    }

    public void setPossuiCNCSBValido(boolean possuiCNCSBValido) {
        this.possuiCNCSBValido = possuiCNCSBValido;
        firePropertyChange("possuiCNCSBValido", null, this.possuiCNCSBValido);
        if (!possuiCNCSBValido) {
            certificadoNacionalControleSanitario.setNumeroCertificado(null);
            certificadoNacionalControleSanitario.setDataValidade(null);
            certificadoNacionalControleSanitario.setData(null);
            certificadoNacionalControleSanitario.setNomePorto(null);
            firePropertyChange("certificadoNacionalControleSanitario", null, this.certificadoNacionalControleSanitario);
        }
    }

    public Certificado getCertificadoNacionalControleSanitario() {
        return certificadoNacionalControleSanitario;
    }

    public void setCertificadoNacionalControleSanitario(Certificado certificadoNacionalControleSanitario) {
        this.certificadoNacionalControleSanitario = certificadoNacionalControleSanitario;
        firePropertyChange("certificadoNacionalControleSanitario", null, this.certificadoNacionalControleSanitario);
    }
    
    public void carregarVO() {
        liberacaoAnvisaVO = new LiberacaoAnvisaVO();
        preencheVOLivrePratica();
        preencheVOInformacoesViagem();
        preencheVOInformacoesSanitarias();
        preencheVOInformacoesResponsavel();
        preencheVORepresentanteLegal();
        preencheVOEmbarcacao();
    }
    
    private void preencheVOLivrePratica() {

        liberacaoAnvisaVO.setPossuiCcsbCicsbValido(possuiCCSBValido);
        if (certificadoControleSanitario != null && certificadoControleSanitario.getNomePorto() != null) {
            liberacaoAnvisaVO.setDataPortoEmissaoCcsbCicsb(SistamDateUtils.formatShortDate(certificadoControleSanitario.getData(), zone) + " " + certificadoControleSanitario.getNomePorto().trim());
        }    

        liberacaoAnvisaVO.setPossuiCncsbCnicsbValido(possuiCNCSBValido);
        if (certificadoNacionalControleSanitario != null && certificadoNacionalControleSanitario.getNomePorto() != null) {
            liberacaoAnvisaVO.setDataPortoEmissaoCnCsbCnicsb(SistamDateUtils.formatShortDate(certificadoNacionalControleSanitario.getData(), zone) + " " + certificadoNacionalControleSanitario.getNomePorto().trim());
        }    

        liberacaoAnvisaVO.setPossuiClpValido(possuiLivrePraticaValido);
        if (certificadoLivrePratica != null && certificadoLivrePratica.getNomePorto() != null) {
            liberacaoAnvisaVO.setDataPortoEmissaoClp(SistamDateUtils.formatShortDate(certificadoLivrePratica.getData(), zone) + " " + certificadoLivrePratica.getNomePorto().trim());
        }
    }
    
    public void carregarVOComunicacaoChegada() {
        liberacaoAnvisaVO.setExistePendenciaCCSB(possuiPendenciaCCSB);
        liberacaoAnvisaVO.setNrCertificadoCcsbCicsb(certificadoControleSanitario.getNumeroCertificado() + "");
        liberacaoAnvisaVO.setDataValidadeCcsbCicsb(SistamDateUtils.formatShortDate(certificadoControleSanitario.getDataValidade(), zone));
        liberacaoAnvisaVO.setPortoEmissaoCcsbCicsb(certificadoControleSanitario.getNomePorto());
        liberacaoAnvisaVO.setDataEmissaoCcsbCicsb(SistamDateUtils.formatShortDate(certificadoControleSanitario.getData(), zone));

        liberacaoAnvisaVO.setNrCertificadoCncsbCnicsb(certificadoNacionalControleSanitario.getNumeroCertificado() + "");
        liberacaoAnvisaVO.setDataValidadeCncsbCnicsb(SistamDateUtils.formatShortDate(certificadoNacionalControleSanitario.getDataValidade(), zone));
        liberacaoAnvisaVO.setPortoEmissaoCncsbCnicsb(certificadoNacionalControleSanitario.getNomePorto());
        liberacaoAnvisaVO.setDataEmissaoCncsbCnicsb(SistamDateUtils.formatShortDate(certificadoNacionalControleSanitario.getData(), zone));
        
        liberacaoAnvisaVO.setNrCertificadoClp(certificadoLivrePratica.getNumeroCertificado() + "");
        liberacaoAnvisaVO.setDataValidadeClp(SistamDateUtils.formatShortDate(certificadoLivrePratica.getDataValidade(), zone));
        liberacaoAnvisaVO.setPortoEmissaoClp(certificadoLivrePratica.getNomePorto());
        liberacaoAnvisaVO.setDataEmissaoClp(SistamDateUtils.formatShortDate(certificadoLivrePratica.getData(), zone));
    }
    
    
    private void preencheVOInformacoesViagem() {
        liberacaoAnvisaVO.setDataChegada(SistamDateUtils.formatShortDate(agenciamento.getEta(), zone));
        liberacaoAnvisaVO.setEta(SistamDateUtils.formatDate(agenciamento.getEta(),"HH:mm", zone));
        liberacaoAnvisaVO.setDataEstimadaSaida(SistamDateUtils.formatShortDate(agenciamento.getDataEstimadaSaida(), zone));

        
        if(agenciamento.getPortoDestino()!=null){           
            liberacaoAnvisaVO.setPortoDestino(agenciamento.getPortoDestino().getNomeCompleto());
            liberacaoAnvisaVO.setPaisPortoDestino(agenciamento.getPortoDestino().getPais().getNomeCompleto());
        }
        
        liberacaoAnvisaVO.setNumeroTripulantes(agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada()+"");
        liberacaoAnvisaVO.setNumeroPassageiros(agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada()+"");

        liberacaoAnvisaVO.setNomeComandante(agenciamento.getAgenciementoViagem().getComandanteEntrada());
        liberacaoAnvisaVO.setNacionalidadeComandante(agenciamento.getAgenciementoViagem().getNacionalidadeEntrada());
        liberacaoAnvisaVO.setOcorrenciaObito(agenciamento.getAgenciementoViagem().isObito());
        liberacaoAnvisaVO.setHouveSepultamento(agenciamento.getAgenciementoViagem().isSepultamento());
        liberacaoAnvisaVO.setOcorrenciaDoenca(agenciamento.getAgenciementoViagem().isDoenca());
        liberacaoAnvisaVO.setFebreHemorragia(agenciamento.getAgenciementoViagem().isFebreHemorragia());
        liberacaoAnvisaVO.setSinaisIctericia(agenciamento.getAgenciementoViagem().isIctericia());
        liberacaoAnvisaVO.setSinaisDiarreia(agenciamento.getAgenciementoViagem().isDiarreia());
        liberacaoAnvisaVO.setSinaisDisfuncoesNeurologicas(agenciamento.getAgenciementoViagem().isDisfuncoesNeurologicas());
        liberacaoAnvisaVO.setSinaisTosseDificuldadeRespiratoria(agenciamento.getAgenciementoViagem().isDificuldadeRespiratoria());
        liberacaoAnvisaVO.setOcorrenciaAcidenteBordo(agenciamento.getAgenciementoViagem().isAcidente());
        liberacaoAnvisaVO.setAcidenteOcorridoBordo(agenciamento.getAgenciementoViagem().getNomeAcidente());
        liberacaoAnvisaVO.setOcorrenciaMortandadeRoedores(agenciamento.getAgenciementoViagem().isRoedores());
        liberacaoAnvisaVO.setCompartimentoMortandadeRoedores(agenciamento.getAgenciementoViagem().getCompartimentoRoedores());
        liberacaoAnvisaVO.setOcorrenciaConsumoMedicamentos(agenciamento.getAgenciementoViagem().isConsumoMedicamento());
        liberacaoAnvisaVO.setNomeMedicamento(agenciamento.getAgenciementoViagem().getNomeMedicamento());
    }
    
    private void preencheVOInformacoesSanitarias() {
        liberacaoAnvisaVO.setProduzAguaPotavel(agenciamento.getAgenciementoSanitaria().isProduzAguaPotavel());
        liberacaoAnvisaVO.setPossuiSistemaTratamentoAguaPotavel(agenciamento.getAgenciementoSanitaria().isTratamentoAguaPotavel());
        liberacaoAnvisaVO.setAguaLastro(agenciamento.getAgenciementoSanitaria().isAguaLastro());
        liberacaoAnvisaVO.setCapacidadeMaximaArmazenamentoAguaPotavel(agenciamento.getEmbarcacao().getCapacidadeAgua());
        liberacaoAnvisaVO.setSubAguaLastro(agenciamento.getAgenciementoSanitaria().isSubAguaLastro());
        liberacaoAnvisaVO.setLatitudeSubstAgua(agenciamento.getAgenciementoSanitaria().getLatitudeSubstAgua());
        liberacaoAnvisaVO.setLongitudeSubstAgua(agenciamento.getAgenciementoSanitaria().getLongitudeSubstAgua());
        liberacaoAnvisaVO.setDeslastro(agenciamento.getAgenciementoSanitaria().isDeslastro());
        liberacaoAnvisaVO.setPossuiTanqueRetencaoTratamentoAfluentes(agenciamento.getAgenciementoSanitaria().isTanqueTratamento());
        liberacaoAnvisaVO.setCapacidadeArmazenamentoAfluentes(agenciamento.getAgenciementoSanitaria().getCapacidadeEfluente());
        liberacaoAnvisaVO.setAutonomiaRetencao(agenciamento.getAgenciementoSanitaria().getAutonomiaRetencao());
        liberacaoAnvisaVO.setTransportaCargaPerigosa(agenciamento.getAgenciementoSanitaria().isCargaPerigosa());
        liberacaoAnvisaVO.setOcorrenciaDesinsetizacao(agenciamento.getAgenciementoSanitaria().isDesinsetizacao());
        liberacaoAnvisaVO.setProdutoDesinsetizacao(agenciamento.getAgenciementoSanitaria().getProdutoDesinsetizacao());
        if(agenciamento.getAgenciementoSanitaria().getDataDesinsetizacao()!=null){
            liberacaoAnvisaVO.setDataDesinsetizacao(SistamDateUtils.formatShortDate(agenciamento.getAgenciementoSanitaria().getDataDesinsetizacao(), zone));
        }
        if (agenciamento.getAgenciementoSanitaria().getPorto()!=null){
            liberacaoAnvisaVO.setPortoUltimoConsumoAguaPotavel(agenciamento.getAgenciementoSanitaria().getPorto().trim());
        }
        liberacaoAnvisaVO.setHaveraRetiradaResiduosSolidos(agenciamento.getAgenciementoSanitaria().isResiduosSolidos());
        liberacaoAnvisaVO.setHaveraAbastecimentoAguaPotavel(agenciamento.getAgenciementoSanitaria().isAbastecimentoAgua());
        liberacaoAnvisaVO.setHaveraAbastecimentoAlimentos(agenciamento.getAgenciementoSanitaria().isAbastecimentoAlimento());
    }
    
    private void preencheVOInformacoesResponsavel() {
        liberacaoAnvisaVO.setNomeResponsavel(Petrobras.PETROBRAS_NOME_AGENCIA_MARITIMA + " " + agenciamento.getAgencia().getNome() );
        liberacaoAnvisaVO.setCnpjCpfPassaporteResponsavel(agenciamento.getAgencia().getCnpj().toString()); 
        liberacaoAnvisaVO.setEnderecoResponsavel(agenciamento.getAgencia().getEndereco());
        liberacaoAnvisaVO.setBairroResponsavel(agenciamento.getAgencia().getBairro());
        liberacaoAnvisaVO.setMunicipioResponsavel(agenciamento.getAgencia().getCidade());
        liberacaoAnvisaVO.setUfResponsavel(agenciamento.getAgencia().getEstado());
        liberacaoAnvisaVO.setTelefoneResponsavel(agenciamento.getAgencia().getTelefone());
        liberacaoAnvisaVO.setCepResponsavel(agenciamento.getAgencia().getCep());
        liberacaoAnvisaVO.setFaxResponsavel(agenciamento.getAgencia().getFax());
        liberacaoAnvisaVO.setEmailResponsavel(agenciamento.getAgencia().getEmail());
        liberacaoAnvisaVO.setAfeAnvisa(agenciamento.getAgencia().getAfe());
        
    }
    
    private void preencheVORepresentanteLegal() {
        if (Agencia.AGENCIA_MANAUS.equals(agenciamento.getAgencia().getSigla())) {
            liberacaoAnvisaVO.setLocalRepresentante(agenciamento.getAgencia().getCidade().trim());
        } else {
            liberacaoAnvisaVO.setLocalRepresentante(agenciamento.getAgencia().getCidade().trim() + " - " + agenciamento.getAgencia().getEstado());
        }
    }
    
    private void preencheVOEmbarcacao() {
        liberacaoAnvisaVO.setBandeiraEmbarcacao(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto().trim());
        
        liberacaoAnvisaVO.setArqueacaoLiquida(agenciamento.getEmbarcacao().getArqueacaoLiquida());
        liberacaoAnvisaVO.setArqueacaoBruta(agenciamento.getEmbarcacao().getArqueacaoBruta());
        liberacaoAnvisaVO.setEscalaFormatada(agenciamento.getEscalas());
        liberacaoAnvisaVO.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        liberacaoAnvisaVO.setImo(agenciamento.getEmbarcacao().getImo());

    }
    
}
