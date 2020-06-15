# Reflection Examples

- [Свойства класса](#Свойства-класса)
- [Определение интерфейсов и конструкторов класса](#Определение-интерфейсов-и-конструкторов-класса)
- [Определение полей класса](#Определение-полей-класса)
- [Определение методов класса](#Определение-методов-класса)
- [Вызов метода класса](#Вызов-метода-класса)

## Свойства класса
Для получения класса используется метод `forName()`   
```java
// Без использования Reflection
Foo foo = new Foo();
// С использованием Reflection
Class foo = Class.forName("Foo");
```
Наименование класса, включающего пакет (package), извлекается методом `getName()` объекта Class :  
```java
Class aclass = foo.getClass(); 
System.out.println (aclass.getName());
```  
Для получения значения модификатора класса используется метод `getModifiers()`. Класс `java.lang.reflect.Modifier` 
содержит статические методы, возвращающие логическое значения проверки модификатора класса :   
```java
Class cls = foo.getClass(); 
int mods = cls.getModifiers(); 
if (Modifier.isPublic  (mods))	{ System.out.println("public");  }
if (Modifier.isAbstract(mods))	{ System.out.println("abstract");}
if (Modifier.isFinal   (mods))	{ System.out.println("final");   }
```
Для получения суперкласса рефлексированного объекта (класса) необходимо использовать метод `getSuperclass()` :   
```java
Class cls      = foo.getClass(); 
Class superCls = cls.getSuperClass(); 
```
Поскольку в Java отсутствет множественное наследование, то для получения всех предков следует рекурсивно вызвать 
метод `getSuperclass()` в цикле, пока не будет достигнут `Object`, являющийся родителем всех классов. `Object` 
не имеет родителей, поэтому вызов его метода `getSuperclass()` вернет `null`.

## Определение интерфейсов и конструкторов класса
Для получения в режиме run-time списка интерфейсов, реализующих классом, необходимо получить Class и использовать 
его метод `getInterfaces()`. Следующий пример демонстрирует получение списка интерфейсов класса ArrayList :
```java
Class<?>   cls = ArrayList.class; 
Class<?>[] ifs = cls.getInterfaces(); 

System.out.println("List of interfaces\n"); 
for(Class<?> ifc : ifs) { 
    System.out.println (ifc.getName()); 
}
```
OutPut:   
        List of interfaces  
        java.util.List  
        java.util.RandomAccess  
        java.lang.Cloneable  
        java.io.Serializable  
        
Метод класса `getConstructors()` позволяет получить массив открытых конструкторов типа `java.lang.reflect.Constructor`. 
После этого, можно извлекать информацию о типах параметров конструктора и генерируемых исключениях. Пример :
```java
Class<?> cls = obj.getClass(); 
Constructor[] constructors = cls.getConstructors(); 
for (Constructor constructor : constructors) { 
    Class<?>[] params = constructor.getParameterTypes(); 
    for (Class<?> param : params) { 
        System.out.println(param.getName()); 
    } 
} 
```

## Определение полей класса
Метод `getFields()` объекта Class возвращает массив открытых полей типа `java.lang.reflect.Field`. Эти поля могут 
быть определены не только в классе, но также и в его родителях (суперклассе), либо интерфейсах, реализованных 
классом или его родителями. Класс Field позволяет получить имя поля, тип и модификаторы.
```java
Class<?> cls = obj.getClass(); 
Field[] fields = cls.getFields(); 
for (Field field : fields) { 
    Class<?> fld = field.getType(); 
    System.out.println("Class name : " + field.getName()); 
    System.out.println("Class type : " + fld.getName()); 
}
```
Если известно наименование поля, то можно получить о нем информацию с помощью метода `getField()` объекта Class.
```java
Class<?> cls = obj.getClass();
Field fld = cls.getField("fieldName"); 
```
Методы `getField()` и `getFields()` возвращают только открытые члены данных класса. Чтобы получить все поля класса 
необходимо использовать методы `getDeclaredField()` и `getDeclaredFields()`. Данные методы работают точно также, 
как и их аналоги `getField()` и `getFields()`, за исключением того, что они возвращают все поля, включая закрытые 
и защищенные.

__Определение значение полей класса__   

Класс `Field` содержит специализированные методы для получения значений примитивных типов: `getInt()`, `getFloat()`, 
`getByte()` и др. Для установки значения поля, используется метод `set()`. Для примитивных типов имеются методы 
`setInt()`, `setFloat()`, `setByte()` и др.
```java
Class<?> cls = obj.getClass();
Field field = cls.getField("fieldName"); 

String value = (String) field.get(obj);
field.set(obj, "New value");
```

## Определение методов класса
Метод `getMethods()` объекта Class возвращает массив открытых методов типа `java.lang.reflect.Method`. Эти методы 
могут быть определены не только в классе, но также и в его родителях (суперклассе), либо интерфейсах, реализованных 
классом или его родителями. Класс `Method` позволяет получить имя метода, тип возвращаемого им значения, типы 
параметров метода, модификаторы и генерируемые исключения.
```java
Class<?> cls = obj.getClass(); 
Method[] methods = cls.getMethods(); 
for (Method method : methods) { 
    System.out.println("Method name : " + method.getName()); 
    System.out.println("Return type : " + 
                          method.getReturnType().getName());
 
    Class<?>[] params = method.getParameterTypes(); 
    System.out.print("Parameters : "); 
    for (Class<?> paramType : params) { 
        System.out.print(" " + paramType.getName()); 
    } 
    System.out.println(); 
} 
```
Если известно имя метода и типы его параметров, то можно получить отдельный метод класса :
```java
Class<?> cls = obj.getClass(); 
Class[] params = new Class[] {Integer.class, String.class};

Method method = cls.getMethod("methodName", params); 
```

## Вызов метода класса
Допустим, что имеется класс Reflect, включающий два закрытых поля id и name и несколько методов для определения 
значений этих полей. Нас будут интересовать только метод setData для определения значений и метод toString для 
печати значений.
```java
class Reflect
{
    private String name;
    private int    id;
	
    public void setData(final int id, String name) {
        this.id   = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Reflect [ id : " + id + ", name : " + name + "]";
    }
}
```
Сначала получаем метод класса. Для этого формируем массив типов параметров метода и вызываем getMethod с 
наименованием метода класса и списком его параметров params. После этого формируем массив новых значение 
полей класса args и вызываем метод invoke объекта Method с указанием объекта класса и аргументами. В 
заключение получаем ссылку на метод toString и распечатываем значения.
```java
Class<?> cls = reflect.getClass(); 

Class<?>[] params = new Class[] {int.class, String.class};
Method method = cls.getMethod("setData", params);

Object[] args = new Object[] {(int) 123, new String("New value")};
method.invoke(reflect, args);

System.out.println("invoke method toString()\n");

method = cls.getMethod("toString");
System.out.println(method.invoke(reflect));
```