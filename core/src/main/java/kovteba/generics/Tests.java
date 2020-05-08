package kovteba.generics;

import java.util.ArrayList;
import java.util.List;

public class Tests {

   public static void main(String[] args) {
      Test3.test3();
   }

   class Test1 {
      public void test1() {
         List<Integer> ints = new ArrayList<Integer>();
         ints.add(1);
         ints.add(2);
         List<? extends Number> nums = ints;
//         nums.add(3.14); // compile-time error
      }
   }

   class Test2<T> {
      public <T> T test2(List<? super T> list) {
//         return list.get(0); // compile-time error
         return null;
      }
   }

   static class Test3 {
      public static void test3(){
         ArrayList<String> strings = new ArrayList<>();
         ArrayList arrayList = new ArrayList();
         arrayList = strings; // Ok
         strings = arrayList; // Unchecked assignment
         arrayList.add(1); //unchecked call
      }
   }

   static class Test4 {
   }


}
