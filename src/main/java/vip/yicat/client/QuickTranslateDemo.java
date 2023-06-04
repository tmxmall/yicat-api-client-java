package vip.yicat.client;

import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.termbase.model.ApiYiCATTBInfo;
import vip.yicat.client.translationmemory.model.ApiYiCATTMInfo;
import vip.yicat.client.sample.QuickTransSample;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuickTranslateDemo {

    public static void main(String[] args) throws InterruptedException {
        // 请先生成token，并设置baseUrl，baseUrl为YiCAT服务的地址
        Credentials credentials = new Credentials("fc6a127932ac411fbe7534a11cfdf54c", "http://192.168.1.186");
        Client client = new Client(credentials);
        // 项目名称可能根据情况自己取名字
        String projectName = "project01";
        String srcLan = "zh-CN";
        // 快速翻译，只传一个目标语言
        List<String> tgtLanList = Arrays.asList("en-US");
        String mtProvider = "TranSmart";
        File file = new File("C:\\Users\\Desktop\\nature1-zh-少1.doc");
        // 获取所有的记忆库和术语库id，用于挂载，也可以不挂载。注意语言方向要匹配
        List<String> tmIds = client.getTranslationMemoryApi().listTMInfo().
                stream().map(ApiYiCATTMInfo::getTmId).collect(Collectors.toList());
        List<String> tbIds = client.getTermBaseApi().listTBInfo().
                stream().map(ApiYiCATTBInfo::getTbId).collect(Collectors.toList());
        //快速实现一个文件上传及翻译
        QuickTransSample.quickTrans(client, projectName, srcLan, tgtLanList, file, mtProvider, tmIds, tbIds);
    }
}
