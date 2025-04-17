package on.ssgdeal.consumertwo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.ssgdeal.common.NumberEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @RetryableTopic(
        attempts = "1",
        timeout = "5000",
        backoff = @Backoff(delay = 1, multiplier = 1.0),
        dltTopicSuffix = ".dlt"
    )
    @KafkaListener(
        topics = "number.event",
        groupId = "saga-example-group"
    )
    public void noOdd(NumberEvent event) {
        log.info("NUMBER!!! I hate Odd!!!  - {}", event.getNumber());
        if (event.getNumber() % 2 > 0) {
            log.error("홀수는 안돼!");
            throw new RuntimeException();
        }
        log.info("짝수는 인정이지~");
    }

    @RetryableTopic(
        attempts = "1",
        timeout = "5000",
        backoff = @Backoff(delay = 1, multiplier = 1.0),
        dltTopicSuffix = ".dlt"
    )
    @KafkaListener(
        topics = "number.event",
        groupId = "saga-example-group-2"
    )
    public void noEven(NumberEvent event) {
        log.info("NUMBER!!! I hate Even!!!  - {}", event.getNumber());
        if (event.getNumber() % 2 == 0) {
            log.error("짝수는 안돼!");
            throw new RuntimeException();
        }
        log.info("홀수는 인정이지~");
    }

    @KafkaListener(
        topics = "number.event.dlt",
        groupId = "saga-example-group"
    )
    public void dlt(
        NumberEvent event,
        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
        @Header(KafkaHeaders.OFFSET) Long offset
    ) {
        log.info("DLT!!! - topic: {}, offset: {} ", topic, offset);
        log.info("event: {}", event.getNumber());
    }
}
