package on.ssgdeal.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@JsonSerialize
@NoArgsConstructor
@Setter
public class StockDecreaseEvent {

    private String productName;
    private Long quantity;

    public StockDecreaseEvent(String productName, Long quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }
}
