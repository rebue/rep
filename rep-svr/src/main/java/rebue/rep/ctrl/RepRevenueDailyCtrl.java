package rebue.rep.ctrl;

import com.github.pagehelper.PageInfo;

import java.util.List;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rebue.afc.msg.PayDoneMsg;
import rebue.rep.mo.RepRevenueDailyMo;
import rebue.rep.ro.RepRevenueRo;
import rebue.rep.svc.RepRevenueDailySvc;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.IdRo;
import rebue.robotech.ro.Ro;

/**
 * 报表-营收报表-日报
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
@Slf4j
public class RepRevenueDailyCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RepRevenueDailySvc svc;

    /**
     * 添加报表-营收报表-日报
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rep/revenue-daily")
    IdRo add(@RequestBody final RepRevenueDailyMo mo) throws Exception {
        log.info("received post:/rep/revenue-daily");
        log.info("revenueDailyCtrl.add: {}", mo);
        final IdRo ro = new IdRo();
        try {
            final int result = svc.add(mo);
            if (result == 1) {
                final String msg = "添加成功";
                log.info("{}: mo-{}", msg, mo);
                ro.setMsg(msg);
                ro.setResult(ResultDic.SUCCESS);
                ro.setId(mo.getId().toString());
                return ro;
            } else {
                final String msg = "添加失败";
                log.error("{}: mo-{}", msg, mo);
                ro.setMsg(msg);
                ro.setResult(ResultDic.FAIL);
                return ro;
            }
        } catch (final DuplicateKeyException e) {
            final String msg = "添加失败，唯一键重复：" + e.getCause().getMessage();
            log.error(msg + ": mo-" + mo, e);
            ro.setMsg(msg);
            ro.setResult(ResultDic.FAIL);
            return ro;
        } catch (final RuntimeException e) {
            final String msg = "添加失败，出现运行时异常";
            log.error(msg + ": mo-" + mo, e);
            ro.setMsg(msg);
            ro.setResult(ResultDic.FAIL);
            return ro;
        }
    }

    /**
     * 修改报表-营收报表-日报
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rep/revenue-daily")
    Ro modify(@RequestBody final RepRevenueDailyMo mo) throws Exception {
        log.info("received put:/rep/revenue-daily");
        log.info("revenueDailyCtrl.modify: {}", mo);
        try {
            if (svc.modify(mo) == 1) {
                final String msg = "修改成功";
                log.info("{}: mo-{}", msg, mo);
                return new Ro(ResultDic.SUCCESS, msg);
            } else {
                final String msg = "修改失败";
                log.error("{}: mo-{}", msg, mo);
                return new Ro(ResultDic.FAIL, msg);
            }
        } catch (final DuplicateKeyException e) {
            final String msg = "修改失败，唯一键重复：" + e.getCause().getMessage();
            log.error(msg + ": mo=" + mo, e);
            return new Ro(ResultDic.FAIL, msg);
        } catch (final RuntimeException e) {
            final String msg = "修改失败，出现运行时异常";
            log.error(msg + ": mo-" + mo, e);
            return new Ro(ResultDic.FAIL, msg);
        }
    }

    /**
     * 删除报表-营收报表-日报
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rep/revenue-daily")
    Ro del(@RequestParam("id") final java.lang.Long id) {
        log.info("received delete:/rep/revenue-daily");
        log.info("revenueDailyCtrl.del: {}", id);
        final int result = svc.del(id);
        if (result == 1) {
            final String msg = "删除成功";
            log.info("{}: id-{}", msg, id);
            return new Ro(ResultDic.SUCCESS, msg);
        } else {
            final String msg = "删除失败，找不到该记录";
            log.error("{}: id-{}", msg, id);
            return new Ro(ResultDic.FAIL, msg);
        }
    }

    /**
     * 查询报表-营收报表-日报
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rep/revenue-daily")
    PageInfo<RepRevenueDailyMo> list(final RepRevenueDailyMo mo,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        log.info("received get:/rep/revenue-daily");
        log.info("revenueDailyCtrl.list: {},pageNum-{},pageSize-{}", mo, pageNum, pageSize);
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        log.info("list RepRevenueDailyMo:" + mo + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            final String msg = "pageSize不能大于50";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        final PageInfo<RepRevenueDailyMo> result = svc.list(mo, pageNum, pageSize);
        log.info("result: " + result);
        return result;
    }

    /**
     * 获取单个报表-营收报表-日报
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rep/revenue-daily/get-by-id")
    RepRevenueDailyMo getById(@RequestParam("id") final java.lang.Long id) {
        log.info("received get:/rep/revenue-daily/get-by-id");
        log.info("revenueDailyCtrl.getById: {}", id);
        return svc.getById(id);
    }

    /**
     * 模拟收到支付完成通知
     * 
     * @param mo
     */
    @PutMapping("/rep/revenue-daily/modify-revenue")
    void modifyRevenue(@RequestBody final PayDoneMsg payDoneMsg) {
        log.info("单元测试收到支付完成通知-{}", payDoneMsg);
        svc.handlePayNotify(payDoneMsg);
    }

    /**
     * 根据店铺id和时间查询统计日报
     * 
     * @param shopId
     * @param revenueTime
     * @return
     */
    @GetMapping("rep/revenue-daily/list-revenue-of-Day")
    List<RepRevenueRo> listRevenueOfDay(@RequestParam("shopId") final java.lang.Long shopId,
            @RequestParam("revenueStartTime") final java.lang.String revenueStartTime, @RequestParam("revenueEndTime") final java.lang.String revenueEndTime) {
        log.info("根据店铺id和时间查询统计日报参数为shopId-{},-{},-{}", shopId, revenueStartTime,revenueEndTime);
        return svc.listRevenueOfDay(shopId, revenueStartTime,revenueEndTime);
    }

}
