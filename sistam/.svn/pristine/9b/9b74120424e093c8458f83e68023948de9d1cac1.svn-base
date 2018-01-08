package br.com.petrobras.sistam.service.impl;

import br.com.petrobras.sistam.common.business.JobService;
import br.com.petrobras.sistam.common.valueobjects.FiltroRelatorioTimesheet;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobServiceImpl implements JobService {

    Logger log = LoggerFactory.getLogger(JobServiceImpl.class);
    private NotesWebServiceImpl notesWebService;

    public void setNotesWebService(NotesWebServiceImpl notesWebService) {
        this.notesWebService = notesWebService;
    }

    @Override
    public void executarEnvioEmailRelatorioTimesheet() {
        log.info("Inicio o envio de emails do relatorio timesheet 'executarEnvioEmailRelatorioTimesheet'");
        try {
            notesWebService.enviarEmailComEmbarcacoesPorStatusParaRelatorioTimesheet(new FiltroRelatorioTimesheet(), Collections.EMPTY_LIST);
            log.info("Fim do envio de emails do relatorio timesheet 'executarEnvioEmailRelatorioTimesheet'");
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public void executarEnvioEmailRelatorioTimesheetAgrupadoPorFrota() {
        log.info("Inicio o envio de emails do relatorio timesheet 'executarEnvioEmailRelatorioTimesheetAgrupadoPorFrota'");
        try {
            notesWebService.enviarEmailComEmbarcacoesPorFrotaParaRelatorioTimesheet(new FiltroRelatorioTimesheet(), Collections.EMPTY_LIST);
            log.info("Fim do envio de emails do relatorio timesheet 'executarEnvioEmailRelatorioTimesheetAgrupadoPorFrota'");
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public void executarEnvioEmailParaRelatorioTimesheet() {
        log.info("Inicio o envio de emails do relatorio timesheet 'executarEnvioEmailParaRelatorioTimesheet'");
        try {
            notesWebService.enviarEmailParaRelatorioTimesheetCarga(new FiltroRelatorioTimesheet(), Collections.EMPTY_LIST);
            log.info("Fim do envio de emails do relatorio timesheet 'executarEnvioEmailParaRelatorioTimesheet'");
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}