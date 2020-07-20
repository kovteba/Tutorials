# Generics

- [Ковариантность](#Ковариантность)
- [Контравариантность](#Контравариантность)
- [Инвариантность](#Инвариантность)
- [Что такое generics](#Что-такое-generics)
- [Wildcards](#Wildcards)
- [The Get and Put Principle или PECS (Producer Extends Consumer Super)](#The-Get-and-Put-Principle-или-PECS-(Producer-Extends-Consumer-Super))
- [<?> и Raw типы](#Raw-типы)
- [Wildcard Capture](#Wildcard-Capture)
- [Переменные типа](#Переменные-типа)
- [Multiple bounds](#Multiple-bounds)
- [Type Erasure](#Type-Erasure)
- [Reifiable типы](#Reifiable-типы)
- [Unchecked Warnings](#Unchecked-Warnings)
- [Heap Pollution](#Heap-Pollution)
- [Reflection in Generics](#Reflection-in-Generics)
- [Type Inference](#Type-Inference)
- [Generics after compille](#Generics-after-compille)
- [Extends](#Extends)
- [Diamond](#Diamond)
- [Ошибка неоднозначности](#Ошибка-неоднозначности)
- [Ограничения](#Ограничения)
    - [Получить экземпляр по параметру типа нельзя](#Получить-экземпляр-по-параметру-типа-нельзя)
    - [Ограничения на статические члены](#Ограничения-на-статические-члены)
    - [Ограничения на создание массивов](#Ограничения-на-создание-массивов)
- [](#)

---

## Ковариантность
__Ковариантность__ — называется сохранение иерархии наследования исходных типов в производных типах в том же порядке. 
Так, если класс Cat наследуется от класса Animal, то естественно полагать, что перечисление _IEnumerable<Cat>_ 
будет потомком перечисления _IEnumerable<Animal>_. Действительно, «список из пяти кошек» — это частный случай 
«списка из пяти животных».  
В таком случае говорят, что тип (в данном случае обобщённый интерфейс) IEnumerable<T> ковариантен своему 
параметру-типу T.
> Множество<Кошки> = Множество<Животные>

---

## Контравариантность
__Контравариантность__ — называется обращение иерархии исходных типов на противоположную в производных типах. Так, 
если класс String наследуется от класса Object, а делегат Action<T> определён как метод, принимающий объект типа T, 
то Action<Object> наследуется от делегата Action<String>, а не наоборот. Действительно, если «все строки — объекты», 
то «всякий метод, оперирующий произвольными объектами, может выполнить операцию над строкой», но не наоборот. 
В таком случае говорят, что тип (в данном случае обобщённый делегат) Action<T> контравариантен своему параметру-типу T.

---

## Инвариантность
__Инвариантность__ — отсутствие наследования между производными типами. Если Кошка — это подтип Животные, 
то Множество<Кошки> не является подтипом Множество<Животные> и Множество<Животные> не является подтипом Множество<Кошки>.

---

## Что такое generics
__Generics__ - это технический термин, обозначающий набор свойств языка позволяющих определять и использовать 
обобщенные типы и методы.   
Обобщенные типы или методы отличаются от обычных тем, что имеют типизированные параметры.  
Примером использования обобщенных типов может служить _Java Collection Framework_.  
Так, класс `LinkedList<E>` - типичный обобщенный тип. Он содержит параметр `E`, который представляет тип элементов, 
которые будут храниться в коллекции. Создание объектов обобщенных типов происходит посредством замены 
параметризированных типов реальными типами данных. Вместо того, чтобы просто использовать `LinkedList`, 
ничего не говоря о типе элемента в списке, предлагается использовать точное указание типа `LinkedList<String>`, 
`LinkedList<Integer>` и т.п.  

__Массивы в Java ковариантны.__ Тип `S[]` является подтипом `T[]`, если `S` — подтип `T`. 
Пример присваивания:
```java
String[] strings = new String[] {"a", "b", "c"};
Object[] arr = strings;
```
__«Дженерики» инвариантны.__ 
Приведем пример:  
```java
List<Integer> ints = Arrays.asList(1,2,3);
List<Number> nums = ints; // compile-time error. Проблема обнаружилась на этапе компиляции
nums.set(2, 3.14);
assert ints.toString().equals("[1, 2, 3.14]");
```
Если взять список целых чисел, то он не будет являться ни подтипом типа Number, ни каким-либо другим подтипом. 
Он является только подтипом самого себя. То есть List <Integer> — это List<Integer> и ничего больше. Компилятор 
позаботится о том, чтобы переменная ints, объявленная как список объектов класса Integer, содержала только объекты 
класса Integer и ничего кроме них. На этапе компиляции производится проверка, и у нас в рантайме уже ничего не упадет.

---

## Wildcards
Всегда ли Generics инварианты? Нет.    

Это ковариантность. List<Integer> — подтип List<? extends Number> :
```java
List<Integer> ints = new ArrayList<Integer>();
List<? extends Number> nums = ints;
```
Это контравариантность. List<Number> является подтипом List<? super Integer>. :
```java
List<Number> nums = new ArrayList<Number>();
List<? super Integer> ints = nums;
```
Запись вида `? extends ...` или `? super ...` — называется __wildcard__ или символом подстановки, с верхней 
границей `extends` или с нижней границей `super`.   
List<? extends Number> может содержать объекты, класс которых является Number или наследуется от Number.   
List<? super Integer> может содержать объекты, класс которых Integer или у которых Integer является 
наследником (супертип от Integer).

---

## The Get and Put Principle или PECS (Producer Extends Consumer Super)
Особенность wildcard с верхней и нижней границей дает дополнительные фичи, связанные с безопасным использованием типов. 
Из одного типа переменных можно только читать, в другой — только вписывать (исключением является возможность 
записать null для extends и прочитать Object для super). Чтобы было легче запомнить, когда какой wildcard использовать, 
существует принцип __PECS — Producer Extends Consumer Super__.

- Если мы объявили wildcard с `extends`, то это _producer_. Он только «продюсирует», предоставляет элемент из 
    контейнера, а сам ничего не принимает.  
- Если же мы объявили wildcard с `super` — то это _consumer_. Он только принимает, а предоставить ничего не может.

---

## ? и Raw типы
## Raw типы
Ниже приведен `wildcard` с неограниченным символом подстановки. Мы просто ставим `<?>`, без ключевых 
слов `super` или `extends`:   
```java
static void printCollection(Collection<?> c) {
   // a wildcard collection
   for (Object o : c) {
       System.out.println(o);
   }
}
```
На самом деле такой «неограниченный» wildcard все-таки ограничен, сверху. `Collection<?>` — это тоже символ
подстановки, как и `? extends Object`. Запись вида `Collection<?>` равносильна `Collection<? extends Object>` , 
а значит — коллекция может содержать объекты любого класса, так как все классы в Java наследуются от `Object` – 
поэтому подстановка называется неограниченной.  
Если мы опустим указание типа, например, как здесь:  
```java
ArrayList arrayList = new ArrayList();
```
то, говорят, что ArrayList — это Raw тип параметризованного `ArrayList<T>`. Используя Raw типы, мы возвращаемся в 
эру до дженериков и сознательно отказываемся от всех фич, присущих параметризованным типам.

Если мы попытаемся вызвать параметризованный метода у Raw типа, то компилятор выдаст нам предупреждение 
«Unchecked call». Если мы попытаемся выполнить присваивание ссылки на параметризованный тип Raw типу, то компилятор 
выдаст предупреждение «Unchecked assignment». Игнорирование этих предупреждений, как мы увидим позже, может привести 
к ошибкам во время выполнения нашего приложения.
```java
ArrayList<String> strings = new ArrayList<>();
ArrayList arrayList = new ArrayList();
arrayList = strings; // Ok
strings = arrayList; // Unchecked assignment
arrayList.add(1); //unchecked call
```

---

## Wildcard Capture
Попробуем теперь реализовать метод, выполняющий перестановку элементов списка в обратном порядке.
```java
public static void reverse(List<?> list);
// Ошибка!
public static void reverse(List<?> list) {
  List<Object> tmp = new ArrayList<Object>(list);
  for (int i = 0; i < list.size(); i++) {
    list.set(i, tmp.get(list.size()-i-1)); // compile-time error
  }
}
```
Ошибка компиляции возникла, потому что в методе reverse в качестве аргумента принимается список с неограниченным 
символом подстановки `<?>`.  
`<?>` означает то же что и `<? extends Object>`. Следовательно, согласно принципу __PECS__, list – это producerStoreWaitNotify. 
А producerStoreWaitNotify только продюсирует элементы. А мы в цикле for вызываем метод set(), т.е. пытаемся записать в list. 
И поэтому упираемся в защиту Java, что не позволяет установить какое-то значение по индексу.  

Что делать? Нам поможет паттерн __Wildcard Capture__. Здесь мы создаем обобщенный метод rev. Он объявлен с переменной 
типа `T`. Этот метод принимает список типов `T`, и мы можем сделать сет.
```java
public static void reverse(List<?> list) { 
  rev(list); 
}
private static <T> void rev(List<T> list) {
  List<T> tmp = new ArrayList<T>(list);
  for (int i = 0; i < list.size(); i++) {
    list.set(i, tmp.get(list.size()-i-1));
  }
}
```
Теперь у нас все скомпилируется. Здесь произошел захват символа подстановки (wildcard capture). При вызове 
метода `reverse(List<?> list)`в качестве аргумента передается список каких-то объектов (например, строк или 
целых чисел). Если мы можем захватить тип этих объектов и присвоить его переменной типа X, то можем 
заключить, что T является X.  

__Вывод__  
- Если необходимо читать из контейнера, то используйте wildcard с верхней границей `? extends`.  
- Если необходимо писать в контейнер, то используйте wildcard с нижней границей `? super`. 
- Не используйте wildcard, если нужно производить и запись, и чтение.

__!!!Не используйте Raw типы! Если аргумент типа не определен, то используйте wildcard <?>.__

---

## Переменные типа
Когда мы записываем при объявлении класса или метода идентификатор в угловых скобках, например `<T>` или `<E>`, 
то создаем переменную типа.  
__Переменная типа__ — это неквалифицированный идентификатор, который можно использовать в качестве типа в теле 
класса или метода. Переменная типа может быть ограничена сверху.
```java
public static <T extends Comparable<T>> T max(Collection<T> coll) {
  T candidate = coll.iterator().next();
  for (T elt : coll) {
    if (candidate.compareTo(elt) < 0) candidate = elt;
  }
  return candidate;
}
```
В этом примере выражение `T extends Comparable<T>` определяет T (переменную типа), ограниченную сверху типом 
`Comparable<T>`. В отличие от wildcard, переменные типа могут быть ограничены только сверху (только `extends`). 
Нельзя записать `super`. Кроме того, в этом примере T зависит от самого себя, это называется __recursive bound__ 
— рекурсивная граница.

---

## Multiple bounds
__Multiple Bounds__ – множественные ограничения. Записывается через символ `&`, то есть мы говорим, что тип, 
представленный переменной типа T, должен быть ограничен сверху классом Object и интерфейсом Comparable.
```java
<T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll)
```
Первое ограничение — в данном случае Object — используется для erasure, процесса затирания типов. 
Его выполняет компилятор на этапе компиляции.

__Вывод:__  
Переменная типа может быть ограничена только сверху одним или несколькими типами. В случае множественного ограничения 
левая граница (первое ограничение) используется в процессе затирания (Type Erasure).

---

## Type Erasure
__Type Erasure__ представляет собой отображение типов (возможно, включая параметризованные типы и переменные типа) 
на типы, которые никогда не являются параметризованными типами или переменными типами. Мы записываем затирание 
типа T как |T|.  

пример стирания типа:  
```java
public <T> List<T> genericMethod(T t) {
    return list.stream().collect(Collectors.toList());
}
```
При компиляции неограниченный тип T заменяется на Object следующим образом:
```java
public List<Object> fromArrayToList(Object a) {
    return list.stream().collect(Collectors.toList());
}
```
если тип ограничен, то тип будет заменен границей во время компиляции следующим образом:
```java
public <T extends Building> void genericMethod(T t) {
    ...
}
```
изменится после компиляции:
```java
public void genericMethod(Building t) {
    ...
}
```

---

## Reifiable типы
В Java мы говорим, что тип является reifiable, если информация о нем полностью доступна во время выполнения программы.  
В reifiable типы входят:  
- Примитивные типы (`int`, `long`, `boolean`)
- Непараметризованные (необобщенные) типы (`String`, `Integer`)
- Параметризованные типы, параметры которых представлены в виде unbounded wildcard (неограниченных символов 
    подстановки) (`List<?>`, `Collection<?>`)
- Raw (несформированные) типы (`List`, `ArrayList`)
- Массивы, компоненты которых — Reifiable типы (`int[]`, `Number[]`, `List<?>[]`, `List[]`)

Почему информация об одних типах доступна, а о других нет? Дело в том, что из-за процесса затирания типов компилятором 
информация о некоторых типах может быть потеряна. Если она потерялась, то такой тип будет уже не reifiable. То есть 
она во время выполнения недоступна.

Решение не делать все обобщенные типы доступными во время выполнения — это одно из наиболее важных и противоречивых 
проектных решений в системе типов Java. Так сделали, в первую очередь, для совместимости с существующим кодом. За 
миграционную совместимость пришлось платить — полная доступность системы обобщенных типов во время выполнения невозможна.

Какие типы не являются reifiable:
- Переменная типа `T`
- Параметризованный тип с указанным типом параметра (`List<Number>`, `ArrayList<String>`, `List<List<String>>`)
- Параметризованный тип с указанной верхней или нижней границей (List<? extends Number>, Comparable<? super String>). 
    Но здесь стоит оговориться: `List<? extends Object>` — не reifiable, а List<?> — reifiable

Почему в примере ниже нельзя создать параметризованный Exception?
```java
class MyException<T> extends Exception { // Generic class may not extend ‘java.lang.Throwable’
   T t;
}
```
Каждое catch выражение в try-catch проверяет тип полученного исключения во время выполнения программы (что 
равносильно instanceof),  соответственно, тип должен быть Reifiable. Поэтому Throwable и его подтипы не могут 
быть параметризованы.

---

## Unchecked Warnings
Компиляция нашего приложения может выдать так называемый Unchecked Warning — предупреждение о том, что компилятор 
не смог корректно определить уровень безопасности использования наших типов. Это не ошибка, а предупреждение, 
так что его можно пропустить. Но желательно все-так исправить, чтобы избежать проблем в будущем.

---

## Heap Pollution
Как мы упомянули ранее, присваивание ссылки на Raw тип переменной параметризованного типа, приводит к предупреждению 
«Unchecked assignment». Если мы проигнорируем его, то возможна ситуация под названием "Heap Pollution" 
(загрязнение кучи). 
Вот пример:
```java
static List<String> t() {
   List l = new ArrayList<Number>();
   l.add(1);
   List<String> ls = l; // (1)
   ls.add("");
   return ls;
}
```
В строке (1) компилятор предупреждает об «Unchecked assignment».

Нужно привести и другой пример «загрязнения кучи» — когда у нас используются параметризованные объекты. Кусок кода 
ниже наглядно показывает, что недопустимо использовать параметризованные типы в качестве аргументов метода с 
использованием Varargs. В данном случае параметр метода m – это List<String>…, т.е. фактически, массив элементов 
типа List<String>. Учитывая правило отображения типов при затирании, тип stringLists превращается в массив raw 
списков (List[]), т.е. можно выполнить присваивание Object[] array = stringLists; и после записать в array объект, 
отличный от списка строк (1), что вызовет ClassCastException в строке (2).
```java
static void m(List<String>... stringLists) {
   Object[] array = stringLists;
   List<Integer> tmpList = Arrays.asList(42);
   array[0] = tmpList; // (1)
   String s = stringLists[0].get(0); // (2)
}
```
Рассмотрим еще один пример:
```java
ArrayList<String> strings = new ArrayList<>();
ArrayList arrayList = new ArrayList();
arrayList = strings; // (1) Ok
arrayList.add(1); // (2) unchecked call
```
Java разрешает выполнить присваивание в строке (1). Это необходимо для обеспечения обратной совместимости. Но если 
мы попытаемся выполнить метод add в строке (2), то получим предупреждение Unchecked call — компилятор предупреждает 
нас о возможной ошибке. В самом деле, мы же пытаемся в список строк добавить целое число.

---

## Reflection in Generics
Хотя при компиляции параметризованные типы подвергаются процедуре стирания (type erasure), кое-какую информацию мы 
можем получить с помощью `Reflection`.  
- Все reifiable доступны через механизм Reflection
- Информация о типе полей класса, параметров методов и возвращаемых ими значений доступна через Reflection.

Если мы хотим через Reflection получить информацию о типе объекта и этот тип не Reifiable, то у нас ничего не получится. 
Но, если, например, этот объект нам вернул какой-то метод, то мы можем получить тип возвращаемого этим методом значения:
```java
java.lang.reflect.Method.getGenericReturnType()
```
С появлением Generics класс java.lang.Class стал параметризованным. 
Рассмотрим вот этот код:
```java
List<Integer> ints = new ArrayList<Integer>();
Class<? extends List> k = ints.getClass();
assert k == ArrayList.class;
```
Переменная ints имеет тип List<Integer> и она содержит ссылку на объект типа ArrayList< Integer>. 
Тогда ints.getClass() вернёт объект типа Class<ArrayLis>, так как List<Integer> затирается в List. 
Объект типа Class<ArrayList> можно присвоить переменной k типа Class<? extends List>, согласно ковариантности 
символов подстановки? extends. А ArrayList.class возвращает объект типа Class<ArrayList>.

__Вывод:__  
Если информация о типе доступна во время выполнения программы, то такой тип называется Reifiable. К Reifiable 
типам относятся: примитивные типы, непараметризованные типы, параметризованные типы с неограниченным символом 
подстановки, Raw типы и массивы, элементы которых являются reifiable.  

Игнорирование Unchecked Warnings может привести к «загрязнению кучи» и ошибкам во время выполнения программы.

Reflection не позволяет получить информацию о типе объекта, если он не Reifiable. Но Reflection позволяет получить 
информацию о типе возвращаемого методом значения, о типе аргументов метода и о типе полей класса.

---

## Type Inference
Термин можно перевести как «Вывод типа». Это возможность компилятора определять (выводить) тип из контекста. 
Вот пример кода:
```java
List<Integer> list = new ArrayList<Integer>();
```
С появлением даймонд-оператора в  Java 7 мы можем не указывать тип у ArrayList:
```java
List<Integer> list = new ArrayList<>();
```
Компилятор выведет тип ArrayList из контекста – List<Integer>. Этот процесс и называется __type inference__.

В Java 8 сильно усовершенствовали механизм выведения типа благодаря JEP 101.  
В общем случае процесс получения информации о неизвестных типах именуется выводом типа Type Inference. 
На верхнем уровне вывод типа можно разделить на три процесса:  
- Приведение (reduction)
- Объединение (incorporation)
- Разрешение (resolution)

Эти процессы тесно взаимодействуют: приведение может запустить объединение, объединение может привести к дальнейшему 
приведению, а разрешение — к дальнейше­му объединению.  
Детальное описание механизма выведения типа доступно в спецификации языка, где ему посвящена целая глава. Мы же 
вернемся к JEP 101 и рассмотрим какие цели он преследовал.  

Предположим у нас есть вот такой класс, который описывает связный список:
```java
class List<E> {
   static <Z> List<Z> nil() { ... };
   static <Z> List<Z> cons(Z head, List<Z> tail) { ... };
   E head() { ... }
}
```
Результат обобщенного метода List.nil() может быть выведен из правой части:
```java
List<String> ls = List.nil();
```
Механизм выбора типа компилятором показывает, что аргумент типа для вызова List.nil() действительно String — это 
работает в JDK 7, все хорошо.

Выглядит разумно, что компилятор также должен иметь возможность вывести тип, когда результат такого вызова обобщенного 
метода передается другому методу в качестве аргумента, например:
```java
List.cons(42, List.nil()); //error: expected List<Integer>, found List<Object>
```
В JDK 7 мы получили бы compile-time error. А в JDK 8 скомпилируется. Это и есть первая часть JEP-101, его первая 
цель — вывод типа в позиции аргумента. Единственная возможность осуществить это в версиях до JDK 8 — использовать 
явный аргумент типа при вызове обобщенного метода:
```java
List.cons(42, List.<Integer>nil());
```
Вторая часть JEP-101 говорит о том, что неплохо бы выводить тип в цепочке вызовов обобщенных методов, например:
```java
String s = List.nil().head(); //error: expected String, found Object
```
Но данная задача не решена до сих пор, и вряд ли в ближайшее время появится такая функция. Возможно, в будущих 
версиях JDK необходимость в этом исчезнет, но пока нужно указывать аргументы вручную:
```java
String s = List.<String>nil().head();
```
После выхода JEP 101 на StackOverflow появилось множество вопросов по теме. Программисты спрашивают, почему код, 
который выполнялся на 7-й версии, на 8-й выполняется иначе – или вообще не компилируется? Вот пример такого кода:
```java
class Test {
   static void m(Object o) {
       System.out.println("one");
   }

   static void m(String[] o) {
       System.out.println("two");
   }

   static <T> T g() {
       return null;
   }

   public static void main(String[] args) {
       m(g());
   }
}
```
Посмотрим на байт-код после компиляции на JDK1.8:
```java
public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=1, locals=1, args_size=1
         0: invokestatic  #6                  // Method g:()Ljava/lang/Object;
         3: checkcast     #7                  // class "[Ljava/lang/String;"
         6: invokestatic  #8                  // Method m:([Ljava/lang/String;)V
         9: return
      LineNumberTable:
        line 15: 0
        line 16: 9
```
Инструкция под номером 0 выполняет вызов метода g:()Ljava/lang/Object; Метод возвращает java.lang.Object. Далее, 
инструкция 3 производит приведение типа («кастинг») объекта, полученного на предыдущем шаге к типу массива 
java.lang.String, и инструкция 6 выполняет метод m:([Ljava/lang/String;), что и напечатает в консоли «two».

А теперь байт-код после компиляции на JDK1.7 – то есть на Java 7:
```java
  public static void main(java.lang.String[]);
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=1, locals=1, args_size=1
         0: invokestatic  #6                  // Method g:()Ljava/lang/Object;
         3: invokestatic  #7                  // Method m:(Ljava/lang/Object;)V
         6: return        
      LineNumberTable:
        line 15: 0
        line 16: 6
```
Мы видим, что здесь нет инструкции checkcast, которую добавила Java 8, так что вызовется метод m:(Ljava/lang/Object;), 
а в консоли напечатается «one». Checkcast – результат нового выведения типа, который был усовершенствован в  Java 8.

Чтобы избежать таких проблем, Oracle выпустил руководство по переходу с JDK1.7 на JDK 1.8 в котором описаны проблемы, 
которые могут возникнуть при переходе на новую версию Java, и то, как эти проблемы можно решить.

Например если вы хотите, чтобы в коде выше после компиляции на Java 8 все работало так же, как и на Java 7, сделайте 
приведение типа вручную:
```java
public static void main(String[] args) {
   m((Object)g());
}
```

---

## Generics after compille
```java
class ClassName <T> {
   T t;
}
//Превратиться в ...
class ClassName {
   Object t;
}
```

---

## Extends
```java
class Parent{}
class GenParent<T> extends Parent {}
class Child1 extends GenParent {}
class Child2 extends GenParent<String> {}
class Child3<T> extends GenParent<T> {}
class Child4<T, X, Y> extends GenParent<Y> {}
class Child5<T, X, Y> extends GenParent {}
class Child6<T, X, Y> extends Child5<Integer, X, String> {}

interface GenInterface<T> {
   T getT();
}

class One implements GenInterface{
   @Override
   public Object getT() {
      return null;
   }
}

class Two implements GenInterface<String>{
   @Override
   public String getT() {
      return null;
   }
}

class Three<T> implements GenInterface<T>{
   @Override
   public T getT() {
      return null;
   }
}
```

---

## Diamond
```java
List<String> list = new ArrayList<>();
```
Это особый синтаксис, который добавили в Java SE 7, и называется он "the diamond", что в переводе означает алмаз. 
Почему? Можно провести аналогию формы алмаза и формы фигурных скобок: `<>`   
Также Diamond синтаксис связан с понятием __Type Inference__, или же выведение типов. Ведь компилятор, видя справа `<>` 
смотрит на левую часть, где расположено объявление типа переменной, в которую присваивается значение. И по этой 
части понимает, каким типом типизируется значение справа.
На самом деле, если в левой части указан дженерик, а справа не указан, компилятор сможет вывести тип:  
```java
import java.util.*;
public class HelloWorld{
	public static void main(String []args) {
		List<String> list = new ArrayList();
		list.add("Hello World");
		String data = list.get(0);
		System.out.println(data);
	}
}
```
Однако это будет смешиванием нового стиля с дженериками и старого стиля без них. И это крайне нежелательно. 
При компиляции кода выше мы получим сообщение: `Note: HelloWorld.java uses unchecked or unsafe operations`. На 
самом деле кажется непонятным, зачем вообще нужен тут diamond добавлять. Но вот пример:   
```java
import java.util.*;
public class HelloWorld{
	public static void main(String []args) {
		List<String> list = Arrays.asList("Hello", "World");
		List<Integer> data = new ArrayList(list);
		Integer intNumber = data.get(0);
		System.out.println(data);
	}
}
```
Как мы помним, у ArrayList есть и второй конструктор, который принимает на вход коллекцию.

И вот тут-то и кроется коварство. Без diamond синтаксиса компилятор не понимает, что его обманывают, а вот с 
diamond — понимает.

---

## Ошибка неоднозначности
```java
class MyGenClass<T, V>{
   T ob1;
   V ob2;
   void Set(T o) {
      ob1 = o;
   } 
   void set(V o) {
      ob1 = o;
   } 
}
```
При попытке перегрузить метод возникает ошибка неоднозначности.

Вопервых нет никаких ограничений чтобы сосздать класс таким образом
```java
MyGenClass<String, Sting> ob = new MyGenClass<>();
```
В таком случае методы будут одинаковы.

Более фундаментальная проблема заключается в том что после стирания типов метод будет выглядеть
```java
void set(Object o) {
   
}
```

---

## Ограничения

### Получить экземпляр по параметру типа нельзя
```java
class Gen<T> {
   T ob;
   Gen() {
      ob = new T(); // Недопустимо
   }
}
```

---

### Ограничения на статические члены
```java
class Wrong<T> {
   static T ob;
   static T getOb() {
      return ob;
   }
}
```

class Right<T> {
   static <U> U getOb(U o) {
      return o;
   }
}

---

### Ограничения на создание массивов
Стр. 435, "Полное руководство java"
```java
class Gen<T extends Number> {

   T ob;

   T vals[]; //Right

   Gen(T o, T[] nums) {
      ob = o;

//      vals = new T[10]; dont right

      vals = nums;

   }

   public static void main(String[] args) {
      Integer[] n = {1, 2, 3, 4, 5};

      Gen<Integer> iOb = new Gen<>(50, n);
      
//      Gen<Integer> gens[] = new Gen<Integer>[10];
      
      Gen<?> gens[] = new Gen[10];
   }
}

```

