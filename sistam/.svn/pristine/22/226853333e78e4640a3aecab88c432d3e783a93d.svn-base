package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.common.support.CollectionUtils;
import br.com.petrobras.notesweb2.common.enumeration.TipoResposta;
import br.com.petrobras.notesweb2.common.valueobject.Email;
import br.com.petrobras.notesweb2.common.valueobject.Resposta;
import br.com.petrobras.sistam.common.business.AcessoService;
import br.com.petrobras.sistam.common.business.AgenciamentoService;
import br.com.petrobras.sistam.common.business.CertificadoService;
import br.com.petrobras.sistam.common.business.DocumentoService;
import br.com.petrobras.sistam.common.business.ManobraService;
import br.com.petrobras.sistam.common.business.NotesWebService;
import br.com.petrobras.sistam.common.business.Notesweb2Service;
import br.com.petrobras.sistam.common.business.OperacaoService;
import br.com.petrobras.sistam.common.business.ServicoProtecaoService;
import br.com.petrobras.sistam.common.entity.Acompanhamento;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Certificado;
import br.com.petrobras.sistam.common.entity.Documento;
import br.com.petrobras.sistam.common.entity.Hospede;
import br.com.petrobras.sistam.common.entity.Manobra;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.entity.Passageiro;
import br.com.petrobras.sistam.common.entity.PontoAtracacao;
import br.com.petrobras.sistam.common.entity.ServicoHospedagem;
import br.com.petrobras.sistam.common.entity.ServicoTransporte;
import br.com.petrobras.sistam.common.entity.ServicoManobra;
import br.com.petrobras.sistam.common.entity.ServicoResponsavel;
import br.com.petrobras.sistam.common.entity.ServicoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.enums.FinalidadeManobra;
import br.com.petrobras.sistam.common.enums.StatusEmbarcacao;
import br.com.petrobras.sistam.common.enums.TipoCertificado;
import br.com.petrobras.sistam.common.enums.TipoDocumento;
import br.com.petrobras.sistam.common.enums.TipoFrota;
import br.com.petrobras.sistam.common.enums.TipoServico;
import br.com.petrobras.sistam.common.enums.TipoTransporte;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import br.com.petrobras.sistam.common.util.SistamDateUtils;
import br.com.petrobras.sistam.common.util.SistamUtils;
import br.com.petrobras.sistam.common.validator.ValidadorPermissao;
import br.com.petrobras.sistam.common.valueobjects.GrupoAgenciaEStatusEmbarcacaoVO;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoHospedagem;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoRetiradaResiduoLixo;
import br.com.petrobras.sistam.common.valueobjects.FiltroEnvioSolicitacaoTransporte;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioTimesheet;
import br.com.petrobras.sistam.common.valueobjects.GrupoAgenciaEFrotaVO;
import br.com.petrobras.sistam.common.valueobjects.RelatorioTimeSheetEmailVO;
import br.com.petrobras.sistam.service.util.ServiceUtil;
import br.com.petrobras.sistam.service.validator.ValidadorNotesWeb;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.LoggerFactory;

public class NotesWebServiceImpl implements NotesWebService {

    org.slf4j.Logger log = LoggerFactory.getLogger(NotesWebServiceImpl.class);
    
    private boolean html;
    private String ignoraDestinatarioInexistente;
    private String usuario;
    private String senha;
    private String mockkeys = null;
    @Autowired
    private ValidadorNotesWeb validador;
    private ValidadorPermissao validadorPermissao;
    private Notesweb2Service notesweb2;
    private AcessoService acessoService;
    private ServicoProtecaoService servicoProtecaoService;
    private AgenciamentoService agenciamentoService;
    private DocumentoService documentoService;
    private CertificadoService certificadoService;
    private ManobraService manobraService;
    private OperacaoService operacaoService;

    public void setNotesweb2(Notesweb2Service notesweb2) {
        this.notesweb2 = notesweb2;
    }

    public void setAcessoService(AcessoService acessoService) {
        this.acessoService = acessoService;
    }

    public void setAgenciamentoService(AgenciamentoService agenciamentoService) {
        this.agenciamentoService = agenciamentoService;
    }

    public void setServicoProtecaoService(ServicoProtecaoService servicoProtecaoService) {
        this.servicoProtecaoService = servicoProtecaoService;
    }

    public void setDocumentoService(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    public void setCertificadoService(CertificadoService certificadoService) {
        this.certificadoService = certificadoService;
    }

    public void setManobraService(ManobraService manobraService) {
        this.manobraService = manobraService;
    }

    public void setOperacaoService(OperacaoService operacaoService) {
        this.operacaoService = operacaoService;
    }

    public ValidadorPermissao getValidadorPermissao() {
        return validadorPermissao;
    }

    public void setValidadorPermissao(ValidadorPermissao validadorPermissao) {
        this.validadorPermissao = validadorPermissao;
    }

    public void setValidador(ValidadorNotesWeb validador) {
        this.validador = validador;
    }

    public ValidadorNotesWeb getValidador() {
        return validador;
    }

    public NotesWebServiceImpl(String usuario, String senha, boolean useMock, boolean testKeys, String mockkeys) {
        this.usuario = usuario;
        this.senha = senha;
        this.html = true;
        this.ignoraDestinatarioInexistente = "true";
        if (testKeys) {
            this.mockkeys = mockkeys;
        }
    }

    private void enviarEmail(String[] destinatarios, String[] destinatariosCopiados, String responderA, String titulo, String mensagem) {
        this.enviarEmail(destinatarios, destinatariosCopiados, responderA, titulo, mensagem, null, null);
    }

    private void enviarEmail(String[] destinatarios, String[] destinatariosCopiados, String responderA, String titulo, String mensagem, String nomeArquivo, byte[] arquivo) {

        if (mockkeys != null) {
            destinatarios = mockkeys.split(",");
            destinatariosCopiados = mockkeys.split(",");
        }

        Email email = new Email();
        email.setUsuario(this.usuario);
        email.setSenha(this.senha);
        email.setPara(destinatarios);
        email.setCopia(destinatariosCopiados);
        email.setAssunto(titulo);
        email.setMensagem(mensagem);
        email.setHTML(this.html);

        if (nomeArquivo != null && arquivo != null) {
            email.setNomeArquivo(nomeArquivo);
            email.setArquivo(arquivo);
        }

        if (responderA != null) {
            email.setResponderA(responderA);
        }

        email.setPermitirCopia(true);

        Resposta resposta = notesweb2.enviarEmail(email);

        if (resposta.getTipo() == TipoResposta.ERRO) {
            throw new BusinessException(resposta.getErro().getDescricao());
        }
    }

    public void enviarSolicitacaoApoioManobra(ServicoManobra servico) {

        AssertUtils.assertExpression(validadorPermissao.podeEnviarSolicitacaoApoioManobra(servico.getManobra().getAgenciamento().getAgencia()), ConstantesI18N.MANOBRA_SEM_PERMISSAO_ENVIAR_SOLICITACAO_MANOBRA);
        validador.validarEnvioSolicitacaoServico(servico);

        TimeZone zone = servico.getManobra().getAgenciamento().getAgencia().obterTimezone();

        String enviadoPor = acessoService.buscarDadosDeAutenticacao().getUsername();

        StringBuilder titulo = new StringBuilder();
        StringBuilder mensagem = new StringBuilder();
        Set<String> destinatarios = new HashSet<String>();
        Set<String> comCopia = new HashSet<String>();

        // destinatarios.add(servico.getEmpresaMaritima().getEmail());
        destinatarios.add(servico.getManobra().getAgenciamento().getAgencia().getEmail());
        //comCopia.add(servico.getManobra().getAgenciamento().getAgencia().getEmail());

        titulo
                .append("Solicitação de apoio para manobra: ")
                .append(servico.getTipoDoServico())
                .append(" Embarcação: ")
                .append(servico.getManobra().getAgenciamento().getEmbarcacao());

        mensagem
                .append("<p>")
                .append("Solicitamos o serviço de ")
                .append(servico.getTipoDoServico()).append(" para manobra de ").append(servico.getManobra().getFinalidadeManobra()).append(" ")
                .append("no porto ").append(servico.getManobra().getAgenciamento().getPorto())
                .append(" no dia <b>").append(SistamDateUtils.formatDate(servico.getDataProgramada(), "dd/MM/yyyy HH:mm", zone)).append("</b>")
                .append("</p>")
                .append("<br>")
                .append("<b>Embarcação / Viagem Chegada:</b> ").append(servico.getManobra().getAgenciamento().getEmbarcacao()).append(" / ").append(servico.getManobra().getAgenciamento().getVgm())
                .append("<br><br>")
                .append("<b>Bandeira:</b> ").append(servico.getManobra().getAgenciamento().getEmbarcacao().getBandeira())
                .append("<br><br>")
                .append("<b>Origem:</b> ").append(servico.getManobra().getPontoAtracacaoOrigem())
                .append("<br><br>")
                .append("<b>Escala:</b> ").append(servico.getManobra().getPontoAtracacaoEscala() == null ? "" : servico.getManobra().getPontoAtracacaoEscala().getNomeFormatado())
                .append("<br><br>")
                .append("<b>Destino:</b> ").append(servico.getManobra().getPontoAtracacaoDestino().getNomeFormatado())
                .append("<br><br>")
                .append("<b>GRT:</b> ").append(servico.getManobra().getAgenciamento().getEmbarcacao().getArqueacaoBruta()).append("&nbsp;&nbsp;&nbsp;")
                .append("<b>DWT:</b> ").append(servico.getManobra().getAgenciamento().getEmbarcacao().getDwt()).append("&nbsp;&nbsp;&nbsp;")
                .append("<b>LOA:</b> ").append(servico.getManobra().getAgenciamento().getEmbarcacao().getLoa()).append("&nbsp;&nbsp;&nbsp;")
                .append("<b>NRT:</b> ").append(servico.getManobra().getAgenciamento().getEmbarcacao().getArqueacaoLiquida())
                .append("<br><br>")
                .append("<b>Calado a Vante:</b> ").append(servico.getManobra().getCaladoVante()).append("&nbsp;&nbsp;&nbsp;")
                .append("<b>Calado a Ré:</b> ").append(servico.getManobra().getCaladoRe())
                .append("<br><br>")
                .append("<b>IRIN:</b> ").append(servico.getManobra().getAgenciamento().getEmbarcacao().getIrin())
                .append("<br><br>")
                .append("<b>Observação:</b> ").append(servico.getObservacao() != null ? servico.getObservacao() : "");

        mensagem
                .append("<br>")
                .append("<p>Mensagem enviada pelo sistema SISTAM. Por favor, confirmar o recebimento e contactar a agência para maiores esclarecimentos</p>")
                .append("<br>")
                .append(servico.getManobra().getAgenciamento().getAgencia().getNomeCompleto())
                .append("  /  ").append(" Telefone: ").append(servico.getManobra().getAgenciamento().getAgencia().getTelefone())
                .append("  /  ").append(" Email: ").append(servico.getManobra().getAgenciamento().getAgencia().getEmail())
                .append("<br>")
                .append("Enviado por : ").append(enviadoPor);

        enviarEmail(destinatarios.toArray(new String[0]), comCopia.toArray(new String[0]), null, titulo.toString(), SistamUtils.converterParaHtml(mensagem.toString()));
    }

    public void enviarFormularioSolicitacaoTransporte(FiltroEnvioSolicitacaoTransporte filtro) {
        ServicoTransporte servico = filtro.getServicoTransporte();

        AssertUtils.assertExpression(validadorPermissao.podeEnviarSolicitacaoTransporte(servico.getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_TRANSPORTE_SEM_PERMISSAO_ENVIAR_EMAIL);
        validador.validarEnvioEmailSolicitacaoTransporte(filtro);

        String assunto = "";
        String mensagem = "";
        Set<String> destinatarios = new HashSet<String>();
        Set<String> comCopia = new HashSet<String>();

        //Destinatários para envio de email
        destinatarios.addAll(destinatariosEmailSolicitacaoTransporte(filtro));

        //Asssunto do email
        if (servico.getServicoProtecao().getDataCancelamento() != null) {
            assunto = "Aviso de Cancelamento da Solicitação de Transporte";
        } else if (!servico.getServicoProtecao().isNovo()) {
            assunto = "Aviso de Alteração da Solicitação de Transporte";
        } else {
            assunto = "Solicitação de Transporte";
        }

        mensagem = montarCorpoEmailSolicitacaoTransporte(filtro, false);
        enviarEmail(destinatarios.toArray(new String[0]), comCopia.toArray(new String[0]), null, assunto, mensagem);

        servicoProtecaoService.confirmarEnvioEmailServicoProtecao(servico.getServicoProtecao());
    }

    public Set<String> destinatariosEmailSolicitacaoTransporte(FiltroEnvioSolicitacaoTransporte filtro) {
        ServicoTransporte servico = filtro.getServicoTransporte();
        Set<String> destinatarios = new HashSet<String>();
        if (filtro.isAgenciaMaritima()) {
            destinatarios.add(servico.getServicoProtecao().getAgenciamento().getAgencia().getEmail());
        }
        if (filtro.isEmpresaTransporte() && (!servico.getTipoTransporte().equals(TipoTransporte.AEREO))) {
            destinatarios.add(servico.getEmpresaServico().getEmpresa().getEmail());
        }
        return destinatarios;
    }

    private String encodeBase64LogoPetrobrasParaString(URL logo) {
        try {
            BufferedImage image = ImageIO.read(logo);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            return new String(Base64.encodeBase64(baos.toByteArray()));
        } catch (IOException ex) {
            throw new BusinessException(ex.getMessage(), ex);
        }
    }

    public String montarCorpoEmailSolicitacaoTransporte(FiltroEnvioSolicitacaoTransporte filtro, boolean preVisualizar) {
        ServicoTransporte servico = filtro.getServicoTransporte();

        AssertUtils.assertExpression(validadorPermissao.podeEnviarSolicitacaoTransporte(servico.getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_TRANSPORTE_SEM_PERMISSAO_ENVIAR_EMAIL);
        validador.validarEnvioEmailSolicitacaoTransporte(filtro);

        StringBuilder mensagem = new StringBuilder();
        mensagem.append(montarCabecalhoComLogoPetrobrasEAgencia(preVisualizar, servico.getServicoProtecao().getAgenciamento().getAgencia()));

        //Texto inicial
        String textoInicial = "";
        if (servico.getServicoProtecao().getDataCancelamento() != null) {
            textoInicial = "<p>Solicitamos o Cancelamento do Serviço de Transporte conforme dados abaixo:</p>";
        } else if (!servico.getServicoProtecao().isNovo()) {
            textoInicial = "<p>Solicitamos a Alteração do Serviço de Transporte conforme dados abaixo:</p>";
        } else {
            textoInicial = "<p>Solicitamos Serviço de Transporte conforme dados abaixo:</p>";
        }
        mensagem.append(textoInicial).append("<br>");

        String transportadora = "";

        if (!servico.getTipoTransporte().equals(TipoTransporte.AEREO)) {
            transportadora = servico.getEmpresaServico().getEmpresa().getRazaoSocial() != null ? servico.getEmpresaServico().getEmpresa().getRazaoSocial() : "-";
        }

        String navio = servico.getServicoProtecao().getAgenciamento().getEmbarcacao().getApelido() != null ? servico.getServicoProtecao().getAgenciamento().getEmbarcacao().getApelido() : "-";
        String data = servico.getDataServico() != null ? SistamDateUtils.formatDate(servico.getDataServico(), "dd/MM/yyyy", TimeZone.getTimeZone(servico.getServicoProtecao().getAgenciamento().getAgencia().getTimezone())) : "-";
        String hora = servico.getDataServico() != null ? SistamDateUtils.formatDate(servico.getDataServico(), "HH:mm:ss", TimeZone.getTimeZone(servico.getServicoProtecao().getAgenciamento().getAgencia().getTimezone())) : "-";
        String voo = servico.getVoo() != null ? servico.getVoo() : "-";
        String origem = servico.getOrigem() != null ? servico.getOrigem() : "-";
        String destino = servico.getDestino() != null ? servico.getDestino() : "-";
        String autorizacao = servico.getAutorizacao() != null ? servico.getAutorizacao() : "-";
        String telefones = servico.getServicoProtecao().getAgenciamento().getAgencia().getTelefone() != null ? servico.getServicoProtecao().getAgenciamento().getAgencia().getTelefone() : "-";
        String viagem = servico.getServicoProtecao().getAgenciamento().getVgm() != null ? servico.getServicoProtecao().getAgenciamento().getVgm() : "-";
        boolean possuiPassageirosAtivos = false;


        StringBuilder tabelaPassageiros = new StringBuilder();
        tabelaPassageiros.append("<table style='border-collapse: collapse; border: 1px solid black;'>")
                .append("<tr>")
                .append(" <th style='border: 1px solid black; background-color: #E6E6FA'>NOME</th>")
                .append(" <th style='border: 1px solid black; background-color: #E6E6FA'>RG/MAT/CIR/PASS</th>")
                .append(" <th style='border: 1px solid black; background-color: #E6E6FA'>CPF</th>")
                .append("</tr>");

        for (Passageiro passageiro : servico.getPassageiros()) {
            if (passageiro.isAtivo() || servico.getServicoProtecao().getDataCancelamento() != null) {
                possuiPassageirosAtivos = true;

                String documento = passageiro.getDocumento() != null ? passageiro.getDocumento() : "";
                String cpf = passageiro.getCpfComMascara() != null ? passageiro.getCpfComMascara() : "";
                tabelaPassageiros.append("<tr>")
                        .append(" <td style='border: 1px solid black; white-space:nowrap;'>").append(passageiro.getNome()).append("</td>")
                        .append(" <td style='border: 1px solid black; white-space:nowrap;'>").append(documento).append("</td>")
                        .append(" <td style='border: 1px solid black; white-space:nowrap;'>").append(cpf).append("</td>")
                        .append("</tr>");
            }
        }

        tabelaPassageiros.append("</table>");

        if (!servico.getTipoTransporte().equals(TipoTransporte.AEREO)) {
            mensagem.append("<b>Transportadora:</b> ").append(transportadora).append("<br>");
        }

        mensagem.append("<b>Embarcação:</b> ").append(navio).append("     <b>Viagem Chegada:</b> ").append(viagem).append("<br>")
                .append("<b>Processo SISTAM: </b>").append(servico.getServicoProtecao().getAgenciamento().getNumeroProcessoformatado()).append("-").append(servico.getServicoProtecao().getProtocolo())
                .append("<br>");

        if (possuiPassageirosAtivos) {
            mensagem.append("<br>").append(tabelaPassageiros.toString()).append("<br><br>");
        }

        mensagem.append("<b>Data:</b> ").append(data).append("<br>")
                .append("<b>Hora:</b> ").append(hora).append("<br>");

        if (!servico.getTipoTransporte().equals(TipoTransporte.AEREO)) {
            mensagem.append("<b>Voo:</b> ").append(voo).append("<br>")
                    .append("<b>Origem:</b> ").append(origem).append("<br>")
                    .append("<b>Destino:</b> ").append(destino).append("<br>").append("<br>")
                    .append("<b>Autorização:</b> ").append(autorizacao).append("<br>").append("<br>").append("<br>");
        }

        //Assinatura
        if (filtro.getResponsavelCusto() != null) {
            mensagem.append("<br>")
                    .append("Os documentos de cobrança devidamente discriminados com todos os comprovantes assinados pelo usuário deverão ser emitidos em nome da: ")
                    .append("<br>")
                    .append("<br>")
                    .append("<b>").append(filtro.getResponsavelCusto().getNomeCompleto()).append("</b>")
                    .append("<br>")
                    .append("<b>").append(filtro.getResponsavelCusto().getEndereco()).append("-").append(filtro.getResponsavelCusto().getBairro()).append("</b>")
                    .append("<br>")
                    .append("<b>CEP: ").append(filtro.getResponsavelCusto().getCep()).append("  ").append(filtro.getResponsavelCusto().getCidade()).append("-").append(filtro.getResponsavelCusto().getUf()).append("</b>")
                    .append("<br>")
                    .append("<b>CNPJ: ").append(filtro.getResponsavelCusto().getCnpj()).append(" e IE ").append(filtro.getResponsavelCusto().getIe()).append("</b>")
                    .append("<br>");
        }
        //Rodape
        mensagem
                .append("<br>")
                .append("<p>Qualquer dúvida contatar-nos pelo(s) telefone(s): ").append(telefones).append("<br>")
                .append("Ou responda o e-mail para: ").append(servico.getServicoProtecao().getAgenciamento().getAgencia().getEmail()).append("</p>")
                .append("<br>");

        return SistamUtils.converterParaHtml(mensagem.toString());
    }

    public void enviarEmailSolicitacaoHospedagem(FiltroEnvioSolicitacaoHospedagem filtro) {

        AssertUtils.assertExpression(validadorPermissao.podeEnviarSolicitacaoHospedagem(filtro.getServicoHospedagem().getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_HOSPEDAGEM_SEM_PERMISSAO_ENVIAR_EMAIL);
        validador.validarEnvioEmailSolicitacaoHospedagem(filtro);

        ServicoHospedagem servico = filtro.getServicoHospedagem();
        String titulo = "";

        if (servico.getServicoProtecao().getDataCancelamento() != null) {
            titulo = "Aviso de Cancelamento da Solicitação de Reserva de Hotel";
        } else if (!servico.getServicoProtecao().isNovo()) {
            titulo = "Aviso de Alteração da Solicitação de Reserva de Hotel";
        } else {
            titulo = "Solicitação de Reserva de Hotel";
        }

        String mensagem = "";
        Set<String> destinatarios = new HashSet<String>();
        Set<String> comCopia = new HashSet<String>();

        destinatarios.addAll(destinatariosEmailSolicitacaoHospedagem(filtro));
        mensagem = montarCorpoEmailSolicitacaoHospedagem(filtro, false);

        enviarEmail(destinatarios.toArray(new String[0]), comCopia.toArray(new String[0]), null, titulo, mensagem);

        servicoProtecaoService.confirmarEnvioEmailServicoProtecao(servico.getServicoProtecao());
    }

    private String montarCabecalhoComLogoPetrobrasEAgencia(boolean preVisualizar, Agencia agencia) {
        StringBuilder cabecalho = new StringBuilder();
        cabecalho.append("<table border='0' style='border: 0px' cellspacing='0' cellpadding='0'>")
                .append("<tr>")
                .append("<td style='border: 0px; padding-right: 50px;'>");
        URL logo = ServiceUtil.getUrlDaLogoPetrobras();
        if (preVisualizar) {
            cabecalho.append("<img src='").append(logo).append("' alt='Logo' />");
        } else {
            cabecalho.append("<img src='data:image/jpg;base64,").append(encodeBase64LogoPetrobrasParaString(logo)).append("' alt='Logo' />");
        }
        cabecalho.append("</td>").append("<td style='border: 0px'>").append("<b>Agência Marítima: </b>")
                .append(agencia.getNome().toUpperCase())
                .append("</td>").append("</tr>").append("</table>");
        //cabecalho.append("<br>");
        return cabecalho.toString();
    }

    private String montarCabecalhoComLogoPetrobrasEAgencia() {
        StringBuilder cabecalho = new StringBuilder();
        cabecalho.append("<table border='0' style='border: 0px' cellspacing='0' cellpadding='0'>")
                .append("<tr>")
                .append("<td style='border: 0px; padding-right: 50px;'>");
        URL logo = ServiceUtil.getUrlDaLogoPetrobras();
        cabecalho.append("<img src='data:image/jpg;base64,").append(encodeBase64LogoPetrobrasParaString(logo)).append("' alt='Logo' />");

        /*cabecalho.append("</td>").append("<td style='border: 0px'>").append("<b>Agência Marítima: </b>")
         .append(agencia.getNome().toUpperCase())
         .append("</td>").append("</tr>").append("</table>");*/
        cabecalho.append("<br>").append("<br>");
        return cabecalho.toString();
    }

    public String montarCorpoEmailSolicitacaoHospedagem(FiltroEnvioSolicitacaoHospedagem filtro, boolean preVisualizar) {
        AssertUtils.assertExpression(validadorPermissao.podeEnviarSolicitacaoHospedagem(filtro.getServicoHospedagem().getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_HOSPEDAGEM_SEM_PERMISSAO_ENVIAR_EMAIL);
        validador.validarEnvioEmailSolicitacaoHospedagem(filtro);

        ServicoHospedagem servico = filtro.getServicoHospedagem();

        StringBuilder mensagem = new StringBuilder();
        mensagem.append(montarCabecalhoComLogoPetrobrasEAgencia(preVisualizar, servico.getServicoProtecao().getAgenciamento().getAgencia()));

        //Texto inicial
        String textoInicial = "";
        if (servico.getServicoProtecao().getDataCancelamento() != null) {
            textoInicial = "<p>Solicitamos o Cancelamento da Solicitação de Reserva de Hotel conforme dados abaixo:</p>";
        } else if (!servico.getServicoProtecao().isNovo()) {
            textoInicial = "<p>Solicitamos a Alteração da Solicitação de Reserva de Hotel conforme dados abaixo:</p>";
        } else {
            textoInicial = "<p>Solicitamos Reserva de Hotel conforme dados abaixo:</p>";
        }
        mensagem.append(textoInicial).append("<br>");

        TimeZone timeZone = TimeZone.getTimeZone(servico.getServicoProtecao().getAgenciamento().getAgencia().getTimezone());
        String dataChegada = SistamDateUtils.formatDate(servico.getDataChegada(), "dd/MM/yyyy", timeZone);
        String dataSaida = SistamDateUtils.formatDate(servico.getDataSaida(), "dd/MM/yyyy", timeZone);
        String fax = servico.getServicoProtecao().getAgenciamento().getAgencia().getFax();
        String autorizacao = servico.getAutorizacao() != null ? servico.getAutorizacao() : "-";
        String observacao = servico.getServicoProtecao().getObservacao() != null ? servico.getServicoProtecao().getObservacao() : "-";
        String cidade = servico.getEmpresaServico().getEmpresa().getCidade() != null ? servico.getEmpresaServico().getEmpresa().getCidade() : "-";
        String estado = servico.getEmpresaServico().getEmpresa().getEstado() != null ? servico.getEmpresaServico().getEmpresa().getEstado() : "-";
        boolean possuiHospedesAtivos = false;

        fax = fax != null ? fax : "-";

        StringBuilder tabelaHospedes = new StringBuilder();
        tabelaHospedes.append("<table style='border-collapse: collapse; border: 1px solid black;'>")
                .append("<tr>")
                .append("  <th style='border: 1px solid black; background-color: #E6E6FA'>NOME</th>")
                .append("  <th style='border: 1px solid black; background-color: #E6E6FA'>RG/MAT/CIR/PASS</th>")
                .append("  <th style='border: 1px solid black; background-color: #E6E6FA'>CPF</th>")
                .append("</tr>");
        for (Hospede hospede : servico.getHospedes()) {
            if (hospede.isAtivo() || servico.getServicoProtecao().getDataCancelamento() != null) {
                possuiHospedesAtivos = true;

                String documento = hospede.getDocumento() != null ? hospede.getDocumento() : "";
                String cpf = hospede.getCpfComMascara() != null ? hospede.getCpfComMascara() : "";
                tabelaHospedes
                        .append("<tr>")
                        .append("  <td style='border: 1px solid black; white-space:nowrap;'>").append(hospede.getNome()).append("</td>")
                        .append("  <td style='border: 1px solid black; white-space:nowrap;'>").append(documento).append("</td>")
                        .append("  <td style='border: 1px solid black; white-space:nowrap;'>").append(cpf).append("</td>")
                        .append("</tr>");
            }
        }

        tabelaHospedes.append("</table>");

        mensagem
                .append("<b>Nome do Hotel: </b>").append(servico.getEmpresaServico().getEmpresa().getRazaoSocial()).append("<br>")
                .append("<b>Cidade: </b>").append(cidade).append("<br>")
                .append("<b>Estado: </b>").append(estado).append("<br>")
                .append("<b>Período da Hospedagem: </b>").append(dataChegada).append(" à ").append(dataSaida)
                .append("<br><br>");

        if (possuiHospedesAtivos) {
            mensagem.append(tabelaHospedes.toString()).append("<br><br>");
        }

        mensagem
                .append("<b>OBS.: </b>").append(observacao)
                .append("<br><br>")
                .append("<b>Autorizado por: </b>").append(autorizacao).append("<br>")
                .append("<b>Lotação/Empresa: </b>").append(filtro.getLotacao()).append("<br>")
                .append("<b>Grupo de Mercadoria: </b>").append("PESSOAS").append("<br>")
                .append("<b>Centro de Custo: </b>").append(servico.getServicoProtecao().getAgenciamento().getEmbarcacao().getApelido()).append(" - ").append(filtro.getTipoCentroCusto().getPorExtenso())
                .append("<br>")
                .append("<b>ADM-2</b>")
                .append("<br>")
                .append("<b>Processo SISTAM: </b>").append(servico.getServicoProtecao().getAgenciamento().getNumeroProcessoformatado()).append("-").append(servico.getServicoProtecao().getProtocolo())
                .append("<br><br>");

        //Assinatura
        mensagem
                .append("Atenciosamente,").append("<br><br>")
                .append("PETROBRAS - Agência Marítima - ").append(servico.getServicoProtecao().getAgenciamento().getAgencia().getNome()).append("<br>")
                .append("Tel.: ").append(servico.getServicoProtecao().getAgenciamento().getAgencia().getTelefone()).append("<br>")
                .append("Fax.: ").append(fax).append("<br>")
                .append("e-mail: ").append(servico.getServicoProtecao().getAgenciamento().getAgencia().getEmail()).append("<br>");

        return SistamUtils.converterParaHtml(mensagem.toString());
    }

    public Set<String> destinatariosEmailSolicitacaoHospedagem(FiltroEnvioSolicitacaoHospedagem filtro) {
        ServicoHospedagem servico = filtro.getServicoHospedagem();
        Set<String> destinatarios = new HashSet<String>();

        if (filtro.isAgenciaMaritima()) {
            destinatarios.add(servico.getServicoProtecao().getAgenciamento().getAgencia().getEmail());
        }
        if (filtro.isHotel()) {
            destinatarios.add(servico.getEmpresaServico().getEmpresa().getEmail());
        }
        if (filtro.getAgenciaViagem() != null) {
            destinatarios.add(filtro.getAgenciaViagem().getEmpresa().getEmail());
        }
        return destinatarios;
    }

    @Override
    public void enviarEmailComEmbarcacoesPorStatusParaRelatorioTimesheet(FiltroRelatorioTimesheet filtro, final List<Agencia> agenciasAutorizadas) {
        Map<GrupoAgenciaEStatusEmbarcacaoVO, InputStream> arquivosZipPorAgenciaEStatusEmbarcacao = agenciamentoService.gerarArquivosZipComPdfsAgrupadosPorStatusEmbarcacao(filtro, agenciasAutorizadas);

        for (Map.Entry<GrupoAgenciaEStatusEmbarcacaoVO, InputStream> map : arquivosZipPorAgenciaEStatusEmbarcacao.entrySet()) {
            try {
                GrupoAgenciaEStatusEmbarcacaoVO grupo = map.getKey();
                InputStream zipBytes = map.getValue();

                byte[] zipBytesCoverter = new byte[(int) zipBytes.available()];
                zipBytes.read(zipBytesCoverter);
                zipBytes.close();

                StatusEmbarcacao statusEmbarcacao = grupo.getStatusEmbarcacao();
                Agencia agencia = grupo.getAgencia();
                String geradoEm = SistamDateUtils.formatDate(grupo.getGeradoEm(), SistamDateUtils.DATE_HOUR_PATTERN, agencia.obterTimezone());
                String nomeArquivo = grupo.getNomeArquivo() + grupo.getExtensao();

                StringBuilder titulo = new StringBuilder();
                titulo.append("Relatório Timesheet").append(" - ")
                        .append("Situação: ").append(statusEmbarcacao.getPorExtenso()).append(" - ")
                        .append("Agência: ").append(agencia.getNome()).append(" - ")
                        .append("Gerado em: ").append(geradoEm);

                StringBuilder mensagem = new StringBuilder();
                mensagem.append("Caro agente marítimo,")
                        .append("<br><br>")
                        .append("Segue no anexo o relatório Timesheet das Embarcações na situação: ").append(statusEmbarcacao.getPorExtenso())
                        .append(". Gerado no dia ").append(geradoEm).append(".")
                        .append("<br><br>")
                        .append("Atenciosamente,")
                        .append("<br><br>")
                        .append("SISTAM – Sistema de Agenciamento Marítimo");

                enviarEmail(new String[]{agencia.getEmail()}, new String[]{}, null, titulo.toString(), SistamUtils.converterParaHtml(mensagem.toString()), nomeArquivo, zipBytesCoverter);
            } catch (IOException ex) {
                Logger.getLogger(NotesWebServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void enviarEmailComEmbarcacoesPorFrotaParaRelatorioTimesheet(FiltroRelatorioTimesheet filtro, final List<Agencia> agenciasAutorizadas) {
        Map<GrupoAgenciaEFrotaVO, InputStream> arquivosZipPorAgenciaEFrota = agenciamentoService.gerarArquivosZipComPdfsAgrupadosPorFrota(filtro, agenciasAutorizadas);

        for (Map.Entry<GrupoAgenciaEFrotaVO, InputStream> map : arquivosZipPorAgenciaEFrota.entrySet()) {
            try {
                GrupoAgenciaEFrotaVO grupo = map.getKey();
                InputStream zipBytes = map.getValue();

                byte[] zipBytesCoverter = new byte[(int) zipBytes.available()];
                zipBytes.read(zipBytesCoverter);
                zipBytes.close();

                TipoFrota frota = grupo.getTipoFrota();
                Agencia agencia = grupo.getAgencia();
                String geradoEm = SistamDateUtils.formatDate(grupo.getGeradoEm(), SistamDateUtils.DATE_HOUR_PATTERN, agencia.obterTimezone());
                String nomeArquivo = grupo.getNomeArquivo() + grupo.getExtensao();

                StringBuilder titulo = new StringBuilder();
                titulo.append("Relatório Timesheet").append(" - ")
                        .append("Frota: ").append(frota.getPorExtenso()).append(" - ")
                        .append("Agência: ").append(agencia.getNome()).append(" - ")
                        .append("Gerado em: ").append(geradoEm);

                StringBuilder mensagem = new StringBuilder();
                mensagem.append("Caro agente marítimo,")
                        .append("<br><br>")
                        .append("Segue no anexo o relatório Timesheet das Embarcações na frota: ").append(frota.getPorExtenso())
                        .append(". Gerado no dia ").append(geradoEm).append(".")
                        .append("<br><br>")
                        .append("Atenciosamente,")
                        .append("<br><br>")
                        .append("SISTAM – Sistema de Agenciamento Marítimo");

                enviarEmail(new String[]{agencia.getEmail()}, new String[]{}, null, titulo.toString(), SistamUtils.converterParaHtml(mensagem.toString()), nomeArquivo, zipBytesCoverter);
            } catch (IOException ex) {
                Logger.getLogger(NotesWebServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    private void enviarEmailParaRelatorioTimesheet(FiltroRelatorioTimesheet filtro, final List<Agencia> agenciasAutorizadas) {
      
        List<Agenciamento> agenciamentos = agenciamentoService.buscarAgenciamentosPorFiltroRelatorioTimesheet(filtro, agenciasAutorizadas);
        for (Agenciamento agenciamento : agenciamentos) {
            if (filtro.getAgencia() == null) {
                filtro.setAgencia(agenciamento.getAgencia());
            }

            String nomeEmbarcacao = agenciamento.getEmbarcacao().getNomeCompleto();

            Agencia agencia = filtro.getAgencia();          
            Set<String> destinatarios = new HashSet<String>();
            Set<String> comCopia = new HashSet<String>();
            String geradoEm = SistamDateUtils.formatDate(new Date(), SistamDateUtils.DATE_HOUR_PATTERN, agencia.obterTimezone());

            //Destinatários para envio de email
            //destinatarios.addAll(destinatariosEmailRelatorioTimesheet(filtro, agenciasAutorizadas));
            destinatarios.add(agenciamento.getAgencia().getEmail());

            StringBuilder titulo = new StringBuilder();
            String mensagem = "";
            
            titulo.append("Relatório Timesheet").append(" - ")
                    .append("Embarcação: ").append(nomeEmbarcacao).append(" - ")
                    .append("Frota: ").append(agenciamento.getTipoFrota().getPorExtenso()).append(" - ")
                    .append("Agência: ").append(agenciamento.getAgencia().getNome()).append(" - ")
                    .append("Gerado em: ").append(geradoEm);
            
            mensagem = montarCorpoEmailRelatorioTimesheet(agenciamento);
            log.info("#################################################");
            log.info("Embarcação:" + nomeEmbarcacao);
            log.info("Frota:" + agenciamento.getTipoFrota().getPorExtenso());
            log.info("Agência:" + agenciamento.getAgencia().getNome());
            log.info("destinatarios.toArray:" + destinatarios.toString());
            log.info("comCopia.toArray:" + comCopia.toString());
            //CollectionUtils.
            enviarEmail(destinatarios.toArray(new String[0]), comCopia.toArray(new String[0]), null, titulo.toString(), mensagem);
        }
    }

    @Override
    public void enviarEmailParaRelatorioTimesheetManual(FiltroRelatorioTimesheet filtro, final List<Agencia> agenciasAutorizadas) {
        
        AssertUtils.assertExpression(validadorPermissao.podeEnviarEmailParaRelatorioTimesheet(filtro.getAgencia()), ConstantesI18N.RELATORIO_TIMESHEET_SEM_PERMISSAO_ENVIAR_EMAIL);
       
        enviarEmailParaRelatorioTimesheet(filtro, agenciasAutorizadas); 
       
    }
    
     public void enviarEmailParaRelatorioTimesheetCarga(FiltroRelatorioTimesheet filtro, final List<Agencia> agenciasAutorizadas) {
        
        enviarEmailParaRelatorioTimesheet(filtro, agenciasAutorizadas); 
       
    }

    public void enviarFormularioSolicitacaoRetiradaResiduoLixo(FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro) {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = filtro.getServicoRetiradaResiduoLixo();

        AssertUtils.assertExpression(validadorPermissao.podeEnviarSolicitacaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_RETIRADA_RESIDUO_LIXO_SEM_PERMISSAO_ENVIAR_EMAIL);
        validador.validarEnvioEmailSolicitacaoRetiradaResiduoLixo(filtro);

        String assunto = "";
        String mensagem = "";
        Set<String> destinatarios = new HashSet<String>();
        Set<String> comCopia = new HashSet<String>();

        //Destinatários para envio de email
        destinatarios.addAll(destinatariosEmailSolicitacaoRetiradaResiduoLixo(filtro));

        //Asssunto do email
        if (servicoRetiradaResiduoLixo.getServicoProtecao().getDataCancelamento() != null) {
            assunto = "AVISO DE CANCELAMENTO DA SOLICITAÇÃO DE RETIRADA DE RESÍDUOS/LIXO";
        } else if (!servicoRetiradaResiduoLixo.getServicoProtecao().isNovo()) {
            assunto = "AVISO DE ALTERAÇÃO DA SOLICITAÇÃO DE RETIRADA DE RESÍDUOS/LIXO";
        } else {
            assunto = "SOLICITAÇÃO DE RETIRADA DE RESÍDUOS/LIXO";
        }

        mensagem = montarCorpoEmailSolicitacaoRetiradaResiduoLixo(filtro, false);
        enviarEmail(destinatarios.toArray(new String[0]), comCopia.toArray(new String[0]), null, assunto, mensagem);

        servicoProtecaoService.confirmarEnvioEmailServicoProtecao(servicoRetiradaResiduoLixo.getServicoProtecao());
    }

    public Set<String> destinatariosEmailSolicitacaoRetiradaResiduoLixo(FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro) {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = filtro.getServicoRetiradaResiduoLixo();
        Set<String> destinatarios = new HashSet<String>();

        if (filtro.isAgenciaMaritima()) {
            destinatarios.add(servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia().getEmail());
        }
        if (filtro.isEmpresaResponsavel()) {
            destinatarios.add(servicoRetiradaResiduoLixo.getEmpresaServico().getEmpresa().getEmail());
        }

        return destinatarios;
    }

    public String montarCorpoEmailSolicitacaoRetiradaResiduoLixo(FiltroEnvioSolicitacaoRetiradaResiduoLixo filtro, boolean preVisualizar) {
        ServicoRetiradaResiduoLixo servicoRetiradaResiduoLixo = filtro.getServicoRetiradaResiduoLixo();

        AssertUtils.assertExpression(validadorPermissao.podeEnviarSolicitacaoRetiradaResiduoLixo(servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia()), ConstantesI18N.SERVICO_TRANSPORTE_SEM_PERMISSAO_ENVIAR_EMAIL);
        validador.validarEnvioEmailSolicitacaoRetiradaResiduoLixo(filtro);

        //CABEÇALHO DO E-MAIL
        StringBuilder mensagem = new StringBuilder();
        mensagem.append(montarCabecalhoComLogoPetrobrasEAgencia(preVisualizar, servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia()));

        //Texto inicial
        String textoInicial = "";
        if (servicoRetiradaResiduoLixo.getServicoProtecao().getDataCancelamento() != null) {
            textoInicial = "<p>AVISO DE CANCELAMENTO DA SOLICITAÇÃO DE RETIRADA DE RESÍDUOS/LIXO</p>";
        } else if (!servicoRetiradaResiduoLixo.getServicoProtecao().isNovo()) {
            textoInicial = "<p>AVISO DE ALTERAÇÃO DA SOLICITAÇÃO DE RETIRADA DE RESÍDUOS/LIXO</p>";
        } else {
            textoInicial = "<p>SOLICITAÇÃO DE RETIRADA DE RESÍDUOS/LIXO</p>";
        }
        mensagem.append(textoInicial).append("<br>");

        String empresaResponsavelTransbordo = "";

        //CORPO DO E-MAIL
        if (servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia().getNome() != null || servicoRetiradaResiduoLixo.getEmpresaMaritima().getRazaoSocial() != null) {
            empresaResponsavelTransbordo = servicoRetiradaResiduoLixo.getEmpresaMaritima().getRazaoSocial() != null ? servicoRetiradaResiduoLixo.getEmpresaMaritima().getRazaoSocial() : "-";
        }

        String tipoResiduo = servicoRetiradaResiduoLixo.getTipoResiduo().getPorExtenso() != null ? servicoRetiradaResiduoLixo.getTipoResiduo().getPorExtenso() : "-";
        String embarcacao = servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getEmbarcacao().getNomeCompleto() != null ? servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getEmbarcacao().getNomeCompleto() : "-";
        String dataInicioOperacao = servicoRetiradaResiduoLixo.getDataInicioOperacao() != null ? SistamDateUtils.formatDate(servicoRetiradaResiduoLixo.getDataInicioOperacao(), "dd/MM/yyyy", TimeZone.getTimeZone(servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia().getTimezone())) : "-";
        String horaInicioOperacao = servicoRetiradaResiduoLixo.getDataInicioOperacao() != null ? SistamDateUtils.formatDate(servicoRetiradaResiduoLixo.getDataInicioOperacao(), "HH:mm", TimeZone.getTimeZone(servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia().getTimezone())) : "-";

        String nomeNavio = servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getEmbarcacao().getNomeCompleto() != null ? servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getEmbarcacao().getNomeCompleto() : "-";
        String vgm = servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getVgm() != null ? servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getVgm() : "-";
        String tipoViagem = servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAreaNavegacao() != null ? servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAreaNavegacao().getPorExtenso() : "-";
        String bandeira = servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getEmbarcacao().getBandeira().getNomeCompleto() != null ? servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getEmbarcacao().getBandeira().getNomeCompleto() : "-";
        String eta = servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getEta() != null ? SistamDateUtils.formatDate(servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getEta(), "dd/MM/yyyy", TimeZone.getTimeZone(servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia().getTimezone())) : "-";
        String horaEta = servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getEta() != null ? SistamDateUtils.formatDate(servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getEta(), "HH:mm", TimeZone.getTimeZone(servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia().getTimezone())) : "-";
        String numeroEscala = (String.valueOf(servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getNumeroEscalaMercante() != null ? servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getNumeroEscalaMercante() : "-"));
        //RODAPE DO E-MAIL
        String empresaRequerente = servicoRetiradaResiduoLixo.getResponsavelCusto().getNomeCompleto() != null ? servicoRetiradaResiduoLixo.getResponsavelCusto().getNomeCompleto() : "-";
        String agenciaMaritima = servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia().getNome() != null ? servicoRetiradaResiduoLixo.getServicoProtecao().getAgenciamento().getAgencia().getNome() : "-";


        //CORPO DO E-MAIL
        mensagem
                .append("<p>Prezados (as),").append("<br>").append("<br>")
                .append("<p>Solicitamos à empresa ").append(empresaResponsavelTransbordo).append(" serviço para retirada de resíduos do tipo ").append(tipoResiduo).append("<br>")
                .append(" da Embarcação ").append(embarcacao).append(" no dia ").append(dataInicioOperacao).append(", ").append(" às ").append(horaInicioOperacao).append(".").append("<br>");

        mensagem
                .append("<br>")
                .append("<p>Seguem também informações necessárias para que a empresa obtenha da Receita Federal (local) a autorização para prestação").append("<br>")
                .append("do serviço: ").append("<br>")
                .append("<br>");

        mensagem.append("<b>NOME DA EMBARCAÇÃO:</b> ").append(nomeNavio).append(" - ").append("<b>VGM:</b> ").append(vgm).append("<br>")
                .append("<b>TIPO DE VIAGEM:</b> ").append(tipoViagem).append("<br>")
                .append("<b>BANDEIRA:</b> ").append(bandeira).append("<br>")
                .append("<b>DATA DA CHEGADA:</b> ").append(eta).append("  ").append(horaEta).append("h").append(".").append("<br>")
                .append("<b>Nº DA ESCALA:</b> ").append(numeroEscala).append("<br>").append("<br>").append("<br>");

        mensagem
                .append("<p>Atenciosamente, ").append("<br>")
                .append("<br>");

        //RODAPE DO E-MAIL 
        mensagem
                .append(empresaRequerente).append(" - ").append(agenciaMaritima).append("<br>");

        return SistamUtils.converterParaHtml(mensagem.toString());
    }

    public Set<String> destinatariosEmailRelatorioTimesheet(FiltroRelatorioTimesheet filtro, List<Agencia> agencias) {
        Set<String> destinatarios = new HashSet<String>();

        List<Agenciamento> agenciamentos = agenciamentoService.buscarAgenciamentosPorFiltroRelatorioTimesheet(filtro, agencias);

        for (Agenciamento agenciamento : agenciamentos) {
            destinatarios.add(agenciamento.getAgencia() != null ? agenciamento.getAgencia().getEmail() : "");
        }

        return destinatarios;
    }

    private String safeDataHoraRelatorio(Date data, TimeZone zone) {
        return data == null ? "-" : SistamDateUtils.formatDate(data, SistamDateUtils.DATE_HOUR_PATTERN, zone);
    }

    private static Map<Date, List<RelatorioTimeSheetEmailVO>> sortByComparator(Map<Date, List<RelatorioTimeSheetEmailVO>> lista) {

        // Convert Map to List
        List<Map.Entry<Date, List<RelatorioTimeSheetEmailVO>>> list = new LinkedList<Map.Entry<Date, List<RelatorioTimeSheetEmailVO>>>(lista.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new Comparator<Map.Entry<Date, List<RelatorioTimeSheetEmailVO>>>() {
            public int compare(Map.Entry<Date, List<RelatorioTimeSheetEmailVO>> o1,
                    Map.Entry<Date, List<RelatorioTimeSheetEmailVO>> o2) {
                return (o1.getKey()).compareTo(o2.getKey());
            }
        });

        // Convert sorted map back to a Map
        Map<Date, List<RelatorioTimeSheetEmailVO>> sortedMap = new LinkedHashMap<Date, List<RelatorioTimeSheetEmailVO>>();
        for (Iterator<Map.Entry<Date, List<RelatorioTimeSheetEmailVO>>> it = list.iterator(); it.hasNext();) {
            Map.Entry<Date, List<RelatorioTimeSheetEmailVO>> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static String retornarTextoTimeSheet(Map<Date, List<RelatorioTimeSheetEmailVO>> map) {
        map = sortByComparator(map);
        StringBuilder textoTimeSheet = new StringBuilder("<BR>");
        for (Map.Entry<Date, List<RelatorioTimeSheetEmailVO>> entry : map.entrySet()) {
            textoTimeSheet.append("<B>").append(SistamDateUtils.formatDate(entry.getKey(), SistamDateUtils.DATE_PATTERN, null)).append("</B><BR>");

            for (RelatorioTimeSheetEmailVO linha : entry.getValue()) {
                textoTimeSheet.append(linha.getDescricao()).append("<BR>");
            }
            textoTimeSheet.append("<BR>");

        }

        return textoTimeSheet.toString();
    }

    private String montarCorpoEmailRelatorioTimesheet(Agenciamento agenciamento) {

        StringBuilder mensagem = new StringBuilder();
        mensagem.append(montarCabecalhoComLogoPetrobrasEAgencia());

        String agencia = "";
        String porto = "";
        String emb = "";
        String vgm = "";
        String portoOrigem = "";
        String portoDestino = "";
        String etaProximoPorto = "";
        String situacao = "";
        PontoAtracacao localizacao = new PontoAtracacao();
        String caladoChegada = "";
        String caladoSaida = "";
        String observacao = "";
        String assinatura = "";

        String geradoEm = SistamDateUtils.formatDate(new Date(), SistamDateUtils.DATE_HOUR_PATTERN, agenciamento.getAgencia().obterTimezone());
        agencia = agenciamento.getAgencia() != null ? agenciamento.getAgencia().getNome() : "-";
        porto = agenciamento.getPorto() != null ? agenciamento.getPorto().getApelido() : "-";
        emb = agenciamento.getEmbarcacao().getNomeCompleto() != null ? agenciamento.getEmbarcacao().getNomeCompleto() : "-";
        vgm = agenciamento.getVgm() != null ? agenciamento.getVgm() : "-";
        portoOrigem = agenciamento.getPortoOrigem() != null ? agenciamento.getPortoOrigem().getNomeCompleto() : "-";
        portoDestino = agenciamento.getPortoDestino() != null ? agenciamento.getPortoDestino().getNomeCompleto() : "-";
        String proximoPorto = SistamDateUtils.formatDate(agenciamento.getEtaProximoPorto(), SistamDateUtils.DATE_HOUR_PATTERN, null);
        etaProximoPorto = !"".equals(proximoPorto) ? proximoPorto : "-";
        situacao = agenciamento.getStatusEmbarcacao().getPorExtenso();
        localizacao = agenciamento.obterLocalizacaoAtual();
        assinatura = agenciamento.getAgencia().getNomeCompleto() + " / Telefone: " + agenciamento.getAgencia().getTelefone() + " / E-mail: " + agenciamento.getAgencia().getEmail();

        String avanteChegada = String.valueOf(agenciamento.getCaladoChegadaVante() == null ? "-" : agenciamento.getCaladoChegadaVante());
        String areChegada = String.valueOf(agenciamento.getCaladoChegadaRe() == null ? "-" : agenciamento.getCaladoChegadaRe());

        String avanteSaida = String.valueOf(agenciamento.getCaladoSaidaVante() == null ? "-" : agenciamento.getCaladoSaidaVante());
        String areSaida = String.valueOf(agenciamento.getCaladoSaidaRe() == null ? "-" : agenciamento.getCaladoSaidaRe());

        caladoChegada = avanteChegada.concat(" A Ré: ").concat(areChegada);
        caladoSaida = avanteSaida.concat(" A Ré: ").concat(areSaida);
        observacao = StringUtils.isBlank(agenciamento.getObservacoes()) ? "-" : agenciamento.getObservacoes();

        String textoTimeSheet = montarTimeSheetEmail(agenciamento);

        //CORPO DO E-MAIL 
        mensagem
                .append("Prezado(s),")
                .append("<br><br>")
                .append("Segue relatório Timesheet da Embarcação: ").append(emb).append(", frota: ").append(agenciamento.getTipoFrota().getPorExtenso())
                .append(". Gerado no dia ").append(geradoEm).append(".")
                .append("<br><br>");

        mensagem
                .append("<br>").append("<b>AGÊNCIA: </b> ").append(agencia).append("<br>")
                .append("<b>PORTO: </b>").append(porto).append("<br>").append("<br>")
                .append("<p>Embarcação: ").append(emb).append("<br>")
                .append("Viagem: ").append(vgm).append("<br>")
                .append("Porto Origem: ").append(portoOrigem).append("<br>")
                .append("Porto Destino: ").append(portoDestino).append("<br>")
                .append("ETA Próximo Porto: ").append(etaProximoPorto).append("<br>")
                .append("Situação: ").append(situacao).append("<br>")
                .append("Localização: ").append(localizacao != null ? localizacao.getNome() : " -").append("<br>")
                .append("Calado de Chegada A Vante: ").append(caladoChegada).append("<br>")
                .append("Calado de Saída A Vante: ").append(caladoSaida).append("<br>")
                .append("Observações: ").append(observacao).append("<br>")
                .append("<BR>").append(textoTimeSheet).append("<br>")
                .append("<br><br>")
                .append("Atenciosamente,")
                .append("<br><br>")
                .append(assinatura);

        return SistamUtils.converterParaHtml(mensagem.toString());
    }

    private String montarTimeSheetEmail(Agenciamento agenciamento) {

        final String HORA_OFICIAL_CHEGADA = "Hora Oficial de Chegada";
        final String ETS = "ETS";
        final String DATA_OFICIAL_SAIDA = "Oficial de Saída";
        final String ETA_PROXIMO_PORTO = "ETA próximo Porto";

        List<RelatorioTimeSheetEmailVO> listaTimeSheetVO = new ArrayList<RelatorioTimeSheetEmailVO>();

        //INCLUIR DADOS DO DOCUMENTO
        incluirLitaDocumentoRelatorioTimeSheet(listaTimeSheetVO, agenciamento);

        //INCLUIR DADOS DO CERTIFICADO
        incluirListaCertificadoRelatorioTimeSheet(listaTimeSheetVO, agenciamento);

        incluirDadosListTimeSheet(listaTimeSheetVO, agenciamento.getDataChegada() != null, agenciamento.getDataChegada(), agenciamento.getAgencia().obterTimezone(), HORA_OFICIAL_CHEGADA);

        //INCLUIR DADOS DA MANOBRA
        incluirListaManobraRelatorioTimeSheet(listaTimeSheetVO, agenciamento);

        //INCLUIR DADOS DA OPERAÇÃO
        incluirListaOperacoesRelatorioTimeSheet(listaTimeSheetVO, agenciamento);

        incluirDadosListTimeSheet(listaTimeSheetVO, agenciamento.getDataEstimadaSaida() != null, agenciamento.getDataEstimadaSaida(), agenciamento.getAgencia().obterTimezone(), ETS);
        incluirDadosListTimeSheet(listaTimeSheetVO, agenciamento.getDataSaida() != null, agenciamento.getDataSaida(), agenciamento.getAgencia().obterTimezone(), DATA_OFICIAL_SAIDA);
        incluirDadosListTimeSheet(listaTimeSheetVO, agenciamento.getEtaProximoPorto() != null, agenciamento.getEtaProximoPorto(), agenciamento.getAgencia().obterTimezone(), ETA_PROXIMO_PORTO);

        //INCLUIR DADOS DO ACOMPANHAMENTO
        incluirListaAcompanhamentosRelatorioTimeSheet(listaTimeSheetVO, agenciamento);

        Map<Date, List<RelatorioTimeSheetEmailVO>> mapTimeSheet = new HashMap<Date, List<RelatorioTimeSheetEmailVO>>();

        CollectionUtils.sort(listaTimeSheetVO, "data");
        for (RelatorioTimeSheetEmailVO relatorioTimeSheetEmailVO : listaTimeSheetVO) {
            Date dataDDMMRRRR = SistamDateUtils.truncateTime(relatorioTimeSheetEmailVO.getData(), agenciamento.getAgencia().obterTimezone());
            if (!mapTimeSheet.containsKey(dataDDMMRRRR)) {
                mapTimeSheet.put(dataDDMMRRRR, new ArrayList<RelatorioTimeSheetEmailVO>());
            }

            mapTimeSheet.get(dataDDMMRRRR).add(relatorioTimeSheetEmailVO);
        }

        return retornarTextoTimeSheet(mapTimeSheet);
    }

    private void incluirDadosListTimeSheet(List<RelatorioTimeSheetEmailVO> lista, boolean incluir, Date data, TimeZone timeZone, String mensagem) {
        if (incluir) {
            RelatorioTimeSheetEmailVO timeSheetEmailVO = new RelatorioTimeSheetEmailVO();
            timeSheetEmailVO.setData(SistamDateUtils.incluirTimeZone(data, timeZone));
            timeSheetEmailVO.setDescricao(String.format("%s - %s", safeDataHoraRelatorio(data, timeZone), mensagem));
            lista.add(timeSheetEmailVO);
        }
    }

    private void incluirLitaDocumentoRelatorioTimeSheet(List<RelatorioTimeSheetEmailVO> listaTimeSheetVO, Agenciamento agenciamento) {
        final String PROTOCOLO_ANVISA = "Protocolo ANVISA";

        List<Documento> docsAnvisa = documentoService.buscarDocumentoDoAgenciamentoPorTipo(TipoDocumento.SOLICITACAO_CERTIFICADO, agenciamento);
        for (Documento documento : docsAnvisa) {
            incluirDadosListTimeSheet(listaTimeSheetVO, !docsAnvisa.isEmpty() && documento.getDataProtocolo() != null, documento.getDataProtocolo(), agenciamento.getAgencia().obterTimezone(), PROTOCOLO_ANVISA);
        }
    }

    private void incluirListaCertificadoRelatorioTimeSheet(List<RelatorioTimeSheetEmailVO> listaTimeSheetVO, Agenciamento agenciamento) {
        final String EMISSAO_CERTIFICADO = "Emissão Certificado";

        Certificado certificado = certificadoService.buscarCertificadoValidoPorTipo(TipoCertificado.CLP, agenciamento, null, agenciamento.getDataEstimadaSaida());
        boolean agenciamentoCertificado = certificado != null ? true : false;
        if (agenciamentoCertificado) {
            incluirDadosListTimeSheet(listaTimeSheetVO, agenciamentoCertificado, certificado.getData(), agenciamento.getAgencia().obterTimezone(), EMISSAO_CERTIFICADO);
        }
    }

    private void incluirListaManobraRelatorioTimeSheet(List<RelatorioTimeSheetEmailVO> listaTimeSheetVO, Agenciamento agenciamento) {
        final String DATA_INICIO_MANOBRA = " - Início Manobra";
        final String DATA_TERMINO_MANOBRA = " - Término Manobra";
        final String MANOBRA_NAVEGACAO_DATA_PARTIDA_PONTO_INICIAL = " de Partida (Ponto Inicial)";
        final String MANOBRA_NAVEGACAO_DATA_CHEGADA_PONTO_FINAL = " de Chegada (Ponto Final)";

        List<Manobra> manobras = manobraService.buscarManobrasEncerradasOuRegistradasOuPreRegistradasPorAgenciamento(agenciamento);
        for (Manobra manobra : manobras) {

            if (manobra.getFinalidadeManobra().equals(FinalidadeManobra.NAVEGACAO)) {
                incluirDadosListTimeSheet(listaTimeSheetVO, manobra.getDataPartidaOrigem() != null, manobra.getDataPartidaOrigem(), agenciamento.getAgencia().obterTimezone(), manobra.getFinalidadeManobra().getPorEntensoMinuscolo() + MANOBRA_NAVEGACAO_DATA_PARTIDA_PONTO_INICIAL + " - " + manobra.getPontoAtracacaoOrigem().getNome());
                incluirDadosListTimeSheet(listaTimeSheetVO, manobra.getDataChegadaDestino() != null, manobra.getDataChegadaDestino(), agenciamento.getAgencia().obterTimezone(), manobra.getFinalidadeManobra().getPorEntensoMinuscolo() + MANOBRA_NAVEGACAO_DATA_CHEGADA_PONTO_FINAL + " - " + manobra.getPontoAtracacaoDestino().getNome());
            } else {
                incluirDadosListTimeSheet(listaTimeSheetVO, manobra.getDataInicio() != null, manobra.getDataInicio(), agenciamento.getAgencia().obterTimezone(), manobra.getFinalidadeManobra().getPorEntensoMinuscolo() + DATA_INICIO_MANOBRA);
                incluirDadosListTimeSheet(listaTimeSheetVO, manobra.getDataTermino() != null, manobra.getDataTermino(), agenciamento.getAgencia().obterTimezone(), manobra.getFinalidadeManobra().getPorEntensoMinuscolo() + DATA_TERMINO_MANOBRA);
            }

            //INCLUIR OS DADOS DO SERVIÇO RESPONSAVEL
            incluirDadosServicoResponsavelRelatorioTimeSheet(manobra, listaTimeSheetVO, agenciamento);
        }
    }

    private void incluirListaOperacoesRelatorioTimeSheet(List<RelatorioTimeSheetEmailVO> listaTimeSheetVO, Agenciamento agenciamento) {
        final String DATA_INICIO = "Início da Operação";
        final String DATA_FIM = "Fim da Operação";

        List<Operacao> operacoes = operacaoService.buscarOperacoesPorAgenciamento(agenciamento);
        for (Operacao operacao : operacoes) {
            incluirDadosListTimeSheet(listaTimeSheetVO, operacao.getDataInicio() != null, operacao.getDataInicio(), agenciamento.getAgencia().obterTimezone(), operacao.getTipoOperacao().getPorExtenso() + " - " + DATA_INICIO);
            incluirDadosListTimeSheet(listaTimeSheetVO, operacao.getDataFim() != null, operacao.getDataFim(), agenciamento.getAgencia().obterTimezone(), operacao.getTipoOperacao().getPorExtenso() + " - " + DATA_FIM);
        }
    }

    private void incluirListaAcompanhamentosRelatorioTimeSheet(List<RelatorioTimeSheetEmailVO> listaTimeSheetVO, Agenciamento agenciamento) {
        List<Acompanhamento> acompanhamentos = agenciamentoService.buscarAcompanhamentos(agenciamento);
        for (Acompanhamento acompanhamento : acompanhamentos) {
            incluirDadosListTimeSheet(listaTimeSheetVO, acompanhamento.getData() != null, acompanhamento.getData(), agenciamento.getAgencia().obterTimezone(), acompanhamento.getTexto());
        }
    }

    private void incluirDadosServicoResponsavelRelatorioTimeSheet(Manobra manobra, List<RelatorioTimeSheetEmailVO> listaTimeSheetVO, Agenciamento agenciamento) {

        final String PRATICO_A_BORODO = "Prático a Bordo";
        final String PRATICO_DISPENSADO = "Prático Dispensado";
        final String REBOCADORES_A_POSTO = "Rebocadores a Postos";
        final String REBOCADORES_DISPENSADOS = "Rebocadores Dispensados";
        final String REBOCADOR_A_POSTO = "Rebocador a Posto";
        final String REBOCADOR_DISPENSADO = "Rebocador Dispensado";

        StringBuilder nomesPraticos = new StringBuilder();

        List<ServicoResponsavel> servicos = manobraService.buscarServicoResponsavelPorManobra(manobra);

        List<ServicoResponsavel> servicoPratico = new ArrayList<ServicoResponsavel>();
        List<ServicoResponsavel> servicoRebocador = new ArrayList<ServicoResponsavel>();

        for (ServicoResponsavel servicoResponsavel : servicos) {
            if (TipoServico.PRATICOS.equals(servicoResponsavel.getServicoManobra().getTipoDoServico())) {
                servicoPratico.add(servicoResponsavel);
                if (nomesPraticos.length() != 0) {
                    nomesPraticos.append(", ");
                }
                nomesPraticos.append(servicoResponsavel.getServico().getNomeServico());
            }

            if (TipoServico.REBOCADORES.equals(servicoResponsavel.getServicoManobra().getTipoDoServico())) {
                servicoRebocador.add(servicoResponsavel);
            }
        }

        if (!servicoPratico.isEmpty()) {
            if (servicoPratico.size() > 1) {
                incluirDadosListTimeSheet(listaTimeSheetVO, servicoPratico.get(0).getDataInicio() != null, servicoPratico.get(0).getDataInicio(), agenciamento.getAgencia().obterTimezone(), PRATICO_A_BORODO + " - " + nomesPraticos.toString() + ".");
                incluirDadosListTimeSheet(listaTimeSheetVO, servicoPratico.get(servicoPratico.size() - 1).getDataTermino() != null, servicoPratico.get(servicoPratico.size() - 1).getDataTermino(), agenciamento.getAgencia().obterTimezone(), PRATICO_DISPENSADO + " - " + nomesPraticos.toString() + ".");
            } else {
                incluirDadosListTimeSheet(listaTimeSheetVO, servicoPratico.get(0).getDataInicio() != null, servicoPratico.get(0).getDataInicio(), agenciamento.getAgencia().obterTimezone(), PRATICO_A_BORODO + " - " + nomesPraticos.toString() + ".");
                incluirDadosListTimeSheet(listaTimeSheetVO, servicoPratico.get(0).getDataTermino() != null, servicoPratico.get(0).getDataTermino(), agenciamento.getAgencia().obterTimezone(), PRATICO_DISPENSADO + " - " + nomesPraticos.toString() + ".");
            }
        }

        if (!servicoRebocador.isEmpty()) {
            if (servicoRebocador.size() > 1) {
                incluirDadosListTimeSheet(listaTimeSheetVO, servicoRebocador.get(0).getDataInicio() != null, servicoRebocador.get(0).getDataInicio(), agenciamento.getAgencia().obterTimezone(), REBOCADORES_A_POSTO);
                incluirDadosListTimeSheet(listaTimeSheetVO, servicoRebocador.get(servicoRebocador.size() - 1).getDataTermino() != null, servicoRebocador.get(servicoRebocador.size() - 1).getDataTermino(), agenciamento.getAgencia().obterTimezone(), REBOCADORES_DISPENSADOS);
            } else {
                incluirDadosListTimeSheet(listaTimeSheetVO, servicoRebocador.get(0).getDataInicio() != null, servicoRebocador.get(0).getDataInicio(), agenciamento.getAgencia().obterTimezone(), REBOCADOR_A_POSTO);
                incluirDadosListTimeSheet(listaTimeSheetVO, servicoRebocador.get(0).getDataTermino() != null, servicoRebocador.get(0).getDataTermino(), agenciamento.getAgencia().obterTimezone(), REBOCADOR_DISPENSADO);
            }
        }
    }
}
