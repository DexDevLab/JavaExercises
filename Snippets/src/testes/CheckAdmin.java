/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;



/**
 * Check if java program is running as admin,
 * creating a dummy file in Windows C root.
 * 
 */
public class CheckAdmin
{
  
  public static void main (String[] args)
  {
    File adminchk = new File ("C:\\admin.dc");
    try
    {
      FileUtils.touch(adminchk);
      if (adminchk.exists())
      {
        System.out.println("PROGRAMA EXECUTADO COMO ADMINISTRADOR");
        FileUtils.deleteQuietly(adminchk);
      }
    }
    catch (IOException e)
    {
      System.out.println("O PROGRAMA NÃO FOI EXECUTADO COMO ADMINISTRADOR");
      FileUtils.deleteQuietly(adminchk);
    }
  }
  
}










