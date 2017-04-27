package com.jorgehernandezramirez.spring.springboot.bpm.activiti;

import com.jorgehernandezramirez.spring.springboot.bpm.activiti.dao.UserEntity;
import com.jorgehernandezramirez.spring.springboot.bpm.activiti.dao.UserRepository;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.subethamail.wiser.Wiser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivitiTestTest {

    private static final String USER = "user";

    private static final String INTERVIEW_PROCESS_ID = "testProcess";

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Wiser wiser;

    @Autowired
    private ProcessEngine processEngine;

    @Before
    public void initialization() {
        wiser.start();
    }

    @Test
    public void shouldInitTestBpmProcess(){
        final UserEntity userEntity = prepareAndBuildUser("ADMIN", "ADMIN", "+3412345678", "admin@admin.com");
        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(INTERVIEW_PROCESS_ID, new HashMap<String, Object>(){{
            put(USER, userEntity);
        }});
        processEngine.getRuntimeService().signal(processInstance.getId());
        assertEquals(2, historyService.createHistoricProcessInstanceQuery().finished().count());
    }

    private UserEntity prepareAndBuildUser(final String userId, final String name, final String phone, final String email){
        final UserEntity userEntity = new UserEntity(userId, name, phone, email);
        userRepository.save(userEntity);
        return userEntity;
    }
}
