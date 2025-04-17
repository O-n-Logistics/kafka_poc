package on.ssgdeal.producer;

import on.ssgdeal.common.Topic;
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
        return new NewTopic(Topic.ORDER.getValue(), 1, (short) 1);
    }

    @Bean
    public NewTopic orderRollbackTopic() {
        return new NewTopic(Topic.ORDER_ROLLBACK.getValue(), 1, (short) 1);
    }

    @Bean
    public NewTopic numberTopic() {
        return new NewTopic(Topic.NUMBER.getValue(), 1, (short) 1);
    }
}
