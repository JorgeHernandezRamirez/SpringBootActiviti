package com.jorgehernandezramirez.spring.springboot.bpm.activiti.service.javadelegate;

import com.jorgehernandezramirez.spring.springboot.bpm.activiti.service.UserService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceJavaDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public UserServiceJavaDelegate(){
        //Para Spring
    }

    @Override
    public void execute(final DelegateExecution execution) throws Exception {
        LOGGER.info("Process -> {}, User -> {}", execution.getProcessInstanceId(), execution.getVariable("user"));
    }
}
