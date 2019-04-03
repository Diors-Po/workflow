package com.flowable.workflow.constant;

/**
 * @Author: xuexb
 * @Date: 2019.4.1 17:32
 */
public enum GroupType {
    SERVICER("servicer"),
    MANAGER("manager");

    private String typeName;

    GroupType(String typeName){
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
