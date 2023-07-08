package br.com.solipy.ariesrabbitmqpublish.repositories;

import br.com.solipy.ariesrabbitmqpublish.models.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Classe responsável por testar a intereção com a tebela de ordem de serviço.")
class OrderServiceRepositoryTest {

    @Autowired
    private OrderServiceRepository orderServiceRepository;
    private OrderService orderServiceForTest;

    @BeforeEach
    @DisplayName("Cria um ordem de serviço antes de executar cada teste, para sempre existir pelo menos uma ordem de serviço para ser usado nos testes.")
    public void createOrderServiceTest() {
        OrderService orderService = OrderService.builder()
                .setClient("Pedro Silva")
                .setWorkOrder("Manutenção no Carro")
                .setObservation("Carro estava com pequenos arranhões na pintura.")
                .setTotal(new BigDecimal(500))
                .build();
        orderServiceForTest = orderServiceRepository.save(orderService);
    }

    @AfterEach
    @DisplayName("Remove todas as ordens de serviços depois de cada teste, para não afetar a execução de outros testes.")
    public void removeOrderServiceTest() {
        orderServiceRepository.deleteAll();
    }

    @Test
    @DisplayName("Obter uma lista com todas as ordem de serivços.")
    public void findAllTest() {
        List<OrderService> result = orderServiceRepository.findAll();

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Obter uma página de ordem de serivços.")
    public void findPageTest() {
        PageRequest page = PageRequest.of(0, 10);
        Page<OrderService> result = orderServiceRepository.findAll(page);

        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
        assertThat(result.getTotalPages()).isGreaterThan(0);
        assertThat(result.getTotalElements()).isGreaterThan(0);
        assertThat(result.getContent().get(0).getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Obter uma ordem de serivço.")
    public void findByIdTest() {
        Long id = orderServiceForTest.getId();
        Optional<OrderService> result = orderServiceRepository.findById(id);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isNotNull();
        assertThat(result.get().getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Atualizar uma ordem de serivço.")
    public void updateTest() {
        String name = "Marcelo Silva";
        orderServiceForTest.setClient(name);
        OrderService result = orderServiceRepository.save(orderServiceForTest);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isGreaterThan(0);
        assertThat(result.getId()).isEqualTo(orderServiceForTest.getId());
        assertThat(result.getClient()).isEqualTo(name);
    }

    @Test
    @DisplayName("Cadastrar uma ordem de serivço.")
    public void createTest() {
        String name = "Carlos Silva";
        BigDecimal total = new BigDecimal(200);
        OrderService orderService = OrderService.builder()
                .setClient(name)
                .setWorkOrder("Manuntenção de Moto")
                .setObservation("Nenuma observação.")
                .setTotal(total)
                .build();
        OrderService result = orderServiceRepository.save(orderService);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isGreaterThan(0);
        assertThat(result.getClient()).isEqualTo(name);
        assertThat(result.getTotal()).isEqualTo(total);
    }

    @Test
    @DisplayName("Remover uma ordem de serivço.")
    public void removeTest() {
        orderServiceRepository.delete(orderServiceForTest);
        List<OrderService> result = orderServiceRepository.findAll();

        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

}