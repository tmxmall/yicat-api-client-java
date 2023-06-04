package vip.yicat.client.segment.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ApiSegmentStageRecord implements Serializable {
    private int stage;
    private List<ApiAtom> atomList;
    private String editorUserId;
    private Date editorTime;
    private int timeSpent;
}
