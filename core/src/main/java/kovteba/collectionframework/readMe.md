# Java Collection Framework API

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
        + [Interface SortedSet](interfacecollection/interfaceset/interfacesortedset)
            + [Interface NavigableSet](interfacecollection/interfaceset/interfacesortedset/interfacenavigableset)
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
    + [Interface SortedMap](interfacemap/interfacesortedmap)
        + [Interface NavigableMap](interfacemap/interfacesortedmap/interfacenavigablemap)
---
+ [Java Collection Framework](#Java-Collection-Framework)
+ [Интерфейс Collection расширяют интерфейсы:](#Интерфейс-Collection-расширяют-интерфейсы)
+ [Interface Map реализован классами](#Interface-Map-реализован-классами)
+ [Итератор по коллекции, его свойства и интерфейс ](#Итератор-по-коллекции,-его-свойства-и-интерфейс)
+ [Сложность алгоритмов](#Сложность-алгоритмов)
+ [Iterator](#Iterator)
+ [ListIterator](#ListIterator)
+ [Fail fast](#Fail-fast)
+ [Fail safe](#Fail-safe)
+ [Enumeration и Iterator](#Enumeration-и-Iterator)
+ [](#)

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

## Интерфейс Collection расширяют интерфейсы
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
    
- List (список) представляет собой коллекцию, в которой допустимы дублирующие значения. Элементы такой коллекции 
    пронумерованы, начиная от нуля, к ним можно обратиться по индексу. Реализации:
    - ArrayList - инкапсулирует в себе обычный массив, длина которого автоматически увеличивается при добавлении 
        новых элементов.
    - LinkedList (двунаправленный связный список) - состоит из узлов, каждый из которых содержит как собственно 
        данные, так и две ссылки на следующий и предыдущий узел.
    - Vector — реализация динамического массива объектов, методы которой синхронизированы.
    - Stack — реализация стека LIFO (last-in-first-out).
- Set (сет) описывает неупорядоченную коллекцию, не содержащую повторяющихся элементов. Реализации:
    - HashSet - использует HashMap для хранения данных. В качестве ключа и значения используется добавляемый элемент. 
        Из-за особенностей реализации порядок элементов не гарантируется при добавлении.
    - LinkedHashSet — гарантирует, что порядок элементов при обходе коллекции будет идентичен порядку 
        добавления элементов.
    - TreeSet — предоставляет возможность управлять порядком элементов в коллекции при помощи объекта Comparator, 
        либо сохраняет элементы с использованием «natural ordering».
- Queue (очередь) предназначена для хранения элементов с предопределённым способом вставки и извлечения FIFO (first-in-first-out):
    - PriorityQueue — предоставляет возможность управлять порядком элементов в коллекции при помощи объекта 
        Comparator, либо сохраняет элементы с использованием «natural ordering».
    - ArrayDeque — реализация интерфейса Deque, который расширяет интерфейс Queue методами, позволяющими 
        реализовать конструкцию вида LIFO (last-in-first-out).
    
## Interface Map реализован классами
+ __Hashtable__
+ __HashMap__
+ __LinkedHashMap__
+ __TreeMap__ 
+ __WeakHashMap__

- Hashtable — хэш-таблица, методы которой синхронизированы. Не позволяет использовать null в качестве значения 
    или ключа и не является упорядоченной.
- HashMap — хэш-таблица. Позволяет использовать null в качестве значения или ключа и не является упорядоченной.
- LinkedHashMap — упорядоченная реализация хэш-таблицы.
- TreeMap — реализация основанная на красно-чёрных деревьях. Является упорядоченной и предоставляет возможность 
    управлять порядком элементов в коллекции при помощи объекта Comparator, либо сохраняет элементы с 
    использованием «natural ordering».
- WeakHashMap — реализация хэш-таблицы, которая организована с использованием weak references для ключей 
    (сборщик мусора автоматически удалит элемент из коллекции при следующей сборке мусора, если на ключ 
    этого элемента нет жёстких ссылок).

## Итератор по коллекции, его свойства и интерфейс
- Может только один раз проходить по коллекции. Для прохождения в двух направлениях есть `ListIterator`
- Если в foreach цикле структурно модифицировать коллекцию, при последующем обращению к элементу (неявно через итератор) 
    получим `ConcurrentModificationException` (fail-fast)
- `hasNext()`, `next()` — основные методы

## Сложность алгоритмов
Функции перечислены в порядке возрастания сложности.
1. __C__ – константа: O(1)
2. __log(log(N))__:
3. __log(N)__:
4. __N^C, 0<C<1)__:
5. __N:__ O(n)
6. __N*log(N)__:
7. __N^C, C>1__: O(n^2)
8. __C^N, C>1__: O(2^n)
9. __N!__:

## Iterator
Интерфейс Collection имеет метод `iterator()`, возвращающий объект-итератор, который используется для последовательного 
обращения к элементам набора данных. Этот итератор реализует интерфейс Iterator, который включает методы `next()`, 
`hasNext()` и `remove()`.

C помощью вызова метода `next()` можно получить следующий элемент в наборе. Метод `hasNext()` позволяет проверить 
наличие следующего элемента. Данные методы следует использовать совместно при организации цикла перебора элементов 
коллекции. Метод `remove()` удаляет текущий элемент, который был получен при последнем вызове `next()`.

__Примечание :__   
1. Если вызвать метод `next()` итератора, указывающего на последний элемент в коллекции, то возникнет исключение 
    `NoSuchElementException`. Следует об этом помнить и использовать метод `hasNext()` перед методом `next()`.
2. Обычные коллекции не потокобезопасные. Поэтому, если два итератора в разных потоках работают с одной и той же 
    коллекцией, то изменение содержимого коллекции в одном потоке будет сопровождаться вызовом исключения 
    `ConcurrentModificationException` в другом потоке при вызове итератора одного из его методов. Чтобы избежать 
    этого, следует использовать потокобезопасные concurrent коллекции.

__Порядок следования элементов в итераторе__    
Порядок получения элементов коллекции при переборе итератором зависит от типа и набора элементов. Если используется 
ArrayList, то итератор начинает с нулевого индекса и инкрементирует индекс на каждом шаге. Если объект имеет тип 
HashSet, то порядок следования элементов коллекции может оказаться случайным. При использовании Iterator'a, можно 
быть уверенным, что он переберет все элементы, но порядок элементов может быть случайным.

Метод итератора `remove()` следует вызывать после вызова метода `next()`, иначе будет вызвано исключение 
`IllegalStateException`. Метод `remove()` удаляет элемент, на который указывает итератор. Нельзя вызывать метод 
`remove()` два раза подряд для удаления элементов. Необходимо после первого удаления повторно вызвать метод `next()`.

## ListIterator
- `ListIterator` расширяет интерфейс `Iterator`
- `ListIterator` может быть использован только для перебора элементов коллекции `List`;
- `Iterator` позволяет перебирать элементы только в одном направлении, при помощи метода `next()`. Тогда как `
    ListIterator` позволяет перебирать список в обоих направлениях, при помощи методов `next()` и `previous()`;
- `ListIterator` не указывает на конкретный элемент: его текущая позиция располагается между элементами, которые
    возвращают методы `previous()` и `next()`.
- При помощи `ListIterator` вы можете модифицировать список, добавляя/удаляя элементы с помощью методов `add()` и 
    `remove()`. `Iterator` не поддерживает данного функционала.

## Fail fast
__fail-fast__ поведение означает, что при возникновении ошибки или состояния, которое может привести к ошибке, 
система немедленно прекращает дальнейшую работу и уведомляет об этом. Использование fail-fast подхода позволяет 
избежать недетерминированного поведения программы в течение времени.

В Java Collections API некоторые итераторы ведут себя как fail-fast и выбрасывают ConcurrentModificationException, 
если после его создания была произведена модификация коллекции, т.е. добавлен или удален элемент напрямую из 
коллекции, а не используя методы итератора.

Реализация такого поведения осуществляется за счет подсчета количества модификаций коллекции (modification count):    
- при изменении коллекции счетчик модификаций так же изменяется;
- при создании итератора ему передается текущее значение счетчика;
- при каждом обращении к итератору сохраненное значение счетчика сравнивается с текущим, и, если они не 
    совпадают, возникает исключение

## Fail safe
В противоположность __fail-fast__, итераторы __fail-safe__ не вызывают никаких исключений при изменении структуры, 
потому что они работают с клоном коллекции вместо оригинала.   
Итератор коллекции `CopyOnWriteArrayList` и итератор представления `keySet` коллекции `ConcurrentHashMap` являются 
примерами итераторов __fail-safe__.

## Enumeration и Iterator
Хотя оба интерфейса и предназначены для обхода коллекций между ними имеются существенные различия:

- с помощью Enumeration нельзя добавлять/удалять элементы;
- в Iterator исправлены имена методов для повышения читаемости кода (`Enumeration.hasMoreElements()` соответствует 
    `Iterator.hasNext()`, `Enumeration.nextElement()` соответствует `Iterator.next()` и т.д);
- Enumeration присутствуют в устаревших классах, таких как Vector/Stack, тогда как Iterator есть во всех 
    современных классах-коллекциях.







