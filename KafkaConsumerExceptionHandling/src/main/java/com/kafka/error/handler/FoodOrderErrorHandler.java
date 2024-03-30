package com.kafka.error.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service(value = "myFoodOrderErrorHandler")
@Slf4j
@RequiredArgsConstructor
public class FoodOrderErrorHandler implements ConsumerAwareListenerErrorHandler {
    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        log.warn("Food order error, sending to elasticsearch : {}, because : {}", message.getPayload(),
                exception.getMessage());

        /*if (exception.getCause() instanceof RuntimeException) {
            throw exception;
        }*/  // re throw error to global handler.

        return null;
    }

}
