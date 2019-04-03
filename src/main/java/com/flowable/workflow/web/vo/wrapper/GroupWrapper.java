package com.flowable.workflow.web.vo.wrapper;

import com.flowable.workflow.web.vo.GroupVO;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.IdmIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuexb
 * @Date: 2019.4.1 17:12
 */
@Service
public class GroupWrapper {
    @Autowired
    private IdmIdentityService idmIdentityService;

    public Group unwrap(GroupVO groupVO){
        Group group = idmIdentityService.newGroup(groupVO.getName());
        group.setId(groupVO.getId());
        group.setType(groupVO.getType());
        return group;
    }

    public GroupVO wrap(Group group){
        GroupVO groupVO = new GroupVO();
        groupVO.setId(group.getId());
        groupVO.setName(group.getName());
        groupVO.setType(group.getType());
        return groupVO;
    }

    public List<GroupVO> wrap(List<Group> groups){
        List<GroupVO> groupVOS = new ArrayList<>();
        for (Group group : groups){
            groupVOS.add(wrap(group));
        }
        return groupVOS;
    }

}
