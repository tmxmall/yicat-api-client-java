package com.yicat.client.segment.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApiSegmentEditRevision{
    private String segmentId;
    private String documentId;
    private int stage;
    private int saveType;
    private String userId;
    private String editorName;
    private Date changeTime;
    private String logIp;
    private Integer editSaveType;
    private List<ApiAtom> srcSegmentAtoms;
    private List<ApiAtom> tgtSegmentAtoms;
}

