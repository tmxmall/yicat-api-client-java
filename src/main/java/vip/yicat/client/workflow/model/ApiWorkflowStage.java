package vip.yicat.client.workflow.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApiWorkflowStage implements Serializable {
    private String baseWorkflowId;
    private int stage;
    private Date createTime;
    private Date modifyTime;
}
