package rebue.rep.svc.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rebue.rep.dao.RepRevenueAnnualDao;
import rebue.rep.jo.RepRevenueAnnualJo;
import rebue.rep.mapper.RepRevenueAnnualMapper;
import rebue.rep.mo.RepRevenueAnnualMo;
import rebue.rep.mo.RepRevenueDailyMo;
import rebue.rep.mo.RepRevenueMonthlyMo;
import rebue.rep.mo.RepRevenueWeeklyMo;
import rebue.rep.ro.RepRevenueRo;
import rebue.rep.svc.RepRevenueAnnualSvc;
import rebue.rep.svc.RepRevenueDailySvc;
import rebue.rep.svc.RepRevenueMonthlySvc;
import rebue.rep.svc.RepRevenueWeeklySvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.slr.mo.SlrShopMo;
import rebue.slr.svr.feign.SlrShopSvc;

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
public class RepRevenueAnnualSvcImpl extends
        BaseSvcImpl<java.lang.Long, RepRevenueAnnualJo, RepRevenueAnnualDao, RepRevenueAnnualMo, RepRevenueAnnualMapper>
        implements RepRevenueAnnualSvc {

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

    @Resource
    private SlrShopSvc slrShopSvc;

    /**
     * 创建营收报表任务(凌晨一点运行一次)
     * 
     */
    @Override
    public void createRevenueReportTask() {
        // 获取所有店铺
        List<SlrShopMo> list = slrShopSvc.listAll();
        log.info("获取所有店铺的返回值为-{}", list);
        for (SlrShopMo shop : list) {

            log.info("定时创建营收报表任务开始执行");
            Calendar ca = Calendar.getInstance();
            ca.setTime(new Date());
            double dayCount = (ca.get(Calendar.YEAR) % 400 == 0 && ca.get(Calendar.YEAR) % 100 == 0 ? 366 : 365);

            // 查询今年报表是否已经创建
            RepRevenueAnnualMo mo = new RepRevenueAnnualMo();
            mo.setShopId(shop.getId());
            mo.setYear(ca.get(Calendar.YEAR));
            log.info("查询今年报表是否已经创建参数为-{}", mo);
            if (super.getOne(mo) != null) {
                log.info("今年报表已经创建-{}", mo.getYear());
                continue;
            }
            // 创建年报
            RepRevenueAnnualMo addAnnualMo = new RepRevenueAnnualMo();
            addAnnualMo.setYear(ca.get(Calendar.YEAR));
            addAnnualMo.setModifiedTimestamp(new Date().getTime());
            addAnnualMo.setShopId(shop.getId());
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
                addMonthlyMo.setShopId(shop.getId());
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
                addWeeklyMo.setShopId(shop.getId());
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
                addDailyMo.setShopId(shop.getId());
                if (repRevenueDailySvc.add(addDailyMo) != 1) {
                    log.error("创建第{}个日报失败", i);
                    throw new RuntimeException("创建第" + i + "个日报失败");
                }
            }

        }
    }
    
    @Override
    public List<RepRevenueRo> listRevenueOfDay(Long shopId, String revenueStartTime, String revenueEndTime) {
        List<RepRevenueRo> result = new ArrayList<RepRevenueRo>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int revenueStartYear = Integer.parseInt(revenueStartTime.substring(0, 4));
            log.info("查询开始年是{}营收记录", revenueStartYear);
            int revenueEndYear = Integer.parseInt(revenueEndTime.substring(0, 4));
            log.info("查询结束年是{}营收记录", revenueEndYear);
            log.info("查询年报的营收记录参数为{},{},{}", shopId, revenueStartYear, revenueEndYear);
            List<RepRevenueAnnualMo> revenueResult = new ArrayList<>();
            revenueResult = _mapper.selectRevenueOfYear(shopId, revenueStartYear, revenueEndYear);
            log.info("查询年报的营收记录结果为{}", revenueResult);

            // 计算开始时间
            Calendar calendarGetStartDay = Calendar.getInstance();
            calendarGetStartDay.setTime(formatter.parse(revenueStartTime));
            log.info("开始时间-{}", formatter.format(calendarGetStartDay.getTime()));
            for (RepRevenueAnnualMo item : revenueResult) {
                RepRevenueRo revenueRo = new RepRevenueRo();
                Date Date = calendarGetStartDay.getTime();
                log.info("营收时间为-{}", formatter.format(Date));
                revenueRo.setRevenueTime(formatter.format(Date));
                revenueRo.setTotal(item.getTurnover());
                result.add(revenueRo);
                calendarGetStartDay.add(Calendar.YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        log.info("即将返回的结果-{}", result);
        return result;
    }

}
