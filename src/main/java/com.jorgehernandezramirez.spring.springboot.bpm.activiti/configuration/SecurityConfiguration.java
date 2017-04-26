package com.jorgehernandezramirez.spring.springboot.bpm.activiti.configuration;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private void userConfiguration(final IdentityService identityService) {
        final Group group = identityService.newGroup("user");
        group.setName("users");
        group.setType("security-role");
        identityService.saveGroup(group);
        final User admin = identityService.newUser("admin");
        admin.setPassword("admin");
        identityService.saveUser(admin);
    }
}
