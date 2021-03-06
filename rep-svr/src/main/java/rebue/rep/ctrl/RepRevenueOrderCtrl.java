package rebue.rep.ctrl;

import com.github.pagehelper.PageInfo;
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
import rebue.rep.mo.RepRevenueOrderMo;
import rebue.rep.svc.RepRevenueOrderSvc;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.IdRo;
import rebue.robotech.ro.Ro;

/**
 * 营收报表订单记录(主要是为了控制幂等)
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
@Slf4j
public class RepRevenueOrderCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RepRevenueOrderSvc svc;

    /**
     * 添加营收报表订单记录
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rep/revenue-order")
    IdRo add(@RequestBody final RepRevenueOrderMo mo) throws Exception {
        log.info("received post:/rep/revenue-order");
        log.info("revenueOrderCtrl.add: {}", mo);
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
     * 修改营收报表订单记录
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rep/revenue-order")
    Ro modify(@RequestBody final RepRevenueOrderMo mo) throws Exception {
        log.info("received put:/rep/revenue-order");
        log.info("revenueOrderCtrl.modify: {}", mo);
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
     * 删除营收报表订单记录
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rep/revenue-order")
    Ro del(@RequestParam("id") final java.lang.Long id) {
        log.info("received delete:/rep/revenue-order");
        log.info("revenueOrderCtrl.del: {}", id);
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
     * 查询营收报表订单记录
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rep/revenue-order")
    PageInfo<RepRevenueOrderMo> list(final RepRevenueOrderMo mo, @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        log.info("received get:/rep/revenue-order");
        log.info("revenueOrderCtrl.list: {},pageNum-{},pageSize-{}", mo, pageNum, pageSize);
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        log.info("list RepRevenueOrderMo:" + mo + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            final String msg = "pageSize不能大于50";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        final PageInfo<RepRevenueOrderMo> result = svc.list(mo, pageNum, pageSize);
        log.info("result: " + result);
        return result;
    }

    /**
     * 获取单个营收报表订单记录
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rep/revenue-order/get-by-id")
    RepRevenueOrderMo getById(@RequestParam("id") final java.lang.Long id) {
        log.info("received get:/rep/revenue-order/get-by-id");
        log.info("revenueOrderCtrl.getById: {}", id);
        return svc.getById(id);
    }
}
