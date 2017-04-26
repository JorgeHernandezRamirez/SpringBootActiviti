package com.jorgehernandezramirez.spring.springboot.bpm.activiti;

import com.jorgehernandezramirez.spring.springboot.bpm.activiti.dao.UserEntity;
import com.jorgehernandezramirez.spring.springboot.bpm.activiti.dao.UserRepository;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.subethamail.smtp.MessageListener;
import org.subethamail.wiser.Wiser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivitiInterviewTest {

    private static final String INTERVIEW_PROCESS_ID = "interviewProcess";

    private static final String USER = "user";

    private static final String TASK_NAME_TELEPHONE_INTERVIEW = "Telephone interview";

    private static final String TASK_NAME_FINALTIAL_NEGOTIATION = "Financial negotiation";

    private static final String TASK_NAME_TECHNICAL_INTERVIEW = "Tech interview";

    private static final String TECH_OK = "techOK";

    private static final String FINALTIAL_OK = "finantialOK";

    private static final String TELEPHONE_INTERVIEW_OUTCOME = "telephoneInterviewOutcome";

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Wiser wiser;

    @Before
    public void initialization() {
        wiser.start();
    }

    @Test
    public void shouldntPassInterviewBeauceUserExistsYet(){
        final UserEntity userEntity = prepareAndBuildUser("ADMIN", "ADMIN", "+3412345678", "admin@admin.com");
        runtimeService.startProcessInstanceByKey(INTERVIEW_PROCESS_ID, new HashMap<String, Object>(){{
            put(USER, userEntity);
        }});
        assertEquals(1, historyService.createHistoricProcessInstanceQuery().finished().count());
    }

    @Test
    public void shouldPassInterview(){
        final UserEntity userEntity = prepareAndBuildUser("userId", "Jorge", "+3412345678", "admin@jorgehernandezramirez.com");
        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(INTERVIEW_PROCESS_ID, new HashMap<String, Object>(){{
            put(USER, userEntity);
        }});
        final Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        assertEquals(TASK_NAME_TELEPHONE_INTERVIEW, task.getName());

        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put(TELEPHONE_INTERVIEW_OUTCOME, true);
        taskService.complete(task.getId(), taskVariables);

        final List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();
        assertEquals(2, tasks.size());
        assertEquals(TASK_NAME_FINALTIAL_NEGOTIATION, tasks.get(0).getName());
        assertEquals(TASK_NAME_TECHNICAL_INTERVIEW, tasks.get(1).getName());

        taskVariables = new HashMap<String, Object>();
        taskVariables.put(TECH_OK, true);
        taskService.complete(tasks.get(0).getId(), taskVariables);

        taskVariables = new HashMap<String, Object>();
        taskVariables.put(FINALTIAL_OK, true);
        taskService.complete(tasks.get(1).getId(), taskVariables);

        assertEquals(1, wiser.getMessages().size());
        assertEquals(1, historyService.createHistoricProcessInstanceQuery().finished().count());
    }

    private UserEntity prepareAndBuildUser(final String userId, final String name, final String phone, final String email){
        final UserEntity userEntity = new UserEntity(userId, name, phone, email);
        userRepository.save(userEntity);
        return userEntity;
    }
}
