package rebue.rep.ro;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class RepRevenueRo {

    /**
     * 营收时间
     */
    private String revenueTime;

    /**
     * 营收金额
     */
    private BigDecimal total;

}
