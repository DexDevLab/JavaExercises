package testes;

import testes.JSONCreator.Entry;
import testes.JSONCreator.ToFile;



/**
 * Exercise to test JSON writing and reading.
 * @version 0.1.0-20190903-8
 */
public class JSONInputAndOutput
{
  
  public static void main(String[] args)
  {
   
    JSONCreator.Entry children1 = new Entry("Children1");
    children1.add(new Entry("Children1.1"));
    children1.add(new Entry("Children1.2"));

    Entry children2 = new Entry("Children2");

    Entry rootNode = new Entry("RootNode");
    rootNode.add(children1);
    rootNode.add(children2);

    String a = rootNode.getJSONString(rootNode);
//
//    a = a.replaceAll("children", "");
//    a = a.replaceAll("name", "");
//
//    System.out.println(a);

    JSONCreator.ToFile b = new ToFile();
    b.setPath("file.json");
    b.write(a);
  }

}












