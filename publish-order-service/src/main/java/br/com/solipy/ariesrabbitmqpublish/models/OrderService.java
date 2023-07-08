package br.com.solipy.ariesrabbitmqpublish.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(setterPrefix = "set")
@Entity
@Table(name = "ordem_servicos")
public class OrderService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull @NotEmpty
    @Column(name = "cliente")
    private String client;
    @NotNull @NotEmpty
    @Column(name = "servico")
    private String workOrder;
    @Column(name = "observacao")
    private String observation;
    @NotNull @Min(1)
    @Column(name = "valor")
    private BigDecimal total;
    @NotNull @NotEmpty
    @Column(name = "token_pagamento")
    private String paymentToken;

    @PrePersist
    private void persistedPaymentToken() {
        paymentToken = UUID.randomUUID().toString();
    }

}
