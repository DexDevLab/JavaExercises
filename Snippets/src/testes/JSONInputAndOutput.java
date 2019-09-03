package testes;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.*;
import java.io.FileWriter;
import java.io.IOException;



/**
 * Exercise to test JSON writing and reading.
 * @version 0.1.0-20190903-8
 */
public class JSONInputAndOutput
{
  
  public static void main(String[] args)
  {
   new JSONInputAndOutput().jsonWritingToFile();
  }
  
 
  
  public void jsonWritingToFile()
  {
    
    JsonNodeFactory factory = JsonNodeFactory.instance;
    ObjectNode node = factory.objectNode();
    ObjectNode child1 = factory.objectNode();
    ObjectNode obj = factory.objectNode();
    obj.put("nome", "Daniel Augusto Monteiro de Almeida");
		obj.put("idade",30);
		obj.put("CPF", "123.456.789-00");
		obj.put("altura", "1.77");
    obj.put("peso", "78kg");
    child1.set("Dados Principais", obj);
    node.set("Pessoas", child1);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser jp = new JsonParser();
    String uglyJSONString = obj.toString();
    JsonElement je = jp.parse(uglyJSONString);
    String prettyJsonString = gson.toJson(je);
    
    try (FileWriter file = new FileWriter("file.json")) 
    {
			file.write(prettyJsonString);
			file.flush();
		}
		catch (IOException e) {} 
  }
      
  
  
}













