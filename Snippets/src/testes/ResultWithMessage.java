/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import javax.swing.JOptionPane;



/**
 * Create a simple MessageDialog showing some result.
 * 
 */
public class ResultWithMessage
{
  
  public static void main(String[] args)
  {
    new ResultWithMessage().iniciar();
  }
  
  public int soma(int a, int b)
  {
    return a+b;
  }
  
  public void iniciar ()
  {
    int resultado = soma(2,5);
    JOptionPane.showMessageDialog(null, "A soma é: " + Integer.toString(resultado) + "");
  }
}
  
















