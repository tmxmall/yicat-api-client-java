package com.yicat.client.project.model;

import lombok.Data;

@Data
public class ApiTranslationProjectWithSettingsResponseObject {
    private String status;
    private String errCode;
    private String errMsg;
    private ApiTranslationProjectWithSettings result;
}
