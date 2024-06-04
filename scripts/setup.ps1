# Run the first PostgreSQL container for the Resource Service
docker run `
    --name resource-postgres `
    --env POSTGRES_PASSWORD=$DB_PASSWORD `
    --publish 5432:5432 `
    --detach postgres

# Run the second PostgreSQL container for the Song Service
docker run `
    --name song-postgres `
    --env POSTGRES_PASSWORD=$DB_PASSWORD `
    --publish 5433:5432 `
    --detach postgres
