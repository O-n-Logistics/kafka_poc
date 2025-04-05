package on.ssgdeal.consumer.dto;

import on.ssgdeal.consumer.request.IncreaseStockRequest;

public record IncreaseStockDto(
    Long stockId,
    Long increaseAmount
) {
    public static IncreaseStockDto from(
        Long id,
        IncreaseStockRequest request
    ) {
        return new IncreaseStockDto(
            id,
            request.increaseAmount()
        );
    }
}
