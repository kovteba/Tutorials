## Mockito

- [Lambda](#Lambda)
- [Mock Interface](#Mock-Interface)
- [Mock class](#Mock-class)
- [Argument matchers](#Argument-matchers)
- [Verifying exact number of invocations ](#Verifying-exact-number-of-invocations)
- [Stubbing void methods with exceptions](#Stubbing-void-methods-with-exceptions)
- [Verification in order](#Verification-in-order)
- [Never happened on mock](#Never-happened-on-mock)
- [Finding redundant invocations](#Finding-redundant-invocations)
- [@Mock annotation](#@Mock-annotation)
- [Iterator style stubbing](#Iterator-style-stubbing)
- [Stubbing with callbacks](#Stubbing-with-callbacks)
- [Spying on real objects](#Spying-on-real-objects)
- [Changing default return](#Changing-default-return)
- [Capturing arguments for further assertions](#Capturing-arguments-for-further-assertions)
- [Real partial mocks](#Real-partial-mocks)
- [Resetting mocks](#Resetting-mocks)
- [@Captor, @Spy, @InjectMocks](#@Captor,-@Spy,-@InjectMocks)
- [Verification with timeout](#Verification-with-timeout)
- [One-liner stubs](#One-liner-stubs)

---

## Lambda
```java
public class ArgumentMatcherWithLambdaUnitTest {

    @Test
    public void whenPersonWithJob__thenIsNotEntitled() {
        Person peter = new Person("Peter");
        Person linda = new Person("Linda");

        JobPosition teacher = new JobPosition("Teacher");

        when(jobService.findCurrentJobPosition(
          ArgumentMatchers.argThat(p -> p.getName().equals("Peter"))))
          .thenReturn(Optional.of(teacher));

        assertTrue(unemploymentService.personIsEntitledToUnemploymentSupport(linda));
        assertFalse(unemploymentService.personIsEntitledToUnemploymentSupport(peter));
    }
}
```

---

## Mock Interface
[Mock Interface](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#verification)

---

## Mock class
[Mock class](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#stubbing)

By default, for all methods that return a value, a mock will return either null, a primitive/primitive wrapper value, 
or an empty collection, as appropriate. For example 0 for an int/Integer and false for a boolean/Boolean.

---

## Argument matchers
[Argument matchers](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#argument_matchers)

---

## Verifying exact number of invocations
[Verifying exact number of invocations](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#exact_verification)

---

## Stubbing void methods with exceptions
[Stubbing void methods with exceptions](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#stubbing_with_exceptions)
[Do method family](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#do_family_methods_stubs)

---

## Verification in order
[Verification in order](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#in_order_verification)

---

## Never happened on mock
[Never happened on mock](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#never_verification)

---

## Finding redundant invocations
[Finding redundant invocations](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#finding_redundant_invocations)

---

## @Mock annotation
[@Mock annotation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#mock_annotation)

---

## Iterator style stubbing
[Iterator style stubbing](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#stubbing_consecutive_calls)

---

## Stubbing with callbacks
[Stubbing with callbacks](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#answer_stubs)

---

## Spying on real objects
[Spying on real objects](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#spy)

---

## Changing default return
[Changing default return](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#defaultreturn)

---

## Capturing arguments for further assertions
[Capturing arguments for further assertions](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#captors)

---

## Real partial mocks
[Real partial mocks](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#partial_mocks)

---

## Resetting mocks
[Resetting mocks](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#resetting_mocks)

---

## 18

## 19

## 20

---

## @Captor, @Spy, @InjectMocks
[@Captor, @Spy, @InjectMocks](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#injectmocks_annotation)

---

## Verification with timeout
[Verification with timeout](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#verification_timeout)

---

## 23

---

## One liner stubs
[One liner stubs](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#one_liner_stub)

---

## 25

## 26












