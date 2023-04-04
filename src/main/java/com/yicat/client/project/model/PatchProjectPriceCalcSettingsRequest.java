package com.yicat.client.project.model;

import lombok.Data;

@Data
public class PatchProjectPriceCalcSettingsRequest {
    private Integer newOff;
    private Integer tm5074Off;
    private Integer tm7584Off;
    private Integer tm8594Off;
    private Integer tm9599Off;
    private Integer tm100Off;
    private Integer tm101Off;
    private Integer tm102Off;
    private Integer innerRepeatOff;
    private Integer outerRepeatOff;
    private Integer mtOff;
    private Byte wordCountMode;
}
