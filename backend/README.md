# TrackYourBedBackend

### Build docker image
Run `package.sh` to create docker image called "trackyourbed"

### Initial setup of database
As a superuser on the database instance, run
```
create user betten;
alter role "betten" with login;
alter user betten with password '<password>';
create database betten;
\connect betten
create extension "uuid-ossp"
```

### Executing flyway
Flyway can be executed by running the flyway docker container defined in the `backend/flyway` directory. To build the docker image called *trackyourbed-flyway*, first
 adapt `flyway.conf` to represent your database, then run `build_flyway_image.sh`.
