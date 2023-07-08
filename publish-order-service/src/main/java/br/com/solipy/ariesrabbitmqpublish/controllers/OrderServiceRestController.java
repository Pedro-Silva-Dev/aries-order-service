package br.com.solipy.ariesrabbitmqpublish.controllers;

import br.com.solipy.ariesrabbitmqpublish.models.requests.OrderServiceRequest;
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

@RestController
@RequestMapping("/v1/order-service")
@RequiredArgsConstructor
public class OrderServiceRestController {

    private final OrderServiceService orderServiceService;
    @PostMapping("/register.json")
    public ResponseEntity<Map<String, Boolean>> createOrderPayment(@RequestBody @Valid OrderServiceRequest orderServiceRequest) {
        Map<String, Boolean> map = orderServiceService.createOrderService(orderServiceRequest);
        return map.get("success") ? ResponseEntity.status(HttpStatus.CREATED).body(map) : ResponseEntity.status(HttpStatus.OK).body(map);
    }

}
