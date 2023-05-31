package com.yicat.client.sample;

import com.yicat.client.Client;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.project.model.*;
import com.yicat.client.segment.model.ApiQARule;
import com.yicat.client.termbase.model.ApiYiCATTBInfo;
import com.yicat.client.workflow.model.ApiWorkflowTemplate;

import java.util.*;

import static com.yicat.client.sample.CommonMethod.createProject;

/**
 * 项目相关设置
 */
public class ProjectSettingSample {
    private static Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    private static Client client = new Client(credentials);

    public static void main(String[] args) {
        //获取工作流模板列表
        List<ApiWorkflowTemplate> workflowTemplates = client.getWorkflowsApi().getWorkflowTemplate();
        System.out.println("获取工作流模板列表:" + workflowTemplates);
        //创建项目
        ApiTranslationProject apiTranslationProject = createProject(client,"project01", "zh-Hans", Arrays.asList("en-US"), "note01");
        //根据projectId获取指定项目
        ApiTranslationProject project = client.getProjectApi().getProject(apiTranslationProject.getProjectId());
        System.out.println("根据projectId获取指定项目:" + project);
        //获取项目列表
        PagingQueryResults<ApiTranslationProject> apiTranslationProjectPagingQueryResults = client.getProjectApi().listProject(1, 3);
        System.out.println("获取项目列表:" + apiTranslationProjectPagingQueryResults);
        //项目基本信息设置
        ApiTranslationProjectWithSettings apiTranslationProjectWithSettings = client.getProjectApi().updateProjectBasicInfo(apiTranslationProject.getProjectId(), getPatchProjectBasicInfoRequest());
        System.out.println("项目基本信息设置:" + apiTranslationProjectWithSettings);
        //项目挂载记忆库设置
        apiTranslationProjectWithSettings = client.getProjectApi().updateProjectMountTranslationMemory(apiTranslationProject.getProjectId(), getApiMountTranslationMemoryList());
        System.out.println("项目访问控制设置:" + apiTranslationProjectWithSettings);
        //更改项目挂载的术语库
        apiTranslationProjectWithSettings = client.getProjectApi().updateProjectMountTermBase(apiTranslationProject.getProjectId(), getApiMountTermBaseList());
        System.out.println("更改项目挂载的术语库:" + apiTranslationProjectWithSettings);
        //项目机器翻译引擎设置
        apiTranslationProjectWithSettings = client.getProjectApi().updateProjectMTProvider(apiTranslationProject.getProjectId(), "Google");
        System.out.println("项目机器翻译引擎设置:" + apiTranslationProjectWithSettings);
        //项目预翻译设置
        apiTranslationProjectWithSettings = client.getProjectApi().updateProjectPretransSettings(apiTranslationProject.getProjectId(), getPatchProjectPretransSettingsRequest());
        System.out.println("项目预翻译设置:" + apiTranslationProjectWithSettings);
        //更新项目重复与锁定设置
        apiTranslationProjectWithSettings = client.getProjectApi().updateProjectRepetitionSettings(apiTranslationProject.getProjectId(), PatchProjectRepetitionSettingsRequest());
        System.out.println("更新项目重复与锁定设置:" + apiTranslationProjectWithSettings);
        //更新项目文件解析器设置
        Map<String, Map<String, Object>> projectFileParserSettings = getProjectFileParserSettings();
        apiTranslationProjectWithSettings = client.getProjectApi().updateProjectFileParserSettings(apiTranslationProject.getProjectId(), projectFileParserSettings);
        System.out.println("项目文件解析器设置:" + apiTranslationProjectWithSettings);
        //项目QA规则设置
        apiTranslationProjectWithSettings = client.getProjectApi().updateProjectQARules(apiTranslationProject.getProjectId(), getApiQARuleList());
        System.out.println("项目QA规则设置:" + apiTranslationProjectWithSettings);
        //项目访问控制设置
        apiTranslationProjectWithSettings = client.getProjectApi().updateProjectAccessControlSettings(apiTranslationProject.getProjectId(), getPatchProjectAccessControlSettingsRequest());
        System.out.println("项目访问控制设置:" + apiTranslationProjectWithSettings);
        //更改项目价格计算设置
        apiTranslationProjectWithSettings = client.getProjectApi().updateProjectPriceCalcSettings(apiTranslationProject.getProjectId(), getPatchProjectPriceCalcSettingsRequest());
        System.out.println("项目价格计算设置:" + apiTranslationProjectWithSettings);
        //删除项目
        List projectIdList = new ArrayList<>();
        projectIdList.add(apiTranslationProject.getProjectId());
        client.getProjectApi().deleteProjects(projectIdList);
    }

    public static Map<String, Map<String, Object>> getProjectFileParserSettings() {
        Map<String, Map<String, Object>> projectFileParserSettings = new HashMap<>();
        Map<String, Object> idml = new HashMap<>();
        idml.put("transNotes", "false");
        idml.put("transHiddenLayers", "false");
        projectFileParserSettings.put(".idml", idml);
        Map<String, Object> dxf = new HashMap<>();
        dxf.put("transNumber", "true");
        projectFileParserSettings.put(".dxf", dxf);
        Map<String, Object> pdf = new HashMap<>();
        pdf.put("convertedFileType", ".docx");
        projectFileParserSettings.put(".pdf", pdf);
        Map<String, Object> md = new HashMap<>();
        md.put("translateUrls", "false");
        md.put("convertedFileType", ".docx");
        md.put("translateCodeBlocks", "true");
        projectFileParserSettings.put(".md", md);
        Map<String, Object> xlsx = new HashMap<>();
        xlsx.put("excludeColors", "false");
        xlsx.put("transTextBox", "false");
        xlsx.put("transSheetName", "false");
        xlsx.put("transComments", "true");
        xlsx.put("transHiddenRowsAndColumns", "false");
        xlsx.put("transDocProps", "false");
        projectFileParserSettings.put(".xlsx", xlsx);
        Map<String, Object> docx = new HashMap<>();
        docx.put("transHeadersAndFooters", "true");
        docx.put("cleanTag", "false");
        docx.put("transHiddenText", "true");
        docx.put("transComments", "true");
        docx.put("optimizeTag", "false");
        docx.put("transDocProps", "false");
        projectFileParserSettings.put(".docx", docx);
        Map<String, Object> mif = new HashMap<>();
        mif.put("transHiddenPage", "true");
        mif.put("transReferencePage", "false");
        mif.put("transMainPage", "true");
        mif.put("transIndexMarkers", "false");
        projectFileParserSettings.put(".mif", mif);
        Map<String, Object> pptx = new HashMap<>();
        pptx.put("transNotes", "true");
        pptx.put("transComments", "true");
        pptx.put("transDocProps", "true");
        projectFileParserSettings.put(".pptx", pptx);
        return projectFileParserSettings;
    }

    public static PatchProjectBasicInfoRequest getPatchProjectBasicInfoRequest() {
        PatchProjectBasicInfoRequest patchProjectBasicInfoRequest = new PatchProjectBasicInfoRequest();
        patchProjectBasicInfoRequest.setProjectName("testProject");
        patchProjectBasicInfoRequest.setFieldId(1);
        patchProjectBasicInfoRequest.setNotes("This is a note");
        patchProjectBasicInfoRequest.setDeadline(new Date());
        return patchProjectBasicInfoRequest;
    }

    public static List<ApiMountTranslationMemory> getApiMountTranslationMemoryList() {
        ApiMountTranslationMemory apiMountTranslationMemory = new ApiMountTranslationMemory();
        apiMountTranslationMemory.setTmId(client.getTranslationMemoryApi().listTMInfo().get(0).getTmId());
        apiMountTranslationMemory.setWriteEnabled(true);
        List<ApiMountTranslationMemory> apiMountTranslationMemoryList = new ArrayList<>();
        apiMountTranslationMemoryList.add(apiMountTranslationMemory);
        return apiMountTranslationMemoryList;
    }

    public static List<ApiMountTermBase> getApiMountTermBaseList() {
        List<ApiMountTermBase> apiMountTermBaseList = new ArrayList<>();
        ApiMountTermBase apiMountTermBase = new ApiMountTermBase();
        apiMountTermBase.setTbId(client.getTermBaseApi().listTBInfo().get(0).getTbId());
        apiMountTermBase.setQaEnabled(true);
        List<ApiMountTermBase> list = new ArrayList<>();
        list.add(apiMountTermBase);
        apiMountTermBaseList.add(apiMountTermBase);
        return apiMountTermBaseList;
    }

    public static PatchProjectPretransSettingsRequest getPatchProjectPretransSettingsRequest() {
        PatchProjectPretransSettingsRequest settings = new PatchProjectPretransSettingsRequest();
        settings.setEnabledTmPreTrans((byte) 1);
        settings.setEnabledMtPreTrans((byte) 1);
        settings.setPretransConfirm((byte) 1);
        settings.setMinMatchingRate(70);
        settings.setAutoTagging((byte) 1);
        settings.setTermInterference((byte) 1);
        settings.setAutoConfirmedUntranslatableElements((byte) 1);
        settings.setAutoConfirmed100MatchedRepeat((byte) 1);
        return settings;
    }

    public static PatchProjectRepetitionSettingsRequest PatchProjectRepetitionSettingsRequest() {
        PatchProjectRepetitionSettingsRequest patchProjectRepetitionSettingsRequest = new PatchProjectRepetitionSettingsRequest();
        patchProjectRepetitionSettingsRequest.setLockInnerRepeat((byte) 1);
        patchProjectRepetitionSettingsRequest.setLockOuterRepeat((byte) 0);
        patchProjectRepetitionSettingsRequest.setLock100MatchedRepeat((byte) 1);
        patchProjectRepetitionSettingsRequest.setLock101MatchedRepeat((byte) 0);
        patchProjectRepetitionSettingsRequest.setLock102MatchedRepeat((byte) 1);
        patchProjectRepetitionSettingsRequest.setEnabledRepetitionSynchronize((byte) 1);
        return patchProjectRepetitionSettingsRequest;
    }

    public static PatchProjectPriceCalcSettingsRequest getPatchProjectPriceCalcSettingsRequest() {
        PatchProjectPriceCalcSettingsRequest patchProjectPriceCalcSettingsRequest = new PatchProjectPriceCalcSettingsRequest();
        patchProjectPriceCalcSettingsRequest.setNewOff(10);
        patchProjectPriceCalcSettingsRequest.setTm5074Off(20);
        patchProjectPriceCalcSettingsRequest.setTm7584Off(30);
        patchProjectPriceCalcSettingsRequest.setTm8594Off(40);
        patchProjectPriceCalcSettingsRequest.setTm9599Off(50);
        patchProjectPriceCalcSettingsRequest.setTm100Off(60);
        patchProjectPriceCalcSettingsRequest.setTm101Off(70);
        patchProjectPriceCalcSettingsRequest.setTm102Off(80);
        patchProjectPriceCalcSettingsRequest.setInnerRepeatOff(90);
        patchProjectPriceCalcSettingsRequest.setOuterRepeatOff(100);
        patchProjectPriceCalcSettingsRequest.setMtOff(110);
        patchProjectPriceCalcSettingsRequest.setWordCountMode((byte) 1);
        return patchProjectPriceCalcSettingsRequest;
    }

    public static List<ApiQARule> getApiQARuleList() {
        ApiQARule apiQARule = new ApiQARule();
        apiQARule.setRuleId(1);
        apiQARule.setRuleLevel(2);
        apiQARule.setRuleDescription("This is a description");
        List<ApiQARule> apiQARuleList = new ArrayList<>();
        apiQARuleList.add(apiQARule);
        return apiQARuleList;
    }

    public static PatchProjectAccessControlSettingsRequest getPatchProjectAccessControlSettingsRequest() {
        PatchProjectAccessControlSettingsRequest patchProjectAccessControlSettingsRequest = new PatchProjectAccessControlSettingsRequest();
        patchProjectAccessControlSettingsRequest.setEnableTranslatorExport((byte) 1);
        patchProjectAccessControlSettingsRequest.setEnableMemberSearchTermTm((byte) 0);
        patchProjectAccessControlSettingsRequest.setLangAssetsMatchDesensitizationLevel(2);
        return patchProjectAccessControlSettingsRequest;
    }
}
