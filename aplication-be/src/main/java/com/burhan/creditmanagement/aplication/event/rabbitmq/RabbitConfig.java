package com.burhan.creditmanagement.aplication.event.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {
    // RabbitMQ kuyruklarının tanımlanması

    // credit-application kuyruğu
    @Bean
    public Queue creditApplicationQueue() {
        return new Queue("creditapplication", false);
    }

    // topic exchange tanımlaması
    @Bean
    public Exchange eventExchange() {
        return new TopicExchange("eventexchange", false, false);
    }

    // credit-application kuyruğu ile exchange arasında binding (bağlantı)
    @Bean
    public Binding bindingCreditApplication(Queue creditApplicationQueue, Exchange eventExchange) {
        return BindingBuilder.bind(creditApplicationQueue).to(eventExchange).with("creditapplication").noargs();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();  // JSON formatında mesaj dönüşümü
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());  // Mesaj dönüşümünü burada ayarlıyoruz
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter());  // Mesaj dönüşümünü listener için de ayarlıyoruz
        return factory;
    }


}
