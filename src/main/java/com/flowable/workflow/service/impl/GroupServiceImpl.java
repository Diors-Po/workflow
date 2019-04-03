package com.flowable.workflow.service.impl;

import com.flowable.workflow.constant.Constant;
import com.flowable.workflow.data.ResponseMessage;
import com.flowable.workflow.service.GroupService;
import com.flowable.workflow.web.exception.HttpBadRequestException;
import com.flowable.workflow.web.vo.GroupVO;
import com.flowable.workflow.web.vo.wrapper.GroupWrapper;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.IdmIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Author: xuexb
 * @Date: 2019.4.1 20:34
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private IdmIdentityService idmIdentityService;

    @Autowired
    private GroupWrapper groupWrapper;

    /**
     * @param groupVO
     * @return com.flowable.workflow.data.ResponseMessage<com.flowable.workflow.web.vo.GroupVO>
     * @author xuexb
     * @date 2019.4.2 13:14
     */
    @Override
    public ResponseMessage<GroupVO> createGroup(GroupVO groupVO) {
        if (groupVO.getName() == null || groupVO.getName().isEmpty()){
            throw new HttpBadRequestException("群组名称不可为空!");
        }
        if (groupVO.getType() == null || groupVO.getType().isEmpty()){
            throw new HttpBadRequestException("群组类型不可为空");
        }
        if (idmIdentityService.createGroupQuery().groupName(groupVO.getName()).list().size()>0){
            throw new HttpBadRequestException("该群组已存在");
        }
        Group group = idmIdentityService.newGroup(UUID.randomUUID().toString());
        group.setName(groupVO.getName());
        group.setType(groupVO.getType());
        idmIdentityService.saveGroup(group);
        return new ResponseMessage<>(Constant.OK,
                Constant.REQUEST_SUCCESS,
                groupWrapper.wrap(group));
    }

    /**
     * @param groupId
     * @return com.flowable.workflow.data.ResponseMessage
     * @author xuexb
     * @date 2019.4.2 19:01
     */
    @Override
    public ResponseMessage deleteGroup(String groupId) {
        if (idmIdentityService.createGroupQuery().groupId(groupId).list().size()<1){
            throw new HttpBadRequestException("该群组不存在");
        }
        idmIdentityService.deleteGroup(groupId);
        return ResponseMessage.OK;
    }

    /**
     * @param groupVO
     * @return com.flowable.workflow.data.ResponseMessage<com.flowable.workflow.web.vo.GroupVO>
     * @author xuexb
     * @date 2019.4.3 12:06
     */
    @Override
    public ResponseMessage<GroupVO> updateGroup(GroupVO groupVO) {
        Group group = idmIdentityService.createGroupQuery().groupId(groupVO.getId()).singleResult();
        if (group == null){
            throw new HttpBadRequestException("该群组不存在");
        }
        if (groupVO.getType() == null || groupVO.getType().isEmpty()
                || groupVO.getName() == null || groupVO.getName().isEmpty()){
            throw new HttpBadRequestException("信息不可为空");
        }
        group.setType(groupVO.getType());
        group.setName(groupVO.getName());
        idmIdentityService.saveGroup(group);
        return new ResponseMessage<>(Constant.OK, Constant.REQUEST_SUCCESS, groupVO);
    }

    /**
     * @param groupId
     * @return com.flowable.workflow.data.ResponseMessage<com.flowable.workflow.web.vo.GroupVO>
     * @author xuexb
     * @date 2019.4.3 \
     */
    @Override
    public ResponseMessage<GroupVO> findById(String groupId) {
        return new ResponseMessage<>(Constant.OK,
                Constant.REQUEST_SUCCESS,
                groupWrapper.wrap(idmIdentityService
                        .createGroupQuery()
                        .groupId(groupId)
                        .singleResult()));
    }

    /**
     * @param condition
	* @param value
     * @return com.flowable.workflow.data.ResponseMessage<java.util.List<com.flowable.workflow.web.vo.GroupVO>>
     * @author xuexb
     * @date 2019.4.3 12:06
     */
    @Override
    public ResponseMessage<List<GroupVO>> findGroups(String condition, String value) {
        ResponseMessage<List<GroupVO>> groups = new ResponseMessage<>();
        groups.setCode(Constant.OK);
        groups.setMsg(Constant.REQUEST_SUCCESS);
        if (condition.equals("null")){
            groups.setData(groupWrapper.wrap(idmIdentityService.createGroupQuery().list()));
        }
        if (condition.equals("name")){
            groups.setData(groupWrapper.wrap(idmIdentityService.createGroupQuery().groupNameLike(value).list()));
        }else if (condition.equals("type")){
            groups.setData(groupWrapper.wrap(idmIdentityService.createGroupQuery().groupType(value).list()));
        }
        return groups;
    }
}
