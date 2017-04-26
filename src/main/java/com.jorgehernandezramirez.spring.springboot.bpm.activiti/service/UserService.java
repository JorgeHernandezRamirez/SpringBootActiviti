package com.jorgehernandezramirez.spring.springboot.bpm.activiti.service;

import com.jorgehernandezramirez.spring.springboot.bpm.activiti.dao.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements IUserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private static final List<String> COMPANYUSERS = Arrays.asList("ADMIN", "JORGE");

    public UserService(){
        //Para
    }

    @Override
    public Boolean isInCompany(final String userId) {
        return COMPANYUSERS.contains(userId);
    }

    @Override
    public void printUserDto(final UserEntity userEntity) {
        LOGGER.info("{}", userEntity);
    }
}
