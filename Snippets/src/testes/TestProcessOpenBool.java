/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Test if Specific Process is opened (now with boolean method).
 */
public class TestProcessOpenBool
{

  public static boolean isProcessOpen(String processName)
  {
    boolean output = false;
    try
    {
      String line;
      String _isRunning = "NÃO está executando!";
      Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
      try (BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream())))
      {
        while ((line = input.readLine()) != null)
        {
          if (line.contains(processName))
          {
            _isRunning = "está executando!";
            output = true;
          }
        }
      }
      System.out.println("O processo " + processName + " " + _isRunning);
    }
    catch (IOException ex)
    {
      System.out.println("FALHA NA EXECUÇÃO DO MÉTODO.");
    }
    return output;
  }

  public static void main(String[] args)
  {
    boolean ready = false;
    while(!ready)
    {
      System.out.println("Validando. Aguarde...");
      ready = isProcessOpen("WINWORD.EXE");
      try
      {
        Thread.sleep(1000);
      }
      catch (InterruptedException ex)
      {

      }
    }
  }
}
