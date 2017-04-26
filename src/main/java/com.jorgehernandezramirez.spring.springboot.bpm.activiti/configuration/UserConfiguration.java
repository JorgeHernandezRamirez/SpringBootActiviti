package com.jorgehernandezramirez.spring.springboot.bpm.activiti.configuration;

import com.jorgehernandezramirez.spring.springboot.bpm.activiti.service.javadelegate.UserServiceJavaDelegate;
import org.activiti.engine.impl.bpmn.behavior.ReceiveTaskActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean(name = "userServiceJavaDelegateWaiting")
    public ActivityBehavior buildAcitivyBehavior(UserServiceJavaDelegate userServiceJavaDelegate) {
        return new ReceiveTaskActivityBehavior() {

            @Override
            public void execute(ActivityExecution execution) throws Exception {
                userServiceJavaDelegate.execute(execution);
            }
        };
    }
}
