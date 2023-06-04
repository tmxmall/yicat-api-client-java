package vip.yicat.client.task;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vip.yicat.client.Client;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.document.model.ApiTranslationDocumentWithSettings;
import vip.yicat.client.document.model.ApiUploadFileInfo;
import vip.yicat.client.member.model.ApiTranslationGroupMember;
import vip.yicat.client.project.model.ApiTranslationProject;
import vip.yicat.client.task.model.ApiTaskStatistics;
import vip.yicat.client.task.model.ApiTranslationTask;
import vip.yicat.client.task.model.ApiTranslationTaskRequest;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static vip.yicat.client.sample.CommonMethod.createDocument;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskApiTest {
    Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    String projectId = "2023032115450943900406279049";
    String documentId = "1168d21d4b2045f08f2d3d894ccff738_en-US";
    Client client = new Client(credentials);
    String taskId = client.getTaskApi().getTaskInfoByProjectId(projectId).get(0).getTaskId();

    @Test
    @Order(1)
    void getTaskInfoByProjectId() {
        List<ApiTranslationTask> apiTranslationTasks = client.getTaskApi().getTaskInfoByProjectId(projectId);
        System.out.println(apiTranslationTasks);
        assertNotNull(apiTranslationTasks);
    }

    @Test
    @Order(6)
    void getTaskInfoByDocumentId() {
        List<ApiTranslationTask> apiTranslationTasks = client.getTaskApi().getTaskInfoByDocumentId(documentId);
        System.out.println(apiTranslationTasks);
        assertNotNull(apiTranslationTasks);
    }

    @Test
    @Order(3)
    void getTaskInfoByTaskId() {
        ApiTranslationTask apiTranslationTask = client.getTaskApi().getTaskInfoByTaskId(taskId);
        System.out.println(apiTranslationTask);
        assertNotNull(apiTranslationTask);
    }

    @Test
    @Order(4)
    void getTaskByGroupMembers() {
        List<String> userIdList = client.getMemberAPI().getTranslationGroupMember().stream().map(ApiTranslationGroupMember::getGroupMemberId).collect(Collectors.toList());
        List<ApiTranslationTask> apiTranslationTasks = client.getTaskApi().getTaskByGroupMembers(userIdList);
        System.out.println(apiTranslationTasks);
    }

    @Test
    @Order(5)
    void getTaskDetailsStatistics() {
        ApiTaskStatistics taskDetailsStatistics = client.getTaskApi().getTaskDetailsStatistics(taskId);
        System.out.println(taskDetailsStatistics);
        assertNotNull(taskDetailsStatistics);
    }

    @Test
    @Order(2)
    void createTask() {
        File file = new File("C:\\Users\\86195\\Desktop\\sdk\\范文.docx");
        List<ApiUploadFileInfo> apiUploadFileInfos = client.getDocumentApi().uploadFile(file);
        ApiTranslationProject apiTranslationProject = client.getProjectApi().getProject(projectId);
        List<ApiTranslationDocumentWithSettings> apiTranslationDocumentWithSettingsList = createDocument(client, apiTranslationProject, apiUploadFileInfos.get(0));
        ApiTranslationTaskRequest request = new ApiTranslationTaskRequest();
        String documentId = apiTranslationDocumentWithSettingsList.get(0).getDocumentId();
        request.setDocumentId(documentId);
        request.setStage(1);
        request.setStageType((byte) 2);
        request.setStageName("阶段01");
        List<ApiTranslationGroupMember> data = client.getMemberAPI().getTranslationGroupMember();
        request.setTaskUserId(data.get(0).getGroupMemberId());
        request.setTaskUserName(data.get(0).getGroupMemberName());
        request.setUnitPrice(100L);
        request.setNotes("notes");
        request.setSegmentRangeStart(0.0);
        request.setSegmentRangeEnd(100.0);
        Date now = new Date();
        request.setDeadline(new Date(now.getTime() + (365 * 24 * 60 * 60 * 1000)));
        List<ApiTranslationTaskRequest> apiTranslationTaskRequestList = new ArrayList<>();
        apiTranslationTaskRequestList.add(request);
        System.out.println(apiTranslationTaskRequestList);
        List<String> taskIdList = client.getTaskApi().createTask(apiTranslationTaskRequestList);
        System.out.println(taskIdList);
        // client.getDocumentApi().deleteDocuments(projectId, Arrays.asList(documentId));
        assertNotNull(taskIdList);
    }

    @Test
    @Order(7)
    void updateTaskInfo() {
        LocalDateTime futureDateTime = LocalDateTime.now().plusMonths(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String deadline = formatter.format(futureDateTime).replace(" ", "%20");
        String unitPrice = "80";
        Byte wordCountMode = 1;
        ApiTranslationTask apiTranslationTask = client.getTaskApi().updateTaskInfo(deadline, unitPrice, wordCountMode, taskId);
        System.out.println(apiTranslationTask);
        assertNotNull(apiTranslationTask);
    }

    @Test
    @Order(11)
    void taskScore() {
        Integer score = 8;
        String taskId = client.getTaskApi().getTaskInfoByProjectId(projectId).get(0).getTaskId();
        System.out.println(taskId);
        ApiTranslationTask apiTranslationTask = client.getTaskApi().taskScore(taskId, score);
        System.out.println(apiTranslationTask);
        assertNotNull(apiTranslationTask);
    }

    @Test
    @Order(9)
    void cancelTask() {
        ApiTranslationTask apiTranslationTask = client.getTaskApi().cancelTask(taskId);
        System.out.println(apiTranslationTask);
        assertNotNull(apiTranslationTask);
    }

    @Test
    @Order(10)
    void deleteTask() {
        String taskId = "2022031017460554900406279049";
        List list = new ArrayList<String>();
        list.add(taskId);
        client.getTaskApi().deleteTask(list);
    }

}