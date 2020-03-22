# -----------------------------------------------------------------------
# When Docker builds the postgres container, it builds it from this image.
#
# This file pulls the latest postgres image from Docker Hub (a sort of
# GitHub for Docker images), and runs the create.sql file.
# -----------------------------------------------------------------------

FROM postgres

COPY *.sql docker-entrypoint-initdb.d/
