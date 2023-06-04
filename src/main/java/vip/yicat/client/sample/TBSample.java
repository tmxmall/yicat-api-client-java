package vip.yicat.client.sample;

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

/**
 * 术语库
 */
public class TBSample {
    private static Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    private static Client client = new Client(credentials);

    public static void main(String[] args) throws InterruptedException {
        //添加一个术语库
        ApiYiCATTMInfo apiYiCATTMInfo = client.getTermBaseApi().createTB(getAddYiCATTBRequest());
        System.out.println("添加一个术语库:" + apiYiCATTMInfo);
        //查询术语库列表
        List<ApiYiCATTBInfo> apiYiCATTBInfos = client.getTermBaseApi().listTBInfo();
        System.out.println("查询术语库列表:" + apiYiCATTBInfos);
        //根据TBId查询单个术语库信息
        String tbId = apiYiCATTBInfos.get(0).getTbId();
        apiYiCATTMInfo = client.getTermBaseApi().getTBInfo(tbId);
        System.out.println("根据TBId查询单个术语库信息:" + apiYiCATTMInfo);
        //为指定的术语库导入术语
        client.getTermBaseApi().importTB(tbId, new File("C:\\Users\\86195\\Desktop\\sdk\\术语\\术语库01.tbx"), false);
        while (client.getTermBaseApi().getTBInfo(tbId).getState() == 1) {
            System.out.println("术语库导入术语成功！");
            break;
        }
        //为指定的术语库添加条目
        String entryId = client.getTermBaseApi().createTBEntry(tbId, getBaseItemList());
        System.out.println("为指定的术语库添加条目：" + entryId);
        //搜索术语库条目
        Thread.sleep(3000);
        List<ApiYiCATTBEntryInfo> apiYiCATTBEntryInfos = client.getTermBaseApi().searchTBEntry(tbId, "government", false);
        System.out.println("搜索术语库条目:" + apiYiCATTBEntryInfos);
        //更新术语库条目信息
        entryId = apiYiCATTBEntryInfos.get(0).getEntryId();
        client.getTermBaseApi().updateTBEntry(tbId, entryId, buildApiBaseTBItem());
        System.out.println("更新术语库tbId为" + tbId + "、entryId为" + entryId + "的条目信息");
        //匹配术语库条目
        String srcLang = "en-US";
        String tgtLang = "zh-Hans";
        String text = "government";
        List<String> tbIdList = Arrays.asList(tbId);
        List<ApiMatchTBEntryInfo> apiMatchTBEntryInfos = client.getTermBaseApi().matchTBEntryByGroup(srcLang, tgtLang, text, tbIdList);
        System.out.println("匹配术语库条目," + apiMatchTBEntryInfos);
    }

    public static ApiBaseTBItem buildApiBaseTBItem() {
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
        return item;
    }

    public static AddYiCATTBRequest getAddYiCATTBRequest() {
        AddYiCATTBRequest request = new AddYiCATTBRequest();
        request.setTbName("术语库01");
        request.setSrcLang("en-US");
        request.setTgtLanList(Arrays.asList("zh-Hans"));
        request.setTag("科技");
        request.setVisibilityLevel((byte) 1);
        return request;
    }

    public static List<BaseItem> getBaseItemList() {
        BaseItem baseItemSrc = new BaseItem();
        baseItemSrc.setLang("en-US");
        baseItemSrc.setText("government");
        BaseItem baseItemTag = new BaseItem();
        baseItemTag.setLang("zh-Hans");
        baseItemTag.setText("政府的");
        List<BaseItem> baseItemList = new ArrayList<>();
        baseItemList.add(baseItemSrc);
        baseItemList.add(baseItemTag);
        return baseItemList;
    }
}
