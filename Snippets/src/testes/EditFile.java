/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author t04693
 */
public class EditFile
{
  static void replaceInFile(String filePath, String oldString, String newString)
  {
    File fileToBeModified = new File(filePath);
    String oldContent = "";
    BufferedReader reader = null;
    FileWriter writer = null;
    try
    {
      reader = new BufferedReader(new FileReader(fileToBeModified));
      String line = reader.readLine();
      while (line != null)
      {
        oldContent = oldContent + line + System.lineSeparator();
        line = reader.readLine();
      }
      String newContent = oldContent.replaceAll(oldString, newString);
      writer = new FileWriter(fileToBeModified);
      writer.write(newContent);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        reader.close();
        writer.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

    public static void main(String[] args)
    {
      replaceInFile("C:/core2.ps1", "= \"HOSTNAME\"", "= \"FIELDPC02\"");

      System.out.println("done");
    }
}
