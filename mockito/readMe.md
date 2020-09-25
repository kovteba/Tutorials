## Mockito

- [Lambda](#Lambda)
- [](#)
- [](#)
- [](#)

---

### Lambda
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

###


---

###


---

###


---

###


---

###


---

###


---

###


---

###


---