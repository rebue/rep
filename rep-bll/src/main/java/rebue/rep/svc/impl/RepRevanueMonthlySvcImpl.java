package rebue.rep.svc.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rebue.rep.dao.RepRevanueMonthlyDao;
import rebue.rep.jo.RepRevanueMonthlyJo;
import rebue.rep.mapper.RepRevanueMonthlyMapper;
import rebue.rep.mo.RepRevanueMonthlyMo;
import rebue.rep.svc.RepRevanueMonthlySvc;
import rebue.robotech.svc.impl.BaseSvcImpl;

/**
 * 报表-营收报表--月报
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
public class RepRevanueMonthlySvcImpl extends BaseSvcImpl<java.lang.Long, RepRevanueMonthlyJo, RepRevanueMonthlyDao, RepRevanueMonthlyMo, RepRevanueMonthlyMapper> implements RepRevanueMonthlySvc {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(RepRevanueMonthlyMo mo) {
        log.info("repRevanueMonthlySvc.add: 添加报表-营收报表--月报 mo-", mo);
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        return super.add(mo);
    }
}