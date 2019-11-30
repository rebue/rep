package rebue.rep.svr.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import rebue.afc.msg.PayDoneMsg;
import rebue.rep.ro.RepRevenueRo;
import rebue.sbs.feign.FeignConfig;

@FeignClient(name = "rep-svr", configuration = FeignConfig.class, contextId = "rep-svr-feign")
public interface RevenueReportSvc {

    @PostMapping("/rep/revenue-annual/create-revenue-report")
    void createRevenueReportTask();

    @PutMapping("/rep/revenue-daily/modify-revenue")
    void modifyRevenue(@RequestBody final PayDoneMsg payDoneMsg);

    @GetMapping("rep/revenue-daily/list-revenue-of-Day")
    List<RepRevenueRo> listRevenueOfDay(@RequestParam("shopId") final java.lang.Long shopId,
            @RequestParam("revenueTime") final java.lang.String revenueTime);

}
