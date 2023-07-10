package br.com.solipy.ariesrabbitmqconsumer.handler;

import br.com.solipy.ariesrabbitmqconsumer.models.RabbitMqError;
import br.com.solipy.ariesrabbitmqconsumer.repositories.RabbitMqErrorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ErrorHandler;

public class RabbitMqErrorHadler implements ErrorHandler {
    @Autowired
    private RabbitMqErrorRepository rabbitMqErrorRepository;

    @Override
    public void handleError(Throwable t) {
        String queue = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue();
        String message = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());
        String error = t.getCause().getMessage();

        RabbitMqError rabbitMqError = RabbitMqError.builder()
                .setQueue(queue)
                .setMessage(message)
                .setError(error)
                .build();
        rabbitMqErrorRepository.save(rabbitMqError);
        throw new AmqpRejectAndDontRequeueException(error);
    }
}
