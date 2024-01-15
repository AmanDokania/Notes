**If we want run a job at a specific time then we need to schedule a job.**
To enable the scheduling we need to configure

```Java
@Configuration  
@EnableScheduling  
public class JobConfig {  
   
}
```

Once we enable the scheduling then we need to create a job and define a crone expression
```Java
package com.javatechie.service;  
  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.scheduling.annotation.EnableScheduling;  
import org.springframework.scheduling.annotation.Scheduled;  
import org.springframework.stereotype.Service;  
  
import javax.mail.MessagingException;  
import java.io.IOException;  
import java.util.Date;  
  
@Service  
public class JobService {  
  
    @Autowired  
    private NotificationService service;  
  
    @Scheduled(cron = "${cron_interval}",zone = "IST")  
    public void process() {  
        System.out.println("job started on " + new Date());  
        try {  
            service.sendDailyReports();  
        } catch (MessagingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
  
}
```

To define the crone expression
```Java
cron_interval = 0 0/2 * * * *
```

![[Pasted image 20230820162342.png]]

[Crone Expression]([https://bradymholt.github.io/cron-expression-descriptor/](https://bradymholt.github.io/cron-expression-descriptor/ "https://bradymholt.github.io/cron-expression-descriptor/")

[Crone Expression](https://bradymholt.github.io/cron-expression-descriptor/)


***Quartz Scheduler***

[refer this for quartz](https://www.callicoder.com/spring-boot-quartz-scheduler-email-scheduling-example/)
[Github link](https://github.com/callicoder/spring-boot-quartz-scheduler-email-scheduling)

