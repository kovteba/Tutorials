# Interface NavigableMap

- [NavigableMap](#NavigableMap)
- [](#)

## NavigableMap
Интерфейс NavigableMap расширяет интерфейс SortedMap и обеспечивает возможность получения элементов отображения 
относительно других элементов. Его основные методы:   
- `Map.Entry<K, V> ceilingEntry(K obj)`: возвращает элемент с наименьшим ключом k, который больше или равен ключу 
    obj (k >=obj). Если такого ключа нет, то возвращается null.
- `Map.Entry<K, V> floorEntry(K obj)`: возвращает элемент с наибольшим ключом k, который меньше или равен ключу 
    obj (k <=obj). Если такого ключа нет, то возвращается null.
- `Map.Entry<K, V> higherEntry()`: возвращает элемент с наименьшим ключом k, который больше ключа obj (k >obj). 
    Если такого ключа нет, то возвращается null.
- `Map.Entry<K, V> lowerEntry()`: возвращает элемент с наибольшим ключом k, который меньше ключа obj (k <obj). 
    Если такого ключа нет, то возвращается null.
- `Map.Entry<K, V> firstEntry()`: возвращает первый элемент отображения
- `Map.Entry<K, V> lastEntry()`: возвращает последний элемент отображения
- `Map.Entry<K, V> pollFirstEntry()`: возвращает и одновременно удаляет первый элемент из отображения
- `Map.Entry<K, V> pollLastEntry()`: возвращает и одновременно удаляет последний элемент из отображения
- `K ceilingKey(K obj)`: возвращает наименьший ключ k, который больше или равен ключу obj (k >=obj). Если такого 
    ключа нет, то возвращается null.
- `K floorKey(K obj)`: возвращает наибольший ключ k, который меньше или равен ключу obj (k <=obj). Если такого 
    ключа нет, то возвращается null.
- `K lowerKey(K obj)`: возвращает наибольший ключ k, который меньше ключа obj (k <obj). Если такого ключа нет, 
    то возвращается null.
- `K higherKey(K obj)`: возвращает наименьший ключ k, который больше ключа obj (k >obj). Если такого ключа нет, 
    то возвращается null.
- `NavigableSet<K> descendingKeySet()`: возвращает объект NavigableSet, который содержит все ключи отображения 
    в обратном порядке
- `NavigableMap<K, V> descendingMap()`: возвращает отображение NavigableMap, которое содержит все элементы 
    в обратном порядке
- `NavigableSet<K> navigableKeySet()`: возвращает объект NavigableSet, который содержит все ключи отображения
- `NavigableMap<K, V> headMap(K upperBound, boolean incl)`: возвращает отображение NavigableMap, которое содержит 
    все элементы оригинального NavigableMap вплоть от элемента с ключом upperBound. Параметр incl при значении 
    true указывает, что элемент с ключом upperBound также включается в выходной набор.
- `NavigableMap<K, V> tailMap(K lowerBound, boolean incl)`: возвращает отображение NavigableMap, которое содержит 
    все элементы оригинального NavigableMap, начиная с элемента с ключом lowerBound. Параметр incl при значении 
    true указывает, что элемент с ключом lowerBound также включается в выходной набор.
- `NavigableMap<K, V> subMap(K lowerBound, boolean lowIncl, K upperBound, boolean highIncl)`: возвращает отображение 
    NavigableMap, которое содержит все элементы оригинального NavigableMap от элемента с ключом lowerBound до 
    элемента с ключом upperBound. Параметры lowIncl и highIncl при значении true включают в выходной набор 
    элементы с ключами lowerBound и upperBound соответственно.
