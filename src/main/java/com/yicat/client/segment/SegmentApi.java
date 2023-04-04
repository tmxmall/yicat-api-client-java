package com.yicat.client.segment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yicat.client.core.YicatApi;
import com.yicat.client.core.http.HttpRequestConfig;
import com.yicat.client.core.http.exceptions.HttpBadRequestException;
import com.yicat.client.core.http.exceptions.HttpException;
import com.yicat.client.core.model.*;
import com.yicat.client.segment.model.*;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SegmentApi extends YicatApi {
    public SegmentApi(Credentials credentials) {
        super(credentials);
    }

    public SegmentApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * 根据文档ID和字段ID获取字段信息
     *
     * @param documentId
     * @param segmentId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiSegment> loadSegmentByDocumentId(String documentId, String segmentId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "segment_id", Optional.ofNullable(segmentId)
        );
        List list = this.httpClient.get(this.url + "/segment/document/" + documentId, new HttpRequestConfig(queryParams), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiSegment>>() {
        });
    }

    /**
     * 根据文档ID提交句段
     *
     * @param documentId
     * @param apiSegmentEditMessageList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiSegmentEditMessage> submitEditMessageByDocument(String documentId, List<ApiSegmentEditMessage> apiSegmentEditMessageList) throws HttpException, HttpBadRequestException {
        List list = this.httpClient.post(this.url + "/segment/document/" + documentId + "/_submit", apiSegmentEditMessageList, new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiSegmentEditMessage>>() {
        });
    }

    /**
     * 根据文档ID对齐
     *
     * @param documentId
     * @param segmentIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public Map<String, List<Atom>> alignSegmentByDocumentId(String documentId, List<String> segmentIdList) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/segment/document/" + documentId + "/_align", segmentIdList, new HttpRequestConfig(), Map.class);
    }

    /**
     * 根据文档ID获取提交历史
     *
     * @param documentId
     * @param segmentId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiSegmentEditRevision> loadSubmitHistoryByDocumentId(String documentId, String segmentId) throws HttpException, HttpBadRequestException {
        JSONObject list = this.httpClient.get(this.url + "/segment/" + segmentId + "/document/" + documentId + "/segmentEditRevision", null, new HttpRequestConfig(), JSONObject.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiSegmentEditRevision>>() {
        });
    }

    /**
     * 根据文档ID获取字段参考信息
     *
     * @param documentId
     * @param segmentId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiSegmentReferenceInfo> getSegmentReferenceInfoByDocumentId(String documentId, String segmentId) throws HttpException, HttpBadRequestException {
        List list = this.httpClient.get(this.url + "/segment/" + segmentId + "/document/" + documentId + "/referenceInfo", new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiSegmentReferenceInfo>>() {
        });
    }

    /**
     * 根据文档ID更改字段参考信息
     *
     * @param url
     * @param documentId
     * @param segmentId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiSegmentReferenceInfo updateSegmentReferenceUrlByDocumentId(String url, String documentId, String segmentId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "url", Optional.ofNullable(url)
        );
        return this.httpClient.patch(this.url + "/segment/" + segmentId + "/document/" + documentId + "/referenceUrl", null, new HttpRequestConfig(queryParams), ApiSegmentReferenceInfo.class);
    }

    /**
     * 根据文档ID更改参考图
     *
     * @param documentId
     * @param segmentId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiSegmentReferenceInfo updateSegmentReferenceImgUrlByDocumentId(File file, String documentId, String segmentId) throws HttpException, HttpBadRequestException {
        return this.httpClient.patch(this.url + "/segment/" + segmentId + "/document/" + documentId + "/referenceImg", new FileObject(file, "image"), new HttpRequestConfig(), ApiSegmentReferenceInfo.class);

    }

    /**
     * 根据文档ID删除句段参考图
     *
     * @param documentId
     * @param segmentId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiSegmentReferenceInfo cleanSegmentReferenceImgUrlByDocumentId(String documentId, String segmentId) throws HttpException, HttpBadRequestException {
        return this.httpClient.delete(this.url + "/segment/" + segmentId + "/document/" + documentId + "/referenceImg", new HttpRequestConfig(), ApiSegmentReferenceInfo.class);
    }

    /**
     * 根据文档ID回退字段
     *
     * @param rangeStart
     * @param rangeEnd
     * @param documentId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void revertSegmentByDocumentId(double rangeStart, double rangeEnd, String documentId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "range_start", Optional.ofNullable(rangeStart),
                "range_end", Optional.ofNullable(rangeEnd)
        );
         this.httpClient.post(this.url + "/segment/document/" + documentId + "/_revert", null, new HttpRequestConfig(queryParams), Void.class);
    }

}
