package com.yicat.client.field;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yicat.client.core.YicatApi;
import com.yicat.client.core.http.HttpRequestConfig;
import com.yicat.client.core.http.exceptions.HttpBadRequestException;
import com.yicat.client.core.http.exceptions.HttpException;
import com.yicat.client.core.model.*;
import com.yicat.client.field.model.ApiTranslationField;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FieldApi extends YicatApi {


    public FieldApi(Credentials credentials) {
        super(credentials);
    }

    public FieldApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * 查询领域分组
     *
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiTranslationField> getField() throws HttpException, HttpBadRequestException {
        List list = this.httpClient.get(this.url + "/field", new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiTranslationField>>() {
        });
    }

    /**
     * 创建领域分组
     *
     * @param fieldName
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationField createField(String fieldName) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "fieldName", Optional.ofNullable(fieldName)
        );
        return this.httpClient.post(this.url + "/field", null, new HttpRequestConfig(queryParams), ApiTranslationField.class);
    }

    /**
     * 删除领域分组
     *
     * @param fieldId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void deleteField(String fieldId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/field/" + fieldId, new HttpRequestConfig(), Void.class);
    }

    /**
     * 修改领域分组
     *
     * @param fieldName
     * @param fieldId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationField updateField(String fieldName, String fieldId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "fieldName", Optional.ofNullable(fieldName)
        );
        return this.httpClient.patch(this.url + "/field/" + fieldId, null, new HttpRequestConfig(queryParams), ApiTranslationField.class);
    }
}
