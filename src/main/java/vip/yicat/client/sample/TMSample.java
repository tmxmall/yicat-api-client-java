package vip.yicat.client.sample;

import vip.yicat.client.Client;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.translationmemory.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 记忆库
 */
public class TMSample {
    private static Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    private static Client client = new Client(credentials);

    public static void main(String[] args) throws InterruptedException {
        //添加记忆库
        ApiYiCATTMInfo apiYiCATTMInfo = client.getTranslationMemoryApi().createTM(getAddYiCATTMRequest());
        System.out.println("添加记忆库:" + apiYiCATTMInfo);
        //根据tmId查看记忆库列表
        String tmId = apiYiCATTMInfo.getTmId();
        ApiYiCATTMInfo tmInfo = client.getTranslationMemoryApi().getTMInfo(tmId);
        System.out.println("根据tmId查看记忆库列表:" + tmInfo);
        //更新记忆库名称或标签
        apiYiCATTMInfo = client.getTranslationMemoryApi().updateTM(tmId, getPatchYiCATTMRequest());
        System.out.println("更新记忆库名称或标签:" + apiYiCATTMInfo);
        //导入记忆库文件（只支持tmx格式）
        client.getTranslationMemoryApi().importTMXFile(tmId, new File("C:\\Users\\86195\\Desktop\\sdk\\时政语料库.tmx"));
        while (client.getTranslationMemoryApi().getTMInfo(tmId).getState() == 1) {
            System.out.println("导入记忆库成功！");
            break;
        }
        //添加记忆库条目
        String entryId = client.getTranslationMemoryApi().createTMEntry(tmId, getBaseItemList());
        System.out.println("添加记忆库条目：:" + entryId);
        //搜索记忆库条目
        Thread.sleep(3000);
        List<ApiYiCATTMEntryInfo> apiYiCATTMEntryInfos = client.getTranslationMemoryApi().searchTMEntry(tmId, "The following is an excerpt from the interview:", false);
        System.out.println("搜索记忆库条目:" + apiYiCATTMEntryInfos);
        //保存记忆库条目
        entryId = apiYiCATTMEntryInfos.get(0).getEntryId();
        client.getTranslationMemoryApi().saveTMEntry(tmId, entryId, "zh-CN", "这是一段中文文本");
        System.out.println("保存记忆库条目：" + entryId);
        //匹配记忆库条目
        List<ApiMatchTMEntryInfo> apiMatchTMEntryInfos = client.getTranslationMemoryApi().matchTMEntryByGroup("en-US", "zh-CN", "", "The following is an excerpt from the interview:", "", 50, Arrays.asList(tmId));
        System.out.println("匹配记忆库条目:" + apiMatchTMEntryInfos);
        //删除记忆库条目
        client.getTranslationMemoryApi().deleteTMEntry(tmId, Arrays.asList(entryId));
        System.out.println("删除记忆库条目：" + entryId);
        //导出记忆库
        Thread.sleep(3000);
        String downloadFileId = client.getTranslationMemoryApi().exportTM(tmId, 2, false);
        System.out.println("导出记忆库,返回：" + downloadFileId);
        //删除记忆库
        client.getTranslationMemoryApi().deleteTM(Arrays.asList(tmId));
        System.out.println("删除记忆库：" + tmId);
        //获取记忆库列表
        List<ApiYiCATTMInfo> apiYiCATTMInfos = client.getTranslationMemoryApi().listTMInfo();
        System.out.println("获取记忆库列表:" + apiYiCATTMInfos);
    }

    public static AddYiCATTMRequest getAddYiCATTMRequest() {
        AddYiCATTMRequest addYiCATTMRequest = new AddYiCATTMRequest();
        addYiCATTMRequest.setTmName("Architecture");
        addYiCATTMRequest.setSrcLang("en-US");
        List list = new ArrayList<>();
        list.add("zh-Hans");
        addYiCATTMRequest.setTgtLanList(list);
        addYiCATTMRequest.setTag("This is a Tag");
        addYiCATTMRequest.setVisibilityLevel((byte) 1);
        return addYiCATTMRequest;
    }

    public static PatchYiCATTMRequest getPatchYiCATTMRequest() {
        PatchYiCATTMRequest patchYiCATTMRequest = new PatchYiCATTMRequest();
        patchYiCATTMRequest.setTag("建筑");
        patchYiCATTMRequest.setTmName("architecture");
        return patchYiCATTMRequest;
    }

    public static List<BaseItem> getBaseItemList() {
        List<BaseItem> baseItemList = new ArrayList<>();
        BaseItem baseItem = new BaseItem();
        baseItem.setLang("en-US");
        baseItem.setText("this is a reference");
        baseItemList.add(baseItem);
        BaseItem baseItem2 = new BaseItem();
        baseItem2.setLang("zh-CN");
        baseItem2.setText("这是一段参考");
        baseItemList.add(baseItem2);
        return baseItemList;
    }
}
