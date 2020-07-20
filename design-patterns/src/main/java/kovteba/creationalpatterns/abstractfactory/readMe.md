# Abstract Factory

- [Шаги реализации](#Шаги-реализации)
- [Преимущества и недостатки](#Преимущества-и-недостатки)
- [Отношения с другими паттернами](#Отношения-с-другими-паттернами)
- [Example](#Example)

## Шаги реализации
1. Создайте таблицу соотношений типов продуктов к вариациям семейств продуктов.
2. Сведите все вариации продуктов к общим интерфейсам.
3. Определите интерфейс абстрактной фабрики. Он должен иметь фабричные методы для создания каждого из типов продуктов.
4. Создайте классы конкретных фабрик, реализовав интерфейс абстрактной фабрики. Этих классов должно быть столько же, 
    сколько и вариаций семейств продуктов.
5. Измените код инициализации программы так, чтобы она создавала определённую фабрику и передавала её в клиентский код.
6. Замените в клиентском коде участки создания продуктов через конструктор вызовами соответствующих методов фабрики.

## Преимущества и недостатки
- [+] Гарантирует сочетаемость создаваемых продуктов.
- [+] Избавляет клиентский код от привязки к конкретным классам продуктов.
- [+] Выделяет код производства продуктов в одно место, упрощая поддержку кода.
- [+] Упрощает добавление новых продуктов в программу.
- [+] Реализует принцип открытости/закрытости.
 
 
- [-] Усложняет код программы из-за введения множества дополнительных классов.
- [-] Требует наличия всех типов продуктов в каждой вариации.

## Отношения с другими паттернами
- Многие архитектуры начинаются с применения __Фабричного метода__ (более простого и расширяемого через подклассы) и 
    эволюционируют в сторону __Абстрактной фабрики__, __Прототипа__ или __Строителя__ (более гибких, но и более сложных).
- __Строитель__ концентрируется на построении сложных объектов шаг за шагом. __Абстрактная фабрика__ специализируется 
    на создании семейств связанных продуктов. __Строитель_ возвращает продукт только после выполнения всех шагов, 
    а __Абстрактная фабрика__ возвращает продукт сразу же.
- Классы __Абстрактной фабрики__ чаще всего реализуются с помощью __Фабричного метода__, хотя они могут быть 
    построены и на основе __Прототипа__.
- __Абстрактная фабрика__ может быть использована вместо __Фасада__ для того, чтобы скрыть платформо-зависимые классы.
- __Абстрактная фабрика__ может работать совместно с __Мостом__. Это особенно полезно, если у вас есть абстракции, 
    которые могут работать только с некоторыми из реализаций. В этом случае фабрика будет определять типы создаваемых 
    абстракций и реализаций.
- __Абстрактная фабрика__, __Строитель__ и __Прототип__ могут быть реализованы при помощи __Одиночки__.

## Example
```java
public interface Button {
   void paint();
}
public class MacOSButton implements Button {
   @Override
   public void paint() {
      System.out.println("You have created MacOSButton.");
   }
}
public class WindowsButton implements Button {
   @Override
   public void paint() {
      System.out.println("You have created WindowsButton.");
   }
}
```
```java
public interface Checkbox {
   void paint();
}
public class MacOSCheckbox implements Checkbox {
   @Override
   public void paint() {
      System.out.println("You have created MacOSCheckbox.");
   }
}
public class WindowsCheckbox implements Checkbox {
   @Override
   public void paint() {
      System.out.println("You have created WindowsCheckbox.");
   }
}
```
```java
// ABSTRACT FACTORY
public interface GUIFactory {
   Button createButton();
   Checkbox createCheckbox();
}
public class MacOSFactory implements GUIFactory {
   @Override
   public Button createButton() {
      return new MacOSButton();
   }

   @Override
   public Checkbox createCheckbox() {
      return new MacOSCheckbox();
   }
}
public class WindowsFactory implements GUIFactory {
   @Override
   public Button createButton() {
      return new WindowsButton();
   }

   @Override
   public Checkbox createCheckbox() {
      return new WindowsCheckbox();
   }
}
```
```java
public class Application {

   private Button button;
   private Checkbox checkbox;

   public Application(GUIFactory factory) {
      button = factory.createButton();
      checkbox = factory.createCheckbox();
   }

   public void paint() {
      button.paint();
      checkbox.paint();
   }

}
public class ConfigureApplication {
   public static Application config(String osName) {
      Application app;
      GUIFactory factory;
      if (osName.contains("mac"))
         factory = new MacOSFactory();
      else
         factory = new WindowsFactory();
      return new Application(factory);
   }
}
public class Demo {
   public static void main(String[] args) {
      Application app = ConfigureApplication.config("win");
      app.paint();
   }
}
```

