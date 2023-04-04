package com.yicat.client.termbase.model;

import lombok.Data;

import java.util.List;

@Data
public class AddYiCATTBRequest {
    private String tbName;
    private String srcLang;
    private List<String> tgtLanList;
    private String tag;
    private Byte visibilityLevel;
}
