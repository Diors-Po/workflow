package com.flowable.workflow.web.vo;

import lombok.Data;
import netscape.security.Privilege;
import org.flowable.idm.api.Group;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;

import java.util.List;

/**
 * @Author: xuexb
 * @Date: 2019.3.23 0:18
 */
@Data
public class UserVO{
    private String id;
    private String userName;
    private String password;
    private String email;

    private List<Group> groups;
    private List<Privilege> privileges;
}
