package com.yicat.client.document.model;

import lombok.Data;

import java.util.Date;

/**
 * Document workflow stage
 */
@Data
public class ApiDocumentWorkflowStage {
    private String stageId;
    private String baseWorkflowId;
    private int stage;
    /**
     * Document workflow stage
     */
    private String name;
    /**
     * Type,
     * - 0: user defined;
     * - 1: system defined -> translation;
     * - 2: system defined -> editing;
     * - 3: system defined -> proofreading;
     * - 4: system defined -> post-editing;
     */
    private byte type;
    private String groupId;
    private String documentId;
    /**
     * The progress of current stage
     */
    private int progress;
    /**
     * The task number of current progress
     */
    private int inExecutionTask;
    private Date createTime;
    private Date modifyTime;

}
