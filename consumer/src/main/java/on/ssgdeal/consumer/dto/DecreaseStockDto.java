package on.ssgdeal.consumer.dto;

import on.ssgdeal.consumer.request.DecreaseStockRequest;

public record DecreaseStockDto(
    Long stockId,
    Long decreaseAmount
) {
    public static DecreaseStockDto from(Long id, DecreaseStockRequest request) {
        return new DecreaseStockDto(
            id,
            request.decreaseAmount()
        );
    }

}