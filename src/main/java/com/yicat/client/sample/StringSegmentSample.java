package com.yicat.client.sample;

import com.yicat.client.Client;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.document.model.ApiTranslationDocumentWithSettings;
import com.yicat.client.document.model.ApiUploadFileInfo;
import com.yicat.client.project.model.ApiTranslationProject;
import com.yicat.client.segment.model.AddStringSegmentRequest;
import com.yicat.client.segment.model.ApiStringsSegment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.yicat.client.sample.CommonMethod.createProject;
import static com.yicat.client.sample.CommonMethod.createDocument;

/**
 * String Segment
 */
public class StringSegmentSample {
    private static Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    private static Client client = new Client(credentials);

    public static void main(String[] args) {
        //创建项目
        ApiTranslationProject apiTranslationProject = createProject(client,"project01", "zh-Hans", Arrays.asList("en-US"), "笔记01");
        //上传文件
        File uploadFile = new File("C:\\Users\\86195\\Desktop\\sdk\\范文.docx");
        ApiUploadFileInfo apiUploadFileInfo = client.getDocumentApi().uploadFile(uploadFile).get(0);
        //添加文档到项目中
        createDocument(client, apiTranslationProject, apiUploadFileInfo);
        //添加字符串类型字段
        client.getStringsSegmentApi().addI18NSegmentInfo(apiTranslationProject.getProjectId(), getAddStringSegmentRequestList());
        System.out.println("为项目添加字段:" + getAddStringSegmentRequestList());
        //获取目标语言的字段信息
        List<ApiStringsSegment> apiStringsSegments = client.getStringsSegmentApi().getI18NSegmentInfo(apiTranslationProject.getProjectId(), "en-US");
        System.out.println("获取字段为: " + apiStringsSegments);
        //删除一个字段
        List<String> segmentIdList = new ArrayList<>();
        segmentIdList.add(apiStringsSegments.get(0).getSegmentId());
        client.getStringsSegmentApi().deleteI18NSegmentInfo(apiTranslationProject.getProjectId(), segmentIdList);
        System.out.println("删除的segmentId为: " + segmentIdList);
        //删除项目
        List projectIdList = new ArrayList<>();
        projectIdList.add(apiTranslationProject.getProjectId());
        client.getProjectApi().deleteProjects(projectIdList);
        System.out.println("删除的项目Id为: " + projectIdList);
    }

    public static List<AddStringSegmentRequest> getAddStringSegmentRequestList() {
        List<AddStringSegmentRequest> addStringSegmentRequestList = new ArrayList<>();
        AddStringSegmentRequest addStringSegmentRequest = new AddStringSegmentRequest();
        addStringSegmentRequest.setSegmentId("10001");
        addStringSegmentRequest.setLanguage("zh-Hans");
        addStringSegmentRequest.setNote("大约在2023年左右句段修改");
        addStringSegmentRequest.setSourceText("大约在2023年左右，新增句段");
        addStringSegmentRequestList.add(addStringSegmentRequest);
        return addStringSegmentRequestList;
    }
}
