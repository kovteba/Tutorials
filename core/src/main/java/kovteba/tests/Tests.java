package kovteba.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
interface A { String text = "a"; }
interface B { String text = "b"; }

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
      if(System.out.printf("Hello world") == null){}
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











