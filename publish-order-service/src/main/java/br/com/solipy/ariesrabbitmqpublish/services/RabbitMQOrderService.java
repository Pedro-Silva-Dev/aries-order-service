package br.com.solipy.ariesrabbitmqpublish.services;

import br.com.solipy.ariesrabbitmqpublish.models.OrderService;
import br.com.solipy.ariesrabbitmqpublish.models.dto.OrderServiceDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class RabbitMQOrderService {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public Map<String, Boolean> registerOrderService(String routingKey, @Valid OrderService orderService) {
        OrderServiceDto orderServiceDto = OrderServiceDto.builder()
                .setTotal(orderService.getTotal())
                .setPaymentToken(orderService.getPaymentToken())
                .build();
        Map<String, Boolean> map = new HashMap<>();
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, orderServiceDto);
            map.put("success", true);
        }catch (Exception e) {
            map.put("success", false);
            e.printStackTrace();
        }
        return map;
    }


}
