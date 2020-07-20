package kovteba.palindrome;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {

   public static void main(String[] args) {

      List<Integer> integerList = new ArrayList<>();
      integerList.add(121);
      integerList.add(122);
      integerList.add(12321);
      integerList.add(-121);
      integerList.add(+121);
      integerList.add(111121111);

      for (Integer integer : integerList){
         System.out.println("palindrome without string : " + integer + " --> " + palindrome(integer));
         System.out.println("palindrome with string : " + integer + " --> " + isPalindrome(integer));
         System.out.println();
      }
   }

   private static boolean isPalindrome(int inputNumber) {
      if (countNumber(inputNumber)) {
         return String.valueOf(inputNumber)
             .equals(String.valueOf(new StringBuffer(String.valueOf(inputNumber)).reverse()));
      } else {
         return false;
      }
   }

   private static boolean countNumber(int inputNumber) {
      int count = 0;
      while (inputNumber != 0) {
         count++;
         inputNumber /= 10;
      }
      return count % 2 != 0;
   }

   private static boolean palindrome(int inputNumber){
      if (countNumber(inputNumber)) {
         int origin = inputNumber;
         int reverse = 0;
         while (inputNumber > 0){
            reverse = (reverse * 10) + (inputNumber % 10);
            inputNumber /= 10;
         }
         return reverse == origin;
      } else {
         return false;
      }
   }

}