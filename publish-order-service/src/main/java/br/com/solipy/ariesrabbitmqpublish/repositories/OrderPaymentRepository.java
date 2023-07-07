package br.com.solipy.ariesrabbitmqpublish.repositories;

import br.com.solipy.ariesrabbitmqpublish.models.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Long> {

}
