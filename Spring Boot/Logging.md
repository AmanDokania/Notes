there are some places where we can get run time error unintentionally and we just catch those error and throw some generic exception. By this way we not able to identify where we are exactly getting this error.

for this problem we can use 
1. system.out.println() to print some useful information
	1. but it is a I/O operation and it consume a lot of CPU usage so bulk amount of these statement can decrease the performance.
 2. spring boot provides us logging framework

we don't need to add any specific dependency for logging . Logging is a part of spring web dependency
![[Pasted image 20230815164023.png]]

By default spring boot provide support of logback. if we wan two include any other framework such as java util, Log4j2 then first we need to  exclude the logback and configure Log4j2 or etc ..

***key component of Logger***
![[Pasted image 20230815164417.png]]

Logging Level
![[Pasted image 20230815164453.png]]
 By default info,warn, error are enabled if we want to use other one then we need to enabled them 
```Java
@RestController  
@RequestMapping("/course")  
***@Slf4j***  
public class CourseController {  
  
    //Logger log = LoggerFactory.getLogger(CourseController.class);  
  
  
    private CourseService courseService;  
  
    public CourseController(CourseService courseService) {  
        this.courseService = courseService;  
    }  
  
    @PostMapping  
    public ServiceResponse<CourseResponseDTO> addCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO) {  
  
        //validate request  
        //validateRequestPayload(courseRequestDTO);        log.info("CourseController:addCourse Request payload : {}", AppUtils.convertObjectToJson(courseRequestDTO));  
        ServiceResponse<CourseResponseDTO> serviceResponse = new ServiceResponse<>();  
        CourseResponseDTO newCourse = courseService.onboardNewCourse(courseRequestDTO);  
        serviceResponse.setStatus(HttpStatus.OK);  
        serviceResponse.setResponse(newCourse);  
        log.info("CourseController:addCourse response  : {}", AppUtils.convertObjectToJson(serviceResponse));  
        return serviceResponse;  
    }
}
```

we can use slf4j annotation then we don't need to create Logger object or optionally we can create logger object

```Java
#logging configuration  
spring.output.ansi.enabled = always  // used to enabled color of logs 
logging.level.com.javatechie.*= DEBUG  // to enable logging level
logging.pattern.console= %clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){yellow} [%level] %c{1.} {%t} %m%n  // specify our own pattern for logs

#FILE CONFIG  
logging.file.name=/Users/javatechie/Desktop/dev-logs/application.log  // name of file with full path
logging.pattern.file= %d [%level] %c{1.} {%t} %m%n
```


To use the Log4j2 library rather than the default Logback, we need to **exclude Logback from our starter dependencies**:

[link](https://www.baeldung.com/spring-boot-logback-log4j2)
[link](https://howtodoinjava.com/spring-boot/spring-boot-log4j2-properties/)

[logging feature](https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/boot-features-logging.html)

``` Java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

Also, we need to add _[spring-boot-starter-log4j2](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-log4j2)_ and [_log4j-spring-boot_](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-spring-boot) dependencies to our _pom.xml_ file:
``` Java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-spring-boot</artifactId>
</dependency>
```

```Java
status = error   #The level of internal Log4j events that should be logged to the console.
name = PropertiesConfig
filters = threshold
filter.threshold.type = ThresholdFilter
filter.threshold.level = debug
appenders = console
appender.console.type = Console
appender.console.name = STDOUT
appender.console.layout.type = PatternLayout
appender.console.layout.pattern = %d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
rootLogger.level = debug
rootLogger.appenderRefs = stdout
rootLogger.appenderRef.stdout.ref = STDOUT
```

```Java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	private static final Logger LOGGER = LogManager.getLogger(Application.class);
	public static void main(String[] args)
    {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        LOGGER.info("Info level log message");
        LOGGER.debug("Debug level log message");
        LOGGER.error("Error level log message");
    }
}
```

```Java
2018-06-01T13:55:42.506+0530 INFO Info level log message
2018-06-01T13:55:42.506+0530 DEBUG Debug level log message
2018-06-01T13:55:42.506+0530 ERROR Error level log message
```
