package rebue.rep.svc;

import java.util.List;

import rebue.rep.jo.RepRevenueWeeklyJo;
import rebue.rep.mo.RepRevenueWeeklyMo;
import rebue.rep.ro.RepRevenueRo;
import rebue.robotech.svc.BaseSvc;

/**
 * 报表-营收报表-周报
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RepRevenueWeeklySvc extends BaseSvc<java.lang.Long, RepRevenueWeeklyMo, RepRevenueWeeklyJo> {

    List<RepRevenueRo> listRevenueOfWeek(Long shopId, String revenueTime);
}
