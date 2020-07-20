CREATE SCHEMA computers_firm;

CREATE TABLE product (
    marker varchar (10),
    model varchar (50),
    type varchar (50)
);

CREATE TABLE pc (
    code int,
    model varchar (50),
    speed smallint ,
    ram smallint ,
    hd real ,
    cd varchar (10),
    price float,
);

CREATE TABLE laptop (
    code int,
    model varchar (50),
    speed smallint ,
    ram smallint ,
    hd real ,
    price float,
    screen tinyint
);

CREATE TABLE printer (
    code int,
    model varchar (50),
    color char (1),
    type varchar (10),
    price float
);

INSERT product VALUES ('A', '1232', 'PC');
INSERT product VALUES ('A', '1233', 'PC');
INSERT product VALUES ('A', '1276', 'Printer');
INSERT product VALUES ('A', '1298', 'Laptop');
INSERT product VALUES ('A','1401', 'Printer');
INSERT product VALUES ('A', '1408', 'Printer');
INSERT product VALUES ('A', '1752', 'Laptop');
INSERT product VALUES ('B', '1121', 'PC');
INSERT product VALUES ('B', '1750', 'Laptop');
INSERT product VALUES ('C', '1321', 'Laptop');
INSERT product VALUES ('D', '1288', 'Printer');
INSERT product VALUES ('D', '1433', 'Printer');
INSERT product VALUES ('E', '1260', 'PC');
INSERT product VALUES ('E', '1434', 'Printer');
INSERT product VALUES ('E', '2112', 'PC');
INSERT product VALUES ('E', '2113', 'PC');

INSERT pc VALUES (1, '1232', 500, 64, 5.0, '12x', 600.0000);
INSERT pc VALUES (10, '1260', 500, 32, 10.0, '12x', 350.0000);
INSERT pc VALUES (12, '1233', 800, 128, 20.0, '50x', 970.0000);
INSERT pc VALUES (2, '1121', 750, 128, 14.0, '40x', 850.0000);
INSERT pc VALUES (3, '1233', 500, 64, 5.0, '12x', 600.0000);
INSERT pc VALUES (4, '1121', 600, 128, 14.0, '40x', 850.0000);
INSERT pc VALUES (5, '1121', 600, 128, 8.0, '40x', 850.0000);
INSERT pc VALUES (6, '1233', 750, 128, 20.0, '50x', 950.0000);
INSERT pc VALUES (7, '1232', 500, 32, 10.0, '12x', 400.0000);
INSERT pc VALUES (8, '1232', 450, 64, 8.0, '24x',350.0000);
INSERT pc VALUES (9, '1232', 450, 32, 10.0, '24x', 350.0000);

INSERT laptop VALUES (1, 1298, 350, 32, 4.0, 700.0000, 11);
INSERT laptop VALUES (2, 1321, 500, 64, 8.0, 970.0000, 12);
INSERT laptop VALUES (3, 1750, 750, 128, 12.0, 1200.0000, 14);
INSERT laptop VALUES (4, 1298, 600, 64, 10.0, 1050.0000, 15);
INSERT laptop VALUES (5, 1752, 750, 128, 10.0, 1150.0000, 14);
INSERT laptop VALUES (6, 1298, 450, 64, 10.0, 950.0000, 12);

INSERT printer VALUES (1, '1276', 'n', 'Laser', 400.0000);
INSERT printer VALUES (2, '1433', 'y', 'Jet', 270.0000);
INSERT printer VALUES (3, '1434', 'y', 'Jet', 290.0000);
INSERT printer VALUES (4, '1401', 'n', 'Matrix', 150.0000);
INSERT printer VALUES (5, '1408', 'n', 'Matrix', 270.0000);
INSERT printer VALUES (6, '1288', 'n', 'Laser', 400.0000);