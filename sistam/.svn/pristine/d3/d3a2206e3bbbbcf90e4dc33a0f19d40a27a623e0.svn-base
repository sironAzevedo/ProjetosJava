package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@JsonSerialize(using=EnumSistamIdIntSerializer.class)
public enum TipoDocumento implements Serializable {

    SOLICITACAO_CERTIFICADO                (1, "Solicitação de Certificado", Autoridade.ANVISA, true),
    COMUNICACAO_CHEGADA                    (2, "Comunicação de Chegada", Autoridade.ANVISA, true),
    PARTE_ENTRADA                          (3, "Parte de Entrada", Autoridade.CAPITANIA_PORTOS, true),
    PARTE_SAIDA                            (4, "Parte de Saída", Autoridade.CAPITANIA_PORTOS, true),
    REGISTRO_MOVIMENTACAO                  (5, "Registro de Movimentação", Autoridade.CAPITANIA_PORTOS, true),
    REGISTRO_ALTERACAO_DESTINO             (6, "Registro de Alteração de Destino", Autoridade.CAPITANIA_PORTOS, true),
    DECLARACAO_GERAL_ENTRADA               (7, "Declaração Geral (Entrada)", Autoridade.CAPITANIA_PORTOS, true),
    DECLARACAO_GERAL_PEDIDO_DESPACHO       (8, "Declaração Geral (Pedido de Despacho)", Autoridade.CAPITANIA_PORTOS, true),
    NOTIFICACAO_PREVISAO_CHEGADA           (9, "Notificação de Previsão de Chegada", Autoridade.CAPITANIA_PORTOS, true),
    PEDIDO_DESPACHO_PROXIMO_PORTO          (10, "Pedido de Despacho para o Próximo Porto", Autoridade.CAPITANIA_PORTOS, true),
    TERMO_COMPROMISSO                      (11, "Termo de Compromisso", Autoridade.CAPITANIA_PORTOS, true),
    TERMO_RESPONSABILIDADE_REPRESENTANTE   (12, "Termo de Responsabilidade do Representante Legal da Embarcação", Autoridade.CAPITANIA_PORTOS, true),
    PASSE_SAIDA                            (13, "Passe de Saída", Autoridade.CAPITANIA_PORTOS, true),
    CONTROLE_FISCALIZACAO_UNICO            (14, "Controle de Fiscalização Único", Autoridade.POLICIA_FEDERAL, true),
    TERMO_RESPONSABILIDADE_EMPRESA         (15, "Termo de Responsabilidade da Empresa de Navegação", Autoridade.RECEITA_FEDERAL, true),
    ISENCAO_AFRMM                          (16, "Isenção AFRMM", Autoridade.RECEITA_FEDERAL, false),
    ANEXO_UNICO                            (17, "Anexo Único", Autoridade.RECEITA_FEDERAL, false),
    CONHECIMENTO_EMBARQUE                  (18, "Conhecimento de Embarque", Autoridade.RECEITA_FEDERAL, false),
    BILL_OF_LADING                         (19, "Bill of Lading (BL)", Autoridade.RECEITA_FEDERAL, false),
    MANIFESTO_EXPORTACAO                   (20, "Manifesto Exportação", Autoridade.RECEITA_FEDERAL, false),
    SOLICITACAO_ALTERACAO_RETIFICACAO      (21, "Solicitação de Alteração ou Retificação de Dados", Autoridade.RECEITA_FEDERAL, false),
    CONTROLE_FISC_UNICO_ENTRADA            (22, "Controle de Fiscalização Único (entrada)", Autoridade.POLICIA_FEDERAL, false),
    CONTROLE_FISC_UNICO_SAIDA              (23, "Controle de Fiscalização Único (saída)", Autoridade.POLICIA_FEDERAL, false),
    PASSE_ENTRADA_PF                       (24, "Passe de Entrada da Polícia Federal", Autoridade.POLICIA_FEDERAL, false),
    PASSE_SAIDA_PF                         (25, "Passe de Saída da Polícia Federal", Autoridade.POLICIA_FEDERAL, false),
    TERMO_COMPROMISSO_RECOLHIMENTO_TUF     (26, "Termo de Compromisso de Recolhimento da TUF", Autoridade.CAPITANIA_PORTOS, true),
    COMUNICACAO_CABOTAGEM_ENTRADA          (27, "Comunicação de Cabotagem da Polícia Federal (entrada)", Autoridade.POLICIA_FEDERAL, false, TipoControleFiscalizacaoUnicoEntradaSaida.ENTRADA, AreaNavegacao.CABOTAGEM),
    COMUNICACAO_CABOTAGEM_SAIDA            (28, "Comunicação de Cabotagem da Polícia Federal (saída)", Autoridade.POLICIA_FEDERAL, false, TipoControleFiscalizacaoUnicoEntradaSaida.SAIDA, AreaNavegacao.CABOTAGEM),
    COMUNICACAO_LONGO_CURSO_ENTRADA        (29, "Comunicação de Longo Curso da Polícia Federal (entrada)", Autoridade.POLICIA_FEDERAL, false, TipoControleFiscalizacaoUnicoEntradaSaida.ENTRADA, AreaNavegacao.LONGO_CURSO),
    COMUNICACAO_LONGO_CURSO_SAIDA          (30, "Comunicação de Longo Curso da Polícia Federal (saída)", Autoridade.POLICIA_FEDERAL, false, TipoControleFiscalizacaoUnicoEntradaSaida.SAIDA, AreaNavegacao.LONGO_CURSO),
    PEDIDIO_VISITA_PF                      (31, "Pedido de Visita da Polícia Federal", Autoridade.POLICIA_FEDERAL, false),
    REQUERIMENTO_PASSE_SAIDA_PF            (32, "Requerimento do Passe de Saída da Polícia Federal", Autoridade.POLICIA_FEDERAL, false),
    AVISO_SAIDA                            (33, "Aviso de Saída", Autoridade.CAPITANIA_PORTOS, true),
    PEDIDO_DESPACHO_POR_PERIODO            (34, "Pedido de Despacho Por Periodo", Autoridade.CAPITANIA_PORTOS, true),
    PASSE_SAIDA_POR_PERIODO                (35, "Passe de Saída Por Periodo", Autoridade.CAPITANIA_PORTOS, true),
    AVISO_ENTRADA                          (36, "Aviso de Entrada", Autoridade.CAPITANIA_PORTOS, true),
    NOTA_DE_DESPACHO_MARITIMO              (37, "Nota de Despacho Marítimo", Autoridade.CAPITANIA_PORTOS, true);
    private Integer id;
    private String porExtenso;
    private Autoridade autoridade;
    private boolean automatico;
    private AreaNavegacao areaNavegacao;
    private TipoControleFiscalizacaoUnicoEntradaSaida tipoControleEntradaSaida;

    private TipoDocumento(Integer id, String porExtenso, Autoridade autoridade, boolean automatico, TipoControleFiscalizacaoUnicoEntradaSaida tipoControleEntradaSaida, AreaNavegacao areaNavegacao) {
        this.id = id;
        this.porExtenso = porExtenso;
        this.autoridade = autoridade;
        this.automatico = automatico;
        this.tipoControleEntradaSaida = tipoControleEntradaSaida;
        this.areaNavegacao = areaNavegacao;
    }

    private TipoDocumento(Integer id, String porExtenso, Autoridade autoridade, boolean automatico) {
        this(id, porExtenso, autoridade, automatico, null, null);
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public Autoridade getAutoridade() {
        return autoridade;
    }

    public boolean isAutomatico() {
        return automatico;
    }

    public AreaNavegacao getAreaNavegacao() {
        return areaNavegacao;
    }

    public static TipoDocumento from(Integer s) {
        for (TipoDocumento tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    public static TipoDocumento from(TipoControleFiscalizacaoUnicoEntradaSaida tipoControle, AreaNavegacao navegacao) {
        for (TipoDocumento tipo : values()) {
            if (tipoControle.equals(tipo.tipoControleEntradaSaida) && navegacao.equals(tipo.areaNavegacao)) {
                return tipo;
            }
        }
        return null;
    }

    public static List<TipoDocumento> obterPorAutoridade(Autoridade autoridade) {
        List<TipoDocumento> lista = new ArrayList<TipoDocumento>();

        for (TipoDocumento tipoDocumento : values()) {
            if (tipoDocumento.getAutoridade().equals(autoridade)) {
                lista.add(tipoDocumento);
            }
        }
        return lista;
    }

    @Override
    public String toString() {
        return porExtenso;
    }
}
