package vip.yicat.client.field.model;

import lombok.Data;

import java.util.Date;

@Data
public class ApiTranslationField  {
	private Integer fieldId;
	private String fieldName;
    private String groupId;
    private String groupName;
    private String createUserId;
    private String createUserName;
    private Date createTime;
    private Date modifyTime;
}