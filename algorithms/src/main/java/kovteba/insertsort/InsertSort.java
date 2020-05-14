package kovteba.insertsort;

import java.util.Arrays;

public class InsertSort {
   public static void main(String[] args) {
      int[] arr = new int[]{2, 5, 5, 1, 10, 14, 3, 9, 12, 99 };
      int[] newArr = sort(arr);
      for (int i = 0; i < newArr.length; i++) {
         System.out.println(newArr[i]);
      }
   }

   public static int[] sort(int[] array) {
      for (int k = 1; k < array.length; k++) {
         int newElement = array[k];
         int index = k - 1;
         System.out.println("newElement : " + newElement);

         while (index >= 0 && array[index] > newElement) {
            int x = index + 1;
            System.out.println("array[index + 1] : " + array[x] + " array[index] : " + array[index]);

            array[x] = array[index];
            index--;

            System.out.println(Arrays.toString(array) + "\n");
         }
         array[index + 1] = newElement;
      }
      return array;
   }
}
