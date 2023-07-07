package br.com.solipy.ariesrabbitmqpublish.models;

import br.com.solipy.ariesrabbitmqpublish.util.Status;
import jakarta.persistence.*;
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
@Table(name = "ordem_pagamento")
public class OrderPayment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nome")
    private String fullName;
    @Column(name = "servico")
    private String workOrder;
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "status")
    private String status;

    @PrePersist
    private void persistStatus() {
        status = Status.PENDENT.name();
    }

}
