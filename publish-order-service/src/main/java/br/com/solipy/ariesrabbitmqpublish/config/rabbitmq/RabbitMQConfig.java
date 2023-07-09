package br.com.solipy.ariesrabbitmqpublish.config.rabbitmq;

import br.com.solipy.ariesrabbitmqpublish.util.RabbitMqQueue;
import br.com.solipy.ariesrabbitmqpublish.util.RabbitMqRouteKey;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    private final AmqpAdmin amqpAdmin;


    public Queue orderServiceQueue(){
        return new Queue(RabbitMqQueue.ORDER_SERVICE, true, false, false);
    }

    public DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    public Binding createOrderServiceBinding(){
        return BindingBuilder
                .bind(orderServiceQueue())
                .to(directExchange())
                .with(RabbitMqRouteKey.ORDER_SERVICE_KEY);
    }

    @PostConstruct
    private void addConection(){
        this.amqpAdmin.declareQueue(orderServiceQueue());
        this.amqpAdmin.declareExchange(directExchange());
        this.amqpAdmin.declareBinding(createOrderServiceBinding());
    }


}
