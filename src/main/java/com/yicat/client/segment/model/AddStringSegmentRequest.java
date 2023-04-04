package com.yicat.client.segment.model;

import lombok.Data;

@Data
public class AddStringSegmentRequest {
    private String sourceText;
    private String segmentId;
    private String language;
    private String note;
}
