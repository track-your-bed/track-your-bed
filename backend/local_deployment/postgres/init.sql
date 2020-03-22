create user betten;
alter role "betten" with login;
alter user betten with password 'betten';
create database betten;
\connect betten
create extension "uuid-ossp"
