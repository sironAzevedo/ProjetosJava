/*
 * Fachada para os métodos de serviço do sistema.
 */
package br.com.petrobras.sistam.common.business;

/**
 * Fachada para os métodos de serviço do sistema. Deve herdar de todas as
 * interfaces que disponibilizam métodos para o cliente desktop.
 */
public interface SistamService extends AcessoService, 
                                       JobService, 
                                       AgenciamentoService,
                                       AgenciaService,
                                       PortoService,
                                       OperacaoService,
                                       ManobraService,
                                       NotesWebService,
                                       CommonService,
                                       CertificadoService,
                                       EmbarcacaoService,
                                       PendenciaService,
                                       TaxaService,
                                       DocumentoService, 
                                       AnexoService,
                                       VisitaService,
                                       ServicoProtecaoService,
                                       EmpresaService,
                                       XmlService,
                                       TransferService,
                                       EmpresaProtecaoService,
                                       PessoaProtecaoService
                                       {

}
