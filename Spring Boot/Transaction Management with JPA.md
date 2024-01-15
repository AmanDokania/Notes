![[Pasted image 20230423161229.png]]

If we are using Plain JDBC then we need to maintain Transaction in such a way for each method
![[Pasted image 20230903183059.png]]

If we are using Plain JPA (without spring framework) then we need to maintain Transaction in such a way for each method

There are lot of duplicate code we need to write again and again but in case of spring boot we just need to use @Transactional annotation

![[Pasted image 20230903183541.png]]

Important Note:-
**Transaction can't handle Checked exception it's always handle RuntimeException**


Spring Data JPA provide default transaction for all methods implemented in SimpleJPARepository class  
@Transactional{
readOnly=true
}

this annotation is implemented at class level but for write method such as save,delete it override @Transactional annotation

if we want to override default transaction mechanism then we just need to override method in  repository interface  and use the @Transactional annotation


There are some cases where we need to override transaction mechanism  
if we want to perform several operation in a single transaction then we put all these operation in a single method and annotated that method with @Transactional
![[Pasted image 20230423162537.png]]

![[Pasted image 20230423163500.png]]

@EnableTransactionManagement   we need to use this annotation with configuration file to enabled the transaction in spring but in spring boot it automatically enable the transaction so we don't need to use this annotation

### **5.3. [Read-Only Transactions]**(https://www.baeldung.com/transaction-configuration-with-jpa-and-spring#3-read-only-transactions)


The _readOnly_ flag usually generates confusion, especially when working with JPA. From the Javadoc:

The fact is that **we can't be sure that an insert or update won't occur when the _readOnly_ flag is set.** This behavior is vendor-dependent, whereas JPA is vendor agnostic.

It's also important to understand that **the _readOnly_ flag is only relevant inside a transaction.** If an operation occurs outside of a transactional context, the flag is simply ignored. A simple example of that would call a method annotated with:

```java
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)

From a non-transactional context, a transaction will not be created and the _readOnly_ flag will be ignored.

### 5.5. Transaction Rollback[](https://www.baeldung.com/transaction-configuration-with-jpa-and-spring#5-transaction-rollback)

The _@Transactional_ annotation is the metadata that specifies the semantics of the transactions on a method. We have two ways to rollback a transaction: declarative and programmatic.

In the **declarative approach, we annotate the methods with the _@_**_**Transactional**_ **annotation**. The _@Transactional_ annotation makes use of the attributes _rollbackFor_ or _rollbackForClassName_ to rollback the transactions, and the attributes _noRollbackFor_ or _noRollbackForClassName_ to avoid rollback on listed exceptions.

The default rollback behavior in the declarative approach will rollback on runtime exceptions.

Let's see a simple example using the declarative approach to rollback a transaction for runtime exceptions or errors:

``` java
@Transactional(rollbackFor = { SQLException.class })
public void createCourseDeclarativeWithRuntimeException(Course course) {
    courseDao.create(course);
    throw new DataIntegrityViolationException("Throwing exception for demoing Rollback!!!");
}
```

Let's see a simple use of attribute _noRollbackFor_ in the declarative approach to prevent rollback of the transaction for the listed exception:
``` java
@Transactional(noRollbackFor = { SQLException.class })
public void createCourseDeclarativeWithNoRollBack(Course course) throws SQLException {
    courseDao.create(course);
    throw new SQLException("Throwing exception for demoing rollback");
}
```
 
 In the **programmatic approach****, we rollback the transactions using _TransactionAspectSupport_**:
 ``` java
 public void createCourseDefaultRatingProgramatic(Course course) {
    try {
       courseDao.create(course);
    } catch (Exception e) {
       TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
```

The **d****eclarative rollback strategy should be favored over the programmatic rollback strategy**.


we are using @Transactional annotation in spring boot so behind the scene spring boot will create a proxy class of your service and extends your service class and it will override the  transactional method after that it will begin transaction and commit or rollback transaction.

if we have a service with transactional method
```Java
@Service  
public class PractoService {  
    @Autowired  
    private PatientRepository patientRepository;  
  
    @Autowired  
    private AppointmentRepository appointmentRepository;  
  
    @Transactional  
    public String bookAppointment(PatientAppointmentRequest request) {  
        //SAVE Patient  
        Patient patient = request.getPatient();  
        long patientId = patientRepository.save(patient).getPatientId();
    }
}
```

![[Pasted image 20230903204622.png]]

[Propagation](https://www.javainuse.com/spring/boot-transaction-propagation)

![[Pasted image 20230904210236.png]]

```Java
logging.level.org.springframework.transaction.interceptor = TRACE
```
