public class Test {


   public static void main(String[] args) {
//      fizzBuzzPrint(3);

      String s1 = "H";
      String s2 = s1;
      System.out.println(s1 == s2);
      s2 += "1";
      System.out.println(s1 == s2);


      System.out.println("-------------");

      Integer i = 1;
      Integer j = i;
      System.out.println(i == j);
      j++;
      System.out.println(i == j);
   }


   static void fizzBuzzPrint(int i){
      if (i % 3 == 0 & i % 5 == 0){
         System.out.println("Fizz");
      } else if(i % 5 == 0) {
         System.out.println("Buzz");
      } else if (i % 3 == 0){
         System.out.println("FizzBuzz");
      } else {
         System.out.println(i);
      }

      // enter your code
   }
}
