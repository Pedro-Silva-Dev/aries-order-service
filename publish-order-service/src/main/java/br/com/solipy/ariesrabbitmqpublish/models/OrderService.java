package br.com.solipy.ariesrabbitmqpublish.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

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
    @Column(name = "cliente")
    private String client;
    @Column(name = "servico")
    private String workOrder;
    @Column(name = "observacao")
    private String observation;
    @Column(name = "valor")
    private BigDecimal total;
    @Column(name = "token_pagamento")
    private String paymentToken;

}
