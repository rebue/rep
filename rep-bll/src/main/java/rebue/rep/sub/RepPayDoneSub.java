package rebue.rep.sub;

import javax.annotation.Resource;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.github.dozermapper.core.Mapper;
import lombok.extern.slf4j.Slf4j;
import rebue.afc.co.AfcExchangeCo;
import rebue.afc.msg.PayDoneMsg;
import rebue.rep.svc.RepRevenueDailySvc;
import rebue.sbs.rabbit.RabbitConsumer;

/**
 * 支付完成通知的订阅者
 * 包括手工记账、V支付和微信支付完成的通知
 */
@Service
@Slf4j
public class RepPayDoneSub implements ApplicationListener<ContextRefreshedEvent> {
    
    private final static String PAY_DONE_QUEUE_NAME = "rebue.rep.pay.done.queue";


    @Resource
    private RabbitConsumer consumer;

    @Resource
    private Mapper dozerMapper;

    @Resource
    private RepRevenueDailySvc repRevanueDailySvc;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
 
        // 防止里面的代码被运行两次
        if (!(event.getApplicationContext() instanceof AnnotationConfigServletWebServerApplicationContext)) {
            return;
        }
        log.info("订阅支付完成的通知");
        consumer.bind(AfcExchangeCo.PAY_DONE_EXCHANGE_NAME, PAY_DONE_QUEUE_NAME, PayDoneMsg.class, (msg) -> {
            log.info("收到支付完成的通知: {}", msg);
            if (!handlePayNotify(msg)) {
                return false;
            }
            return true;
        });
    }

    /**
     * 处理支付完成的通知
     */
    private boolean handlePayNotify(final PayDoneMsg payDoneMsg) {
        try {
            // 记录支付信息
            log.info("收到支付完成通知payDoneMsg-{},", payDoneMsg);
            repRevanueDailySvc.handlePayNotify(payDoneMsg);
            return true;
        } catch (final DuplicateKeyException e) {
            log.warn("收到重复的消息: " + payDoneMsg, e);
            return true;
        } catch (final Exception e) {
            log.error("处理支付完成通知出现异常", e);
            return false;
        }
    }
}
