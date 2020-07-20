package kovteba.insertsort;

import java.sql.Time;
import java.util.Arrays;
import java.util.Random;

public class InsertSort {

   private static int[] arr;

   public static void main(String[] args) {
      initArr(100);
      System.out.println(Arrays.toString(sort(arr)));
   }

   public static int[] sort(int[] array) {
      long startTime = System.currentTimeMillis();
      for (int k = 1; k < array.length; k++) {
         int newElement = array[k];
         int index = k - 1;
         while (index >= 0 && array[index] > newElement) {
            int x = index + 1;
            array[x] = array[index];
            index--;
         }
         array[index + 1] = newElement;
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
