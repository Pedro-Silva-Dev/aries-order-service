package br.com.solipy.ariesrabbitmqconsumer.models;

import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder(setterPrefix = "set")
@Entity
@Table(name = "rabbitmq_erros")
public record RabbitMqError (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        @Column(name = "fila")
        String queue,
        @Column(name = "mensagem")
        String message,
        @Column(name = "erro")
        String error,
        @CreatedDate
        @Column(name = "dhc")
        LocalDateTime dhc
) {
}
