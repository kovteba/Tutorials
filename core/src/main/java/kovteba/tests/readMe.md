# TESTS
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
## Test
## Test
















