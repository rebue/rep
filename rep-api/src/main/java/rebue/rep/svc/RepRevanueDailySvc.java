package rebue.rep.svc;

import rebue.afc.msg.PayDoneMsg;
import rebue.rep.jo.RepRevanueDailyJo;
import rebue.rep.mo.RepRevanueDailyMo;
import rebue.robotech.svc.BaseSvc;

/**
 * 报表-营收报表-日报
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RepRevanueDailySvc extends BaseSvc<java.lang.Long, RepRevanueDailyMo, RepRevanueDailyJo> {

    Void handlePayNotify(final PayDoneMsg payDoneMsg);
}
