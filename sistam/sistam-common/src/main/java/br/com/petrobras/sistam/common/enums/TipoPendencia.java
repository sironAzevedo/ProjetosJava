package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public enum TipoPendencia implements Serializable {
    PAGAMENTO_LP                              (1, "Pagamento Taxa de Livre Prática"),  
    PAGAMENTO_FUNAPOL                         (2, "Pagamento Taxa FUNAPOL"),
    DESPACHO_SAIDA                            (3, "Despacho de Saída do Porto Anterior"),
    DOCUMENTACAO_OPERACAO_DESGARGA_CABOTAGEM  (4, "Controlar Documentação Carga (Operação Descarga)"),
    PARTE_ENTRADA                             (5, "Parte de Entrada"),
    COMUNICACAO_CHEGADA                       (6, "Comunicação de Chegada"),
    CABOTAGEM_PASSE_SAIDA                     (7, "Registro de Cabotagem ou Passe de Saída PF"),
    LISTA_PERTENCE_ORIGINAL                   (8, "Entregar Lista de Pertences Original RF"),
    PAGAMENTO_TUF                             (9, "Pagamento Taxa TUF"),
    PARTE_SAIDA                               (10, "Parte de Saída"),
    DOCUMENTACAO_OPERACAO_CARGA_CABOTAGEM     (11, "Controle da Documentação Carga (Operação Carga)"),
    CONTROLE_SANITARIO                        (12, "Medidas Sanitárias registradas no Certificado de Controle Sanitário"),
    VENCIMENTO_LP                             (13, "Vencimento de Certificado Livre Prática" ),
    VENCIMENTO_CONFORMIDADE                   (14, "Vencimento de Conformidade" ),
    REGISTRO_MOVIMENTACAO                     (15, "Registro de Movimentação da Embarcação"),
    DESVIO_ROTA                               (16, "Registro de Alteração de Destino" ),
    LISTA_TRIPULANTES_PASSAGEIROS             (17, "Lista de Tripulantes e Passageiros" ),
    LISTA_PERTENCES_TRIPULANTES_PASSAGEIROS   (18, "Lista de Pertences de Tripulantes e Passageiros" ),
    LISTA_ULTIMOS_PORTOS                      (19, "Lista dos últimos Portos" ),
    LISTA_PROVISOES_BORDO                     (20, "Lista de Provisões de bordo" ),
    PLANO_CARGA                               (21, "Plano de Carga" ),
    BL                                        (22, "Bill of Lading (BL)" ),
    CONTROLE_FISCALIZACAO_UNICO_ENTRADA       (23, "Controle de Fiscalização Único de Entrada" ),
    CONTROLE_FISCALIZACAO_UNICO_SAIDA         (24, "Controle de Fiscalização Único de Saída" ),
    CABOTAGEM_PASSE_ENTRADA                   (25, "Registro de Cabotagem ou Passe de Entrada PF" )
    ;


    private Integer id;
    private String porExtenso;

    private TipoPendencia(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso;
    }

    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static TipoPendencia from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoPendencia tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

   public static TipoPendencia obterPorTipoDocumento(TipoDocumento tipoDocumento){
       switch(tipoDocumento){
            case PARTE_ENTRADA:
                return TipoPendencia.PARTE_ENTRADA;
            case COMUNICACAO_CHEGADA:
                return TipoPendencia.COMUNICACAO_CHEGADA;
            case PARTE_SAIDA:
                return TipoPendencia.PARTE_SAIDA;
            case REGISTRO_ALTERACAO_DESTINO:
                return TipoPendencia.DESVIO_ROTA;
            case REGISTRO_MOVIMENTACAO:
                return TipoPendencia.REGISTRO_MOVIMENTACAO;
            case BILL_OF_LADING:
                return TipoPendencia.BL;
            case CONTROLE_FISC_UNICO_ENTRADA:
                return TipoPendencia.CABOTAGEM_PASSE_ENTRADA;
            case COMUNICACAO_CABOTAGEM_ENTRADA:
                return TipoPendencia.CABOTAGEM_PASSE_ENTRADA;
            case COMUNICACAO_LONGO_CURSO_ENTRADA:
                return TipoPendencia.CABOTAGEM_PASSE_ENTRADA;
            case PASSE_ENTRADA_PF:
                return TipoPendencia.CABOTAGEM_PASSE_ENTRADA;
            case CONTROLE_FISC_UNICO_SAIDA:
                return TipoPendencia.CABOTAGEM_PASSE_SAIDA;
            case COMUNICACAO_CABOTAGEM_SAIDA:
                return TipoPendencia.CABOTAGEM_PASSE_SAIDA;
            case COMUNICACAO_LONGO_CURSO_SAIDA:
                return TipoPendencia.CABOTAGEM_PASSE_SAIDA;
            case PASSE_SAIDA_PF:
                return TipoPendencia.CABOTAGEM_PASSE_SAIDA;
       }
        return null;
   }
   
   public static TipoPendencia obterPorTipoTaxa(TipoTaxa tipoTaxa){
       switch(tipoTaxa){
            case FUNAPOL_POLICIA_FEDERAL_ENTRADA:
                return TipoPendencia.PAGAMENTO_FUNAPOL;
            case LIVRE_PRATICA_ANVISA:
                return TipoPendencia.PAGAMENTO_LP;
            case TUF_TAXA_UTILIZACAO_FAROL_CAPITANIA:
                return TipoPendencia.PAGAMENTO_TUF;
        }
        return null;
   }
   
   public static List<TipoPendencia> obterPendenciasDeTaxa(){
       List<TipoPendencia> pendenciasDeTaxa = new ArrayList<TipoPendencia>();
       pendenciasDeTaxa.add(PAGAMENTO_FUNAPOL);
       pendenciasDeTaxa.add(PAGAMENTO_LP);
       pendenciasDeTaxa.add(PAGAMENTO_TUF);
       
       return pendenciasDeTaxa;
   }
   

    @Override
    public String toString() {
        return porExtenso;
    }

}
