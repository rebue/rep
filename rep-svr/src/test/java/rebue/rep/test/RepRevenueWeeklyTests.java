package rebue.rep.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.expression.ParseException;

import rebue.rep.mo.RepRevenueWeeklyMo;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.IdRo;
import rebue.robotech.ro.Ro;
import rebue.wheel.OkhttpUtils;
import rebue.wheel.RandomEx;

/**
 * 报表-营收报表-周报
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
public class RepRevenueWeeklyTests {

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
  //  @Test
    public void testCrud() throws IOException, ReflectiveOperationException {
        RepRevenueWeeklyMo mo = null;
        for (int i = 0; i < 20; i++) {
            mo = (RepRevenueWeeklyMo) RandomEx.randomPojo(RepRevenueWeeklyMo.class);
            mo.setId(null);
            log.info("添加报表-营收报表-周报的参数为：" + mo);
            final String addResult = OkhttpUtils.postByJsonParams(hostUrl + "/rep/revenue-weekly", mo);
            log.info("添加报表-营收报表-周报的返回值为：" + addResult);
            final IdRo idRo = _objectMapper.readValue(addResult, IdRo.class);
            log.info(idRo.toString());
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            mo.setId(Long.valueOf(idRo.getId()));
        }
        final String listResult = OkhttpUtils.get(hostUrl + "/rep/revenue-weekly?pageNum=1&pageSize=5");
        log.info("查询报表-营收报表-周报的返回值为：" + listResult);
        log.info("获取单个报表-营收报表-周报的参数为：" + mo.getId());
        final String getByIdResult = OkhttpUtils.get(hostUrl + "/rep/revenue-weekly/get-by-id?id=" + mo.getId());
        log.info("获取单个报表-营收报表-周报的返回值为：" + getByIdResult);
        log.info("修改报表-营收报表-周报的参数为：" + mo);
        final String modifyResult = OkhttpUtils.putByJsonParams(hostUrl + "/rep/revenue-weekly", mo);
        log.info("修改报表-营收报表-周报的返回值为：" + modifyResult);
        final Ro modifyRo = _objectMapper.readValue(modifyResult, Ro.class);
        log.info(modifyRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());
        log.info("删除报表-营收报表-周报的参数为：" + mo.getId());
        final String deleteResult = OkhttpUtils.delete(hostUrl + "/rep/revenue-weekly?id=" + mo.getId());
        log.info("删除报表-营收报表-周报的返回值为：" + deleteResult);
        final Ro deleteRo = _objectMapper.readValue(deleteResult, Ro.class);
        log.info(deleteRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());
    }
    
    
    @Test
    public void method_2() throws java.text.ParseException {
        // 创建Calendar对象
        Calendar calendar = Calendar.getInstance();
        // 定义输入时间的格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        // 从键盘上获取时间
        String date = "2019-04-17";
        try {
            // 将输入的时间转化为Date对象
            Date date2 = format.parse(date);
            // 将Date对象传给calendar
            calendar.setTime(date2);
            // 获取它在这 一年中是第第几周
            System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
