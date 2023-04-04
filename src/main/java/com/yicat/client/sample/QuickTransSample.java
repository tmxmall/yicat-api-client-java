package com.yicat.client.sample;

import com.yicat.client.Client;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.document.model.ApiTranslationDocumentWithSettings;
import com.yicat.client.document.model.ApiUploadFileInfo;
import com.yicat.client.project.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.yicat.client.sample.CommonMethod.createProject;
import static com.yicat.client.sample.CommonMethod.createDocument;

/**
 * 快速实现一个文件上传及翻译工具类
 */
public class QuickTransSample {
    private static Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    private static Client client = new Client(credentials);

    /**
     * 快速实现一个文件上传及翻译
     *
     * @param projectName 项目名称
     * @param srcLan      源语言方向
     * @param tgtLanList  目标语言方向
     * @param file        上传的文件
     * @param mtProvider  机器翻译引擎
     * @param tmIds       挂载记忆库id集合
     * @param tbIds       挂载术语库id集合
     */
    public static void quickTrans(String projectName, String srcLan, List<String> tgtLanList, File file, String mtProvider, List<String> tmIds, List<String> tbIds) {
        //创建项目
        ApiTranslationProject apiTranslationProject = createProject(projectName, srcLan, tgtLanList, "笔记01");
        //项目设置
        int minMatchingRate = 70;
        //项目挂载记忆库设置（tmIds不传则最低匹配率设置70，传则设置100%匹配）
        ApiTranslationProjectWithSettings apiTranslationProjectWithSettings;
        if (tmIds != null) {
            apiTranslationProjectWithSettings = client.getProjectApi().updateProjectMountTranslationMemory(apiTranslationProject.getProjectId(), getMountTranslationMemoryList(tmIds));
            System.out.println("项目挂载记忆库设置:" + apiTranslationProjectWithSettings);
            minMatchingRate = 100;
        }
        //项目挂载术语库设置（tbIds不传则最低匹配率设置70，传则设置100%匹配）
        if (tbIds != null) {
            apiTranslationProjectWithSettings = client.getProjectApi().updateProjectMountTermBase(apiTranslationProject.getProjectId(), getMountTermBaseList(tbIds));
            System.out.println("项目挂载的术语库设置:" + apiTranslationProjectWithSettings);
            minMatchingRate = 100;
        }
        //项目机器翻译引擎设置
        apiTranslationProjectWithSettings = client.getProjectApi().updateProjectMTProvider(apiTranslationProject.getProjectId(), mtProvider);
        System.out.println("项目机器翻译引擎设置:" + apiTranslationProjectWithSettings);
        //预翻译设置
        apiTranslationProjectWithSettings = client.getProjectApi().updateProjectPretransSettings(apiTranslationProject.getProjectId(), getPatchProjectPretransSettingsRequest(minMatchingRate));
        System.out.println("项目预翻译设置:" + apiTranslationProjectWithSettings);
        //上传文件
        ApiUploadFileInfo apiUploadFileInfo = client.getDocumentApi().uploadFile(file).get(0);
        //添加文档到项目中
        List<ApiTranslationDocumentWithSettings> apiTranslationDocumentWithSettingsList = createDocument(apiTranslationProject, apiUploadFileInfo);
        //循环查询文件的状态 如状态变为未开始，则下载文件
        while (true) {
            ApiTranslationDocumentWithSettings result = client.getDocumentApi().getDocument(apiTranslationDocumentWithSettingsList.get(0).getProjectId(), apiTranslationDocumentWithSettingsList.get(0).getDocumentId());
            if (result.getStatus() == 2) {
                //下载文件
                String fileId = apiTranslationDocumentWithSettingsList.get(0).getOriginalFileId();
                String isIe = "no";
                client.getDocumentApi().downFile(fileId, isIe);
                System.out.println("下载文件成功!");
                break;
            }
        }
    }

    public static List<ApiMountTranslationMemory> getMountTranslationMemoryList(List<String> tmIds) {
        List<ApiMountTranslationMemory> apiMountTranslationMemoryList = new ArrayList<>();
        for (String tmId : tmIds) {
            ApiMountTranslationMemory apiMountTranslationMemory = new ApiMountTranslationMemory();
            apiMountTranslationMemory.setTmId(tmId);
            apiMountTranslationMemoryList.add(apiMountTranslationMemory);
        }
        return apiMountTranslationMemoryList;
    }

    public static List<ApiMountTermBase> getMountTermBaseList(List<String> tbIds) {
        List<ApiMountTermBase> apiMountTermBaseList = new ArrayList<>();
        for (String TBId : tbIds) {
            ApiMountTermBase apiMountTermBase = new ApiMountTermBase();
            apiMountTermBase.setTbId(TBId);
            List<ApiMountTermBase> list = new ArrayList<>();
            list.add(apiMountTermBase);
            apiMountTermBaseList.add(apiMountTermBase);
        }
        return apiMountTermBaseList;
    }

    public static PatchProjectPretransSettingsRequest getPatchProjectPretransSettingsRequest(int minMatchingRate) {
        PatchProjectPretransSettingsRequest settings = new PatchProjectPretransSettingsRequest();
        settings.setEnabledTmPreTrans((byte) 1);
        settings.setEnabledMtPreTrans((byte) 1);
        settings.setMinMatchingRate(minMatchingRate);
        settings.setAutoTagging((byte) 1);
        settings.setTermInterference((byte) 1);
        return settings;
    }
}
