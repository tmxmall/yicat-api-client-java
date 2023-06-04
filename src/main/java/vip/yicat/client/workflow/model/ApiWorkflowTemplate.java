package vip.yicat.client.workflow.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ApiWorkflowTemplate implements Serializable {
    private String templateId;
    private String name;
    private String note;
    private byte type;
    private String groupId;
    private String createUserId;
    private String createUserName;
    private List<ApiWorkflowStage> apiWorkflowStageList;
    private Date createTime;
    private Date modifyTime;
}
