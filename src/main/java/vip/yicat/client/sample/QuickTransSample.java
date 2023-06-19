package vip.yicat.client.sample;

import vip.yicat.client.Client;
import vip.yicat.client.document.model.ApiTranslationDocumentWithSettings;
import vip.yicat.client.document.model.ApiUploadFileInfo;
import vip.yicat.client.project.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static vip.yicat.client.sample.CommonMethod.createDocument;
import static vip.yicat.client.sample.CommonMethod.createProject;

/**
 * 快速实现一个文件上传及翻译工具类
 */
public class QuickTransSample {
    /**
     * 快速实现一个文件上传及翻译
     *
     * @param client      Client对象
     * @param projectName 项目名称
     * @param srcLan      源语言方向
     * @param tgtLanList  目标语言方向
     * @param file        上传的文件
     * @param mtProvider  机器翻译引擎
     * @param tmIds       挂载记忆库id集合
     * @param tbIds       挂载术语库id集合
     */
    public static void quickTrans(Client client, String projectName, String srcLan, List<String> tgtLanList, File file, String mtProvider, List<String> tmIds, List<String> tbIds) throws InterruptedException {
        //创建项目
        ApiTranslationProject apiTranslationProject = createProject(client, projectName, srcLan, tgtLanList, "笔记01");
        //项目设置
        int minMatchingRate = 100;
        ApiTranslationProjectWithSettings apiTranslationProjectWithSettings;
        //记忆库和术语库可以根据实际情况挂载，也可以不挂载，注意语言方向要匹配
        if (tmIds != null) {
            apiTranslationProjectWithSettings = client.getProjectApi().updateProjectMountTranslationMemory(apiTranslationProject.getProjectId(), getMountTranslationMemoryList(tmIds));
            System.out.println("项目挂载记忆库设置:" + apiTranslationProjectWithSettings);
        }
        if (tbIds != null) {
            apiTranslationProjectWithSettings = client.getProjectApi().updateProjectMountTermBase(apiTranslationProject.getProjectId(), getMountTermBaseList(tbIds));
            System.out.println("项目挂载的术语库设置:" + apiTranslationProjectWithSettings);
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
        List<ApiTranslationDocumentWithSettings> apiTranslationDocumentWithSettingsList = createDocument(client, apiTranslationProject, apiUploadFileInfo);
        //循环查询文件的状态 如状态变为未开始，则下载文件
        while (true) {
            ApiTranslationDocumentWithSettings result = client.getDocumentApi().
                    getDocument(apiTranslationDocumentWithSettingsList.get(0).getProjectId(),
                            apiTranslationDocumentWithSettingsList.get(0).getDocumentId());
            System.out.println("文件状态:" + result.getStatus());
            // 状态status为2表示文档状态正常，preTransStatus为0表示预翻译完成
            if (result.getStatus() == 2 && result.getPreTransStatus() == 0) {
                //导出文件，导出前需要等待数据落盘
                Thread.sleep(1000 * 3);
                String fileId = client.getDocumentApi().exportDocuments(Arrays.asList(apiTranslationDocumentWithSettingsList.get(0).getDocumentId()),
                        1, apiTranslationProject.getProjectId());
                String isIe = "no";
                InputStream inputStream = client.getDocumentApi().downFile(fileId, isIe);
                // 将inputStream写入文件
                String docName = result.getDocumentName();
                try (OutputStream outputStream = new FileOutputStream("D:\\" + docName)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("下载文件成功!");
                break;
            }
            Thread.sleep(1000 * 3);
        }
        // 删除项目，根据实际情况决定是否删除
        client.getProjectApi().deleteProjects(Arrays.asList(apiTranslationProject.getProjectId()));
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
