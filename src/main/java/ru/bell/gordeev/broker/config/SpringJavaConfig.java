package ru.bell.gordeev.broker.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 * Created by EGordeev on 16.07.2017.
 */

@Configuration
@EnableJms
public class SpringJavaConfig {


    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory
                = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean(name = "connectionFactory")
    public ConnectionFactory connectionFactory() throws NamingException{
        final InitialContext context = new InitialContext();
        return (ConnectionFactory) context.lookup("java:/ConnectionFactory");
    }

    @Bean(name="jmsQueue")
    public Destination jmsQueue() throws NamingException {
        final InitialContext context = new InitialContext();
        return (Destination) context.lookup("java:/jms/queue/JndiQueue");
    }
}
