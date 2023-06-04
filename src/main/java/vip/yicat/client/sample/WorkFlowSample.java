package vip.yicat.client.sample;

import vip.yicat.client.Client;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.workflow.model.AddWorkflowTemplateRequest;
import vip.yicat.client.workflow.model.ApiWorkflowBase;
import vip.yicat.client.workflow.model.ApiWorkflowStage;
import vip.yicat.client.workflow.model.ApiWorkflowTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * 工作流
 */
public class WorkFlowSample {
    private static Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    private static Client client = new Client(credentials);

    public static void main(String[] args) {
        //添加一个工作流
        String name = "工作流01";
        String workflowBaseId = client.getWorkflowsApi().createWorkflowBaseInfo(name);
        System.out.println("添加一个工作流:" + name);
        //查询工作流列表
        List<ApiWorkflowBase> workflowBaseInfos = client.getWorkflowsApi().getWorkflowBaseInfo();
        System.out.println("查询工作流列表:" + workflowBaseInfos);
        //添加工作流模板
        String workflowTemplateId = client.getWorkflowsApi().createWorkflowTemplate(getAddWorkflowTemplateRequest());
        System.out.println("添加工作流模板:" + workflowTemplateId);
        //查询工作流模板列表
        List<ApiWorkflowTemplate> apiWorkflowTemplateList = client.getWorkflowsApi().getWorkflowTemplate();
        System.out.println("查询工作流模板列表:" + apiWorkflowTemplateList);
        //删除工作流模板
        client.getWorkflowsApi().deleteWorkflowTemplate(Arrays.asList(workflowTemplateId));
        System.out.println("删除工作流模板:" + workflowTemplateId);
        //删除工作流
        client.getWorkflowsApi().deleteWorkflowBaseInfo(Arrays.asList(workflowBaseId));
        System.out.println("删除工作流:" + workflowBaseId);
    }

    public static AddWorkflowTemplateRequest getAddWorkflowTemplateRequest() {
        List<ApiWorkflowStage> apiWorkflowStageList = client.getWorkflowsApi().getWorkflowTemplate().get(0).getApiWorkflowStageList();
        AddWorkflowTemplateRequest addWorkflowTemplateRequest = new AddWorkflowTemplateRequest();
        addWorkflowTemplateRequest.setApiWorkflowStageList(apiWorkflowStageList);
        addWorkflowTemplateRequest.setName("Template");
        addWorkflowTemplateRequest.setNote("This is a note");
        addWorkflowTemplateRequest.setType((byte) 1);
        return addWorkflowTemplateRequest;
    }
}
