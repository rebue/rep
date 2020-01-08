package rebue.rep.to;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 退款后减去营收报表to
 * @author jjl
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class ReturnTurnoverTo {
    
    
    private Long shopId;
    
    private BigDecimal realMoney;
}
