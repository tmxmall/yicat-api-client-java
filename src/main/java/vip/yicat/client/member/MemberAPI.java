package vip.yicat.client.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import vip.yicat.client.core.YiCATApi;
import vip.yicat.client.core.http.HttpRequestConfig;
import vip.yicat.client.core.http.exceptions.HttpBadRequestException;
import vip.yicat.client.core.http.exceptions.HttpException;
import vip.yicat.client.core.model.ClientConfig;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.member.model.ApiTranslationGroupMember;

import java.util.List;

public class MemberAPI extends YiCATApi {
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
