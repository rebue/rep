package rebue.rep.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 报表-营收报表--年报
 *
 * 数据库表: REP_REVANUE_ANNUAL
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RepRevanueAnnualMo implements Serializable {

    /**
     *    年报ID
     *
     *    数据库字段: REP_REVANUE_ANNUAL.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    营业额
     *
     *    数据库字段: REP_REVANUE_ANNUAL.TURNOVER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private BigDecimal turnover;

    /**
     *    订单数
     *
     *    数据库字段: REP_REVANUE_ANNUAL.ORDER_NUMBER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long orderNumber;

    /**
     *    利润
     *
     *    数据库字段: REP_REVANUE_ANNUAL.PROFIT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private BigDecimal profit;

    /**
     *    成本
     *
     *    数据库字段: REP_REVANUE_ANNUAL.COST
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private BigDecimal cost;

    /**
     *    时间中的哪一年，为了类型统一就使用Integer
     *
     *    数据库字段: REP_REVANUE_ANNUAL.YEAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Integer year;

    /**
     *    修改时间戳
     *
     *    数据库字段: REP_REVANUE_ANNUAL.MODIFIED_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long modifiedTimestamp;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    年报ID
     *
     *    数据库字段: REP_REVANUE_ANNUAL.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    年报ID
     *
     *    数据库字段: REP_REVANUE_ANNUAL.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    营业额
     *
     *    数据库字段: REP_REVANUE_ANNUAL.TURNOVER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public BigDecimal getTurnover() {
        return turnover;
    }

    /**
     *    营业额
     *
     *    数据库字段: REP_REVANUE_ANNUAL.TURNOVER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    /**
     *    订单数
     *
     *    数据库字段: REP_REVANUE_ANNUAL.ORDER_NUMBER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getOrderNumber() {
        return orderNumber;
    }

    /**
     *    订单数
     *
     *    数据库字段: REP_REVANUE_ANNUAL.ORDER_NUMBER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     *    利润
     *
     *    数据库字段: REP_REVANUE_ANNUAL.PROFIT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     *    利润
     *
     *    数据库字段: REP_REVANUE_ANNUAL.PROFIT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    /**
     *    成本
     *
     *    数据库字段: REP_REVANUE_ANNUAL.COST
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     *    成本
     *
     *    数据库字段: REP_REVANUE_ANNUAL.COST
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     *    时间中的哪一年，为了类型统一就使用Integer
     *
     *    数据库字段: REP_REVANUE_ANNUAL.YEAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Integer getYear() {
        return year;
    }

    /**
     *    时间中的哪一年，为了类型统一就使用Integer
     *
     *    数据库字段: REP_REVANUE_ANNUAL.YEAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     *    修改时间戳
     *
     *    数据库字段: REP_REVANUE_ANNUAL.MODIFIED_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    /**
     *    修改时间戳
     *
     *    数据库字段: REP_REVANUE_ANNUAL.MODIFIED_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setModifiedTimestamp(Long modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
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
        sb.append(", year=").append(year);
        sb.append(", modifiedTimestamp=").append(modifiedTimestamp);
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
        RepRevanueAnnualMo other = (RepRevanueAnnualMo) that;
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
