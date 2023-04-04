package com.yicat.client.translationmemory;

import com.yicat.client.Client;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.translationmemory.model.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TranslationMemoryApiTest {
    Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    Client client = new Client(credentials);
    String tmId = client.getTranslationMemoryApi().listTMInfo().get(0).getTmId();

    @Test
    @Order(1)
    void testListTMInfo() {
        List<ApiYiCATTMInfo> apiYiCATTMInfos = client.getTranslationMemoryApi().listTMInfo();
        System.out.println(apiYiCATTMInfos);
        assertNotNull(apiYiCATTMInfos);
    }

    @Test
    @Order(2)
    void testGetTMInfo() {
        ApiYiCATTMInfo tmInfo = client.getTranslationMemoryApi().getTMInfo(tmId);
        System.out.println(tmInfo);
        assertNotNull(tmInfo);
    }

    @Test
    @Order(3)
    void testCreateTM() {
        AddYiCATTMRequest addYiCATTMRequest = new AddYiCATTMRequest();
        addYiCATTMRequest.setTmName("This is TMName");
        addYiCATTMRequest.setSrcLang("en-US");
        List list = new ArrayList<>();
        list.add("zh-Hans");
        addYiCATTMRequest.setTgtLanList(list);
        addYiCATTMRequest.setTag("This is Tag");
        addYiCATTMRequest.setVisibilityLevel((byte) 1);
        ApiYiCATTMInfo apiYiCATTMInfo = client.getTranslationMemoryApi().createTM(addYiCATTMRequest);
        System.out.println(apiYiCATTMInfo);
        assertNotNull(apiYiCATTMInfo);
    }

    @Test
    @Order(4)
    void testCreateTMEntry() {
        List<BaseItem> list = new ArrayList<>();
        BaseItem baseItem = new BaseItem();
        baseItem.setLang("en-US");
        baseItem.setText("This is text");
        list.add(baseItem);

        BaseItem baseItem2 = new BaseItem();
        baseItem2.setLang("zh-Hans");
        baseItem2.setText("这是一段文本");

        list.add(baseItem2);
        String entryId = client.getTranslationMemoryApi().createTMEntry(tmId, list);
        System.out.println(entryId);
        assertNotNull(entryId);
    }

    @Test
    @Order(5)
    void testSearchTMEntry() {
        String text = "这是一段文本";
        Boolean caseSensitive = false;
        List<ApiYiCATTMEntryInfo> apiYiCATTMEntryInfos = client.getTranslationMemoryApi().searchTMEntry(tmId, text, caseSensitive);
        System.out.println(apiYiCATTMEntryInfos);
        assertNotNull(apiYiCATTMEntryInfos);
    }

    @Test
    @Order(6)
    void testImportTMXFile() {
        File file = new File("C:\\Users\\86195\\Desktop\\sdk\\时政语料库.tmx");
        client.getTranslationMemoryApi().importTMXFile(tmId, file);
    }

    @Test
    @Order(7)
    void testExportTM() {
        int format = 2;
        boolean complete = false;
        String downloadFileId = client.getTranslationMemoryApi().exportTM("0c4435054e744c6ab3333858db77767a", format, complete);
        System.out.println(downloadFileId);
        assertNotNull(downloadFileId);
    }

    @Test
    @Order(8)
    void testMatchTMEntryByGroup() {
        String srcLang = "en-US";
        String tgtLang = "zh-CN";
        String preText = "";
        String text = "This is a text";
        String nextText = "";
        Integer minMatchRate = 30;
        List tmIdList = new ArrayList<>();
        tmIdList.add("36bfb4007c914b22afc7893fb8f49ca0");
        List<ApiMatchTMEntryInfo> apiMatchTMEntryInfos = client.getTranslationMemoryApi().matchTMEntryByGroup(srcLang, tgtLang, preText, text, nextText, minMatchRate, tmIdList);
        System.out.println(apiMatchTMEntryInfos);
        assertNotNull(apiMatchTMEntryInfos);
    }

    @Test
    @Order(9)
    void testUpdateTM() {
        PatchYiCATTMRequest patchYiCATTMRequest = new PatchYiCATTMRequest();
        patchYiCATTMRequest.setTag("set tag");
        patchYiCATTMRequest.setTmName("set TmName");
        ApiYiCATTMInfo apiYiCATTMInfo = client.getTranslationMemoryApi().updateTM(tmId, patchYiCATTMRequest);
        System.out.println(apiYiCATTMInfo);
        assertNotNull(apiYiCATTMInfo);
    }

    @Test
    @Order(10)
    void testSaveTMEntry() {
        String entryId = client.getTranslationMemoryApi().searchTMEntry(tmId, "这是一段文本", false).get(0).getEntryId();
        String lang = "zh-Hans";
        String text = "这是一段中文Entry";
        client.getTranslationMemoryApi().saveTMEntry(tmId, entryId, lang, text);
    }

    @Test
    @Order(11)
    void testDeleteTMEntry() {
        List entryIdList = new ArrayList<>();
        entryIdList.add(client.getTranslationMemoryApi().searchTMEntry(tmId, "这是一段文本", false).get(0).getEntryId());
        client.getTranslationMemoryApi().deleteTMEntry(tmId, entryIdList);
    }

    @Test
    @Order(12)
    void testDeleteTM() {
        List tmIds = new ArrayList<>();
        tmIds.add(tmId);
        client.getTranslationMemoryApi().deleteTM(tmIds);
    }
}