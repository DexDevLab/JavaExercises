/*
* Snippets, code samples and misc excercises.
*/
package testes;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Test if Specific Process is opened.
 */
public class TestProcessOpen
{
  static String _pName = "winword.exe";
  public static void main(String[] args)
  {
    try
    {
      String line;
      String _isRunning = "NÃO está executando!";
      Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
      try (BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream())))
      {
        while ((line = input.readLine()) != null)
        {
          if (line.contains(_pName))
          {
            _isRunning = "está executando!";
          }
        }
      }
      System.out.println("O processo " + _pName + " " + _isRunning);
    }
    catch (IOException ex)
    {
      System.out.println("ERRO");
    }
  }

}
