package br.com.solipy.ariesrabbitmqpublish.services;

import br.com.solipy.ariesrabbitmqpublish.models.OrderService;
import br.com.solipy.ariesrabbitmqpublish.models.dto.OrderServiceDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class RabbitMQOrderService {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public Map<String, Boolean> sendMessageOrderService(String routingKey, OrderService orderService) {
        Map<String, Boolean> map = new HashMap<>();
        if(orderService.getTotal() == null || orderService.getPaymentToken() == null) {
            map.put("success", false);
            return map;
        }

        OrderServiceDto orderServiceDto = OrderServiceDto.builder()
                .setTotal(orderService.getTotal())
                .setPaymentToken(orderService.getPaymentToken())
                .build();

        try {
            String message = this.objectMapper.writeValueAsString(orderServiceDto);
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            map.put("success", true);
        }catch (Exception e) {
            map.put("success", false);
            e.printStackTrace();
        }
        return map;
    }



}
