package com.yicat.client.translationmemory.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApiYiCATTMInfo {

    private String tmId;

    private String tmName;

    private Byte state;

    private String srcLang;

    private List<LangInfo> langInfoList;

    private String tag;

    private String groupId;

    private String createUserId;

    private String createUserEmail;

    private String createUserName;

    private Byte visibilityLevel;

    private int entryNum;

    private Date lastImportTime;

    private Date createTime;

    private Date modifyTime;
}
