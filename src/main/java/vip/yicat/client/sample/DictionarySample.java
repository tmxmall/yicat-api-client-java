package vip.yicat.client.sample;

import vip.yicat.client.Client;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.dictionary.model.AddTranslationDictionaryEntryRequest;
import vip.yicat.client.dictionary.model.ApiTranslationDictionary;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 词典相关操作
 */
public class DictionarySample {
    static Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    static Client client = new Client(credentials);

    public static void main(String[] args) {
        //创建一个词典
        ApiTranslationDictionary apiTranslationDictionary = client.getDictionaryApi().createDictionary("en-US", "词典01");
        System.out.println("创建一个词典:" + apiTranslationDictionary);
        //根据词典Id查看词典详细信息
        String dictionaryId = apiTranslationDictionary.getDictionaryId();
        ApiTranslationDictionary dictionaryDetail = client.getDictionaryApi().dictionaryDetail(dictionaryId);
        System.out.println("根据词典Id查看词典详细信息:" + dictionaryDetail);
        //获取词典列表信息
        List<ApiTranslationDictionary> apiTranslationDictionaryList = client.getDictionaryApi().listDictionary();
        System.out.println("获取词典列表信息:" + apiTranslationDictionaryList);
        //更新词典信息
        ApiTranslationDictionary updateDictionary = client.getDictionaryApi().updateDictionary(dictionaryId, "词典02");
        System.out.println("更新词典信息:" + updateDictionary);
        //导入词典
        client.getDictionaryApi().importEntryFile(dictionaryId, new File("C:\\Users\\86195\\Desktop\\sdk\\词典.xlsx"));
        System.out.println("导入词典..");
        //导出词典
        client.getDictionaryApi().exportEntryFile(dictionaryId);
        System.out.println("导出词典..");
        //新增条目
        client.getDictionaryApi().insertEntry(dictionaryId, getAddTranslationDictionaryEntryRequest());
        System.out.println("新增一个条目:" + getAddTranslationDictionaryEntryRequest());
        //删除词典条目
        List entryNameList = new ArrayList<String>();
        entryNameList.add("This is an Entry");
        client.getDictionaryApi().deleteDictionaryEntry(dictionaryId, entryNameList);
        System.out.println("删除词典条目:" + entryNameList);
        //删除词典
        client.getDictionaryApi().deleteDictionary(Arrays.asList(dictionaryId));
        System.out.println("删除词典:" + dictionaryId);
    }

    public static AddTranslationDictionaryEntryRequest getAddTranslationDictionaryEntryRequest() {
        AddTranslationDictionaryEntryRequest addTranslationDictionaryEntryRequest = new AddTranslationDictionaryEntryRequest();
        addTranslationDictionaryEntryRequest.setEntryName("This is an Entry");
        addTranslationDictionaryEntryRequest.setNote("This is a note");
        addTranslationDictionaryEntryRequest.setReference("This is a reference");
        return addTranslationDictionaryEntryRequest;
    }
}
