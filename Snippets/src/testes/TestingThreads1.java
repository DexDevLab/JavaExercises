package testes;


import java.util.Scanner;


/**
 * Testing and working with Concurrency.
 */
public class TestingThreads1
{

  public static void main(String[] args)
  {
    new TestingThreads1().running();
  }

  public void running()
  {
    final Processor processor = new Processor();
    Thread t1 = new Thread(() ->
    {
      processor.produce();
    });
    Thread t2 = new Thread(() ->
    {
      processor.consume();
    });
    t1.start();
    t2.start();
    try
    {
      t1.join();
      t2.join();
    }
    catch (InterruptedException ex)
    {
      System.out.println("ERROR AT JOIN");
    }
    System.out.println("EOF");

  }




  public class Processor
  {
    public void produce()
    {
      synchronized(this)
      {
        System.out.println("Producer Thread running...");
        try
        {
          wait();
        }
        catch (InterruptedException ex)
        {
          System.out.println("ERROR AT PRODUCER");
        }
        System.out.println("Producer Thread resumed...");
      }
    }

    public void consume()
    {
      Scanner scanner = new Scanner(System.in);
      synchronized(this)
      {
        System.out.println("Waiting for return key.");
        scanner.nextLine();
        System.out.println("Return key pressed.");
        notify();
      }
    }
  }
}
