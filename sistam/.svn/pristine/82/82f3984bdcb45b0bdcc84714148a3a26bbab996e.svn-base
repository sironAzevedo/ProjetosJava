/*
 * Serviço de controle e busca de visita. 
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Transporte;
import br.com.petrobras.sistam.common.entity.Visita;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioVisita;
import br.com.petrobras.sistam.common.valueobjects.RelatorioVisitaVO;
import java.util.List;

public interface VisitaService {

    @AuthorizedResource("")
    public List<Visita> buscarVisitasDoAgenciamento(Agenciamento agenciamento);

    @AuthorizedResource("")
    public Visita salvarVisita(Visita visita);

    @AuthorizedResource("")
    public void excluirVisita(Visita visita);

    @AuthorizedResource("")
    public void validarSalvarTransporte(Transporte transporte);

    @AuthorizedResource("")
    public Transporte salvarTransporte(Transporte transporte);

    @AuthorizedResource("")
    public void excluirTransporte(Transporte transporte);

    @AuthorizedResource("")
    public List<RelatorioVisitaVO> buscarTransportesUtilizadosNaVisita(FiltroRelatorioVisita filtro);
}
