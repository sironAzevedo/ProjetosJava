package br.com.petrobras.sistam.common.enums;

import java.io.Serializable;


public enum TipoExcecao implements Serializable {
    
    AGENCIAMENTO_INFORMACOES_GERAIS  ("Agenciamento - Informações Gerais"),
    AGENCIAMENTO_VIAGEM              ("Agenciamento - Informações sobre a Viagem"),
    AGENCIAMENTO_SANITARIA           ("Agenciamento - Informações Sanitárias"),
    LIBERACAO_ANVISA                 ("Liberação Anvisa"),
    COMUNICACAO_CAPITANIA            ("Comunicação para Capitania"),
    MANOBRA                          ("Manobra"),
    OPERACAO                         ("Operação"),
    PENDENCIA                        ("Pendência"),
    OUTROS                           ("Outros"),
    AGENCIAMENTO                     ("Agenciamento"),
    INFORMACOES_GERAIS               ("Informações Gerais"),
    CERTIFICADO_ANVISA               ("Certificados ANVISA"),
    CLP                              ("CLP"),
    CCSB_CICSB                       ("CCSB/CICSB"),
    CNCSB_CNICSB                     ("CNCSB/CNICSB"), 
    COMUNICACAO_CHEGADA              ("Comunicação de Chegada"),
    DECLARACAO_GERAL_ENTRADA         ("Declaração Geral"),
    EMBARCACAO                       ("Embarcação"),
    TAXA                             ("Taxa"),
    CERTIFICADO                      ("Certificado"),
    DOCUMENTO                        ("Documento"),
    AGENCIA                          ("Agência"),
    ANEXO                            ("Anexo"),
    RELATORIO_INFO                   ("Informações do Relatório"),
    RECEITA                          ("Receita"),
    CONTROLE_FISCALIZACAO_UNICO      ("Controle de Fiscalização Único"),
    VEICULO                          ("Veículo"),
    VISITA                           ("Visita"),
    SERVICO_PROTECAO                 ("Serviço de Proteção"),
    SERVICO_MEDICO_ODONTOLOGICO      ("Serviço Médico/Odontológico"),
    SERVICO_TRANSPORTE               ("Serviço Transporte"),
    EMPRESA                          ("Empresa"),
    EMPRESA_PROTECAO                 ("Empresa de Proteção"),
    PONTO_ATRACACAO                  ("Ponto de Atracação"),
    AGENCIA_PORTO                    ("Porto"),
    PORTO_COMPLEMENTO                ("Informações Complementares do Porto"),
    DESTINATARIO_AGENCIA             ("Destinatário da Agência"),
    SERVICO_HOSPEDAGEM               ("Serviço Hospedagem"),
    SERVICO_ACESSO_PESSOA            ("Serviço Acesso a Pessoas"),
    SERVICO_SUPRIMENTO               ("Serviço Suprimento"),
    SERVICO_SUPRIMENTO_TRANSITO      ("Solicitação de Trânsito"),
    SERVICO_SUPRIMENTO_FORNECEDOR    ("Fornecedor"),
    SERVICO_SUPRIMENTO_CONDUTOR      ("Condutor"),
    SERVICO_RETIRADA_RESIDUO_LIXO    ("Serviço de Retirada de Resíduos/Lixo"),
    HOSPEDE                          ("Hóspede"),
    ENVIO_EMAIL                      ("Envio E-mail"),
    PASSAGEIRO                       ("Passageiro"),
    PESSOA                           ("Pessoa"),
    POLICIA_FEDERAL                  ("Polícia Federal"),
    RECEITA_FEDERAL                  ("Receita Federal")
    ;

    private String porExtenso;

    private TipoExcecao(String porExtenso) {
        this.porExtenso = porExtenso;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public static TipoExcecao from(String s) {
        if (s == null) {
            return null;
        }
        for (TipoExcecao tipo : values()) {
            if (tipo.porExtenso.equals(s)) {
                return tipo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return porExtenso;
    }

}
