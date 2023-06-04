package vip.yicat.client.document.model;

import lombok.Data;

@Data
public class ApiUploadFileInfo {
    private String fileId;
    private String fileName;
    private String parentPath = "";
    private long fileSize;
    private String resultInfo;
}
