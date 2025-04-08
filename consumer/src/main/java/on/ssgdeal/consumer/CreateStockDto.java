package on.ssgdeal.consumer;

public record CreateStockDto(
    String productName,
    Long stock
) {

    public static CreateStockDto from(CreateStockRequest request) {
        return new CreateStockDto(
            request.productName(),
            request.stock()
        );
    }
}
