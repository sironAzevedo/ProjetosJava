package br.com.petrobras.sistam.common.business;

import br.com.petrobras.security.annotation.AuthorizedResource;
import br.com.petrobras.snarf.filetransfer.FileTransferEntry;
import br.com.petrobras.snarf.filetransfer.FileTransferHandler;
import java.util.Collection;
import java.util.Map;

public interface TransferService extends FileTransferHandler {
    
   /**
    * Método que trata os uploads do sistema
    * @param l
    * @param parameterMap
    */
    @AuthorizedResource("")
    @Override
    void handleFileUpload(Collection<FileTransferEntry> l, Map<String, String> parameterMap);

   /**
    * Método que trata os downloads do sistema
    * @param parameterMap
    * @return
    */
    @AuthorizedResource("")
    @Override
    FileTransferEntry handleFileDownload(Map<String, String> parameterMap);
    

}
