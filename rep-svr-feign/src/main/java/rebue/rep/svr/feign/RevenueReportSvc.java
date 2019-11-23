package rebue.rep.svr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import rebue.afc.msg.PayDoneMsg;
import rebue.sbs.feign.FeignConfig;

@FeignClient(name = "rep-svr", configuration = FeignConfig.class, contextId = "rep-svr-feign")
public interface RevenueReportSvc {

    @PostMapping("/rep/revenue-annual/create-revenue-report")
    void createRevenueReportTask();
    
    
    @PutMapping("/rep/revenue-daily/modify-revenue")
    void modifyRevenue(@RequestBody final PayDoneMsg payDoneMsg);

}
