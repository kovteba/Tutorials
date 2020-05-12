# Byte 

- `8 bit.	min: -128 max: 127`   

Класс __Byte__ является оболочкой для byte.
```java
byte x = Byte.parseByte("100");
```
Он лучше всего подходит для хранения произвольного потока байтов, загружаемого из сети или из файла.  
Если речь не идет о манипуляциях с битами, использования типа byte, как правило, следует избегать.  
Для нормальных целых чисел, используемых в качестве счетчиков и в арифметических выражениях, гораздо лучше подходит тип int.

Слово «байт» (byte) возникло в компании IBM примерно в 1956 году. Оно произошло от слова bite («кусок»), но его было 
решено писать через букву y, чтобы не путать со словом «bit» («бит»). В течение некоторого времени слово «байт» 
обозначало просто число битов в конкретном потоке данных. Однако в середине 1960-х, в связи с разработкой семейства 
компьютеров System/360 в компании IBM, это слово стало обозначать группу из восьми бит.