package br.com.solipy.ariesrabbitmqconsumer.repositories;

import br.com.solipy.ariesrabbitmqconsumer.models.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE pagamentos SET status =:status where token_pagamento =:token", nativeQuery = true)
    Integer updateStatusByToken(@Param("token") String token, @Param("status") String status);

    Optional<Payment> findByPaymentToken(String token);
}
