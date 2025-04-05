package on.ssgdeal.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.ssgdeal.common.StockDecreaseEvent;
import on.ssgdeal.consumer.dto.CreateStockDto;
import on.ssgdeal.consumer.dto.IncreaseStockDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {

    private final StockRepository stockRepository;

    @Transactional
    public Long createStock(CreateStockDto createStockDto) {
        Stock stock = Stock.create(createStockDto);
        stockRepository.save(stock);
        return stock.getId();
    }

    @Transactional
    @KafkaListener(topics = "order-events", groupId = "saga-example-group",
        containerFactory = "kafkaListenerContainerFactory")
    public void decreaseStock(StockDecreaseEvent event) {
        log.info("재고 감소 요청, {}: {}개", event.getStockId(), event.getQuantity());
        Stock stock = findStock(event.getStockId());
        stock.decrease(event.getQuantity());
    }

    @Transactional
    public void increaseStock(IncreaseStockDto dto) {
        Stock stock = findStock(dto.stockId());
        stock.increase(dto.increaseAmount());
    }

    public Stock findStock(Long stockId) {
        return stockRepository.findById(stockId)
            .orElseThrow(() -> new IllegalArgumentException("해당 상품은 없습니다."));
    }

}
