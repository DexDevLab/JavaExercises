package testes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import testes.JSONCreator.Entry;


/**
 * Exercise to create JSON Nodes.
 * @version 1.0.0-201909018-4
 */
public class JSONCreator
{
  
  public static class Entry 
  {
    
    private final String name;

    public Entry(String name) 
    { 
      this.name = name;
    }
    
    private List<Entry> children;

    public void add(Entry node)
    {
     if (children == null) { children = new ArrayList<>(); }
     children.add(node);
    }
    
    public String getJSONString(Entry entry)
    {
      Gson g = new GsonBuilder().setPrettyPrinting().create();
      return g.toJson(entry);
    }
    
  }
  
  public static class ToFile
  {
    
    private String path;
    
    public void setPath(String path)
    {
      this.path = path;
    }
    
    public void write(String json)
    {
      FileWriter s;
      try
      {
        s = new FileWriter(path);
        s.write(json);
        s.flush();
      } catch (IOException ex){}
    }
     
  }
}









