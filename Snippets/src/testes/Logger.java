package testes;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static jdk.nashorn.internal.objects.NativeError.getStackTrace;
import static org.apache.commons.io.FileUtils.sizeOfDirectory;
import static org.apache.commons.io.FileUtils.writeStringToFile;



/**
 *
 * @author Daniel Augusto Monteiro de Almeida
 * @since 10/06/2019
 * @version 1.0.0-20191017-9
 * Generates a Log.
 * 
 */
public class Logger
{
  /** 
    * Log message's date/time format
    * @see #time
    */
  DateFormat df;
  /** 
    * Collect actual log message's date/time
    * @see #df
    */
  String time;
  /**
   * Log file's date/time format
   * @see #time2
   */ 
  DateFormat df2;
  /** 
    * Collect log file's date/time
    * @see #df
    * @see #logname
    */
  String time2;
  /** 
    * Define formatted name for the logfile.
    * @see #time2
    * @see #logfile
    */
  File logname;
  /** Folder containing the logs. */
  File logdir;
  /** Log file object with date/time recorded. */
  File logfile;
  /** Line separator */
  final String line = System.getProperty("line.separator");
  /** Store log folder size in bytes. */
  long logsize;
  
  
  
  public void setMessageFormat(String dateformat) { this.df = new SimpleDateFormat(dateformat); }
  
  public String getMessageFormat() { return this.df.toString(); }
  
  public void setLogNameFormat(String dateformat) { this.df2 = new SimpleDateFormat(dateformat); }
  
  public String getLogNameFormat() { return this.df2.toString(); }
  
  public void setLogFile(File fil) { this.logfile = fil; }
  
  public File getLogFile() { return this.logfile; }
  
  public void setLogDir(File dir) { this.logdir = dir; }
  
  public File getLogDir() { return this.logdir; }
  
  public long getLogSize() { return this.logsize = sizeOfDirectory(this.logdir); }
  
  
  /**
   * Escreve o log no console e gera a mesma frase externamente.
   * 
   * @param throwable - o throwable da exceção (se necessário)
   * @param logtype - "INFO" para informações em geral e "ERRO" para erros
   * @param logmsg - A mensagem a ser escrita
   */
  public void log (Throwable throwable, String logtype, String logmsg)
  {
    try
    {
      if (this.logfile == null)
      {
        this.logname = new File (this.df2.format(Calendar.getInstance().getTime()) + ".txt");
        this.logfile = new File (this.logdir + File.separator + this.logname);
      }
      this.time = this.df.format(Calendar.getInstance().getTime());
      logtype = "[" + logtype + "]: ";
      if (throwable != null) { logmsg = (line + logmsg + line + getStackTrace(throwable)); }
      System.out.println(time + logtype + logmsg);
      writeStringToFile(this.logfile, this.time + logtype + logmsg + line, "UTF-8", true);
    } 
    catch (IOException ex)
    {
      System.out.println("ERRO CRÍTICO: ERRO AO GERAR O ARQUIVO DE LOG.");
    }
  }

//  /* For Testing. */
//  public static void main(String[] args)
//  {
//    Logger l = new Logger();
//    l.setMessageFormat("yyyy/MM/dd HH:mm:ss");
//    l.setLogNameFormat("yyyy-MM-dd--HH.mm.ss");
//    l.setLogDir(new File("D:"));
//    l.log(null, "INFO", "Início");
//    l.log(null, "INFO", "Teste1");
//    l.log(null, "INFO", "Teste2");
//    l.log(null, "INFO", "Teste3");
//    l.log(null, "INFO", "Teste4");
//    l.log(null, "INFO", "Teste5");
//    l.log(null, "INFO", "Teste6");
//    l.log(null, "INFO", "Teste7");
//    l.log(null, "INFO", "Teste8");
//    l.log(null, "INFO", "Teste9");
//    l.log(null, "INFO", "Teste10");
//    l.log(null, "INFO", "Teste11");
//    l.log(null, "INFO", "Teste12");
//    l.log(null, "INFO", "Teste13");
//    try
//    {
//      FileUtils.writeStringToFile(new File("C:\blabla.txt"), "blabla", "UTF-8");
//    } 
//    catch (IOException ex)
//    {
//      l.log(ex, "ERRO", "Teste de Exceção1");
//    }
//    l.log(null, "INFO", "Teste14");
//    l.log(null, "INFO", "Fim.");
//  }
  
}
