package vip.yicat.client.translationmemory.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
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
