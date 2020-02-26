/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;


import com.github.tuupertunut.powershelllibjava.PowerShell;
import com.github.tuupertunut.powershelllibjava.PowerShellExecutionException;
import java.io.IOException;


/**
 * Test if Specific Process is opened (now with boolean method).
 */
public class TestWindowOpenBool
{

  public static boolean isWindowOpen(String processName)
  {
    boolean output = false;
    String line;
    String _isRunning = "NÃO está executando!";
    try (PowerShell psSession = PowerShell.open())
    {
      line = psSession.executeCommands("Get-Process | Where-Object {$_.MainWindowTitle -ne \"\"} | Select-Object MainWindowTitle");
      if (line.contains(processName))
      {
        _isRunning = "está executando!";
        output = true;
      }
    }
    catch (IOException | PowerShellExecutionException ex)
    {
      System.out.println("ERRO");
    }
    System.out.println("O processo " + processName + " " + _isRunning);
    return output;
  }

  public static void main(String[] args)
  {
    boolean ready = false;
    while(!ready)
    {
      System.out.println("Validando. Aguarde...");
      ready = isWindowOpen("MeuIP");
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
