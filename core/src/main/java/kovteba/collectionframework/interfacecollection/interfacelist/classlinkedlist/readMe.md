## Class LinkedList

- [LinkedList](#LinkedList)
- [Конструкторы](#Конструкторы)
- [Methods](#Methods)
- [](#)

## LinkedList
- Из LinkedList можно организовать стэк, очередь, или двойную очередь, со временем доступа O(1);    
- На вставку и удаление из середины списка, получение элемента по индексу или значению потребуется линейное время O(n).   
- Однако, на добавление и удаление из середины списка, используя `ListIterator.add()` и `ListIterator.remove()`, потребуется O(1);  
- Позволяет добавлять любые значения в том числе и `null`. Для хранения примитивных типов использует соответствующие классы-оберки;  
- Не синхронизирован.  
- Быстрый доступ к элементам по индексу за время O(n), придётся последовательно перебрать все элементы, пока не 
    доберёмся до нужного элемента;  
- В LinkedList вставка заключается в создании нового связующего объекта и установки ссылок на него у соседних 
    элементов. Сложность __O(1)__.
    
```
get(index)        |   O(n)
add(E)            |   O(1)	
add(E, index)     |   O(n)	
remove(index)     |   O(n)	
Iterator.remove() |   O(n)
Iterator.add(E)   |   O(1)
```

Особенностью реализации данной коллекции является то, что в её основе лежит двунаправленный связный список 
(каждый элемент имеет ссылку на предыдущий и следующий).   
Благодаря этому, добавление и удаление из середины, доступ по индексу, значению происходит за линейное время `O(n)`, 
а из начала и конца за константное `O(1)`.    
Так же, ввиду реализации, данную коллекцию можно использовать как стек или очередь. Для этого в ней реализованы 
соответсвующие методы.
```java
List<String> list = new LinkedList<String>();
```
Только что созданный объект list, содержит свойства `header` и `size`.  
`header` — псевдо-элемент списка. Его значение всегда равно `null`, a свойства `next` и `prev` всегда указывают на 
первый и последний элемент списка соответственно. Так как на данный момент список еще пуст, свойства `next` и `prev` 
указывают сами на себя (т.е. на элемент `header`). Размер списка `size = 0`.

__Добавление элемента:__
- в конец списка с помощью методом `add(value)`, `addLast(value)`за время `O(1)`.
- добавление в начало списка с помощью `addFirst(value)` выполняется за время `O(1)`.
- в середину списка `add(index, value)` за время `O(n)`.

__Удалять элементы:__
- из начала или конца списка с помощью `removeFirst()`, `removeLast()` за время `O(1)`;
- по индексу `remove(index)` и по значению `remove(value)` за время `O(n)`.

>Внутри метода `remove(value)` просматриваются все элементы списка в поисках нужного. Удален будет лишь __первый__ найденный элемент.

Внутри класса `LinkedList` существует `static inner class Entry`, с помощью которого создаются новые элементы.

```java
private static class Entry<E>
{
    E element;
    Entry<E> next;
    Entry<E> prev;
	
    Entry(E element, Entry<E> next, Entry<E> prev)
    {
        this.element = element;
        this.next = next;
        this.prev = prev;
    }
}
```

## Конструкторы
- `LinkedList()`: создает пустой список
- `LinkedList(Collection<? extends E> col)`: создает список, в который добавляет все элементы коллекции col

## Methods
- `addFirst() / offerFirst()`: добавляет элемент в начало списка
- `addLast() / offerLast()`: добавляет элемент в конец списка
- `removeFirst() / pollFirst()`: удаляет первый элемент из начала списка
- `removeLast() / pollLast()`: удаляет последний элемент из конца списка
- `getFirst() / peekFirst()`: получает первый элемент
- `getLast() / peekLast()`: получает последний элемент