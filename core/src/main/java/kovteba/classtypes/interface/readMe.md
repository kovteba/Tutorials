# Interface

- [Назначение интерфейса](#Назначение-интерфейса)
- [Параметризация интерфейсов](#Параметризация-интерфейсов)
- [Интерфейсы без методов](#Интерфейсы-без-методов)
- [Почему нельзя объявить метод интерфейса с модификатором final?](#Почему-нельзя-объявить-метод-интерфейса-с-модификатором-final?)
- [Понятие «интерфейс». Какие модификаторы по умолчанию имеют поля и методы интерфейсов?](#Понятие-«интерфейс».-Какие-модификаторы-по-умолчанию-имеют-поля-и-методы-интерфейсов?)
- [Static method in interface](#Static-method-in-interface)
- [Default method in interface](#Default-method-in-interface)
- [Private method in interface](#Private-method-in-interface)
- [Example](#Example)
- [Example FunctionalInterface](#Example-FunctionalInterface)

## Назначение интерфейса
Назначение интерфейса — описание или спецификация функциональности, которую должен реализовывать каждый класс, 
его имплементирующий. Класс, реализующий интерфейс, предоставляет к использованию объявленный интерфейс в виде 
набора `public`-методов в полном объеме.

В языке Java существует два вида интерфейсов: интерфейсы, определяющие функциональность для классов посредством 
описания методов, но не их реализации; и интерфейсы, реализация которых автоматически придает классу определенные 
свойства. К последним относятся, например, интерфейсы `Cloneable` и `Serializable`, отвечающие за клонирование и 
сохранение объекта в информа­ционном потоке соответственно.

Все объявленные в интерфейсе методы автоматически трактуются как `public` и `abstract`, а все поля — как `public`, 
`static` и `final`, даже если они так не объявлены.

Если по каким-либо причинам метод для данного класса не имеет реализации или его реализация нежелательна, 
рекомендуется генерация исключения в теле метода, а именно:
```java
public boolean blocking() {
    throw new UnsupportedOperationException(); // лучше собственное исключение
}
```
## Параметризация интерфейсов
```java
public interface IShapeAction <T extends AbstractShape> {
    double computeSquare(T shape);
    double computePerimeter(T shape);
}
```

## Интерфейсы без методов
Это так называемые _маркерные интерфейсы_. Они просто указывают что класс относится к определенному типу. 
Примером может послужить интерфейс `Clonable`, который указывает на то, что класс поддерживает механизм клонирования.

## Почему нельзя объявить метод интерфейса с модификатором final?
В случае интерфейсов указание модификатора `final` бессмысленно, т.к. все методы интерфейсов неявно объявляются как 
абстрактные, т.е. их невозможно выполнить, не реализовав где-то еще, а этого нельзя будет сделать, если у метода 
идентификатор `final`.

## Понятие «интерфейс». Какие модификаторы по умолчанию имеют поля и методы интерфейсов?
Ключевое слово `interface` используется для создания полностью абстрактных классов. Основное предназначение 
интерфейса - определять каким образом мы можем использовать класс, который его реализует. Создатель интерфейса 
определяет имена методов, списки аргументов и типы возвращаемых значений, но не реализует их поведение. 
Все методы неявно объявляются как `public`.

Начиная с Java 8 в интерфейсах разрешается размещать реализацию методов по умолчанию `default` и 
статических `static` методов.

Интерфейс также может содержать и поля. В этом случае они автоматически являются публичными `public`, 
статическими `static` и неизменяемыми `final`.

## Static method in interface
Да, у нас может быть статический метод в интерфейсе из Java 8.
Которые не переопределяется.
```java
interface MyInterface {
   void Test();
   //static void Test1(); <- error "Add method body" так как метод принадлежит интерфейсу
   static void Test1() {} //<- right
}
```
Static-методы в интерфейсе - это по существу то же самое, что static-методы в абстрактном классе.
- Статические методы в интерфейсе являются частью интерфейса, мы не можем использовать его для объектов класса 
    реализации.
- Статические методы в интерфейсе хороши для обеспечения вспомогательных методов, например, проверки на `null`, 
    сортировки коллекций и т.д.
- Статические методы в интерфейсе помогают обеспечивать безопасность, не позволяя классам, которые реализуют интерфейс, 
    переопределить их.
- Мы не можем определить статические методы для методов класса Object, потому что получим ошибку компиляции.

__Вызов static method:__   
```java
class TestClass implements MyInterface{
   public void Test() {
      MyInterface.Test1();
   }
}
```

## Default method in interface
Начиная с Java 8 в интерфейсах есть default методы с по умолчанию реализованной логикой. Которые не требуют 
имплементации
```java
interface MyInterface {
   default void Test1() {}
}
```
__Вызов default method:__  
```java
class TestClass implements MyInterface{
   public void Test() {
      MyInterface.super.Test2();
   }
}
```

## Private method in interface
C __Java 9__ доступен для методов модификатор private

## Example
```java
interface MyInterface {

   int i = 2;

   void method();

   // Не переопределяется
   static void staticMethod() {
   }

   // Не обязательно переопределять
   default void defaultMethod() {
      System.out.println("defaultMethod");
   }

   default void sum(int a, int b){
      sumAll(a, b);
   }
   default void sum(int a, int b, int c){
      sumAll(a, b, c);
   }

   private void sumAll(int... values){
      int result = 0;
      for(int n : values){
         result += n;
      }
      System.out.println(result);
   }
}

class TestClass implements MyInterface {

   @Override
   public void method() { throw new UnsupportedOperationException(); } // Обязательно переопределять

   public void myTest() { MyInterface.staticMethod(); } // Вызов статического метода interface

   public void myTestForDefault(){
      // to do something
      MyInterface.super.defaultMethod();
   }

   public static void main(String[] args) {
      TestClass testClass = new TestClass();
      testClass.method();
      testClass.sum(1, 2);
   }
}
```

## Example FunctionalInterface
```java
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
```
