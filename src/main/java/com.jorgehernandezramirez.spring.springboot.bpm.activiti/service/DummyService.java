package com.jorgehernandezramirez.spring.springboot.bpm.activiti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DummyService implements IDummyService{

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyService.class);

    private static final String DUMMY = "Dummy";

    public DummyService(){
        //Para Spring
    }

    @Override
    public String getDummyString() {
        LOGGER.info("{}", DUMMY);
        return DUMMY;
    }

    @Override
    public String getDummyString(String dummyString) {
        LOGGER.info("{}", DUMMY + dummyString);
        return DUMMY + dummyString;
    }
}
