package br.com.solipy.ariesrabbitmqconsumer.consumers;

import br.com.solipy.ariesrabbitmqconsumer.models.dto.OrderServiceDto;
import br.com.solipy.ariesrabbitmqconsumer.util.RabbitMqQueue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class OrderServiceConsumer {


    @RabbitListener(queues = RabbitMqQueue.ORDER_SERVICE)
    private void orderServiceConsumer(String message) throws JsonProcessingException {
        OrderServiceDto orderServiceDto = new ObjectMapper().readValue(message, OrderServiceDto.class);
        log.info("Total: {}, Token: {}", orderServiceDto.getTotal(), orderServiceDto.getPaymentToken());
    }

}
