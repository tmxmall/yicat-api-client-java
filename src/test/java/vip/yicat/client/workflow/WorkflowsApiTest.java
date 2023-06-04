package vip.yicat.client.workflow;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vip.yicat.client.Client;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.workflow.model.AddWorkflowTemplateRequest;
import vip.yicat.client.workflow.model.ApiWorkflowBase;
import vip.yicat.client.workflow.model.ApiWorkflowStage;
import vip.yicat.client.workflow.model.ApiWorkflowTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WorkflowsApiTest {
    Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    Client client = new Client(credentials);

    @Test
    @Order(1)
    void getWorkflowBaseInfo() {
        List<ApiWorkflowBase> workflowBaseInfos = client.getWorkflowsApi().getWorkflowBaseInfo();
        System.out.println(workflowBaseInfos);
        assertNotNull(workflowBaseInfos);
    }

    @Test
    @Order(2)
    void getWorkflowTemplate() {
        List<ApiWorkflowTemplate> apiWorkflowTemplateList = client.getWorkflowsApi().getWorkflowTemplate();
        System.out.println(apiWorkflowTemplateList);
        assertNotNull(apiWorkflowTemplateList);
    }

    @Test
    @Order(3)
    void createWorkflowBaseInfo() {
        client.getWorkflowsApi().createWorkflowBaseInfo("翻译2");
    }

    @Test
    @Order(4)
    void createWorkflowTemplate() {
        List<ApiWorkflowStage> apiWorkflowStageList = client.getWorkflowsApi().getWorkflowTemplate().get(0).getApiWorkflowStageList();

        AddWorkflowTemplateRequest addWorkflowTemplateRequest = new AddWorkflowTemplateRequest();
        addWorkflowTemplateRequest.setApiWorkflowStageList(apiWorkflowStageList);
        addWorkflowTemplateRequest.setName("Template");
        addWorkflowTemplateRequest.setNote("这是一段描述");
        addWorkflowTemplateRequest.setType((byte) 1);

        String workflowTemplate = client.getWorkflowsApi().createWorkflowTemplate(addWorkflowTemplateRequest);
        System.out.println(workflowTemplate);
        assertNotNull(workflowTemplate);
    }

    @Test
    @Order(5)
    void deleteWorkflowBaseInfo() {
        List workflowBaseIdList = new ArrayList();
        workflowBaseIdList.add("81d81f003e15496ab67cd2e1a364cfbb");
        client.getWorkflowsApi().deleteWorkflowBaseInfo(workflowBaseIdList);
    }

    @Test
    @Order(6)
    void deleteWorkflowTemplate() {
        List workflowTemplateIdList = new ArrayList();
        workflowTemplateIdList.add(client.getWorkflowsApi().getWorkflowTemplate().get(0).getTemplateId());
        client.getWorkflowsApi().deleteWorkflowTemplate(workflowTemplateIdList);
    }

}