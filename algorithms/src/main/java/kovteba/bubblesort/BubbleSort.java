package kovteba.bubblesort;

import java.util.Arrays;
import java.util.Random;

public class BubbleSort {

   private static int[] arr;

   public static void main(String[] args) {
      initArr(100);
      System.out.println(Arrays.toString(sort(arr)));
   }

   public static int[] sort(int[] array) {
      long startTime = System.currentTimeMillis();
      for (int i = 0; i < array.length - 1; i++) {
         for (int j = 0; j < array.length - i - 1; j++) {
            if (array[j] > array[j + 1]) {
               array[j] = array[j] ^ array[j + 1];
               array[j + 1] = array[j] ^ array[j + 1];
               array[j] = array[j] ^ array[j + 1];
            }
         }
      }
      System.out.println("Sort time : " + (System.currentTimeMillis() - startTime));
      return array;
   }

   private static void initArr(int size){
      arr = new int[size];
      for (int i = 0; i < size; i++) {
         arr[i] = randomNumber();
      }
   }

   private static int randomNumber(){
      return new Random().nextInt(1000);
   }

}
