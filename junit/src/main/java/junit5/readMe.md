## JUnit5

- [About](#About)
- [Writing Tests](#Writing-Tests)
    - [Meta Annotations and Composed Annotations](#Meta-Annotations-and-Composed-Annotations)
    - [Test Classes and Methods](#Test-Classes-and-Methods)
    - [Display Names](#Display-Names)
    - [Display Name Generators](#Display-Name-Generators)
    - [Assertions](#Assertions)
    - [Third party Assertion Libraries](#Third-party-Assertion-Libraries)
    - [Assumptions](#Assumptions)
    - [Disabling Tests](#Disabling-Tests)
    - [Conditional Test Execution](#Conditional-Test-Execution)
        - [Operating System Conditions](#Operating-System-Conditions)
        - [Java Runtime Environment Conditions](#Java-Runtime-Environment-Conditions)
        - [System Property Conditions](#System-Property-Conditions)
        - [Environment Variable Conditions](#Environment-Variable-Conditions)
        - [Custom Conditions](#Custom-Conditions)
    - [Tagging and Filtering](#Tagging-and-Filtering)
    - [Test Execution Order](#Test-Execution-Order)
    - [Test Instance Lifecycle](#Test-Instance-Lifecycle)
        - [Changing the Default Test Instance Lifecycle](#Changing-the-Default-Test-Instance-Lifecycle)
    - [Nested Tests](#Nested-Tests)
    - [Dependency Injection for Constructors and Methods](#Dependency-Injection-for-Constructors-and-Methods)
    - [Repeated Tests](#Repeated-Tests)
    - [Parameterized Tests](#Parameterized-Tests)
        - [Sources of Arguments](#Sources-of-Arguments)
            - [@ValueSource](#@ValueSource)
            - [Null and Empty Sources](#Null-and-Empty-Sources)
            - [@EnumSource](#@EnumSource)
            - [@MethodSource](#@MethodSource)
            - [@CsvSource](#@CsvSource)
            - [@CsvFileSource](#@CsvFileSource)
            - [@ArgumentsSource](#@ArgumentsSource)
        - [Argument Conversion](#Argument-Conversion)
            - [Widening Conversion](#Widening-Conversion)
            - [Implicit Conversion](#Implicit-Conversion)
                - [Fallback String-to-Object Conversion](#Fallback-String-to-Object-Conversion)
            - [Explicit Conversion](#Explicit-Conversion)
        - [Argument Aggregation](#Argument-Aggregation)
            - [Custom Aggregators](#Custom-Aggregators)
        - [Customizing Display Names](#Customizing-Display-Names)
        - [](#)
- [Annotations](#Annotations)
- [](#)
- [](#)

---

## About

---
## Writing Tests

## Meta Annotations and Composed Annotations
```java
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Tag("fast")
public @interface Fast {
}
```
```java
@Fast
@Test
void myFastTest() {
    // ...
}
```
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Tag("fast")
@Test
public @interface FastTest {
}
```
```java
@FastTest
void myFastTest() {
    // ...
}
```

---

## Test Classes and Methods

Test Method: any instance method that is directly annotated or meta-annotated with @Test, @RepeatedTest, 
@ParameterizedTest, @TestFactory, or @TestTemplate.

Lifecycle Method: any method that is directly annotated or meta-annotated with @BeforeAll, @AfterAll, @BeforeEach, 
or @AfterEach.


Test methods and lifecycle methods may be declared locally within the current test class, inherited from superclasses, 
or inherited from interfaces (see Test Interfaces and Default Methods). In addition, test methods and lifecycle methods 
must not be abstract and must not return a value.

>Test classes, test methods, and lifecycle methods are not required to be public, but they must not be private.

The following test class demonstrates the use of @Test methods and all supported lifecycle methods.
```java
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StandardTests {

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
    }

    @Test
    void succeedingTest() {
    }

    @Test
    void failingTest() {
        fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @Test
    void abortedTest() {
        assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }

}
```

---

## Display Names
Test classes and test methods can declare custom display names via @DisplayName with spaces, special 
characters, and even emojis that will be displayed in test reports and by test runners and IDEs.
```java
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A special test case")
class DisplayNameDemo {

    @Test
    @DisplayName("Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces() {
    }

    @Test
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
    }

    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
    }

}
```

---

## Display Name Generators
JUnit Jupiter supports custom display name generators that can be configured via the @DisplayNameGeneration 
annotation. Values provided via @DisplayName annotations always take precedence over display names generated 
by a DisplayNameGenerator.


DisplayNameGenerator	Behavior

Standard
Matches the standard display name generation behavior in place since JUnit Jupiter 5.0 was released.

Simple
Removes trailing parentheses for methods with no parameters.

ReplaceUnderscores
Replaces underscores with spaces.

IndicativeSentences
Generates complete sentences by concatenating the names of the test and the enclosing classes.

Note that for IndicativeSentences, you can customize the separator and the underlying generator by using 
@IndicativeSentencesGeneration as shown in the following example.
```java
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DisplayNameGeneratorDemo {

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class A_year_is_not_supported {

        @Test
        void if_it_is_zero() {
        }

        @DisplayName("A negative value for year is not supported by the leap year computation.")
        @ParameterizedTest(name = "For example, year {0} is not supported.")
        @ValueSource(ints = { -1, -4 })
        void if_it_is_negative(int year) {
        }

    }

    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
    class A_year_is_a_leap_year {

        @Test
        void if_it_is_divisible_by_4_but_not_by_100() {
        }

        @ParameterizedTest(name = "Year {0} is a leap year.")
        @ValueSource(ints = { 2016, 2020, 2048 })
        void if_it_is_one_of_the_following_years(int year) {
        }

    }

}
```

---

## Assertions
```java
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;

import example.domain.Person;
import example.util.Calculator;

import org.junit.jupiter.api.Test;

class AssertionsDemo {

    private final Calculator calculator = new Calculator();

    private final Person person = new Person("Jane", "Doe");

    @Test
    void standardAssertions() {
        assertEquals(2, calculator.add(1, 1));
        assertEquals(4, calculator.multiply(2, 2),
                "The optional failure message is now the last parameter");
        assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        assertAll("person",
            () -> assertEquals("Jane", person.getFirstName()),
            () -> assertEquals("Doe", person.getLastName())
        );
    }

    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("properties",
            () -> {
                String firstName = person.getFirstName();
                assertNotNull(firstName);

                // Executed only if the previous assertion is valid.
                assertAll("first name",
                    () -> assertTrue(firstName.startsWith("J")),
                    () -> assertTrue(firstName.endsWith("e"))
                );
            },
            () -> {
                // Grouped assertion, so processed independently
                // of results of first name assertions.
                String lastName = person.getLastName();
                assertNotNull(lastName);

                // Executed only if the previous assertion is valid.
                assertAll("last name",
                    () -> assertTrue(lastName.startsWith("D")),
                    () -> assertTrue(lastName.endsWith("e"))
                );
            }
        );
    }

    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () ->
            calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // The following assertion succeeds.
        assertTimeout(ofMinutes(2), () -> {
            // Perform task that takes less than 2 minutes.
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        // The following assertion invokes a method reference and returns an object.
        String actualGreeting = assertTimeout(ofMinutes(2), AssertionsDemo::greeting);
        assertEquals("Hello, World!", actualGreeting);
    }

    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            new CountDownLatch(1).await();
        });
    }

    private static String greeting() {
        return "Hello, World!";
    }

}
```

>Preemptive Timeouts with assertTimeoutPreemptively()
> Contrary to declarative timeouts, the various assertTimeoutPreemptively() methods in the Assertions class execute 
>the provided executable or supplier in a different thread than that of the calling code. This behavior can lead to 
>undesirable side effects if the code that is executed within the executable or supplier relies on java.lang.ThreadLocal storage.
> 
>One common example of this is the transactional testing support in the Spring Framework. Specifically, Spring’s 
>testing support binds transaction state to the current thread (via a ThreadLocal) before a test method is invoked. 
>Consequently, if an executable or supplier provided to assertTimeoutPreemptively() invokes Spring-managed components 
>that participate in transactions, any actions taken by those components will not be rolled back with the test-managed 
>transaction. On the contrary, such actions will be committed to the persistent store (e.g., relational database) even 
>though the test-managed transaction is rolled back.
> 
> Similar side effects may be encountered with other frameworks that rely on ThreadLocal storage.

---

## Third party Assertion Libraries
Even though the assertion facilities provided by JUnit Jupiter are sufficient for many testing scenarios, 
there are times when more power and additional functionality such as matchers are desired or required. 
In such cases, the JUnit team recommends the use of third-party assertion libraries such as AssertJ, Hamcrest,
 Truth, etc. Developers are therefore free to use the assertion library of their choice.
```java
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import example.util.Calculator;

import org.junit.jupiter.api.Test;

class HamcrestAssertionsDemo {

    private final Calculator calculator = new Calculator();

    @Test
    void assertWithHamcrestMatcher() {
        assertThat(calculator.subtract(4, 1), is(equalTo(3)));
    }

}

```

---

## Assumptions
```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import example.util.Calculator;

import org.junit.jupiter.api.Test;

class AssumptionsDemo {

    private final Calculator calculator = new Calculator();

    @Test
    void testOnlyOnCiServer() {
        assumeTrue("CI".equals(System.getenv("ENV")));
        // remainder of test
    }

    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")),
            () -> "Aborting test: not on developer workstation");
        // remainder of test
    }

    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")),
            () -> {
                // perform these assertions only on the CI server
                assertEquals(2, calculator.divide(4, 2));
            });

        // perform these assertions in all environments
        assertEquals(42, calculator.multiply(6, 7));
    }
}
```
>	As of JUnit Jupiter 5.4, it is also possible to use methods from JUnit 4’s org.junit.Assume class for assumptions. 
>Specifically, JUnit Jupiter supports JUnit 4’s AssumptionViolatedException to signal that a test should be aborted 
>instead of marked as a failure.

---

## Disabling Tests
```java
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Disabled until bug #99 has been fixed")
class DisabledClassDemo {

    @Test
    void testWillBeSkipped() {
    }
}
```
```java
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DisabledTestsDemo {

    @Disabled("Disabled until bug #42 has been resolved")
    @Test
    void testWillBeSkipped() {
    }

    @Test
    void testWillBeExecuted() {
    }
}
```
>	@Disabled may be declared without providing a reason; however, the JUnit team recommends that developers 
>provide a short explanation for why a test class or test method has been disabled. Consequently, the above 
>examples both show the use of a reason — for example, @Disabled("Disabled until bug #42 has been resolved"). 
>Some development teams even require the presence of issue tracking numbers in the reason for automated traceability, etc.

---
## Conditional Test Execution

## Operating System Conditions
```java
@Test
@EnabledOnOs(MAC)
void onlyOnMacOs() {
    // ...
}

@TestOnMac
void testOnMac() {
    // ...
}

@Test
@EnabledOnOs({ LINUX, MAC })
void onLinuxOrMac() {
    // ...
}

@Test
@DisabledOnOs(WINDOWS)
void notOnWindows() {
    // ...
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Test
@EnabledOnOs(MAC)
@interface TestOnMac {
}
```

---

## Java Runtime Environment Conditions
A container or test may be enabled or disabled on particular versions of the Java Runtime Environment (JRE) via 
the @EnabledOnJre and @DisabledOnJre annotations or on a particular range of versions of the JRE via the 
@EnabledForJreRange and @DisabledForJreRange annotations. The range defaults to JRE.JAVA_8 as the lower border 
(min) and JRE.OTHER as the higher border (max), which allows usage of half open ranges.
```java
@Test
@EnabledOnJre(JAVA_8)
void onlyOnJava8() {
    // ...
}

@Test
@EnabledOnJre({ JAVA_9, JAVA_10 })
void onJava9Or10() {
    // ...
}

@Test
@EnabledForJreRange(min = JAVA_9, max = JAVA_11)
void fromJava9to11() {
    // ...
}

@Test
@EnabledForJreRange(min = JAVA_9)
void fromJava9toCurrentJavaFeatureNumber() {
    // ...
}

@Test
@EnabledForJreRange(max = JAVA_11)
void fromJava8To11() {
    // ...
}

@Test
@DisabledOnJre(JAVA_9)
void notOnJava9() {
    // ...
}

@Test
@DisabledForJreRange(min = JAVA_9, max = JAVA_11)
void notFromJava9to11() {
    // ...
}

@Test
@DisabledForJreRange(min = JAVA_9)
void notFromJava9toCurrentJavaFeatureNumber() {
    // ...
}

@Test
@DisabledForJreRange(max = JAVA_11)
void notFromJava8to11() {
    // ...
}
```

---

## System Property Conditions
A container or test may be enabled or disabled based on the value of the named JVM system property via the
 @EnabledIfSystemProperty and @DisabledIfSystemProperty annotations. The value supplied via the matches 
 attribute will be interpreted as a regular expression.
```java
@Test
@EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
void onlyOn64BitArchitectures() {
    // ...
}

@Test
@DisabledIfSystemProperty(named = "ci-server", matches = "true")
void notOnCiServer() {
    // ...
}
```

>As of JUnit Jupiter 5.6, @EnabledIfSystemProperty and @DisabledIfSystemProperty are repeatable annotations. 
>Consequently, these annotations may be declared multiple times on a test interface, test class, or test 
>method. Specifically, these annotations will be found if they are directly present, indirectly present, 
>or meta-present on a given element.

---

## Environment Variable Conditions
A container or test may be enabled or disabled based on the value of the named environment variable from the 
underlying operating system via the @EnabledIfEnvironmentVariable and @DisabledIfEnvironmentVariable annotations. 
The value supplied via the matches attribute will be interpreted as a regular expression.
```java
@Test
@EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
void onlyOnStagingServer() {
    // ...
}

@Test
@DisabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
void notOnDeveloperWorkstation() {
    // ...
}
```

>As of JUnit Jupiter 5.6, @EnabledIfEnvironmentVariable and @DisabledIfEnvironmentVariable are repeatable 
>annotations. Consequently, these annotations may be declared multiple times on a test interface, test class, 
>or test method. Specifically, these annotations will be found if they are directly present, indirectly present, 
>or meta-present on a given element.

---

## Custom Conditions
A container or test may be enabled or disabled based on the boolean return of a method via the @EnabledIf 
and @DisabledIf annotations. The method is provided to the annotation via its name, or its fully qualified 
name if located outside the test class. If needed, the condition method can take a single parameter of type 
ExtensionContext.
```java
@Test
@EnabledIf("customCondition")
void enabled() {
    // ...
}

@Test
@DisabledIf("customCondition")
void disabled() {
    // ...
}

boolean customCondition() {
    return true;
}
```
>	When @EnabledIf or @DisabledIf is used at class level, the condition method must always be static. 
>Condition methods located in external classes must also be static. In any other case, you can use both 
>static or instance methods.

---

## Tagging and Filtering
Test classes and methods can be tagged via the @Tag annotation. Those tags can later be used to filter test 
discovery and execution.

__Syntax Rules for Tags__:   
- A tag must not be null or blank.
- A trimmed tag must not contain whitespace.
- A trimmed tag must not contain ISO control characters.
- A trimmed tag must not contain any of the following reserved characters.
`,`: comma
`(`: left parenthesis
`)`: right parenthesis
`&`: ampersand
`|`: vertical bar
`!`: exclamation point
```java
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("fast")
@Tag("model")
class TaggingDemo {

    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
    }

}
```

---

## Test Execution Order
By default, test methods will be ordered using an algorithm that is deterministic but intentionally nonobvious. 
This ensures that subsequent runs of a test suite execute test methods in the same order, thereby allowing 
for repeatable builds.

Although true unit tests typically should not rely on the order in which they are executed, there are times when 
it is necessary to enforce a specific test method execution order — for example, when writing integration 
tests or functional tests where the sequence of the tests is important, especially in conjunction with 
@TestInstance(Lifecycle.PER_CLASS).

To control the order in which test methods are executed, annotate your test class or test interface with 
@TestMethodOrder and specify the desired MethodOrderer implementation. You can implement your own custom 
MethodOrderer or use one of the following built-in MethodOrderer implementations.

DisplayName: sorts test methods alphanumerically based on their display names (see display name generation precedence rules)

MethodName: sorts test methods alphanumerically based on their method name and formal parameter lists.

OrderAnnotation: sorts test methods numerically based on values specified via the @Order annotation.

Random: orders test methods pseudo-randomly and supports configuration of a custom seed.

Alphanumeric: sorts test methods alphanumerically based on their names and formal parameter lists. Deprecated in 
favor of MethodName. Will be removed in 6.0
```java
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class OrderedTestsDemo {

    @Test
    @Order(1)
    void nullValues() {
        // perform assertions against null values
    }

    @Test
    @Order(2)
    void emptyValues() {
        // perform assertions against empty values
    }

    @Test
    @Order(3)
    void validValues() {
        // perform assertions against valid values
    }

}

```

You can use the junit.jupiter.testmethod.order.default configuration parameter to specify the fully qualified class name of the MethodOrderer you would like to use by default. Just like for the orderer configured via the @TestMethodOrder annotation, the supplied class has to implement the MethodOrderer interface. The default orderer will be used for all tests unless the @TestMethodOrder annotation is present on an enclosing test class or test interface.

For example, to use the OrderAnnotation method orderer by default, you should set the configuration parameter to the corresponding fully qualified class name (e.g., in src/test/resources/junit-platform.properties):

>junit.jupiter.testmethod.order.default = \
>    org.junit.jupiter.api.MethodOrderer$OrderAnnotation
    
Similarly, you can specify the fully qualified name of any custom class that implements MethodOrderer.

---

## Test Instance Lifecycle
JUnit creates a new instance of each test class before executing each test method
This "per-method" test instance lifecycle is the default behavior in JUnit Jupiter and is analogous to all previous versions of JUnit.

>Please note that the test class will still be instantiated if a given test method is disabled via a condition (e.g., 
>@Disabled, @DisabledOnOs, etc.) even when the "per-method" test instance lifecycle mode is active.

If you would prefer that JUnit Jupiter execute all test methods on the same test instance, annotate your test class 
with @TestInstance(Lifecycle.PER_CLASS). When using this mode, a new test instance will be created once per test 
class. Thus, if your test methods rely on state stored in instance variables, you may need to reset that state 
in @BeforeEach or @AfterEach methods.

The "per-class" mode has some additional benefits over the default "per-method" mode. Specifically, with the
 "per-class" mode it becomes possible to declare @BeforeAll and @AfterAll on non-static methods as well as on 
 interface default methods. The "per-class" mode therefore also makes it possible to use @BeforeAll and 
 @AfterAll methods in @Nested test classes.

---

## Changing the Default Test Instance Lifecycle
If a test class or test interface is not annotated with @TestInstance, JUnit Jupiter will use a default lifecycle 
mode. The standard default mode is PER_METHOD;

To change the default test instance lifecycle mode, set the junit.jupiter.testinstance.lifecycle.default configuration 
parameter to the name of an enum constant defined in TestInstance.Lifecycle, ignoring case.

```
-Djunit.jupiter.testinstance.lifecycle.default=per_class
```
Note, however, that setting the default test instance lifecycle mode via the JUnit Platform configuration file 
is a more robust solution since the configuration file can be checked into a version control system along with 
your project and can therefore be used within IDEs and your build software.

To set the default test instance lifecycle mode to Lifecycle.PER_CLASS via the JUnit Platform configuration file, 
create a file named junit-platform.properties in the root of the class path (e.g., src/test/resources) with 
the following content.
```
junit.jupiter.testinstance.lifecycle.default = per_class
```

---

## Nested Tests
@Nested tests give the test writer more capabilities to express the relationship among several groups of tests. 
Here’s an elaborate example.
```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("A stack")
class TestingAStackDemo {

    Stack<Object> stack;

    @Test
    @DisplayName("is instantiated with new Stack()")
    void isInstantiatedWithNew() {
        new Stack<>();
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void createNewStack() {
            stack = new Stack<>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("throws EmptyStackException when popped")
        void throwsExceptionWhenPopped() {
            assertThrows(EmptyStackException.class, stack::pop);
        }

        @Test
        @DisplayName("throws EmptyStackException when peeked")
        void throwsExceptionWhenPeeked() {
            assertThrows(EmptyStackException.class, stack::peek);
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {

            String anElement = "an element";

            @BeforeEach
            void pushAnElement() {
                stack.push(anElement);
            }

            @Test
            @DisplayName("it is no longer empty")
            void isNotEmpty() {
                assertFalse(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            void returnElementWhenPopped() {
                assertEquals(anElement, stack.pop());
                assertTrue(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when peeked but remains not empty")
            void returnElementWhenPeeked() {
                assertEquals(anElement, stack.peek());
                assertFalse(stack.isEmpty());
            }
        }
    }
}
```

>	Only non-static nested classes (i.e. inner classes) can serve as @Nested test classes. Nesting can be 
>arbitrarily deep, and those inner classes are considered to be full members of the test class family with 
>one exception: @BeforeAll and @AfterAll methods do not work by default. The reason is that Java does not 
>allow static members in inner classes. However, this restriction can be circumvented by annotating a 
>@Nested test class with @TestInstance(Lifecycle.PER_CLASS) (see Test Instance Lifecycle).

---

## Dependency Injection for Constructors and Methods
In all prior JUnit versions, test constructors or methods were not allowed to have parameters (at least not with 
the standard Runner implementations). As one of the major changes in JUnit Jupiter, both test constructors 
and methods are now permitted to have parameters. This allows for greater flexibility and enables Dependency 
Injection for constructors and methods.

ParameterResolver defines the API for test extensions that wish to dynamically resolve parameters at runtime. 
If a test class constructor, a test method, or a lifecycle method (see Test Classes and Methods) accepts a 
parameter, the parameter must be resolved at runtime by a registered ParameterResolver.

- TestInfoParameterResolver: if a constructor or method parameter is of type TestInfo, the TestInfoParameterResolver 
will supply an instance of TestInfo corresponding to the current container or test as the value for the parameter. 
The TestInfo can then be used to retrieve information about the current container or test such as the display name, 
the test class, the test method, and associated tags. The display name is either a technical name, such as the name 
of the test class or test method, or a custom name configured via @DisplayName.

TestInfo acts as a drop-in replacement for the TestName rule from JUnit 4. The following demonstrates how to have 
TestInfo injected into a test constructor, @BeforeEach method, and @Test method.
```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

@DisplayName("TestInfo Demo")
class TestInfoDemo {

    TestInfoDemo(TestInfo testInfo) {
        assertEquals("TestInfo Demo", testInfo.getDisplayName());
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        assertTrue(displayName.equals("TEST 1") || displayName.equals("test2()"));
    }

    @Test
    @DisplayName("TEST 1")
    @Tag("my-tag")
    void test1(TestInfo testInfo) {
        assertEquals("TEST 1", testInfo.getDisplayName());
        assertTrue(testInfo.getTags().contains("my-tag"));
    }

    @Test
    void test2() {
    }

}
```


- RepetitionInfoParameterResolver: if a method parameter in a @RepeatedTest, @BeforeEach, or @AfterEach method 
is of type RepetitionInfo, the RepetitionInfoParameterResolver will supply an instance of RepetitionInfo. 
RepetitionInfo can then be used to retrieve information about the current repetition and the total number 
of repetitions for the corresponding @RepeatedTest. Note, however, that RepetitionInfoParameterResolver 
is not registered outside the context of a @RepeatedTest. See Repeated Test Examples.


- RepetitionInfoParameterResolver: if a method parameter in a @RepeatedTest, @BeforeEach, or @AfterEach method 
is of type RepetitionInfo, the RepetitionInfoParameterResolver will supply an instance of RepetitionInfo. 
RepetitionInfo can then be used to retrieve information about the current repetition and the total number 
of repetitions for the corresponding @RepeatedTest. Note, however, that RepetitionInfoParameterResolver 
is not registered outside the context of a @RepeatedTest. See Repeated Test Examples.
```java
class TestReporterDemo {

    @Test
    void reportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("a status message");
    }

    @Test
    void reportKeyValuePair(TestReporter testReporter) {
        testReporter.publishEntry("a key", "a value");
    }

    @Test
    void reportMultipleKeyValuePairs(TestReporter testReporter) {
        Map<String, String> values = new HashMap<>();
        values.put("user name", "dk38");
        values.put("award year", "1974");

        testReporter.publishEntry(values);
    }

}
```

Check out the RandomParametersExtension for an example of a custom ParameterResolver. While not intended to be 
production-ready, it demonstrates the simplicity and expressiveness of both the extension model and the 
parameter resolution process. MyRandomParametersTest demonstrates how to inject random values into @Test methods.
```java
@ExtendWith(RandomParametersExtension.class)
class MyRandomParametersTest {

    @Test
    void injectsInteger(@Random int i, @Random int j) {
        assertNotEquals(i, j);
    }

    @Test
    void injectsDouble(@Random double d) {
        assertEquals(0.0, d, 1.0);
    }

}
```

---

## Repeated Tests
In addition to specifying the number of repetitions, a custom display name can be configured for each repetition via the name attribute of the @RepeatedTest annotation. Furthermore, the display name can be a pattern composed of a combination of static text and dynamic placeholders. The following placeholders are currently supported.

DisplayName: display name of the @RepeatedTest method

{currentRepetition}: the current repetition count

{totalRepetitions}: the total number of repetitions
```java
    @RepeatedTest(value = 10, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("NAME ")
    public void methor(){

    }
```
```java
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

class RepeatedTestsDemo {

    private Logger logger = // ...

    @BeforeEach
    void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        int totalRepetitions = repetitionInfo.getTotalRepetitions();
        String methodName = testInfo.getTestMethod().get().getName();
        logger.info(String.format("About to execute repetition %d of %d for %s", //
            currentRepetition, totalRepetitions, methodName));
    }

    @RepeatedTest(10)
    void repeatedTest() {
        // ...
    }

    @RepeatedTest(5)
    void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        assertEquals(5, repetitionInfo.getTotalRepetitions());
    }

    @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Repeat!")
    void customDisplayName(TestInfo testInfo) {
        assertEquals("Repeat! 1/1", testInfo.getDisplayName());
    }

    @RepeatedTest(value = 1, name = RepeatedTest.LONG_DISPLAY_NAME)
    @DisplayName("Details...")
    void customDisplayNameWithLongPattern(TestInfo testInfo) {
        assertEquals("Details... :: repetition 1 of 1", testInfo.getDisplayName());
    }

    @RepeatedTest(value = 5, name = "Wiederholung {currentRepetition} von {totalRepetitions}")
    void repeatedTestInGerman() {
        // ...
    }

}
```

---

## Parameterized Tests

## @ValueSource
```java
@ParameterizedTest
@ValueSource(ints = { 1, 2, 3 })
void testWithValueSource(int argument) {
    assertTrue(argument > 0 && argument < 4);
}
```

---

## Null and Empty Sources
- @NullSource: provides a single null argument to the annotated @ParameterizedTest method.
@NullSource cannot be used for a parameter that has a primitive type.

- @EmptySource: provides a single empty argument to the annotated @ParameterizedTest method for parameters of 
the following types: java.lang.String, java.util.List, java.util.Set, java.util.Map, primitive arrays 
(e.g., int[], char[][], etc.), object arrays (e.g.,String[], Integer[][], etc.).
Subtypes of the supported types are not supported.

- @NullAndEmptySource: a composed annotation that combines the functionality of @NullSource and @EmptySource.

If you need to supply multiple varying types of blank strings to a parameterized test, you can achieve that using
 @ValueSource — for example, @ValueSource(strings = {" ", "   ", "\t", "\n"}).
 ```java
@ParameterizedTest
@NullSource
@EmptySource
@ValueSource(strings = { " ", "   ", "\t", "\n" })
void nullEmptyAndBlankStrings(String text) {
    assertTrue(text == null || text.trim().isEmpty());
}
```
or
```java
@ParameterizedTest
@NullAndEmptySource
@ValueSource(strings = { " ", "   ", "\t", "\n" })
void nullEmptyAndBlankStrings(String text) {
    assertTrue(text == null || text.trim().isEmpty());
}
```

---

## @EnumSource
```java
@ParameterizedTest
@EnumSource(ChronoUnit.class)
void testWithEnumSource(TemporalUnit unit) {
    assertNotNull(unit);
}
```
```java
@ParameterizedTest
@EnumSource(names = { "DAYS", "HOURS" })
void testWithEnumSourceInclude(ChronoUnit unit) {
    assertTrue(EnumSet.of(ChronoUnit.DAYS, ChronoUnit.HOURS).contains(unit));
}
```

The @EnumSource annotation also provides an optional mode attribute that enables fine-grained control over 
which constants are passed to the test method. For example, you can exclude names from the enum constant 
pool or specify regular expressions as in the following examples.
```java
@ParameterizedTest
@EnumSource(mode = EXCLUDE, names = { "ERAS", "FOREVER" })
void testWithEnumSourceExclude(ChronoUnit unit) {
    assertFalse(EnumSet.of(ChronoUnit.ERAS, ChronoUnit.FOREVER).contains(unit));
}
```
```java
@ParameterizedTest
@EnumSource(mode = MATCH_ALL, names = "^.*DAYS$")
void testWithEnumSourceRegex(ChronoUnit unit) {
    assertTrue(unit.name().endsWith("DAYS"));
}
```

---

## @MethodSource
Factory methods within the test class must be static unless the test class is annotated with 
@TestInstance(Lifecycle.PER_CLASS); whereas, factory methods in external classes must always be 
static. In addition, such factory methods must not accept any arguments.
```java
@ParameterizedTest
@MethodSource("stringProvider")
void testWithExplicitLocalMethodSource(String argument) {
    assertNotNull(argument);
}

static Stream<String> stringProvider() {
    return Stream.of("apple", "banana");
}
```

If you do not explicitly provide a factory method name via @MethodSource, JUnit Jupiter will search for a factory 
method that has the same name as the current @ParameterizedTest method by convention. This is demonstrated in the 
following example.
```java
@ParameterizedTest
@MethodSource
void testWithDefaultLocalMethodSource(String argument) {
    assertNotNull(argument);
}

static Stream<String> testWithDefaultLocalMethodSource() {
    return Stream.of("apple", "banana");
}
```

---

## @CsvSource
```java
@ParameterizedTest
@CsvSource({
    "apple,         1",
    "banana,        2",
    "'lemon, lime', 0xF1"
})
void testWithCsvSource(String fruit, int rank) {
    assertNotNull(fruit);
    assertNotEquals(0, rank);
}
```
@CsvSource uses a single quote ' as its quote character. See the 'lemon, lime' value in the example above and in 
the table below. An empty, quoted value '' results in an empty String unless the emptyValue attribute is set; 
whereas, an entirely empty value is interpreted as a null reference. By specifying one or more nullValues, a 
custom value can be interpreted as a null reference (see the NIL example in the table below). An 
ArgumentConversionException is thrown if the target type of a null reference is a primitive type.


Example Input	Resulting Argument List

@CsvSource({ "apple, banana" })
"apple", "banana"

@CsvSource({ "apple, 'lemon, lime'" })
"apple", "lemon, lime"

@CsvSource({ "apple, ''" })
"apple", ""

@CsvSource({ "apple, " })
"apple", null

@CsvSource(value = { "apple, banana, NIL" }, nullValues = "NIL")
"apple", "banana", null

---

## @CsvFileSource
@CsvFileSource lets you use CSV files from the classpath or the local file system. Each line from a CSV file 
results in one invocation of the parameterized test.
```java
@ParameterizedTest
@CsvFileSource(resources = "/two-column.csv", numLinesToSkip = 1)
void testWithCsvFileSourceFromClasspath(String country, int reference) {
    assertNotNull(country);
    assertNotEquals(0, reference);
}

@ParameterizedTest
@CsvFileSource(files = "src/test/resources/two-column.csv", numLinesToSkip = 1)
void testWithCsvFileSourceFromFile(String country, int reference) {
    assertNotNull(country);
    assertNotEquals(0, reference);
}
```

---

## @ArgumentsSource
@ArgumentsSource can be used to specify a custom, reusable ArgumentsProvider. Note that an implementation 
of ArgumentsProvider must be declared as either a top-level class or as a static nested class.
```java
@ParameterizedTest
@ArgumentsSource(MyArgumentsProvider.class)
void testWithArgumentsSource(String argument) {
    assertNotNull(argument);
}
```
```java
public class MyArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of("apple", "banana").map(Arguments::of);
    }
}
```

---

## Argument Conversion

## Widening Conversion
JUnit Jupiter supports Widening Primitive Conversion for arguments supplied to a @ParameterizedTest. For example, 
a parameterized test annotated with @ValueSource(ints = { 1, 2, 3 }) can be declared to accept not only an argument 
of type int but also an argument of type long, float, or double.

## Implicit Conversion

## Fallback String to Object Conversion

In addition to implicit conversion from strings to the target types listed in the above table, JUnit Jupiter also 
provides a fallback mechanism for automatic conversion from a String to a given target type if the target type 
declares exactly one suitable factory method or a factory constructor as defined below.

- factory method: a non-private, static method declared in the target type that accepts a single String argument and 
returns an instance of the target type. The name of the method can be arbitrary and need not follow any particular 
convention.

- factory constructor: a non-private constructor in the target type that accepts a single String argument. Note 
that the target type must be declared as either a top-level class or as a static nested class.

>If multiple factory methods are discovered, they will be ignored. If a factory method and a factory constructor 
>are discovered, the factory method will be used instead of the constructor.
```java
@ParameterizedTest
@ValueSource(strings = "42 Cats")
void testWithImplicitFallbackArgumentConversion(Book book) {
    assertEquals("42 Cats", book.getTitle());
}
```
```java
public class Book {

    private final String title;

    private Book(String title) {
        this.title = title;
    }

    public static Book fromTitle(String title) {
        return new Book(title);
    }

    public String getTitle() {
        return this.title;
    }
}
```

---

## Explicit Conversion
Instead of relying on implicit argument conversion you may explicitly specify an ArgumentConverter to use for a 
certain parameter using the @ConvertWith annotation like in the following example. Note that an implementation 
of ArgumentConverter must be declared as either a top-level class or as a static nested class.
```java
@ParameterizedTest
@EnumSource(ChronoUnit.class)
void testWithExplicitArgumentConversion(
        @ConvertWith(ToStringArgumentConverter.class) String argument) {

    assertNotNull(ChronoUnit.valueOf(argument));
}
```
```java
public class ToStringArgumentConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) {
        assertEquals(String.class, targetType, "Can only convert to String");
        if (source instanceof Enum<?>) {
            return ((Enum<?>) source).name();
        }
        return String.valueOf(source);
    }
}
```
If the converter is only meant to convert one type to another, you can extend TypedArgumentConverter to avoid boilerplate type checks.
```java
public class ToLengthArgumentConverter extends TypedArgumentConverter<String, Integer> {

    protected ToLengthArgumentConverter() {
        super(String.class, Integer.class);
    }

    @Override
    protected Integer convert(String source) {
        return source.length();
    }

}
```
```java
@ParameterizedTest
@ValueSource(strings = { "01.01.2017", "31.12.2017" })
void testWithExplicitJavaTimeConverter(
        @JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {

    assertEquals(2017, argument.getYear());
}
```

---

## Argument Aggregation
By default, each argument provided to a @ParameterizedTest method corresponds to a single method parameter. 
Consequently, argument sources which are expected to supply a large number of arguments can lead to large 
method signatures.
```java
@ParameterizedTest
@CsvSource({
    "Jane, Doe, F, 1990-05-20",
    "John, Doe, M, 1990-10-22"
})
void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
    Person person = new Person(arguments.getString(0),
                               arguments.getString(1),
                               arguments.get(2, Gender.class),
                               arguments.get(3, LocalDate.class));

    if (person.getFirstName().equals("Jane")) {
        assertEquals(Gender.F, person.getGender());
    }
    else {
        assertEquals(Gender.M, person.getGender());
    }
    assertEquals("Doe", person.getLastName());
    assertEquals(1990, person.getDateOfBirth().getYear());
}
```

---

## Custom Aggregators
```java
@ParameterizedTest
@CsvSource({
    "Jane, Doe, F, 1990-05-20",
    "John, Doe, M, 1990-10-22"
})
void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) Person person) {
    // perform assertions against person
}
```
```java
public class PersonAggregator implements ArgumentsAggregator {
    @Override
    public Person aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
        return new Person(arguments.getString(0),
                          arguments.getString(1),
                          arguments.get(2, Gender.class),
                          arguments.get(3, LocalDate.class));
    }
}
```
If you find yourself repeatedly declaring @AggregateWith(MyTypeAggregator.class) for multiple parameterized 
test methods across your codebase, you may wish to create a custom composed annotation such as @CsvToMyType 
that is meta-annotated with @AggregateWith(MyTypeAggregator.class). The following example demonstrates this 
in action with a custom @CsvToPerson annotation.
```java
@ParameterizedTest
@CsvSource({
    "Jane, Doe, F, 1990-05-20",
    "John, Doe, M, 1990-10-22"
})
void testWithCustomAggregatorAnnotation(@CsvToPerson Person person) {
    // perform assertions against person
}
```
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@AggregateWith(PersonAggregator.class)
public @interface CsvToPerson {
}
```

---

## Customizing Display Names
```java
@DisplayName("Display name of container")
@ParameterizedTest(name = "{index} ==> the rank of ''{0}'' is {1}")
@CsvSource({ "apple, 1", "banana, 2", "'lemon, lime', 3" })
void testWithCustomDisplayNames(String fruit, int rank) {
}
```
Please note that name is a MessageFormat pattern. Thus, a single quote (') needs to be represented as a doubled 
single quote ('') in order to be displayed.


Placeholder	Description

DisplayName
the display name of the method

{index}
the current invocation index (1-based)

{arguments}
the complete, comma-separated arguments list

{argumentsWithNames}
the complete, comma-separated arguments list with parameter names

{0}, {1}, …​
an individual argument

---

## Annotations

Annotation	Description
@Test

Denotes that a method is a test method. Unlike JUnit 4’s @Test annotation, this annotation does not declare any 
attributes, since test extensions in JUnit Jupiter operate based on their own dedicated annotations. Such methods 
are inherited unless they are overridden.

@ParameterizedTest

Denotes that a method is a parameterized test. Such methods are inherited unless they are overridden.

@RepeatedTest

Denotes that a method is a test template for a repeated test. Such methods are inherited unless they are overridden.

@TestFactory

Denotes that a method is a test factory for dynamic tests. Such methods are inherited unless they are overridden.

@TestTemplate

Denotes that a method is a template for test cases designed to be invoked multiple times depending on the number 
of invocation contexts returned by the registered providers. Such methods are inherited unless they are overridden.

@TestMethodOrder

Used to configure the test method execution order for the annotated test class; similar to JUnit 4’s @FixMethodOrder. 
Such annotations are inherited.

@TestInstance

Used to configure the test instance lifecycle for the annotated test class. Such annotations are inherited.

@DisplayName

Declares a custom display name for the test class or test method. Such annotations are not inherited.

@DisplayNameGeneration

Declares a custom display name generator for the test class. Such annotations are inherited.

@BeforeEach

Denotes that the annotated method should be executed before each @Test, @RepeatedTest, @ParameterizedTest, or 
@TestFactory method in the current class; analogous to JUnit 4’s @Before. Such methods are inherited unless 
they are overridden.

@AfterEach

Denotes that the annotated method should be executed after each @Test, @RepeatedTest, @ParameterizedTest, or 
@TestFactory method in the current class; analogous to JUnit 4’s @After. Such methods are inherited unless 
they are overridden.

@BeforeAll

Denotes that the annotated method should be executed before all @Test, @RepeatedTest, @ParameterizedTest, 
and @TestFactory methods in the current class; analogous to JUnit 4’s @BeforeClass. Such methods are 
inherited (unless they are hidden or overridden) and must be static (unless the "per-class" test instance 
lifecycle is used).

@AfterAll

Denotes that the annotated method should be executed after all @Test, @RepeatedTest, @ParameterizedTest, 
and @TestFactory methods in the current class; analogous to JUnit 4’s @AfterClass. Such methods are 
inherited (unless they are hidden or overridden) and must be static (unless the "per-class" test instance 
lifecycle is used).

@Nested

Denotes that the annotated class is a non-static nested test class. @BeforeAll and @AfterAll methods cannot 
be used directly in a @Nested test class unless the "per-class" test instance lifecycle is used. Such 
annotations are not inherited.

@Tag

Used to declare tags for filtering tests, either at the class or method level; analogous to test groups 
in TestNG or Categories in JUnit 4. Such annotations are inherited at the class level but not at the method level.

@Disabled

Used to disable a test class or test method; analogous to JUnit 4’s @Ignore. Such annotations are not inherited.

@Timeout

Used to fail a test, test factory, test template, or lifecycle method if its execution exceeds a given 
duration. Such annotations are inherited.

@ExtendWith

Used to register extensions declaratively. Such annotations are inherited.

@RegisterExtension

Used to register extensions programmatically via fields. Such fields are inherited unless they are shadowed.

@TempDir

Used to supply a temporary directory via field injection or parameter injection in a lifecycle method or test 
method; located in the org.junit.jupiter.api.io package.