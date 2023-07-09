package br.com.solipy.ariesrabbitmqpublish.controllers;

import br.com.solipy.ariesrabbitmqpublish.models.requests.OrderServiceRequest;
import br.com.solipy.ariesrabbitmqpublish.models.responses.OrderServiceResponse;
import br.com.solipy.ariesrabbitmqpublish.services.OrderServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/v1/order-service")
@RequiredArgsConstructor
public class OrderServiceRestController {

    private final OrderServiceService orderServiceService;
    @PostMapping("/register.json")
    public ResponseEntity<OrderServiceResponse> createOrderPayment(@RequestBody @Valid OrderServiceRequest orderServiceRequest) {
        OrderServiceResponse result = orderServiceService.createOrderService(orderServiceRequest);
        return result.success() ? ResponseEntity.status(HttpStatus.CREATED).body(result) : ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
