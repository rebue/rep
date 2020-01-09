package rebue.rep.jo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the REP_REVENUE_MONTHLY database table.
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "REP_REVENUE_MONTHLY")
@Getter
@Setter
@ToString
public class RepRevenueMonthlyJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 月报ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 19)
    private Long id;

    /**
     * 营业额
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "TURNOVER", nullable = false, precision = 20, scale = 4)
    private BigDecimal turnover;

    /**
     * 订单数
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "ORDER_NUMBER", nullable = false, length = 19)
    private Long orderNumber;

    /**
     * 利润
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "PROFIT", nullable = false, precision = 20, scale = 4)
    private BigDecimal profit;

    /**
     * 成本
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "COST", nullable = false, precision = 20, scale = 4)
    private BigDecimal cost;

    /**
     * 年中的哪一月，为了类型统一就使用Integer
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "MONTH_OF_YEAR", nullable = false, length = 10)
    private Integer monthOfYear;

    /**
     * 这个月的记录是属于哪一年的
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "YEAR", nullable = false, length = 10)
    private Integer year;

    /**
     * 店铺ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "SHOP_ID", nullable = false, length = 19)
    private Long shopId;

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "MODIFIED_TIMESTAMP", nullable = false, length = 19)
    private Long modifiedTimestamp;

    /**
     * 现金支付
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "CASH", nullable = false, precision = 20, scale = 4)
    private BigDecimal cash;

    /**
     * 微信支付
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "WXPAY", nullable = false, precision = 20, scale = 4)
    private BigDecimal wxpay;

    /**
     * 支付宝支付
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "ALIPAY", nullable = false, precision = 20, scale = 4)
    private BigDecimal alipay;

    /**
     * 返现金支付
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "CASHBACK", nullable = false, precision = 20, scale = 4)
    private BigDecimal cashback;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RepRevenueMonthlyJo other = (RepRevenueMonthlyJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
