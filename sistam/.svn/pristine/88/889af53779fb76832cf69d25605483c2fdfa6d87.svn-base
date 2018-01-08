/*
 * Serviço de comunicação para envio de e-mail com relatorio no notesWeb.  
 */
package br.com.petrobras.sistam.common.business;

import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioTimesheet;
import java.util.List;

public interface NotesWebService {

    public void enviarEmailComEmbarcacoesPorStatusParaRelatorioTimesheet(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas); 
    public void enviarEmailComEmbarcacoesPorFrotaParaRelatorioTimesheet(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas); 
    public void enviarEmailParaRelatorioTimesheetManual(FiltroRelatorioTimesheet filtro, List<Agencia> agenciasAutorizadas);
}
