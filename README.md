# Code Fellowship

Code Fellowship is a page where people learning to code can collaborate, exchange ideas, and share their throughts.

This is a full stack Java app design with Spring, and includes features such as authentication.

# Features
* Homepage displays basic info about the site and includes links to login and sign up.
* Authentication: Users can create an account to login.  Password hashed with BCrypt.
* Profile page: Each user has a profile page that displays their info.
* Posts: Users can post messages on their profile that others can view. 

## How to Setup Project

In order to run, you must have a file called: ```application.properties``` inside the directory: ```/src/main/resources```.

File contains:

```
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/album_app
spring.datasource.username=your-postgres-username
spring.datasource.password=your-postgres-password

spring.jpa.generate-ddl=true
spring.jpq.hibernate.ddl-auto=create
```

## How to Run with Gradle

From the terminal, use command:

`./gradlew bootRun`


## Credits

* Code bracket image from pixabay: https://pixabay.com/en/html-icon-web-development-design-2188441/
* Error handling with `ResourceNotFoundException` class: https://stackoverflow.com/questions/2066946/trigger-404-in-spring-mvc-controller?rq=1
* Thymeleaf custom error page: https://www.logicbig.com/tutorials/spring-framework/spring-boot/custom-thymeleaf-error-page.html