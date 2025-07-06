package com.school.school.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
	@Bean
  public Queue schoolRatingQueue() {
	 return new Queue("schoolRatingQueue");
  }
	@Bean
  public MessageConverter jsonMessageConverter() {
	  return new Jackson2JsonMessageConverter();
  }
	@Bean
  public RabbitTemplate rabbitTemplate(final ConnectionFactory factory) {
	  RabbitTemplate template = new RabbitTemplate(factory);
	  template.setMessageConverter(jsonMessageConverter());
	  return template;
  }
}
