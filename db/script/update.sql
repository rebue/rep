


-- ------------------------------------------------------- 下面的已更新到线上 2019.12.04 ----------------------------------------------------

alter table REP_REVENUE_ANNUAL add  CASH                 decimal(20,4) not null default 0 comment '现金支付';
alter table REP_REVENUE_ANNUAL add   WXPAY                decimal(20,4) not null default 0 comment '微信支付';
alter table REP_REVENUE_ANNUAL add   ALIPAY               decimal(20,4) not null default 0 comment '支付宝支付';
alter table REP_REVENUE_ANNUAL add   CASHBACK             decimal(20,4) not null default 0 comment '返现金支付';


alter table REP_REVENUE_DAILY add   CASH                 decimal(20,4) not null default 0 comment '现金支付';
alter table REP_REVENUE_DAILY add   WXPAY                decimal(20,4) not null default 0 comment '微信支付';
alter table REP_REVENUE_DAILY add   ALIPAY               decimal(20,4) not null default 0 comment '支付宝支付';
alter table REP_REVENUE_DAILY add   CASHBACK             decimal(20,4) not null default 0 comment '返现金支付';

alter table REP_REVENUE_MONTHLY add   CASH                 decimal(20,4) not null default 0 comment '现金支付';
alter table REP_REVENUE_MONTHLY add   WXPAY                decimal(20,4) not null default 0 comment '微信支付';
alter table REP_REVENUE_MONTHLY add   ALIPAY               decimal(20,4) not null default 0 comment '支付宝支付';
alter table REP_REVENUE_MONTHLY add   CASHBACK             decimal(20,4) not null default 0 comment '返现金支付';

alter table REP_REVENUE_WEEKLY add   CASH                 decimal(20,4) not null default 0 comment '现金支付';
alter table REP_REVENUE_WEEKLY add   WXPAY                decimal(20,4) not null default 0 comment '微信支付';
alter table REP_REVENUE_WEEKLY add   ALIPAY               decimal(20,4) not null default 0 comment '支付宝支付';
alter table REP_REVENUE_WEEKLY add   CASHBACK             decimal(20,4) not null default 0 comment '返现金支付';