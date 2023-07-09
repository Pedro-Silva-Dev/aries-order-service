package br.com.solipy.ariesrabbitmqpublish.controllers;

import br.com.solipy.ariesrabbitmqpublish.models.requests.OrderServiceRequest;
import br.com.solipy.ariesrabbitmqpublish.models.responses.OrderServiceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Classe responsável por testar o controller de ordem de serviço.")
class OrderServiceRestControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Registra a ordem de serviço.")
    public void registerOrderServiceTest() {
        OrderServiceRequest orderServiceRequest = OrderServiceRequest.builder()
                .fullName("Carol Silva")
                .workOrder("Manuntenção do PC")
                .observation("Nenhuma observação.")
                .total(new BigDecimal(100))
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderServiceRequest> requestEntity = new HttpEntity<>(orderServiceRequest, headers);
        ResponseEntity<OrderServiceResponse> result = testRestTemplate.exchange("/v1/order-service/register.json", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<OrderServiceResponse>() {});

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().success()).isTrue();
    }

    @Test
    @DisplayName("Tenta registra a ordem de serviço com o cmapo inválido e retorna Bad Request.")
    public void registerOrderServiceForInvalidFieldTest() {
        OrderServiceRequest orderServiceRequest = OrderServiceRequest.builder()
                .fullName(null)
                .workOrder("Manuntenção do PC")
                .observation("Nenhuma observação.")
                .total(new BigDecimal(100))
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderServiceRequest> requestEntity = new HttpEntity<>(orderServiceRequest, headers);
        ResponseEntity<OrderServiceResponse> result = testRestTemplate.exchange("/v1/order-service/register.json", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<OrderServiceResponse>() {});

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}