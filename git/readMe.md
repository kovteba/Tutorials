# GIT

- [Create User](#Create-User)
- [git log](#git-log)
- [git fetch](#git-fetch)
- [git pull](#git-pull)
- [git push](#git-push)
- [Create branch](#Create-branch)
- [Checkout branch](#Checkout-branch)
    - [Create and checkout branch in one command](#Create-and-checkout-branch-in-one-command)
- [git merge](#git-merge)
- [Checkout on different commit](#Checkout-on-different-commit)
- [Cancel or change last commit](#Cancel-or-change-last-commit)
- [git show](#git-show)
- [git reset](#git-reset)
- [git commit](#git-commit)
- [](#)
- [](#)




## Create User
>$ git config --global user.name `<name>`

`<name>` - Имя пользователя 

>$ git config --global user.email `<email>`

`<email>` - Email пользователя

## git log
>commit 256222386bd7a459a888331d8f05090e47332234 (HEAD -> master, origin/master)   
>Author: Kovteba <kovteba@gmail.com>   
>Date:   Mon Jun 22 17:20:59 2020 +0300       
>init commit   

__Параметры для удобного просмотра лога:__
- `--author="name"` — выводит коммиты, сделанные конкретным человеком
- `--name-only` — выводит только названия изменённых файлов
- `--oneline` — выводит сокращённые данные коммита (в виде одной строки)
- `--graph` — выводит дерево зависимостей для всех коммитов
- `--reverse` — выводит коммиты в обратном хронологическом порядке (сначала старые)
- `--after` — выводит коммиты, сделанные после определённой даты
- `--before` — выводит коммиты, сделанные до определённой даты



## git fetch
Команда `git fetch` связывается с удалённым репозиторием и забирает из него все изменения, которых у вас пока нет и 
сохраняет их локально.

## git pull
Команда `git pull` работает как комбинация команд `git fetch` и `git merge`, т.е. Git вначале забирает изменения из 
указанного удалённого репозитория, а затем пытается слить их с текущей веткой.

## git push
Команда `git push` используется для установления связи с удалённым репозиторием, вычисления локальных изменений 
отсутствующих в нём, и собственно их передачи в вышеупомянутый репозиторий. Этой команде нужно право на запись 
в репозиторий, поэтому она использует аутентификацию.


## Create branch
>git branch `<branch_name>`

## Checkout branch
>git checkout `<branch_name>`

## Create and checkout branch in one command
>git checkout -b `<branch_name>`


## git merge
>git merge `<целевая ветка>`

Делать __merge__ из ветки с изменениями в целевую ветку


## Checkout on different commit
>git checkout `<Commit number>`

## Cancel or change last commit
Добавить изменения в последний комит:  
>git commit -a --amend

Ключ `--amend` (улучшить, в переводе с английского) позволяет добавить к последнему коммиту новые изменения.

>git reset --soft HEAD^

Эта команда отменит последний коммит (но не изменения, которые вы внесли, они сохранятся).

>git reset --hard HEAD^

Удалить комит (но не стоит забывать, что эта команда не просто удалит последний коммит, но и ВСЕ правки, которые 
не были закоммиченны)

Все это работает, если вы не опубликовали свои изменения.


# git show
>git show

```
commit 03c9db53d5369667b5dfef2dad76468585d6a3a2 (HEAD -> master, origin/newBranch, origin/master, newBranch)
Author: kovtebaTest <d.v.kovteba@gmail.com>
Date:   Mon Jun 22 21:15:51 2020 +0300

    clean code in newBranch and print "userToken"

diff --git a/src/main/java/ua/nure/kovteba/finaltask/controller/car/ChangeCarInformation.java b/src/main/java/ua/nure/kovteba/finaltask/controller/car/ChangeCarInformation.java
index 9e84436..aeeaadc 100644
--- a/src/main/java/ua/nure/kovteba/finaltask/controller/car/ChangeCarInformation.java
+++ b/src/main/java/ua/nure/kovteba/finaltask/controller/car/ChangeCarInformation.java
@@ -31,7 +31,6 @@ import java.util.logging.Logger;
 )
 public class ChangeCarInformation  extends HttpServlet {
 
-    //Create logger
     private static Logger log = Logger.getLogger(ChangeCarInformation.class.getName());
 
 
@@ -52,10 +51,8 @@ public class ChangeCarInformation  extends HttpServlet {
     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 
-        //some commit
         String userToken = "0";
-        //new commit
-        //new commit in newBranch
+        System.out.println(userToken);
 
         if (req.getSession().getAttribute("userToken") != null){
             userToken = String.valueOf(req.getSession().getAttribute("userToken"));

```

## git reset
- `git reset --hard {{some-commit-hash}}` — вернуться на определённый коммит в истории. Все изменения, сделанные 
    после этого коммита пропадут.
- `git reset {{some-commit-hash}}` — вернуться на определённый коммит в истории. Все изменения, сделанные после 
    этого коммита, получат состояние «Not staged for commit». Чтобы вернуть их обратно, нужно использовать 
    команды git add и git commit.
- `git reset --soft {{some-commit-hash}}` — вернуться на определённый коммит в истории. Все изменения, 
    сделанные после этого коммита, получат состояние «Staged for commit». Чтобы вернуть их обратно, нужно 
    использовать команду git commit.


## git commit
>git commit -m {{Comment for commit}}




## git remote -v
Запустите в консоли команду `git remote -v` и посмотрите, через какой протокол у вас осуществляется доступ к репозиторию.

Если это https, то будет примерно следующий путь:   
>https://github.com/USERNAME/OTHERREPOSITORY.git

Для ssh будет такой:      
>git@github.com:USERNAME/OTHERREPOSITORY.git















