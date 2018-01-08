package br.com.petrobras.sistam.service.impl;

public class TransferServiceImpl {

    //private XmlService xmlService;

    //public void setXmlService(XmlService xmlService) {
    //    this.xmlService = xmlService;
    //}
    
    //@Override
    //public void handleFileUpload(Collection<FileTransferEntry> l, Map<String, String> parameterMap) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    /*@Override
    public FileTransferEntry handleFileDownload(Map<String, String> parameterMap) {

        String nomeArquivo = null;
        Integer tamanhoEmBytes = 0;
        InputStream is = null;
        
        String tipoDownloadUpload = parameterMap.get(ConstantesI18N.PARAM_TIPO_DOWNLOAD_UPLOAD);
        
        if (TipoDownloadUpload.DOWNLOAD_XML_DUV.getId().equals(tipoDownloadUpload)) {
            byte[] dados = xmlService.downloadXmlDuv(parameterMap);
            tamanhoEmBytes = dados.length;
            is = new ByteArrayInputStream(dados);
            nomeArquivo = TipoDownloadUpload.DOWNLOAD_XML_DUV.getPorExtenso();
        }
        
        return new FileTransferEntry(nomeArquivo, tamanhoEmBytes, is);
    }*/

    
    
    
    
}
