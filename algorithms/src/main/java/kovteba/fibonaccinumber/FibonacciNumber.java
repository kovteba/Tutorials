package kovteba.fibonaccinumber;

import java.util.Arrays;

public class FibonacciNumber {

   public static void main(String[] args) {
      int[] arr = printFib(100);
      System.out.println(Arrays.toString(arr));
      System.out.println(fib(100));
      System.out.println(fib(10));
      System.out.println(Arrays.toString(printFib(11)));



      int n = 100;
      long[] mem = new long[n + 1];
      Arrays.fill(mem, -1);
      System.out.println(fibRecursiveEffective(n, mem));

      System.out.println(fibRecursiveNonEffective(51));


   }

   public static long fib(int n) {
      if (n == 0)
         return 0;
      if (n == 1)
         return 1;

      long[] arr = new long[n + 1];
      arr[0] = 0;
      arr[1] = 1;
      for (int i = 2; i <= n; i++)
         arr[i] = arr[i - 1] + arr[i - 2];
      return arr[n];
   }

   private static int[] printFib(int n){
      int[] result = new int[n];
      result[0] = 0;
      result[1] = 1;
      for (int i = 2; i < n; i++)
         result[i] = result[i - 1] + result[i - 2];
      return result;
   }

   //not effective
   private static long fibRecursiveNonEffective(int n){
      if (n <= 1)
         return n;
      return fibRecursiveNonEffective(n - 1) + fibRecursiveNonEffective(n - 2);
   }

   //меморизация. чтобы не дублировать повторные запросы
   private static long fibRecursiveEffective(int n, long[] mem){
      if (mem[n] != -1)
         return mem[n];

      if (n <= 1)
         return n;

      long result = fibRecursiveEffective(n - 1, mem) + fibRecursiveEffective(n - 2, mem);
      mem[n] = result;

      return result;
   }




}
