package dev.ohhoonim.modulith_demo.orders;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdersIntegrationConfiguration {
    
    public static final String ORDER_DESTINATION = "orders";

    @Bean
    Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ORDER_DESTINATION).noargs();
    }

    @Bean
    Queue queue() {
        return QueueBuilder.durable(ORDER_DESTINATION).build();
    }

    @Bean
    Exchange exchange() {
        return ExchangeBuilder.topicExchange(ORDER_DESTINATION).build();
    }
}

