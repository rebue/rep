package rebue.rep.to;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UpdateTurnoverTo {

    private Long id;

    private BigDecimal newTurnover;

    private BigDecimal oldTurnover;
    
    private BigDecimal newCost;
    
    private BigDecimal newProfit;

    private Long newOrderNumber;
    
    private Long modifiedTimestamp;

}
