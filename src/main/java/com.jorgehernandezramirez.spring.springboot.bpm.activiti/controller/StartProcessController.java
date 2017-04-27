package com.jorgehernandezramirez.spring.springboot.bpm.activiti.controller;

import com.jorgehernandezramirez.spring.springboot.bpm.activiti.dao.UserEntity;
import com.jorgehernandezramirez.spring.springboot.bpm.activiti.dao.UserRepository;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class StartProcessController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProcessEngine processEngine;

    public StartProcessController(){
        super();
    }

    @RequestMapping(value = "/start-interview", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String startInterview(@RequestBody final UserEntity userEntity) {
        userRepository.save(userEntity);
        return runtimeService.startProcessInstanceByKey("interviewProcess",
                Collections.<String, Object>singletonMap("user", userEntity)).getProcessInstanceId();
    }

    @RequestMapping(value = "/start-test", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String startTest(@RequestBody final UserEntity userEntity) {
        userRepository.save(userEntity);
        return runtimeService.startProcessInstanceByKey("testProcess",
                Collections.<String, Object>singletonMap("user", userEntity)).getProcessInstanceId();
    }

    @RequestMapping(value = "/resume/{processId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void startTest(@RequestParam final String processId) {
        processEngine.getRuntimeService().signal(processId);
    }
}
