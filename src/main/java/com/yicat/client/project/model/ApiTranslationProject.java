package com.yicat.client.project.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ApiTranslationProject implements Serializable {
    private String projectId;
    private String projectName;
    private String srcLan;
    private List<String> tgtLanList;
    private String notes;
    private Date deadline;
    private Integer status;
    private Integer progress;
    private List<ApiProjectWorkflowStage> workflowStageList;
    private Integer mtWords;
    private Integer htWords;
    private Integer tm100MatchedWords;
    private Integer innerRepeatWords;
    private Integer outerRepeatWords;
    private Date createTime;
    private Date modifyTime;
}
