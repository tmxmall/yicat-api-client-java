package vip.yicat.client.document.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CoverDocumentRequest implements Serializable {
    /**
     * File id, according to the result of ApiUploadFileInfo's file id
     */
    private String fileId;
    private String fileName;
    private long fileSize;
}
