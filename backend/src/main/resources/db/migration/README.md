Naming convention for flyway migration files:

    V[date]__[description].sql

E.g.: `V2019.10.01__Create_login.sql`

This way name clashes on feature branches are prevented. In case update scripts on two feature branches clash oh the 
date an additional version can be added like: `V2019.10.01_1__Create_login.sql`

Before the migration can be executed, the following command must be run on the created database from a role with 
superuser privileges:

    CREATE EXTENSION "uuid-ossp";
