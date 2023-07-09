package br.com.solipy.ariesrabbitmqpublish.services;

import br.com.solipy.ariesrabbitmqpublish.models.requests.OrderServiceRequest;
import br.com.solipy.ariesrabbitmqpublish.models.responses.OrderServiceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Classe responsável por testar os serviços de ordem de serviço.")
class OrderServiceServiceTest {

    @Autowired
    private OrderServiceService orderServiceService;

    @Test
    @DisplayName("Criar uma ordem de serviço aparti de uma orderRequest e enviar para o rabbitMQ.")
    public void createOrderServiceTest() {
        OrderServiceRequest orderServiceRequest = OrderServiceRequest.builder()
                .fullName("Paulo Silva")
                .workOrder("Manutenação do oculos")
                .observation("Nenhuma observação")
                .total(new BigDecimal(120))
                .build();
        OrderServiceResponse result = orderServiceService.createOrderService(orderServiceRequest);

        assertThat(result).isNotNull();
        assertThat(result.success()).isTrue();
    }

    @Test
    @DisplayName("Criar uma ordem de serviço aparti de uma orderRequest e enviar para o rabbitMQ.")
    public void createOrderServiceFailForInvalidFieldTest() {
        OrderServiceRequest orderServiceRequest = OrderServiceRequest.builder()
                .fullName("Paulo Silva")
                .workOrder("Manutenação do oculos")
                .observation("Nenhuma observação")
                .total(null)
                .build();
        OrderServiceResponse result = orderServiceService.createOrderService(orderServiceRequest);

        assertThat(result).isNotNull();
        assertThat(result.success()).isFalse();
    }

}