# Interface SortedMap

- [SortedMap](#SortedMap)
- [](#)

## SortedMap
SortedMap добавляет ряд методов:  
- `K firstKey()`: возвращает ключ первого элемента отображения
- `K lastKey()`: возвращает ключ последнего элемента отображения
- `SortedMap<K, V> headMap(K end)`: возвращает отображение SortedMap, которые содержит все элементы оригинального 
    SortedMap вплоть до элемента с ключом end
- `SortedMap<K, V> tailMap(K start)`: возвращает отображение SortedMap, которые содержит все элементы оригинального 
    SortedMap, начиная с элемента с ключом start
- `SortedMap<K, V> subMap(K start, K end)`: возвращает отображение SortedMap, которые содержит все элементы 
    оригинального SortedMap вплоть от элемента с ключом start до элемента с ключом end