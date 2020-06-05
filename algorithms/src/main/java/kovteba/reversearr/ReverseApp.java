package kovteba.reversearr;

public class ReverseApp {
   public static void main(String[] args) {
      Integer[] arr1 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
      Integer[] arr2 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9,   10,   11, 12, 13, 14, 15, 16, 17, 18, 19};
      String[] arr3 = new String[]{"a", "s", "d", "f", "g", "h"};

      print(reverse(arr1));
      print(reverse(arr2));
      print(reverse(arr3));
   }

   static <T> T[] reverse(T[] arr){
      T time;
      for (int i = 0; i < arr.length / 2; i++) {
         time = arr[i];
         arr[i] = arr[arr.length - 1 - i];
         arr[arr.length - 1 - i] = time;
      }
      return arr;
   }



   static <T> void print(T[] arr){
      for (T i : arr)
         System.out.print(i + " ");
      System.out.println("\n");
   }
}
