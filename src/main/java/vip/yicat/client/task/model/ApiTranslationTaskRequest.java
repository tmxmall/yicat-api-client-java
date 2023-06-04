package vip.yicat.client.task.model;

import lombok.Data;

import java.util.Date;

@Data
public class ApiTranslationTaskRequest {
    private String documentId;
    private Integer stage;
    private Byte stageType;
    private String stageName;
    private String taskUserId;
    private String taskUserName;
    private Long unitPrice;
    private String notes;
    private Double segmentRangeStart;
    private Double segmentRangeEnd;
    private Date deadline;
}
