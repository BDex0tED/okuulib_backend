To run project in docker-compose.yml file replace ur database fields:
```
    environment:
      - POSTGRES_DB=ur_dbname
      - POSTGRES_USER=ur_user
      - POSTGRES_PASSWORD=ur_password
```
in application.properties replace database fields too:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/ur_dbname
spring.datasource.username=ur_user
spring.datasource.password=ur_password
```
then run ```sudo docker compose up --build``` in directory with the project
