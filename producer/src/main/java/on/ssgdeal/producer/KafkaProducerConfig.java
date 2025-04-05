package on.ssgdeal.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaProducerConfig {
    // Producer에 대한 추가 Bean 설정이 필요한 경우

    @Bean
    public NewTopic orderTopic() {
        return new NewTopic("order-events", 3, (short) 1);
    }
}
