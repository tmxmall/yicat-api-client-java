package vip.yicat.client.member.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApiTranslationGroupMember implements Serializable {
    private String groupMemberId;
    private String groupMemberName;
    private String groupMemberNickname;
    private Integer role;
    private Integer status;
    private Integer score;
    private Byte memberType;
    private Integer completeWords;
    private Integer completeTasks;
    private String mobile;
    private Byte activeStatus;
    private Date activeTime;
    private String mtUsedChars;
    private String mtLimitChars;
    private Date createTime;
    private Date modifyTime;
}

