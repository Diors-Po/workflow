package com.flowable.workflow.web.vo.wrapper;

import com.flowable.workflow.web.vo.UserVO;
import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuexb
 * @Date: 2019.3.25 17:32
 */
@Service
public class UserWrapper {

    @Autowired
    private IdmIdentityService idmIdentityService;

    public User unwrapper(UserVO userVO){
        User user = idmIdentityService.newUser(null);
        user.setDisplayName(userVO.getUserName());
        user.setPassword(userVO.getPassword());
        user.setEmail(userVO.getEmail());
        user.setId(userVO.getId());
        return user;
    }

    public UserVO wrapper(User user){
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setEmail(user.getEmail());
        userVO.setPassword("**********");
        userVO.setUserName(user.getDisplayName());
        return userVO;
    }

    public List<UserVO> wrapper(List<User> users){
        List<UserVO> userVOs = new ArrayList<>();
        for (User user : users){
            userVOs.add(wrapper(user));
        }
        return userVOs;
    }
}

