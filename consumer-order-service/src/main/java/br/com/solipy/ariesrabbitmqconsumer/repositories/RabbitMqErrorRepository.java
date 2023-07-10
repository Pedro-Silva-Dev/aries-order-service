package br.com.solipy.ariesrabbitmqconsumer.repositories;

import br.com.solipy.ariesrabbitmqconsumer.models.RabbitMqError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RabbitMqErrorRepository extends JpaRepository<RabbitMqError, Long> {

}
