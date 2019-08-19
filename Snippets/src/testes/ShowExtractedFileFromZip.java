/*
 * Snippets, code samples and misc excercises.
 */
package testes;

import java.io.File;
import java.util.List;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;



/**
 * Scans file extract during extraction process
 * and tell his name without the full path.
 */
public class ShowExtractedFileFromZip
{
  
  public static void main (String[] args) throws ZipException
  {
    ZipFile zip = new ZipFile(new File ("C:\\zip.zip"));
    List fileHeaderList = zip.getFileHeaders();
    int f = fileHeaderList.size();
    int i = 0;
    while (i < f)
    {
      FileHeader fh = (FileHeader) fileHeaderList.get(i);
      String input4 = fh.getFileName();
      if (!fh.isDirectory())
      {
        File extzip = new File (input4);
        String zipname = extzip.getName();
        if (zipname.contains(".")) {System.out.println(zipname);}
      }
      i += 1;
    }
  }
  
}
