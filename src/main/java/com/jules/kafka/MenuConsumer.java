package com.jules.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jules.dtos.Menu;
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
public class MenuConsumer {

    @Component
    class Consumer {

        @SneakyThrows
        @KafkaListener(topics = "${cloudkarafka.menu-topic}")
        public void processMessage(String message,
                                   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                                   @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                                   @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
            ObjectMapper objectMapper = new ObjectMapper();
            var menu = objectMapper.readValue(message, Menu.class);
            log.info("menu {}", menu);
        }

    }
}
