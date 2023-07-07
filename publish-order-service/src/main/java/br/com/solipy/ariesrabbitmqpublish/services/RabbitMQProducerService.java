package br.com.solipy.ariesrabbitmqpublish.services;

import br.com.solipy.ariesrabbitmqpublish.models.requests.OrderPaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class RabbitMQProducerService {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public Map<String, Boolean> registerOrderService(String routingKey, Long orderPaymentId) {
        Map<String, Boolean> map = new HashMap<>();
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, orderPaymentId);
            map.put("success", true);
        }catch (Exception e) {
            map.put("success", false);
            e.printStackTrace();
        }
        return map;
    }


}
