package com.yicat.client.project.model;

import com.yicat.client.translationmemory.model.ApiMatchTMEntryInfo;
import lombok.Data;

@Data
public class ApiTranslationProjectResponseObject {
    private String status;
    private String errCode;
    private String errMsg;
    private ApiTranslationProject result;
}
