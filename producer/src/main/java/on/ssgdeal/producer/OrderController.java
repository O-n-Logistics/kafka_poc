package on.ssgdeal.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public String createOrder(
        @RequestBody String name
    ) {
        try {
            // 주문 생성
            return orderService.createOrder(name);
        } catch (RuntimeException e) {
            // 예외 처리
            return e.getMessage();
        }
    }
}
