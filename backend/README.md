# TrackYourBedBackend

### Build docker image
Run `package.sh` to create docker image called "trackyourbed", which runs the service. Consult *src/main/resources/application.yml* for the environment variables that need to be passed to the container to configure the database connection.

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
 
As configuration, the following envvars need top be passed:
 - `FLYWAY_USER`: Database user to be used by flyway
 - `FLYWAY_PASSWORD`: Password for that user
 - `FLYWAY_URL`: URL of the database
 
