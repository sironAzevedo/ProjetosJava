/*
 * Serviço de controle e busca de operações.  
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.DocumentacaoLongoCurso;
import br.com.petrobras.sistam.common.entity.DocumentacaoCabotagem;
import br.com.petrobras.sistam.common.entity.DocumentacaoOperacao;
import br.com.petrobras.sistam.common.entity.Operacao;
import br.com.petrobras.sistam.common.enums.TipoOperacao;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioSiscomex;
import br.com.petrobras.sistam.common.valueobjects.RelatorioSiscomexVO;
import java.util.List;

public interface OperacaoService {
    
    @AuthorizedResource("")
    public List<Operacao> buscarOperacoesPorAgenciamento(Agenciamento agenciamento);
    
    @AuthorizedResource("")
    public List<Operacao> buscarOperacoesPorAgenciamentoETipo(Agenciamento agenciamento, TipoOperacao tipo);

    @AuthorizedResource("")
    public Operacao buscarOperacaoPorId(Long id);
    
    @AuthorizedResource("")
    public Operacao salvarOperacao(Operacao operacao); 

    @AuthorizedResource("")
    public Operacao excluirOperacao(Operacao operacao);
    
    @AuthorizedResource("")
    public DocumentacaoCabotagem salvarDocumentacaoCabotagem(DocumentacaoCabotagem documentacaoCabotagem); 
    
    @AuthorizedResource("")
    public DocumentacaoCabotagem excluirDocumentacaoCabotagem(DocumentacaoCabotagem documentacaoCabotagem);
    
    @AuthorizedResource("")
    public RelatorioSiscomexVO buscarDocumentosParaRelatorioSiscomex(FiltroRelatorioSiscomex filtro);
            
    @AuthorizedResource("")
    public List<DocumentacaoCabotagem> buscarDocumentacaoCabotagemPorAgenciamentoETipo(Agenciamento agenciamento, TipoOperacao tipo);

    @AuthorizedResource("")
    public DocumentacaoCabotagem buscarDocumentacaoCabotagemPorId(Long id);
    
    @AuthorizedResource("")
    public DocumentacaoOperacao salvarDocumentacaoProduto(DocumentacaoOperacao documentacaoOperacao); 
    
    @AuthorizedResource("")
    public List<DocumentacaoOperacao> buscarDocumentacaoOperacaoPorDocumentacao(DocumentacaoCabotagem documentacaoCabotagem);

    @AuthorizedResource("")
    public DocumentacaoOperacao buscarDocumentacaoOperacaoPorId(Long id);
    
    @AuthorizedResource("")
    public void excluirDocumentacaoOperacao(DocumentacaoOperacao documentacaoOperacao); 
    
    @AuthorizedResource("")
    public DocumentacaoLongoCurso salvarDocumentacaoLongoCurso(DocumentacaoLongoCurso documentacaoLongoCurso); 
    
    @AuthorizedResource("")
    public void excluirDocumentacaoLongoCurso(DocumentacaoLongoCurso documentacaoLongoCurso); 

    @AuthorizedResource("")
    public List<DocumentacaoLongoCurso> buscarDocumentacaoLongoCursoPorOperacao(Operacao operacao);
    
}
