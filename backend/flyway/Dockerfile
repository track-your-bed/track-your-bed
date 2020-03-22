FROM flyway/flyway

COPY migration_tmp/ /flyway/sql/
ENTRYPOINT flyway migrate
