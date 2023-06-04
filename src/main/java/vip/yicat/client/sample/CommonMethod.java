package vip.yicat.client.sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vip.yicat.client.Client;
import vip.yicat.client.document.model.ApiDocumentWorkflowStage;
import vip.yicat.client.document.model.ApiTranslationDocumentWithSettings;
import vip.yicat.client.document.model.ApiUploadFileInfo;
import vip.yicat.client.document.model.UploadDocumentRequest;
import vip.yicat.client.member.model.ApiTranslationGroupMember;
import vip.yicat.client.project.model.AddTranslationProjectRequest;
import vip.yicat.client.project.model.ApiProjectWorkflowStage;
import vip.yicat.client.project.model.ApiTranslationProject;
import vip.yicat.client.task.model.ApiTranslationTaskRequest;

public class CommonMethod {

    /**
     * 创建一个项目
     *
     * @param projectName
     * @param srcLan
     * @param tgtLanList
     * @return
     */
    public static ApiTranslationProject createProject(Client client, String projectName, String srcLan, List<String> tgtLanList,
                                                      String notes) {
        AddTranslationProjectRequest addTranslationProjectRequest = new AddTranslationProjectRequest();
        addTranslationProjectRequest.setProjectName(projectName);
        addTranslationProjectRequest.setSrcLan(srcLan);
        addTranslationProjectRequest.setTgtLanList(tgtLanList);
        addTranslationProjectRequest.setNotes(notes);
        Date now = new Date();
        addTranslationProjectRequest.setDeadline(new Date(now.getTime() + (365 * 24 * 60 * 60 * 1000)));
        addTranslationProjectRequest.setWorkflowTemplateId(client.getWorkflowsApi().getWorkflowTemplate().get(0).getTemplateId());
        addTranslationProjectRequest.setEnabledSimultaneousCollaboration((byte) 1);
        addTranslationProjectRequest.setProjectType(1);
        ApiTranslationProject project = client.getProjectApi().createProject(addTranslationProjectRequest);
        System.out.println("创建的项目Id为：" + project.getProjectId());
        return project;
    }

    /**
     * 添加文档到项目中
     *
     * @param apiTranslationProject
     * @param apiUploadFileInfo
     * @return
     */
    public static List<ApiTranslationDocumentWithSettings> createDocument(Client client, ApiTranslationProject apiTranslationProject,
                                                                          ApiUploadFileInfo apiUploadFileInfo) {
        ApiTranslationGroupMember apiTranslationGroupMember = client.getMemberAPI().getTranslationGroupMember().get(0);
        List<UploadDocumentRequest> uploadDocumentRequests = new ArrayList<>();
        UploadDocumentRequest uploadDocumentRequest = new UploadDocumentRequest();
        uploadDocumentRequest.setFileId(apiUploadFileInfo.getFileId());
        uploadDocumentRequest.setSrcLan(apiTranslationProject.getSrcLan());
        uploadDocumentRequest.setFileName(apiUploadFileInfo.getFileName());
        uploadDocumentRequest.setParentPath(apiUploadFileInfo.getParentPath());
        uploadDocumentRequest.setFileSize(apiUploadFileInfo.getFileSize());
        uploadDocumentRequest.setTgtLanList(apiTranslationProject.getTgtLanList());

        List<ApiProjectWorkflowStage> apiProjectWorkflowStageList = client.getProjectApi().getProject(apiTranslationProject.getProjectId()).getWorkflowStageList();
        List<ApiDocumentWorkflowStage> apiDocumentWorkflowStageList = new ArrayList<>();
        apiProjectWorkflowStageList.stream().forEach(s -> {
            ApiDocumentWorkflowStage apiDocumentWorkflowStage = new ApiDocumentWorkflowStage();
            apiDocumentWorkflowStage.setStage(s.getStage());
            apiDocumentWorkflowStage.setName(s.getName());
            apiDocumentWorkflowStage.setType(s.getType());
            apiDocumentWorkflowStage.setGroupId(apiTranslationGroupMember.getGroupMemberId());
            apiDocumentWorkflowStage.setBaseWorkflowId(s.getBaseWorkflowId());
            apiDocumentWorkflowStage.setCreateTime(new Date());
            apiDocumentWorkflowStage.setModifyTime(new Date());
            apiDocumentWorkflowStageList.add(apiDocumentWorkflowStage);
        });
        uploadDocumentRequest.setApiDocumentWorkflowStageList(apiDocumentWorkflowStageList);
        uploadDocumentRequests.add(uploadDocumentRequest);
        List<ApiTranslationDocumentWithSettings> response = client.getDocumentApi()
                .createDocument(apiTranslationProject.getProjectId(), uploadDocumentRequests);
        // 解析完成才返回
        while (true) {
            ApiTranslationDocumentWithSettings result = client.getDocumentApi().getDocument(response.get(0).getProjectId(),
                    response.get(0).getDocumentId());
            if (result.getStatus() != 1) {
                return response;
            }
        }
    }

    /**
     * 分配一个任务
     *
     * @param apiTranslationDocumentWithSettingsList
     * @return
     */
    public static List<String> createTask(Client client, List<ApiTranslationDocumentWithSettings> apiTranslationDocumentWithSettingsList) {
        ApiTranslationGroupMember apiTranslationGroupMember = client.getMemberAPI().getTranslationGroupMember().get(0);
        ApiTranslationTaskRequest apiTranslationTaskRequest = new ApiTranslationTaskRequest();
        apiTranslationTaskRequest.setDocumentId(apiTranslationDocumentWithSettingsList.get(0).getDocumentId());
        apiTranslationTaskRequest.setStage(1);
        apiTranslationTaskRequest.setStageType((byte) 2);
        apiTranslationTaskRequest.setStageName("阶段一");
        apiTranslationTaskRequest.setTaskUserId(apiTranslationGroupMember.getGroupMemberId());
        apiTranslationTaskRequest.setTaskUserName(apiTranslationGroupMember.getGroupMemberName());
        apiTranslationTaskRequest.setUnitPrice(100L);
        apiTranslationTaskRequest.setNotes(apiTranslationDocumentWithSettingsList.get(0).getNotes());
        apiTranslationTaskRequest.setSegmentRangeStart(0.0);
        apiTranslationTaskRequest.setSegmentRangeEnd(100.0);
        Date now = new Date();
        apiTranslationTaskRequest.setDeadline(new Date(now.getTime() + (365 * 24 * 60 * 60 * 1000)));
        List<ApiTranslationTaskRequest> apiTranslationTaskRequestList = new ArrayList<>();
        apiTranslationTaskRequestList.add(apiTranslationTaskRequest);
        List<String> taskIds = client.getTaskApi().createTask(apiTranslationTaskRequestList);
        System.out.println("分配一个任务给" + apiTranslationGroupMember.getGroupMemberName());
        return taskIds;
    }

}
