
CREATE TABLE IF NOT EXISTS school (
   name text PRIMARY KEY not null,
   country text not null
);

CREATE TABLE IF NOT EXISTS student (
   login text PRIMARY KEY not null,
   school text,
   name text not null,
   age int not null,

   FOREIGN KEY (school) REFERENCES school (name)
);



insert into school(name, country) VALUES('school1', 'US');

insert into student(login, name, school, age) VALUES('uwuthless', 'Mike', 'school1', 24);
insert into student(login, name, school, age) VALUES('lala', 'Dave', 'school1', 18);
