## JPA Specification, QueryDSL

- [About](#About)
- [Example](#Example)
- [](#)
- [](#)

---

### About
Spring Data JPA определяет интерфейс `Specification` для создания таких предикатов Criteria API, которые можно 
было бы использовать повторно. Интерфейс определяет ровно один метод, который должен вернуть предикат:
```java
public interface Specification<T> {
  Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder);
}
```
Тип `T`  в данном случае указывает, к какой сущности относится спецификация.

Сама по себе спецификация не очень полезна, а чтобы использовать её с репозиториями, необходимо чтоб репозиторий 
имел в списке предков интерфейс `JpaSpecificationExecutor<T>` , где `T` — тип сущности, с которой работает репозиторий. 

__Интерфейс JpaSpecificationExecutor<T> определяет несколько методов:__   
- `T FindOne(Predicate)` — возвращает один объект, соответствующий условия
- `Iterable<T> findAll(Predicate)` — возвращает несколько объектов, соответствующих условию. Обратите внимание, 
    что возвращается всегда `Iterable<T>`, без возможности уточнить тип
- `long count(Predicate)` — возвращает количество объектов в базе данных, соответствующих условию
- `boolean exists(Predicate)` — сообщает, есть ли в базе данных объект соответствующий условию

---

### Example
```java
public class SearchCriteria {
    private String key;
    private String value;
    private String operation;
}
```
```java
public class BookSpecification implements Specification<Book> {
    private SearchCriteria criteria;
    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase("equal")) {
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
        return null;
    }
}
```
```java
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {}
```
```java
@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void init() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(null, "title", new byte[10],"author", "description", 12, 12.12, BookAvailability.AVAILABLE));
        books.add(new Book(null, "title1", new byte[10],"author", "description", 12, 12.12, BookAvailability.AVAILABLE));
        books.add(new Book(null, "title", new byte[10],"author1", "description", 12, 12.12, BookAvailability.AVAILABLE));
        books.add(new Book(null, "title", new byte[10],"author", "description1", 12, 12.12, BookAvailability.AVAILABLE));
        books.add(new Book(null, "title1", new byte[10],"author1", "description1", 12, 12.12, BookAvailability.AVAILABLE));
        bookRepository.saveAll(books);
    }

    @Test
     void testSpecification(){
        BookSpecification bookSpecification1 = new BookSpecification(new SearchCriteria("author", "author", "equal"));
        BookSpecification bookSpecification2 = new BookSpecification(new SearchCriteria("description", "description1", "equal"));
        List<Book> list = bookRepository.findAll(bookSpecification1.and(bookSpecification2));
        for (Book b : list) {
            System.out.println(b.toString());
        }
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