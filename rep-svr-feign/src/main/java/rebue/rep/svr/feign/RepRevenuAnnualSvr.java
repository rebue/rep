package rebue.rep.svr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import rebue.sbs.feign.FeignConfig;

@FeignClient(name = "rep-svr", configuration = FeignConfig.class, contextId = "rep-revenu-annual")
public interface RepRevenuAnnualSvr {

    /**
     * 定时任务创建营收报表
     */
    @PostMapping("/rep/revenue-annual/create-revenue-report")
    void createRevenueReportTask();
}
