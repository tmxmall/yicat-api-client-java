package com.yicat.client.project.model;

import lombok.Data;

import java.util.Date;

@Data
public class PatchProjectBasicInfoRequest {
    private String projectName;
    private Integer fieldId;
    private String notes;
    private Date deadline;
}
