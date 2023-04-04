package com.yicat.client.dictionary;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yicat.client.core.YicatApi;
import com.yicat.client.core.http.HttpRequestConfig;
import com.yicat.client.core.http.exceptions.HttpBadRequestException;
import com.yicat.client.core.http.exceptions.HttpException;
import com.yicat.client.core.model.ClientConfig;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.core.model.FileObject;
import com.yicat.client.dictionary.model.AddTranslationDictionaryEntryRequest;
import com.yicat.client.dictionary.model.ApiTranslationDictionary;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DictionaryApi extends YicatApi {
    public DictionaryApi(Credentials credentials) {
        super(credentials);
    }

    public DictionaryApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * 创建词典
     *
     * @param language
     * @param dictionaryName
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationDictionary createDictionary(String language, String dictionaryName) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "language", Optional.ofNullable(language),
                "dictionary_name", Optional.ofNullable(dictionaryName)
        );
        return this.httpClient.post(this.url + "/dictionary", null, new HttpRequestConfig(queryParams), ApiTranslationDictionary.class);
    }

    /**
     * 获取词典列表信息
     *
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiTranslationDictionary> listDictionary() throws HttpException, HttpBadRequestException {
        List list = this.httpClient.get(this.url + "/dictionary", new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiTranslationDictionary>>() {
        });
    }

    /**
     * 根据词典Id查看词典详细信息
     *
     * @param dictionaryId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationDictionary dictionaryDetail(String dictionaryId) throws HttpException, HttpBadRequestException {
        return this.httpClient.get(this.url + "/dictionary/" + dictionaryId, new HttpRequestConfig(), ApiTranslationDictionary.class);
    }

    /**
     * 删除词典
     *
     * @param dictionaryIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void deleteDictionary(List<String> dictionaryIdList) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/dictionary", dictionaryIdList, new HttpRequestConfig(), Void.class);
    }

    /**
     * 更新词典信息
     *
     * @param dictionaryId
     * @param dictionaryName
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationDictionary updateDictionary(String dictionaryId, String dictionaryName) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "dictionaryName", Optional.ofNullable(dictionaryName)
        );
        return this.httpClient.patch(this.url + "/dictionary/" + dictionaryId, null, new HttpRequestConfig(queryParams), ApiTranslationDictionary.class);
    }

    /**
     * 导入词典
     *
     * @param dictionaryId
     * @param file
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void importEntryFile(String dictionaryId, File file) throws HttpException, HttpBadRequestException {
        this.httpClient.post(this.url + "/dictionary/" + dictionaryId + "/entry/_import", new FileObject(file, "file"), new HttpRequestConfig(), Void.class);
    }

    /**
     * 导出词典
     *
     * @param dictionaryId
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public InputStream exportEntryFile(String dictionaryId) throws HttpException, HttpBadRequestException {
        return this.httpClient.executeDownload(this.url + "/dictionary/" + dictionaryId + "/entry/_export", new HttpRequestConfig());
    }

    /**
     * 删除词典条目
     *
     * @param dictionaryId
     * @param entryNameList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void deleteDictionaryEntry(String dictionaryId, List<String> entryNameList) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/dictionary/" + dictionaryId + "/entry", entryNameList, new HttpRequestConfig(), Void.class);
    }

    /**
     * 新增条目
     *
     * @param dictionaryId
     * @param addTranslationDictionaryEntryRequest
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void insertEntry(String dictionaryId, AddTranslationDictionaryEntryRequest addTranslationDictionaryEntryRequest) throws HttpException, HttpBadRequestException {
        this.httpClient.post(this.url + "/dictionary/" + dictionaryId + "/entry", addTranslationDictionaryEntryRequest, new HttpRequestConfig(), Void.class);
    }

}
