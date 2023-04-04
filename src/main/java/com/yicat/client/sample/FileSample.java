package com.yicat.client.sample;

import com.yicat.client.Client;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.document.model.*;
import com.yicat.client.project.model.ApiTranslationProject;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.yicat.client.sample.CommonMethod.createProject;
import static com.yicat.client.sample.CommonMethod.createDocument;

/**
 * 文件相关操作
 */
public class FileSample {
    private static Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    private static Client client = new Client(credentials);

    public static void main(String[] args) {
        //创建项目
        ApiTranslationProject apiTranslationProject = createProject("project01", "zh-Hans",  Arrays.asList("en-US"), "笔记01");
        //上传文件
        ApiUploadFileInfo apiUploadFileInfo = client.getDocumentApi().uploadFile(new File("C:\\Users\\86195\\Desktop\\sdk\\范文.docx")).get(0);
        System.out.println("上传文件成功：" + apiUploadFileInfo);
        //添加文档到项目中
        List<ApiTranslationDocumentWithSettings> apiTranslationDocumentWithSettingsList = createDocument(apiTranslationProject, apiUploadFileInfo);
        //获取文档列表
        List<ApiTranslationDocumentWithSettings> documents = client.getDocumentApi().getDocuments(apiTranslationProject.getProjectId());
        System.out.println("获取文档列表：" + documents);
        //根据projectId和documentId获取文档信息
        String documentId = apiTranslationDocumentWithSettingsList.get(0).getDocumentId();
        ApiTranslationDocumentWithSettings apiTranslationDocumentWithSettings = client.getDocumentApi().getDocument(apiTranslationProject.getProjectId(), documentId);
        System.out.println("获取文档信息：" + apiTranslationDocumentWithSettings);
        //根据工作流导出文档
        List<String> documentIdList = apiTranslationDocumentWithSettingsList.stream().map(s -> s.getDocumentId()).collect(Collectors.toList());
        String fileId = client.getDocumentApi().exportDocumentByWorkflow(apiTranslationProject.getProjectId(), getExportDocumentRequest(documentIdList));
        System.out.println("根据工作流导出文档,fileId=" + fileId);
        //根据下载类型导出文档
        fileId = client.getDocumentApi().exportDocuments(documentIdList, 1, apiTranslationProject.getProjectId());
        System.out.println("根据下载类型导出文档,fileId=" + fileId);
        //下载文件
        InputStream inputStream = client.getDocumentApi().downFile(fileId, "no");
        System.out.println("下载文件成功！");
        //上传文件
        List<ApiUploadFileInfo> apiUploadFileInfos = client.getDocumentApi().uploadFile(new File("C:\\Users\\86195\\Desktop\\sdk\\范文.docx"));
        System.out.println("上传文件,apiUploadFileInfos=" + apiUploadFileInfos);
        //更新原文
        client.getDocumentApi().updateDocument(apiTranslationProject.getProjectId(), getCoverDocumentRequest(apiUploadFileInfos.get(0)), documentId);
        System.out.println("更新原文成功！");
        //删除文档
        client.getDocumentApi().deleteDocuments(apiTranslationProject.getProjectId(), documentIdList);
        System.out.println("删除文档成功！");
        //删除项目
        List projectIdList = new ArrayList<>();
        projectIdList.add(apiTranslationProject.getProjectId());
        client.getProjectApi().deleteProjects(projectIdList);
        System.out.println("删除项目，projectIdList=" + projectIdList);
    }

    public static ExportDocumentRequest getExportDocumentRequest(List<String> documentIdList) {
        ExportDocumentRequest exportDocumentRequest = new ExportDocumentRequest();
        exportDocumentRequest.setDocumentIdList(documentIdList);
        List<Integer> stageList = new ArrayList<>();
        stageList.add(1);
        exportDocumentRequest.setStageList(stageList);
        List<Integer> downloadTypeList = new ArrayList<>();
        downloadTypeList.add(1);
        exportDocumentRequest.setDownloadTypeList(downloadTypeList);
        return exportDocumentRequest;
    }

    public static CoverDocumentRequest getCoverDocumentRequest(ApiUploadFileInfo apiUploadFileInfo) {
        CoverDocumentRequest coverDocumentRequest = new CoverDocumentRequest();
        coverDocumentRequest.setFileId(apiUploadFileInfo.getFileId());
        coverDocumentRequest.setFileName(apiUploadFileInfo.getFileName());
        coverDocumentRequest.setFileSize(apiUploadFileInfo.getFileSize());
        return coverDocumentRequest;
    }
}
