package kovteba.findmissingnumber;

import kovteba.bubblesort.BubbleSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindMissingNumberInArray {

   public static void main(String[] args) {
      System.out.println(missingNumber(new int[]{1, 2, 3, 4, 6}));
      System.out.println(missingNumber(new int[]{16, 3, 4, 6, 7, 9, 8, 10}));
      System.out.println(missingNumber(new int[]{1, 2, 3, 4, 6, 9, 8}));
      System.out.println(missingNumber(new int[]{1, 2, 3, 4, 9, 8}));
   }

   private static List<Integer> missingNumber(int[] arrIn){
      List<Integer> origin = new ArrayList<>();
      for (int i : arrIn)
         origin.add(i);
      Collections.sort(origin);
      List<Integer> result = new ArrayList<>();
      for (int i = origin.get(0); i <= origin.get(origin.size() - 1); i++)
         result.add(i);
      result.removeAll(origin);
      return result;
   }

}
