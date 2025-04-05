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
import on.ssgdeal.consumer.dto.CreateStockDto;

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

    public void increase(Long value) {
        stock += value;
    }

    public void decrease(Long value) {
        stock -= value;
    }
}
