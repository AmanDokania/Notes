@CreationTimeStamp and @UpdateTimeStamp  >> we mainly use to create and update the time while inserting the data in table
![[Pasted image 20230422150605.png]]

![[Pasted image 20230422173757.png]]

@Configration annotation indicate that this java class is a configration class (for java based configration) 
it is as class level configuration which is used with class where we need to define some bean
@EnableAutoConfiguration annotation enable all auto configuration class whenever spring boot find dependencies in class path as jar
IMP** >> AutoConfiguration feature is something spring boot will read (Scan) pom.xml and find all dependencies after that on the basis of condition on each and every class spring boot will enable or create the bean for it.             
@ComponentScan annotation scan all **spring component** in base package and it's sub package
**spring component is a class which is annotated with spring provide sterotype annotation

**StereoType** annotation >> 
	1. Component
	2. Controller 
		1. Controller
		2. RestController >> combination of Controller+ResponseBody
	3. Service
	4. Repository

Component is parent annotation and other three are child of component annotation
there is not any strict rule that where we need to use which one annotation we can use only Component anno...  at all places. but it is good practice to define the role of each and every class by annotating with specific annotation. so developer can know the role of each class
Component >> used to create a bean of simple class
Controller >> used where we need to define web services
	Controllers are typically used in web applications that render server-generated HTML views.
	Use `@RestController` when you're building a RESTful web service that returns data (usually in JSON or XML format) rather than rendering views.
	Note: This annotation can be used both at the class and at the method level. In most cases, at the method level applications will prefer to use one of the HTTP method specific variants @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, or @PatchMapping.
Service >> used to define business logic
Repository >> used to established database connection (DAO layer)

@RequestBody
Annotation indicating a method parameter should be bound to the body of the web request. The body of the request is passed through an HttpMessageConverter to resolve the method argument depending on the content type of the request. 

Optionally, automatic validation can be applied by annotating the argument with @Valid.

@ResponseBody
Annotation that indicates a method return value should be bound to the web response body. Supported for annotated handler methods.

@RequestMapping
Note: This annotation can be used both at the class and at the method level. In most cases, at the method level applications will prefer to use one of the HTTP method specific variants @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, or @PatchMapping.


@RequestParam
Annotation which indicates that a method parameter should be bound to a web request parameter.
Supported for annotated handler methods in Spring MVC .

@PathVaraible
Annotation which indicates that a method parameter should be bound to a URI template variable. Supported for RequestMapping annotated handler methods.

used to format from string to date using jackson api
![[Pasted image 20230813134745.png]]
when we are providing date as string from postman

@JsonInclude(JsonInclude.Include.NON_NULL)
this  annotation is used when we don't want to return the field if its null
>>>
-   Classes can be **excluded** from auto-configuration by adding:
```java
1.  @SpringBootApplication (exclude={JacksonAutoConfiguration.class, JmxAutoConfiguration.class})
```
Or add the following statement in the **application.properties** file.
1.  spring.autoconfiguration.exclude=org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration  

We exclude classes form the auto-configuration for **faster startup** and **better performance** of the application.

>>>
@ConditionOnClass, @ConditionOnProperty ,@CoditionOnMissingBean etc ...  these annotation mainly  used to check weather creating bean of this type resolve all dependencies (it's satisfying all dependencies or not ) or not
create bean on the basis of  some condition on bean 
![[Pasted image 20230422160047.png]]


@Primary  and @Qualifier() annotation use to resolve ambiguity in case of multiple implementation of an interface