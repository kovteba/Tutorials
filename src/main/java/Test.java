public class Test {


   public static void main(String[] args) {
//      fizzBuzzPrint(3);

//      String s1 = "H";
//      String s2 = s1;
//      System.out.println(s1 == s2);
//      s2 += "1";
//      System.out.println(s1 == s2);
//
//
//      System.out.println("-------------");
//
//      Integer i = 1;
//      Integer j = i;
//      System.out.println(i == j);
//      j++;
//      System.out.println(i == j);


      System.out.println(Integer.valueOf(42) == Integer.valueOf(42));//true
      System.out.println(Integer.valueOf(42).equals(42L));//false
      System.out.println(new Integer(42) == new Integer(42));//false
      System.out.println(Long.valueOf(42L).equals(42));//false
      System.out.println(Long.valueOf(42L).equals(new Long(42L)));//true



//      System.out.println(Integer.valueOf(42).equals(42L));
//      System.out.println(Integer.valueOf(42) == Integer.valueOf(42));
//      System.out.println(Long.valueOf(42L).equals(new Long(42L)));
//      System.out.println(new Integer(42) == new Integer(42));
//      System.out.println(Long.valueOf(42L).equals(42L));


//      int[] arr = new int[]{2, 1, 4, 1, 2, 3, 4, 5, 6, 7, 3, 1};
//      int[] arr1 = new int[]{2, 1, 4, 1, 2, 3, 4, 5, 6, 7, 7, 7, 7, 7, 7, 3, 1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
//      calculateAndPrint(arr1);
   }

   static int reverse(int n) {


      return Integer.parseInt(String.valueOf(new StringBuffer(String.valueOf(n)).reverse()));
   }

   final static void fizzBuzzPrint(int i) {
      if (i % 3 == 0 & i % 5 == 0) {
         System.out.println("Fizz");
      } else if (i % 5 == 0) {
         System.out.println("Buzz");
      } else if (i % 3 == 0) {
         System.out.println("FizzBuzz");
      } else {
         System.out.println(i);
      }

      // enter your code
   }

   static void calculateAndPrint(int[] array) {
      int count = 1;
      int index = 0;
      int[] max = new int[array.length];
      for (int i = 0; i < array.length - 1; i++) {
         if (array[i] <= array[i + 1]) {
            count++;
         } else {
            max[index] = count;
            index++;
            count = 1;
         }
      }
      max[++index] = count;
      int maxRes = 0;
      for (int i = 0; i < max.length; i++) {
         if (max[i] > maxRes) maxRes = max[i];
      }
      System.out.println(maxRes);
   }

   Node merge(Node head1, Node head2) {
      Node newHead = null;
      if (head1.getData() < head2.getData()) {
         newHead = head1;
      } else {
         newHead = head2;
      }


      return newHead;
   }

   class Node{
      private int data;
      private Node next;

      public int getData() {
         return data;
      }

      public void setData(int data) {
         this.data = data;
      }

      public Node getNext() {
         return next;
      }

      public void setNext(Node next) {
         this.next = next;
      }

   }

   void checkFruitCount(Object[] objects) {
      int countBanana = 0, countApple = 0;
      for (int i = 0; i < objects.length; i++) {
         if (objects[i] instanceof Apple) {
            countApple++;
         }
         if (objects[i] instanceof Banana){
            countBanana++;
         }
      }
      System.out.println("banan=" + countBanana + ", apple=" + countApple);
   }

   interface Apple {

   }

   interface Banana {

   }

}

interface MyInterface {
   void Test();

   static void Test1() {

   }
}
