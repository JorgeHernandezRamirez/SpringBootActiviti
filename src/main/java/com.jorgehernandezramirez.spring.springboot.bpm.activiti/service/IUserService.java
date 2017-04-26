package com.jorgehernandezramirez.spring.springboot.bpm.activiti.service;

import com.jorgehernandezramirez.spring.springboot.bpm.activiti.dao.UserEntity;

/**
 * Api de usuarios
 */
public interface IUserService {

    /**
     * Método que indica si un usuario es administrador
     * @param userId
     * @return
     */
    Boolean isInCompany(String userId);

    /**
     * Método que imprime en los logs el objeto userDto
     * @param userEntity
     */
    void printUserDto(UserEntity userEntity);
}
