package vip.yicat.client.member.model;

import lombok.Data;

import java.util.List;

@Data
public class ApiTranslationGroupMemberResponseList {

    private String status;
    private String errCode;
    private String errMsg;
    private List<ApiTranslationGroupMember> result;

}
