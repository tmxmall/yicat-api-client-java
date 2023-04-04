package com.yicat.client.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yicat.client.core.YicatApi;
import com.yicat.client.core.http.HttpRequestConfig;
import com.yicat.client.core.http.exceptions.HttpBadRequestException;
import com.yicat.client.core.model.ClientConfig;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.project.model.*;
import com.yicat.client.segment.model.ApiQARule;
import com.yicat.client.core.http.exceptions.HttpException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProjectApi extends YicatApi {

    public ProjectApi(Credentials credentials) {
        super(credentials);
    }

    public ProjectApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * 分页查询项目列表
     *
     * @param currentPage
     * @param pageSize
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public PagingQueryResults<ApiTranslationProject> listProject(Integer currentPage, Integer pageSize) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "current_page", Optional.ofNullable(currentPage),
                "page_size", Optional.ofNullable(pageSize)
        );
        JSONObject jsonObject = this.httpClient.get(this.url + "/project", new HttpRequestConfig(queryParams), JSONObject.class);
        return JSON.parseObject(JSONObject.toJSONString(jsonObject), new TypeReference<PagingQueryResults<ApiTranslationProject> >() {
        });
    }

    /**
     * 根据项目Id获取单个项目
     *
     * @param projectId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationProject getProject(String projectId) throws HttpException, HttpBadRequestException {
        return this.httpClient.get(this.url + "/project/" + projectId, new HttpRequestConfig(), ApiTranslationProject.class);
    }

    /**
     * 创建项目
     *
     * @param AddTranslationProjectRequest
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationProjectWithSettings createProject(AddTranslationProjectRequest AddTranslationProjectRequest) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/project", AddTranslationProjectRequest, new HttpRequestConfig(), ApiTranslationProjectWithSettings.class);
    }

    /**
     * 删除项目
     *
     * @param projectIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void deleteProjects(List<String> projectIdList) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/project", projectIdList, new HttpRequestConfig(), Void.class);
    }

    /**
     * 项目基本信息设置
     *
     * @param projectId
     * @param patchProjectBasicInfoRequest
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationProjectWithSettings updateProjectBasicInfo(String projectId, PatchProjectBasicInfoRequest patchProjectBasicInfoRequest) throws HttpException, HttpBadRequestException {
        return this.httpClient.patch(this.url + "/project/" + projectId + "/basicInfo", patchProjectBasicInfoRequest, new HttpRequestConfig(), ApiTranslationProjectWithSettings.class);
    }

    /**
     * 项目重复设置
     *
     * @param projectId
     * @param patchProjectRepetitionSettingsRequest
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationProjectWithSettings updateProjectRepetitionSettings(String projectId, PatchProjectRepetitionSettingsRequest patchProjectRepetitionSettingsRequest) throws HttpException, HttpBadRequestException {
        return this.httpClient.patch(this.url + "/project/" + projectId + "/repetitionSettings", patchProjectRepetitionSettingsRequest, new HttpRequestConfig(), ApiTranslationProjectWithSettings.class);
    }

    /**
     * 项目QA规则设置
     *
     * @param projectId
     * @param apiQARuleList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationProjectWithSettings updateProjectQARules(String projectId, List<ApiQARule> apiQARuleList) throws HttpException, HttpBadRequestException {
        return this.httpClient.patch(this.url + "/project/" + projectId + "/qaRules", apiQARuleList, new HttpRequestConfig(), ApiTranslationProjectWithSettings.class);
    }

    /**
     * 项目挂载记忆库设置
     *
     * @param projectId
     * @param apiMountTranslationMemoryList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationProjectWithSettings updateProjectMountTranslationMemory(String projectId, List<ApiMountTranslationMemory> apiMountTranslationMemoryList) throws HttpException, HttpBadRequestException {
        return this.httpClient.patch(this.url + "/project/" + projectId + "/mountTranslationMemory", apiMountTranslationMemoryList, new HttpRequestConfig(), ApiTranslationProjectWithSettings.class);
    }

    /**
     * 项目挂载的术语库设置
     *
     * @param projectId
     * @param apiMountTermBaseList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationProjectWithSettings updateProjectMountTermBase(String projectId, List<ApiMountTermBase> apiMountTermBaseList) throws HttpException, HttpBadRequestException {
        return this.httpClient.patch(this.url + "/project/" + projectId + "/mountTermBase", apiMountTermBaseList, new HttpRequestConfig(), ApiTranslationProjectWithSettings.class);
    }

    /**
     * 项目机器翻译提供者设置
     *
     * @param projectId
     * @param mtProvider
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationProjectWithSettings updateProjectMTProvider(String projectId, String mtProvider) throws HttpException, HttpBadRequestException {
        return this.httpClient.patch(this.url + "/project/" + projectId + "/mtProvider/" + mtProvider, null, new HttpRequestConfig(), ApiTranslationProjectWithSettings.class);
    }

    /**
     * 项目转换前设置
     *
     * @param projectId
     * @param patchProjectPretransSettingsRequest
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationProjectWithSettings updateProjectPretransSettings(String projectId, PatchProjectPretransSettingsRequest patchProjectPretransSettingsRequest) throws HttpException, HttpBadRequestException {
        return this.httpClient.patch(this.url + "/project/" + projectId + "/pretransSettings", patchProjectPretransSettingsRequest, new HttpRequestConfig(), ApiTranslationProjectWithSettings.class);
    }

    /**
     * 项目价格计算设置
     *
     * @param projectId
     * @param patchProjectPriceCalcSettingsRequest
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationProjectWithSettings updateProjectPriceCalcSettings(String projectId, PatchProjectPriceCalcSettingsRequest patchProjectPriceCalcSettingsRequest) throws HttpException, HttpBadRequestException {
        return this.httpClient.patch(this.url + "/project/" + projectId + "/priceCalcSettings", patchProjectPriceCalcSettingsRequest, new HttpRequestConfig(), ApiTranslationProjectWithSettings.class);
    }

    /**
     * 项目文件解析器设置
     *
     * @param projectId
     * @param fileParserSettings
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationProjectWithSettings updateProjectFileParserSettings(String projectId, Map<String, Map<String, Object>> fileParserSettings) throws HttpException, HttpBadRequestException {
        return this.httpClient.patch(this.url + "/project/" + projectId + "/fileParserSettings", fileParserSettings, new HttpRequestConfig(), ApiTranslationProjectWithSettings.class);
    }

    /**
     * 项目访问控制设置
     *
     * @param projectId
     * @param patchProjectAccessControlSettingsRequest
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationProjectWithSettings updateProjectAccessControlSettings(String projectId, PatchProjectAccessControlSettingsRequest patchProjectAccessControlSettingsRequest) throws HttpException, HttpBadRequestException {
        return this.httpClient.patch(this.url + "/project/" + projectId + "/accessControlSettings", patchProjectAccessControlSettingsRequest, new HttpRequestConfig(), ApiTranslationProjectWithSettings.class);
    }
}
