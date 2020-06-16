package kovteba.testspack;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.lang.Thread.currentThread;

public class Test {
   static int xStat = 2;
   int nonStat = 23;

   public static void main(String[] args) throws Exception {


      FinalClass finalClass = new FinalClass("1", "2");
      System.out.println(finalClass.toString());
      change(finalClass);
      System.out.println(finalClass.toString());

      System.out.println("---------------");

      Integer integer = 12;
      System.out.println(integer);
      change(integer);
      System.out.println(integer);

      int iInt = integer;

      List<String> list = new ArrayList<>();


      BiFunction<String, String, Integer> compareFunc
          = (firstStr, secondStr) -> Integer.compare(firstStr.length(), secondStr.length());


      list.forEach(System.out::println);


      int s = 0;

      changeT(s);

      App obj = new App();

      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

      System.out.println(Objects.hash(null));

      /////
//      Stream<Integer> stream = Stream.of(1, 2);
//      System.out.println(stream.reduce((final x, final y) -> x).get());





   }








   static void change(FinalClass finalClass) {
      finalClass.setName("!!!!!!!!!!");
   }

   static void change(Integer integer) {
      integer += 12;
   }

   static <T> void changeT(T t) {
      System.out.println(t);
   }

   static class TestStat {
      int x = 0;
      static int x1 = 0;

      void print() {
         System.out.println(x);
//         xStat;
//         new Test().nonStat;
         System.out.println(x);
         System.out.println(x1);

      }

      static void print1() {
         System.out.println("123");
      }
   }
}





class FinalClass {
   private String name;
   private String value;

   public FinalClass(String name, String value) {
      this.name = name;
      this.value = value;
      System.out.println("New ImmutableClass created! Name " + this.name + " value " + this.value);
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      FinalClass that = (FinalClass) o;
      return Objects.equals(name, that.name) &&
          Objects.equals(value, that.value);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name, value);
   }

   @Override
   public String toString() {
      return "ImmutableCat{" +
          "breed='" + name + '\'' +
          ", eyeColor='" + value + '\'' +
          '}';
   }
}


class CallableClass implements Runnable {

   public void interup() {
      currentThread().interrupt();
   }

   @Override
   public void run() {
      System.out.println(currentThread().isInterrupted());
      while (!currentThread().isInterrupted()) {
         System.out.println(currentThread().getName());
      }
   }
}

class MyRunnable implements Runnable {
   @Override
   public void run() {
      long millisOut = System.currentTimeMillis() + 1000;

      while (!Thread.interrupted()) {
         System.out.println(currentThread().getName() + " MyThread");
         try {
            Thread.sleep(300);
         } catch (InterruptedException e) {
            System.out.println(currentThread().getName() + " is interrupted");
            break;
         }
         if (millisOut <= System.currentTimeMillis()) {
            currentThread().interrupt();
         }
      }
   }
}


class Outer {
   // Анонимный класс наследуется от класса Demo
   static final Demo demo = new Demo() {
      @Override
      public void show() {
//         super.show();
         System.out.println("Метод внутреннего анонимного класса");
      }
   };

   public static void main(String[] args) {
      demo.show();
   }
}

class Demo {
   public void show() {
      System.out.println("Метод суперкласса");
   }
}


interface My {
   default void print() {
      new INF() {
         void show() {

         }
      }.show();
   }
}

class MyClass {

   private final MyNonStaticInnerClass myNonStaticInnerClass;
   private final MyStaticInnerClass myStaticInnerClass;

   public MyClass() {
      this.myNonStaticInnerClass = new MyNonStaticInnerClass();
      myStaticInnerClass = new MyStaticInnerClass();
   }

   class MyNonStaticInnerClass {
      public /*static*/ void method() {
         System.out.println("non static");
      }
   }

   static class MyStaticInnerClass {
      public static void method() {
         System.out.println("static");
      }
   }
}

interface App1 {
}

final class App implements App1 {
   public App() {
   }

   public App(String s) {
      System.out.println(s);
   }

   public void m1(int i) {

   }

   public Integer m2(Integer i) {
      return i;
   }

   public static void main(String[] args) {
      MyClass myClass = new MyClass();
      MyClass.MyNonStaticInnerClass myNonStaticInnerClass = myClass.new MyNonStaticInnerClass();

      MyClass.MyNonStaticInnerClass myInnerClass = new MyClass().new MyNonStaticInnerClass();

      MyClass.MyStaticInnerClass myStaticInnerClass = new MyClass.MyStaticInnerClass();
   }
}


class InnerClass {
   public static void main(String[] args) {
      new INF() {
         void print() {
            System.out.println("ASFD");
         }
      }.print();

      new INF() {
         int i = 10;

         void print(int i1, int i2) {
            System.out.println(i1 * i2);
         }
      }.print(12, 13);
   }
}

abstract class INF {
}


interface Test34 {
   void testMethod(String i);
}




















