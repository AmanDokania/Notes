Refer this [link](https://www.educative.io/blog/what-is-redis) to go through theory about Redis


This is the dependency that we need to use 
```Java
<dependency>  
    <groupId>redis.clients</groupId>  
    <artifactId>jedis</artifactId>  
    <version>3.9.0</version>  
</dependency>
```

Now to make a connection between our application and Redis server we need to configure Redis server using JedisConnectionFactory class

```Java
@Configuration  
@EnableRedisRepositories  
public class RedisConfig {  
  
    @Bean  
    public JedisConnectionFactory connectionFactory(){  
        //host & port  
//        RedisStandaloneConfiguration configuration=new RedisStandaloneConfiguration();  
//        configuration.setHostName("localhost");  
//        configuration.setPort(6379);  
        return new JedisConnectionFactory();  
    }  
  
    @Bean  
    public RedisTemplate<Object,Object> template(){  
        RedisTemplate<Object,Object> template=new RedisTemplate<>();  
        template.setConnectionFactory(connectionFactory());  
        return template;  
    }  
}
```

JedisConnectionFactory class  provide connection object  that is responsible to make a connection between application and Redis server
After making a connection we need some template to make communication between our application and Redis server such as we use Rest Template to  make communication between two services.

```Java
package com.javatechie.repository;  
  
import com.javatechie.hash.Customer;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.data.redis.core.RedisTemplate;  
import org.springframework.stereotype.Repository;  
  
import java.util.List;  
  
@Repository  
public class CustomerDAO {  
  
    public static final String HASH_KEY = "CustomersInfo";  
  
  
    @Autowired  
    private RedisTemplate template;  
  
    public Customer addCustomer(Customer customer) {  
        template.opsForHash().put(HASH_KEY, customer.getId(), customer);  
        return customer;  
    }  
  
    public List<Customer> getAllCustomers() {  
        return template.opsForHash().values(HASH_KEY);  
    }  
  
    public Customer getCustomer(int id) {  
        return (Customer) template.opsForHash().get(HASH_KEY, id);  
    }  
  
    public String deleteCustomer(int id) {  
        template.opsForHash().delete(HASH_KEY, id);  
        return "Customer " + id + " has been removed from system !";  
    }  
  
    public Customer updateCustomer(int id, Customer customer) {  
        Customer existingCustomer = (Customer) template.opsForHash().get(HASH_KEY, id);  
        if (existingCustomer != null) {  
            existingCustomer.setFirstName(customer.getFirstName());  
            existingCustomer.setLastName(customer.getLastName());  
            existingCustomer.setEmail(customer.getEmail());  
            existingCustomer.setPhone(customer.getPhone());  
            existingCustomer.setDob(customer.getDob());  
            template.opsForHash().put(HASH_KEY, id, existingCustomer);  
            return existingCustomer;  
        } else {  
            throw new RuntimeException("Customer not found !");  
        }  
  
    }  
  
  
}
```

we are using Redis as database . and we are using core implementation using redisTemplate.

Now if we want to use Redis as database  using spring data Redis

```Java
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-data-redis</artifactId>  
</dependency>
```

```Java
package com.javatechie.service;  
  
import com.javatechie.hash.Customer;  
import com.javatechie.repository.CustomerDAO;  
import com.javatechie.repository.CustomerRepository;  
import lombok.extern.slf4j.Slf4j;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.cache.annotation.CacheEvict;  
import org.springframework.cache.annotation.CachePut;  
import org.springframework.cache.annotation.Cacheable;  
import org.springframework.stereotype.Service;  
  
import java.util.List;  
import java.util.stream.Collectors;  
import java.util.stream.StreamSupport;  
  
@Service  
@Slf4j  
public class CustomerService {  
  
    @Autowired  
    private CustomerDAO dao;  
  
    private static final String CACHE_NAME="customers";  
  
    @Autowired  
    private CustomerRepository repository;  
  
    @CachePut(key = "#customer.id",value = CACHE_NAME)  
    public Customer saveCustomer(Customer customer) {  
        //return dao.addCustomer(customer);  
        log.info("CustomerService::saveCustomer() Inserting record to DB");  
        return repository.save(customer);  
    }  
  
    @Cacheable(value = CACHE_NAME)  
    public List<Customer> getAllCustomers() {  
        //return dao.getAllCustomers();  
        log.info("CustomerService::getAllCustomers() fetching records from DB");  
        return StreamSupport.stream(repository.findAll().spliterator(), false)  
                .collect(Collectors.toList());  
    }  
  
    @Cacheable(key = "#id",value = CACHE_NAME)  
    public Customer getCustomer(int id) {  
        //return dao.getCustomer(id);  
        log.info("CustomerService::getCustomer() fetching record from DB");  
        return repository.findById(id).get();  
    }  
  
    @CacheEvict(key = "#id",value = CACHE_NAME)  
    public String deleteCustomer(int id) {  
        //return dao.deleteCustomer(id);  
        log.info("CustomerService::deleteCustomer() deleting record from DB");  
        repository.deleteById(id);  
        return "customer removed !";  
    }  
  
    @CachePut(key = "#id",value = CACHE_NAME)  
    public Customer updateCustomer(int id, Customer customer) {  
        //return dao.updateCustomer(id, customer);  
        log.info("CustomerService::updateCustomer() updating record from DB");  
        Customer existingCustomer = repository.findById(id).get();  
        existingCustomer.setFirstName(customer.getFirstName());  
        existingCustomer.setLastName(customer.getLastName());  
        existingCustomer.setEmail(customer.getEmail());  
        existingCustomer.setPhone(customer.getPhone());  
        existingCustomer.setDob(customer.getDob());  
        return repository.save(existingCustomer);  
  
    }  
}
```

So instead of redisTemplate we can directly used  repository which extends form CrudRepository

```Java
package com.javatechie.repository;  
  
import com.javatechie.hash.Customer;  
import org.springframework.data.repository.CrudRepository;  
  
import java.io.Serializable;  
  
public interface CustomerRepository extends CrudRepository<Customer,Integer> {  
}
```

Now if want to use Redis as cache then we just need to use caching annotation on the top of each method 


If Redis serve is not running  then try this command first
```Shell
- sudo service redis-server stop
```


[TODO] >> In case of deleting any item we are  getting stale data while fetching all data

work around for this problem is use @CacheEvict instead of @CachePut while saving,updating and deleting records>> https://stackoverflow.com/questions/51237672/spring-cache-all-elements-in-list-separately
