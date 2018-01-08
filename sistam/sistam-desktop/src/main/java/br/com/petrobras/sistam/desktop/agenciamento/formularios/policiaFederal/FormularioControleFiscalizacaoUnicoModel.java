package br.com.petrobras.sistam.desktop.agenciamento.formularios.policiaFederal;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Pendencia;
import br.com.petrobras.sistam.common.entity.RepresentanteLegal;
import br.com.petrobras.sistam.common.entity.Taxa;
import br.com.petrobras.sistam.common.enums.AreaNavegacao;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.text.MaskFormatter;

public final class FormularioControleFiscalizacaoUnicoModel extends BasePresentationModel<SistamService> {

    private FormularioControleFiscalizacaoUnicoDialog dialog;
    private Agenciamento agenciamento;
    private FormularioControleFiscalizacaoUnicoVO controleFiscalizacaoUnicoVO = new FormularioControleFiscalizacaoUnicoVO();
    private RepresentanteLegal representanteSelecionado;
    private List<TipoControleFiscalizacaoUnico> listaControleFiscalizacaoUnico;
    private List<TipoControleFiscalizacaoUnicoEntradaSaida> listaEntradaSaida;
    private String armadorCnpj;
    private String agenteArmador;
    private String procedencia;
    private String destino;
    private String nrEscala;
    private String produtoCarga;
    private String portoEntradaBrasil;
    private Integer saidaTransito;
    private Integer clandestinoImpedidos;
    private boolean habilitar;

    public FormularioControleFiscalizacaoUnicoModel(Agenciamento agenciamento) {
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

    //</editor-fold>
    public void validar() {
        SistamPendencyManager pm = new SistamPendencyManager();

        pm.assertNotNull(controleFiscalizacaoUnicoVO.getTipoControleFiscalizacaoUnico()).orRegister(TipoExcecao.CONTROLE_FISCALIZACAO_UNICO, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_INFORME_TIPO_ENTRADA);

        if (controleFiscalizacaoUnicoVO.isRegistroCabotagem() || controleFiscalizacaoUnicoVO.isComunicacaoRegistroCabotagem()) {
            pm.assertNotNull(controleFiscalizacaoUnicoVO.getControleFiscalizacaoUnicoEntradaSaida()).orRegister(TipoExcecao.CONTROLE_FISCALIZACAO_UNICO, ConstantesI18N.CONTROLE_FISCALIZAÇÃO_UNICO_ENTRADA_SAIDA);
        }

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
        String comandanteEntrada = agenciamento.getAgenciementoViagem().getComandanteEntrada() != null ? agenciamento.getAgenciementoViagem().getComandanteEntrada() : "-";
        String comandanteSaida = agenciamento.getAgenciementoViagem().getComandanteSaida() != null ? agenciamento.getAgenciementoViagem().getComandanteSaida() : "-";
        String numeroTripulantesEntrada = String.valueOf(agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada() != null ? agenciamento.getAgenciementoViagem().getNumeroTripulantesEntrada() : "-");
        String numeroTripulantesSaida = String.valueOf(agenciamento.getAgenciementoViagem().getNumeroTripulantesSaida() != null ? agenciamento.getAgenciementoViagem().getNumeroTripulantesSaida() : "-");
        String numeroPassageirosEntrada = String.valueOf(agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada() != null ? agenciamento.getAgenciementoViagem().getNumeroPassageirosEntrada() : "-");
        String numeroPassageirosSaida = String.valueOf(agenciamento.getAgenciementoViagem().getNumeroPassageirosSaida() != null ? agenciamento.getAgenciementoViagem().getNumeroPassageirosSaida() : "-");


        parametros.put("AGENCIA_NOME", agenciamento.getAgencia().getNome());
        parametros.put("AGENCIA_SIGLA", agenciamento.getAgencia().getEstado());
        parametros.put("DATA_DOCUMENTO", !"".equals(dataDocumento) ? dataDocumento : "  /   /    ");

        textoValidade.append("Válido por 48 horas")
                .append("\n");

        if (controleFiscalizacaoUnicoVO.isPasseSaida() || controleFiscalizacaoUnicoVO.isSaida()) {
            parametros.put("TEXTO_VALIDADE", textoValidade.toString() != null ? textoValidade.toString() : " ");
        }

        if (isTipoAgenciaManaus()) {

            String chegadaPortoEtaData = SistamDateUtils.formatDate(agenciamento.getEta(), SistamDateUtils.DATE_HOUR_PATTERN, null);

            if (controleFiscalizacaoUnicoVO.isPedidoVisita()) {
                parametros.put("COMANDANTE", comandanteEntrada);
                parametros.put("CHEGADA_PORTO_ETA_DATA_HORA", agenciamento.getEta() != null ? chegadaPortoEtaData : "-");
            } else {
                parametros.put("CHEGADA_PORTO_ETA_DATA_HORA", "-");
            }

        }

        //PARAMENTRO PARA COMANDANTE
        if (controleFiscalizacaoUnicoVO.isPasseEntrada() || controleFiscalizacaoUnicoVO.isSolicitacaoPasseEntrada()) {
            parametros.put("COMANDANTE", comandanteEntrada);

        } else if (controleFiscalizacaoUnicoVO.isPasseSaida() || controleFiscalizacaoUnicoVO.isSolicitacaoPasseSaida()) {
            parametros.put("COMANDANTE", comandanteSaida);

        } else if (controleFiscalizacaoUnicoVO.isRegistroCabotagem() || controleFiscalizacaoUnicoVO.isComunicacaoRegistroCabotagem()) {
            if (controleFiscalizacaoUnicoVO.isEntrada()) {
                parametros.put("COMANDANTE", comandanteEntrada);

            } else if (controleFiscalizacaoUnicoVO.isSaida()) {
                parametros.put("COMANDANTE", comandanteSaida);
            }
        }
        //PARAMENTRO PARA TRIPULANTE
        if (controleFiscalizacaoUnicoVO.isPasseEntrada() || controleFiscalizacaoUnicoVO.isSolicitacaoPasseEntrada()) {
            parametros.put("TRIPULANTE", numeroTripulantesEntrada);

        } else if (controleFiscalizacaoUnicoVO.isPasseSaida() || controleFiscalizacaoUnicoVO.isSolicitacaoPasseSaida()) {
            parametros.put("TRIPULANTE", numeroTripulantesSaida);

        } else if (controleFiscalizacaoUnicoVO.isRegistroCabotagem() || controleFiscalizacaoUnicoVO.isComunicacaoRegistroCabotagem()) {
            if (controleFiscalizacaoUnicoVO.isEntrada()) {
                parametros.put("TRIPULANTE", numeroTripulantesEntrada);

            } else if (controleFiscalizacaoUnicoVO.isSaida()) {
                parametros.put("TRIPULANTE", numeroTripulantesSaida);
            }
        }

        //PARAMENTRO PARA PASSAGEIRO
        if (controleFiscalizacaoUnicoVO.isPasseEntrada() || controleFiscalizacaoUnicoVO.isSolicitacaoPasseEntrada()) {
            parametros.put("PASSAGEIRO", numeroPassageirosEntrada);

        } else if (controleFiscalizacaoUnicoVO.isPasseSaida() || controleFiscalizacaoUnicoVO.isSolicitacaoPasseSaida()) {
            parametros.put("PASSAGEIRO", numeroPassageirosSaida);

        } else if (controleFiscalizacaoUnicoVO.isRegistroCabotagem() || controleFiscalizacaoUnicoVO.isComunicacaoRegistroCabotagem()) {
            if (controleFiscalizacaoUnicoVO.isEntrada()) {
                parametros.put("PASSAGEIRO", numeroPassageirosEntrada);

            } else if (controleFiscalizacaoUnicoVO.isSaida()) {
                parametros.put("PASSAGEIRO", numeroPassageirosSaida);
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

        controleFiscalizacaoUnicoVO.setArmadorEmbarcacao(agenciamento.getEmbarcacao().getArmador() != null ? agenciamento.getEmbarcacao().getArmador() : "-");

        String arqueacaoLiquida = String.valueOf(agenciamento.getEmbarcacao().getArqueacaoLiquida());
        String arqueacaoBruta = String.valueOf(agenciamento.getEmbarcacao().getArqueacaoBruta());

        controleFiscalizacaoUnicoVO.setTonelagemLiquida(arqueacaoLiquida != null ? arqueacaoLiquida : "-");
        controleFiscalizacaoUnicoVO.setTonelagemBruta(arqueacaoBruta != null ? arqueacaoBruta : "-");

        //DADOS ESPECIFICO PARA AGENCIA DE MANAUS
        if (isTipoAgenciaManaus()) {
            controleFiscalizacaoUnicoVO.setImo(agenciamento.getEmbarcacao().getImo() != null ? agenciamento.getEmbarcacao().getImo() : "-");
            controleFiscalizacaoUnicoVO.setIrin(agenciamento.getEmbarcacao().getIrin() != null ? agenciamento.getEmbarcacao().getIrin() : "-");
            controleFiscalizacaoUnicoVO.setClasse(agenciamento.getEmbarcacao().getClassificacao().getPorExtenso() != null ? agenciamento.getEmbarcacao().getClassificacao().getPorExtenso() : "-");
            controleFiscalizacaoUnicoVO.setPortoEntradaBrasil(portoEntradaBrasil != null ? portoEntradaBrasil : "-");
            controleFiscalizacaoUnicoVO.setProcedencia(agenciamento.getPortoOrigem().getNomeCompleto());
            if (agenciamento.getPortoDestino() != null) {
                controleFiscalizacaoUnicoVO.setDestino(agenciamento.getPortoDestino().getNomeCompleto() != null ? agenciamento.getPortoDestino().getNomeCompleto() : "-");

            } else {
                controleFiscalizacaoUnicoVO.setDestino("-");
            }
        } else {
            controleFiscalizacaoUnicoVO.setProcedencia(procedencia != null ? procedencia : "-");
            controleFiscalizacaoUnicoVO.setDestino(destino != null ? destino : "-");
        }

        for (Taxa taxa : agenciamento.getTaxas()) {
            if (controleFiscalizacaoUnicoVO.getTipoControleFiscalizacaoUnico().equals(TipoControleFiscalizacaoUnico.PASSE_ENTRADA)) {
                if (taxa.getTipoTaxa().equals(TipoTaxa.FUNAPOL_POLICIA_FEDERAL_ENTRADA)) {
                    controleFiscalizacaoUnicoVO.setTaxaPagaEntrada(true);
                    break;
                }
            } else if ((controleFiscalizacaoUnicoVO.getTipoControleFiscalizacaoUnico().equals(TipoControleFiscalizacaoUnico.PASSE_SAIDA)) || (controleFiscalizacaoUnicoVO.getTipoControleFiscalizacaoUnico().equals(TipoControleFiscalizacaoUnico.SOLICITACAO_PASSE_SAIDA))) {
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
            if ((controleFiscalizacaoUnicoVO.isRegistroCabotagemOuLongoCursoEntrada()) || (controleFiscalizacaoUnicoVO.isPasseEntradaOuSolicitacaoPasseEntrada())) {
                if (agenciamento.getDataChegada() != null) {
                    List<Pendencia> listaPendenciaEntrada = getService().buscarPendenciasDoAgenciamentoPorTipo(agenciamento, TipoPendencia.CABOTAGEM_PASSE_ENTRADA);
                    if (listaPendenciaEntrada.isEmpty()) {
                        getService().criarPendencia(agenciamento, TipoPendencia.CABOTAGEM_PASSE_ENTRADA);
                    } else {
                        getService().removerResolucaoDaPendencia(listaPendenciaEntrada.get(0));
                    }

                    List<Pendencia> listaPendenciaSaida = getService().buscarPendenciasDoAgenciamentoPorTipo(agenciamento, TipoPendencia.CABOTAGEM_PASSE_SAIDA);
                    if (listaPendenciaSaida.isEmpty()) {
                        getService().criarPendencia(agenciamento, TipoPendencia.CABOTAGEM_PASSE_SAIDA);
                    } else {
                        getService().removerResolucaoDaPendencia(listaPendenciaSaida.get(0));
                    }
                }
            }

            if ((controleFiscalizacaoUnicoVO.isRegistroCabotagemOuLongoCursoSaida()) || (controleFiscalizacaoUnicoVO.isPasseSaidaOuSolicitacaoPasseSaida())) {
                if (agenciamento.getDataEstimadaSaida() != null) {
                    List<Pendencia> listaPendencia = getService().buscarPendenciasDoAgenciamentoPorTipo(agenciamento, TipoPendencia.CABOTAGEM_PASSE_SAIDA);
                    if (listaPendencia.isEmpty()) {
                        getService().criarPendencia(agenciamento, TipoPendencia.CABOTAGEM_PASSE_SAIDA);
                    } else {
                        getService().removerResolucaoDaPendencia(listaPendencia.get(0));
                    }
                }
            }
        }
    }

    public void gerarDocumentoDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario() {
        TipoDocumento tipo = null;

        if (controleFiscalizacaoUnicoVO.isPasseEntradaOuSolicitacaoPasseEntrada()) {
            tipo = TipoDocumento.PASSE_ENTRADA_PF;
        } else if (controleFiscalizacaoUnicoVO.isPasseSaidaOuSolicitacaoPasseSaida()) {
            tipo = TipoDocumento.PASSE_SAIDA_PF;
        } else if (controleFiscalizacaoUnicoVO.isRegistroCabotagem()) {
            if (controleFiscalizacaoUnicoVO.isEntrada()) {
                tipo = TipoDocumento.CONTROLE_FISC_UNICO_ENTRADA;
            } else if (controleFiscalizacaoUnicoVO.isSaida()) {
                tipo = TipoDocumento.CONTROLE_FISC_UNICO_SAIDA;
            }
        } else if (controleFiscalizacaoUnicoVO.isComunicacaoRegistroCabotagem()) {
            AreaNavegacao areaNavegacao = controleFiscalizacaoUnicoVO.isEntrada() ? agenciamento.getAreaNavegacao() : agenciamento.getAreaNavegacaoSaida();

            tipo = TipoDocumento.from(controleFiscalizacaoUnicoVO.getControleFiscalizacaoUnicoEntradaSaida(), areaNavegacao);

//            if (controleFiscalizacaoUnicoVO.isEntrada()) {

//                if (AreaNavegacao.CABOTAGEM.equals(agenciamento.getAreaNavegacao())) {
//                    tipo = TipoDocumento.COMUNICACAO_CABOTAGEM_ENTRADA;
//                } else if (agenciamento.getAreaNavegacao().equals(AreaNavegacao.LONGO_CURSO)) {
//                    tipo = TipoDocumento.COMUNICACAO_LONGO_CURSO_ENTRADA;
//                }
//            } else if (controleFiscalizacaoUnicoVO.isSaida()) {
//                if (agenciamento.getAreaNavegacaoSaida().equals(AreaNavegacao.CABOTAGEM)) {
//                    tipo = TipoDocumento.COMUNICACAO_CABOTAGEM_SAIDA;
//                } else if (agenciamento.getAreaNavegacaoSaida().equals(AreaNavegacao.LONGO_CURSO)) {
//                    tipo = TipoDocumento.COMUNICACAO_LONGO_CURSO_SAIDA;
//                }
//            }
        } else if (controleFiscalizacaoUnicoVO.isPedidoVisita()) {
            tipo = TipoDocumento.PEDIDIO_VISITA_PF;
        }

        boolean criaDocNovo = false;
        Documento documento = null;
        List<Documento> docs = getService().buscarDocumentoDoAgenciamentoPorTipoSemProtocolo(tipo, agenciamento);
        if (docs != null && !docs.isEmpty()) {
            documento = docs.get(0);
        }
        if ((documento == null)) {
            criaDocNovo = true;
        } else {
            criaDocNovo = false;
        }
        getService().registrarEmissaoDeDocumentoSemProtocolo(tipo, agenciamento, null, criaDocNovo);
    }

    public void gerarFormulario() {

        if (isTipoAgenciaManaus()) {
            RelatorioUtil.abrirFormularioControleFiscalizacaoUnicoEspecifoParaAgenciaManaus(agenciamento.getAgencia(), getControleFiscalizacaoUnicoVO(), obterParametros());
        } else {
            RelatorioUtil.abrirFormularioControleFiscalizacaoUnicoAgencias(agenciamento.getAgencia(), getControleFiscalizacaoUnicoVO(), obterParametros());
        }
        gerarPendenciasDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario();
        gerarDocumentoDoAgenciamentoQUandoForRealizadoAImpressaoDoFormulario();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("tipoControleFiscalizacaoUnico")) {
            validarCampoRegistroCabotagem();
        }
    }
}
