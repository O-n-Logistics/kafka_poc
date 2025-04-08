package on.ssgdeal.producer;

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
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public String createOrder(String name) {
        Orders order = Orders.create(name);
        orderRepository.save(order);

        try {
            log.info("재고 감소 요청: {}", name);
            StockDecreaseEvent event = new StockDecreaseEvent(name, 1L);
            kafkaTemplate.send(Topic.ORDER.getValue(), event);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("재고 감소 요청에 실패했습니다.", e);
        }
        return "주문 생성에 성공했습니다. 주문번호: " + order.getId();
    }

    @Transactional
    @KafkaListener(topics = "rollback.order.event", groupId = "saga-example-group",
        containerFactory = "kafkaListenerContainerFactory")
    public void rollbackOrder(OrderRollbackEvent event) {
        String productName = event.getProductName();
        log.info("주문 롤백 요청, {}", productName);

        orderRepository.deleteByProductName(productName);

        if (!orderRepository.existsByProductName(productName)) {
            log.info("주문 롤백 성공, {}", productName);
        }
    }
}
