package com.yicat.client.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yicat.client.core.YicatApi;
import com.yicat.client.core.http.HttpRequestConfig;
import com.yicat.client.core.http.exceptions.HttpBadRequestException;
import com.yicat.client.core.http.exceptions.HttpException;
import com.yicat.client.core.model.ClientConfig;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.member.model.ApiTranslationGroupMember;

import java.util.List;

public class MemberAPI extends YicatApi {
    public MemberAPI(Credentials credentials) {
        super(credentials);
    }

    public MemberAPI(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * 获取所有组成员信息
     *
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiTranslationGroupMember> getTranslationGroupMember() throws HttpException, HttpBadRequestException {
        List list = this.httpClient.get(this.url + "/member", new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiTranslationGroupMember>>() {
        });
    }
}
