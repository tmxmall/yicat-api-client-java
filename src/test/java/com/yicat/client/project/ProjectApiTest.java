package com.yicat.client.project;

import com.yicat.client.Client;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.project.model.*;
import com.yicat.client.segment.model.ApiQARule;
import com.yicat.client.termbase.model.ApiYiCATTBInfo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectApiTest {

    Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    Client client = new Client(credentials);
    String projectId = client.getProjectApi().listProject(1,1).getList().get(0).getProjectId();

    @Test
    @Order(1)
    void createProject() {
        AddTranslationProjectRequest addTranslationProjectRequest = new AddTranslationProjectRequest();
        addTranslationProjectRequest.setProjectName("project01");
        addTranslationProjectRequest.setSrcLan("zh-Hans");
        addTranslationProjectRequest.setTgtLanList(Arrays.asList("en-US"));
        addTranslationProjectRequest.setNotes("Create by open api");
        addTranslationProjectRequest.setDeadline(new Date(System.currentTimeMillis() + (365 * 24 * 60 * 60 * 1000)));
        addTranslationProjectRequest.setWorkflowTemplateId(client.getWorkflowsApi().getWorkflowTemplate().get(0).getTemplateId());
        addTranslationProjectRequest.setEnabledSimultaneousCollaboration((byte) 1);
        addTranslationProjectRequest.setProjectType(1);
        System.out.println(addTranslationProjectRequest);
        ApiTranslationProjectWithSettings project = client.getProjectApi().createProject(addTranslationProjectRequest);
        System.out.println(project);
        assertNotNull(project);
    }

    @Test
    @Order(2)
    void listProject() {
        PagingQueryResults<ApiTranslationProject> apiTranslationProjectPagingQueryResults = client.getProjectApi().listProject(1,1);
        System.out.println(apiTranslationProjectPagingQueryResults);
        assertNotNull(apiTranslationProjectPagingQueryResults);
    }

    @Test
    @Order(3)
    void getProject() {
        ApiTranslationProject project = client.getProjectApi().getProject(projectId);
        System.out.println(project);
        assertNotNull(project);
    }

    @Test
    @Order(4)
    void updateProjectBasicInfo() {
        PatchProjectBasicInfoRequest patchProjectBasicInfoRequest = new PatchProjectBasicInfoRequest();
        patchProjectBasicInfoRequest.setProjectName("This is test Project");
        patchProjectBasicInfoRequest.setFieldId(1);
        patchProjectBasicInfoRequest.setNotes("This is a test project");
        patchProjectBasicInfoRequest.setDeadline(new Date());

        ApiTranslationProjectWithSettings apiTranslationProjectWithSettings = client.getProjectApi().updateProjectBasicInfo(projectId, patchProjectBasicInfoRequest);
        System.out.println(apiTranslationProjectWithSettings);
        assertNotNull(apiTranslationProjectWithSettings);
    }

    @Test
    @Order(5)
    void updateProjectRepetitionSettings() {
        PatchProjectRepetitionSettingsRequest patchProjectRepetitionSettingsRequest = new PatchProjectRepetitionSettingsRequest();
        patchProjectRepetitionSettingsRequest.setLockInnerRepeat((byte) 1);
        patchProjectRepetitionSettingsRequest.setLockOuterRepeat((byte) 0);
        patchProjectRepetitionSettingsRequest.setLock100MatchedRepeat((byte) 1);
        patchProjectRepetitionSettingsRequest.setLock101MatchedRepeat((byte) 0);
        patchProjectRepetitionSettingsRequest.setLock102MatchedRepeat((byte) 1);
        patchProjectRepetitionSettingsRequest.setEnabledRepetitionSynchronize((byte) 1);
        ApiTranslationProjectWithSettings apiTranslationProjectWithSettings = client.getProjectApi().updateProjectRepetitionSettings(projectId, patchProjectRepetitionSettingsRequest);
        System.out.println(apiTranslationProjectWithSettings);
        assertNotNull(apiTranslationProjectWithSettings);
    }

    @Test
    @Order(6)
    void updateProjectQARules() {
        ApiQARule apiQARule = new ApiQARule();
        apiQARule.setRuleId(1);
        apiQARule.setRuleLevel(2);
        apiQARule.setRuleDescription("This is a test rule");
        List<ApiQARule> list = new ArrayList<>();
        list.add(apiQARule);
        ApiTranslationProjectWithSettings apiTranslationProjectWithSettings = client.getProjectApi().updateProjectQARules(projectId, list);
        System.out.println(apiTranslationProjectWithSettings);
        assertNotNull(apiTranslationProjectWithSettings);
    }

    @Test
    @Order(7)
    void updateProjectMountTranslationMemory() {
        ApiMountTranslationMemory apiMountTranslationMemory = new ApiMountTranslationMemory();
        apiMountTranslationMemory.setTmId(client.getTranslationMemoryApi().listTMInfo().get(0).getTmId());
        apiMountTranslationMemory.setWriteEnabled(true);
        List<ApiMountTranslationMemory> list = new ArrayList<>();
        list.add(apiMountTranslationMemory);
        ApiTranslationProjectWithSettings apiTranslationProjectWithSettings = client.getProjectApi().updateProjectMountTranslationMemory(projectId, list);
        System.out.println(apiTranslationProjectWithSettings);
        assertNotNull(apiTranslationProjectWithSettings);
    }

    @Test
    @Order(8)
    void updateProjectMountTermBase() {
        ApiMountTermBase apiMountTermBase = new ApiMountTermBase();
        apiMountTermBase.setTbId( client.getTermBaseApi().listTBInfo().get(0).getTbId());
        apiMountTermBase.setQaEnabled(true);
        List<ApiMountTermBase> list = new ArrayList<>();
        list.add(apiMountTermBase);
        ApiTranslationProjectWithSettings apiTranslationProjectWithSettings = client.getProjectApi().updateProjectMountTermBase(projectId, list);
        System.out.println(apiTranslationProjectWithSettings);
        assertNotNull(apiTranslationProjectWithSettings);
    }

    @Test
    @Order(9)
    void updateProjectMTProvider() {
        String mtProvider = "Google";
        ApiTranslationProjectWithSettings apiTranslationProjectWithSettings = client.getProjectApi().updateProjectMTProvider(projectId, mtProvider);
        System.out.println(apiTranslationProjectWithSettings);
        assertNotNull(apiTranslationProjectWithSettings);
    }

    @Test
    @Order(10)
    void updateProjectPretransSettings() {
        PatchProjectPretransSettingsRequest settings = new PatchProjectPretransSettingsRequest();
        settings.setEnabledTmPreTrans((byte) 1);
        settings.setEnabledMtPreTrans((byte) 1);
        settings.setPretransConfirm((byte) 1);
        settings.setMinMatchingRate(70);
        settings.setAutoTagging((byte) 1);
        settings.setTermInterference((byte) 1);
        settings.setAutoConfirmedUntranslatableElements((byte) 1);
        settings.setAutoConfirmed100MatchedRepeat((byte) 1);
        ApiTranslationProjectWithSettings apiTranslationProjectWithSettings = client.getProjectApi().updateProjectPretransSettings(projectId, settings);
        System.out.println(apiTranslationProjectWithSettings);
        assertNotNull(apiTranslationProjectWithSettings);
    }

    @Test
    @Order(11)
    void updateProjectPriceCalcSettings() {
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
        ApiTranslationProjectWithSettings apiTranslationProjectWithSettings = client.getProjectApi().updateProjectPriceCalcSettings(projectId, patchProjectPriceCalcSettingsRequest);
        System.out.println(apiTranslationProjectWithSettings);
        assertNotNull(apiTranslationProjectWithSettings);
    }

    @Test
    @Order(12)
    void updateProjectFileParserSettings() {
        Map<String, Map<String, Object>> fileFormats = new HashMap<>();
        Map<String, Object> idml = new HashMap<>();
        idml.put("transNotes", "false");
        idml.put("transHiddenLayers", "false");
        fileFormats.put(".idml", idml);
        Map<String, Object> dxf = new HashMap<>();
        dxf.put("transNumber", "true");
        fileFormats.put(".dxf", dxf);
        Map<String, Object> pdf = new HashMap<>();
        pdf.put("convertedFileType", ".docx");
        fileFormats.put(".pdf", pdf);
        Map<String, Object> md = new HashMap<>();
        md.put("translateUrls", "false");
        md.put("convertedFileType", ".docx");
        md.put("translateCodeBlocks", "true");
        fileFormats.put(".md", md);
        Map<String, Object> xlsx = new HashMap<>();
        xlsx.put("excludeColors", "false");
        xlsx.put("transTextBox", "false");
        xlsx.put("transSheetName", "false");
        xlsx.put("transComments", "true");
        xlsx.put("transHiddenRowsAndColumns", "false");
        xlsx.put("transDocProps", "false");
        fileFormats.put(".xlsx", xlsx);
        Map<String, Object> docx = new HashMap<>();
        docx.put("transHeadersAndFooters", "true");
        docx.put("cleanTag", "false");
        docx.put("transHiddenText", "true");
        docx.put("transComments", "true");
        docx.put("optimizeTag", "false");
        docx.put("transDocProps", "false");
        fileFormats.put(".docx", docx);
        Map<String, Object> mif = new HashMap<>();
        mif.put("transHiddenPage", "true");
        mif.put("transReferencePage", "false");
        mif.put("transMainPage", "true");
        mif.put("transIndexMarkers", "false");
        fileFormats.put(".mif", mif);
        Map<String, Object> pptx = new HashMap<>();
        pptx.put("transNotes", "true");
        pptx.put("transComments", "true");
        pptx.put("transDocProps", "true");
        fileFormats.put(".pptx", pptx);
        ApiTranslationProjectWithSettings apiTranslationProjectWithSettings = client.getProjectApi().updateProjectFileParserSettings(projectId, fileFormats);
        System.out.println(apiTranslationProjectWithSettings);
        assertNotNull(apiTranslationProjectWithSettings);
    }

    @Test
    @Order(13)
    void updateProjectAccessControlSettings() {
        PatchProjectAccessControlSettingsRequest request = new PatchProjectAccessControlSettingsRequest();
        request.setEnableTranslatorExport((byte) 1);
        request.setEnableMemberSearchTermTm((byte) 0);
        request.setLangAssetsMatchDesensitizationLevel(2);
        ApiTranslationProjectWithSettings apiTranslationProjectWithSettings = client.getProjectApi().updateProjectAccessControlSettings(projectId, request);
        System.out.println(apiTranslationProjectWithSettings);
        assertNotNull(apiTranslationProjectWithSettings);
    }

    @Test
    @Order(14)
    void deleteProjects() {
        String projectId = client.getProjectApi().listProject(1,1).getList().get(0).getProjectId();
        List projectIdList = new ArrayList<>();
        projectIdList.add(projectId);
        client.getProjectApi().deleteProjects(projectIdList);
    }
}