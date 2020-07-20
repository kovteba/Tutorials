# Interface NavigableSet

- [NavigableSet](#NavigableSet)

## NavigableSet
Интерфейс NavigableSet расширяет интерфейс SortedSet и позволяет извлекать элементы на основании их значений. 
NavigableSet определяет следующие методы:   
- `E ceiling(E obj)`: ищет в наборе наименьший элемент e, который больше obj (e >=obj). Если такой элемент найден, 
    то он возвращается в качестве результата. Иначе возвращается null.
- `E floor(E obj)`: ищет в наборе наибольший элемент e, который меньше элемента obj (e <=obj). Если такой элемент 
    найден, то он возвращается в качестве результата. Иначе возвращается null.
- `E higher(E obj)`: ищет в наборе наименьший элемент e, который больше элемента obj (e >obj). Если такой элемент 
    найден, то он возвращается в качестве результата. Иначе возвращается null.
- `E lower(E obj)`: ищет в наборе наибольший элемент e, который меньше элемента obj (e <obj). Если такой элемент 
    найден, то он возвращается в качестве результата. Иначе возвращается null.
- `E pollFirst()`: возвращает первый элемент и удаляет его из набора
- `E pollLast()`: возвращает последний элемент и удаляет его из набора
- `NavigableSet<E> descendingSet()`: возвращает объект NavigableSet, который содержит все элементы первичного 
    набора NavigableSet в обратном порядке
- `NavigableSet<E> headSet(E upperBound, boolean incl)`: возвращает объект NavigableSet, который содержит все 
    элементы первичного набора NavigableSet до upperBound. Параметр incl при значении true, позволяет включить 
    в выходной набор элемент upperBound
- `NavigableSet<E> tailSet(E lowerBound, boolean incl)`: возвращает объект NavigableSet, который содержит все 
    элементы первичного набора NavigableSet, начиная с lowerBound. Параметр incl при значении true, позволяет 
    включить в выходной набор элемент lowerBound
- `NavigableSet<E> subSet(E lowerBound, boolean lowerIncl, E upperBound, boolean highIncl)`: возвращает объект 
    NavigableSet, который содержит все элементы первичного набора NavigableSet от lowerBound до upperBound.