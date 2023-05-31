package com.yicat.client;

import com.yicat.client.core.model.Credentials;
import com.yicat.client.termbase.model.ApiYiCATTBInfo;
import com.yicat.client.translationmemory.model.ApiYiCATTMInfo;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.yicat.client.sample.QuickTransSample.quickTrans;

public class QuickTranslateDemo {

    public static void main(String[] args) throws InterruptedException {
        // 请先生成token，并设置baseUrl，baseUrl为YiCAT服务的地址
        Credentials credentials = new Credentials("fc6a127932ac401fbe7534a11cfdf54c", "http://192.168.60.186");
        Client client = new Client(credentials);
        String projectName = "project01";
        String srcLan = "zh-CN";
        // 快速翻译，只传一个目标语言
        List<String> tgtLanList = Arrays.asList("en-US");
        String mtProvider = "TranSmart";
        File file = new File("C:\\Users\\ChenJian\\Desktop\\misc\\对齐\\nature1-zh-少1.doc");
        // 获取所有的记忆库和术语库id，用于挂载，也可以不挂载。注意语言方向要匹配
        List<String> tmIds = client.getTranslationMemoryApi().listTMInfo().
                stream().map(ApiYiCATTMInfo::getTmId).collect(Collectors.toList());
        List<String> tbIds = client.getTermBaseApi().listTBInfo().
                stream().map(ApiYiCATTBInfo::getTbId).collect(Collectors.toList());
        //快速实现一个文件上传及翻译
        quickTrans(client, projectName, srcLan, tgtLanList, file, mtProvider, tmIds, tbIds);
    }
}
