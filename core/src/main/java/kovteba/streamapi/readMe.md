## Stream API

- [Stream](#Stream)
- [Collect](#Collect)
- [Разница между Collection и Stream](#Разница-между-Collection-и-Stream)
- [Stream forEach](#Stream-forEach)
- [Random 10 numvber Stream foreach](#Random-10-numvber-Stream-foreach)
- [Stream map](#Stream-map)
- [Stream filter](#Stream-filter)
- [Stream sorted](#Stream-sorted)
- [Max](#Max)
- [Min](#Min)
- [Average](#Average)
- [Sum](#Sum)
- [](#)

## Stream
__Stream__ — интерфейс, предоставляющий функциональные возможности обработки коллекций (filter, map, reduce, peek).  
Операции на стримах делятся на терминальные и нетерминальные. Нетерминальные операции модифицируют pipeline операций 
над коллекцией, при этом не изменяя саму коллекцию, терминальные (например, collect) — проводят действия pipeline'а, 
возвращают результат и закрывают Stream.

`java.util.stream` - введен для поддержки распараллеливания вычислений в потоках. Предоставляет возможность 
обрабатывать последовательность элементов исполняя одну или несколько операций, которые могут выполняться 
либо последовательно либо паралельно. Потоки делятся на последовательные и параллельные. Самая большая польза от 
этого - в работе с коллекциями.   
Операции над потоком относятся либо к промежуточным, либо к терминальным. Все промежуточные операции возвращают 
поток, так что мы можем объединять несколько промежуточных операций без использования точки с запятой. Терминальные 
операции возвращают void или непотоковый результат.

## Collect
Большинство операций класса Stream, которые модифицируют набор данных, возвращают этот набор в виде потока. Однако 
бывают ситуации, когда хотелось бы получить данные не в виде потока, а в виде обычной коллекции, например, `ArrayList` 
или `HashSet`. И для этого у класса Stream определен метод `collect`. Метод принимает в качестве параметра функцию 
преобразования к коллекции:  
- <R,A> R collect(Collector<? super T,A,R> collector) 

Параметр R представляет тип результата метода, параметр Т - тип элемента в потоке, а параметр А - тип промежуточных 
накапливаемых данных. В итоге параметр collector представляет функцию преобразования потока в коллекцию.

## Разница между Collection и Stream
Разница между коллекцией(Collection) данных и потоком(Stream) из новой JDK8 в том что коллекции позволяют работать 
с элементами по-отдельности, тогда как поток(Stream) не позволяет. Например, с использованием коллекций, вы можете 
добавлять элементы, удалять, и вставлять в середину. Поток(Stream) не позволяет манипулировать отдельными элементами 
из набора данных, но вместо этого позволяет выполнять функции над данными как одним целом.

## Stream forEach
Метод `void forEach(Consumer<? super T> action)` - для каждого элемента выполняется действие action. forEach является 
терминальной операцией, используется для перебора каждого элемента потока.  
`forEach` не гарантирует последовательности вывода элементов в параллельном stream-е, для этого используется 
метод `forEachOrdered` - он это гарантирует.

## Random 10 numvber Stream foreach
```java
  Random random = new Random();
  random.ints().limit(10).forEach(System.out::println);
```

## Stream map
Метод `<R> Stream<R> map(Function<? super T,? extends R> mapper)` - преобразует элементы типа T в элементы типа R и 
возвращает поток с элементами R. map является промежуточной операцией, метод используется для преобразования 
элементов в другой объект при помощи переданной функции.

Квадраты всех чисел:  
```java
List<Integer> numbers = Arrays.asList(3, 2, 6, 4, 8, 2, 3, 4, 5);
List<Integer> squaresList = numbers.stream().map(i -> i * i).collect(Collectors.toList());
```
Уникальные квадраты чисел:  
```java
List<Integer> numbers = Arrays.asList(3, 2, 6, 4, 8, 2, 3, 4, 5);
List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
```

## Stream filter
Метод `Stream<T> filter(Predicate<? super T> predicate)` - фильтрует элементы в соответствии с условием в предикате. 
filter является промежуточной операцией, используется для фильтрации массива через функцию.  
На самом деле filter, не выполняет фильтрацию, вместо этого он создается новый поток, который по завершению 
формирования, содержит элементы исходного потока, которые соответствуют заданному предикату.   
количество пустых строк с помощью метода filter:   
```java
List<String> stringList = Arrays.asList("asd", "", "sd2", "", "qwe");
long count = stringList.stream().filter(String::isEmpty).count();
```

## Stream sorted
Метод `Stream<T> sorted()/Stream<T> sorted(Comparator<? super T> comparator)` - возвращает отсортированный поток. 
sorted является промежуточной операцией, метод используется для сортировки потока используя естественный порядок 
сравнения его элементов. Существует также второй метод sorted(), принимающий либо экземпляр Comparable, 
либо соответствующее ему лямбда-выражение.
```java
Random random = new Random();
random.ints().limit(10).sorted().forEach(System.out::println);
```

## Max
```java
List<Integer> integerList = Arrays.asList(3, 2, 5, 7, 10, 23, 34);
IntSummaryStatistics intSummaryStatistics = integerList.stream().mapToInt(x -> x).summaryStatistics();
System.out.println(intSummaryStatistics.getMax());
```

## Min
```java
List<Integer> integerList = Arrays.asList(3, 2, 5, 7, 10, 23, 34);
IntSummaryStatistics intSummaryStatistics = integerList.stream().mapToInt(x -> x).summaryStatistics();
System.out.println(intSummaryStatistics.getMin());
```

## Average
```java
List<Integer> integerList = Arrays.asList(3, 2, 5, 7, 10, 23, 34);
IntSummaryStatistics intSummaryStatistics = integerList.stream().mapToInt(x -> x).summaryStatistics();
System.out.println(intSummaryStatistics.getAverage());
```

## Sum
```java
List<Integer> integerList = Arrays.asList(3, 2, 5, 7, 10, 23, 34);
IntSummaryStatistics intSummaryStatistics = integerList.stream().mapToInt(x -> x).summaryStatistics();
System.out.println(intSummaryStatistics.getSum());
```