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