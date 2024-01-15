![[Pasted image 20230613153952.png]]

![[Pasted image 20230613154241.png]]
![[Pasted image 20230613154659.png]]
 Job Launcher >> It is an interface which contains run() method to execute a job. it is an starting point in spring batch to execute a job
 Job Repository >>Job launcher immediately launch the job repository which is responsible to maintain the status the job like how many messages has read and how many failed and how may skipped
Job >> It is a component which is created just after launching the job ... (it is registered with job launcher) .it connect with
step component which is combination of ItemReader, ItemWriter, ItemProcessor

![[Pasted image 20230617155250.png]]

**Spring Batch Dependency**
```Java
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-batch</artifactId>  
</dependency>
```

Keys for Spring Batch
```Java
spring.batch.jdbc.initialize-schema=ALWAYS // to allow to create table of spring batch 
#disabled job run at startup  
spring.batch.job.enabled=false
```


In spring batch we can define the number of thread will be excecuting concurrently
```
@Bean  
public TaskExecutor taskExecutor() {  
SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();  
asyncTaskExecutor.setConcurrencyLimit(10);  
return asyncTaskExecutor;  
}
```

but all thread will be using single step

Spring Partition>>

spring partition-er assign the set of records to multiple threads 
with the help of partitioning we can take control on thread which thread will execute what kind of data
in spring partition job will be splitted into multiple steps and  each stpes will be eexecuted by  their own thread 
![[Pasted image 20230618152618.png]]

size of chunks and size of partition should be match
other wise all the thread will be pick up number of row form 0-size of chunk it will be vary for each and every thread

for example total records are 1000
1. threads are 10
	chunk size 250
	each thread can take the record 0-250

2. thread are 2
		1. chunk size 1000
	1. each thread can take the record 0-1000

3. thread are 2
	1. chunk size 250
	2.  each thread can take the record 0-250
	3. when number of records are more as compare to chunk then it will insert data multiple times



spring batch fault tolerance
	if there is any kind of error in spring batch then it will revert all transaction suppose if there is any corruption in data at any row then it will revert the whole process instead of skipping the corrupted rows

for skipping that particular row we can use faulttolerance() in our step
```java
@Bean  
public Step slaveStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {  
return new StepBuilder("slaveStep",jobRepository).  
<Customer, Customer>chunk(250,transactionManager)  
.reader(reader())  
.processor(customerProcessor)  
.writer(itemWriter)  
.faultTolerant()  
.skipLimit(1000)  
.skip(NumberFormatException.class)  
.noSkip(IllegalArgumentException.class)  
.build();  
}
```


if we want to define our own skip policy 
![[Pasted image 20230618221519.png]]
```java
.faultTolerant()  
//.skipLimit(1000)  
// .skip(NumberFormatException.class)  
.skipPolicy(skipPolicy)
```


To inform to user like what are the rows which don't have correct format of data for that we have listner class 

first we need to create the class
```java
@Component  
public class StepSkipListner implements SkipListener<Customer,Number> {  
Logger logger = LoggerFactory.getLogger(StepSkipListner.class);  
  
@Override // item reader  
public void onSkipInRead(Throwable throwable) {  
logger.info("A failure on read {} ", throwable.getMessage());  
}  
  
@Override // item writter  
public void onSkipInWrite(Number item, Throwable throwable) {  
logger.info("A failure on write {} , {}", throwable.getMessage(), item);  
}  
  
@SneakyThrows  
@Override // item processor  
public void onSkipInProcess(Customer customer, Throwable throwable) {  
logger.info("Item {} was skipped due to the exception {}", new ObjectMapper().writeValueAsString(customer),  
throwable.getMessage());  
}  
}
```

then we need to register the listner before skipPolicy
```java
.faultTolerant()  
//.skipLimit(1000)  
// .skip(NumberFormatException.class)  
.listener(skipListener)
```

When we will fixed the stale rows and re-run the job the only corrected row will be insert in DB. Spring Batch will not insert all rows again.