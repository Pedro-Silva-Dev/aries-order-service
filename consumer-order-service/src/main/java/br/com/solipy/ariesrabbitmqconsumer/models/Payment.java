package br.com.solipy.ariesrabbitmqconsumer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
@Data
@Entity
@Table(name = "pagamentos")
public class Payment {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "valor")
        private BigDecimal total;
        @Column(name = "status")
        private String status;
        @Column(name = "token_pagamento")
        private String paymentToken;
}
