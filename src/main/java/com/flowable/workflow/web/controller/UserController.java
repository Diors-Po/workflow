package com.flowable.workflow.web.controller;

import com.flowable.workflow.service.UserService;
import com.flowable.workflow.web.vo.UserVO;
import com.flowable.workflow.data.ResponseMessage;
import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @Author: xuexb
 * @Date: 2019.3.23 17:39
 */
@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseMessage<UserVO> createUser(@RequestBody UserVO user, HttpSession session){
        return userService.create(user, session);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseMessage<UserVO> updateUser(@RequestBody UserVO user){
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseMessage deleteUser(@RequestParam String userId){
        return userService.delete(userId);
    }

    @RequestMapping(value = "/query/all", method = RequestMethod.GET)
    public ResponseMessage<List<UserVO>> findAll(){
        return userService.findAllUsers();
    }

    @RequestMapping(value = "/query/id", method = RequestMethod.GET)
    public ResponseMessage<UserVO> queryByUserId(String userId){
        return userService.findByUserId(userId);
    }

    @RequestMapping(value = "/query/name", method = RequestMethod.GET)
    public ResponseMessage<List<UserVO>> queryByUserName(String userName){
        return userService.findByUserName(userName);
    }
    @RequestMapping(value = "/query/group", method = RequestMethod.GET)
    public ResponseMessage<List<UserVO>> queryByGroup(String groupId){
        return userService.findByGroup(groupId);
    }

    @RequestMapping(value = "/query/email", method = RequestMethod.GET)
    public ResponseMessage<UserVO> queryByEmail(String email){
        return userService.findByEmail(email);
    }

    @PostMapping(value = "/login")
    public ResponseMessage<UserVO> login(@RequestBody UserVO user, HttpSession session){
        return userService.login(user, session);
    }

}
