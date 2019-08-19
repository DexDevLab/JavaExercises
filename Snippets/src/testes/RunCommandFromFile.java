/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;



/**
 * Run ProcessBuilder cmd using line
 * from file recursively.
 * 
 */
public class RunCommandFromFile
{
  
  public static void main (String[] args) throws IOException
  {
    File file = new File ("C:\\read.txt");
    LineIterator it = FileUtils.lineIterator(file, "UTF-8");
    while (it.hasNext()) 
    {
      String cmdline = it.nextLine();
      new ProcessBuilder("cmd", "/c", cmdline).start();
    }
  }
  
}







