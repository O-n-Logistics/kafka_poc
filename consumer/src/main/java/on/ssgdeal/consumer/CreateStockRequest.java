package on.ssgdeal.consumer;

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
