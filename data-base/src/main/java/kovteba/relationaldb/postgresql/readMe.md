# PostgreSQL

- [](#)
- [Удаление PostgreSQL](#Удаление-PostgreSQL)
- [Переключение БД](#Переключение-БД)
- [Show all tables in DB](#Show-all-tables-in-DB)
- [Install PostgreSQL](#Install-PostgreSQL)
- [Установка пароля для пользователя postgres](#Установка-пароля-для-пользователя-postgres)
- [Список пользователей](#Список-пользователей)
- [Список баз данных](#Список-баз-данных)
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