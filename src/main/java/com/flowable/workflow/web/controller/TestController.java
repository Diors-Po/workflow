package com.flowable.workflow.web.controller;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.api.User;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xuexb
 * @Date: 2019.3.22 15:28
 */
@RequestMapping(value = "/test")
@RestController
public class TestController {

    @Autowired
    private IdmIdentityService idmIdentityService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public User createUser(){
        User user = idmIdentityService.newUser("1");
        user.setEmail("xuexiaobo.dlmu@gmail.com");
        user.setPassword("123456");
        idmIdentityService.saveUser(user);
        User res = idmIdentityService.createUserQuery().userId("1").list().get(0);
        return res;
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    public User udpateUser(){
        User user = idmIdentityService.createUserQuery().userId("1").singleResult();
        user.setEmail("171256175@qq.com");
        idmIdentityService.saveUser(user);
        return user;
    }

    public List<Task> getTaskListByUserId(String userId){
        return null;
    }

    public List<Task> getTaskListByGroup(String groupId){
        return null;
    }



}
