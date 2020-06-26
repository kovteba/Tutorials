package kovteba.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.UnaryOperator;

/**
 * @author Kovteba
 */
public class Tests {

   public static void main(String[] args) {

//        Test20 test20 = new Test20();
//        test20.test();

      Test23 test23 = new Test23();

   }
}

// Generics
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

class Test3 {
   //    @SuppressWarnings("unchecked")
   public static void test3() {
      ArrayList<String> strings = new ArrayList<>();
      ArrayList arrayList = new ArrayList();
      arrayList = strings; // Ok
      strings = arrayList; // Unchecked assignment
      arrayList.add(1); //unchecked call
   }
}

// all
class Test20 {
   class MedicalStaff {
   }

   class Doctor extends MedicalStaff {
   }

   class Nurse extends MedicalStaff {
   }

   class HeadDoctor extends Doctor {
   }

   public void test() {
      MedicalStaff medic = new HeadDoctor();
      if (medic instanceof Nurse) {
         System.out.println("Nurse");
      } else if (medic instanceof Doctor) {
         System.out.println("Doctor");
      } else if (medic instanceof HeadDoctor) {
         System.out.println("HeadDoctor");
      }
   }
}

class Test22 {
   public static int method() {
      final int loc;
//        System.out.println(loc);//1 <----- error compile
      loc = 4;//2
      return loc + 1;//3
   }

   public static void main(String[] args) {
      method();
      method();
      method();
      System.out.println(method());
   }
}

class Test23 {
   public Test23() {
      System.out.println("Empty");
   }

   public Test23(int i) {
      this(i, i);
      System.out.println("One");
   }

   public Test23(int i, int j) {
      this();
      System.out.println("Two");
   }

   public static void main(String[] args) {

      Test23 q = new Test23(2, 3); //1
   }
}

class Test24 {
   public void meth(Number obj) {
      System.out.print("1");
   }

   public void meth(Character obj) {
      System.out.print("2");
   }

   private static void meth(Integer obj) {
      System.out.print("3");
   }

   public void meth(int i) {
      System.out.print("4");
   }

   public void meth(double d) {
      System.out.print("5");
   }

   public static void main(String[] args) {
      Test24 q = new Test24();
      Number n = 67;
      Integer i = 78;
      q.meth(n);
      q.meth(i);
   }
}

class Test26 {
   enum Numbers {ONE, TWO, THREE, FOUR, FIVE}

   ;

   public static void main(String[] args) {
      Numbers n1 = Numbers.ONE;
      Numbers n2 = Numbers.ONE;//1
      if (n1 == n2) {
         System.out.print("true");
      } else {
         System.out.print("false");
      }
      System.out.println(Numbers.FIVE.ordinal());//2
   }
}

class Test28 {
   public class Quest43 {
      private final void method() {
      } //1
   }

   class Quest431 extends Quest43 {
      public void method() {
      } //2
   }
}

class Test29 {
   public class Quest51 {
      public String toString() {
         return getClass().getSimpleName();
      }

      class Quest52 extends Quest51 {
      }

      class Quest53 extends Quest52 {
      }

      public void main(String[] args) {
         Quest53 q = new Quest53();
         System.out.println(q.toString());
      }
   }
}

class Test30 {
   class Item {
      public int item;

      Item(int item) {
         this.item = item;
      }
   }

   public class Quest61 {
      public void main(String[] args) {
         Item ar1[] = {new Item(1), new Item(2), new Item(3)};
         Item ar2[] = ar1.clone();
         ar2[0].item = 4;
         System.out.println(ar1[0].item + " " + ar1[1].item + " " + ar1[2].item);
      }
   }
}

class Test31 {
   public class Quest1 {
      public /*static*/ void main(String[] args) {
         for (Numbers num : Numbers.values()) {
            System.out.print(num.getNumber());
         }
      }
   }

   enum Numbers {
      ONE(1), TWO(2) {
         public int getNumber() {
            return x + x;
         }
      }, THREE(3) {
         public int getNumber() {
            return x + x + x;
         }
      }, FOUR(4), FIVE(5);
      int x;

      Numbers(int x) {

         this.x = x;
      }

      public int getNumber() {

         return x;
      }
   }
}

class Test32 {
   public class Quest3 {
      public /*static*/ void main(String[] args) {
         Outer obj = new Outer().new Inner1();

         obj.print();
      }
   }

   class Outer {
      public void print() {
      }

      class Inner1 /*-----------*/ extends Outer { //<------
         public void print() {

            System.out.println("In inner.");

         }
      }
   }
}

class Test35 {
   interface Quest10 {
      Number returner();
   }

   abstract class Quest100 {
      public abstract Integer returner();
   }

   public class Quest1 extends Quest100 implements Quest10 {//line 1

      @Override //line 2
      public Integer returner() {// line 3
         return new Integer(6);
      }

      public void main(String[] args) {
         Quest1 quest = new Quest1();
         Quest10 quest10 = quest;
         Quest100 quest100 = quest;
         System.out.println(quest10.returner() + "" + quest100.returner());
      }
   }
}

class Test41 {
   public static void main(String[] args) {
      try {
         FileReader fr1 = new FileReader("kovteba/tests/test1.txt");
         try {
            FileReader fr2 = new FileReader("test2.txt");
         } catch (IOException e) {

            System.out.print("test2");
         }
         System.out.print("+");
      } catch (FileNotFoundException e) {
         System.out.print("test1");
      }
      System.out.print("+");
   }
}

class Test42 {
   private int qQ;

   public Test42(int q) {
      qQ = 12 / q;//1
   }

   public int getQQ() {
      return qQ;//2
   }

   public static void main(String[] args) {
      Test42 test42 = null;
      try {
         test42 = new Test42(0);//3
      } catch (Exception e) {//4
      }
      System.out.println(test42.getQQ());//5
   }
}


class Test43 {
   public static void main(String[] args) {


   }
}

class Test44 {
   public static void main(String[] args) {
      Set<String> setList = new HashSet<>();
      setList.add("1");
      setList.add("2");
      setList.add("3");

      String s = "1";

      System.out.println(setList.stream().filter(e -> e.equals(s)).findFirst().get());

      System.out.println();
      List<String> list = new LinkedList<>();
   }
}

class Test46 {
   public static void main(String[] args) {
      int[] arr = new int[10];
      System.out.println(args[0]);
   }
}

class Test47 {
   public static void main(String[] args) {
      int i1, i2, i3 = 10;
//        System.out.println(i1 + " " + i2 + " " + i3);
   }
}

class Test48 {
   public static void main(String[] args) {
      int value = 10;
      for (int i = 0; i < 4; i++) {
         value *= 10;
         System.out.println(value);
      }
   }
}

class Test49 {
   public static void main(String[] args) {
      String s1 = new String("Test");
      String s2 = new String("Test");
      if (s1 == s2) {
         System.out.println("same");
      }
      if (s1.equals(s2)) {
         System.out.println("Equals");
      }
   }
}

class Test50 {
   public static void main(String[] args) {
      int i = 1;
//        i += new Date();
      System.out.println(i);
   }
}

class Test51 {
   public static void main(String[] args) {
      System.out.println(Integer.compare(1, -2));
   }
}

class Test52 {
   public static void main(String[] args) {
      System.out.println(get());
   }

   public static boolean get() {
      return true || get();
   }
}

class Test53 {
   public static void main(String[] args) {
      Stack stack = new Stack();
      stack.push("abc");
      stack.push("abcd");
      System.out.println(stack.pop());
      System.out.println(stack.size());
   }
}

class Test54 {
   public static void main(String[] args) {
//        int ch = System.in.read();
//        System.out.println(ch);
   }
}

class Test55 {
   public static void main(String[] args) {
      String text = "ABC";
      String subString = "";
      System.out.println(text.contains(subString));
   }
}

class Test56 {
   public static void main(String[] args) {
      int i = 1;
      {
         i = 2;
      }
      System.out.println(i);
   }
}

class Test57 {
   private Object data;

   public Test57(Object data) {
      this.data = data;
   }

   public List<String> stringList() {
      return Arrays.asList(String.valueOf(data));
   }

   public static void main(String[] args) {
      Test57 test57 = new Test57("1");
      for (String s : test57.stringList()) {
         System.out.println(s);
      }
   }
}

class Test58 {
   public static void main(String[] args) {
      Map<String, String> map = new HashMap<>();
      map.put("1", null);
      System.out.println(map.get("1"));
   }
}

class Test59 {
   Example example;
}

class Example {
}

class Test60 {
   public static void main(String[] args) {

   }

   //    public static <T extends Date> void run(List<T> items){
//        System.out.println("String");
//    }
   public static <T extends Object> void run(List<T> items) {
      System.out.println("Object");
   }
}

class Test61 {
   public static void main(String[] args) {
      System.out.println(invert(true));
      System.out.println(invert(false));
   }

   public static boolean invert(boolean flag) {
      return flag ? false : true;
   }
}

class Test63 {
   public static void main(String[] args) {
      Integer i1 = 127;
      Integer i2 = 127;
      System.out.println(i1 == i2);
      i1 -= 1;
      System.out.println(i1 + " " + i2);
      Integer i3 = 128;
      Integer i4 = 128;
      System.out.println(i3 == i4);
   }
}

class Test64 {
   static int a = 1111;

   public static void main(String[] args) {
      System.out.println(a--);
      System.out.println(--a);
      System.out.println(a-- - --a);
      System.out.println(a++ + ++a);
   }
}

class Test65 {
   public static void main(String[] args) {
      String s1 = "H";
      String s2 = s1;
      System.out.println(s1 == s2);
      s2 += "1";
      System.out.println(s1 == s2);
   }
}

class Test66 {
   public static void main(String[] args) {
      Integer i = 1;
      Integer j = i;
      System.out.println(i == j);
      j++;
      System.out.println(i == j);
   }
}

class Test67 {
   public static void main(String[] args) {
      System.out.println(Integer.valueOf(42) == Integer.valueOf(42));//true
      System.out.println(Integer.valueOf(42).equals(42L));//false
      System.out.println(new Integer(42) == new Integer(42));//false
      System.out.println(Long.valueOf(42L).equals(42));//false
      System.out.println(Long.valueOf(42L).equals(new Long(42L)));//true
      System.out.println(Integer.valueOf(42).equals(42L));//false
      System.out.println(Integer.valueOf(42) == Integer.valueOf(42));//true
      System.out.println(Long.valueOf(42L).equals(new Long(42L)));//true
      System.out.println(new Integer(42) == new Integer(42));//false
      System.out.println(Long.valueOf(42L).equals(42L));//true
   }
}

class Test68 {
   private static int value = 2;

   static {
      value = 1;
   }

   public static void main(String[] args) {
      System.out.println(value);
   }
}

class Test69 {
   public static void main(String[] args) {
      byte a = 1;
      byte b = ++a;
//      byte c = -a;    // 3
      System.out.print(a);
      System.out.print(b);
//      System.out.print(c);
   }
}

class Test70 {
   public static void main(String[] args) {
      Map map = new TestMap();
      for (Object o : map.keySet()) {
         System.out.println(o);
      }
   }

   static class TestMap extends HashMap {
      @Override
      public Set keySet() {
         return null;
      }
   }
}

class Test71 implements A, B {
   public static void main(String[] args) {
//      System.out.println(Test71.text);
   }
}

interface A {
   String text = "a";
}

interface B {
   String text = "b";
}

class Test72 {
   class A {
      String str = "ab";

      A() {
         printLength();
      }

      void printLength() {
         System.out.println(str.length());
      }
   }

   class B extends A {
      String str = "abc";

      void printLength() {
         System.out.println(str.length());
      }
   }

   public static void main(String[] args) {
      new Test72().new B();
   }
}

class Test73 {
   public static void main(String[] args) {
      if (System.out.printf("Hello world") == null) {
      }
   }
}

class Test74 {
   public static void main(String[] args) {
      int i = 5;
      i = i++;
      System.out.println(i);

      i = 5;
      i = i++ + i++;// 5 + 6
      System.out.println(i);

      i = 5;
      i = i++ + ++i;//5 + 7
      System.out.println(i);

      i = 5;
      i = ++i + ++i;// 6 + 7
      System.out.println(i);
   }
}

class Test76 {
   public static class B {

      B b = new B();

      public int show() {
         return (true ? null : 0);
      }
   }

   public static void main(String[] args) {

      B b = new B();
      b.show();
   }
}

class Test77 {
   public static void show() {
      System.out.println("Static method called");
   }

   public static void main(String[] args) {
      Test77 obj = null;
      obj.show();
   }
}

class Test78 {
   static int a = 1111;

   static {
      a = a-- - --a;
   }

   {
      a = a++ + ++a;
   }

   public static void main(String[] args) {
      System.out.println(a);
   }
}

class Test79 {
   int GetValue() {
      return (true ? null : 0);
   }

   public static void main(String[] args) {
      Test79 obj = new Test79();
      obj.GetValue();
   }
}

class Test80A {
   void method(int i) {
   }
}

class Test80B extends Test80A {
   //   @Override
   void method(Integer i) {

   }
}

class Test81 {
   public static void main(String[] args) {
      Integer i = new Integer(null);
//      String s = new String(null);
   }
}

class Test82 {
   static int method1(int i) {
      return method2(i *= 11);
   }

   static int method2(int i) {
      return method3(i /= 11);
   }

   static int method3(int i) {
      return method4(i -= 11);
   }

   static int method4(int i) {
      return i += 11;
   }

   public static void main(String[] args) {
      System.out.println(method1(11));
   }
}

class Test83 {
   public static void main(String[] args) {
//      System.out.println(null);
   }
}

class Test85 {
   public static void main(String[] args) {
      List<String> list = Arrays.asList("Hello", "World");
      List<Integer> data = new ArrayList(list);
//      for (Integer i : data){ // <--  ClassCastException
//         System.out.println(i);
//      }
   }
}

class Test86 {
   public static void main(String[] args) {
      String s = null;
      System.out.println(s.length());// NullPointerException
   }
}

class Test87 {
   public static void main(String[] args) {
      String s = null;
      System.out.println(System.identityHashCode(s));
   }
}

class Test88 {
   public static void main(String[] args) {
//      System.out.println(get()++);
   }

   static int get() {
      return 1;
   }
}

class Test89 {
   //   abstract class ClassName1 {abstract  void woof(){}}  // false
   abstract class ClassName2 {
      abstract void woof();
   }  // true

   //   class ClassName3{abstract void woof();}  // false
   abstract class ClassName4 {
      void woof() {
      }
   }  // true
//   abstract class ClassName5 { void woof();}  // false
}

class Test90 {
   class A {
      protected int i;

      public A(int i) {
         this.i = i;
      }
   }

   class B extends A {
      public B(int i) {
         super(i);
      }
   }
}

class Test91 {
   //   1
   class A {
      public A() {
      }

      public A(int k) {
      }
   }

//   2
//   class A {}

//   3
//   class A {
//      public A (int k){}
//      public A (int k, int m){}
//   }

//   4
//   class A {
//      public A (int k){}
//   }

   class B extends A {
      public B(int p1, int p2) {
         System.out.println(p1);
         System.out.println(p2);
      }

      public B(int m) {
         super(m);
      }
   }
}

class Test92 {
   static class Super {
      public int getNumber(int a) {
         return a;
      }
   }

   static class TestClass extends Super {
      public int getNumber(int a, int b) {
         return 5;
      }
   }

   public static void main(String[] args) {
      System.out.println(new TestClass().getNumber(4));
   }
}

class Test93 {
   static class Super {
      public short getNumber() {
         return 100;
      }
   }

   static class TestClass extends Super {
      public /*byte*/ short getNumber() {
         return 5;
      }
   }

   public static void main(String[] args) {
      Super s = new TestClass();
      System.out.println(s.getNumber());
   }
}

class Test94 {
   class Class1 {
   }

   class Class2 extends Class1 {
   }

   abstract class Class3 {
      abstract Class1 method();
   }

   class Class4 extends Class3 {
      Class1 method() {
         return new Class2();
      }
   }
   /*
   1. Class2 method(){return null;}
   2. Class3 method(){return null;}
   3. Class1 method(){return null;}
   4. Class3 method(){return new Class2();}
   5. Class1 method(){return new Class2();}
   6. Class2 method(){return new Class1();}
   7. Class3 method(int t){return null;}
   7. Class2 method(){return new Class2();}
   */
}

class Test95 {
   static class A {
      class B {
      }
   }

   public static void main(String[] args) {
      //Create class B
   }
   /*
   1.A.B b = new A().B();
   2.A a = new A();
     A.B b = a.new B();
   3.B b = new A().new B();
   4.B b = new B();
   5.A.B b = new A().new B();
   */
}

class Test96 {
   public static void main(String[] args) {
      int mult = 10;
      class Local {
         int multiplay(int n) {
            return n/* * mult*/;
         }
      }
      mult = 5;
      int res = new Local().multiplay(mult);
      System.out.println(res);
   }
}

abstract class Test97 {
   protected String s;
   public Test97(String s) {
      this.s = s;
   }
   abstract void print();
   public static void main(String[] args) {
      Test97 test97 = new Test97("Error") {
         String s = "1";
         @Override
         void print() {
            System.out.println(s);
         }
      };
      test97.print();
   }
}

enum Directions {
   NORTH(1), SOUTH(2), WEST(3), EAST(4);
   public int value;
   Directions(int number) {
      this.value = number;
   }
}
class Test98 {
   public static void main(String[] args) {
      for (Directions directions : Directions.values())
         System.out.print(directions.value + " ");

   }
}

class Test99 {
   static void method(StringBuilder builder){
      System.out.println(builder);
      builder.append("2");
      System.out.println(builder);
   }
   public static void main(String[] args) {
      StringBuilder stringBuilder = new StringBuilder("1");
      method(stringBuilder);
      System.out.print(stringBuilder);
   }
}

class Test100 {
   public static void main(String[] args) {
      char[] arr1 = new char[]{'J', 'a', 'v', 'a'};
      String str1 = new String(arr1).intern();
      String str2 = new String("Java").intern();
      System.out.println(str1 == str2);
   }
}

class Test101 {
   public static void main(String[] args) {
      StringBuilder builder = new StringBuilder("Java");
      String str = new String("Java");
//      System.out.println(str == builder);

   }
}

class Test102 {
   public static void main(String[] args) {
      StringBuilder builder = new StringBuilder("Java");
      String str1 = builder.toString();
      String str2 = builder.toString();
      String str3 = builder.substring(0);
      String str4 = builder.toString().intern();
      String str5 = builder.substring(0).intern();
      System.out.println(str1);
      System.out.println(str2);
      System.out.println(str1 == str2);
      System.out.println(str1 == str3);
      System.out.println(str2 == str3);
      System.out.println(str4 == str5);
   }
}

class Test103 {
   public static void main(String[] args) {
      StringBuilder builder = new StringBuilder("Java");
      builder.setLength(3);
      System.out.println(builder);
   }
}

class Test104 {
   public static void main(String[] args) {
      String s = "Java";
      s.substring(1);
      System.out.println(s);
   }
}

class Test105 {
   public static void main(String[] args) {
      StringBuilder stringBuilder = new StringBuilder("Java");
      StringBuffer stringBuffer = new StringBuffer("Java");
      System.out.println(stringBuilder.hashCode() == stringBuffer.hashCode());
      System.out.println(stringBuilder.capacity() == stringBuffer.capacity());
   }
}

class Test106 {
   static void method(String s){
      System.out.println("String " + s);
   }
   static void method(StringBuilder builder){
      System.out.println("StringBuilder " + builder);
   }
   public static void main(String[] args) {
      method("Java");
//      method(null);
//      method(new Object());
   }
}

class Test107 {
   public static void main(String[] args) {
      StringBuilder builder = new StringBuilder("Java");
      builder.insert(5, "-8");
      System.out.println(builder);
   }
}

class Test108 {
   public static void main(String[] args) {
      String srt = "Java";
      srt = srt.replaceAll("a", "-");
      System.out.println(srt);
   }
}

class Test109 {
   public static void main(String[] args) {
      String str1 = "Hello";
      String str2 = "World";
      UnaryOperator<String> operator = s -> s.concat(str1);
      System.out.println(operator.apply(str2));
   }
}

class Test110 {
   public void m() throws Exception{
      throw new Exception();
   }
}
class Test110t extends Test110{
   public void m(){}
   public static void main(String[] args) {
      Test110 x = new Test110t();
//      x.m();
   }
}

class Test111 {
   public static void method() throws Exception{
      throw new Error("Error");
   }
   public static void main(String[] args) {
      try{
         method();
      } catch (Exception e){
         System.out.println("Exception");
      }
   }
}

class Test112 {
   static class A {
      protected void m() throws Exception{}
   }
   static class B extends A {
      public void m(){}
   }
   public static void main(String[] args) {
      A a = new B();
//      a.m();
   }
}

class Test113 {
   static class A {
      protected void m() throws Exception{}
   }
   static class B extends A {
      public void m(){}
   }
   public static void main(String[] args) {
      A a = new B();
      ((B)a).m();
   }
}

class Test114 {
   void m1() throws Exception {
      throw new Exception();
   }
   void m2() throws RuntimeException {
      throw new NullPointerException();
   }
   public static void main(String[] args) {
      Test114 test114 = new Test114();
      try {
         test114.m1();
      } catch (Exception e){
//         test114.m1();
      } finally {
         test114.m2();
      }
   }
}

class Test115 {
   public Test115() {
      try {
         throw new MyException();
      } catch (Exception e){

      }
   }
   class MyException extends Exception {
      private static final long serialUID = 1L;
   }
   public static void main(String[] args) {
      Test115 test115 = new Test115();
      Test115 test1151 = test115;
   }
}


class Test116 {
   static class FirstEx extends Exception {
      private static final long serialID = 1L;
   }
   static class SecondEx extends Exception {
      private static final long serialID = 1L;
   }
   static void m1() throws FirstEx { throw new FirstEx(); }
   static void m2() throws SecondEx { throw new SecondEx(); }
   public static void main(String[] args) {
//      try {
//         m1();
//      } finally {
//         m2();
//      }
   }
}

class Base {
   public void m() throws Exception {
      System.out.println("Test1");
   }
}
class Test117 extends Base{
   public void m() {
      System.out.println("Test2");
   }
   public static void main(String[] args) {
      Base base = new Test117();
//      base.m();
   }
}


class Test118 {
   public static void main(String[] args) {
      Thread t = new MyThread();
      for (int i = 1; i <= 5; i++) {
         System.out.println(i + " ");
         try {
            t.sleep(1000);
         } catch (InterruptedException e){
            System.out.println("inter");
         }
      }
   }

   static class MyThread extends Thread{
      @Override
      public void run() {
         for (int i = 1; i <= 5; i++) {
            System.out.println(i + " ");
            try {
               Thread.sleep(1000);
            } catch (InterruptedException e){
               System.out.println("inter");
            }
         }
      }
   }
}

class Test119 {
   public static void main(String args[]) {
      System.out.println(TestClass.v);
      new TestClass().a();
      System.out.println(TestClass.v);
   }
}
class TestClass {

   public static String v = "Initial val";
   {
      System.out.println("!!! Non-static initializer");
      v = "Val from non-static";
   }
   static {
      System.out.println("!!! Static initializer");
      v = "Some val";
   }
   public void a() {
      System.out.println("!!! a() called");
   }
}


class Test120 {
   public static void main(String[] args) {
      int n = 33;
      System.out.println(n % 2);
      int s = 0;
      while (n > 0){
         s += n % 2;
         System.out.println("s: " + s);
         n /= 2;
         System.out.println("n: " + n);
      }
      System.out.println(s);
   }
}

//class Test111 {
//   public static void main(String[] args) {
//
//   }
//}

//class Test111 {
//   public static void main(String[] args) {
//
//   }
//}

//class Test111 {
//   public static void main(String[] args) {
//
//   }
//}










