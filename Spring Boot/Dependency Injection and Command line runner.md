Dependency Injection (DI) is a design pattern used in software engineering to achieve the Inversion of Control (IoC) principle. It's a way to manage and provide the dependencies that an object needs, without the object having to create those dependencies itself. This pattern promotes loose coupling between components and enhances modularity, testability and maintainability in your codebase.

In simpler terms, DI helps to decouple the creation and management of dependencies from the classes that use them. Instead of a class creating its own dependencies, the dependencies are provided from the outside, typically by a framework or a container. This allows for easier swapping of implementations, better testability through mocking, and improved overall flexibility.
We are delegating this request to spring container for creation and management the life cycle of beans(Object).

**Check the code as well at high level for different approach.**

 ways
1. field injection >> Under the hood it use reflection technology
2. setter injection
3. constructor injection
```
    // field injection  
//    @Autowired  
//    @Qualifier("whatsappService")  
//    private SocialAppService service;  
          
      
    //setter injection  
    private SocialAppService service;  
    @Autowired  
    @Qualifier("whatsappService")  
    public void setService(SocialAppService service) {  
        this.service = service;  
    }  
  
  
    //constructor injection  
  
//    private SocialAppService service;  
	// @Autowired  //(optional)
//    public UserApp(@Qualifier("whatsappService") SocialAppService service) {  
//        System.out.println("called UserApp ");  
//        this.service = service;  
//    }
```

if we have only one constructor then we can skip autowired annotation

**Autowired** annotation
it is a special annotation that controls dependency injection design pattern
this annotation tells spring boot that i need this object as dependencies object in the existing class.

**Qualifier** annotation
if we have multiple implementation class of a interface then there will be problem of ambiguity. so we need to tell spring boot that which implementation class object  should be create

**In Spring, the IoC container creates bean instances at the time of application bootstrapping based on the bean definitions. This means that the container will create instances of all beans defined in your configuration during startup, regardless of whether they are immediately needed or not. This is the default behavior.
However, Spring provides a feature known as "lazy loading" for beans. When lazy loading is enabled (which is the default behavior for most beans), the container will create the bean instances during startup, but it will delay the instantiation of the bean until it's first requested from the application context. This means that the actual creation of the bean's instance is deferred until the moment you ask for the bean from the application context.
So, in summary:
1.Beans are created at the time of application bootstrapping based on their bean definitions.
2 For beans with lazy loading enabled, the actual instantiation of the bean's instance is deferred until the bean is first requested from the application context.
3. To enable lazy loading we use **@Lazy** annotation at class level

** Key difference b/w Constructor injection and setter injection**
**Initialization Time:**
- Constructor injection initializes dependencies when the object is created, ensuring the object is immediately in a valid state.
- Setter injection initializes dependencies after the object is created, allowing for more flexibility but possibly leading to an object being in an invalid state until all required dependencies are set.
-**Required Dependencies:**
    - Constructor injection enforces the provision of all required dependencies during object creation.
    - Setter injection allows dependencies to be optional, as they can be injected at a later time.
``` Java
private TikTokService tikTokService;  
  
/**  
 * Optional Dependency * @param tikTokService  
 */  
@Autowired(required = false)  
public void setTikTokService(TikTokService tikTokService) {  
    this.tikTokService = tikTokService;  
}
```
   
**Ease of Testing:**
- Constructor injection can make unit testing easier because dependencies are explicitly provided, and you can create instances with mock dependencies easily.
- Setter injection might require additional setup for testing since you need to ensure that all required dependencies are properly set before testing.
In general, constructor injection is often preferred due to its benefits in terms of object initialization, immutability, and clearer dependencies. However, there might be cases where setter injection is more appropriate, such as when you want to change dependencies dynamically or when dealing with optional dependencies.

SpringBoot provide two type of runner 
1. CommandLineRunner
2. ApplicationRunner
CommandLineRunner Interface  provides the run method which we will be called at the time starting the application . so here we can write the code for testing purpose
![[Pasted image 20230421141811.png]]

we can use PostConstruct annotation as well instead of commandLineRunner
we if use both then PostConstruct will run first 