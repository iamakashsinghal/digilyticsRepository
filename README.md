Springboot Simple CSV Download Project

1. Firstly Create DataBase run Command in Mysql Terminal = create database digilytics.
2. Set Up project in sts ellipse.
3. You provide mysql database user credentials Like (database name = spring.datasource.url=jdbc:mysql://localhost:3306/digilytics, Username = spring.datasource.username=root, Password = spring.datasource.password=root) in application.properties file then change Auto create database Schema - spring.jpa.hibernate.ddl-auto=create.
4. Run Application
5. Stop Server Change application.properties file spring.jpa.hibernate.ddl-auto=validate.
6. Run Application
7. You Need Insert the data into data base table Role table. insert this three query == 1. insert into role_tbl (role_id,role_name) values (1,"ADMIN"); 2. insert into role_tbl (role_id,role_name) values (2,"SU"); 3. insert into role_tbl (role_id,role_name) values (3,"USER");

8. You have run your application on your web Browser (Chrome, FireFox Mozilla) then Enter the URL http://localhost:6060/swagger-ui.html#/
9. Then Call APIs (POST /register, GET /download) 
