CREATE SCHEMA buildings_employees;

USE buildings_employees;

CREATE TABLE buildings (
    Building_name varchar(255) PRIMARY KEY,
    Capacity int
);

CREATE TABLE employees (
    Role varchar(255),
    Name varchar(255),
    Building varchar(255) REFERENCES buildings,
    Years_employed int,
    FOREIGN KEY (Building) REFERENCES buildings (building_name)
)

INSERT buildings(Building_name, Capacity) VALUES ('1e', 24);
INSERT buildings(Building_name, Capacity) VALUES ('1w', 24);
INSERT buildings(Building_name, Capacity) VALUES ('2e', 24);
INSERT buildings(Building_name, Capacity) VALUES ('2w', 24);

INSERT employees(Role, Name, Building, Years_employed) VALUES ('Engineer', 'Becky A.', '1e', 4);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Engineer', 'Dan B.', '1e', 2);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Engineer', 'Sharon F.', '|1e', 6);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Engineer', 'Dan M.', '1e', 4);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Engineer', 'Malcom S.', '1e', 1);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Artist', 'Tylar S.', '2w', 2);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Artist', 'Sherman D.', '2w', 8);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Artist', 'Jakob J.', '2w', 6);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Artist', 'Lillia A.', '2w', 7);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Artist', 'Brandon J.', '2w', 7);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Manager', 'Scott K.', '1e', 9);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Manager', 'Shirlee M.', '1e', 3);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Manager', 'Daria O.', '2w', 6);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Engineer', 'Yancy I.', null , 0);
INSERT employees(Role, Name, Building, Years_employed) VALUES ('Artist', 'Oliver P.', null , 0);
