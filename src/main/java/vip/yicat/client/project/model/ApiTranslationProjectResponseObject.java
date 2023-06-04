package vip.yicat.client.project.model;

import lombok.Data;

@Data
public class ApiTranslationProjectResponseObject {
    private String status;
    private String errCode;
    private String errMsg;
    private ApiTranslationProject result;
}
