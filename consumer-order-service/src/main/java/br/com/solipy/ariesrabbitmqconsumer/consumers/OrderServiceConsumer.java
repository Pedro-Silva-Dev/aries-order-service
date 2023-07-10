package br.com.solipy.ariesrabbitmqconsumer.consumers;

import br.com.solipy.ariesrabbitmqconsumer.models.dto.OrderServiceDto;
import br.com.solipy.ariesrabbitmqconsumer.services.PaymentService;
import br.com.solipy.ariesrabbitmqconsumer.util.RabbitMqQueue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class OrderServiceConsumer {

    @Autowired
    private PaymentService paymentService;

    @RabbitListener(queues = RabbitMqQueue.ORDER_SERVICE, containerFactory = "rabbitListenerContainerFactory")
    private void orderServiceConsumer(String message) throws JsonProcessingException, InterruptedException {
        OrderServiceDto orderServiceDto = new ObjectMapper().readValue(message, OrderServiceDto.class);
        String token = paymentService.registerPayment(orderServiceDto.getPaymentToken(), orderServiceDto.getTotal());
        log.info("Processando o pagamento...");
        Thread.sleep(5000);
        paymentService.finishPayment(token);
    }

}
