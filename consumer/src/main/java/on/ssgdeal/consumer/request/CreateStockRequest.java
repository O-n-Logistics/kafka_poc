package on.ssgdeal.consumer.request;

import on.ssgdeal.consumer.dto.CreateStockDto;

public record CreateStockRequest(
    String productName,
    Long stock
) {
    public static CreateStockRequest from(String productName, Long stock) {
        return new CreateStockRequest(
            productName,
            stock
        );
    }

}
