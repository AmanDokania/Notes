
Refer  ppt to konw about basics why we need JPA auditing 

To implement  JPA auditing 
WE can make as base class and we can inherit this class form  child class on that we want to implement  auditing

```Java
@EntityListeners(AuditingEntityListener.class)  
@SuperBuilder  
public abstract class Auditable<U> {  
  
  @Column(name = "created_by_id", updatable = false)  
  @CreatedBy  
  protected U createdBy;  
  
  @CreatedDate  
  @Temporal(TIMESTAMP)  
  @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)  
  protected Date creationDate;  
  
  @LastModifiedBy  
  @Column(name = "updated_by_id")  
  protected U lastModifiedBy;  
  
  @LastModifiedDate  
  @Temporal(TIMESTAMP)  
  @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")  
  protected Date lastModifiedDate;  
}
```

@EntityListeners(AuditingEntityListener.class)   this annotation tells spring Data JPA that please call the callback function such as @PrePersist() and @PreUpdate() so whenever we insert or update this object in DB then populate this fields

@CreatedDate   and  @LastModifiedDate  these annotation populate data form system time 

But in case of @CreatedBy  and  @LastModifiedBy   we need to create a bean of AuditorAware

```Java
public class AuditorAwareImpl implements AuditorAware<Long> {  
  
  
  // TODO return the current user when spring security is enabled  
    @Override  
    public Optional<Long> getCurrentAuditor() {  
  
      CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
//      System.out.println(currentUser);  
      currentUser.getUserID();  
        return Optional.of(currentUser.getUserID());  
        // Can use Spring Security to return currently logged in user  
        // return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()    }  
}
```

```Java
@Configuration  
@EnableJpaAuditing(auditorAwareRef = "auditorAware")  
public class JpaConfig {  
    @Bean  
    public AuditorAware<Long> auditorAware() {  
        return new AuditorAwareImpl();  
    }  
}
```

But now if we want to maintain history like what we have changed earlier
Then we need to use some frame-work like EnverseJPAAuditing
```Java
<dependency>  
    <groupId>org.springframework.data</groupId>  
    <artifactId>spring-data-envers</artifactId>  
</dependency>
```

Now we need to use **@Auditing** annotation on entity where we want to maintain history

```Java
@EntityListeners(AuditingEntityListener.class)  
@Audited  
public class Product {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
}
```

Now we need to tell to spring boot that use the RevisionRepository to perform all the crud operation to maintain history

```Java
public interface ProductRepository extends RevisionRepository<Product, Integer,Integer> ,  
        JpaRepository<Product, Integer> {
    }
```

This annotation will enable the JPA repository using **EnversRevisionRepositoryFactoryBean** this class
```Java
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
```
\