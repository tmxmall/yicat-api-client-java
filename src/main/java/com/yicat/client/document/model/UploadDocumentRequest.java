package com.yicat.client.document.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class UploadDocumentRequest implements Serializable {
    private String fileId;
    private String fileName;
    private String parentPath = "";
    private long fileSize;
    private String srcLan;
    private List<String> tgtLanList;
    private Map<String, Map<String, Object>> settings;
    private List<ApiDocumentWorkflowStage> apiDocumentWorkflowStageList;
}
