# Short
 
- `16 bit min: -32768 max: 32767`   

Класс __Short__ является оболочкой для short.         

```java
short x = Short.parseShort("100");
```                 
В арифметических выражениях с переменными типа short вычисления выполняются как с типом int, т.е. с помощью 32-битовой 
арифметики, а полученный результат будет 32-битовым. Например, такой код не пройдёт.
```java
// накорми кота
short fishNumber = 3; // три рыбки
short beefNumber = 2; // два кусочка говядины
short breakfast = 0;
breakfast = fishNumber + beefNumber; // завтрак чемпиона
```
Java будет ругаться на последнюю строчку, так как итоговый результат не может быть short. Как вариант, вам нужно 
преобразовать результат снова в 16-битовое число.
```java
breakfast = (short) (fishNumber + beefNumber); // завтрак чемпиона
```
