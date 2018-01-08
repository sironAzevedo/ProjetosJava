
package br.com.petrobras.sistam.common.util;

import java.io.*;
import java.text.Normalizer;

public class FileUtil
{


  /**
   * Calls writeToFile with createDir flag false.
   *
   * @see writeToFile(String fileName, InputStream iStream, boolean createDir)
   *
   * @created 2002-05-02 by Alexander Feldman
   *
   */
  public static void writeToFile(String fileName, InputStream iStream)
    throws IOException
  {
    writeToFile(fileName, iStream, false);
  }


  /**
   * Writes InputStream to a given <code>fileName<code>.
   * And, if directory for this file does not exists,
   * if createDir is true, creates it, otherwice throws OMDIOexception.
   *
   * @param fileName - filename save to.
   * @param iStream  - InputStream with data to read from.
   * @param createDir (false by default)
   * @throws IOException in case of any error.
   *
   * @refactored 2002-05-02 by Alexander Feldman
   * - moved from OMDBlob.
   *
   */
  public static void writeToFile(String fileName, InputStream iStream,
    boolean createDir)
    throws IOException
  {
    String me = "FileUtils.WriteToFile";
    if (fileName == null)
    {
      throw new IOException(me + ": filename is null");
    }
    if (iStream == null)
    {
      throw new IOException(me + ": InputStream is null");
    }

    File theFile = new File(fileName);

    // Check if a file exists.
    if (theFile.exists())
    {
       String msg =
         theFile.isDirectory() ? "directory" :
         (! theFile.canWrite() ? "not writable" : null);
       if (msg != null)
       {
         throw new IOException(me + ": file '" + fileName + "' is " + msg);
       }
    }

    // Create directory for the file, if requested.
    if (createDir && theFile.getParentFile() != null)
    {
      theFile.getParentFile().mkdirs();
    }

    // Save InputStream to the file.
    BufferedOutputStream fOut = null;
    try
    {
      fOut = new BufferedOutputStream(new FileOutputStream(theFile));
      byte[] buffer = new byte[32 * 1024];
      int bytesRead = 0;
      while ((bytesRead = iStream.read(buffer)) != -1)
      {
        fOut.write(buffer, 0, bytesRead);
      }
    }
    catch (Exception e)
    {
      throw new IOException(me + " failed, got: " + e.toString());
    }
    finally
    {
      close(iStream, fOut);
    }
  }


  /**
   * Closes InputStream and/or OutputStream.
   * It makes sure that both streams tried to be closed,
   * even first throws an exception.
   *
   * @throw IOException if stream (is not null and) cannot be closed.
   *
   */
  protected static void close(InputStream iStream, OutputStream oStream)
    throws IOException
  {
    try
    {
      if (iStream != null)
      {
        iStream.close();
      }
    }
    finally
    {
      if (oStream != null)
      {
        oStream.close();
      }
    }
  }
  
  
  public static void copyFileTo(String source, String target) throws IOException {
      
    if (new File(target).exists()) {
        new File(target).delete();
    }
      
    if (!new File(target).getParentFile().exists())
        new File(target).getParentFile().mkdirs();
    
    InputStream in = new FileInputStream(source);
    OutputStream out = new FileOutputStream(target);  
      
    byte[] buf = new byte[1024];
    int len;
    while ((len = in.read(buf)) > 0) {
        out.write(buf, 0, len);
    }
    in.close();
    out.close();
  }
  
  
    public static String unaccent(String src) {
        return Normalizer
                .normalize(src, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^\\x20-\\x7E]", "")
                .replaceAll("[\\\\:;&=<>/\\*\\?\"|]", "");
    }

    public static File createFileOnTemp(String nomeArquivo, String extensao) throws IOException {
        File fileTemp = File.createTempFile(nomeArquivo, extensao);

        String absolutePath = fileTemp.getAbsolutePath();
        String fileName = fileTemp.getName();

        File file = new File(absolutePath.replaceAll(fileName, nomeArquivo).concat(extensao));
        int increment = 1;
        while (file.exists()) {
            file = new File(absolutePath.replaceAll(fileName, nomeArquivo)
                    .concat("_").concat(String.valueOf(increment++)).concat(extensao));
        }
        fileTemp.delete();
        return file;
    }

}