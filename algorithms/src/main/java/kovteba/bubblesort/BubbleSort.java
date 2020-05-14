package kovteba.bubblesort;

import java.util.Arrays;

public class BubbleSort {
   public static void main(String[] args) {
      int[] arr = new int[]{2, 5, 5, 1, 10, 14, 3, 9, 12, 99 };
      int[] newArr = sort(arr);
      for (int i = 0; i < newArr.length; i++) {
         System.out.println(newArr[i]);
      }
   }

   public static int[] sort(int[] array) {
      for (int i = 0; i < array.length - 1; i++) {
         for (int j = 0; j < array.length - i - 1; j++) {
            System.out.println(">> " + Arrays.toString(array));
            if (array[j] < array[j + 1]) {
               array[j] = array[j] ^ array[j + 1];
               array[j + 1] = array[j] ^ array[j + 1];
               array[j] = array[j] ^ array[j + 1];
            }
         }
      }
      return array;
   }


}
