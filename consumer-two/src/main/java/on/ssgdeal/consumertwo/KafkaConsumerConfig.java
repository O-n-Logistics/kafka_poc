package on.ssgdeal.consumertwo;

import on.ssgdeal.common.Topic;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    // 추가적인 Consumer 설정이 필요하다면 Bean 등록
    @Bean
    public NewTopic dlt() {
        return new NewTopic(Topic.ORDER.getValue() + ".dlt", 1, (short) 1);
    }
}
