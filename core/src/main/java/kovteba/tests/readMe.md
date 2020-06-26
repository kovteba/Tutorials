# TESTS

- [Practice](#Practice)
- [Theory](#Theory)

## Practice
## Test 1
```java
class A {
    public void m1() {
        System.out.print(" A_m1");
    }
    public void m2() {
        System.out.print(" A_m2");
    }
}
class B extends A {
    public void m1() {
        System.out.print(" B_m1");
    }
    public void m2() {
        System.out.print(" B_m2");
        m1();
    }
}
class Main {
    public static void main(String[] args) {
        A a = new B();
        a.m2();
    }
}
```
__RESULT:__ B_m2 B_m1

## Test 2
```java
class A {
    public void m1() {
        System.out.print(" A_m1");
    }
    public void m2() {
        System.out.print(" A_m2");
        m1();
    }
}
class B extends A {
    public void m1() {
        System.out.print(" B_m1");
    }
}
class Main {
    public static void main(String[] args) {
        A a = new B();
        a.m2();
    }
}
```
__RESULT:__ A_m2 B_m1

## Test 3
```java
class A {
    public void m1() {
        System.out.print(" A_m1");
    }

    public void m2() {
        System.out.print(" A_m2");
    }
}

class B extends A {
    /*private*/ public void m1() { // <----
        System.out.print(" B_m1");
    }

    public void m2() {
        System.out.print(" B_m2");
        m1();
    }
}

class Main {
    public static void main(String[] args) {
        A a = new B();
        a.m2();
        a.m1();
    }
}
```

__EXPLAIN:__ попытка сужения уровня доступа

## Test 4
```java
class A {
    private void m1() {
        System.out.print(" A_m1");
    }
    public void m2() {
        System.out.print(" A_m2");
        m1();
    }
}
class B extends A {
    public void m1() {
        System.out.print(" B_m1");
    }
}
class Main {
    public static void main(String[] args) {
        A a = new B();
        a.m2();
    }
}
```
__RESULT:__ A_m2 A_m1

## Test 5
```java
class A {
    public void m1() {
        System.out.print(" A_m1");
    }
    public void m2() {
        System.out.print(" A_m2");
        m1();
    }
}
class B extends A {
    public /*static*/ void m1() { // <-- cant be static
        System.out.print(" B_m1");
    }
}
class Main {
    public static void main(String[] args) {
        A a = new B();
        a.m1();
    }
}
```
__RESULT:__ compile error

## Test 6
```java
class A {
    public static void m1() {
        System.out.print(" A_m1");
    }
    public void m2() {
        System.out.print(" A_m2");
        m1();
    }
}
class B extends A {
    public static void m1() { // <-- cant be static
        System.out.print(" B_m1");
    }
}
class Main {
    public static void main(String[] args) {
        B a = new B();
        a.m2();
        a.m1();
    }
}
```
__RESULT:__ A_m2 A_m1 B_m1

## Test 7
```java
class A {
    int i = 1;
    public void m1() {
        System.out.print(" A_m1:" + i);
    }
}
class B extends A {
    double i = 1.1;
    public void m1() { // <-- cant be static
        System.out.print(" B_m1:" + i);
    }
}
class Main {
    public static void main(String[] args) {
        A a = new B();
        a.m1();
        System.out.println(" a.i=" + a.i);
    }
}
```
__RESULT:__ B_m1:1.1 a.i=1

## Test 8
```java
class A {
}
class B extends A {
}
class C extends A{
}
class Main {
    static void m1(A a){
        System.out.println(" A");
    }
    static void m1(B b){
        System.out.println(" B");
    }
    public static void main(String[] args) {
        m1(new C());
    }
}
```
__RESULT:__ A

## Test 9
```java
class A {
}
class B extends A {
}
class C extends B{
}
class Main {
    static void m1(A a){
        System.out.println(" A");
    }
    static void m1(B b){
        System.out.println(" B");
    }
    public static void main(String[] args) {
        m1(new C());
    }
}
```
__RESULT:__ B

## Test 10
```java
class A {
}
class B extends A {
}
class Main {
    static void m1(A a){
        System.out.println(" A");
    }
    static void m1(B b){
        System.out.println(" B");
    }
    public static void main(String[] args) {
        m1(null);
    }
}
```
__RESULT:__ B

## Test 11
```java
class Outher {
    public static int field1 = 1;
    public int field2 = 2;
    class Inner{
        private static final int field2 = 3;
        private static final int field3 = 4;
        /*private static*/ int getResult(){ // <-- cant be private and static
            return (field1 + field2 + field3);
        }
    }
}
class Main {
    public static void main(String[] args) {
        System.out.println(new Outher().new Inner().getResult());
    }
}
```
__RESULT:__ compile error

## Test12
```java
public static void main(String[] args) {
    boolean b = false;
    if (b == false)
        if (b = false)
            System.out.println("1");
        else
            System.out.println("2");
}
```
__RESULT:__ 2

## test13
```java
public class Quest21 {
    public static void main (String [] args) {
        System.out.println ("Hello, java 7");
    }
}
public class Quest22 {
    String java = "Java 7";
    public static void main (String [] args) {
        System.out.println (java);
    }
}
public class Quest23 {
    {
    System.out.println ("Java 7");
    }
}
``` 
Код класса _Quest21_ ошибок не содержит; статический метод main() класса _Quest22_ пытается получить доступ к 
нестатическому полю, описанному в этом классе, что является ошибкой; код класса _Quest23_ включает логический блок 
инициализации, внутри которого вызов методов допустим, код данного класса скомпилируется без ошибок, однако 
при запуске возникнет исключительная ситуация типа `java.lang.NoClassSuchMethodError`. 

## test14
```java
String s; // 1
if ((s = "java") == "java") {// 2
    System.out.println (s+ " true");
} else {
    System.out.println (s+ " false");
}
```
Ошибок компиляции данный фрагмент кода не вызовет. Ссылка s инициализируется перед первым использованием. 
Сравнение в операторе __if__ производится по ссылкам на строку, определенную в пуле литералов.

## Test15
```java
public class Quest5 {
    private static void main (String [] args) {
        System.out.println (args [2]);
    }
}
```
Данное приложение не запустится, так как статический метод `main` имеет атрибут доступа `private`, который не позволяет 
вызвать данный метод на выполнение вне класса _Quest5_. После успешной компиляции и запуска с помощью командной строки
Quest6 Java 7 "" появится сообщение __Error: Main method not found in class Quest5, please define the main method 
as: public static void main(String[] args)__.

## Test16
```java
public class Quest6 {
    public static void main (String [] args) {
        System.out.print ("A");
        main ("java7");
    }
    private static void main (String args) {
        System.out.print ("B");
    }
}
```
__RESULT:__ AB

## Test17
```java
class Book {
    private String book;
    public void setBook (String b) {book = b;}
}
public class Quest7 {
    public static void main (String [] args) {
        Book book1 = new Book (); book1.setBook ("Java 7"); 
        Book book2 = new Book (); book2.setBook ("Java 7");
        if (book1.equals (book2)) {
            System.out.println ("True");
        } else {
            System.out.println ("False");
        }
    }
}
```
__RESULT:__ false  
Класс __Book__ не имеет собственной реализации метода equals, данный метод (возвращающий `true` только при 
тождественности сравниваемых ссылок) наследуется от класса `Object`.

## Test18
```java
int var1 = 356f
double var2 = 356f // <-- true
float var3 = 356f // <-- true
char var4 = 356f
long var5 = 356f
byte var6 = 356f
Integer var7 = 356f
Character var8 = 356f
Object var9 = 356f // <-- true
```
## Test19
```java
public class Quest4 {
    public static void main (String [] args) {
        double x=0, y=2, z;
        z = y/x;
        System.out.println ("z="+z);
    }
}
```
__RESULT:__ z=Infinity  
При делении на вещественный нуль приводит к возбуждению исключительной ситуации. Результатом, в зависимости от 
делимого, будет +/- Infinity.

## Test20
```java
class Item {}
1) int [] mas1 = new int [24];
2) Integer mas2 [] = new Integer [24];
3) char [] mas3 = new Character [] {'a', 'b', 'c'};
4) Item [] mas4 = new Item {new Item (), new Item ()};
5) double [] mas5 = {5, 10, 15, 20};
6) int [] mas6 [] = new int [4] [5];
7) int mas7 [] [] = new int [4] [];
```
Компиляция строки 3 приведет к ошибке __Type mismatch: cannot convert from Character[] to char[]__. Строка 4 содержит 
синтаксическую ошибку — отсутствуют квадратные скобки, указывающие, что создается массив.

## Test21
```java
1) public class Quest1 {}
2) public static class Quest1 {}
3) public abstract final class Quest1 {}
4) private class Quest1 {}
5) final class Quest1 {}
```
__RESULT:__ 2, 3, 4
Корректными являются описания классов 1) и 5). При описании класса Quest1 в файле Quest1.java допустимо использовать 
модификаторы `public`, `abstract` или `final`.

## Test22
```java
class Test22 {
    public static int method() {
        final int loc;
        System.out.println(loc);//1
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
```
При компиляции данной программы будет ошибка в строке 1, так как локальная переменная используется без инициализации

## Test23
```java
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
```
__RESULT:__ Вызоветься три конструктора
При создании объекта класса __Test23__ в строке 1 вызовется 3 конструктора, один класса __Object__, и два класса __Test23__.

## Test24
```java
class Test24{
    public void meth (Number obj) {System.out.print ("1");}
    public void meth (Character obj) {System.out.print ("2");}
    private static void meth (Integer obj) {System.out.print ("3");}
    public void meth (int i) {System.out.print ("4");}
    public void meth (double d) {System.out.print ("5");}
    public static void main (String [] args) {
        Test24 q = new Test24 ();
        Number n = 67;
        Integer i = 78;
        q.meth (n);
        q.meth (i);
    }
}
```
__RESULT:__ 13
Для разрешения перегрузки сначала ищется метод, тип формального параметра которого совпадает с типом фактического 
параметра, и только в случае неудачи ищется другой метод, к типу формального параметра которого можно 
преобразовать передаваемый объект. 

## Test25
```java
public class Quest8<T1, T2>;
1.Quest8 obj = new Quest8 ()
2.Quest8<Object> obj = new Quest8<Object> ()
3.Quest8<Object, Object> obj = new Quest8<Object, Object> ()
4.Quest8<..., Object> obj = new Quest8<..., Object> ()
5.Quest8<Object, Integer> obj = new Quest8<Integer, Object> ()
6.Quest8<Number, Integer> obj = new Quest8<Integer, Integer> ()
```
__RESULT:__ 1, 3
Второй вариант создания экземпляра параметризованного класса содержит неверное число аргументов, четвертый — 
объявлен с неправильным синтаксисом, пятый и шестой содержат ошибку преобразования типа.

## Test26
```java
class Test25{
    enum Numbers {ONE, TWO, THREE, FOUR, FIVE};
    public static void main (String [] args) {
        Numbers n1 = Numbers.ONE;
        Numbers n2 = Numbers.ONE;//1
        if (n1 == n2) {System.out.print ("true");}
        else {System.out.print ("false");}
        System.out.println (Numbers.FIVE.ordinal ());//2
    }
}
```
__RESULT:__ true4

## Test27
```java
package ch04.q01;
class Quest41 {}
1) package ch04.q01; class Quest4 extends Quest41 {}
2) package ch04.q01._2; public class Quest42 extends Quest41 {}
3) package ch04.q01; public class Quest43 implements Quest41 {}
4) package ch04.q01._2; import ch04.q01.Quest41;
5) public class Quest44 extends Quest41 {}
6) package ch04.q01; public class Quest45 extends Quest41 {}
```
__RESULT:__ 1, 6
Класс Quest41 объявлен в пакете с атрибутом friendly, значит, доступен для наследования классам этого пакета. 
Для наследования применяется ключевое слово `extends`, а не `implements`. 

## Test28
```java
class Test28 {
    public class Quest43 {
        private final void method() { } //1
    }
    class Quest431 extends Quest43 {
        public void method() { } //2
    }
}
```
Компиляция этого кода завершится без ошибок, закрытый final-метод класса Quest43 не переопределяется открытым 
методом класса Quest431.

## Test29
```java
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
```
__RESULT:__ Quest53
При вызове getClass().getSimpleName() в виде строки вернется имя класса, от которого был создан объект

## Test30
```java
class Test30 {
    class Item {
        public int item;
        Item (int item) {
            this.item = item;
        }
    }
    public class Quest61 {
        public void main (String [] args) {
            Item ar1 [] = {new Item (1), new Item (2), new Item (3)};
            Item ar2 [] = ar1.clone ();
            ar2 [0].item = 4;
            System.out.println (ar1 [0].item +" " + ar1 [1].item + " " + ar1 [2].item);
        }
    }
}
```
__RESULT:__ 4 2 3
Для массивов Java переопределен метод clone(), который производит поэлементное копирование.

## Test31
```java
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
```
__RESULT:__ 14945

## Test32
```java
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

        class Inner1 /*-----------*/ { //<------ insert lose string
            public void print() {

                System.out.println("In inner.");

            }
        }
    }
}
```
__RESULT:__ extends Outer

## Test33
Выберите правильный вариант доступа из внутреннего Inner класса к эк-
земпляру его внешнего Outer класса (1):
1) Outer.class.this;
2) new Outer ().this;
3) Inner.Outer.class.this;
4) Outer.class.newInstance ().this;
5) Outer.this.
__RESULT:__ 5
Для получения доступа из внутреннего класса к экземпляру его внешнего класса необходимо в ссылке указать имя 
класса и ключевое слово this, поставив между ними точку (например, Outer.this).

## Test34
Фрагмент 1
```java
new Object () {
	public void hello () {
        System.out.print ("Hello!");	
    }
}.hello ();
```
Фрагмент 2
```java
Object obj = new Object () {
    public void hello () {
        System.out.print ("Hello!");
    }
};
obj.hello ();
```
__RESULT:__ первый фрагмент кода скомпилируется и выведет на консоль строку «Hello!», при компиляции второго 
            фрагмента возникнет ошибка;
Во втором фрагменте кода создается объект от анонимного класса, на объект указывает ссылка типа Object, для которого 
метод hello() не определен.

## Test35
```java
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
            Quest1 test42 = new Quest1();
            Quest10 quest10 = test42;
            Quest100 quest100 = test42;
            System.out.println(quest10.returner() + "" + quest100.returner());
        }
    }
}
```
__RESULT:__ компиляция и запуск программы осуществится без ошибок
Метод returner() класса Quest1 реализует соответствующий абстрактный метод класса Quest100 и одновременно метод 
returner() интерфейса Quest10. В последнем случае возвращаемый тип заменяется его подклассом (метод подставки). 

## Test36
```java
1)
interface Quest20{
    class Inner{
        private int x;
    }
}
2)
interface Quest21{
    static class Inner{ int x; }
}
3)
class Quest22{
    interface Inner{ int x; }
}
4)
class Quest23{
    static interface Inner{		
        int x;
    }
}
```
__RESULT:__ 3, 4 скомпилируются без ошибок

## Test37
```java
interface Inter1{
    void f();
}
interface Inter2 extends Inter1{
    void f();
}
class Cl1 implements Inter1{
    public void f(){		
        System.out.println("one");
    }
}
class Cl2 implements Inter2{
    public void f(){	
        System.out.println("two");
    }
}
public static void main(String[] args) {
    Inter2 obj = new Cl2();
    ((Inter1) obj).f();
}
```
Какой метод f() будет вызван при выполнении следующего кода (1)?
__RESULT:__ метод f() класса Cl2
На объект obj класса Cl2 ссылается ссылка базового типа (интерфейса) Inter1, метод f() для класса Cl2 переопределен, 
следовательно, при выполнении кода механизм позднего связывания вызовет метод f() класса Cl2.

## Test38
```java
String s1 = "Minsk";
String s2 = new String("Minsk");
if(s1.equals(s2.intern())){
    System.out.print("true");
} else {
    System.out.print("false");
}
if(s1 == s2){
    System.out.print("true");
} else{
    System.out.print("false");
}
```
__RESULT:__ true false
Синтаксис языка Java позволяет создавать строковые объекты с использованием упрощенного синтаксиса, но в этом 
случая строка автоматически размещается в пуле литералов. Метод intern() класса String размещает строку в пуле 
литералов и возвращает ссылку на нее, ссылку нужно сохранить для дальнейшего использования. 

## Test39
```java
StringBuilder sb1 = new StringBuilder("I like Java.");//1
StringBuilder sb2 = new StringBuilder(sb1);//2
if (sb1.equals(sb2)){
    System.out.println("true");
} else {
    System.out.println("false");
}
```
__RESULT:__ false
Для класса StringBuffer не переопределены методы equals() и hashCode().

## Test40
```java
Pattern p = Pattern.compile("(1*)0");
Matcher m = p.matcher("111110");
System.out.println(m.group(1));
```
__RESULT:__ ошибка выполнения.
Начальное состояние объекта типа Matcher неопределенно, поэтому до вызова метода group() на объекте необходимо 
вызвать любой из методов обработчиков (например, find() или lookingAt()).

## Test41
```java
class Test41 {
    public static void main(String[] args) {
        try { FileReader fr1 = new FileReader("kovteba/tests/test1.txt");
            try { FileReader fr2 = new FileReader("test2.txt");
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
```
__RESULT:__ test1+

## Test42
```java
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
```
__RESULT:__ 5
Если в конструкторе выбрасывается исключение, то объект не создается. ArithmeticException гасится соответствующим 
блоком catch, не обработается только NullPointerException, полученный в строке 5.

## Test43
```java
    public static void main(String[] args) {
        try {
            throw new UnsupportedOperationException();
        } catch(Throwable t) {
            System.out.print("1");
        } catch(Exception e) {
            System.out.print("2");
        } catch(UnsupportedOperationException uoe) {
            System.out.print("3");
        }
    }
```
## Test44 Какой новый модификатор для методов в интерфейсах был добавлен в Java 9?  
- private
- static
- abstract
- final

Answer: private

## Test45 Связь между классами Sample и Info называется
```java
class Sample1 {
   private Info info;

   public Info getInfo() {
      return info;
   }

   public void setInfo(Info info) {
      this.info = info;
   }
}
class Info{ }
```
- Композиция
- Агрегация 
- Наследование

Answer: Композиция


## Test46 
```java
class Test46 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        System.out.println(args[0]);
    }
}
```
Answer: Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at kovteba.tests.Test46.main(Tests.java:365)

## Test47
```java
class Test47 {
    public static void main(String[] args) {
        int i1, i2, i3 = 10;
        System.out.println(i1 + " " + i2 + " " + i3);
    }
}
```
Answer:   
        Error:(372, 28) java: variable i1 might not have been initialized  
        Error:(372, 28) java: variable i2 might not have been initialized

## Test49
```java
class Test49 {
    public static void main(String[] args) {
        String s1 = new String("Test");
        String s2 = new String("Test");
        if (s1 == s2){
            System.out.println("same");
        }
        if (s1.equals(s2)){
            System.out.println("Equals");
        }
    }
}
```
Answer: Equals

## Test50
```java
class Test50 {
    public static void main(String[] args) {
        int i = 1;
        i += new Date();// <-- Error
        System.out.println(i);
    }
}
```
Answer: Error:(404, 11) java: bad operand types for binary operator '+'
          first type:  int
          second type: java.util.Date
          
## Test51
```java
class Test51 {
    public static void main(String[] args) {
        System.out.println(Integer.compare(1, -2));
    }
}
```
Answer: 1

## Test
```java
class Test52 {
    public static void main(String[] args) {
        System.out.println(get());
    }
    public static boolean get(){
        return true || get();
    }
}
```
Answer: true

## Test54
```java
class Test53 {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push("abc");
        stack.push("abcd");
        System.out.println(stack.pop());
        System.out.println(stack.size());
    }
}
```
Answer: abcd
        1

## Test54
```java
class Test54 {
    public static void main(String[] args) {
        int ch = System.in.read();// <-- Error
        System.out.println(ch);
    }
}
```
Answer: Error:(436, 32) java: unreported exception java.io.IOException; must be caught or declared to be thrown

## Test55
```java
class Test55 {
    public static void main(String[] args) {
        String text = "ABC";
        String subString = "";
        System.out.println(text.contains(subString));
    }
}
```
Answer: true

## Test56
```java
class Test56 {
    public static void main(String[] args) {
        int i = 1;
        {
            i = 2;
        }
        System.out.println(i);
    }
}
```
Answer: 2

## Test57
```java
class Test57 {
    private Object data;
    public Test57(Object data){
        this.data = data;
    }
    public List<String> stringList(){
        return Arrays.asList(String.valueOf(data));
    }
    public static void main(String[] args) {
        Test57 test57 = new Test57("1");
        for (String s : test57.stringList()){
            System.out.println(s);
        }
    }
}
```
Answer: 1

## Test58
```java
class Test58 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", null);
        System.out.println(map.get("1"));
    }
}
```
Answer: null

## Test59
```java
class Test59 {
    Example example;
}
class Example{}
```
Answer: Агрегация

## Test60
```java
class Test60 {
    public static void main(String[] args) {
        
    }
    public static <T extends Date> void run(List<T> items){
        System.out.println("String");
    }
    public static <T extends Object> void run(List<T> items){
        System.out.println("Object");
    }
}
```
Answer: Error:(493, 43) java: name clash: <T>run(java.util.List<T>) and <T>run(java.util.List<T>) have the same erasure

## Test61
```java
class Test61 {
    public static void main(String[] args) {
        System.out.println(invert(true));
        System.out.println(invert(false));
    }
    public static boolean invert(boolean flag){
        return flag ? false : true;
    }
}
```
Answer: invert true -> false, false -> true

## Test62 Что вернет выражение 1.0/0.0?
Answer:  генерации исключения `ArithmeticException` не произойдет, будет возвращено значение `Double.INFINITY`.

## Test63
```java
class Test63{
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
```
Answer:  
        true  
        126 127  
        false  
Explain: Автобокс кэширует от -128 до 127. Это указано в JLS ( 5.1.7 ).

## Test64
```java
class Test64 {
   static int a = 1111;
   public static void main(String[] args) {
      System.out.println(a--);
      System.out.println(--a);
      System.out.println(a-- - --a);
      System.out.println(a++ + ++a);
   }
}
```
Answer:  
        1111  
        1109  
        2  
        2216  

## Test65
```java
class Test65 {
   public static void main(String[] args) {
      String s1 = "H";
      String s2 = s1;
      System.out.println(s1 == s2);
      s2 += "1";
      System.out.println(s1 == s2);
   }
}
```
Answer:   
        true  
        false  

## Test76
```java
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
```
Answer:   
        true  
        false  
        false  
        false  
        true  
        false  
        true  
        true  
        false  
        true  

## Test68
```java
class Test68 {
   private static int value = 2;
   static {
//      this.value = 1; <-- Error
        value = 1; // <-- right
   }

   public static void main(String[] args) {
      System.out.println(value);
   }
}
```
Answer:   Error:(571, 7) java: non-static variable this cannot be referenced from a static context

## Test69
```java
class Test69 {
   public static void main(String[] args) {
      byte a = 1;
      byte b = ++a;
      byte c = -a;    // 3
      System.out.print(a);
      System.out.print(b);
      System.out.print(c);
   }
}
```
Answer:  Error:(583, 16) java: incompatible types: possible lossy conversion from int to byte

## Test70
```java
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
```
Answer: будет NullPointerException. цикл развернется в такую конструкцию:
```java
for(Iterator<object> i = map.keySet().iterator(); i.hasNext(); ) { 
    String item = i.next(); 
    System.out.println(item); 
} 
```

## Test71
```java
class Test71 implements A, B {
   public static void main(String[] args) {
      System.out.println(Test71.text);// <-- Error
   }
}
interface A { String text = "a"; }

interface B { String text = "b"; }
```
Answer:   
        На самом деле переменная text имеет неоднозначное значение. Об этом говорит ошибка компиляции 
        "The field Implementor.text is ambiguous". То есть переменная text имеет сразу два значения, 
        взятые у обоих интерфейсов.

## Test72
```java
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
```
Answer:   
        В результате выполнения данного кода возникнет NullPointerException, так как, создавая объект класса B, 
        сначала вызывается конструктор супер класса (родителя). В конструкторе родителя происходит вызов метода 
        printLength(), который переопределен в подклассе (наследнике), внутри этого метода идет обращение к 
        переменной str наследника, которая еще не проинициализирована и равна null, так как конструктор родителя 
        еще не закончил свою работу, отсюда и исключение.

## Test73 Измените код так, чтобы он вернул hello world на консоль.
```java
public class NullReferenceTest { 
   //..... 
    public static void main(String[] args) { 
        NullReferenceTest nullReferenceTest = null; 
        System.out.println(nullReferenceTest./*<some descriptor>*/); 
    } 
} 
```
Answer:  
        Через null ссылку можно обратиться к статике, следовательно могут быть такие варианты:   
        - Добавить в класс NullReferenceTest статическое поле string со значение "hello world"   
        - Добавить статический метод  

## Test73 Без точки с запятой
```java
class Test73 {
   public static void main(String[] args) {
      if(System.out.printf("Hello world") == null){}
   }
}
```
Answer: 

## Test74
```java
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
```
Answer:   
        5
        11
        12
        13

## Test75
```java
int v1=1; long v2=2; v1=v1+v2;  
int v1=1; long v2=2; v1+=v2;  
```
Answer:   
        При сокращённых операциях в java производится приведение типов. То есть v1+=v2 аналогична v1=(int)(v1+v2) 
        Соответственно в первой строке будет ошибка компиляции так как long шире чем int, а вторая строка успешно 
        выполнится.

## Test76
```java
class Test76 {
   public static class B {

      B b = new B();

      public int show(){
         return (true ? null : 0);
      }
   }
   public static void main(String[] args)  {

      B b = new B();
      b.show();
   }
}
```
Answer:   
        - Каждый раз, когда мы создаем объект какого-либо класса, первым вызывается конструктор и выделяется память 
            для всех нестатических переменных.
        - Здесь B b = new B(); объекту класса B присваивается новый объект того же класса.
        - Запись B b = new B(); приводит к рекурсивному исполнению конструктора, что создает бесконечные объекты. 
            Именно поэтому во время выполнения этого кода будет возбуждено исключение java.lang.StackOverFlowError 
            в потоке "main".
        - Распространенной причиной появления такого рода исключений является плохая рекурсия. Обычно это 
            происходит из-за неправильного условия завершения.

## Test77
```java
class Test77 {
   public static void show(){
      System.out.println("Static method called");
   }
   public static void main(String[] args)  {
      Test77 obj = null;
      obj.show();
   }
}
```
Answer:    
        Мы можем вызвать статические методы, используя переменную, ссылающуюся на null. Дело в том, что статические 
        методы находятся на уровне класса. Именно поэтому мы можем вызывать статические функции как при помощи самого 
        класса, так и при помощи переменной, равной null.

## Test78
```java
class Test78{
   static int a = 1111;
   static
   {
      a = a-- - --a;
   }
   {
      a = a++ + ++a;
   }
   public static void main(String[] args)  {
      System.out.println(a);
   }
}
```
Answer: 2

## Test79
```java
class Test79 {
   int GetValue()
   {
      return (true ? null : 0);
   }
   public static void main(String[] args)  {
      Test79 obj = new Test79();
      obj.GetValue();
   }
}
```
Answer: java.lang.NullPointerException

## Test80
```java
class Test80A
{
   void method(int i) { }
}
class Test80B extends Test80A {
   @Override
   void method(Integer i)
   {

   }
}
```
Answer: Compile time Error:The method method(Integer) of type B must override or implement a supertype method

## Test81
```java
class Test81 {
   public static void main(String [] args) {
      Integer i = new Integer(null);
      String s = new String(null);
   }
}
```
Answer: Compile time Error at line number 9:The constructor String(String) is ambiguous

## Test82
```java
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
   public static void main(String [] args) {
      System.out.println(method1(11));
   }
}
```
Answer: 11

## Test83
```java
class Test83 {
   public static void main(String[] args) {
//      System.out.println(null);
   }
}
```
Answer:   
        Происходит, разумеется, ошибка компиляции. У метода println есть несколько вариаций. Он может принимать 
        String, Object или char[]. При передаче в эту функцию аргумента null компилятор не может определиться, 
        к какому из трех методов обращаться.

## Test84
```java
class Test84 {
   public static void main(String[] args) {
        String s1 = "ASDFG";
        String s2 = null;
        System.out.println(s1.concat(s2)); // NullPointerException
        System.out.println(s1 + s2); // ASDFGnull
   }
}
```
Answer: 

## Test85  ???????????????
```java
class Test85 {
   public static void main(String[] args) {
      List<String> list = Arrays.asList("Hello", "World");
      List<Integer> data = new ArrayList(list);
      for (Integer i : data){ // <--  ClassCastException
         System.out.println(i);
      }
   }
}
```
Answer: 

## Test86
```java
String s1 = new String("ABC").intern();
String s2 = new String("ABC");
System.out.println(s1 == s2); //false
String s3 = "ABC";
String s4 = "ABC";
System.out.println(s1 == s2); //false
System.out.println(s3 == s4); //true. Т.к. один набор литералов будет указывать на одну область памяти
System.out.println(s1 == s4); //false. Т.к. один набор литералов будет указывать на одну область памяти
System.out.println(s1.equals(s2));//true
```
Answer: 

## Test86
```java
class Test86 {
   public static void main(String[] args) {
      String s = null;
      System.out.println(s.length());
   }
}

```
Answer: NullPointerException

## Test87
```java
class Test87 {
   public static void main(String[] args) {
      String s = null;
      System.out.println(System.identityHashCode(s));
   }
}
```
Answer: 0

## Test88
```java
class Test88 {
   public static void main(String[] args) {
//      System.out.println(get()++);
   }
   static int get(){
      return 1;
   }
}
```
Answer: Compille error

## Test89
```java
class Test89 {
   abstract class ClassName1 {abstract  void woof(){}}  // false
   abstract class ClassName2 {abstract  void woof();}  // true
   class ClassName3{abstract void woof();}  // false
   abstract class ClassName4 { void woof(){}}  // true
   abstract class ClassName5 { void woof();}  // false
}
```
Answer: 

## Test90
```java
class Test90 {
   class A {
      protected int i;

      public A(int i) { this.i = i; }
   }
   class B extends A{   }
}
```
Answer: 
        Dont right
        Add constructor to B class
```java
      public B(int i) {
         super(i);
      }
```

## Test91
```java
class Test91 {
//   1
   class A {
      public A (){}
      public A (int k){}
   }

//   2
   class A {}

//   3
   class A {
      public A (int k){}
      public A (int k, int m){}
   }

//   4
   class A {
      public A (int k){}
   }
   
   class B extends A {
      public B(int p1, int p2) {
         System.out.println(p1);
         System.out.println(p2);
      }
      public B(int m) { super(m); }
   }
}
```
Answer: 1. Класс-потомок явно или неявно вызывает конструкторы класса-предка.


## Test92
```java
class Test92 {
   static class Super {
      public int getNumber(int a){
         return a;
      }
   }
   static class TestClass extends Super {
      public int getNumber(int a, int b){
         return 5;
      }
   }
   public static void main(String[] args) {
      System.out.println(new TestClass().getNumber(4));
   }
}
```
Answer: 4

## Test
```java
class Test93 {
   static class Super {
      public short getNumber(){
         return 100;
      }
   }
   static class TestClass extends Super {
      public byte getNumber(){ // compile error
         return 5;
      }
   }
   public static void main(String[] args) {
      Super s = new TestClass();
      System.out.println(s.getNumber());
   }
}
```
Answer: commpile error, dont right change return type

## Test94
```java
class Test94 {
   class Class1{}
   class Class2 extends Class1{}
   abstract class Class3{
      abstract Class1 method();
   }
   class Class4 extends Class3{
      ??????
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
```
Answer: 5

## Test95
```java
class Test95 {
   static class A{
      class B{}
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
```
Answer: 2, 5

## Test96
```java
class Test96 {
   public static void main(String[] args) {
      int mult = 10;
      class Local {
         int multiplay(int n) {
            return n * mult; // <- compile error
         }
      }
      mult = 5;
      int res = new Local().multiplay(mult);
      System.out.println(res);
   }
}
```
Answer: compile error, локальные классы могут использовать только final переменные, либо те которые гарантированно не 
изменят свое знаение

## Test97
```java
abstract class Test97 {
   protected String s;
   public Test97(String s) {
      this.s = s;
   }
   abstract void print();
   public static void main(String[] args) {
      Test97 test97 = new Test97() {
         String s = "1";
         @Override
         void print() {
            System.out.println(s);
         }
      };
      test97.print();
   }
}
```
Answer: compile error, lose args in constructor

## Test99
```java
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
```
Answer: 11212

## Test100
```java
class Test100 {
   public static void main(String[] args) {
      char[] arr1 = new char[]{'J', 'a', 'v', 'a'};
      String str1 = new String(arr1).intern();
      String str2 = new String("Java").intern();
      System.out.println(str1 == str2);
   }
}
```
Answer: true

## Test101
```java
class Test101 {
   public static void main(String[] args) {
      StringBuilder builder = new StringBuilder("Java");
      String str = new String("Java");
      System.out.println(str == builder);

   }
}
```
Answer: compile error

## Test
```java
class Test102 {
   public static void main(String[] args) {
      StringBuilder builder = new StringBuilder("Java");
      String str1 = builder.toString();
      String str2 = builder.toString();
      String str3 = builder.substring(0);
      String str4 = builder.toString();
      String str5 = builder.substring(0);
      System.out.println(str1);
      System.out.println(str2);
      System.out.println(str1 == str2);
      System.out.println(str1 == str3);
      System.out.println(str2 == str3);
      System.out.println(str4 == str5);
   }
}
```
Answer: 
        Java
        Java
        false
        false
        false
        true
Explain: `toString()`, `subString()` вызывают метод `newString()` который возвращает `new String()`

## Test103
```java
class Test103 {
   public static void main(String[] args) {
      StringBuilder builder = new StringBuilder("Java");
      builder.setLength(3);
      System.out.println(builder);
   }
}
```
Answer: Jav

## Test104
```java
class Test104 {
   public static void main(String[] args) {
      String s = "Java";
      s.substring(1);
      System.out.println(s);
   }
}

```
Answer: Java

## Test105
```java
class Test105 {
   public static void main(String[] args) {
      StringBuilder stringBuilder = new StringBuilder("Java");
      StringBuffer stringBuffer = new StringBuffer("Java");
      System.out.println(stringBuilder.hashCode() == stringBuffer.hashCode()); // false
      System.out.println(stringBuilder.capacity() == stringBuffer.capacity()); // true
   }
}
```
Answer: 
        false
        true
        
## Test106
```java
class Test106 {
   static void method(String s){
      System.out.println("String " + s);
   }
   static void method(StringBuilder builder){
      System.out.println("StringBuilder " + builder);
   }
   public static void main(String[] args) {
      method("Java"); // output : String Java
      method(null);  //  compile error
      method(new Object());  //  compile error
   }
}
``` 
Answer: 

## Test107
```java
class Test107 {
   public static void main(String[] args) {
      StringBuilder builder = new StringBuilder("Java");
      builder.insert(5, "-8");
      System.out.println(builder);
   }
}
``` 
Answer: RunTime error (StringIndexOutOfBoundsException)

## Test109
```java
class Test109 {
   public static void main(String[] args) {
      String str1 = "Hello";
      String str2 = "World";
      UnaryOperator<String> operator = s -> s.concat(str1);
      System.out.println(operator.apply(str2));
   }
}
``` 
Answer: WorldHello

## Test110
```java
class Test110 {
   public void m() throws Exception{
      throw new Exception();
   }
}
class Test110t extends Test110{
   public void m(){}
   public static void main(String[] args) {
      Test110 x = new Test110t();
      x.m(); // compile error
   }
}
``` 
```java
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
      base.m();
   }
}
```
Answer: compile error. Если обращаемся в методу базового класса то требуется добавить exception в сигнатуру

## Test111
```java
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
``` 
Answer: error : printed

## Test112
```java
class Test112 {
   static class A {
      protected void m() throws Exception{}
   }
   static class B extends A { 
      public void m(){} 
   }
   public static void main(String[] args) {
      A a = new B();
      a.m();
   }
}
``` 
Answer: compile error. требуется добавить exception в сигнатуру

## Test
```java
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
``` 
Answer: Run withiout errors

## Test114
```java
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
         test114.m1(); // lose try block
      } finally {
         test114.m2();
      }
   }
}
``` 
Answer: compile error. Lose try block for second method m1

## Test115    Сколько обьектов будет создано, 1 не верно ????????????????   
```java
class Test115 {
   public Test115() {
      try {
         throw new MyException();
      } catch (Exception e){

      }
   }
   public static void main(String[] args) {
      Test115 test115 = new Test115();
      Test115 test1151 = test115;
   }
}
class MyException extends Exception {
   private static final long serialUID = 1L;
}
```
Answer: 

## Test116
```java
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
      try {
         m1();
      } finally {
         m2();
      }
   }
}
```
Answer: Compile error, lose catch block

## Test118
```java
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
```
Answer: 1 2 3 4 5

## Test119
```java
class Test119 {
   public static void main(String args[]) {
      System.out.println(TestClass.v);
      System.out.println("-");
      new TestClass().a();
      System.out.println("-");
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
```
Answer: 
```
!!! Static initializer
Some val
!!! Non-static initializer
!!! a() called
Val from non-static
```

## Test120
```java
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
```
Answer: 2

## Test
```java

```
Answer: 

## Test
```java

```
Answer: 

## Test
```java

```
Answer: 

## Test
```java

```
Answer: 
## Test
```java

```
Answer: 

## Test
```java

```
Answer: 





## Theory
### Какой базовый тип в Java NIO, который представляет собой соединение ввода/вывода?
Answer: Channel

### Какой тип данных в Java следует использовать для хранения данных из столбца таблицы типа varchar?
Answer: String

### Что такое статический конструктор в Java?
Answer: В Java нет такого понятия

### Какой новый тип для упрощения многопоточных операций появился в Java 8?
Answer: 

### Что не позволяет делать reflection в Java?
Answer: Изменять код методов

### javax.servlet.ServletException: Class home.web.Type is not a Servlet
Answer: Проблема заключается в том, что либа с сервлетами не должна входить в итоговый war файл, а должна 
использоваться только для компиляции. Такого поведения можно добиться добавив `<scope>provided</scope>` в тег dependency, 
содержащий либу с сервлетами.

### 
Answer: 
### 
Answer: 
### 
Answer: 
### 
Answer: 
### 
Answer: 
### 
Answer: 
### 
Answer: 
### 
Answer: 
### 
Answer:















