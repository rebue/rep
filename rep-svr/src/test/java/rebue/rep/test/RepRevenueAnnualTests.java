package rebue.rep.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rebue.rep.mo.RepRevenueAnnualMo;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.IdRo;
import rebue.robotech.ro.Ro;
import rebue.wheel.OkhttpUtils;
import rebue.wheel.RandomEx;

/**
 * 报表-营收报表--年报
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
public class RepRevenueAnnualTests {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private final String hostUrl = "http://127.0.0.1:8500";

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private final ObjectMapper _objectMapper = new ObjectMapper();

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() throws IOException, ReflectiveOperationException {
        RepRevenueAnnualMo mo = null;
        for (int i = 0; i < 20; i++) {
            mo = (RepRevenueAnnualMo) RandomEx.randomPojo(RepRevenueAnnualMo.class);
            mo.setId(null);
            log.info("添加报表-营收报表--年报的参数为：" + mo);
            final String addResult = OkhttpUtils.postByJsonParams(hostUrl + "/rep/revenue-annual", mo);
            log.info("添加报表-营收报表--年报的返回值为：" + addResult);
            final IdRo idRo = _objectMapper.readValue(addResult, IdRo.class);
            log.info(idRo.toString());
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            mo.setId(Long.valueOf(idRo.getId()));
        }
        final String listResult = OkhttpUtils.get(hostUrl + "/rep/revenue-annual?pageNum=1&pageSize=5");
        log.info("查询报表-营收报表--年报的返回值为：" + listResult);
        log.info("获取单个报表-营收报表--年报的参数为：" + mo.getId());
        final String getByIdResult = OkhttpUtils.get(hostUrl + "/rep/revenue-annual/get-by-id?id=" + mo.getId());
        log.info("获取单个报表-营收报表--年报的返回值为：" + getByIdResult);
        log.info("修改报表-营收报表--年报的参数为：" + mo);
        final String modifyResult = OkhttpUtils.putByJsonParams(hostUrl + "/rep/revenue-annual", mo);
        log.info("修改报表-营收报表--年报的返回值为：" + modifyResult);
        final Ro modifyRo = _objectMapper.readValue(modifyResult, Ro.class);
        log.info(modifyRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());
        log.info("删除报表-营收报表--年报的参数为：" + mo.getId());
        final String deleteResult = OkhttpUtils.delete(hostUrl + "/rep/revenue-annual?id=" + mo.getId());
        log.info("删除报表-营收报表--年报的返回值为：" + deleteResult);
        final Ro deleteRo = _objectMapper.readValue(deleteResult, Ro.class);
        log.info(deleteRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());
    }
    
    

    // @Test
    public void testDate() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        // 获取是第多少天
        System.out.println(ca.get(Calendar.DAY_OF_YEAR));
        // 获取是第几周
        System.out.println(ca.get(Calendar.WEEK_OF_YEAR));
        // 获取是第月周
        System.out.println(ca.get(Calendar.MONTH) + 1);
        // 获取那一年
        System.out.println(ca.get(Calendar.YEAR));

        Calendar cale = null;
        cale = Calendar.getInstance();
        // 获取当月第一天和最后一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday, lastday;
        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        // 获取前月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime());
        System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);
    }

  

    @Test
    public void testWeekCount() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        double dayCount = (ca.get(Calendar.YEAR) % 400 == 0 && ca.get(Calendar.YEAR) % 100 == 0 ? 366 : 365);
        int weekCout =(int)  Math.ceil(dayCount / 7);
        System.out.println("天" + dayCount);
        System.out.println("周" + weekCout);

    }
}
