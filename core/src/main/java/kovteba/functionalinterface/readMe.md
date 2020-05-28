# Functional interface

- [Введение](#Введение)
- [Predicate<T>](#Predicate<T>)
    - [Test](#Test)
    - [And](#And)
    - [Or](#Or)
    - [Not](#Not)
- [Consumer<T>](#Consumer<T>)
    - [AndThen, Accept](#AndThen,-Accept)
- [Function<T,R>](#Function<T,R>)
- [Supplier<T>](#Supplier<T>)
- [UnaryOperator<T>](#UnaryOperator<T>)
- [BinaryOperator<T>](#BinaryOperator<T>)

## Введение
В JDK 8 вместе с самой функциональностью лямбда-выражений также было добавлено некоторое количество встроенных 
функциональных интерфейсов:    
- Predicate<T>
- Consumer<T>
- Function<T,R>
- Supplier<T>
- UnaryOperator<T>
- BinaryOperator<T>

## Predicate<T>
Функциональный интерфейс `Predicate<T>` проверяет соблюдение некоторого условия. Если оно соблюдается, то возвращается 
значение `true`. В качестве параметра лямбда-выражение принимает объект типа `T`:
```java
public interface Predicate<T> {
    boolean test(T t);
}
```

### Test
```java
public class Test {
   public static void main(String[] args) {
      Predicate<Integer> isPositive = x -> x > 0;
      System.out.println(isPositive.test(5)); // true
      System.out.println(isPositive.test(-7)); // false
   }
}
```

### And
```java
public class Test {
   public static void main(String[] args) {
      Predicate<Integer> isPositive = x -> x > 0;
      Predicate<Integer> isEqualsTwo = x -> x % 2 == 0;
      System.out.println(isPositive.and(isEqualsTwo).test(6)); // true
      System.out.println(isPositive.and(isEqualsTwo).test(-6)); // false
   }
}
```

### Or
```java
public class Test {
   public static void main(String[] args) {
      Predicate<Integer> isPositive = x -> x > 0;
      Predicate<Integer> isEqualsTwo = x -> x % 2 == 0;
      System.out.println(isPositive.or(isEqualsTwo).test(-8)); // true
      System.out.println(isPositive.or(isEqualsTwo).test(8)); // true
      System.out.println(isPositive.or(isEqualsTwo).test(-3)); // false
      System.out.println(isPositive.or(isEqualsTwo).test(3)); // frue
   }
}
```

### Not
```java
public class Test {
   public static void main(String[] args) {
      Predicate<Integer> isPositive = x -> x > 0;
      Predicate<Integer> isEqualsTwo = x -> x % 2 == 0;
      System.out.println(Predicate.not(isPositive).test(-4)); // true
      System.out.println(Predicate.not(isPositive).test(4)); // false
      System.out.println(Predicate.not(isEqualsTwo).test(3)); // true
      System.out.println(Predicate.not(isEqualsTwo).test(4)); // false
   }
}
```

## Consumer<T>
__Consumer<T>__ выполняет некоторое действие над объектом типа T, при этом ничего не возвращая:
```java
public interface Consumer<T> {
    void accept(T t);
}
```

### AndThen, Accept
```java
public class Test {
   public static void main(String[] args) {
      Consumer<String> printUpperCase = str -> System.out.println(str.toUpperCase());
      printUpperCase.accept("hello");
      Consumer<String> printLowerCase = str -> System.out.println(str.toLowerCase());
      printUpperCase.andThen(printLowerCase).accept("Hello world");
   }
}
//HELLO
//HELLO WORLD
//hello world
```

## Function<T,R>
Функциональный интерфейс Function<T,R> представляет функцию перехода от объекта типа T к объекту типа R:
```java
public interface Function<T, R> {
    R apply(T t);
}       Function<Integer, String> convert = x-> String.valueOf(x) + " долларов";
         System.out.println(convert.apply(5)); // 5 долларов
```
```java
public class Test {
   public static void main(String[] args) {

      Integer f = 10;
      Function<Integer, String> convert = x-> String.valueOf(x) + " долларов";
      String s = convert.apply(f);
      System.out.println(s);
      
      Function<String, String> f1 = s1 -> s1 + "1";
      Function<String, String> f2 = s2 -> s2 + "2";
      Function<String, String> f3 = s3 -> s3 + "3";
      Function<String, String> f4 = s4 -> s4 + "4";
      System.out.println(f1.andThen(f2).compose(f3).compose(f4).apply("Compose"));//Compose4312
      System.out.println(f1.andThen(f2).andThen(f3).apply("AndThen"));///AndThen123

   }
}
```

## Supplier<T>
Supplier<T> не принимает никаких аргументов, но должен возвращать объект типа T:
```java
public class Test {
   public static void main(String[] args) {
      Supplier<User> userFactory = ()->{
         Scanner in = new Scanner(System.in);
         System.out.println("Введите имя: ");
         String name = in.nextLine();
         return new User(name);
      };
      User user1 = userFactory.get();
      User user2 = userFactory.get();
      System.out.println("Имя user1: " + user1.getName());
      System.out.println("Имя user2: " + user2.getName());
   }
}
class User{
   private String name;
   String getName(){
      return name;
   }
   User(String n){
      this.name=n;
   }
}
```

## UnaryOperator<T>
UnaryOperator<T> принимает в качестве параметра объект типа T, выполняет над ними операции и возвращает результат 
операций в виде объекта типа T:
```java
public class Test {
   public static void main(String[] args) {
      UnaryOperator<Integer> square = x -> x*x;
      int y = square.apply(5);
      System.out.println(y); // 25
   }
}
```

## BinaryOperator<T>
BinaryOperator<T> принимает в качестве параметра два объекта типа T, выполняет над ними бинарную операцию и 
возвращает ее результат также в виде объекта типа T:
```java
public class Test {
   public static void main(String[] args) {
      BinaryOperator<Integer> multiply = (x, y) -> x*y;
      System.out.println(multiply.apply(3, 5)); // 15
      System.out.println(multiply.apply(10, -2)); // -20
   }
}
```