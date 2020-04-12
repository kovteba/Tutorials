## Exception
![](https://github.com/kovteba/Tutorials/blob/master/core/img/exception.png)
![](/home/dima/Documents/IT/Tutorials/core/img/exception.png)

__Исключение__ - это нештатная ситуация, ошибка во время выполнения программы. Самый простой пример - деление на ноль. 
Можно вручную отслеживать возникновение подобных ошибок, а можно воспользоваться специальным механизмом исключений, 
который упрощает создание больших надёжных программ, уменьшает объём необходимого кода и повышает уверенность в том, 
что в приложении не будет необработанной ошибки.  

Основными классами являются `Throwable`, `Error`, `Exception` и `RuntimeException`. 
Дело в том, что в java есть два типа исключений: _checked_ и _unchecked_.
1. _Checked_ исключения, это те, которые должны обрабатываться блоком `catch` или описываться в сигнатуре метода. 
    наследованные от `Exception` (не включая unchecked).
2. _Unchecked_ могут не обрабатываться и не быть описанными. _Unchecked_ наследованные от `RuntimeException`.

`Error` - это подкласс, который показывает серьезные проблемы возникающие во время выполнения приложения. 
Большинство из этих ошибок сигнализируют о ненормальном ходе выполнения программы, т.е. о каких-то критических 
проблемах. Эти ошибки не рекомендуется отмечать в методах посредством throws-объявления, поэтому они также очень 
часто называются не проверяемые _unchecked_.

При программировании на Java основное внимание следует уделять иерархии `Exception`. Эта иерархия также разделяется 
на две ветви: исключения, производные от класса `RuntimeException`, и остальные. Исключения типа `RuntimeException` 
возникают вследствие ошибок программирования. Все другие исключения являются следствием непредвиденного стечения 
обстоятельств, например, ошибок ввода-вывода, возникающих при выполнении вполне корректных программ.

__Непроверяемых исключений:__
+ ArithmeticException - арифметическая ошибка, например, деление на нуль
+ ArrayIndexOutOfBoundsException - выход индекса за границу массива
+ ArrayStoreException - присваивание элементу массива объекта несовместимого типа
+ ClassCastException - неверное приведение
+ EnumConstantNotPresentException - попытка использования неопределённого значения перечисления
+ IllegalArgumentException - неверный аргумент при вызове метода
+ IllegalMonitorStateException - неверная операция мониторинга
+ IllegalStateException - некорректное состояние приложения
+ IllegalThreadStateException - запрашиваемая операция несовместима с текущим потоком
+ IndexOutofBoundsException - тип индекса вышел за допустимые пределы
+ NegativeArraySizeException - создан массив отрицательного размера
+ NullPointerException - неверное использование пустой ссылки
+ NumberFormatException - неверное преобразование строки в числовой формат
+ SecurityException - попытка нарушения безопасности
+ StringIndexOutOfBounds - попытка использования индекса за пределами строки
+ TypeNotPresentException - тип не найден
+ UnsupportedOperationException - обнаружена неподдерживаемая операция

__Проверяемые исключения:__
+ ClassNotFoundException - класс не найден
+ CloneNotSupportedException - попытка клонировать объект, который не реализует интерфейс Cloneable
+ IllegalAccessException - запрещен доступ к классу
+ InstantiationException - попытка создать объект абстрактного класса или интерфейса
+ InterruptedException - поток прерван другим потоком
+ NoSuchFieldException - запрашиваемое поле не существует
+ NoSuchMethodException - запрашиваемый метод не существует
+ ReflectiveOperationException - исключение, связанное с рефлексией

__Синтаксис__   
В Java есть пять ключевых слов для работы с исключениями:  
+ `try` - данное ключевое слово используется для отметки начала блока кода, который потенциально может привести к ошибке. 
+ `catch` - ключевое слово для отметки начала блока кода, предназначенного для перехвата и обработки исключений.
+ `finally` - ключевое слово для отметки начала блока кода, которое является дополнительным. Этот блок помещается 
    после последнего блока `catch`. Управление обычно передаётся в блок `finally` в любом случае. 
+ `throw` - служит для генерации исключений.
+ `throws` - ключевое слово, которое прописывается в сигнатуре метода, и обозначающее что метод потенциально может 
    выбросить исключение с указанным типом.
    
```java
try{ 
//здесь код, который потенциально может привести к ошибке 
} 
catch(SomeException e ){ //в скобках указывается класс конкретной ожидаемой ошибки  
//здесь описываются действия, направленные на обработку исключений 
} 
finally{ 
//выполняется в любом случае ( блок finnaly не обязателен) 
} 
```

То есть если в вашем коде есть участок который может бросить checked исключение то вы обязаны либо заключить его в 
конструкцию `try/catch` либо объявить в сигнатуре метода "throws SomeException" но в данном случае обработка 
исключения делегируется на уровень выше. В любом случае его нужно будет перехватить. В противном случае 
программа просто не скомпилируется.

##__finally__

Иногда требуется гарантировать, что определенный участок кода будет выполняться независимо от того, какие исключения 
были возбуждены и перехвачены. Для создания такого участка кода используется ключевое слово `finally`. Даже в тех 
случаях, когда в методе нет соответствующего возбужденному исключению раздела catch, блок finally будет выполнен до 
того, как управление перейдет к операторам, следующим за разделом `try`. У каждого раздела `try` должен быть по 
крайней мере или один раздел `catch` или блок `finally`. Блок `finally` очень удобен для закрытия файлов и 
освобождения любых других ресурсов, захваченных для временного использования в начале выполнения метода. 

### Существуют ли ситуации, когда блок `finally` не будет выполнен?

+ принудительный системный выход из программы:
```java
try { 
    System.exit(0); 
} catch(Exception e) { 
    e.printStackTrace(); 
} finally { }
```
+ Если сначала произойдет сбой JVM;
+ Если JVM достигает бесконечного цикла (или другого не прерывающегося, не заканчивающегося оператора) в 
    блоке `try` или `catch`;
+ Если ОС принудительно завершает процесс JVM; например, "убить -9" в UNIX.
+ Если хост-система умирает; например, сбой питания, аппаратная ошибка, паника ОС и т.д.
+ Если, наконец, блок будет выполняться потоком демона, а все остальные не-демонные потоки выходят до того, 
    как, наконец, вызывается.
    
## Когда в приложении может быть выброшено исключение `ClassCastException`?
`ClassCastException` (потомок `RuntimeException`) - исключение, которое будет выброшено при ошибке приведения типа.

## Какое исключение выбрасывается при возникновении ошибки в блоке инициализации класса?
Если возникшее исключение - наследник `RuntimeException`: 
+ для статических блоков инициализации будет выброшено `java.lang.ExceptionInInitializerError`;
+ для нестатических будет проброшено исключение-источник.  

Если возникшее исключение - наследник `Error`, то в обоих случаях будет выброшено `java.lang.Error`. 
Исключение: `java.lang.ThreadDeath` - смерть потока. В этом случае никакое исключение выброшено не будет.