package on.ssgdeal.consumer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Long stock;

    public static Stock create(CreateStockDto dto) {
        return Stock.builder()
            .productName(dto.productName())
            .stock(dto.stock())
            .build();
    }

    public void decrease(Long value) {
        if (stock < value) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        stock -= value;
    }
}
