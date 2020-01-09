package rebue.rep.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * 营收报表订单记录(主要是为了控制幂等)
 *
 * 数据库表: REP_REVENUE_ORDER
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RepRevenueOrderMo implements Serializable {

    /**
     *    订单记录ID
     *
     *    数据库字段: REP_REVENUE_ORDER.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    订单ID
     *
     *    数据库字段: REP_REVENUE_ORDER.ORDER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long orderId;

    /**
     *    类型有1：营收2：退款
     *
     *    数据库字段: REP_REVENUE_ORDER.TYPE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Byte type;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    订单记录ID
     *
     *    数据库字段: REP_REVENUE_ORDER.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    订单记录ID
     *
     *    数据库字段: REP_REVENUE_ORDER.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    订单ID
     *
     *    数据库字段: REP_REVENUE_ORDER.ORDER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     *    订单ID
     *
     *    数据库字段: REP_REVENUE_ORDER.ORDER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     *    类型有1：营收2：退款
     *
     *    数据库字段: REP_REVENUE_ORDER.TYPE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getType() {
        return type;
    }

    /**
     *    类型有1：营收2：退款
     *
     *    数据库字段: REP_REVENUE_ORDER.TYPE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setType(Byte type) {
        this.type = type;
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
        sb.append(", orderId=").append(orderId);
        sb.append(", type=").append(type);
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
        RepRevenueOrderMo other = (RepRevenueOrderMo) that;
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
