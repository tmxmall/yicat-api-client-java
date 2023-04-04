package com.yicat.client;

import com.yicat.client.core.model.Credentials;
import com.yicat.client.termbase.model.ApiYiCATTBInfo;
import com.yicat.client.translationmemory.model.ApiYiCATTMInfo;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.yicat.client.sample.QuickTransSample.quickTrans;

public class Sandbox {

    public static void main(String[] args) {
        Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
        Client client = new Client(credentials);
        String projectName = "project01", srcLan = "zh-Hans", mtProvider = "Google";
        File file = new File("C:\\Users\\86195\\Desktop\\sdk\\范文.docx");
        List<String> tgtLanList = Arrays.asList("en-US");
        List<String> tmIds = client.getTranslationMemoryApi().listTMInfo().
                stream().map(ApiYiCATTMInfo::getTmId).collect(Collectors.toList());
        List<String> tbIds = client.getTermBaseApi().listTBInfo().
                stream().map(ApiYiCATTBInfo::getTbId).collect(Collectors.toList());
        //快速实现一个文件上传及翻译
        quickTrans(projectName, srcLan, tgtLanList, file, mtProvider, tmIds, tbIds);
    }
}
