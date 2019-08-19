/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.util.Scanner;



/**
 * Detect Prime numbers in interval made by
 * two variabels and return the quantity.
 * 
 */
public class PrimeNumbers
{
  
  public static void main (String[] args)
  {
    int num1, num2;
    System.out.println("Digite o primeiro número do intervalo");
    Scanner scanner = new Scanner (System.in);
    num1 = scanner.nextInt();
    System.out.println("Digite o segundo número do intervalo");
    num2 = scanner.nextInt();
    int x = PrimeNumbers.countPrimes(num1, num2);
    System.out.println("A quantidade de primos entre " + num1 + " e " + num2 + " é de " + x);
  }
  
  public static int countPrimes(int num1, int num2)
  {
    int x = 0;
    int cont = 0;
    for (int intervalo = num1; intervalo <= num2; intervalo++)
    {
      for (int i = 1; i <= intervalo; i++) 
      {
        if (intervalo % i == 0) { cont++; }
      }
      if (cont == 2) { x++; }
      cont = 0;
    }
    return x;
  }
  
}
