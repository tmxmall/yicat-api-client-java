package com.yicat.client.workflow.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiWorkflowBase implements Serializable {
    private String baseWorkflowId;
    private String name;
    private byte type;
    private String groupId;
    private String createUserId;
    private String createUserName;
    private String createTime;
    private String modifyTime;
}
