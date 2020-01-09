package rebue.rep.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.expression.ParseException;
import rebue.afc.msg.PayDoneMsg;
import rebue.rep.mo.RepRevenueDailyMo;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.IdRo;
import rebue.robotech.ro.Ro;
import rebue.wheel.OkhttpUtils;
import rebue.wheel.RandomEx;

/**
 * 报表-营收报表-日报
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
public class RepRevenueDailyTests {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private final String hostUrl = "http://127.0.0.1:8500";

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private final ObjectMapper _objectMapper = new ObjectMapper();

    // @Test
    public void testCrud() throws IOException, ReflectiveOperationException {
        RepRevenueDailyMo mo = null;
        for (int i = 0; i < 20; i++) {
            mo = (RepRevenueDailyMo) RandomEx.randomPojo(RepRevenueDailyMo.class);
            mo.setId(null);
            log.info("添加报表-营收报表-日报的参数为：" + mo);
            final String addResult = OkhttpUtils.postByJsonParams(hostUrl + "/rep/revenue-daily", mo);
            log.info("添加报表-营收报表-日报的返回值为：" + addResult);
            final IdRo idRo = _objectMapper.readValue(addResult, IdRo.class);
            log.info(idRo.toString());
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            mo.setId(Long.valueOf(idRo.getId()));
        }
        final String listResult = OkhttpUtils.get(hostUrl + "/rep/revenue-daily?pageNum=1&pageSize=5");
        log.info("查询报表-营收报表-日报的返回值为：" + listResult);
        log.info("获取单个报表-营收报表-日报的参数为：" + mo.getId());
        final String getByIdResult = OkhttpUtils.get(hostUrl + "/rep/revenue-daily/get-by-id?id=" + mo.getId());
        log.info("获取单个报表-营收报表-日报的返回值为：" + getByIdResult);
        log.info("修改报表-营收报表-日报的参数为：" + mo);
        final String modifyResult = OkhttpUtils.putByJsonParams(hostUrl + "/rep/revenue-daily", mo);
        log.info("修改报表-营收报表-日报的返回值为：" + modifyResult);
        final Ro modifyRo = _objectMapper.readValue(modifyResult, Ro.class);
        log.info(modifyRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());
        log.info("删除报表-营收报表-日报的参数为：" + mo.getId());
        final String deleteResult = OkhttpUtils.delete(hostUrl + "/rep/revenue-daily?id=" + mo.getId());
        log.info("删除报表-营收报表-日报的返回值为：" + deleteResult);
        final Ro deleteRo = _objectMapper.readValue(deleteResult, Ro.class);
        log.info(deleteRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());
    }

    @Test
    public void modifyRevenue() throws IOException {
        log.info("测试支付完成通知");
        PayDoneMsg payDoneMsg = new PayDoneMsg();
        payDoneMsg.setPayAccountId("-1");
        payDoneMsg.setOrderId("674482426814267404");
        payDoneMsg.setPayAmount(new BigDecimal("20"));
        payDoneMsg.setUserId(1l);
        OkhttpUtils.putByJsonParams(hostUrl + "/rep/revenue-daily/modify-revenue", payDoneMsg);
    }

    // @Test
    public void method_2() throws java.text.ParseException {
        // 创建Calendar对象
        Calendar calendar = Calendar.getInstance();
        // 定义输入时间的格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 从键盘上获取时间
        String date = "2019-03-17";
        try {
            // 将输入的时间转化为Date对象
            Date date2 = format.parse(date);
            // 将Date对象传给calendar
            calendar.setTime(date2);
            // 获取它在这 一年中是第几天
            System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DATE, 1);
        calendar1.roll(Calendar.DATE, -1);
        System.out.println(calendar1.getTimeInMillis());
        final Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        final int last = cal.getActualMinimum(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.DAY_OF_YEAR, last);
        System.out.println("----------------");
        String date1 = "2019-12-31";
        cal.setTime(format.parse(date1));
        cal.add(Calendar.DAY_OF_YEAR, 1);
        System.out.println(format.format(cal.getTime()));
    }

    // @Test
    public void textRevenue() throws IOException {
        // final String listResult = OkhttpUtils.get(hostUrl + "/rep/revenue-daily/list-revenue-of-Day?shopId="
        // + 583124897568522240l + "&revenueTime=2019-11-25");
        final String listResult = OkhttpUtils.get(hostUrl + "/rep/revenue-daily/list-revenue-of-Day?shopId=" + 583124897568522240l + "&revenueStartTime=2019-12-30" + "&revenueEndTime=2020-01-03");
        System.out.println(listResult);
    }
}
