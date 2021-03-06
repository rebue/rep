package rebue.rep.svc.impl;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
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

import rebue.afc.dic.PayAndRefundTypeDic;
import rebue.afc.msg.PayDoneMsg;
import rebue.ord.mo.OrdOrderDetailMo;
import rebue.ord.mo.OrdOrderMo;
import rebue.ord.svr.feign.OrdOrderDetailSvc;
import rebue.ord.svr.feign.OrdOrderSvc;
import rebue.rep.dao.RepRevenueDailyDao;
import rebue.rep.jo.RepRevenueDailyJo;
import rebue.rep.mapper.RepRevenueAnnualMapper;
import rebue.rep.mapper.RepRevenueDailyMapper;
import rebue.rep.mapper.RepRevenueMonthlyMapper;
import rebue.rep.mapper.RepRevenueWeeklyMapper;
import rebue.rep.mo.RepRevenueAnnualMo;
import rebue.rep.mo.RepRevenueDailyMo;
import rebue.rep.mo.RepRevenueMonthlyMo;
import rebue.rep.mo.RepRevenueOrderMo;
import rebue.rep.mo.RepRevenueWeeklyMo;
import rebue.rep.ro.RepRevenueRo;
import rebue.rep.svc.RepRevenueAnnualSvc;
import rebue.rep.svc.RepRevenueDailySvc;
import rebue.rep.svc.RepRevenueMonthlySvc;
import rebue.rep.svc.RepRevenueOrderSvc;
import rebue.rep.svc.RepRevenueWeeklySvc;
import rebue.rep.to.ReturnTurnoverTo;
import rebue.rep.to.UpdateTurnoverTo;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
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
public class RepRevenueDailySvcImpl extends
        BaseSvcImpl<java.lang.Long, RepRevenueDailyJo, RepRevenueDailyDao, RepRevenueDailyMo, RepRevenueDailyMapper>
        implements RepRevenueDailySvc {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(RepRevenueDailyMo mo) {
        log.info("repRevenueDailySvc.add: 添加报表-营收报表-日报 mo-{}", mo);
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        return super.add(mo);
    }

    @Resource
    private RepRevenueOrderSvc repRevenueOrderSvc;

    @Resource
    private RepRevenueAnnualMapper repRevenueAnnualMapper;

    @Resource
    private RepRevenueWeeklyMapper repRevenueWeeklyMapper;

    @Resource
    private RepRevenueMonthlyMapper repRevenueMonthlyMapper;

    @Resource
    private RepRevenueMonthlySvc repRevenueMonthlySvc;

    @Resource
    private RepRevenueWeeklySvc repRevenueWeeklySvc;

    @Resource
    private RepRevenueAnnualSvc repRevenueAnnualSvc;

    @Resource
    private OrdOrderSvc ordOrderSvc;

    @Resource
    private OrdOrderDetailSvc ordOrderDetailSvc;

    /**
     * 处理支付完成通知
     * 1:判断是否添加过该笔记录
     * 2:然后插入天，周，月，年的记录
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void handlePayNotify(PayDoneMsg payDoneMsg) {
        log.info("收到支付完成通知-{}", payDoneMsg);
        RepRevenueOrderMo getOnlmo = new RepRevenueOrderMo();
        getOnlmo.setOrderId(Long.valueOf(payDoneMsg.getOrderId()));
        getOnlmo.setType((byte) 1);
        log.info("获取是否已经添加过的参数为getOnlmo-{}", getOnlmo);
        if (repRevenueOrderSvc.getOne(getOnlmo) != null) {
            return ;
        }

        // 获取订单来获取订单详情
        BigDecimal costTitle = BigDecimal.ZERO;
        log.info("根据订单支付id获取到的订单参数为-{}", payDoneMsg.getOrderId());
        List<OrdOrderMo> orderList = ordOrderSvc.listSelective(Long.valueOf(payDoneMsg.getOrderId()));
        log.info("根据订单支付id获取到的订单结果为-{}", orderList);
        if (orderList.size() < 1) {
            throw new RuntimeException("订单不存在");
        }
        if (orderList.get(0).getShopId() == null) {
            log.info("店铺id为空，是线上商品，不需要记录营收-shopId{}", orderList.get(0).getShopId());
            return ;
        }
        if (payDoneMsg.getPayType() == PayAndRefundTypeDic.VPAY) {
            // 订单同时收到支付完成通知并去修改支付类型，但可能没修改完成这边就查询出来造成还是原来的支付方式
            // 所以这边手动修改为返现金支付(v支付)
            orderList.get(0).setPayWay((byte) 4);
        }

        // 初始化各个支付类型本次应该增加的金额
        BigDecimal cash = BigDecimal.ZERO;
        BigDecimal wxpay = BigDecimal.ZERO;
        BigDecimal alipay = BigDecimal.ZERO;
        BigDecimal cashback = BigDecimal.ZERO;
        switch (orderList.get(0).getPayWay()) {
        case 1:
            cash = payDoneMsg.getPayAmount();
            break;
        case 2:
            wxpay = payDoneMsg.getPayAmount();
            break;
        case 3:
            alipay = payDoneMsg.getPayAmount();
            break;
        case 4:
            cashback = payDoneMsg.getPayAmount();
            break;
        }

        // 获取订单详情计算成本和利润
        for (final OrdOrderMo order : orderList) {
            List<OrdOrderDetailMo> orderDetailList = ordOrderDetailSvc.listAll(order.getId());
            log.info("根据订单id获取到的订单详情结果为-{}", orderDetailList);
            if (orderDetailList.size() < 1) {
                throw new RuntimeException("订单详情不存在");
            }
            for (final OrdOrderDetailMo orderDetail : orderDetailList) {
                costTitle = costTitle.add(orderDetail.getCostPrice().multiply(orderDetail.getBuyCount()));
            }

        }
        ;

        // 计算利润
        BigDecimal profitTitle = payDoneMsg.getPayAmount().subtract(costTitle);
        log.info("当前交易的金额明细为{}(总金额)-{}(总成本)={}(总利润)", payDoneMsg.getPayAmount(), costTitle, profitTitle);
        // 更新营收记录
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());

        // 更新当天的营收记录
        RepRevenueDailyMo dailyMo = new RepRevenueDailyMo();
        dailyMo.setDayOfYear(ca.get(Calendar.DAY_OF_YEAR));
        dailyMo.setYear(ca.get(Calendar.YEAR));
        dailyMo.setShopId(orderList.get(0).getShopId());
        RepRevenueDailyMo dailyResult = super.getOne(dailyMo);
        if (dailyResult == null) {
            throw new RuntimeException("获取当天营收记录失败");
        }
        UpdateTurnoverTo updateDayTurnoverTo = new UpdateTurnoverTo();
        updateDayTurnoverTo.setId(dailyResult.getId());
        updateDayTurnoverTo.setModifiedTimestamp(new Date().getTime());
        updateDayTurnoverTo.setNewTurnover(dailyResult.getTurnover().add(payDoneMsg.getPayAmount()));
        updateDayTurnoverTo.setOldTurnover(dailyResult.getTurnover());
        updateDayTurnoverTo.setNewOrderNumber(dailyResult.getOrderNumber() + 1);
        updateDayTurnoverTo.setNewCost(dailyResult.getCost().add(costTitle));
        updateDayTurnoverTo.setNewProfit(dailyResult.getProfit().add(profitTitle));

        updateDayTurnoverTo.setCash(dailyResult.getCash().add(cash));
        updateDayTurnoverTo.setWxpay(dailyResult.getWxpay().add(wxpay));
        updateDayTurnoverTo.setAlipay(dailyResult.getAlipay().add(alipay));
        updateDayTurnoverTo.setCashback(dailyResult.getCashback().add(cashback));

        log.info("更新当天营收记录的参数为updateDayTurnoverTo-{}", updateDayTurnoverTo);
        if (_mapper.updateDayTurnover(updateDayTurnoverTo) != 1) {
            throw new RuntimeException("更新当天营收记录失败");

        }

        // 更新当周的营收记录(因为如果那个星期是跨年的，那么使用(Calendar.WEEK_OF_YEAR将会有问题，所以周数使用以下方法计算)
        RepRevenueWeeklyMo weekMoly = new RepRevenueWeeklyMo();
        if (ca.get(Calendar.WEEK_OF_YEAR) == 1 && ca.get(Calendar.MONTH) + 1 == 12) {
            weekMoly.setWeekOfYear(53);// 取53是因为任务插入记录的时候每一年都插了53周
        } else {
            weekMoly.setWeekOfYear(ca.get(Calendar.WEEK_OF_YEAR));
        }
        weekMoly.setYear(ca.get(Calendar.YEAR));
        weekMoly.setShopId(orderList.get(0).getShopId());
        RepRevenueWeeklyMo weeklyResult = repRevenueWeeklySvc.getOne(weekMoly);
        if (weeklyResult == null) {
            throw new RuntimeException("获取当周营收记录失败");
        }
        UpdateTurnoverTo updateWeekTurnoverTo = new UpdateTurnoverTo();
        updateWeekTurnoverTo.setId(weeklyResult.getId());
        updateWeekTurnoverTo.setModifiedTimestamp(new Date().getTime());
        updateWeekTurnoverTo.setNewTurnover(weeklyResult.getTurnover().add(payDoneMsg.getPayAmount()));
        updateWeekTurnoverTo.setOldTurnover(weeklyResult.getTurnover());
        updateWeekTurnoverTo.setNewOrderNumber(weeklyResult.getOrderNumber() + 1);
        updateWeekTurnoverTo.setNewCost(weeklyResult.getCost().add(costTitle));
        updateWeekTurnoverTo.setNewProfit(weeklyResult.getProfit().add(profitTitle));

        updateWeekTurnoverTo.setCash(weeklyResult.getCash().add(cash));
        updateWeekTurnoverTo.setWxpay(weeklyResult.getWxpay().add(wxpay));
        updateWeekTurnoverTo.setAlipay(weeklyResult.getAlipay().add(alipay));
        updateWeekTurnoverTo.setCashback(weeklyResult.getCashback().add(cashback));

        log.info("更新当周营收记录的参数为updateWeekTurnoverTo-{}", updateWeekTurnoverTo);
        if (repRevenueWeeklyMapper.updateWeekTurnover(updateWeekTurnoverTo) != 1) {
            throw new RuntimeException("更新当周营收记录失败");
        }

        // 更新当月营收记录
        RepRevenueMonthlyMo Monthly = new RepRevenueMonthlyMo();
        Monthly.setMonthOfYear(ca.get(Calendar.MONTH) + 1);
        Monthly.setYear(ca.get(Calendar.YEAR));
        Monthly.setShopId(orderList.get(0).getShopId());

        RepRevenueMonthlyMo monthlyResult = repRevenueMonthlySvc.getOne(Monthly);
        if (monthlyResult == null) {
            throw new RuntimeException("获取当月营收记录失败");
        }
        UpdateTurnoverTo updateMonthTurnoverTo = new UpdateTurnoverTo();
        updateMonthTurnoverTo.setId(monthlyResult.getId());
        updateMonthTurnoverTo.setModifiedTimestamp(new Date().getTime());
        updateMonthTurnoverTo.setNewTurnover(monthlyResult.getTurnover().add(payDoneMsg.getPayAmount()));
        updateMonthTurnoverTo.setOldTurnover(monthlyResult.getTurnover());
        updateMonthTurnoverTo.setNewOrderNumber(monthlyResult.getOrderNumber() + 1);
        updateMonthTurnoverTo.setNewCost(monthlyResult.getCost().add(costTitle));
        updateMonthTurnoverTo.setNewProfit(monthlyResult.getProfit().add(profitTitle));

        updateMonthTurnoverTo.setCash(monthlyResult.getCash().add(cash));
        updateMonthTurnoverTo.setWxpay(monthlyResult.getWxpay().add(wxpay));
        updateMonthTurnoverTo.setAlipay(monthlyResult.getAlipay().add(alipay));
        updateMonthTurnoverTo.setCashback(monthlyResult.getCashback().add(cashback));

        log.info("更新当月营收记录的参数为updateMonthTurnoverTo-{}", updateMonthTurnoverTo);
        if (repRevenueMonthlyMapper.updateMonthTurnover(updateMonthTurnoverTo) != 1) {
            throw new RuntimeException("更新当月营收记录失败");
        }

        // 更新当年营收记录
        RepRevenueAnnualMo annual = new RepRevenueAnnualMo();
        annual.setYear(ca.get(Calendar.YEAR));
        annual.setShopId(orderList.get(0).getShopId());
        RepRevenueAnnualMo annualResult = repRevenueAnnualSvc.getOne(annual);

        if (annualResult == null) {
            throw new RuntimeException("获取当年营收记录失败");
        }
        UpdateTurnoverTo updateYearTurnoverTo = new UpdateTurnoverTo();
        updateYearTurnoverTo.setId(annualResult.getId());
        updateYearTurnoverTo.setModifiedTimestamp(new Date().getTime());
        updateYearTurnoverTo.setNewTurnover(annualResult.getTurnover().add(payDoneMsg.getPayAmount()));
        updateYearTurnoverTo.setOldTurnover(annualResult.getTurnover());
        updateYearTurnoverTo.setNewOrderNumber(annualResult.getOrderNumber() + 1);
        updateYearTurnoverTo.setNewCost(annualResult.getCost().add(costTitle));
        updateYearTurnoverTo.setNewProfit(annualResult.getProfit().add(profitTitle));

        updateYearTurnoverTo.setCash(annualResult.getCash().add(cash));
        updateYearTurnoverTo.setWxpay(annualResult.getWxpay().add(wxpay));
        updateYearTurnoverTo.setAlipay(annualResult.getAlipay().add(alipay));
        updateYearTurnoverTo.setCashback(annualResult.getCashback().add(cashback));

        log.info("更新年营收记录的参数为updateYearTurnoverTo-{}", updateYearTurnoverTo);
        if (repRevenueAnnualMapper.updateYearTurnover(updateYearTurnoverTo) != 1) {
            throw new RuntimeException("更新当年营收记录失败");
        }

        // 将该条订单记录添加进去订单记录中
        RepRevenueOrderMo revanueOrder = new RepRevenueOrderMo();
        revanueOrder.setOrderId(Long.valueOf(payDoneMsg.getOrderId()));
        revanueOrder.setType((byte) 1);
        if (repRevenueOrderSvc.add(revanueOrder) != 1) {
            throw new RuntimeException("添加订单记录失败");
        }
        return ;
    }

    @Override
    public List<RepRevenueRo> listRevenueOfDay(Long shopId, String revenueStartTime, String revenueEndTime) {
        List<RepRevenueRo> result = new ArrayList<RepRevenueRo>();
        // 计算revenueTime属于该日期的那一天。
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date revenueStartDate;
        Date revenueEndDate;
        try {
            revenueStartDate = formatter.parse(revenueStartTime);
            revenueEndDate = formatter.parse(revenueEndTime);
            calendar.setTime(revenueStartDate);
            int revenueStartDay = calendar.get(Calendar.DAY_OF_YEAR);
            int revenueStartYear = Integer.parseInt(revenueStartTime.substring(0, 4));
            log.info("查询开始年是{}的第{}天的营收记录", revenueStartYear, revenueStartDay);
            calendar.setTime(revenueEndDate);
            int revenueEndDay = calendar.get(Calendar.DAY_OF_YEAR);
            int revenueEndYear = Integer.parseInt(revenueEndTime.substring(0, 4));
            log.info("查询结束年是{}的第{}天的营收记录", revenueEndYear, revenueEndDay);
            log.info("查询日报的营收记录参数为{},{},{},{},{}", shopId, revenueStartYear, revenueStartDay, revenueEndYear,
                    revenueEndDay);
            List<RepRevenueDailyMo> dayRevenueResult = new ArrayList<>();
            if (revenueStartYear != revenueEndYear) {
                dayRevenueResult = _mapper.selectRevenueOfDay1(shopId, revenueStartYear, revenueStartDay,
                        revenueEndYear, revenueEndDay);
                log.info("查询日报的营收记录结果为{}", dayRevenueResult);
            } else {
                dayRevenueResult = _mapper.selectRevenueOfDay2(shopId, revenueStartYear, revenueStartDay,
                        revenueEndYear, revenueEndDay);
                log.info("查询日报的营收记录结果为{}", dayRevenueResult);
            }
            // 计算开始时间
            Calendar calendarGetStartDay = Calendar.getInstance();
            calendarGetStartDay.setTime(formatter.parse(revenueStartTime));
            log.info("开始时间-{}", formatter.format(calendarGetStartDay.getTime()));
            for (RepRevenueDailyMo item : dayRevenueResult) {
                RepRevenueRo revenueRo = new RepRevenueRo();
                Date Date = calendarGetStartDay.getTime();
                log.info("营收时间为-{}", formatter.format(Date));
                revenueRo.setRevenueTime(formatter.format(Date));
                revenueRo.setTotal(item.getTurnover());
                revenueRo.setCash(item.getCash());
                revenueRo.setWxpay(item.getWxpay());
                revenueRo.setAlipay(item.getAlipay());
                revenueRo.setCashback(item.getCashback());

                result.add(revenueRo);
                calendarGetStartDay.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        log.info("即将返回的结果-{}", result);
        return result;
    }

    /**
     * 同意退款修改营收记录
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro returnTurnover(ReturnTurnoverTo returnTurnoverTo) {
        Ro result = new Ro();
        // 获取订单来获取订单详情
        BigDecimal costTitle = BigDecimal.ZERO;
        OrdOrderMo orderResult = ordOrderSvc.getById(returnTurnoverTo.getOrderId());
        log.info("根据订单id获取到的订单结果为-{}", orderResult);
        if (orderResult == null) {
            throw new RuntimeException("订单不存在");
        }
        // 初始化各个支付类型本次应该增加的金额
        BigDecimal cash = BigDecimal.ZERO;
        BigDecimal wxpay = BigDecimal.ZERO;
        BigDecimal alipay = BigDecimal.ZERO;
        BigDecimal cashback = BigDecimal.ZERO;
        switch (orderResult.getPayWay()) {
        case 1:
            cash = orderResult.getRealMoney();
            break;
        case 2:
            wxpay = orderResult.getRealMoney();
            break;
        case 3:
            alipay = orderResult.getRealMoney();
            break;
        case 4:
            cashback = orderResult.getRealMoney();
            break;
        }

        // 获取订单详情计算成本和利润
        List<OrdOrderDetailMo> orderDetailList = ordOrderDetailSvc.listAll(orderResult.getId());
        log.info("根据订单id获取到的订单详情结果为-{}", orderDetailList);
        if (orderDetailList.size() < 1) {
            throw new RuntimeException("订单详情不存在");
        }
        for (final OrdOrderDetailMo orderDetail : orderDetailList) {
            costTitle = costTitle.add(orderDetail.getCostPrice().multiply(orderDetail.getBuyCount()));
        }

        // 计算利润
        BigDecimal profitTitle = returnTurnoverTo.getRealMoney().subtract(costTitle);
        log.info("当前交易的金额明细为{}(总金额)-{}(总成本)={}(总利润)", returnTurnoverTo.getRealMoney(), costTitle, profitTitle);

        // 更新营收记录
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        // 更新当天的营收记录
        RepRevenueDailyMo dailyMo = new RepRevenueDailyMo();
        dailyMo.setDayOfYear(ca.get(Calendar.DAY_OF_YEAR));
        dailyMo.setYear(ca.get(Calendar.YEAR));
        dailyMo.setShopId(returnTurnoverTo.getShopId());
        RepRevenueDailyMo dailyResult = super.getOne(dailyMo);
        if (dailyResult == null) {
            throw new RuntimeException("获取当天营收记录失败");
        }
        UpdateTurnoverTo updateDayTurnoverTo = new UpdateTurnoverTo();
        updateDayTurnoverTo.setId(dailyResult.getId());
        updateDayTurnoverTo.setModifiedTimestamp(new Date().getTime());
        updateDayTurnoverTo.setNewTurnover(dailyResult.getTurnover().subtract(returnTurnoverTo.getRealMoney()));
        updateDayTurnoverTo.setOldTurnover(dailyResult.getTurnover());
        updateDayTurnoverTo.setNewOrderNumber(dailyResult.getOrderNumber() - 1);
        updateDayTurnoverTo.setNewCost(dailyResult.getCost().subtract(costTitle));
        updateDayTurnoverTo.setNewProfit(dailyResult.getProfit().subtract(profitTitle));

        updateDayTurnoverTo.setCash(dailyResult.getCash().subtract(cash));
        updateDayTurnoverTo.setWxpay(dailyResult.getWxpay().subtract(wxpay));
        updateDayTurnoverTo.setAlipay(dailyResult.getAlipay().subtract(alipay));
        updateDayTurnoverTo.setCashback(dailyResult.getCashback().subtract(cashback));

        log.info("更新当天营收记录的参数为updateDayTurnoverTo-{}", updateDayTurnoverTo);
        if (_mapper.updateDayTurnover(updateDayTurnoverTo) != 1) {
            throw new RuntimeException("更新当天营收记录失败");

        }

        // 更新当周的营收记录(因为如果那个星期是跨年的，那么使用(Calendar.WEEK_OF_YEAR将会有问题，所以周数使用以下方法计算)
        RepRevenueWeeklyMo weekMoly = new RepRevenueWeeklyMo();
        if (ca.get(Calendar.WEEK_OF_YEAR) == 1 && ca.get(Calendar.MONTH) + 1 == 12) {
            weekMoly.setWeekOfYear(53);// 取53是因为任务插入记录的时候每一年都插了53周
        } else {
            weekMoly.setWeekOfYear(ca.get(Calendar.WEEK_OF_YEAR));
        }
        weekMoly.setYear(ca.get(Calendar.YEAR));
        weekMoly.setShopId(returnTurnoverTo.getShopId());
        RepRevenueWeeklyMo weeklyResult = repRevenueWeeklySvc.getOne(weekMoly);
        if (weeklyResult == null) {
            throw new RuntimeException("获取当周营收记录失败");
        }
        UpdateTurnoverTo updateWeekTurnoverTo = new UpdateTurnoverTo();
        updateWeekTurnoverTo.setId(weeklyResult.getId());
        updateWeekTurnoverTo.setModifiedTimestamp(new Date().getTime());
        updateWeekTurnoverTo.setNewTurnover(weeklyResult.getTurnover().subtract(returnTurnoverTo.getRealMoney()));
        updateWeekTurnoverTo.setOldTurnover(weeklyResult.getTurnover());
        updateWeekTurnoverTo.setNewOrderNumber(weeklyResult.getOrderNumber() - 1);
        updateWeekTurnoverTo.setNewCost(weeklyResult.getCost().subtract(costTitle));
        updateWeekTurnoverTo.setNewProfit(weeklyResult.getProfit().subtract(profitTitle));

        updateWeekTurnoverTo.setCash(weeklyResult.getCash().subtract(cash));
        updateWeekTurnoverTo.setWxpay(weeklyResult.getWxpay().subtract(wxpay));
        updateWeekTurnoverTo.setAlipay(weeklyResult.getAlipay().subtract(alipay));
        updateWeekTurnoverTo.setCashback(weeklyResult.getCashback().subtract(cashback));

        log.info("更新当周营收记录的参数为updateWeekTurnoverTo-{}", updateWeekTurnoverTo);
        if (repRevenueWeeklyMapper.updateWeekTurnover(updateWeekTurnoverTo) != 1) {
            throw new RuntimeException("更新当周营收记录失败");
        }

        // 更新当月营收记录
        RepRevenueMonthlyMo Monthly = new RepRevenueMonthlyMo();
        Monthly.setMonthOfYear(ca.get(Calendar.MONTH) + 1);
        Monthly.setYear(ca.get(Calendar.YEAR));
        Monthly.setShopId(returnTurnoverTo.getShopId());

        RepRevenueMonthlyMo monthlyResult = repRevenueMonthlySvc.getOne(Monthly);
        if (monthlyResult == null) {
            throw new RuntimeException("获取当月营收记录失败");
        }
        UpdateTurnoverTo updateMonthTurnoverTo = new UpdateTurnoverTo();
        updateMonthTurnoverTo.setId(monthlyResult.getId());
        updateMonthTurnoverTo.setModifiedTimestamp(new Date().getTime());
        updateMonthTurnoverTo.setNewTurnover(monthlyResult.getTurnover().subtract(returnTurnoverTo.getRealMoney()));
        updateMonthTurnoverTo.setOldTurnover(monthlyResult.getTurnover());
        updateMonthTurnoverTo.setNewOrderNumber(monthlyResult.getOrderNumber() - 1);
        updateMonthTurnoverTo.setNewCost(monthlyResult.getCost().subtract(costTitle));
        updateMonthTurnoverTo.setNewProfit(monthlyResult.getProfit().subtract(profitTitle));

        updateMonthTurnoverTo.setCash(monthlyResult.getCash().subtract(cash));
        updateMonthTurnoverTo.setWxpay(monthlyResult.getWxpay().subtract(wxpay));
        updateMonthTurnoverTo.setAlipay(monthlyResult.getAlipay().subtract(alipay));
        updateMonthTurnoverTo.setCashback(monthlyResult.getCashback().subtract(cashback));

        log.info("更新当月营收记录的参数为updateMonthTurnoverTo-{}", updateMonthTurnoverTo);
        if (repRevenueMonthlyMapper.updateMonthTurnover(updateMonthTurnoverTo) != 1) {
            throw new RuntimeException("更新当月营收记录失败");
        }

        // 更新当年营收记录
        RepRevenueAnnualMo annual = new RepRevenueAnnualMo();
        annual.setYear(ca.get(Calendar.YEAR));
        annual.setShopId(returnTurnoverTo.getShopId());
        RepRevenueAnnualMo annualResult = repRevenueAnnualSvc.getOne(annual);

        if (annualResult == null) {
            throw new RuntimeException("获取当年营收记录失败");
        }
        UpdateTurnoverTo updateYearTurnoverTo = new UpdateTurnoverTo();
        updateYearTurnoverTo.setId(annualResult.getId());
        updateYearTurnoverTo.setModifiedTimestamp(new Date().getTime());
        updateYearTurnoverTo.setNewTurnover(annualResult.getTurnover().subtract(returnTurnoverTo.getRealMoney()));
        updateYearTurnoverTo.setOldTurnover(annualResult.getTurnover());
        updateYearTurnoverTo.setNewOrderNumber(annualResult.getOrderNumber() - 1);
        updateYearTurnoverTo.setNewCost(annualResult.getCost().subtract(costTitle));
        updateYearTurnoverTo.setNewProfit(annualResult.getProfit().subtract(profitTitle));

        updateYearTurnoverTo.setCash(annualResult.getCash().subtract(cash));
        updateYearTurnoverTo.setWxpay(annualResult.getWxpay().subtract(wxpay));
        updateYearTurnoverTo.setAlipay(annualResult.getAlipay().subtract(alipay));
        updateYearTurnoverTo.setCashback(annualResult.getCashback().subtract(cashback));

        log.info("更新年营收记录的参数为updateYearTurnoverTo-{}", updateYearTurnoverTo);
        if (repRevenueAnnualMapper.updateYearTurnover(updateYearTurnoverTo) != 1) {
            throw new RuntimeException("更新当年营收记录失败");
        }
        
        
        // 删除该条营收订单记录
        OrdOrderMo  ordOrderMo= ordOrderSvc.getById(returnTurnoverTo.getOrderId());
        RepRevenueOrderMo getRevanueOrder = new RepRevenueOrderMo();
        getRevanueOrder.setOrderId(ordOrderMo.getPayOrderId());
        getRevanueOrder.setType((byte) 1);
        RepRevenueOrderMo getRevanueResult = repRevenueOrderSvc.getOne(getRevanueOrder);
        if (getRevanueResult == null || repRevenueOrderSvc.del(getRevanueResult.getId()) != 1) {
            throw new RuntimeException("添加订单记录失败");
        }
        
        result.setMsg("更新营收记录成功");
        result.setResult(ResultDic.SUCCESS);
        return result;
    }

}
