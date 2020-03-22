#Local database setup

Run local postgres database. You will need to install *psql*. If you don't have a postgres database running locally, you can use docker:
```
docker run -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres
```
To initialize the users database, log into the database as postgres user (password is postgres or whatever you have defined in the first step)
```
psql --host=localhost -U postgres
```
In the *psql* console run the following commands to create the *betten* database and user:
```
create user betten;
alter role "betten" with login;
alter user betten with password 'betten';
create database betten;
\q
```
Log into the *betten* database with the postgres user
```
psql --host=localhost -U postgres -d betten
```
And run (in *psql* console)
```
create extension "uuid-ossp";
\q
```
Use the maven flyway plugin to initialize the database. Working directory has to be *backend* in this repository
```
mvn flyway:baseline
mvn flyway:migrate
```
As the database is updated by adding new migrations in the *backend/src/main/resources/db/migration*, run
```
mvn flyway:migrate
```
again to apply the changes to the database.
