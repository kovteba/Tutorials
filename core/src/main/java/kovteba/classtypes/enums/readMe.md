# ENUM

- [К Enum можно применять методы](#К-Enum-можно-применять-методы)
- [Методы equals(), hashcode(), toString(), finalize() и clone()](#Методы-equals(),-hashcode(),-toString(),-finalize()-и-clone())
- [Зачем же Enum реализовывает интерфейс Comparable?](#Зачем-же-Enum-реализовывает-интерфейс-Comparable?)
- [Enum реализовывает интерфейс Serializable](#Enum-реализовывает-интерфейс-Serializable)
- [Абстрактные методы в перечислениях](#Абстрактные-методы-в-перечислениях)
- [Специальные коллекции для перечислений](#Специальные-коллекции-для-перечислений)
- [Example](#Example)

__Enum__ - это отдельная структура. Он может находится в отдельном файле, а может быть частью класса. Но при этом 
`enum` не обязательно должен лежать в каком-либо классе. При таком подходе мы как бы создаем еще один класс, 
только вместо слова "class" пишем "enum". Посмотрите - даже в IDE они выделены отдельно:

Enums are implicitly  `public`, `static`, and `final`.

```java
public enum Seasons {
    WINTER,
    SUMMER,
    SPRING,
    FALL;
}
```

У __enum-ов__ есть модификатор доступа. Если Ваш enum не лежит внутри какого-нибудь класса, он должен быть 
объявлен `public`.  

Поля и методы в перечислениях как и в классах могут иметь модификаторы доступа `private`, `protected`, `default`, 
`public`, а вот конструкторы в перечислениях всегда будут иметь модификатор  `private`. При попытке указать другой 
модификатор мы получим ошибку компиляции.

На самом деле `EnumSet` это абстрактный класс у которого 2 реализации - `RegularEnumSet` и `JumboEnumSet`. Выбор 
реализации определяется в методах создания `EnumSet`. Если количество элементов в перечислении, из которого вы хотите 
создать `EnumSet`, не превышает 64 то выбран будет `RegularEnumSet`, если элементов больше 64 - будет выбран `JumboEnumSet`.

Почему именно 64 элемента? Потому что в `RegularEnumSet` все значения перечисления умещаются в одну переменную 
типа `long`, максимальное колличество битов в котором равно 64. Все операции в `RegularEnumSet`  основаны 
на булевой логике.

В `JumboEnumSet` также используется булевая логика, но вместе с массивом значений.

## К Enum можно применять методы
+ name() - возвращает имя
+ ordinal() - возвращает порядковый номер
+ equals()
+ hashCode()
+ toString()
+ finalize()
+ clone()
+ values()
+ valueOf()

>Enum реализовывает интерфейс Comparable  

>Enum реализовывает интерфейс Serializable

## Методы equals(), hashcode(), toString(), finalize() и clone()
__Enum__ переопределяет базовые методы класса Object. Так что их можно использовать сразу же в наших перечислениях.

+ метод `toString()` возвращает имя значения перечисления. Назвали значение WHITE, это же значение и получим при 
    вызове `toString()` или `name()`;  
+ метод `equals()` сравнивает значения перечислений по ссылкам. Почему? Потому, что значения в перечислениях являются 
    константными (уникальными), существует всего один экземпляр цвета RED, один цвета GREEN и один BLUE, значит 
    ссылка на этот экземпляр будет всего одна, значит их можно сравнивать с помощью ==. Вы можете сами убедиться 
    в этом, написав Color.RED == Color.RED или Color.GREEN == COLOR.BLUE;   
+ метод `hashCode()` использует стандартную реализацию из класса Object.  
+ метод `finalize()` пустой, а это значит, что не нужно закрывать "ресурсы" перед сборщиком мусора.  Мы говорим о 
    тех "ресурсах", которые используются в try-with-resources. Да и вообще метод `finalize()` пережиток прошлых лет и 
    в Java 9 данный метод уже помечен как `@Deprecated` (устаревший метод, который уберут в последующих реализациях);
+ метод `clone()` мы можем вызвать только внутри самого перечисления т.к. он помечен ключевым словом `protected`. Но 
    даже если мы попытаемся сделать это, то ничего мы не получим, кроме `CloneNotSupportedException`. Нужно это для 
    того чтобы нельзя было создать несколько экземпляров одного и того же перечисления. Ведь в реальной жизни у нас 
    нет двух цифр "1", нет двух значений скорости света, так и с перечислениями.

## Зачем же Enum реализовывает интерфейс Comparable?
Сделано это для того, чтобы перечисления можно было сравнивать друг с другом при сортировке. При этом сравнение 
происходит по `ordinal()` перечисления.

```java
enum Color {
    RED, GREEN, BLUE
}
System.out.println(Color.GREEN.compareTo(Color.RED)); //output: 1
System.out.println(Color.GREEN.compareTo(Color.GREEN)); //output: 0
System.out.println(Color.GREEN.compareTo(Color.BLUE)); //output: -1
System.out.println(Color.RED.compareTo(Color.BLUE)); //output: -2
```

сравнивать перечисления можно только между своими типами. Нельзя сравнивать перечисления типа Color с перечислением 
типа Car. Мало того, что компилятор не даст вам это сделать с помощью своих подсказок так еще и в самом методе 
есть проверка на тип класса перечислений.

## Enum реализовывает интерфейс Serializable
Причина того, что эти методы приватные да и к тому же бросают исключения при их вызове так же, что и в случае с `clone()`. 
Если бы эта возможность была открыта, тогда легко можно было бы сохранить перечисление в файл, затем считать его 
обратно и получить на выходе два экземпляра одного значения перечисления. Этот как два значения числа "1";

## Абстрактные методы в перечислениях
```java
public enum Seasons {
    WINTER("1"){
        @Override
        String value() {
            return "Test abstract method1";
        }
    },
    SUMMER("2"){
        @Override
        String value() {
            return "Test abstract method2";
        }
    },
    SPRING("3"){
        @Override
        String value() {
            return "Test abstract method3";
        }
    },
    FALL("4"){
        @Override
        String value() {
            return "Test abstract method4";
        }
    };
    private String value;

    Seasons(String s) {
        this.value = s;
    }
    abstract String value();
}
```
Если же мы оставим одно значение не переопределённым то получим ошибку компиляции

## Специальные коллекции для перечислений
Но раз уж мы знаем наперед значения перечислений, знаем их количество и знаем, что новые значения не будут добавляться 
в перечисление в работе приложения(в `Runtime`) т.к. это невозможно,  то разработчики придумали специальную коллекцию 
для них. Эта коллекция работает быстрее и эффективнее обычных, используя, особенности перечислений, которые мы только 
что описали выше. Эта коллекция называется EnumSet. При работе с enum хорошей практикой является использовать именно 
коллекцию  `EnumSet` вместо стандартных коллекций.

```java
Set<Country> countries = EnumSet.allOf(Sessions.class);
```

## Example
```java
enum Numbers {
   ONE("one"){
      @Override
      void abstractMethod() {
         System.out.println("abstractMethod");
      }
      void oneMethod(){
         System.out.println("one method");
      }
   },
   TWO("two") {
      @Override
      void abstractMethod() {
         System.out.println("abstractMethod");
      }
   },
   THREE("three") {
      @Override
      void abstractMethod() {
         System.out.println("abstractMethod");
      }
   };
   private String value;
   Numbers(String value) {
      this.value = value;
   }
   public String getValue() { return value; }
   
   abstract void abstractMethod();
}

class EnumExample {
   public static void main(String[] args) {
      System.out.println(Numbers.ONE.getValue()); // one
      System.out.println(Numbers.ONE.name()); // ONE
      System.out.println(Numbers.ONE.ordinal()); // 0
   }
}
```






















  



