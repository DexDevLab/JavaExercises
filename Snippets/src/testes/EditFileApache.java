/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;


import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;


/**
 * Same as EditFile, but using Apache Commons
 * File Utils.
 */
public class EditFileApache
{
  static void replaceInFile(String filePath, String oldString, String newString)
  {
    File fileToBeModified = new File(filePath);
    String oldContent;
    String newContent;
    try
    {
      oldContent = FileUtils.readFileToString(fileToBeModified, "UTF-8");
      newContent = oldContent.replaceAll(oldString, newString);
      FileUtils.write(fileToBeModified, newContent, "UTF-8");
    }
    catch (IOException ex)
    {
      Logger.getLogger(EditFileApache.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

    public static void main(String[] args)
    {
      replaceInFile("C:/core2.ps1", "= \"FIELDPC02\"", "= \"HOSTNAME\"");

      System.out.println("done");
    }
}
