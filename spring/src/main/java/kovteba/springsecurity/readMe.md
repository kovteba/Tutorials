# Spring Security

- [Безопасность на уровне методов](#Безопасность-на-уровне-методов)
    - [@Secured](#@Secured)
    - [@PreAuthorize](#@PreAuthorize)
    - [@PostAuthorize](#@PostAuthorize)
    - [](#)
- [](#)
- [](#)

---

- [Расскажите о Spring Security.](#Расскажите-о-Spring-Security.)

---
## Безопасность на уровне методов

### @Secured
`@Secured` - Используется чтобы определить привилегию, которой должен обладать пользователь, чтобы вызвать 
аннотированный метод.
```java
@Secured("ROLE_ADMIN")
public void changeSomeInformation(){
   // do something
}
```

### @PreAuthorize
`@PreAuthorize`- Ограничивает доступ к методам перед их вызовом, опираясь на результат вычисления выражения.

### @PostAuthorize
`@PostAuthorize` - Позволяет вызывать методы, но возбуждает исключение, если выражение возвращает значение false.

---

---

## Расскажите о Spring Security.
Проект Spring Security предоставляет широкие возможности для защиты приложения. Кроме стандартных настроек для 
аутентификации, авторизации и распределения ролей и маппинга доступных страниц, ссылок и т.п., предоставляет 
защиту от различных вариантов атак (например CSRF). Имеет множество различных настроек, но остается легким в 
использовании.