package rebue.rep.svr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import rebue.rep.to.ReturnTurnoverTo;
import rebue.robotech.ro.Ro;
import rebue.sbs.feign.FeignConfig;


@FeignClient(name = "rep-svr", configuration = FeignConfig.class, contextId = "rep-revenue-daily")
public interface RepRevenueDailySvc {
    
    /**
     * 同意退货扣除营收报表
     */
    @PostMapping("/rep/revenue-daily/return-turnover")
    Ro returnTurnover(@RequestBody final ReturnTurnoverTo returnTurnoverTo);

}
