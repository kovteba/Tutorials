# PostgreSQL

- [](#)
- [Удаление PostgreSQL](#Удаление-PostgreSQL)
- [Переключение БД](#Переключение-БД)
- [Show all tables in DB](#Show-all-tables-in-DB)
- [Install PostgreSQL](#Install-PostgreSQL)
- [Установка пароля для пользователя postgres](#Установка-пароля-для-пользователя-postgres)
- [Список пользователей](#Список-пользователей)
- [Список баз данных](#Список-баз-данных)
- [Как изменить root пароль в PostgreSQL](#Как-изменить-root-пароль-в-PostgreSQL)
- [Как установить PostgreSQL в автозапуск?](#Как-установить-PostgreSQL-в-автозапуск?)
- [Проверяем состояние сервера](#Проверяем-состояние-сервера)
- [Запустить, остановить, перезапустить](#Запустить-остановить-перезапустить)
- [Как посмотреть какая версия PostgreSQL запущена?](#Как-посмотреть-какая-версия-PostgreSQL-запущена?)
- [](#)

---

## Удаление PostgreSQL
```
sudo apt-get --purge remove pgadmin3
sudo apt-get --purge remove postgresql\*

sudo rm -r /etc/postgresql/
sudo rm -r /etc/postgresql-common/
sudo rm -r /var/lib/postgresql/
sudo userdel -r postgres
sudo groupdel postgres
```

---

## Переключение БД
```
\connect (or \c) dbname
```

---   

## Show all tables in DB
```
\dt;
```

---   

## Install PostgreSQL
```
$ sudo apt update
$ sudo apt install postgresql postgresql-contrib
```

---

## Установка пароля для пользователя postgres
Логинемся в postgres:
```sudo su postgres -c psql template```

Меняем пароль:
```ALTER USER postgres with PASSWORD 'password';```

---

## Список пользователей
```
select * from pg_shadow;
```

---

   
## Список баз данных
```
select * from pg_database;
```

---

## Подключение к Postgres в Docker Conteiner
```
psql -U postgres
```

---

### Как изменить root пароль в PostgreSQL
```
$ <strong>/usr/local/pgsql/bin/psql postgres postgres
</strong>Password: (oldpassword)
# <strong>ALTER USER postgres WITH PASSWORD ‘tmppassword’;</strong>
$ <strong>/usr/local/pgsql/bin/psql postgres postgres</strong>
Password: (tmppassword)
```

Изменение пароля для обычного пользователя происходит таким же образом. Пользователь root может поменять пароль любому пользователю.
```
# <strong>ALTER USER username WITH PASSWORD ‘tmppassword’;
</strong>
```

---

### Как установить PostgreSQL в автозапуск?
```
$ su - root
# tar xvfz postgresql-8.3.7.tar.gz
# cd postgresql-8.3.7
# cp contrib/start-scripts/linux /etc/rc.d/init.d/postgresql
# chmod a+x /etc/rc.d/init.d/postgresql
```

---

### Проверяем состояние сервера
```
$ <strong>/etc/init.d/postgresql status
</strong>Password:
pg_ctl: server is running (PID: 6171)
/usr/local/pgsql/bin/postgres “-D” “/usr/local/pgsql/data”
[<strong>Замечание</strong>: Это сообщение говорит о том, что сервер запущен и работате нормально]
$ <strong>/etc/init.d/postgresql status</strong>
Password:
pg_ctl: no server running
[<strong>Замечание</strong>: Это сообщение готоворит о том, что сервер не запущен]
```

---

### Запустить остановить перезапустить
```
# <strong>service postgresql stop
</strong>Stopping PostgreSQL: server stopped
ok
# <strong>service postgresql start</strong>
Starting PostgreSQL: ok
# <strong>service postgresql restart</strong>
Restarting PostgreSQL: server stopped
ok
```

---
 
### Как посмотреть какая версия PostgreSQL запущена?
```
$ <strong>/usr/local/pgsql/bin/psql test
</strong>Welcome to psql 8.3.7, the PostgreSQL interactive terminal.
Type:  \copyright for distribution terms
\h for help with SQL commands
\? for help with psql commands
\g or terminate with semicolon to execute query
\q to quit
test=# <strong>select version();</strong>
version
—————————————————————————————————-
PostgreSQL 8.3.7 on i686-pc-linux-gnu, compiled by GCC gcc (GCC) 4.1.2 20071124 (Red Hat 4.1.2-42)
(1 row)
test=#
```

---

### Как создать пользователя в PostgreSQL?
Для этого существуют два метода..

__Метод 1:__ Создаем пользователя в через PSQL шелл, командой CREATE USER.  

```
# <strong>CREATE USER ramesh WITH password ‘tmppassword’;
</strong>CREATE ROLE
```

__Метод 2:__ Создаем пользователя в через шелл команду createuser.  

```
$ <strong>/usr/local/pgsql/bin/createuser sathiya
</strong>Shall the new role be a superuser? (y/n) n
Shall the new role be allowed to create databases? (y/n) n
Shall the new role be allowed to create more new roles? (y/n) n
CREATE ROLE
```

---

### Получаем список всех баз в Postgresql
```
# <strong>\l</strong> 
List of databases
Name | Owner | Encoding
———-+———-+———-
backup | postgres | UTF8
mydb | ramesh | UTF8
postgres | postgres | UTF8
template0 | postgres | UTF8
template1 | postgres | UTF8
```

--- 

### Как узнать время выполнения запроса?

\timing — после выполения данной команды каждый последующий запрос будет показывать время выполнения.

```
# <strong>\timing
</strong>Timing is on.
# <strong>SELECT * from pg_catalog.pg_attribute ;</strong>
Time: 9.583 ms
```





























