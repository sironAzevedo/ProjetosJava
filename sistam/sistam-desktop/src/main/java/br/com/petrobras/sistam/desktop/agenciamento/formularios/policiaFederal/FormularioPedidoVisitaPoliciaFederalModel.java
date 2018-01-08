package br.com.petrobras.sistam.desktop.agenciamento.formularios.policiaFederal;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.TipoControleFiscalizacaoUnico;
import br.com.petrobras.sistam.common.enums.TipoControleFiscalizacaoUnicoEntradaSaida;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoExcecao;
import br.com.petrobras.sistam.common.enums.TipoPendencia;
import br.com.petrobras.sistam.common.enums.TipoTaxa;
import br.com.petrobras.sistam.common.exception.SistamPendencyManager;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.Petrobras;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.sistam.common.valueobjects.FormularioControleFiscalizacaoUnicoVO;
import br.com.petrobras.sistam.desktop.util.RelatorioUtil;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.swing.text.MaskFormatter;

public final class FormularioPedidoVisitaPoliciaFederalModel extends BasePresentationModel<SistamService> {

    private FormularioControleFiscalizacaoUnicoDialog dialog;
    private Agenciamento agenciamento;
    private FormularioControleFiscalizacaoUnicoVO controleFiscalizacaoUnicoVO = new FormularioControleFiscalizacaoUnicoVO();
    private RepresentanteLegal representanteSelecionado;
    private List<TipoControleFiscalizacaoUnico> listaControleFiscalizacaoUnico;
    private List<TipoControleFiscalizacaoUnicoEntradaSaida> listaEntradaSaida;
    private String armadorCnpj;
    private String agenteArmador;
    private String nrEscala;
    private String produtoCarga;
    private String portoEntradaBrasil;
    private Integer saidaTransito;
    private Integer clandestinoImpedidos;
    private Date dataDocumento;
    private boolean habilitar;

    public FormularioPedidoVisitaPoliciaFederalModel(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        this.controleFiscalizacaoUnicoVO = new FormularioControleFiscalizacaoUnicoVO();

        setListaEntradaSaida(new ArrayList<TipoControleFiscalizacaoUnicoEntradaSaida>(Arrays.asList(TipoControleFiscalizacaoUnicoEntradaSaida.values())));
        carregarTipoControleFiscalizacaoUnico();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }

    public FormularioControleFiscalizacaoUnicoVO getControleFiscalizacaoUnicoVO() {
        return controleFiscalizacaoUnicoVO;
    }

    public RepresentanteLegal getRepresentanteSelecionado() {
        return representanteSelecionado;
    }

    public void setRepresentanteSelecionado(RepresentanteLegal representanteSelecionado) {
        this.representanteSelecionado = representanteSelecionado;
    }

    public List<TipoControleFiscalizacaoUnico> getListaControleFiscalizacaoUnico() {
        return listaControleFiscalizacaoUnico;
    }

    public void setListaControleFiscalizacaoUnico(List<TipoControleFiscalizacaoUnico> listaControleFiscalizacaoUnico) {
        this.listaControleFiscalizacaoUnico = listaControleFiscalizacaoUnico;
        firePropertyChange("listaControleFiscalizacaoUnico", null, null);
    }

    public List<TipoControleFiscalizacaoUnicoEntradaSaida> getListaEntradaSaida() {
        return listaEntradaSaida;
    }

    public void setListaEntradaSaida(List<TipoControleFiscalizacaoUnicoEntradaSaida> listaEntradaSaida) {
        this.listaEntradaSaida = listaEntradaSaida;
        firePropertyChange("listaEntradaSaida", null, null);
    }

    public String getArmadorCnpj() {
        return armadorCnpj;
    }

    public void setArmadorCnpj(String armadorCnpj) {
        this.armadorCnpj = armadorCnpj;
    }

    public String getArmadorCnpjComMascara() {
        return SistamUtils.formatMask("##.###.###/####-##", armadorCnpj);
    }

    public void setArmadorCnpjComMascara(String cnpj) {
        setArmadorCnpj(cnpj == null ? null : cnpj.replaceAll("\\D", ""));
    }

    public String getAgenteArmador() {
        return agenteArmador;
    }

    public void setAgenteArmador(String agenteArmador) {
        this.agenteArmador = agenteArmador;
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

    public String getPortoEntradaBrasil() {
        return portoEntradaBrasil;
    }

    public void setPortoEntradaBrasil(String portoEntradaBrasil) {
        this.portoEntradaBrasil = portoEntradaBrasil;
    }

    public FormularioControleFiscalizacaoUnicoDialog getDialog() {
        return dialog;
    }

    public void setDialog(FormularioControleFiscalizacaoUnicoDialog dialog) {
        this.dialog = dialog;
    }

    public boolean isHabilitar() {
        return habilitar;
    }

    public void setHabilitar(boolean habilitar) {
        this.habilitar = habilitar;
        firePropertyChange("habilitar", null, null);
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    //</editor-fold>
    public void validar() {
        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertNotNull(controleFiscalizacaoUnicoVO.getTipoControleFiscalizacaoUnico()).orRegister(TipoExcecao.CONTROLE_FISCALIZACAO_UNICO, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_INFORME_TIPO_ENTRADA);
//        pm.assertNotEmpty(armadorCnpj).orRegister(TipoExcecao.CONTROLE_FISCALIZACAO_UNICO, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_ARMADOR_CNPJ);
//        pm.assertNotEmpty(nrEscala).orRegister(TipoExcecao.CONTROLE_FISCALIZACAO_UNICO, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_AGENTE_ARMADOR);
//        pm.assertNotEmpty(produtoCarga).orRegister(TipoExcecao.CONTROLE_FISCALIZACAO_UNICO, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_PRODUTO_CARGA);
//        pm.assertNotNull(saidaTransito).orRegister(TipoExcecao.CONTROLE_FISCALIZACAO_UNICO, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_TRANSITO);
//        pm.assertNotNull(clandestinoImpedidos).orRegister(TipoExcecao.CONTROLE_FISCALIZACAO_UNICO, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_CLANDESTINO_IMPEDIDOS);

        if (controleFiscalizacaoUnicoVO.isRegistroCabotagem() || controleFiscalizacaoUnicoVO.isComunicacaoRegistroCabotagem()) {
            pm.assertNotNull(controleFiscalizacaoUnicoVO.getControleFiscalizacaoUnicoEntradaSaida()).orRegister(TipoExcecao.CONTROLE_FISCALIZACAO_UNICO, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_ENTRADA_SAIDA);
        }

//        if (isTipoAgenciaManaus()) {
//            pm.assertNotEmpty(portoEntradaBrasil).orRegister(TipoExcecao.CONTROLE_FISCALIZACAO_UNICO, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_PORTO_ENTRADA_BRASIL);
//        }

        pm.verifyAll();
    }

    public void carregarTipoControleFiscalizacaoUnico() {
        if (isTipoAgenciaManaus()) {
            listaControleFiscalizacaoUnico = new ArrayList<TipoControleFiscalizacaoUnico>(Arrays.asList(TipoControleFiscalizacaoUnico.valueAgenciaManaus()));
        } else {
            listaControleFiscalizacaoUnico = new ArrayList<TipoControleFiscalizacaoUnico>(Arrays.asList(TipoControleFiscalizacaoUnico.valueAgenciaSaoSebastiaoEOutros()));
        }
    }

    public Map<String, Object> obterParametros() {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder textoValidade = new StringBuilder();

        String dataDocumento = SistamDateUtils.formatDate(controleFiscalizacaoUnicoVO.getDataDocumento(), "dd/MM/yyyy", null);
        String comandante = agenciamento.getAgenciementoViagem().getComandanteEntrada();


        parametros.put("AGENCIA_NOME", agenciamento.getAgencia().getNome());
        parametros.put("AGENCIA_SIGLA", agenciamento.getAgencia().getEstado());
        parametros.put("DATA_DOCUMENTO", dataDocumento != "" ? dataDocumento : "  /   /    ");

        textoValidade.append("Válido por 48 horas")
                .append("\n");

        if (controleFiscalizacaoUnicoVO.isPasseSaida() || controleFiscalizacaoUnicoVO.isSaida()) {
            parametros.put("TEXTO_VALIDADE", textoValidade.toString() != null ? textoValidade.toString() : " ");
        }

        if (isTipoAgenciaManaus()) {
            if (controleFiscalizacaoUnicoVO.isPedidoVisita()) {
                parametros.put("COMANDANTE", comandante != null ? comandante : "-");
                parametros.put("CHEGADA_PORTO_ETA_DATA", agenciamento.getEta() != null ? SistamDateUtils.formatDate(agenciamento.getEta(), "dd/MM/yyyy", TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone())) : "-");
                parametros.put("CHEGADA_PORTO_ETA_HORA", agenciamento.getEta() != null ? SistamDateUtils.formatDate(agenciamento.getEta(), "HH:mm", TimeZone.getTimeZone(agenciamento.getAgencia().getTimezone())) : "-");
            } else {
                parametros.put("CHEGADA_PORTO_ETA_DATA", "-");
                parametros.put("CHEGADA_PORTO_ETA_HORA", "-");
            }

        }

        //PARAMENTRO PARA COMANDANTE
        if (controleFiscalizacaoUnicoVO.isPasseEntrada() || controleFiscalizacaoUnicoVO.isSolicitacaoPasseEntrada()) {
            parametros.put("COMANDANTE", agenciamento.getAgenciementoViagem().getComandanteEntrada() != null ? agenciamento.getAgenciementoViagem().getComandanteEntrada() : "-");

        } else if (controleFiscalizacaoUnicoVO.isPasseSaida() || controleFiscalizacaoUnicoVO.isSolicitacaoPasseSaida()) {
            parametros.put("COMANDANTE", agenciamento.getAgenciementoViagem().getComandanteSaida() != null ? agenciamento.getAgenciementoViagem().getComandanteSaida() : "-");

        } else if (controleFiscalizacaoUnicoVO.isRegistroCabotagem() || controleFiscalizacaoUnicoVO.isComunicacaoRegistroCabotagem()) {
            if (controleFiscalizacaoUnicoVO.isEntrada()) {
                parametros.put("COMANDANTE", agenciamento.getAgenciementoViagem().getComandanteEntrada() != null ? agenciamento.getAgenciementoViagem().getComandanteEntrada() : "-");

            } else if (controleFiscalizacaoUnicoVO.isSaida()) {
                parametros.put("COMANDANTE", agenciamento.getAgenciementoViagem().getComandanteSaida() != null ? agenciamento.getAgenciementoViagem().getComandanteSaida() : "-");
            }
        }
        //PARAMENTRO PARA TRIPULANTE
        if (controleFiscalizacaoUnicoVO.isPasseEntrada() || controleFiscalizacaoUnicoVO.isSolicitacaoPasseEntrada()) {
            parametros.put("TRIPULANTE", String.valueOf(agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada() != null ? agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada() : "-"));

        } else if (controleFiscalizacaoUnicoVO.isPasseSaida() || controleFiscalizacaoUnicoVO.isSolicitacaoPasseSaida()) {
            parametros.put("TRIPULANTE", String.valueOf(agenciamento.getAgenciementoViagem().getNumeroTripulantesSaida() != null ? agenciamento.getAgenciementoViagem().getNumeroTripulantesSaida() : "-"));

        } else if (controleFiscalizacaoUnicoVO.isRegistroCabotagem() || controleFiscalizacaoUnicoVO.isComunicacaoRegistroCabotagem()) {
            if (controleFiscalizacaoUnicoVO.isEntrada()) {
                parametros.put("TRIPULANTE", String.valueOf(agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada() != null ? agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada() : "-"));

            } else if (controleFiscalizacaoUnicoVO.isSaida()) {
                parametros.put("TRIPULANTE", String.valueOf(agenciamento.getAgenciementoViagem().getNumeroTripulantesSaida() != null ? agenciamento.getAgenciementoViagem().getNumeroTripulantesSaida() : "-"));
            }
        }

        //PARAMENTRO PARA PASSAGEIRO
        if (controleFiscalizacaoUnicoVO.isPasseEntrada() || controleFiscalizacaoUnicoVO.isSolicitacaoPasseEntrada()) {
            parametros.put("PASSAGEIRO", String.valueOf(agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada() != null ? agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada() : "-"));

        } else if (controleFiscalizacaoUnicoVO.isPasseSaida() || controleFiscalizacaoUnicoVO.isSolicitacaoPasseSaida()) {
            parametros.put("PASSAGEIRO", String.valueOf(agenciamento.getAgenciementoViagem().getNumeroPassageirosSaida() != null ? agenciamento.getAgenciementoViagem().getNumeroPassageirosSaida() : "-"));

        } else if (controleFiscalizacaoUnicoVO.isRegistroCabotagem() || controleFiscalizacaoUnicoVO.isComunicacaoRegistroCabotagem()) {
            if (controleFiscalizacaoUnicoVO.isEntrada()) {
                parametros.put("PASSAGEIRO", String.valueOf(agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada() != null ? agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada() : "-"));

            } else if (controleFiscalizacaoUnicoVO.isSaida()) {
                parametros.put("PASSAGEIRO", String.valueOf(agenciamento.getAgenciementoViagem().getNumeroPassageirosSaida() != null ? agenciamento.getAgenciementoViagem().getNumeroPassageirosSaida() : "-"));
            }
        }

        return parametros;

    }

    public boolean isTipoAgenciaManaus() {
        return agenciamento.getAgencia().getNome().equals("MANAUS");
    }

    public void carregarVO() {

        //INFORMAÇÕES COMPLEMENTARES
        try {
            MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
            mask.setValueContainsLiteralCharacters(false);
            controleFiscalizacaoUnicoVO.setArmadorCnpj(armadorCnpj != null ? " / " + mask.valueToString(armadorCnpj) : " ");
        } catch (Exception e) {
        }

        controleFiscalizacaoUnicoVO.setAgenteArmador(agenteArmador != null ? agenteArmador : "-");
        controleFiscalizacaoUnicoVO.setNrEscala(nrEscala != null ? nrEscala : "-");
        controleFiscalizacaoUnicoVO.setProdutoCarga(produtoCarga != null ? produtoCarga : "-");
        controleFiscalizacaoUnicoVO.setSaidaTransito(saidaTransito != null ? saidaTransito : 0);
        controleFiscalizacaoUnicoVO.setClandestinoImpedidos(clandestinoImpedidos != null ? clandestinoImpedidos : 0);


        //DADOS EM COMUN DE TODAS AS AGENCIAS
        controleFiscalizacaoUnicoVO.setNomeEmbarcacao(agenciamento.getEmbarcacao().getNomeCompleto());
        controleFiscalizacaoUnicoVO.setNacionalidade(agenciamento.getEmbarcacao().getBandeira().getNomeCompleto());
        controleFiscalizacaoUnicoVO.setConsignatarioCnpj(Petrobras.PETROBRAS_NOME + " / " + Petrobras.PETROBRAS_CNPJ);

        try {
            MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
            mask.setValueContainsLiteralCharacters(false);
            controleFiscalizacaoUnicoVO.setAgenteConsignatarioCnpj("PETROBRAS AGÊNCIA MARÍTIMA / " + mask.valueToString(agenciamento.getAgencia().getCnpj()));
        } catch (Exception e) {
        }

        controleFiscalizacaoUnicoVO.setDataChegadaPorto(agenciamento.getDataChegada());
        controleFiscalizacaoUnicoVO.setDataPartidaPorto(agenciamento.getDataEstimadaSaida());
        controleFiscalizacaoUnicoVO.setHoraChegadaPorto(agenciamento.getDataChegada());
        controleFiscalizacaoUnicoVO.setHoraPartidaPorto(agenciamento.getDataSaida());
        controleFiscalizacaoUnicoVO.setProcedencia(agenciamento.getPortoOrigem().getNomeCompleto());
        controleFiscalizacaoUnicoVO.setArmadorEmbarcacao(agenciamento.getEmbarcacao().getArmador() != null ? agenciamento.getEmbarcacao().getArmador() : "-");


        if (agenciamento.getPortoDestino() != null) {
            controleFiscalizacaoUnicoVO.setDestino(agenciamento.getPortoDestino().getNomeCompleto() != null ? agenciamento.getPortoDestino().getNomeCompleto() : "-");

        } else {
            controleFiscalizacaoUnicoVO.setDestino("-");
        }
        String arqueacaoLiquida = String.valueOf(agenciamento.getEmbarcacao().getArqueacaoLiquida());
        controleFiscalizacaoUnicoVO.setTonelagemLiquida(arqueacaoLiquida != null ? arqueacaoLiquida : "-");

        //DADOS ESPECIFICO PARA AGENCIA DE MANAUS
        if (isTipoAgenciaManaus()) {
            controleFiscalizacaoUnicoVO.setImo(agenciamento.getEmbarcacao().getImo() != null ? agenciamento.getEmbarcacao().getImo() : "-");
            controleFiscalizacaoUnicoVO.setIrin(agenciamento.getEmbarcacao().getIrin() != null ? agenciamento.getEmbarcacao().getIrin() : "-");
            controleFiscalizacaoUnicoVO.setClasse(agenciamento.getEmbarcacao().getClassificacao().getPorExtenso() != null ? agenciamento.getEmbarcacao().getClassificacao().getPorExtenso() : "-");
            controleFiscalizacaoUnicoVO.setPortoEntradaBrasil(portoEntradaBrasil != null ? portoEntradaBrasil : "-");

        }

        for (Taxa taxa : agenciamento.getTaxas()) {
            if (controleFiscalizacaoUnicoVO.getTipoControleFiscalizacaoUnico().equals(TipoControleFiscalizacaoUnico.PASSE_ENTRADA)) {
                if (taxa.getTipoTaxa().equals(TipoTaxa.FUNAPOL_POLICIA_FEDERAL_ENTRADA)) {
                    controleFiscalizacaoUnicoVO.setTaxaPagaEntrada(true);
                    break;
                }
            } else if (controleFiscalizacaoUnicoVO.getTipoControleFiscalizacaoUnico().equals(TipoControleFiscalizacaoUnico.PASSE_SAIDA)) {
                if (taxa.getTipoTaxa().equals(TipoTaxa.FUNAPOL_POLICIA_FEDERAL_SAIDA)) {
                    controleFiscalizacaoUnicoVO.setTaxaPagaSaida(true);
                    break;
                }
            }
        }
    }

    public void validarCampoRegistroCabotagem() {
        if (controleFiscalizacaoUnicoVO.isRegistroCabotagem() || controleFiscalizacaoUnicoVO.isComunicacaoRegistroCabotagem()) {
            setHabilitar(true);
        } else {
            setHabilitar(false);
            controleFiscalizacaoUnicoVO.setControleFiscalizacaoUnicoEntradaSaida(null);
            firePropertyChange("controleFiscalizacaoUnicoVO", null, null);
        }
    }

    public void gerarPendenciasDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario() {

        if (agenciamento.isTipoContatoTCP()) {
            if (agenciamento.getDataChegada() != null) {
                List<Pendencia> listaPendencia = getService().buscarPendenciasDoAgenciamentoPorTipo(agenciamento, TipoPendencia.CONTROLE_FISCALIZACAO_UNICO_ENTRADA);
                if (listaPendencia.isEmpty()) {
                    getService().criarPendencia(agenciamento, TipoPendencia.CONTROLE_FISCALIZACAO_UNICO_ENTRADA);
                }
            }

            if (agenciamento.getDataEstimadaSaida() != null) {
                List<Pendencia> listaPendencia = getService().buscarPendenciasDoAgenciamentoPorTipo(agenciamento, TipoPendencia.CONTROLE_FISCALIZACAO_UNICO_SAIDA);
                if (listaPendencia.isEmpty()) {
                    getService().criarPendencia(agenciamento, TipoPendencia.CONTROLE_FISCALIZACAO_UNICO_SAIDA);
                }
            }
        }
    }

    public void gerarDocumentoDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario() {
        if (controleFiscalizacaoUnicoVO.isPasseEntrada()) {
            getService().registrarEmissaoDeDocumento(TipoDocumento.PASSE_ENTRADA_PF, agenciamento, null, false);
        } else if (controleFiscalizacaoUnicoVO.isPasseSaida()) {
            getService().registrarEmissaoDeDocumento(TipoDocumento.PASSE_SAIDA_PF, agenciamento, null, false);
        } else if (controleFiscalizacaoUnicoVO.isRegistroCabotagem()) {
            if (controleFiscalizacaoUnicoVO.isEntrada()) {
                getService().registrarEmissaoDeDocumento(TipoDocumento.CONTROLE_FISC_UNICO_ENTRADA, agenciamento, null, false);
            } else if (controleFiscalizacaoUnicoVO.isSaida()) {
                getService().registrarEmissaoDeDocumento(TipoDocumento.CONTROLE_FISC_UNICO_SAIDA, agenciamento, null, false);
            }
        }
    }

    public void gerarFormulario() {

        if (isTipoAgenciaManaus()) {
            RelatorioUtil.abrirFormularioControleFiscalizacaoUnicoEspecifoParaAgenciaManaus(agenciamento.getAgencia(), getControleFiscalizacaoUnicoVO(), obterParametros());
        } else {
            RelatorioUtil.abrirFormularioControleFiscalizacaoUnicoAgencias(agenciamento.getAgencia(), getControleFiscalizacaoUnicoVO(), obterParametros());
        }
        gerarPendenciasDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario();
        if (!isTipoAgenciaManaus()) {
            gerarDocumentoDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("tipoControleFiscalizacaoUnico")) {
            validarCampoRegistroCabotagem();
        }
    }
}
