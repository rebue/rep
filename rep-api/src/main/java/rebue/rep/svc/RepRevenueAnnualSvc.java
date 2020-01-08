package rebue.rep.svc;

import java.util.List;

import rebue.rep.jo.RepRevenueAnnualJo;
import rebue.rep.mo.RepRevenueAnnualMo;
import rebue.rep.ro.RepRevenueRo;
import rebue.rep.to.ReturnTurnoverTo;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;

/**
 * 报表-营收报表--年报
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RepRevenueAnnualSvc extends BaseSvc<java.lang.Long, RepRevenueAnnualMo, RepRevenueAnnualJo> {

    void createRevenueReportTask();

    List<RepRevenueRo> listRevenueOfDay(Long shopId, String revenueStartTime, String revenueEndTime);

    Ro returnTurnover(ReturnTurnoverTo returnTurnoverTo);
}
