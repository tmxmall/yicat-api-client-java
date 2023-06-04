package vip.yicat.client.language;

import com.alibaba.fastjson.JSONObject;
import vip.yicat.client.core.YiCATApi;
import vip.yicat.client.core.http.HttpRequestConfig;
import vip.yicat.client.core.http.exceptions.HttpBadRequestException;
import vip.yicat.client.core.http.exceptions.HttpException;
import vip.yicat.client.core.model.ClientConfig;
import vip.yicat.client.core.model.Credentials;

public class LanguageAPI extends YiCATApi {


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
