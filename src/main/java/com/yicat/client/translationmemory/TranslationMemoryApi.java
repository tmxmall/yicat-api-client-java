package com.yicat.client.translationmemory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yicat.client.core.YicatApi;
import com.yicat.client.core.http.HttpRequestConfig;
import com.yicat.client.core.http.exceptions.HttpBadRequestException;
import com.yicat.client.core.http.exceptions.HttpException;
import com.yicat.client.core.model.*;
import com.yicat.client.translationmemory.model.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TranslationMemoryApi extends YicatApi {

    public TranslationMemoryApi(Credentials credentials) {
        super(credentials);
    }

    public TranslationMemoryApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * 获取记忆库列表信息
     *
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiYiCATTMInfo> listTMInfo() throws HttpException, HttpBadRequestException {
        List list = this.httpClient.get(this.url + "/tm", new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiYiCATTMInfo>>() {
        });
    }

    /**
     * 获取单个记忆库信息
     *
     * @param tmId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiYiCATTMInfo getTMInfo(String tmId) throws HttpException, HttpBadRequestException {
        return this.httpClient.get(this.url + "/tm/" + tmId, new HttpRequestConfig(), ApiYiCATTMInfo.class);
    }

    /**
     * 创建记忆库
     *
     * @param request
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiYiCATTMInfo createTM(AddYiCATTMRequest request) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/tm", request, new HttpRequestConfig(), ApiYiCATTMInfo.class);
    }

    /**
     * 添加记忆库条目
     *
     * @param tmId
     * @param baseItemList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public String createTMEntry(String tmId, List<BaseItem> baseItemList) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/tm/" + tmId + "/entry", baseItemList, new HttpRequestConfig(), String.class);
    }

    /**
     * @param tmId
     * @param text
     * @param caseSensitive
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiYiCATTMEntryInfo> searchTMEntry(String tmId, String text, Boolean caseSensitive) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = null;
        try {
            queryParams = HttpRequestConfig.buildUrlParams(
                    "text", Optional.ofNullable(URLEncoder.encode(text, "UTF-8")),
                    "caseSensitive", Optional.ofNullable(caseSensitive)
            );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        List list = this.httpClient.post(this.url + "/tm/" + tmId + "/entry/_search", null, new HttpRequestConfig(queryParams), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiYiCATTMEntryInfo>>() {
        });
    }

    /**
     * 导入记忆库文件（只支持tmx格式）
     *
     * @param tmId
     * @param file
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void importTMXFile(String tmId, File file) throws HttpException, HttpBadRequestException {
        this.httpClient.post(this.url + "/tm/" + tmId + "/tmxFile/_import", new FileObject(file, "file"),
                new HttpRequestConfig(), Void.class);
    }

    /**
     * 导出记忆库
     *
     * @param tmId
     * @param format   tmx-5 excel-2
     * @param complete true-完整版excel, false-简版excel
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public String exportTM(String tmId, int format, boolean complete) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "format", Optional.ofNullable(format),
                "complete", Optional.ofNullable(complete)
        );
        return this.httpClient.post(this.url + "/tm/" + tmId + "/_export", null, new HttpRequestConfig(queryParams), String.class);
    }

    /**
     * 匹配记忆库条目
     *
     * @param srcLang
     * @param tgtLang
     * @param preText
     * @param text
     * @param nextText
     * @param minMatchRate
     * @param tmIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiMatchTMEntryInfo> matchTMEntryByGroup(String srcLang, String tgtLang, String preText, String text, String nextText, Integer minMatchRate, List<String> tmIdList) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = null;
        try {
            queryParams = HttpRequestConfig.buildUrlParams(
                    "srcLang", Optional.ofNullable(srcLang),
                    "tgtLang", Optional.ofNullable(tgtLang),
                    "preText", Optional.ofNullable(URLEncoder.encode(preText, "UTF-8")),
                    "text", Optional.ofNullable(URLEncoder.encode(text, "UTF-8")),
                    "nextText", Optional.ofNullable(URLEncoder.encode(nextText, "UTF-8")),
                    "minMatchRate", Optional.ofNullable(minMatchRate)
            );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        List list = this.httpClient.post(this.url + "/tm/entry/_match", tmIdList, new HttpRequestConfig(queryParams), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiMatchTMEntryInfo>>() {
        });
    }

    /**
     * 更新记忆库名称或标签
     *
     * @param tmId
     * @param patchYiCATTMRequest
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiYiCATTMInfo updateTM(String tmId, PatchYiCATTMRequest patchYiCATTMRequest) throws HttpException, HttpBadRequestException {
        return this.httpClient.patch(this.url + "/tm/" + tmId, patchYiCATTMRequest, new HttpRequestConfig(), ApiYiCATTMInfo.class);
    }

    /**
     * 保存记忆库条目
     *
     * @param tmId
     * @param entryId
     * @param lang
     * @param text
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void saveTMEntry(String tmId, String entryId, String lang, String text) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "lang", Optional.ofNullable(lang),
                "text", Optional.ofNullable(text)
        );
        this.httpClient.patch(this.url + "/tm/" + tmId + "/entry/" + entryId, null, new HttpRequestConfig(queryParams), Void.class);
    }

    /**
     * 删除记忆库
     *
     * @param tmIds
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void deleteTM(List<String> tmIds) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/tm", tmIds, new HttpRequestConfig(), Void.class);
    }

    /**
     * 删除记忆库条目
     *
     * @param tmId
     * @param entryIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void deleteTMEntry(String tmId, List<String> entryIdList) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/tm/" + tmId + "/entry", entryIdList, new HttpRequestConfig(), Void.class);
    }

}
