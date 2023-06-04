package vip.yicat.client.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import vip.yicat.client.core.YiCATApi;
import vip.yicat.client.core.http.HttpRequestConfig;
import vip.yicat.client.core.http.exceptions.HttpBadRequestException;
import vip.yicat.client.core.http.exceptions.HttpException;
import vip.yicat.client.core.model.ClientConfig;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.task.model.ApiTaskStatistics;
import vip.yicat.client.task.model.ApiTranslationTask;
import vip.yicat.client.task.model.ApiTranslationTaskRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskApi extends YiCATApi {

    public TaskApi(Credentials credentials) {
        super(credentials);
    }

    public TaskApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * 根据项目Id查看任务列表
     *
     * @param projectId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiTranslationTask> getTaskInfoByProjectId(String projectId) throws HttpException, HttpBadRequestException {
        List list = this.httpClient.get(this.url + "/task/project/" + projectId, new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiTranslationTask>>() {
        });
    }

    /**
     * 根据文档Id查看任务列表
     *
     * @param documentId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiTranslationTask> getTaskInfoByDocumentId(String documentId) throws HttpException, HttpBadRequestException {
        return this.httpClient.get(this.url + "/task/document/" + documentId, new HttpRequestConfig(), List.class);
    }

    /**
     * 根据任务Id查看单个任务信息
     *
     * @param taskId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationTask getTaskInfoByTaskId(String taskId) throws HttpException, HttpBadRequestException {
        return this.httpClient.get(this.url + "/task/" + taskId, new HttpRequestConfig(), ApiTranslationTask.class);
    }


    /**
     * 按用户id列出任务
     *
     * @param userIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<ApiTranslationTask> getTaskByGroupMembers(List<String> userIdList) throws HttpException, HttpBadRequestException {
        List list = this.httpClient.get(this.url + "/task/user", userIdList, new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<ApiTranslationTask>>() {
        });
    }

    /**
     * 列出任务统计信息
     *
     * @param taskId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTaskStatistics getTaskDetailsStatistics(String taskId) throws HttpException, HttpBadRequestException {
        return this.httpClient.get(this.url + "/task/" + taskId + "/detailsStatistics", new HttpRequestConfig(), ApiTaskStatistics.class);
    }

    /**
     * 创建任务
     *
     * @param apiTranslationTaskRequestList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public List<String> createTask(List<ApiTranslationTaskRequest> apiTranslationTaskRequestList) throws HttpException, HttpBadRequestException {
        List list = this.httpClient.post(this.url + "/task", apiTranslationTaskRequestList, new HttpRequestConfig(), List.class);
        return JSON.parseObject(JSONObject.toJSONString(list), new TypeReference<List<String>>() {
        });
    }

    /**
     * 修改任务信息
     *
     * @param deadline
     * @param unitPrice
     * @param wordCountMode
     * @param taskId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationTask updateTaskInfo(String deadline, String unitPrice, Byte wordCountMode, String taskId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "deadline", Optional.ofNullable(deadline),
                "unit_price", Optional.ofNullable(unitPrice),
                "word_count_mode", Optional.ofNullable(wordCountMode)
        );
        return this.httpClient.patch(this.url + "/task/" + taskId, null, new HttpRequestConfig(queryParams), ApiTranslationTask.class);
    }

    /**
     * 取消任务
     *
     * @param taskId
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationTask cancelTask(String taskId) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/task/" + taskId + "/_cancel", null, new HttpRequestConfig(), ApiTranslationTask.class);
    }

    /**
     * 删除任务
     *
     * @param taskIdList
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public void deleteTask(List<String> taskIdList) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/task", taskIdList, new HttpRequestConfig(), Void.class);
    }


    /**
     * 给任务打分
     *
     * @param taskId
     * @param score  分数得范围必须大于0小于等于10
     * @return
     * @throws HttpException
     * @throws HttpBadRequestException
     */
    public ApiTranslationTask taskScore(String taskId, Integer score) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "score", Optional.ofNullable(score)
        );
        return this.httpClient.post(this.url + "/task/" + taskId + "/_score", null, new HttpRequestConfig(queryParams), ApiTranslationTask.class);
    }
}
