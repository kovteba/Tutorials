# MySQL

- [Установка MySQL Server](#Установка-MySQL-Server)
- [Настройка аутентификации и привилегий](#Настройка-аутентификации-и-привилегий)
- [Проверка состояния](#Проверка-состояния)
- [Запуск MySQL server](#Запуск-MySQL-server)
- [Остановка MySQL server](#Остановка-MySQL-server)
- [MySQl Versoin](#MySQl-Version)
- [Создание пользователя](#Создание-пользователя)
- [Переименования таблицы](#Переименования-таблицы)
- [Добавить столбец](#Добавить-столбец)
- [Delete column](#Delete-column)
- [Add row](#Add-row)
- [Update row](#Update-row)
- [Delete row](#Delete-row)
- [LIMIT](#LIMIT)
- [](#)

## Установка MySQL Server
```
sudo apt update
sudo apt install mysql-server
sudo mysql_secure_installation
```

## Настройка аутентификации и привилегий
На серверах с Ubuntu, использующей MySQL 5.7 (и более поздние версии), пользователь `root` в `MySQL` по умолчанию 
аутентифицируется с помощью плагина `auth_socket`, а не по паролю. Это в целом более безопасно и удобно во многих 
случаях, но не в случае, когда вам необходимо организовать доступ к `MySQL` со стороны сторонней программы, 
например, phpMyAdmin.  
Для того, чтобы пользователь `root` в `MySQL` мог использовать пароль для входа в систему вам необходимо изменить 
метод аутентификации с `auth_socket` на `mysql_native_password`. Для этого войдите в оболочку MySQL следующей командой:  
```
sudo mysql
```
Далее проверьте, какой метод аутентификации используется для каждого из ваших пользователей MySQL:
```mysql
SELECT user,authentication_string,plugin,host FROM mysql.user;
```
В этом примере ваш пользователь root использует аутентификацию с помощью плагина auth_socket. Для изменения этой 
настройки на использование пароля используйте следующую команду `ALTER USER`. Не забудьте изменить password на 
ваш сильный пароль:
```mysql
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
```
Далее выполните команду `FLUSH PRIVILEGES`, которая применит внесённые изменения:
```mysql
FlUSH PRIVILEGES:
```

## Проверка состояния
```
systemctl status mysql.service
q = exit
```

## Запуск MySQL server
```
$ /etc/init.d/mysql start
```

## Остановка MySQL server
```
$ /etc/init.d/mysql stop
sudo /etc/init.d/mysql stop
```

## MySQl Version
```
mysql --version
```

## Создание пользователя
```
CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';
```

## Переименования таблицы
```mysql
ALTER TABLE oldname RENAME newname
```
## Добавить столбец
```mysql
ALTER TABLE nameTable ADD nameColumn typeColumn;
```

## Delete column
```mysql
ALTER TABLE nameTable DROP nameColumn;
```

## Add row
```mysql
INSERT INTO nameTable (value, .....) values(value,...);
```

## Update row
```mysql
UPDATE nameTable SET nameField = 'value', ... WHERE id=value;
```

## Delete row
```mysql
DELETE FROM nameTable WHERE id=value;
```

## LIMIT
```mysql
SELECT * FROM nameTable WHERE id > 5 LIMIT 10
```
Данным запросом мы получим 10 первых записей. Все остальные отпадут. 
```mysql
SELECT * FROM nameTable WHERE id > 5 LIMIT 10, 20
```
начиная с 10-го номера включительно в количестве 20-ти штук.



