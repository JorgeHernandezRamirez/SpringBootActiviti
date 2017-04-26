package com.jorgehernandezramirez.spring.springboot.bpm.activiti.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}