package kovteba.mergesort;

import java.util.Arrays;
import java.util.Random;

public class MergeSort {
   private static int[] arr;

   public static void main(String[] args) {
      initArr(9);
      System.out.println(Arrays.toString(arr));
      System.out.println(Arrays.toString(sort(arr)));
   }

   public static int[] sort(int[] arr) {
      if (arr.length < 2) return arr;
      int m = arr.length / 2;
      int[] arr1 = Arrays.copyOfRange(arr, 0, m);
      int[] arr2 = Arrays.copyOfRange(arr, m, arr.length);
      return merge(sort(arr1), sort(arr2));
   }

   public static int[] merge(int[] arr1, int[] arr2) {
      int n = arr1.length + arr2.length;
      int[] arr = new int[n];
      int i1 = 0;
      int i2 = 0;
      for (int i = 0; i < n; i++) {
         if (i1 == arr1.length) {
            arr[i] = arr2[i2++];
         } else if (i2 == arr2.length) {
            arr[i] = arr1[i1++];
         } else {
            if (arr1[i1] < arr2[i2]) {
               arr[i] = arr1[i1++];
            } else {
               arr[i] = arr2[i2++];
            }
         }
      }
      return arr;
   }

   private static void initArr(int size) {
      arr = new int[size];
      for (int i = 0; i < size; i++) {
         arr[i] = randomNumber();
      }
   }

   private static int randomNumber() {
      return new Random().nextInt(100);
   }
}
