package rebue.rep.sub;

import javax.annotation.Resource;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.github.dozermapper.core.Mapper;
import lombok.extern.slf4j.Slf4j;
import rebue.rep.svc.RepRevenueAnnualSvc;
import rebue.sbs.rabbit.RabbitConsumer;
import rebue.slr.co.SlrExchangeCo;

/**
 * 支付完成通知的订阅者
 * 包括手工记账、V支付和微信支付完成的通知
 */
@Service
@Slf4j
public class RepShopAddDoneSub implements ApplicationListener<ContextRefreshedEvent> {
    
    private final static String ADD_SHOP_DONE_QUEUE_NAME = "rebue.rep.add.shop.done.queue";


    @Resource
    private RabbitConsumer consumer;

    @Resource
    private Mapper dozerMapper;

    @Resource
    private RepRevenueAnnualSvc repRevenueAnnualSvc;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
 
        // 防止里面的代码被运行两次
        if (!(event.getApplicationContext() instanceof AnnotationConfigServletWebServerApplicationContext)) {
            return;
        }
        log.info("订阅添加店铺完成的通知");
        consumer.bind(SlrExchangeCo.SLR_ADD_SHOP_DONE_EXCHANGE_NAME, ADD_SHOP_DONE_QUEUE_NAME, String.class, (msg) -> {
            log.info("收到添加店铺完成的通知: {}", msg);
            if (!addShopDone(msg)) {
                return false;
            }
            return true;
        });
    }

    /**
     * 处理添加店铺完成的通知
     */
    private boolean addShopDone(final String msg) {
        try {
            // 记录支付信息
            log.info("收到添加店铺完成通知payDoneMsg-{},", msg);
            repRevenueAnnualSvc.createRevenueReportTask();
            return true;
        } catch (final DuplicateKeyException e) {
            log.warn("收到重复的消息: " + msg, e);
            return true;
        } catch (final Exception e) {
            log.error("处理添加店铺完成通知出现异常", e);
            return false;
        }
    }
}
