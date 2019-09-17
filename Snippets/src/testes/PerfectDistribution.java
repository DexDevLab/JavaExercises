/*
 * 
 */
package testes;


/**
 *  This was an Exercise from a Facebook's Java group.
 *  The user wants to make a distributed division
 *  between numbers, in a better manner.
 *  This is the code another user made. Gotta try to
 *  understand it to make DistributedDivision work
 *  properly.
 *  The Exercise was: "Create a program who can be distribute
 *  properly any possible division in correct multiples. Ex.:
 *  2000 distributed perfectly in 5 x 310, 1 x 450.
 * 
 */
public class PerfectDistribution
{
  
      public static void main(String[] args) {
        int[] arr = {60, 110, 310, 450};
        int valor = 2000;
        int[] resultado = calcular(valor, arr);
        if (resultado != null) {
            for (int i = 0; i < resultado.length; i++) {
                if (resultado[i] > 0) {
                    System.out.printf("%d vezes %d%n", resultado[i], arr[i]);
                }
            }
        } else {
            System.out.println("resultado não encontrado");
        }
    }

    public static int[] calcular(int num, int... weight) {
        int[] max = new int[weight.length];
        for (int i = 0; i < weight.length; i++) {
            max[i] = (int) Math.ceil((double) num / weight[i]);
        }
        int[] indices = new int[weight.length];
        do {
            int sum = 0;
            for (int i = 0; i < weight.length; i++) {
                sum += weight[i] * indices[i];
            }
            if (sum == num) {
                return indices;
            }
        } while (incrementar(indices, max));
        return null;
    }

    private static boolean incrementar(int[] indices, int[] max) {
        for (int i = indices.length - 1; i >= 0; i--) {
            if (++indices[i] <= max[i]) {
                break;
            }
            if (i == 0) {
                return false;
            }
            indices[i] = 0;
        }
        return true;
    }
  
  
  
}



















