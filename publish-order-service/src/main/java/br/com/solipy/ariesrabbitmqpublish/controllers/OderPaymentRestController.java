package br.com.solipy.ariesrabbitmqpublish.controllers;

import br.com.solipy.ariesrabbitmqpublish.models.requests.OrderPaymentRequest;
import br.com.solipy.ariesrabbitmqpublish.services.OrderPaymentService;
import br.com.solipy.ariesrabbitmqpublish.util.RabbitMqRouteKey;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/order-service")
@RequiredArgsConstructor
public class OderPaymentRestController {

    private final OrderPaymentService orderPaymentService;

    @PostMapping("/register.json")
    public ResponseEntity<Map<String, Boolean>> createOrderPayment(@RequestBody @Valid OrderPaymentRequest orderPaymentRequest) {
        Map<String, Boolean> map = orderPaymentService.createOrderPayment(orderPaymentRequest);
        return map.get("success") ? ResponseEntity.status(HttpStatus.CREATED).body(map) : ResponseEntity.status(HttpStatus.OK).body(map);
    }

}
