package vip.yicat.client.termbase;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vip.yicat.client.Client;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.termbase.model.*;
import vip.yicat.client.translationmemory.model.ApiYiCATTMInfo;
import vip.yicat.client.translationmemory.model.BaseItem;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TermBaseApiTest {

    Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    Client client = new Client(credentials);
    String tbId = client.getTermBaseApi().listTBInfo().get(0).getTbId();

    @Test
    @Order(1)
    void listTBInfo() {
        List<ApiYiCATTBInfo> apiYiCATTBInfos = client.getTermBaseApi().listTBInfo();
        System.out.println(apiYiCATTBInfos);
        assertNotNull(apiYiCATTBInfos);
    }

    @Test
    @Order(3)
    void getTBInfo() {
        ApiYiCATTMInfo tbInfo = client.getTermBaseApi().getTBInfo(tbId);
        System.out.println(tbInfo);
        assertNotNull(tbInfo);
    }

    @Test
    @Order(2)
    void createTB() {
        AddYiCATTBRequest request = new AddYiCATTBRequest();
        request.setTbName("术语库01");
        request.setSrcLang("zh-Hans");
        request.setTgtLanList(Arrays.asList("en-US"));
        request.setTag("IT通信");
        request.setVisibilityLevel((byte) 1);
        ApiYiCATTMInfo apiYiCATTMInfo = client.getTermBaseApi().createTB(request);
        System.out.println(apiYiCATTMInfo);
        assertNotNull(apiYiCATTMInfo);
    }

    @Order(3)
    @Test
    void updateTB() {
        PatchYiCATTBRequest patchYiCATTBRequest = new PatchYiCATTBRequest();
        patchYiCATTBRequest.setTbName("术语库07");
        patchYiCATTBRequest.setTag("IT通信");
        ApiYiCATTMInfo apiYiCATTMInfo = client.getTermBaseApi().updateTB(tbId, patchYiCATTBRequest);
        System.out.println(apiYiCATTMInfo);
        assertNotNull(apiYiCATTMInfo);
    }

    @Order(4)
    @Test
    void importTB() {
        File file = new File("C:\\Users\\86195\\Desktop\\sdk\\术语\\导入术语文件.tbx");
        client.getTermBaseApi().importTB("71986900406279049", file, false);
    }

    @Test
    @Order(5)
    void searchTBEntry() {
        List<ApiYiCATTBEntryInfo> apiYiCATTBEntryInfos = client.getTermBaseApi().searchTBEntry("71986900406279049", "calm", false);
        System.out.println(apiYiCATTBEntryInfos);
        assertNotNull(apiYiCATTBEntryInfos);
    }

    @Test
    @Order(6)
    void createTBEntry() {
        BaseItem baseItemSrc = new BaseItem();
        baseItemSrc.setLang("zh-Hans");
        baseItemSrc.setText("government");
        BaseItem baseItemTag = new BaseItem();
        baseItemTag.setLang("en-US");
        baseItemTag.setText("政府的");
        List<BaseItem> baseItemList = new ArrayList<>();
        baseItemList.add(baseItemSrc);
        baseItemList.add(baseItemTag);
        String entryId = client.getTermBaseApi().createTBEntry(tbId, baseItemList);
        System.out.println(entryId);
        assertNotNull(entryId);
    }

    @Test
    @Order(7)
    void updateTBEntry() {
        String tbId = "71986900406279049";
        String entryId = "E3wxA4cBfLc99Jc4VrhC";
        ApiBaseTBItem item = new ApiBaseTBItem();
        item.setNote(null);
        item.setCreateUserId("900000001207");
        item.setCreateUserEmail("services@tmxmall.com");
        item.setModifyUserEmail("services@tmxmall.com");
        item.setCaseSensitive(false);
        item.setForbidden(false);
        item.setPartOfSpeech(null);
        item.setModifyUserId("900000001207");
        item.setTermType(null);
        item.setDescription(null);
        item.setCreateUserName("管理员");
        item.setSource(null);
        item.setModifyUserName("管理员");
        item.setText("新增术语条目-update");
        item.setLang("zh-CN");
        item.setPreferred(false);
        item.setCreateTime(new Date());
        item.setModifyTime(new Date());
        item.setStatus((byte) 0);
        client.getTermBaseApi().updateTBEntry(tbId, entryId, item);
    }

    @Test
    @Order(8)
    void matchTBEntryByGroup() {
        String srcLang = "zh-Hans";
        String tgtLang = "en-US";
        String text = "沉稳的";
        List<String> tbIdList = Arrays.asList("71986900406279049");
        List<ApiMatchTBEntryInfo> apiMatchTBEntryInfos = client.getTermBaseApi().matchTBEntryByGroup(srcLang, tgtLang, text, tbIdList);
        System.out.println(apiMatchTBEntryInfos);
        assertNotNull(apiMatchTBEntryInfos);
    }

    @Test
    @Order(9)
    void exportTB() {
        int format = 2;
        String downloadFileId = client.getTermBaseApi().exportTB("71986900406279049", format, true);
        System.out.println(downloadFileId);
        assertNotNull(downloadFileId);
    }

    @Test
    @Order(10)
    void deleteTBEntry() {
        List<String> entryIdList = Arrays.asList(entryId());
        client.getTermBaseApi().deleteTBEntry(tbId, entryIdList);
    }

    @Test
    @Order(11)
    void deleteTB() {
        List<String> tbIdList = Arrays.asList(tbId);
        client.getTermBaseApi().deleteTB(tbIdList);
    }

    public String entryId() {
        BaseItem baseItemSrc = new BaseItem();
        baseItemSrc.setLang("zh-Hans");
        baseItemSrc.setText("government");
        BaseItem baseItemTag = new BaseItem();
        baseItemTag.setLang("en-US");
        baseItemTag.setText("政府的");
        List<BaseItem> baseItemList = new ArrayList<>();
        baseItemList.add(baseItemSrc);
        baseItemList.add(baseItemTag);
        String entryId = client.getTermBaseApi().createTBEntry(tbId, baseItemList);
        return entryId;
    }
}