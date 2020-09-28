## javax 

- [@DecimalMin](#@DecimalMin)
- [@DecimalMax](#@DecimalMax)
- [@Digits](#@Digits)
- [](#)
- [](#)

---

### @DecimalMin
The annotated element must be a number whose value is higher or equal to the specified minimum. `@DecimalMin` 
has an attribute `inclusive` that indicates whether the specified minimum value is inclusive or exclusive.
```java
@DecimalMin(value = "0.0", inclusive = false)
private BigDecimal price;
```

---

### @DecimalMax

@DecimalMax is the counterpart of @DecimalMin. The annotated element must be a number whose value is lower or 
equal to the specified maximum. @DecimalMax has an inclusive attribute that specifies whether the specified 
maximum value is inclusive or exclusive.

Also, @Min and @Max accept long values only. In @DecimalMin and @DecimalMax, we can specify the value in 
string format, which can be of any numeric type.

---

### @Digits
In many cases, we need to validate the number of digits in the integral part and fraction part of a 
decimal number.

The @Digit annotation has two attributes, integer and fraction, for specifying the number of allowed 
digits in the integral part and fraction part of the number.

As per the official documentation, integer allows us to specify the maximum number of integral digits 
accepted for this number. But this is true only for non-decimal numbers. For decimal numbers, it 
checks the exact number of digits in an integral part of the number. We will see this in our test case.

Similarly, the fraction attribute allows us to specify the maximum number of fractional digits 
accepted for this number.
```java
@Digits(integer=3, fraction=2)
private BigDecimal price;
```