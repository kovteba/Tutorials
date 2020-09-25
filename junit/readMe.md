## JUnit

- [About](#About)
- [@Before](#@Before)
- [@BeforeClass](#@BeforeClass)
- [@After](#@After)
- [@AfterClass](#@AfterClass)
- [@Test](#@Test)
- [@Ignore](#@Ignore)
- [@Rule](#@Rule)
- [Наборы тестов, JUnit Suite, SuiteClasses](#Наборы-тестов,-JUnit Suite,-SuiteClasses)
- [@Categories](#@Categories)
- [JUnit Parameterized](#JUnit-Parameterized)
- [Порядок выполнения тестов](#Порядок-выполнения-тестов)
- [Список типов проверок Asserts](#Список-типов-проверок-Asserts)
- [](#)

---

### About
Тестирование программного обеспечение можно разделить на два вида:   
- тестирование черного ящика;
- тестирование белого ящика.

Во время тестирования программы как черного ящика внутренняя структура приложения в расчет не принимается. 
Все, что имеет значение, это функциональность, которую приложение должно обеспечить. При тестировании 
программы как белого ящика во внимание принимается внутренняя структура, т.е. класс и методы. Кроме 
этого, тестирование можно разделить на четыре уровня:   
- юнит тесты — тестирование отдельных участков кода;
- интеграционное тестирование — тестирование взаимодействия и совместной работы компонентов;
- системное тестирование — тестирование всей системы как целого;
- приемное тестирование — итоговое тестирование готовой системы на соответствие требованиям.

Юнит тестирование по определению является тестированием белого ящика.

---

### @Before
__@Before__ - обозначает методы, которые будут вызваны перед исполнением тестов. Методы должны быть `public void`. 
Здесь обычно размещаются предустановки для теста.

---

### @BeforeClass
__@BeforeClass__ - обозначает методы, которые будут вызваны до создания экземпляра тест-класса; методы 
должны быть `public static void`.
Данную аннотацию (метод) имеет смысл использовать для тестирования в случае, когда класс содержит 
несколько тестов, использующих различные предустановки, либо когда несколько тестов используют одни и 
те же данные, чтобы не тратить время на их создание для каждого теста.

---

### @After
__@After__ - обозначает методы, которые будут вызваны после выполнения тестов. Методы должны быть `public void`. 
Здесь размещаются операции освобождения ресурсов после теста;

---

### @AfterClass
@AfterClass связана по смыслу с @BeforeClass, но выполняет методы после тестирования класса. Как и в 
случае с @BeforeClass, методы должны быть `public static void`.

---

### @Test
__@Test__ обозначает тестовые методы. Как и ранее, эти методы должны быть `public void`. Кроме того, 
в данной аннотации можно использовать два параметра, `expected` — задает ожидаемое исключение 
и `timeout` — задает время, по истечению которого тест считается провалившимся.
```java
@Test(expected = NullPointerException.class)
  public void testToHexStringWrong() {
      StringUtils.toHexString(null);
}

@Test(timeout = 1000)
  public void infinity() {
      while (true);
}
```

---

### @Ignore
Если один из тестов по какой-либо серьезной причине необходимо отключить.  
Если поместить эту аннотацию на класс, то все тесты в этом классе будут отключены.
```java
@Ignore
@Test(timeout = 1000)
public void infinity() {
      while (true);
}
```

---

### @Rule
Для объявления правила необходимо создать `public` __не__ `static` поле типа производного от MethodRule 
и аннотировать его с помощью ключевого слова `Rule`.

---

### Наборы тестов, JUnit Suite, SuiteClasses
Запуск теста может быть сконфигурирован с помощью аннотации @RunWith. Тестовые классы, которые содержат 
в себе тестовые методы, можно объединить в наборы тестов (Suite). Например, создано два класса 
тестирования объектов : `TestFilter`, `TestConnect`. Эти два тестовых класса можно объединить в 
дин тестовый класс TestWidgets.java :
```java
@RunWith(Suite.class)
@Suite.SuiteClasses ({
    TestFilter.class,
    TestConnect.class
})
public class TestWidgets {}
```
Для настройки запускаемых тестов используется аннотация @SuiteClasses, в которую включены тестовые классы.

---

### @Category
Аннотация Categories позволяет объединить тесты в категории (группы). Для этого в тесте определяется 
категория `@Category`, после чего настраиваются запускаемые категории тестов в Suite. Это может 
выглядеть следующим образом:
```java
public class JUnitStringUtilsCategoriesTest extends Assert
{
    //...

    @Category (Unit.class)
    @Test
    public void testIsEmpty() {
       //...
    }
    //...
}

@RunWith (Categories.class)
@Categories.IncludeCategory (Unit.class)
@Suite.SuiteClasses ({
    JUnitOtherTest.class, 
	JUnitStringUtilsCategoriesTest.class 
})
public class JUnitTestSuite {}
```

---

### JUnit Parameterized
Аннотация Parameterized позволяет использовать параметризированные тесты. Для этого в тест-классе 
объявляется статический метод, возвращающий список данных, которые будут использованы в качестве 
аргументов конструктора класса.
```java
@RunWith (Parameterized.class)
public class JUnitStringUtilsParameterizedTest extends Assert
{
    private final CharSequence testData;
    private final boolean expected;

    public JUnitStringUtilsParameterizedTest(final CharSequence testData,
                                             final boolean expected) 
    {
        this.testData = testData;
        this.expected = expected;
    }

    @Test
    public void testIsEmpty () {
        final boolean actual = StringUtils.isEmpty (testData);
        assertEquals(expected, actual);
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
            { null, true },
            { "", true },
            { " ", false },
            { "some string", false },
        });
    }
}
```

---

### Порядок выполнения тестов
Если необходимо выполнить тест в определенном порядке, то можно воспользоваться 
аннотацией `@FixMethodOrder(MethodSorters.NAME_ASCENDING)`:
```java
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyTest {
    @Test 
	public void test01(){}
    @Test 
	public void test02(){}
    @Test 
	public void test09(){}
}
```

---

### Список типов проверок Asserts
```
+--------------------------------------------------------------------------+-----------------------------------------------------------------+
|                   Тип проверки                                           |                            Описание                             |       
+--------------------------------------------------------------------------+-----------------------------------------------------------------+
|   fail()                                                                 |                                                                 |
|   fail(String message)                                                   |прерывание теста с ошибкой, т.е. тест будет неудачным            |
+--------------------------------------------------------------------------+-----------------------------------------------------------------+
|   assertTrue(boolean condition)                                          |                                                                 |
|   assertTrue(java.lang.String message, boolean condition)                |проверка на равенство условия condition значению true            |
+--------------------------------------------------------------------------+-----------------------------------------------------------------+
|   assertFalse(boolean condition)                                         |                                                                 |
|   assertFalse(String message, boolean condition)                         |проверка на равенство условия condition значению false           |
+--------------------------------------------------------------------------+-----------------------------------------------------------------+
|   assertEquals(<тип> expected, <тип> actual)                             |                                                                 |
|   assertEquals(String message, <тип> expected, <тип> actual)	           |проверка на равенство; <тип> — это Object, int, double и т.д.    |
+--------------------------------------------------------------------------+-----------------------------------------------------------------+
|   assertArrayEquals(byte[] expecteds, byte[] actuals)                    |проверка массивов на равенство; аналогично assertEquals;         |
|   assertArrayEquals(String message, <тип>[] expecteds, <тип>[] actuals)  |<тип> — это Object, int, double и т.д.                           |
+--------------------------------------------------------------------------+-----------------------------------------------------------------+
|   assertNotNull(Object object)                                           |                                                                 |
|   assertNotNull(String message, Object object)                           |проверка, что Object не null                                     |
+--------------------------------------------------------------------------+-----------------------------------------------------------------+
|   assertNull(Object object)                                              |                                                                 |
|   assertNull(String message, Object object)	                           |проверка, что Object null                                        |
+--------------------------------------------------------------------------+-----------------------------------------------------------------+
|   assertSame(Object expected, Object actual)                             |проверка на равенство двух объектов expected и actual,           |
|   assertSame(String message, Object expected, Object actual)             |т.е. один и тот же объект                                        |
+--------------------------------------------------------------------------+-----------------------------------------------------------------+
```

---