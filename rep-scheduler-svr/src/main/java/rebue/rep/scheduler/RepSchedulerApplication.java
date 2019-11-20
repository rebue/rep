package rebue.rep.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringCloudApplication
@EnableScheduling
@EnableFeignClients(basePackages = { "rebue.rep.svr.feign" })
public class RepSchedulerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RepSchedulerApplication.class, args);

       
    }
    

}
