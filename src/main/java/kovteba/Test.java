package kovteba;


import java.util.*;
import java.util.concurrent.*;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.enumerate;

public class Test {
   public static void main(String[] args) throws Exception {

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


//      String s1 = new String("ABC").intern();
//      String s2 = new String("ABC");
//      System.out.println(s1 == s2); //false
//      String s3 = "ABC";
//      String s4 = "ABC";
//      System.out.println(s1 == s2); //false
//      System.out.println(s3 == s4); //true. Т.к. один набор литералов будет указывать на одну область памяти
//      System.out.println(s1 == s4); //false. Т.к. один набор литералов будет указывать на одну область памяти
//      System.out.println(s1.equals(s2));//true


      List<String> linkedList = new LinkedList<>();
      Map<String, String> stringStringMap = new HashMap<>();
      stringStringMap.put(null, null);
      stringStringMap.put(null, null);

      Map<byte[], String> stringStringMap1 = new HashMap<>();
      byte[] bytes = new byte[10];
      bytes[0] = 0;
      bytes[1] = 1;
      bytes[2] = 2;
      bytes[3] = 3;
      stringStringMap1.put(bytes, "sdf");
   }
}




class CallableClass implements Runnable{

   public void interup(){
      currentThread().interrupt();
   }

   @Override
   public void run() {
      System.out.println(currentThread().isInterrupted());
      while (!currentThread().isInterrupted()){
         System.out.println(currentThread().getName());
      }
   }
}

class MyRunnable implements Runnable {
   @Override
   public void run() {
      long millisOut = System.currentTimeMillis() + 1000;

      while (!Thread.interrupted()){
         System.out.println(currentThread().getName() + " MyThread");
         try {
            Thread.sleep(300);
         } catch (InterruptedException e) {
            System.out.println(currentThread().getName() + " is interrupted");
            break;
         }
         if (millisOut <= System.currentTimeMillis()){
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
   default void print(){
      new INF(){
         void show(){

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

   class MyNonStaticInnerClass{
      public /*static*/ void method(){
         System.out.println("non static");
      }
   }
   static class MyStaticInnerClass{
      public static void method(){
         System.out.println("static");
      }
   }
}
class App{
   public static void main(String[] args) {
      MyClass myClass = new MyClass();
      MyClass.MyNonStaticInnerClass myNonStaticInnerClass = myClass.new MyNonStaticInnerClass();

      MyClass.MyNonStaticInnerClass myInnerClass = new MyClass().new MyNonStaticInnerClass();

      MyClass.MyStaticInnerClass myStaticInnerClass = new MyClass.MyStaticInnerClass();
   }
}




class InnerClass {
   public static void main(String[] args) {
      new INF(){
         void print(){
            System.out.println("ASFD");
         }
      }.print();

      new INF(){
         int i = 10;
         void print(int i1, int i2){
            System.out.println(i1 * i2);
         }
      }.print(12, 13);
   }
}

abstract class INF{
}




interface Test34{
   void testMethod(String i);
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




















