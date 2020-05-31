# Lambda

- [Структура лямбда-выражения](#Структура-лямбда-выражения)
- [Создание лямбда-выражений](#Создание-лямбда-выражений)
- [Лямбды, анонимные классы и обычные классы](#Лямбды,-анонимные-классы-и-обычные-классы)
- [Ссылки на метод](#Ссылки-на-метод)
- [](#)

## Структура лямбда выражения
> (method params) -> {lambda expression body}  

где:
- method params - список входящих параметров, разделенных запятой(в случаи если параметров больше одного 
    заключаются в скобки). 
- оператор "->" - служит разделителем между списком параметров нашего метода и телом, в котором происходит 
    его реализация. 
- lambda expression body - тело метода, состоящее из одного выражения или нескольких, заключенных в фигурные скобки.

Сигнатура лямбда-выражения соответствует сигнатуре абстрактного метода реализуемого функционального интерфейса. 
Можно даже сказать, что лямбда-выражение является реализацией абстрактного метода этого функционального интерфейса. 
Главное отличие сигнатуры лямбда-выражения от сигнатуры метода в том, что она состоит только из двух частей: списка 
аргументов и тела, разделённых при помощи «->». Возвращаемый тип и возможные выбрасываемые исключения JVM берёт 
из интерфейса.

Типы аргументов лямбда-выражения опциональны, так как они декларируются интерфейсом, но при использовании обобщений 
(дженериков) с extends/super может возникнуть необходимость в указании конкретных типов аргументов. При этом стоит 
отметить, что типы либо указываются для всех аргументов, либо не указываются вообще. Это же касается и использования 
var, введённой в Java 11. Всё это можно свести к такому правилу: все аргументы объявляются либо с типами, либо с 
var, либо без них.

Если у лямбда-выражения всего один аргумент, и для него не требуется объявление типа или var, то круглые скобки 
можно опустить. В остальных случаях, в том числе если лямбда не принимает никаких аргументов, скобки нельзя опустить.

Аналогичная ситуация и с телом лямбда-выражений: если оно состоит только из одной строки, то фигурные скобки, 
точку с запятой (;) и директиву return можно тоже опустить.

В качестве тела лямбда-выражения может использоваться ссылка на метод.

## Создание лямбда выражений
```java
@FunctionalInterface
interface CarFilter {
   boolean test(Car car);
}

class Car {
   int year;
   public int getYear() {
      return year;
   }
   public void setYear(int year) {
      this.year = year;
   }
}
```
Если мы будем использовать анонимный класс, то создание объекта CarFilter будет выглядеть примерно следующим образом:  
```java
CarFilter carFilter = new CarFilter() {
    public boolean test(Car car) {
        return car.getYear() >= 2010;
    }
};
```
Но мы можем описать объект CarFilter при помощи лямбда-выражения:  
```java
CarFilter carFilter = (Car car) -> {
    return car.getYear() >= 2010;
};
```
Однако, эту запись можно сделать ещё меньше:  
```java
CarFilter carFilter = car -> car.getYear() >= 2010;
// or JDK11+
var carFilter = (CarFilter) car -> car.getYear() >= 2010;
```

## Лямбды, анонимные классы и обычные классы
Как уже было написано, лямбда-выражения могут заменить анонимные классы, которые реализуют функциональные интерфейсы, 
но в остальных случаях анонимные классы не теряют актуальности.

Если одно и то же лямбда-выражение (или анонимный класс) используется в нескольких случаях, то появляется смысл 
сделать его членом класса или объекта, или и вовсе написать полноценный класс, реализующий необходимый интерфейс.

Но в большинстве случаев, там где можно применять лямбда-выражения, например в Stream, Optional или CompletableFuture, 
логичнее применять именно лямбды.

## Ссылки на метод
Ссылки на метод - компактные лямбда-выражения которые позволяют передавать ссылки на методы или конструкторы. 
Для этого нужно использовать ключевое слово `::`.   
Ссылочные методы внедряют полезный синтаксис, чтобы ссылаться на существующие методы или конструкторы Java-классов 
или объектов (экземпляров). Совместно с лямбда-выражениями, ссылочные методы делают языковые конструкции компактными 
и лаконичными, делая его шаблонным.

## Виды ссылок
Существует четыре вида ссылок на методы:   
- Ссылка на статический метод(ContainingClass::staticMethodName)
- Ссылка на метод конкретного объекта(ContainingObject::instanceMethodName)
- Ссылка на метод произвольного объекта конкретного типа(ContainingType::methodName)
- Ссылка на конструктор(ClassName::new), для дженериков (generics) Class< T >::new.
