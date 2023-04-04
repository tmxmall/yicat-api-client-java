package com.yicat.client.termbase.model;

import com.yicat.client.translationmemory.model.ApiBaseItem;
import lombok.Data;

import java.util.Date;

@Data
public abstract class ApiAbstractBaseItem extends ApiBaseItem {

    private String createUserId;

    private String createUserEmail;

    private String createUserName;

    private Date createTime;

    private String modifyUserId;

    private String modifyUserEmail;

    private String modifyUserName;

    private Date modifyTime;
}
