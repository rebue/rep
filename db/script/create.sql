/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/11/23 11:11:22                          */
/*==============================================================*/


drop table if exists REP_REVENUE_ANNUAL;

drop table if exists REP_REVENUE_DAILY;

drop table if exists REP_REVENUE_MONTHLY;

drop table if exists REP_REVENUE_ORDER;

drop table if exists REP_REVENUE_WEEKLY;

/*==============================================================*/
/* Table: REP_REVENUE_ANNUAL                                    */
/*==============================================================*/
create table REP_REVENUE_ANNUAL
(
   ID                   bigint not null comment '年报ID',
   TURNOVER             decimal(20,4) not null default 0 comment '营业额',
   ORDER_NUMBER         bigint not null default 0 comment '订单数',
   PROFIT               decimal(20,4) not null default 0 comment '利润',
   COST                 decimal(20,4) not null default 0 comment '成本',
   YEAR                 int not null comment '时间中的哪一年，为了类型统一就使用Integer',
   MODIFIED_TIMESTAMP   bigint not null comment '修改时间戳',
   SHOP_ID              bigint  comment '店铺ID',
   primary key (ID),
   unique key AK_YAER (YEAR)
);

alter table REP_REVENUE_ANNUAL comment '报表-营收报表--年报';

/*==============================================================*/
/* Table: REP_REVENUE_DAILY                                     */
/*==============================================================*/
create table REP_REVENUE_DAILY
(
   ID                   bigint not null comment '日报ID',
   TURNOVER             decimal(20,4) not null default 0 comment '营业额',
   ORDER_NUMBER         bigint not null default 0 comment '订单数',
   PROFIT               decimal(20,4) not null default 0 comment '利润',
   COST                 decimal(20,4) not null default 0 comment '成本',
   DAY_OF_YEAR          int not null comment '年中的哪一天，为了类型统一就使用Integer',
   YEAR                 int not null comment '这个天的记录是属于哪一年的',
   SHOP_ID              bigint  comment '店铺ID',
   MODIFIED_TIMESTAMP   bigint not null comment '修改时间戳',
   primary key (ID),
   unique key AK_DAY_OF_YEAR_YEAR (DAY_OF_YEAR, YEAR)
);

alter table REP_REVENUE_DAILY comment '报表-营收报表-日报';

/*==============================================================*/
/* Table: REP_REVENUE_MONTHLY                                   */
/*==============================================================*/
create table REP_REVENUE_MONTHLY
(
   ID                   bigint not null comment '月报ID',
   TURNOVER             decimal(20,4) not null default 0 comment '营业额',
   ORDER_NUMBER         bigint not null default 0 comment '订单数',
   PROFIT               decimal(20,4) not null default 0 comment '利润',
   COST                 decimal(20,4) not null default 0 comment '成本',
   MONTH_OF_YEAR        int not null comment '年中的哪一月，为了类型统一就使用Integer',
   YEAR                 int not null comment '这个月的记录是属于哪一年的',
   SHOP_ID              bigint  comment '店铺ID',
   MODIFIED_TIMESTAMP   bigint not null comment '修改时间戳',
   primary key (ID),
   unique key AK_MONTH_OF_YEAR_YEAR (MONTH_OF_YEAR, YEAR)
);

alter table REP_REVENUE_MONTHLY comment '报表-营收报表--月报';

/*==============================================================*/
/* Table: REP_REVENUE_ORDER                                     */
/*==============================================================*/
create table REP_REVENUE_ORDER
(
   ID                   bigint not null comment '订单记录ID',
   ORDER_ID             bigint not null comment '订单ID',
   TYPE                 tinyint not null comment '类型有1：营收2：退款',
   primary key (ID)
);

alter table REP_REVENUE_ORDER comment '营收报表订单记录(主要是为了控制幂等)';

/*==============================================================*/
/* Table: REP_REVENUE_WEEKLY                                    */
/*==============================================================*/
create table REP_REVENUE_WEEKLY
(
   ID                   bigint not null comment '周报ID',
   TURNOVER             decimal(20,4) not null default 0 comment '营业额',
   ORDER_NUMBER         bigint not null default 0 comment '订单数',
   PROFIT               decimal(20,4) not null default 0 comment '利润',
   COST                 decimal(20,4) not null default 0 comment '成本',
   WEEK_OF_YEAR         int not null comment '年中的哪一周，为了类型统一就使用Integer',
   YEAR                 int not null comment '这个周的记录是属于哪一年的',
   SHOP_ID              bigint  comment '店铺ID',
   MODIFIED_TIMESTAMP   bigint not null comment '修改时间戳',
   primary key (ID),
   unique key AK_WEEK_OF_YEAR_YEAR (WEEK_OF_YEAR, YEAR)
);

alter table REP_REVENUE_WEEKLY comment '报表-营收报表-周报';

