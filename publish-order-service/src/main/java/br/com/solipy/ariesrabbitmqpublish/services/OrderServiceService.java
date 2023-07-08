package br.com.solipy.ariesrabbitmqpublish.services;

import br.com.solipy.ariesrabbitmqpublish.models.OrderService;
import br.com.solipy.ariesrabbitmqpublish.models.requests.OrderServiceRequest;
import br.com.solipy.ariesrabbitmqpublish.repositories.OrderServiceRepository;
import br.com.solipy.ariesrabbitmqpublish.util.RabbitMqRouteKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceService {
    private final OrderServiceRepository orderServiceRepository;
    private final RabbitMQOrderService rabbitMQOrderService;

    public Map<String, Boolean> createOrderService(OrderServiceRequest orderServiceRequest) {
        OrderService orderService = OrderService.builder()
                .setClient(orderServiceRequest.fullName())
                .setWorkOrder(orderServiceRequest.workOrder())
                .setTotal(orderServiceRequest.total())
                .setObservation(orderServiceRequest.observation())
                .build();
        try{
            OrderService orderServicePersisted = orderServiceRepository.save(orderService);
            return rabbitMQOrderService.registerOrderService(RabbitMqRouteKey.ORDER_SERVICE_KEY.name(), orderServicePersisted);
        }catch (Exception e) {
            Map<String, Boolean> map = new HashMap<>();
            map.put("success", false);
            return map;
        }

    }
}
