

<p >
<img src="https://upload.wikimedia.org/wikipedia/commons/b/b1/Lsogo.jpg" title="Java JDK 8" width="100" height="100">  
<img src="https://upload.wikimedia.org/wikipedia/commons/4/44/Spring_Framework_Logo_2018.svg" title="Spring Boot Framework" width="200" height="100">
<img src="https://upload.wikimedia.org/wikipedia/commons/7/79/Docker_%28container_engine%29_logo.png" title="Docker" width="200" height="90">
<img src="https://upload.wikimedia.org/wikipedia/commons/3/32/Mongo-db-logo.png" title="MongoDB" width="120" height="80">
<img src="https://upload.wikimedia.org/wikipedia/commons/c/c5/Nginx_logo.svg" title="nginx" width="100" height="50">
</p>

<h1> Product Comparison (Prodcomp) </h1>

**Prodcomp** is a Backend solution, for importing and retrieval of Products' data for further comparison.  

For example a user can fetch products based upon name and category, to view various attributes.

Based upon different parameters, user can then make an educated decision to buy the product or not to.

This repository is prepared as part of a coding challenge, to demonstrate author's solution.


## Contents
- [Background](#background)
- [Features](#features)
- [Stack Development](#stack)
- [Execution](#execution)
- [References](#references)
- [Contact](#contact)

## <a name="background"></a>Background
### Details:
A service must be offered for the end users for making decisions on buying the product 
* fetch list of Products based upon parameters queried (name, category) 
* support multiple data sources for updating the database 
   * Selected course of action : REST API - Post Method for uploading Product information with json payload
* Document the solution 
* Performance and test cases 
* The solution must be easy to scale and maintain 

### Design 
 1. selection of endpoints  
 
 ```code
 - POST        : products/upload 
   Content-type: application/json 
   Response    : 200 OK for success
   
 - GET     : products?category=<category>&name=<name>
   Response: 200 OK for success
   A list of Products matching givin criteria ( empty array otherwise)
  ```
 2. Database selection
	 - MongoDB     :  free, open-source, supports rapid development and highly scalable 
	 - MongoExpress: light-weight administrative portal for MongoDB 
 
 3. Product collection model
 
       A Product can be stored as follows in the block.
      
       Here price, rating and vendor are factors influencing buyers' decision. 
       
       Based upon additional features (sorting based upon price or rating), end-users can get sufficient information.
 ```json
				{
					"name": "laptop",
					"category": "electronics",
					"vendor": "samsung",
					"price": "1300",
					"rating": 1
				}
  ```    
  
  4. Import Data into Database
    - REST API : upload via POST Method (Selected method)
		- import based : upload Files of application/json type containing the product particulars(as part of Web Application)
		- import based : external Cloud Drive Storage containing File with latest product information  ( ex: AWS S3 Bucket)
		- Scheduled : Cron job which executes a script, periodically to update the database, with latest product details 
		- Manual update via Database Portal 
    
## <a name="features"></a>Salient Features

	- Endpoints to support Products operations (Create, Read)
	- Highly scalable, available and maintainable ( scalable by docker-compose, reverse proxied by nginx) 
	- Containerized solution on Docker platform (Docker version 19.03.13, docker-compose version 1.27.4, build 40524192, Windows-10)
	- powered by JDK8 and Spring Boot Framework for production ready deployment (Spring Boot v2.3.4.RELEASE)
	- Junit tested  and system tested by Postman
	- Easily portable to Cloud (ex : AWS EC2)

## <a name="stack"></a>Solution Stack

A stack of containers is deployed to realize the solution. 
* *mongodb* - (database)  : Documents based highly scalable Database, single instance playing role of a master database 
* *prodcomp* - (backend application) : containing java application powered by Spring boot framework, listening to port 8080 via HTTP Protocol
* *mongo-express* - (web portal) : A web portal to administer MongoDB, authenticated users to control 
* *nginx* - (load balancer) :  load balancer to distribute HTTP traffic onto underlying backend service, offers reverse proxying.
* *Postman* - used for System testing, by executing REST Endpoints products/upload products?category=<category>&name=<name>
  
## <a name="execution"></a>Execution

### Application execution (on Host machine)

There are two options to execute the application on local development machine

    
    mvn spring-boot:run 
    
 OR
 
   ```
    java -jar -Dspring.data.mongodb.uri=mongodb://mongodb/<databaseName> target/productcomparision-0.0.1-SNAPSHOT.jar
   ```
for viewing or controlling MongoDB via **Mongo Express** setup, visit http://localhost:8985 

### Test execution

For testing of application, JUnit test framework is employed with execution of command `mvn test`. 

**Coverage Statistics** 

*ProductControllerTest* [Line (82%) Branch(100%)]

*ProductRepositoryTest* [Line(76%) Branch(100%)]

*ProductServiceTest* [Line (100%) Branch(100%)]

For System Testing, Postman API Testing software can be used.

### Docker execution

 Here **N** is number of instances, you want to run of prodcompapp container for High Availabilty
 Execute following steps
 ```
$ docker-compose -f prodcompapp_docker-compose.yml up --scale prodcompapp=<N> -d 
On Postman, create two Endpoints and execute
			- URL  : http://localhost:11290/
			- POST : products/upload ( payload type : application/json) 
				{
					"name": "laptop",
					"category": "electronics",
					"vendor": "samsung",
					"price": "1300",
					"rating": 1
				}	
			- GET  : products?category=electronics&name=laptop 
 ```
After the usage, if you want to stop and remove containers, execute following commands
  ```
  $ docker-compose -f prodcompapp_docker-compose.yml stop
  $ docker-compose -f prodcompapp_docker-compose.yml rm
  ```
 
## <a name="references"></a>References
 - [Spring Boot](https://spring.io/projects/spring-boot)
 - [MongoDB with Spring Boot](https://spring.io/guides/gs/accessing-data-mongodb/)
 - [DockerHub, Docker Compose and scaling](https://docs.docker.com/)
 - [nginx reverse proxy](https://docs.nginx.com/nginx/admin-guide/web-server/reverse-proxy/)


## <a name="contact"></a>Contact

For feedback on solution, contact me hulekal.preeti@gmail.com


Image Licenses and rights are reserved. Author takes no ownership
