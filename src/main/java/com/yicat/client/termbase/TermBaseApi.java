package com.yicat.client.termbase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yicat.client.core.YicatApi;
import com.yicat.client.core.http.HttpRequestConfig;
import com.yicat.client.core.http.exceptions.HttpBadRequestException;
import com.yicat.client.core.http.exceptions.HttpException;
import com.yicat.client.core.model.*;
import com.yicat.client.termbase.model.*;
import com.yicat.client.translationmemory.model.ApiYiCATTMInfo;
import com.yicat.client.translationmemory.model.BaseItem;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TermBaseApi extends YicatApi {
    public TermBaseApi(Credentials credentials) {
        super(credentials);
    }

    public TermBaseApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * 获取术语库信息列表
     *
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiYiCATTBInfo> listTBInfo() throws HttpException, HttpBadRequestException {
        List list = this.httpClient.get(this.url + "/tb", new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiYiCATTBInfo>>() {
        });
    }

    /**
     * 根据tbId查询单个术语库信息
     *
     * @param tbId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiYiCATTMInfo getTBInfo(String tbId) throws HttpException, HttpBadRequestException {
        return this.httpClient.get(this.url + "/tb/" + tbId, new HttpRequestConfig(), ApiYiCATTMInfo.class);
    }

    /**
     * 创建术语库
     *
     * @param addYiCATTBRequest
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiYiCATTMInfo createTB(AddYiCATTBRequest addYiCATTBRequest) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/tb", addYiCATTBRequest, new HttpRequestConfig(), ApiYiCATTMInfo.class);
    }

    /**
     * 修改术语库
     *
     * @param tbId
     * @param patchYiCATTBRequest
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiYiCATTMInfo updateTB(String tbId, PatchYiCATTBRequest patchYiCATTBRequest) throws HttpException, HttpBadRequestException {
        return this.httpClient.patch(this.url + "/tb/" + tbId, patchYiCATTBRequest, new HttpRequestConfig(), ApiYiCATTMInfo.class);
    }

    /**
     * 删除术语库
     *
     * @param tbIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void deleteTB(List<String> tbIdList) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/tb", tbIdList, new HttpRequestConfig(), Void.class);
    }

    /**
     * 导入术语（只支持tbx文件）
     *
     * @param tbId
     * @param file
     * @param clearEntry 是否清空后导入
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void importTB(String tbId, File file, boolean clearEntry) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "clearEntry", Optional.ofNullable(clearEntry)
        );
        this.httpClient.post(this.url + "/tb/" + tbId + "/tbxFile/_import", new FileObject(file, "file"), new HttpRequestConfig(queryParams), Void.class);
    }

    /**
     * 搜索术语库条目
     *
     * @param tbId
     * @param text
     * @param caseSensitive
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiYiCATTBEntryInfo> searchTBEntry(String tbId, String text, boolean caseSensitive) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = null;
        try {
            queryParams = HttpRequestConfig.buildUrlParams(
                    "text", Optional.ofNullable(URLEncoder.encode(text, "UTF-8")),
                    "clearEntry", Optional.ofNullable(caseSensitive)
            );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        List list = this.httpClient.post(this.url + "/tb/" + tbId + "/entry/_search", null, new HttpRequestConfig(queryParams), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiYiCATTBEntryInfo>>() {
        });
    }

    /**
     * 创建术语库条目
     *
     * @param tbId
     * @param baseItemList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public String createTBEntry(String tbId, List<BaseItem> baseItemList) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/tb/" + tbId + "/entry", baseItemList, new HttpRequestConfig(), String.class);
    }

    /**
     * 修改术语库条目
     *
     * @param tbId
     * @param entryId
     * @param apiBaseTBItem
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void updateTBEntry(String tbId, String entryId, ApiBaseTBItem apiBaseTBItem) throws HttpException, HttpBadRequestException {
        this.httpClient.patch(this.url + "/tb/" + tbId + "/entry/" + entryId, apiBaseTBItem, new HttpRequestConfig(), Void.class);
    }

    /**
     * 删除术语库条目
     *
     * @param tbId
     * @param entryIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void deleteTBEntry(String tbId, List<String> entryIdList) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/tb/" + tbId + "/entry", entryIdList, new HttpRequestConfig(), Void.class);
    }

    /**
     * 匹配术语库条目
     *
     * @param srcLang
     * @param tgtLang
     * @param text
     * @param tbIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiMatchTBEntryInfo> matchTBEntryByGroup(String srcLang, String tgtLang, String text, List<String> tbIdList) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = null;
        try {
            queryParams = HttpRequestConfig.buildUrlParams(
                    "srcLang", Optional.ofNullable(srcLang),
                    "tgtLang", Optional.ofNullable(tgtLang),
                    "text", Optional.ofNullable(URLEncoder.encode(text, "UTF-8"))
            );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        List list = this.httpClient.post(this.url + "/tb/entry/_match", tbIdList, new HttpRequestConfig(queryParams), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiMatchTBEntryInfo>>() {
        });
    }

    /**
     * 导出术语
     * 注意：导入的术语源语言和目标语言要对应
     *
     * @param tbId
     * @param format   导出文件类型，2是 XLSX，6是 TBX
     * @param complete false导出简版excel，true导出完整版excel
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public String exportTB(String tbId, int format, boolean complete) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "format", Optional.ofNullable(format),
                "complete", Optional.ofNullable(complete)
        );
        return this.httpClient.post(this.url + "/tb/" + tbId + "/_export", null, new HttpRequestConfig(queryParams), String.class);
    }


}
