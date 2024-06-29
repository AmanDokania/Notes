
[TODO]  [Stored Procedure using spring data JPA](https://www.youtube.com/watch?v=HqejreL9hTM)

**There are some drawbacks of Spring Data JDBC**
1. No automatic table creation
2. No association mapping
3. Not direct access to play with object

some points are there which are not included in slides (slides of udemy course).

Primary key id generation strategy >> https://thorben-janssen.com/jpa-generate-primary-keys/

JPA Entity life cycle event>>
https://www.baeldung.com/jpa-entity-lifecycle-events


![[Pasted image 20230420130852.png]]

![[Pasted image 20230421105150.png]]

![[Pasted image 20230421110044.png]]

![[Pasted image 20230421105950.png]]


Life cycle of entity >>
![[Pasted image 20230416163459.png]]


![[Pasted image 20230416163421.png]]



![[Pasted image 20230420121651.png]]

![[Pasted image 20230416180624.png]]

![[Pasted image 20240128131434.png]]

***spring data jpa  and jpa api's completely different, java api provides persistent provider***
EntityManager class  is a part of JPA API 

![[Pasted image 20230420122330.png]]

![[Pasted image 20230830192800.png]]

![[Pasted image 20230420122619.png]]






![[Pasted image 20230416180543.png]]

![[Pasted image 20230416180758.png]]


![[Pasted image 20230416182211.png]]

``` java
Mehtod of CrudRepository 
1. save();  It is used to save the data  if data is not exist otherwise it will update the data
2. saveAll();
3. findById();
4. findAll();
5. deleteById();
6. delete();
7. deleteAll(); delete all records of table
8. deleteAll(List<Object>list); delete  records of table provided in list 
9. count() method return number of record exist in tabel
10. existById(long id) to check that entity is exist or not in tabel by id
```

![[Pasted image 20230421221641.png]]

![[Pasted image 20230421222338.png]]

now this JPQL will be passed to jpa provider JPA provider (hibernate) is responsible to convert this JPQL query to SQL query

![[Pasted image 20230421222734.png]]

![[Pasted image 20230421223119.png]]
![[Pasted image 20230421223318.png]]
![[Pasted image 20230421224453.png]]

![[Pasted image 20230421225150.png]]
![[Pasted image 20230421225757.png]]
![[Pasted image 20230421230152.png]]

![[Pasted image 20230421230635.png]]

![[Pasted image 20230421230900.png]]

![[Pasted image 20230422145500.png]]

![[Pasted image 20230422150228.png]]
![[Pasted image 20230422150453.png]]

![[Pasted image 20230422150920.png]]

![[Pasted image 20230422151232.png]]

![[Pasted image 20230831164227.png]]


If we implement join query and fetch the data from different table then we can create a Dto for mapping these values and we can provide FQDN of class in query

**This concept will only work in JPQL not with native query**
```Java
@Query(value = "SELECT new com.javatechie.bo.ProjectEngineerResponseBO(p.name , p.projectCode , e.name , e.email) FROM Project p JOIN p.engineers e")  
public List<ProjectEngineerResponseBO> getProjectSpecificInfoWithJPQL();
```
