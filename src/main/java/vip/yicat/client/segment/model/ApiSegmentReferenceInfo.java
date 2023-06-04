package vip.yicat.client.segment.model;

import lombok.Data;

import java.util.Date;

@Data
public class ApiSegmentReferenceInfo {
    private String _id;
    private String documentId;
    private String segmentId;
    private String referenceUrl;
    private String referenceImgUrl;
    private Date createTime;
}
