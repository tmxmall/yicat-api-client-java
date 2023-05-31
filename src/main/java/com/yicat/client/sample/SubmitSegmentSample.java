package com.yicat.client.sample;

import com.yicat.client.Client;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.document.model.*;
import com.yicat.client.project.model.ApiTranslationProject;
import com.yicat.client.segment.model.*;

import java.io.File;
import java.util.*;

import static com.yicat.client.sample.CommonMethod.*;

/**
 * 项目-文件-分任务-确认句段（文档维度）-删除项目
 */
public class SubmitSegmentSample {
    private static Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    private static Client client = new Client(credentials);

    public static void main(String[] args) {
        //创建项目
        ApiTranslationProject apiTranslationProject = createProject(client, "project01", "zh-Hans", Arrays.asList("en-US", "ja-JP"), "note01");
        //上传文件
        File uploadFile = new File("C:\\Users\\86195\\Desktop\\sdk\\范文.docx");
        ApiUploadFileInfo apiUploadFileInfo = client.getDocumentApi().uploadFile(uploadFile).get(0);
        System.out.println("上传文件成功:" + apiUploadFileInfo);
        //添加文档到项目中
        List<ApiTranslationDocumentWithSettings> apiTranslationDocumentWithSettingsList = createDocument(client, apiTranslationProject, apiUploadFileInfo);
        System.out.println("添加文档成功:" + apiTranslationDocumentWithSettingsList);
        //分配任务
        createTask(client, apiTranslationDocumentWithSettingsList);
        //确认单个句段（文档维度）
        submitSegment(apiTranslationDocumentWithSettingsList);
        //删除项目
        List<String> projectIdList = new ArrayList<>();
        projectIdList.add(apiTranslationProject.getProjectId());
        client.getProjectApi().deleteProjects(projectIdList);
        System.out.println("删除项目,projectIdList=" + projectIdList);
    }

    public static void submitSegment(List<ApiTranslationDocumentWithSettings> apiTranslationDocumentWithSettingsList) {
        String documentId = apiTranslationDocumentWithSettingsList.get(0).getDocumentId();
        List<ApiSegment> apiSegments = client.getSegmentApi().loadSegmentByDocumentId(documentId, "");
        String segmentId = apiSegments.get(0).get_id();
        ApiSegmentEditMessage apiSegmentEditMessage = buildApiSegmentEditMessage(documentId, segmentId);
        apiSegmentEditMessage.getOpSegment().set_id(segmentId);
        List<ApiSegmentEditMessage> apiSegmentEditMessageList = new ArrayList<>();
        apiSegmentEditMessageList.add(apiSegmentEditMessage);
        client.getSegmentApi().submitEditMessageByDocument(documentId, apiSegmentEditMessageList);
        System.out.println("确认单个句段,documentId=" + documentId + ",segmentId=" + segmentId);
    }

    public static ApiSegmentEditMessage buildApiSegmentEditMessage(String documentId, String segmentId) {
        ApiSegmentEditMessage apiSegmentEditMessage = new ApiSegmentEditMessage();
        apiSegmentEditMessage.setOpType("commit");
        ApiSegment apiSegment = client.getSegmentApi().loadSegmentByDocumentId(documentId, segmentId).get(0);
        apiSegment.getSrcSegmentAtoms().get(0).setData("update this SrcSegmentAtoms success！");
        List<ApiAtom> tgtSegmentAtoms = new ArrayList<>();
        ApiAtom apiAtom = new ApiAtom();
        apiAtom.setData("update this Atom success！");
        apiSegment.setTgtSegmentAtoms(tgtSegmentAtoms);
        tgtSegmentAtoms.add(apiAtom);
        apiSegmentEditMessage.setOpSegment(apiSegment);
        apiSegmentEditMessage.setSrcLan("en");
        apiSegmentEditMessage.setTgtLan("fr");
        apiSegmentEditMessage.setApiQARuleList(buildApiQARule());
        apiSegmentEditMessage.setStage(1);
        apiSegmentEditMessage.setSrcChanged(true);
        apiSegmentEditMessage.setEditSaveType(2);
        return apiSegmentEditMessage;
    }

    public static List<ApiQARule> buildApiQARule() {
        ApiQARule rule = new ApiQARule();
        rule.setRuleId(1);
        rule.setRuleLevel(2);
        rule.setRuleDescription("This is rule description");
        List<ApiQARule> apiQARuleList = new ArrayList<>();
        apiQARuleList.add(rule);
        return apiQARuleList;
    }

}
