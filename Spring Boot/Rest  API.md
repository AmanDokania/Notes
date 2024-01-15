	![[Pasted image 20230813111646.png]]
![[Pasted image 20230813112003.png]]

while creating any web application we used 3 layer architecture>>
![[Pasted image 20230813112147.png]]


Advantage
-   RESTful web services are **platform-independent**.
- It can be written in any programming language and can be executed on any platform.
-   It provides different data format like **JSON, text, HTML,** and **XML**.
-   It is fast in comparison to SOAP because there is no strict specification like SOAP.
-   These are **reusable**.
-   They are **language neutral**.


Micro-Services are something that are developed and deployed independently and have their own business  . These services are not dependent on each other.
if we are using multiple services then we can consume rest api's using RestTemplate
![[Pasted image 20230819160241.png]]

```Java
@Service  
public class FacultyService {  
  
    private final static String BASE_URL = "http://localhost:8080/";  
  
    @Autowired  
    private RestTemplate restTemplate;  
  
  
    public ServiceResponse<CourseResponseDTO> addNewCourseToDashboard(CourseRequestDTO courseRequestDTO) {  
        return restTemplate.postForObject(BASE_URL + "course", courseRequestDTO, ServiceResponse.class);  
    }  
  
    public ServiceResponse<List<CourseResponseDTO>> fetchAllCourses() {  
        return restTemplate.getForObject(BASE_URL + "course", ServiceResponse.class);  
    }  
  
    // get object using path variables  
    public ServiceResponse<CourseResponseDTO> findCourseById(Integer courseId) {  
        return restTemplate.getForObject(BASE_URL+"course/search/path/{courseId}",ServiceResponse.class,new Object[]{courseId});  
//        return restTemplate.getForObject(BASE_URL + "course/search/path/"+courseId, ServiceResponse.class);  
    }  
	// get using request parameter
    public ServiceResponse<CourseResponseDTO> findCourseByIdUsingRequestParam(Integer courseId) {  
        Map<String, Object> requestMap = new HashMap<>();  
        requestMap.put("courseId", courseId);  
        return rest@Service
public class FacultyService {

    private final static String BASE_URL = "http://localhost:8080/";

    @Autowired
    private RestTemplate restTemplate;


    public ServiceResponse<CourseResponseDTO> addNewCourseToDashboard(CourseRequestDTO courseRequestDTO) {
        return restTemplate.postForObject(BASE_URL + "course", courseRequestDTO, ServiceResponse.class);
    }

    public ServiceResponse<List<CourseResponseDTO>> fetchAllCourses() {
        return restTemplate.getForObject(BASE_URL + "course", ServiceResponse.class);
    }

    // get object using path variables
    public ServiceResponse<CourseResponseDTO> findCourseById(Integer courseId) {
        return restTemplate.getForObject(BASE_URL+"course/search/path/{courseId}",ServiceResponse.class,new Object[]{courseId});
//        return restTemplate.getForObject(BASE_URL + "course/search/path/"+courseId, ServiceResponse.class);
    }

    public ServiceResponse<CourseResponseDTO> findCourseByIdUsingRequestParam(Integer courseId) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("courseId", courseId);
        return restTemplate.getForObject(BASE_URL + "course/search/request/?courseId={courseId}", ServiceResponse.class, requestMap);
    }

    public void updateCourseInDashboard(int courseId,CourseRequestDTO courseRequestDTO){
         restTemplate.put(BASE_URL+"course/"+courseId, courseRequestDTO);
    }

    public void deleteCourseFromDashboard(int courseId){
        restTemplate.delete(BASE_URL+"course/"+courseId);
    }
}
```

Javatpoint>>

The other thing to notice is that ErrorMvcAutoConfiguration:
1.  ErrorMvcAutoConfiguration matched:  
``` java
@ConditionalOnClass found required classes 'javax.servlet.Servlet', 'org.springframework.web.servlet.DispatcherServlet' (OnClassCondition)- found 'session' scope (OnWebApplicatiossssnCondition)

```

It configures the **basicErrorController, errorAttributes, ErrorMvcAutoConfiguration,** and **DefaultErrorViewResolverConfiguration**. It creates the default error page which is known as **Whitelabel Error Page**.

![Spring Boot Auto Configuration and Dispatcher Servlet](https://static.javatpoint.com/tutorial/restful-web-services/images/spring-boot-auto-configure2.png)


***>>
1.  HttpMessageConvertersAutoConfiguration matched: 
```java
@ConditionalOnClass found required class 'org.springframework.http.converter.HttpMessageConverter' (OnClassCondition)  
4.  ----------------  
5.  -----------------  
6.  JacksonAutoConfiguration.Jackson2ObjectMapperBuilderCustomizerConfiguration matched: - @ConditionalOnClass found required class 'org.springframework.http.converter.json.Jackson2ObjectMapperBuilder'(OnClassCondition)  
```

It initializes the Jackson bean and the message converter. The **Jackson2ObjectMapper** does the conversion from **bean to JSON** and **JSON to bean**.


***Very Important
[Implementing HATEOAS for RESTful Services](https://www.javatpoint.com/restful-web-services-hateoas)


[SOAP](https://www.geeksforgeeks.org/difference-between-rest-api-and-soap-api/)
Diff between Rest and SOAP>>
	1.soap stands for simple object access protocol and rest stands for representational
	state transfer
	2.soap is standarized protocol with pre-defined rule(strict standard) to follow  and rest is architectural style with loose guidlines and recommendations
	3. soap is functional driven (data is availabel as services like get user())  and rest is data driven (data is availabel as a resources)
	4. soap by default it is stateless but we can make it statefull  and rest is stateless
	5. soap api can't be canched and rest api call can be cached
	6. soap api support ws-security(follow transactions) with ssl  and rest api support ssl with https
	7. -   Benefits of SOAP over REST as SOAP has ACID compliance transaction. Some of the applications require transaction ability which is accepted by SOAP whereas REST lacks in it.
	8. soap require more bandwidth whearas rest require less bandwidth
	9. soap support only xml message format and rest support (xml,json,simple text,html,yaml etc...) message format
	10. soap can used http,smtp udp and other transfer protocol and rest support only http protocol

![[Pasted image 20230518180105.png]]

![[Pasted image 20230518180332.png]]

Asynchronous processing means when client need a guranteed level of reliability and security then soap is perfect. new soap stamdard od soap 1.2   provides a lot of feature specially for security.
formal means of communication means when client and server both have an kind of aggreement what tpye of data should be send b/w client and server

![[Pasted image 20230518180829.png]]
wsdl file is a kind of documentation that  contains information about web services like what kind of data it accepts

![[Pasted image 20230518181109.png]]
