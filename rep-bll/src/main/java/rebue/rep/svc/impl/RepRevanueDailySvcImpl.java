package rebue.rep.svc.impl;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rebue.afc.msg.PayDoneMsg;
import rebue.rep.dao.RepRevanueDailyDao;
import rebue.rep.jo.RepRevanueDailyJo;
import rebue.rep.mapper.RepRevanueAnnualMapper;
import rebue.rep.mapper.RepRevanueDailyMapper;
import rebue.rep.mapper.RepRevanueMonthlyMapper;
import rebue.rep.mapper.RepRevanueWeeklyMapper;
import rebue.rep.mo.RepRevanueAnnualMo;
import rebue.rep.mo.RepRevanueDailyMo;
import rebue.rep.mo.RepRevanueMonthlyMo;
import rebue.rep.mo.RepRevanueOrderMo;
import rebue.rep.mo.RepRevanueWeeklyMo;
import rebue.rep.svc.RepRevanueAnnualSvc;
import rebue.rep.svc.RepRevanueDailySvc;
import rebue.rep.svc.RepRevanueMonthlySvc;
import rebue.rep.svc.RepRevanueOrderSvc;
import rebue.rep.svc.RepRevanueWeeklySvc;
import rebue.robotech.svc.impl.BaseSvcImpl;

/**
 * 报表-营收报表-日报
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
public class RepRevanueDailySvcImpl extends
        BaseSvcImpl<java.lang.Long, RepRevanueDailyJo, RepRevanueDailyDao, RepRevanueDailyMo, RepRevanueDailyMapper>
        implements RepRevanueDailySvc {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(RepRevanueDailyMo mo) {
        log.info("repRevanueDailySvc.add: 添加报表-营收报表-日报 mo-{}", mo);
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        return super.add(mo);
    }

    @Resource
    private RepRevanueOrderSvc repRevanueOrderSvc;
    
    @Resource
    private RepRevanueAnnualMapper repRevanueAnnualMapper;
    
    @Resource
    private RepRevanueWeeklyMapper repRevanueWeeklyMapper;
    
    @Resource
    private RepRevanueMonthlyMapper repRevanueMonthlyMapper;
    
    
    @Resource
    private RepRevanueMonthlySvc repRevanueMonthlySvc;
    
    @Resource
    private RepRevanueWeeklySvc repRevanueWeeklySvc;
    
    @Resource
    private RepRevanueAnnualSvc repRevanueAnnualSvc;
    

    /**
     * 处理支付完成通知
     * 1:判断是否添加过该笔记录
     * 2:然后插入天，周，月，年的记录
     */
    @Override
    public Void handlePayNotify(PayDoneMsg payDoneMsg) {
        RepRevanueOrderMo getOnlmo = new RepRevanueOrderMo();
        getOnlmo.setOrderId(Long.valueOf(payDoneMsg.getOrderId()));
        getOnlmo.setType((byte) 1);
        log.info("获取是否已经添加过的参数为getOnlmo-{}", getOnlmo);
        if (repRevanueOrderSvc.getOne(getOnlmo) == null) {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        // 更新当天的营收记录
        RepRevanueDailyMo dailyMo = new RepRevanueDailyMo();
        dailyMo.setDayOfYear(ca.get(Calendar.DAY_OF_YEAR));
        dailyMo.setYear(ca.get(Calendar.YEAR));
        RepRevanueDailyMo dailyResult = super.getOne(dailyMo);
        if (dailyResult == null) {
            throw new RuntimeException("获取当天营收记录失败");
        }
        if (_mapper.updateDayTurnover(dailyResult.getTurnover().add(payDoneMsg.getPayAmount()), dailyResult.getTurnover(),
                dailyResult.getId()) != 1) {
            throw new RuntimeException("更新当天营收记录失败");

        }
        // 更新当周的营收记录
        RepRevanueWeeklyMo weekMoly = new RepRevanueWeeklyMo();
        weekMoly.setWeekOfYear(ca.get(Calendar.WEEK_OF_YEAR));
        weekMoly.setYear(ca.get(Calendar.YEAR));
        RepRevanueWeeklyMo weeklyResult = repRevanueWeeklySvc.getOne(weekMoly);
        if (weeklyResult == null) {
            throw new RuntimeException("获取当周营收记录失败");
        }
        if (repRevanueWeeklyMapper.updateWeekTurnover(weeklyResult.getTurnover().add(payDoneMsg.getPayAmount()), weeklyResult.getTurnover(),
                weeklyResult.getId()) != 1) {
            throw new RuntimeException("更新当周营收记录失败");
        }
        //更新当月营收记录
        RepRevanueMonthlyMo Monthly = new RepRevanueMonthlyMo();
        Monthly.setMonthOfYear(ca.get(Calendar.MONTH) + 1);
        Monthly.setYear(ca.get(Calendar.YEAR));
        RepRevanueMonthlyMo monthlyResult = repRevanueMonthlySvc.getOne(Monthly);
        if (monthlyResult == null) {
            throw new RuntimeException("获取当月营收记录失败");
        }
        if (repRevanueMonthlyMapper.updateMonthTurnover(monthlyResult.getTurnover().add(payDoneMsg.getPayAmount()), monthlyResult.getTurnover(),
                monthlyResult.getId()) != 1) {
            throw new RuntimeException("更新当月营收记录失败");
        }
        //更新当年营收记录
        RepRevanueAnnualMo annual = new RepRevanueAnnualMo();
        annual.setYear(ca.get(Calendar.YEAR));
        RepRevanueAnnualMo annualResult = repRevanueAnnualSvc.getOne(annual);
        if (annualResult == null) {
            throw new RuntimeException("获取当年营收记录失败");
        }
        if (repRevanueAnnualMapper.updateYearTurnover(annualResult.getTurnover().add(payDoneMsg.getPayAmount()), annualResult.getTurnover(),
                annualResult.getId()) != 1) {
            throw new RuntimeException("更新当年营收记录失败");
        }
        
        // 将该条订单记录添加进去订单记录中
        RepRevanueOrderMo revanueOrder = new RepRevanueOrderMo();
        revanueOrder.setOrderId(Long.valueOf(payDoneMsg.getOrderId()));
        revanueOrder.setType((byte) 1);
        if(repRevanueOrderSvc.add(revanueOrder) != 1) {
            throw new RuntimeException("添加订单记录失败");
        }
        return null;
    }
}
