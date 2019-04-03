package com.flowable.workflow.service;

import com.flowable.workflow.data.ResponseMessage;
import com.flowable.workflow.web.vo.GroupVO;

import java.util.List;

/**
 * @Author: xuexb
 * @Date: 2019.4.1 20:30
 */
public interface GroupService {
    ResponseMessage<GroupVO> createGroup(GroupVO groupVO);

    ResponseMessage deleteGroup(String groupId);

    ResponseMessage<GroupVO> updateGroup(GroupVO groupVO);

    ResponseMessage<GroupVO> findById(String groupId);

    ResponseMessage<List<GroupVO>> findGroups(String condition, String value);
}
