package com.yicat.client.workflow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yicat.client.core.YicatApi;
import com.yicat.client.core.http.HttpRequestConfig;
import com.yicat.client.core.http.exceptions.HttpBadRequestException;
import com.yicat.client.core.http.exceptions.HttpException;
import com.yicat.client.core.model.*;
import com.yicat.client.workflow.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WorkflowsApi extends YicatApi {

    public WorkflowsApi(Credentials credentials) {
        super(credentials);
    }

    public WorkflowsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * 查询工作流列表信息
     *
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiWorkflowBase> getWorkflowBaseInfo() throws HttpException, HttpBadRequestException {
        List list = this.httpClient.get(this.url + "/workflow/baseInfo", new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiWorkflowBase>>() {
        });
    }

    /**
     * 查询工作流模板列表
     *
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiWorkflowTemplate> getWorkflowTemplate() throws HttpException, HttpBadRequestException {
        List list = this.httpClient.get(this.url + "/workflow/template", new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiWorkflowTemplate>>() {
        });
    }

    /**
     * 创建工作流
     * @param name
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public String createWorkflowBaseInfo(String name) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "name", Optional.ofNullable(name)
        );
       return this.httpClient.post(this.url + "/workflow/baseInfo", null, new HttpRequestConfig(queryParams), String.class);
    }

    /**
     * 添加一个工作流文件
     *
     * @param addWorkflowTemplateRequest
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public String createWorkflowTemplate(AddWorkflowTemplateRequest addWorkflowTemplateRequest) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/workflow/template", addWorkflowTemplateRequest, new HttpRequestConfig(), String.class);
    }

    /**
     * 删除工作流
     *
     * @param workflowBaseIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void deleteWorkflowBaseInfo(List<String> workflowBaseIdList) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/workflow/baseInfo", workflowBaseIdList, new HttpRequestConfig(), Void.class);
    }

    /**
     * 删除工作流模板
     *
     * @param workflowTemplateIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void deleteWorkflowTemplate(List<String> workflowTemplateIdList) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/workflow/template", workflowTemplateIdList, new HttpRequestConfig(), Void.class);
    }
}
