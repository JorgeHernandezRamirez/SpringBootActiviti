package com.jorgehernandezramirez.spring.springboot.bpm.activiti.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.subethamail.smtp.MessageListener;
import org.subethamail.wiser.Wiser;

@Configuration
public class WiserConfiguration {

    @Value("${spring.activiti.mailServerPort}")
    private Integer port;

    public WiserConfiguration(){
        //Para Spring
    }

    @Bean
    public MessageListener buildWiser(){
        final Wiser wiser = new Wiser();
        wiser.setPort(port);
        return wiser;
    }
}
