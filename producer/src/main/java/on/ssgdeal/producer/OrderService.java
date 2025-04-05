package on.ssgdeal.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.ssgdeal.common.StockDecreaseEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private static final String TOPIC = "order-events";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public String createOrder(String name) {
        Orders order = Orders.create(name);
        orderRepository.save(order);

        // 재고 감소
        try {
            Long quantity = 1L;
            Long stockId = 1L;
            log.info("재고 감소 요청, {}: {}개", order.getId(), quantity);

            // publisher로 추출
            StockDecreaseEvent event = new StockDecreaseEvent(stockId, quantity);
            kafkaTemplate.send(TOPIC, event);
            // -----

        } catch (Exception e) {
           // 재고 상승
            log.error(e.getMessage());
            throw new RuntimeException("재고 감소 요청에 실패했습니다.", e);
        }

        return "주문 생성에 성공했습니다.";
    }

}