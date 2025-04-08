package on.ssgdeal.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
@NoArgsConstructor
public class OrderRollbackEvent{

    private String productName;

    public OrderRollbackEvent(String productName) {
        this.productName = productName;
    }
}
