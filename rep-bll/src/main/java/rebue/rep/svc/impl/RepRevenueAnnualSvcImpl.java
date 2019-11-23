package rebue.rep.svc.impl;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rebue.rep.dao.RepRevenueAnnualDao;
import rebue.rep.jo.RepRevenueAnnualJo;
import rebue.rep.mapper.RepRevenueAnnualMapper;
import rebue.rep.mo.RepRevenueAnnualMo;
import rebue.rep.mo.RepRevenueDailyMo;
import rebue.rep.mo.RepRevenueMonthlyMo;
import rebue.rep.mo.RepRevenueWeeklyMo;
import rebue.rep.svc.RepRevenueAnnualSvc;
import rebue.rep.svc.RepRevenueDailySvc;
import rebue.rep.svc.RepRevenueMonthlySvc;
import rebue.rep.svc.RepRevenueWeeklySvc;
import rebue.robotech.svc.impl.BaseSvcImpl;

/**
 * 报表-营收报表--年报
 *
 * 在单独使用不带任何参数的 @Transactional 注释时，
 * propagation(传播模式)=REQUIRED，readOnly=false，
 * isolation(事务隔离级别)=READ_COMMITTED，
 * 而且事务不会针对受控异常（checked exception）回滚。
 *
 * 注意：
 * 一般是查询的数据库操作，默认设置readOnly=true, propagation=Propagation.SUPPORTS
 * 而涉及到增删改的数据库操作的方法，要设置 readOnly=false, propagation=Propagation.REQUIRED
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
@Slf4j
public class RepRevenueAnnualSvcImpl extends BaseSvcImpl<java.lang.Long, RepRevenueAnnualJo, RepRevenueAnnualDao, RepRevenueAnnualMo, RepRevenueAnnualMapper> implements RepRevenueAnnualSvc {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(RepRevenueAnnualMo mo) {
        log.info("repRevenueAnnualSvc.add: 添加报表-营收报表--年报 mo-", mo);
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        return super.add(mo);
    }
    
    
    @Resource
    private RepRevenueMonthlySvc repRevenueMonthlySvc;

    @Resource
    private RepRevenueWeeklySvc repRevenueWeeklySvc;

    @Resource
    private RepRevenueDailySvc repRevenueDailySvc;

    /**
     * 创建营收报表任务(凌晨一点运行一次)
     * 
     */
    @Override
    public void createRevenueReportTask() {
        log.info("定时创建营收报表任务开始执行");
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        double dayCount = (ca.get(Calendar.YEAR) % 400 == 0 && ca.get(Calendar.YEAR) % 100 == 0 ? 366 : 365);

        // 查询今年报表是否已经创建
        RepRevenueAnnualMo mo = new RepRevenueAnnualMo();
        mo.setYear(ca.get(Calendar.YEAR));
        log.info("查询今年报表是否已经创建参数为-{}", mo);
        if (super.getOne(mo) != null) {
            log.info("今年报表已经创建-{}", mo.getYear());
            return;
        }
        // 创建年报
        RepRevenueAnnualMo addAnnualMo = new RepRevenueAnnualMo();
        addAnnualMo.setYear(ca.get(Calendar.YEAR));
        addAnnualMo.setModifiedTimestamp(new Date().getTime());
        log.info("创建年报的参数为-{}", addAnnualMo);
        if (add(addAnnualMo) != 1) {
            log.error("创建年报失败");
            throw new RuntimeException("创建年报失败");
        }
        // 创建月报
        for (int i = 1; i <= 12; i++) {
            RepRevenueMonthlyMo addMonthlyMo = new RepRevenueMonthlyMo();
            addMonthlyMo.setYear(ca.get(Calendar.YEAR));
            addMonthlyMo.setModifiedTimestamp(new Date().getTime());
            addMonthlyMo.setMonthOfYear(i);
            if (repRevenueMonthlySvc.add(addMonthlyMo) != 1) {
                log.error("创建第{}个月报失败", i);
                throw new RuntimeException("创建第" + i + "个月报失败");
            }
        }
        // 创建周报
        int weekCout = (int) Math.ceil(dayCount / 7);
        log.info("今年的周数为-{}", weekCout);
        for (int i = 1; i <= weekCout; i++) {
            RepRevenueWeeklyMo addWeeklyMo = new RepRevenueWeeklyMo();
            addWeeklyMo.setYear(ca.get(Calendar.YEAR));
            addWeeklyMo.setModifiedTimestamp(new Date().getTime());
            addWeeklyMo.setWeekOfYear(i);
            if (repRevenueWeeklySvc.add(addWeeklyMo) != 1) {
                log.error("创建第{}个周报失败", i);
                throw new RuntimeException("创建第" + i + "个周报失败");
            }
        }
        // 创建日报
        log.info("今年的天数为-{}", dayCount);
        for (int i = 1; i <= dayCount; i++) {
            RepRevenueDailyMo addDailyMo = new RepRevenueDailyMo();
            addDailyMo.setYear(ca.get(Calendar.YEAR));
            addDailyMo.setModifiedTimestamp(new Date().getTime());
            addDailyMo.setDayOfYear(i);
            if (repRevenueDailySvc.add(addDailyMo) != 1) {
                log.error("创建第{}个日报失败", i);
                throw new RuntimeException("创建第" + i + "个日报失败");
            }
        }

    }
    
}
