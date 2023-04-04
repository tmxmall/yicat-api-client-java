package com.yicat.client.project.model;

import lombok.Data;

@Data
public class ApiTranslationProjectResponseList {
    private String status;
    private String errCode;
    private String errMsg;
    private PagingQueryResults<ApiTranslationProject> result;
}
