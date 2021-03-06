package rebue.rep.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 报表-营收报表-日报
 *
 * 数据库表: REP_REVENUE_DAILY
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RepRevenueDailyMo implements Serializable {

    /**
     *    日报ID
     *
     *    数据库字段: REP_REVENUE_DAILY.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    营业额
     *
     *    数据库字段: REP_REVENUE_DAILY.TURNOVER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private BigDecimal turnover;

    /**
     *    订单数
     *
     *    数据库字段: REP_REVENUE_DAILY.ORDER_NUMBER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long orderNumber;

    /**
     *    利润
     *
     *    数据库字段: REP_REVENUE_DAILY.PROFIT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private BigDecimal profit;

    /**
     *    成本
     *
     *    数据库字段: REP_REVENUE_DAILY.COST
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private BigDecimal cost;

    /**
     *    年中的哪一天，为了类型统一就使用Integer
     *
     *    数据库字段: REP_REVENUE_DAILY.DAY_OF_YEAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Integer dayOfYear;

    /**
     *    这个天的记录是属于哪一年的
     *
     *    数据库字段: REP_REVENUE_DAILY.YEAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Integer year;

    /**
     *    店铺ID
     *
     *    数据库字段: REP_REVENUE_DAILY.SHOP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long shopId;

    /**
     *    修改时间戳
     *
     *    数据库字段: REP_REVENUE_DAILY.MODIFIED_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long modifiedTimestamp;

    /**
     *    现金支付
     *
     *    数据库字段: REP_REVENUE_DAILY.CASH
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private BigDecimal cash;

    /**
     *    微信支付
     *
     *    数据库字段: REP_REVENUE_DAILY.WXPAY
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private BigDecimal wxpay;

    /**
     *    支付宝支付
     *
     *    数据库字段: REP_REVENUE_DAILY.ALIPAY
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private BigDecimal alipay;

    /**
     *    返现金支付
     *
     *    数据库字段: REP_REVENUE_DAILY.CASHBACK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private BigDecimal cashback;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    日报ID
     *
     *    数据库字段: REP_REVENUE_DAILY.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    日报ID
     *
     *    数据库字段: REP_REVENUE_DAILY.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    营业额
     *
     *    数据库字段: REP_REVENUE_DAILY.TURNOVER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public BigDecimal getTurnover() {
        return turnover;
    }

    /**
     *    营业额
     *
     *    数据库字段: REP_REVENUE_DAILY.TURNOVER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    /**
     *    订单数
     *
     *    数据库字段: REP_REVENUE_DAILY.ORDER_NUMBER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getOrderNumber() {
        return orderNumber;
    }

    /**
     *    订单数
     *
     *    数据库字段: REP_REVENUE_DAILY.ORDER_NUMBER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     *    利润
     *
     *    数据库字段: REP_REVENUE_DAILY.PROFIT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     *    利润
     *
     *    数据库字段: REP_REVENUE_DAILY.PROFIT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    /**
     *    成本
     *
     *    数据库字段: REP_REVENUE_DAILY.COST
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     *    成本
     *
     *    数据库字段: REP_REVENUE_DAILY.COST
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     *    年中的哪一天，为了类型统一就使用Integer
     *
     *    数据库字段: REP_REVENUE_DAILY.DAY_OF_YEAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Integer getDayOfYear() {
        return dayOfYear;
    }

    /**
     *    年中的哪一天，为了类型统一就使用Integer
     *
     *    数据库字段: REP_REVENUE_DAILY.DAY_OF_YEAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDayOfYear(Integer dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    /**
     *    这个天的记录是属于哪一年的
     *
     *    数据库字段: REP_REVENUE_DAILY.YEAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Integer getYear() {
        return year;
    }

    /**
     *    这个天的记录是属于哪一年的
     *
     *    数据库字段: REP_REVENUE_DAILY.YEAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     *    店铺ID
     *
     *    数据库字段: REP_REVENUE_DAILY.SHOP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     *    店铺ID
     *
     *    数据库字段: REP_REVENUE_DAILY.SHOP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     *    修改时间戳
     *
     *    数据库字段: REP_REVENUE_DAILY.MODIFIED_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    /**
     *    修改时间戳
     *
     *    数据库字段: REP_REVENUE_DAILY.MODIFIED_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setModifiedTimestamp(Long modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }

    /**
     *    现金支付
     *
     *    数据库字段: REP_REVENUE_DAILY.CASH
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public BigDecimal getCash() {
        return cash;
    }

    /**
     *    现金支付
     *
     *    数据库字段: REP_REVENUE_DAILY.CASH
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    /**
     *    微信支付
     *
     *    数据库字段: REP_REVENUE_DAILY.WXPAY
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public BigDecimal getWxpay() {
        return wxpay;
    }

    /**
     *    微信支付
     *
     *    数据库字段: REP_REVENUE_DAILY.WXPAY
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxpay(BigDecimal wxpay) {
        this.wxpay = wxpay;
    }

    /**
     *    支付宝支付
     *
     *    数据库字段: REP_REVENUE_DAILY.ALIPAY
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public BigDecimal getAlipay() {
        return alipay;
    }

    /**
     *    支付宝支付
     *
     *    数据库字段: REP_REVENUE_DAILY.ALIPAY
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAlipay(BigDecimal alipay) {
        this.alipay = alipay;
    }

    /**
     *    返现金支付
     *
     *    数据库字段: REP_REVENUE_DAILY.CASHBACK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public BigDecimal getCashback() {
        return cashback;
    }

    /**
     *    返现金支付
     *
     *    数据库字段: REP_REVENUE_DAILY.CASHBACK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCashback(BigDecimal cashback) {
        this.cashback = cashback;
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", turnover=").append(turnover);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", profit=").append(profit);
        sb.append(", cost=").append(cost);
        sb.append(", dayOfYear=").append(dayOfYear);
        sb.append(", year=").append(year);
        sb.append(", shopId=").append(shopId);
        sb.append(", modifiedTimestamp=").append(modifiedTimestamp);
        sb.append(", cash=").append(cash);
        sb.append(", wxpay=").append(wxpay);
        sb.append(", alipay=").append(alipay);
        sb.append(", cashback=").append(cashback);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RepRevenueDailyMo other = (RepRevenueDailyMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
}
