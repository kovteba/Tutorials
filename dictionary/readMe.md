## Dictionary

- [BDD (Behavior-driven development)](#BDD-(Behavior-driven-development))
- [TDD (Test driven development)](#TDD-(Test-driven-development))
- [](#)
- [](#)
- [](#)

---

### BDD (Behavior driven development)
__BDD__ - Основной идеей данной методологии является совмещение в процессе разработки чисто технических 
интересов и интересов бизнеса, позволяя тем самым управляющему персоналу и программистам говорить на 
одном языке. Для общения между этими группами персонала используется предметно-ориентированный язык, 
основу которого представляют конструкции из естественного языка, понятные неспециалисту, обычно 
выражающие поведение программного продукта и ожидаемые результаты.

Следующий пример демонстрирует спецификацию поведения с использованием языка Gherkin.   
```
Story: Returns go to stock

As a store owner
In order to keep track of stock
I want to add items back to stock when they're returned.

Scenario 1: Refunded items should be returned to stock
Given that a customer previously bought a black sweater from me
And I have three black sweaters in stock.
When they return the black sweater for a refund
Then I should have four black sweaters in stock.

Scenario 2: Replaced items should be returned to stock
Given that a customer previously bought a blue garment from me
And I have two blue garments in stock
And three black garments in stock.
When they return the blue garment for a replacement in black
Then I should have three blue garments in stock
And two black garments in stock.
```

---

### TDD (Test driven development)
__TDD__ - техника разработки программного обеспечения, которая основывается на повторении очень коротких 
циклов разработки: сначала пишется тест, покрывающий желаемое изменение, затем пишется код, который 
позволит пройти тест, и под конец проводится рефакторинг нового кода к соответствующим стандартам.

---

### 


---

### 


---