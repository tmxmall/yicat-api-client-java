package vip.yicat.client.project.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApiProjectWorkflowStage implements Serializable {
    private String stageId;
    private String baseWorkflowId;
    private int stage;
    private String name;
    private byte type;
    private String projectId;
    private int progress;
    private Date createTime;
    private Date modifyTime;
}
