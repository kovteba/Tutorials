## Class ArrayDeque

- [ArrayDeque](#ArrayDeque)
- [Конструкторы](#Конструкторы)
- [](#)

## ArrayDeque
- реализация `Interface Deque`, который расширяет `Interface Queue` методами, позволяющими реализовать конструкцию 
    вида __LIFO (last-in-first-out)__.
    
## Конструкторы
- `ArrayDeque()`: создает пустую очередь
- `ArrayDeque(Collection<? extends E> col)`: создает очередь, наполненную элементами из коллекции col
- `ArrayDeque(int capacity)`: создает очередь с начальной емкостью capacity. Если мы явно не указываем начальную 
    емкость, то емкость по умолчанию будет равна 16