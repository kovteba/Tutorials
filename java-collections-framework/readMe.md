+ [Interface Collection](src/main/java/kovteba/interfacecollection)
    + [Interface List](src/main/java/kovteba/interfacecollection/interfacelist)
        + [Class ArrayList](src/main/java/kovteba/interfacecollection/interfacelist/classarraylist)
        + [Class LinkedList](src/main/java/kovteba/interfacecollection/interfacelist/classlinkedlist)
        + [Class Stack](src/main/java/kovteba/interfacecollection/interfacelist/classstack)
        + [Class Vector](src/main/java/kovteba/interfacecollection/interfacelist/classvector)
    + [Interface Set](src/main/java/kovteba/interfacecollection/interfaceset)
        + [Class HashSet](src/main/java/kovteba/interfacecollection/interfaceset/classhashset)
        + [Class LinkedHashSet](src/main/java/kovteba/interfacecollection/interfaceset/classlinkedhashset)
        + [Class TreeSet](src/main/java/kovteba/interfacecollection/interfaceset/classtreeset)
    + [Interface Queue](src/main/java/kovteba/interfacecollection/interfacequeue)
        + [Class ArrayDeque](src/main/java/kovteba/interfacecollection/interfacequeue/classarraydeque)
        + [Class PriorityQueue](src/main/java/kovteba/interfacecollection/interfacequeue/classpriorityqueue)
+ [Interface Map](src/main/java/kovteba/interfacemap)
        
        
# Java Collection Framework
- __java.util.Collections__ - набор статических методов для работы с коллекциями.
- __java.util.Collection__ - один из основных интерфейсов Java Collections Framework.

На ввершине иерархи __Iterable__

На вершине иерархии в Java Collection Framework располагаются 2 интерфейса: 
1. __Collection__ - простые последовательные наборы элементов. 
2. __Map__ - наборы пар «ключ — значение».

#### Interface Collection
Этот интерфейс находится в составе `JDK 1.2` и определяет основные методы работы с простыми наборами элементов, 
которые будут общими для всех его реализаций (например `size()`, `isEmpty()`, `add(E e)` и др.). Интерфейс был слегка 
доработан с приходом дженериков в `Java 1.5`. Так же в версии `Java 8` было добавлено несколько новых метода для 
работы с лямбдами (такие как `stream()`, `parallelStream()`, `removeIf(Predicate<? super E> filter)` и др.).

#### Interface Map
Данный интерфейс также находится в составе `JDK 1.2` и предоставляет разработчику базовые методы для работы с 
данными вида «ключ — значение». Также как и Collection, он был дополнен дженериками в версии `Java 1.5`и 
в версии `Java 8` появились дополнительные методы для работы с лямбдами, а также методы, которые зачастую 
реализовались в логике приложения (`getOrDefault(Object key, V defaultValue)`, `putIfAbsent(K key, V value)`).

![BigONotation](img/BigONotation.png)

## **Интерфейс "Collection" расширяют интерфейсы:**
+ __Interface List__ Реализации:
    + __ArrayList__
    + __LinkedList__
    + __Vector__
    + __Stack__ 
    
+ __Interface Set__ Реализации:
    + __HashSet__
    + __LinkedHashSet__ 
    + __TreeSet__

+ __Interface Queue__ Реализации:
    + __PriorityQueue__ 
    + __ArrayDeque__ 
    
## Interface "Map" реализован классами:
+ __Hashtable__
+ __HashMap__
+ __LinkedHashMap__
+ __TreeMap__ 
+ __WeakHashMap__
    





### Class Hashtable 
Реализация такой структуры данных, как хэш-таблица. Она не позволяет использовать `null` в качестве значения или 
ключа и не является упорядоченной. Эта коллекция была реализована раньше, чем Java Collection Framework, 
но в последствии была включена в его состав. Как и другие коллекции из Java 1.0, Hashtable является с
инхронизированной (почти все методы помечены как synchronized). Из-за этой особенности у неё имеются существенные 
проблемы с производительностью и, начиная с Java 1.2, в большинстве случаев рекомендуется использовать другие 
реализации интерфейса Map ввиду отсутствия у них синхронизации.

### Class HashMap
Коллекция является альтернативой Hashtable. Двумя основными отличиями от Hashtable являются то, 
что HashMap не синхронизирована и HashMap позволяет использовать `null` как в качестве ключа, так и значения. 
Так же как и Hashtable, данная коллекция не является упорядоченной: порядок хранения элементов зависит от хэш-функции. 
Добавление элемента выполняется за константное время `O(1)`, но время удаления, получения 
зависит от распределения хэш-функции. В идеале является константным, но может быть и линейным `O(n)`.

### Class LinkedHashMap
Это упорядоченная реализация хэш-таблицы. Здесь, в отличии от HashMap, порядок итерирования равен порядку добавления 
элементов. Данная особенность достигается благодаря двунаправленным связям между элементами (аналогично LinkedList). 
Но это преимущество имеет также и недостаток — увеличение памяти, которое занимет коллекция. 
Более подробная информация изложена в этой статье.

### Class TreeMap
реализация Map основанная на красно-чёрных деревьях. Как и LinkedHashMap является упорядоченной. 
По-умолчанию, коллекция сортируется по ключам с использованием принципа `"natural ordering"`, но это поведение 
может быть настроено под конкретную задачу при помощи объекта Comparator, которые указывается в качестве параметра 
при создании объекта TreeMap.

### Class WeakHashMap
Реализация хэш-таблицы, которая организована с использованием weak references. Другими словами, `Garbage Collector` 
автоматически удалит элемент из коллекции при следующей сборке мусора, если на ключ этого элеметна нет жёстких ссылок.