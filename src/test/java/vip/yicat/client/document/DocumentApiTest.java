package vip.yicat.client.document;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vip.yicat.client.Client;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.document.model.*;
import vip.yicat.client.project.model.ApiProjectWorkflowStage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DocumentApiTest {
    Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    Client client = new Client(credentials);
    String projectId = "2023032115450943900406279049";
    String documentId = client.getDocumentApi().getDocuments(projectId).get(0).getDocumentId();

    @Test
    @Order(1)
    void getDocuments() {
        List<ApiTranslationDocumentWithSettings> apiTranslationDocumentWithSettingsList = client.getDocumentApi().getDocuments(projectId);
        System.out.println(apiTranslationDocumentWithSettingsList.get(0));
        assertNotNull(apiTranslationDocumentWithSettingsList);
    }

    @Test
    @Order(2)
    void getDocument() {
        ApiTranslationDocumentWithSettings apiTranslationDocumentWithSettings = client.getDocumentApi().getDocument(projectId, documentId);
        System.out.println(apiTranslationDocumentWithSettings);
        assertNotNull(apiTranslationDocumentWithSettings);
    }

    @Test
    @Order(3)
    void uploadFile() {
        File file = new File("C:\\Users\\86195\\Desktop\\sdk\\范文.docx");
        List<ApiUploadFileInfo> apiUploadFileInfos = client.getDocumentApi().uploadFile(file);
        System.out.println(apiUploadFileInfos);
        assertNotNull(apiUploadFileInfos);
    }

    @Test
    @Order(3)
    void exportDocuments() {
        List list = new ArrayList<>();
        list.add(documentId);
        System.out.println(documentId);
        String fileId = client.getDocumentApi().exportDocuments(list, 1, projectId);
        System.out.println(fileId);
        assertNotNull(fileId);
    }

    @Test
    @Order(4)
    void createDocument() {
        File file = new File("C:\\Users\\86195\\Desktop\\sdk\\范文.docx");
        ApiUploadFileInfo apiUploadFileInfo = client.getDocumentApi().uploadFile(file).get(0);
        System.out.println(apiUploadFileInfo);
        List<UploadDocumentRequest> uploadDocumentRequests = new ArrayList<>();

        UploadDocumentRequest uploadDocumentRequest = new UploadDocumentRequest();
        uploadDocumentRequest.setFileId(apiUploadFileInfo.getFileId());
        uploadDocumentRequest.setSrcLan(client.getProjectApi().getProject(projectId).getSrcLan());
        uploadDocumentRequest.setFileName(apiUploadFileInfo.getFileName());
        uploadDocumentRequest.setParentPath(apiUploadFileInfo.getParentPath());
        uploadDocumentRequest.setFileSize(apiUploadFileInfo.getFileSize());
        uploadDocumentRequest.setTgtLanList(client.getProjectApi().getProject(projectId).getTgtLanList());

        List<ApiProjectWorkflowStage> apiProjectWorkflowStageList = client.getProjectApi().getProject(projectId).getWorkflowStageList();
        List<ApiDocumentWorkflowStage> apiDocumentWorkflowStageList = new ArrayList<>();
        apiProjectWorkflowStageList.stream().forEach(s -> {
            ApiDocumentWorkflowStage apiDocumentWorkflowStage = new ApiDocumentWorkflowStage();
            apiDocumentWorkflowStage.setStage(s.getStage());
            apiDocumentWorkflowStage.setName(s.getName());
            apiDocumentWorkflowStage.setType(s.getType());
            apiDocumentWorkflowStage.setGroupId(client.getMemberAPI().getTranslationGroupMember().get(0).getGroupMemberId());
            apiDocumentWorkflowStage.setBaseWorkflowId(s.getBaseWorkflowId());
            apiDocumentWorkflowStage.setCreateTime(new Date());
            apiDocumentWorkflowStage.setModifyTime(new Date());
            apiDocumentWorkflowStageList.add(apiDocumentWorkflowStage);
        });
        uploadDocumentRequest.setApiDocumentWorkflowStageList(apiDocumentWorkflowStageList);
        uploadDocumentRequests.add(uploadDocumentRequest);
        List<ApiTranslationDocumentWithSettings> apiTranslationDocumentWithSettingsList = client.getDocumentApi().createDocument(projectId, uploadDocumentRequests);
        System.out.println(apiTranslationDocumentWithSettingsList);
        assertNotNull(apiTranslationDocumentWithSettingsList);
    }

    @Test
    @Order(5)
    void uploadOfflineFile() {
        File file = new File("C:\\Users\\86195\\Desktop\\sdk\\范文_OfflineTrans.docx");
        client.getDocumentApi().uploadOfflineFile(file, "91adc38ebe0648498847a7066bc5b507_en-US", "2023032115450943900406279049");
    }

    @Test
    @Order(6)
    void exportDocumentByWorkflow() {
        ExportDocumentRequest exportDocumentRequest = new ExportDocumentRequest();
        List<String> documentIdList = new ArrayList<>();
        documentIdList.add(documentId);
        exportDocumentRequest.setDocumentIdList(documentIdList);

        List<Integer> stageList = new ArrayList<>();
        stageList.add(1);
        exportDocumentRequest.setStageList(stageList);

        List<Integer> downloadTypeList = new ArrayList<>();
        downloadTypeList.add(1);
        exportDocumentRequest.setDownloadTypeList(downloadTypeList);
        String fileId = client.getDocumentApi().exportDocumentByWorkflow(projectId, exportDocumentRequest);
        System.out.println(fileId);
        assertNotNull(fileId);
    }

    @Test
    @Order(7)
    void updateDocument() {
        File file = new File("C:\\Users\\86195\\Desktop\\sdk\\范文.docx");
        List<ApiUploadFileInfo> apiUploadFileInfos = client.getDocumentApi().uploadFile(file);
        System.out.println(apiUploadFileInfos);
        CoverDocumentRequest coverDocumentRequest = new CoverDocumentRequest();
        coverDocumentRequest.setFileId(apiUploadFileInfos.get(0).getFileId());
        coverDocumentRequest.setFileName(apiUploadFileInfos.get(0).getFileName());
        coverDocumentRequest.setFileSize(apiUploadFileInfos.get(0).getFileSize());
        client.getDocumentApi().updateDocument(projectId, coverDocumentRequest, documentId);
    }

    @Test
    @Order(8)
    void downFile() throws IOException {
        String fileId = "YWNmOWQ4YzE1OGRlNGU2YzhiMGVlMjU5M2VlOTE3NGE=%0A";
        String isIe = "no";
        InputStream inputStream = client.getDocumentApi().downFile(fileId, isIe);
        FileOutputStream outputStream = new FileOutputStream("e:\\output.docx");
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.close();
    }

    @Test
    @Order(9)
    void deleteDocuments() {
        List list = new ArrayList<>();
        list.add(documentId);
        client.getDocumentApi().deleteDocuments(projectId, list);
    }
}