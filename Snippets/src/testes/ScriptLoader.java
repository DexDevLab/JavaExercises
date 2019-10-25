package testes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


/**
  * @author Daniel Augusto Monteiro de Almeida
  * @since 10/07/2019
  * @version 1.0.0-20191017-15
  *
  * Just a class for testing Lists, collections
  * and recursive list items.
  *
  */
public class ScriptLoader
{

  public List<String> loadScript(File scriptfil, int startlin, int endlin)
  {
    List<String> lis = new ArrayList<>();
    startlin--;
    endlin--;
    while (startlin <= endlin)
    {
      try
      {
        String entry = Files.readAllLines(scriptfil.toPath()).get(startlin);
        lis.add(entry);
      }
      catch (IOException ex) { System.out.println("EXCEÇÃO EM loadScript(File, int, int): NÃO FOI POSSÍVEL INCLUIR A LINHA " + startlin + " NA LISTA."); }
      startlin++;
    }
    return lis;
  }

  public void runCMD (List<String> list)
  {
    list.forEach((cmdlin) ->
    {
      try
      {
        System.out.println("Executando comando " + cmdlin);
        new ProcessBuilder("cmd", "/c", cmdlin).start();
        Thread.sleep(500);
      }
      catch (IOException ex)
      {
        System.out.println("EXCEÇÃO EM runCMD(String<List>) - COMANDO " + cmdlin + " INVÁLIDO OU FALHA CRÍTICA DO CMD.");
      }
      catch (InterruptedException ex2)
      {
        System.out.println("EXCEÇÃO EM runCMD(String<List>) - THREAD INTERROMPIDA DE MANEIRA INESPERADA.");
      }
    });
  }

  public static void main(String[] args) throws IOException
  {
//    File scriptfile = new File("D:" + File.separator + "core.cfg");
//    List<String> test = new ScriptLoader().loadScript(scriptfile, 2, 4);
//    new ScriptLoader().runCMD(test); 
  }

}


