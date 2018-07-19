#!/bin/bash

# Run this script from the command line with administrator privileges
# This script assumes the postgres database server is started and running

# Create role called automain and allow it to create a database
psql postgres -c "CREATE ROLE automain WITH LOGIN PASSWORD 'pass12345';
ALTER ROLE automain CREATEDB;"

# Create a database called automain_db and assign it to automain
createdb automain_db -U automain

# Grant all privileges for the automain_db to automain
psql postgres -U automain -c "GRANT ALL PRIVILEGES ON DATABASE automain_db TO automain;"

# Run the db-init.sql file to build the database tables
psql -d automain_db -a -f db-init.sql

psql postgres -U automain -d automain_db -f db-init.sql 
