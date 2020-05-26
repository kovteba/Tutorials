package kovteba.fibonaccinumber;

import java.util.Arrays;

public class FibonacciNumber {

   public static void main(String[] args) {
      int[] arr = printFib(100);
      System.out.println(Arrays.toString(arr));

   }

   public static int fib(int n) {
      int sum = 1;
      if (n == 0) return 0;
      if (n == 1) return sum;

      int[] arr = new int[n];
      arr[0] = 0;
      arr[1] = 1;

      for (int i = 2; i < n; i++) {
         arr[i] = arr[i - 1] + arr[i - 2];
         sum += arr[i];
      }
      return sum;
   }

   private static int[] printFib(int n){
      int sum = 1;
      int[] result = new int[n];
      result[0] = 0;
      result[1] = 1;
      for (int i = 2; i < n; i++) {
         result[i] = result[i - 1] + result[i - 2];
         sum += result[i];
      }
      return result;
   }


}
