[Refer](https://www.geeksforgeeks.org/spring-boot-caching/)

```Java
@EnableCaching
```

This annotation is used to enable caching in spring boot

```Java
@CacheConfig(cacheNames = "products")
```

With @CacheConfig annotation, we can simplify some of the cache configurations into a single place at the class level, so that we don’t have to declare things multiple times.

```Java
@Cacheable
```

The simplest way to enable caching behavior for a method is to mark it with **@Cacheable** and parameterize it with the name of the cache where the results would be stored.

**Condition based Caching**
```Java
@Cacheable(value=”Customer”, condition=”#name.length<10″)  

public Customer findCustomer(String name)  {…}
```

**@CachePut**
```Java
@CachePut(value=”name”)

public String getName(Customer customer) {…}
```

**@CachePut** annotation can update the content of the cache without interfering with the method execution.

**@Cache Evict**

Since the cache is small in size. We don’t want to populate the cache with values that we don’t need often. Caches can grow quite large, quite fast. We can use the **@CacheEvict** annotation to remove values so that fresh values can be loaded into the cache again:

> @CacheEvict(value=”name”, allEntries=true)
> 
> public String getName(Customer customer) {…}

It provides a parameter called **allEntries** that evicts all entries rather than one entry based on the key.


This is default annotation based caching provided by spring boot. Spring Boot internally use ConcurrentHashMap to maintain cache.

**CacheManager** is a parent interface there are multiple implementation class of this interface.
One of the implementation class is **ConcurrentMapCacheManager** which use this map
```Java
private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>(16);
```

![[Pasted image 20230908204242.png]]
