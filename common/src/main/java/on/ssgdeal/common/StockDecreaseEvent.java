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

    private Long stockId;
    private Long quantity;

    public StockDecreaseEvent(Long stockId, Long quantity) {
        this.stockId = stockId;
        this.quantity = quantity;
    }
}
