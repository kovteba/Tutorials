package kovteba;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
   public static void main(String[] args) {

//      List<Integer> ints = Arrays.asList(1, 2, 3);
////      List<Number> nums = ints; // compile-time error. Проблема обнаружилась на этапе компиляции
////      nums.set(2, 3.14);
//      assert ints.toString().equals("[1, 2, 3.14]");
//
//      List<Number> nums1 = Arrays.asList(1, 1.0, 1, 25.2);
//
//
//      m1(1);
//      m2(new ArrayList<Short>());
//
//      Test1<Integer> test1 = new Test1<>();
//      test1.setT(12);
//      System.out.println(test1.getT());


//      String s3 = "ABC";
//      String s4 = "ABC";
//      String s1 = new String("ABC").intern();
//      String s2 = new String("ABC");
//      System.out.println(s1 == s2); //false
//      System.out.println(s3 == s4); //true. Т.к. один набор литералов будет указывать на одну область памяти
//      System.out.println(s1 == s4); //false. Т.к. один набор литералов будет указывать на одну область памяти
//      System.out.println(s1.equals(s2));//true



   }

   private static <T> void m1(T t) {
      System.out.println(t);
   }

   private static <T extends Number> void m2(List<T> t) {
      System.out.println(t);
   }




}









class Test1<T extends Number> {
   private T t;

   public T getT() {
      return t;
   }

   public void setT(T t) {
      this.t = t;
   }
}


interface MyInterface {
   void Test();

   static void Test1() {
   }

   default void Test2() {
      System.out.println("!");
   }
}

class TestClass implements MyInterface {
   @Override
   public void Test() {

   }

   @Override
   public void Test2() {

   }
//   public void Test() {
//      MyInterface.super.Test2();
//   }


}

class Test100 {
   @FunctionalInterface
   interface CarFilter<T> {
      boolean test(T car);
   }

   static class Car {
      int year;

      public int getYear() {
         return year;
      }

      public void setYear(int year) {
         this.year = year;
      }
   }

   public static void main(String[] args) {
      CarFilter<Car> carFilter = car -> car.getYear() >= 2010;
      Car car = new Car();
      car.setYear(2009);
      System.out.println(carFilter.test(car));
   }
}



