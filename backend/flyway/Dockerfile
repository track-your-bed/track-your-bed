FROM flyway/flyway

COPY migration/ /flyway/sql/
COPY flyway.conf /flyway/conf/
ENTRYPOINT flyway migrate
