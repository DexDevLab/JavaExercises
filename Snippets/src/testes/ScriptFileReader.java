package testes;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;


/**
  * @author Daniel Augusto Monteiro de Almeida
  * @since 12/02/2019
  * @version 1.1.0-20191217-86
  *
  * Read a custom Script File.
  */
public class ScriptFileReader
{

  public ScriptFileReader()
  {
    organized = false;
    openbracket = false;
    closebracket = false;
    searchDone = false;
    searchCount = 0;
    breakCount = 0;
    underline = new ArrayList<>();
    underline2 = new ArrayList<>();
    output = "";
    substr = "";
    linecount = 0;
    str = "";
    organizedOutput = new ArrayList<>();
    unorganizedOutput = new ArrayList<>();
    itemListing = new ArrayList<>();
  }

  private File scriptFile;
  private boolean organized;
  private boolean openbracket;
  private boolean closebracket;
  private boolean searchDone;
  private int searchCount;
  private int breakCount;
  private final List<String> underline;
  private final List<String> underline2;
  private String output;
  private String substr;
  private int linecount;
  private String str;
  private final List<String> organizedOutput;
  private final List<String> unorganizedOutput;
  private final List<String> itemListing;


  //  method for testing.
  //  public static void main(String[] args)
  //  {
  //    new ScriptFileReader().running();
  //  }
  //
  //  public void running()
  //  {
  //    ScriptFileRead sfr = new ScriptFileReader.ScriptFileRead();
  //    File fil = new File("C:/coreScript2.cfg");
  //    sfr.setScript(fil, true);
  //    System.out.println(sfr.exportOrgOutput());
  //    List<String> example = new ArrayList<>();
  //    example = sfr.searchItemsFromCategory("machineList");
  //    System.out.println(example);
  //  }

  private void cleanVars()
  {
    this.linecount = 0;
    this.underline.clear();
  }

  private void readScript(File scriptFile, boolean isOrganized)
  {
    this.scriptFile = scriptFile;
    this.organized = isOrganized;
    List<String> lis = new ArrayList<>();
    if (this.organized)
    {
      try
      {
        lis = FileUtils.readLines(this.scriptFile, "UTF-8");
        lis.forEach((line) ->
        {
          if (line.contains("{"))
          {
            this.openbracket = true;
          }
          else if (this.openbracket)
          {
            try
            {
              this.underline.add(Files.readAllLines(this.scriptFile.toPath()).get(this.linecount+1));
              this.underline.forEach((lines) ->
              {
                if (lines.contains("{"))
                {
                  substr = line.trim().replaceAll("\\s+", " ");
                  this.organizedOutput.add("Category: " + substr);
                }
                else
                {
                  substr = line.trim().replaceAll("\\s+", " ");
                  this.organizedOutput.add("Item: " + substr);
                }
              });
              this.underline.clear();
              this.openbracket = false;
            }
            catch (IOException ex)
            {
              logging("ERROR AT READSCRIPT()");
            }
          }
          else
          {
            if (line.contains("}"))
            {
              this.closebracket = true;
            }
            else if (closebracket)
            {
              try
              {
                this.underline.add(Files.readAllLines(this.scriptFile.toPath()).get(this.linecount+1));
              }
              catch (IOException ex)
              {
                System.out.println("ERROR");
              }
              this.underline.forEach((lines2) ->
              {
                if (!lines2.contains("}"))
                {
                  try
                  {
                    this.underline2.add(Files.readAllLines(this.scriptFile.toPath()).get(this.linecount+2));
                    this.underline2.forEach((lines3) ->
                    {
                      substr = line.trim().replaceAll("\\s+", " ");
                      this.organizedOutput.add("\nCategory: " + substr);
                    });
                    this.underline2.clear();
                    this.closebracket = false;
                  }
                  catch (IOException ex)
                  {
                    logging("ERROR AT READSCRIPT()");
                  }
                }
              });
              this.underline.clear();
            }
            else
            {
              substr = line.trim().replaceAll("\\s+", " ");
              this.organizedOutput.add("Item: " + substr);
            }
          }
          this.linecount++;
        });
        this.cleanVars();
      }
      catch (IOException ex)
      {
        logging("ERROR AT READSCRIPT()");
      }
    }
    else
    {
      readScript(this.scriptFile, true);
      this.organizedOutput.forEach((line) ->
      {
        if (line.contains("Category: ") | line.contains("Item: "))
        {
          line = line.replace("Category: ", "");
          line = line.replace("Item: ", "");
          this.unorganizedOutput.add(line);
        }
      });
      this.organized = false;
      this.cleanVars();
    }
  }

  /**
   * Pass to a String the complete output of a list.
   * @param organize - true or false if its organized
   * @return the output in a String form
   */
  public String exportOutput(boolean organize)
  {
    this.str = null;
    readScript(this.scriptFile, organize);
    if (this.organized)
    {
      this.organizedOutput.forEach((line) ->
      {
        if (this.str == null)
        {
          this.str = line;
        }
        else
        {
          this.str = str + "\n" + line;
        }
      });
    }
    else
    {
      this.unorganizedOutput.forEach((line) ->
      {
        if (this.str == null)
        {
          this.str = line;
        }
        else
        {
          this.str = str + "\n" + line;
        }
      });
    }
    return this.str;
  }

  private List<String> searchItemsFromCategory(String category)
  {
    readScript(this.scriptFile, true);
    this.organizedOutput.forEach((line2) ->
    {
      line2 = line2.trim().replaceAll("\\s+", " ");
      if (line2.equals("Category: " + category))
      {
        this.breakCount = this.linecount + 1;
        while (!this.searchDone)
        {
          this.organizedOutput.forEach((line) ->
          {
            if (this.searchCount == this.breakCount)
            {
              if(line.contains("Item: "))
              {
                line = line.replace("Item: ", "");
                this.itemListing.add(line);
              }
              else
              {
                this.searchDone = true;
              }
            }
            this.searchCount++;
          });
          this.searchCount = 0;
          this.breakCount++;
        }
      }
      this.linecount++;
    });
    this.cleanVars();
    return this.itemListing;
  }

  /**
   * Load a defined Script File and put values into a list.
   *
   * @param category - the keyword to find as category
   * @return lis - the output list
   *
   */
  public List<String> loadScript(String category)
  {
    readScript(this.scriptFile, true);
    List<String> lis = new ArrayList<>();
    lis = this.searchItemsFromCategory(category);
    return lis;
  }

  /**
   * Load a defined Script File, get the single line of a
   * specific category and returns it to a String.
   *
   * @param scriptFil - the scriptFile
   * @param category - the keyword to find as category
   * @return lis - the output list
   *
   */
  public String exportSingleLineFromScript(File scriptFil, String category)
  {
    this.scriptFile = scriptFil;
    List<String> outputlis = new ArrayList<>();
    outputlis = loadScript(category);
    outputlis.forEach((item) ->
    {
      output = item;
    });
    return output;
  }

  private void logging (String message) { System.out.println(message); }

}