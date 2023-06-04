package vip.yicat.client.segment.model;

import lombok.Data;

@Data
public class ApiStringsSegment {
    private String sourceText;
    private String targetText;
    private String segmentId;
    private String language;
}
