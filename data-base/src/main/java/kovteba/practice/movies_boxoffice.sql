CREATE SCHEMA movies_boxoffice;

CREATE TABLE movies(
    id int PRIMARY KEY,
    title varchar(255),
    director varchar(255),
    year int,
    length_minutes int
);

CREATE TABLE boxoffice(
    movie_id int,
    rating double,
    domestic_sales int,
    international_sales int,
    FOREIGN KEY (movie_id) REFERENCES movies (id)
);

INSERT movies VALUES (1, 'Toy Story', 'John Lasseter', 1995, 81);
INSERT movies VALUES (2, 'A Bugs Life', 'John Lasseter', 1998, 95);
INSERT movies VALUES (3, 'Toy Story 2', 'John Lasseter', 1999, 93);
INSERT movies VALUES (4, 'Monsters, Inc.', 'Pete Docter', 2001, 92);
INSERT movies VALUES (5, 'Finding Nemo', 'Andrew Stanton', 2003, 107);
INSERT movies VALUES (6, 'The Incredibles', 'Brad Bird', 2004, 116);
INSERT movies VALUES (7, 'Cars', 'John Lasseter', 2006, 117);
INSERT movies VALUES (8, 'Ratatouille', 'Brad Bird', 2007, 115);
INSERT movies VALUES (9, 'WALL-E', 'Andrew Stanton', 2008, 104);
INSERT movies VALUES (10, 'Up', 'Pete Docter', 2009, 101);
INSERT movies VALUES (11, 'Toy Story 3', 'Lee Unkrich', 2010, 103);
INSERT movies VALUES (12, 'Cars 2', 'John Lasseter', 2011, 120);
INSERT movies VALUES (13, 'Brave', 'Brenda Chapman', 2012, 102);
INSERT movies VALUES (14, 'Monsters University', 'Dan Scanlon', 2013, 110);

INSERT boxoffice VALUES (5, 8.2, 380843261, 555900000);
INSERT boxoffice VALUES (14, 7.4, 268492764, 475066843);
INSERT boxoffice VALUES (8, 8, 206445654, 417277164);
INSERT boxoffice VALUES (12, 6.4, 191452396, 368400000);
INSERT boxoffice VALUES (3, 7.9, 245852179, 239163000);
INSERT boxoffice VALUES (6, 8, 261441092, 370001000);
INSERT boxoffice VALUES (9, 8.5, 223808164, 297503696);
INSERT boxoffice VALUES (11, 8.4, 415004880, 648167031);
INSERT boxoffice VALUES (1, 8.3, 191796233, 170162503);
INSERT boxoffice VALUES (7, 7.2, 244082982, 217900167);
INSERT boxoffice VALUES (10, 8.3, 293004164, 438338580);
INSERT boxoffice VALUES (4, 8.1, 289916256, 272900000);
INSERT boxoffice VALUES (2, 7.2, 162798565, 200600000);
INSERT boxoffice VALUES (13, 7.2, 237283207, 301700000);



-- INSERT movies(id, title, director, year, length_minutes) VALUES (1, 'Toy Story', 'John Lasseter', 1995, 81);
-- INSERT movies(id, title, director, year, length_minutes) VALUES (2, 'A Bugs Life', 'John Lasseter', 1998, 95);
-- INSERT movies(id, title, director, year, length_minutes) VALUES (3, 'Toy Story 2', 'John Lasseter', 1999, 93);
-- INSERT movies(id, title, director, year, length_minutes) VALUES (4, 'Monsters, Inc.', 'Pete Docter', 2001, 92);
-- INSERT movies(id, title, director, year, length_minutes) VALUES (5, 'Finding Nemo', 'Andrew Stanton', 2003, 107);
-- INSERT movies(id, title, director, year, length_minutes) VALUES (6, 'The Incredibles', 'Brad Bird', 2004, 116);
-- INSERT movies(id, title, director, year, length_minutes) VALUES (7, 'Cars', 'John Lasseter', 2006, 117);
-- INSERT movies(id, title, director, year, length_minutes) VALUES (8, 'Ratatouille', 'Brad Bird', 2007, 115);
-- INSERT movies(id, title, director, year, length_minutes) VALUES (9, 'WALL-E', 'Andrew Stanton', 2008, 104);
-- INSERT movies(id, title, director, year, length_minutes) VALUES (10, 'Up', 'Pete Docter', 2009, 101);
-- INSERT movies(id, title, director, year, length_minutes) VALUES (11, 'Toy Story 3', 'Lee Unkrich', 2010, 103);
-- INSERT movies(id, title, director, year, length_minutes) VALUES (12, 'Cars 2', 'John Lasseter', 2011, 120);
-- INSERT movies(id, title, director, year, length_minutes) VALUES (13, 'Brave', 'Brenda Chapman', 2012, 102);
-- INSERT movies(id, title, director, year, length_minutes) VALUES (14, 'Monsters University', 'Dan Scanlon', 2013, 110);
--
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (5, 8.2, 380843261, 555900000);
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (14, 7.4, 268492764, 475066843);
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (8, 8, 206445654, 417277164);
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (12, 6.4, 191452396, 368400000);
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (3, 7.9, 245852179, 239163000);
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (6, 8, 261441092, 370001000);
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (9, 8.5, 223808164, 297503696);
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (11, 8.4, 415004880, 648167031);
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (1, 8.3, 191796233, 170162503);
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (7, 7.2, 244082982, 217900167);
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (10, 8.3, 293004164, 438338580);
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (4, 8.1, 289916256, 272900000);
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (2, 7.2, 162798565, 200600000);
-- INSERT boxoffice(movie_id, rating, domestic_sales, international_sales) VALUES (13, 7.2, 237283207, 301700000);