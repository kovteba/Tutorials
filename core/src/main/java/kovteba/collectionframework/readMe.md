# Java Collection Framework

+ [Interface Collection](interfacecollection)
    + [Interface List](interfacecollection/interfacelist)
        + [Class ArrayList](interfacecollection/interfacelist/classarraylist)
        + [Class LinkedList](interfacecollection/interfacelist/classlinkedlist)
        + [Class Stack](interfacecollection/interfacelist/classstack)
        + [Class Vector](interfacecollection/interfacelist/classvector)
    + [Interface Set](interfacecollection/interfaceset)
        + [Class HashSet](interfacecollection/interfaceset/classhashset)
        + [Class LinkedHashSet](interfacecollection/interfaceset/classlinkedhashset)
        + [Class TreeSet](interfacecollection/interfaceset/classtreeset)
    + [Interface Queue](interfacecollection/interfacequeue)
        + [Interface Deque](interfacecollection/interfacequeue/interfacedeque)
            + [Class ArrayDeque](interfacecollection/interfacequeue/interfacedeque/classarraydeque)
        + [Class PriorityQueue](interfacecollection/interfacequeue/classpriorityqueue)
+ [Interface Map](interfacemap)
    + [Class HashMap](interfacemap/classhashmap)
    + [Class HashTable](interfacemap/classhashtable)
    + [Class LinkedHashMap](interfacemap/classlinkedhashmap)
    + [Class TreeMap](interfacemap/classtreemap)
    + [Class WeakHashMap](interfacemap/classweakhashmap)

## Java Collection Framework

__Java Collection Framework__ — иерархия интерфейсов и их реализаций, которая является частью `JDK` и позволяет 
разработчику пользоваться большим количесвом структур данных из «коробки».        

- __java.util.Collections__ - набор статических методов для работы с коллекциями.
- __java.util.Collection__ - один из основных интерфейсов Java Collections Framework.

На ввершине иерархи __Iterable__

На вершине иерархии в Java Collection Framework располагаются 2 интерфейса: 
1. __Collection__ - простые последовательные наборы элементов. 
2. __Map__ - наборы пар «ключ — значение».

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

## Итератор по коллекции, его св-ва и интерфейс 
- Может только один раз проходить по коллекции. Для прохождения в двух направлениях есть `ListIterator`
- Если в foreach цикле структурно модифицировать коллекцию, при последующем обращению к элементу (неявно через итератор) 
    получим `ConcurrentModificationException` (fail-fast)
- `hasNext()`, `next()` — основные методы
    