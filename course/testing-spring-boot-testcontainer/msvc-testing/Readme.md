# Para levantar la base de datos es lo siguiente :
docker compose up 
# Para ejecutar el archivo de script de base de datos es el siguiente:
docker exec -it postgresql-container psql -U root -d postgres -f /docker-entrypoint-initdb.d/create_database.sql
