package on.ssgdeal.producer;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/numbers/{number}")
    public String number(@PathVariable int number) {
        try {
            return orderService.numberEvent(number);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/numbers/random")
    public String randomNumber() {
        try {
            Random random = new Random();
            return orderService.numberEvent(random.nextInt(100));
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
