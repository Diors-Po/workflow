package com.flowable.workflow.service.impl;

import com.flowable.workflow.constant.Constant;
import com.flowable.workflow.web.vo.UserVO;
import com.flowable.workflow.web.vo.wrapper.UserWrapper;
import com.flowable.workflow.web.exception.HttpBadRequestException;
import com.flowable.workflow.data.ResponseMessage;
import com.flowable.workflow.service.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @Author: xuexb
 * @Date: 2019.3.25 17:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IdmIdentityService idmIdentityService;

    @Autowired
    private UserWrapper userWrapper;

    @Override
    public ResponseMessage<UserVO> login(UserVO userVO, HttpSession session) {
        if (userVO.getEmail()==null || userVO.getEmail().isEmpty() || userVO.getPassword()==null || userVO.getPassword().isEmpty())
            throw new HttpBadRequestException("邮箱和密码不可为空");
        User user = idmIdentityService.createUserQuery().userEmail(userVO.getEmail()).singleResult();
        if (user == null)
            throw new HttpBadRequestException(Constant.USER_NOT_EXISTS);
        if (!idmIdentityService.checkPassword(user.getId(), userVO.getPassword()))
            throw new HttpBadRequestException(Constant.USER_LOGIN_ERROR);
        session.setAttribute("currUser", userWrapper.wrapper(user));
        userVO = userWrapper.wrapper(user);
        return new ResponseMessage<>(Constant.OK, Constant.REQUEST_SUCCESS, userVO);
    }

    @Override
    public ResponseMessage<UserVO> create(UserVO userVO, HttpSession session) {
        if (userVO.getEmail()==null || userVO.getEmail().isEmpty() || userVO.getPassword()==null || userVO.getPassword().isEmpty())
            throw new HttpBadRequestException("邮箱和密码不可为空");
        if (!EmailValidator.getInstance().isValid(userVO.getEmail()))
            throw new HttpBadRequestException("邮箱非法");
        if (userVO.getPassword().length()>16 || userVO.getPassword().length()<8)
            throw new HttpBadRequestException("密码长度非法，请输入8~16位密码");
        if (idmIdentityService.createUserQuery()
                .userEmail(userVO.getEmail())
                .list().size()>0)
            throw new HttpBadRequestException(Constant.USER_HAS_PRESENCE);
        User user = idmIdentityService.newUser(UUID.randomUUID().toString());
        user.setEmail(userVO.getEmail());
        user.setPassword(userVO.getPassword());
        user.setDisplayName(userVO.getUserName());
        idmIdentityService.saveUser(user);
        return new ResponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,userWrapper.wrapper(user));
    }

    @Override
    public ResponseMessage<UserVO> delete(String userId) {
        if (idmIdentityService.createUserQuery().list().size()<1)
            throw new HttpBadRequestException(Constant.USER_NOT_EXISTS);
        idmIdentityService.deleteUser(userId);
        return ResponseMessage.OK;
    }

    @Override
    public ResponseMessage<UserVO> update(UserVO userVO) {
        return null;
    }

    @Override
    public ResponseMessage<List<UserVO>> findAllUsers() {
        return new ResponseMessage<>(Constant.OK, Constant.REQUEST_SUCCESS,
                userWrapper.wrapper(idmIdentityService.createUserQuery().list()));
    }

    @Override
    public ResponseMessage<UserVO> findByUserId(String userId) {
        User user = idmIdentityService.createUserQuery().userId(userId).singleResult();
        if (user==null)
            throw new HttpBadRequestException(Constant.USER_NOT_EXISTS);
        return new ResponseMessage<>(Constant.OK, Constant.REQUEST_SUCCESS, userWrapper.wrapper(user));
    }

    @Override
    public ResponseMessage<UserVO> findByEmail(String email) {
        User user = idmIdentityService.createUserQuery().userEmail(email).singleResult();
        if (user==null)
            throw new HttpBadRequestException(Constant.USER_NOT_EXISTS);
        return new ResponseMessage<>(Constant.OK, Constant.REQUEST_SUCCESS, userWrapper.wrapper(user));
    }

    @Override
    public ResponseMessage<List<UserVO>> findByGroup(String groupId) {
        if (idmIdentityService.createGroupQuery().groupId(groupId).list().size()<1)
            throw new HttpBadRequestException("用户组不存在");
        List<User> users = idmIdentityService.createUserQuery().memberOfGroup(groupId).list();
        return new ResponseMessage<>(Constant.OK, Constant.REQUEST_SUCCESS, userWrapper.wrapper(users));
    }

    @Override
    public ResponseMessage<List<UserVO>> findByUserName(String userName) {
        if (userName==null || userName.isEmpty())
            throw new HttpBadRequestException("用户名输入不可为空");
        List<User> users = idmIdentityService.createUserQuery().userDisplayNameLike(userName).list();
        return null;
    }
}
