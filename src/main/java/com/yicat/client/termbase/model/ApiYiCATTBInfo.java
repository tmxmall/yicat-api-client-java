package com.yicat.client.termbase.model;

import com.yicat.client.translationmemory.model.LangInfo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApiYiCATTBInfo {

    private String tbId;

    private String tbName;

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
