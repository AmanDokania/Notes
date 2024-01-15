
![[Pasted image 20230812121317.png]]

![[Pasted image 20230812121351.png]]
![[Pasted image 20230812121516.png]]

![[Pasted image 20230422152337.png]]

SpringBoot starter 
1. spring-boot-starter-data-jpa
2. spring-boot-starter-web >> tomacat server,web dependency(DispatcherServlet, servlet mapping etc ..),  jackson library etc ..
![[Pasted image 20230509142951.png]]
![[Pasted image 20230812121613.png]]
![[Pasted image 20230422154337.png]]
![[Pasted image 20230422153146.png]]
![[Pasted image 20230422155402.png]]

**Spring Boot AutoConfiguration feature is something that at the time of bootstrapping application spring boot will scan the pom.xml file and find all dependency that we have added in our class path  and on the basis of condition on each and every class spring boot decide to enable that feature for our application.**

Spring Boot create a copy of meta-data in spring-boot-autoconfigure.jar file that what all classes we need to be configured

**Externalized Configuration** >> It means we can deploy same spring boot application in different environment like dev,QA,production on the basis of type of application.

There is concept of profiles in SpringBoot by which we can create muitiple application properties file for different environment
such as 
	application-prod.properties
	application-dev.properties
	application-qa.properties

By default spring boot application read application.properties file so we need to active a particular properties files (properties) by below property 
![[Pasted image 20230423160451.png]]

We should use Spring Boot Framework because:

-   The dependency injection approach is used in Spring Boot.
-   It contains powerful database transaction management capabilities.
-   It simplifies integration with other Java frameworks like JPA/Hibernate ORM, Struts, etc.
-   It reduces the cost and development time of the application.


![[Pasted image 20230422161117.png]]
![[Pasted image 20230422161514.png]]
	Bootstrapping means it will load all the necessary component and configuration to run  our spring boot application

**1. Create application context
**2. Check applicationType(servlet (synchronous) and reactive(event driven architecture asynchronous))**
**3. Add scanned bean to application context**
**4. Enabled embedded tomcat server****

**Note :**
**At the time of creating environment spring boot use OriginTrackmapPropertySource class and load the properties file
 ** Main method is not always required in spring boot application but SpringApplication.run() method is required**


![[Pasted image 20230422172605.png]]

	
 **Spring Boot Parent Dependencies**>> when we create spring boot app using spring initializer then it creates spring boot parent dependencies in our pom.xml file
 1. It is mainly used for auto configuration
 2. it provides default java version that can be override
 3. It maintain the default version for all dependencies
 4. It provide deafult maven plugin 
 5. spring boot starter dependencies doesn't specify version so it is maintained by parent dependencies
 6. -   **UTF-8** source encoding

![[Pasted image 20230423151754.png]]
![[Pasted image 20230423153249.png]]

By default spring-boot-starter-web dependency provide tomacat as embedeed server
if we want to change the server then first we need to exclude tomact serve and add dependency for another server  here we add **jetty server** 
SpringBoot supported server>>
1. Tomcat
2. Jetty server
3. undertow server