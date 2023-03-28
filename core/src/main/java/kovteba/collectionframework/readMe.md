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

+ [Iterator](#Iterator)
+ [ListIterator](#ListIterator)
+ [Fail fast](#Fail-fast)
+ [Fail safe](#Fail-safe)
+ [Enumeration и Iterator](#Enumeration-и-Iterator)
+ [](#)





    




## Итератор по коллекции, его свойства и интерфейс
- Может только один раз проходить по коллекции. Для прохождения в двух направлениях есть `ListIterator`
- Если в foreach цикле структурно модифицировать коллекцию, при последующем обращению к элементу (неявно через итератор) 
    получим `ConcurrentModificationException` (fail-fast)
- `hasNext()`, `next()` — основные методы



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







