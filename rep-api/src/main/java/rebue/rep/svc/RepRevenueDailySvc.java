package rebue.rep.svc;

import rebue.afc.msg.PayDoneMsg;
import rebue.rep.jo.RepRevenueDailyJo;
import rebue.rep.mo.RepRevenueDailyMo;
import rebue.robotech.svc.BaseSvc;

/**
 * 报表-营收报表-日报
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RepRevenueDailySvc extends BaseSvc<java.lang.Long, RepRevenueDailyMo, RepRevenueDailyJo> {

    Void handlePayNotify(PayDoneMsg payDoneMsg);
}
