package com.jorgehernandezramirez.spring.springboot.bpm.activiti.controller;

import com.jorgehernandezramirez.spring.springboot.bpm.activiti.dao.UserEntity;
import com.jorgehernandezramirez.spring.springboot.bpm.activiti.dao.UserRepository;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class StartProcessController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private UserRepository userRepository;

    public StartProcessController(){
        super();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/start-interview", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void startHireProcess(@RequestBody final UserEntity userEntity) {
        userRepository.save(userEntity);
        runtimeService.startProcessInstanceByKey("interviewProcess",
                Collections.<String, Object>singletonMap("user", userEntity));
    }
}
