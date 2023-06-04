package vip.yicat.client.dictionary.model;

import lombok.Data;

import java.util.Date;

@Data
public class ApiTranslationDictionary {
    private String groupId;
    private String createUserId;
    private String dictionaryId;
    private String dictionaryName;
    private String language;
    private Integer entryNumber;
    private Date createTime;
    private Date modifyTime;
}
