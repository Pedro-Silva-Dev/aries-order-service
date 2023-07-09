package br.com.solipy.ariesrabbitmqpublish.config.rabbitmq;

import br.com.solipy.ariesrabbitmqpublish.util.RabbitMqQueue;
import br.com.solipy.ariesrabbitmqpublish.util.RabbitMqRouteKey;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Bean
    public Queue orderServiceQueue(){
        return new Queue(RabbitMqQueue.ORDER_SERVICE, true, false, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding createOrderServiceBinding(){
        return BindingBuilder
                .bind(orderServiceQueue())
                .to(directExchange())
                .with(RabbitMqRouteKey.ORDER_SERVICE_KEY);
    }


}
