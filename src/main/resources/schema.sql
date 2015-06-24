create table "users"
("id" SERIAL NOT NULL PRIMARY KEY,
"email" VARCHAR(254) NOT NULL,
"name" VARCHAR(254),
"surname" VARCHAR(254),
"password_id" integer
);

create table "passwords"
("id" SERIAL NOT NULL PRIMARY KEY,
"hashed_password" VARCHAR(254),
"salt" VARCHAR(254))