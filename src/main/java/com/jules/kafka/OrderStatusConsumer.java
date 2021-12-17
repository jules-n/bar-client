package com.jules.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jules.dtos.Menu;
import com.jules.dtos.OrderStatusDTO;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class OrderStatusConsumer {

    @Component
    class Consumer {

        @SneakyThrows
        @KafkaListener(topics = "${cloudkarafka.status-topic}")
        public void processMessage(String message,
                                   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                                   @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                                   @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
            ObjectMapper objectMapper = new ObjectMapper();
            var dto = objectMapper.readValue(message, OrderStatusDTO.class);
            log.info("order status {}", dto);
        }

    }
}
