package kovteba.duplicatesnumber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicatesNumber {

   public static void main(String[] args) {
      int[][] test = new int[][]{
          {1, 1, 2, 2, 3, 4, 5},
          {1, 1, 1, 1, 1, 1, 1},
          {1, 2, 3, 4, 5, 6, 7},
          {1, 2, 1, 1, 1, 1, 1}
      };
      System.out.println(findDuplicates(test));
   }

   private static List<Integer> findDuplicates(int[][] arrIn){
      Map<Integer, Integer> res = new HashMap<>();
      for (int i = 0; i < arrIn.length; i++) {
         for (int j = 0; j < arrIn[i].length; j++) {
            if (res.get(arrIn[i][j]) == null){
               res.put(arrIn[i][j], 1);
            } else {
               Integer count = res.get(arrIn[i][j]);
               res.put(arrIn[i][j], ++count);
            }
         }
      }
      return res.entrySet().stream().filter(e -> e.getValue() > 1)
          .map(Map.Entry::getKey).collect(Collectors.toList());
   }

}
