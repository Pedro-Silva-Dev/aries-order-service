package br.com.solipy.ariesrabbitmqpublish.services;

import br.com.solipy.ariesrabbitmqpublish.models.OrderService;
import br.com.solipy.ariesrabbitmqpublish.models.requests.OrderServiceRequest;
import br.com.solipy.ariesrabbitmqpublish.models.responses.OrderServiceResponse;
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

    public OrderServiceResponse createOrderService(OrderServiceRequest orderServiceRequest) {
        OrderService orderService = OrderService.builder()
                .setClient(orderServiceRequest.fullName())
                .setWorkOrder(orderServiceRequest.workOrder())
                .setTotal(orderServiceRequest.total())
                .setObservation(orderServiceRequest.observation())
                .build();
        try{
            OrderService orderServicePersisted = orderServiceRepository.save(orderService);
            Map<String, Boolean> map = rabbitMQOrderService.sendMessageOrderService(RabbitMqRouteKey.ORDER_SERVICE_KEY, orderServicePersisted);
            return OrderServiceResponse.builder()
                    .setSuccess(map.get("success"))
                    .build();
        }catch (Exception e) {
            return OrderServiceResponse.builder()
                    .setSuccess(false)
                    .build();
        }

    }
}
