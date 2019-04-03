package com.flowable.workflow.web.controller;

import com.flowable.workflow.data.ResponseMessage;
import com.flowable.workflow.service.GroupService;
import com.flowable.workflow.web.vo.GroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: xuexb
 * @Date: 2019.3.25 21:33
 */
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;


    /**
     * @param groupVO
     * @return com.flowable.workflow.data.ResponseMessage<com.flowable.workflow.web.vo.GroupVO>
     * @author xuexb
     * @date 2019.4.1 20:36
     */
    @PostMapping("/group")
    public ResponseMessage<GroupVO> create(@RequestBody GroupVO groupVO){
        return groupService.createGroup(groupVO);
    }

    /**
     * @param groupId
     * @return com.flowable.workflow.data.ResponseMessage
     * @author xuexb
     * @date 2019.4.1 20:37
     */
    @DeleteMapping("/group/{groupId}")
    public ResponseMessage delete(@PathVariable("groupId") String groupId){
        return groupService.deleteGroup(groupId);
    }


    /**
     * @param groupVO
     * @return com.flowable.workflow.data.ResponseMessage<com.flowable.workflow.web.vo.GroupVO>
     * @author xuexb
     * @date 2019.4.1 20:37
     */
    @PutMapping("/group")
    public ResponseMessage<GroupVO> edit(@RequestBody GroupVO groupVO){
        return groupService.updateGroup(groupVO);
    }


    /**
     * @param groupId
     * @return com.flowable.workflow.data.ResponseMessage<com.flowable.workflow.web.vo.GroupVO>
     * @author xuexb
     * @date 2019.4.1 20:37
     */
    @GetMapping("/group/{groupId}")
    public ResponseMessage<GroupVO> findByGroupId(@PathVariable("groupId") String groupId){
        return groupService.findById(groupId);
    }

    /**
     * @param condition
	* @param value
     * @return com.flowable.workflow.data.ResponseMessage<java.util.List<com.flowable.workflow.web.vo.GroupVO>>
     * @author xuexb
     * @date 2019.4.1 20:37
     */
    @GetMapping("/group")
    public ResponseMessage<List<GroupVO>> findGroups(@RequestParam(value = "condition", defaultValue = "null") String condition,
                                                     @RequestParam(value = "value", required = false) String value){
        return groupService.findGroups(condition, value);
    }





}
