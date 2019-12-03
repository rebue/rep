package rebue.rep.svc.impl;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rebue.rep.dao.RepRevenueMonthlyDao;
import rebue.rep.jo.RepRevenueMonthlyJo;
import rebue.rep.mapper.RepRevenueMonthlyMapper;
import rebue.rep.mo.RepRevenueDailyMo;
import rebue.rep.mo.RepRevenueMonthlyMo;
import rebue.rep.ro.RepRevenueRo;
import rebue.rep.svc.RepRevenueMonthlySvc;
import rebue.robotech.svc.impl.BaseSvcImpl;

/**
 * 报表-营收报表--月报
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
public class RepRevenueMonthlySvcImpl extends
        BaseSvcImpl<java.lang.Long, RepRevenueMonthlyJo, RepRevenueMonthlyDao, RepRevenueMonthlyMo, RepRevenueMonthlyMapper>
        implements RepRevenueMonthlySvc {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(RepRevenueMonthlyMo mo) {
        log.info("repRevenueMonthlySvc.add: 添加报表-营收报表--月报 mo-", mo);
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        return super.add(mo);
    }

    @Override
    public List<RepRevenueRo> listRevenueOfDay(Long shopId, String revenueStartTime, String revenueEndTime) {
        List<RepRevenueRo> result = new ArrayList<RepRevenueRo>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
         
            int revenueStartMonth = Integer.parseInt(revenueStartTime.substring(5, 7));
            int revenueStartYear = Integer.parseInt(revenueStartTime.substring(0, 4));
            log.info("查询开始年是{}的第{}月的营收记录", revenueStartYear, revenueStartMonth);
           
            int revenueEndMonth = Integer.parseInt(revenueEndTime.substring(5, 7));
            int revenueEndYear = Integer.parseInt(revenueEndTime.substring(0, 4));
            log.info("查询结束年是{}的第{}月的营收记录", revenueEndYear, revenueEndMonth);
            log.info("查询日报的营收记录参数为{},{},{},{},{}", shopId, revenueStartYear, revenueStartMonth, revenueEndYear,
                    revenueEndMonth);
            List<RepRevenueMonthlyMo> dayRevenueResult = new ArrayList<>();
            if (revenueStartYear != revenueEndYear) {
                dayRevenueResult = _mapper.selectRevenueOfMonth1(shopId, revenueStartYear, revenueStartMonth,
                        revenueEndYear, revenueEndMonth);
                log.info("查询月报的营收记录结果为{}", dayRevenueResult);
            } else {
                dayRevenueResult = _mapper.selectRevenueOfMonth2(shopId, revenueStartYear, revenueStartMonth,
                        revenueEndYear, revenueEndMonth);
                log.info("查询月报的营收记录结果为{}", dayRevenueResult);
            }
            // 计算开始时间
            Calendar calendarGetStartDay = Calendar.getInstance();
            calendarGetStartDay.setTime(formatter.parse(revenueStartTime));
            log.info("开始时间-{}", formatter.format(calendarGetStartDay.getTime()));
            for (RepRevenueMonthlyMo item : dayRevenueResult) {
                RepRevenueRo revenueRo = new RepRevenueRo();
                Date Date = calendarGetStartDay.getTime();
                log.info("营收时间为-{}", formatter.format(Date));
                revenueRo.setRevenueTime(formatter.format(Date));
                revenueRo.setTotal(item.getTurnover());
                result.add(revenueRo);
                calendarGetStartDay.add(Calendar.MONTH, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        log.info("即将返回的结果-{}", result);
        return result;
    }

}
