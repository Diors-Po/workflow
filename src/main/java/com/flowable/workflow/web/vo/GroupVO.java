package com.flowable.workflow.web.vo;

import lombok.Data;
import org.flowable.idm.api.Privilege;

import java.util.List;

/**
 * @Author: xuexb
 * @Date: 2019.3.25 21:40
 */
@Data
public class GroupVO {
    private String id;
    private String name;
    private String type;

    private List<UserVO> members;
    private List<Privilege> privileges;
}
