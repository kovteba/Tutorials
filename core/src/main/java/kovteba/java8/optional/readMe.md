# Optional

- [Create Optional](#Create-Optional)
- [IfPresent()](#IfPresent())
- [IsPresent()](#IsPresent())
- [Семейство orElse()](#Семейство-orElse())
    - [OrElse()](#OrElse())
    - [OrElseGet()](#OrElseGet())
    - [](#)
- [Get() — возвращает объект, если он есть](#Get())
- [Map() — преобразовывает объект в другой объект](#Map())
- [Filter() — фильтрует содержащиеся объекты по предикату](#Filter())
- [Flatmap() — возвращает множество в виде стрима](#Flatmap())
- [](#)

## Create Optional
```java
//Пустой Optional объект
Optional<T> optionalPerson = Optional.empty();
	
//Optional объект с ненулевым значением
Optional<T> optionalNonNull = Optional.of(ob);
		
//Optional объект с возможностью нулевого значения
Optional<T> optionalNullable = Optional.ofNullable(ob);
```

## IfPresent()
Метод позволяет выполнить какое-то действие, если объект не пустой.   
```java
Optional.of(repository.findById(userId)).ifPresent(createLog());
```    
Если обычно мы выполняем какое-то действие в том случае, когда объект отсутствует, то здесь как раз наоборот.

## IsPresent()
Этот метод возвращает ответ, существует ли искомый объект или нет, в виде Boolean:    
```java
Boolean present = repository.findById(userId).isPresent();
```   
Если Вы решили использовать нижеописанный метод get(), то не будет лишним проверить, существует ли данный объект, 
при помощи этого метода, например:     
```java
Optional<User> optionalUser = repository.findById(userId);
User user = optionalUser.isPresent() ? optionalUser.get() : new User();
```   
Но такая конструкция лично мне кажется громоздкой, и о более удобных методах получения объекта мы поговорим ниже.

## Семейство orElse()
- orElse() — возвращает объект по дефолту.
- orElseGet() — вызывает указанный метод.
- orElseThrow() — выбрасывает исключение.

### OrElse()
Подходит для случаев, когда нам обязательно нужно получить объект, пусть даже и пустой. Код, в таком случае, 
может выглядеть так:    
```java
User user = repository.findById(userId).orElse(new User());
```    
Эта конструкция гарантированно вернёт нам объект класса User. Она очень выручает на начальных этапах познания 
Optional, а также, во многих случаях, связанных с использованием Spring Data JPA (там большинство классов 
семейства find возвращает именно Optional).

### OrElseGet()
Если объект не найден, Optional оставляет пространство для «Варианта Б» — Вы можете выполнить другой метод, например:   
```java
User user = repository.findById(userId).orElseGet(() -> findInAnotherPlace(userId));
```    
Если объект не был найден, предлагается поискать в другом месте.    
Этот метод, как и `orElseThrow()`, использует __Supplier__. Также, через этот метод можно, опять же, вызвать объект
 по умолчанию, как и в `.orElse()`:    
```java
User user = repository.findById(userId).orElseGet(() -> new User());
```
Помимо методов получения объектов, существует богатый инструментарий преобразования объекта, морально унаследованный 
от stream().

### OrElseThrow()
Очень часто, и опять же, в случае с использованием Spring Data JPA, нам требуется явно заявить, что такого объекта 
нет, например, когда речь идёт о сущности в репозитории. В таком случае, мы можем получить объект или, если его 
нет, выбросить исключение:   
```java
User user = repository.findById(userId).orElseThrow(() -> new NoEntityException(userId));
```    
Если сущность не обнаружена и объект null, будет выброшено исключение NoEntityException (в моём случае, кастомное). 
В моём случае, на клиент уходит строчка «Пользователь {userID} не найден. Проверьте данные запроса».

## Get()
Метод get() возвращает объект, запакованный в Optional.    
Например:    
```java
User user = repository.findById(userId).get();
```    
Будет получен объект User, запакованный в Optional. Такая конструкция крайне опасна, поскольку минует проверку 
на null и лишает смысла само использование Optional, поскольку Вы можете получить желаемый объект, а можете 
получить NPE. Такую конструкцию придётся оборачивать в .isPresent().

## Map()
Этот метод полностью повторяет аналогичный метод для stream(), но срабатывает только в том случае, если в 
Optional есть не-нулловый объект.
```java
String name = repository.findById(userId).map(user -> user.getName()).orElseThrow(() -> new Exception());
```    
В примере мы получили одно из полей класса User, упакованного в Optional.

## Filter()
Данный метод также позаимствован из stream() и фильтрует элементы по условию.    
```java
List<User> users = repository.findAll().filter(user -> user.age >= 18).orElseThrow(() -> new Exception());
```

## Flatmap()
Этот метод делает ровно то же, что и стримовский, с той лишь разницей, что он работает только в том случае, 
если значение не null.

