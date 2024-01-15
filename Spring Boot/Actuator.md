### Spring Boot actuator will help us to monitor our application in the production

### Spring Boot actuator by default enabled a couple of endpoints using these endpoints you can find a lot of information about your application which is running in any environment.

By default spring boot will enabled only one endpoint related to health

```Java
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-actuator</artifactId>  
</dependency>  
<dependency>
```

localhost:8080/actuator >>
```Java
{
  "_links": {
    "self": {
      "href": "http://localhost:9090/actuator",
      "templated": false
    },
    "health": {
      "href": "http://localhost:9090/actuator/health",
      "templated": false
    },
    "health-path": {
      "href": "http://localhost:9090/actuator/health/{*path}",
      "templated": true
    }
  }
}
```

if we want to enabled all end points for that we need to add a key
```
management.endpoints.web.exposure.include=*
management.endpoints.web.exposure.include=health,bean,info
```

Metrices :- This is crucial information about our application and JVM such as disk size,
disk free, 

![[Pasted image 20240113143727.png]]


Initially info endpoint doesn't show anything but we can add properties related to info
```Java
#INFO endpoint configuration  
#info.application.name= SpringBoot-Actuator  
#info.application.description= Explain about actuator 14 endpoints  
#info.application.version= V 3.0  
#  
#info.technology.java = open jdk 8  
#info.technology.springboot= 2.7.3

management.info.env.enabled=true // to enable info endpoint
```

if we want to  add data dynamically then we need to add plugins in pom.xml file
```Java
<executions>  
    <execution>       
	    <goals>          
		    <goal>build-info</goal>  
       </goals>    
    </execution>
</executions>
```

In the same way we can add git related plugin

After adding this goal there is inbuilt class called BuildInfoContributer that will load buid-info.properties file from target folder and will display as part of info endpoint.

![[Pasted image 20240113145758.png]]

 if we want create our own contributer
```Java
package com.javatechie.contributor;  
  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.boot.actuate.info.Info;  
import org.springframework.boot.actuate.info.InfoContributor;  
import org.springframework.context.ApplicationContext;  
import org.springframework.stereotype.Component;  
  
import java.util.HashMap;  
import java.util.Map;  
  
@Component  
public class ApplicationContextContributor implements InfoContributor {  
  
    @Autowired  
    private ApplicationContext context;  
  
    @Override  
    public void contribute(Info.Builder builder) {  
        Map<String,Object> contextMap=new HashMap<>();  
        contextMap.put("bean-definition-count", context.getBeanDefinitionCount());  
        contextMap.put("bean-names", context.getBeanDefinitionNames());  
        contextMap.put("application-startup-time", context.getStartupDate());  
        builder.withDetail("context",contextMap);  
    }  
}
```

If we want to create our own endpoint 
```Java
package com.javatechie.endpoint;  
  
import com.javatechie.dto.ProdRelease;  
import org.springframework.boot.actuate.endpoint.annotation.*;  
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;  
import org.springframework.stereotype.Component;  
import org.springframework.web.bind.annotation.PostMapping;  
  
import java.util.ArrayList;  
import java.util.Arrays;  
import java.util.List;  
import java.util.stream.Collectors;  
  
@Component  
@Endpoint(id="releases")  
public class FeatureReleasesEndPoint {  
  
    List<ProdRelease> prodReleases=new ArrayList<>();  
  
    @WriteOperation //POST  
    %%  %%@Selector same as the path variable
    public void addNewReleaseInfo(@Selector String crq,@Selector String releaseDt, String features){  
        ProdRelease release = ProdRelease.builder().crq(crq)  
                .releaseDt(releaseDt)  
                .features(Arrays.stream(features.split(",")).collect(Collectors.toList())).build();  
        prodReleases.add(release);  
    }  
  
    @ReadOperation //GET  
    public List<ProdRelease> getAllReleases(){  
        return prodReleases;  
    }  
  
    @ReadOperation //  
    public ProdRelease getReleaseByCRQ(@Selector String crq){  
        return prodReleases.stream().filter(prodRelease -> prodRelease.getCrq().equals(crq))  
                .findAny().get();  
    }  
  
    @DeleteOperation //DELETE  
    public void deleteRelease(@Selector String crq){  
        prodReleases.remove(getReleaseByCRQ(crq));  
    }  
  
  
}
```
