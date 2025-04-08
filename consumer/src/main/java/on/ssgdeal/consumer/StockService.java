package on.ssgdeal.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.ssgdeal.common.OrderRollbackEvent;
import on.ssgdeal.common.StockDecreaseEvent;
import on.ssgdeal.common.Topic;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {

    private final StockRepository stockRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public Long createStock(CreateStockDto createStockDto) {
        Stock stock = Stock.create(createStockDto);
        stockRepository.save(stock);
        return stock.getId();
    }

    @Transactional
    @KafkaListener(topics = "create.order.event", groupId = "saga-example-group",
        containerFactory = "kafkaListenerContainerFactory")
    public void decreaseStock(StockDecreaseEvent event) {
        log.info("재고 감소 요청, {}: {}개", event.getProductName(), event.getQuantity());
        Stock stock = stockRepository.findByProductName(event.getProductName())
            .orElseThrow(() -> new IllegalArgumentException("해당 상품은 없습니다."));
        try {
            log.info("재고 감소 전, {}: {}개", stock.getProductName(), stock.getStock());
            stock.decrease(event.getQuantity());
            log.info("재고 감소 후, {}: {}개", stock.getProductName(), stock.getStock());
        } catch (Exception e) {
            log.warn("재고 감소 실패, 롤백 처리, {}", stock.getProductName());
            OrderRollbackEvent orderRollbackEvent = new OrderRollbackEvent(stock.getProductName());
            kafkaTemplate.send(Topic.ORDER_ROLLBACK.getValue(), orderRollbackEvent);
        }
    }

    @Transactional(readOnly = true)
    public Stock findStock(Long id) {
        return stockRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 재고는 없습니다."));
    }
}
