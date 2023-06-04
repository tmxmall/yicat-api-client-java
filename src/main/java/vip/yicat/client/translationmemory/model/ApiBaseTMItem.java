package vip.yicat.client.translationmemory.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class ApiBaseTMItem {

    private byte status;

    private String projectId;

    private String projectName;

    private String documentId;

    private String documentName;

    private boolean absoluteMatch;

    private String previousText;

    private String nextText;

    private String createUserId;

    private String createUserEmail;

    private String createUserName;

    private Date createTime;

    private String modifyUserId;

    private String modifyUserEmail;

    private String modifyUserName;

    private Date modifyTime;

    private String lang;

    private String text;

}