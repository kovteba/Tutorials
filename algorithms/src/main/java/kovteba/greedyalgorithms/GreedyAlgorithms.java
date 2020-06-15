package kovteba.greedyalgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GreedyAlgorithms {
   public static void main(String[] args) {
      int[] arr = new int[]{1, 3, 7, 1, 8, 9};
      System.out.println(biggestNumberFromDigits(arr));

   }

   private static int biggestNumberFromDigits(int[] arr) {
      List<Integer> intList = new ArrayList<>();
      for (Integer i : arr)
         intList.add(i);
      Collections.sort(intList);
      String s = intList.stream().map(String::valueOf).collect(Collectors.joining());
      StringBuilder builder = new StringBuilder(s);
      return Integer.parseInt(builder.reverse().toString());

   }



}
