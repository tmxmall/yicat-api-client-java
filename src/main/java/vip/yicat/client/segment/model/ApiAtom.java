package vip.yicat.client.segment.model;

import lombok.Data;

@Data
public class ApiAtom {
    private String atomId;
    private String data;
    private String textStyle;
    private boolean isHidden;
    private String tagType;
    private String tagId;
    private boolean isCustom;
    private Integer reviseType;
    private String styleContent;
}

