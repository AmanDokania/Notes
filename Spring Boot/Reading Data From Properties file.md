

we can define this property in application.properties file
![[Pasted image 20231224192235.png]]

If we want to read data from application properties file in a class then we need to use the @ConfigurationProperties annotation and define a prefix value 
![[Pasted image 20231224192022.png]]


![[Pasted image 20231224191944.png]]

**@EnableConfigurationProperties** :- This class is likely used for reading configuration properties from a configuration source (like application.properties or application.yml) with a prefix of "authorization". The `@ConfigurationProperties` annotation is used to bind the properties under the specified prefix to the fields of the `ServiceConfig` class.
```
@Component  
@EnableConfigurationProperties  
@ConfigurationProperties(prefix="authorization")  
public class ServiceConfig {  
  @NotNull  private String scheme;  
  @NotNull  private String host;  
  @NotNull  private String path;
  }
```

```
authorization.scheme=myScheme authorization.host=myHost authorization.path=myPath
