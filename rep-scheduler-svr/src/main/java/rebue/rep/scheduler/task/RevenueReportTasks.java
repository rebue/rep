package rebue.rep.scheduler.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import rebue.rep.svr.feign.RevenueReportSvc;

@Component
public class RevenueReportTasks {

    private final static Logger _log = LoggerFactory.getLogger(RevenueReportTasks.class);

    @Resource
    private RevenueReportSvc revenueReportSvc;

   // @Scheduled(cron = "${rep.scheduler.createRevenueReportTask:0 0 1 * * ?}")
    @Scheduled(fixedDelayString = "${ord.scheduler.firstBuyFixedDelay:300000}")
    public void executeTasks() throws InterruptedException {
        _log.info("定时执行创建营收报表的任务");

        try {
            revenueReportSvc.createRevenueReportTask();
        } catch (final RuntimeException e) {
            _log.info("定时执行创建营收报表出现异常", e);
        }
    }

}
