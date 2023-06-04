package vip.yicat.client.document;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import vip.yicat.client.core.YiCATApi;
import vip.yicat.client.core.http.HttpRequestConfig;
import vip.yicat.client.core.http.exceptions.HttpBadRequestException;
import vip.yicat.client.core.http.exceptions.HttpException;
import vip.yicat.client.core.model.ClientConfig;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.core.model.FileObject;
import vip.yicat.client.document.model.*;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DocumentApi extends YiCATApi {

    public DocumentApi(Credentials credentials) {
        super(credentials);
    }

    public DocumentApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * 获取文档列表
     *
     * @param projectId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiTranslationDocumentWithSettings> getDocuments(String projectId) throws HttpException, HttpBadRequestException {
        List list = this.httpClient.get(this.url + "/project/" + projectId + "/document", new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiTranslationDocumentWithSettings>>() {
        });
    }

    /**
     * 根据projectId和documentId获取文档信息
     *
     * @param projectId
     * @param documentId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationDocumentWithSettings getDocument(String projectId, String documentId) throws HttpException, HttpBadRequestException {
        return this.httpClient.get(this.url + "/project/" + projectId + "/document/" + documentId, new HttpRequestConfig(), ApiTranslationDocumentWithSettings.class);
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiUploadFileInfo> uploadFile(File file) throws HttpException, HttpBadRequestException {
        List list = this.httpClient.post(this.url + "/project/document/file", new FileObject(file, "file"), new HttpRequestConfig(), List.class);
        List<ApiUploadFileInfo> apiUploadFileInfoResponseList = JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiUploadFileInfo>>() {
        });
        List<ApiUploadFileInfo> apiUploadFileInfos = apiUploadFileInfoResponseList.stream().map(s ->
        {
            try {
                // 将文件名进行解码
                s.setFileName(URLDecoder.decode(s.getFileName(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            return s;
        }).collect(Collectors.toList());
        return apiUploadFileInfos;
    }

    /**
     * 添加文档到项目
     *
     * @param projectId
     * @param uploadDocumentRequestList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiTranslationDocumentWithSettings> createDocument(String projectId, List<UploadDocumentRequest> uploadDocumentRequestList) throws HttpException, HttpBadRequestException {
        List list = this.httpClient.post(this.url + "/project/" + projectId + "/document", uploadDocumentRequestList, new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiTranslationDocumentWithSettings>>() {
        });
    }

    /**
     * 根据下载类型导出文档
     *
     * @param documentIdList
     * @param downloadType   - 0: export original file
     *                       - 1: export target file
     *                       - 2: export target bilingual file
     *                       - 3: export tmx file
     *                       - 4: export target file with revision trace
     *                       - 5: export offline translation file with all segments
     *                       - 6: export offline translation file with segments not locked
     *                       - 7: export MTPE report file
     * @param projectId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public String exportDocuments(List<String> documentIdList, int downloadType, String projectId) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/project/" + projectId + "/document/type/" + downloadType + "/_export", documentIdList, new HttpRequestConfig(), String.class);
    }

    /**
     * 上传离线文件
     *
     * @param file
     * @param documentId
     * @param projectId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void uploadOfflineFile(File file, String documentId, String projectId) throws HttpException, HttpBadRequestException {
        this.httpClient.post(this.url + "/project/" + projectId + "/document/" + documentId + "/offlineFile/_upload", new FileObject(file, "offline_file"), new HttpRequestConfig(), Void.class);
    }

    /**
     * 根据工作流导出文档
     *
     * @param projectId
     * @param exportDocumentRequest
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public String exportDocumentByWorkflow(String projectId, ExportDocumentRequest exportDocumentRequest) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/project/" + projectId + "/document/workflow/_export", exportDocumentRequest, new HttpRequestConfig(), String.class);
    }

    /**
     * 删除文档
     *
     * @param projectId
     * @param documentIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void deleteDocuments(String projectId, List<String> documentIdList) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/project/" + projectId + "/document", documentIdList, new HttpRequestConfig(), Void.class);
    }

    /**
     * 更新原文
     *
     * @param projectId
     * @param coverDocumentRequest
     * @param sourceDocumentId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void updateDocument(String projectId, CoverDocumentRequest coverDocumentRequest, String sourceDocumentId) throws HttpException, HttpBadRequestException {
        this.httpClient.patch(this.url + "/project/" + projectId + "/document/" + sourceDocumentId, coverDocumentRequest, new HttpRequestConfig(), Void.class);
    }

    /**
     * 下载文档
     *
     * @param fileId
     * @param isIe   判断当前是否为IE浏览器
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public InputStream downFile(String fileId, String isIe) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "file_id", Optional.ofNullable(fileId),
                "is_ie", Optional.ofNullable(isIe)
        );
        return this.httpClient.executeDownload(this.url + "/file", new HttpRequestConfig(queryParams));
    }

}