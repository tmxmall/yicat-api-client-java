package com.yicat.client.workflow.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AddWorkflowTemplateRequest implements Serializable {
    private String name;
    private String note;
    private byte type;
    private List<ApiWorkflowStage> apiWorkflowStageList;
}
