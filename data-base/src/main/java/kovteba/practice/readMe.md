# Practice

- [MOVIES BOXOFFICE](#MOVIES)
    - [Find the movie with a row id of 6](#Find-the-movie-with-a-row-id-of-6)
    - [BETWEEN](#BETWEEN)
    - [NOT BETWEEN](#NOT-BETWEEN)
    - [<=](#<=)
    - [LIKE](#LIKE)
    - [DISTINCT ORDER BY](#DISTINCT-ORDER-BY)
    - [LIMIT DESC](#LIMIT-DESC)
    - [Первые 5 по алфавиту](#Первые-5-по-алфавиту)
    - [Следующие 5 по алфавиту](#Следующие-5-по-алфавиту)
    - [Find the domestic and international sales for each movie](#Find-the-domestic-and-international-sales-for-each-movie)
    - [for each movie that did better internationally rather than domestically](#for-each-movie-that-did-better-internationally-rather-than-domestically)
    - [all the movies by their ratings in descending order](#all-the-movies-by-their-ratings-in-descending-order)
    - [List all movies and their combined sales in millions of dollars](#List-all-movies-and-their-combined-sales-in-millions-of-dollars)
    - [List all movies and their ratings in percent](#List-all-movies-and-their-ratings-in-percent)
    - [List all movies that were released on even number years](#List-all-movies-that-were-released-on-even-number-years)
    - [Find the number of movies each director has directed](#Find-the-number-of-movies-each-director-has-directed)
    - [Find the total domestic and international sales that can be attributed to each director](#Find-the-total-domestic-and-international-sales-that-can-be-attributed-to-each-director)
    - [remove all movies that were released before 2005](#remove-all-movies-that-were-released-before-2005)
    - [Add another column named Language ](#Add-another-column-named-Language)
    - [](#)


- [North american cities](#North-american-cities)
    - [From north to south](#From-north-to-south)
    - [West of Chicago, ordered from west to east](#West-of-Chicago,-ordered-from-west-to-east)
    - [List the two largest cities in Mexico (by population) ](#List-the-two-largest-cities-in-Mexico-(by-population))
    - [List the third and fourth largest cities (by population) in the United States and their population](#List-the-third-and-fourth-largest-cities-(by-population)-in-the-United-States-and-their-population)
    
    
- [Buildings Employees](#Buildings-Employees)
    - [all buildings and the distinct employee roles in each building (including empty buildings)](#all-buildings-and-the-distinct-employee-roles-in-each-building-including-empty-buildings)
    - [Find the names of the buildings that hold no employees ](#Find-the-names-of-the-buildings-that-hold-no-employees)
    - [longest time that an employee has been at the studio ](#longest-time-that-an-employee-has-been-at-the-studio)
    - [For each role, find the average number of years employed by employees in that role](#For-each-role,-find-the-average-number-of-years-employed-by-employees-in-that-role)
    - [Find the total number of employee years worked in each building](#Find-the-total-number-of-employee-years-worked-in-each-building)
    - [Find the number of Artists in the studio](#Find-the-number-of-Artists-in-the-studio)
    - [Find the number of Employees of each role in the studio](#Find-the-number-of-Employees-of-each-role-in-the-studio)
    - [Find the total number of years employed by all Engineers](#Find-the-total-number-of-years-employed-by-all-Engineers)
    - [](#)
    
- [Sourses](#sourses)

## MOVIES
```mysql
Table: Movies
+----+--------------------+----------------+--------+---------------+
|Id  |Title               |Director        |Year    |Length_minutes |
+----+--------------------+----------------+--------+---------------+
|1   |Toy Story           |John Lasseter   |1995    |81             |
|2   |A Bugs Life         |John Lasseter   |1998    |95             |
|3   |Toy Story 2         |John Lasseter   |1999    |93             |
|4   |Monsters, Inc.      |Pete Docter     |2001    |92             |
|5   |Finding Nemo        |Andrew Stanton  |2003    |107            |
|6   |The Incredibles     |Brad Bird       |2004    |116            |
|7   |Cars                |John Lasseter   |2006    |117            |
|8   |Ratatouille         |Brad Bird       |2007    |115            |
|9   |WALL-E              |Andrew Stanton  |2008    |104            |
|10  |Up                  |Pete Docter     |2009    |101            |
|11  |Toy Story 3         |Lee Unkrich     |2010    |103            |
|12  |Cars 2              |John Lasseter   |2011    |120            |
|13  |Brave               |Brenda Chapman  |2012    |102            |
|14  |Monsters University |Dan Scanlon     |2013    |110            |
+----+--------------------+----------------+--------+---------------+

Table: Boxoffice
+--------+-------+---------------+-------------------+
|Movie_id|Rating |Domestic_sales |International_sales|
+--------+-------+---------------+-------------------+
|5       |8.2    |380843261      |555900000          |
|14      |7.4    |268492764      |475066843          |
|8       |8      |206445654      |417277164          |
|12      |6.4    |191452396      |368400000          |
|3       |7.9    |245852179      |239163000          |
|6       |8      |261441092      |370001000          |
|9       |8.5    |223808164      |297503696          |
|11      |8.4    |415004880      |648167031          |
|1       |8.3    |191796233      |170162503          |
|7       |7.2    |244082982      |217900167          |
|10      |8.3    |293004164      |438338580          |
|4       |8.1    |289916256      |272900000          |
|2       |7.2    |162798565      |200600000          |
|13      |7.2    |237283207      |301700000          |
+--------+-------+---------------+-------------------+
```

## Find the movie with a row id of 6
```mysql
SELECT * FROM movies where id = 6;
```

## BETWEEN
```mysql
SELECT * FROM movies where year between 2000 and 2010;
```

## NOT BETWEEN
```mysql
SELECT * FROM movies where year not between 2000 and 2010;
```

## <=
```mysql
SELECT title, year FROM movies
WHERE year <= 2003;
```

## LIKE
```mysql
SELECT * FROM movies where title like 'Toy Story%';
```
RESULT: Toy Story, Toy Story 2, Toy Story 3
Без `%` ищет четкое совпадение

## DISTINCT ORDER BY
```mysql
SELECT DISTINCT director FROM movies
ORDER BY director ASC;
```
Выборает все director без дубликатов и выводит их в алфавитном порядке

## LIMIT DESC
```mysql
SELECT title, year FROM movies
ORDER BY year DESC
LIMIT 4;
```
Выберет 4 записи выведет в обратном порядкке(2013, 2012, 2011, 2010 .....)

## Первые 5 по алфавиту 
```mysql
SELECT title FROM movies
ORDER BY title ASC
LIMIT 5;
```

## Следующие 5 по алфавиту
```mysql
SELECT title FROM movies
ORDER BY title ASC
LIMIT 5 OFFSET 5;
```

## Find the domestic and international sales for each movie
```mysql
SELECT title, domestic_sales, international_sales 
FROM movies
  JOIN boxoffice
    ON movies.id = boxoffice.movie_id;
```

## for each movie that did better internationally rather than domestically
```mysql
SELECT title, domestic_sales, international_sales
FROM movies
  JOIN boxoffice
    ON movies.id = boxoffice.movie_id
WHERE international_sales > domestic_sales;
```

## all the movies by their ratings in descending order
```mysql
SELECT title, rating
FROM movies
  JOIN boxoffice
    ON movies.id = boxoffice.movie_id
ORDER BY rating DESC;
```

## List all movies and their combined sales in millions of dollars
```mysql
SELECT title, (domestic_sales + international_sales) / 1000000 AS gross_sales_millions
FROM movies
  JOIN boxoffice
    ON movies.id = boxoffice.movie_id;
```

## List all movies and their ratings in percent
```mysql
SELECT title, rating * 10 AS rating_percent
FROM movies
  JOIN boxoffice
    ON movies.id = boxoffice.movie_id;
```

## List all movies that were released on even number years
```mysql
SELECT title, year
FROM movies
WHERE year % 2 = 0;
```

## Find the number of movies each director has directed
```mysql
SELECT director, COUNT(id) as Num_movies_directed
FROM movies
GROUP BY director;
```

## Find the total domestic and international sales that can be attributed to each director
```mysql
SELECT director, SUM(domestic_sales + international_sales) as Cumulative_sales_from_all_movies
FROM movies
    INNER JOIN boxoffice
        ON movies.id = boxoffice.movie_id
GROUP BY director;
```

## remove all movies that were released before 2005
```mysql
DELETE FROM movies
where year < 2005;
```

## Add another column named Language
```mysql
ALTER TABLE Movies
  ADD COLUMN Language TEXT DEFAULT "English";
```


## North american cities
```mysql
Table: North_american_cities
+--------------------+-------------------+---------------+---------------+---------------+
|City                |   Country         |   Population  |   Latitude    |   Longitude   |
+--------------------+-------------------+---------------+---------------+---------------+
|Guadalajara         |   Mexico          |   1500800     |   20.659699   |   -103.349609 |
|Toronto             |   Canada          |   2795060     |   43.653226   |   -79.383184  |
|Houston             |   United States   |   2195914     |   29.760427   |   -95.369803  |
|New York            |   United States   |   8405837     |   40.712784   |   -74.005941  |
|Philadelphia        |   United States   |   1553165     |   39.952584   |   -75.165222  |
|Havana              |   Cuba            |   2106146     |   23.05407    |   -82.345189  |
|Mexico City         |   Mexico          |   8555500     |   19.432608   |   -99.133208  |
|Phoenix             |   United States   |   1513367     |   33.448377   |   -112.074037 |
|Los Angeles         |   United States   |   3884307     |   34.052234   |   -118.243685 |
|Ecatepec de Morelos |   Mexico          |   1742000     |   19.601841   |   -99.050674  |
|Montreal            |   Canada          |   1717767     |   45.501689   |   -73.567256  |
|Chicago             |   United States   |   2718782     |   41.878114   |   -87.629798  |
+--------------------+-------------------+---------------+---------------+---------------+
```

## From north to south
```mysql
SELECT city, latitude FROM north_american_cities
WHERE country = "United States"
ORDER BY latitude DESC;
```
## West of Chicago, ordered from west to east
```mysql
SELECT city, longitude FROM north_american_cities
WHERE longitude < -87.629798
ORDER BY longitude ASC;
```

## List the two largest cities in Mexico (by population) 
```mysql
SELECT city, population FROM north_american_cities
WHERE country LIKE "Mexico"
ORDER BY population DESC
LIMIT 2;
```

## List the third and fourth largest cities (by population) in the United States and their population
```mysql
SELECT city, population FROM north_american_cities
WHERE country LIKE "United States"
ORDER BY population DESC
LIMIT 2 OFFSET 2;
```

## Buildings Employees
```mysql
Table: Buildings
+----------------+-----------+
|Building_name   |Capacity   |
+----------------+-----------+
|1e              |24         |
|1w              |32         |
|2e              |16         |
|2w              |20         |
+----------------+-----------+
Table: Employees
+-----------+-----------+-----------+---------------+
|Role       |Name       |Building   |Years_employed |
+-----------+-----------+-----------+---------------+
|Engineer   |Becky A.   |1e         |4              |
|Engineer   |Dan B.     |1e         |2              |
|Engineer   |Sharon F.  |1e         |6              |
|Engineer   |Dan M.     |1e         |4              |
|Engineer   |Malcom S.  |1e         |1              |
|Artist     |Tylar S.   |2w         |2              |
|Artist     |Sherman D. |2w         |8              |
|Artist     |Jakob J.   |2w         |6              |
|Artist     |Lillia A.  |2w         |7              |
|Artist     |Brandon J. |2w         |7              |
|Manager    |Scott K.   |1e         |9              |
|Manager    |Shirlee M. |1e         |3              |
|Manager    |Daria O.   |2w         |6              |
|Engineer   |Yancy I.   |2w         |0              |
|Artist     |Oliver P.  |2w         |0              |
+-----------+-----------+-----------+---------------+
```

## all buildings and the distinct employee roles in each building including empty buildings
```mysql
SELECT DISTINCT building_name, role 
FROM buildings 
  LEFT JOIN employees
    ON building_name = building;
```

## Find the names of the buildings that hold no employees
```mysql
SELECT DISTINCT building_name
FROM buildings 
  LEFT JOIN employees
    ON building_name = building
WHERE role IS NULL;
```

## longest time that an employee has been at the studio
```mysql
SELECT max(Years_employed) AS max_years_employeed from employees;
```

## For each role, find the average number of years employed by employees in that role
```mysql
SELECT role, AVG(years_employed) as Average_years_employed
FROM employees
GROUP BY role;
```

## Find the total number of employee years worked in each building
```mysql
SELECT building, SUM(years_employed) as Total_years_employed
FROM employees
GROUP BY building;
```

## Find the number of Artists in the studio
```mysql
SELECT role, COUNT(*) as Number_of_artists
FROM employees
WHERE role = 'Artist';
```

## Find the number of Employees of each role in the studio
```mysql
SELECT role, COUNT(*)
FROM employees
GROUP BY role;
```

## Find the total number of years employed by all Engineers
```mysql
SELECT role, SUM(Years_employed) as Number_of_artists
FROM employees
WHERE Role = 'Engineer';
```

## 



## Sourses
- [sqlbolt](https://sqlbolt.com/)
- [sql-tutorial](http://www.sql-tutorial.ru/)

















