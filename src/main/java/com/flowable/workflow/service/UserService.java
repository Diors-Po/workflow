package com.flowable.workflow.service;

import com.flowable.workflow.web.vo.UserVO;
import com.flowable.workflow.data.ResponseMessage;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: xuexb
 * @Date: 2019.3.25 16:12
 */
public interface UserService {

    ResponseMessage<UserVO> login(UserVO userVO, HttpSession session);

    ResponseMessage<UserVO> create(UserVO userVO, HttpSession session);

    ResponseMessage<UserVO> delete(String userId);

    ResponseMessage<UserVO> update(UserVO userVO);

    ResponseMessage<List<UserVO>> findAllUsers();

    ResponseMessage<UserVO> findByUserId(String userId);

    ResponseMessage<UserVO> findByEmail(String email);

    ResponseMessage<List<UserVO>> findByGroup(String groupId);

    ResponseMessage<List<UserVO>> findByUserName(String userName);

}
