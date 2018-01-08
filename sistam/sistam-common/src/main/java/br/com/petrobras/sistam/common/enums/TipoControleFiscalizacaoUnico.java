package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoControleFiscalizacaoUnico implements Serializable {
    
    
    //SÃO SEBASTIÃO E OUTROS
    PASSE_ENTRADA                                       (1,"PASSE DE ENTRADA" ),
    PASSE_SAIDA                                         (2,"PASSE DE SAÍDA" ),
    REGISTRO_CABOTAGEM                                  (3,"REGISTRO DE CABOTAGEM" ), 
    
    //MANAUS                        
    SOLICITACAO_PASSE_ENTRADA                           (4,"SOLICITAÇÃO PASSE DE ENTRADA"),                        
    SOLICITACAO_PASSE_SAIDA                             (5,"SOLICITAÇÃO PASSE DE SAÍDA"),
    COMUNICAO_CABOTAGEM_LONGO_CURSO                     (6,"COMUNIÇÃO DE CAB./LONG. C"),
    PEDIDO_VISITA                                       (7,"PEDIDO DE VISITA DE EMBACAÇÃO");
              
     
    private Integer id;
    private String porExtenso; 

    private TipoControleFiscalizacaoUnico(Integer id, String porExtenso) {
        this.id = id;
        this.porExtenso = porExtenso; 
    }
    
    public static TipoControleFiscalizacaoUnico[]valueAgenciaSaoSebastiaoEOutros(){
        return new TipoControleFiscalizacaoUnico[]{PASSE_ENTRADA, PASSE_SAIDA, REGISTRO_CABOTAGEM};
    }
    
    public static TipoControleFiscalizacaoUnico[]valueAgenciaManaus(){
        return new TipoControleFiscalizacaoUnico[]{SOLICITACAO_PASSE_ENTRADA, SOLICITACAO_PASSE_SAIDA, COMUNICAO_CABOTAGEM_LONGO_CURSO, PEDIDO_VISITA};
    } 
    
    public static List<TipoControleFiscalizacaoUnico> listValues() {
        return new ArrayList<TipoControleFiscalizacaoUnico>(Arrays.asList(TipoControleFiscalizacaoUnico.values()));
    }
    
     public static TipoControleFiscalizacaoUnico[]valuePasseEntradaSolicitacaoPasseEntrada(){
        return new TipoControleFiscalizacaoUnico[]{PASSE_ENTRADA, SOLICITACAO_PASSE_ENTRADA};
    }
      
    public Integer getId() {
        return id;
    }

    public String getPorExtenso() {
        return porExtenso;
    }  

    public static TipoControleFiscalizacaoUnico from(Integer s) {
        if (s == null) {
            return null;
        }
        for (TipoControleFiscalizacaoUnico tipo : values()) {
            if (tipo.id.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return porExtenso;
    }

    
//    public static List<TipoUnidadeMedida> obter() {
//        List<TipoUnidadeMedida> lista = new ArrayList<TipoUnidadeMedida>();
//        for (TipoUnidadeMedida tipo : values()) {
//            lista.add(tipo);
//        }
//        return lista;
//    }
        
    
}
