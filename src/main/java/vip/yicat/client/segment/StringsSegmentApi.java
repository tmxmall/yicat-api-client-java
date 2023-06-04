package vip.yicat.client.segment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import vip.yicat.client.core.YiCATApi;
import vip.yicat.client.core.http.HttpRequestConfig;
import vip.yicat.client.core.http.exceptions.HttpBadRequestException;
import vip.yicat.client.core.http.exceptions.HttpException;
import vip.yicat.client.core.model.ClientConfig;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.segment.model.AddStringSegmentRequest;
import vip.yicat.client.segment.model.ApiStringsSegment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StringsSegmentApi extends YiCATApi {
    public StringsSegmentApi(Credentials credentials) {
        super(credentials);
    }

    public StringsSegmentApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * 获取目标语言的字段信息
     *
     * @param projectId
     * @param tgt_lan
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiStringsSegment> getI18NSegmentInfo(String projectId, String tgt_lan) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "tgt_lan", Optional.ofNullable(tgt_lan)
        );
        List list = this.httpClient.get(this.url + "/string/project/" + projectId + "/segment", new HttpRequestConfig(queryParams), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiStringsSegment>>() {
        });
    }

    /**
     * 为项目添加segment
     *
     * @param projectId
     * @param addStringSegmentRequestList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void addI18NSegmentInfo(String projectId, List<AddStringSegmentRequest> addStringSegmentRequestList) throws HttpException, HttpBadRequestException {
        this.httpClient.post(this.url + "/string/project/" + projectId + "/segment", addStringSegmentRequestList, new HttpRequestConfig(), Void.class);
    }

    /**
     * 删除segment
     *
     * @param projectId
     * @param segmentIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void deleteI18NSegmentInfo(String projectId, List<String> segmentIdList) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/string/project/" + projectId + "/segment", segmentIdList, new HttpRequestConfig(), Void.class);
    }

}
