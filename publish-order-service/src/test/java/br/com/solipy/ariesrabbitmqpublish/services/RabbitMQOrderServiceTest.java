package br.com.solipy.ariesrabbitmqpublish.services;

import br.com.solipy.ariesrabbitmqpublish.models.OrderService;
import br.com.solipy.ariesrabbitmqpublish.util.RabbitMqRouteKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Classe responsável por testar os serviços de mensagens para o RabbitMQ.")
class RabbitMQOrderServiceTest {

    @Autowired
    private RabbitMQOrderService rabbitMQOrderService;

    @Test
    @DisplayName("Registra a mensagem para a ordem de serviço.")
    public void registerOrderServiceTest() {
        OrderService orderService = OrderService.builder()
                .setId(1L)
                .setClient("Lucia Silva")
                .setWorkOrder("Manutenção no Ar-Condicionado")
                .setObservation("Nenhuma observação.")
                .setTotal(new BigDecimal(200))
                .setPaymentToken(UUID.randomUUID().toString())
                .build();
        Map<String, Boolean> result = rabbitMQOrderService.sendMessageOrderService(RabbitMqRouteKey.ORDER_SERVICE_KEY.name(), orderService);

        assertThat(result).isNotNull();
        assertThat(result.get("success")).isTrue();
    }

    @Test
    @DisplayName("Tenta registrar uma mensagem para a ordem de serviço, com o total inválido.")
    public void registerOrderServiceFailForTotalInvalidTest() {
        OrderService orderService = OrderService.builder()
                .setId(1L)
                .setClient("Lucia Silva")
                .setWorkOrder("Manutenção no Ar-Condicionado")
                .setObservation("Nenhuma observação.")
                .setTotal(null)
                .setPaymentToken(UUID.randomUUID().toString())
                .build();
        Map<String, Boolean> result = rabbitMQOrderService.sendMessageOrderService(RabbitMqRouteKey.ORDER_SERVICE_KEY.name(), orderService);

        assertThat(result).isNotNull();
        assertThat(result.get("success")).isFalse();
    }

    @Test
    @DisplayName("Tenta registrar uma mensagem para a ordem de serviço, com o token de pagamento inválido.")
    public void registerOrderServiceFailForPyamentTokenInvalidTest() {
        OrderService orderService = OrderService.builder()
                .setId(1L)
                .setClient("Lucia Silva")
                .setWorkOrder("Manutenção no Ar-Condicionado")
                .setObservation("Nenhuma observação.")
                .setTotal(new BigDecimal(100))
                .setPaymentToken(null)
                .build();
        Map<String, Boolean> result = rabbitMQOrderService.sendMessageOrderService(RabbitMqRouteKey.ORDER_SERVICE_KEY.name(), orderService);

        assertThat(result).isNotNull();
        assertThat(result.get("success")).isFalse();
    }

}