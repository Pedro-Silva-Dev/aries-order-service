package br.com.solipy.ariesrabbitmqpublish.repositories;

import br.com.solipy.ariesrabbitmqpublish.models.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {

}
