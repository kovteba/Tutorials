# EXAMPLES
# Date Classes

- [Current date](#Current-date)
- [Plus one week](#Plus-one-week)
- [Plus one month](#Plus-one-month)
- [Plus one year](#Plus-one-year)
- [Plus ten years](#Plus-ten-years)
- [Next Tuesday](#Next-Tuesday)
- [Get second saturday in this month](#Get-second-saturday-in-this-month)
- [Current date in millisec](#Current-date-in-millisec)
- [Current date in millisec by localTime](#Current-date-in-millisec-by-localTime)


## Current date
```java
LocalDate today = ZonedDateTime.now().toLocalDate();
```

## Plus one week
```java
LocalDate today = ZonedDateTime.now().toLocalDate();
LocalDate plusOneWeek = today.plus(1, ChronoUnit.WEEKS);
```

## Plus one month
```java
LocalDate today = ZonedDateTime.now().toLocalDate();
LocalDate plusOneMonth = today.plus(1, ChronoUnit.MONTHS);
```

## Plus one year
```java
LocalDate today = ZonedDateTime.now().toLocalDate();
LocalDate plusOneYear = today.plus(1, ChronoUnit.YEARS);
```

## Plus ten years
```java
LocalDate today = ZonedDateTime.now().toLocalDate();
LocalDate plusTenYears = today.plus(1, ChronoUnit.DECADES);
```

## Next Tuesday
```java
LocalDate today = ZonedDateTime.now().toLocalDate();
LocalDate nextTuesday = today.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
```

## Get second saturday in this month
```java
LocalDate today = ZonedDateTime.now().toLocalDate();
LocalDate firstInYear = LocalDate.of(today.getYear(), today.getMonth(), 1);
LocalDate secondsSaturday = firstInYear.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)).with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
```

## Current date in millisec
```java
Date currentDate = new Date();
Instant now = currentDate.toInstant();
```
## Current date in millisec by localTime
```java
Date currentDate = new Date();
Instant now = currentDate.toInstant();
ZoneId currentZone = ZoneId.systemDefault();
LocalDateTime localDateTime = LocalDateTime.ofInstant(now, currentZone);
```