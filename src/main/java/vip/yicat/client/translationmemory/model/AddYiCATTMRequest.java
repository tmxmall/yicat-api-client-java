package vip.yicat.client.translationmemory.model;

import lombok.Data;

import java.util.List;

@Data
public class AddYiCATTMRequest {
    private String tmName;
    private String srcLang;
    private List<String> tgtLanList;
    private String tag;
    private Byte visibilityLevel;
}
