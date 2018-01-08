package br.com.petrobras.sistam.desktop.transfer;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.fcorp.common.exception.SystemException;
import br.com.petrobras.fcorp.common.support.AssertUtils;
import br.com.petrobras.fcorp.swing.ExceptionUtil;
import br.com.petrobras.fcorp.swing.base.BaseApp;
import br.com.petrobras.fcorp.swing.components.dialog.FinalizeOnDisposeDialog;
import br.com.petrobras.snarf.filetransfer.FileDownloadClient;
import br.com.petrobras.snarf.filetransfer.FileTransferEntry;
import br.com.petrobras.snarf.filetransfer.FileTransferHandler;
import br.com.petrobras.snarf.filetransfer.FileUploadClient;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import org.jdesktop.swingworker.SwingWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author x4rb
 */
public class SistamFileTransferDialog extends FinalizeOnDisposeDialog {

    private final static Charset charset = Charset.defaultCharset();
    private static final Logger log = LoggerFactory.getLogger(SistamFileTransferDialog.class);
    
    @SuppressWarnings("PMD.DoNotUseThreads")
    private SistamFileTransferDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(parent);
    }

    @SuppressWarnings("PMD.DoNotUseThreads")
    public static void download(java.awt.Frame parent, final FileDownloadClient client) {
        AssertUtils.assertNotNull(client, "FileDownloadClient não pode ser nulo");

        final SistamFileTransferDialog dialog = new SistamFileTransferDialog(parent, true);
        client.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        dialog.progressBar.setValue((int) client.getPercentProgress());
                        dialog.progressBar.setString(client.getFile().getName());
                        if (client.getPercentProgress() >= FileTransferHandler.ONE_HUNDRED_PERCENT) {
                            dialog.progressBar.setIndeterminate(true);
                        }
                    }
                });
            }
        });

        SwingWorker sw = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                client.download();
                return null;
            }

            @Override
            protected void done() {
                dialog.setVisible(false);
            }
        };
        sw.execute();
        dialog.setVisible(true);
        dialog.dispose();
        try {
            sw.get();
        } catch (Exception ex) {
            throwCleanedException(ex);
        }
    }

    @SuppressWarnings("PMD.DoNotUseThreads")
    public static String upload(java.awt.Frame parent, final FileUploadClient client) {
        AssertUtils.assertNotNull(client, "FileUploadClient não pode ser nulo");

        final SistamFileTransferDialog dialog = new SistamFileTransferDialog(parent, true);
        client.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        dialog.progressBar.setValue((int) client.getPercentProgress());
                        dialog.progressBar.setString(client.getCurrentFile());
                        if (client.getPercentProgress() >= FileTransferHandler.ONE_HUNDRED_PERCENT) {
                            dialog.progressBar.setIndeterminate(true);
                        }
                    }
                });
            }
        });

        SwingWorker sw = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                if (BaseApp.isFatClient()) {
                    // ExecuÃ§Ã£o em modo mock
                    if (!(BaseApp.getApplication().getService() instanceof FileTransferHandler)) {
                        throw new SystemException("O serviço da aplicação precisa implementar a interface FileTransferHandler");
                    } else {
                        List<FileTransferEntry> l = new ArrayList<FileTransferEntry>();
                        for (File file : client.getFiles()) {
                            l.add(new FileTransferEntry(getFileName(file), file.length(), new FileInputStream(file)));
                        }
                        ((FileTransferHandler) BaseApp.getApplication().getService()).handleFileUpload(l, client.getParametros());
                        return "OK";
                    }
                } else {
                    return client.upload();
                }
            }

            @Override
            protected void done() {
                dialog.setVisible(false);
            }
        };
        sw.execute();
        dialog.setVisible(true);
        dialog.dispose();

        try {
            return sw.get() + "";
        } catch (Exception ex) {
            throwCleanedException(ex);
            return null;
        }
    }


    @SuppressWarnings("PMD.DoNotUseThreads")
    public static String getFileName(File file) {
        String name = file.getName();
        log.debug("Transfer Dialog");
        log.debug("Nome recebido: " + name);
        log.debug("Charset: " + charset);
        try {
            byte[] bytes = name.getBytes("utf-8");
            return new String(bytes,charset);
        } catch (UnsupportedEncodingException e) {
            throw new SystemException(e);
        }
    }

    @SuppressWarnings("PMD.DoNotUseThreads")
    private static void throwCleanedException(Exception ex) {
        Throwable t = ExceptionUtil.cleanException(ex);
        if (t instanceof SystemException) {
            throw (SystemException) t;
        } else if (t instanceof BusinessException) {
            throw (BusinessException) t;
        } else {
            throw new SystemException(t);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        progressBar = new br.com.petrobras.fcorp.swing.components.SProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TransferÃªncia de Arquivos");
        setResizable(false);
        getContentPane().add(progressBar, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>                        
    // Variables declaration - do not modify                     
    private br.com.petrobras.fcorp.swing.components.SProgressBar progressBar;
    // End of variables declaration                   
}