## Spring Boot, MySQL, JPA, Hibernate Rest API Tutorial

Restful CRUD API to maintain movies and actors relationship using Spring Boot, Mysql, JPA and Hibernate.

### Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 5.x.x

### Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/Piyush-Karira/Movie-API.git
```

**2. Create Mysql database**
```bash
create database <databse name>
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation
+ change `spring.datasource.url` according to the database name. 
 ```
  spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>?createDatabaseIfNotExist=true&useSSL=false
  ```



**4. Build and run the app using maven**

You can run the app without packaging it using -

```bash
mvn spring-boot:run
```
Alternatively, you can run the app directly from IDE. The app will start running at <http://localhost:8080>.

### Explore Rest APIs

The app defines following CRUD APIs for the given resources: 

**1. Actor**

    GET /actors
    
    POST /actors
    
    GET /actors/{actorId}
    
    PUT /actors/{actorId}
    
    DELETE /actors/{actorId}
    
 **2. Movie**
  
    GET /movies
    
    POST /movies
    
    GET /movies/{movieId}
    
    PUT /movies/{movieId}
    
    DELETE /movies/{movieId}
   
 **3. Association between Actors and Movies**
 
    PUT /actors/{actorId}/movies
    
    DELETE /actors/{actorId}/movies
    
    PUT /movies/{movieId}/actors
    
    DELETE /movies/{movieId}/actors
   
Example of Request body for association, adding movies to a actor
```
PUT /actors/{actorId}/movies

{
"movies" : [] //list of movieIds
}
```

You can test them using postman or any other rest client.
