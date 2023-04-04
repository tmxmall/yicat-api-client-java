package com.yicat.client.language;

import com.alibaba.fastjson.JSONObject;
import com.yicat.client.core.YicatApi;
import com.yicat.client.core.http.HttpRequestConfig;
import com.yicat.client.core.http.exceptions.HttpBadRequestException;
import com.yicat.client.core.http.exceptions.HttpException;
import com.yicat.client.core.model.ClientConfig;
import com.yicat.client.core.model.Credentials;

public class LanguageAPI extends YicatApi {


    public LanguageAPI(Credentials credentials) {
        super(credentials);
    }

    public LanguageAPI(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * 获取支持的语言列表
     *
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public JSONObject getLanguages() throws HttpException, HttpBadRequestException {
        return this.httpClient.get(this.url + "/languages", new HttpRequestConfig(), JSONObject.class);
    }

}
