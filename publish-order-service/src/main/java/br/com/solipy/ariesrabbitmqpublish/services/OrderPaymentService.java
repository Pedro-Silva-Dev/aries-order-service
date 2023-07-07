package br.com.solipy.ariesrabbitmqpublish.services;

import br.com.solipy.ariesrabbitmqpublish.models.OrderPayment;
import br.com.solipy.ariesrabbitmqpublish.models.requests.OrderPaymentRequest;
import br.com.solipy.ariesrabbitmqpublish.repositories.OrderPaymentRepository;
import br.com.solipy.ariesrabbitmqpublish.util.RabbitMqRouteKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderPaymentService {
    private final OrderPaymentRepository orderPaymentRepository;
    private final RabbitMQProducerService rabbitMQProducerService;

    public Map<String, Boolean> createOrderPayment(OrderPaymentRequest orderPaymentRequest) {
        OrderPayment orderPayment = OrderPayment.builder().setFullName(orderPaymentRequest.fullName()).setWorkOrder(orderPaymentRequest.workOrder()).setTotal(orderPaymentRequest.total()).build();
        OrderPayment orderPaymentPersisted = orderPaymentRepository.save(orderPayment);
        return rabbitMQProducerService.registerOrderService(RabbitMqRouteKey.ORDER_SERVICE_KEY.name(), orderPaymentPersisted.getId());
    }
}
